package com.dnfm.game.bag.model;

import com.dnfm.mina.protobuf.PT_SHOP_BUY_COUNT;
import com.dnfm.mina.protobuf.PT_SHOP_GROUP_RESET;
import com.dnfm.mina.protobuf.PT_SHOP_TAB_RESET;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoleShopInfoBox {
   public List<PT_SHOP_TAB_RESET> reset = new ArrayList();
   public Map<Integer, PT_SHOP_GROUP_RESET> groupMap = new HashMap();
   private List<PT_SHOP_BUY_COUNT> buy = new ArrayList();

   public void addBuy(PT_SHOP_BUY_COUNT ptShopBuyCount) {
      PT_SHOP_BUY_COUNT ptShopBuyCount1 = null;

      for(PT_SHOP_BUY_COUNT ee : this.buy) {
         if (ee.goodsid == ptShopBuyCount.goodsid) {
            ptShopBuyCount1 = ee;
         }
      }

      if (ptShopBuyCount1 != null) {
         ptShopBuyCount1.count = ptShopBuyCount1.count + ptShopBuyCount.count;
      } else {
         this.buy.add(ptShopBuyCount);
      }

   }

   public void addGroup(int groupid, int count) {
      PT_SHOP_GROUP_RESET pt_shop_group_reset = (PT_SHOP_GROUP_RESET)this.groupMap.get(groupid);
      if (pt_shop_group_reset == null) {
         pt_shop_group_reset = new PT_SHOP_GROUP_RESET();
         pt_shop_group_reset.groupid = groupid;
         pt_shop_group_reset.count = count;
      } else {
         pt_shop_group_reset.count = pt_shop_group_reset.count + count;
      }

   }

   public List<PT_SHOP_TAB_RESET> getReset() {
      return this.reset;
   }

   public Map<Integer, PT_SHOP_GROUP_RESET> getGroupMap() {
      return this.groupMap;
   }

   public List<PT_SHOP_BUY_COUNT> getBuy() {
      return this.buy;
   }

   public void setReset(List<PT_SHOP_TAB_RESET> reset) {
      this.reset = reset;
   }

   public void setGroupMap(Map<Integer, PT_SHOP_GROUP_RESET> groupMap) {
      this.groupMap = groupMap;
   }

   public void setBuy(List<PT_SHOP_BUY_COUNT> buy) {
      this.buy = buy;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof RoleShopInfoBox)) {
         return false;
      } else {
         RoleShopInfoBox other = (RoleShopInfoBox)o;
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

            Object this$groupMap = this.getGroupMap();
            Object other$groupMap = other.getGroupMap();
            if (this$groupMap == null) {
               if (other$groupMap != null) {
                  return false;
               }
            } else if (!this$groupMap.equals(other$groupMap)) {
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
      return other instanceof RoleShopInfoBox;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $reset = this.getReset();
      result = result * 59 + ($reset == null ? 43 : $reset.hashCode());
      Object $groupMap = this.getGroupMap();
      result = result * 59 + ($groupMap == null ? 43 : $groupMap.hashCode());
      Object $buy = this.getBuy();
      result = result * 59 + ($buy == null ? 43 : $buy.hashCode());
      return result;
   }

   public String toString() {
      return "RoleShopInfoBox(reset=" + this.getReset() + ", groupMap=" + this.getGroupMap() + ", buy=" + this.getBuy() + ")";
   }
}
