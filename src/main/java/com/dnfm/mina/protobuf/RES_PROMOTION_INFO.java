package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = -1,
   cmd = 1
)
public class RES_PROMOTION_INFO extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 2,
      required = false
   )
   public String msg;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer wxPay;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer kjPay;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 5,
      required = false
   )
   public Integer qualified;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 6,
      required = false
   )
   public String qualifiedInfo;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 7,
      required = false
   )
   public Integer isShowActPage;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 8,
      required = false
   )
   public String discounttype;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 9,
      required = false
   )
   public String discounturl;
}
