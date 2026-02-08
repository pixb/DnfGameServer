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

public class RES_GUILD_DONATION_REQUEST_HELP$$JProtoBufClass implements Codec<RES_GUILD_DONATION_REQUEST_HELP>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_GUILD_DONATION_REQUEST_HELP var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_GUILD_DONATION_REQUEST_HELP decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_GUILD_DONATION_REQUEST_HELP var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var10 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var10);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.helpguid)) {
         Long var11 = var1.helpguid;
         var2 += CodedOutputStream.computeUInt64Size(2, var11);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var12 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(3, var12);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.itemindex)) {
         Integer var13 = var1.itemindex;
         var2 += CodedOutputStream.computeInt32Size(4, var13);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var14 = var1.count;
         var2 += CodedOutputStream.computeInt32Size(5, var14);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.chatguid)) {
         Long var15 = var1.chatguid;
         var2 += CodedOutputStream.computeInt64Size(6, var15);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.requestcount)) {
         Integer var16 = var1.requestcount;
         var2 += CodedOutputStream.computeInt32Size(7, var16);
      }

      return var2;
   }

   public void doWriteTo(RES_GUILD_DONATION_REQUEST_HELP var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var10 = var1.error;
         if (var10 != null) {
            var2.writeInt32(1, var10);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.helpguid)) {
         Long var11 = var1.helpguid;
         if (var11 != null) {
            var2.writeUInt64(2, var11);
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
      if (!CodedConstant.isNull(var1.itemindex)) {
         Integer var13 = var1.itemindex;
         if (var13 != null) {
            var2.writeInt32(4, var13);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var14 = var1.count;
         if (var14 != null) {
            var2.writeInt32(5, var14);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.chatguid)) {
         Long var15 = var1.chatguid;
         if (var15 != null) {
            var2.writeInt64(6, var15);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.requestcount)) {
         Integer var16 = var1.requestcount;
         if (var16 != null) {
            var2.writeInt32(7, var16);
         }
      }

   }

   public void writeTo(RES_GUILD_DONATION_REQUEST_HELP var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_GUILD_DONATION_REQUEST_HELP readFrom(CodedInputStream var1) throws IOException {
      RES_GUILD_DONATION_REQUEST_HELP var2 = new RES_GUILD_DONATION_REQUEST_HELP();

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
               var2.helpguid = var1.readUInt64();
            } else if (var5 == 24) {
               var2.index = var1.readInt32();
            } else if (var5 == 32) {
               var2.itemindex = var1.readInt32();
            } else if (var5 == 40) {
               var2.count = var1.readInt32();
            } else if (var5 == 48) {
               var2.chatguid = var1.readInt64();
            } else if (var5 == 56) {
               var2.requestcount = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_GUILD_DONATION_REQUEST_HELP.class);
         return this.descriptor = var1;
      }
   }
}
