package com.dnfm.listener.event;

import com.dnfm.game.equip.model.RoleEquip;
import com.dnfm.game.role.model.Role;
import com.dnfm.listener.BasePlayerEvent;
import com.dnfm.listener.EventType;

public class ChangeEquipEvent extends BasePlayerEvent {
   private final Role role;
   private final RoleEquip roleEquip;

   public ChangeEquipEvent(EventType evtType, Role role, RoleEquip roleEquip) {
      super(evtType, role);
      this.role = role;
      this.roleEquip = roleEquip;
   }

   public Role getRole() {
      return this.role;
   }

   public RoleEquip getRoleEquip() {
      return this.roleEquip;
   }
}
