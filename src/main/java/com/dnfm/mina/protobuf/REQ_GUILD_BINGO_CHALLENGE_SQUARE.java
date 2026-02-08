package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class REQ_GUILD_BINGO_CHALLENGE_SQUARE {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer maplevel;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer squareindex;
   @Protobuf(
      fieldType = FieldType.ENUM,
      order = 3,
      required = false
   )
   public ENUM_GUILD_BINGO_CHALLENGE_TYPE.T type;
}
