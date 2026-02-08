package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 14041,
   cmd = 1
)
public class RES_SHOP_LIMIT_RESET extends Message {
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
   public Integer shopid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer tab;
   @Protobuf(
      order = 4
   )
   public List<PT_MONEY_ITEM> currency;
   @Protobuf(
      order = 5
   )
   public List<PT_MONEY_ITEM> accountcurrency;
}
