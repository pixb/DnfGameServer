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

public class RES_GET_PRIVATESTORE_GOODSLIST$$JProtoBufClass implements Codec<RES_GET_PRIVATESTORE_GOODSLIST>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_GET_PRIVATESTORE_GOODSLIST var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_GET_PRIVATESTORE_GOODSLIST decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_GET_PRIVATESTORE_GOODSLIST var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.shopid)) {
         Integer var10 = var1.shopid;
         var2 += CodedOutputStream.computeInt32Size(1, var10);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.remaincostresetcount)) {
         Integer var11 = var1.remaincostresetcount;
         var2 += CodedOutputStream.computeInt32Size(2, var11);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.lastfreeresettime)) {
         Long var12 = var1.lastfreeresettime;
         var2 += CodedOutputStream.computeInt64Size(3, var12);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var13 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(4, var13);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.goods)) {
         List var14 = var1.goods;
         var2 += CodedConstant.computeListSize(5, var14, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.isrefresh)) {
         Boolean var15 = var1.isrefresh;
         var2 += CodedOutputStream.computeBoolSize(6, var15);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.shoptype)) {
         Integer var16 = var1.shoptype;
         var2 += CodedOutputStream.computeInt32Size(7, var16);
      }

      return var2;
   }

   public void doWriteTo(RES_GET_PRIVATESTORE_GOODSLIST var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.shopid)) {
         Integer var10 = var1.shopid;
         if (var10 != null) {
            var2.writeInt32(1, var10);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.remaincostresetcount)) {
         Integer var11 = var1.remaincostresetcount;
         if (var11 != null) {
            var2.writeInt32(2, var11);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.lastfreeresettime)) {
         Long var12 = var1.lastfreeresettime;
         if (var12 != null) {
            var2.writeInt64(3, var12);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var13 = var1.error;
         if (var13 != null) {
            var2.writeInt32(4, var13);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.goods)) {
         List var14 = var1.goods;
         if (var14 != null) {
            CodedConstant.writeToList(var2, 5, FieldType.OBJECT, var14, false);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.isrefresh)) {
         Boolean var15 = var1.isrefresh;
         if (var15 != null) {
            var2.writeBool(6, var15);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.shoptype)) {
         Integer var16 = var1.shoptype;
         if (var16 != null) {
            var2.writeInt32(7, var16);
         }
      }

   }

   public void writeTo(RES_GET_PRIVATESTORE_GOODSLIST var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_GET_PRIVATESTORE_GOODSLIST readFrom(CodedInputStream var1) throws IOException {
      RES_GET_PRIVATESTORE_GOODSLIST var2 = new RES_GET_PRIVATESTORE_GOODSLIST();
      var2.goods = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.shopid = var1.readInt32();
            } else if (var5 == 16) {
               var2.remaincostresetcount = var1.readInt32();
            } else if (var5 == 24) {
               var2.lastfreeresettime = var1.readInt64();
            } else if (var5 == 32) {
               var2.error = var1.readInt32();
            } else if (var5 == 42) {
               Codec var10 = ProtobufProxy.create(PT_PRIVATESTORE_GOODS.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.goods == null) {
                  var2.goods = new ArrayList();
               }

               var2.goods.add((PT_PRIVATESTORE_GOODS)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 48) {
               var2.isrefresh = var1.readBool();
            } else if (var5 == 56) {
               var2.shoptype = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_GET_PRIVATESTORE_GOODSLIST.class);
         return this.descriptor = var1;
      }
   }
}
