package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.baidu.bjf.remoting.protobuf.code.CodedConstant;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.InvalidProtocolBufferException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RES_AUTHKEY_REFRESH$$JProtoBufClass implements Codec<RES_AUTHKEY_REFRESH>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_AUTHKEY_REFRESH var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_AUTHKEY_REFRESH decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_AUTHKEY_REFRESH var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var7 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var7);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.authkey)) {
         String var8 = var1.authkey;
         var2 += CodedOutputStream.computeStringSize(2, var8);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.channel)) {
         List var9 = var1.channel;
         var2 += CodedConstant.computeListSize(3, var9, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.version)) {
         String var10 = var1.version;
         var2 += CodedOutputStream.computeStringSize(4, var10);
      }

      return var2;
   }

   public void doWriteTo(RES_AUTHKEY_REFRESH var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var7 = var1.error;
         if (var7 != null) {
            var2.writeInt32(1, var7);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.authkey)) {
         String var8 = var1.authkey;
         if (var8 != null) {
            var2.writeString(2, var8);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.channel)) {
         List var9 = var1.channel;
         if (var9 != null) {
            CodedConstant.writeToList(var2, 3, FieldType.OBJECT, var9, false);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.version)) {
         String var10 = var1.version;
         if (var10 != null) {
            var2.writeString(4, var10);
         }
      }

   }

   public void writeTo(RES_AUTHKEY_REFRESH var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_AUTHKEY_REFRESH readFrom(CodedInputStream var1) throws IOException {
      RES_AUTHKEY_REFRESH var2 = new RES_AUTHKEY_REFRESH();
      var2.channel = new ArrayList();

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
               var2.authkey = var1.readString();
            } else if (var5 == 26) {
               Codec var10 = ProtobufProxy.create(PT_CHANNEL_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.channel == null) {
                  var2.channel = new ArrayList();
               }

               var2.channel.add((PT_CHANNEL_INFO)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 34) {
               var2.version = var1.readString();
            } else {
               var1.skipField(var5);
            }
         }

         return var2;
      } catch (InvalidProtocolBufferException var8) {
         throw var8;
      } catch (IOException var9) {
         throw var9;
      }
   }

   public Descriptors.Descriptor getDescriptor() throws IOException {
      if (this.descriptor != null) {
         return this.descriptor;
      } else {
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_AUTHKEY_REFRESH.class);
         return this.descriptor = var1;
      }
   }
}
