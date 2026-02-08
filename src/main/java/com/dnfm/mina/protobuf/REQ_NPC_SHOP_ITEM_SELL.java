package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 14005,
   cmd = 0
)
public class REQ_NPC_SHOP_ITEM_SELL extends Message {
   @Protobuf(
      order = 1
   )
   public List<PT_ITEM_SELL> item;
}
