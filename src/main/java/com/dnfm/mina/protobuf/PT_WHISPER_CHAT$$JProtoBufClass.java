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

public class PT_WHISPER_CHAT$$JProtoBufClass implements Codec<PT_WHISPER_CHAT>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_WHISPER_CHAT var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_WHISPER_CHAT decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_WHISPER_CHAT var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var12 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var12);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.sender)) {
         String var13 = var1.sender;
         var2 += CodedOutputStream.computeStringSize(2, var13);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.receiver)) {
         String var14 = var1.receiver;
         var2 += CodedOutputStream.computeStringSize(3, var14);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.chat)) {
         String var15 = var1.chat;
         var2 += CodedOutputStream.computeStringSize(4, var15);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.voice)) {
         String var16 = var1.voice;
         var2 += CodedOutputStream.computeStringSize(5, var16);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var17 = var1.job;
         var2 += CodedOutputStream.computeInt32Size(6, var17);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var18 = var1.growtype;
         var2 += CodedOutputStream.computeInt32Size(7, var18);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var19 = var1.secondgrowtype;
         var2 += CodedOutputStream.computeInt32Size(8, var19);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.date)) {
         Long var20 = var1.date;
         var2 += CodedOutputStream.computeUInt64Size(9, var20);
      }

      return var2;
   }

   public void doWriteTo(PT_WHISPER_CHAT var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var12 = var1.charguid;
         if (var12 != null) {
            var2.writeUInt64(1, var12);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.sender)) {
         String var13 = var1.sender;
         if (var13 != null) {
            var2.writeString(2, var13);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.receiver)) {
         String var14 = var1.receiver;
         if (var14 != null) {
            var2.writeString(3, var14);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.chat)) {
         String var15 = var1.chat;
         if (var15 != null) {
            var2.writeString(4, var15);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.voice)) {
         String var16 = var1.voice;
         if (var16 != null) {
            var2.writeString(5, var16);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var17 = var1.job;
         if (var17 != null) {
            var2.writeInt32(6, var17);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var18 = var1.growtype;
         if (var18 != null) {
            var2.writeInt32(7, var18);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var19 = var1.secondgrowtype;
         if (var19 != null) {
            var2.writeInt32(8, var19);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.date)) {
         Long var20 = var1.date;
         if (var20 != null) {
            var2.writeUInt64(9, var20);
         }
      }

   }

   public void writeTo(PT_WHISPER_CHAT var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_WHISPER_CHAT readFrom(CodedInputStream var1) throws IOException {
      PT_WHISPER_CHAT var2 = new PT_WHISPER_CHAT();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.charguid = var1.readUInt64();
            } else if (var5 == 18) {
               var2.sender = var1.readString();
            } else if (var5 == 26) {
               var2.receiver = var1.readString();
            } else if (var5 == 34) {
               var2.chat = var1.readString();
            } else if (var5 == 42) {
               var2.voice = var1.readString();
            } else if (var5 == 48) {
               var2.job = var1.readInt32();
            } else if (var5 == 56) {
               var2.growtype = var1.readInt32();
            } else if (var5 == 64) {
               var2.secondgrowtype = var1.readInt32();
            } else if (var5 == 72) {
               var2.date = var1.readUInt64();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_WHISPER_CHAT.class);
         return this.descriptor = var1;
      }
   }
}
