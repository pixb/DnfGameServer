package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = -1,
   cmd = 0
)
public class REQ_CONTENTS_REWARD extends Message {
   @Protobuf(
      order = 1
   )
   public List<PT_ITEM> items;
   @Protobuf(
      order = 2,
      required = false
   )
   public PT_CURRENCY_REWARD_INFO currency;
   @Protobuf(
      order = 3,
      required = false
   )
   public PT_TICKET_REWARD_INFO ticket;
}
