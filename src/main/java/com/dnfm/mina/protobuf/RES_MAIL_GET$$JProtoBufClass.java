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

public class RES_MAIL_GET$$JProtoBufClass implements Codec<RES_MAIL_GET>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_MAIL_GET var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_MAIL_GET decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_MAIL_GET var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var10 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var10);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var11 = var1.guid;
         var2 += CodedOutputStream.computeUInt64Size(2, var11);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.limit)) {
         Boolean var12 = var1.limit;
         var2 += CodedOutputStream.computeBoolSize(3, var12);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var13 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(4, var13);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.remaineditems)) {
         List var14 = var1.remaineditems;
         var2 += CodedConstant.computeListSize(5, var14, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.remainedpackages)) {
         List var15 = var1.remainedpackages;
         var2 += CodedConstant.computeListSize(6, var15, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.rewards)) {
         PT_CONTENTS_REWARD_INFO var16 = var1.rewards;
         var2 += CodedConstant.computeSize(7, var16, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      return var2;
   }

   public void doWriteTo(RES_MAIL_GET var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var10 = var1.error;
         if (var10 != null) {
            var2.writeInt32(1, var10);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var11 = var1.guid;
         if (var11 != null) {
            var2.writeUInt64(2, var11);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.limit)) {
         Boolean var12 = var1.limit;
         if (var12 != null) {
            var2.writeBool(3, var12);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var13 = var1.type;
         if (var13 != null) {
            var2.writeInt32(4, var13);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.remaineditems)) {
         List var14 = var1.remaineditems;
         if (var14 != null) {
            CodedConstant.writeToList(var2, 5, FieldType.OBJECT, var14, false);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.remainedpackages)) {
         List var15 = var1.remainedpackages;
         if (var15 != null) {
            CodedConstant.writeToList(var2, 6, FieldType.OBJECT, var15, false);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.rewards)) {
         PT_CONTENTS_REWARD_INFO var16 = var1.rewards;
         if (var16 != null) {
            CodedConstant.writeObject(var2, 7, FieldType.OBJECT, var16, false);
         }
      }

   }

   public void writeTo(RES_MAIL_GET var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_MAIL_GET readFrom(CodedInputStream var1) throws IOException {
      RES_MAIL_GET var2 = new RES_MAIL_GET();
      var2.remaineditems = new ArrayList();
      var2.remainedpackages = new ArrayList();

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
               var2.guid = var1.readUInt64();
            } else if (var5 == 24) {
               var2.limit = var1.readBool();
            } else if (var5 == 32) {
               var2.type = var1.readInt32();
            } else if (var5 == 42) {
               Codec var10 = ProtobufProxy.create(PT_SELECTED_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.remaineditems == null) {
                  var2.remaineditems = new ArrayList();
               }

               var2.remaineditems.add((PT_SELECTED_ITEM)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 50) {
               Codec var11 = ProtobufProxy.create(PT_POST_PACKAGE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var13 = var1.readRawVarint32();
               int var15 = var1.pushLimit(var13);
               if (var2.remainedpackages == null) {
                  var2.remainedpackages = new ArrayList();
               }

               var2.remainedpackages.add((PT_POST_PACKAGE)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var15);
            } else if (var5 == 58) {
               Codec var12 = ProtobufProxy.create(PT_CONTENTS_REWARD_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var14 = var1.readRawVarint32();
               int var16 = var1.pushLimit(var14);
               var2.rewards = (PT_CONTENTS_REWARD_INFO)var12.readFrom(var1);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_MAIL_GET.class);
         return this.descriptor = var1;
      }
   }
}
