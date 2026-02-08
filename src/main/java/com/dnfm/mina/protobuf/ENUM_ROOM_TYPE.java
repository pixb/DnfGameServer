package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public class ENUM_ROOM_TYPE {
   public static enum T implements EnumReadable {
      NONE(0),
      GUILD_FRIENDLY(1),
      CUSTOM_PVP(2),
      GUILDHISTORICSITE(3);

      private final int value;

      private T(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
