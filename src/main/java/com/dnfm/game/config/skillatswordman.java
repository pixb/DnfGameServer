package com.dnfm.game.config;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("p_skillatswordman")
public class skillatswordman {
   @Id
   @Column("index")
   private int index;
   @Column("name")
   @Comment("技能名称")
   private String skillName;
   @Column("type")
   private String type;
   @Column("growtype")
   private int growtype;
   @Column("reqlevel")
   private int reqlevel;
   @Column("levelrange")
   private int levelrange;
   @Column("cost")
   private int cost;
   @Column("maxlevel")
   private int maxlevel;

   public int getIndex() {
      return this.index;
   }

   public void setIndex(int index) {
      this.index = index;
   }

   public String getSkillName() {
      return this.skillName;
   }

   public void setSkillName(String skillName) {
      this.skillName = skillName;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public int getGrowtype() {
      return this.growtype;
   }

   public void setGrowtype(int growtype) {
      this.growtype = growtype;
   }

   public int getReqlevel() {
      return this.reqlevel;
   }

   public void setReqlevel(int reqlevel) {
      this.reqlevel = reqlevel;
   }

   public int getLevelrange() {
      return this.levelrange;
   }

   public void setLevelrange(int levelrange) {
      this.levelrange = levelrange;
   }

   public int getCost() {
      return this.cost;
   }

   public void setCost(int cost) {
      this.cost = cost;
   }

   public int getMaxlevel() {
      return this.maxlevel;
   }

   public void setMaxlevel(int maxlevel) {
      this.maxlevel = maxlevel;
   }
}
