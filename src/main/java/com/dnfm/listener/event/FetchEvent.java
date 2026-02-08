package com.dnfm.listener.event;

import com.dnfm.game.role.model.Role;
import com.dnfm.listener.BasePlayerEvent;
import com.dnfm.listener.EventType;

public class FetchEvent extends BasePlayerEvent {
   public static final byte FETCH_PET = 1;
   private final Role role;
   private final byte fetchType;

   public FetchEvent(EventType evtType, Role role, byte fetchType) {
      super(evtType, role);
      this.role = role;
      this.fetchType = fetchType;
   }

   public Role getRole() {
      return this.role;
   }

   public byte getFetchType() {
      return this.fetchType;
   }
}
