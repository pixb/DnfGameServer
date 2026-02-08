package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public class ENUM_IDIP_NOTIFY_TYPE {
   public static enum T implements EnumReadable {
      SERA(0),
      GOLD(1),
      EXP(2),
      FATIGUE(3),
      ITEM(4),
      BAN(5),
      DUNGEON_CLEAR(6),
      QUEST_CLEAR(7),
      TICKET(8),
      TERA(9),
      MESSAGE(10),
      SPEAK_CLEAR(11),
      SYSTEM_BUFF(12);

      private final int value;

      private T(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
