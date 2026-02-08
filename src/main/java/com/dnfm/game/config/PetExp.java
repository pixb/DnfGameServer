package com.dnfm.game.config;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("p_petexp")
public class PetExp {
   @Id(
      auto = false
   )
   private int level;
   @Column
   private int nextLevelExp;
   @Column
   private int expDan;
   @Column
   private int HighExpDan;

   public int getLevel() {
      return this.level;
   }

   public int getNextLevelExp() {
      return this.nextLevelExp;
   }

   public int getExpDan() {
      return this.expDan;
   }

   public int getHighExpDan() {
      return this.HighExpDan;
   }

   public void setLevel(int level) {
      this.level = level;
   }

   public void setNextLevelExp(int nextLevelExp) {
      this.nextLevelExp = nextLevelExp;
   }

   public void setExpDan(int expDan) {
      this.expDan = expDan;
   }

   public void setHighExpDan(int HighExpDan) {
      this.HighExpDan = HighExpDan;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PetExp)) {
         return false;
      } else {
         PetExp other = (PetExp)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getLevel() != other.getLevel()) {
            return false;
         } else if (this.getNextLevelExp() != other.getNextLevelExp()) {
            return false;
         } else if (this.getExpDan() != other.getExpDan()) {
            return false;
         } else {
            return this.getHighExpDan() == other.getHighExpDan();
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof PetExp;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getLevel();
      result = result * 59 + this.getNextLevelExp();
      result = result * 59 + this.getExpDan();
      result = result * 59 + this.getHighExpDan();
      return result;
   }

   public String toString() {
      return "PetExp(level=" + this.getLevel() + ", nextLevelExp=" + this.getNextLevelExp() + ", expDan=" + this.getExpDan() + ", HighExpDan=" + this.getHighExpDan() + ")";
   }
}
