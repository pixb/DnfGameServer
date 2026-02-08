package com.dnfm.game.bag.model;

import com.dnfm.mina.protobuf.PT_STACKABLE;
import java.util.ArrayList;
import java.util.List;

public class CrackBox {
   private int maxcount;
   private List<PT_STACKABLE> cracks = new ArrayList();

   public void addCrack(PT_STACKABLE crack) {
      this.cracks.add(crack);
   }

   public int getMaxcount() {
      return this.maxcount;
   }

   public List<PT_STACKABLE> getCracks() {
      return this.cracks;
   }

   public void setMaxcount(int maxcount) {
      this.maxcount = maxcount;
   }

   public void setCracks(List<PT_STACKABLE> cracks) {
      this.cracks = cracks;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CrackBox)) {
         return false;
      } else {
         CrackBox other = (CrackBox)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getMaxcount() != other.getMaxcount()) {
            return false;
         } else {
            Object this$cracks = this.getCracks();
            Object other$cracks = other.getCracks();
            if (this$cracks == null) {
               if (other$cracks != null) {
                  return false;
               }
            } else if (!this$cracks.equals(other$cracks)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof CrackBox;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getMaxcount();
      Object $cracks = this.getCracks();
      result = result * 59 + ($cracks == null ? 43 : $cracks.hashCode());
      return result;
   }

   public String toString() {
      return "CrackBox(maxcount=" + this.getMaxcount() + ", cracks=" + this.getCracks() + ")";
   }
}
