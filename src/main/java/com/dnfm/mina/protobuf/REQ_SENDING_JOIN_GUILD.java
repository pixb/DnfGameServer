package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = 27005,
   cmd = 0
)
public class REQ_SENDING_JOIN_GUILD extends Message {
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 1,
      required = false
   )
   public Long gguid;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 2,
      required = false
   )
   public Boolean quick;
}
