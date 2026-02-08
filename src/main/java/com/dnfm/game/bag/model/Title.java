package com.dnfm.game.bag.model;

public class Title {
   private int index;
   private long guid;
   private int quality;

   public int getIndex() {
      return this.index;
   }

   public long getGuid() {
      return this.guid;
   }

   public int getQuality() {
      return this.quality;
   }

   public void setIndex(int index) {
      this.index = index;
   }

   public void setGuid(long guid) {
      this.guid = guid;
   }

   public void setQuality(int quality) {
      this.quality = quality;
   }
}
