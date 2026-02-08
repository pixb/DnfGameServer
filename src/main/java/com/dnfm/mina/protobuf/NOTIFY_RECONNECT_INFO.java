package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = 10024,
   cmd = 1
)
public class NOTIFY_RECONNECT_INFO extends Message {
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
   public Long rpguid;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 3,
      required = false
   )
   public Boolean keepraidparty;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 4,
      required = false
   )
   public Long partyguid;
   @Protobuf(
      fieldType = FieldType.UINT32,
      order = 5,
      required = false
   )
   public Integer town;
   @Protobuf(
      fieldType = FieldType.UINT32,
      order = 6,
      required = false
   )
   public Integer area;
}
