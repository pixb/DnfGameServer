package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public class ENUM_GUILD_AGIT_FINISH_TYPE {
   public static enum T implements EnumReadable {
      CLEAR(0),
      LEAVE(1),
      ETC(2),
      FAIL(3);

      private final int value;

      private T(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
