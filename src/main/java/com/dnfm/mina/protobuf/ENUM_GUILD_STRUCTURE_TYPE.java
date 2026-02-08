package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public class ENUM_GUILD_STRUCTURE_TYPE {
   public static enum T implements EnumReadable {
      NORMAL(0),
      UPGRADE(1),
      ADD_ON(2),
      SKIN(3);

      private final int value;

      private T(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
