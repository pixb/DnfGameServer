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

public class RES_CERA_SHOP_BUY$$JProtoBufClass implements Codec<RES_CERA_SHOP_BUY>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_CERA_SHOP_BUY var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_CERA_SHOP_BUY decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_CERA_SHOP_BUY var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var12 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var12);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.buy)) {
         List var13 = var1.buy;
         var2 += CodedConstant.computeListSize(2, var13, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.billno)) {
         Long var14 = var1.billno;
         var2 += CodedOutputStream.computeUInt64Size(3, var14);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.account)) {
         PT_CERA_SHOP_INFO var15 = var1.account;
         var2 += CodedConstant.computeSize(4, var15, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.character)) {
         PT_CERA_SHOP_INFO var16 = var1.character;
         var2 += CodedConstant.computeSize(5, var16, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.shopid)) {
         Integer var17 = var1.shopid;
         var2 += CodedOutputStream.computeInt32Size(6, var17);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.rewards)) {
         PT_CONTENTS_REWARD_INFO var18 = var1.rewards;
         var2 += CodedConstant.computeSize(7, var18, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.battlepassexp)) {
         Integer var19 = var1.battlepassexp;
         var2 += CodedOutputStream.computeInt32Size(8, var19);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.removeitems)) {
         PT_REMOVEITEMS var20 = var1.removeitems;
         var2 += CodedConstant.computeSize(9, var20, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      return var2;
   }

   public void doWriteTo(RES_CERA_SHOP_BUY var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var12 = var1.error;
         if (var12 != null) {
            var2.writeInt32(1, var12);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.buy)) {
         List var13 = var1.buy;
         if (var13 != null) {
            CodedConstant.writeToList(var2, 2, FieldType.OBJECT, var13, false);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.billno)) {
         Long var14 = var1.billno;
         if (var14 != null) {
            var2.writeUInt64(3, var14);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.account)) {
         PT_CERA_SHOP_INFO var15 = var1.account;
         if (var15 != null) {
            CodedConstant.writeObject(var2, 4, FieldType.OBJECT, var15, false);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.character)) {
         PT_CERA_SHOP_INFO var16 = var1.character;
         if (var16 != null) {
            CodedConstant.writeObject(var2, 5, FieldType.OBJECT, var16, false);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.shopid)) {
         Integer var17 = var1.shopid;
         if (var17 != null) {
            var2.writeInt32(6, var17);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.rewards)) {
         PT_CONTENTS_REWARD_INFO var18 = var1.rewards;
         if (var18 != null) {
            CodedConstant.writeObject(var2, 7, FieldType.OBJECT, var18, false);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.battlepassexp)) {
         Integer var19 = var1.battlepassexp;
         if (var19 != null) {
            var2.writeInt32(8, var19);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.removeitems)) {
         PT_REMOVEITEMS var20 = var1.removeitems;
         if (var20 != null) {
            CodedConstant.writeObject(var2, 9, FieldType.OBJECT, var20, false);
         }
      }

   }

   public void writeTo(RES_CERA_SHOP_BUY var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_CERA_SHOP_BUY readFrom(CodedInputStream var1) throws IOException {
      RES_CERA_SHOP_BUY var2 = new RES_CERA_SHOP_BUY();
      var2.buy = new ArrayList();

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
               Codec var10 = ProtobufProxy.create(PT_CERA_SHOP_BUY.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.buy == null) {
                  var2.buy = new ArrayList();
               }

               var2.buy.add((PT_CERA_SHOP_BUY)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 24) {
               var2.billno = var1.readUInt64();
            } else if (var5 == 34) {
               Codec var11 = ProtobufProxy.create(PT_CERA_SHOP_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var15 = var1.readRawVarint32();
               int var19 = var1.pushLimit(var15);
               var2.account = (PT_CERA_SHOP_INFO)var11.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var19);
            } else if (var5 == 42) {
               Codec var12 = ProtobufProxy.create(PT_CERA_SHOP_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var16 = var1.readRawVarint32();
               int var20 = var1.pushLimit(var16);
               var2.character = (PT_CERA_SHOP_INFO)var12.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var20);
            } else if (var5 == 48) {
               var2.shopid = var1.readInt32();
            } else if (var5 == 58) {
               Codec var13 = ProtobufProxy.create(PT_CONTENTS_REWARD_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var17 = var1.readRawVarint32();
               int var21 = var1.pushLimit(var17);
               var2.rewards = (PT_CONTENTS_REWARD_INFO)var13.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var21);
            } else if (var5 == 64) {
               var2.battlepassexp = var1.readInt32();
            } else if (var5 == 74) {
               Codec var14 = ProtobufProxy.create(PT_REMOVEITEMS.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var18 = var1.readRawVarint32();
               int var22 = var1.pushLimit(var18);
               var2.removeitems = (PT_REMOVEITEMS)var14.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var22);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_CERA_SHOP_BUY.class);
         return this.descriptor = var1;
      }
   }
}
