package com.dnfm.game.config;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("p_skin")
public class Skin {
   @Column
   @Comment("物品名称")
   private String itemname;
   @Id
   private int index;
   @Column
   @Comment("客户端物品类型")
   private int periodtype;
   @Column
   @Comment("物品品质")
   private int grade;
   @Column
   @Comment("物品类型")
   private int subtype;
   @Column
   @Comment("物品重量")
   private int weight;
   @Column
   @Comment("抗魔值")
   private int score;
   @Column
   @Comment("最低使用等级")
   private int minlevel;
   @Column
   @Comment("物品介绍")
   private String iteminfo;

   public String getItemname() {
      return this.itemname;
   }

   public void setItemname(String itemname) {
      this.itemname = itemname;
   }

   public int getIndex() {
      return this.index;
   }

   public void setIndex(int index) {
      this.index = index;
   }

   public String getIteminfo() {
      return this.iteminfo;
   }

   public void setIteminfo(String iteminfo) {
      this.iteminfo = iteminfo;
   }

   public int getPeriodtype() {
      return this.periodtype;
   }

   public void setPeriodtype(int periodtype) {
      this.periodtype = periodtype;
   }

   public int getGrade() {
      return this.grade;
   }

   public void setGrade(int grade) {
      this.grade = grade;
   }

   public int getWeight() {
      return this.weight;
   }

   public void setWeight(int weight) {
      this.weight = weight;
   }

   public int getScore() {
      return this.score;
   }

   public void setScore(int score) {
      this.score = score;
   }

   public int getMinlevel() {
      return this.minlevel;
   }

   public void setMinlevel(int minlevel) {
      this.minlevel = minlevel;
   }

   public int getSubtype() {
      return this.subtype;
   }
}
