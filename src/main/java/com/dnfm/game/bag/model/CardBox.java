package com.dnfm.game.bag.model;

import com.dnfm.mina.protobuf.PT_STACKABLE;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardBox {
   private int maxcount = 40;
   private Map<Integer, PT_STACKABLE> cardMap = new HashMap();

   public void updateCardSub(int index, int count) {
      PT_STACKABLE card = (PT_STACKABLE)this.cardMap.get(index);
      if (card == null) {
         card = new PT_STACKABLE();
         card.index = index;
         card.count = count;
         this.cardMap.put(index, card);
      } else {
         card.count = card.count - count;
      }

      if (card.count <= 0) {
         this.cardMap.remove(index);
      }

   }

   public void updateCardAdd(int index, int count) {
      PT_STACKABLE card = (PT_STACKABLE)this.cardMap.get(index);
      if (card == null) {
         card = new PT_STACKABLE();
         card.index = index;
         card.count = count;
         this.cardMap.put(index, card);
      } else {
         card.count = card.count + count;
      }

   }

   public PT_STACKABLE getCard(int index) {
      return (PT_STACKABLE)this.cardMap.get(index);
   }

   public void addCard(int index, int cnt, long acqTime) {
      PT_STACKABLE pt_stackable = (PT_STACKABLE)this.cardMap.get(index);
      if (pt_stackable == null) {
         pt_stackable = new PT_STACKABLE();
         pt_stackable.index = index;
         pt_stackable.count = cnt;
         pt_stackable.acquisitiontime = acqTime;
         this.cardMap.put(index, pt_stackable);
      } else {
         pt_stackable.count = pt_stackable.count + cnt;
         pt_stackable.acquisitiontime = acqTime;
         this.cardMap.put(index, pt_stackable);
      }

   }

   public void addCard(PT_STACKABLE card) {
      if (card.count == 0) {
         if (this.cardMap.get(card.index) != null) {
            this.cardMap.remove(card.index);
         }
      } else {
         this.cardMap.put(card.index, card);
      }

   }

   public void updateCard(PT_STACKABLE card) {
      if (card.count == 0) {
         this.cardMap.remove(card.index);
      } else {
         PT_STACKABLE pt_stackable = (PT_STACKABLE)this.cardMap.get(card.index);
         if (pt_stackable != null) {
            pt_stackable.count = pt_stackable.count + card.count;
            this.cardMap.put(card.index, pt_stackable);
         } else {
            this.cardMap.put(card.index, card);
         }
      }

   }

   public void removeCard(int index, int cnt) {
      PT_STACKABLE pt_stackable = (PT_STACKABLE)this.cardMap.get(index);
      pt_stackable.count = pt_stackable.count - cnt;
      if (pt_stackable.count <= 0) {
         this.cardMap.remove(index);
      } else {
         this.cardMap.put(index, pt_stackable);
      }

   }

   public List<PT_STACKABLE> getCards() {
      return new ArrayList(this.cardMap.values());
   }

   public int getMaxcount() {
      return this.maxcount;
   }

   public Map<Integer, PT_STACKABLE> getCardMap() {
      return this.cardMap;
   }

   public void setMaxcount(int maxcount) {
      this.maxcount = maxcount;
   }

   public void setCardMap(Map<Integer, PT_STACKABLE> cardMap) {
      this.cardMap = cardMap;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CardBox)) {
         return false;
      } else {
         CardBox other = (CardBox)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getMaxcount() != other.getMaxcount()) {
            return false;
         } else {
            Object this$cardMap = this.getCardMap();
            Object other$cardMap = other.getCardMap();
            if (this$cardMap == null) {
               if (other$cardMap != null) {
                  return false;
               }
            } else if (!this$cardMap.equals(other$cardMap)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof CardBox;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getMaxcount();
      Object $cardMap = this.getCardMap();
      result = result * 59 + ($cardMap == null ? 43 : $cardMap.hashCode());
      return result;
   }

   public String toString() {
      return "CardBox(maxcount=" + this.getMaxcount() + ", cardMap=" + this.getCardMap() + ")";
   }
}
