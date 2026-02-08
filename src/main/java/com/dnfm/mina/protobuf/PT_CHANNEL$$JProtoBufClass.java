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

public class PT_CHANNEL$$JProtoBufClass implements Codec<PT_CHANNEL>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_CHANNEL var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_CHANNEL decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_CHANNEL var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var9 = var1.world;
         var2 += CodedOutputStream.computeInt32Size(1, var9);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.channel)) {
         Integer var10 = var1.channel;
         var2 += CodedOutputStream.computeInt32Size(2, var10);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.iP)) {
         String var11 = var1.iP;
         var2 += CodedOutputStream.computeStringSize(3, var11);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.port)) {
         Integer var12 = var1.port;
         var2 += CodedOutputStream.computeInt32Size(4, var12);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.saturation)) {
         Integer var13 = var1.saturation;
         var2 += CodedOutputStream.computeInt32Size(5, var13);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.integration)) {
         Boolean var14 = var1.integration;
         var2 += CodedOutputStream.computeBoolSize(6, var14);
      }

      return var2;
   }

   public void doWriteTo(PT_CHANNEL var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var9 = var1.world;
         if (var9 != null) {
            var2.writeInt32(1, var9);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.channel)) {
         Integer var10 = var1.channel;
         if (var10 != null) {
            var2.writeInt32(2, var10);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.iP)) {
         String var11 = var1.iP;
         if (var11 != null) {
            var2.writeString(3, var11);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.port)) {
         Integer var12 = var1.port;
         if (var12 != null) {
            var2.writeInt32(4, var12);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.saturation)) {
         Integer var13 = var1.saturation;
         if (var13 != null) {
            var2.writeInt32(5, var13);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.integration)) {
         Boolean var14 = var1.integration;
         if (var14 != null) {
            var2.writeBool(6, var14);
         }
      }

   }

   public void writeTo(PT_CHANNEL var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_CHANNEL readFrom(CodedInputStream var1) throws IOException {
      PT_CHANNEL var2 = new PT_CHANNEL();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.world = var1.readInt32();
            } else if (var5 == 16) {
               var2.channel = var1.readInt32();
            } else if (var5 == 26) {
               var2.iP = var1.readString();
            } else if (var5 == 32) {
               var2.port = var1.readInt32();
            } else if (var5 == 40) {
               var2.saturation = var1.readInt32();
            } else if (var5 == 48) {
               var2.integration = var1.readBool();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_CHANNEL.class);
         return this.descriptor = var1;
      }
   }
}
