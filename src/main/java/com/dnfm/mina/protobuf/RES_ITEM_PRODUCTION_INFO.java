package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 14074,
   cmd = 1
)
public class RES_ITEM_PRODUCTION_INFO extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      order = 2
   )
   public List<PT_ITEM_PRODUCTION_SLOT> infos;
   @Protobuf(
      order = 3
   )
   public List<PT_ALCHEMY_RECIPE_LIMIT> recipelimit;
}
