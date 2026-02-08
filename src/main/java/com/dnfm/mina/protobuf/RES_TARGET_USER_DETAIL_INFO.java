package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 10107,
   cmd = 1
)
public class RES_TARGET_USER_DETAIL_INFO extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 2,
      required = false
   )
   public Long guid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer world;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 4,
      required = false
   )
   public Long gguid;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 5,
      required = false
   )
   public String gmastername;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 6,
      required = false
   )
   public String name;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 7,
      required = false
   )
   public Integer exp;
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
   public Integer growtype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 11,
      required = false
   )
   public Integer secgrowtype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 12,
      required = false
   )
   public Integer equipscore;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 13,
      required = false
   )
   public Integer spoint;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 14,
      required = false
   )
   public Integer adventureunionlevel;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 15,
      required = false
   )
   public Long adventureunionexp;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 16,
      required = false
   )
   public Integer characlistcount;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 17,
      required = false
   )
   public String gname;
   @Protobuf(
      order = 18
   )
   public List<PT_GUILD_SYMBOL> gsymbol;
   @Protobuf(
      order = 19
   )
   public List<PT_EQUIP> equiplist;
   @Protobuf(
      order = 20
   )
   public List<PT_AVATAR_ITEM> avatarlist;
   @Protobuf(
      order = 21
   )
   public List<PT_CREATURE> creaturelist;
   @Protobuf(
      order = 22
   )
   public List<PT_ARTIFACT> cartifactlist;
   @Protobuf(
      order = 23
   )
   public List<PT_SKIN_ITEM> skinlist;
   @Protobuf(
      order = 24,
      required = false
   )
   public PT_BUFF_LIST bufflist;
   @Protobuf(
      order = 25,
      required = false
   )
   public PT_SKILL_INFO skill;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 26,
      required = false
   )
   public Integer gmembergrade;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 27,
      required = false
   )
   public Long blackdiamond;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 28,
      required = false
   )
   public Integer creditscore;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 29,
      required = false
   )
   public Integer gamecenterinfo;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 30,
      required = false
   )
   public Integer qqVipinfo;
   @Protobuf(
      fieldType = FieldType.UINT32,
      order = 31,
      required = false
   )
   public Integer avatarVisibleFlags;
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
      fieldType = FieldType.INT32,
      order = 39,
      required = false
   )
   public Integer fame;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 40,
      required = false
   )
   public Integer charm;
   @Protobuf(
      order = 41
   )
   public List<PT_AVATAR_ITEM> sdavatarlist;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 42,
      required = false
   )
   public Integer grade;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 43,
      required = false
   )
   public Integer gradeFair;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 44,
      required = false
   )
   public Boolean isadventureCondition;
}
