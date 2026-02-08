package com.dnfm.game.activity.model;

public class ActivityBox {
   private long tylorBagExpireTime;

   public long getTylorBagExpireTime() {
      return this.tylorBagExpireTime;
   }

   public void setTylorBagExpireTime(long tylorBagExpireTime) {
      this.tylorBagExpireTime = tylorBagExpireTime;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ActivityBox)) {
         return false;
      } else {
         ActivityBox other = (ActivityBox)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            return this.getTylorBagExpireTime() == other.getTylorBagExpireTime();
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ActivityBox;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $tylorBagExpireTime = this.getTylorBagExpireTime();
      result = result * 59 + (int)($tylorBagExpireTime >>> 32 ^ $tylorBagExpireTime);
      return result;
   }

   public String toString() {
      return "ActivityBox(tylorBagExpireTime=" + this.getTylorBagExpireTime() + ")";
   }
}
