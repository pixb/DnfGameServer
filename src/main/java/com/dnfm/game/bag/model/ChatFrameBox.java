package com.dnfm.game.bag.model;

import com.dnfm.common.thread.IdGenerator;
import com.dnfm.mina.protobuf.PT_SKIN_ITEM;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatFrameBox {
   private int maxcount = -1;
   private Map<Long, PT_SKIN_ITEM> chatFrameMap = new HashMap();

   public void remove(long guid) {
      this.chatFrameMap.remove(guid);
   }

   public void addChatFrame(PT_SKIN_ITEM chatFrame) {
      this.chatFrameMap.put(chatFrame.guid, chatFrame);
   }

   public List<PT_SKIN_ITEM> getChatFrames() {
      return new ArrayList(this.chatFrameMap.values());
   }

   public void addChatFrame(int index) {
      PT_SKIN_ITEM pt_skin_item = new PT_SKIN_ITEM();
      pt_skin_item.guid = IdGenerator.getNextId();
      pt_skin_item.index = index;
      this.chatFrameMap.put(pt_skin_item.guid, pt_skin_item);
   }

   public int getMaxcount() {
      return this.maxcount;
   }

   public Map<Long, PT_SKIN_ITEM> getChatFrameMap() {
      return this.chatFrameMap;
   }

   public void setMaxcount(int maxcount) {
      this.maxcount = maxcount;
   }

   public void setChatFrameMap(Map<Long, PT_SKIN_ITEM> chatFrameMap) {
      this.chatFrameMap = chatFrameMap;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ChatFrameBox)) {
         return false;
      } else {
         ChatFrameBox other = (ChatFrameBox)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getMaxcount() != other.getMaxcount()) {
            return false;
         } else {
            Object this$chatFrameMap = this.getChatFrameMap();
            Object other$chatFrameMap = other.getChatFrameMap();
            if (this$chatFrameMap == null) {
               if (other$chatFrameMap != null) {
                  return false;
               }
            } else if (!this$chatFrameMap.equals(other$chatFrameMap)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ChatFrameBox;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getMaxcount();
      Object $chatFrameMap = this.getChatFrameMap();
      result = result * 59 + ($chatFrameMap == null ? 43 : $chatFrameMap.hashCode());
      return result;
   }

   public String toString() {
      return "ChatFrameBox(maxcount=" + this.getMaxcount() + ", chatFrameMap=" + this.getChatFrameMap() + ")";
   }
}
