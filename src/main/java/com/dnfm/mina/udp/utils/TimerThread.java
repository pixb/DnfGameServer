package com.dnfm.mina.udp.utils;

import java.util.PriorityQueue;

class TimerThread extends Thread {
   boolean run = true;
   private PriorityQueue<TimerTask> queue;

   public TimerThread(PriorityQueue<TimerTask> queue) {
      this.queue = queue;
   }

   public void run() {
      try {
         this.mainLoop();
      } finally {
         synchronized(this.queue) {
            this.run = false;
            this.queue.clear();
         }
      }

   }

   private void mainLoop() {
      while(true) {
         try {
            TimerTask task;
            boolean taskFired;
            synchronized(this.queue) {
               while(this.queue.isEmpty() && this.run) {
                  this.queue.wait();
               }

               if (this.queue.isEmpty()) {
                  return;
               }

               task = (TimerTask)this.queue.peek();
               long currentTime;
               long executionTime;
               synchronized(task.lock) {
                  if (task.state == 3) {
                     this.queue.poll();
                     continue;
                  }

                  currentTime = System.currentTimeMillis();
                  executionTime = task.nextExecutionTime;
                  if (taskFired = executionTime <= currentTime) {
                     task = (TimerTask)this.queue.poll();
                     if (task.period == 0L) {
                        task.state = 2;
                     } else {
                        long nextExecTime = task.period < 0L ? currentTime - task.period : executionTime + task.period;
                        task.nextExecutionTime = nextExecTime;
                        this.queue.add(task);
                     }
                  }
               }

               if (!taskFired) {
                  this.queue.wait(executionTime - currentTime);
               }
            }

            if (taskFired) {
               try {
                  task.run();
               } catch (Exception e) {
                  Timer.logger.error("timer任务执行异常", e);
               }
            }
         } catch (InterruptedException var16) {
         }
      }
   }
}
