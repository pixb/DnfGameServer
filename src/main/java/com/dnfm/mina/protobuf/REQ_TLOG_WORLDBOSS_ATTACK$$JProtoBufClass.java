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

public class REQ_TLOG_WORLDBOSS_ATTACK$$JProtoBufClass implements Codec<REQ_TLOG_WORLDBOSS_ATTACK>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_TLOG_WORLDBOSS_ATTACK var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_TLOG_WORLDBOSS_ATTACK decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_TLOG_WORLDBOSS_ATTACK var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var12 = var1.dungeonguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var12);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var13 = var1.partyguid;
         var2 += CodedOutputStream.computeUInt64Size(2, var13);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.atkthresholdvalue)) {
         Long var14 = var1.atkthresholdvalue;
         var2 += CodedOutputStream.computeUInt64Size(3, var14);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.bosspattern5SInjuryavg)) {
         Long var15 = var1.bosspattern5SInjuryavg;
         var2 += CodedOutputStream.computeUInt64Size(4, var15);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.bossPattern5SInjurymax)) {
         Long var16 = var1.bossPattern5SInjurymax;
         var2 += CodedOutputStream.computeUInt64Size(5, var16);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.bossPattern5SInjurymin)) {
         Long var17 = var1.bossPattern5SInjurymin;
         var2 += CodedOutputStream.computeUInt64Size(6, var17);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.bossPatternInjurySequence)) {
         String var18 = var1.bossPatternInjurySequence;
         var2 += CodedOutputStream.computeStringSize(7, var18);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.atkbuffidlist)) {
         String var19 = var1.atkbuffidlist;
         var2 += CodedOutputStream.computeStringSize(8, var19);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.atkbuffcoefficientlist)) {
         String var20 = var1.atkbuffcoefficientlist;
         var2 += CodedOutputStream.computeStringSize(9, var20);
      }

      return var2;
   }

   public void doWriteTo(REQ_TLOG_WORLDBOSS_ATTACK var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var12 = var1.dungeonguid;
         if (var12 != null) {
            var2.writeUInt64(1, var12);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var13 = var1.partyguid;
         if (var13 != null) {
            var2.writeUInt64(2, var13);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.atkthresholdvalue)) {
         Long var14 = var1.atkthresholdvalue;
         if (var14 != null) {
            var2.writeUInt64(3, var14);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.bosspattern5SInjuryavg)) {
         Long var15 = var1.bosspattern5SInjuryavg;
         if (var15 != null) {
            var2.writeUInt64(4, var15);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.bossPattern5SInjurymax)) {
         Long var16 = var1.bossPattern5SInjurymax;
         if (var16 != null) {
            var2.writeUInt64(5, var16);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.bossPattern5SInjurymin)) {
         Long var17 = var1.bossPattern5SInjurymin;
         if (var17 != null) {
            var2.writeUInt64(6, var17);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.bossPatternInjurySequence)) {
         String var18 = var1.bossPatternInjurySequence;
         if (var18 != null) {
            var2.writeString(7, var18);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.atkbuffidlist)) {
         String var19 = var1.atkbuffidlist;
         if (var19 != null) {
            var2.writeString(8, var19);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.atkbuffcoefficientlist)) {
         String var20 = var1.atkbuffcoefficientlist;
         if (var20 != null) {
            var2.writeString(9, var20);
         }
      }

   }

   public void writeTo(REQ_TLOG_WORLDBOSS_ATTACK var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_TLOG_WORLDBOSS_ATTACK readFrom(CodedInputStream var1) throws IOException {
      REQ_TLOG_WORLDBOSS_ATTACK var2 = new REQ_TLOG_WORLDBOSS_ATTACK();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.dungeonguid = var1.readUInt64();
            } else if (var5 == 16) {
               var2.partyguid = var1.readUInt64();
            } else if (var5 == 24) {
               var2.atkthresholdvalue = var1.readUInt64();
            } else if (var5 == 32) {
               var2.bosspattern5SInjuryavg = var1.readUInt64();
            } else if (var5 == 40) {
               var2.bossPattern5SInjurymax = var1.readUInt64();
            } else if (var5 == 48) {
               var2.bossPattern5SInjurymin = var1.readUInt64();
            } else if (var5 == 58) {
               var2.bossPatternInjurySequence = var1.readString();
            } else if (var5 == 66) {
               var2.atkbuffidlist = var1.readString();
            } else if (var5 == 74) {
               var2.atkbuffcoefficientlist = var1.readString();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_TLOG_WORLDBOSS_ATTACK.class);
         return this.descriptor = var1;
      }
   }
}
