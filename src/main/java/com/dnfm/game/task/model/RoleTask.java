package com.dnfm.game.task.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(
   ignoreUnknown = true
)
public class RoleTask {
   public static final byte NOT_SUBMIT = 0;
   public static final byte CAN_SUBMIT = 1;
   private Map<Integer, Byte> acceptedTasks = new HashMap();
   private Set<Integer> finishedTasks = new HashSet();
   private Map<Integer, DoingTask> doingTask = new HashMap();
   private Map<Integer, Long> giveUpTimes;
   private transient int gatherTaskId;

   public void acceptTask(int taskId) {
      this.acceptedTasks.put(taskId, (byte)0);
      this.doingTask.put(taskId, new DoingTask(taskId, 0));
   }

   public void updateToCanSubmit(int taskId) {
      Byte status = (Byte)this.acceptedTasks.get(taskId);
      if (status != null) {
         if (status == 0) {
            this.acceptedTasks.put(taskId, (byte)1);
         }

      }
   }

   public void updateToFinished(int taskId) {
      this.removeAcceptedTask(taskId);
      this.removeDoingTask(taskId);
      this.finishedTasks.add(taskId);
   }

   public boolean isCanSubmit(int taskId) {
      Byte status = (Byte)this.acceptedTasks.get(taskId);
      if (status == null) {
         return false;
      } else {
         return status == 1;
      }
   }

   public boolean isAccepted(int taskId) {
      return this.acceptedTasks.containsKey(taskId);
   }

   public boolean isFinished(int taskId) {
      return this.finishedTasks.contains(taskId);
   }

   private void removeAcceptedTask(int taskId) {
      this.acceptedTasks.remove(taskId);
   }

   private void removeDoingTask(int taskId) {
      this.doingTask.remove(taskId);
   }

   public int getTargetProgress(int taskId) {
      DoingTask doingTask = (DoingTask)this.doingTask.get(taskId);
      return doingTask == null ? 0 : doingTask.getTargetProgress();
   }

   public void updateTargetProgress(int taskId, int targetIndex, int targetCount) {
      DoingTask doingTask = (DoingTask)this.doingTask.get(taskId);
      if (doingTask == null) {
         doingTask = new DoingTask(taskId, 0);
         this.doingTask.put(taskId, doingTask);
      }

      doingTask.addProgress(targetIndex);
      if (doingTask.getTargetProgress() == targetCount) {
         this.updateToCanSubmit(taskId);
      }

   }

   public void revertTargetProgress(int taskId, int targetIndex) {
      DoingTask doingTask = (DoingTask)this.doingTask.get(taskId);
      if (doingTask != null) {
         doingTask = new DoingTask(taskId, targetIndex);
         this.doingTask.put(taskId, doingTask);
      }

      doingTask.setTargetProgress(targetIndex);
   }

   @JsonIgnore
   public Map<Integer, Integer> getAllAcceptedTaskProgress() {
      Map<Integer, Integer> progressMap = new HashMap(this.acceptedTasks.size());

      for(int taskId : this.acceptedTasks.keySet()) {
         int progress = this.getTargetProgress(taskId);
         progressMap.put(taskId, progress);
      }

      return progressMap;
   }

   @JsonIgnore
   public List<Integer> getAcceptedTaskList() {
      List<Integer> list = new ArrayList(this.acceptedTasks.size());
      list.addAll(this.acceptedTasks.keySet());
      return list;
   }

   public void addRecord4FightAndTask(int fightId, int taskId) {
      DoingTask doingTask = (DoingTask)this.doingTask.get(taskId);
      if (doingTask == null) {
         doingTask = new DoingTask(taskId, 0);
         this.doingTask.put(taskId, doingTask);
      }

      doingTask.setFightId(fightId);
   }

   public Integer getTaskIdByFightId(int fightId) {
      for(DoingTask doTask : this.doingTask.values()) {
         if (fightId == doTask.getFightId()) {
            return doTask.getTaskId();
         }
      }

      return null;
   }

   public void resetTask(int taskId) {
      this.deleteTask(taskId);
      this.finishedTasks.remove(taskId);
   }

   public void giveUpTask(int taskId) {
      this.deleteTask(taskId);
   }

   public void deleteTask(int taskId) {
      this.removeAcceptedTask(taskId);
      this.removeDoingTask(taskId);
   }

   public long getGiveUpTime(TaskType taskType) {
      return this.giveUpTimes != null && this.giveUpTimes.containsKey(taskType.getId()) ? (Long)this.giveUpTimes.get(taskType.getId()) : -1L;
   }

   public void addGiveUpTime(TaskType taskType, long time) {
      if (this.giveUpTimes == null) {
         this.giveUpTimes = new HashMap();
      }

      this.giveUpTimes.put(taskType.getId(), time);
   }

   public void removeGiveUpTime(TaskType taskType) {
      if (this.giveUpTimes != null) {
         this.giveUpTimes.remove(taskType.getId());
      }

   }

   public void addTaskNpcId(int taskId, int npcId) {
      DoingTask doingTask = (DoingTask)this.doingTask.get(taskId);
      if (doingTask == null) {
         doingTask = new DoingTask(taskId, 0);
         this.doingTask.put(taskId, doingTask);
      }

      doingTask.setNpcId(npcId);
   }

   public int getTaskNpcId(int taskId) {
      DoingTask doingTask = (DoingTask)this.doingTask.get(taskId);
      return doingTask != null ? doingTask.getNpcId() : 0;
   }

   public long getAcceptTime(int taskId) {
      DoingTask doingTask = (DoingTask)this.doingTask.get(taskId);
      return doingTask != null ? doingTask.getCreateTime() : 0L;
   }

   public Map<Integer, Byte> getAcceptedTasks() {
      return this.acceptedTasks;
   }

   public Set<Integer> getFinishedTasks() {
      return this.finishedTasks;
   }

   @JsonIgnore
   public int getGatherTaskId() {
      return this.gatherTaskId;
   }

   public void setGatherTaskId(int gatherTaskId) {
      this.gatherTaskId = gatherTaskId;
   }

   public Map<Integer, DoingTask> getDoingTask() {
      return this.doingTask;
   }

   public Map<Integer, Long> getGiveUpTimes() {
      return this.giveUpTimes;
   }

   public void setAcceptedTasks(Map<Integer, Byte> acceptedTasks) {
      this.acceptedTasks = acceptedTasks;
   }

   public void setFinishedTasks(Set<Integer> finishedTasks) {
      this.finishedTasks = finishedTasks;
   }

   public void setDoingTask(Map<Integer, DoingTask> doingTask) {
      this.doingTask = doingTask;
   }

   public void setGiveUpTimes(Map<Integer, Long> giveUpTimes) {
      this.giveUpTimes = giveUpTimes;
   }
}
