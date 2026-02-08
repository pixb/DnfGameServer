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

public class REQ_BOARD_GAME_SYNC$$JProtoBufClass implements Codec<REQ_BOARD_GAME_SYNC>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_BOARD_GAME_SYNC var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_BOARD_GAME_SYNC decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_BOARD_GAME_SYNC var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.type)) {
         ENUM_BOARD_GAME_SYNC_TYPE.T var4 = var1.type;
         var2 += CodedOutputStream.computeEnumSize(1, ((ENUM_BOARD_GAME_SYNC_TYPE.T)var4).value());
      }

      return var2;
   }

   public void doWriteTo(REQ_BOARD_GAME_SYNC var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.type)) {
         ENUM_BOARD_GAME_SYNC_TYPE.T var4 = var1.type;
         if (var4 != null) {
            var2.writeEnum(1, ((ENUM_BOARD_GAME_SYNC_TYPE.T)var4).value());
         }
      }

   }

   public void writeTo(REQ_BOARD_GAME_SYNC var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_BOARD_GAME_SYNC readFrom(CodedInputStream var1) throws IOException {
      REQ_BOARD_GAME_SYNC var2 = new REQ_BOARD_GAME_SYNC();
      var2.type = (ENUM_BOARD_GAME_SYNC_TYPE.T)CodedConstant.getEnumValue(ENUM_BOARD_GAME_SYNC_TYPE.T.class, ENUM_BOARD_GAME_SYNC_TYPE.T.values()[0].name());

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.type = (ENUM_BOARD_GAME_SYNC_TYPE.T)CodedConstant.getEnumValue(ENUM_BOARD_GAME_SYNC_TYPE.T.class, CodedConstant.getEnumName(ENUM_BOARD_GAME_SYNC_TYPE.T.values(), var1.readEnum()));
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_BOARD_GAME_SYNC.class);
         return this.descriptor = var1;
      }
   }
}
