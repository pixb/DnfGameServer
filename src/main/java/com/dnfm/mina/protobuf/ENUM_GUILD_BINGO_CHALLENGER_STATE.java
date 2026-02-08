package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public class ENUM_GUILD_BINGO_CHALLENGER_STATE {
   public static enum T implements EnumReadable {
      FAIL(0),
      SUCCESS(1),
      PLAYING(2);

      private final int value;

      private T(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
