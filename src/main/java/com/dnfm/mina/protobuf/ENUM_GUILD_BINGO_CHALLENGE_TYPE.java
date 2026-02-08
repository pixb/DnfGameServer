package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public class ENUM_GUILD_BINGO_CHALLENGE_TYPE {
   public static enum T implements EnumReadable {
      REQUEST(0),
      CANCEL(1),
      UPDATE(2);

      private final int value;

      private T(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
