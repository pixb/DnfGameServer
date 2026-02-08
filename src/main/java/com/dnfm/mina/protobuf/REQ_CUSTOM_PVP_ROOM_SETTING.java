package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = 32005,
   cmd = 0
)
public class REQ_CUSTOM_PVP_ROOM_SETTING extends Message {
   @Protobuf(
      order = 1,
      required = false
   )
   public PT_CUSTOM_PVP_ROOM_SETTING customdata;
}
