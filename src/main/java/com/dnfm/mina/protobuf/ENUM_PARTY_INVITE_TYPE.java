package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public class ENUM_PARTY_INVITE_TYPE {
   public static enum T implements EnumReadable {
      None(0),
      PVP_3VS3(1),
      FAIR_PVP_3VS3(2);

      private final int value;

      private T(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
