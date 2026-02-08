package com.dnfm.game.config;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("p_exp")
public class RoleExp {
   @Id(
      auto = false
   )
   @Comment("level")
   private int level;
   @Column
   @Comment("exp")
   private int exp;

   public int getLevel() {
      return this.level;
   }

   public int getExp() {
      return this.exp;
   }

   public void setLevel(int level) {
      this.level = level;
   }

   public void setExp(int exp) {
      this.exp = exp;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof RoleExp)) {
         return false;
      } else {
         RoleExp other = (RoleExp)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getLevel() != other.getLevel()) {
            return false;
         } else {
            return this.getExp() == other.getExp();
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof RoleExp;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getLevel();
      result = result * 59 + this.getExp();
      return result;
   }

   public String toString() {
      return "RoleExp(level=" + this.getLevel() + ", exp=" + this.getExp() + ")";
   }
}
