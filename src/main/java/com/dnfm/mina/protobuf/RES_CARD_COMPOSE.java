package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 14065,
   cmd = 1
)
public class RES_CARD_COMPOSE extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      order = 2
   )
   public List<PT_CARD_COMPOSE> usercardlist;
   @Protobuf(
      order = 3
   )
   public List<PT_CARD_COMPOSE> card;
   @Protobuf(
      order = 4
   )
   public List<PT_MONEY_ITEM> currency;
}
