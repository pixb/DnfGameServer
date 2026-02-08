package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 14200,
   cmd = 1
)
public class RES_MONEY_ITEM_LIST extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      order = 2
   )
   public List<PT_MONEY_ITEM> currency;
   @Protobuf(
      order = 3
   )
   public List<PT_MONEY_ITEM> accountcurrency;
   @Protobuf(
      order = 4
   )
   public List<PT_CURRENCY_DAILY_GAIN> characterdailygain;
   @Protobuf(
      order = 5
   )
   public List<PT_CURRENCY_DAILY_GAIN> accountdailygain;
}
