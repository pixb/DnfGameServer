package com.dnfm.common.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

public class VisiableThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {
   private static final Logger logger = LoggerFactory.getLogger(VisiableThreadPoolTaskExecutor.class);

   private void showThreadPoolInfo(String prefix) {
      ThreadPoolExecutor threadPoolExecutor = this.getThreadPoolExecutor();
      if (null != threadPoolExecutor) {
         logger.info("{}, {},taskCount [{}], completedTaskCount [{}], activeCount [{}], queueSize [{}]", new Object[]{this.getThreadNamePrefix(), prefix, threadPoolExecutor.getTaskCount(), threadPoolExecutor.getCompletedTaskCount(), threadPoolExecutor.getActiveCount(), threadPoolExecutor.getQueue().size()});
      }
   }

   public void execute(Runnable task) {
      this.showThreadPoolInfo("1. do execute");
      super.execute(task);
   }

   public void execute(Runnable task, long startTimeout) {
      this.showThreadPoolInfo("2. do execute");
      super.execute(task, startTimeout);
   }

   public Future<?> submit(Runnable task) {
      this.showThreadPoolInfo("1. do submit");
      return super.submit(task);
   }

   public <T> Future<T> submit(Callable<T> task) {
      this.showThreadPoolInfo("2. do submit");
      return super.submit(task);
   }

   public ListenableFuture<?> submitListenable(Runnable task) {
      this.showThreadPoolInfo("1. do submitListenable");
      return super.submitListenable(task);
   }

   public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
      this.showThreadPoolInfo("2. do submitListenable");
      return super.submitListenable(task);
   }
}
