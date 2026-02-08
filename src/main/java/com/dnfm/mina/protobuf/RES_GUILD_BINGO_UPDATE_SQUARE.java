package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class RES_GUILD_BINGO_UPDATE_SQUARE {
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
      order = 3,
      required = false
   )
   public PT_GUILD_BINGO_SQUARE_INFO square;
}
