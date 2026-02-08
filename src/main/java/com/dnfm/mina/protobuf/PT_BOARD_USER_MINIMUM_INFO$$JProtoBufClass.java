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

public class PT_BOARD_USER_MINIMUM_INFO$$JProtoBufClass implements Codec<PT_BOARD_USER_MINIMUM_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_BOARD_USER_MINIMUM_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_BOARD_USER_MINIMUM_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_BOARD_USER_MINIMUM_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var11 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var11);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.playerid)) {
         Integer var12 = var1.playerid;
         var2 += CodedOutputStream.computeInt32Size(2, var12);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var13 = var1.growtype;
         var2 += CodedOutputStream.computeInt32Size(3, var13);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.secgrowtype)) {
         Integer var14 = var1.secgrowtype;
         var2 += CodedOutputStream.computeInt32Size(4, var14);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var15 = var1.job;
         var2 += CodedOutputStream.computeInt32Size(5, var15);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var16 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(6, var16);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var17 = var1.name;
         var2 += CodedOutputStream.computeStringSize(7, var17);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.teamtype)) {
         ENUM_TEAM.T var18 = var1.teamtype;
         var2 += CodedOutputStream.computeEnumSize(8, ((Enum)var18).ordinal());
      }

      return var2;
   }

   public void doWriteTo(PT_BOARD_USER_MINIMUM_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var11 = var1.charguid;
         if (var11 != null) {
            var2.writeUInt64(1, var11);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.playerid)) {
         Integer var12 = var1.playerid;
         if (var12 != null) {
            var2.writeInt32(2, var12);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var13 = var1.growtype;
         if (var13 != null) {
            var2.writeInt32(3, var13);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.secgrowtype)) {
         Integer var14 = var1.secgrowtype;
         if (var14 != null) {
            var2.writeInt32(4, var14);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var15 = var1.job;
         if (var15 != null) {
            var2.writeInt32(5, var15);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var16 = var1.level;
         if (var16 != null) {
            var2.writeInt32(6, var16);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var17 = var1.name;
         if (var17 != null) {
            var2.writeString(7, var17);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.teamtype)) {
         ENUM_TEAM.T var18 = var1.teamtype;
         if (var18 != null) {
            var2.writeEnum(8, ((Enum)var18).ordinal());
         }
      }

   }

   public void writeTo(PT_BOARD_USER_MINIMUM_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_BOARD_USER_MINIMUM_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_BOARD_USER_MINIMUM_INFO var2 = new PT_BOARD_USER_MINIMUM_INFO();
      var2.teamtype = (ENUM_TEAM.T)CodedConstant.getEnumValue(ENUM_TEAM.T.class, ENUM_TEAM.T.values()[0].name());

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.charguid = var1.readUInt64();
            } else if (var5 == 16) {
               var2.playerid = var1.readInt32();
            } else if (var5 == 24) {
               var2.growtype = var1.readInt32();
            } else if (var5 == 32) {
               var2.secgrowtype = var1.readInt32();
            } else if (var5 == 40) {
               var2.job = var1.readInt32();
            } else if (var5 == 48) {
               var2.level = var1.readInt32();
            } else if (var5 == 58) {
               var2.name = var1.readString();
            } else if (var5 == 64) {
               var2.teamtype = (ENUM_TEAM.T)CodedConstant.getEnumValue(ENUM_TEAM.T.class, CodedConstant.getEnumName(ENUM_TEAM.T.values(), var1.readEnum()));
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_BOARD_USER_MINIMUM_INFO.class);
         return this.descriptor = var1;
      }
   }
}
