package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public class ENUM_BOARD_RESULT_TYPE {
   public static enum T implements EnumReadable {
      FINISH(0),
      LEAVE(1);

      private final int value;

      private T(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
