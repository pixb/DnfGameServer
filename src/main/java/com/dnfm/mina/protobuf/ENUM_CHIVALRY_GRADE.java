package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public class ENUM_CHIVALRY_GRADE {
   public static enum T implements EnumReadable {
      JUNIOR(0),
      NORMAL(1),
      SENIOR_1(2),
      SENIOR_2(3),
      SENIOR_3(4);

      private final int value;

      private T(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
