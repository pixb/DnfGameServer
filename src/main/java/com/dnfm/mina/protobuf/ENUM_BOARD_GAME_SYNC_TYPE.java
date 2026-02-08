package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public class ENUM_BOARD_GAME_SYNC_TYPE {
   public static enum T implements EnumReadable {
      SYNC_DICE_GAUGE(0);

      private final int value;

      private T(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
