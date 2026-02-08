package com.dnfm.listener.event;

import com.dnfm.game.role.model.Role;
import com.dnfm.listener.BasePlayerEvent;
import com.dnfm.listener.EventType;

public class PaySuccessEvent extends BasePlayerEvent {
   private final Role role;
   private final int money;
   private final int addDq;

   public PaySuccessEvent(EventType evtType, Role role, int money, int addDq) {
      super(evtType, role);
      this.role = role;
      this.money = money;
      this.addDq = addDq;
   }

   public Role getRole() {
      return this.role;
   }

   public int getMoney() {
      return this.money;
   }

   public int getAddDq() {
      return this.addDq;
   }
}
