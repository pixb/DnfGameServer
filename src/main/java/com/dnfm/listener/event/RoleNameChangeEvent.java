package com.dnfm.listener.event;

import com.dnfm.game.role.model.Role;
import com.dnfm.listener.BasePlayerEvent;
import com.dnfm.listener.EventType;

public class RoleNameChangeEvent extends BasePlayerEvent {
   private final Role role;
   private final String prevName;
   private final String currName;

   public RoleNameChangeEvent(EventType evtType, Role role, String prevName, String currName) {
      super(evtType, role);
      this.role = role;
      this.prevName = prevName;
      this.currName = currName;
   }

   public Role getRole() {
      return this.role;
   }

   public String getPrevName() {
      return this.prevName;
   }

   public String getCurrName() {
      return this.currName;
   }
}
