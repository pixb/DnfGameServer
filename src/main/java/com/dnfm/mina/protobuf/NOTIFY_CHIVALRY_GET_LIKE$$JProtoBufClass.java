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

public class NOTIFY_CHIVALRY_GET_LIKE$$JProtoBufClass implements Codec<NOTIFY_CHIVALRY_GET_LIKE>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(NOTIFY_CHIVALRY_GET_LIKE var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public NOTIFY_CHIVALRY_GET_LIKE decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(NOTIFY_CHIVALRY_GET_LIKE var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var10 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var10);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.dungeontype)) {
         Integer var11 = var1.dungeontype;
         var2 += CodedOutputStream.computeInt32Size(2, var11);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var12 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(3, var12);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.exp)) {
         Integer var13 = var1.exp;
         var2 += CodedOutputStream.computeInt32Size(4, var13);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.totallike)) {
         Integer var14 = var1.totallike;
         var2 += CodedOutputStream.computeInt32Size(5, var14);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.like)) {
         Integer var15 = var1.like;
         var2 += CodedOutputStream.computeInt32Size(6, var15);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.rank)) {
         Integer var16 = var1.rank;
         var2 += CodedOutputStream.computeInt32Size(7, var16);
      }

      return var2;
   }

   public void doWriteTo(NOTIFY_CHIVALRY_GET_LIKE var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var10 = var1.error;
         if (var10 != null) {
            var2.writeInt32(1, var10);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.dungeontype)) {
         Integer var11 = var1.dungeontype;
         if (var11 != null) {
            var2.writeInt32(2, var11);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var12 = var1.index;
         if (var12 != null) {
            var2.writeInt32(3, var12);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.exp)) {
         Integer var13 = var1.exp;
         if (var13 != null) {
            var2.writeInt32(4, var13);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.totallike)) {
         Integer var14 = var1.totallike;
         if (var14 != null) {
            var2.writeInt32(5, var14);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.like)) {
         Integer var15 = var1.like;
         if (var15 != null) {
            var2.writeInt32(6, var15);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.rank)) {
         Integer var16 = var1.rank;
         if (var16 != null) {
            var2.writeInt32(7, var16);
         }
      }

   }

   public void writeTo(NOTIFY_CHIVALRY_GET_LIKE var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public NOTIFY_CHIVALRY_GET_LIKE readFrom(CodedInputStream var1) throws IOException {
      NOTIFY_CHIVALRY_GET_LIKE var2 = new NOTIFY_CHIVALRY_GET_LIKE();

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
               var2.dungeontype = var1.readInt32();
            } else if (var5 == 24) {
               var2.index = var1.readInt32();
            } else if (var5 == 32) {
               var2.exp = var1.readInt32();
            } else if (var5 == 40) {
               var2.totallike = var1.readInt32();
            } else if (var5 == 48) {
               var2.like = var1.readInt32();
            } else if (var5 == 56) {
               var2.rank = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(NOTIFY_CHIVALRY_GET_LIKE.class);
         return this.descriptor = var1;
      }
   }
}
