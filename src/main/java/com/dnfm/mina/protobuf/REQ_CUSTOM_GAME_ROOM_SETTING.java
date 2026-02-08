package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = 13071,
   cmd = 0
)
public class REQ_CUSTOM_GAME_ROOM_SETTING extends Message {
   @Protobuf(
      order = 1,
      required = false
   )
   public PT_CUSTOM_DATA customdata;
}
