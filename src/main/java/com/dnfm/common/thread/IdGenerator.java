package com.dnfm.common.thread;

import com.dnfm.common.spring.SpringUtils;
import com.dnfm.mina.ServerConfig;
import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {
   private static final AtomicLong generator = new AtomicLong(0L);

   public static long getNextId() {
      ServerConfig config = (ServerConfig)SpringUtils.getBean(ServerConfig.class);
      long serverId = (long)config.getServerId();
      return serverId << 48 | (System.currentTimeMillis() / 1000L & -1L) << 16 | generator.getAndIncrement() & 65535L;
   }
}
