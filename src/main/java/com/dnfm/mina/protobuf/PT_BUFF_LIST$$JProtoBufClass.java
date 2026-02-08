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

public class PT_BUFF_LIST$$JProtoBufClass implements Codec<PT_BUFF_LIST>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_BUFF_LIST var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_BUFF_LIST decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_BUFF_LIST var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.time)) {
         Long var5 = var1.time;
         var2 += CodedOutputStream.computeInt64Size(1, var5);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.appendages)) {
         List var6 = var1.appendages;
         var2 += CodedConstant.computeListSize(2, var6, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(PT_BUFF_LIST var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.time)) {
         Long var5 = var1.time;
         if (var5 != null) {
            var2.writeInt64(1, var5);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.appendages)) {
         List var6 = var1.appendages;
         if (var6 != null) {
            CodedConstant.writeToList(var2, 2, FieldType.OBJECT, var6, false);
         }
      }

   }

   public void writeTo(PT_BUFF_LIST var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_BUFF_LIST readFrom(CodedInputStream var1) throws IOException {
      PT_BUFF_LIST var2 = new PT_BUFF_LIST();
      var2.appendages = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.time = var1.readInt64();
            } else if (var5 == 18) {
               Codec var10 = ProtobufProxy.create(PT_SYSTEM_BUFF_APPENDAGE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.appendages == null) {
                  var2.appendages = new ArrayList();
               }

               var2.appendages.add((PT_SYSTEM_BUFF_APPENDAGE)var10.readFrom(var1));
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_BUFF_LIST.class);
         return this.descriptor = var1;
      }
   }
}
