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

public class PT_PVE_ROUND_INFO$$JProtoBufClass implements Codec<PT_PVE_ROUND_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_PVE_ROUND_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_PVE_ROUND_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_PVE_ROUND_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.totalDamage)) {
         Long var18 = var1.totalDamage;
         var2 += CodedOutputStream.computeInt64Size(1, var18);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.totalDamageRate)) {
         Float var19 = var1.totalDamageRate;
         var2 += CodedOutputStream.computeFloatSize(2, var19);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.harmDamage)) {
         Long var20 = var1.harmDamage;
         var2 += CodedOutputStream.computeInt64Size(3, var20);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.harmDamageRate)) {
         Float var21 = var1.harmDamageRate;
         var2 += CodedOutputStream.computeFloatSize(4, var21);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.supportPoint)) {
         Integer var22 = var1.supportPoint;
         var2 += CodedOutputStream.computeInt32Size(5, var22);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.playtime)) {
         Long var23 = var1.playtime;
         var2 += CodedOutputStream.computeInt64Size(6, var23);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.skillAccuracyRate)) {
         Float var24 = var1.skillAccuracyRate;
         var2 += CodedOutputStream.computeFloatSize(7, var24);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.counterDamage)) {
         Long var25 = var1.counterDamage;
         var2 += CodedOutputStream.computeInt64Size(8, var25);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.maxComboDamage)) {
         Long var26 = var1.maxComboDamage;
         var2 += CodedOutputStream.computeInt64Size(9, var26);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.maximumDamage)) {
         Long var27 = var1.maximumDamage;
         var2 += CodedOutputStream.computeInt64Size(10, var27);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.totalCombo)) {
         Integer var28 = var1.totalCombo;
         var2 += CodedOutputStream.computeInt32Size(11, var28);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.aerialDamage)) {
         Long var29 = var1.aerialDamage;
         var2 += CodedOutputStream.computeInt64Size(12, var29);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.standDamage)) {
         Long var30 = var1.standDamage;
         var2 += CodedOutputStream.computeInt64Size(13, var30);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.harmCount)) {
         Integer var31 = var1.harmCount;
         var2 += CodedOutputStream.computeInt32Size(14, var31);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.monsterKillCount)) {
         Integer var32 = var1.monsterKillCount;
         var2 += CodedOutputStream.computeInt32Size(15, var32);
      }

      return var2;
   }

   public void doWriteTo(PT_PVE_ROUND_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.totalDamage)) {
         Long var18 = var1.totalDamage;
         if (var18 != null) {
            var2.writeInt64(1, var18);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.totalDamageRate)) {
         Float var19 = var1.totalDamageRate;
         if (var19 != null) {
            var2.writeFloat(2, var19);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.harmDamage)) {
         Long var20 = var1.harmDamage;
         if (var20 != null) {
            var2.writeInt64(3, var20);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.harmDamageRate)) {
         Float var21 = var1.harmDamageRate;
         if (var21 != null) {
            var2.writeFloat(4, var21);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.supportPoint)) {
         Integer var22 = var1.supportPoint;
         if (var22 != null) {
            var2.writeInt32(5, var22);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.playtime)) {
         Long var23 = var1.playtime;
         if (var23 != null) {
            var2.writeInt64(6, var23);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.skillAccuracyRate)) {
         Float var24 = var1.skillAccuracyRate;
         if (var24 != null) {
            var2.writeFloat(7, var24);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.counterDamage)) {
         Long var25 = var1.counterDamage;
         if (var25 != null) {
            var2.writeInt64(8, var25);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.maxComboDamage)) {
         Long var26 = var1.maxComboDamage;
         if (var26 != null) {
            var2.writeInt64(9, var26);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.maximumDamage)) {
         Long var27 = var1.maximumDamage;
         if (var27 != null) {
            var2.writeInt64(10, var27);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.totalCombo)) {
         Integer var28 = var1.totalCombo;
         if (var28 != null) {
            var2.writeInt32(11, var28);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.aerialDamage)) {
         Long var29 = var1.aerialDamage;
         if (var29 != null) {
            var2.writeInt64(12, var29);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.standDamage)) {
         Long var30 = var1.standDamage;
         if (var30 != null) {
            var2.writeInt64(13, var30);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.harmCount)) {
         Integer var31 = var1.harmCount;
         if (var31 != null) {
            var2.writeInt32(14, var31);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.monsterKillCount)) {
         Integer var32 = var1.monsterKillCount;
         if (var32 != null) {
            var2.writeInt32(15, var32);
         }
      }

   }

   public void writeTo(PT_PVE_ROUND_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_PVE_ROUND_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_PVE_ROUND_INFO var2 = new PT_PVE_ROUND_INFO();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.totalDamage = var1.readInt64();
            } else if (var5 == 21) {
               var2.totalDamageRate = var1.readFloat();
            } else if (var5 == 24) {
               var2.harmDamage = var1.readInt64();
            } else if (var5 == 37) {
               var2.harmDamageRate = var1.readFloat();
            } else if (var5 == 40) {
               var2.supportPoint = var1.readInt32();
            } else if (var5 == 48) {
               var2.playtime = var1.readInt64();
            } else if (var5 == 61) {
               var2.skillAccuracyRate = var1.readFloat();
            } else if (var5 == 64) {
               var2.counterDamage = var1.readInt64();
            } else if (var5 == 72) {
               var2.maxComboDamage = var1.readInt64();
            } else if (var5 == 80) {
               var2.maximumDamage = var1.readInt64();
            } else if (var5 == 88) {
               var2.totalCombo = var1.readInt32();
            } else if (var5 == 96) {
               var2.aerialDamage = var1.readInt64();
            } else if (var5 == 104) {
               var2.standDamage = var1.readInt64();
            } else if (var5 == 112) {
               var2.harmCount = var1.readInt32();
            } else if (var5 == 120) {
               var2.monsterKillCount = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_PVE_ROUND_INFO.class);
         return this.descriptor = var1;
      }
   }
}
