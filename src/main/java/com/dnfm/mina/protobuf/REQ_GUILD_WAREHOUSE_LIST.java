package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = -1,
   cmd = 0
)
public class REQ_GUILD_WAREHOUSE_LIST extends Message {
   @Protobuf(
      order = 1,
      required = false
   )
   public PT_GUILD_WAREHOUSE_PARAMETER parameter;
}
