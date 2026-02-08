package com.dnfm.listener.event;

import com.dnfm.game.equip.model.RoleEquip;
import com.dnfm.game.role.model.Role;
import com.dnfm.listener.BasePlayerEvent;
import com.dnfm.listener.EventType;

public class EquipPerfectChangeEvent extends BasePlayerEvent {
   private final Role role;
   private final RoleEquip roleEquip;
   private final int perfect;

   public EquipPerfectChangeEvent(EventType evtType, Role role, RoleEquip roleEquip, int perfect) {
      super(evtType, role);
      this.role = role;
      this.roleEquip = roleEquip;
      this.perfect = perfect;
   }

   public Role getRole() {
      return this.role;
   }

   public RoleEquip getRoleEquip() {
      return this.roleEquip;
   }

   public int getPerfect() {
      return this.perfect;
   }
}
