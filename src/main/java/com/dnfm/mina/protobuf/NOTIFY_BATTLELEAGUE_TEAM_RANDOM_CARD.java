package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class NOTIFY_BATTLELEAGUE_TEAM_RANDOM_CARD {
   @Protobuf(
      order = 1,
      required = false
   )
   public ENUM_TEAM.T teamtype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer itemindex;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer buffindex;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 4,
      required = false
   )
   public Long monsterguid;
   @Protobuf(
      order = 5,
      required = false
   )
   public PT_BATTLELEAGUE_BUFF buff;
}
