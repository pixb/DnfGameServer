package com.dnfm.game.friend.model;

import com.dnfm.mina.protobuf.PT_FRIEND_INFO;
import java.util.ArrayList;

public class FriendBox {
   private ArrayList<PT_FRIEND_INFO> friends = new ArrayList();
   private ArrayList<PT_FRIEND_INFO> received = new ArrayList();
   private ArrayList<PT_FRIEND_INFO> sends = new ArrayList();

   public ArrayList<PT_FRIEND_INFO> getFriends() {
      return this.friends;
   }

   public void setFriends(ArrayList<PT_FRIEND_INFO> friends) {
      this.friends = friends;
   }

   public ArrayList<PT_FRIEND_INFO> getReceived() {
      return this.received;
   }

   public ArrayList<PT_FRIEND_INFO> getSends() {
      return this.sends;
   }

   public void addFriend(PT_FRIEND_INFO info) {
      this.friends.add(info);
   }

   public PT_FRIEND_INFO getFriendByGid(long gid) {
      for(PT_FRIEND_INFO friend : this.getFriends()) {
         if (friend.fguid == gid) {
            return friend;
         }
      }

      return null;
   }

   public PT_FRIEND_INFO getFriendByName(String Name) {
      for(PT_FRIEND_INFO friend : this.getFriends()) {
         if (friend.name.equals(Name)) {
            return friend;
         }
      }

      return null;
   }

   public void removeFriend(PT_FRIEND_INFO info) {
      this.getFriends().remove(info);
   }

   public void addReceivedFriend(PT_FRIEND_INFO info) {
      this.received.add(info);
   }

   public PT_FRIEND_INFO getReceivedFriendByGid(long gid) {
      for(PT_FRIEND_INFO friend : this.getReceived()) {
         if (friend.fguid == gid) {
            return friend;
         }
      }

      return null;
   }

   public void removeReceivedFriend(PT_FRIEND_INFO info) {
      this.getReceived().remove(info);
   }

   public void addSendFriend(PT_FRIEND_INFO info) {
      this.sends.add(info);
   }

   public PT_FRIEND_INFO getSendFriendByGid(long gid) {
      for(PT_FRIEND_INFO friend : this.getSends()) {
         if (friend.fguid == gid) {
            return friend;
         }
      }

      return null;
   }

   public void removeSendFriend(PT_FRIEND_INFO info) {
      this.getSends().remove(info);
   }
}
