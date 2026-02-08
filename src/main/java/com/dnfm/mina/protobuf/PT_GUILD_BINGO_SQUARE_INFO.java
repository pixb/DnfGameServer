package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_GUILD_BINGO_SQUARE_INFO {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer index;
   @Protobuf(
      fieldType = FieldType.ENUM,
      order = 2,
      required = false
   )
   public ENUM_GUILD_BINGO_SQUARE_STATE.T state;
   @Protobuf(
      order = 3,
      required = false
   )
   public ENUM_GUILD_BINGO_SQUARE_PLAY_TYPE.T playtype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer playindex;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 5,
      required = false
   )
   public Long aid;
   @Protobuf(
      order = 6
   )
   public List<PT_GUILD_BINGO_CHALLENGERS_INFO> challengers;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 7,
      required = false
   )
   public Long lastplaytime;
}
