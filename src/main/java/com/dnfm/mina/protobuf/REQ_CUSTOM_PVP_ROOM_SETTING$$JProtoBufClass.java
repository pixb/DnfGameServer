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

public class REQ_CUSTOM_PVP_ROOM_SETTING$$JProtoBufClass implements Codec<REQ_CUSTOM_PVP_ROOM_SETTING>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_CUSTOM_PVP_ROOM_SETTING var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_CUSTOM_PVP_ROOM_SETTING decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_CUSTOM_PVP_ROOM_SETTING var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.customdata)) {
         PT_CUSTOM_PVP_ROOM_SETTING var4 = var1.customdata;
         var2 += CodedConstant.computeSize(1, var4, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      return var2;
   }

   public void doWriteTo(REQ_CUSTOM_PVP_ROOM_SETTING var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.customdata)) {
         PT_CUSTOM_PVP_ROOM_SETTING var4 = var1.customdata;
         if (var4 != null) {
            CodedConstant.writeObject(var2, 1, FieldType.OBJECT, var4, false);
         }
      }

   }

   public void writeTo(REQ_CUSTOM_PVP_ROOM_SETTING var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_CUSTOM_PVP_ROOM_SETTING readFrom(CodedInputStream var1) throws IOException {
      REQ_CUSTOM_PVP_ROOM_SETTING var2 = new REQ_CUSTOM_PVP_ROOM_SETTING();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 10) {
               Codec var10 = ProtobufProxy.create(PT_CUSTOM_PVP_ROOM_SETTING.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.customdata = (PT_CUSTOM_PVP_ROOM_SETTING)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_CUSTOM_PVP_ROOM_SETTING.class);
         return this.descriptor = var1;
      }
   }
}
