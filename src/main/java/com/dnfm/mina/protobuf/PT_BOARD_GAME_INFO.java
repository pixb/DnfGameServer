package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_BOARD_GAME_INFO {
   @Protobuf(
      order = 1
   )
   public List<PT_BOARD_TILE_INFO> tiles;
   @Protobuf(
      order = 2
   )
   public List<PT_BOARD_USER_CONTENTS_INFO> users;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer sizex;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer sizey;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 5,
      required = false
   )
   public Integer playtime;
}
