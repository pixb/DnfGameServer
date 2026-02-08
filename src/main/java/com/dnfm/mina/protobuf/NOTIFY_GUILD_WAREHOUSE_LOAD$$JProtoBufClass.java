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

public class NOTIFY_GUILD_WAREHOUSE_LOAD$$JProtoBufClass implements Codec<NOTIFY_GUILD_WAREHOUSE_LOAD>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(NOTIFY_GUILD_WAREHOUSE_LOAD var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public NOTIFY_GUILD_WAREHOUSE_LOAD decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(NOTIFY_GUILD_WAREHOUSE_LOAD var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var8 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var8);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.notitype)) {
         ENUM_GUILD_WAREHOUSE_TYPES.T var9 = var1.notitype;
         var2 += CodedOutputStream.computeEnumSize(2, ((ENUM_GUILD_WAREHOUSE_TYPES.T)var9).value());
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.inventory)) {
         PT_GUILD_INVENTORY_LIST var10 = var1.inventory;
         var2 += CodedConstant.computeSize(3, var10, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.structurelist)) {
         List var11 = var1.structurelist;
         var2 += CodedConstant.computeListSize(4, var11, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.npclist)) {
         List var12 = var1.npclist;
         var2 += CodedConstant.computeListSize(5, var12, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(NOTIFY_GUILD_WAREHOUSE_LOAD var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var8 = var1.error;
         if (var8 != null) {
            var2.writeInt32(1, var8);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.notitype)) {
         ENUM_GUILD_WAREHOUSE_TYPES.T var9 = var1.notitype;
         if (var9 != null) {
            var2.writeEnum(2, ((ENUM_GUILD_WAREHOUSE_TYPES.T)var9).value());
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.inventory)) {
         PT_GUILD_INVENTORY_LIST var10 = var1.inventory;
         if (var10 != null) {
            CodedConstant.writeObject(var2, 3, FieldType.OBJECT, var10, false);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.structurelist)) {
         List var11 = var1.structurelist;
         if (var11 != null) {
            CodedConstant.writeToList(var2, 4, FieldType.OBJECT, var11, false);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.npclist)) {
         List var12 = var1.npclist;
         if (var12 != null) {
            CodedConstant.writeToList(var2, 5, FieldType.OBJECT, var12, false);
         }
      }

   }

   public void writeTo(NOTIFY_GUILD_WAREHOUSE_LOAD var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public NOTIFY_GUILD_WAREHOUSE_LOAD readFrom(CodedInputStream var1) throws IOException {
      NOTIFY_GUILD_WAREHOUSE_LOAD var2 = new NOTIFY_GUILD_WAREHOUSE_LOAD();
      var2.structurelist = new ArrayList();
      var2.npclist = new ArrayList();
      var2.notitype = (ENUM_GUILD_WAREHOUSE_TYPES.T)CodedConstant.getEnumValue(ENUM_GUILD_WAREHOUSE_TYPES.T.class, ENUM_GUILD_WAREHOUSE_TYPES.T.values()[0].name());

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
               var2.notitype = (ENUM_GUILD_WAREHOUSE_TYPES.T)CodedConstant.getEnumValue(ENUM_GUILD_WAREHOUSE_TYPES.T.class, CodedConstant.getEnumName(ENUM_GUILD_WAREHOUSE_TYPES.T.values(), var1.readEnum()));
            } else if (var5 == 26) {
               Codec var10 = ProtobufProxy.create(PT_GUILD_INVENTORY_LIST.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.inventory = (PT_GUILD_INVENTORY_LIST)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 34) {
               Codec var11 = ProtobufProxy.create(PT_WAREHOUSE_STRUCTURE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var13 = var1.readRawVarint32();
               int var15 = var1.pushLimit(var13);
               if (var2.structurelist == null) {
                  var2.structurelist = new ArrayList();
               }

               var2.structurelist.add((PT_WAREHOUSE_STRUCTURE)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var15);
            } else if (var5 == 42) {
               Codec var12 = ProtobufProxy.create(PT_WAREHOUSE_NPC.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var14 = var1.readRawVarint32();
               int var16 = var1.pushLimit(var14);
               if (var2.npclist == null) {
                  var2.npclist = new ArrayList();
               }

               var2.npclist.add((PT_WAREHOUSE_NPC)var12.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var16);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(NOTIFY_GUILD_WAREHOUSE_LOAD.class);
         return this.descriptor = var1;
      }
   }
}
