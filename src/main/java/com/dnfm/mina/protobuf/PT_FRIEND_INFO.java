package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_FRIEND_INFO {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer world;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 2,
      required = false
   )
   public Long fguid;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 3,
      required = false
   )
   public String name;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer level;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 5,
      required = false
   )
   public Integer job;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 6,
      required = false
   )
   public Integer growtype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 7,
      required = false
   )
   public Integer secondgrowtype;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 8,
      required = false
   )
   public Long date;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 9,
      required = false
   )
   public Integer online;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 10,
      required = false
   )
   public Long lastlogout;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 11,
      required = false
   )
   public Integer channel;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 12,
      required = false
   )
   public Integer closenessscore;
   @Protobuf(
      order = 13
   )
   public List<PT_FRIEND_DAILY_CLOSENESS_INFO> dailyContents;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 14,
      required = false
   )
   public Long sendtime;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 15,
      required = false
   )
   public Long recvtime;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 16,
      required = false
   )
   public Long fromsendtime;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 17,
      required = false
   )
   public Integer status;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 18,
      required = false
   )
   public String msg;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 19,
      required = false
   )
   public Integer route;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 20,
      required = false
   )
   public String openid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 21,
      required = false
   )
   public Integer launchinfo;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 22,
      required = false
   )
   public Integer vip;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 23,
      required = false
   )
   public Integer creditscore;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 24,
      required = false
   )
   public Integer characterframe;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 25,
      required = false
   )
   public String profileurl;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 26,
      required = false
   )
   public Long charm;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 27,
      required = false
   )
   public Integer remainCharm;
   @Protobuf(
      order = 28,
      required = false
   )
   public ENUM_MESSAGE_ASSISTANT_SWITCH_STATUS.T switchstatus;
}
