package com.dnfm.game.friend.model;

public class FriendInfo {
   private String numberName;
   private short status;
   private String lineName;
   private byte a = 0;

   public String getNumberName() {
      return this.numberName;
   }

   public void setNumberName(String numberName) {
      this.numberName = numberName;
   }

   public short getStatus() {
      return this.status;
   }

   public void setStatus(short status) {
      this.status = status;
   }

   public String getLineName() {
      return this.lineName;
   }

   public void setLineName(String lineName) {
      this.lineName = lineName;
   }

   public byte getA() {
      return this.a;
   }

   public void setA(byte a) {
      this.a = a;
   }
}
