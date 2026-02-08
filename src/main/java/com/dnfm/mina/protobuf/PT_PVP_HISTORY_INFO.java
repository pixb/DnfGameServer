package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_PVP_HISTORY_INFO {
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 1,
      required = false
   )
   public Long createtime;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer equipscore;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer growtype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer secondgrowtype;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 5,
      required = false
   )
   public String name;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 6,
      required = false
   )
   public Integer job;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 7,
      required = false
   )
   public Integer result;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 8,
      required = false
   )
   public Integer type;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 9,
      required = false
   )
   public Integer level;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 10,
      required = false
   )
   public Integer pvpcoin;
   @Protobuf(
      order = 11
   )
   public List<PT_PVP_HISTORY_CHARACTER_INFO> characters;
}
