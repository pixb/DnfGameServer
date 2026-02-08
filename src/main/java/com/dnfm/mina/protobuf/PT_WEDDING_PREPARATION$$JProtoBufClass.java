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

public class PT_WEDDING_PREPARATION$$JProtoBufClass implements Codec<PT_WEDDING_PREPARATION>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_WEDDING_PREPARATION var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_WEDDING_PREPARATION decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_WEDDING_PREPARATION var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var8 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var8);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.theme)) {
         PT_WEDDING_THEME var9 = var1.theme;
         var2 += CodedConstant.computeSize(2, var9, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.paymentamount)) {
         Integer var10 = var1.paymentamount;
         var2 += CodedOutputStream.computeInt32Size(3, var10);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.paymentgold)) {
         Integer var11 = var1.paymentgold;
         var2 += CodedOutputStream.computeInt32Size(4, var11);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.paymenttera)) {
         Integer var12 = var1.paymenttera;
         var2 += CodedOutputStream.computeInt32Size(5, var12);
      }

      return var2;
   }

   public void doWriteTo(PT_WEDDING_PREPARATION var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var8 = var1.charguid;
         if (var8 != null) {
            var2.writeUInt64(1, var8);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.theme)) {
         PT_WEDDING_THEME var9 = var1.theme;
         if (var9 != null) {
            CodedConstant.writeObject(var2, 2, FieldType.OBJECT, var9, false);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.paymentamount)) {
         Integer var10 = var1.paymentamount;
         if (var10 != null) {
            var2.writeInt32(3, var10);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.paymentgold)) {
         Integer var11 = var1.paymentgold;
         if (var11 != null) {
            var2.writeInt32(4, var11);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.paymenttera)) {
         Integer var12 = var1.paymenttera;
         if (var12 != null) {
            var2.writeInt32(5, var12);
         }
      }

   }

   public void writeTo(PT_WEDDING_PREPARATION var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_WEDDING_PREPARATION readFrom(CodedInputStream var1) throws IOException {
      PT_WEDDING_PREPARATION var2 = new PT_WEDDING_PREPARATION();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.charguid = var1.readUInt64();
            } else if (var5 == 18) {
               Codec var10 = ProtobufProxy.create(PT_WEDDING_THEME.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.theme = (PT_WEDDING_THEME)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 24) {
               var2.paymentamount = var1.readInt32();
            } else if (var5 == 32) {
               var2.paymentgold = var1.readInt32();
            } else if (var5 == 40) {
               var2.paymenttera = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_WEDDING_PREPARATION.class);
         return this.descriptor = var1;
      }
   }
}
