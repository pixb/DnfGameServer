package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public class ENUM_WEDDING_INVITATION_REPLY_TYPE {
   public static enum T implements EnumReadable {
      NONE(0),
      ACCEPT(1),
      REFUSE(2);

      private final int value;

      private T(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
