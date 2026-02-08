package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public class ENUM_PARTY_LOAD_TYPES {
   public static enum T implements EnumReadable {
      RECOMMENDATION(0),
      SEARCH(1),
      NAME(2);

      private final int value;

      private T(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
