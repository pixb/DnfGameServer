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

public class AUTH_INFO$REQ$$JProtoBufClass implements Codec<AUTH_INFO.REQ>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(AUTH_INFO.REQ var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public AUTH_INFO.REQ decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(AUTH_INFO.REQ var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.authIndex)) {
         Long var4 = var1.authIndex;
         var2 += CodedOutputStream.computeUInt64Size(1, var4);
      }

      return var2;
   }

   public void doWriteTo(AUTH_INFO.REQ var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.authIndex)) {
         Long var4 = var1.authIndex;
         if (var4 != null) {
            var2.writeUInt64(1, var4);
         }
      }

   }

   public void writeTo(AUTH_INFO.REQ var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public AUTH_INFO.REQ readFrom(CodedInputStream var1) throws IOException {
      AUTH_INFO.REQ var2 = new AUTH_INFO.REQ();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(AUTH_INFO.REQ.class);
         return this.descriptor = var1;
      }
   }
}
