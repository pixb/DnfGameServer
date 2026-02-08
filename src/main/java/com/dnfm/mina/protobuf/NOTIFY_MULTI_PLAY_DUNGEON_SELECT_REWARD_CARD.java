package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class NOTIFY_MULTI_PLAY_DUNGEON_SELECT_REWARD_CARD {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer type;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer index;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer gold;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 5,
      required = false
   )
   public Integer tera;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 6,
      required = false
   )
   public Integer earngold;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 7,
      required = false
   )
   public Integer premiumindex;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 8,
      required = false
   )
   public Long charguid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 9,
      required = false
   )
   public Integer qqvipinfo;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 10,
      required = false
   )
   public Integer gamecenter;
   @Protobuf(
      order = 11,
      required = false
   )
   public PT_ITEMS items;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 12,
      required = false
   )
   public Boolean blackdiamond;
}
