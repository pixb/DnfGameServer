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

public class GET_SESSION$Types$REQ$$JProtoBufClass implements Codec<GET_SESSION.Types.REQ>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(GET_SESSION.Types.REQ var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public GET_SESSION.Types.REQ decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(GET_SESSION.Types.REQ var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.openID)) {
         String var6 = var1.openID;
         var2 += CodedOutputStream.computeStringSize(1, var6);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.authkey)) {
         String var7 = var1.authkey;
         var2 += CodedOutputStream.computeStringSize(2, var7);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.worldID)) {
         Integer var8 = var1.worldID;
         var2 += CodedOutputStream.computeInt32Size(3, var8);
      }

      return var2;
   }

   public void doWriteTo(GET_SESSION.Types.REQ var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.openID)) {
         String var6 = var1.openID;
         if (var6 != null) {
            var2.writeString(1, var6);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.authkey)) {
         String var7 = var1.authkey;
         if (var7 != null) {
            var2.writeString(2, var7);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.worldID)) {
         Integer var8 = var1.worldID;
         if (var8 != null) {
            var2.writeInt32(3, var8);
         }
      }

   }

   public void writeTo(GET_SESSION.Types.REQ var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public GET_SESSION.Types.REQ readFrom(CodedInputStream var1) throws IOException {
      GET_SESSION.Types.REQ var2 = new GET_SESSION.Types.REQ();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 10) {
               var2.openID = var1.readString();
            } else if (var5 == 18) {
               var2.authkey = var1.readString();
            } else if (var5 == 24) {
               var2.worldID = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(GET_SESSION.Types.REQ.class);
         return this.descriptor = var1;
      }
   }
}
