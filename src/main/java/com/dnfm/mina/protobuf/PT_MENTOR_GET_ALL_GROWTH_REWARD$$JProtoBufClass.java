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

public class PT_MENTOR_GET_ALL_GROWTH_REWARD$$JProtoBufClass implements Codec<PT_MENTOR_GET_ALL_GROWTH_REWARD>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_MENTOR_GET_ALL_GROWTH_REWARD var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_MENTOR_GET_ALL_GROWTH_REWARD decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_MENTOR_GET_ALL_GROWTH_REWARD var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.followerguid)) {
         Long var6 = var1.followerguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var6);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.seqlevelgiftmaster)) {
         String var7 = var1.seqlevelgiftmaster;
         var2 += CodedOutputStream.computeStringSize(2, var7);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.questreward)) {
         PT_MENTOR_GET_ALL_GROWTH_REWARD_QUEST var8 = var1.questreward;
         var2 += CodedConstant.computeSize(3, var8, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      return var2;
   }

   public void doWriteTo(PT_MENTOR_GET_ALL_GROWTH_REWARD var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.followerguid)) {
         Long var6 = var1.followerguid;
         if (var6 != null) {
            var2.writeUInt64(1, var6);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.seqlevelgiftmaster)) {
         String var7 = var1.seqlevelgiftmaster;
         if (var7 != null) {
            var2.writeString(2, var7);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.questreward)) {
         PT_MENTOR_GET_ALL_GROWTH_REWARD_QUEST var8 = var1.questreward;
         if (var8 != null) {
            CodedConstant.writeObject(var2, 3, FieldType.OBJECT, var8, false);
         }
      }

   }

   public void writeTo(PT_MENTOR_GET_ALL_GROWTH_REWARD var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_MENTOR_GET_ALL_GROWTH_REWARD readFrom(CodedInputStream var1) throws IOException {
      PT_MENTOR_GET_ALL_GROWTH_REWARD var2 = new PT_MENTOR_GET_ALL_GROWTH_REWARD();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.followerguid = var1.readUInt64();
            } else if (var5 == 18) {
               var2.seqlevelgiftmaster = var1.readString();
            } else if (var5 == 26) {
               Codec var10 = ProtobufProxy.create(PT_MENTOR_GET_ALL_GROWTH_REWARD_QUEST.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.questreward = (PT_MENTOR_GET_ALL_GROWTH_REWARD_QUEST)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_MENTOR_GET_ALL_GROWTH_REWARD.class);
         return this.descriptor = var1;
      }
   }
}
