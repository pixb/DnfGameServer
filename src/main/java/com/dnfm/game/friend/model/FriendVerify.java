package com.dnfm.game.friend.model;

public class FriendVerify {
   private long uid;
   private String name;

   public FriendVerify() {
   }

   public FriendVerify(long uid, String name) {
      this.uid = uid;
      this.name = name;
   }

   public long getUid() {
      return this.uid;
   }

   public String getName() {
      return this.name;
   }
}
