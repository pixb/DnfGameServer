package com.dnfm.game.bag.model;

import com.dnfm.mina.protobuf.PT_ADVENTUREBOOK_INFO;
import com.dnfm.mina.protobuf.PT_ADVENTUREBOOK_OPEN_CONDITION;
import java.util.List;

public class AdvBookBox {
   private List<PT_ADVENTUREBOOK_INFO> adventurebooks;
   private List<PT_ADVENTUREBOOK_OPEN_CONDITION> openconditions;

   public List<PT_ADVENTUREBOOK_INFO> getAdventurebooks() {
      return this.adventurebooks;
   }

   public List<PT_ADVENTUREBOOK_OPEN_CONDITION> getOpenconditions() {
      return this.openconditions;
   }

   public void setAdventurebooks(List<PT_ADVENTUREBOOK_INFO> adventurebooks) {
      this.adventurebooks = adventurebooks;
   }

   public void setOpenconditions(List<PT_ADVENTUREBOOK_OPEN_CONDITION> openconditions) {
      this.openconditions = openconditions;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof AdvBookBox)) {
         return false;
      } else {
         AdvBookBox other = (AdvBookBox)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$adventurebooks = this.getAdventurebooks();
            Object other$adventurebooks = other.getAdventurebooks();
            if (this$adventurebooks == null) {
               if (other$adventurebooks != null) {
                  return false;
               }
            } else if (!this$adventurebooks.equals(other$adventurebooks)) {
               return false;
            }

            Object this$openconditions = this.getOpenconditions();
            Object other$openconditions = other.getOpenconditions();
            if (this$openconditions == null) {
               if (other$openconditions != null) {
                  return false;
               }
            } else if (!this$openconditions.equals(other$openconditions)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof AdvBookBox;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $adventurebooks = this.getAdventurebooks();
      result = result * 59 + ($adventurebooks == null ? 43 : $adventurebooks.hashCode());
      Object $openconditions = this.getOpenconditions();
      result = result * 59 + ($openconditions == null ? 43 : $openconditions.hashCode());
      return result;
   }

   public String toString() {
      return "AdvBookBox(adventurebooks=" + this.getAdventurebooks() + ", openconditions=" + this.getOpenconditions() + ")";
   }
}
