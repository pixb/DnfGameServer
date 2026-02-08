package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = 10418,
   cmd = 1
)
public class RES_LOAD_MARRIAGE_INFO extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 2,
      required = false
   )
   public Long marriageguid;
   @Protobuf(
      order = 3,
      required = false
   )
   public PT_MARRIAGE_CHARACTER_INFO groom;
   @Protobuf(
      order = 4,
      required = false
   )
   public PT_MARRIAGE_CHARACTER_INFO bride;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 5,
      required = false
   )
   public Long weddingdate;
   @Protobuf(
      order = 6,
      required = false
   )
   public PT_WEDDING_THEME theme;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 7,
      required = false
   )
   public Integer maritalstatus;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 8,
      required = false
   )
   public Long divorcedate;
}
