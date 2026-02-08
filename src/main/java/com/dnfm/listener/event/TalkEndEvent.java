package com.dnfm.listener.event;

import com.dnfm.game.role.model.Role;
import com.dnfm.listener.BasePlayerEvent;
import com.dnfm.listener.EventType;

public class TalkEndEvent extends BasePlayerEvent {
   private final Role role;
   private final int talkId;

   public TalkEndEvent(EventType evtType, Role role, int talkId) {
      super(evtType, role);
      this.role = role;
      this.talkId = talkId;
   }

   public Role getRole() {
      return this.role;
   }

   public int getTalkId() {
      return this.talkId;
   }
}
