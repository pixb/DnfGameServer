package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public class ENUM_RAID_PHASE {
   public static enum T implements EnumReadable {
      WAITING(0),
      PLAYING(1),
      FAILED(2),
      SUCCESS(3);

      private final int value;

      private T(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
