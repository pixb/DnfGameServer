package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public class ENUM_DRAGONBOARD_CHANCECARD_TYPES {
   public static enum T implements EnumReadable {
      NONE(0),
      HOLD(1),
      PC_CHANGE(2),
      DICE_1MORE(3),
      DICE_CHANGE(4),
      MOVE(5),
      DEBUFF(6),
      DOTDAMAGE(7),
      BOSS(8);

      private final int value;

      private T(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
