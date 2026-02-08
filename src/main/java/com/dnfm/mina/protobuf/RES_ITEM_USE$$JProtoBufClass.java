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

public class RES_ITEM_USE$$JProtoBufClass implements Codec<RES_ITEM_USE>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_ITEM_USE var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_ITEM_USE decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_ITEM_USE var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var13 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var13);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var14 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(2, var14);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var15 = var1.count;
         var2 += CodedOutputStream.computeInt32Size(3, var15);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.bind)) {
         Boolean var16 = var1.bind;
         var2 += CodedOutputStream.computeBoolSize(4, var16);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.info)) {
         PT_CONTENTS_REWARD_INFO var17 = var1.info;
         var2 += CodedConstant.computeSize(5, var17, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.removeitems)) {
         PT_REMOVEITEMS var18 = var1.removeitems;
         var2 += CodedConstant.computeSize(6, var18, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.result)) {
         PT_ITEM_USE_RESULT var19 = var1.result;
         var2 += CodedConstant.computeSize(7, var19, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var20 = var1.guid;
         var2 += CodedOutputStream.computeUInt64Size(8, var20);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.changecount)) {
         Long var21 = var1.changecount;
         var2 += CodedOutputStream.computeInt64Size(9, var21);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.sender)) {
         Long var22 = var1.sender;
         var2 += CodedOutputStream.computeUInt64Size(10, var22);
      }

      return var2;
   }

   public void doWriteTo(RES_ITEM_USE var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var13 = var1.error;
         if (var13 != null) {
            var2.writeInt32(1, var13);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var14 = var1.index;
         if (var14 != null) {
            var2.writeInt32(2, var14);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var15 = var1.count;
         if (var15 != null) {
            var2.writeInt32(3, var15);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.bind)) {
         Boolean var16 = var1.bind;
         if (var16 != null) {
            var2.writeBool(4, var16);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.info)) {
         PT_CONTENTS_REWARD_INFO var17 = var1.info;
         if (var17 != null) {
            CodedConstant.writeObject(var2, 5, FieldType.OBJECT, var17, false);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.removeitems)) {
         PT_REMOVEITEMS var18 = var1.removeitems;
         if (var18 != null) {
            CodedConstant.writeObject(var2, 6, FieldType.OBJECT, var18, false);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.result)) {
         PT_ITEM_USE_RESULT var19 = var1.result;
         if (var19 != null) {
            CodedConstant.writeObject(var2, 7, FieldType.OBJECT, var19, false);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var20 = var1.guid;
         if (var20 != null) {
            var2.writeUInt64(8, var20);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.changecount)) {
         Long var21 = var1.changecount;
         if (var21 != null) {
            var2.writeInt64(9, var21);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.sender)) {
         Long var22 = var1.sender;
         if (var22 != null) {
            var2.writeUInt64(10, var22);
         }
      }

   }

   public void writeTo(RES_ITEM_USE var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_ITEM_USE readFrom(CodedInputStream var1) throws IOException {
      RES_ITEM_USE var2 = new RES_ITEM_USE();

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
               var2.index = var1.readInt32();
            } else if (var5 == 24) {
               var2.count = var1.readInt32();
            } else if (var5 == 32) {
               var2.bind = var1.readBool();
            } else if (var5 == 42) {
               Codec var10 = ProtobufProxy.create(PT_CONTENTS_REWARD_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.info = (PT_CONTENTS_REWARD_INFO)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 50) {
               Codec var11 = ProtobufProxy.create(PT_REMOVEITEMS.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var13 = var1.readRawVarint32();
               int var15 = var1.pushLimit(var13);
               var2.removeitems = (PT_REMOVEITEMS)var11.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var15);
            } else if (var5 == 58) {
               Codec var12 = ProtobufProxy.create(PT_ITEM_USE_RESULT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var14 = var1.readRawVarint32();
               int var16 = var1.pushLimit(var14);
               var2.result = (PT_ITEM_USE_RESULT)var12.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var16);
            } else if (var5 == 64) {
               var2.guid = var1.readUInt64();
            } else if (var5 == 72) {
               var2.changecount = var1.readInt64();
            } else if (var5 == 80) {
               var2.sender = var1.readUInt64();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_ITEM_USE.class);
         return this.descriptor = var1;
      }
   }
}
