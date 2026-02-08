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

public class PT_DRAGON_BOARD_MEMBER$$JProtoBufClass implements Codec<PT_DRAGON_BOARD_MEMBER>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_DRAGON_BOARD_MEMBER var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_DRAGON_BOARD_MEMBER decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_DRAGON_BOARD_MEMBER var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var10 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var10);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.dicetype)) {
         Integer var11 = var1.dicetype;
         var2 += CodedOutputStream.computeInt32Size(2, var11);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.slot)) {
         Integer var12 = var1.slot;
         var2 += CodedOutputStream.computeInt32Size(3, var12);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.connected)) {
         Boolean var13 = var1.connected;
         var2 += CodedOutputStream.computeBoolSize(4, var13);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.clearboss)) {
         Boolean var14 = var1.clearboss;
         var2 += CodedOutputStream.computeBoolSize(5, var14);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.rewards)) {
         List var15 = var1.rewards;
         var2 += CodedConstant.computeListSize(6, var15, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.appendages)) {
         List var16 = var1.appendages;
         var2 += CodedConstant.computeListSize(7, var16, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(PT_DRAGON_BOARD_MEMBER var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var10 = var1.charguid;
         if (var10 != null) {
            var2.writeUInt64(1, var10);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.dicetype)) {
         Integer var11 = var1.dicetype;
         if (var11 != null) {
            var2.writeInt32(2, var11);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.slot)) {
         Integer var12 = var1.slot;
         if (var12 != null) {
            var2.writeInt32(3, var12);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.connected)) {
         Boolean var13 = var1.connected;
         if (var13 != null) {
            var2.writeBool(4, var13);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.clearboss)) {
         Boolean var14 = var1.clearboss;
         if (var14 != null) {
            var2.writeBool(5, var14);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.rewards)) {
         List var15 = var1.rewards;
         if (var15 != null) {
            CodedConstant.writeToList(var2, 6, FieldType.OBJECT, var15, false);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.appendages)) {
         List var16 = var1.appendages;
         if (var16 != null) {
            CodedConstant.writeToList(var2, 7, FieldType.OBJECT, var16, false);
         }
      }

   }

   public void writeTo(PT_DRAGON_BOARD_MEMBER var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_DRAGON_BOARD_MEMBER readFrom(CodedInputStream var1) throws IOException {
      PT_DRAGON_BOARD_MEMBER var2 = new PT_DRAGON_BOARD_MEMBER();
      var2.rewards = new ArrayList();
      var2.appendages = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.charguid = var1.readUInt64();
            } else if (var5 == 16) {
               var2.dicetype = var1.readInt32();
            } else if (var5 == 24) {
               var2.slot = var1.readInt32();
            } else if (var5 == 32) {
               var2.connected = var1.readBool();
            } else if (var5 == 40) {
               var2.clearboss = var1.readBool();
            } else if (var5 == 50) {
               Codec var10 = ProtobufProxy.create(PT_DRAGON_BOARD_REWARD.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.rewards == null) {
                  var2.rewards = new ArrayList();
               }

               var2.rewards.add((PT_DRAGON_BOARD_REWARD)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 58) {
               Codec var11 = ProtobufProxy.create(PT_DRAGON_BOARD_GAIN_APPENDAGE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var12 = var1.readRawVarint32();
               int var13 = var1.pushLimit(var12);
               if (var2.appendages == null) {
                  var2.appendages = new ArrayList();
               }

               var2.appendages.add((PT_DRAGON_BOARD_GAIN_APPENDAGE)var11.readFrom(var1));
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_DRAGON_BOARD_MEMBER.class);
         return this.descriptor = var1;
      }
   }
}
