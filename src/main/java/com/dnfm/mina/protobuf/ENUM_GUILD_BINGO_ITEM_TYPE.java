package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public class ENUM_GUILD_BINGO_ITEM_TYPE {
   public static enum T implements EnumReadable {
      OPEN(0),
      REPAIR(1),
      AREA_CHANGE(2);

      private final int value;

      private T(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
