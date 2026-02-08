package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class PT_POSTAL_REWARD_INFO {
   @Protobuf(
      order = 1,
      required = false
   )
   public PT_ITEM_REWARD_INFO item;
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
