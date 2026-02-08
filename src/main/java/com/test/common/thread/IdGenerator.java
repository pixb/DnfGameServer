package com.test.common.thread;

import com.test.common.spring.SpringUtils;
import com.test.mina.ServerConfig;
import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {
   private static AtomicLong generator = new AtomicLong(0L);

   public static long getNextId() {
      ServerConfig config = (ServerConfig)SpringUtils.getBean(ServerConfig.class);
      long serverId = (long)config.getServerId();
      return serverId << 48 | (System.currentTimeMillis() / 1000L & -1L) << 16 | generator.getAndIncrement() & 65535L;
   }
}
