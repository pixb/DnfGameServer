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

public class RES_LOAD_MARRIAGE_INFO$$JProtoBufClass implements Codec<RES_LOAD_MARRIAGE_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_LOAD_MARRIAGE_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_LOAD_MARRIAGE_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_LOAD_MARRIAGE_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var11 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var11);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.marriageguid)) {
         Long var12 = var1.marriageguid;
         var2 += CodedOutputStream.computeUInt64Size(2, var12);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.groom)) {
         PT_MARRIAGE_CHARACTER_INFO var13 = var1.groom;
         var2 += CodedConstant.computeSize(3, var13, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.bride)) {
         PT_MARRIAGE_CHARACTER_INFO var14 = var1.bride;
         var2 += CodedConstant.computeSize(4, var14, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.weddingdate)) {
         Long var15 = var1.weddingdate;
         var2 += CodedOutputStream.computeInt64Size(5, var15);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.theme)) {
         PT_WEDDING_THEME var16 = var1.theme;
         var2 += CodedConstant.computeSize(6, var16, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.maritalstatus)) {
         Integer var17 = var1.maritalstatus;
         var2 += CodedOutputStream.computeInt32Size(7, var17);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.divorcedate)) {
         Long var18 = var1.divorcedate;
         var2 += CodedOutputStream.computeInt64Size(8, var18);
      }

      return var2;
   }

   public void doWriteTo(RES_LOAD_MARRIAGE_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var11 = var1.error;
         if (var11 != null) {
            var2.writeInt32(1, var11);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.marriageguid)) {
         Long var12 = var1.marriageguid;
         if (var12 != null) {
            var2.writeUInt64(2, var12);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.groom)) {
         PT_MARRIAGE_CHARACTER_INFO var13 = var1.groom;
         if (var13 != null) {
            CodedConstant.writeObject(var2, 3, FieldType.OBJECT, var13, false);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.bride)) {
         PT_MARRIAGE_CHARACTER_INFO var14 = var1.bride;
         if (var14 != null) {
            CodedConstant.writeObject(var2, 4, FieldType.OBJECT, var14, false);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.weddingdate)) {
         Long var15 = var1.weddingdate;
         if (var15 != null) {
            var2.writeInt64(5, var15);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.theme)) {
         PT_WEDDING_THEME var16 = var1.theme;
         if (var16 != null) {
            CodedConstant.writeObject(var2, 6, FieldType.OBJECT, var16, false);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.maritalstatus)) {
         Integer var17 = var1.maritalstatus;
         if (var17 != null) {
            var2.writeInt32(7, var17);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.divorcedate)) {
         Long var18 = var1.divorcedate;
         if (var18 != null) {
            var2.writeInt64(8, var18);
         }
      }

   }

   public void writeTo(RES_LOAD_MARRIAGE_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_LOAD_MARRIAGE_INFO readFrom(CodedInputStream var1) throws IOException {
      RES_LOAD_MARRIAGE_INFO var2 = new RES_LOAD_MARRIAGE_INFO();

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
            } else if (var5 == 16) {
               var2.marriageguid = var1.readUInt64();
            } else if (var5 == 26) {
               Codec var10 = ProtobufProxy.create(PT_MARRIAGE_CHARACTER_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.groom = (PT_MARRIAGE_CHARACTER_INFO)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 34) {
               Codec var11 = ProtobufProxy.create(PT_MARRIAGE_CHARACTER_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var13 = var1.readRawVarint32();
               int var15 = var1.pushLimit(var13);
               var2.bride = (PT_MARRIAGE_CHARACTER_INFO)var11.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var15);
            } else if (var5 == 40) {
               var2.weddingdate = var1.readInt64();
            } else if (var5 == 50) {
               Codec var12 = ProtobufProxy.create(PT_WEDDING_THEME.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var14 = var1.readRawVarint32();
               int var16 = var1.pushLimit(var14);
               var2.theme = (PT_WEDDING_THEME)var12.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var16);
            } else if (var5 == 56) {
               var2.maritalstatus = var1.readInt32();
            } else if (var5 == 64) {
               var2.divorcedate = var1.readInt64();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_LOAD_MARRIAGE_INFO.class);
         return this.descriptor = var1;
      }
   }
}
