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

public class PT_CONTENTS_REWARD_INFO$$JProtoBufClass implements Codec<PT_CONTENTS_REWARD_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_CONTENTS_REWARD_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_CONTENTS_REWARD_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_CONTENTS_REWARD_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.items)) {
         PT_ITEM_REWARD_INFO var9 = var1.items;
         var2 += CodedConstant.computeSize(1, var9, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.currency)) {
         PT_CURRENCY_REWARD_INFO var10 = var1.currency;
         var2 += CodedConstant.computeSize(2, var10, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.paymentcurrency)) {
         PT_CURRENCY_REWARD_INFO var11 = var1.paymentcurrency;
         var2 += CodedConstant.computeSize(3, var11, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.ticket)) {
         PT_TICKET_REWARD_INFO var12 = var1.ticket;
         var2 += CodedConstant.computeSize(4, var12, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.postal)) {
         PT_POSTAL_REWARD_INFO var13 = var1.postal;
         var2 += CodedConstant.computeSize(5, var13, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.accountpostal)) {
         PT_POSTAL_REWARD_INFO var14 = var1.accountpostal;
         var2 += CodedConstant.computeSize(6, var14, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      return var2;
   }

   public void doWriteTo(PT_CONTENTS_REWARD_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.items)) {
         PT_ITEM_REWARD_INFO var9 = var1.items;
         if (var9 != null) {
            CodedConstant.writeObject(var2, 1, FieldType.OBJECT, var9, false);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.currency)) {
         PT_CURRENCY_REWARD_INFO var10 = var1.currency;
         if (var10 != null) {
            CodedConstant.writeObject(var2, 2, FieldType.OBJECT, var10, false);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.paymentcurrency)) {
         PT_CURRENCY_REWARD_INFO var11 = var1.paymentcurrency;
         if (var11 != null) {
            CodedConstant.writeObject(var2, 3, FieldType.OBJECT, var11, false);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.ticket)) {
         PT_TICKET_REWARD_INFO var12 = var1.ticket;
         if (var12 != null) {
            CodedConstant.writeObject(var2, 4, FieldType.OBJECT, var12, false);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.postal)) {
         PT_POSTAL_REWARD_INFO var13 = var1.postal;
         if (var13 != null) {
            CodedConstant.writeObject(var2, 5, FieldType.OBJECT, var13, false);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.accountpostal)) {
         PT_POSTAL_REWARD_INFO var14 = var1.accountpostal;
         if (var14 != null) {
            CodedConstant.writeObject(var2, 6, FieldType.OBJECT, var14, false);
         }
      }

   }

   public void writeTo(PT_CONTENTS_REWARD_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_CONTENTS_REWARD_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_CONTENTS_REWARD_INFO var2 = new PT_CONTENTS_REWARD_INFO();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 10) {
               Codec var10 = ProtobufProxy.create(PT_ITEM_REWARD_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.items = (PT_ITEM_REWARD_INFO)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 18) {
               Codec var11 = ProtobufProxy.create(PT_CURRENCY_REWARD_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var16 = var1.readRawVarint32();
               int var21 = var1.pushLimit(var16);
               var2.currency = (PT_CURRENCY_REWARD_INFO)var11.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var21);
            } else if (var5 == 26) {
               Codec var12 = ProtobufProxy.create(PT_CURRENCY_REWARD_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var17 = var1.readRawVarint32();
               int var22 = var1.pushLimit(var17);
               var2.paymentcurrency = (PT_CURRENCY_REWARD_INFO)var12.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var22);
            } else if (var5 == 34) {
               Codec var13 = ProtobufProxy.create(PT_TICKET_REWARD_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var18 = var1.readRawVarint32();
               int var23 = var1.pushLimit(var18);
               var2.ticket = (PT_TICKET_REWARD_INFO)var13.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var23);
            } else if (var5 == 42) {
               Codec var14 = ProtobufProxy.create(PT_POSTAL_REWARD_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var19 = var1.readRawVarint32();
               int var24 = var1.pushLimit(var19);
               var2.postal = (PT_POSTAL_REWARD_INFO)var14.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var24);
            } else if (var5 == 50) {
               Codec var15 = ProtobufProxy.create(PT_POSTAL_REWARD_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var20 = var1.readRawVarint32();
               int var25 = var1.pushLimit(var20);
               var2.accountpostal = (PT_POSTAL_REWARD_INFO)var15.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var25);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_CONTENTS_REWARD_INFO.class);
         return this.descriptor = var1;
      }
   }
}
