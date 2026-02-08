package com.dnfm.game.bag.model;

import com.dnfm.mina.protobuf.PT_MONEY_ITEM;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoneyBox {
   private static final Logger log = LoggerFactory.getLogger(MoneyBox.class);
   private Map<Integer, PT_MONEY_ITEM> currency = new HashMap();
   private Map<Integer, PT_MONEY_ITEM> dailygain = new HashMap();

   public void subTeraForAuction(int cnt) {
      PT_MONEY_ITEM ptMoneyItem = (PT_MONEY_ITEM)this.currency.get(2013000001);
      log.error("before-tera==" + ptMoneyItem.count);
      if (ptMoneyItem != null) {
         ptMoneyItem.count = ptMoneyItem.count - cnt;
      }

      log.error("after-tera==" + ptMoneyItem.count);
   }

   public void addCnt(int index, int cnt) {
      if (this.currency.get(index) == null) {
         PT_MONEY_ITEM item = new PT_MONEY_ITEM();
         item.index = index;
         item.count = cnt;
         this.currency.put(index, item);
      } else {
         PT_MONEY_ITEM item = (PT_MONEY_ITEM)this.currency.get(index);
         item.count = item.count + cnt;
         this.currency.put(index, item);
      }

   }

   public void subCnt(int index, int cnt) {
      PT_MONEY_ITEM item = (PT_MONEY_ITEM)this.currency.get(index);
      item.count = item.count - cnt;
      this.currency.put(index, item);
   }

   public void putCurrency(PT_MONEY_ITEM pt_money_item) {
      this.currency.put(pt_money_item.index, pt_money_item);
   }

   public void putAccountCurrency(PT_MONEY_ITEM pt_money_item) {
      this.currency.put(pt_money_item.index, pt_money_item);
   }

   public void updateCurrency(PT_MONEY_ITEM pt_money_item) {
      PT_MONEY_ITEM oldMoneyItem = (PT_MONEY_ITEM)this.currency.get(pt_money_item.index);
      if (oldMoneyItem == null) {
         this.currency.put(pt_money_item.index, pt_money_item);
      } else {
         oldMoneyItem.count = oldMoneyItem.count + pt_money_item.count;
      }

   }

   public PT_MONEY_ITEM getMoneyItem(int index) {
      return (PT_MONEY_ITEM)this.currency.get(index);
   }

   public PT_MONEY_ITEM getAccountMoneyItem(int index) {
      return (PT_MONEY_ITEM)this.currency.get(index);
   }

   public int getMoneyCnt() {
      PT_MONEY_ITEM ptMoneyItem = this.getMoneyItem(0);
      return ptMoneyItem == null ? 0 : ptMoneyItem.count;
   }

   public void addmoney(int count) {
      PT_MONEY_ITEM pt_money_item = (PT_MONEY_ITEM)this.currency.get(0);
      pt_money_item.count = pt_money_item.count + count;
      this.currency.put(0, pt_money_item);
   }

   public void submoney(int count) {
      PT_MONEY_ITEM pt_money_item = (PT_MONEY_ITEM)this.currency.get(0);
      if (pt_money_item.count - count >= 0) {
         pt_money_item.count = pt_money_item.count - count;
         this.currency.put(0, pt_money_item);
      }

   }

   public int getFuhuoCnt() {
      PT_MONEY_ITEM pt_money_item = (PT_MONEY_ITEM)this.currency.get(2013100902);
      return pt_money_item == null ? 0 : pt_money_item.count;
   }

   public void subFuhuoCnt() {
      PT_MONEY_ITEM pt_money_item = (PT_MONEY_ITEM)this.currency.get(2013100902);
      if (pt_money_item != null) {
         pt_money_item.count = pt_money_item.count - 1;
         this.currency.put(2013100902, pt_money_item);
      }

   }

   public Map<Integer, PT_MONEY_ITEM> getCurrency() {
      return this.currency;
   }

   public Map<Integer, PT_MONEY_ITEM> getDailygain() {
      return this.dailygain;
   }

   public void setCurrency(Map<Integer, PT_MONEY_ITEM> currency) {
      this.currency = currency;
   }

   public void setDailygain(Map<Integer, PT_MONEY_ITEM> dailygain) {
      this.dailygain = dailygain;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof MoneyBox)) {
         return false;
      } else {
         MoneyBox other = (MoneyBox)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$currency = this.getCurrency();
            Object other$currency = other.getCurrency();
            if (this$currency == null) {
               if (other$currency != null) {
                  return false;
               }
            } else if (!this$currency.equals(other$currency)) {
               return false;
            }

            Object this$dailygain = this.getDailygain();
            Object other$dailygain = other.getDailygain();
            if (this$dailygain == null) {
               if (other$dailygain != null) {
                  return false;
               }
            } else if (!this$dailygain.equals(other$dailygain)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof MoneyBox;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $currency = this.getCurrency();
      result = result * 59 + ($currency == null ? 43 : $currency.hashCode());
      Object $dailygain = this.getDailygain();
      result = result * 59 + ($dailygain == null ? 43 : $dailygain.hashCode());
      return result;
   }

   public String toString() {
      return "MoneyBox(currency=" + this.getCurrency() + ", dailygain=" + this.getDailygain() + ")";
   }
}
