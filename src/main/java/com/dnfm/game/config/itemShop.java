package com.dnfm.game.config;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

@Table("p_itemshop")
public class itemShop {
   @Column
   private int index;
   @Column
   private int goodsid;
   @Column
   private int moneytype;
   @Column
   private int moneycount;
   @Column
   private String limittype;
   @Column
   private String limitday;
   @Column
   private int limitcount;
   @Column
   private String shopname;

   public void setIndex(int index) {
      this.index = index;
   }

   public void setGoodsid(int goodsid) {
      this.goodsid = goodsid;
   }

   public void setMoneytype(int moneytype) {
      this.moneytype = moneytype;
   }

   public void setMoneycount(int moneycount) {
      this.moneycount = moneycount;
   }

   public void setLimittype(String limittype) {
      this.limittype = limittype;
   }

   public void setLimitday(String limitday) {
      this.limitday = limitday;
   }

   public void setLimitcount(int limitcount) {
      this.limitcount = limitcount;
   }

   public void setShopname(String shopname) {
      this.shopname = shopname;
   }

   public int getIndex() {
      return this.index;
   }

   public int getGoodsid() {
      return this.goodsid;
   }

   public int getMoneytype() {
      return this.moneytype;
   }

   public int getMoneycount() {
      return this.moneycount;
   }

   public String getLimittype() {
      return this.limittype;
   }

   public String getLimitday() {
      return this.limitday;
   }

   public int getLimitcount() {
      return this.limitcount;
   }

   public String getShopname() {
      return this.shopname;
   }
}
