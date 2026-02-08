package com.dnfm.game.equip.model;

import java.util.HashMap;

public class ChargeData {
   long time;
   HashMap<Long, RoleData> hashMap;

   public long getTime() {
      return this.time;
   }

   public void setTime(long time) {
      this.time = time;
   }

   public HashMap<Long, RoleData> getHashMap() {
      return this.hashMap;
   }

   public void setHashMap(HashMap<Long, RoleData> hashMap) {
      this.hashMap = hashMap;
   }
}
