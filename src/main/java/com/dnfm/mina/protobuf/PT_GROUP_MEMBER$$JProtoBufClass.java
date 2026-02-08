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

public class PT_GROUP_MEMBER$$JProtoBufClass implements Codec<PT_GROUP_MEMBER>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_GROUP_MEMBER var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_GROUP_MEMBER decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_GROUP_MEMBER var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var35 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var35);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.equipscore)) {
         Integer var36 = var1.equipscore;
         var2 += CodedOutputStream.computeInt32Size(2, var36);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var37 = var1.job;
         var2 += CodedOutputStream.computeInt32Size(3, var37);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var38 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(4, var38);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var39 = var1.name;
         var2 += CodedOutputStream.computeStringSize(5, var39);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var40 = var1.growtype;
         var2 += CodedOutputStream.computeInt32Size(6, var40);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var41 = var1.secondgrowtype;
         var2 += CodedOutputStream.computeInt32Size(7, var41);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.ip)) {
         String var42 = var1.ip;
         var2 += CodedOutputStream.computeStringSize(8, var42);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.port)) {
         Integer var43 = var1.port;
         var2 += CodedOutputStream.computeUInt32Size(9, var43);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.gguid)) {
         Long var44 = var1.gguid;
         var2 += CodedOutputStream.computeUInt64Size(10, var44);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.gname)) {
         String var45 = var1.gname;
         var2 += CodedOutputStream.computeStringSize(11, var45);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.partyleader)) {
         Boolean var46 = var1.partyleader;
         var2 += CodedOutputStream.computeBoolSize(12, var46);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.fatigue)) {
         Integer var47 = var1.fatigue;
         var2 += CodedOutputStream.computeInt32Size(13, var47);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var48 = var1.world;
         var2 += CodedOutputStream.computeInt32Size(14, var48);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.creditscore)) {
         Integer var49 = var1.creditscore;
         var2 += CodedOutputStream.computeInt32Size(15, var49);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.chivalrygrade)) {
         Integer var50 = var1.chivalrygrade;
         var2 += CodedOutputStream.computeInt32Size(16, var50);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.characterframe)) {
         Integer var51 = var1.characterframe;
         var2 += CodedOutputStream.computeInt32Size(17, var51);
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.disconnecttime)) {
         Long var52 = var1.disconnecttime;
         var2 += CodedOutputStream.computeInt64Size(18, var52);
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.specialcategoryitemindex)) {
         Integer var53 = var1.specialcategoryitemindex;
         var2 += CodedOutputStream.computeInt32Size(19, var53);
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.pvpready)) {
         Boolean var54 = var1.pvpready;
         var2 += CodedOutputStream.computeBoolSize(20, var54);
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.pvpcharindex)) {
         Integer var55 = var1.pvpcharindex;
         var2 += CodedOutputStream.computeInt32Size(21, var55);
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.teamtype)) {
         ENUM_TEAM.T var56 = var1.teamtype;
         var2 += CodedOutputStream.computeEnumSize(22, ((Enum)var56).ordinal());
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.reservekicktime)) {
         Long var57 = var1.reservekicktime;
         var2 += CodedOutputStream.computeInt64Size(23, var57);
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.seq)) {
         Integer var58 = var1.seq;
         var2 += CodedOutputStream.computeInt32Size(24, var58);
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.customdata)) {
         PT_CUSTOM_DATA var59 = var1.customdata;
         var2 += CodedConstant.computeSize(25, var59, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.marriageguid)) {
         Long var60 = var1.marriageguid;
         var2 += CodedOutputStream.computeUInt64Size(26, var60);
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.switchstatus)) {
         Integer var61 = var1.switchstatus;
         var2 += CodedOutputStream.computeInt32Size(27, var61);
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.ping)) {
         Integer var62 = var1.ping;
         var2 += CodedOutputStream.computeInt32Size(28, var62);
      }

      Object var31 = null;
      if (!CodedConstant.isNull(var1.isInvite)) {
         Boolean var63 = var1.isInvite;
         var2 += CodedOutputStream.computeBoolSize(29, var63);
      }

      Object var32 = null;
      if (!CodedConstant.isNull(var1.inviteblockflag)) {
         Integer var64 = var1.inviteblockflag;
         var2 += CodedOutputStream.computeInt32Size(30, var64);
      }

      Object var33 = null;
      if (!CodedConstant.isNull(var1.mineworld)) {
         Integer var65 = var1.mineworld;
         var2 += CodedOutputStream.computeInt32Size(31, var65);
      }

      Object var34 = null;
      if (!CodedConstant.isNull(var1.iskeyboard)) {
         Boolean var66 = var1.iskeyboard;
         var2 += CodedOutputStream.computeBoolSize(32, var66);
      }

      return var2;
   }

   public void doWriteTo(PT_GROUP_MEMBER var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var35 = var1.charguid;
         if (var35 != null) {
            var2.writeUInt64(1, var35);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.equipscore)) {
         Integer var36 = var1.equipscore;
         if (var36 != null) {
            var2.writeInt32(2, var36);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var37 = var1.job;
         if (var37 != null) {
            var2.writeInt32(3, var37);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var38 = var1.level;
         if (var38 != null) {
            var2.writeInt32(4, var38);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var39 = var1.name;
         if (var39 != null) {
            var2.writeString(5, var39);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var40 = var1.growtype;
         if (var40 != null) {
            var2.writeInt32(6, var40);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var41 = var1.secondgrowtype;
         if (var41 != null) {
            var2.writeInt32(7, var41);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.ip)) {
         String var42 = var1.ip;
         if (var42 != null) {
            var2.writeString(8, var42);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.port)) {
         Integer var43 = var1.port;
         if (var43 != null) {
            var2.writeUInt32(9, var43);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.gguid)) {
         Long var44 = var1.gguid;
         if (var44 != null) {
            var2.writeUInt64(10, var44);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.gname)) {
         String var45 = var1.gname;
         if (var45 != null) {
            var2.writeString(11, var45);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.partyleader)) {
         Boolean var46 = var1.partyleader;
         if (var46 != null) {
            var2.writeBool(12, var46);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.fatigue)) {
         Integer var47 = var1.fatigue;
         if (var47 != null) {
            var2.writeInt32(13, var47);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var48 = var1.world;
         if (var48 != null) {
            var2.writeInt32(14, var48);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.creditscore)) {
         Integer var49 = var1.creditscore;
         if (var49 != null) {
            var2.writeInt32(15, var49);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.chivalrygrade)) {
         Integer var50 = var1.chivalrygrade;
         if (var50 != null) {
            var2.writeInt32(16, var50);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.characterframe)) {
         Integer var51 = var1.characterframe;
         if (var51 != null) {
            var2.writeInt32(17, var51);
         }
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.disconnecttime)) {
         Long var52 = var1.disconnecttime;
         if (var52 != null) {
            var2.writeInt64(18, var52);
         }
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.specialcategoryitemindex)) {
         Integer var53 = var1.specialcategoryitemindex;
         if (var53 != null) {
            var2.writeInt32(19, var53);
         }
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.pvpready)) {
         Boolean var54 = var1.pvpready;
         if (var54 != null) {
            var2.writeBool(20, var54);
         }
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.pvpcharindex)) {
         Integer var55 = var1.pvpcharindex;
         if (var55 != null) {
            var2.writeInt32(21, var55);
         }
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.teamtype)) {
         ENUM_TEAM.T var56 = var1.teamtype;
         if (var56 != null) {
            var2.writeEnum(22, ((Enum)var56).ordinal());
         }
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.reservekicktime)) {
         Long var57 = var1.reservekicktime;
         if (var57 != null) {
            var2.writeInt64(23, var57);
         }
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.seq)) {
         Integer var58 = var1.seq;
         if (var58 != null) {
            var2.writeInt32(24, var58);
         }
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.customdata)) {
         PT_CUSTOM_DATA var59 = var1.customdata;
         if (var59 != null) {
            CodedConstant.writeObject(var2, 25, FieldType.OBJECT, var59, false);
         }
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.marriageguid)) {
         Long var60 = var1.marriageguid;
         if (var60 != null) {
            var2.writeUInt64(26, var60);
         }
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.switchstatus)) {
         Integer var61 = var1.switchstatus;
         if (var61 != null) {
            var2.writeInt32(27, var61);
         }
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.ping)) {
         Integer var62 = var1.ping;
         if (var62 != null) {
            var2.writeInt32(28, var62);
         }
      }

      Object var31 = null;
      if (!CodedConstant.isNull(var1.isInvite)) {
         Boolean var63 = var1.isInvite;
         if (var63 != null) {
            var2.writeBool(29, var63);
         }
      }

      Object var32 = null;
      if (!CodedConstant.isNull(var1.inviteblockflag)) {
         Integer var64 = var1.inviteblockflag;
         if (var64 != null) {
            var2.writeInt32(30, var64);
         }
      }

      Object var33 = null;
      if (!CodedConstant.isNull(var1.mineworld)) {
         Integer var65 = var1.mineworld;
         if (var65 != null) {
            var2.writeInt32(31, var65);
         }
      }

      Object var34 = null;
      if (!CodedConstant.isNull(var1.iskeyboard)) {
         Boolean var66 = var1.iskeyboard;
         if (var66 != null) {
            var2.writeBool(32, var66);
         }
      }

   }

   public void writeTo(PT_GROUP_MEMBER var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_GROUP_MEMBER readFrom(CodedInputStream var1) throws IOException {
      PT_GROUP_MEMBER var2 = new PT_GROUP_MEMBER();
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
               var2.equipscore = var1.readInt32();
            } else if (var5 == 24) {
               var2.job = var1.readInt32();
            } else if (var5 == 32) {
               var2.level = var1.readInt32();
            } else if (var5 == 42) {
               var2.name = var1.readString();
            } else if (var5 == 48) {
               var2.growtype = var1.readInt32();
            } else if (var5 == 56) {
               var2.secondgrowtype = var1.readInt32();
            } else if (var5 == 66) {
               var2.ip = var1.readString();
            } else if (var5 == 72) {
               var2.port = var1.readUInt32();
            } else if (var5 == 80) {
               var2.gguid = var1.readUInt64();
            } else if (var5 == 90) {
               var2.gname = var1.readString();
            } else if (var5 == 96) {
               var2.partyleader = var1.readBool();
            } else if (var5 == 104) {
               var2.fatigue = var1.readInt32();
            } else if (var5 == 112) {
               var2.world = var1.readInt32();
            } else if (var5 == 120) {
               var2.creditscore = var1.readInt32();
            } else if (var5 == 128) {
               var2.chivalrygrade = var1.readInt32();
            } else if (var5 == 136) {
               var2.characterframe = var1.readInt32();
            } else if (var5 == 144) {
               var2.disconnecttime = var1.readInt64();
            } else if (var5 == 152) {
               var2.specialcategoryitemindex = var1.readInt32();
            } else if (var5 == 160) {
               var2.pvpready = var1.readBool();
            } else if (var5 == 168) {
               var2.pvpcharindex = var1.readInt32();
            } else if (var5 == 176) {
               var2.teamtype = (ENUM_TEAM.T)CodedConstant.getEnumValue(ENUM_TEAM.T.class, CodedConstant.getEnumName(ENUM_TEAM.T.values(), var1.readEnum()));
            } else if (var5 == 184) {
               var2.reservekicktime = var1.readInt64();
            } else if (var5 == 192) {
               var2.seq = var1.readInt32();
            } else if (var5 == 202) {
               Codec var10 = ProtobufProxy.create(PT_CUSTOM_DATA.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.customdata = (PT_CUSTOM_DATA)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 208) {
               var2.marriageguid = var1.readUInt64();
            } else if (var5 == 216) {
               var2.switchstatus = var1.readInt32();
            } else if (var5 == 224) {
               var2.ping = var1.readInt32();
            } else if (var5 == 232) {
               var2.isInvite = var1.readBool();
            } else if (var5 == 240) {
               var2.inviteblockflag = var1.readInt32();
            } else if (var5 == 248) {
               var2.mineworld = var1.readInt32();
            } else if (var5 == 256) {
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_GROUP_MEMBER.class);
         return this.descriptor = var1;
      }
   }
}
