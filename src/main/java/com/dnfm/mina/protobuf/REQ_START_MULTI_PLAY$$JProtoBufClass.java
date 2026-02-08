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

public class REQ_START_MULTI_PLAY$$JProtoBufClass implements Codec<REQ_START_MULTI_PLAY>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_START_MULTI_PLAY var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_START_MULTI_PLAY decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_START_MULTI_PLAY var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.input)) {
         Integer var11 = var1.input;
         var2 += CodedOutputStream.computeInt32Size(1, var11);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var12 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(2, var12);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var13 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(3, var13);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var14 = var1.grade;
         var2 += CodedOutputStream.computeInt32Size(4, var14);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var15 = var1.partyguid;
         var2 += CodedOutputStream.computeUInt64Size(5, var15);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.position)) {
         Integer var16 = var1.position;
         var2 += CodedOutputStream.computeInt32Size(6, var16);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.cardindex)) {
         Integer var17 = var1.cardindex;
         var2 += CodedOutputStream.computeInt32Size(7, var17);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.entranceitemindex)) {
         Integer var18 = var1.entranceitemindex;
         var2 += CodedOutputStream.computeInt32Size(8, var18);
      }

      return var2;
   }

   public void doWriteTo(REQ_START_MULTI_PLAY var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.input)) {
         Integer var11 = var1.input;
         if (var11 != null) {
            var2.writeInt32(1, var11);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var12 = var1.type;
         if (var12 != null) {
            var2.writeInt32(2, var12);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var13 = var1.index;
         if (var13 != null) {
            var2.writeInt32(3, var13);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var14 = var1.grade;
         if (var14 != null) {
            var2.writeInt32(4, var14);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var15 = var1.partyguid;
         if (var15 != null) {
            var2.writeUInt64(5, var15);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.position)) {
         Integer var16 = var1.position;
         if (var16 != null) {
            var2.writeInt32(6, var16);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.cardindex)) {
         Integer var17 = var1.cardindex;
         if (var17 != null) {
            var2.writeInt32(7, var17);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.entranceitemindex)) {
         Integer var18 = var1.entranceitemindex;
         if (var18 != null) {
            var2.writeInt32(8, var18);
         }
      }

   }

   public void writeTo(REQ_START_MULTI_PLAY var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_START_MULTI_PLAY readFrom(CodedInputStream var1) throws IOException {
      REQ_START_MULTI_PLAY var2 = new REQ_START_MULTI_PLAY();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.input = var1.readInt32();
            } else if (var5 == 16) {
               var2.type = var1.readInt32();
            } else if (var5 == 24) {
               var2.index = var1.readInt32();
            } else if (var5 == 32) {
               var2.grade = var1.readInt32();
            } else if (var5 == 40) {
               var2.partyguid = var1.readUInt64();
            } else if (var5 == 48) {
               var2.position = var1.readInt32();
            } else if (var5 == 56) {
               var2.cardindex = var1.readInt32();
            } else if (var5 == 64) {
               var2.entranceitemindex = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_START_MULTI_PLAY.class);
         return this.descriptor = var1;
      }
   }
}
