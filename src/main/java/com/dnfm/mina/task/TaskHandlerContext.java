package com.dnfm.mina.task;

import com.dnfm.common.thread.NamedThreadFactory;
import com.dnfm.game.utils.JsonUtils;
import com.dnfm.logs.LoggerUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum TaskHandlerContext {
   INSTANCE;

   private static final Logger logger = LoggerFactory.getLogger(TaskHandlerContext.class);
   private final int CORE_SIZE = Runtime.getRuntime().availableProcessors();
   private final List<TaskWorker> workerPool = new ArrayList();
   private final AtomicBoolean run = new AtomicBoolean(true);

   public void initialize() {
      int size = this.CORE_SIZE;
      logger.error("TaskHandlerContext.initialize=={}", size);

      for(int i = 1; i <= size; ++i) {
         TaskWorker worker = new TaskWorker(i);
         this.workerPool.add(worker);
         (new NamedThreadFactory("message-task-handler_" + i)).newThread(worker).start();
      }

   }

   public void acceptTask(AbstractDistributeTask task) {
      if (task == null) {
         throw new NullPointerException("task is null");
      } else {
         int distributeKey = task.distributeKey() % this.workerPool.size();
         ((TaskWorker)this.workerPool.get(distributeKey)).addTask(task);
      }
   }

   public void shutDown() {
      this.run.set(false);
      LoggerUtils.error("关闭业务场景线程结束");
   }

   public String statistics() {
      Map<Integer, Integer> map = new HashMap();

      for(TaskWorker worker : this.workerPool) {
         map.put(worker.id, worker.taskQueue.size());
      }

      return JsonUtils.map2String(map);
   }

   private class TaskWorker implements Runnable {
      private final int id;
      private final BlockingQueue<AbstractDistributeTask> taskQueue = new LinkedBlockingQueue();

      TaskWorker(int index) {
         this.id = index;
      }

      public void addTask(AbstractDistributeTask task) {
         this.taskQueue.add(task);
      }

      public void run() {
         while(TaskHandlerContext.this.run.get()) {
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
               TaskHandlerContext.logger.error("task Worker" + this.id, e);
            }
         }

      }
   }
}
