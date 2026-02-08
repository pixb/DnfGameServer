package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = -1,
   cmd = 1
)
public class RES_WARDROBE_EXTENTION_MANNEQUIN extends Message {
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
   public Integer extentioncount;
   @Protobuf(
      order = 3
   )
   public List<PT_MONEY_ITEM> currency;
   @Protobuf(
      order = 4,
      required = false
   )
   public PT_REMOVEITEMS removeitems;
}
