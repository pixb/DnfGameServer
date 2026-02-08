package com.dnfm.game.player;

import com.dnfm.common.spring.SpringUtils;
import com.dnfm.game.player.model.PlayerProfile;
import com.dnfm.game.role.model.Role;
import com.dnfm.logs.LoggerUtils;
import java.util.Map;

public class DataTransmitUtil {
   public static void transmitPlayer(Role role) {
      LoggerUtils.error("数据迁移玩家[{}]=[{}]", role.getUid(), role.getName());
      role.save();
   }

   public static void beginTransmit() {
      try {
         for(Map.Entry<Long, PlayerProfile> entry : SpringUtils.getPlayerService().getAllPlayerProfiles().entrySet()) {
            long playerId = (Long)entry.getKey();
            Role role = SpringUtils.getPlayerService().getPlayerBy(playerId);
            transmitPlayer(role);
            Thread.sleep(100L);
         }
      } catch (Exception e) {
         LoggerUtils.error("", e);
      }

   }
}
