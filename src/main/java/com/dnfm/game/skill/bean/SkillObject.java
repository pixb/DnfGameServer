package com.dnfm.game.skill.bean;

public class SkillObject {
   private String name = "";
   private int index;
   private int cost = 0;
   private String type = "";
   private int growtype = 0;
   private int reqlevel = 0;
   private int levelrange = 0;
   private int maxlevel = 0;

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getIndex() {
      return this.index;
   }

   public void setIndex(int index) {
      this.index = index;
   }

   public int getCost() {
      return this.cost;
   }

   public void setCost(int cost) {
      this.cost = cost;
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

   public int getMaxlevel() {
      return this.maxlevel;
   }

   public void setMaxlevel(int maxlevel) {
      this.maxlevel = maxlevel;
   }
}
