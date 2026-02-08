package com.dnfm.mina.message;

import com.dnfm.game.role.model.Role;
import com.dnfm.mina.cache.SessionUtils;
import com.dnfm.mina.protobuf.Message;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessagePusher {
   static Logger logger = LoggerFactory.getLogger(MessagePusher.class);

   public static void pushMessage(Role role, Message message) {
      if (role != null) {
         pushMessage(role.getId(), message);
      }
   }

   public static void pushMessages(Role role, Message... messages) {
      if (role != null) {
         for(Message message : messages) {
            pushMessage(role.getId(), message);
         }

      }
   }

   private static void pushMessage(long uid, Message message) {
      IoSession session = SessionUtils.getSessionBy(uid);
      if (session != null) {
         pushMessage(session, message);
      }

   }

   public static void pushMessage(IoSession session, Message message) {
      if (session != null && message != null && !session.isClosing()) {
         session.write(message);
      } else {
         if (session.isClosing()) {
         }

      }
   }
}
