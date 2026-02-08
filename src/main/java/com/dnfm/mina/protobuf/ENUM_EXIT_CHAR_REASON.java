package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

public class ENUM_EXIT_CHAR_REASON {
   public static enum T implements EnumReadable {
      NONE(0),
      LOGOUT(1),
      PUTOUT(2),
      GOTO_LOBBY(3),
      QUICK_CHARAC_CHANGE(4),
      MOVE_CHANNEL(5),
      RELOAD_CHARACTER(6),
      PLATFORM_USER_SWITCH(7),
      RAID(1000),
      HISTORIC_SITE(1001),
      CUSTOM_PVP(1002),
      DRAGON_ROAD(1003),
      PVP_2VS2(1005),
      PVP_3VS3(1004),
      PVP_3VS3_FAIR(1006);

      private final int value;

      private T(int value) {
         this.value = value;
      }

      public int value() {
         return this.value;
      }
   }
}
