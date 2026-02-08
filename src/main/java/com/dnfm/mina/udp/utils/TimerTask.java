package com.dnfm.mina.udp.utils;

class TimerTask implements Runnable, Comparable<TimerTask> {
   private Runnable task;
   final Object lock = new Object();
   int state = 0;
   static final int VIRGIN = 0;
   static final int SCHEDULED = 1;
   static final int EXECUTED = 2;
   static final int CANCELLED = 3;
   long nextExecutionTime;
   long period = 0L;

   TimerTask(Runnable task) {
      this.task = task;
   }

   public int compareTo(TimerTask o) {
      if (o.nextExecutionTime > this.nextExecutionTime) {
         return -1;
      } else {
         return o.nextExecutionTime < this.nextExecutionTime ? 1 : 0;
      }
   }

   public void run() {
      this.task.run();
   }
}
