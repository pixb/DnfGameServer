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

public class PT_RAID_ENTRANCE_COUNT$$JProtoBufClass implements Codec<PT_RAID_ENTRANCE_COUNT>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_RAID_ENTRANCE_COUNT var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_RAID_ENTRANCE_COUNT decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_RAID_ENTRANCE_COUNT var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.raidindex)) {
         Integer var12 = var1.raidindex;
         var2 += CodedOutputStream.computeInt32Size(1, var12);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.payment)) {
         Boolean var13 = var1.payment;
         var2 += CodedOutputStream.computeBoolSize(2, var13);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.dailycharacter)) {
         Integer var14 = var1.dailycharacter;
         var2 += CodedOutputStream.computeInt32Size(3, var14);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.character)) {
         Integer var15 = var1.character;
         var2 += CodedOutputStream.computeInt32Size(4, var15);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.account)) {
         Integer var16 = var1.account;
         var2 += CodedOutputStream.computeInt32Size(5, var16);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.dailyrewardcharacter)) {
         Integer var17 = var1.dailyrewardcharacter;
         var2 += CodedOutputStream.computeInt32Size(6, var17);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.rewardcharacter)) {
         Integer var18 = var1.rewardcharacter;
         var2 += CodedOutputStream.computeInt32Size(7, var18);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.rewardaccount)) {
         Integer var19 = var1.rewardaccount;
         var2 += CodedOutputStream.computeInt32Size(8, var19);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.maxclearphase)) {
         Integer var20 = var1.maxclearphase;
         var2 += CodedOutputStream.computeInt32Size(9, var20);
      }

      return var2;
   }

   public void doWriteTo(PT_RAID_ENTRANCE_COUNT var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.raidindex)) {
         Integer var12 = var1.raidindex;
         if (var12 != null) {
            var2.writeInt32(1, var12);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.payment)) {
         Boolean var13 = var1.payment;
         if (var13 != null) {
            var2.writeBool(2, var13);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.dailycharacter)) {
         Integer var14 = var1.dailycharacter;
         if (var14 != null) {
            var2.writeInt32(3, var14);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.character)) {
         Integer var15 = var1.character;
         if (var15 != null) {
            var2.writeInt32(4, var15);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.account)) {
         Integer var16 = var1.account;
         if (var16 != null) {
            var2.writeInt32(5, var16);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.dailyrewardcharacter)) {
         Integer var17 = var1.dailyrewardcharacter;
         if (var17 != null) {
            var2.writeInt32(6, var17);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.rewardcharacter)) {
         Integer var18 = var1.rewardcharacter;
         if (var18 != null) {
            var2.writeInt32(7, var18);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.rewardaccount)) {
         Integer var19 = var1.rewardaccount;
         if (var19 != null) {
            var2.writeInt32(8, var19);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.maxclearphase)) {
         Integer var20 = var1.maxclearphase;
         if (var20 != null) {
            var2.writeInt32(9, var20);
         }
      }

   }

   public void writeTo(PT_RAID_ENTRANCE_COUNT var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_RAID_ENTRANCE_COUNT readFrom(CodedInputStream var1) throws IOException {
      PT_RAID_ENTRANCE_COUNT var2 = new PT_RAID_ENTRANCE_COUNT();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.raidindex = var1.readInt32();
            } else if (var5 == 16) {
               var2.payment = var1.readBool();
            } else if (var5 == 24) {
               var2.dailycharacter = var1.readInt32();
            } else if (var5 == 32) {
               var2.character = var1.readInt32();
            } else if (var5 == 40) {
               var2.account = var1.readInt32();
            } else if (var5 == 48) {
               var2.dailyrewardcharacter = var1.readInt32();
            } else if (var5 == 56) {
               var2.rewardcharacter = var1.readInt32();
            } else if (var5 == 64) {
               var2.rewardaccount = var1.readInt32();
            } else if (var5 == 72) {
               var2.maxclearphase = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_RAID_ENTRANCE_COUNT.class);
         return this.descriptor = var1;
      }
   }
}
