package com.dnfm.listener.event;

import com.dnfm.game.role.model.Role;
import com.dnfm.listener.BasePlayerEvent;
import com.dnfm.listener.EventType;

public class LogoutEvent extends BasePlayerEvent {
   private final Role role;

   public LogoutEvent(EventType evtType, Role role) {
      super(evtType, role);
      this.role = role;
   }

   public Role getRole() {
      return this.role;
   }
}
