package com.dnfm.game.bag.model;

import com.dnfm.mina.protobuf.PT_SHOP_BUY_COUNT;
import com.dnfm.mina.protobuf.PT_SHOP_GROUP_RESET;
import com.dnfm.mina.protobuf.PT_SHOP_TAB_RESET;
import java.util.ArrayList;
import java.util.List;

public class AccShopInfoBox {
   public List<PT_SHOP_TAB_RESET> reset;
   public List<PT_SHOP_GROUP_RESET> group;
   private List<PT_SHOP_BUY_COUNT> buy;

   public void addBuy(PT_SHOP_BUY_COUNT ptShopBuyCount) {
      if (this.buy == null) {
         this.buy = new ArrayList();
      }

      this.buy.add(ptShopBuyCount);
   }

   public List<PT_SHOP_TAB_RESET> getReset() {
      return this.reset;
   }

   public List<PT_SHOP_GROUP_RESET> getGroup() {
      return this.group;
   }

   public List<PT_SHOP_BUY_COUNT> getBuy() {
      return this.buy;
   }

   public void setReset(List<PT_SHOP_TAB_RESET> reset) {
      this.reset = reset;
   }

   public void setGroup(List<PT_SHOP_GROUP_RESET> group) {
      this.group = group;
   }

   public void setBuy(List<PT_SHOP_BUY_COUNT> buy) {
      this.buy = buy;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof AccShopInfoBox)) {
         return false;
      } else {
         AccShopInfoBox other = (AccShopInfoBox)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$reset = this.getReset();
            Object other$reset = other.getReset();
            if (this$reset == null) {
               if (other$reset != null) {
                  return false;
               }
            } else if (!this$reset.equals(other$reset)) {
               return false;
            }

            Object this$group = this.getGroup();
            Object other$group = other.getGroup();
            if (this$group == null) {
               if (other$group != null) {
                  return false;
               }
            } else if (!this$group.equals(other$group)) {
               return false;
            }

            Object this$buy = this.getBuy();
            Object other$buy = other.getBuy();
            if (this$buy == null) {
               if (other$buy != null) {
                  return false;
               }
            } else if (!this$buy.equals(other$buy)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof AccShopInfoBox;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $reset = this.getReset();
      result = result * 59 + ($reset == null ? 43 : $reset.hashCode());
      Object $group = this.getGroup();
      result = result * 59 + ($group == null ? 43 : $group.hashCode());
      Object $buy = this.getBuy();
      result = result * 59 + ($buy == null ? 43 : $buy.hashCode());
      return result;
   }

   public String toString() {
      return "AccShopInfoBox(reset=" + this.getReset() + ", group=" + this.getGroup() + ", buy=" + this.getBuy() + ")";
   }
}
