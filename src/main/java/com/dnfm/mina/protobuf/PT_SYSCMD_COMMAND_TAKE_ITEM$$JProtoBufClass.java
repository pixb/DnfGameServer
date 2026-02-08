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

public class PT_SYSCMD_COMMAND_TAKE_ITEM$$JProtoBufClass implements Codec<PT_SYSCMD_COMMAND_TAKE_ITEM>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_SYSCMD_COMMAND_TAKE_ITEM var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_SYSCMD_COMMAND_TAKE_ITEM decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_SYSCMD_COMMAND_TAKE_ITEM var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.gold)) {
         Integer var11 = var1.gold;
         var2 += CodedOutputStream.computeInt32Size(1, var11);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.tera)) {
         Integer var12 = var1.tera;
         var2 += CodedOutputStream.computeInt32Size(2, var12);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.cera)) {
         Integer var13 = var1.cera;
         var2 += CodedOutputStream.computeInt32Size(3, var13);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.items)) {
         List var14 = var1.items;
         var2 += CodedConstant.computeListSize(4, var14, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.itemreason)) {
         String var15 = var1.itemreason;
         var2 += CodedOutputStream.computeStringSize(5, var15);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.itemsubreason)) {
         Integer var16 = var1.itemsubreason;
         var2 += CodedOutputStream.computeInt32Size(6, var16);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.moneyreason)) {
         String var17 = var1.moneyreason;
         var2 += CodedOutputStream.computeStringSize(7, var17);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.moneysubreason)) {
         Integer var18 = var1.moneysubreason;
         var2 += CodedOutputStream.computeInt32Size(8, var18);
      }

      return var2;
   }

   public void doWriteTo(PT_SYSCMD_COMMAND_TAKE_ITEM var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.gold)) {
         Integer var11 = var1.gold;
         if (var11 != null) {
            var2.writeInt32(1, var11);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.tera)) {
         Integer var12 = var1.tera;
         if (var12 != null) {
            var2.writeInt32(2, var12);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.cera)) {
         Integer var13 = var1.cera;
         if (var13 != null) {
            var2.writeInt32(3, var13);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.items)) {
         List var14 = var1.items;
         if (var14 != null) {
            CodedConstant.writeToList(var2, 4, FieldType.OBJECT, var14, false);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.itemreason)) {
         String var15 = var1.itemreason;
         if (var15 != null) {
            var2.writeString(5, var15);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.itemsubreason)) {
         Integer var16 = var1.itemsubreason;
         if (var16 != null) {
            var2.writeInt32(6, var16);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.moneyreason)) {
         String var17 = var1.moneyreason;
         if (var17 != null) {
            var2.writeString(7, var17);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.moneysubreason)) {
         Integer var18 = var1.moneysubreason;
         if (var18 != null) {
            var2.writeInt32(8, var18);
         }
      }

   }

   public void writeTo(PT_SYSCMD_COMMAND_TAKE_ITEM var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_SYSCMD_COMMAND_TAKE_ITEM readFrom(CodedInputStream var1) throws IOException {
      PT_SYSCMD_COMMAND_TAKE_ITEM var2 = new PT_SYSCMD_COMMAND_TAKE_ITEM();
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
               var2.gold = var1.readInt32();
            } else if (var5 == 16) {
               var2.tera = var1.readInt32();
            } else if (var5 == 24) {
               var2.cera = var1.readInt32();
            } else if (var5 == 34) {
               Codec var10 = ProtobufProxy.create(PT_SYSCMD_ITEMJSON.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.items == null) {
                  var2.items = new ArrayList();
               }

               var2.items.add((PT_SYSCMD_ITEMJSON)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 42) {
               var2.itemreason = var1.readString();
            } else if (var5 == 48) {
               var2.itemsubreason = var1.readInt32();
            } else if (var5 == 58) {
               var2.moneyreason = var1.readString();
            } else if (var5 == 64) {
               var2.moneysubreason = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_SYSCMD_COMMAND_TAKE_ITEM.class);
         return this.descriptor = var1;
      }
   }
}
