package com.dnfm.game.drop.model.user;

import java.util.Map;

public class RoleDrop {
   private Map<String, Integer> dailyDropCountMap;

   public Map<String, Integer> getDailyDropCountMap() {
      return this.dailyDropCountMap;
   }

   public void setDailyDropCountMap(Map<String, Integer> dailyDropCountMap) {
      this.dailyDropCountMap = dailyDropCountMap;
   }
}
