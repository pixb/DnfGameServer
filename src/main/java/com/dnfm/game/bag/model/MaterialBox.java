package com.dnfm.game.bag.model;

import com.dnfm.mina.protobuf.PT_STACKABLE;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MaterialBox {
   private static final Logger log = LoggerFactory.getLogger(MaterialBox.class);
   private int maxcount = 40;
   private Map<Integer, PT_STACKABLE> materialsMap = new HashMap();

   public PT_STACKABLE getMaterial(int index) {
      return (PT_STACKABLE)this.materialsMap.get(index);
   }

   public void updateMaterial(PT_STACKABLE material) {
      PT_STACKABLE oldMaterial = (PT_STACKABLE)this.materialsMap.get(material.index);
      if (oldMaterial == null) {
         int key = material.index;
         this.materialsMap.put(key, material);
      } else {
         oldMaterial.count = oldMaterial.count + material.count;
      }

   }

   public int updateMaterialSub(int index, int count) {
      PT_STACKABLE material = (PT_STACKABLE)this.materialsMap.get(index);
      if (material == null) {
         log.error("UNREACH==updateMaterialSub==不应该执行到这");
         return -1;
      } else {
         int delta = material.count - count;
         if (delta == 0) {
            this.materialsMap.remove(index);
            return count;
         } else if (delta > 0) {
            material.count = delta;
            return count;
         } else {
            int res = material.count;
            this.materialsMap.remove(index);
            return res;
         }
      }
   }

   public void updateMaterialAdd(int index, int count) {
      PT_STACKABLE material = (PT_STACKABLE)this.materialsMap.get(index);
      if (material == null) {
         material = new PT_STACKABLE();
         material.index = index;
         material.count = count;
         this.materialsMap.put(index, material);
      } else {
         material.count = material.count + count;
      }

   }

   public void removeMaterial(int index) {
      this.materialsMap.remove(index);
   }

   public void addMaterial(PT_STACKABLE material) {
      int key = material.index;
      if (material.count == 0) {
         if (this.materialsMap.get(key) != null) {
            this.materialsMap.remove(key);
         }
      } else {
         this.materialsMap.put(key, material);
      }

   }

   public List<PT_STACKABLE> getMaterials() {
      return new ArrayList(this.materialsMap.values());
   }

   public void updateCnt(int index, int cnt) {
      ((PT_STACKABLE)this.materialsMap.get(index)).count = cnt;
   }

   public void addCnt(int index, int count, long acqTime) {
      PT_STACKABLE pt_stackable = (PT_STACKABLE)this.materialsMap.get(index);
      if (pt_stackable == null) {
         pt_stackable = new PT_STACKABLE();
         pt_stackable.count = 0;
         pt_stackable.index = index;
      }

      pt_stackable.count = pt_stackable.count + count;
      int key = pt_stackable.index;
      this.materialsMap.put(key, pt_stackable);
   }

   public void subCnt(int index, int count) {
      PT_STACKABLE pt_stackable = (PT_STACKABLE)this.materialsMap.get(index);
      pt_stackable.count = pt_stackable.count - count;
      this.materialsMap.put(pt_stackable.index, pt_stackable);
   }

   public int getCnt(int index) {
      return this.materialsMap.containsKey(index) ? ((PT_STACKABLE)this.materialsMap.get(index)).count : -1;
   }

   public List<PT_STACKABLE> getMaterial() {
      return new ArrayList(this.materialsMap.values());
   }

   public int getMaxcount() {
      return this.maxcount;
   }

   public Map<Integer, PT_STACKABLE> getMaterialsMap() {
      return this.materialsMap;
   }

   public void setMaxcount(int maxcount) {
      this.maxcount = maxcount;
   }

   public void setMaterialsMap(Map<Integer, PT_STACKABLE> materialsMap) {
      this.materialsMap = materialsMap;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof MaterialBox)) {
         return false;
      } else {
         MaterialBox other = (MaterialBox)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getMaxcount() != other.getMaxcount()) {
            return false;
         } else {
            Object this$materialsMap = this.getMaterialsMap();
            Object other$materialsMap = other.getMaterialsMap();
            if (this$materialsMap == null) {
               if (other$materialsMap != null) {
                  return false;
               }
            } else if (!this$materialsMap.equals(other$materialsMap)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof MaterialBox;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getMaxcount();
      Object $materialsMap = this.getMaterialsMap();
      result = result * 59 + ($materialsMap == null ? 43 : $materialsMap.hashCode());
      return result;
   }

   public String toString() {
      return "MaterialBox(maxcount=" + this.getMaxcount() + ", materialsMap=" + this.getMaterialsMap() + ")";
   }
}
