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

public class RES_GUILD_AD_MESSAGE$$JProtoBufClass implements Codec<RES_GUILD_AD_MESSAGE>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_GUILD_AD_MESSAGE var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_GUILD_AD_MESSAGE decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_GUILD_AD_MESSAGE var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var11 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var11);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.area)) {
         Integer var12 = var1.area;
         var2 += CodedOutputStream.computeInt32Size(2, var12);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.freejoin)) {
         Boolean var13 = var1.freejoin;
         var2 += CodedOutputStream.computeBoolSize(3, var13);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.gguid)) {
         Long var14 = var1.gguid;
         var2 += CodedOutputStream.computeUInt64Size(4, var14);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.minlevel)) {
         Integer var15 = var1.minlevel;
         var2 += CodedOutputStream.computeInt32Size(5, var15);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.msg)) {
         String var16 = var1.msg;
         var2 += CodedOutputStream.computeStringSize(6, var16);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var17 = var1.name;
         var2 += CodedOutputStream.computeStringSize(7, var17);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.prohibittime)) {
         Long var18 = var1.prohibittime;
         var2 += CodedOutputStream.computeInt64Size(8, var18);
      }

      return var2;
   }

   public void doWriteTo(RES_GUILD_AD_MESSAGE var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var11 = var1.error;
         if (var11 != null) {
            var2.writeInt32(1, var11);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.area)) {
         Integer var12 = var1.area;
         if (var12 != null) {
            var2.writeInt32(2, var12);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.freejoin)) {
         Boolean var13 = var1.freejoin;
         if (var13 != null) {
            var2.writeBool(3, var13);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.gguid)) {
         Long var14 = var1.gguid;
         if (var14 != null) {
            var2.writeUInt64(4, var14);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.minlevel)) {
         Integer var15 = var1.minlevel;
         if (var15 != null) {
            var2.writeInt32(5, var15);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.msg)) {
         String var16 = var1.msg;
         if (var16 != null) {
            var2.writeString(6, var16);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var17 = var1.name;
         if (var17 != null) {
            var2.writeString(7, var17);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.prohibittime)) {
         Long var18 = var1.prohibittime;
         if (var18 != null) {
            var2.writeInt64(8, var18);
         }
      }

   }

   public void writeTo(RES_GUILD_AD_MESSAGE var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_GUILD_AD_MESSAGE readFrom(CodedInputStream var1) throws IOException {
      RES_GUILD_AD_MESSAGE var2 = new RES_GUILD_AD_MESSAGE();

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
               var2.area = var1.readInt32();
            } else if (var5 == 24) {
               var2.freejoin = var1.readBool();
            } else if (var5 == 32) {
               var2.gguid = var1.readUInt64();
            } else if (var5 == 40) {
               var2.minlevel = var1.readInt32();
            } else if (var5 == 50) {
               var2.msg = var1.readString();
            } else if (var5 == 58) {
               var2.name = var1.readString();
            } else if (var5 == 64) {
               var2.prohibittime = var1.readInt64();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_GUILD_AD_MESSAGE.class);
         return this.descriptor = var1;
      }
   }
}
