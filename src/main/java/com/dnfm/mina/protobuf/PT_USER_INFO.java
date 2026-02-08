package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_USER_INFO {
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
   public Integer playerid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer objectgroupid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer cobjectid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 5,
      required = false
   )
   public Integer performancerating;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 6,
      required = false
   )
   public Integer job;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 7,
      required = false
   )
   public Integer level;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 8,
      required = false
   )
   public Integer exp;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 9,
      required = false
   )
   public Integer growtype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 10,
      required = false
   )
   public Integer secgrowtype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 11,
      required = false
   )
   public Integer hellticket;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 12,
      required = false
   )
   public String name;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 13,
      required = false
   )
   public String gname;
   @Protobuf(
      order = 14
   )
   public List<PT_GUILD_SYMBOL> gsymbol;
   @Protobuf(
      order = 15
   )
   public List<PT_SYSTEM_BUFF_APPENDAGE> appendages;
   @Protobuf(
      order = 16,
      required = false
   )
   public PT_EQUIP_LIST equiplist;
   @Protobuf(
      order = 17,
      required = false
   )
   public PT_SKILLS skilllist;
   @Protobuf(
      order = 18,
      required = false
   )
   public PT_ALL_SKILL_SLOT skillslot;
   @Protobuf(
      order = 19,
      required = false
   )
   public PT_MATERIAL_LIST material;
   @Protobuf(
      order = 20,
      required = false
   )
   public PT_CONSUME_LIST consume;
   @Protobuf(
      order = 21,
      required = false
   )
   public ENUM_TEAM.T teamtype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 22,
      required = false
   )
   public Integer adventureunionlevel;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 23,
      required = false
   )
   public Long adventureunionexp;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 24,
      required = false
   )
   public Long time;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 25,
      required = false
   )
   public Integer pvpstarcount;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 26,
      required = false
   )
   public Integer pvpstargrade;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 27,
      required = false
   )
   public Long customdata;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 28,
      required = false
   )
   public Integer world;
   @Protobuf(
      order = 29
   )
   public List<PT_MONEY_ITEM> currency;
   @Protobuf(
      order = 30
   )
   public List<PT_MONEY_ITEM> accountcurrency;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 31,
      required = false
   )
   public Integer specialcharactertype;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 32,
      required = false
   )
   public String characoptionsyncdata;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 33,
      required = false
   )
   public String accountoptionsyncdata;
   @Protobuf(
      fieldType = FieldType.UINT32,
      order = 34,
      required = false
   )
   public Integer avatarVisibleFlags;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 35,
      required = false
   )
   public Integer equipscore;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 36,
      required = false
   )
   public Integer chivalrygrade;
   @Protobuf(
      order = 37
   )
   public List<PT_EQUIP> equipitems;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 38,
      required = false
   )
   public Integer creditscore;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 39,
      required = false
   )
   public String adventureunionname;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 40,
      required = false
   )
   public Integer dice;
   @Protobuf(
      order = 41,
      required = false
   )
   public PT_CREATURE_LEARN_SKILL_INFOS creturecommunionskillinfos;
}
