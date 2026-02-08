package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = 22511,
   cmd = 0
)
public class REQ_VERIFICATION_BY_SYNC_DIFFERENCE extends Message {
   @Protobuf(
      fieldType = FieldType.BYTES,
      order = 1,
      required = false
   )
   public byte[] damagehistory;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer stage;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 3,
      required = false
   )
   public Long dungeonguid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer type;
}
