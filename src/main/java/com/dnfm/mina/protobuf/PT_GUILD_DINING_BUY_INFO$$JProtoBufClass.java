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

public class PT_GUILD_DINING_BUY_INFO$$JProtoBufClass implements Codec<PT_GUILD_DINING_BUY_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_GUILD_DINING_BUY_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_GUILD_DINING_BUY_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_GUILD_DINING_BUY_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         String var9 = var1.charguid;
         var2 += CodedOutputStream.computeStringSize(1, var9);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.charname)) {
         String var10 = var1.charname;
         var2 += CodedOutputStream.computeStringSize(2, var10);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.appendageindex)) {
         Integer var11 = var1.appendageindex;
         var2 += CodedOutputStream.computeInt32Size(3, var11);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.activate)) {
         Boolean var12 = var1.activate;
         var2 += CodedOutputStream.computeBoolSize(4, var12);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.groupindex)) {
         Integer var13 = var1.groupindex;
         var2 += CodedOutputStream.computeInt32Size(5, var13);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var14 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(6, var14);
      }

      return var2;
   }

   public void doWriteTo(PT_GUILD_DINING_BUY_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         String var9 = var1.charguid;
         if (var9 != null) {
            var2.writeString(1, var9);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.charname)) {
         String var10 = var1.charname;
         if (var10 != null) {
            var2.writeString(2, var10);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.appendageindex)) {
         Integer var11 = var1.appendageindex;
         if (var11 != null) {
            var2.writeInt32(3, var11);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.activate)) {
         Boolean var12 = var1.activate;
         if (var12 != null) {
            var2.writeBool(4, var12);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.groupindex)) {
         Integer var13 = var1.groupindex;
         if (var13 != null) {
            var2.writeInt32(5, var13);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var14 = var1.index;
         if (var14 != null) {
            var2.writeInt32(6, var14);
         }
      }

   }

   public void writeTo(PT_GUILD_DINING_BUY_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_GUILD_DINING_BUY_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_GUILD_DINING_BUY_INFO var2 = new PT_GUILD_DINING_BUY_INFO();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 10) {
               var2.charguid = var1.readString();
            } else if (var5 == 18) {
               var2.charname = var1.readString();
            } else if (var5 == 24) {
               var2.appendageindex = var1.readInt32();
            } else if (var5 == 32) {
               var2.activate = var1.readBool();
            } else if (var5 == 40) {
               var2.groupindex = var1.readInt32();
            } else if (var5 == 48) {
               var2.index = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_GUILD_DINING_BUY_INFO.class);
         return this.descriptor = var1;
      }
   }
}
