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

public class RES_ITEM_AVR_PRICE$$JProtoBufClass implements Codec<RES_ITEM_AVR_PRICE>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_ITEM_AVR_PRICE var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_ITEM_AVR_PRICE decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_ITEM_AVR_PRICE var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var11 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var11);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var12 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(2, var12);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.price)) {
         Integer var13 = var1.price;
         var2 += CodedOutputStream.computeInt32Size(3, var13);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.prices)) {
         List var14 = var1.prices;
         var2 += CodedConstant.computeListSize(4, var14, FieldType.INT32, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.upgrade)) {
         Integer var15 = var1.upgrade;
         var2 += CodedOutputStream.computeInt32Size(5, var15);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.amplify)) {
         Integer var16 = var1.amplify;
         var2 += CodedOutputStream.computeInt32Size(6, var16);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.reforge)) {
         Integer var17 = var1.reforge;
         var2 += CodedOutputStream.computeInt32Size(7, var17);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.time)) {
         Long var18 = var1.time;
         var2 += CodedOutputStream.computeInt64Size(8, var18);
      }

      return var2;
   }

   public void doWriteTo(RES_ITEM_AVR_PRICE var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var11 = var1.error;
         if (var11 != null) {
            var2.writeInt32(1, var11);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var12 = var1.index;
         if (var12 != null) {
            var2.writeInt32(2, var12);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.price)) {
         Integer var13 = var1.price;
         if (var13 != null) {
            var2.writeInt32(3, var13);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.prices)) {
         List var14 = var1.prices;
         if (var14 != null) {
            CodedConstant.writeToList(var2, 4, FieldType.INT32, var14, true);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.upgrade)) {
         Integer var15 = var1.upgrade;
         if (var15 != null) {
            var2.writeInt32(5, var15);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.amplify)) {
         Integer var16 = var1.amplify;
         if (var16 != null) {
            var2.writeInt32(6, var16);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.reforge)) {
         Integer var17 = var1.reforge;
         if (var17 != null) {
            var2.writeInt32(7, var17);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.time)) {
         Long var18 = var1.time;
         if (var18 != null) {
            var2.writeInt64(8, var18);
         }
      }

   }

   public void writeTo(RES_ITEM_AVR_PRICE var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_ITEM_AVR_PRICE readFrom(CodedInputStream var1) throws IOException {
      RES_ITEM_AVR_PRICE var2 = new RES_ITEM_AVR_PRICE();
      var2.prices = new ArrayList();

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
               var2.index = var1.readInt32();
            } else if (var5 == 24) {
               var2.price = var1.readInt32();
            } else if (var5 == 32) {
               if (var2.prices == null) {
                  var2.prices = new ArrayList();
               }

               var2.prices.add(var1.readInt32());
            } else if (var5 != 34) {
               if (var5 == 40) {
                  var2.upgrade = var1.readInt32();
               } else if (var5 == 48) {
                  var2.amplify = var1.readInt32();
               } else if (var5 == 56) {
                  var2.reforge = var1.readInt32();
               } else if (var5 == 64) {
                  var2.time = var1.readInt64();
               } else {
                  var1.skipField(var5);
               }
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.prices == null) {
                  var2.prices = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.prices.add(var1.readInt32());
               }

               var1.popLimit(var7);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_ITEM_AVR_PRICE.class);
         return this.descriptor = var1;
      }
   }
}
