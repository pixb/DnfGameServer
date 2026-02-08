package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 11100,
   cmd = 1
)
public class RES_CERA_SHOP_BUY extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      order = 2
   )
   public List<PT_CERA_SHOP_BUY> buy;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 3,
      required = false
   )
   public Long billno;
   @Protobuf(
      order = 4,
      required = false
   )
   public PT_CERA_SHOP_INFO account;
   @Protobuf(
      order = 5,
      required = false
   )
   public PT_CERA_SHOP_INFO character;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 6,
      required = false
   )
   public Integer shopid;
   @Protobuf(
      order = 7,
      required = false
   )
   public PT_CONTENTS_REWARD_INFO rewards;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 8,
      required = false
   )
   public Integer battlepassexp;
   @Protobuf(
      order = 9,
      required = false
   )
   public PT_REMOVEITEMS removeitems;
}
