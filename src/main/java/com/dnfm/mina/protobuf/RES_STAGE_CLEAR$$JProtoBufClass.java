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

public class RES_STAGE_CLEAR$$JProtoBufClass implements Codec<RES_STAGE_CLEAR>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_STAGE_CLEAR var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_STAGE_CLEAR decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_STAGE_CLEAR var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var11 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var11);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.fatigue)) {
         Integer var12 = var1.fatigue;
         var2 += CodedOutputStream.computeInt32Size(2, var12);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.gage)) {
         Integer var13 = var1.gage;
         var2 += CodedOutputStream.computeInt32Size(3, var13);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.wagonhp)) {
         Integer var14 = var1.wagonhp;
         var2 += CodedOutputStream.computeInt32Size(4, var14);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.exp)) {
         Integer var15 = var1.exp;
         var2 += CodedOutputStream.computeInt32Size(5, var15);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.reward)) {
         PT_ITEMS var16 = var1.reward;
         var2 += CodedConstant.computeSize(6, var16, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.servertime)) {
         Long var17 = var1.servertime;
         var2 += CodedOutputStream.computeUInt64Size(7, var17);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.durability)) {
         Integer var18 = var1.durability;
         var2 += CodedOutputStream.computeInt32Size(8, var18);
      }

      return var2;
   }

   public void doWriteTo(RES_STAGE_CLEAR var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var11 = var1.error;
         if (var11 != null) {
            var2.writeInt32(1, var11);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.fatigue)) {
         Integer var12 = var1.fatigue;
         if (var12 != null) {
            var2.writeInt32(2, var12);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.gage)) {
         Integer var13 = var1.gage;
         if (var13 != null) {
            var2.writeInt32(3, var13);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.wagonhp)) {
         Integer var14 = var1.wagonhp;
         if (var14 != null) {
            var2.writeInt32(4, var14);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.exp)) {
         Integer var15 = var1.exp;
         if (var15 != null) {
            var2.writeInt32(5, var15);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.reward)) {
         PT_ITEMS var16 = var1.reward;
         if (var16 != null) {
            CodedConstant.writeObject(var2, 6, FieldType.OBJECT, var16, false);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.servertime)) {
         Long var17 = var1.servertime;
         if (var17 != null) {
            var2.writeUInt64(7, var17);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.durability)) {
         Integer var18 = var1.durability;
         if (var18 != null) {
            var2.writeInt32(8, var18);
         }
      }

   }

   public void writeTo(RES_STAGE_CLEAR var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_STAGE_CLEAR readFrom(CodedInputStream var1) throws IOException {
      RES_STAGE_CLEAR var2 = new RES_STAGE_CLEAR();

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
               var2.fatigue = var1.readInt32();
            } else if (var5 == 24) {
               var2.gage = var1.readInt32();
            } else if (var5 == 32) {
               var2.wagonhp = var1.readInt32();
            } else if (var5 == 40) {
               var2.exp = var1.readInt32();
            } else if (var5 == 50) {
               Codec var10 = ProtobufProxy.create(PT_ITEMS.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.reward = (PT_ITEMS)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 56) {
               var2.servertime = var1.readUInt64();
            } else if (var5 == 64) {
               var2.durability = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_STAGE_CLEAR.class);
         return this.descriptor = var1;
      }
   }
}
