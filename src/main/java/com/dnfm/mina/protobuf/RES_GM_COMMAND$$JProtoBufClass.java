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

public class RES_GM_COMMAND$$JProtoBufClass implements Codec<RES_GM_COMMAND>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_GM_COMMAND var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_GM_COMMAND decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_GM_COMMAND var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var11 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var11);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var12 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(2, var12);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.list)) {
         List var13 = var1.list;
         var2 += CodedConstant.computeListSize(3, var13, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.time)) {
         String var14 = var1.time;
         var2 += CodedOutputStream.computeStringSize(4, var14);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.gguid)) {
         Long var15 = var1.gguid;
         var2 += CodedOutputStream.computeUInt64Size(5, var15);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var16 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(6, var16);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.expiretime)) {
         Long var17 = var1.expiretime;
         var2 += CodedOutputStream.computeInt64Size(7, var17);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.items)) {
         List var18 = var1.items;
         var2 += CodedConstant.computeListSize(8, var18, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(RES_GM_COMMAND var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var11 = var1.error;
         if (var11 != null) {
            var2.writeInt32(1, var11);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var12 = var1.type;
         if (var12 != null) {
            var2.writeInt32(2, var12);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.list)) {
         List var13 = var1.list;
         if (var13 != null) {
            CodedConstant.writeToList(var2, 3, FieldType.OBJECT, var13, false);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.time)) {
         String var14 = var1.time;
         if (var14 != null) {
            var2.writeString(4, var14);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.gguid)) {
         Long var15 = var1.gguid;
         if (var15 != null) {
            var2.writeUInt64(5, var15);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var16 = var1.level;
         if (var16 != null) {
            var2.writeInt32(6, var16);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.expiretime)) {
         Long var17 = var1.expiretime;
         if (var17 != null) {
            var2.writeInt64(7, var17);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.items)) {
         List var18 = var1.items;
         if (var18 != null) {
            CodedConstant.writeToList(var2, 8, FieldType.OBJECT, var18, false);
         }
      }

   }

   public void writeTo(RES_GM_COMMAND var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_GM_COMMAND readFrom(CodedInputStream var1) throws IOException {
      RES_GM_COMMAND var2 = new RES_GM_COMMAND();
      var2.list = new ArrayList();
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
               var2.error = var1.readInt32();
            } else if (var5 == 16) {
               var2.type = var1.readInt32();
            } else if (var5 == 26) {
               Codec var10 = ProtobufProxy.create(PT_GM_COMMAND_ARG.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.list == null) {
                  var2.list = new ArrayList();
               }

               var2.list.add((PT_GM_COMMAND_ARG)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 34) {
               var2.time = var1.readString();
            } else if (var5 == 40) {
               var2.gguid = var1.readUInt64();
            } else if (var5 == 48) {
               var2.level = var1.readInt32();
            } else if (var5 == 56) {
               var2.expiretime = var1.readInt64();
            } else if (var5 == 66) {
               Codec var11 = ProtobufProxy.create(PT_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var12 = var1.readRawVarint32();
               int var13 = var1.pushLimit(var12);
               if (var2.items == null) {
                  var2.items = new ArrayList();
               }

               var2.items.add((PT_ITEM)var11.readFrom(var1));
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_GM_COMMAND.class);
         return this.descriptor = var1;
      }
   }
}
