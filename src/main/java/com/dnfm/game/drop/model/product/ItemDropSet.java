package com.dnfm.game.drop.model.product;

import org.nutz.dao.entity.annotation.Table;

@Table("p_itemdropset")
public class ItemDropSet {
   private String itemName;
   private int dropGroup;

   public void setItemName(String itemName) {
      this.itemName = itemName;
   }

   public void setDropGroup(int dropGroup) {
      this.dropGroup = dropGroup;
   }

   public String getItemName() {
      return this.itemName;
   }

   public int getDropGroup() {
      return this.dropGroup;
   }
}
