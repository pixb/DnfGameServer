package com.dnfm.game.bag.model;

import com.dnfm.common.thread.IdGenerator;
import com.dnfm.mina.protobuf.PT_SKIN_ITEM;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharFrameBox {
   private int maxcount = -1;
   private Map<Long, PT_SKIN_ITEM> charFrameMap = new HashMap();

   public void addCharFrame(PT_SKIN_ITEM charFrame) {
      this.charFrameMap.put(charFrame.guid, charFrame);
   }

   public List<PT_SKIN_ITEM> getCharFrames() {
      return new ArrayList(this.charFrameMap.values());
   }

   public void remove(long guid) {
      this.charFrameMap.remove(guid);
   }

   public void addCharFrame(int index) {
      PT_SKIN_ITEM pt_skin_item = new PT_SKIN_ITEM();
      pt_skin_item.index = index;
      pt_skin_item.guid = IdGenerator.getNextId();
      this.charFrameMap.put(pt_skin_item.guid, pt_skin_item);
   }

   public int getMaxcount() {
      return this.maxcount;
   }

   public Map<Long, PT_SKIN_ITEM> getCharFrameMap() {
      return this.charFrameMap;
   }

   public void setMaxcount(int maxcount) {
      this.maxcount = maxcount;
   }

   public void setCharFrameMap(Map<Long, PT_SKIN_ITEM> charFrameMap) {
      this.charFrameMap = charFrameMap;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CharFrameBox)) {
         return false;
      } else {
         CharFrameBox other = (CharFrameBox)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getMaxcount() != other.getMaxcount()) {
            return false;
         } else {
            Object this$charFrameMap = this.getCharFrameMap();
            Object other$charFrameMap = other.getCharFrameMap();
            if (this$charFrameMap == null) {
               if (other$charFrameMap != null) {
                  return false;
               }
            } else if (!this$charFrameMap.equals(other$charFrameMap)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof CharFrameBox;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getMaxcount();
      Object $charFrameMap = this.getCharFrameMap();
      result = result * 59 + ($charFrameMap == null ? 43 : $charFrameMap.hashCode());
      return result;
   }

   public String toString() {
      return "CharFrameBox(maxcount=" + this.getMaxcount() + ", charFrameMap=" + this.getCharFrameMap() + ")";
   }
}
