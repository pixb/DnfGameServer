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

public class REQ_SENDING_JOIN_GUILD$$JProtoBufClass implements Codec<REQ_SENDING_JOIN_GUILD>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_SENDING_JOIN_GUILD var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_SENDING_JOIN_GUILD decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_SENDING_JOIN_GUILD var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.gguid)) {
         Long var5 = var1.gguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var5);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.quick)) {
         Boolean var6 = var1.quick;
         var2 += CodedOutputStream.computeBoolSize(2, var6);
      }

      return var2;
   }

   public void doWriteTo(REQ_SENDING_JOIN_GUILD var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.gguid)) {
         Long var5 = var1.gguid;
         if (var5 != null) {
            var2.writeUInt64(1, var5);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.quick)) {
         Boolean var6 = var1.quick;
         if (var6 != null) {
            var2.writeBool(2, var6);
         }
      }

   }

   public void writeTo(REQ_SENDING_JOIN_GUILD var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_SENDING_JOIN_GUILD readFrom(CodedInputStream var1) throws IOException {
      REQ_SENDING_JOIN_GUILD var2 = new REQ_SENDING_JOIN_GUILD();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.gguid = var1.readUInt64();
            } else if (var5 == 16) {
               var2.quick = var1.readBool();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_SENDING_JOIN_GUILD.class);
         return this.descriptor = var1;
      }
   }
}
