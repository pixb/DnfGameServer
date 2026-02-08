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

public class RES_CREATURE_EVOLVE$$JProtoBufClass implements Codec<RES_CREATURE_EVOLVE>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_CREATURE_EVOLVE var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_CREATURE_EVOLVE decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_CREATURE_EVOLVE var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var11 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var11);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.creature)) {
         Long var12 = var1.creature;
         var2 += CodedOutputStream.computeUInt64Size(2, var12);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.material)) {
         Long var13 = var1.material;
         var2 += CodedOutputStream.computeUInt64Size(3, var13);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.exp)) {
         Integer var14 = var1.exp;
         var2 += CodedOutputStream.computeInt32Size(4, var14);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.newcreatureindex)) {
         Integer var15 = var1.newcreatureindex;
         var2 += CodedOutputStream.computeInt32Size(5, var15);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.currency)) {
         List var16 = var1.currency;
         var2 += CodedConstant.computeListSize(6, var16, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.rewards)) {
         PT_CONTENTS_REWARD_INFO var17 = var1.rewards;
         var2 += CodedConstant.computeSize(7, var17, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.removeitems)) {
         PT_REMOVEITEMS var18 = var1.removeitems;
         var2 += CodedConstant.computeSize(8, var18, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      return var2;
   }

   public void doWriteTo(RES_CREATURE_EVOLVE var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var11 = var1.error;
         if (var11 != null) {
            var2.writeInt32(1, var11);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.creature)) {
         Long var12 = var1.creature;
         if (var12 != null) {
            var2.writeUInt64(2, var12);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.material)) {
         Long var13 = var1.material;
         if (var13 != null) {
            var2.writeUInt64(3, var13);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.exp)) {
         Integer var14 = var1.exp;
         if (var14 != null) {
            var2.writeInt32(4, var14);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.newcreatureindex)) {
         Integer var15 = var1.newcreatureindex;
         if (var15 != null) {
            var2.writeInt32(5, var15);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.currency)) {
         List var16 = var1.currency;
         if (var16 != null) {
            CodedConstant.writeToList(var2, 6, FieldType.OBJECT, var16, false);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.rewards)) {
         PT_CONTENTS_REWARD_INFO var17 = var1.rewards;
         if (var17 != null) {
            CodedConstant.writeObject(var2, 7, FieldType.OBJECT, var17, false);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.removeitems)) {
         PT_REMOVEITEMS var18 = var1.removeitems;
         if (var18 != null) {
            CodedConstant.writeObject(var2, 8, FieldType.OBJECT, var18, false);
         }
      }

   }

   public void writeTo(RES_CREATURE_EVOLVE var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_CREATURE_EVOLVE readFrom(CodedInputStream var1) throws IOException {
      RES_CREATURE_EVOLVE var2 = new RES_CREATURE_EVOLVE();
      var2.currency = new ArrayList();

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
               var2.creature = var1.readUInt64();
            } else if (var5 == 24) {
               var2.material = var1.readUInt64();
            } else if (var5 == 32) {
               var2.exp = var1.readInt32();
            } else if (var5 == 40) {
               var2.newcreatureindex = var1.readInt32();
            } else if (var5 == 50) {
               Codec var10 = ProtobufProxy.create(PT_MONEY_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.currency == null) {
                  var2.currency = new ArrayList();
               }

               var2.currency.add((PT_MONEY_ITEM)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 58) {
               Codec var11 = ProtobufProxy.create(PT_CONTENTS_REWARD_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var13 = var1.readRawVarint32();
               int var15 = var1.pushLimit(var13);
               var2.rewards = (PT_CONTENTS_REWARD_INFO)var11.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var15);
            } else if (var5 == 66) {
               Codec var12 = ProtobufProxy.create(PT_REMOVEITEMS.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var14 = var1.readRawVarint32();
               int var16 = var1.pushLimit(var14);
               var2.removeitems = (PT_REMOVEITEMS)var12.readFrom(var1);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_CREATURE_EVOLVE.class);
         return this.descriptor = var1;
      }
   }
}
