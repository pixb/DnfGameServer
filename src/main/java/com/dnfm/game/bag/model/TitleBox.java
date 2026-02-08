package com.dnfm.game.bag.model;

import com.dnfm.common.thread.IdGenerator;
import com.dnfm.game.config.Equip;
import com.dnfm.game.equip.EquipDataPool;
import com.dnfm.mina.protobuf.PT_EQUIP;
import com.dnfm.mina.protobuf.PT_EQUIPPED;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TitleBox {
   private int maxcount = 40;
   private Map<Long, PT_EQUIP> titleMap = new HashMap();

   public void addTitle(PT_EQUIP title) {
      this.titleMap.put(title.guid, title);
   }

   public List<PT_EQUIP> getTitleList() {
      return new ArrayList(this.titleMap.values());
   }

   public void removeTitle(long guid) {
      this.titleMap.remove(guid);
   }

   public PT_EQUIP changeTitle(PT_EQUIPPED ptEquip) {
      PT_EQUIP title = new PT_EQUIP();
      title.score = ptEquip.score;
      title.index = ptEquip.index;
      title.guid = ptEquip.guid;
      title.quality = ptEquip.quality;
      return title;
   }

   public PT_EQUIP getTitle(long guid) {
      return (PT_EQUIP)this.titleMap.get(guid);
   }

   public PT_EQUIP addTitle(int index) {
      Equip equip = (Equip)EquipDataPool.index2Equip.get(index);
      PT_EQUIP title = new PT_EQUIP();
      title.score = equip.getScore();
      title.index = index;
      title.guid = IdGenerator.getNextId();
      title.quality = 100;
      this.titleMap.put(title.guid, title);
      return title;
   }

   public PT_EQUIP createTitle(int index) {
      Equip equip = (Equip)EquipDataPool.index2Equip.get(index);
      PT_EQUIP pt_equip = new PT_EQUIP();
      pt_equip.score = equip.getScore();
      pt_equip.index = index;
      pt_equip.guid = IdGenerator.getNextId();
      pt_equip.quality = 100;
      this.titleMap.put(pt_equip.guid, pt_equip);
      return pt_equip;
   }

   public List<PT_EQUIP> getTitles() {
      return new ArrayList(this.titleMap.values());
   }

   public void remove(long guid) {
      this.titleMap.remove(guid);
   }

   public int getMaxcount() {
      return this.maxcount;
   }

   public Map<Long, PT_EQUIP> getTitleMap() {
      return this.titleMap;
   }

   public void setMaxcount(int maxcount) {
      this.maxcount = maxcount;
   }

   public void setTitleMap(Map<Long, PT_EQUIP> titleMap) {
      this.titleMap = titleMap;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof TitleBox)) {
         return false;
      } else {
         TitleBox other = (TitleBox)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getMaxcount() != other.getMaxcount()) {
            return false;
         } else {
            Object this$titleMap = this.getTitleMap();
            Object other$titleMap = other.getTitleMap();
            if (this$titleMap == null) {
               if (other$titleMap != null) {
                  return false;
               }
            } else if (!this$titleMap.equals(other$titleMap)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof TitleBox;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getMaxcount();
      Object $titleMap = this.getTitleMap();
      result = result * 59 + ($titleMap == null ? 43 : $titleMap.hashCode());
      return result;
   }

   public String toString() {
      return "TitleBox(maxcount=" + this.getMaxcount() + ", titleMap=" + this.getTitleMap() + ")";
   }
}
