package com.dnfm.game.config;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("p_talkset")
public class TalkSet implements Cloneable {
   @Id
   private int id;
   @Column
   private String npcName;
   @Column
   private short npcIcon;
   @Column
   @ColDefine(
      width = 512
   )
   private String msg;
   @Column
   private String taskName;
   @Column
   private String name;
   @Column
   private int nextId;

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getId() {
      return this.id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getNpcName() {
      return this.npcName;
   }

   public void setNpcName(String npcName) {
      this.npcName = npcName;
   }

   public short getNpcIcon() {
      return this.npcIcon;
   }

   public void setNpcIcon(short npcIcon) {
      this.npcIcon = npcIcon;
   }

   public String getMsg() {
      return this.msg;
   }

   public void setMsg(String msg) {
      this.msg = msg;
   }

   public String getTaskName() {
      return this.taskName;
   }

   public void setTaskName(String taskName) {
      this.taskName = taskName;
   }

   public int getNextId() {
      return this.nextId;
   }

   public void setNextId(int nextId) {
      this.nextId = nextId;
   }

   public TalkSet clone() throws CloneNotSupportedException {
      return (TalkSet)super.clone();
   }
}
