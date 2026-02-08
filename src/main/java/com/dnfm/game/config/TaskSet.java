package com.dnfm.game.config;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Default;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.util.NutMap;

@Table("p_taskset")
public class TaskSet implements Cloneable {
   @Id
   private int id;
   @Column
   private String taskName;
   @Column
   private String taskContent;
   @Column
   @ColDefine(
      width = 512
   )
   private String taskInstruction;
   @Column
   private String taskGift;
   @Column
   private String npcName;
   @Column
   @ColDefine(
      width = 512
   )
   private String buttton;
   @Column
   @Default("{}")
   @ColDefine(
      width = 256
   )
   private NutMap rewardList;
   @Column
   @Default("0")
   private int previousId;
   @Column
   @Default("0")
   private int nextId;
   @Column
   @ColDefine(
      width = 2048
   )
   private String taskTargetJson;

   public String getButtton() {
      return this.buttton;
   }

   public void setButtton(String buttton) {
      this.buttton = buttton;
   }

   public String getNpcName() {
      return this.npcName;
   }

   public void setNpcName(String npcName) {
      this.npcName = npcName;
   }

   public int getId() {
      return this.id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getTaskName() {
      return this.taskName;
   }

   public void setTaskName(String taskName) {
      this.taskName = taskName;
   }

   public String getTaskContent() {
      return this.taskContent;
   }

   public void setTaskContent(String taskContent) {
      this.taskContent = taskContent;
   }

   public String getTaskInstruction() {
      return this.taskInstruction;
   }

   public void setTaskInstruction(String taskInstruction) {
      this.taskInstruction = taskInstruction;
   }

   public String getTaskGift() {
      return this.taskGift;
   }

   public void setTaskGift(String taskGift) {
      this.taskGift = taskGift;
   }

   public NutMap getRewardList() {
      return this.rewardList;
   }

   public void setRewardList(NutMap rewardList) {
      this.rewardList = rewardList;
   }

   public int getNextId() {
      return this.nextId;
   }

   public void setNextId(int nextId) {
      this.nextId = nextId;
   }

   public int getPreviousId() {
      return this.previousId;
   }

   public void setPreviousId(int previousId) {
      this.previousId = previousId;
   }

   public String getTaskTargetJson() {
      return this.taskTargetJson;
   }

   public TaskSet clone() throws CloneNotSupportedException {
      return (TaskSet)super.clone();
   }
}
