package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public class ENUM_IDIP_PROHIBIT_TYPE {
   public static enum T implements EnumReadable {
      NONE(0),
      PATTERN(1),
      INCOME(2),
      RANKING(3),
      CHAT(4),
      BAN(5),
      MESSAGE(6),
      GUILD_CREATE(7),
      FRIEND_INVITE(8),
      FRIEND_ACCEPT(9),
      CREATE_CHARACTER(10),
      AUCTION_BUY(11),
      AUCTION_SELL(12),
      DUNGEON(13),
      PARTY(14),
      FRIEND(15);

      private final int value;

      private T(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
