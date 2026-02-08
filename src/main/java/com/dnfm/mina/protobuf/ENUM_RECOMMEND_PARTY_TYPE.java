package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public class ENUM_RECOMMEND_PARTY_TYPE {
   public static enum T implements EnumReadable {
      FRIEND(0),
      GUILD(1),
      TOWN(2),
      PLAYWITH(3),
      LEVEL(4),
      PVP(5),
      WATTING(6);

      private final int value;

      private T(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
