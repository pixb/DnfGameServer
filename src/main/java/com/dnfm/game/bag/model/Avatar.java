package com.dnfm.game.bag.model;

public class Avatar {
   private int index;
   private long guid;

   public int getIndex() {
      return this.index;
   }

   public long getGuid() {
      return this.guid;
   }

   public void setIndex(int index) {
      this.index = index;
   }

   public void setGuid(long guid) {
      this.guid = guid;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof Avatar)) {
         return false;
      } else {
         Avatar other = (Avatar)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getIndex() != other.getIndex()) {
            return false;
         } else {
            return this.getGuid() == other.getGuid();
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof Avatar;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getIndex();
      long $guid = this.getGuid();
      result = result * 59 + (int)($guid >>> 32 ^ $guid);
      return result;
   }

   public String toString() {
      return "Avatar(index=" + this.getIndex() + ", guid=" + this.getGuid() + ")";
   }
}
