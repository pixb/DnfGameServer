package com.dnfm.mina.client;

import com.dnfm.mina.message.IMessageDispatcher;
import com.dnfm.mina.protobuf.Message;
import com.dnfm.mina.session.SessionManager;
import com.dnfm.mina.session.SessionProperties;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientHandler extends IoHandlerAdapter {
   Logger logger = LoggerFactory.getLogger(ClientHandler.class);
   private final IMessageDispatcher messageDispatcher;

   public ClientHandler(IMessageDispatcher messageDispatcher) {
      this.messageDispatcher = messageDispatcher;
   }

   public void sessionClosed(IoSession session) throws Exception {
      super.sessionClosed(session);
   }

   public void sessionCreated(IoSession session) throws Exception {
      session.setAttributeIfAbsent(SessionProperties.DISTRIBUTE_KEY, SessionManager.INSTANCE.getNextDistributeKey());
   }

   public void messageReceived(IoSession session, Object data) throws Exception {
      Message message = (Message)data;
      this.messageDispatcher.dispatch(session, message);
      this.logger.info("收到消息");
   }

   public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
   }

   public void messageSent(IoSession session, Object message) throws Exception {
   }
}
