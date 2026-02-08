package com.dnfm.listener.event;

import com.dnfm.game.role.model.Role;
import com.dnfm.listener.BasePlayerEvent;
import com.dnfm.listener.EventType;

public class StudySkillEvent extends BasePlayerEvent {
   private final Role role;
   private final int skillId;
   private final short skillLevel;

   public StudySkillEvent(EventType evtType, Role role, int skillId, short skillLevel) {
      super(evtType, role);
      this.role = role;
      this.skillId = skillId;
      this.skillLevel = skillLevel;
   }

   public Role getRole() {
      return this.role;
   }

   public int getSkillId() {
      return this.skillId;
   }

   public short getSkillLevel() {
      return this.skillLevel;
   }
}
