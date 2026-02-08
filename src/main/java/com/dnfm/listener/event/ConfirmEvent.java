package com.dnfm.listener.event;

import com.dnfm.game.role.model.Role;
import com.dnfm.listener.BasePlayerEvent;
import com.dnfm.listener.EventType;

public class ConfirmEvent extends BasePlayerEvent {
   private final Role role;
   private final int taskId;
   private final String select;

   public ConfirmEvent(EventType evtType, Role role, int taskId, String select) {
      super(evtType, role);
      this.taskId = taskId;
      this.select = select;
      this.role = role;
   }

   public Role getRole() {
      return this.role;
   }

   public int getTaskId() {
      return this.taskId;
   }

   public String getSelect() {
      return this.select;
   }
}
