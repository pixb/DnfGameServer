package com.test.mina.task;

public abstract class TimerTask extends AbstractDistributeTask {
   private int currLoop;
   private int maxLoop;

   public TimerTask(int distributeKey) {
      this(distributeKey, 1);
   }

   public TimerTask(int distributeKey, int maxLoop) {
      this.distributeKey = distributeKey;
      this.maxLoop = maxLoop;
   }

   public void updateLoopTimes() {
      ++this.currLoop;
   }

   public boolean canRunAgain() {
      if (this.maxLoop <= 0) {
         return true;
      } else {
         return this.currLoop < this.maxLoop;
      }
   }
}
