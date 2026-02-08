package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = 10029,
   cmd = 0
)
public class REQ_DECIDE_INTRUDE extends Message {
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 1,
      required = false
   )
   public Boolean intrude;
}
