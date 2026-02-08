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

public class RES_MINIGAME_RESTAURANT_CLEAR$$JProtoBufClass implements Codec<RES_MINIGAME_RESTAURANT_CLEAR>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_MINIGAME_RESTAURANT_CLEAR var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_MINIGAME_RESTAURANT_CLEAR decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_MINIGAME_RESTAURANT_CLEAR var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var10 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var10);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.state)) {
         Integer var11 = var1.state;
         var2 += CodedOutputStream.computeInt32Size(2, var11);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.ticketcount)) {
         Integer var12 = var1.ticketcount;
         var2 += CodedOutputStream.computeInt32Size(3, var12);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.playcount)) {
         Integer var13 = var1.playcount;
         var2 += CodedOutputStream.computeInt32Size(4, var13);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.myscore)) {
         Long var14 = var1.myscore;
         var2 += CodedOutputStream.computeUInt64Size(5, var14);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.mycharguid)) {
         Long var15 = var1.mycharguid;
         var2 += CodedOutputStream.computeUInt64Size(6, var15);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.info)) {
         PT_CONTENTS_REWARD_INFO var16 = var1.info;
         var2 += CodedConstant.computeSize(7, var16, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      return var2;
   }

   public void doWriteTo(RES_MINIGAME_RESTAURANT_CLEAR var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var10 = var1.error;
         if (var10 != null) {
            var2.writeInt32(1, var10);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.state)) {
         Integer var11 = var1.state;
         if (var11 != null) {
            var2.writeInt32(2, var11);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.ticketcount)) {
         Integer var12 = var1.ticketcount;
         if (var12 != null) {
            var2.writeInt32(3, var12);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.playcount)) {
         Integer var13 = var1.playcount;
         if (var13 != null) {
            var2.writeInt32(4, var13);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.myscore)) {
         Long var14 = var1.myscore;
         if (var14 != null) {
            var2.writeUInt64(5, var14);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.mycharguid)) {
         Long var15 = var1.mycharguid;
         if (var15 != null) {
            var2.writeUInt64(6, var15);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.info)) {
         PT_CONTENTS_REWARD_INFO var16 = var1.info;
         if (var16 != null) {
            CodedConstant.writeObject(var2, 7, FieldType.OBJECT, var16, false);
         }
      }

   }

   public void writeTo(RES_MINIGAME_RESTAURANT_CLEAR var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_MINIGAME_RESTAURANT_CLEAR readFrom(CodedInputStream var1) throws IOException {
      RES_MINIGAME_RESTAURANT_CLEAR var2 = new RES_MINIGAME_RESTAURANT_CLEAR();

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
               var2.state = var1.readInt32();
            } else if (var5 == 24) {
               var2.ticketcount = var1.readInt32();
            } else if (var5 == 32) {
               var2.playcount = var1.readInt32();
            } else if (var5 == 40) {
               var2.myscore = var1.readUInt64();
            } else if (var5 == 48) {
               var2.mycharguid = var1.readUInt64();
            } else if (var5 == 58) {
               Codec var10 = ProtobufProxy.create(PT_CONTENTS_REWARD_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.info = (PT_CONTENTS_REWARD_INFO)var10.readFrom(var1);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_MINIGAME_RESTAURANT_CLEAR.class);
         return this.descriptor = var1;
      }
   }
}
