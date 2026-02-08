package com.dnfm.listener.event;

import com.dnfm.game.role.model.Role;
import com.dnfm.listener.BasePlayerEvent;
import com.dnfm.listener.EventType;

public class RoleLevelUpEvent extends BasePlayerEvent {
   private final Role role;
   private final short oldLevel;
   private final short level;

   public RoleLevelUpEvent(EventType evtType, Role role, short oldLevel, short level) {
      super(evtType, role);
      this.oldLevel = oldLevel;
      this.level = level;
      this.role = role;
   }

   public Role getRole() {
      return this.role;
   }

   public short getOldLevel() {
      return this.oldLevel;
   }

   public short getLevel() {
      return this.level;
   }
}
