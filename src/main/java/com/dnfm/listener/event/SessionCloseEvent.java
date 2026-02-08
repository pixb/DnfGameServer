package com.dnfm.listener.event;

import com.dnfm.game.role.model.Role;
import com.dnfm.listener.BasePlayerEvent;
import com.dnfm.listener.EventType;
import org.apache.mina.core.session.IoSession;

public class SessionCloseEvent extends BasePlayerEvent {
   private final Role role;
   private final IoSession session;

   public SessionCloseEvent(EventType evtType, Role role, IoSession session) {
      super(evtType, role);
      this.role = role;
      this.session = session;
   }

   public Role getRole() {
      return this.role;
   }

   public IoSession getSession() {
      return this.session;
   }
}
