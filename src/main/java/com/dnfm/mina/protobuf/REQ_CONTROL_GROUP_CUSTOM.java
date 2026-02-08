package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = 13053,
   cmd = 0
)
public class REQ_CONTROL_GROUP_CUSTOM extends Message {
   @Protobuf(
      order = 1,
      required = false
   )
   public PT_CUSTOM_DATA customdata;
}
