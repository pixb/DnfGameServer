package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_AI_CHARACTER_DETAIL_INFO {
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
   public Integer growtype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer secondgrowtype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 5,
      required = false
   )
   public Integer exp;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 6,
      required = false
   )
   public Integer hp;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 7,
      required = false
   )
   public Integer mp;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 8,
      required = false
   )
   public Integer level;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 9,
      required = false
   )
   public Integer job;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 10,
      required = false
   )
   public Integer equipscore;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 11,
      required = false
   )
   public Integer score;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 12,
      required = false
   )
   public Integer count;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 13,
      required = false
   )
   public Long date;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 14,
      required = false
   )
   public Integer spoint;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 15,
      required = false
   )
   public Integer adventureunionlevel;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 16,
      required = false
   )
   public Long adventureunionexp;
   @Protobuf(
      order = 17
   )
   public List<PT_SKILL> skilllist;
   @Protobuf(
      order = 18
   )
   public List<PT_EQUIP> equiplist;
   @Protobuf(
      order = 19
   )
   public List<PT_CREATURE> creaturelist;
   @Protobuf(
      order = 20
   )
   public List<PT_ARTIFACT> cartifactlist;
   @Protobuf(
      order = 21
   )
   public List<PT_AVATAR_ITEM> avatarlist;
   @Protobuf(
      order = 22
   )
   public List<PT_SKIN_ITEM> skinlist;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 23,
      required = false
   )
   public Integer world;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 24,
      required = false
   )
   public Long gguid;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 25,
      required = false
   )
   public String gname;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 26,
      required = false
   )
   public String gmastername;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 27,
      required = false
   )
   public Integer gmembergrade;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 28,
      required = false
   )
   public Long blackdiamond;
   @Protobuf(
      fieldType = FieldType.UINT32,
      order = 29,
      required = false
   )
   public Integer avatarVisibleFlags;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 30,
      required = false
   )
   public Integer gamecenterinfo;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 31,
      required = false
   )
   public Integer qqVipinfo;
   @Protobuf(
      order = 32
   )
   public List<PT_EQUIP> equipskinlist;
   @Protobuf(
      order = 33
   )
   public List<PT_AVATAR_ITEM> avatarskinlist;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 34,
      required = false
   )
   public Integer totallike;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 35,
      required = false
   )
   public Integer like;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 36,
      required = false
   )
   public Integer rank;
   @Protobuf(
      order = 37
   )
   public List<PT_CHIVALRY> info;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 38,
      required = false
   )
   public Integer communionlevel;
   @Protobuf(
      order = 39
   )
   public List<PT_AVATAR_ITEM> sdavatarlist;
}
