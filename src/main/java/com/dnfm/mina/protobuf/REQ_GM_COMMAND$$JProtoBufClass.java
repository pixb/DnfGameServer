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

public class REQ_GM_COMMAND$$JProtoBufClass implements Codec<REQ_GM_COMMAND>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_GM_COMMAND var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_GM_COMMAND decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_GM_COMMAND var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var8 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(1, var8);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.list)) {
         List var9 = var1.list;
         var2 += CodedConstant.computeListSize(2, var9, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.time)) {
         String var10 = var1.time;
         var2 += CodedOutputStream.computeStringSize(3, var10);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.msg)) {
         String var11 = var1.msg;
         var2 += CodedOutputStream.computeStringSize(4, var11);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var12 = var1.guid;
         var2 += CodedOutputStream.computeUInt64Size(5, var12);
      }

      return var2;
   }

   public void doWriteTo(REQ_GM_COMMAND var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var8 = var1.type;
         if (var8 != null) {
            var2.writeInt32(1, var8);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.list)) {
         List var9 = var1.list;
         if (var9 != null) {
            CodedConstant.writeToList(var2, 2, FieldType.OBJECT, var9, false);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.time)) {
         String var10 = var1.time;
         if (var10 != null) {
            var2.writeString(3, var10);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.msg)) {
         String var11 = var1.msg;
         if (var11 != null) {
            var2.writeString(4, var11);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var12 = var1.guid;
         if (var12 != null) {
            var2.writeUInt64(5, var12);
         }
      }

   }

   public void writeTo(REQ_GM_COMMAND var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_GM_COMMAND readFrom(CodedInputStream var1) throws IOException {
      REQ_GM_COMMAND var2 = new REQ_GM_COMMAND();
      var2.list = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.type = var1.readInt32();
            } else if (var5 == 18) {
               Codec var10 = ProtobufProxy.create(PT_GM_COMMAND_ARG.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.list == null) {
                  var2.list = new ArrayList();
               }

               var2.list.add((PT_GM_COMMAND_ARG)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 26) {
               var2.time = var1.readString();
            } else if (var5 == 34) {
               var2.msg = var1.readString();
            } else if (var5 == 40) {
               var2.guid = var1.readUInt64();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_GM_COMMAND.class);
         return this.descriptor = var1;
      }
   }
}
