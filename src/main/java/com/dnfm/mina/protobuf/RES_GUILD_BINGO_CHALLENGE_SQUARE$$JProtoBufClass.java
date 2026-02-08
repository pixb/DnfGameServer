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

public class RES_GUILD_BINGO_CHALLENGE_SQUARE$$JProtoBufClass implements Codec<RES_GUILD_BINGO_CHALLENGE_SQUARE>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_GUILD_BINGO_CHALLENGE_SQUARE var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_GUILD_BINGO_CHALLENGE_SQUARE decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_GUILD_BINGO_CHALLENGE_SQUARE var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var9 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var9);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.maplevel)) {
         Integer var10 = var1.maplevel;
         var2 += CodedOutputStream.computeInt32Size(2, var10);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.squareindex)) {
         Integer var11 = var1.squareindex;
         var2 += CodedOutputStream.computeInt32Size(3, var11);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.playtype)) {
         ENUM_GUILD_BINGO_SQUARE_PLAY_TYPE.T var12 = var1.playtype;
         var2 += CodedOutputStream.computeEnumSize(4, ((ENUM_GUILD_BINGO_SQUARE_PLAY_TYPE.T)var12).value());
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.playindex)) {
         Integer var13 = var1.playindex;
         var2 += CodedOutputStream.computeInt32Size(5, var13);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.type)) {
         ENUM_GUILD_BINGO_CHALLENGE_TYPE.T var14 = var1.type;
         var2 += CodedOutputStream.computeEnumSize(6, ((ENUM_GUILD_BINGO_CHALLENGE_TYPE.T)var14).value());
      }

      return var2;
   }

   public void doWriteTo(RES_GUILD_BINGO_CHALLENGE_SQUARE var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var9 = var1.error;
         if (var9 != null) {
            var2.writeInt32(1, var9);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.maplevel)) {
         Integer var10 = var1.maplevel;
         if (var10 != null) {
            var2.writeInt32(2, var10);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.squareindex)) {
         Integer var11 = var1.squareindex;
         if (var11 != null) {
            var2.writeInt32(3, var11);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.playtype)) {
         ENUM_GUILD_BINGO_SQUARE_PLAY_TYPE.T var12 = var1.playtype;
         if (var12 != null) {
            var2.writeEnum(4, ((ENUM_GUILD_BINGO_SQUARE_PLAY_TYPE.T)var12).value());
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.playindex)) {
         Integer var13 = var1.playindex;
         if (var13 != null) {
            var2.writeInt32(5, var13);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.type)) {
         ENUM_GUILD_BINGO_CHALLENGE_TYPE.T var14 = var1.type;
         if (var14 != null) {
            var2.writeEnum(6, ((ENUM_GUILD_BINGO_CHALLENGE_TYPE.T)var14).value());
         }
      }

   }

   public void writeTo(RES_GUILD_BINGO_CHALLENGE_SQUARE var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_GUILD_BINGO_CHALLENGE_SQUARE readFrom(CodedInputStream var1) throws IOException {
      RES_GUILD_BINGO_CHALLENGE_SQUARE var2 = new RES_GUILD_BINGO_CHALLENGE_SQUARE();
      var2.playtype = (ENUM_GUILD_BINGO_SQUARE_PLAY_TYPE.T)CodedConstant.getEnumValue(ENUM_GUILD_BINGO_SQUARE_PLAY_TYPE.T.class, ENUM_GUILD_BINGO_SQUARE_PLAY_TYPE.T.values()[0].name());
      var2.type = (ENUM_GUILD_BINGO_CHALLENGE_TYPE.T)CodedConstant.getEnumValue(ENUM_GUILD_BINGO_CHALLENGE_TYPE.T.class, ENUM_GUILD_BINGO_CHALLENGE_TYPE.T.values()[0].name());

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
               var2.maplevel = var1.readInt32();
            } else if (var5 == 24) {
               var2.squareindex = var1.readInt32();
            } else if (var5 == 32) {
               var2.playtype = (ENUM_GUILD_BINGO_SQUARE_PLAY_TYPE.T)CodedConstant.getEnumValue(ENUM_GUILD_BINGO_SQUARE_PLAY_TYPE.T.class, CodedConstant.getEnumName(ENUM_GUILD_BINGO_SQUARE_PLAY_TYPE.T.values(), var1.readEnum()));
            } else if (var5 == 40) {
               var2.playindex = var1.readInt32();
            } else if (var5 == 48) {
               var2.type = (ENUM_GUILD_BINGO_CHALLENGE_TYPE.T)CodedConstant.getEnumValue(ENUM_GUILD_BINGO_CHALLENGE_TYPE.T.class, CodedConstant.getEnumName(ENUM_GUILD_BINGO_CHALLENGE_TYPE.T.values(), var1.readEnum()));
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_GUILD_BINGO_CHALLENGE_SQUARE.class);
         return this.descriptor = var1;
      }
   }
}
