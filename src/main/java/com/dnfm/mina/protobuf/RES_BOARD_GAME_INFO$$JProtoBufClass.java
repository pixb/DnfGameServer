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

public class RES_BOARD_GAME_INFO$$JProtoBufClass implements Codec<RES_BOARD_GAME_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_BOARD_GAME_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_BOARD_GAME_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_BOARD_GAME_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var9 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var9);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.boardguid)) {
         Long var10 = var1.boardguid;
         var2 += CodedOutputStream.computeUInt64Size(2, var10);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.matchtype)) {
         Integer var11 = var1.matchtype;
         var2 += CodedOutputStream.computeInt32Size(3, var11);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.boardtype)) {
         ENUM_BOARD_TYPE.T var12 = var1.boardtype;
         var2 += CodedOutputStream.computeEnumSize(4, ((ENUM_BOARD_TYPE.T)var12).value());
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.users)) {
         List var13 = var1.users;
         var2 += CodedConstant.computeListSize(5, var13, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.boardinfo)) {
         PT_BOARD_GAME_INFO var14 = var1.boardinfo;
         var2 += CodedConstant.computeSize(6, var14, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      return var2;
   }

   public void doWriteTo(RES_BOARD_GAME_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var9 = var1.error;
         if (var9 != null) {
            var2.writeInt32(1, var9);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.boardguid)) {
         Long var10 = var1.boardguid;
         if (var10 != null) {
            var2.writeUInt64(2, var10);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.matchtype)) {
         Integer var11 = var1.matchtype;
         if (var11 != null) {
            var2.writeInt32(3, var11);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.boardtype)) {
         ENUM_BOARD_TYPE.T var12 = var1.boardtype;
         if (var12 != null) {
            var2.writeEnum(4, ((ENUM_BOARD_TYPE.T)var12).value());
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.users)) {
         List var13 = var1.users;
         if (var13 != null) {
            CodedConstant.writeToList(var2, 5, FieldType.OBJECT, var13, false);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.boardinfo)) {
         PT_BOARD_GAME_INFO var14 = var1.boardinfo;
         if (var14 != null) {
            CodedConstant.writeObject(var2, 6, FieldType.OBJECT, var14, false);
         }
      }

   }

   public void writeTo(RES_BOARD_GAME_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_BOARD_GAME_INFO readFrom(CodedInputStream var1) throws IOException {
      RES_BOARD_GAME_INFO var2 = new RES_BOARD_GAME_INFO();
      var2.users = new ArrayList();
      var2.boardtype = (ENUM_BOARD_TYPE.T)CodedConstant.getEnumValue(ENUM_BOARD_TYPE.T.class, ENUM_BOARD_TYPE.T.values()[0].name());

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
               var2.boardguid = var1.readUInt64();
            } else if (var5 == 24) {
               var2.matchtype = var1.readInt32();
            } else if (var5 == 32) {
               var2.boardtype = (ENUM_BOARD_TYPE.T)CodedConstant.getEnumValue(ENUM_BOARD_TYPE.T.class, CodedConstant.getEnumName(ENUM_BOARD_TYPE.T.values(), var1.readEnum()));
            } else if (var5 == 42) {
               Codec var10 = ProtobufProxy.create(PT_BOARD_USER_MINIMUM_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.users == null) {
                  var2.users = new ArrayList();
               }

               var2.users.add((PT_BOARD_USER_MINIMUM_INFO)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 50) {
               Codec var11 = ProtobufProxy.create(PT_BOARD_GAME_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var12 = var1.readRawVarint32();
               int var13 = var1.pushLimit(var12);
               var2.boardinfo = (PT_BOARD_GAME_INFO)var11.readFrom(var1);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_BOARD_GAME_INFO.class);
         return this.descriptor = var1;
      }
   }
}
