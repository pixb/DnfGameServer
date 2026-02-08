package com.dnfm.listener.event;

import com.dnfm.game.role.model.Role;
import com.dnfm.listener.BasePlayerEvent;
import com.dnfm.listener.EventType;

public class EnterMapEvent extends BasePlayerEvent {
   private final Role role;

   public EnterMapEvent(EventType evtType, Role role) {
      super(evtType, role);
      this.role = role;
   }

   public Role getRole() {
      return this.role;
   }
}
