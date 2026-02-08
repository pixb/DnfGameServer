package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public class ENUM_ROTATION_TYPE {
   public static enum T implements EnumReadable {
      DEGREES_0(0),
      DEGREES_90(1),
      DEGREES_180(2),
      DEGREES_270(3);

      private final int value;

      private T(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
