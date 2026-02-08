package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_CHARACTER_INFO {
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
   public Integer job;
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
   public Integer level;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 6,
      required = false
   )
   public String name;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 7,
      required = false
   )
   public Long gguid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 8,
      required = false
   )
   public Integer posx;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 9,
      required = false
   )
   public Integer posy;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 10,
      required = false
   )
   public String gname;
   @Protobuf(
      order = 11
   )
   public List<PT_GUILD_SYMBOL> gsymbol;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 12,
      required = false
   )
   public Integer vip;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 13,
      required = false
   )
   public Integer vcreature;
   @Protobuf(
      order = 14
   )
   public List<PT_EQUIP> equiplist;
   @Protobuf(
      order = 15
   )
   public List<PT_AVATAR_ITEM> avatarlist;
   @Protobuf(
      order = 16
   )
   public List<PT_CREATURE> creaturelist;
   @Protobuf(
      order = 17
   )
   public List<PT_ARTIFACT> cartifactlist;
   @Protobuf(
      order = 18
   )
   public List<PT_SKIN_ITEM> skinlist;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 19,
      required = false
   )
   public Integer partydisturb;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 20,
      required = false
   )
   public Integer spoint;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 21,
      required = false
   )
   public Integer adventureunionlevel;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 22,
      required = false
   )
   public Long adventureunionexp;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 23,
      required = false
   )
   public Long partyguid;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 24,
      required = false
   )
   public Long partyleaderguid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 25,
      required = false
   )
   public Integer type;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 26,
      required = false
   )
   public Integer pvpstargrade;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 27,
      required = false
   )
   public Integer pvpstarcount;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 28,
      required = false
   )
   public Boolean blackdiamond;
   @Protobuf(
      fieldType = FieldType.UINT32,
      order = 29,
      required = false
   )
   public Integer avatarVisibleFlags;
   @Protobuf(
      order = 30
   )
   public List<PT_EQUIP> equipskinlist;
   @Protobuf(
      order = 31
   )
   public List<PT_AVATAR_ITEM> avatarskinlist;
   @Protobuf(
      order = 32
   )
   public List<PT_AVATAR_ITEM> sdavatarlist;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 33,
      required = false
   )
   public Integer fairpvpstargrade;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 34,
      required = false
   )
   public Integer fairpvpstarcount;
}
