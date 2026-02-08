package com.dnfm.listener.event;

import com.dnfm.game.role.model.Role;
import com.dnfm.listener.BasePlayerEvent;
import com.dnfm.listener.EventType;

public class TaskEndEvent extends BasePlayerEvent {
   private final Role role;
   private final int taskId;

   public TaskEndEvent(EventType eventType, Role role, int taskId) {
      super(eventType, role);
      this.role = role;
      this.taskId = taskId;
   }

   public Role getRole() {
      return this.role;
   }

   public int getTaskId() {
      return this.taskId;
   }
}
