package com.dnfm.mina.udp.utils;

import java.util.Date;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Timer {
   static final Logger logger = LoggerFactory.getLogger(Timer.class);
   private PriorityQueue<TimerTask> queue;
   private final TimerThread thread;
   private static final AtomicInteger nextSerialNumber = new AtomicInteger(0);

   private static int serialNumber() {
      return nextSerialNumber.getAndIncrement();
   }

   public Timer() {
      this("Timer-" + serialNumber());
   }

   public Timer(boolean daemon) {
      this("Timer-" + serialNumber(), daemon);
   }

   public Timer(String threadName) {
      this.queue = new PriorityQueue();
      this.thread = new TimerThread(this.queue);
      this.thread.setName(threadName);
      this.thread.start();
   }

   public Timer(String threadName, boolean daemon) {
      this.queue = new PriorityQueue();
      this.thread = new TimerThread(this.queue);
      this.thread.setName(threadName);
      this.thread.setDaemon(daemon);
      this.thread.start();
   }

   public void schedule(Runnable task, long delay) {
      if (delay < 0L) {
         throw new IllegalArgumentException("Negative delay.");
      } else {
         this.schedule0(task, System.currentTimeMillis() + delay, 0L);
      }
   }

   public void schedule(Runnable task, Date start) {
      this.schedule0(task, start.getTime(), 0L);
   }

   public void scheduleAtFixedRate(Runnable task, long delay, long period) {
      if (delay < 0L) {
         throw new IllegalArgumentException("Negative delay.");
      } else if (period <= 0L) {
         throw new IllegalArgumentException("Non-positive period.");
      } else {
         this.schedule0(task, System.currentTimeMillis() + delay, period);
      }
   }

   public void scheduleAtFixedRate(Runnable task, Date start, long period) {
      if (period <= 0L) {
         throw new IllegalArgumentException("Non-positive period.");
      } else {
         this.schedule0(task, start.getTime(), period);
      }
   }

   private void schedule0(Runnable task, long time, long period) {
      if (time < 0L) {
         throw new IllegalArgumentException("Illegal execution time.");
      } else {
         if (Math.abs(period) > 4611686018427387903L) {
            period >>= 1;
         }

         TimerTask timerTask = new TimerTask(task);
         synchronized(this.queue) {
            if (!this.thread.run) {
               throw new IllegalStateException("Timer already cancelled.");
            } else {
               synchronized(timerTask.lock) {
                  if (timerTask.state != 0) {
                     throw new IllegalStateException("Task already scheduled or cancelled");
                  }

                  timerTask.nextExecutionTime = time;
                  timerTask.period = period;
                  timerTask.state = 1;
               }

               this.queue.add(timerTask);
               if (this.queue.peek() == timerTask) {
                  this.queue.notify();
               }

            }
         }
      }
   }

   public void cancel() {
      synchronized(this.queue) {
         this.thread.run = false;
         this.queue.clear();
         this.queue.notify();
      }
   }
}
