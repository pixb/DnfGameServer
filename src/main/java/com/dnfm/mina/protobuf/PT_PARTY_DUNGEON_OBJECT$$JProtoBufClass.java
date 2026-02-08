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

public class PT_PARTY_DUNGEON_OBJECT$$JProtoBufClass implements Codec<PT_PARTY_DUNGEON_OBJECT>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_PARTY_DUNGEON_OBJECT var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_PARTY_DUNGEON_OBJECT decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_PARTY_DUNGEON_OBJECT var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Integer var10 = var1.guid;
         var2 += CodedOutputStream.computeInt32Size(1, var10);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var11 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(2, var11);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var12 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(3, var12);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.teamtype)) {
         Integer var13 = var1.teamtype;
         var2 += CodedOutputStream.computeInt32Size(4, var13);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var14 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(5, var14);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.golds)) {
         List var15 = var1.golds;
         var2 += CodedConstant.computeListSize(6, var15, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.items)) {
         List var16 = var1.items;
         var2 += CodedConstant.computeListSize(7, var16, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(PT_PARTY_DUNGEON_OBJECT var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Integer var10 = var1.guid;
         if (var10 != null) {
            var2.writeInt32(1, var10);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var11 = var1.index;
         if (var11 != null) {
            var2.writeInt32(2, var11);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var12 = var1.level;
         if (var12 != null) {
            var2.writeInt32(3, var12);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.teamtype)) {
         Integer var13 = var1.teamtype;
         if (var13 != null) {
            var2.writeInt32(4, var13);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var14 = var1.type;
         if (var14 != null) {
            var2.writeInt32(5, var14);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.golds)) {
         List var15 = var1.golds;
         if (var15 != null) {
            CodedConstant.writeToList(var2, 6, FieldType.OBJECT, var15, false);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.items)) {
         List var16 = var1.items;
         if (var16 != null) {
            CodedConstant.writeToList(var2, 7, FieldType.OBJECT, var16, false);
         }
      }

   }

   public void writeTo(PT_PARTY_DUNGEON_OBJECT var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_PARTY_DUNGEON_OBJECT readFrom(CodedInputStream var1) throws IOException {
      PT_PARTY_DUNGEON_OBJECT var2 = new PT_PARTY_DUNGEON_OBJECT();
      var2.golds = new ArrayList();
      var2.items = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.guid = var1.readInt32();
            } else if (var5 == 16) {
               var2.index = var1.readInt32();
            } else if (var5 == 24) {
               var2.level = var1.readInt32();
            } else if (var5 == 32) {
               var2.teamtype = var1.readInt32();
            } else if (var5 == 40) {
               var2.type = var1.readInt32();
            } else if (var5 == 50) {
               Codec var10 = ProtobufProxy.create(PT_DROP_OBJECT_GOLD.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.golds == null) {
                  var2.golds = new ArrayList();
               }

               var2.golds.add((PT_DROP_OBJECT_GOLD)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 58) {
               Codec var11 = ProtobufProxy.create(PT_DROP_OBJECT_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var12 = var1.readRawVarint32();
               int var13 = var1.pushLimit(var12);
               if (var2.items == null) {
                  var2.items = new ArrayList();
               }

               var2.items.add((PT_DROP_OBJECT_ITEM)var11.readFrom(var1));
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_PARTY_DUNGEON_OBJECT.class);
         return this.descriptor = var1;
      }
   }
}
