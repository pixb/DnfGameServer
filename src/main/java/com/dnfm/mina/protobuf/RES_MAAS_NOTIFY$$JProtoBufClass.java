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

public class RES_MAAS_NOTIFY$$JProtoBufClass implements Codec<RES_MAAS_NOTIFY>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_MAAS_NOTIFY var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_MAAS_NOTIFY decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_MAAS_NOTIFY var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var8 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var8);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.title)) {
         String var9 = var1.title;
         var2 += CodedOutputStream.computeStringSize(2, var9);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.msg)) {
         String var10 = var1.msg;
         var2 += CodedOutputStream.computeStringSize(3, var10);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.traceId)) {
         String var11 = var1.traceId;
         var2 += CodedOutputStream.computeStringSize(4, var11);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.fullMsg)) {
         String var12 = var1.fullMsg;
         var2 += CodedOutputStream.computeStringSize(5, var12);
      }

      return var2;
   }

   public void doWriteTo(RES_MAAS_NOTIFY var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var8 = var1.error;
         if (var8 != null) {
            var2.writeInt32(1, var8);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.title)) {
         String var9 = var1.title;
         if (var9 != null) {
            var2.writeString(2, var9);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.msg)) {
         String var10 = var1.msg;
         if (var10 != null) {
            var2.writeString(3, var10);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.traceId)) {
         String var11 = var1.traceId;
         if (var11 != null) {
            var2.writeString(4, var11);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.fullMsg)) {
         String var12 = var1.fullMsg;
         if (var12 != null) {
            var2.writeString(5, var12);
         }
      }

   }

   public void writeTo(RES_MAAS_NOTIFY var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_MAAS_NOTIFY readFrom(CodedInputStream var1) throws IOException {
      RES_MAAS_NOTIFY var2 = new RES_MAAS_NOTIFY();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.error = var1.readInt32();
            } else if (var5 == 18) {
               var2.title = var1.readString();
            } else if (var5 == 26) {
               var2.msg = var1.readString();
            } else if (var5 == 34) {
               var2.traceId = var1.readString();
            } else if (var5 == 42) {
               var2.fullMsg = var1.readString();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_MAAS_NOTIFY.class);
         return this.descriptor = var1;
      }
   }
}
