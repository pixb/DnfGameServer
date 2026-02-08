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

public class RES_GUILD_MEMBER_BANISH$$JProtoBufClass implements Codec<RES_GUILD_MEMBER_BANISH>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_GUILD_MEMBER_BANISH var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_GUILD_MEMBER_BANISH decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_GUILD_MEMBER_BANISH var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var9 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var9);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.gguid)) {
         Long var10 = var1.gguid;
         var2 += CodedOutputStream.computeUInt64Size(2, var10);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.targetguid)) {
         Long var11 = var1.targetguid;
         var2 += CodedOutputStream.computeUInt64Size(3, var11);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.kcount)) {
         Integer var12 = var1.kcount;
         var2 += CodedOutputStream.computeInt32Size(4, var12);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.gpoint)) {
         Integer var13 = var1.gpoint;
         var2 += CodedOutputStream.computeInt32Size(5, var13);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.decreaseguildcoin)) {
         Integer var14 = var1.decreaseguildcoin;
         var2 += CodedOutputStream.computeInt32Size(6, var14);
      }

      return var2;
   }

   public void doWriteTo(RES_GUILD_MEMBER_BANISH var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var9 = var1.error;
         if (var9 != null) {
            var2.writeInt32(1, var9);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.gguid)) {
         Long var10 = var1.gguid;
         if (var10 != null) {
            var2.writeUInt64(2, var10);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.targetguid)) {
         Long var11 = var1.targetguid;
         if (var11 != null) {
            var2.writeUInt64(3, var11);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.kcount)) {
         Integer var12 = var1.kcount;
         if (var12 != null) {
            var2.writeInt32(4, var12);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.gpoint)) {
         Integer var13 = var1.gpoint;
         if (var13 != null) {
            var2.writeInt32(5, var13);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.decreaseguildcoin)) {
         Integer var14 = var1.decreaseguildcoin;
         if (var14 != null) {
            var2.writeInt32(6, var14);
         }
      }

   }

   public void writeTo(RES_GUILD_MEMBER_BANISH var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_GUILD_MEMBER_BANISH readFrom(CodedInputStream var1) throws IOException {
      RES_GUILD_MEMBER_BANISH var2 = new RES_GUILD_MEMBER_BANISH();

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
               var2.gguid = var1.readUInt64();
            } else if (var5 == 24) {
               var2.targetguid = var1.readUInt64();
            } else if (var5 == 32) {
               var2.kcount = var1.readInt32();
            } else if (var5 == 40) {
               var2.gpoint = var1.readInt32();
            } else if (var5 == 48) {
               var2.decreaseguildcoin = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_GUILD_MEMBER_BANISH.class);
         return this.descriptor = var1;
      }
   }
}
