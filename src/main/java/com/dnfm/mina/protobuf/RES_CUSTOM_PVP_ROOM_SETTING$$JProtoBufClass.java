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

public class RES_CUSTOM_PVP_ROOM_SETTING$$JProtoBufClass implements Codec<RES_CUSTOM_PVP_ROOM_SETTING>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_CUSTOM_PVP_ROOM_SETTING var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_CUSTOM_PVP_ROOM_SETTING decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_CUSTOM_PVP_ROOM_SETTING var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var7 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var7);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.customdata)) {
         PT_CUSTOM_PVP_ROOM_SETTING var8 = var1.customdata;
         var2 += CodedConstant.computeSize(2, var8, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var9 = var1.guid;
         var2 += CodedOutputStream.computeUInt64Size(3, var9);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.type)) {
         ENUM_ROOM_TYPE.T var10 = var1.type;
         var2 += CodedOutputStream.computeEnumSize(4, ((ENUM_ROOM_TYPE.T)var10).value());
      }

      return var2;
   }

   public void doWriteTo(RES_CUSTOM_PVP_ROOM_SETTING var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var7 = var1.error;
         if (var7 != null) {
            var2.writeInt32(1, var7);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.customdata)) {
         PT_CUSTOM_PVP_ROOM_SETTING var8 = var1.customdata;
         if (var8 != null) {
            CodedConstant.writeObject(var2, 2, FieldType.OBJECT, var8, false);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var9 = var1.guid;
         if (var9 != null) {
            var2.writeUInt64(3, var9);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.type)) {
         ENUM_ROOM_TYPE.T var10 = var1.type;
         if (var10 != null) {
            var2.writeEnum(4, ((ENUM_ROOM_TYPE.T)var10).value());
         }
      }

   }

   public void writeTo(RES_CUSTOM_PVP_ROOM_SETTING var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_CUSTOM_PVP_ROOM_SETTING readFrom(CodedInputStream var1) throws IOException {
      RES_CUSTOM_PVP_ROOM_SETTING var2 = new RES_CUSTOM_PVP_ROOM_SETTING();
      var2.type = (ENUM_ROOM_TYPE.T)CodedConstant.getEnumValue(ENUM_ROOM_TYPE.T.class, ENUM_ROOM_TYPE.T.values()[0].name());

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
            } else if (var5 == 18) {
               Codec var10 = ProtobufProxy.create(PT_CUSTOM_PVP_ROOM_SETTING.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.customdata = (PT_CUSTOM_PVP_ROOM_SETTING)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 24) {
               var2.guid = var1.readUInt64();
            } else if (var5 == 32) {
               var2.type = (ENUM_ROOM_TYPE.T)CodedConstant.getEnumValue(ENUM_ROOM_TYPE.T.class, CodedConstant.getEnumName(ENUM_ROOM_TYPE.T.values(), var1.readEnum()));
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_CUSTOM_PVP_ROOM_SETTING.class);
         return this.descriptor = var1;
      }
   }
}
