package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class PT_RANKING_DUNGEON_SEASON_INFO {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer type;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer trycount;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 3,
      required = false
   )
   public Long rank;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 4,
      required = false
   )
   public Long score;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 5,
      required = false
   )
   public Long count;
}
