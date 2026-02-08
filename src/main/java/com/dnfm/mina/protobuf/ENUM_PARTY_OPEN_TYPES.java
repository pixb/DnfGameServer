package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public class ENUM_PARTY_OPEN_TYPES {
   public static enum T implements EnumReadable {
      PUBLIC(0),
      HALF(1),
      PRIVATE(2);

      private final int value;

      private T(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
