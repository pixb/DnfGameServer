package com.dnfm.mina.session;

import org.apache.mina.core.session.AttributeKey;

public interface SessionProperties {
   AttributeKey CUR_HELL_STAGE_GUID = new AttributeKey(SessionProperties.class, "CUR_HELL_STAGE_GUID");
   AttributeKey CUR_HELL_SEL_LIST = new AttributeKey(SessionProperties.class, "CUR_HELL_SEL_LIST");
   AttributeKey DEVICE_TYPE = new AttributeKey(SessionProperties.class, "DEVICE_TYPE");
   AttributeKey CODEC_CONTEXT = new AttributeKey(SessionProperties.class, "CONTEXT_KEY");
   AttributeKey DISTRIBUTE_KEY = new AttributeKey(SessionProperties.class, "DISTRIBUTE_KEY");
   AttributeKey PLAYER_ID = new AttributeKey(SessionProperties.class, "PLAYER_ID");
   AttributeKey PLAYER_UID = new AttributeKey(SessionProperties.class, "PLAYER_UID");
   AttributeKey PLAYER = new AttributeKey(SessionProperties.class, "PLAYER");
   AttributeKey ACCOUNT = new AttributeKey(SessionProperties.class, "ACCOUNT");
   AttributeKey PLAYER_NAME = new AttributeKey(SessionProperties.class, "PLAYER_NAME");
   AttributeKey FLOOD = new AttributeKey(SessionProperties.class, "FLOOD");
   AttributeKey MODULE_COUNTER = new AttributeKey(SessionProperties.class, "MODULE_COUNTER");
   AttributeKey ACCOUNT_SID = new AttributeKey(SessionProperties.class, "ACCOUNT_SID");
   AttributeKey CLIENT_STATUS = new AttributeKey(SessionProperties.class, "CLIENT_STATUS");
   AttributeKey HEART_MAP = new AttributeKey(SessionProperties.class, "HEARTBEAT_MAP");
   AttributeKey SEQ = new AttributeKey(SessionProperties.class, "SEQ");
   AttributeKey TRANS_MAP = new AttributeKey(SessionProperties.class, "TRANS_MAP");
   AttributeKey CHAR_MAP = new AttributeKey(SessionProperties.class, "CHAR_MAP");
   AttributeKey ACCOUNT_OPENID = new AttributeKey(SessionProperties.class, "ACCOUNT_OPENID");
   AttributeKey ACCOUNT_KEY = new AttributeKey(SessionProperties.class, "ACCOUNT_KEY");
   AttributeKey AUTH_KEY = new AttributeKey(SessionProperties.class, "AUTH_KEY");
   AttributeKey AUTH_KEY_BATTLE = new AttributeKey(SessionProperties.class, "AUTH_KEY_BATTLE");
   AttributeKey CLEAR_STATUS = new AttributeKey(SessionProperties.class, "CLEAR_STATUS");
   AttributeKey DUNGEON_TICKETS_STATUS = new AttributeKey(SessionProperties.class, "DUNGEON_TICKETS_STATUS");
   AttributeKey LOCAL_REWARDS_STATUS = new AttributeKey(SessionProperties.class, "LOCAL_REWARDS_STATUS");
   AttributeKey NOTIFY_TRANS_ID = new AttributeKey(SessionProperties.class, "NOTIFY_TRANS_ID");
   AttributeKey LOOTING_STATUS = new AttributeKey(SessionProperties.class, "LOOTING_STATUS");
   AttributeKey MATCHING_GUID = new AttributeKey(SessionProperties.class, "MATCHING_GUID");
   AttributeKey PARTY_NAME = new AttributeKey(SessionProperties.class, "PARTY_NAME");
   AttributeKey LEADER_ROLE = new AttributeKey(SessionProperties.class, "LEADER_ROLE");
   AttributeKey DUNGEON_GUID = new AttributeKey(SessionProperties.class, "DUNGEON_GUID");
   AttributeKey DUNGEON_TYPE = new AttributeKey(SessionProperties.class, "DUNGEON_TYPE");
   AttributeKey START_STAGE_GUID = new AttributeKey(SessionProperties.class, "START_STAGE_GUID");
   AttributeKey CONSUME_FATIGUE = new AttributeKey(SessionProperties.class, "CONSUME_FATIGUE");
   AttributeKey MONSTER_EXP = new AttributeKey(SessionProperties.class, "MONSTER_EXP");
   AttributeKey CUR_DUNGEON_INDEX = new AttributeKey(SessionProperties.class, "CUR_DUNGEON_INDEX");
}
