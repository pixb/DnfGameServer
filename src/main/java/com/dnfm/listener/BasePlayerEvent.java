package com.dnfm.listener;

import com.dnfm.game.role.model.Role;

public abstract class BasePlayerEvent extends BaseGameEvent {
   private final long playerUid;

   public BasePlayerEvent(EventType evtType, Role role) {
      super(evtType);
      this.playerUid = role.getUid();
   }

   public long getPlayerUid() {
      return this.playerUid;
   }
}
