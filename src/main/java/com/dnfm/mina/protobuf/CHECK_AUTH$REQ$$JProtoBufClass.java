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

public class CHECK_AUTH$REQ$$JProtoBufClass implements Codec<CHECK_AUTH.REQ>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(CHECK_AUTH.REQ var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public CHECK_AUTH.REQ decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(CHECK_AUTH.REQ var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.authkey)) {
         String var7 = var1.authkey;
         var2 += CodedOutputStream.computeStringSize(1, var7);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.openID)) {
         String var8 = var1.openID;
         var2 += CodedOutputStream.computeStringSize(2, var8);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.worldID)) {
         Integer var9 = var1.worldID;
         var2 += CodedOutputStream.computeInt32Size(3, var9);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.authIndex)) {
         Long var10 = var1.authIndex;
         var2 += CodedOutputStream.computeUInt64Size(4, var10);
      }

      return var2;
   }

   public void doWriteTo(CHECK_AUTH.REQ var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.authkey)) {
         String var7 = var1.authkey;
         if (var7 != null) {
            var2.writeString(1, var7);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.openID)) {
         String var8 = var1.openID;
         if (var8 != null) {
            var2.writeString(2, var8);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.worldID)) {
         Integer var9 = var1.worldID;
         if (var9 != null) {
            var2.writeInt32(3, var9);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.authIndex)) {
         Long var10 = var1.authIndex;
         if (var10 != null) {
            var2.writeUInt64(4, var10);
         }
      }

   }

   public void writeTo(CHECK_AUTH.REQ var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public CHECK_AUTH.REQ readFrom(CodedInputStream var1) throws IOException {
      CHECK_AUTH.REQ var2 = new CHECK_AUTH.REQ();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 10) {
               var2.authkey = var1.readString();
            } else if (var5 == 18) {
               var2.openID = var1.readString();
            } else if (var5 == 24) {
               var2.worldID = var1.readInt32();
            } else if (var5 == 32) {
               var2.authIndex = var1.readUInt64();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(CHECK_AUTH.REQ.class);
         return this.descriptor = var1;
      }
   }
}
