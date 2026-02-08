package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public class ENUM_GUILD_STRUCTURE_STATUS {
   public static enum T implements EnumReadable {
      NONE(0),
      CONSTRUCTION(1),
      FINISH_CONSTRUCTION(2),
      CONSTRUCTION_HIDDEN(3);

      private final int value;

      private T(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
