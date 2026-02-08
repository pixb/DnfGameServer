package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = 10132,
   cmd = 0
)
public class REQ_MESSAGE_ASSISTANT_SET_SWITCH extends Message {
   @Protobuf(
      order = 1,
      required = false
   )
   public ENUM_MESSAGE_ASSISTANT_SWITCH_STATUS.T switchstatus;
}
