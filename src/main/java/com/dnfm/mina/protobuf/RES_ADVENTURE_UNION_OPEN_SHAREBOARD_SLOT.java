package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 17210,
   cmd = 1
)
public class RES_ADVENTURE_UNION_OPEN_SHAREBOARD_SLOT extends Message {
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
   public Integer slot;
   @Protobuf(
      order = 3
   )
   public List<PT_MONEY_ITEM> currency;
   @Protobuf(
      order = 4
   )
   public List<PT_MONEY_ITEM> accountcurrency;
   @Protobuf(
      order = 5,
      required = false
   )
   public PT_REMOVEITEMS removeitems;
}
