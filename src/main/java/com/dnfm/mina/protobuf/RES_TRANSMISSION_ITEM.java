package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = 14046,
   cmd = 1
)
public class RES_TRANSMISSION_ITEM extends Message {
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
      order = 3,
      required = false
   )
   public PT_CONTENTS_REWARD_INFO rewardinfo;
   @Protobuf(
      order = 4,
      required = false
   )
   public PT_REMOVEITEMS removeitems;
   @Protobuf(
      order = 5,
      required = false
   )
   public PT_EQUIP metarialitem;
   @Protobuf(
      order = 6,
      required = false
   )
   public PT_EQUIP targetitem;
}
