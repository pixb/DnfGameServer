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

public class REQ_CERA_SHOP_BUY$$JProtoBufClass implements Codec<REQ_CERA_SHOP_BUY>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_CERA_SHOP_BUY var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_CERA_SHOP_BUY decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_CERA_SHOP_BUY var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.shopid)) {
         Integer var7 = var1.shopid;
         var2 += CodedOutputStream.computeInt32Size(1, var7);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.openkey)) {
         String var8 = var1.openkey;
         var2 += CodedOutputStream.computeStringSize(2, var8);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.paytoken)) {
         String var9 = var1.paytoken;
         var2 += CodedOutputStream.computeStringSize(3, var9);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.buy)) {
         List var10 = var1.buy;
         var2 += CodedConstant.computeListSize(4, var10, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(REQ_CERA_SHOP_BUY var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.shopid)) {
         Integer var7 = var1.shopid;
         if (var7 != null) {
            var2.writeInt32(1, var7);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.openkey)) {
         String var8 = var1.openkey;
         if (var8 != null) {
            var2.writeString(2, var8);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.paytoken)) {
         String var9 = var1.paytoken;
         if (var9 != null) {
            var2.writeString(3, var9);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.buy)) {
         List var10 = var1.buy;
         if (var10 != null) {
            CodedConstant.writeToList(var2, 4, FieldType.OBJECT, var10, false);
         }
      }

   }

   public void writeTo(REQ_CERA_SHOP_BUY var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_CERA_SHOP_BUY readFrom(CodedInputStream var1) throws IOException {
      REQ_CERA_SHOP_BUY var2 = new REQ_CERA_SHOP_BUY();
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
               var2.shopid = var1.readInt32();
            } else if (var5 == 18) {
               var2.openkey = var1.readString();
            } else if (var5 == 26) {
               var2.paytoken = var1.readString();
            } else if (var5 == 34) {
               Codec var10 = ProtobufProxy.create(PT_CERA_SHOP_BUY.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.buy == null) {
                  var2.buy = new ArrayList();
               }

               var2.buy.add((PT_CERA_SHOP_BUY)var10.readFrom(var1));
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_CERA_SHOP_BUY.class);
         return this.descriptor = var1;
      }
   }
}
