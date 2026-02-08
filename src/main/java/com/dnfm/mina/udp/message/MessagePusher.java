package com.dnfm.mina.udp.message;

import com.dnfm.mina.udp.Message;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessagePusher {
   static Logger logger = LoggerFactory.getLogger(MessagePusher.class);

   public static void pushMessage(IoSession session, Message message) {
      if (session != null && message != null && !session.isClosing()) {
         session.write(message);
      } else {
         if (session.isClosing()) {
         }

      }
   }
}
