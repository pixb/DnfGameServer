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

public class NOTIFY_GUILD_PROHIBIT$$JProtoBufClass implements Codec<NOTIFY_GUILD_PROHIBIT>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(NOTIFY_GUILD_PROHIBIT var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public NOTIFY_GUILD_PROHIBIT decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(NOTIFY_GUILD_PROHIBIT var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.gguid)) {
         Long var8 = var1.gguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var8);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var9 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(2, var9);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.subtype)) {
         Integer var10 = var1.subtype;
         var2 += CodedOutputStream.computeInt32Size(3, var10);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.endtime)) {
         Long var11 = var1.endtime;
         var2 += CodedOutputStream.computeInt64Size(4, var11);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.reason)) {
         String var12 = var1.reason;
         var2 += CodedOutputStream.computeStringSize(5, var12);
      }

      return var2;
   }

   public void doWriteTo(NOTIFY_GUILD_PROHIBIT var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.gguid)) {
         Long var8 = var1.gguid;
         if (var8 != null) {
            var2.writeUInt64(1, var8);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var9 = var1.type;
         if (var9 != null) {
            var2.writeInt32(2, var9);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.subtype)) {
         Integer var10 = var1.subtype;
         if (var10 != null) {
            var2.writeInt32(3, var10);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.endtime)) {
         Long var11 = var1.endtime;
         if (var11 != null) {
            var2.writeInt64(4, var11);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.reason)) {
         String var12 = var1.reason;
         if (var12 != null) {
            var2.writeString(5, var12);
         }
      }

   }

   public void writeTo(NOTIFY_GUILD_PROHIBIT var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public NOTIFY_GUILD_PROHIBIT readFrom(CodedInputStream var1) throws IOException {
      NOTIFY_GUILD_PROHIBIT var2 = new NOTIFY_GUILD_PROHIBIT();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.gguid = var1.readUInt64();
            } else if (var5 == 16) {
               var2.type = var1.readInt32();
            } else if (var5 == 24) {
               var2.subtype = var1.readInt32();
            } else if (var5 == 32) {
               var2.endtime = var1.readInt64();
            } else if (var5 == 42) {
               var2.reason = var1.readString();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(NOTIFY_GUILD_PROHIBIT.class);
         return this.descriptor = var1;
      }
   }
}
