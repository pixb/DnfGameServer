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

public class REQ_GUILD_AGIT_MINIGAME_FINISH$$JProtoBufClass implements Codec<REQ_GUILD_AGIT_MINIGAME_FINISH>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_GUILD_AGIT_MINIGAME_FINISH var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_GUILD_AGIT_MINIGAME_FINISH decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_GUILD_AGIT_MINIGAME_FINISH var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.type)) {
         ENUM_GUILD_AGIT_MINIGAME_TYPE.T var11 = var1.type;
         var2 += CodedOutputStream.computeEnumSize(1, ((ENUM_GUILD_AGIT_MINIGAME_TYPE.T)var11).value());
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.score)) {
         Long var12 = var1.score;
         var2 += CodedOutputStream.computeUInt64Size(2, var12);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.distance)) {
         Long var13 = var1.distance;
         var2 += CodedOutputStream.computeUInt64Size(3, var13);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.mineindex)) {
         Integer var14 = var1.mineindex;
         var2 += CodedOutputStream.computeInt32Size(4, var14);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.minrank)) {
         Integer var15 = var1.minrank;
         var2 += CodedOutputStream.computeInt32Size(5, var15);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.maxrank)) {
         Integer var16 = var1.maxrank;
         var2 += CodedOutputStream.computeInt32Size(6, var16);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.finishtype)) {
         ENUM_GUILD_AGIT_FINISH_TYPE.T var17 = var1.finishtype;
         var2 += CodedOutputStream.computeEnumSize(7, ((ENUM_GUILD_AGIT_FINISH_TYPE.T)var17).value());
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.areaindex)) {
         Integer var18 = var1.areaindex;
         var2 += CodedOutputStream.computeInt32Size(8, var18);
      }

      return var2;
   }

   public void doWriteTo(REQ_GUILD_AGIT_MINIGAME_FINISH var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.type)) {
         ENUM_GUILD_AGIT_MINIGAME_TYPE.T var11 = var1.type;
         if (var11 != null) {
            var2.writeEnum(1, ((ENUM_GUILD_AGIT_MINIGAME_TYPE.T)var11).value());
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.score)) {
         Long var12 = var1.score;
         if (var12 != null) {
            var2.writeUInt64(2, var12);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.distance)) {
         Long var13 = var1.distance;
         if (var13 != null) {
            var2.writeUInt64(3, var13);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.mineindex)) {
         Integer var14 = var1.mineindex;
         if (var14 != null) {
            var2.writeInt32(4, var14);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.minrank)) {
         Integer var15 = var1.minrank;
         if (var15 != null) {
            var2.writeInt32(5, var15);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.maxrank)) {
         Integer var16 = var1.maxrank;
         if (var16 != null) {
            var2.writeInt32(6, var16);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.finishtype)) {
         ENUM_GUILD_AGIT_FINISH_TYPE.T var17 = var1.finishtype;
         if (var17 != null) {
            var2.writeEnum(7, ((ENUM_GUILD_AGIT_FINISH_TYPE.T)var17).value());
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.areaindex)) {
         Integer var18 = var1.areaindex;
         if (var18 != null) {
            var2.writeInt32(8, var18);
         }
      }

   }

   public void writeTo(REQ_GUILD_AGIT_MINIGAME_FINISH var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_GUILD_AGIT_MINIGAME_FINISH readFrom(CodedInputStream var1) throws IOException {
      REQ_GUILD_AGIT_MINIGAME_FINISH var2 = new REQ_GUILD_AGIT_MINIGAME_FINISH();
      var2.type = (ENUM_GUILD_AGIT_MINIGAME_TYPE.T)CodedConstant.getEnumValue(ENUM_GUILD_AGIT_MINIGAME_TYPE.T.class, ENUM_GUILD_AGIT_MINIGAME_TYPE.T.values()[0].name());
      var2.finishtype = (ENUM_GUILD_AGIT_FINISH_TYPE.T)CodedConstant.getEnumValue(ENUM_GUILD_AGIT_FINISH_TYPE.T.class, ENUM_GUILD_AGIT_FINISH_TYPE.T.values()[0].name());

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.type = (ENUM_GUILD_AGIT_MINIGAME_TYPE.T)CodedConstant.getEnumValue(ENUM_GUILD_AGIT_MINIGAME_TYPE.T.class, CodedConstant.getEnumName(ENUM_GUILD_AGIT_MINIGAME_TYPE.T.values(), var1.readEnum()));
            } else if (var5 == 16) {
               var2.score = var1.readUInt64();
            } else if (var5 == 24) {
               var2.distance = var1.readUInt64();
            } else if (var5 == 32) {
               var2.mineindex = var1.readInt32();
            } else if (var5 == 40) {
               var2.minrank = var1.readInt32();
            } else if (var5 == 48) {
               var2.maxrank = var1.readInt32();
            } else if (var5 == 56) {
               var2.finishtype = (ENUM_GUILD_AGIT_FINISH_TYPE.T)CodedConstant.getEnumValue(ENUM_GUILD_AGIT_FINISH_TYPE.T.class, CodedConstant.getEnumName(ENUM_GUILD_AGIT_FINISH_TYPE.T.values(), var1.readEnum()));
            } else if (var5 == 64) {
               var2.areaindex = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_GUILD_AGIT_MINIGAME_FINISH.class);
         return this.descriptor = var1;
      }
   }
}
