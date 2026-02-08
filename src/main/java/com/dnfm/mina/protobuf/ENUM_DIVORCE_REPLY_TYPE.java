package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public class ENUM_DIVORCE_REPLY_TYPE {
   public static enum T implements EnumReadable {
      ACCEPT(0),
      REJECT(1),
      REQUEST(2);

      private final int value;

      private T(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
