package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 11102,
   cmd = 1
)
public class RES_CERA_GACHA_BUY extends Message {
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
   public Integer grade;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer index;
   @Protobuf(
      order = 4,
      required = false
   )
   public PT_CERA_SHOP_MONEY money;
   @Protobuf(
      order = 5,
      required = false
   )
   public PT_REMOVEITEMS removeitems;
   @Protobuf(
      order = 6
   )
   public List<PT_CERA_SHOP_Mileage> mileage;
   @Protobuf(
      order = 7
   )
   public List<PT_GACHA_RESULT> result;
   @Protobuf(
      order = 8
   )
   public List<PT_GACHA_RESULT> resultinven;
   @Protobuf(
      order = 9
   )
   public List<PT_GACHA_RESULT> reward;
   @Protobuf(
      order = 10
   )
   public List<PT_GACHA_RESULT> rewardinven;
   @Protobuf(
      order = 11
   )
   public List<PT_CERA_SHOP_MONEY> cash;
}
