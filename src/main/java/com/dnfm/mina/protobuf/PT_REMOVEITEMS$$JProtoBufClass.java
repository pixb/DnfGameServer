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

public class PT_REMOVEITEMS$$JProtoBufClass implements Codec<PT_REMOVEITEMS>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_REMOVEITEMS var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_REMOVEITEMS decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_REMOVEITEMS var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.materialitems)) {
         List var10 = var1.materialitems;
         var2 += CodedConstant.computeListSize(1, var10, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.emblemitems)) {
         List var11 = var1.emblemitems;
         var2 += CodedConstant.computeListSize(2, var11, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.carditems)) {
         List var12 = var1.carditems;
         var2 += CodedConstant.computeListSize(3, var12, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.consumeitems)) {
         List var13 = var1.consumeitems;
         var2 += CodedConstant.computeListSize(4, var13, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.epicpieceitems)) {
         List var14 = var1.epicpieceitems;
         var2 += CodedConstant.computeListSize(5, var14, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.crackitems)) {
         List var15 = var1.crackitems;
         var2 += CodedConstant.computeListSize(6, var15, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.bookmarkitems)) {
         List var16 = var1.bookmarkitems;
         var2 += CodedConstant.computeListSize(7, var16, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(PT_REMOVEITEMS var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.materialitems)) {
         List var10 = var1.materialitems;
         if (var10 != null) {
            CodedConstant.writeToList(var2, 1, FieldType.OBJECT, var10, false);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.emblemitems)) {
         List var11 = var1.emblemitems;
         if (var11 != null) {
            CodedConstant.writeToList(var2, 2, FieldType.OBJECT, var11, false);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.carditems)) {
         List var12 = var1.carditems;
         if (var12 != null) {
            CodedConstant.writeToList(var2, 3, FieldType.OBJECT, var12, false);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.consumeitems)) {
         List var13 = var1.consumeitems;
         if (var13 != null) {
            CodedConstant.writeToList(var2, 4, FieldType.OBJECT, var13, false);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.epicpieceitems)) {
         List var14 = var1.epicpieceitems;
         if (var14 != null) {
            CodedConstant.writeToList(var2, 5, FieldType.OBJECT, var14, false);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.crackitems)) {
         List var15 = var1.crackitems;
         if (var15 != null) {
            CodedConstant.writeToList(var2, 6, FieldType.OBJECT, var15, false);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.bookmarkitems)) {
         List var16 = var1.bookmarkitems;
         if (var16 != null) {
            CodedConstant.writeToList(var2, 7, FieldType.OBJECT, var16, false);
         }
      }

   }

   public void writeTo(PT_REMOVEITEMS var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_REMOVEITEMS readFrom(CodedInputStream var1) throws IOException {
      PT_REMOVEITEMS var2 = new PT_REMOVEITEMS();
      var2.materialitems = new ArrayList();
      var2.emblemitems = new ArrayList();
      var2.carditems = new ArrayList();
      var2.consumeitems = new ArrayList();
      var2.epicpieceitems = new ArrayList();
      var2.crackitems = new ArrayList();
      var2.bookmarkitems = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 10) {
               Codec var10 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.materialitems == null) {
                  var2.materialitems = new ArrayList();
               }

               var2.materialitems.add((PT_STACKABLE)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 18) {
               Codec var11 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var17 = var1.readRawVarint32();
               int var23 = var1.pushLimit(var17);
               if (var2.emblemitems == null) {
                  var2.emblemitems = new ArrayList();
               }

               var2.emblemitems.add((PT_STACKABLE)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var23);
            } else if (var5 == 26) {
               Codec var12 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var18 = var1.readRawVarint32();
               int var24 = var1.pushLimit(var18);
               if (var2.carditems == null) {
                  var2.carditems = new ArrayList();
               }

               var2.carditems.add((PT_STACKABLE)var12.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var24);
            } else if (var5 == 34) {
               Codec var13 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var19 = var1.readRawVarint32();
               int var25 = var1.pushLimit(var19);
               if (var2.consumeitems == null) {
                  var2.consumeitems = new ArrayList();
               }

               var2.consumeitems.add((PT_STACKABLE)var13.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var25);
            } else if (var5 == 42) {
               Codec var14 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var20 = var1.readRawVarint32();
               int var26 = var1.pushLimit(var20);
               if (var2.epicpieceitems == null) {
                  var2.epicpieceitems = new ArrayList();
               }

               var2.epicpieceitems.add((PT_STACKABLE)var14.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var26);
            } else if (var5 == 50) {
               Codec var15 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var21 = var1.readRawVarint32();
               int var27 = var1.pushLimit(var21);
               if (var2.crackitems == null) {
                  var2.crackitems = new ArrayList();
               }

               var2.crackitems.add((PT_STACKABLE)var15.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var27);
            } else if (var5 == 58) {
               Codec var16 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var22 = var1.readRawVarint32();
               int var28 = var1.pushLimit(var22);
               if (var2.bookmarkitems == null) {
                  var2.bookmarkitems = new ArrayList();
               }

               var2.bookmarkitems.add((PT_STACKABLE)var16.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var28);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_REMOVEITEMS.class);
         return this.descriptor = var1;
      }
   }
}
