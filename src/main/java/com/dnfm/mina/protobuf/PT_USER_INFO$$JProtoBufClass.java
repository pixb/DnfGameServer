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

public class PT_USER_INFO$$JProtoBufClass implements Codec<PT_USER_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_USER_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_USER_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_USER_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var44 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var44);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.playerid)) {
         Integer var45 = var1.playerid;
         var2 += CodedOutputStream.computeInt32Size(2, var45);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.objectgroupid)) {
         Integer var46 = var1.objectgroupid;
         var2 += CodedOutputStream.computeInt32Size(3, var46);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.cobjectid)) {
         Integer var47 = var1.cobjectid;
         var2 += CodedOutputStream.computeInt32Size(4, var47);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.performancerating)) {
         Integer var48 = var1.performancerating;
         var2 += CodedOutputStream.computeInt32Size(5, var48);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var49 = var1.job;
         var2 += CodedOutputStream.computeInt32Size(6, var49);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var50 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(7, var50);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.exp)) {
         Integer var51 = var1.exp;
         var2 += CodedOutputStream.computeInt32Size(8, var51);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var52 = var1.growtype;
         var2 += CodedOutputStream.computeInt32Size(9, var52);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.secgrowtype)) {
         Integer var53 = var1.secgrowtype;
         var2 += CodedOutputStream.computeInt32Size(10, var53);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.hellticket)) {
         Integer var54 = var1.hellticket;
         var2 += CodedOutputStream.computeInt32Size(11, var54);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var55 = var1.name;
         var2 += CodedOutputStream.computeStringSize(12, var55);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.gname)) {
         String var56 = var1.gname;
         var2 += CodedOutputStream.computeStringSize(13, var56);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.gsymbol)) {
         List var57 = var1.gsymbol;
         var2 += CodedConstant.computeListSize(14, var57, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.appendages)) {
         List var58 = var1.appendages;
         var2 += CodedConstant.computeListSize(15, var58, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.equiplist)) {
         PT_EQUIP_LIST var59 = var1.equiplist;
         var2 += CodedConstant.computeSize(16, var59, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.skilllist)) {
         PT_SKILLS var60 = var1.skilllist;
         var2 += CodedConstant.computeSize(17, var60, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.skillslot)) {
         PT_ALL_SKILL_SLOT var61 = var1.skillslot;
         var2 += CodedConstant.computeSize(18, var61, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.material)) {
         PT_MATERIAL_LIST var62 = var1.material;
         var2 += CodedConstant.computeSize(19, var62, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.consume)) {
         PT_CONSUME_LIST var63 = var1.consume;
         var2 += CodedConstant.computeSize(20, var63, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.teamtype)) {
         ENUM_TEAM.T var64 = var1.teamtype;
         var2 += CodedOutputStream.computeEnumSize(21, ((Enum)var64).ordinal());
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.adventureunionlevel)) {
         Integer var65 = var1.adventureunionlevel;
         var2 += CodedOutputStream.computeInt32Size(22, var65);
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.adventureunionexp)) {
         Long var66 = var1.adventureunionexp;
         var2 += CodedOutputStream.computeInt64Size(23, var66);
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.time)) {
         Long var67 = var1.time;
         var2 += CodedOutputStream.computeInt64Size(24, var67);
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.pvpstarcount)) {
         Integer var68 = var1.pvpstarcount;
         var2 += CodedOutputStream.computeInt32Size(25, var68);
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.pvpstargrade)) {
         Integer var69 = var1.pvpstargrade;
         var2 += CodedOutputStream.computeInt32Size(26, var69);
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.customdata)) {
         Long var70 = var1.customdata;
         var2 += CodedOutputStream.computeUInt64Size(27, var70);
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var71 = var1.world;
         var2 += CodedOutputStream.computeInt32Size(28, var71);
      }

      Object var31 = null;
      if (!CodedConstant.isNull(var1.currency)) {
         List var72 = var1.currency;
         var2 += CodedConstant.computeListSize(29, var72, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var32 = null;
      if (!CodedConstant.isNull(var1.accountcurrency)) {
         List var73 = var1.accountcurrency;
         var2 += CodedConstant.computeListSize(30, var73, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var33 = null;
      if (!CodedConstant.isNull(var1.specialcharactertype)) {
         Integer var74 = var1.specialcharactertype;
         var2 += CodedOutputStream.computeInt32Size(31, var74);
      }

      Object var34 = null;
      if (!CodedConstant.isNull(var1.characoptionsyncdata)) {
         String var75 = var1.characoptionsyncdata;
         var2 += CodedOutputStream.computeStringSize(32, var75);
      }

      Object var35 = null;
      if (!CodedConstant.isNull(var1.accountoptionsyncdata)) {
         String var76 = var1.accountoptionsyncdata;
         var2 += CodedOutputStream.computeStringSize(33, var76);
      }

      Object var36 = null;
      if (!CodedConstant.isNull(var1.avatarVisibleFlags)) {
         Integer var77 = var1.avatarVisibleFlags;
         var2 += CodedOutputStream.computeUInt32Size(34, var77);
      }

      Object var37 = null;
      if (!CodedConstant.isNull(var1.equipscore)) {
         Integer var78 = var1.equipscore;
         var2 += CodedOutputStream.computeInt32Size(35, var78);
      }

      Object var38 = null;
      if (!CodedConstant.isNull(var1.chivalrygrade)) {
         Integer var79 = var1.chivalrygrade;
         var2 += CodedOutputStream.computeInt32Size(36, var79);
      }

      Object var39 = null;
      if (!CodedConstant.isNull(var1.equipitems)) {
         List var80 = var1.equipitems;
         var2 += CodedConstant.computeListSize(37, var80, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var40 = null;
      if (!CodedConstant.isNull(var1.creditscore)) {
         Integer var81 = var1.creditscore;
         var2 += CodedOutputStream.computeInt32Size(38, var81);
      }

      Object var41 = null;
      if (!CodedConstant.isNull(var1.adventureunionname)) {
         String var82 = var1.adventureunionname;
         var2 += CodedOutputStream.computeStringSize(39, var82);
      }

      Object var42 = null;
      if (!CodedConstant.isNull(var1.dice)) {
         Integer var83 = var1.dice;
         var2 += CodedOutputStream.computeInt32Size(40, var83);
      }

      Object var43 = null;
      if (!CodedConstant.isNull(var1.creturecommunionskillinfos)) {
         PT_CREATURE_LEARN_SKILL_INFOS var84 = var1.creturecommunionskillinfos;
         var2 += CodedConstant.computeSize(41, var84, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      return var2;
   }

   public void doWriteTo(PT_USER_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var44 = var1.charguid;
         if (var44 != null) {
            var2.writeUInt64(1, var44);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.playerid)) {
         Integer var45 = var1.playerid;
         if (var45 != null) {
            var2.writeInt32(2, var45);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.objectgroupid)) {
         Integer var46 = var1.objectgroupid;
         if (var46 != null) {
            var2.writeInt32(3, var46);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.cobjectid)) {
         Integer var47 = var1.cobjectid;
         if (var47 != null) {
            var2.writeInt32(4, var47);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.performancerating)) {
         Integer var48 = var1.performancerating;
         if (var48 != null) {
            var2.writeInt32(5, var48);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var49 = var1.job;
         if (var49 != null) {
            var2.writeInt32(6, var49);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var50 = var1.level;
         if (var50 != null) {
            var2.writeInt32(7, var50);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.exp)) {
         Integer var51 = var1.exp;
         if (var51 != null) {
            var2.writeInt32(8, var51);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var52 = var1.growtype;
         if (var52 != null) {
            var2.writeInt32(9, var52);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.secgrowtype)) {
         Integer var53 = var1.secgrowtype;
         if (var53 != null) {
            var2.writeInt32(10, var53);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.hellticket)) {
         Integer var54 = var1.hellticket;
         if (var54 != null) {
            var2.writeInt32(11, var54);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var55 = var1.name;
         if (var55 != null) {
            var2.writeString(12, var55);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.gname)) {
         String var56 = var1.gname;
         if (var56 != null) {
            var2.writeString(13, var56);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.gsymbol)) {
         List var57 = var1.gsymbol;
         if (var57 != null) {
            CodedConstant.writeToList(var2, 14, FieldType.OBJECT, var57, false);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.appendages)) {
         List var58 = var1.appendages;
         if (var58 != null) {
            CodedConstant.writeToList(var2, 15, FieldType.OBJECT, var58, false);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.equiplist)) {
         PT_EQUIP_LIST var59 = var1.equiplist;
         if (var59 != null) {
            CodedConstant.writeObject(var2, 16, FieldType.OBJECT, var59, false);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.skilllist)) {
         PT_SKILLS var60 = var1.skilllist;
         if (var60 != null) {
            CodedConstant.writeObject(var2, 17, FieldType.OBJECT, var60, false);
         }
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.skillslot)) {
         PT_ALL_SKILL_SLOT var61 = var1.skillslot;
         if (var61 != null) {
            CodedConstant.writeObject(var2, 18, FieldType.OBJECT, var61, false);
         }
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.material)) {
         PT_MATERIAL_LIST var62 = var1.material;
         if (var62 != null) {
            CodedConstant.writeObject(var2, 19, FieldType.OBJECT, var62, false);
         }
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.consume)) {
         PT_CONSUME_LIST var63 = var1.consume;
         if (var63 != null) {
            CodedConstant.writeObject(var2, 20, FieldType.OBJECT, var63, false);
         }
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.teamtype)) {
         ENUM_TEAM.T var64 = var1.teamtype;
         if (var64 != null) {
            var2.writeEnum(21, ((Enum)var64).ordinal());
         }
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.adventureunionlevel)) {
         Integer var65 = var1.adventureunionlevel;
         if (var65 != null) {
            var2.writeInt32(22, var65);
         }
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.adventureunionexp)) {
         Long var66 = var1.adventureunionexp;
         if (var66 != null) {
            var2.writeInt64(23, var66);
         }
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.time)) {
         Long var67 = var1.time;
         if (var67 != null) {
            var2.writeInt64(24, var67);
         }
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.pvpstarcount)) {
         Integer var68 = var1.pvpstarcount;
         if (var68 != null) {
            var2.writeInt32(25, var68);
         }
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.pvpstargrade)) {
         Integer var69 = var1.pvpstargrade;
         if (var69 != null) {
            var2.writeInt32(26, var69);
         }
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.customdata)) {
         Long var70 = var1.customdata;
         if (var70 != null) {
            var2.writeUInt64(27, var70);
         }
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var71 = var1.world;
         if (var71 != null) {
            var2.writeInt32(28, var71);
         }
      }

      Object var31 = null;
      if (!CodedConstant.isNull(var1.currency)) {
         List var72 = var1.currency;
         if (var72 != null) {
            CodedConstant.writeToList(var2, 29, FieldType.OBJECT, var72, false);
         }
      }

      Object var32 = null;
      if (!CodedConstant.isNull(var1.accountcurrency)) {
         List var73 = var1.accountcurrency;
         if (var73 != null) {
            CodedConstant.writeToList(var2, 30, FieldType.OBJECT, var73, false);
         }
      }

      Object var33 = null;
      if (!CodedConstant.isNull(var1.specialcharactertype)) {
         Integer var74 = var1.specialcharactertype;
         if (var74 != null) {
            var2.writeInt32(31, var74);
         }
      }

      Object var34 = null;
      if (!CodedConstant.isNull(var1.characoptionsyncdata)) {
         String var75 = var1.characoptionsyncdata;
         if (var75 != null) {
            var2.writeString(32, var75);
         }
      }

      Object var35 = null;
      if (!CodedConstant.isNull(var1.accountoptionsyncdata)) {
         String var76 = var1.accountoptionsyncdata;
         if (var76 != null) {
            var2.writeString(33, var76);
         }
      }

      Object var36 = null;
      if (!CodedConstant.isNull(var1.avatarVisibleFlags)) {
         Integer var77 = var1.avatarVisibleFlags;
         if (var77 != null) {
            var2.writeUInt32(34, var77);
         }
      }

      Object var37 = null;
      if (!CodedConstant.isNull(var1.equipscore)) {
         Integer var78 = var1.equipscore;
         if (var78 != null) {
            var2.writeInt32(35, var78);
         }
      }

      Object var38 = null;
      if (!CodedConstant.isNull(var1.chivalrygrade)) {
         Integer var79 = var1.chivalrygrade;
         if (var79 != null) {
            var2.writeInt32(36, var79);
         }
      }

      Object var39 = null;
      if (!CodedConstant.isNull(var1.equipitems)) {
         List var80 = var1.equipitems;
         if (var80 != null) {
            CodedConstant.writeToList(var2, 37, FieldType.OBJECT, var80, false);
         }
      }

      Object var40 = null;
      if (!CodedConstant.isNull(var1.creditscore)) {
         Integer var81 = var1.creditscore;
         if (var81 != null) {
            var2.writeInt32(38, var81);
         }
      }

      Object var41 = null;
      if (!CodedConstant.isNull(var1.adventureunionname)) {
         String var82 = var1.adventureunionname;
         if (var82 != null) {
            var2.writeString(39, var82);
         }
      }

      Object var42 = null;
      if (!CodedConstant.isNull(var1.dice)) {
         Integer var83 = var1.dice;
         if (var83 != null) {
            var2.writeInt32(40, var83);
         }
      }

      Object var43 = null;
      if (!CodedConstant.isNull(var1.creturecommunionskillinfos)) {
         PT_CREATURE_LEARN_SKILL_INFOS var84 = var1.creturecommunionskillinfos;
         if (var84 != null) {
            CodedConstant.writeObject(var2, 41, FieldType.OBJECT, var84, false);
         }
      }

   }

   public void writeTo(PT_USER_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_USER_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_USER_INFO var2 = new PT_USER_INFO();
      var2.gsymbol = new ArrayList();
      var2.appendages = new ArrayList();
      var2.currency = new ArrayList();
      var2.accountcurrency = new ArrayList();
      var2.equipitems = new ArrayList();
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
               var2.objectgroupid = var1.readInt32();
            } else if (var5 == 32) {
               var2.cobjectid = var1.readInt32();
            } else if (var5 == 40) {
               var2.performancerating = var1.readInt32();
            } else if (var5 == 48) {
               var2.job = var1.readInt32();
            } else if (var5 == 56) {
               var2.level = var1.readInt32();
            } else if (var5 == 64) {
               var2.exp = var1.readInt32();
            } else if (var5 == 72) {
               var2.growtype = var1.readInt32();
            } else if (var5 == 80) {
               var2.secgrowtype = var1.readInt32();
            } else if (var5 == 88) {
               var2.hellticket = var1.readInt32();
            } else if (var5 == 98) {
               var2.name = var1.readString();
            } else if (var5 == 106) {
               var2.gname = var1.readString();
            } else if (var5 == 114) {
               Codec var10 = ProtobufProxy.create(PT_GUILD_SYMBOL.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.gsymbol == null) {
                  var2.gsymbol = new ArrayList();
               }

               var2.gsymbol.add((PT_GUILD_SYMBOL)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 122) {
               Codec var11 = ProtobufProxy.create(PT_SYSTEM_BUFF_APPENDAGE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var21 = var1.readRawVarint32();
               int var31 = var1.pushLimit(var21);
               if (var2.appendages == null) {
                  var2.appendages = new ArrayList();
               }

               var2.appendages.add((PT_SYSTEM_BUFF_APPENDAGE)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var31);
            } else if (var5 == 130) {
               Codec var12 = ProtobufProxy.create(PT_EQUIP_LIST.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var22 = var1.readRawVarint32();
               int var32 = var1.pushLimit(var22);
               var2.equiplist = (PT_EQUIP_LIST)var12.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var32);
            } else if (var5 == 138) {
               Codec var13 = ProtobufProxy.create(PT_SKILLS.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var23 = var1.readRawVarint32();
               int var33 = var1.pushLimit(var23);
               var2.skilllist = (PT_SKILLS)var13.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var33);
            } else if (var5 == 146) {
               Codec var14 = ProtobufProxy.create(PT_ALL_SKILL_SLOT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var24 = var1.readRawVarint32();
               int var34 = var1.pushLimit(var24);
               var2.skillslot = (PT_ALL_SKILL_SLOT)var14.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var34);
            } else if (var5 == 154) {
               Codec var15 = ProtobufProxy.create(PT_MATERIAL_LIST.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var25 = var1.readRawVarint32();
               int var35 = var1.pushLimit(var25);
               var2.material = (PT_MATERIAL_LIST)var15.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var35);
            } else if (var5 == 162) {
               Codec var16 = ProtobufProxy.create(PT_CONSUME_LIST.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var26 = var1.readRawVarint32();
               int var36 = var1.pushLimit(var26);
               var2.consume = (PT_CONSUME_LIST)var16.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var36);
            } else if (var5 == 168) {
               var2.teamtype = (ENUM_TEAM.T)CodedConstant.getEnumValue(ENUM_TEAM.T.class, CodedConstant.getEnumName(ENUM_TEAM.T.values(), var1.readEnum()));
            } else if (var5 == 176) {
               var2.adventureunionlevel = var1.readInt32();
            } else if (var5 == 184) {
               var2.adventureunionexp = var1.readInt64();
            } else if (var5 == 192) {
               var2.time = var1.readInt64();
            } else if (var5 == 200) {
               var2.pvpstarcount = var1.readInt32();
            } else if (var5 == 208) {
               var2.pvpstargrade = var1.readInt32();
            } else if (var5 == 216) {
               var2.customdata = var1.readUInt64();
            } else if (var5 == 224) {
               var2.world = var1.readInt32();
            } else if (var5 == 234) {
               Codec var17 = ProtobufProxy.create(PT_MONEY_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var27 = var1.readRawVarint32();
               int var37 = var1.pushLimit(var27);
               if (var2.currency == null) {
                  var2.currency = new ArrayList();
               }

               var2.currency.add((PT_MONEY_ITEM)var17.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var37);
            } else if (var5 == 242) {
               Codec var18 = ProtobufProxy.create(PT_MONEY_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var28 = var1.readRawVarint32();
               int var38 = var1.pushLimit(var28);
               if (var2.accountcurrency == null) {
                  var2.accountcurrency = new ArrayList();
               }

               var2.accountcurrency.add((PT_MONEY_ITEM)var18.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var38);
            } else if (var5 == 248) {
               var2.specialcharactertype = var1.readInt32();
            } else if (var5 == 258) {
               var2.characoptionsyncdata = var1.readString();
            } else if (var5 == 266) {
               var2.accountoptionsyncdata = var1.readString();
            } else if (var5 == 272) {
               var2.avatarVisibleFlags = var1.readUInt32();
            } else if (var5 == 280) {
               var2.equipscore = var1.readInt32();
            } else if (var5 == 288) {
               var2.chivalrygrade = var1.readInt32();
            } else if (var5 == 298) {
               Codec var19 = ProtobufProxy.create(PT_EQUIP.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var29 = var1.readRawVarint32();
               int var39 = var1.pushLimit(var29);
               if (var2.equipitems == null) {
                  var2.equipitems = new ArrayList();
               }

               var2.equipitems.add((PT_EQUIP)var19.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var39);
            } else if (var5 == 304) {
               var2.creditscore = var1.readInt32();
            } else if (var5 == 314) {
               var2.adventureunionname = var1.readString();
            } else if (var5 == 320) {
               var2.dice = var1.readInt32();
            } else if (var5 == 330) {
               Codec var20 = ProtobufProxy.create(PT_CREATURE_LEARN_SKILL_INFOS.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var30 = var1.readRawVarint32();
               int var40 = var1.pushLimit(var30);
               var2.creturecommunionskillinfos = (PT_CREATURE_LEARN_SKILL_INFOS)var20.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var40);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_USER_INFO.class);
         return this.descriptor = var1;
      }
   }
}
