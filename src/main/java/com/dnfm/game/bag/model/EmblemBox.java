package com.dnfm.game.bag.model;

import com.dnfm.mina.protobuf.PT_STACKABLE;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmblemBox {
   private int maxcount = 40;
   private Map<Integer, PT_STACKABLE> emblemMap = new HashMap();

   public PT_STACKABLE getEmblem(int index) {
      return (PT_STACKABLE)this.emblemMap.get(index);
   }

   public void updateEmblem(PT_STACKABLE emblem) {
      if (emblem.count == 0) {
         this.emblemMap.remove(emblem.index);
      } else {
         PT_STACKABLE pt_stackable = (PT_STACKABLE)this.emblemMap.get(emblem.index);
         if (pt_stackable != null) {
            pt_stackable.count = pt_stackable.count + emblem.count;
            this.emblemMap.put(emblem.index, pt_stackable);
         } else {
            this.emblemMap.put(emblem.index, emblem);
         }
      }

   }

   public void updateEmblemSub(int index, int count) {
      PT_STACKABLE emblem = (PT_STACKABLE)this.emblemMap.get(index);
      if (emblem == null) {
         emblem = new PT_STACKABLE();
         emblem.index = index;
         emblem.count = count;
         this.emblemMap.put(index, emblem);
      } else {
         emblem.count = emblem.count - count;
      }

      if (emblem.count <= 0) {
         this.emblemMap.remove(index);
      }

   }

   public void updateEmblemAdd(int index, int count) {
      PT_STACKABLE emblem = (PT_STACKABLE)this.emblemMap.get(index);
      if (emblem == null) {
         emblem = new PT_STACKABLE();
         emblem.index = index;
         emblem.count = count;
         this.emblemMap.put(index, emblem);
      } else {
         emblem.count = emblem.count + count;
      }

   }

   public void putEmblem(PT_STACKABLE emblem) {
      if (emblem.count == 0) {
         this.emblemMap.remove(emblem.index);
      } else {
         this.emblemMap.put(emblem.index, emblem);
      }

   }

   public void addEmblem(PT_STACKABLE emblem) {
      int key = emblem.index;
      if (emblem.count == 0) {
         this.emblemMap.remove(emblem.index);
      } else {
         this.emblemMap.put(key, emblem);
      }

   }

   public List<PT_STACKABLE> getEmblems() {
      return new ArrayList(this.emblemMap.values());
   }

   public int getMaxcount() {
      return this.maxcount;
   }

   public Map<Integer, PT_STACKABLE> getEmblemMap() {
      return this.emblemMap;
   }

   public void setMaxcount(int maxcount) {
      this.maxcount = maxcount;
   }

   public void setEmblemMap(Map<Integer, PT_STACKABLE> emblemMap) {
      this.emblemMap = emblemMap;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EmblemBox)) {
         return false;
      } else {
         EmblemBox other = (EmblemBox)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getMaxcount() != other.getMaxcount()) {
            return false;
         } else {
            Object this$emblemMap = this.getEmblemMap();
            Object other$emblemMap = other.getEmblemMap();
            if (this$emblemMap == null) {
               if (other$emblemMap != null) {
                  return false;
               }
            } else if (!this$emblemMap.equals(other$emblemMap)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EmblemBox;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getMaxcount();
      Object $emblemMap = this.getEmblemMap();
      result = result * 59 + ($emblemMap == null ? 43 : $emblemMap.hashCode());
      return result;
   }

   public String toString() {
      return "EmblemBox(maxcount=" + this.getMaxcount() + ", emblemMap=" + this.getEmblemMap() + ")";
   }
}
