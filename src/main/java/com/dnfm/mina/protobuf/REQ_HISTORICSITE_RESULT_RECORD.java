package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = 27156,
   cmd = 0
)
public class REQ_HISTORICSITE_RESULT_RECORD extends Message {
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 1,
      required = false
   )
   public String time;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 2,
      required = false
   )
   public Long gguid;
}
