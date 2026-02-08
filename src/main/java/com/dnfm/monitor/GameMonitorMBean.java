package com.dnfm.monitor;

public interface GameMonitorMBean {
   int getOnlinePlayerSum();

   int getCachePlayerSum();

   int getFightRoomSum();

   int getPlayerDbQueueSum();

   String getMessageStatistics();

   String getTaskContextInfo();

   String execGroovyScript(String var1);
}
