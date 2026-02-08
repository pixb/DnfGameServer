package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class PT_MESSENGER_FRIEND_INFO {
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 1,
      required = false
   )
   public Long charguid;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 2,
      required = false
   )
   public String name;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer level;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer job;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 5,
      required = false
   )
   public Integer growtype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 6,
      required = false
   )
   public Integer secgrowtype;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 7,
      required = false
   )
   public String openid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 8,
      required = false
   )
   public Integer world;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 9,
      required = false
   )
   public Integer creditscore;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 10,
      required = false
   )
   public Long lastlogin;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 11,
      required = false
   )
   public Long lastlogout;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 12,
      required = false
   )
   public Integer platform;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 13,
      required = false
   )
   public Integer status;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 14,
      required = false
   )
   public Integer online;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 15,
      required = false
   )
   public Long sendtime;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 16,
      required = false
   )
   public Long recvtime;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 17,
      required = false
   )
   public Long fromsendtime;
   @Protobuf(
      fieldType = FieldType.UINT32,
      order = 18,
      required = false
   )
   public Integer platformserverid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 19,
      required = false
   )
   public Integer gamecenterinfo;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 20,
      required = false
   )
   public Integer qqVipInfo;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 21,
      required = false
   )
   public Integer chivalrygrade;
   @Protobuf(
      order = 22,
      required = false
   )
   public PT_SKIN_CHAT_INFO skinchatinfo;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 23,
      required = false
   )
   public Boolean isInvite;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 24,
      required = false
   )
   public Integer inviteblockflag;
}
