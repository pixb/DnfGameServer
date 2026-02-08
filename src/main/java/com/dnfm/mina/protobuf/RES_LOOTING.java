package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 11014,
   cmd = 1
)
public class RES_LOOTING extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer earnexp;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer earnitemcount;
   @Protobuf(
      order = 4
   )
   public List<PT_MONEY_ITEM> currency;
   @Protobuf(
      order = 5
   )
   public List<PT_MONEY_ITEM> accountcurrency;
   @Protobuf(
      order = 6,
      required = false
   )
   public PT_REMOVEITEMS removeitems;
   @Protobuf(
      order = 7,
      required = false
   )
   public PT_ITEMS items;
   @Protobuf(
      order = 8
   )
   public List<PT_CURRENCY_DAILY_GAIN> characterdailygain;
   @Protobuf(
      order = 9
   )
   public List<PT_CURRENCY_DAILY_GAIN> accountdailygain;
}
