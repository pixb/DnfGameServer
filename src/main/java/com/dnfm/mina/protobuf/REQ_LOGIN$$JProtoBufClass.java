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

public class REQ_LOGIN$$JProtoBufClass implements Codec<REQ_LOGIN>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_LOGIN var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_LOGIN decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_LOGIN var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.openid)) {
         String var14 = var1.openid;
         var2 += CodedOutputStream.computeStringSize(1, var14);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var15 = var1.type;
         var2 += CodedOutputStream.computeUInt32Size(2, var15);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.token)) {
         String var16 = var1.token;
         var2 += CodedOutputStream.computeStringSize(3, var16);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.platID)) {
         Integer var17 = var1.platID;
         var2 += CodedOutputStream.computeUInt32Size(4, var17);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.clientIP)) {
         String var18 = var1.clientIP;
         var2 += CodedOutputStream.computeStringSize(5, var18);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.version)) {
         String var19 = var1.version;
         var2 += CodedOutputStream.computeStringSize(6, var19);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.friendopenid)) {
         String var20 = var1.friendopenid;
         var2 += CodedOutputStream.computeStringSize(7, var20);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.cancelunregist)) {
         Integer var21 = var1.cancelunregist;
         var2 += CodedOutputStream.computeUInt32Size(8, var21);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.countrycode)) {
         String var22 = var1.countrycode;
         var2 += CodedOutputStream.computeStringSize(9, var22);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.toyplatid)) {
         Integer var23 = var1.toyplatid;
         var2 += CodedOutputStream.computeInt32Size(10, var23);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.agetype)) {
         Integer var24 = var1.agetype;
         var2 += CodedOutputStream.computeInt32Size(11, var24);
      }

      return var2;
   }

   public void doWriteTo(REQ_LOGIN var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.openid)) {
         String var14 = var1.openid;
         if (var14 != null) {
            var2.writeString(1, var14);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var15 = var1.type;
         if (var15 != null) {
            var2.writeUInt32(2, var15);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.token)) {
         String var16 = var1.token;
         if (var16 != null) {
            var2.writeString(3, var16);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.platID)) {
         Integer var17 = var1.platID;
         if (var17 != null) {
            var2.writeUInt32(4, var17);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.clientIP)) {
         String var18 = var1.clientIP;
         if (var18 != null) {
            var2.writeString(5, var18);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.version)) {
         String var19 = var1.version;
         if (var19 != null) {
            var2.writeString(6, var19);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.friendopenid)) {
         String var20 = var1.friendopenid;
         if (var20 != null) {
            var2.writeString(7, var20);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.cancelunregist)) {
         Integer var21 = var1.cancelunregist;
         if (var21 != null) {
            var2.writeUInt32(8, var21);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.countrycode)) {
         String var22 = var1.countrycode;
         if (var22 != null) {
            var2.writeString(9, var22);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.toyplatid)) {
         Integer var23 = var1.toyplatid;
         if (var23 != null) {
            var2.writeInt32(10, var23);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.agetype)) {
         Integer var24 = var1.agetype;
         if (var24 != null) {
            var2.writeInt32(11, var24);
         }
      }

   }

   public void writeTo(REQ_LOGIN var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_LOGIN readFrom(CodedInputStream var1) throws IOException {
      REQ_LOGIN var2 = new REQ_LOGIN();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 10) {
               var2.openid = var1.readString();
            } else if (var5 == 16) {
               var2.type = var1.readUInt32();
            } else if (var5 == 26) {
               var2.token = var1.readString();
            } else if (var5 == 32) {
               var2.platID = var1.readUInt32();
            } else if (var5 == 42) {
               var2.clientIP = var1.readString();
            } else if (var5 == 50) {
               var2.version = var1.readString();
            } else if (var5 == 58) {
               var2.friendopenid = var1.readString();
            } else if (var5 == 64) {
               var2.cancelunregist = var1.readUInt32();
            } else if (var5 == 74) {
               var2.countrycode = var1.readString();
            } else if (var5 == 80) {
               var2.toyplatid = var1.readInt32();
            } else if (var5 == 88) {
               var2.agetype = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_LOGIN.class);
         return this.descriptor = var1;
      }
   }
}
