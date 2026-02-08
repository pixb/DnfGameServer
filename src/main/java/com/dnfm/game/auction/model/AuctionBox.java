package com.dnfm.game.auction.model;

import com.dnfm.common.thread.IdGenerator;
import com.dnfm.mina.protobuf.PT_AUCTION_EQUIP;
import com.dnfm.mina.protobuf.PT_AUCTION_STACKABLE;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuctionBox {
   private static final Logger log = LoggerFactory.getLogger(AuctionBox.class);
   private Map<Long, PT_AUCTION_EQUIP> equipBuyMap = new HashMap();
   private Map<Long, PT_AUCTION_STACKABLE> stackableBuyMap = new HashMap();
   private Map<Long, PT_AUCTION_EQUIP> equipSellMap = new HashMap();
   private Map<Long, PT_AUCTION_STACKABLE> stackableSellMap = new HashMap();
   private Map<Long, PT_AUCTION_EQUIP> equipMapReg = new HashMap();
   private Map<Long, PT_AUCTION_STACKABLE> stackableMapReg = new HashMap();

   public List<PT_AUCTION_EQUIP> getEquipRegList() {
      return new ArrayList(this.equipMapReg.values());
   }

   public List<PT_AUCTION_STACKABLE> getStackableRegList() {
      return new ArrayList(this.stackableMapReg.values());
   }

   public void addEquipReg(PT_AUCTION_EQUIP equip) {
      this.equipMapReg.put(equip.auid, equip);
   }

   public void removeEquipReg(PT_AUCTION_EQUIP equip) {
      this.equipMapReg.remove(equip.auid);
   }

   public void addStackableReg(PT_AUCTION_STACKABLE stackable) {
      this.stackableMapReg.put(stackable.auid, stackable);
   }

   public void removeStackableReg(PT_AUCTION_STACKABLE stackable) {
      this.stackableMapReg.remove(stackable.auid);
   }

   public PT_AUCTION_STACKABLE removeStackableReg(long auid) {
      PT_AUCTION_STACKABLE res = (PT_AUCTION_STACKABLE)this.stackableMapReg.get(auid);
      this.stackableMapReg.remove(auid);
      return res;
   }

   public PT_AUCTION_EQUIP removeEquipReg(long auid) {
      PT_AUCTION_EQUIP res = (PT_AUCTION_EQUIP)this.equipMapReg.get(auid);
      this.equipMapReg.remove(auid);
      return res;
   }

   public void addEquipBuy(PT_AUCTION_EQUIP equip) {
      this.equipBuyMap.put(equip.auid, equip);
   }

   public void buyEqu(long auid, int cnt) {
      PT_AUCTION_EQUIP pt_auction_equip = (PT_AUCTION_EQUIP)this.equipMapReg.get(auid);
      if (pt_auction_equip != null) {
         pt_auction_equip.count = pt_auction_equip.count - cnt;
         if (pt_auction_equip.tera == null) {
            pt_auction_equip.tera = 0;
         }

         pt_auction_equip.tera = pt_auction_equip.tera + cnt * pt_auction_equip.buyprice;
         if (pt_auction_equip.count == 0) {
            pt_auction_equip.flag = 2;
         } else {
            pt_auction_equip.flag = 1;
         }
      } else {
         log.error("UNREACH==AuctionBox.buyEqu: " + auid);
      }

   }

   public void buyStackable(long auid, int cnt) {
      PT_AUCTION_STACKABLE pt_auction_stackable = (PT_AUCTION_STACKABLE)this.stackableMapReg.get(auid);
      if (pt_auction_stackable != null) {
         pt_auction_stackable.count = pt_auction_stackable.count - cnt;
         if (pt_auction_stackable.tera == null) {
            pt_auction_stackable.tera = 0;
         }

         pt_auction_stackable.tera = pt_auction_stackable.tera + cnt * pt_auction_stackable.buyprice;
         if (pt_auction_stackable.count == 0) {
            pt_auction_stackable.flag = 2;
         } else {
            pt_auction_stackable.flag = 1;
         }
      } else {
         log.error("UNREACH==AuctionBox.buyStackable: " + auid);
      }

   }

   public void addEquipBuy(int price, long enddate, int registcount, int buyprice, int index, int count) {
      PT_AUCTION_EQUIP equip = new PT_AUCTION_EQUIP();
      equip.type = 1;
      equip.price = price;
      equip.enddate = enddate;
      equip.registcount = registcount;
      equip.buyprice = buyprice;
      equip.index = index;
      equip.count = count;
      this.equipBuyMap.put(equip.auid, equip);
   }

   public void addStackableBuy(PT_AUCTION_STACKABLE stackable) {
      this.stackableBuyMap.put(stackable.auid, stackable);
   }

   public void addStackableBuy(int price, long enddate, int registcount, int buyprice, int index, int count) {
      PT_AUCTION_STACKABLE stackable = new PT_AUCTION_STACKABLE();
      stackable.auid = IdGenerator.getNextId();
      stackable.type = 1;
      stackable.price = price;
      stackable.enddate = enddate;
      stackable.registcount = registcount;
      stackable.buyprice = buyprice;
      stackable.index = index;
      stackable.count = count;
      this.stackableBuyMap.put(stackable.auid, stackable);
   }

   public void addEquipSell(PT_AUCTION_EQUIP equip) {
      this.equipSellMap.put(equip.auid, equip);
   }

   public void addStackableSell(PT_AUCTION_STACKABLE stackable) {
      long key = stackable.auid;
      this.stackableSellMap.put(key, stackable);
   }

   public int removeReg(long auid) {
      if (this.equipMapReg.containsKey(auid)) {
         ((PT_AUCTION_EQUIP)this.equipMapReg.get(auid)).flag = -1;
         return 2;
      } else {
         ((PT_AUCTION_STACKABLE)this.stackableMapReg.get(auid)).flag = -1;
         return 1;
      }
   }

   public int completeReg(long auid) {
      if (this.equipMapReg.containsKey(auid)) {
         this.addEquipSell((PT_AUCTION_EQUIP)this.equipMapReg.get(auid));
         ((PT_AUCTION_EQUIP)this.equipMapReg.get(auid)).flag = -1;
         return 2;
      } else {
         this.addStackableSell((PT_AUCTION_STACKABLE)this.stackableMapReg.get(auid));
         ((PT_AUCTION_STACKABLE)this.stackableMapReg.get(auid)).flag = -1;
         return 1;
      }
   }

   public List<PT_AUCTION_STACKABLE> getStackableBuyList() {
      return new ArrayList(this.stackableBuyMap.values());
   }

   public List<PT_AUCTION_EQUIP> getEquipBuyList() {
      return new ArrayList(this.equipBuyMap.values());
   }

   public List<PT_AUCTION_STACKABLE> getStackableSellList() {
      return new ArrayList(this.stackableSellMap.values());
   }

   public List<PT_AUCTION_EQUIP> getEquipSellList() {
      return new ArrayList(this.equipSellMap.values());
   }

   public PT_AUCTION_STACKABLE getStackableReg(long auid) {
      return (PT_AUCTION_STACKABLE)this.stackableMapReg.get(auid);
   }

   public PT_AUCTION_EQUIP getEquipReg(long auid) {
      return (PT_AUCTION_EQUIP)this.equipMapReg.get(auid);
   }

   public Map<Long, PT_AUCTION_EQUIP> getEquipBuyMap() {
      return this.equipBuyMap;
   }

   public Map<Long, PT_AUCTION_STACKABLE> getStackableBuyMap() {
      return this.stackableBuyMap;
   }

   public Map<Long, PT_AUCTION_EQUIP> getEquipSellMap() {
      return this.equipSellMap;
   }

   public Map<Long, PT_AUCTION_STACKABLE> getStackableSellMap() {
      return this.stackableSellMap;
   }

   public Map<Long, PT_AUCTION_EQUIP> getEquipMapReg() {
      return this.equipMapReg;
   }

   public Map<Long, PT_AUCTION_STACKABLE> getStackableMapReg() {
      return this.stackableMapReg;
   }

   public void setEquipBuyMap(Map<Long, PT_AUCTION_EQUIP> equipBuyMap) {
      this.equipBuyMap = equipBuyMap;
   }

   public void setStackableBuyMap(Map<Long, PT_AUCTION_STACKABLE> stackableBuyMap) {
      this.stackableBuyMap = stackableBuyMap;
   }

   public void setEquipSellMap(Map<Long, PT_AUCTION_EQUIP> equipSellMap) {
      this.equipSellMap = equipSellMap;
   }

   public void setStackableSellMap(Map<Long, PT_AUCTION_STACKABLE> stackableSellMap) {
      this.stackableSellMap = stackableSellMap;
   }

   public void setEquipMapReg(Map<Long, PT_AUCTION_EQUIP> equipMapReg) {
      this.equipMapReg = equipMapReg;
   }

   public void setStackableMapReg(Map<Long, PT_AUCTION_STACKABLE> stackableMapReg) {
      this.stackableMapReg = stackableMapReg;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof AuctionBox)) {
         return false;
      } else {
         AuctionBox other = (AuctionBox)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$equipBuyMap = this.getEquipBuyMap();
            Object other$equipBuyMap = other.getEquipBuyMap();
            if (this$equipBuyMap == null) {
               if (other$equipBuyMap != null) {
                  return false;
               }
            } else if (!this$equipBuyMap.equals(other$equipBuyMap)) {
               return false;
            }

            Object this$stackableBuyMap = this.getStackableBuyMap();
            Object other$stackableBuyMap = other.getStackableBuyMap();
            if (this$stackableBuyMap == null) {
               if (other$stackableBuyMap != null) {
                  return false;
               }
            } else if (!this$stackableBuyMap.equals(other$stackableBuyMap)) {
               return false;
            }

            Object this$equipSellMap = this.getEquipSellMap();
            Object other$equipSellMap = other.getEquipSellMap();
            if (this$equipSellMap == null) {
               if (other$equipSellMap != null) {
                  return false;
               }
            } else if (!this$equipSellMap.equals(other$equipSellMap)) {
               return false;
            }

            Object this$stackableSellMap = this.getStackableSellMap();
            Object other$stackableSellMap = other.getStackableSellMap();
            if (this$stackableSellMap == null) {
               if (other$stackableSellMap != null) {
                  return false;
               }
            } else if (!this$stackableSellMap.equals(other$stackableSellMap)) {
               return false;
            }

            Object this$equipMapReg = this.getEquipMapReg();
            Object other$equipMapReg = other.getEquipMapReg();
            if (this$equipMapReg == null) {
               if (other$equipMapReg != null) {
                  return false;
               }
            } else if (!this$equipMapReg.equals(other$equipMapReg)) {
               return false;
            }

            Object this$stackableMapReg = this.getStackableMapReg();
            Object other$stackableMapReg = other.getStackableMapReg();
            if (this$stackableMapReg == null) {
               if (other$stackableMapReg != null) {
                  return false;
               }
            } else if (!this$stackableMapReg.equals(other$stackableMapReg)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof AuctionBox;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $equipBuyMap = this.getEquipBuyMap();
      result = result * 59 + ($equipBuyMap == null ? 43 : $equipBuyMap.hashCode());
      Object $stackableBuyMap = this.getStackableBuyMap();
      result = result * 59 + ($stackableBuyMap == null ? 43 : $stackableBuyMap.hashCode());
      Object $equipSellMap = this.getEquipSellMap();
      result = result * 59 + ($equipSellMap == null ? 43 : $equipSellMap.hashCode());
      Object $stackableSellMap = this.getStackableSellMap();
      result = result * 59 + ($stackableSellMap == null ? 43 : $stackableSellMap.hashCode());
      Object $equipMapReg = this.getEquipMapReg();
      result = result * 59 + ($equipMapReg == null ? 43 : $equipMapReg.hashCode());
      Object $stackableMapReg = this.getStackableMapReg();
      result = result * 59 + ($stackableMapReg == null ? 43 : $stackableMapReg.hashCode());
      return result;
   }

   public String toString() {
      return "AuctionBox(equipBuyMap=" + this.getEquipBuyMap() + ", stackableBuyMap=" + this.getStackableBuyMap() + ", equipSellMap=" + this.getEquipSellMap() + ", stackableSellMap=" + this.getStackableSellMap() + ", equipMapReg=" + this.getEquipMapReg() + ", stackableMapReg=" + this.getStackableMapReg() + ")";
   }
}
