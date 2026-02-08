package com.dnfm.game.bag.model;

import com.dnfm.mina.protobuf.PT_TUTORIAL;
import com.dnfm.mina.protobuf.TYPE_TUTORIAL_LIST;
import java.util.ArrayList;

public class TutoBox {
   private TYPE_TUTORIAL_LIST tutos0 = new TYPE_TUTORIAL_LIST();
   private TYPE_TUTORIAL_LIST tutos1 = new TYPE_TUTORIAL_LIST();
   private TYPE_TUTORIAL_LIST tutos2 = new TYPE_TUTORIAL_LIST();
   private TYPE_TUTORIAL_LIST tutos3 = new TYPE_TUTORIAL_LIST();

   public void addTuto(PT_TUTORIAL tuto) {
      if (this.tutos1.type == null) {
         this.tutos1.type = 1;
         this.tutos1.tutorial = new ArrayList();
      }

      this.tutos1.tutorial.add(tuto);
   }

   public TYPE_TUTORIAL_LIST getTutos0() {
      return this.tutos0;
   }

   public TYPE_TUTORIAL_LIST getTutos1() {
      return this.tutos1;
   }

   public TYPE_TUTORIAL_LIST getTutos2() {
      return this.tutos2;
   }

   public TYPE_TUTORIAL_LIST getTutos3() {
      return this.tutos3;
   }

   public void setTutos0(TYPE_TUTORIAL_LIST tutos0) {
      this.tutos0 = tutos0;
   }

   public void setTutos1(TYPE_TUTORIAL_LIST tutos1) {
      this.tutos1 = tutos1;
   }

   public void setTutos2(TYPE_TUTORIAL_LIST tutos2) {
      this.tutos2 = tutos2;
   }

   public void setTutos3(TYPE_TUTORIAL_LIST tutos3) {
      this.tutos3 = tutos3;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof TutoBox)) {
         return false;
      } else {
         TutoBox other = (TutoBox)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$tutos0 = this.getTutos0();
            Object other$tutos0 = other.getTutos0();
            if (this$tutos0 == null) {
               if (other$tutos0 != null) {
                  return false;
               }
            } else if (!this$tutos0.equals(other$tutos0)) {
               return false;
            }

            Object this$tutos1 = this.getTutos1();
            Object other$tutos1 = other.getTutos1();
            if (this$tutos1 == null) {
               if (other$tutos1 != null) {
                  return false;
               }
            } else if (!this$tutos1.equals(other$tutos1)) {
               return false;
            }

            Object this$tutos2 = this.getTutos2();
            Object other$tutos2 = other.getTutos2();
            if (this$tutos2 == null) {
               if (other$tutos2 != null) {
                  return false;
               }
            } else if (!this$tutos2.equals(other$tutos2)) {
               return false;
            }

            Object this$tutos3 = this.getTutos3();
            Object other$tutos3 = other.getTutos3();
            if (this$tutos3 == null) {
               if (other$tutos3 != null) {
                  return false;
               }
            } else if (!this$tutos3.equals(other$tutos3)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof TutoBox;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $tutos0 = this.getTutos0();
      result = result * 59 + ($tutos0 == null ? 43 : $tutos0.hashCode());
      Object $tutos1 = this.getTutos1();
      result = result * 59 + ($tutos1 == null ? 43 : $tutos1.hashCode());
      Object $tutos2 = this.getTutos2();
      result = result * 59 + ($tutos2 == null ? 43 : $tutos2.hashCode());
      Object $tutos3 = this.getTutos3();
      result = result * 59 + ($tutos3 == null ? 43 : $tutos3.hashCode());
      return result;
   }

   public String toString() {
      return "TutoBox(tutos0=" + this.getTutos0() + ", tutos1=" + this.getTutos1() + ", tutos2=" + this.getTutos2() + ", tutos3=" + this.getTutos3() + ")";
   }
}
