package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public class ENUM_GUILD_BINGO_SQUARE_STATE {
   public static enum T implements EnumReadable {
      NONE(0),
      WAIT(1),
      PLAY(2),
      OPEN(3),
      CLEAR(4),
      LOCK(5);

      private final int value;

      private T(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
