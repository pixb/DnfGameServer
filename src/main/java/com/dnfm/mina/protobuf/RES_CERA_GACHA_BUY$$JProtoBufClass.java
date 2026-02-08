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

public class RES_CERA_GACHA_BUY$$JProtoBufClass implements Codec<RES_CERA_GACHA_BUY>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_CERA_GACHA_BUY var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_CERA_GACHA_BUY decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_CERA_GACHA_BUY var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var14 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var14);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var15 = var1.grade;
         var2 += CodedOutputStream.computeInt32Size(2, var15);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var16 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(3, var16);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.money)) {
         PT_CERA_SHOP_MONEY var17 = var1.money;
         var2 += CodedConstant.computeSize(4, var17, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.removeitems)) {
         PT_REMOVEITEMS var18 = var1.removeitems;
         var2 += CodedConstant.computeSize(5, var18, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.mileage)) {
         List var19 = var1.mileage;
         var2 += CodedConstant.computeListSize(6, var19, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.result)) {
         List var20 = var1.result;
         var2 += CodedConstant.computeListSize(7, var20, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.resultinven)) {
         List var21 = var1.resultinven;
         var2 += CodedConstant.computeListSize(8, var21, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.reward)) {
         List var22 = var1.reward;
         var2 += CodedConstant.computeListSize(9, var22, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.rewardinven)) {
         List var23 = var1.rewardinven;
         var2 += CodedConstant.computeListSize(10, var23, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.cash)) {
         List var24 = var1.cash;
         var2 += CodedConstant.computeListSize(11, var24, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(RES_CERA_GACHA_BUY var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var14 = var1.error;
         if (var14 != null) {
            var2.writeInt32(1, var14);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var15 = var1.grade;
         if (var15 != null) {
            var2.writeInt32(2, var15);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var16 = var1.index;
         if (var16 != null) {
            var2.writeInt32(3, var16);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.money)) {
         PT_CERA_SHOP_MONEY var17 = var1.money;
         if (var17 != null) {
            CodedConstant.writeObject(var2, 4, FieldType.OBJECT, var17, false);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.removeitems)) {
         PT_REMOVEITEMS var18 = var1.removeitems;
         if (var18 != null) {
            CodedConstant.writeObject(var2, 5, FieldType.OBJECT, var18, false);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.mileage)) {
         List var19 = var1.mileage;
         if (var19 != null) {
            CodedConstant.writeToList(var2, 6, FieldType.OBJECT, var19, false);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.result)) {
         List var20 = var1.result;
         if (var20 != null) {
            CodedConstant.writeToList(var2, 7, FieldType.OBJECT, var20, false);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.resultinven)) {
         List var21 = var1.resultinven;
         if (var21 != null) {
            CodedConstant.writeToList(var2, 8, FieldType.OBJECT, var21, false);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.reward)) {
         List var22 = var1.reward;
         if (var22 != null) {
            CodedConstant.writeToList(var2, 9, FieldType.OBJECT, var22, false);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.rewardinven)) {
         List var23 = var1.rewardinven;
         if (var23 != null) {
            CodedConstant.writeToList(var2, 10, FieldType.OBJECT, var23, false);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.cash)) {
         List var24 = var1.cash;
         if (var24 != null) {
            CodedConstant.writeToList(var2, 11, FieldType.OBJECT, var24, false);
         }
      }

   }

   public void writeTo(RES_CERA_GACHA_BUY var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_CERA_GACHA_BUY readFrom(CodedInputStream var1) throws IOException {
      RES_CERA_GACHA_BUY var2 = new RES_CERA_GACHA_BUY();
      var2.mileage = new ArrayList();
      var2.result = new ArrayList();
      var2.resultinven = new ArrayList();
      var2.reward = new ArrayList();
      var2.rewardinven = new ArrayList();
      var2.cash = new ArrayList();

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
               var2.grade = var1.readInt32();
            } else if (var5 == 24) {
               var2.index = var1.readInt32();
            } else if (var5 == 34) {
               Codec var10 = ProtobufProxy.create(PT_CERA_SHOP_MONEY.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.money = (PT_CERA_SHOP_MONEY)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 42) {
               Codec var11 = ProtobufProxy.create(PT_REMOVEITEMS.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var18 = var1.readRawVarint32();
               int var25 = var1.pushLimit(var18);
               var2.removeitems = (PT_REMOVEITEMS)var11.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var25);
            } else if (var5 == 50) {
               Codec var12 = ProtobufProxy.create(PT_CERA_SHOP_Mileage.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var19 = var1.readRawVarint32();
               int var26 = var1.pushLimit(var19);
               if (var2.mileage == null) {
                  var2.mileage = new ArrayList();
               }

               var2.mileage.add((PT_CERA_SHOP_Mileage)var12.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var26);
            } else if (var5 == 58) {
               Codec var13 = ProtobufProxy.create(PT_GACHA_RESULT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var20 = var1.readRawVarint32();
               int var27 = var1.pushLimit(var20);
               if (var2.result == null) {
                  var2.result = new ArrayList();
               }

               var2.result.add((PT_GACHA_RESULT)var13.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var27);
            } else if (var5 == 66) {
               Codec var14 = ProtobufProxy.create(PT_GACHA_RESULT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var21 = var1.readRawVarint32();
               int var28 = var1.pushLimit(var21);
               if (var2.resultinven == null) {
                  var2.resultinven = new ArrayList();
               }

               var2.resultinven.add((PT_GACHA_RESULT)var14.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var28);
            } else if (var5 == 74) {
               Codec var15 = ProtobufProxy.create(PT_GACHA_RESULT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var22 = var1.readRawVarint32();
               int var29 = var1.pushLimit(var22);
               if (var2.reward == null) {
                  var2.reward = new ArrayList();
               }

               var2.reward.add((PT_GACHA_RESULT)var15.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var29);
            } else if (var5 == 82) {
               Codec var16 = ProtobufProxy.create(PT_GACHA_RESULT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var23 = var1.readRawVarint32();
               int var30 = var1.pushLimit(var23);
               if (var2.rewardinven == null) {
                  var2.rewardinven = new ArrayList();
               }

               var2.rewardinven.add((PT_GACHA_RESULT)var16.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var30);
            } else if (var5 == 90) {
               Codec var17 = ProtobufProxy.create(PT_CERA_SHOP_MONEY.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var24 = var1.readRawVarint32();
               int var31 = var1.pushLimit(var24);
               if (var2.cash == null) {
                  var2.cash = new ArrayList();
               }

               var2.cash.add((PT_CERA_SHOP_MONEY)var17.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var31);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_CERA_GACHA_BUY.class);
         return this.descriptor = var1;
      }
   }
}
