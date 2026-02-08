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

public class PT_USER_MINIMUM_INFO$$JProtoBufClass implements Codec<PT_USER_MINIMUM_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_USER_MINIMUM_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_USER_MINIMUM_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_USER_MINIMUM_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var16 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var16);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var17 = var1.growtype;
         var2 += CodedOutputStream.computeInt32Size(2, var17);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.secgrowtype)) {
         Integer var18 = var1.secgrowtype;
         var2 += CodedOutputStream.computeInt32Size(3, var18);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var19 = var1.job;
         var2 += CodedOutputStream.computeInt32Size(4, var19);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var20 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(5, var20);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var21 = var1.name;
         var2 += CodedOutputStream.computeStringSize(6, var21);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.teamtype)) {
         ENUM_TEAM.T var22 = var1.teamtype;
         var2 += CodedOutputStream.computeEnumSize(7, ((Enum)var22).ordinal());
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.rank)) {
         Integer var23 = var1.rank;
         var2 += CodedOutputStream.computeInt32Size(8, var23);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.pvpstargrade)) {
         Integer var24 = var1.pvpstargrade;
         var2 += CodedOutputStream.computeInt32Size(9, var24);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.profileurl)) {
         String var25 = var1.profileurl;
         var2 += CodedOutputStream.computeStringSize(10, var25);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var26 = var1.world;
         var2 += CodedOutputStream.computeInt32Size(11, var26);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.skinchatinfo)) {
         PT_SKIN_CHAT_INFO var27 = var1.skinchatinfo;
         var2 += CodedConstant.computeSize(12, var27, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.iskeyboard)) {
         Boolean var28 = var1.iskeyboard;
         var2 += CodedOutputStream.computeBoolSize(13, var28);
      }

      return var2;
   }

   public void doWriteTo(PT_USER_MINIMUM_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var16 = var1.charguid;
         if (var16 != null) {
            var2.writeUInt64(1, var16);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var17 = var1.growtype;
         if (var17 != null) {
            var2.writeInt32(2, var17);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.secgrowtype)) {
         Integer var18 = var1.secgrowtype;
         if (var18 != null) {
            var2.writeInt32(3, var18);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var19 = var1.job;
         if (var19 != null) {
            var2.writeInt32(4, var19);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var20 = var1.level;
         if (var20 != null) {
            var2.writeInt32(5, var20);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var21 = var1.name;
         if (var21 != null) {
            var2.writeString(6, var21);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.teamtype)) {
         ENUM_TEAM.T var22 = var1.teamtype;
         if (var22 != null) {
            var2.writeEnum(7, ((Enum)var22).ordinal());
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.rank)) {
         Integer var23 = var1.rank;
         if (var23 != null) {
            var2.writeInt32(8, var23);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.pvpstargrade)) {
         Integer var24 = var1.pvpstargrade;
         if (var24 != null) {
            var2.writeInt32(9, var24);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.profileurl)) {
         String var25 = var1.profileurl;
         if (var25 != null) {
            var2.writeString(10, var25);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var26 = var1.world;
         if (var26 != null) {
            var2.writeInt32(11, var26);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.skinchatinfo)) {
         PT_SKIN_CHAT_INFO var27 = var1.skinchatinfo;
         if (var27 != null) {
            CodedConstant.writeObject(var2, 12, FieldType.OBJECT, var27, false);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.iskeyboard)) {
         Boolean var28 = var1.iskeyboard;
         if (var28 != null) {
            var2.writeBool(13, var28);
         }
      }

   }

   public void writeTo(PT_USER_MINIMUM_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_USER_MINIMUM_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_USER_MINIMUM_INFO var2 = new PT_USER_MINIMUM_INFO();
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
               var2.growtype = var1.readInt32();
            } else if (var5 == 24) {
               var2.secgrowtype = var1.readInt32();
            } else if (var5 == 32) {
               var2.job = var1.readInt32();
            } else if (var5 == 40) {
               var2.level = var1.readInt32();
            } else if (var5 == 50) {
               var2.name = var1.readString();
            } else if (var5 == 56) {
               var2.teamtype = (ENUM_TEAM.T)CodedConstant.getEnumValue(ENUM_TEAM.T.class, CodedConstant.getEnumName(ENUM_TEAM.T.values(), var1.readEnum()));
            } else if (var5 == 64) {
               var2.rank = var1.readInt32();
            } else if (var5 == 72) {
               var2.pvpstargrade = var1.readInt32();
            } else if (var5 == 82) {
               var2.profileurl = var1.readString();
            } else if (var5 == 88) {
               var2.world = var1.readInt32();
            } else if (var5 == 98) {
               Codec var10 = ProtobufProxy.create(PT_SKIN_CHAT_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.skinchatinfo = (PT_SKIN_CHAT_INFO)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 104) {
               var2.iskeyboard = var1.readBool();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_USER_MINIMUM_INFO.class);
         return this.descriptor = var1;
      }
   }
}
