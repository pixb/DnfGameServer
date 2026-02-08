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

public class PT_GUILD_HISTORICSITE_CHAT$$JProtoBufClass implements Codec<PT_GUILD_HISTORICSITE_CHAT>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_GUILD_HISTORICSITE_CHAT var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_GUILD_HISTORICSITE_CHAT decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_GUILD_HISTORICSITE_CHAT var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var10 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(1, var10);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var11 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(2, var11);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.sender)) {
         String var12 = var1.sender;
         var2 += CodedOutputStream.computeStringSize(3, var12);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.chat)) {
         String var13 = var1.chat;
         var2 += CodedOutputStream.computeStringSize(4, var13);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.date)) {
         Long var14 = var1.date;
         var2 += CodedOutputStream.computeInt64Size(5, var14);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.subtype)) {
         Integer var15 = var1.subtype;
         var2 += CodedOutputStream.computeInt32Size(6, var15);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.sub)) {
         List var16 = var1.sub;
         var2 += CodedConstant.computeListSize(7, var16, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(PT_GUILD_HISTORICSITE_CHAT var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var10 = var1.type;
         if (var10 != null) {
            var2.writeInt32(1, var10);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var11 = var1.charguid;
         if (var11 != null) {
            var2.writeUInt64(2, var11);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.sender)) {
         String var12 = var1.sender;
         if (var12 != null) {
            var2.writeString(3, var12);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.chat)) {
         String var13 = var1.chat;
         if (var13 != null) {
            var2.writeString(4, var13);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.date)) {
         Long var14 = var1.date;
         if (var14 != null) {
            var2.writeInt64(5, var14);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.subtype)) {
         Integer var15 = var1.subtype;
         if (var15 != null) {
            var2.writeInt32(6, var15);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.sub)) {
         List var16 = var1.sub;
         if (var16 != null) {
            CodedConstant.writeToList(var2, 7, FieldType.OBJECT, var16, false);
         }
      }

   }

   public void writeTo(PT_GUILD_HISTORICSITE_CHAT var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_GUILD_HISTORICSITE_CHAT readFrom(CodedInputStream var1) throws IOException {
      PT_GUILD_HISTORICSITE_CHAT var2 = new PT_GUILD_HISTORICSITE_CHAT();
      var2.sub = new ArrayList();

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
            } else if (var5 == 16) {
               var2.charguid = var1.readUInt64();
            } else if (var5 == 26) {
               var2.sender = var1.readString();
            } else if (var5 == 34) {
               var2.chat = var1.readString();
            } else if (var5 == 40) {
               var2.date = var1.readInt64();
            } else if (var5 == 48) {
               var2.subtype = var1.readInt32();
            } else if (var5 == 58) {
               Codec var10 = ProtobufProxy.create(PT_HYPERLINK_SYSTEMMESSAGE_SUB.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.sub == null) {
                  var2.sub = new ArrayList();
               }

               var2.sub.add((PT_HYPERLINK_SYSTEMMESSAGE_SUB)var10.readFrom(var1));
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_GUILD_HISTORICSITE_CHAT.class);
         return this.descriptor = var1;
      }
   }
}
