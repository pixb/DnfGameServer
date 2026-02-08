package com.dnfm.game.bag.model;

import com.dnfm.mina.protobuf.DEF_ITEM_CONSUMABLE;
import com.dnfm.mina.protobuf.PT_STACKABLE;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumableBox {
   private static final Logger log = LoggerFactory.getLogger(ConsumableBox.class);
   private int maxcount = 40;
   private Map<Integer, DEF_ITEM_CONSUMABLE> consumableMap = new HashMap();

   public void addConsumable(DEF_ITEM_CONSUMABLE consumable) {
      if (consumable.count == 0) {
         this.consumableMap.remove(consumable.index);
      } else {
         this.consumableMap.put(consumable.index, consumable);
      }

   }

   public void removeConsumable(int index) {
      this.consumableMap.remove(index);
   }

   public void addConsumable(PT_STACKABLE consumable) {
      if (consumable.count == 0) {
         this.consumableMap.remove(consumable.index);
      } else {
         DEF_ITEM_CONSUMABLE def_item_consumable = new DEF_ITEM_CONSUMABLE();
         def_item_consumable.index = consumable.index;
         def_item_consumable.count = consumable.count;
         this.consumableMap.put(consumable.index, def_item_consumable);
      }

   }

   public void updateConsume(PT_STACKABLE consume) {
      DEF_ITEM_CONSUMABLE oldConsume = (DEF_ITEM_CONSUMABLE)this.consumableMap.get(consume.index);
      if (oldConsume == null) {
         DEF_ITEM_CONSUMABLE consume2 = new DEF_ITEM_CONSUMABLE();
         consume2.index = consume.index;
         consume2.count = consume.count;
         this.consumableMap.put(consume.index, consume2);
      } else {
         oldConsume.count = oldConsume.count + consume.count;
      }

   }

   public void updateConsumeSub(int index, int count) {
      DEF_ITEM_CONSUMABLE consume = (DEF_ITEM_CONSUMABLE)this.consumableMap.get(index);
      if (consume == null) {
         log.error("UNREACH==updateConsumeSub==不应该执行到这");
      } else {
         consume.count = consume.count - count;
      }

      if (consume.count <= 0) {
         this.consumableMap.remove(index);
      }

   }

   public void updateConsumeAdd(int index, int count) {
      DEF_ITEM_CONSUMABLE consume = (DEF_ITEM_CONSUMABLE)this.consumableMap.get(index);
      if (consume == null) {
         consume = new DEF_ITEM_CONSUMABLE();
         consume.index = index;
         consume.count = count;
         this.consumableMap.put(index, consume);
      } else {
         consume.count = consume.count + count;
      }

   }

   public List<DEF_ITEM_CONSUMABLE> getConsumeList() {
      return new ArrayList(this.consumableMap.values());
   }

   public DEF_ITEM_CONSUMABLE getConsumable(int index) {
      return (DEF_ITEM_CONSUMABLE)this.consumableMap.get(index);
   }

   public int getMaxcount() {
      return this.maxcount;
   }

   public Map<Integer, DEF_ITEM_CONSUMABLE> getConsumableMap() {
      return this.consumableMap;
   }

   public void setMaxcount(int maxcount) {
      this.maxcount = maxcount;
   }

   public void setConsumableMap(Map<Integer, DEF_ITEM_CONSUMABLE> consumableMap) {
      this.consumableMap = consumableMap;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ConsumableBox)) {
         return false;
      } else {
         ConsumableBox other = (ConsumableBox)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getMaxcount() != other.getMaxcount()) {
            return false;
         } else {
            Object this$consumableMap = this.getConsumableMap();
            Object other$consumableMap = other.getConsumableMap();
            if (this$consumableMap == null) {
               if (other$consumableMap != null) {
                  return false;
               }
            } else if (!this$consumableMap.equals(other$consumableMap)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ConsumableBox;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getMaxcount();
      Object $consumableMap = this.getConsumableMap();
      result = result * 59 + ($consumableMap == null ? 43 : $consumableMap.hashCode());
      return result;
   }

   public String toString() {
      return "ConsumableBox(maxcount=" + this.getMaxcount() + ", consumableMap=" + this.getConsumableMap() + ")";
   }
}
