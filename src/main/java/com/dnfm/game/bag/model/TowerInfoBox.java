package com.dnfm.game.bag.model;

import com.dnfm.mina.protobuf.PT_TOWER_OF_ILLUSION_CLEAR_RATE;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TowerInfoBox {
   private int clearfloor;
   private int type;
   private int sweepcount;
   private Set<Integer> hasRewardSet = new HashSet();
   private List<PT_TOWER_OF_ILLUSION_CLEAR_RATE> clearrate;

   public void setReward(int floor) {
      if (this.hasRewardSet == null) {
         this.hasRewardSet = new HashSet();
      }

      this.hasRewardSet.add(floor);
   }

   public boolean hasReward(int floor) {
      return this.hasRewardSet == null ? false : this.hasRewardSet.contains(floor);
   }

   public int getClearfloor() {
      return this.clearfloor;
   }

   public int getType() {
      return this.type;
   }

   public int getSweepcount() {
      return this.sweepcount;
   }

   public Set<Integer> getHasRewardSet() {
      return this.hasRewardSet;
   }

   public List<PT_TOWER_OF_ILLUSION_CLEAR_RATE> getClearrate() {
      return this.clearrate;
   }

   public void setClearfloor(int clearfloor) {
      this.clearfloor = clearfloor;
   }

   public void setType(int type) {
      this.type = type;
   }

   public void setSweepcount(int sweepcount) {
      this.sweepcount = sweepcount;
   }

   public void setHasRewardSet(Set<Integer> hasRewardSet) {
      this.hasRewardSet = hasRewardSet;
   }

   public void setClearrate(List<PT_TOWER_OF_ILLUSION_CLEAR_RATE> clearrate) {
      this.clearrate = clearrate;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof TowerInfoBox)) {
         return false;
      } else {
         TowerInfoBox other = (TowerInfoBox)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getClearfloor() != other.getClearfloor()) {
            return false;
         } else if (this.getType() != other.getType()) {
            return false;
         } else if (this.getSweepcount() != other.getSweepcount()) {
            return false;
         } else {
            Object this$hasRewardSet = this.getHasRewardSet();
            Object other$hasRewardSet = other.getHasRewardSet();
            if (this$hasRewardSet == null) {
               if (other$hasRewardSet != null) {
                  return false;
               }
            } else if (!this$hasRewardSet.equals(other$hasRewardSet)) {
               return false;
            }

            Object this$clearrate = this.getClearrate();
            Object other$clearrate = other.getClearrate();
            if (this$clearrate == null) {
               if (other$clearrate != null) {
                  return false;
               }
            } else if (!this$clearrate.equals(other$clearrate)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof TowerInfoBox;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getClearfloor();
      result = result * 59 + this.getType();
      result = result * 59 + this.getSweepcount();
      Object $hasRewardSet = this.getHasRewardSet();
      result = result * 59 + ($hasRewardSet == null ? 43 : $hasRewardSet.hashCode());
      Object $clearrate = this.getClearrate();
      result = result * 59 + ($clearrate == null ? 43 : $clearrate.hashCode());
      return result;
   }

   public String toString() {
      return "TowerInfoBox(clearfloor=" + this.getClearfloor() + ", type=" + this.getType() + ", sweepcount=" + this.getSweepcount() + ", hasRewardSet=" + this.getHasRewardSet() + ", clearrate=" + this.getClearrate() + ")";
   }
}
