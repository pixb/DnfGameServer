package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = 11204,
   cmd = 0
)
public class REQ_ATTACK_SQUAD_SEND_JOIN extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer index;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 2,
      required = false
   )
   public Long rpguid;
}
