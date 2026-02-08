package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = 11101,
   cmd = 1
)
public class RES_CERA_SHOP_BUY_INFO extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      order = 2,
      required = false
   )
   public PT_CERA_SHOP_INFO account;
   @Protobuf(
      order = 3,
      required = false
   )
   public PT_CERA_SHOP_INFO character;
}
