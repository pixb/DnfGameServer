package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = 18500,
   cmd = 0
)
public class REQ_ATTACH_CRACK extends Message {
   @Protobuf(
      order = 1,
      required = false
   )
   public PT_ATTACH_CRACK_REQUEST crack;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 2,
      required = false
   )
   public Long guid;
}
