package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class RES_GUILD_BINGO_CHALLENGE_SQUARE {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer maplevel;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer squareindex;
   @Protobuf(
      order = 4,
      required = false
   )
   public ENUM_GUILD_BINGO_SQUARE_PLAY_TYPE.T playtype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 5,
      required = false
   )
   public Integer playindex;
   @Protobuf(
      fieldType = FieldType.ENUM,
      order = 6,
      required = false
   )
   public ENUM_GUILD_BINGO_CHALLENGE_TYPE.T type;
}
