package com.dnfm.mina.udp.session;

import org.apache.mina.core.session.AttributeKey;

public interface SessionProperties {
   AttributeKey CODEC_CONTEXT = new AttributeKey(SessionProperties.class, "CONTEXT_KEY");
   AttributeKey DISTRIBUTE_KEY = new AttributeKey(SessionProperties.class, "DISTRIBUTE_KEY");
   AttributeKey FLOOD = new AttributeKey(SessionProperties.class, "FLOOD");
   AttributeKey USER_ID = new AttributeKey(SessionProperties.class, "USER_ID");
   AttributeKey USER_ID2 = new AttributeKey(SessionProperties.class, "USER_ID2");
   AttributeKey ROOM_ID = new AttributeKey(SessionProperties.class, "ROOM_ID");
   AttributeKey ROOM_SERVER_ID = new AttributeKey(SessionProperties.class, "ROOM_SERVER_ID");
   AttributeKey COMMUNICATION_TYPE = new AttributeKey(SessionProperties.class, "COMMUNICATION_TYPE");
   AttributeKey TMP_SESSION_ID = new AttributeKey(SessionProperties.class, "TMP_SESSION_ID");
   AttributeKey USER_SESSION_MAP = new AttributeKey(SessionProperties.class, "USER_SESSION_MAP");
   AttributeKey ACCESS_TOKEN = new AttributeKey(SessionProperties.class, "ACCESS_TOKEN");
   AttributeKey PK_PLAYER_ID = new AttributeKey(SessionProperties.class, "PK_PLAYER_ID");
   AttributeKey SESSION_ID = new AttributeKey(SessionProperties.class, "SESSION_ID");
   AttributeKey CURRENT_FRAME_ID = new AttributeKey(SessionProperties.class, "CURRENT_FRAME_ID");
}
