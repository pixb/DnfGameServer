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

public class RES_HALF_OPEN_PARTY_ACCEPT$$JProtoBufClass implements Codec<RES_HALF_OPEN_PARTY_ACCEPT>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_HALF_OPEN_PARTY_ACCEPT var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_HALF_OPEN_PARTY_ACCEPT decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_HALF_OPEN_PARTY_ACCEPT var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var10 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var10);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.leaderguid)) {
         Long var11 = var1.leaderguid;
         var2 += CodedOutputStream.computeUInt64Size(2, var11);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var12 = var1.partyguid;
         var2 += CodedOutputStream.computeUInt64Size(3, var12);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.ip)) {
         String var13 = var1.ip;
         var2 += CodedOutputStream.computeStringSize(4, var13);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.port)) {
         Integer var14 = var1.port;
         var2 += CodedOutputStream.computeInt32Size(5, var14);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var15 = var1.world;
         var2 += CodedOutputStream.computeInt32Size(6, var15);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.channel)) {
         Integer var16 = var1.channel;
         var2 += CodedOutputStream.computeInt32Size(7, var16);
      }

      return var2;
   }

   public void doWriteTo(RES_HALF_OPEN_PARTY_ACCEPT var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var10 = var1.error;
         if (var10 != null) {
            var2.writeInt32(1, var10);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.leaderguid)) {
         Long var11 = var1.leaderguid;
         if (var11 != null) {
            var2.writeUInt64(2, var11);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var12 = var1.partyguid;
         if (var12 != null) {
            var2.writeUInt64(3, var12);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.ip)) {
         String var13 = var1.ip;
         if (var13 != null) {
            var2.writeString(4, var13);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.port)) {
         Integer var14 = var1.port;
         if (var14 != null) {
            var2.writeInt32(5, var14);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var15 = var1.world;
         if (var15 != null) {
            var2.writeInt32(6, var15);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.channel)) {
         Integer var16 = var1.channel;
         if (var16 != null) {
            var2.writeInt32(7, var16);
         }
      }

   }

   public void writeTo(RES_HALF_OPEN_PARTY_ACCEPT var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_HALF_OPEN_PARTY_ACCEPT readFrom(CodedInputStream var1) throws IOException {
      RES_HALF_OPEN_PARTY_ACCEPT var2 = new RES_HALF_OPEN_PARTY_ACCEPT();

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
               var2.leaderguid = var1.readUInt64();
            } else if (var5 == 24) {
               var2.partyguid = var1.readUInt64();
            } else if (var5 == 34) {
               var2.ip = var1.readString();
            } else if (var5 == 40) {
               var2.port = var1.readInt32();
            } else if (var5 == 48) {
               var2.world = var1.readInt32();
            } else if (var5 == 56) {
               var2.channel = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_HALF_OPEN_PARTY_ACCEPT.class);
         return this.descriptor = var1;
      }
   }
}
