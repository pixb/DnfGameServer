package com.dnfm.game.bag.model;

import com.dnfm.mina.protobuf.PT_CREATURE_ERRAND;

public class CreatureErrandBox {
   private PT_CREATURE_ERRAND errandinfo = new PT_CREATURE_ERRAND();

   public PT_CREATURE_ERRAND getErrandinfo() {
      return this.errandinfo;
   }

   public void setErrandinfo(PT_CREATURE_ERRAND errandinfo) {
      this.errandinfo = errandinfo;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CreatureErrandBox)) {
         return false;
      } else {
         CreatureErrandBox other = (CreatureErrandBox)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$errandinfo = this.getErrandinfo();
            Object other$errandinfo = other.getErrandinfo();
            if (this$errandinfo == null) {
               if (other$errandinfo != null) {
                  return false;
               }
            } else if (!this$errandinfo.equals(other$errandinfo)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof CreatureErrandBox;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $errandinfo = this.getErrandinfo();
      result = result * 59 + ($errandinfo == null ? 43 : $errandinfo.hashCode());
      return result;
   }

   public String toString() {
      return "CreatureErrandBox(errandinfo=" + this.getErrandinfo() + ")";
   }
}
