package com.test.logs;

public enum Reason {
   RECHARGE(511, "充值");

   int type;
   String desc;

   private Reason(int type, String desc) {
      this.type = type;
      this.desc = desc;
   }

   public int getType() {
      return this.type;
   }

   public String getDesc() {
      return this.desc;
   }
}
