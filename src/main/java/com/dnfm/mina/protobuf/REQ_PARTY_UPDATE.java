package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = 14420,
   cmd = 0
)
public class REQ_PARTY_UPDATE extends Message {
   @Protobuf(
      order = 1,
      required = false
   )
   public PT_UPDATE_PARTY info;
}
