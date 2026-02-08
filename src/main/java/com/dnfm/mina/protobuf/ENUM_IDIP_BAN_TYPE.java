package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public class ENUM_IDIP_BAN_TYPE {
   public static enum T implements EnumReadable {
      BAD_CHAT(0),
      ABNORMAL_GAME_PLAY(1),
      EXPLOITING_OPEN_MARKET_PROCESS(2),
      EXPLOITING_GAME_SYSTEM(3),
      VIOLATION_OPERATIONAL_POLICY(4),
      USING_ILLEGAL_PROGRAM(5),
      RESTRICTION_TEMPORARY_USE(6),
      CASH_TRANSACTION(7),
      ETC(8),
      NONE(9);

      private final int value;

      private T(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
