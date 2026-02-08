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

public class PT_ROOM_USER_INFO$$JProtoBufClass implements Codec<PT_ROOM_USER_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_ROOM_USER_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_ROOM_USER_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_ROOM_USER_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var13 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var13);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var14 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(2, var14);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var15 = var1.job;
         var2 += CodedOutputStream.computeInt32Size(3, var15);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var16 = var1.growtype;
         var2 += CodedOutputStream.computeInt32Size(4, var16);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var17 = var1.secondgrowtype;
         var2 += CodedOutputStream.computeInt32Size(5, var17);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var18 = var1.name;
         var2 += CodedOutputStream.computeStringSize(6, var18);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.teamtype)) {
         Integer var19 = var1.teamtype;
         var2 += CodedOutputStream.computeInt32Size(7, var19);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.creditscore)) {
         Integer var20 = var1.creditscore;
         var2 += CodedOutputStream.computeInt32Size(8, var20);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.ready)) {
         Boolean var21 = var1.ready;
         var2 += CodedOutputStream.computeBoolSize(9, var21);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.skinchatinfo)) {
         PT_SKIN_CHAT_INFO var22 = var1.skinchatinfo;
         var2 += CodedConstant.computeSize(10, var22, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      return var2;
   }

   public void doWriteTo(PT_ROOM_USER_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var13 = var1.charguid;
         if (var13 != null) {
            var2.writeUInt64(1, var13);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var14 = var1.level;
         if (var14 != null) {
            var2.writeInt32(2, var14);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var15 = var1.job;
         if (var15 != null) {
            var2.writeInt32(3, var15);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var16 = var1.growtype;
         if (var16 != null) {
            var2.writeInt32(4, var16);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var17 = var1.secondgrowtype;
         if (var17 != null) {
            var2.writeInt32(5, var17);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var18 = var1.name;
         if (var18 != null) {
            var2.writeString(6, var18);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.teamtype)) {
         Integer var19 = var1.teamtype;
         if (var19 != null) {
            var2.writeInt32(7, var19);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.creditscore)) {
         Integer var20 = var1.creditscore;
         if (var20 != null) {
            var2.writeInt32(8, var20);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.ready)) {
         Boolean var21 = var1.ready;
         if (var21 != null) {
            var2.writeBool(9, var21);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.skinchatinfo)) {
         PT_SKIN_CHAT_INFO var22 = var1.skinchatinfo;
         if (var22 != null) {
            CodedConstant.writeObject(var2, 10, FieldType.OBJECT, var22, false);
         }
      }

   }

   public void writeTo(PT_ROOM_USER_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_ROOM_USER_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_ROOM_USER_INFO var2 = new PT_ROOM_USER_INFO();

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
            } else if (var5 == 16) {
               var2.level = var1.readInt32();
            } else if (var5 == 24) {
               var2.job = var1.readInt32();
            } else if (var5 == 32) {
               var2.growtype = var1.readInt32();
            } else if (var5 == 40) {
               var2.secondgrowtype = var1.readInt32();
            } else if (var5 == 50) {
               var2.name = var1.readString();
            } else if (var5 == 56) {
               var2.teamtype = var1.readInt32();
            } else if (var5 == 64) {
               var2.creditscore = var1.readInt32();
            } else if (var5 == 72) {
               var2.ready = var1.readBool();
            } else if (var5 == 82) {
               Codec var10 = ProtobufProxy.create(PT_SKIN_CHAT_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.skinchatinfo = (PT_SKIN_CHAT_INFO)var10.readFrom(var1);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_ROOM_USER_INFO.class);
         return this.descriptor = var1;
      }
   }
}
