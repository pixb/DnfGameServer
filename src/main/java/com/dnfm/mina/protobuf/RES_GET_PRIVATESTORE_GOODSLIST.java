package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 11104,
   cmd = 1
)
public class RES_GET_PRIVATESTORE_GOODSLIST extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer shopid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer remaincostresetcount;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 3,
      required = false
   )
   public Long lastfreeresettime;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer error;
   @Protobuf(
      order = 5
   )
   public List<PT_PRIVATESTORE_GOODS> goods;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 6,
      required = false
   )
   public Boolean isrefresh;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 7,
      required = false
   )
   public Integer shoptype;
}
