package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public class ENUM_DIVORCE_TYPE {
   public static enum T implements EnumReadable {
      SPECIAL(0),
      COMPULSION(1),
      AGREEMENT(2);

      private final int value;

      private T(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
