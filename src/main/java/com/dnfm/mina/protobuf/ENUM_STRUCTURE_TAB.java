package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public class ENUM_STRUCTURE_TAB {
   public static enum T implements EnumReadable {
      DEVELOPMENT(0),
      SPECIAL(1),
      THEME(2);

      private final int value;

      private T(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
