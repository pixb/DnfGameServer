package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public class ENUM_GUILD_PRODUCTION_TYPES {
   public static enum T implements EnumReadable {
      NONE(0),
      WORKSHOP(1),
      KITCHEN(2);

      private final int value;

      private T(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
