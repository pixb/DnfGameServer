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

public class PT_GUARDIAN_ORDER$$JProtoBufClass implements Codec<PT_GUARDIAN_ORDER>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_GUARDIAN_ORDER var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_GUARDIAN_ORDER decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_GUARDIAN_ORDER var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.guardiandeal)) {
         Long var9 = var1.guardiandeal;
         var2 += CodedOutputStream.computeInt64Size(1, var9);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var10 = var1.growtype;
         var2 += CodedOutputStream.computeInt32Size(2, var10);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var11 = var1.secondgrowtype;
         var2 += CodedOutputStream.computeInt32Size(3, var11);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var12 = var1.job;
         var2 += CodedOutputStream.computeInt32Size(4, var12);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var13 = var1.name;
         var2 += CodedOutputStream.computeStringSize(5, var13);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var14 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(6, var14);
      }

      return var2;
   }

   public void doWriteTo(PT_GUARDIAN_ORDER var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.guardiandeal)) {
         Long var9 = var1.guardiandeal;
         if (var9 != null) {
            var2.writeInt64(1, var9);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var10 = var1.growtype;
         if (var10 != null) {
            var2.writeInt32(2, var10);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var11 = var1.secondgrowtype;
         if (var11 != null) {
            var2.writeInt32(3, var11);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var12 = var1.job;
         if (var12 != null) {
            var2.writeInt32(4, var12);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var13 = var1.name;
         if (var13 != null) {
            var2.writeString(5, var13);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var14 = var1.level;
         if (var14 != null) {
            var2.writeInt32(6, var14);
         }
      }

   }

   public void writeTo(PT_GUARDIAN_ORDER var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_GUARDIAN_ORDER readFrom(CodedInputStream var1) throws IOException {
      PT_GUARDIAN_ORDER var2 = new PT_GUARDIAN_ORDER();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.guardiandeal = var1.readInt64();
            } else if (var5 == 16) {
               var2.growtype = var1.readInt32();
            } else if (var5 == 24) {
               var2.secondgrowtype = var1.readInt32();
            } else if (var5 == 32) {
               var2.job = var1.readInt32();
            } else if (var5 == 42) {
               var2.name = var1.readString();
            } else if (var5 == 48) {
               var2.level = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_GUARDIAN_ORDER.class);
         return this.descriptor = var1;
      }
   }
}
