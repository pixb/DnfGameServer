package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = 10100,
   cmd = 1
)
public class RES_ENTER_TO_TOWN extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      fieldType = FieldType.UINT32,
      order = 2,
      required = false
   )
   public Integer town;
   @Protobuf(
      fieldType = FieldType.UINT32,
      order = 3,
      required = false
   )
   public Integer area;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer posx;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 5,
      required = false
   )
   public Integer posy;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 6,
      required = false
   )
   public Long partyguid;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 7,
      required = false
   )
   public Long servertime;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 8,
      required = false
   )
   public Integer expratio;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 9,
      required = false
   )
   public Integer fatigueratio;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 10,
      required = false
   )
   public String version;
}
