package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public class ENUM_CLIENT_BUILD_TYPE {
   public static enum T implements EnumReadable {
      NONE(0),
      TEEN(1),
      ADULT(2);

      private final int value;

      private T(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
