package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 11045,
   cmd = 1
)
public class RES_DUNGEON_AUTOCLEAR extends Message {
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
   public Integer level;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer fatigue;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer consumefatigue;
   @Protobuf(
      order = 5,
      required = false
   )
   public PT_EXP_DETAILINFO clearexp;
   @Protobuf(
      order = 6,
      required = false
   )
   public PT_TICKET ticket;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 7,
      required = false
   )
   public Integer adventureticket;
   @Protobuf(
      order = 8
   )
   public List<PT_MONEY_ITEM> consumecurrency;
   @Protobuf(
      order = 9
   )
   public List<PT_MONEY_ITEM> consumeaccountcurrency;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 10,
      required = false
   )
   public Integer adventureunionlevel;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 11,
      required = false
   )
   public Long adventureunionexp;
   @Protobuf(
      order = 12,
      required = false
   )
   public PT_CONTENTS_REWARD_INFO rewards;
}
