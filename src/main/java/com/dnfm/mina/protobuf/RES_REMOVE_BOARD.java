package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = 12502,
   cmd = 1
)
public class RES_REMOVE_BOARD extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      order = 2,
      required = false
   )
   public PT_BOARD_INFO removednote;
   @Protobuf(
      fieldType = FieldType.FLOAT,
      order = 3,
      required = false
   )
   public Float avgscore;
}
