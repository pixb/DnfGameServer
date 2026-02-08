package com.dnfm.game.bag.model;

import com.dnfm.mina.protobuf.PT_MONEY_ITEM;
import java.util.HashMap;
import java.util.Map;

public class AccountMoneyBox {
   private Map<Integer, PT_MONEY_ITEM> accountcurrency = new HashMap();
   private Map<Integer, PT_MONEY_ITEM> accountdailygain = new HashMap();

   public void putCurrency(PT_MONEY_ITEM pt_money_item) {
      this.accountcurrency.put(pt_money_item.index, pt_money_item);
   }

   public void updateAccountCurrency(PT_MONEY_ITEM pt_money_item) {
      PT_MONEY_ITEM oldAccountCurrency = (PT_MONEY_ITEM)this.accountcurrency.get(pt_money_item.index);
      if (oldAccountCurrency == null) {
         this.accountcurrency.put(pt_money_item.index, pt_money_item);
      } else {
         oldAccountCurrency.count = oldAccountCurrency.count + pt_money_item.count;
      }

   }

   public void updateAccountCurrencySub(int index, int cnt) {
      PT_MONEY_ITEM oldAccountCurrency = (PT_MONEY_ITEM)this.accountcurrency.get(index);
      if (oldAccountCurrency == null) {
         PT_MONEY_ITEM pt_money_item = new PT_MONEY_ITEM();
         pt_money_item.index = index;
         pt_money_item.count = cnt;
         this.accountcurrency.put(index, pt_money_item);
      } else {
         oldAccountCurrency.count = oldAccountCurrency.count - cnt;
      }

   }

   public void updateAccountCurrencyAdd(int index, int cnt) {
      PT_MONEY_ITEM oldAccountCurrency = (PT_MONEY_ITEM)this.accountcurrency.get(index);
      if (oldAccountCurrency == null) {
         PT_MONEY_ITEM pt_money_item = new PT_MONEY_ITEM();
         pt_money_item.index = index;
         pt_money_item.count = cnt;
         this.accountcurrency.put(index, pt_money_item);
      } else {
         oldAccountCurrency.count = oldAccountCurrency.count + cnt;
      }

   }

   public void putCurrency(int index, int count) {
      PT_MONEY_ITEM pt_money_item = new PT_MONEY_ITEM();
      pt_money_item.index = index;
      pt_money_item.count = count;
      this.accountcurrency.put(index, pt_money_item);
   }

   public void putAccountCurrency(PT_MONEY_ITEM pt_money_item) {
      this.accountcurrency.put(pt_money_item.index, pt_money_item);
   }

   public PT_MONEY_ITEM getMoneyItem(int index) {
      return (PT_MONEY_ITEM)this.accountcurrency.get(index);
   }

   public PT_MONEY_ITEM getAccountMoneyItem(int index) {
      return (PT_MONEY_ITEM)this.accountcurrency.get(index);
   }

   public PT_MONEY_ITEM getTylor() {
      return (PT_MONEY_ITEM)this.accountcurrency.get(2013000001);
   }

   public int getTylorCnt() {
      PT_MONEY_ITEM pt_money_item = (PT_MONEY_ITEM)this.accountcurrency.get(2013000001);
      return pt_money_item == null ? 0 : pt_money_item.count;
   }

   public long getCeraCnt() {
      return this.accountcurrency.get(2) == null ? 0L : (long)((PT_MONEY_ITEM)this.accountcurrency.get(2)).count;
   }

   public void addCnt(int index, int cnt) {
      PT_MONEY_ITEM pt_money_item = (PT_MONEY_ITEM)this.accountcurrency.get(index);
      if (pt_money_item == null) {
         pt_money_item = new PT_MONEY_ITEM();
         pt_money_item.index = index;
         pt_money_item.count = cnt;
         this.accountcurrency.put(index, pt_money_item);
      } else {
         PT_MONEY_ITEM var4 = (PT_MONEY_ITEM)this.accountcurrency.get(index);
         var4.count = var4.count + cnt;
      }

   }

   public void subCnt(int index, int cnt) {
      if (this.accountcurrency.get(index) != null && ((PT_MONEY_ITEM)this.accountcurrency.get(index)).count >= cnt) {
         PT_MONEY_ITEM var3 = (PT_MONEY_ITEM)this.accountcurrency.get(index);
         var3.count = var3.count - cnt;
      }

   }

   public void updateTylor(PT_MONEY_ITEM pt_money_item) {
      this.accountcurrency.put(2013000001, pt_money_item);
   }

   public void addTylor(int count) {
      PT_MONEY_ITEM pt_money_item = (PT_MONEY_ITEM)this.accountcurrency.get(2013000001);
      if (pt_money_item == null) {
         pt_money_item = new PT_MONEY_ITEM();
         pt_money_item.index = 2013000001;
         pt_money_item.count = count;
         this.accountcurrency.put(2013000001, pt_money_item);
      } else {
         pt_money_item.count = pt_money_item.count + count;
      }

      this.accountcurrency.put(2013000001, pt_money_item);
   }

   public void addCera(int count) {
      PT_MONEY_ITEM pt_money_item = (PT_MONEY_ITEM)this.accountcurrency.get(2);
      if (pt_money_item == null) {
         pt_money_item = new PT_MONEY_ITEM();
         pt_money_item.index = 2;
         pt_money_item.count = count;
      } else {
         pt_money_item.count = pt_money_item.count + count;
      }

      this.accountcurrency.put(2, pt_money_item);
   }

   public Map<Integer, PT_MONEY_ITEM> getAccountcurrency() {
      return this.accountcurrency;
   }

   public Map<Integer, PT_MONEY_ITEM> getAccountdailygain() {
      return this.accountdailygain;
   }

   public void setAccountcurrency(Map<Integer, PT_MONEY_ITEM> accountcurrency) {
      this.accountcurrency = accountcurrency;
   }

   public void setAccountdailygain(Map<Integer, PT_MONEY_ITEM> accountdailygain) {
      this.accountdailygain = accountdailygain;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof AccountMoneyBox)) {
         return false;
      } else {
         AccountMoneyBox other = (AccountMoneyBox)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$accountcurrency = this.getAccountcurrency();
            Object other$accountcurrency = other.getAccountcurrency();
            if (this$accountcurrency == null) {
               if (other$accountcurrency != null) {
                  return false;
               }
            } else if (!this$accountcurrency.equals(other$accountcurrency)) {
               return false;
            }

            Object this$accountdailygain = this.getAccountdailygain();
            Object other$accountdailygain = other.getAccountdailygain();
            if (this$accountdailygain == null) {
               if (other$accountdailygain != null) {
                  return false;
               }
            } else if (!this$accountdailygain.equals(other$accountdailygain)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof AccountMoneyBox;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $accountcurrency = this.getAccountcurrency();
      result = result * 59 + ($accountcurrency == null ? 43 : $accountcurrency.hashCode());
      Object $accountdailygain = this.getAccountdailygain();
      result = result * 59 + ($accountdailygain == null ? 43 : $accountdailygain.hashCode());
      return result;
   }

   public String toString() {
      return "AccountMoneyBox(accountcurrency=" + this.getAccountcurrency() + ", accountdailygain=" + this.getAccountdailygain() + ")";
   }
}
