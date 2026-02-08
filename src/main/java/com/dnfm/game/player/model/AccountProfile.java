package com.dnfm.game.player.model;

import java.util.List;

public class AccountProfile {
   private String sid;
   private List<PlayerProfile> players;

   public String getSid() {
      return this.sid;
   }

   public void setSid(String sid) {
      this.sid = sid;
   }

   public List<PlayerProfile> getPlayers() {
      return this.players;
   }

   public void setPlayers(List<PlayerProfile> players) {
      this.players = players;
   }
}
