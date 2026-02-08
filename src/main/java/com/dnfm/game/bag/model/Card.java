package com.dnfm.game.bag.model;

public class Card {
   private int index;
   private int count;
   private boolean bind;
   private long acquisitiontime;

   public int getIndex() {
      return this.index;
   }

   public int getCount() {
      return this.count;
   }

   public boolean isBind() {
      return this.bind;
   }

   public long getAcquisitiontime() {
      return this.acquisitiontime;
   }

   public void setIndex(int index) {
      this.index = index;
   }

   public void setCount(int count) {
      this.count = count;
   }

   public void setBind(boolean bind) {
      this.bind = bind;
   }

   public void setAcquisitiontime(long acquisitiontime) {
      this.acquisitiontime = acquisitiontime;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof Card)) {
         return false;
      } else {
         Card other = (Card)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getIndex() != other.getIndex()) {
            return false;
         } else if (this.getCount() != other.getCount()) {
            return false;
         } else if (this.isBind() != other.isBind()) {
            return false;
         } else {
            return this.getAcquisitiontime() == other.getAcquisitiontime();
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof Card;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getIndex();
      result = result * 59 + this.getCount();
      result = result * 59 + (this.isBind() ? 79 : 97);
      long $acquisitiontime = this.getAcquisitiontime();
      result = result * 59 + (int)($acquisitiontime >>> 32 ^ $acquisitiontime);
      return result;
   }

   public String toString() {
      return "Card(index=" + this.getIndex() + ", count=" + this.getCount() + ", bind=" + this.isBind() + ", acquisitiontime=" + this.getAcquisitiontime() + ")";
   }
}
