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

public class REQ_COMMUNITY_GIFT_SEND$$JProtoBufClass implements Codec<REQ_COMMUNITY_GIFT_SEND>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_COMMUNITY_GIFT_SEND var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_COMMUNITY_GIFT_SEND decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_COMMUNITY_GIFT_SEND var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.targetguid)) {
         Long var12 = var1.targetguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var12);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.goodsid)) {
         Integer var13 = var1.goodsid;
         var2 += CodedOutputStream.computeInt32Size(2, var13);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var14 = var1.count;
         var2 += CodedOutputStream.computeInt32Size(3, var14);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.buycount)) {
         Integer var15 = var1.buycount;
         var2 += CodedOutputStream.computeInt32Size(4, var15);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.msg)) {
         String var16 = var1.msg;
         var2 += CodedOutputStream.computeStringSize(5, var16);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.packagingindex)) {
         Integer var17 = var1.packagingindex;
         var2 += CodedOutputStream.computeInt32Size(6, var17);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.openkey)) {
         String var18 = var1.openkey;
         var2 += CodedOutputStream.computeStringSize(7, var18);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.paytoken)) {
         String var19 = var1.paytoken;
         var2 += CodedOutputStream.computeStringSize(8, var19);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.itemindex)) {
         Integer var20 = var1.itemindex;
         var2 += CodedOutputStream.computeInt32Size(9, var20);
      }

      return var2;
   }

   public void doWriteTo(REQ_COMMUNITY_GIFT_SEND var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.targetguid)) {
         Long var12 = var1.targetguid;
         if (var12 != null) {
            var2.writeUInt64(1, var12);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.goodsid)) {
         Integer var13 = var1.goodsid;
         if (var13 != null) {
            var2.writeInt32(2, var13);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var14 = var1.count;
         if (var14 != null) {
            var2.writeInt32(3, var14);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.buycount)) {
         Integer var15 = var1.buycount;
         if (var15 != null) {
            var2.writeInt32(4, var15);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.msg)) {
         String var16 = var1.msg;
         if (var16 != null) {
            var2.writeString(5, var16);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.packagingindex)) {
         Integer var17 = var1.packagingindex;
         if (var17 != null) {
            var2.writeInt32(6, var17);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.openkey)) {
         String var18 = var1.openkey;
         if (var18 != null) {
            var2.writeString(7, var18);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.paytoken)) {
         String var19 = var1.paytoken;
         if (var19 != null) {
            var2.writeString(8, var19);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.itemindex)) {
         Integer var20 = var1.itemindex;
         if (var20 != null) {
            var2.writeInt32(9, var20);
         }
      }

   }

   public void writeTo(REQ_COMMUNITY_GIFT_SEND var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_COMMUNITY_GIFT_SEND readFrom(CodedInputStream var1) throws IOException {
      REQ_COMMUNITY_GIFT_SEND var2 = new REQ_COMMUNITY_GIFT_SEND();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.targetguid = var1.readUInt64();
            } else if (var5 == 16) {
               var2.goodsid = var1.readInt32();
            } else if (var5 == 24) {
               var2.count = var1.readInt32();
            } else if (var5 == 32) {
               var2.buycount = var1.readInt32();
            } else if (var5 == 42) {
               var2.msg = var1.readString();
            } else if (var5 == 48) {
               var2.packagingindex = var1.readInt32();
            } else if (var5 == 58) {
               var2.openkey = var1.readString();
            } else if (var5 == 66) {
               var2.paytoken = var1.readString();
            } else if (var5 == 72) {
               var2.itemindex = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_COMMUNITY_GIFT_SEND.class);
         return this.descriptor = var1;
      }
   }
}
