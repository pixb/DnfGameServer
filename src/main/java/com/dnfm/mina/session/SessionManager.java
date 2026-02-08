package com.dnfm.mina.session;

import com.dnfm.common.db.Db4CommonService;
import com.dnfm.common.spring.SpringUtils;
import com.dnfm.common.start.GameServer;
import com.dnfm.game.auction.model.AuctionCache;
import com.dnfm.game.auction.model.AuctionItem;
import com.dnfm.game.role.model.Role;
import com.dnfm.game.utils.TimeUtil;
import com.dnfm.logs.LoggerFunction;
import com.dnfm.logs.LoggerUtils;
import com.dnfm.mina.FireWallConfig;
import com.dnfm.mina.cache.SessionUtils;
import com.dnfm.mina.protobuf.PT_AUCTION_EQUIP;
import com.dnfm.mina.protobuf.PT_AUCTION_STACKABLE;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.mina.core.future.CloseFuture;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum SessionManager {
   INSTANCE;

   private static final Logger log = LoggerFactory.getLogger(SessionManager.class);
   public ConcurrentMap<Long, IoSession> player2sessions = new ConcurrentHashMap();
   public ConcurrentMap<Long, IoSession> anonymouSessions = new ConcurrentHashMap();
   private final AtomicInteger distributeKeyGenerator = new AtomicInteger(1);

   public void registerSession(long charguid, IoSession session) {
      session.setAttribute(SessionProperties.PLAYER_ID, charguid);
      this.player2sessions.put(charguid, session);
   }

   public void unRegisterSession(long charguid) {
      this.player2sessions.remove(charguid);
   }

   public void add2Anonymous(IoSession session) {
      this.anonymouSessions.putIfAbsent(session.getId(), session);
   }

   public void removeFromAnonymous(IoSession session) {
      this.anonymouSessions.remove(session.getId());
   }

   public long getPlayerIdBy(IoSession session) {
      return session != null && session.containsAttribute(SessionProperties.PLAYER_ID) ? (Long)this.getSessionAttr(session, SessionProperties.PLAYER_ID, Long.class) : 0L;
   }

   public IoSession getSessionBy(long charguid) {
      return (IoSession)this.player2sessions.get(charguid);
   }

   public <T> T getSessionAttr(IoSession session, AttributeKey attrKey, Class<T> attrType) {
      return (T)session.getAttribute(attrKey);
   }

   public int getNextDistributeKey() {
      return this.distributeKeyGenerator.getAndIncrement();
   }

   public String getRemoteIp(IoSession session) {
      return ((InetSocketAddress)session.getRemoteAddress()).getAddress().getHostAddress();
   }

   public void closeSession(final IoSession session) {
      if (session != null) {
         Role role = SessionUtils.getRoleBySession(session);
         final String name = role != null ? role.getName() : "";
         final Logger logger = LoggerFunction.MONOIOR.getLogger();
         if (session.isConnected() && !session.isClosing()) {
            CloseFuture future = session.closeNow();
            future.addListener(new IoFutureListener<IoFuture>() {
               public void operationComplete(IoFuture future) {
                  logger.error("session[{}] closed complete,player[{}]", session.getId(), name);
               }
            });
         }

         if (role != null) {
            SpringUtils.getRoleService().addOffline(role);
         }

      }
   }

   public void killAllPlayers() {
      GameServer gameServer = (GameServer)SpringUtils.getBean(GameServer.class);
      Map<Long, IoSession> managedSessions = gameServer.getAcceptor().getManagedSessions();
      log.error("killAllPlayers==持久化拍卖行信息==managedSessions={}", managedSessions.size());
      Map<Integer, Map<Integer, Map<Long, PT_AUCTION_STACKABLE>>> indexStackableMap = AuctionCache.indexStackableMap;
      Map<Integer, Map<Integer, Map<Long, PT_AUCTION_EQUIP>>> indexEquipMap = AuctionCache.indexEquipMap;

      for(Map.Entry<Integer, Map<Integer, Map<Long, PT_AUCTION_STACKABLE>>> entry : indexStackableMap.entrySet()) {
         int index = (Integer)entry.getKey();
         Map<Integer, Map<Long, PT_AUCTION_STACKABLE>> map = (Map)entry.getValue();

         for(Map.Entry<Integer, Map<Long, PT_AUCTION_STACKABLE>> entry1 : map.entrySet()) {
            int price = (Integer)entry1.getKey();
            Map<Long, PT_AUCTION_STACKABLE> auidMap = (Map)entry1.getValue();

            for(PT_AUCTION_STACKABLE pt : auidMap.values()) {
               AuctionItem auctionItem = new AuctionItem();
               auctionItem.setAuid(pt.auid);
               auctionItem.setType(1);
               auctionItem.setPrice(pt.price);
               auctionItem.setIndex1(index);
               auctionItem.setStackable(pt);
               if (pt.enddate > TimeUtil.currS()) {
                  Db4CommonService.getInstance().add2Queue(auctionItem);
               }
            }
         }
      }

      for(Map.Entry<Integer, Map<Integer, Map<Long, PT_AUCTION_EQUIP>>> entry : indexEquipMap.entrySet()) {
         int index = (Integer)entry.getKey();
         Map<Integer, Map<Long, PT_AUCTION_EQUIP>> map = (Map)entry.getValue();

         for(Map.Entry<Integer, Map<Long, PT_AUCTION_EQUIP>> entry1 : map.entrySet()) {
            int price = (Integer)entry1.getKey();
            Map<Long, PT_AUCTION_EQUIP> auidMap = (Map)entry1.getValue();

            for(PT_AUCTION_EQUIP equip : auidMap.values()) {
               AuctionItem auctionItem = new AuctionItem();
               auctionItem.setAuid(equip.auid);
               auctionItem.setType(2);
               auctionItem.setPrice(equip.price);
               auctionItem.setIndex1(index);
               auctionItem.setEquip(equip);
               if (equip.enddate > TimeUtil.currS()) {
                  Db4CommonService.getInstance().add2Queue(auctionItem);
               }
            }
         }
      }

      for(IoSession session : managedSessions.values()) {
         try {
            this.closeSession(session);
         } catch (Exception e) {
            LoggerUtils.error("", e);
         }
      }

   }

   private class AnonymousCheck implements Runnable {
      public void run() {
         while(true) {
            try {
               long now = System.currentTimeMillis();
               FireWallConfig config = (FireWallConfig)SpringUtils.getBean(FireWallConfig.class);
               long aliveTime = (long)config.getAnonymousAliveMilli();
               List<IoSession> toRemove = new ArrayList();

               for(Map.Entry<Long, IoSession> entry : SessionManager.this.anonymouSessions.entrySet()) {
                  IoSession session = (IoSession)entry.getValue();
                  if (now - session.getCreationTime() > aliveTime) {
                     toRemove.add(session);
                  }
               }

               for(IoSession var12 : toRemove) {
                  ;
               }

               Thread.sleep(100L);
            } catch (Exception e) {
               LoggerUtils.error("", e);
            }
         }
      }
   }
}
