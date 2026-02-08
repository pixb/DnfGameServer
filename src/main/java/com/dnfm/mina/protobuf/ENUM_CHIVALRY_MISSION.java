package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public class ENUM_CHIVALRY_MISSION {
   public static enum T implements EnumReadable {
      CLEAR(0),
      TIME_ATTACK(1),
      ATTACK_DAMAGE(2),
      HIT_DAMAGE(3),
      CLEAR_WITHOUT_SENIOR(4),
      CLEAR_WITH_JUNIOR(5);

      private final int value;

      private T(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
