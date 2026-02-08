package com.dnfm.game.task.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("p_taskinfo")
public class TaskInfo {
   @Id
   private int index;
   @Column
   private String name;
   @Column
   private int rewardtera;
   @Column
   private int rewardexp;
   @Column
   private int rewardgold;
   @Column
   private int rewardgrow;
   @Column
   private int rewardpromotion;
   @Column
   private String rewarditem;
   @Column
   private String map;

   public int getIndex() {
      return this.index;
   }

   public String getName() {
      return this.name;
   }

   public int getRewardtera() {
      return this.rewardtera;
   }

   public int getRewardexp() {
      return this.rewardexp;
   }

   public int getRewardgold() {
      return this.rewardgold;
   }

   public int getRewardgrow() {
      return this.rewardgrow;
   }

   public int getRewardpromotion() {
      return this.rewardpromotion;
   }

   public String getRewarditem() {
      return this.rewarditem;
   }

   public String getMap() {
      return this.map;
   }

   public void setIndex(int index) {
      this.index = index;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setRewardtera(int rewardtera) {
      this.rewardtera = rewardtera;
   }

   public void setRewardexp(int rewardexp) {
      this.rewardexp = rewardexp;
   }

   public void setRewardgold(int rewardgold) {
      this.rewardgold = rewardgold;
   }

   public void setRewardgrow(int rewardgrow) {
      this.rewardgrow = rewardgrow;
   }

   public void setRewardpromotion(int rewardpromotion) {
      this.rewardpromotion = rewardpromotion;
   }

   public void setRewarditem(String rewarditem) {
      this.rewarditem = rewarditem;
   }

   public void setMap(String map) {
      this.map = map;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof TaskInfo)) {
         return false;
      } else {
         TaskInfo other = (TaskInfo)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getIndex() != other.getIndex()) {
            return false;
         } else {
            Object this$name = this.getName();
            Object other$name = other.getName();
            if (this$name == null) {
               if (other$name != null) {
                  return false;
               }
            } else if (!this$name.equals(other$name)) {
               return false;
            }

            if (this.getRewardtera() != other.getRewardtera()) {
               return false;
            } else if (this.getRewardexp() != other.getRewardexp()) {
               return false;
            } else if (this.getRewardgold() != other.getRewardgold()) {
               return false;
            } else if (this.getRewardgrow() != other.getRewardgrow()) {
               return false;
            } else if (this.getRewardpromotion() != other.getRewardpromotion()) {
               return false;
            } else {
               Object this$rewarditem = this.getRewarditem();
               Object other$rewarditem = other.getRewarditem();
               if (this$rewarditem == null) {
                  if (other$rewarditem != null) {
                     return false;
                  }
               } else if (!this$rewarditem.equals(other$rewarditem)) {
                  return false;
               }

               Object this$map = this.getMap();
               Object other$map = other.getMap();
               if (this$map == null) {
                  if (other$map != null) {
                     return false;
                  }
               } else if (!this$map.equals(other$map)) {
                  return false;
               }

               return true;
            }
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof TaskInfo;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getIndex();
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      result = result * 59 + this.getRewardtera();
      result = result * 59 + this.getRewardexp();
      result = result * 59 + this.getRewardgold();
      result = result * 59 + this.getRewardgrow();
      result = result * 59 + this.getRewardpromotion();
      Object $rewarditem = this.getRewarditem();
      result = result * 59 + ($rewarditem == null ? 43 : $rewarditem.hashCode());
      Object $map = this.getMap();
      result = result * 59 + ($map == null ? 43 : $map.hashCode());
      return result;
   }

   public String toString() {
      return "TaskInfo(index=" + this.getIndex() + ", name=" + this.getName() + ", rewardtera=" + this.getRewardtera() + ", rewardexp=" + this.getRewardexp() + ", rewardgold=" + this.getRewardgold() + ", rewardgrow=" + this.getRewardgrow() + ", rewardpromotion=" + this.getRewardpromotion() + ", rewarditem=" + this.getRewarditem() + ", map=" + this.getMap() + ")";
   }
}
