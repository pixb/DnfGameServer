package com.dnfm.listener.event;

import com.dnfm.game.role.model.Role;
import com.dnfm.listener.BasePlayerEvent;
import com.dnfm.listener.EventType;

public class OnEquipEvent extends BasePlayerEvent {
   private final Role role;
   private final String equipName;

   public OnEquipEvent(EventType evtType, Role role, String equipName) {
      super(evtType, role);
      this.equipName = equipName;
      this.role = role;
   }

   public Role getRole() {
      return this.role;
   }

   public String getEquipName() {
      return this.equipName;
   }
}
