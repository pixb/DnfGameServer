package com.dnfm.game.equip.model;

import java.util.HashMap;

public class LeijiData {
   private HashMap<Integer, Leiji> data = new HashMap();

   public HashMap<Integer, Leiji> getData() {
      return this.data;
   }

   public void setData(HashMap<Integer, Leiji> data) {
      this.data = data;
   }
}
