package com.dnfm.listener.event;

import com.dnfm.game.role.model.Role;
import com.dnfm.listener.BasePlayerEvent;
import com.dnfm.listener.EventType;

public class HeartBeatEvent extends BasePlayerEvent {
   private final Role role;
   private final long prevHeartTime;

   public HeartBeatEvent(EventType evtType, Role role, long prevHeartTime) {
      super(evtType, role);
      this.role = role;
      this.prevHeartTime = prevHeartTime;
   }

   public Role getRole() {
      return this.role;
   }

   public long getPrevHeartTime() {
      return this.prevHeartTime;
   }
}
