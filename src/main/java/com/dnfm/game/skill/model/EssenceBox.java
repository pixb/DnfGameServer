package com.dnfm.game.skill.model;

import com.dnfm.mina.protobuf.PT_EQUIP;
import java.util.ArrayList;
import java.util.List;

public class EssenceBox {
   private List<PT_EQUIP> essence = new ArrayList();

   public List<PT_EQUIP> getEssence() {
      return this.essence;
   }

   public void setEssence(List<PT_EQUIP> essence) {
      this.essence = essence;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EssenceBox)) {
         return false;
      } else {
         EssenceBox other = (EssenceBox)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$essence = this.getEssence();
            Object other$essence = other.getEssence();
            if (this$essence == null) {
               if (other$essence != null) {
                  return false;
               }
            } else if (!this$essence.equals(other$essence)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EssenceBox;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $essence = this.getEssence();
      result = result * 59 + ($essence == null ? 43 : $essence.hashCode());
      return result;
   }

   public String toString() {
      return "EssenceBox(essence=" + this.getEssence() + ")";
   }
}
