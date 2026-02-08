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

public class PT_NOTE_MESSAGE$$JProtoBufClass implements Codec<PT_NOTE_MESSAGE>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_NOTE_MESSAGE var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_NOTE_MESSAGE decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_NOTE_MESSAGE var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var14 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var14);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var15 = var1.name;
         var2 += CodedOutputStream.computeStringSize(2, var15);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var16 = var1.job;
         var2 += CodedOutputStream.computeInt32Size(3, var16);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var17 = var1.growtype;
         var2 += CodedOutputStream.computeInt32Size(4, var17);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var18 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(5, var18);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var19 = var1.count;
         var2 += CodedOutputStream.computeInt32Size(6, var19);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.date)) {
         Long var20 = var1.date;
         var2 += CodedOutputStream.computeInt64Size(7, var20);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.online)) {
         Integer var21 = var1.online;
         var2 += CodedOutputStream.computeInt32Size(8, var21);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.creditscore)) {
         Integer var22 = var1.creditscore;
         var2 += CodedOutputStream.computeInt32Size(9, var22);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.skinchatinfo)) {
         PT_SKIN_CHAT_INFO var23 = var1.skinchatinfo;
         var2 += CodedConstant.computeSize(10, var23, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.list)) {
         List var24 = var1.list;
         var2 += CodedConstant.computeListSize(11, var24, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(PT_NOTE_MESSAGE var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var14 = var1.charguid;
         if (var14 != null) {
            var2.writeUInt64(1, var14);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var15 = var1.name;
         if (var15 != null) {
            var2.writeString(2, var15);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var16 = var1.job;
         if (var16 != null) {
            var2.writeInt32(3, var16);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var17 = var1.growtype;
         if (var17 != null) {
            var2.writeInt32(4, var17);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var18 = var1.level;
         if (var18 != null) {
            var2.writeInt32(5, var18);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var19 = var1.count;
         if (var19 != null) {
            var2.writeInt32(6, var19);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.date)) {
         Long var20 = var1.date;
         if (var20 != null) {
            var2.writeInt64(7, var20);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.online)) {
         Integer var21 = var1.online;
         if (var21 != null) {
            var2.writeInt32(8, var21);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.creditscore)) {
         Integer var22 = var1.creditscore;
         if (var22 != null) {
            var2.writeInt32(9, var22);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.skinchatinfo)) {
         PT_SKIN_CHAT_INFO var23 = var1.skinchatinfo;
         if (var23 != null) {
            CodedConstant.writeObject(var2, 10, FieldType.OBJECT, var23, false);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.list)) {
         List var24 = var1.list;
         if (var24 != null) {
            CodedConstant.writeToList(var2, 11, FieldType.OBJECT, var24, false);
         }
      }

   }

   public void writeTo(PT_NOTE_MESSAGE var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_NOTE_MESSAGE readFrom(CodedInputStream var1) throws IOException {
      PT_NOTE_MESSAGE var2 = new PT_NOTE_MESSAGE();
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
               var2.charguid = var1.readUInt64();
            } else if (var5 == 18) {
               var2.name = var1.readString();
            } else if (var5 == 24) {
               var2.job = var1.readInt32();
            } else if (var5 == 32) {
               var2.growtype = var1.readInt32();
            } else if (var5 == 40) {
               var2.level = var1.readInt32();
            } else if (var5 == 48) {
               var2.count = var1.readInt32();
            } else if (var5 == 56) {
               var2.date = var1.readInt64();
            } else if (var5 == 64) {
               var2.online = var1.readInt32();
            } else if (var5 == 72) {
               var2.creditscore = var1.readInt32();
            } else if (var5 == 82) {
               Codec var10 = ProtobufProxy.create(PT_SKIN_CHAT_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.skinchatinfo = (PT_SKIN_CHAT_INFO)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 90) {
               Codec var11 = ProtobufProxy.create(PT_CHAT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var12 = var1.readRawVarint32();
               int var13 = var1.pushLimit(var12);
               if (var2.list == null) {
                  var2.list = new ArrayList();
               }

               var2.list.add((PT_CHAT)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var13);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_NOTE_MESSAGE.class);
         return this.descriptor = var1;
      }
   }
}
