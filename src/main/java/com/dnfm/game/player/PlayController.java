package com.dnfm.game.player;

import com.dnfm.game.role.model.Role;
import com.dnfm.listener.EventType;
import com.dnfm.listener.annotation.EventHandler;
import com.dnfm.listener.event.LoginEvent;
import com.dnfm.listener.event.LogoutEvent;
import com.dnfm.listener.event.RoleLevelUpEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PlayController {
   @Autowired
   PlayerService playerService;

   @EventHandler({EventType.ROLE_LEVEL_UP})
   public void handleRoleLevelUpEvent(RoleLevelUpEvent roleLevelUpEvent) {
      Role role = roleLevelUpEvent.getRole();
      this.playerService.updateRoleLevel(role);
   }

   @EventHandler({EventType.LOGIN})
   public void handleLoginEvent(LoginEvent loginEvent) {
      Role role = loginEvent.getRole();
      this.playerService.updateRoleEnterTime(role);
   }

   @EventHandler({EventType.LOGOUT})
   public void handleLogoutEvent(LogoutEvent logoutEvent) {
      Role role = logoutEvent.getRole();
      this.playerService.updatePlayerProfile(role);
   }
}
