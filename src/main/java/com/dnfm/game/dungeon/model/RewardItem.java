package com.dnfm.game.dungeon.model;

public class RewardItem {
   public int index;
   public double minRate;
   public double maxRate;
   public int finalCnt;

   public RewardItem(int index, double minRate, double maxRate) {
      this.index = index;
      this.minRate = minRate;
      this.maxRate = maxRate;
   }
}
