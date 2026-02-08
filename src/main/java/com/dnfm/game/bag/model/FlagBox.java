package com.dnfm.game.bag.model;

import com.dnfm.game.equip.model.RoleEquip;
import java.util.ArrayList;
import java.util.List;

public class FlagBox {
   private int maxcount = 40;
   private List<RoleEquip> flags = new ArrayList();

   public void addCreature(RoleEquip flag) {
      this.flags.add(flag);
   }

   public int getMaxcount() {
      return this.maxcount;
   }

   public List<RoleEquip> getFlags() {
      return this.flags;
   }

   public void setMaxcount(int maxcount) {
      this.maxcount = maxcount;
   }

   public void setFlags(List<RoleEquip> flags) {
      this.flags = flags;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof FlagBox)) {
         return false;
      } else {
         FlagBox other = (FlagBox)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getMaxcount() != other.getMaxcount()) {
            return false;
         } else {
            Object this$flags = this.getFlags();
            Object other$flags = other.getFlags();
            if (this$flags == null) {
               if (other$flags != null) {
                  return false;
               }
            } else if (!this$flags.equals(other$flags)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof FlagBox;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getMaxcount();
      Object $flags = this.getFlags();
      result = result * 59 + ($flags == null ? 43 : $flags.hashCode());
      return result;
   }

   public String toString() {
      return "FlagBox(maxcount=" + this.getMaxcount() + ", flags=" + this.getFlags() + ")";
   }
}
