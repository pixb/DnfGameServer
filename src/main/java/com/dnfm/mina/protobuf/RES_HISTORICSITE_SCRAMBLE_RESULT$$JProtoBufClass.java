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

public class RES_HISTORICSITE_SCRAMBLE_RESULT$$JProtoBufClass implements Codec<RES_HISTORICSITE_SCRAMBLE_RESULT>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_HISTORICSITE_SCRAMBLE_RESULT var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_HISTORICSITE_SCRAMBLE_RESULT decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_HISTORICSITE_SCRAMBLE_RESULT var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var9 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var9);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.win)) {
         ENUM_TEAM.T var10 = var1.win;
         var2 += CodedOutputStream.computeEnumSize(2, ((Enum)var10).ordinal());
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.endtime)) {
         Long var11 = var1.endtime;
         var2 += CodedOutputStream.computeInt64Size(3, var11);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.red)) {
         PT_TEAM_RESULT var12 = var1.red;
         var2 += CodedConstant.computeSize(4, var12, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.blue)) {
         PT_TEAM_RESULT var13 = var1.blue;
         var2 += CodedConstant.computeSize(5, var13, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.excavation)) {
         PT_HISTORICSITE_EXCAVATION_RESULT var14 = var1.excavation;
         var2 += CodedConstant.computeSize(6, var14, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      return var2;
   }

   public void doWriteTo(RES_HISTORICSITE_SCRAMBLE_RESULT var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var9 = var1.error;
         if (var9 != null) {
            var2.writeInt32(1, var9);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.win)) {
         ENUM_TEAM.T var10 = var1.win;
         if (var10 != null) {
            var2.writeEnum(2, ((Enum)var10).ordinal());
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.endtime)) {
         Long var11 = var1.endtime;
         if (var11 != null) {
            var2.writeInt64(3, var11);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.red)) {
         PT_TEAM_RESULT var12 = var1.red;
         if (var12 != null) {
            CodedConstant.writeObject(var2, 4, FieldType.OBJECT, var12, false);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.blue)) {
         PT_TEAM_RESULT var13 = var1.blue;
         if (var13 != null) {
            CodedConstant.writeObject(var2, 5, FieldType.OBJECT, var13, false);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.excavation)) {
         PT_HISTORICSITE_EXCAVATION_RESULT var14 = var1.excavation;
         if (var14 != null) {
            CodedConstant.writeObject(var2, 6, FieldType.OBJECT, var14, false);
         }
      }

   }

   public void writeTo(RES_HISTORICSITE_SCRAMBLE_RESULT var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_HISTORICSITE_SCRAMBLE_RESULT readFrom(CodedInputStream var1) throws IOException {
      RES_HISTORICSITE_SCRAMBLE_RESULT var2 = new RES_HISTORICSITE_SCRAMBLE_RESULT();
      var2.win = (ENUM_TEAM.T)CodedConstant.getEnumValue(ENUM_TEAM.T.class, ENUM_TEAM.T.values()[0].name());

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
               var2.win = (ENUM_TEAM.T)CodedConstant.getEnumValue(ENUM_TEAM.T.class, CodedConstant.getEnumName(ENUM_TEAM.T.values(), var1.readEnum()));
            } else if (var5 == 24) {
               var2.endtime = var1.readInt64();
            } else if (var5 == 34) {
               Codec var10 = ProtobufProxy.create(PT_TEAM_RESULT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.red = (PT_TEAM_RESULT)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 42) {
               Codec var11 = ProtobufProxy.create(PT_TEAM_RESULT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var13 = var1.readRawVarint32();
               int var15 = var1.pushLimit(var13);
               var2.blue = (PT_TEAM_RESULT)var11.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var15);
            } else if (var5 == 50) {
               Codec var12 = ProtobufProxy.create(PT_HISTORICSITE_EXCAVATION_RESULT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var14 = var1.readRawVarint32();
               int var16 = var1.pushLimit(var14);
               var2.excavation = (PT_HISTORICSITE_EXCAVATION_RESULT)var12.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var16);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_HISTORICSITE_SCRAMBLE_RESULT.class);
         return this.descriptor = var1;
      }
   }
}
