package com.dnfm.game.friend.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(
   ignoreUnknown = true
)
public class Friend {
   private String mark;
   private int friendliness;
   private String groupNumber;
   private String friendName;
   private String gid;
   private boolean online;

   public void setMark(String mark) {
      this.mark = mark;
   }

   public void setFriendliness(int friendliness) {
      this.friendliness = friendliness;
   }

   public void setGroupNumber(String groupNumber) {
      this.groupNumber = groupNumber;
   }

   public void setFriendName(String friendName) {
      this.friendName = friendName;
   }

   public void setGid(String gid) {
      this.gid = gid;
   }

   public void setOnline(boolean online) {
      this.online = online;
   }

   public String getMark() {
      return this.mark;
   }

   public int getFriendliness() {
      return this.friendliness;
   }

   public String getGroupNumber() {
      return this.groupNumber;
   }

   public String getFriendName() {
      return this.friendName;
   }

   public String getGid() {
      return this.gid;
   }

   public boolean isOnline() {
      return this.online;
   }
}
