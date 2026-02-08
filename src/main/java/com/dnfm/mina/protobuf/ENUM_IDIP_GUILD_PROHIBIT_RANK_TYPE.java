package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public class ENUM_IDIP_GUILD_PROHIBIT_RANK_TYPE {
   public static enum T implements EnumReadable {
      NONE(0),
      POINT(1),
      WORLDBOSS(2),
      HISTORISITE(3);

      private final int value;

      private T(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
