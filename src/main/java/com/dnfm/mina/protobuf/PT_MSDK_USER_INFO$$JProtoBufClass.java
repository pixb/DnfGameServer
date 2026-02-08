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

public class PT_MSDK_USER_INFO$$JProtoBufClass implements Codec<PT_MSDK_USER_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_MSDK_USER_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_MSDK_USER_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_MSDK_USER_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.username)) {
         String var8 = var1.username;
         var2 += CodedOutputStream.computeStringSize(1, var8);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.gender)) {
         Integer var9 = var1.gender;
         var2 += CodedOutputStream.computeInt32Size(2, var9);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.birthdate)) {
         String var10 = var1.birthdate;
         var2 += CodedOutputStream.computeStringSize(3, var10);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.pictureurl)) {
         String var11 = var1.pictureurl;
         var2 += CodedOutputStream.computeStringSize(4, var11);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.channelinfo)) {
         String var12 = var1.channelinfo;
         var2 += CodedOutputStream.computeStringSize(5, var12);
      }

      return var2;
   }

   public void doWriteTo(PT_MSDK_USER_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.username)) {
         String var8 = var1.username;
         if (var8 != null) {
            var2.writeString(1, var8);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.gender)) {
         Integer var9 = var1.gender;
         if (var9 != null) {
            var2.writeInt32(2, var9);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.birthdate)) {
         String var10 = var1.birthdate;
         if (var10 != null) {
            var2.writeString(3, var10);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.pictureurl)) {
         String var11 = var1.pictureurl;
         if (var11 != null) {
            var2.writeString(4, var11);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.channelinfo)) {
         String var12 = var1.channelinfo;
         if (var12 != null) {
            var2.writeString(5, var12);
         }
      }

   }

   public void writeTo(PT_MSDK_USER_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_MSDK_USER_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_MSDK_USER_INFO var2 = new PT_MSDK_USER_INFO();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 10) {
               var2.username = var1.readString();
            } else if (var5 == 16) {
               var2.gender = var1.readInt32();
            } else if (var5 == 26) {
               var2.birthdate = var1.readString();
            } else if (var5 == 34) {
               var2.pictureurl = var1.readString();
            } else if (var5 == 42) {
               var2.channelinfo = var1.readString();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_MSDK_USER_INFO.class);
         return this.descriptor = var1;
      }
   }
}
