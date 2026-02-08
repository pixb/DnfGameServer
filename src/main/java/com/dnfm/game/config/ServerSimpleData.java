package com.dnfm.game.config;

public class ServerSimpleData {
   private int type;
   private int enumvalue;
   private String value;
   private int transId;

   public int getType() {
      return this.type;
   }

   public int getEnumvalue() {
      return this.enumvalue;
   }

   public String getValue() {
      return this.value;
   }

   public int getTransId() {
      return this.transId;
   }

   public void setType(int type) {
      this.type = type;
   }

   public void setEnumvalue(int enumvalue) {
      this.enumvalue = enumvalue;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public void setTransId(int transId) {
      this.transId = transId;
   }
}
