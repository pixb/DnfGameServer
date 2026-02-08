package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class PT_GUILD_STRUCTURE_INFO {
   @Protobuf(
      fieldType = FieldType.ENUM,
      order = 1,
      required = false
   )
   public ENUM_GUILD_STRUCTURE_TYPE.T type;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer index;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer count;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 4,
      required = false
   )
   public Long guid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 5,
      required = false
   )
   public Integer skinindex;
   @Protobuf(
      order = 6,
      required = false
   )
   public ENUM_GUILD_STRUCTURE_STATUS.T status;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 7,
      required = false
   )
   public Integer posx;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 8,
      required = false
   )
   public Integer posy;
   @Protobuf(
      fieldType = FieldType.ENUM,
      order = 9,
      required = false
   )
   public ENUM_ROTATION_TYPE.T rotationtype;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 10,
      required = false
   )
   public Long time;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 11,
      required = false
   )
   public Integer baseindex;
}
