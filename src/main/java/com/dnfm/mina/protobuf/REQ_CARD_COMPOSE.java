package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 14065,
   cmd = 0
)
public class REQ_CARD_COMPOSE extends Message {
   @Protobuf(
      order = 1
   )
   public List<PT_CARD_COMPOSE> usercardlist;
}
