package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class PT_GUILD_NPC_INFO {
   @Protobuf(
      fieldType = FieldType.ENUM,
      order = 1,
      required = false
   )
   public ENUM_GUILD_NPC_TYPE.T type;
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
   public Integer skinindex;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 4,
      required = false
   )
   public Long guid;
   @Protobuf(
      fieldType = FieldType.ENUM,
      order = 5,
      required = false
   )
   public ENUM_GUILD_NPC_STATUS.T status;
}
