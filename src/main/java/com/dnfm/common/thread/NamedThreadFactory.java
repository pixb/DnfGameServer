package com.dnfm.common.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NamedThreadFactory implements ThreadFactory {
   private final boolean daemo;
   private ThreadGroup threadGroup;
   private final String groupName;
   private final AtomicInteger idGenerator;

   public NamedThreadFactory(String group) {
      this(group, false);
   }

   public NamedThreadFactory(String group, boolean daemo) {
      this.idGenerator = new AtomicInteger(1);
      this.groupName = group;
      this.daemo = daemo;
   }

   public Thread newThread(Runnable r) {
      String name = this.getNextThreadName();
      Thread ret = new Thread(this.threadGroup, r, name, 0L);
      ret.setDaemon(this.daemo);
      return ret;
   }

   private String getNextThreadName() {
      return this.groupName + "-thread-" + this.idGenerator.getAndIncrement();
   }
}
