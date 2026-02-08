package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class PT_RANKING_DUNGEON_PARTY_INFO {
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 1,
      required = false
   )
   public Long guid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer job;
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
   public Integer secgrowtype;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 5,
      required = false
   )
   public String name;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 6,
      required = false
   )
   public Long rank;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 7,
      required = false
   )
   public Long score;
}
