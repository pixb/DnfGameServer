package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public class ENUM_GUILD_WAREHOUSE_TYPES {
   public static enum T implements EnumReadable {
      NONE(0),
      INVENTORY(1),
      STRUCTURE(2),
      NPC(3),
      ALL(4);

      private final int value;

      private T(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
