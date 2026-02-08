package com.dnfm.listener.event;

import com.dnfm.game.role.model.Role;
import com.dnfm.listener.BasePlayerEvent;
import com.dnfm.listener.EventType;

public class RoleExpChangeEvent extends BasePlayerEvent {
   private final Role role;
   private final int change;

   public RoleExpChangeEvent(EventType evtType, Role role, int change) {
      super(evtType, role);
      this.role = role;
      this.change = change;
   }

   public Role getRole() {
      return this.role;
   }

   public int getChange() {
      return this.change;
   }
}
