package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public class ENUM_HISTORICSITE_ENTER {
   public static enum T implements EnumReadable {
      NONE(0),
      ANOTHER(1),
      REGISTERED(2);

      private final int value;

      private T(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
