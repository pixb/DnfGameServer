package com.dnfm.game.bag.model;

import com.dnfm.mina.protobuf.PT_LOCALREWARD;
import java.util.List;

public class LocalRewardBox {
   private Integer totalpage;
   private List<PT_LOCALREWARD> rewards;

   public Integer getTotalpage() {
      return this.totalpage;
   }

   public List<PT_LOCALREWARD> getRewards() {
      return this.rewards;
   }

   public void setTotalpage(Integer totalpage) {
      this.totalpage = totalpage;
   }

   public void setRewards(List<PT_LOCALREWARD> rewards) {
      this.rewards = rewards;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof LocalRewardBox)) {
         return false;
      } else {
         LocalRewardBox other = (LocalRewardBox)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$totalpage = this.getTotalpage();
            Object other$totalpage = other.getTotalpage();
            if (this$totalpage == null) {
               if (other$totalpage != null) {
                  return false;
               }
            } else if (!this$totalpage.equals(other$totalpage)) {
               return false;
            }

            Object this$rewards = this.getRewards();
            Object other$rewards = other.getRewards();
            if (this$rewards == null) {
               if (other$rewards != null) {
                  return false;
               }
            } else if (!this$rewards.equals(other$rewards)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof LocalRewardBox;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $totalpage = this.getTotalpage();
      result = result * 59 + ($totalpage == null ? 43 : $totalpage.hashCode());
      Object $rewards = this.getRewards();
      result = result * 59 + ($rewards == null ? 43 : $rewards.hashCode());
      return result;
   }

   public String toString() {
      return "LocalRewardBox(totalpage=" + this.getTotalpage() + ", rewards=" + this.getRewards() + ")";
   }
}
