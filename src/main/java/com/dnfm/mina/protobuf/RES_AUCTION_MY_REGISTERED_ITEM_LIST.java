package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 10068,
   cmd = 1
)
public class RES_AUCTION_MY_REGISTERED_ITEM_LIST extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      order = 2
   )
   public List<PT_AUCTION_EQUIP> equip;
   @Protobuf(
      order = 3
   )
   public List<PT_AUCTION_STACKABLE> stackable;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 4,
      required = false
   )
   public Long time;
}
