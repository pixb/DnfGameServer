package com.dnfm.adminclient;

public class ServerMonitorNode {
   private int serverId;
   private int onlinePlayerSum;
   private int cachePlayerSum;
   private int playerDbQueueSum;

   public int getServerId() {
      return this.serverId;
   }

   public void setServerId(int serverId) {
      this.serverId = serverId;
   }

   public int getOnlinePlayerSum() {
      return this.onlinePlayerSum;
   }

   public void setOnlinePlayerSum(int onlinePlayerSum) {
      this.onlinePlayerSum = onlinePlayerSum;
   }

   public int getCachePlayerSum() {
      return this.cachePlayerSum;
   }

   public void setCachePlayerSum(int cachePlayerSum) {
      this.cachePlayerSum = cachePlayerSum;
   }

   public int getPlayerDbQueueSum() {
      return this.playerDbQueueSum;
   }

   public void setPlayerDbQueueSum(int playerDbQueueSum) {
      this.playerDbQueueSum = playerDbQueueSum;
   }
}
