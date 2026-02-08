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

public class REQ_PLATFORM_PROFILE_UPDATE$$JProtoBufClass implements Codec<REQ_PLATFORM_PROFILE_UPDATE>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_PLATFORM_PROFILE_UPDATE var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_PLATFORM_PROFILE_UPDATE decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_PLATFORM_PROFILE_UPDATE var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.profileurl)) {
         String var7 = var1.profileurl;
         var2 += CodedOutputStream.computeStringSize(1, var7);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.profilename)) {
         String var8 = var1.profilename;
         var2 += CodedOutputStream.computeStringSize(2, var8);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.firstlocation)) {
         Integer var9 = var1.firstlocation;
         var2 += CodedOutputStream.computeInt32Size(3, var9);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.secondlocation)) {
         Integer var10 = var1.secondlocation;
         var2 += CodedOutputStream.computeInt32Size(4, var10);
      }

      return var2;
   }

   public void doWriteTo(REQ_PLATFORM_PROFILE_UPDATE var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.profileurl)) {
         String var7 = var1.profileurl;
         if (var7 != null) {
            var2.writeString(1, var7);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.profilename)) {
         String var8 = var1.profilename;
         if (var8 != null) {
            var2.writeString(2, var8);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.firstlocation)) {
         Integer var9 = var1.firstlocation;
         if (var9 != null) {
            var2.writeInt32(3, var9);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.secondlocation)) {
         Integer var10 = var1.secondlocation;
         if (var10 != null) {
            var2.writeInt32(4, var10);
         }
      }

   }

   public void writeTo(REQ_PLATFORM_PROFILE_UPDATE var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_PLATFORM_PROFILE_UPDATE readFrom(CodedInputStream var1) throws IOException {
      REQ_PLATFORM_PROFILE_UPDATE var2 = new REQ_PLATFORM_PROFILE_UPDATE();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 10) {
               var2.profileurl = var1.readString();
            } else if (var5 == 18) {
               var2.profilename = var1.readString();
            } else if (var5 == 24) {
               var2.firstlocation = var1.readInt32();
            } else if (var5 == 32) {
               var2.secondlocation = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_PLATFORM_PROFILE_UPDATE.class);
         return this.descriptor = var1;
      }
   }
}
