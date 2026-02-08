package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.baidu.bjf.remoting.protobuf.code.CodedConstant;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.InvalidProtocolBufferException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class RES_BILING_KR_VERIFY$$JProtoBufClass implements Codec<RES_BILING_KR_VERIFY>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_BILING_KR_VERIFY var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_BILING_KR_VERIFY decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_BILING_KR_VERIFY var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var9 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var9);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.toyError)) {
         Integer var10 = var1.toyError;
         var2 += CodedOutputStream.computeInt32Size(2, var10);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.cera)) {
         Integer var11 = var1.cera;
         var2 += CodedOutputStream.computeInt32Size(3, var11);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.billingResult)) {
         PT_TOY_BILLING_VERIFY var12 = var1.billingResult;
         var2 += CodedConstant.computeSize(4, var12, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.goodsId)) {
         Integer var13 = var1.goodsId;
         var2 += CodedOutputStream.computeInt32Size(5, var13);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.info)) {
         PT_CONTENTS_REWARD_INFO var14 = var1.info;
         var2 += CodedConstant.computeSize(6, var14, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      return var2;
   }

   public void doWriteTo(RES_BILING_KR_VERIFY var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var9 = var1.error;
         if (var9 != null) {
            var2.writeInt32(1, var9);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.toyError)) {
         Integer var10 = var1.toyError;
         if (var10 != null) {
            var2.writeInt32(2, var10);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.cera)) {
         Integer var11 = var1.cera;
         if (var11 != null) {
            var2.writeInt32(3, var11);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.billingResult)) {
         PT_TOY_BILLING_VERIFY var12 = var1.billingResult;
         if (var12 != null) {
            CodedConstant.writeObject(var2, 4, FieldType.OBJECT, var12, false);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.goodsId)) {
         Integer var13 = var1.goodsId;
         if (var13 != null) {
            var2.writeInt32(5, var13);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.info)) {
         PT_CONTENTS_REWARD_INFO var14 = var1.info;
         if (var14 != null) {
            CodedConstant.writeObject(var2, 6, FieldType.OBJECT, var14, false);
         }
      }

   }

   public void writeTo(RES_BILING_KR_VERIFY var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_BILING_KR_VERIFY readFrom(CodedInputStream var1) throws IOException {
      RES_BILING_KR_VERIFY var2 = new RES_BILING_KR_VERIFY();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.error = var1.readInt32();
            } else if (var5 == 16) {
               var2.toyError = var1.readInt32();
            } else if (var5 == 24) {
               var2.cera = var1.readInt32();
            } else if (var5 == 34) {
               Codec var10 = ProtobufProxy.create(PT_TOY_BILLING_VERIFY.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.billingResult = (PT_TOY_BILLING_VERIFY)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 40) {
               var2.goodsId = var1.readInt32();
            } else if (var5 == 50) {
               Codec var11 = ProtobufProxy.create(PT_CONTENTS_REWARD_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var12 = var1.readRawVarint32();
               int var13 = var1.pushLimit(var12);
               var2.info = (PT_CONTENTS_REWARD_INFO)var11.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var13);
            } else {
               var1.skipField(var5);
            }
         }

         return var2;
      } catch (InvalidProtocolBufferException var8) {
         throw var8;
      } catch (IOException var9) {
         throw var9;
      }
   }

   public Descriptors.Descriptor getDescriptor() throws IOException {
      if (this.descriptor != null) {
         return this.descriptor;
      } else {
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_BILING_KR_VERIFY.class);
         return this.descriptor = var1;
      }
   }
}
