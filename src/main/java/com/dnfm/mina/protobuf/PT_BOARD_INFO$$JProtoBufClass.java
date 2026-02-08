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

public class PT_BOARD_INFO$$JProtoBufClass implements Codec<PT_BOARD_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_BOARD_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_BOARD_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_BOARD_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.score)) {
         Integer var12 = var1.score;
         var2 += CodedOutputStream.computeInt32Size(1, var12);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.msg)) {
         String var13 = var1.msg;
         var2 += CodedOutputStream.computeStringSize(2, var13);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.noteguid)) {
         Long var14 = var1.noteguid;
         var2 += CodedOutputStream.computeUInt64Size(3, var14);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.charinfo)) {
         PT_CHARACTER var15 = var1.charinfo;
         var2 += CodedConstant.computeSize(4, var15, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.likecount)) {
         Integer var16 = var1.likecount;
         var2 += CodedOutputStream.computeInt32Size(5, var16);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.hatecount)) {
         Integer var17 = var1.hatecount;
         var2 += CodedOutputStream.computeInt32Size(6, var17);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.registtime)) {
         Long var18 = var1.registtime;
         var2 += CodedOutputStream.computeInt64Size(7, var18);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.myreaction)) {
         Integer var19 = var1.myreaction;
         var2 += CodedOutputStream.computeInt32Size(8, var19);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.skinchatinfo)) {
         PT_SKIN_CHAT_INFO var20 = var1.skinchatinfo;
         var2 += CodedConstant.computeSize(9, var20, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      return var2;
   }

   public void doWriteTo(PT_BOARD_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.score)) {
         Integer var12 = var1.score;
         if (var12 != null) {
            var2.writeInt32(1, var12);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.msg)) {
         String var13 = var1.msg;
         if (var13 != null) {
            var2.writeString(2, var13);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.noteguid)) {
         Long var14 = var1.noteguid;
         if (var14 != null) {
            var2.writeUInt64(3, var14);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.charinfo)) {
         PT_CHARACTER var15 = var1.charinfo;
         if (var15 != null) {
            CodedConstant.writeObject(var2, 4, FieldType.OBJECT, var15, false);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.likecount)) {
         Integer var16 = var1.likecount;
         if (var16 != null) {
            var2.writeInt32(5, var16);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.hatecount)) {
         Integer var17 = var1.hatecount;
         if (var17 != null) {
            var2.writeInt32(6, var17);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.registtime)) {
         Long var18 = var1.registtime;
         if (var18 != null) {
            var2.writeInt64(7, var18);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.myreaction)) {
         Integer var19 = var1.myreaction;
         if (var19 != null) {
            var2.writeInt32(8, var19);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.skinchatinfo)) {
         PT_SKIN_CHAT_INFO var20 = var1.skinchatinfo;
         if (var20 != null) {
            CodedConstant.writeObject(var2, 9, FieldType.OBJECT, var20, false);
         }
      }

   }

   public void writeTo(PT_BOARD_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_BOARD_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_BOARD_INFO var2 = new PT_BOARD_INFO();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.score = var1.readInt32();
            } else if (var5 == 18) {
               var2.msg = var1.readString();
            } else if (var5 == 24) {
               var2.noteguid = var1.readUInt64();
            } else if (var5 == 34) {
               Codec var10 = ProtobufProxy.create(PT_CHARACTER.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.charinfo = (PT_CHARACTER)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 40) {
               var2.likecount = var1.readInt32();
            } else if (var5 == 48) {
               var2.hatecount = var1.readInt32();
            } else if (var5 == 56) {
               var2.registtime = var1.readInt64();
            } else if (var5 == 64) {
               var2.myreaction = var1.readInt32();
            } else if (var5 == 74) {
               Codec var11 = ProtobufProxy.create(PT_SKIN_CHAT_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var12 = var1.readRawVarint32();
               int var13 = var1.pushLimit(var12);
               var2.skinchatinfo = (PT_SKIN_CHAT_INFO)var11.readFrom(var1);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_BOARD_INFO.class);
         return this.descriptor = var1;
      }
   }
}
