package com.dnfm.game.utils;

import java.util.Arrays;

public class BitSet {
   private final long[] BV = new long[32];
   private final Boolean[] bit = new Boolean[32];

   public BitSet(long i32) {
      this.initiate();
      this.set(i32);
   }

   public static BitSet NEW() {
      return new BitSet(0L);
   }

   private void initiate() {
      for(int i = 1; i <= 32; ++i) {
         this.BV[i - 1] = (long)Math.pow((double)2.0F, (double)(i - 1));
      }

   }

   private void setI32(int startPos, long i32) {
      for(int j = 1; j <= 32; ++j) {
         int i = 33 - j - 1;
         if (i32 >= this.BV[i]) {
            this.bit[startPos + i] = true;
            i32 -= this.BV[i];
         } else {
            this.bit[startPos + i] = false;
         }
      }

   }

   public void set(long i32) {
      long[] nums = new long[]{i32};
      int startPos = 0;

      for(long num : nums) {
         this.setI32(startPos, num);
         startPos += 32;
      }

   }

   public void setBit(int index, Boolean value) {
      if (index >= 32) {
         index -= 32;
      }

      if (index >= 0 && index <= this.bit.length) {
         this.bit[index] = value;
      }
   }

   public void setBit(int index) {
      --index;
      if (index >= 32) {
         index -= 32;
      }

      if (index >= 0 && index <= this.bit.length) {
         this.bit[index] = true;
      }
   }

   public boolean isSet(int index) {
      return index >= 0 && index <= this.bit.length ? this.bit[index] : false;
   }

   public int getI32() {
      int i32 = 0;
      int i = 0;

      for(Boolean boo : this.bit) {
         if (boo != null && boo) {
            i32 = (int)((long)i32 + this.BV[i]);
         }

         ++i;
      }

      return i32;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         BitSet bitSet = (BitSet)o;
         return Arrays.equals(this.BV, bitSet.BV);
      } else {
         return false;
      }
   }
}
