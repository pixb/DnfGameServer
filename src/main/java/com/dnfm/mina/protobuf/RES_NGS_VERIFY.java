package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = 10044,
   cmd = 1
)
public class RES_NGS_VERIFY extends Message {
   @Protobuf(
      fieldType = FieldType.BYTES,
      order = 1,
      required = false
   )
   public byte[] verifydata;
}
