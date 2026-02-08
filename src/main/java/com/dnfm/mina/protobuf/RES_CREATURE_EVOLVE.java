package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = -1,
   cmd = 1
)
public class RES_CREATURE_EVOLVE extends Message {
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
   public Long creature;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 3,
      required = false
   )
   public Long material;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer exp;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 5,
      required = false
   )
   public Integer newcreatureindex;
   @Protobuf(
      order = 6
   )
   public List<PT_MONEY_ITEM> currency;
   @Protobuf(
      order = 7,
      required = false
   )
   public PT_CONTENTS_REWARD_INFO rewards;
   @Protobuf(
      order = 8,
      required = false
   )
   public PT_REMOVEITEMS removeitems;
}
