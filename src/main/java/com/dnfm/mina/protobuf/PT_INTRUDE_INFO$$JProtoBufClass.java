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

public class PT_INTRUDE_INFO$$JProtoBufClass implements Codec<PT_INTRUDE_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_INTRUDE_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_INTRUDE_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_INTRUDE_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.intrude)) {
         Boolean var9 = var1.intrude;
         var2 += CodedOutputStream.computeBoolSize(1, var9);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var10 = var1.name;
         var2 += CodedOutputStream.computeStringSize(2, var10);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var11 = var1.guid;
         var2 += CodedOutputStream.computeUInt64Size(3, var11);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.channel)) {
         PT_CHANNEL_INFO var12 = var1.channel;
         var2 += CodedConstant.computeSize(4, var12, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.partymembers)) {
         List var13 = var1.partymembers;
         var2 += CodedConstant.computeListSize(5, var13, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.rpguid)) {
         Long var14 = var1.rpguid;
         var2 += CodedOutputStream.computeUInt64Size(6, var14);
      }

      return var2;
   }

   public void doWriteTo(PT_INTRUDE_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.intrude)) {
         Boolean var9 = var1.intrude;
         if (var9 != null) {
            var2.writeBool(1, var9);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var10 = var1.name;
         if (var10 != null) {
            var2.writeString(2, var10);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var11 = var1.guid;
         if (var11 != null) {
            var2.writeUInt64(3, var11);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.channel)) {
         PT_CHANNEL_INFO var12 = var1.channel;
         if (var12 != null) {
            CodedConstant.writeObject(var2, 4, FieldType.OBJECT, var12, false);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.partymembers)) {
         List var13 = var1.partymembers;
         if (var13 != null) {
            CodedConstant.writeToList(var2, 5, FieldType.OBJECT, var13, false);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.rpguid)) {
         Long var14 = var1.rpguid;
         if (var14 != null) {
            var2.writeUInt64(6, var14);
         }
      }

   }

   public void writeTo(PT_INTRUDE_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_INTRUDE_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_INTRUDE_INFO var2 = new PT_INTRUDE_INFO();
      var2.partymembers = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.intrude = var1.readBool();
            } else if (var5 == 18) {
               var2.name = var1.readString();
            } else if (var5 == 24) {
               var2.guid = var1.readUInt64();
            } else if (var5 == 34) {
               Codec var10 = ProtobufProxy.create(PT_CHANNEL_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.channel = (PT_CHANNEL_INFO)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 42) {
               Codec var11 = ProtobufProxy.create(PT_INTRUDE_MEMBER_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var12 = var1.readRawVarint32();
               int var13 = var1.pushLimit(var12);
               if (var2.partymembers == null) {
                  var2.partymembers = new ArrayList();
               }

               var2.partymembers.add((PT_INTRUDE_MEMBER_INFO)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var13);
            } else if (var5 == 48) {
               var2.rpguid = var1.readUInt64();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_INTRUDE_INFO.class);
         return this.descriptor = var1;
      }
   }
}
