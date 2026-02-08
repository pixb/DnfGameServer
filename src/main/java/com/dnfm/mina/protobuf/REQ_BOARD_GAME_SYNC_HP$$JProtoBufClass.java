package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.code.CodedConstant;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.InvalidProtocolBufferException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class REQ_BOARD_GAME_SYNC_HP$$JProtoBufClass implements Codec<REQ_BOARD_GAME_SYNC_HP>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_BOARD_GAME_SYNC_HP var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_BOARD_GAME_SYNC_HP decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_BOARD_GAME_SYNC_HP var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.boardguid)) {
         Long var6 = var1.boardguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var6);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.boardtype)) {
         ENUM_BOARD_TYPE.T var7 = var1.boardtype;
         var2 += CodedOutputStream.computeEnumSize(2, ((ENUM_BOARD_TYPE.T)var7).value());
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.hp)) {
         Integer var8 = var1.hp;
         var2 += CodedOutputStream.computeInt32Size(3, var8);
      }

      return var2;
   }

   public void doWriteTo(REQ_BOARD_GAME_SYNC_HP var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.boardguid)) {
         Long var6 = var1.boardguid;
         if (var6 != null) {
            var2.writeUInt64(1, var6);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.boardtype)) {
         ENUM_BOARD_TYPE.T var7 = var1.boardtype;
         if (var7 != null) {
            var2.writeEnum(2, ((ENUM_BOARD_TYPE.T)var7).value());
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.hp)) {
         Integer var8 = var1.hp;
         if (var8 != null) {
            var2.writeInt32(3, var8);
         }
      }

   }

   public void writeTo(REQ_BOARD_GAME_SYNC_HP var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_BOARD_GAME_SYNC_HP readFrom(CodedInputStream var1) throws IOException {
      REQ_BOARD_GAME_SYNC_HP var2 = new REQ_BOARD_GAME_SYNC_HP();
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
               var2.boardguid = var1.readUInt64();
            } else if (var5 == 16) {
               var2.boardtype = (ENUM_BOARD_TYPE.T)CodedConstant.getEnumValue(ENUM_BOARD_TYPE.T.class, CodedConstant.getEnumName(ENUM_BOARD_TYPE.T.values(), var1.readEnum()));
            } else if (var5 == 24) {
               var2.hp = var1.readInt32();
            } else {
               var1.skipField(var5);
            }
         }

         return var2;
      } catch (InvalidProtocolBufferException var6) {
         throw var6;
      } catch (IOException var7) {
         throw var7;
      }
   }

   public Descriptors.Descriptor getDescriptor() throws IOException {
      if (this.descriptor != null) {
         return this.descriptor;
      } else {
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_BOARD_GAME_SYNC_HP.class);
         return this.descriptor = var1;
      }
   }
}
