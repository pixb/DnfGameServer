package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class PT_BOARD_GAME_MOVE_RESULT {
   @Protobuf(
      order = 1,
      required = false
   )
   public PT_BOARD_TILE_INFO tile;
   @Protobuf(
      order = 2,
      required = false
   )
   public PT_BOARD_USER_CONTENTS_INFO user;
}
