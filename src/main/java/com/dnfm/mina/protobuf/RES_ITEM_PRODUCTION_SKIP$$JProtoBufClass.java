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

public class RES_ITEM_PRODUCTION_SKIP$$JProtoBufClass implements Codec<RES_ITEM_PRODUCTION_SKIP>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_ITEM_PRODUCTION_SKIP var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_ITEM_PRODUCTION_SKIP decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_ITEM_PRODUCTION_SKIP var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var12 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var12);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.slotindex)) {
         Integer var13 = var1.slotindex;
         var2 += CodedOutputStream.computeInt32Size(2, var13);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.recipeindex)) {
         Integer var14 = var1.recipeindex;
         var2 += CodedOutputStream.computeInt32Size(3, var14);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.starttime)) {
         Long var15 = var1.starttime;
         var2 += CodedOutputStream.computeUInt64Size(4, var15);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.endtime)) {
         Long var16 = var1.endtime;
         var2 += CodedOutputStream.computeUInt64Size(5, var16);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.state)) {
         Integer var17 = var1.state;
         var2 += CodedOutputStream.computeInt32Size(6, var17);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.currency)) {
         List var18 = var1.currency;
         var2 += CodedConstant.computeListSize(7, var18, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.accountcurrency)) {
         List var19 = var1.accountcurrency;
         var2 += CodedConstant.computeListSize(8, var19, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.usablecount)) {
         Integer var20 = var1.usablecount;
         var2 += CodedOutputStream.computeInt32Size(9, var20);
      }

      return var2;
   }

   public void doWriteTo(RES_ITEM_PRODUCTION_SKIP var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var12 = var1.error;
         if (var12 != null) {
            var2.writeInt32(1, var12);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.slotindex)) {
         Integer var13 = var1.slotindex;
         if (var13 != null) {
            var2.writeInt32(2, var13);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.recipeindex)) {
         Integer var14 = var1.recipeindex;
         if (var14 != null) {
            var2.writeInt32(3, var14);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.starttime)) {
         Long var15 = var1.starttime;
         if (var15 != null) {
            var2.writeUInt64(4, var15);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.endtime)) {
         Long var16 = var1.endtime;
         if (var16 != null) {
            var2.writeUInt64(5, var16);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.state)) {
         Integer var17 = var1.state;
         if (var17 != null) {
            var2.writeInt32(6, var17);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.currency)) {
         List var18 = var1.currency;
         if (var18 != null) {
            CodedConstant.writeToList(var2, 7, FieldType.OBJECT, var18, false);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.accountcurrency)) {
         List var19 = var1.accountcurrency;
         if (var19 != null) {
            CodedConstant.writeToList(var2, 8, FieldType.OBJECT, var19, false);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.usablecount)) {
         Integer var20 = var1.usablecount;
         if (var20 != null) {
            var2.writeInt32(9, var20);
         }
      }

   }

   public void writeTo(RES_ITEM_PRODUCTION_SKIP var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_ITEM_PRODUCTION_SKIP readFrom(CodedInputStream var1) throws IOException {
      RES_ITEM_PRODUCTION_SKIP var2 = new RES_ITEM_PRODUCTION_SKIP();
      var2.currency = new ArrayList();
      var2.accountcurrency = new ArrayList();

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
               var2.slotindex = var1.readInt32();
            } else if (var5 == 24) {
               var2.recipeindex = var1.readInt32();
            } else if (var5 == 32) {
               var2.starttime = var1.readUInt64();
            } else if (var5 == 40) {
               var2.endtime = var1.readUInt64();
            } else if (var5 == 48) {
               var2.state = var1.readInt32();
            } else if (var5 == 58) {
               Codec var10 = ProtobufProxy.create(PT_MONEY_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.currency == null) {
                  var2.currency = new ArrayList();
               }

               var2.currency.add((PT_MONEY_ITEM)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 66) {
               Codec var11 = ProtobufProxy.create(PT_MONEY_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var12 = var1.readRawVarint32();
               int var13 = var1.pushLimit(var12);
               if (var2.accountcurrency == null) {
                  var2.accountcurrency = new ArrayList();
               }

               var2.accountcurrency.add((PT_MONEY_ITEM)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var13);
            } else if (var5 == 72) {
               var2.usablecount = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_ITEM_PRODUCTION_SKIP.class);
         return this.descriptor = var1;
      }
   }
}
