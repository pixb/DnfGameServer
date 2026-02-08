package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public class ENUM_GUILD_BINGO_SQUARE_PLAY_TYPE {
   public static enum T implements EnumReadable {
      DUNGEON(0),
      PARTY_DUNGEON(1),
      MINIGAME(2);

      private final int value;

      private T(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
