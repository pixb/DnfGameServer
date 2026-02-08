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

public class RES_COMMUNITY_GIFT_OTHER_EFFECT$$JProtoBufClass implements Codec<RES_COMMUNITY_GIFT_OTHER_EFFECT>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_COMMUNITY_GIFT_OTHER_EFFECT var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_COMMUNITY_GIFT_OTHER_EFFECT decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_COMMUNITY_GIFT_OTHER_EFFECT var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var13 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var13);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.itemindex)) {
         Integer var14 = var1.itemindex;
         var2 += CodedOutputStream.computeInt32Size(2, var14);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var15 = var1.count;
         var2 += CodedOutputStream.computeInt32Size(3, var15);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var16 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(4, var16);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.sender)) {
         String var17 = var1.sender;
         var2 += CodedOutputStream.computeStringSize(5, var17);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.receiver)) {
         String var18 = var1.receiver;
         var2 += CodedOutputStream.computeStringSize(6, var18);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.receiverjob)) {
         Integer var19 = var1.receiverjob;
         var2 += CodedOutputStream.computeInt32Size(7, var19);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.receiverlevel)) {
         Integer var20 = var1.receiverlevel;
         var2 += CodedOutputStream.computeInt32Size(8, var20);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.hongbaocount)) {
         Integer var21 = var1.hongbaocount;
         var2 += CodedOutputStream.computeInt32Size(9, var21);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.packagingindex)) {
         Integer var22 = var1.packagingindex;
         var2 += CodedOutputStream.computeInt32Size(10, var22);
      }

      return var2;
   }

   public void doWriteTo(RES_COMMUNITY_GIFT_OTHER_EFFECT var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var13 = var1.error;
         if (var13 != null) {
            var2.writeInt32(1, var13);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.itemindex)) {
         Integer var14 = var1.itemindex;
         if (var14 != null) {
            var2.writeInt32(2, var14);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var15 = var1.count;
         if (var15 != null) {
            var2.writeInt32(3, var15);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var16 = var1.charguid;
         if (var16 != null) {
            var2.writeUInt64(4, var16);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.sender)) {
         String var17 = var1.sender;
         if (var17 != null) {
            var2.writeString(5, var17);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.receiver)) {
         String var18 = var1.receiver;
         if (var18 != null) {
            var2.writeString(6, var18);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.receiverjob)) {
         Integer var19 = var1.receiverjob;
         if (var19 != null) {
            var2.writeInt32(7, var19);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.receiverlevel)) {
         Integer var20 = var1.receiverlevel;
         if (var20 != null) {
            var2.writeInt32(8, var20);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.hongbaocount)) {
         Integer var21 = var1.hongbaocount;
         if (var21 != null) {
            var2.writeInt32(9, var21);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.packagingindex)) {
         Integer var22 = var1.packagingindex;
         if (var22 != null) {
            var2.writeInt32(10, var22);
         }
      }

   }

   public void writeTo(RES_COMMUNITY_GIFT_OTHER_EFFECT var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_COMMUNITY_GIFT_OTHER_EFFECT readFrom(CodedInputStream var1) throws IOException {
      RES_COMMUNITY_GIFT_OTHER_EFFECT var2 = new RES_COMMUNITY_GIFT_OTHER_EFFECT();

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
               var2.itemindex = var1.readInt32();
            } else if (var5 == 24) {
               var2.count = var1.readInt32();
            } else if (var5 == 32) {
               var2.charguid = var1.readUInt64();
            } else if (var5 == 42) {
               var2.sender = var1.readString();
            } else if (var5 == 50) {
               var2.receiver = var1.readString();
            } else if (var5 == 56) {
               var2.receiverjob = var1.readInt32();
            } else if (var5 == 64) {
               var2.receiverlevel = var1.readInt32();
            } else if (var5 == 72) {
               var2.hongbaocount = var1.readInt32();
            } else if (var5 == 80) {
               var2.packagingindex = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_COMMUNITY_GIFT_OTHER_EFFECT.class);
         return this.descriptor = var1;
      }
   }
}
