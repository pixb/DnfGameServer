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

public class REQ_BOARD_GAME_RESULT$$JProtoBufClass implements Codec<REQ_BOARD_GAME_RESULT>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_BOARD_GAME_RESULT var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_BOARD_GAME_RESULT decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_BOARD_GAME_RESULT var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.boardguid)) {
         Long var7 = var1.boardguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var7);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.boardtype)) {
         ENUM_BOARD_TYPE.T var8 = var1.boardtype;
         var2 += CodedOutputStream.computeEnumSize(2, ((ENUM_BOARD_TYPE.T)var8).value());
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.results)) {
         List var9 = var1.results;
         var2 += CodedConstant.computeListSize(3, var9, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.type)) {
         ENUM_BOARD_RESULT_TYPE.T var10 = var1.type;
         var2 += CodedOutputStream.computeEnumSize(4, ((ENUM_BOARD_RESULT_TYPE.T)var10).value());
      }

      return var2;
   }

   public void doWriteTo(REQ_BOARD_GAME_RESULT var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.boardguid)) {
         Long var7 = var1.boardguid;
         if (var7 != null) {
            var2.writeUInt64(1, var7);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.boardtype)) {
         ENUM_BOARD_TYPE.T var8 = var1.boardtype;
         if (var8 != null) {
            var2.writeEnum(2, ((ENUM_BOARD_TYPE.T)var8).value());
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.results)) {
         List var9 = var1.results;
         if (var9 != null) {
            CodedConstant.writeToList(var2, 3, FieldType.OBJECT, var9, false);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.type)) {
         ENUM_BOARD_RESULT_TYPE.T var10 = var1.type;
         if (var10 != null) {
            var2.writeEnum(4, ((ENUM_BOARD_RESULT_TYPE.T)var10).value());
         }
      }

   }

   public void writeTo(REQ_BOARD_GAME_RESULT var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_BOARD_GAME_RESULT readFrom(CodedInputStream var1) throws IOException {
      REQ_BOARD_GAME_RESULT var2 = new REQ_BOARD_GAME_RESULT();
      var2.results = new ArrayList();
      var2.boardtype = (ENUM_BOARD_TYPE.T)CodedConstant.getEnumValue(ENUM_BOARD_TYPE.T.class, ENUM_BOARD_TYPE.T.values()[0].name());
      var2.type = (ENUM_BOARD_RESULT_TYPE.T)CodedConstant.getEnumValue(ENUM_BOARD_RESULT_TYPE.T.class, ENUM_BOARD_RESULT_TYPE.T.values()[0].name());

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.boardguid = var1.readUInt64();
            } else if (var5 == 16) {
               var2.boardtype = (ENUM_BOARD_TYPE.T)CodedConstant.getEnumValue(ENUM_BOARD_TYPE.T.class, CodedConstant.getEnumName(ENUM_BOARD_TYPE.T.values(), var1.readEnum()));
            } else if (var5 == 26) {
               Codec var10 = ProtobufProxy.create(PT_USER_BOARD_GAME_RESULT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.results == null) {
                  var2.results = new ArrayList();
               }

               var2.results.add((PT_USER_BOARD_GAME_RESULT)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 32) {
               var2.type = (ENUM_BOARD_RESULT_TYPE.T)CodedConstant.getEnumValue(ENUM_BOARD_RESULT_TYPE.T.class, CodedConstant.getEnumName(ENUM_BOARD_RESULT_TYPE.T.values(), var1.readEnum()));
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_BOARD_GAME_RESULT.class);
         return this.descriptor = var1;
      }
   }
}
