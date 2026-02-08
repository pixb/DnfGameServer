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

public class NOTIFY_GUILD_STRUCTURE_LIST$$JProtoBufClass implements Codec<NOTIFY_GUILD_STRUCTURE_LIST>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(NOTIFY_GUILD_STRUCTURE_LIST var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public NOTIFY_GUILD_STRUCTURE_LIST decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(NOTIFY_GUILD_STRUCTURE_LIST var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var8 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var8);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.arrangestructure)) {
         List var9 = var1.arrangestructure;
         var2 += CodedConstant.computeListSize(2, var9, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.arrangewarehouse)) {
         List var10 = var1.arrangewarehouse;
         var2 += CodedConstant.computeListSize(3, var10, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.removestructrue)) {
         List var11 = var1.removestructrue;
         var2 += CodedConstant.computeListSize(4, var11, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.removewarehouse)) {
         List var12 = var1.removewarehouse;
         var2 += CodedConstant.computeListSize(5, var12, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(NOTIFY_GUILD_STRUCTURE_LIST var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var8 = var1.error;
         if (var8 != null) {
            var2.writeInt32(1, var8);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.arrangestructure)) {
         List var9 = var1.arrangestructure;
         if (var9 != null) {
            CodedConstant.writeToList(var2, 2, FieldType.OBJECT, var9, false);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.arrangewarehouse)) {
         List var10 = var1.arrangewarehouse;
         if (var10 != null) {
            CodedConstant.writeToList(var2, 3, FieldType.OBJECT, var10, false);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.removestructrue)) {
         List var11 = var1.removestructrue;
         if (var11 != null) {
            CodedConstant.writeToList(var2, 4, FieldType.OBJECT, var11, false);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.removewarehouse)) {
         List var12 = var1.removewarehouse;
         if (var12 != null) {
            CodedConstant.writeToList(var2, 5, FieldType.OBJECT, var12, false);
         }
      }

   }

   public void writeTo(NOTIFY_GUILD_STRUCTURE_LIST var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public NOTIFY_GUILD_STRUCTURE_LIST readFrom(CodedInputStream var1) throws IOException {
      NOTIFY_GUILD_STRUCTURE_LIST var2 = new NOTIFY_GUILD_STRUCTURE_LIST();
      var2.arrangestructure = new ArrayList();
      var2.arrangewarehouse = new ArrayList();
      var2.removestructrue = new ArrayList();
      var2.removewarehouse = new ArrayList();

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
            } else if (var5 == 18) {
               Codec var10 = ProtobufProxy.create(PT_GUILD_STRUCTURE_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.arrangestructure == null) {
                  var2.arrangestructure = new ArrayList();
               }

               var2.arrangestructure.add((PT_GUILD_STRUCTURE_INFO)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 26) {
               Codec var11 = ProtobufProxy.create(PT_WAREHOUSE_STRUCTURE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var14 = var1.readRawVarint32();
               int var17 = var1.pushLimit(var14);
               if (var2.arrangewarehouse == null) {
                  var2.arrangewarehouse = new ArrayList();
               }

               var2.arrangewarehouse.add((PT_WAREHOUSE_STRUCTURE)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var17);
            } else if (var5 == 34) {
               Codec var12 = ProtobufProxy.create(PT_GUILD_STRUCTURE_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var15 = var1.readRawVarint32();
               int var18 = var1.pushLimit(var15);
               if (var2.removestructrue == null) {
                  var2.removestructrue = new ArrayList();
               }

               var2.removestructrue.add((PT_GUILD_STRUCTURE_INFO)var12.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var18);
            } else if (var5 == 42) {
               Codec var13 = ProtobufProxy.create(PT_WAREHOUSE_STRUCTURE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var16 = var1.readRawVarint32();
               int var19 = var1.pushLimit(var16);
               if (var2.removewarehouse == null) {
                  var2.removewarehouse = new ArrayList();
               }

               var2.removewarehouse.add((PT_WAREHOUSE_STRUCTURE)var13.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var19);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(NOTIFY_GUILD_STRUCTURE_LIST.class);
         return this.descriptor = var1;
      }
   }
}
