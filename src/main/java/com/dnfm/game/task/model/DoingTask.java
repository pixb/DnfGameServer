package com.dnfm.game.task.model;

public class DoingTask {
   private int taskId;
   private int targetProgress;
   private int fightId;
   private int npcId;
   private long createTime;

   public DoingTask() {
   }

   public DoingTask(int taskId, int targetProgress) {
      this.taskId = taskId;
      this.targetProgress = targetProgress;
      this.createTime = System.currentTimeMillis();
   }

   public int getTaskId() {
      return this.taskId;
   }

   public void setTaskId(int taskId) {
      this.taskId = taskId;
   }

   public int getTargetProgress() {
      return this.targetProgress;
   }

   public void setTargetProgress(int targetProgress) {
      this.targetProgress = targetProgress;
   }

   public void addProgress(int targetIndex) {
      this.targetProgress = targetIndex + 1;
   }

   public int getFightId() {
      return this.fightId;
   }

   public void setFightId(int fightId) {
      this.fightId = fightId;
   }

   public int getNpcId() {
      return this.npcId;
   }

   public void setNpcId(int npcId) {
      this.npcId = npcId;
   }

   public long getCreateTime() {
      return this.createTime;
   }

   public void setCreateTime(long createTime) {
      this.createTime = createTime;
   }
}
