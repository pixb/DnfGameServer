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

public class PT_TOY_BILLING_VERIFY$$JProtoBufClass implements Codec<PT_TOY_BILLING_VERIFY>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_TOY_BILLING_VERIFY var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_TOY_BILLING_VERIFY decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_TOY_BILLING_VERIFY var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.stampToken)) {
         String var11 = var1.stampToken;
         var2 += CodedOutputStream.computeStringSize(1, var11);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.orderId)) {
         String var12 = var1.orderId;
         var2 += CodedOutputStream.computeStringSize(2, var12);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.productId)) {
         String var13 = var1.productId;
         var2 += CodedOutputStream.computeStringSize(3, var13);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.userId)) {
         String var14 = var1.userId;
         var2 += CodedOutputStream.computeStringSize(4, var14);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.characterId)) {
         String var15 = var1.characterId;
         var2 += CodedOutputStream.computeStringSize(5, var15);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.servicePayload)) {
         String var16 = var1.servicePayload;
         var2 += CodedOutputStream.computeStringSize(6, var16);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.marketType)) {
         String var17 = var1.marketType;
         var2 += CodedOutputStream.computeStringSize(7, var17);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.purchaseType)) {
         String var18 = var1.purchaseType;
         var2 += CodedOutputStream.computeStringSize(8, var18);
      }

      return var2;
   }

   public void doWriteTo(PT_TOY_BILLING_VERIFY var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.stampToken)) {
         String var11 = var1.stampToken;
         if (var11 != null) {
            var2.writeString(1, var11);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.orderId)) {
         String var12 = var1.orderId;
         if (var12 != null) {
            var2.writeString(2, var12);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.productId)) {
         String var13 = var1.productId;
         if (var13 != null) {
            var2.writeString(3, var13);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.userId)) {
         String var14 = var1.userId;
         if (var14 != null) {
            var2.writeString(4, var14);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.characterId)) {
         String var15 = var1.characterId;
         if (var15 != null) {
            var2.writeString(5, var15);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.servicePayload)) {
         String var16 = var1.servicePayload;
         if (var16 != null) {
            var2.writeString(6, var16);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.marketType)) {
         String var17 = var1.marketType;
         if (var17 != null) {
            var2.writeString(7, var17);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.purchaseType)) {
         String var18 = var1.purchaseType;
         if (var18 != null) {
            var2.writeString(8, var18);
         }
      }

   }

   public void writeTo(PT_TOY_BILLING_VERIFY var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_TOY_BILLING_VERIFY readFrom(CodedInputStream var1) throws IOException {
      PT_TOY_BILLING_VERIFY var2 = new PT_TOY_BILLING_VERIFY();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 10) {
               var2.stampToken = var1.readString();
            } else if (var5 == 18) {
               var2.orderId = var1.readString();
            } else if (var5 == 26) {
               var2.productId = var1.readString();
            } else if (var5 == 34) {
               var2.userId = var1.readString();
            } else if (var5 == 42) {
               var2.characterId = var1.readString();
            } else if (var5 == 50) {
               var2.servicePayload = var1.readString();
            } else if (var5 == 58) {
               var2.marketType = var1.readString();
            } else if (var5 == 66) {
               var2.purchaseType = var1.readString();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_TOY_BILLING_VERIFY.class);
         return this.descriptor = var1;
      }
   }
}
