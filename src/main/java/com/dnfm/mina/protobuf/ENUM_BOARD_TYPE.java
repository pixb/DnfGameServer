package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public class ENUM_BOARD_TYPE {
   public static enum T implements EnumReadable {
      NONE(0),
      EXCAVATION(1),
      DRAGONBOARD(2);

      private final int value;

      private T(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
