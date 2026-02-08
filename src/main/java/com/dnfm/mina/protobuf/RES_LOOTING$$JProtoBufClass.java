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

public class RES_LOOTING$$JProtoBufClass implements Codec<RES_LOOTING>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_LOOTING var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_LOOTING decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_LOOTING var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var12 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var12);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.earnexp)) {
         Integer var13 = var1.earnexp;
         var2 += CodedOutputStream.computeInt32Size(2, var13);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.earnitemcount)) {
         Integer var14 = var1.earnitemcount;
         var2 += CodedOutputStream.computeInt32Size(3, var14);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.currency)) {
         List var15 = var1.currency;
         var2 += CodedConstant.computeListSize(4, var15, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.accountcurrency)) {
         List var16 = var1.accountcurrency;
         var2 += CodedConstant.computeListSize(5, var16, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.removeitems)) {
         PT_REMOVEITEMS var17 = var1.removeitems;
         var2 += CodedConstant.computeSize(6, var17, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.items)) {
         PT_ITEMS var18 = var1.items;
         var2 += CodedConstant.computeSize(7, var18, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.characterdailygain)) {
         List var19 = var1.characterdailygain;
         var2 += CodedConstant.computeListSize(8, var19, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.accountdailygain)) {
         List var20 = var1.accountdailygain;
         var2 += CodedConstant.computeListSize(9, var20, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(RES_LOOTING var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var12 = var1.error;
         if (var12 != null) {
            var2.writeInt32(1, var12);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.earnexp)) {
         Integer var13 = var1.earnexp;
         if (var13 != null) {
            var2.writeInt32(2, var13);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.earnitemcount)) {
         Integer var14 = var1.earnitemcount;
         if (var14 != null) {
            var2.writeInt32(3, var14);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.currency)) {
         List var15 = var1.currency;
         if (var15 != null) {
            CodedConstant.writeToList(var2, 4, FieldType.OBJECT, var15, false);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.accountcurrency)) {
         List var16 = var1.accountcurrency;
         if (var16 != null) {
            CodedConstant.writeToList(var2, 5, FieldType.OBJECT, var16, false);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.removeitems)) {
         PT_REMOVEITEMS var17 = var1.removeitems;
         if (var17 != null) {
            CodedConstant.writeObject(var2, 6, FieldType.OBJECT, var17, false);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.items)) {
         PT_ITEMS var18 = var1.items;
         if (var18 != null) {
            CodedConstant.writeObject(var2, 7, FieldType.OBJECT, var18, false);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.characterdailygain)) {
         List var19 = var1.characterdailygain;
         if (var19 != null) {
            CodedConstant.writeToList(var2, 8, FieldType.OBJECT, var19, false);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.accountdailygain)) {
         List var20 = var1.accountdailygain;
         if (var20 != null) {
            CodedConstant.writeToList(var2, 9, FieldType.OBJECT, var20, false);
         }
      }

   }

   public void writeTo(RES_LOOTING var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_LOOTING readFrom(CodedInputStream var1) throws IOException {
      RES_LOOTING var2 = new RES_LOOTING();
      var2.currency = new ArrayList();
      var2.accountcurrency = new ArrayList();
      var2.characterdailygain = new ArrayList();
      var2.accountdailygain = new ArrayList();

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
               var2.earnexp = var1.readInt32();
            } else if (var5 == 24) {
               var2.earnitemcount = var1.readInt32();
            } else if (var5 == 34) {
               Codec var10 = ProtobufProxy.create(PT_MONEY_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.currency == null) {
                  var2.currency = new ArrayList();
               }

               var2.currency.add((PT_MONEY_ITEM)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 42) {
               Codec var11 = ProtobufProxy.create(PT_MONEY_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var16 = var1.readRawVarint32();
               int var21 = var1.pushLimit(var16);
               if (var2.accountcurrency == null) {
                  var2.accountcurrency = new ArrayList();
               }

               var2.accountcurrency.add((PT_MONEY_ITEM)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var21);
            } else if (var5 == 50) {
               Codec var12 = ProtobufProxy.create(PT_REMOVEITEMS.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var17 = var1.readRawVarint32();
               int var22 = var1.pushLimit(var17);
               var2.removeitems = (PT_REMOVEITEMS)var12.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var22);
            } else if (var5 == 58) {
               Codec var13 = ProtobufProxy.create(PT_ITEMS.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var18 = var1.readRawVarint32();
               int var23 = var1.pushLimit(var18);
               var2.items = (PT_ITEMS)var13.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var23);
            } else if (var5 == 66) {
               Codec var14 = ProtobufProxy.create(PT_CURRENCY_DAILY_GAIN.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var19 = var1.readRawVarint32();
               int var24 = var1.pushLimit(var19);
               if (var2.characterdailygain == null) {
                  var2.characterdailygain = new ArrayList();
               }

               var2.characterdailygain.add((PT_CURRENCY_DAILY_GAIN)var14.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var24);
            } else if (var5 == 74) {
               Codec var15 = ProtobufProxy.create(PT_CURRENCY_DAILY_GAIN.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var20 = var1.readRawVarint32();
               int var25 = var1.pushLimit(var20);
               if (var2.accountdailygain == null) {
                  var2.accountdailygain = new ArrayList();
               }

               var2.accountdailygain.add((PT_CURRENCY_DAILY_GAIN)var15.readFrom(var1));
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_LOOTING.class);
         return this.descriptor = var1;
      }
   }
}
