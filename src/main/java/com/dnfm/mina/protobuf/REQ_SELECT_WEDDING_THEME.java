package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = 10405,
   cmd = 0
)
public class REQ_SELECT_WEDDING_THEME extends Message {
   @Protobuf(
      order = 1,
      required = false
   )
   public PT_WEDDING_THEME theme;
}
