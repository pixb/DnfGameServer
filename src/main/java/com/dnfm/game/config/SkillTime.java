package com.dnfm.game.config;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("p_skill_play_time")
public class SkillTime {
   @Id
   private int id;
   @Column
   private String name;
   @Column
   private int millisecond;

   public int getId() {
      return this.id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getMillisecond() {
      return this.millisecond;
   }

   public void setMillisecond(int millisecond) {
      this.millisecond = millisecond;
   }
}
