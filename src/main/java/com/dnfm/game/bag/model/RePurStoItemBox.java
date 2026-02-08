package com.dnfm.game.bag.model;

import com.dnfm.common.thread.IdGenerator;
import com.dnfm.mina.protobuf.PT_EQUIP;
import com.dnfm.mina.protobuf.PT_REPURCHASE_EQUIP;
import com.dnfm.mina.protobuf.PT_REPURCHASE_STACKABLE;
import com.dnfm.mina.protobuf.PT_STACKABLE;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RePurStoItemBox {
   public static final int DEFAULT_CACHE_LIMIT = 28;
   public Map<Long, PT_REPURCHASE_EQUIP> equipitemsMap = new LinkedHashMap<Long, PT_REPURCHASE_EQUIP>(28, 0.75F, true) {
      protected boolean removeEldestEntry(Map.Entry<Long, PT_REPURCHASE_EQUIP> eldest) {
         if (this.size() > 28) {
            RePurStoItemBox.this.equipitemsMap.remove(eldest.getKey());
            return true;
         } else {
            return false;
         }
      }
   };
   public Map<Long, PT_REPURCHASE_EQUIP> titleitemsMap = new LinkedHashMap<Long, PT_REPURCHASE_EQUIP>(28, 0.75F, true) {
      protected boolean removeEldestEntry(Map.Entry<Long, PT_REPURCHASE_EQUIP> eldest) {
         if (this.size() > 28) {
            RePurStoItemBox.this.titleitemsMap.remove(eldest.getKey());
            return true;
         } else {
            return false;
         }
      }
   };
   public Map<Long, PT_REPURCHASE_STACKABLE> materialitemsMap = new LinkedHashMap<Long, PT_REPURCHASE_STACKABLE>(28, 0.75F, true) {
      protected boolean removeEldestEntry(Map.Entry<Long, PT_REPURCHASE_STACKABLE> eldest) {
         if (this.size() > 28) {
            RePurStoItemBox.this.materialitemsMap.remove(eldest.getKey());
            return true;
         } else {
            return false;
         }
      }
   };
   public Map<Long, PT_REPURCHASE_STACKABLE> consumeitemsMap = new LinkedHashMap<Long, PT_REPURCHASE_STACKABLE>(28, 0.75F, true) {
      protected boolean removeEldestEntry(Map.Entry<Long, PT_REPURCHASE_STACKABLE> eldest) {
         if (this.size() > 28) {
            RePurStoItemBox.this.consumeitemsMap.remove(eldest.getKey());
            return true;
         } else {
            return false;
         }
      }
   };
   public Map<Long, PT_REPURCHASE_STACKABLE> emblemitemsMap = new LinkedHashMap<Long, PT_REPURCHASE_STACKABLE>(28, 0.75F, true) {
      protected boolean removeEldestEntry(Map.Entry<Long, PT_REPURCHASE_STACKABLE> eldest) {
         if (this.size() > 28) {
            RePurStoItemBox.this.emblemitemsMap.remove(eldest.getKey());
            return true;
         } else {
            return false;
         }
      }
   };
   public Map<Long, PT_REPURCHASE_STACKABLE> carditemsMap = new LinkedHashMap<Long, PT_REPURCHASE_STACKABLE>(28, 0.75F, true) {
      protected boolean removeEldestEntry(Map.Entry<Long, PT_REPURCHASE_STACKABLE> eldest) {
         if (this.size() > 28) {
            RePurStoItemBox.this.carditemsMap.remove(eldest.getKey());
            return true;
         } else {
            return false;
         }
      }
   };

   public void addEquRepur(PT_EQUIP pt_equip) {
      PT_REPURCHASE_EQUIP pt_repurchase_equip = new PT_REPURCHASE_EQUIP();
      pt_repurchase_equip.guid = IdGenerator.getNextId();
      pt_repurchase_equip.item = pt_equip;
      this.equipitemsMap.put(pt_repurchase_equip.guid, pt_repurchase_equip);
   }

   public List<PT_REPURCHASE_EQUIP> getEquipitems() {
      return new ArrayList(this.equipitemsMap.values());
   }

   public void addTitleRepur(PT_EQUIP pt_equip) {
      PT_REPURCHASE_EQUIP pt_repurchase_equip = new PT_REPURCHASE_EQUIP();
      pt_repurchase_equip.guid = IdGenerator.getNextId();
      pt_repurchase_equip.item = pt_equip;
      this.titleitemsMap.put(pt_repurchase_equip.guid, pt_repurchase_equip);
   }

   public List<PT_REPURCHASE_EQUIP> getTitleitems() {
      return new ArrayList(this.titleitemsMap.values());
   }

   public void addMaterialRepur(int index, int count) {
      PT_REPURCHASE_STACKABLE pt_repurchase_stackable = new PT_REPURCHASE_STACKABLE();
      pt_repurchase_stackable.guid = IdGenerator.getNextId();
      pt_repurchase_stackable.item = new PT_STACKABLE();
      pt_repurchase_stackable.item.index = index;
      pt_repurchase_stackable.item.count = count;
      this.materialitemsMap.put(pt_repurchase_stackable.guid, pt_repurchase_stackable);
   }

   public List<PT_REPURCHASE_STACKABLE> getMaterialitems() {
      return new ArrayList(this.materialitemsMap.values());
   }

   public void addConsumeRepur(int index, int count) {
      PT_REPURCHASE_STACKABLE pt_repurchase_stackable = new PT_REPURCHASE_STACKABLE();
      pt_repurchase_stackable.guid = IdGenerator.getNextId();
      pt_repurchase_stackable.item = new PT_STACKABLE();
      pt_repurchase_stackable.item.index = index;
      pt_repurchase_stackable.item.count = count;
      this.consumeitemsMap.put(pt_repurchase_stackable.guid, pt_repurchase_stackable);
   }

   public List<PT_REPURCHASE_STACKABLE> getConsumeitems() {
      return new ArrayList(this.consumeitemsMap.values());
   }

   public void addEmblemRepur(int index, int count) {
      PT_REPURCHASE_STACKABLE pt_repurchase_stackable = new PT_REPURCHASE_STACKABLE();
      pt_repurchase_stackable.guid = IdGenerator.getNextId();
      pt_repurchase_stackable.item = new PT_STACKABLE();
      pt_repurchase_stackable.item.index = index;
      pt_repurchase_stackable.item.count = count;
      this.emblemitemsMap.put(pt_repurchase_stackable.guid, pt_repurchase_stackable);
   }

   public List<PT_REPURCHASE_STACKABLE> getEmblemitems() {
      return new ArrayList(this.emblemitemsMap.values());
   }

   public void addCardRepur(int index, int count) {
      PT_REPURCHASE_STACKABLE pt_repurchase_stackable = new PT_REPURCHASE_STACKABLE();
      pt_repurchase_stackable.guid = IdGenerator.getNextId();
      pt_repurchase_stackable.item = new PT_STACKABLE();
      pt_repurchase_stackable.item.index = index;
      pt_repurchase_stackable.item.count = count;
      this.carditemsMap.put(pt_repurchase_stackable.guid, pt_repurchase_stackable);
   }

   public List<PT_REPURCHASE_STACKABLE> getCarditems() {
      return new ArrayList(this.carditemsMap.values());
   }

   public PT_REPURCHASE_EQUIP getEquItem(long guid) {
      return (PT_REPURCHASE_EQUIP)this.equipitemsMap.get(guid);
   }

   public PT_REPURCHASE_EQUIP getTitleItem(long guid) {
      return (PT_REPURCHASE_EQUIP)this.titleitemsMap.get(guid);
   }

   public PT_REPURCHASE_STACKABLE getMaterialItem(long guid) {
      return (PT_REPURCHASE_STACKABLE)this.materialitemsMap.get(guid);
   }

   public PT_REPURCHASE_STACKABLE getConsumeItem(long guid) {
      return (PT_REPURCHASE_STACKABLE)this.consumeitemsMap.get(guid);
   }

   public PT_REPURCHASE_STACKABLE getEmblemItem(long guid) {
      return (PT_REPURCHASE_STACKABLE)this.emblemitemsMap.get(guid);
   }

   public PT_REPURCHASE_STACKABLE getCardItem(long guid) {
      return (PT_REPURCHASE_STACKABLE)this.carditemsMap.get(guid);
   }

   public void removeEqu(long guid) {
      this.equipitemsMap.remove(guid);
   }

   public void removeTitle(long guid) {
      this.titleitemsMap.remove(guid);
   }

   public void removeMaterial(long guid) {
      this.materialitemsMap.remove(guid);
   }

   public void removeConsume(long guid) {
      this.consumeitemsMap.remove(guid);
   }

   public void removeEmblem(long guid) {
      this.emblemitemsMap.remove(guid);
   }

   public void removeCard(long guid) {
      this.carditemsMap.remove(guid);
   }

   public Map<Long, PT_REPURCHASE_EQUIP> getEquipitemsMap() {
      return this.equipitemsMap;
   }

   public Map<Long, PT_REPURCHASE_EQUIP> getTitleitemsMap() {
      return this.titleitemsMap;
   }

   public Map<Long, PT_REPURCHASE_STACKABLE> getMaterialitemsMap() {
      return this.materialitemsMap;
   }

   public Map<Long, PT_REPURCHASE_STACKABLE> getConsumeitemsMap() {
      return this.consumeitemsMap;
   }

   public Map<Long, PT_REPURCHASE_STACKABLE> getEmblemitemsMap() {
      return this.emblemitemsMap;
   }

   public Map<Long, PT_REPURCHASE_STACKABLE> getCarditemsMap() {
      return this.carditemsMap;
   }

   public void setEquipitemsMap(Map<Long, PT_REPURCHASE_EQUIP> equipitemsMap) {
      this.equipitemsMap = equipitemsMap;
   }

   public void setTitleitemsMap(Map<Long, PT_REPURCHASE_EQUIP> titleitemsMap) {
      this.titleitemsMap = titleitemsMap;
   }

   public void setMaterialitemsMap(Map<Long, PT_REPURCHASE_STACKABLE> materialitemsMap) {
      this.materialitemsMap = materialitemsMap;
   }

   public void setConsumeitemsMap(Map<Long, PT_REPURCHASE_STACKABLE> consumeitemsMap) {
      this.consumeitemsMap = consumeitemsMap;
   }

   public void setEmblemitemsMap(Map<Long, PT_REPURCHASE_STACKABLE> emblemitemsMap) {
      this.emblemitemsMap = emblemitemsMap;
   }

   public void setCarditemsMap(Map<Long, PT_REPURCHASE_STACKABLE> carditemsMap) {
      this.carditemsMap = carditemsMap;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof RePurStoItemBox)) {
         return false;
      } else {
         RePurStoItemBox other = (RePurStoItemBox)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$equipitemsMap = this.getEquipitemsMap();
            Object other$equipitemsMap = other.getEquipitemsMap();
            if (this$equipitemsMap == null) {
               if (other$equipitemsMap != null) {
                  return false;
               }
            } else if (!this$equipitemsMap.equals(other$equipitemsMap)) {
               return false;
            }

            Object this$titleitemsMap = this.getTitleitemsMap();
            Object other$titleitemsMap = other.getTitleitemsMap();
            if (this$titleitemsMap == null) {
               if (other$titleitemsMap != null) {
                  return false;
               }
            } else if (!this$titleitemsMap.equals(other$titleitemsMap)) {
               return false;
            }

            Object this$materialitemsMap = this.getMaterialitemsMap();
            Object other$materialitemsMap = other.getMaterialitemsMap();
            if (this$materialitemsMap == null) {
               if (other$materialitemsMap != null) {
                  return false;
               }
            } else if (!this$materialitemsMap.equals(other$materialitemsMap)) {
               return false;
            }

            Object this$consumeitemsMap = this.getConsumeitemsMap();
            Object other$consumeitemsMap = other.getConsumeitemsMap();
            if (this$consumeitemsMap == null) {
               if (other$consumeitemsMap != null) {
                  return false;
               }
            } else if (!this$consumeitemsMap.equals(other$consumeitemsMap)) {
               return false;
            }

            Object this$emblemitemsMap = this.getEmblemitemsMap();
            Object other$emblemitemsMap = other.getEmblemitemsMap();
            if (this$emblemitemsMap == null) {
               if (other$emblemitemsMap != null) {
                  return false;
               }
            } else if (!this$emblemitemsMap.equals(other$emblemitemsMap)) {
               return false;
            }

            Object this$carditemsMap = this.getCarditemsMap();
            Object other$carditemsMap = other.getCarditemsMap();
            if (this$carditemsMap == null) {
               if (other$carditemsMap != null) {
                  return false;
               }
            } else if (!this$carditemsMap.equals(other$carditemsMap)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof RePurStoItemBox;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $equipitemsMap = this.getEquipitemsMap();
      result = result * 59 + ($equipitemsMap == null ? 43 : $equipitemsMap.hashCode());
      Object $titleitemsMap = this.getTitleitemsMap();
      result = result * 59 + ($titleitemsMap == null ? 43 : $titleitemsMap.hashCode());
      Object $materialitemsMap = this.getMaterialitemsMap();
      result = result * 59 + ($materialitemsMap == null ? 43 : $materialitemsMap.hashCode());
      Object $consumeitemsMap = this.getConsumeitemsMap();
      result = result * 59 + ($consumeitemsMap == null ? 43 : $consumeitemsMap.hashCode());
      Object $emblemitemsMap = this.getEmblemitemsMap();
      result = result * 59 + ($emblemitemsMap == null ? 43 : $emblemitemsMap.hashCode());
      Object $carditemsMap = this.getCarditemsMap();
      result = result * 59 + ($carditemsMap == null ? 43 : $carditemsMap.hashCode());
      return result;
   }

   public String toString() {
      return "RePurStoItemBox(equipitemsMap=" + this.getEquipitemsMap() + ", titleitemsMap=" + this.getTitleitemsMap() + ", materialitemsMap=" + this.getMaterialitemsMap() + ", consumeitemsMap=" + this.getConsumeitemsMap() + ", emblemitemsMap=" + this.getEmblemitemsMap() + ", carditemsMap=" + this.getCarditemsMap() + ")";
   }
}
