package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public class ENUM_NOTIFY_FRIEND_INFO_TYPE {
   public static enum T implements EnumReadable {
      UPDATE(0),
      REMOVE(1);

      private final int value;

      private T(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
