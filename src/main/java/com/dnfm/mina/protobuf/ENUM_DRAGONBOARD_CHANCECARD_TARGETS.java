package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public class ENUM_DRAGONBOARD_CHANCECARD_TARGETS {
   public static enum T implements EnumReadable {
      NONE(0),
      FORWARDMOST_PC(1),
      RANDOM_PC(2),
      LAST_PC(3),
      ME(4);

      private final int value;

      private T(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
