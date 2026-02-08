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

public class RES_PROMOTION_INFO$$JProtoBufClass implements Codec<RES_PROMOTION_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_PROMOTION_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_PROMOTION_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_PROMOTION_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var12 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var12);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.msg)) {
         String var13 = var1.msg;
         var2 += CodedOutputStream.computeStringSize(2, var13);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.wxPay)) {
         Integer var14 = var1.wxPay;
         var2 += CodedOutputStream.computeInt32Size(3, var14);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.kjPay)) {
         Integer var15 = var1.kjPay;
         var2 += CodedOutputStream.computeInt32Size(4, var15);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.qualified)) {
         Integer var16 = var1.qualified;
         var2 += CodedOutputStream.computeInt32Size(5, var16);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.qualifiedInfo)) {
         String var17 = var1.qualifiedInfo;
         var2 += CodedOutputStream.computeStringSize(6, var17);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.isShowActPage)) {
         Integer var18 = var1.isShowActPage;
         var2 += CodedOutputStream.computeInt32Size(7, var18);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.discounttype)) {
         String var19 = var1.discounttype;
         var2 += CodedOutputStream.computeStringSize(8, var19);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.discounturl)) {
         String var20 = var1.discounturl;
         var2 += CodedOutputStream.computeStringSize(9, var20);
      }

      return var2;
   }

   public void doWriteTo(RES_PROMOTION_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var12 = var1.error;
         if (var12 != null) {
            var2.writeInt32(1, var12);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.msg)) {
         String var13 = var1.msg;
         if (var13 != null) {
            var2.writeString(2, var13);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.wxPay)) {
         Integer var14 = var1.wxPay;
         if (var14 != null) {
            var2.writeInt32(3, var14);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.kjPay)) {
         Integer var15 = var1.kjPay;
         if (var15 != null) {
            var2.writeInt32(4, var15);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.qualified)) {
         Integer var16 = var1.qualified;
         if (var16 != null) {
            var2.writeInt32(5, var16);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.qualifiedInfo)) {
         String var17 = var1.qualifiedInfo;
         if (var17 != null) {
            var2.writeString(6, var17);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.isShowActPage)) {
         Integer var18 = var1.isShowActPage;
         if (var18 != null) {
            var2.writeInt32(7, var18);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.discounttype)) {
         String var19 = var1.discounttype;
         if (var19 != null) {
            var2.writeString(8, var19);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.discounturl)) {
         String var20 = var1.discounturl;
         if (var20 != null) {
            var2.writeString(9, var20);
         }
      }

   }

   public void writeTo(RES_PROMOTION_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_PROMOTION_INFO readFrom(CodedInputStream var1) throws IOException {
      RES_PROMOTION_INFO var2 = new RES_PROMOTION_INFO();

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
               var2.msg = var1.readString();
            } else if (var5 == 24) {
               var2.wxPay = var1.readInt32();
            } else if (var5 == 32) {
               var2.kjPay = var1.readInt32();
            } else if (var5 == 40) {
               var2.qualified = var1.readInt32();
            } else if (var5 == 50) {
               var2.qualifiedInfo = var1.readString();
            } else if (var5 == 56) {
               var2.isShowActPage = var1.readInt32();
            } else if (var5 == 66) {
               var2.discounttype = var1.readString();
            } else if (var5 == 74) {
               var2.discounturl = var1.readString();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_PROMOTION_INFO.class);
         return this.descriptor = var1;
      }
   }
}
