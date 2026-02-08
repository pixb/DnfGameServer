package com.dnfm.game.adventure.model;

import com.dnfm.mina.protobuf.PT_REAP_REWARD;
import java.util.ArrayList;

public class AdventureReapInfo {
   private ArrayList<PT_REAP_REWARD> rewards;
   private long starttime = -1L;

   public ArrayList<PT_REAP_REWARD> getRewards() {
      return this.rewards;
   }

   public long getStarttime() {
      return this.starttime;
   }

   public void setRewards(ArrayList<PT_REAP_REWARD> rewards) {
      this.rewards = rewards;
   }

   public void setStarttime(long starttime) {
      this.starttime = starttime;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof AdventureReapInfo)) {
         return false;
      } else {
         AdventureReapInfo other = (AdventureReapInfo)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$rewards = this.getRewards();
            Object other$rewards = other.getRewards();
            if (this$rewards == null) {
               if (other$rewards != null) {
                  return false;
               }
            } else if (!this$rewards.equals(other$rewards)) {
               return false;
            }

            if (this.getStarttime() != other.getStarttime()) {
               return false;
            } else {
               return true;
            }
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof AdventureReapInfo;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $rewards = this.getRewards();
      result = result * 59 + ($rewards == null ? 43 : $rewards.hashCode());
      long $starttime = this.getStarttime();
      result = result * 59 + (int)($starttime >>> 32 ^ $starttime);
      return result;
   }

   public String toString() {
      return "AdventureReapInfo(rewards=" + this.getRewards() + ", starttime=" + this.getStarttime() + ")";
   }
}
