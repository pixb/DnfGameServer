package com.test.mina.udp;

import com.test.common.thread.NamedThreadFactory;
import com.test.game.utils.JsonUtils;
import com.test.logs.LoggerUtils;
import com.test.mina.task.AbstractDistributeTask;
import com.test.mina.task.TimerTask;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum UdpTaskHandlerContext {
   INSTANCE;

   private static Logger logger = LoggerFactory.getLogger(UdpTaskHandlerContext.class);
   private final int CORE_SIZE = Runtime.getRuntime().availableProcessors();
   private final List<TaskWorker> workerPool = new ArrayList();
   private final AtomicBoolean run = new AtomicBoolean(true);

   public void initialize() {
      int size = this.CORE_SIZE;
      logger.error("UdpTaskHandlerContext.initialize=={}", size);

      for(int i = 1; i <= size; ++i) {
         TaskWorker worker = new TaskWorker(i);
         this.workerPool.add(worker);
         (new NamedThreadFactory("udp-message-task-handler_" + i)).newThread(worker).start();
      }

   }

   public void acceptTask(AbstractDistributeTask task) {
      if (task == null) {
         throw new NullPointerException("udp-task is null");
      } else {
         int distributeKey = task.distributeKey() % this.workerPool.size();
         ((TaskWorker)this.workerPool.get(distributeKey)).addTask(task);
      }
   }

   public void shutDown() {
      this.run.set(false);
      LoggerUtils.error("关闭UDP线程结束");
   }

   public String statistics() {
      Map<Integer, Integer> map = new HashMap();

      for(TaskWorker worker : this.workerPool) {
         map.put(worker.id, worker.taskQueue.size());
      }

      return JsonUtils.map2String(map);
   }

   private class TaskWorker implements Runnable {
      private int id;
      private BlockingQueue<AbstractDistributeTask> taskQueue = new LinkedBlockingQueue();

      TaskWorker(int index) {
         this.id = index;
      }

      public void addTask(AbstractDistributeTask task) {
         this.taskQueue.add(task);
      }

      public void run() {
         while(UdpTaskHandlerContext.this.run.get()) {
            try {
               AbstractDistributeTask task = (AbstractDistributeTask)this.taskQueue.take();
               task.markStartMillis();
               task.action();
               task.markEndMillis();
               if (task instanceof TimerTask) {
                  TimerTask timerTask = (TimerTask)task;
                  if (timerTask.canRunAgain()) {
                     this.addTask(task);
                  }
               }
            } catch (Exception e) {
               UdpTaskHandlerContext.logger.error("udp task Worker" + this.id, e);
            }
         }

      }
   }
}
