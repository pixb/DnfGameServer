package com.dnfm.listener.event;

import com.dnfm.game.role.model.Role;
import com.dnfm.listener.BasePlayerEvent;
import com.dnfm.listener.EventType;

public class LeaveMapEvent extends BasePlayerEvent {
   private final int mapId;
   private final Role role;

   public LeaveMapEvent(EventType evtType, Role role, int mapId) {
      super(evtType, role);
      this.mapId = mapId;
      this.role = role;
   }

   public int getMapId() {
      return this.mapId;
   }

   public Role getRole() {
      return this.role;
   }
}
