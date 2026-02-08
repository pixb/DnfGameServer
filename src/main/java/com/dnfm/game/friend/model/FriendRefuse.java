package com.dnfm.game.friend.model;

public class FriendRefuse {
   private byte count;
   private long refuseTime;

   public FriendRefuse() {
   }

   public FriendRefuse(byte count, long refuseTime) {
      this.count = count;
      this.refuseTime = refuseTime;
   }

   public byte getCount() {
      return this.count;
   }

   public long getRefuseTime() {
      return this.refuseTime;
   }

   public void setCount(byte count) {
      this.count = count;
   }

   public void setRefuseTime(long refuseTime) {
      this.refuseTime = refuseTime;
   }
}
