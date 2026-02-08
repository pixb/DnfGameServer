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

public class RES_EVENT_LIST$$JProtoBufClass implements Codec<RES_EVENT_LIST>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_EVENT_LIST var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_EVENT_LIST decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_EVENT_LIST var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var10 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var10);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.account)) {
         List var11 = var1.account;
         var2 += CodedConstant.computeListSize(2, var11, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.characters)) {
         List var12 = var1.characters;
         var2 += CodedConstant.computeListSize(3, var12, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.scheduler)) {
         List var13 = var1.scheduler;
         var2 += CodedConstant.computeListSize(4, var13, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.ontimeEvents)) {
         List var14 = var1.ontimeEvents;
         var2 += CodedConstant.computeListSize(5, var14, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.page)) {
         Integer var15 = var1.page;
         var2 += CodedOutputStream.computeInt32Size(6, var15);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.maxpage)) {
         Integer var16 = var1.maxpage;
         var2 += CodedOutputStream.computeInt32Size(7, var16);
      }

      return var2;
   }

   public void doWriteTo(RES_EVENT_LIST var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var10 = var1.error;
         if (var10 != null) {
            var2.writeInt32(1, var10);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.account)) {
         List var11 = var1.account;
         if (var11 != null) {
            CodedConstant.writeToList(var2, 2, FieldType.OBJECT, var11, false);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.characters)) {
         List var12 = var1.characters;
         if (var12 != null) {
            CodedConstant.writeToList(var2, 3, FieldType.OBJECT, var12, false);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.scheduler)) {
         List var13 = var1.scheduler;
         if (var13 != null) {
            CodedConstant.writeToList(var2, 4, FieldType.OBJECT, var13, false);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.ontimeEvents)) {
         List var14 = var1.ontimeEvents;
         if (var14 != null) {
            CodedConstant.writeToList(var2, 5, FieldType.OBJECT, var14, false);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.page)) {
         Integer var15 = var1.page;
         if (var15 != null) {
            var2.writeInt32(6, var15);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.maxpage)) {
         Integer var16 = var1.maxpage;
         if (var16 != null) {
            var2.writeInt32(7, var16);
         }
      }

   }

   public void writeTo(RES_EVENT_LIST var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_EVENT_LIST readFrom(CodedInputStream var1) throws IOException {
      RES_EVENT_LIST var2 = new RES_EVENT_LIST();
      var2.account = new ArrayList();
      var2.characters = new ArrayList();
      var2.scheduler = new ArrayList();
      var2.ontimeEvents = new ArrayList();

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
               Codec var10 = ProtobufProxy.create(PT_MAIN_EVENT_DATA.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.account == null) {
                  var2.account = new ArrayList();
               }

               var2.account.add((PT_MAIN_EVENT_DATA)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 26) {
               Codec var11 = ProtobufProxy.create(PT_EVENT_DATA_FOR_CHARACTER.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var14 = var1.readRawVarint32();
               int var17 = var1.pushLimit(var14);
               if (var2.characters == null) {
                  var2.characters = new ArrayList();
               }

               var2.characters.add((PT_EVENT_DATA_FOR_CHARACTER)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var17);
            } else if (var5 == 34) {
               Codec var12 = ProtobufProxy.create(PT_EVENT_SCHEDULER.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var15 = var1.readRawVarint32();
               int var18 = var1.pushLimit(var15);
               if (var2.scheduler == null) {
                  var2.scheduler = new ArrayList();
               }

               var2.scheduler.add((PT_EVENT_SCHEDULER)var12.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var18);
            } else if (var5 == 42) {
               Codec var13 = ProtobufProxy.create(PT_EVENT_ON_TIME.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var16 = var1.readRawVarint32();
               int var19 = var1.pushLimit(var16);
               if (var2.ontimeEvents == null) {
                  var2.ontimeEvents = new ArrayList();
               }

               var2.ontimeEvents.add((PT_EVENT_ON_TIME)var13.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var19);
            } else if (var5 == 48) {
               var2.page = var1.readInt32();
            } else if (var5 == 56) {
               var2.maxpage = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_EVENT_LIST.class);
         return this.descriptor = var1;
      }
   }
}
