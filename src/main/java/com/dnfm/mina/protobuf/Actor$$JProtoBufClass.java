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

public class Actor$$JProtoBufClass implements Codec<Actor>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(Actor var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public Actor decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(Actor var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.accountkey)) {
         String var13 = var1.accountkey;
         var2 += CodedOutputStream.computeStringSize(1, var13);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.cera)) {
         Integer var14 = var1.cera;
         var2 += CodedOutputStream.computeInt32Size(2, var14);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         String var15 = var1.charguid;
         var2 += CodedOutputStream.computeStringSize(3, var15);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var16 = var1.name;
         var2 += CodedOutputStream.computeStringSize(4, var16);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.openid)) {
         String var17 = var1.openid;
         var2 += CodedOutputStream.computeStringSize(5, var17);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.sessionid)) {
         Integer var18 = var1.sessionid;
         var2 += CodedOutputStream.computeInt32Size(6, var18);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.userip)) {
         String var19 = var1.userip;
         var2 += CodedOutputStream.computeStringSize(7, var19);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.version)) {
         String var20 = var1.version;
         var2 += CodedOutputStream.computeStringSize(8, var20);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.worldid)) {
         Integer var21 = var1.worldid;
         var2 += CodedOutputStream.computeInt32Size(9, var21);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.platid)) {
         Integer var22 = var1.platid;
         var2 += CodedOutputStream.computeInt32Size(10, var22);
      }

      return var2;
   }

   public void doWriteTo(Actor var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.accountkey)) {
         String var13 = var1.accountkey;
         if (var13 != null) {
            var2.writeString(1, var13);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.cera)) {
         Integer var14 = var1.cera;
         if (var14 != null) {
            var2.writeInt32(2, var14);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         String var15 = var1.charguid;
         if (var15 != null) {
            var2.writeString(3, var15);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var16 = var1.name;
         if (var16 != null) {
            var2.writeString(4, var16);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.openid)) {
         String var17 = var1.openid;
         if (var17 != null) {
            var2.writeString(5, var17);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.sessionid)) {
         Integer var18 = var1.sessionid;
         if (var18 != null) {
            var2.writeInt32(6, var18);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.userip)) {
         String var19 = var1.userip;
         if (var19 != null) {
            var2.writeString(7, var19);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.version)) {
         String var20 = var1.version;
         if (var20 != null) {
            var2.writeString(8, var20);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.worldid)) {
         Integer var21 = var1.worldid;
         if (var21 != null) {
            var2.writeInt32(9, var21);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.platid)) {
         Integer var22 = var1.platid;
         if (var22 != null) {
            var2.writeInt32(10, var22);
         }
      }

   }

   public void writeTo(Actor var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public Actor readFrom(CodedInputStream var1) throws IOException {
      Actor var2 = new Actor();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 10) {
               var2.accountkey = var1.readString();
            } else if (var5 == 16) {
               var2.cera = var1.readInt32();
            } else if (var5 == 26) {
               var2.charguid = var1.readString();
            } else if (var5 == 34) {
               var2.name = var1.readString();
            } else if (var5 == 42) {
               var2.openid = var1.readString();
            } else if (var5 == 48) {
               var2.sessionid = var1.readInt32();
            } else if (var5 == 58) {
               var2.userip = var1.readString();
            } else if (var5 == 66) {
               var2.version = var1.readString();
            } else if (var5 == 72) {
               var2.worldid = var1.readInt32();
            } else if (var5 == 80) {
               var2.platid = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(Actor.class);
         return this.descriptor = var1;
      }
   }
}
