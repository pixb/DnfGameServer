package com.dnfm.game.bag.model;

import com.dnfm.mina.protobuf.PT_CREATURE;
import java.util.ArrayList;
import java.util.List;

public class CreatureBox {
   private int maxcount = 80;
   private List<PT_CREATURE> creatures = new ArrayList();

   public void addCreature(PT_CREATURE creature) {
      this.creatures.add(creature);
   }

   public int getMaxcount() {
      return this.maxcount;
   }

   public List<PT_CREATURE> getCreatures() {
      return this.creatures;
   }

   public void setMaxcount(int maxcount) {
      this.maxcount = maxcount;
   }

   public void setCreatures(List<PT_CREATURE> creatures) {
      this.creatures = creatures;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CreatureBox)) {
         return false;
      } else {
         CreatureBox other = (CreatureBox)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getMaxcount() != other.getMaxcount()) {
            return false;
         } else {
            Object this$creatures = this.getCreatures();
            Object other$creatures = other.getCreatures();
            if (this$creatures == null) {
               if (other$creatures != null) {
                  return false;
               }
            } else if (!this$creatures.equals(other$creatures)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof CreatureBox;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getMaxcount();
      Object $creatures = this.getCreatures();
      result = result * 59 + ($creatures == null ? 43 : $creatures.hashCode());
      return result;
   }

   public String toString() {
      return "CreatureBox(maxcount=" + this.getMaxcount() + ", creatures=" + this.getCreatures() + ")";
   }
}
