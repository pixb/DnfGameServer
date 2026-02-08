package com.dnfm.listener.event;

import com.dnfm.game.equip.model.RoleEquip;
import com.dnfm.game.role.model.Role;
import com.dnfm.listener.BasePlayerEvent;
import com.dnfm.listener.EventType;

public class EquipUpgradeLevelChangeEvent extends BasePlayerEvent {
   private final Role role;
   private final RoleEquip roleEquip;
   private short prevUpgradeLevlel;
   private final short upgradeLevlel;

   public EquipUpgradeLevelChangeEvent(EventType evtType, Role role, RoleEquip roleEquip, short upgradeLevlel) {
      super(evtType, role);
      this.role = role;
      this.roleEquip = roleEquip;
      this.upgradeLevlel = upgradeLevlel;
   }

   public EquipUpgradeLevelChangeEvent(EventType evtType, Role role, RoleEquip roleEquip, short prevUpgradeLevlel, short upgradeLevlel) {
      this(evtType, role, roleEquip, upgradeLevlel);
      this.prevUpgradeLevlel = prevUpgradeLevlel;
   }

   public Role getRole() {
      return this.role;
   }

   public RoleEquip getRoleEquip() {
      return this.roleEquip;
   }

   public short getPrevUpgradeLevlel() {
      return this.prevUpgradeLevlel;
   }

   public short getUpgradeLevlel() {
      return this.upgradeLevlel;
   }
}
