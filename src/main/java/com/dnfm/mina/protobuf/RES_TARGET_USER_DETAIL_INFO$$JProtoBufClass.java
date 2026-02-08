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

public class RES_TARGET_USER_DETAIL_INFO$$JProtoBufClass implements Codec<RES_TARGET_USER_DETAIL_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_TARGET_USER_DETAIL_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_TARGET_USER_DETAIL_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_TARGET_USER_DETAIL_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var47 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var47);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var48 = var1.guid;
         var2 += CodedOutputStream.computeUInt64Size(2, var48);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var49 = var1.world;
         var2 += CodedOutputStream.computeInt32Size(3, var49);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.gguid)) {
         Long var50 = var1.gguid;
         var2 += CodedOutputStream.computeUInt64Size(4, var50);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.gmastername)) {
         String var51 = var1.gmastername;
         var2 += CodedOutputStream.computeStringSize(5, var51);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var52 = var1.name;
         var2 += CodedOutputStream.computeStringSize(6, var52);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.exp)) {
         Integer var53 = var1.exp;
         var2 += CodedOutputStream.computeInt32Size(7, var53);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var54 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(8, var54);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var55 = var1.job;
         var2 += CodedOutputStream.computeInt32Size(9, var55);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var56 = var1.growtype;
         var2 += CodedOutputStream.computeInt32Size(10, var56);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.secgrowtype)) {
         Integer var57 = var1.secgrowtype;
         var2 += CodedOutputStream.computeInt32Size(11, var57);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.equipscore)) {
         Integer var58 = var1.equipscore;
         var2 += CodedOutputStream.computeInt32Size(12, var58);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.spoint)) {
         Integer var59 = var1.spoint;
         var2 += CodedOutputStream.computeInt32Size(13, var59);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.adventureunionlevel)) {
         Integer var60 = var1.adventureunionlevel;
         var2 += CodedOutputStream.computeInt32Size(14, var60);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.adventureunionexp)) {
         Long var61 = var1.adventureunionexp;
         var2 += CodedOutputStream.computeInt64Size(15, var61);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.characlistcount)) {
         Integer var62 = var1.characlistcount;
         var2 += CodedOutputStream.computeInt32Size(16, var62);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.gname)) {
         String var63 = var1.gname;
         var2 += CodedOutputStream.computeStringSize(17, var63);
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.gsymbol)) {
         List var64 = var1.gsymbol;
         var2 += CodedConstant.computeListSize(18, var64, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.equiplist)) {
         List var65 = var1.equiplist;
         var2 += CodedConstant.computeListSize(19, var65, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.avatarlist)) {
         List var66 = var1.avatarlist;
         var2 += CodedConstant.computeListSize(20, var66, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.creaturelist)) {
         List var67 = var1.creaturelist;
         var2 += CodedConstant.computeListSize(21, var67, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.cartifactlist)) {
         List var68 = var1.cartifactlist;
         var2 += CodedConstant.computeListSize(22, var68, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.skinlist)) {
         List var69 = var1.skinlist;
         var2 += CodedConstant.computeListSize(23, var69, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.bufflist)) {
         PT_BUFF_LIST var70 = var1.bufflist;
         var2 += CodedConstant.computeSize(24, var70, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.skill)) {
         PT_SKILL_INFO var71 = var1.skill;
         var2 += CodedConstant.computeSize(25, var71, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.gmembergrade)) {
         Integer var72 = var1.gmembergrade;
         var2 += CodedOutputStream.computeInt32Size(26, var72);
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.blackdiamond)) {
         Long var73 = var1.blackdiamond;
         var2 += CodedOutputStream.computeUInt64Size(27, var73);
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.creditscore)) {
         Integer var74 = var1.creditscore;
         var2 += CodedOutputStream.computeInt32Size(28, var74);
      }

      Object var31 = null;
      if (!CodedConstant.isNull(var1.gamecenterinfo)) {
         Integer var75 = var1.gamecenterinfo;
         var2 += CodedOutputStream.computeInt32Size(29, var75);
      }

      Object var32 = null;
      if (!CodedConstant.isNull(var1.qqVipinfo)) {
         Integer var76 = var1.qqVipinfo;
         var2 += CodedOutputStream.computeInt32Size(30, var76);
      }

      Object var33 = null;
      if (!CodedConstant.isNull(var1.avatarVisibleFlags)) {
         Integer var77 = var1.avatarVisibleFlags;
         var2 += CodedOutputStream.computeUInt32Size(31, var77);
      }

      Object var34 = null;
      if (!CodedConstant.isNull(var1.equipskinlist)) {
         List var78 = var1.equipskinlist;
         var2 += CodedConstant.computeListSize(32, var78, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var35 = null;
      if (!CodedConstant.isNull(var1.avatarskinlist)) {
         List var79 = var1.avatarskinlist;
         var2 += CodedConstant.computeListSize(33, var79, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var36 = null;
      if (!CodedConstant.isNull(var1.totallike)) {
         Integer var80 = var1.totallike;
         var2 += CodedOutputStream.computeInt32Size(34, var80);
      }

      Object var37 = null;
      if (!CodedConstant.isNull(var1.like)) {
         Integer var81 = var1.like;
         var2 += CodedOutputStream.computeInt32Size(35, var81);
      }

      Object var38 = null;
      if (!CodedConstant.isNull(var1.rank)) {
         Integer var82 = var1.rank;
         var2 += CodedOutputStream.computeInt32Size(36, var82);
      }

      Object var39 = null;
      if (!CodedConstant.isNull(var1.info)) {
         List var83 = var1.info;
         var2 += CodedConstant.computeListSize(37, var83, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var40 = null;
      if (!CodedConstant.isNull(var1.communionlevel)) {
         Integer var84 = var1.communionlevel;
         var2 += CodedOutputStream.computeInt32Size(38, var84);
      }

      Object var41 = null;
      if (!CodedConstant.isNull(var1.fame)) {
         Integer var85 = var1.fame;
         var2 += CodedOutputStream.computeInt32Size(39, var85);
      }

      Object var42 = null;
      if (!CodedConstant.isNull(var1.charm)) {
         Integer var86 = var1.charm;
         var2 += CodedOutputStream.computeInt32Size(40, var86);
      }

      Object var43 = null;
      if (!CodedConstant.isNull(var1.sdavatarlist)) {
         List var87 = var1.sdavatarlist;
         var2 += CodedConstant.computeListSize(41, var87, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var44 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var88 = var1.grade;
         var2 += CodedOutputStream.computeInt32Size(42, var88);
      }

      Object var45 = null;
      if (!CodedConstant.isNull(var1.gradeFair)) {
         Integer var89 = var1.gradeFair;
         var2 += CodedOutputStream.computeInt32Size(43, var89);
      }

      Object var46 = null;
      if (!CodedConstant.isNull(var1.isadventureCondition)) {
         Boolean var90 = var1.isadventureCondition;
         var2 += CodedOutputStream.computeBoolSize(44, var90);
      }

      return var2;
   }

   public void doWriteTo(RES_TARGET_USER_DETAIL_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var47 = var1.error;
         if (var47 != null) {
            var2.writeInt32(1, var47);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var48 = var1.guid;
         if (var48 != null) {
            var2.writeUInt64(2, var48);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var49 = var1.world;
         if (var49 != null) {
            var2.writeInt32(3, var49);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.gguid)) {
         Long var50 = var1.gguid;
         if (var50 != null) {
            var2.writeUInt64(4, var50);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.gmastername)) {
         String var51 = var1.gmastername;
         if (var51 != null) {
            var2.writeString(5, var51);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var52 = var1.name;
         if (var52 != null) {
            var2.writeString(6, var52);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.exp)) {
         Integer var53 = var1.exp;
         if (var53 != null) {
            var2.writeInt32(7, var53);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var54 = var1.level;
         if (var54 != null) {
            var2.writeInt32(8, var54);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var55 = var1.job;
         if (var55 != null) {
            var2.writeInt32(9, var55);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var56 = var1.growtype;
         if (var56 != null) {
            var2.writeInt32(10, var56);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.secgrowtype)) {
         Integer var57 = var1.secgrowtype;
         if (var57 != null) {
            var2.writeInt32(11, var57);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.equipscore)) {
         Integer var58 = var1.equipscore;
         if (var58 != null) {
            var2.writeInt32(12, var58);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.spoint)) {
         Integer var59 = var1.spoint;
         if (var59 != null) {
            var2.writeInt32(13, var59);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.adventureunionlevel)) {
         Integer var60 = var1.adventureunionlevel;
         if (var60 != null) {
            var2.writeInt32(14, var60);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.adventureunionexp)) {
         Long var61 = var1.adventureunionexp;
         if (var61 != null) {
            var2.writeInt64(15, var61);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.characlistcount)) {
         Integer var62 = var1.characlistcount;
         if (var62 != null) {
            var2.writeInt32(16, var62);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.gname)) {
         String var63 = var1.gname;
         if (var63 != null) {
            var2.writeString(17, var63);
         }
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.gsymbol)) {
         List var64 = var1.gsymbol;
         if (var64 != null) {
            CodedConstant.writeToList(var2, 18, FieldType.OBJECT, var64, false);
         }
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.equiplist)) {
         List var65 = var1.equiplist;
         if (var65 != null) {
            CodedConstant.writeToList(var2, 19, FieldType.OBJECT, var65, false);
         }
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.avatarlist)) {
         List var66 = var1.avatarlist;
         if (var66 != null) {
            CodedConstant.writeToList(var2, 20, FieldType.OBJECT, var66, false);
         }
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.creaturelist)) {
         List var67 = var1.creaturelist;
         if (var67 != null) {
            CodedConstant.writeToList(var2, 21, FieldType.OBJECT, var67, false);
         }
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.cartifactlist)) {
         List var68 = var1.cartifactlist;
         if (var68 != null) {
            CodedConstant.writeToList(var2, 22, FieldType.OBJECT, var68, false);
         }
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.skinlist)) {
         List var69 = var1.skinlist;
         if (var69 != null) {
            CodedConstant.writeToList(var2, 23, FieldType.OBJECT, var69, false);
         }
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.bufflist)) {
         PT_BUFF_LIST var70 = var1.bufflist;
         if (var70 != null) {
            CodedConstant.writeObject(var2, 24, FieldType.OBJECT, var70, false);
         }
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.skill)) {
         PT_SKILL_INFO var71 = var1.skill;
         if (var71 != null) {
            CodedConstant.writeObject(var2, 25, FieldType.OBJECT, var71, false);
         }
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.gmembergrade)) {
         Integer var72 = var1.gmembergrade;
         if (var72 != null) {
            var2.writeInt32(26, var72);
         }
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.blackdiamond)) {
         Long var73 = var1.blackdiamond;
         if (var73 != null) {
            var2.writeUInt64(27, var73);
         }
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.creditscore)) {
         Integer var74 = var1.creditscore;
         if (var74 != null) {
            var2.writeInt32(28, var74);
         }
      }

      Object var31 = null;
      if (!CodedConstant.isNull(var1.gamecenterinfo)) {
         Integer var75 = var1.gamecenterinfo;
         if (var75 != null) {
            var2.writeInt32(29, var75);
         }
      }

      Object var32 = null;
      if (!CodedConstant.isNull(var1.qqVipinfo)) {
         Integer var76 = var1.qqVipinfo;
         if (var76 != null) {
            var2.writeInt32(30, var76);
         }
      }

      Object var33 = null;
      if (!CodedConstant.isNull(var1.avatarVisibleFlags)) {
         Integer var77 = var1.avatarVisibleFlags;
         if (var77 != null) {
            var2.writeUInt32(31, var77);
         }
      }

      Object var34 = null;
      if (!CodedConstant.isNull(var1.equipskinlist)) {
         List var78 = var1.equipskinlist;
         if (var78 != null) {
            CodedConstant.writeToList(var2, 32, FieldType.OBJECT, var78, false);
         }
      }

      Object var35 = null;
      if (!CodedConstant.isNull(var1.avatarskinlist)) {
         List var79 = var1.avatarskinlist;
         if (var79 != null) {
            CodedConstant.writeToList(var2, 33, FieldType.OBJECT, var79, false);
         }
      }

      Object var36 = null;
      if (!CodedConstant.isNull(var1.totallike)) {
         Integer var80 = var1.totallike;
         if (var80 != null) {
            var2.writeInt32(34, var80);
         }
      }

      Object var37 = null;
      if (!CodedConstant.isNull(var1.like)) {
         Integer var81 = var1.like;
         if (var81 != null) {
            var2.writeInt32(35, var81);
         }
      }

      Object var38 = null;
      if (!CodedConstant.isNull(var1.rank)) {
         Integer var82 = var1.rank;
         if (var82 != null) {
            var2.writeInt32(36, var82);
         }
      }

      Object var39 = null;
      if (!CodedConstant.isNull(var1.info)) {
         List var83 = var1.info;
         if (var83 != null) {
            CodedConstant.writeToList(var2, 37, FieldType.OBJECT, var83, false);
         }
      }

      Object var40 = null;
      if (!CodedConstant.isNull(var1.communionlevel)) {
         Integer var84 = var1.communionlevel;
         if (var84 != null) {
            var2.writeInt32(38, var84);
         }
      }

      Object var41 = null;
      if (!CodedConstant.isNull(var1.fame)) {
         Integer var85 = var1.fame;
         if (var85 != null) {
            var2.writeInt32(39, var85);
         }
      }

      Object var42 = null;
      if (!CodedConstant.isNull(var1.charm)) {
         Integer var86 = var1.charm;
         if (var86 != null) {
            var2.writeInt32(40, var86);
         }
      }

      Object var43 = null;
      if (!CodedConstant.isNull(var1.sdavatarlist)) {
         List var87 = var1.sdavatarlist;
         if (var87 != null) {
            CodedConstant.writeToList(var2, 41, FieldType.OBJECT, var87, false);
         }
      }

      Object var44 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var88 = var1.grade;
         if (var88 != null) {
            var2.writeInt32(42, var88);
         }
      }

      Object var45 = null;
      if (!CodedConstant.isNull(var1.gradeFair)) {
         Integer var89 = var1.gradeFair;
         if (var89 != null) {
            var2.writeInt32(43, var89);
         }
      }

      Object var46 = null;
      if (!CodedConstant.isNull(var1.isadventureCondition)) {
         Boolean var90 = var1.isadventureCondition;
         if (var90 != null) {
            var2.writeBool(44, var90);
         }
      }

   }

   public void writeTo(RES_TARGET_USER_DETAIL_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_TARGET_USER_DETAIL_INFO readFrom(CodedInputStream var1) throws IOException {
      RES_TARGET_USER_DETAIL_INFO var2 = new RES_TARGET_USER_DETAIL_INFO();
      var2.gsymbol = new ArrayList();
      var2.equiplist = new ArrayList();
      var2.avatarlist = new ArrayList();
      var2.creaturelist = new ArrayList();
      var2.cartifactlist = new ArrayList();
      var2.skinlist = new ArrayList();
      var2.equipskinlist = new ArrayList();
      var2.avatarskinlist = new ArrayList();
      var2.info = new ArrayList();
      var2.sdavatarlist = new ArrayList();

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
               var2.guid = var1.readUInt64();
            } else if (var5 == 24) {
               var2.world = var1.readInt32();
            } else if (var5 == 32) {
               var2.gguid = var1.readUInt64();
            } else if (var5 == 42) {
               var2.gmastername = var1.readString();
            } else if (var5 == 50) {
               var2.name = var1.readString();
            } else if (var5 == 56) {
               var2.exp = var1.readInt32();
            } else if (var5 == 64) {
               var2.level = var1.readInt32();
            } else if (var5 == 72) {
               var2.job = var1.readInt32();
            } else if (var5 == 80) {
               var2.growtype = var1.readInt32();
            } else if (var5 == 88) {
               var2.secgrowtype = var1.readInt32();
            } else if (var5 == 96) {
               var2.equipscore = var1.readInt32();
            } else if (var5 == 104) {
               var2.spoint = var1.readInt32();
            } else if (var5 == 112) {
               var2.adventureunionlevel = var1.readInt32();
            } else if (var5 == 120) {
               var2.adventureunionexp = var1.readInt64();
            } else if (var5 == 128) {
               var2.characlistcount = var1.readInt32();
            } else if (var5 == 138) {
               var2.gname = var1.readString();
            } else if (var5 == 146) {
               Codec var10 = ProtobufProxy.create(PT_GUILD_SYMBOL.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.gsymbol == null) {
                  var2.gsymbol = new ArrayList();
               }

               var2.gsymbol.add((PT_GUILD_SYMBOL)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 154) {
               Codec var11 = ProtobufProxy.create(PT_EQUIP.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var22 = var1.readRawVarint32();
               int var33 = var1.pushLimit(var22);
               if (var2.equiplist == null) {
                  var2.equiplist = new ArrayList();
               }

               var2.equiplist.add((PT_EQUIP)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var33);
            } else if (var5 == 162) {
               Codec var12 = ProtobufProxy.create(PT_AVATAR_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var23 = var1.readRawVarint32();
               int var34 = var1.pushLimit(var23);
               if (var2.avatarlist == null) {
                  var2.avatarlist = new ArrayList();
               }

               var2.avatarlist.add((PT_AVATAR_ITEM)var12.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var34);
            } else if (var5 == 170) {
               Codec var13 = ProtobufProxy.create(PT_CREATURE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var24 = var1.readRawVarint32();
               int var35 = var1.pushLimit(var24);
               if (var2.creaturelist == null) {
                  var2.creaturelist = new ArrayList();
               }

               var2.creaturelist.add((PT_CREATURE)var13.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var35);
            } else if (var5 == 178) {
               Codec var14 = ProtobufProxy.create(PT_ARTIFACT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var25 = var1.readRawVarint32();
               int var36 = var1.pushLimit(var25);
               if (var2.cartifactlist == null) {
                  var2.cartifactlist = new ArrayList();
               }

               var2.cartifactlist.add((PT_ARTIFACT)var14.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var36);
            } else if (var5 == 186) {
               Codec var15 = ProtobufProxy.create(PT_SKIN_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var26 = var1.readRawVarint32();
               int var37 = var1.pushLimit(var26);
               if (var2.skinlist == null) {
                  var2.skinlist = new ArrayList();
               }

               var2.skinlist.add((PT_SKIN_ITEM)var15.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var37);
            } else if (var5 == 194) {
               Codec var16 = ProtobufProxy.create(PT_BUFF_LIST.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var27 = var1.readRawVarint32();
               int var38 = var1.pushLimit(var27);
               var2.bufflist = (PT_BUFF_LIST)var16.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var38);
            } else if (var5 == 202) {
               Codec var17 = ProtobufProxy.create(PT_SKILL_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var28 = var1.readRawVarint32();
               int var39 = var1.pushLimit(var28);
               var2.skill = (PT_SKILL_INFO)var17.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var39);
            } else if (var5 == 208) {
               var2.gmembergrade = var1.readInt32();
            } else if (var5 == 216) {
               var2.blackdiamond = var1.readUInt64();
            } else if (var5 == 224) {
               var2.creditscore = var1.readInt32();
            } else if (var5 == 232) {
               var2.gamecenterinfo = var1.readInt32();
            } else if (var5 == 240) {
               var2.qqVipinfo = var1.readInt32();
            } else if (var5 == 248) {
               var2.avatarVisibleFlags = var1.readUInt32();
            } else if (var5 == 258) {
               Codec var18 = ProtobufProxy.create(PT_EQUIP.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var29 = var1.readRawVarint32();
               int var40 = var1.pushLimit(var29);
               if (var2.equipskinlist == null) {
                  var2.equipskinlist = new ArrayList();
               }

               var2.equipskinlist.add((PT_EQUIP)var18.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var40);
            } else if (var5 == 266) {
               Codec var19 = ProtobufProxy.create(PT_AVATAR_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var30 = var1.readRawVarint32();
               int var41 = var1.pushLimit(var30);
               if (var2.avatarskinlist == null) {
                  var2.avatarskinlist = new ArrayList();
               }

               var2.avatarskinlist.add((PT_AVATAR_ITEM)var19.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var41);
            } else if (var5 == 272) {
               var2.totallike = var1.readInt32();
            } else if (var5 == 280) {
               var2.like = var1.readInt32();
            } else if (var5 == 288) {
               var2.rank = var1.readInt32();
            } else if (var5 == 298) {
               Codec var20 = ProtobufProxy.create(PT_CHIVALRY.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var31 = var1.readRawVarint32();
               int var42 = var1.pushLimit(var31);
               if (var2.info == null) {
                  var2.info = new ArrayList();
               }

               var2.info.add((PT_CHIVALRY)var20.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var42);
            } else if (var5 == 304) {
               var2.communionlevel = var1.readInt32();
            } else if (var5 == 312) {
               var2.fame = var1.readInt32();
            } else if (var5 == 320) {
               var2.charm = var1.readInt32();
            } else if (var5 == 330) {
               Codec var21 = ProtobufProxy.create(PT_AVATAR_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var32 = var1.readRawVarint32();
               int var43 = var1.pushLimit(var32);
               if (var2.sdavatarlist == null) {
                  var2.sdavatarlist = new ArrayList();
               }

               var2.sdavatarlist.add((PT_AVATAR_ITEM)var21.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var43);
            } else if (var5 == 336) {
               var2.grade = var1.readInt32();
            } else if (var5 == 344) {
               var2.gradeFair = var1.readInt32();
            } else if (var5 == 352) {
               var2.isadventureCondition = var1.readBool();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_TARGET_USER_DETAIL_INFO.class);
         return this.descriptor = var1;
      }
   }
}
