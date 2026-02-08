package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class PT_GROUP_MEMBER {
   public Integer playerid;
   public Integer objectgroupid;
   public Boolean ready;
   public Boolean waiting;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 1,
      required = false
   )
   public Long charguid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer equipscore;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer job;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer level;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 5,
      required = false
   )
   public String name;
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
      fieldType = FieldType.STRING,
      order = 8,
      required = false
   )
   public String ip;
   @Protobuf(
      fieldType = FieldType.UINT32,
      order = 9,
      required = false
   )
   public Integer port;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 10,
      required = false
   )
   public Long gguid;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 11,
      required = false
   )
   public String gname;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 12,
      required = false
   )
   public Boolean partyleader;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 13,
      required = false
   )
   public Integer fatigue;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 14,
      required = false
   )
   public Integer world;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 15,
      required = false
   )
   public Integer creditscore;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 16,
      required = false
   )
   public Integer chivalrygrade;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 17,
      required = false
   )
   public Integer characterframe;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 18,
      required = false
   )
   public Long disconnecttime;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 19,
      required = false
   )
   public Integer specialcategoryitemindex;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 20,
      required = false
   )
   public Boolean pvpready;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 21,
      required = false
   )
   public Integer pvpcharindex;
   @Protobuf(
      order = 22,
      required = false
   )
   public ENUM_TEAM.T teamtype;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 23,
      required = false
   )
   public Long reservekicktime;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 24,
      required = false
   )
   public Integer seq;
   @Protobuf(
      order = 25,
      required = false
   )
   public PT_CUSTOM_DATA customdata;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 26,
      required = false
   )
   public Long marriageguid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 27,
      required = false
   )
   public Integer switchstatus;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 28,
      required = false
   )
   public Integer ping;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 29,
      required = false
   )
   public Boolean isInvite;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 30,
      required = false
   )
   public Integer inviteblockflag;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 31,
      required = false
   )
   public Integer mineworld;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 32,
      required = false
   )
   public Boolean iskeyboard;
}
