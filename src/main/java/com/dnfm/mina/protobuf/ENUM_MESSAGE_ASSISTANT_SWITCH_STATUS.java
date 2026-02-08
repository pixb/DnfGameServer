package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public class ENUM_MESSAGE_ASSISTANT_SWITCH_STATUS {
   public static enum T implements EnumReadable {
      NONE(0),
      OFF(1),
      ON(2);

      private final int value;

      private T(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
