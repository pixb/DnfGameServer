package com.dnfm.game.config;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("p_consume")
public class ConsumeItem {
   @Column
   @Comment("物品名称")
   private String itemname;
   @Id
   private int index;
   @Column
   @Comment("客户端物品类型")
   private int stackabletype;
   @Column
   @Comment("物品品质")
   private int grade;
   @Column
   @Comment("类别")
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
   @Comment("稀有度")
   private int rarity;
   @Column
   @Comment("物品等级")
   private int level;
   @Column
   @Comment("出售价格")
   private int sellprice;

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

   public int getStackabletype() {
      return this.stackabletype;
   }

   public void setStackabletype(int stackabletype) {
      this.stackabletype = stackabletype;
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

   public int getRarity() {
      return this.rarity;
   }

   public int getLevel() {
      return this.level;
   }

   public int getSellprice() {
      return this.sellprice;
   }

   public void setSubtype(int subtype) {
      this.subtype = subtype;
   }

   public void setRarity(int rarity) {
      this.rarity = rarity;
   }

   public void setLevel(int level) {
      this.level = level;
   }

   public void setSellprice(int sellprice) {
      this.sellprice = sellprice;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ConsumeItem)) {
         return false;
      } else {
         ConsumeItem other = (ConsumeItem)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$itemname = this.getItemname();
            Object other$itemname = other.getItemname();
            if (this$itemname == null) {
               if (other$itemname != null) {
                  return false;
               }
            } else if (!this$itemname.equals(other$itemname)) {
               return false;
            }

            if (this.getIndex() != other.getIndex()) {
               return false;
            } else if (this.getStackabletype() != other.getStackabletype()) {
               return false;
            } else if (this.getGrade() != other.getGrade()) {
               return false;
            } else if (this.getSubtype() != other.getSubtype()) {
               return false;
            } else if (this.getWeight() != other.getWeight()) {
               return false;
            } else if (this.getScore() != other.getScore()) {
               return false;
            } else if (this.getMinlevel() != other.getMinlevel()) {
               return false;
            } else if (this.getRarity() != other.getRarity()) {
               return false;
            } else if (this.getLevel() != other.getLevel()) {
               return false;
            } else if (this.getSellprice() != other.getSellprice()) {
               return false;
            } else {
               return true;
            }
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ConsumeItem;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $itemname = this.getItemname();
      result = result * 59 + ($itemname == null ? 43 : $itemname.hashCode());
      result = result * 59 + this.getIndex();
      result = result * 59 + this.getStackabletype();
      result = result * 59 + this.getGrade();
      result = result * 59 + this.getSubtype();
      result = result * 59 + this.getWeight();
      result = result * 59 + this.getScore();
      result = result * 59 + this.getMinlevel();
      result = result * 59 + this.getRarity();
      result = result * 59 + this.getLevel();
      result = result * 59 + this.getSellprice();
      return result;
   }

   public String toString() {
      return "ConsumeItem(itemname=" + this.getItemname() + ", index=" + this.getIndex() + ", stackabletype=" + this.getStackabletype() + ", grade=" + this.getGrade() + ", subtype=" + this.getSubtype() + ", weight=" + this.getWeight() + ", score=" + this.getScore() + ", minlevel=" + this.getMinlevel() + ", rarity=" + this.getRarity() + ", level=" + this.getLevel() + ", sellprice=" + this.getSellprice() + ")";
   }
}
