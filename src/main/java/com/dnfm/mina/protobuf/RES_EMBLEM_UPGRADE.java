package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = 14023,
   cmd = 1
)
public class RES_EMBLEM_UPGRADE extends Message {
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
   public Integer index;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer trycount;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer success;
   @Protobuf(
      order = 5,
      required = false
   )
   public PT_REMOVEITEMS removeitems;
   @Protobuf(
      order = 6,
      required = false
   )
   public PT_CONTENTS_REWARD_INFO rewards;
}
