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

public class PT_GUILD_DONATION_HELP_INFO$$JProtoBufClass implements Codec<PT_GUILD_DONATION_HELP_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_GUILD_DONATION_HELP_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_GUILD_DONATION_HELP_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_GUILD_DONATION_HELP_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var11 = var1.guid;
         var2 += CodedOutputStream.computeUInt64Size(1, var11);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var12 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(2, var12);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.charname)) {
         String var13 = var1.charname;
         var2 += CodedOutputStream.computeStringSize(3, var13);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.itemindex)) {
         Integer var14 = var1.itemindex;
         var2 += CodedOutputStream.computeInt32Size(4, var14);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var15 = var1.count;
         var2 += CodedOutputStream.computeInt32Size(5, var15);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.helperguid)) {
         Long var16 = var1.helperguid;
         var2 += CodedOutputStream.computeUInt64Size(6, var16);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.helpername)) {
         String var17 = var1.helpername;
         var2 += CodedOutputStream.computeStringSize(7, var17);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var18 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(8, var18);
      }

      return var2;
   }

   public void doWriteTo(PT_GUILD_DONATION_HELP_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var11 = var1.guid;
         if (var11 != null) {
            var2.writeUInt64(1, var11);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var12 = var1.charguid;
         if (var12 != null) {
            var2.writeUInt64(2, var12);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.charname)) {
         String var13 = var1.charname;
         if (var13 != null) {
            var2.writeString(3, var13);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.itemindex)) {
         Integer var14 = var1.itemindex;
         if (var14 != null) {
            var2.writeInt32(4, var14);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var15 = var1.count;
         if (var15 != null) {
            var2.writeInt32(5, var15);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.helperguid)) {
         Long var16 = var1.helperguid;
         if (var16 != null) {
            var2.writeUInt64(6, var16);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.helpername)) {
         String var17 = var1.helpername;
         if (var17 != null) {
            var2.writeString(7, var17);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var18 = var1.index;
         if (var18 != null) {
            var2.writeInt32(8, var18);
         }
      }

   }

   public void writeTo(PT_GUILD_DONATION_HELP_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_GUILD_DONATION_HELP_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_GUILD_DONATION_HELP_INFO var2 = new PT_GUILD_DONATION_HELP_INFO();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.guid = var1.readUInt64();
            } else if (var5 == 16) {
               var2.charguid = var1.readUInt64();
            } else if (var5 == 26) {
               var2.charname = var1.readString();
            } else if (var5 == 32) {
               var2.itemindex = var1.readInt32();
            } else if (var5 == 40) {
               var2.count = var1.readInt32();
            } else if (var5 == 48) {
               var2.helperguid = var1.readUInt64();
            } else if (var5 == 58) {
               var2.helpername = var1.readString();
            } else if (var5 == 64) {
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_GUILD_DONATION_HELP_INFO.class);
         return this.descriptor = var1;
      }
   }
}
