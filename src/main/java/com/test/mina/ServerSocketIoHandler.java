package com.test.mina;

import com.dnfm.game.role.model.Role;
import com.dnfm.mina.cache.SessionUtils;
import com.test.mina.message.IMessageDispatcher;
import com.test.mina.session.SessionManager;
import com.test.mina.session.SessionProperties;
import com.test.mina.udp.Message;
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
      Global.ALL_SESSIONS.add(session);
   }

   public void sessionClosed(IoSession session) {
      Global.ALL_SESSIONS.remove(session);
      logger.error("sessionClosed==" + session.getRemoteAddress());
   }

   public void sessionCreated(IoSession session) {
      session.setAttributeIfAbsent(SessionProperties.DISTRIBUTE_KEY, SessionManager.INSTANCE.getNextDistributeKey());
      SessionManager.INSTANCE.add2Anonymous(session);
      logger.error("新客户端连接=={}", session.getRemoteAddress());
   }

   public void messageReceived(IoSession session, Object data) {
      Message message = (Message)data;
      this.messageDispatcher.dispatch(session, message);
   }

   public void exceptionCaught(IoSession session, Throwable cause) {
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
