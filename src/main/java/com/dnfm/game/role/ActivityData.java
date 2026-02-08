package com.dnfm.game.role;

public class ActivityData {
   private String name;
   private short count;
   private short activeValue;
   private String timeStr;

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public short getCount() {
      return this.count;
   }

   public void setCount(short count) {
      this.count = count;
   }

   public short getActiveValue() {
      return this.activeValue;
   }

   public void setActiveValue(short activeValue) {
      this.activeValue = activeValue;
   }

   public String getTimeStr() {
      return this.timeStr;
   }

   public void setTimeStr(String timeStr) {
      this.timeStr = timeStr;
   }
}
