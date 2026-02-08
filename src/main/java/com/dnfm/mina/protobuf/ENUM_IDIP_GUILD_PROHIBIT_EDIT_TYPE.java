package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public class ENUM_IDIP_GUILD_PROHIBIT_EDIT_TYPE {
   public static enum T implements EnumReadable {
      NONE(0),
      GUILDNAME(1),
      BULLETIN(2),
      BOARD(3);

      private final int value;

      private T(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
