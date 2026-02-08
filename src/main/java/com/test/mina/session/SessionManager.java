package com.test.mina.session;

import com.test.common.spring.SpringUtils;
import com.test.logs.LoggerUtils;
import com.test.mina.FireWallConfig;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;

public enum SessionManager {
   INSTANCE;

   private AtomicInteger distributeKeyGenerator = new AtomicInteger(1);
   private ConcurrentMap<Integer, IoSession> player2sessions = new ConcurrentHashMap();
   private ConcurrentMap<Long, IoSession> anonymouSessions = new ConcurrentHashMap();

   public void registerSession(int roleId, IoSession session) {
   }

   public void unRegisterSession(int playerId) {
      this.player2sessions.remove(playerId);
   }

   public void add2Anonymous(IoSession session) {
      this.anonymouSessions.putIfAbsent(session.getId(), session);
   }

   public void removeFromAnonymous(IoSession session) {
      this.anonymouSessions.remove(session.getId());
   }

   public IoSession getSessionBy(int playerId) {
      return (IoSession)this.player2sessions.get(playerId);
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
