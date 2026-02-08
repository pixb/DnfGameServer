package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public class ENUM_WEDDING_PROGRESS_STATE {
   public static enum T implements EnumReadable {
      NONE(0),
      WEDDING(1),
      CEREMONY(2),
      RECEPTION(3),
      CANCEL(4);

      private final int value;

      private T(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
