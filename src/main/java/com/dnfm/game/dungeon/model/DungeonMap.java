package com.dnfm.game.dungeon.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("p_dungeonmap")
public class DungeonMap {
   @Id
   private int index;
   @Column
   private int dungeon;
   @Column
   private String type;
   @Column
   private String monster;
   @Column
   private String ai;

   public int getIndex() {
      return this.index;
   }

   public int getDungeon() {
      return this.dungeon;
   }

   public String getType() {
      return this.type;
   }

   public String getMonster() {
      return this.monster;
   }

   public String getAi() {
      return this.ai;
   }

   public void setIndex(int index) {
      this.index = index;
   }

   public void setDungeon(int dungeon) {
      this.dungeon = dungeon;
   }

   public void setType(String type) {
      this.type = type;
   }

   public void setMonster(String monster) {
      this.monster = monster;
   }

   public void setAi(String ai) {
      this.ai = ai;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof DungeonMap)) {
         return false;
      } else {
         DungeonMap other = (DungeonMap)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getIndex() != other.getIndex()) {
            return false;
         } else if (this.getDungeon() != other.getDungeon()) {
            return false;
         } else {
            Object this$type = this.getType();
            Object other$type = other.getType();
            if (this$type == null) {
               if (other$type != null) {
                  return false;
               }
            } else if (!this$type.equals(other$type)) {
               return false;
            }

            Object this$monster = this.getMonster();
            Object other$monster = other.getMonster();
            if (this$monster == null) {
               if (other$monster != null) {
                  return false;
               }
            } else if (!this$monster.equals(other$monster)) {
               return false;
            }

            Object this$ai = this.getAi();
            Object other$ai = other.getAi();
            if (this$ai == null) {
               if (other$ai != null) {
                  return false;
               }
            } else if (!this$ai.equals(other$ai)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof DungeonMap;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getIndex();
      result = result * 59 + this.getDungeon();
      Object $type = this.getType();
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      Object $monster = this.getMonster();
      result = result * 59 + ($monster == null ? 43 : $monster.hashCode());
      Object $ai = this.getAi();
      result = result * 59 + ($ai == null ? 43 : $ai.hashCode());
      return result;
   }

   public String toString() {
      return "DungeonMap(index=" + this.getIndex() + ", dungeon=" + this.getDungeon() + ", type=" + this.getType() + ", monster=" + this.getMonster() + ", ai=" + this.getAi() + ")";
   }
}
