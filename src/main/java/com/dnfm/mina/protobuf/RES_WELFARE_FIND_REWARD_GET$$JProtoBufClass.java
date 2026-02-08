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

public class RES_WELFARE_FIND_REWARD_GET$$JProtoBufClass implements Codec<RES_WELFARE_FIND_REWARD_GET>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_WELFARE_FIND_REWARD_GET var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_WELFARE_FIND_REWARD_GET decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_WELFARE_FIND_REWARD_GET var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var10 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var10);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var11 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(2, var11);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.clearexp)) {
         PT_EXP_DETAILINFO var12 = var1.clearexp;
         var2 += CodedConstant.computeSize(3, var12, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.ticket)) {
         PT_TICKET var13 = var1.ticket;
         var2 += CodedConstant.computeSize(4, var13, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.adventureunionlevel)) {
         Integer var14 = var1.adventureunionlevel;
         var2 += CodedOutputStream.computeInt32Size(5, var14);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.adventureunionexp)) {
         Long var15 = var1.adventureunionexp;
         var2 += CodedOutputStream.computeInt64Size(6, var15);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.rewards)) {
         PT_CONTENTS_REWARD_INFO var16 = var1.rewards;
         var2 += CodedConstant.computeSize(7, var16, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      return var2;
   }

   public void doWriteTo(RES_WELFARE_FIND_REWARD_GET var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var10 = var1.error;
         if (var10 != null) {
            var2.writeInt32(1, var10);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var11 = var1.level;
         if (var11 != null) {
            var2.writeInt32(2, var11);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.clearexp)) {
         PT_EXP_DETAILINFO var12 = var1.clearexp;
         if (var12 != null) {
            CodedConstant.writeObject(var2, 3, FieldType.OBJECT, var12, false);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.ticket)) {
         PT_TICKET var13 = var1.ticket;
         if (var13 != null) {
            CodedConstant.writeObject(var2, 4, FieldType.OBJECT, var13, false);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.adventureunionlevel)) {
         Integer var14 = var1.adventureunionlevel;
         if (var14 != null) {
            var2.writeInt32(5, var14);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.adventureunionexp)) {
         Long var15 = var1.adventureunionexp;
         if (var15 != null) {
            var2.writeInt64(6, var15);
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

   public void writeTo(RES_WELFARE_FIND_REWARD_GET var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_WELFARE_FIND_REWARD_GET readFrom(CodedInputStream var1) throws IOException {
      RES_WELFARE_FIND_REWARD_GET var2 = new RES_WELFARE_FIND_REWARD_GET();

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
               var2.level = var1.readInt32();
            } else if (var5 == 26) {
               Codec var10 = ProtobufProxy.create(PT_EXP_DETAILINFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.clearexp = (PT_EXP_DETAILINFO)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 34) {
               Codec var11 = ProtobufProxy.create(PT_TICKET.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var13 = var1.readRawVarint32();
               int var15 = var1.pushLimit(var13);
               var2.ticket = (PT_TICKET)var11.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var15);
            } else if (var5 == 40) {
               var2.adventureunionlevel = var1.readInt32();
            } else if (var5 == 48) {
               var2.adventureunionexp = var1.readInt64();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_WELFARE_FIND_REWARD_GET.class);
         return this.descriptor = var1;
      }
   }
}
