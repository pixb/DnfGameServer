package com.dnfm.game.skill.model;

public class RoleSkill {
   private int roleId;
   private int index;
   private int level;
   private int slot = -1;
   private String name;

   public int getRoleId() {
      return this.roleId;
   }

   public int getIndex() {
      return this.index;
   }

   public int getLevel() {
      return this.level;
   }

   public int getSlot() {
      return this.slot;
   }

   public String getName() {
      return this.name;
   }

   public void setRoleId(int roleId) {
      this.roleId = roleId;
   }

   public void setIndex(int index) {
      this.index = index;
   }

   public void setLevel(int level) {
      this.level = level;
   }

   public void setSlot(int slot) {
      this.slot = slot;
   }

   public void setName(String name) {
      this.name = name;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof RoleSkill)) {
         return false;
      } else {
         RoleSkill other = (RoleSkill)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getRoleId() != other.getRoleId()) {
            return false;
         } else if (this.getIndex() != other.getIndex()) {
            return false;
         } else if (this.getLevel() != other.getLevel()) {
            return false;
         } else if (this.getSlot() != other.getSlot()) {
            return false;
         } else {
            Object this$name = this.getName();
            Object other$name = other.getName();
            if (this$name == null) {
               if (other$name != null) {
                  return false;
               }
            } else if (!this$name.equals(other$name)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof RoleSkill;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getRoleId();
      result = result * 59 + this.getIndex();
      result = result * 59 + this.getLevel();
      result = result * 59 + this.getSlot();
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      return result;
   }

   public String toString() {
      return "RoleSkill(roleId=" + this.getRoleId() + ", index=" + this.getIndex() + ", level=" + this.getLevel() + ", slot=" + this.getSlot() + ", name=" + this.getName() + ")";
   }
}
