package com.dnfm.game.utils;

import com.dnfm.mina.cache.DataCache;
import com.dnfm.mina.message.MessagePusher;
import com.dnfm.mina.protobuf.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SenderUtils {
   private static final Logger logger = LoggerFactory.getLogger(SenderUtils.class);

   public static void brodCastMessage(Message message, String type) {
      if (type.equals("npc")) {
         DataCache.ONLINE_ROLES.values().forEach((role) -> MessagePusher.pushMessage(role, message));
      }

   }

   public static void sendAllOnlinePlayers(Message message) {
      DataCache.ONLINE_ROLES.values().forEach((role) -> MessagePusher.pushMessage(role, message));
   }

   public static void pushChatMessage(Message respChat) {
      DataCache.ONLINE_ROLES.values().forEach((role) -> MessagePusher.pushMessage(role, respChat));
   }

   public static void pushAreaChatMessage(int town, int area, Message respChat) {
      DataCache.ONLINE_ROLES.values().forEach((onlineRole) -> {
         int rtown = onlineRole.getPos().getTown();
         int rarea = onlineRole.getPos().getArea();
         if (rtown == town && rarea == area) {
            MessagePusher.pushMessage(onlineRole, respChat);
         }
      });
   }
}
