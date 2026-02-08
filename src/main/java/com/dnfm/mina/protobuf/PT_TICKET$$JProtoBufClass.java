package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.code.CodedConstant;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.InvalidProtocolBufferException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class PT_TICKET$$JProtoBufClass implements Codec<PT_TICKET>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_TICKET var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_TICKET decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_TICKET var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.dungeontype)) {
         String var13 = var1.dungeontype;
         var2 += CodedOutputStream.computeStringSize(1, var13);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.dailyticket)) {
         Integer var14 = var1.dailyticket;
         var2 += CodedOutputStream.computeInt32Size(2, var14);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.weeklyticket)) {
         Integer var15 = var1.weeklyticket;
         var2 += CodedOutputStream.computeInt32Size(3, var15);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.premiumticket)) {
         Integer var16 = var1.premiumticket;
         var2 += CodedOutputStream.computeInt32Size(4, var16);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.sweepticket)) {
         Integer var17 = var1.sweepticket;
         var2 += CodedOutputStream.computeInt32Size(5, var17);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.dailyrewardticket)) {
         Integer var18 = var1.dailyrewardticket;
         var2 += CodedOutputStream.computeInt32Size(6, var18);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.weeklyrewardticket)) {
         Integer var19 = var1.weeklyrewardticket;
         var2 += CodedOutputStream.computeInt32Size(7, var19);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.buycount)) {
         Integer var20 = var1.buycount;
         var2 += CodedOutputStream.computeInt32Size(8, var20);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.chargetime)) {
         Long var21 = var1.chargetime;
         var2 += CodedOutputStream.computeInt64Size(9, var21);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.freeticket)) {
         Integer var22 = var1.freeticket;
         var2 += CodedOutputStream.computeInt32Size(10, var22);
      }

      return var2;
   }

   public void doWriteTo(PT_TICKET var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.dungeontype)) {
         String var13 = var1.dungeontype;
         if (var13 != null) {
            var2.writeString(1, var13);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.dailyticket)) {
         Integer var14 = var1.dailyticket;
         if (var14 != null) {
            var2.writeInt32(2, var14);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.weeklyticket)) {
         Integer var15 = var1.weeklyticket;
         if (var15 != null) {
            var2.writeInt32(3, var15);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.premiumticket)) {
         Integer var16 = var1.premiumticket;
         if (var16 != null) {
            var2.writeInt32(4, var16);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.sweepticket)) {
         Integer var17 = var1.sweepticket;
         if (var17 != null) {
            var2.writeInt32(5, var17);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.dailyrewardticket)) {
         Integer var18 = var1.dailyrewardticket;
         if (var18 != null) {
            var2.writeInt32(6, var18);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.weeklyrewardticket)) {
         Integer var19 = var1.weeklyrewardticket;
         if (var19 != null) {
            var2.writeInt32(7, var19);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.buycount)) {
         Integer var20 = var1.buycount;
         if (var20 != null) {
            var2.writeInt32(8, var20);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.chargetime)) {
         Long var21 = var1.chargetime;
         if (var21 != null) {
            var2.writeInt64(9, var21);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.freeticket)) {
         Integer var22 = var1.freeticket;
         if (var22 != null) {
            var2.writeInt32(10, var22);
         }
      }

   }

   public void writeTo(PT_TICKET var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_TICKET readFrom(CodedInputStream var1) throws IOException {
      PT_TICKET var2 = new PT_TICKET();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 10) {
               var2.dungeontype = var1.readString();
            } else if (var5 == 16) {
               var2.dailyticket = var1.readInt32();
            } else if (var5 == 24) {
               var2.weeklyticket = var1.readInt32();
            } else if (var5 == 32) {
               var2.premiumticket = var1.readInt32();
            } else if (var5 == 40) {
               var2.sweepticket = var1.readInt32();
            } else if (var5 == 48) {
               var2.dailyrewardticket = var1.readInt32();
            } else if (var5 == 56) {
               var2.weeklyrewardticket = var1.readInt32();
            } else if (var5 == 64) {
               var2.buycount = var1.readInt32();
            } else if (var5 == 72) {
               var2.chargetime = var1.readInt64();
            } else if (var5 == 80) {
               var2.freeticket = var1.readInt32();
            } else {
               var1.skipField(var5);
            }
         }

         return var2;
      } catch (InvalidProtocolBufferException var6) {
         throw var6;
      } catch (IOException var7) {
         throw var7;
      }
   }

   public Descriptors.Descriptor getDescriptor() throws IOException {
      if (this.descriptor != null) {
         return this.descriptor;
      } else {
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_TICKET.class);
         return this.descriptor = var1;
      }
   }
}
