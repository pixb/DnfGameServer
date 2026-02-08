package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = 14018,
   cmd = 0
)
public class REQ_EMBLEM_EQUIP extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer type;
   @Protobuf(
      order = 2,
      required = false
   )
   public PT_EMBLEM_REQUEST emblem;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 3,
      required = false
   )
   public Long guid;
}
