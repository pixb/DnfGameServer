package com.dnfm.game.identity;

public enum IdentityType {
   ROLE_EQUIP(1),
   BOOK(2),
   TASK(3),
   SUPER_BOSS(4, Boolean.FALSE),
   ROLE(5);

   int type;
   boolean save;

   private IdentityType(int type) {
      this.type = type;
      this.save = Boolean.TRUE;
   }

   private IdentityType(int type, boolean save) {
      this.type = type;
      this.save = save;
   }

   public int getType() {
      return this.type;
   }

   public boolean isSave() {
      return this.save;
   }
}
