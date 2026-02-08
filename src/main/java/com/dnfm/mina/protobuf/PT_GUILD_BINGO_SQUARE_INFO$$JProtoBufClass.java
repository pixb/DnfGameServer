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

public class PT_GUILD_BINGO_SQUARE_INFO$$JProtoBufClass implements Codec<PT_GUILD_BINGO_SQUARE_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_GUILD_BINGO_SQUARE_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_GUILD_BINGO_SQUARE_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_GUILD_BINGO_SQUARE_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var10 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(1, var10);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.state)) {
         ENUM_GUILD_BINGO_SQUARE_STATE.T var11 = var1.state;
         var2 += CodedOutputStream.computeEnumSize(2, ((ENUM_GUILD_BINGO_SQUARE_STATE.T)var11).value());
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.playtype)) {
         ENUM_GUILD_BINGO_SQUARE_PLAY_TYPE.T var12 = var1.playtype;
         var2 += CodedOutputStream.computeEnumSize(3, ((ENUM_GUILD_BINGO_SQUARE_PLAY_TYPE.T)var12).value());
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.playindex)) {
         Integer var13 = var1.playindex;
         var2 += CodedOutputStream.computeInt32Size(4, var13);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.aid)) {
         Long var14 = var1.aid;
         var2 += CodedOutputStream.computeUInt64Size(5, var14);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.challengers)) {
         List var15 = var1.challengers;
         var2 += CodedConstant.computeListSize(6, var15, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.lastplaytime)) {
         Long var16 = var1.lastplaytime;
         var2 += CodedOutputStream.computeInt64Size(7, var16);
      }

      return var2;
   }

   public void doWriteTo(PT_GUILD_BINGO_SQUARE_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var10 = var1.index;
         if (var10 != null) {
            var2.writeInt32(1, var10);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.state)) {
         ENUM_GUILD_BINGO_SQUARE_STATE.T var11 = var1.state;
         if (var11 != null) {
            var2.writeEnum(2, ((ENUM_GUILD_BINGO_SQUARE_STATE.T)var11).value());
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.playtype)) {
         ENUM_GUILD_BINGO_SQUARE_PLAY_TYPE.T var12 = var1.playtype;
         if (var12 != null) {
            var2.writeEnum(3, ((ENUM_GUILD_BINGO_SQUARE_PLAY_TYPE.T)var12).value());
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.playindex)) {
         Integer var13 = var1.playindex;
         if (var13 != null) {
            var2.writeInt32(4, var13);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.aid)) {
         Long var14 = var1.aid;
         if (var14 != null) {
            var2.writeUInt64(5, var14);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.challengers)) {
         List var15 = var1.challengers;
         if (var15 != null) {
            CodedConstant.writeToList(var2, 6, FieldType.OBJECT, var15, false);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.lastplaytime)) {
         Long var16 = var1.lastplaytime;
         if (var16 != null) {
            var2.writeInt64(7, var16);
         }
      }

   }

   public void writeTo(PT_GUILD_BINGO_SQUARE_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_GUILD_BINGO_SQUARE_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_GUILD_BINGO_SQUARE_INFO var2 = new PT_GUILD_BINGO_SQUARE_INFO();
      var2.challengers = new ArrayList();
      var2.state = (ENUM_GUILD_BINGO_SQUARE_STATE.T)CodedConstant.getEnumValue(ENUM_GUILD_BINGO_SQUARE_STATE.T.class, ENUM_GUILD_BINGO_SQUARE_STATE.T.values()[0].name());
      var2.playtype = (ENUM_GUILD_BINGO_SQUARE_PLAY_TYPE.T)CodedConstant.getEnumValue(ENUM_GUILD_BINGO_SQUARE_PLAY_TYPE.T.class, ENUM_GUILD_BINGO_SQUARE_PLAY_TYPE.T.values()[0].name());

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.index = var1.readInt32();
            } else if (var5 == 16) {
               var2.state = (ENUM_GUILD_BINGO_SQUARE_STATE.T)CodedConstant.getEnumValue(ENUM_GUILD_BINGO_SQUARE_STATE.T.class, CodedConstant.getEnumName(ENUM_GUILD_BINGO_SQUARE_STATE.T.values(), var1.readEnum()));
            } else if (var5 == 24) {
               var2.playtype = (ENUM_GUILD_BINGO_SQUARE_PLAY_TYPE.T)CodedConstant.getEnumValue(ENUM_GUILD_BINGO_SQUARE_PLAY_TYPE.T.class, CodedConstant.getEnumName(ENUM_GUILD_BINGO_SQUARE_PLAY_TYPE.T.values(), var1.readEnum()));
            } else if (var5 == 32) {
               var2.playindex = var1.readInt32();
            } else if (var5 == 40) {
               var2.aid = var1.readUInt64();
            } else if (var5 == 50) {
               Codec var10 = ProtobufProxy.create(PT_GUILD_BINGO_CHALLENGERS_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.challengers == null) {
                  var2.challengers = new ArrayList();
               }

               var2.challengers.add((PT_GUILD_BINGO_CHALLENGERS_INFO)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 56) {
               var2.lastplaytime = var1.readInt64();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_GUILD_BINGO_SQUARE_INFO.class);
         return this.descriptor = var1;
      }
   }
}
