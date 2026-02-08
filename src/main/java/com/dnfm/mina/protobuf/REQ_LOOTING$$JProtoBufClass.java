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

public class REQ_LOOTING$$JProtoBufClass implements Codec<REQ_LOOTING>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_LOOTING var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_LOOTING decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_LOOTING var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.authkey)) {
         String var11 = var1.authkey;
         var2 += CodedOutputStream.computeStringSize(1, var11);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.loots)) {
         List var12 = var1.loots;
         var2 += CodedConstant.computeListSize(2, var12, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.stage)) {
         Integer var13 = var1.stage;
         var2 += CodedOutputStream.computeInt32Size(3, var13);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.matchingguid)) {
         Long var14 = var1.matchingguid;
         var2 += CodedOutputStream.computeUInt64Size(4, var14);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.time)) {
         String var15 = var1.time;
         var2 += CodedOutputStream.computeStringSize(5, var15);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var16 = var1.dungeonguid;
         var2 += CodedOutputStream.computeUInt64Size(6, var16);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.consume)) {
         List var17 = var1.consume;
         var2 += CodedConstant.computeListSize(7, var17, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.frame)) {
         Integer var18 = var1.frame;
         var2 += CodedOutputStream.computeInt32Size(8, var18);
      }

      return var2;
   }

   public void doWriteTo(REQ_LOOTING var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.authkey)) {
         String var11 = var1.authkey;
         if (var11 != null) {
            var2.writeString(1, var11);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.loots)) {
         List var12 = var1.loots;
         if (var12 != null) {
            CodedConstant.writeToList(var2, 2, FieldType.OBJECT, var12, false);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.stage)) {
         Integer var13 = var1.stage;
         if (var13 != null) {
            var2.writeInt32(3, var13);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.matchingguid)) {
         Long var14 = var1.matchingguid;
         if (var14 != null) {
            var2.writeUInt64(4, var14);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.time)) {
         String var15 = var1.time;
         if (var15 != null) {
            var2.writeString(5, var15);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var16 = var1.dungeonguid;
         if (var16 != null) {
            var2.writeUInt64(6, var16);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.consume)) {
         List var17 = var1.consume;
         if (var17 != null) {
            CodedConstant.writeToList(var2, 7, FieldType.OBJECT, var17, false);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.frame)) {
         Integer var18 = var1.frame;
         if (var18 != null) {
            var2.writeInt32(8, var18);
         }
      }

   }

   public void writeTo(REQ_LOOTING var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_LOOTING readFrom(CodedInputStream var1) throws IOException {
      REQ_LOOTING var2 = new REQ_LOOTING();
      var2.loots = new ArrayList();
      var2.consume = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 10) {
               var2.authkey = var1.readString();
            } else if (var5 == 18) {
               Codec var10 = ProtobufProxy.create(PT_LOOTS.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.loots == null) {
                  var2.loots = new ArrayList();
               }

               var2.loots.add((PT_LOOTS)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 24) {
               var2.stage = var1.readInt32();
            } else if (var5 == 32) {
               var2.matchingguid = var1.readUInt64();
            } else if (var5 == 42) {
               var2.time = var1.readString();
            } else if (var5 == 48) {
               var2.dungeonguid = var1.readUInt64();
            } else if (var5 == 58) {
               Codec var11 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var12 = var1.readRawVarint32();
               int var13 = var1.pushLimit(var12);
               if (var2.consume == null) {
                  var2.consume = new ArrayList();
               }

               var2.consume.add((PT_STACKABLE)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var13);
            } else if (var5 == 64) {
               var2.frame = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_LOOTING.class);
         return this.descriptor = var1;
      }
   }
}
