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
import java.util.ArrayList;
import java.util.List;

public class RES_CREATURE_COMMUNION_SLOT$$JProtoBufClass implements Codec<RES_CREATURE_COMMUNION_SLOT>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_CREATURE_COMMUNION_SLOT var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_CREATURE_COMMUNION_SLOT decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_CREATURE_COMMUNION_SLOT var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var8 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var8);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var9 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(2, var9);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.slotindex)) {
         Integer var10 = var1.slotindex;
         var2 += CodedOutputStream.computeInt32Size(3, var10);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.info)) {
         PT_CREATURE_COMMUNION var11 = var1.info;
         var2 += CodedConstant.computeSize(4, var11, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.currency)) {
         List var12 = var1.currency;
         var2 += CodedConstant.computeListSize(5, var12, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(RES_CREATURE_COMMUNION_SLOT var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var8 = var1.error;
         if (var8 != null) {
            var2.writeInt32(1, var8);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var9 = var1.type;
         if (var9 != null) {
            var2.writeInt32(2, var9);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.slotindex)) {
         Integer var10 = var1.slotindex;
         if (var10 != null) {
            var2.writeInt32(3, var10);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.info)) {
         PT_CREATURE_COMMUNION var11 = var1.info;
         if (var11 != null) {
            CodedConstant.writeObject(var2, 4, FieldType.OBJECT, var11, false);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.currency)) {
         List var12 = var1.currency;
         if (var12 != null) {
            CodedConstant.writeToList(var2, 5, FieldType.OBJECT, var12, false);
         }
      }

   }

   public void writeTo(RES_CREATURE_COMMUNION_SLOT var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_CREATURE_COMMUNION_SLOT readFrom(CodedInputStream var1) throws IOException {
      RES_CREATURE_COMMUNION_SLOT var2 = new RES_CREATURE_COMMUNION_SLOT();
      var2.currency = new ArrayList();

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
               var2.type = var1.readInt32();
            } else if (var5 == 24) {
               var2.slotindex = var1.readInt32();
            } else if (var5 == 34) {
               Codec var10 = ProtobufProxy.create(PT_CREATURE_COMMUNION.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.info = (PT_CREATURE_COMMUNION)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 42) {
               Codec var11 = ProtobufProxy.create(PT_MONEY_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var12 = var1.readRawVarint32();
               int var13 = var1.pushLimit(var12);
               if (var2.currency == null) {
                  var2.currency = new ArrayList();
               }

               var2.currency.add((PT_MONEY_ITEM)var11.readFrom(var1));
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_CREATURE_COMMUNION_SLOT.class);
         return this.descriptor = var1;
      }
   }
}
