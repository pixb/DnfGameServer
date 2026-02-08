package com.dnfm.mina;

import com.dnfm.game.role.model.Account;
import com.dnfm.game.role.model.Role;
import com.dnfm.game.utils.TimeUtil;
import com.dnfm.mina.cache.DataCache;
import com.dnfm.mina.cache.SessionUtils;
import com.dnfm.mina.message.IMessageDispatcher;
import com.dnfm.mina.protobuf.Message;
import com.dnfm.mina.session.SessionManager;
import com.dnfm.mina.session.SessionProperties;
import com.dnfm.mina.task.ThreadLocalUtil;
import java.io.IOException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.nutz.lang.Lang;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerSocketIoHandler extends IoHandlerAdapter {
   private static final Logger logger = LoggerFactory.getLogger(ServerSocketIoHandler.class);
   private static final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(16);
   private final IMessageDispatcher messageDispatcher;

   public ServerSocketIoHandler(IMessageDispatcher messageDispatcher) {
      this.messageDispatcher = messageDispatcher;
   }

   public void sessionOpened(IoSession session) {
   }

   public void sessionClosed(IoSession session) {
      Role role = SessionUtils.getRoleBySession(session);
      Account account = SessionUtils.getAccountBySession(session);
      if (role == null) {
         logger.error("UNREACH==sessionClosed role is null");
      } else {
         Integer lastNotiTransId = (Integer)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.NOTIFY_TRANS_ID, Integer.class);
         logger.error("saveNotiTransId=={}", lastNotiTransId);
         if (lastNotiTransId != null) {
            role.lastNotiTransId = lastNotiTransId;
            DataCache.AUCTION_ROLES.put(role.getUid(), role);
         }

         if (account == null) {
            logger.error("UNREACH==sessionClosed account is null");
         } else {
            DataCache.ONLINE_ACC_MAP.remove(account.getId());

            try {
               ThreadLocalUtil.addLocalTask((Role)role, () -> this.onPlayerLogout(role, account));
            } catch (Exception e) {
               logger.error("sessionClosed==ERROR=={}", Lang.getStackTrace(e));
            }

            logger.error("sessionClosed==" + session.getRemoteAddress());
         }
      }
   }

   private void onPlayerLogout(Role role, Account account) {
      synchronized(account) {
         IoSession nowSession = SessionUtils.getSessionBy(role.getUid());
         if (nowSession != null) {
            role.setLastlogout(TimeUtil.currS());
            DataCache.ONLINE_ROLES.remove(role.getUid());
            logger.error("removeSession==uid=={} ", role.getUid());
            SessionManager.INSTANCE.unRegisterSession(role.getUid());
         }

      }
   }

   public void sessionCreated(IoSession session) {
      session.setAttributeIfAbsent(SessionProperties.DISTRIBUTE_KEY, SessionManager.INSTANCE.getNextDistributeKey());
      logger.error("新客户端连接=={}", session.getRemoteAddress());
   }

   public void messageReceived(IoSession session, Object data) {
      Message message = (Message)data;
      this.messageDispatcher.dispatch(session, message);
   }

   public void exceptionCaught(IoSession session, Throwable cause) {
      logger.error("exceptionCaught sessionId={} exception-{}", session.getId(), Lang.getStackTrace(cause));
      if (cause instanceof IOException) {
         session.closeNow();
      } else {
         Role role = SessionUtils.getRoleBySession(session);
         if (role != null) {
            logger.error("exceptionCaught userId={} username-{}", role.getRoleId(), role.getName());
         }

         logger.error("exceptionCaught sessionId={} exception-{}", session.getId(), Lang.getStackTrace(cause));
      }

   }
}
