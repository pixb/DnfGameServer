package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public class ENUM_GUILD_AGIT_MINIGAME_TYPE {
   public static enum T implements EnumReadable {
      MINE(0),
      MASTER_OF_UPPER(1),
      BOAT_RACE(2),
      FISHING(3);

      private final int value;

      private T(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
