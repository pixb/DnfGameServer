package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public class ENUM_WEDDING_PREPARATION_STEP {
   public static enum T implements EnumReadable {
      NONE(0),
      SELECT_THEME(1),
      PAYMENT(2),
      SELECT_TIME(3),
      EDIT_TIME(4),
      AGREE_TIME(5),
      WAIT_WEDDING(6),
      WEDDING(7),
      CANCEL_WEDDING(8),
      COMPLETE(9);

      private final int value;

      private T(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
