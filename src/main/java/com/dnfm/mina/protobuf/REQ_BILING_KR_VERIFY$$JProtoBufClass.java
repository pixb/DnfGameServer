package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.code.CodedConstant;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.InvalidProtocolBufferException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class REQ_BILING_KR_VERIFY$$JProtoBufClass implements Codec<REQ_BILING_KR_VERIFY>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_BILING_KR_VERIFY var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_BILING_KR_VERIFY decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_BILING_KR_VERIFY var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.stampToken)) {
         String var9 = var1.stampToken;
         var2 += CodedOutputStream.computeStringSize(1, var9);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.productId)) {
         String var10 = var1.productId;
         var2 += CodedOutputStream.computeStringSize(2, var10);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.goodsId)) {
         Integer var11 = var1.goodsId;
         var2 += CodedOutputStream.computeInt32Size(3, var11);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.currency)) {
         String var12 = var1.currency;
         var2 += CodedOutputStream.computeStringSize(4, var12);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.adid)) {
         String var13 = var1.adid;
         var2 += CodedOutputStream.computeStringSize(5, var13);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.idfv)) {
         String var14 = var1.idfv;
         var2 += CodedOutputStream.computeStringSize(6, var14);
      }

      return var2;
   }

   public void doWriteTo(REQ_BILING_KR_VERIFY var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.stampToken)) {
         String var9 = var1.stampToken;
         if (var9 != null) {
            var2.writeString(1, var9);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.productId)) {
         String var10 = var1.productId;
         if (var10 != null) {
            var2.writeString(2, var10);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.goodsId)) {
         Integer var11 = var1.goodsId;
         if (var11 != null) {
            var2.writeInt32(3, var11);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.currency)) {
         String var12 = var1.currency;
         if (var12 != null) {
            var2.writeString(4, var12);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.adid)) {
         String var13 = var1.adid;
         if (var13 != null) {
            var2.writeString(5, var13);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.idfv)) {
         String var14 = var1.idfv;
         if (var14 != null) {
            var2.writeString(6, var14);
         }
      }

   }

   public void writeTo(REQ_BILING_KR_VERIFY var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_BILING_KR_VERIFY readFrom(CodedInputStream var1) throws IOException {
      REQ_BILING_KR_VERIFY var2 = new REQ_BILING_KR_VERIFY();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 10) {
               var2.stampToken = var1.readString();
            } else if (var5 == 18) {
               var2.productId = var1.readString();
            } else if (var5 == 24) {
               var2.goodsId = var1.readInt32();
            } else if (var5 == 34) {
               var2.currency = var1.readString();
            } else if (var5 == 42) {
               var2.adid = var1.readString();
            } else if (var5 == 50) {
               var2.idfv = var1.readString();
            } else {
               var1.skipField(var5);
            }
         }

         return var2;
      } catch (InvalidProtocolBufferException var6) {
         throw var6;
      } catch (IOException var7) {
         throw var7;
      }
   }

   public Descriptors.Descriptor getDescriptor() throws IOException {
      if (this.descriptor != null) {
         return this.descriptor;
      } else {
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_BILING_KR_VERIFY.class);
         return this.descriptor = var1;
      }
   }
}
