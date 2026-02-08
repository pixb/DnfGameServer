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

public class RES_BILING_CONFIG_CHECK$$JProtoBufClass implements Codec<RES_BILING_CONFIG_CHECK>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_BILING_CONFIG_CHECK var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_BILING_CONFIG_CHECK decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_BILING_CONFIG_CHECK var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var5 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var5);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.state)) {
         Integer var6 = var1.state;
         var2 += CodedOutputStream.computeInt32Size(2, var6);
      }

      return var2;
   }

   public void doWriteTo(RES_BILING_CONFIG_CHECK var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var5 = var1.error;
         if (var5 != null) {
            var2.writeInt32(1, var5);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.state)) {
         Integer var6 = var1.state;
         if (var6 != null) {
            var2.writeInt32(2, var6);
         }
      }

   }

   public void writeTo(RES_BILING_CONFIG_CHECK var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_BILING_CONFIG_CHECK readFrom(CodedInputStream var1) throws IOException {
      RES_BILING_CONFIG_CHECK var2 = new RES_BILING_CONFIG_CHECK();

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
            } else if (var5 == 16) {
               var2.state = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_BILING_CONFIG_CHECK.class);
         return this.descriptor = var1;
      }
   }
}
