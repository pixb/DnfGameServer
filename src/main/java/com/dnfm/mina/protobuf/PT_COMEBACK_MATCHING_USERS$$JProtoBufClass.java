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

public class PT_COMEBACK_MATCHING_USERS$$JProtoBufClass implements Codec<PT_COMEBACK_MATCHING_USERS>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_COMEBACK_MATCHING_USERS var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_COMEBACK_MATCHING_USERS decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_COMEBACK_MATCHING_USERS var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.channel)) {
         Integer var8 = var1.channel;
         var2 += CodedOutputStream.computeInt32Size(1, var8);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.ip)) {
         String var9 = var1.ip;
         var2 += CodedOutputStream.computeStringSize(2, var9);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.port)) {
         Integer var10 = var1.port;
         var2 += CodedOutputStream.computeUInt32Size(3, var10);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var11 = var1.world;
         var2 += CodedOutputStream.computeInt32Size(4, var11);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var12 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(5, var12);
      }

      return var2;
   }

   public void doWriteTo(PT_COMEBACK_MATCHING_USERS var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.channel)) {
         Integer var8 = var1.channel;
         if (var8 != null) {
            var2.writeInt32(1, var8);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.ip)) {
         String var9 = var1.ip;
         if (var9 != null) {
            var2.writeString(2, var9);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.port)) {
         Integer var10 = var1.port;
         if (var10 != null) {
            var2.writeUInt32(3, var10);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var11 = var1.world;
         if (var11 != null) {
            var2.writeInt32(4, var11);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var12 = var1.charguid;
         if (var12 != null) {
            var2.writeUInt64(5, var12);
         }
      }

   }

   public void writeTo(PT_COMEBACK_MATCHING_USERS var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_COMEBACK_MATCHING_USERS readFrom(CodedInputStream var1) throws IOException {
      PT_COMEBACK_MATCHING_USERS var2 = new PT_COMEBACK_MATCHING_USERS();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.channel = var1.readInt32();
            } else if (var5 == 18) {
               var2.ip = var1.readString();
            } else if (var5 == 24) {
               var2.port = var1.readUInt32();
            } else if (var5 == 32) {
               var2.world = var1.readInt32();
            } else if (var5 == 40) {
               var2.charguid = var1.readUInt64();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_COMEBACK_MATCHING_USERS.class);
         return this.descriptor = var1;
      }
   }
}
