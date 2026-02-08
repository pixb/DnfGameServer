package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public class ENUM_DRAGONBOARD_SLOT_TYPES {
   public static enum T implements EnumReadable {
      NONE(0),
      BLANK(101),
      GOLD(102),
      TREASUREBOX(103),
      CHANCECARD(104),
      POWERUPBUFF(105),
      MAXHPUPBUFF(106),
      DEFENSEUPBUFF(107),
      ATTSPEEDUPBUFF(108),
      MOVSPEEDUPBUFF(109),
      START(110),
      BOSS(111);

      private final int value;

      private T(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
