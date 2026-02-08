package com.dnfm.listener.event;

import com.dnfm.game.role.model.Role;
import com.dnfm.listener.BasePlayerEvent;
import com.dnfm.listener.EventType;

public class SwitchChangeEvent extends BasePlayerEvent {
   Role role;
   private final String key;
   private final boolean open;

   public SwitchChangeEvent(EventType evtType, Role role, String key, boolean open) {
      super(evtType, role);
      this.role = role;
      this.key = key;
      this.open = open;
   }

   public Role getRole() {
      return this.role;
   }

   public String getKey() {
      return this.key;
   }

   public boolean isOpen() {
      return this.open;
   }
}
