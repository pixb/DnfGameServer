package com.dnfm.listener.event;

import com.dnfm.game.role.model.Role;
import com.dnfm.listener.BasePlayerEvent;
import com.dnfm.listener.EventType;

public class DefenseChangeEvent extends BasePlayerEvent {
   private final Role role;
   private final int defense;

   public DefenseChangeEvent(EventType evtType, Role role, int defense) {
      super(evtType, role);
      this.role = role;
      this.defense = defense;
   }

   public Role getRole() {
      return this.role;
   }

   public int getDefense() {
      return this.defense;
   }
}
