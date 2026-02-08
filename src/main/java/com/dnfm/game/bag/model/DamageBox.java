package com.dnfm.game.bag.model;

import com.dnfm.common.thread.IdGenerator;
import com.dnfm.mina.protobuf.PT_SKIN_ITEM;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DamageBox {
   private int maxcount = -1;
   private Map<Long, PT_SKIN_ITEM> fontMap = new HashMap();

   public void addDamageFont(PT_SKIN_ITEM font) {
      this.fontMap.put(font.guid, font);
   }

   public List<PT_SKIN_ITEM> getFonts() {
      return new ArrayList(this.fontMap.values());
   }

   public void remove(long guid) {
      this.fontMap.remove(guid);
   }

   public void addDamageFont(int index) {
      PT_SKIN_ITEM pt_skin_item = new PT_SKIN_ITEM();
      pt_skin_item.index = index;
      pt_skin_item.guid = IdGenerator.getNextId();
      this.fontMap.put(pt_skin_item.guid, pt_skin_item);
   }

   public int getMaxcount() {
      return this.maxcount;
   }

   public Map<Long, PT_SKIN_ITEM> getFontMap() {
      return this.fontMap;
   }

   public void setMaxcount(int maxcount) {
      this.maxcount = maxcount;
   }

   public void setFontMap(Map<Long, PT_SKIN_ITEM> fontMap) {
      this.fontMap = fontMap;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof DamageBox)) {
         return false;
      } else {
         DamageBox other = (DamageBox)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getMaxcount() != other.getMaxcount()) {
            return false;
         } else {
            Object this$fontMap = this.getFontMap();
            Object other$fontMap = other.getFontMap();
            if (this$fontMap == null) {
               if (other$fontMap != null) {
                  return false;
               }
            } else if (!this$fontMap.equals(other$fontMap)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof DamageBox;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getMaxcount();
      Object $fontMap = this.getFontMap();
      result = result * 59 + ($fontMap == null ? 43 : $fontMap.hashCode());
      return result;
   }

   public String toString() {
      return "DamageBox(maxcount=" + this.getMaxcount() + ", fontMap=" + this.getFontMap() + ")";
   }
}
