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

public class PT_AI_CHARACTER_DETAIL_INFO$$JProtoBufClass implements Codec<PT_AI_CHARACTER_DETAIL_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_AI_CHARACTER_DETAIL_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_AI_CHARACTER_DETAIL_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_AI_CHARACTER_DETAIL_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var42 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var42);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var43 = var1.name;
         var2 += CodedOutputStream.computeStringSize(2, var43);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var44 = var1.growtype;
         var2 += CodedOutputStream.computeInt32Size(3, var44);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var45 = var1.secondgrowtype;
         var2 += CodedOutputStream.computeInt32Size(4, var45);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.exp)) {
         Integer var46 = var1.exp;
         var2 += CodedOutputStream.computeInt32Size(5, var46);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.hp)) {
         Integer var47 = var1.hp;
         var2 += CodedOutputStream.computeInt32Size(6, var47);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.mp)) {
         Integer var48 = var1.mp;
         var2 += CodedOutputStream.computeInt32Size(7, var48);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var49 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(8, var49);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var50 = var1.job;
         var2 += CodedOutputStream.computeInt32Size(9, var50);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.equipscore)) {
         Integer var51 = var1.equipscore;
         var2 += CodedOutputStream.computeInt32Size(10, var51);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.score)) {
         Integer var52 = var1.score;
         var2 += CodedOutputStream.computeInt32Size(11, var52);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var53 = var1.count;
         var2 += CodedOutputStream.computeInt32Size(12, var53);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.date)) {
         Long var54 = var1.date;
         var2 += CodedOutputStream.computeInt64Size(13, var54);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.spoint)) {
         Integer var55 = var1.spoint;
         var2 += CodedOutputStream.computeInt32Size(14, var55);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.adventureunionlevel)) {
         Integer var56 = var1.adventureunionlevel;
         var2 += CodedOutputStream.computeInt32Size(15, var56);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.adventureunionexp)) {
         Long var57 = var1.adventureunionexp;
         var2 += CodedOutputStream.computeInt64Size(16, var57);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.skilllist)) {
         List var58 = var1.skilllist;
         var2 += CodedConstant.computeListSize(17, var58, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.equiplist)) {
         List var59 = var1.equiplist;
         var2 += CodedConstant.computeListSize(18, var59, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.creaturelist)) {
         List var60 = var1.creaturelist;
         var2 += CodedConstant.computeListSize(19, var60, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.cartifactlist)) {
         List var61 = var1.cartifactlist;
         var2 += CodedConstant.computeListSize(20, var61, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.avatarlist)) {
         List var62 = var1.avatarlist;
         var2 += CodedConstant.computeListSize(21, var62, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.skinlist)) {
         List var63 = var1.skinlist;
         var2 += CodedConstant.computeListSize(22, var63, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var64 = var1.world;
         var2 += CodedOutputStream.computeInt32Size(23, var64);
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.gguid)) {
         Long var65 = var1.gguid;
         var2 += CodedOutputStream.computeUInt64Size(24, var65);
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.gname)) {
         String var66 = var1.gname;
         var2 += CodedOutputStream.computeStringSize(25, var66);
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.gmastername)) {
         String var67 = var1.gmastername;
         var2 += CodedOutputStream.computeStringSize(26, var67);
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.gmembergrade)) {
         Integer var68 = var1.gmembergrade;
         var2 += CodedOutputStream.computeInt32Size(27, var68);
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.blackdiamond)) {
         Long var69 = var1.blackdiamond;
         var2 += CodedOutputStream.computeUInt64Size(28, var69);
      }

      Object var31 = null;
      if (!CodedConstant.isNull(var1.avatarVisibleFlags)) {
         Integer var70 = var1.avatarVisibleFlags;
         var2 += CodedOutputStream.computeUInt32Size(29, var70);
      }

      Object var32 = null;
      if (!CodedConstant.isNull(var1.gamecenterinfo)) {
         Integer var71 = var1.gamecenterinfo;
         var2 += CodedOutputStream.computeInt32Size(30, var71);
      }

      Object var33 = null;
      if (!CodedConstant.isNull(var1.qqVipinfo)) {
         Integer var72 = var1.qqVipinfo;
         var2 += CodedOutputStream.computeInt32Size(31, var72);
      }

      Object var34 = null;
      if (!CodedConstant.isNull(var1.equipskinlist)) {
         List var73 = var1.equipskinlist;
         var2 += CodedConstant.computeListSize(32, var73, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var35 = null;
      if (!CodedConstant.isNull(var1.avatarskinlist)) {
         List var74 = var1.avatarskinlist;
         var2 += CodedConstant.computeListSize(33, var74, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var36 = null;
      if (!CodedConstant.isNull(var1.totallike)) {
         Integer var75 = var1.totallike;
         var2 += CodedOutputStream.computeInt32Size(34, var75);
      }

      Object var37 = null;
      if (!CodedConstant.isNull(var1.like)) {
         Integer var76 = var1.like;
         var2 += CodedOutputStream.computeInt32Size(35, var76);
      }

      Object var38 = null;
      if (!CodedConstant.isNull(var1.rank)) {
         Integer var77 = var1.rank;
         var2 += CodedOutputStream.computeInt32Size(36, var77);
      }

      Object var39 = null;
      if (!CodedConstant.isNull(var1.info)) {
         List var78 = var1.info;
         var2 += CodedConstant.computeListSize(37, var78, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var40 = null;
      if (!CodedConstant.isNull(var1.communionlevel)) {
         Integer var79 = var1.communionlevel;
         var2 += CodedOutputStream.computeInt32Size(38, var79);
      }

      Object var41 = null;
      if (!CodedConstant.isNull(var1.sdavatarlist)) {
         List var80 = var1.sdavatarlist;
         var2 += CodedConstant.computeListSize(39, var80, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(PT_AI_CHARACTER_DETAIL_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var42 = var1.charguid;
         if (var42 != null) {
            var2.writeUInt64(1, var42);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var43 = var1.name;
         if (var43 != null) {
            var2.writeString(2, var43);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var44 = var1.growtype;
         if (var44 != null) {
            var2.writeInt32(3, var44);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var45 = var1.secondgrowtype;
         if (var45 != null) {
            var2.writeInt32(4, var45);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.exp)) {
         Integer var46 = var1.exp;
         if (var46 != null) {
            var2.writeInt32(5, var46);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.hp)) {
         Integer var47 = var1.hp;
         if (var47 != null) {
            var2.writeInt32(6, var47);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.mp)) {
         Integer var48 = var1.mp;
         if (var48 != null) {
            var2.writeInt32(7, var48);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var49 = var1.level;
         if (var49 != null) {
            var2.writeInt32(8, var49);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var50 = var1.job;
         if (var50 != null) {
            var2.writeInt32(9, var50);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.equipscore)) {
         Integer var51 = var1.equipscore;
         if (var51 != null) {
            var2.writeInt32(10, var51);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.score)) {
         Integer var52 = var1.score;
         if (var52 != null) {
            var2.writeInt32(11, var52);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var53 = var1.count;
         if (var53 != null) {
            var2.writeInt32(12, var53);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.date)) {
         Long var54 = var1.date;
         if (var54 != null) {
            var2.writeInt64(13, var54);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.spoint)) {
         Integer var55 = var1.spoint;
         if (var55 != null) {
            var2.writeInt32(14, var55);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.adventureunionlevel)) {
         Integer var56 = var1.adventureunionlevel;
         if (var56 != null) {
            var2.writeInt32(15, var56);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.adventureunionexp)) {
         Long var57 = var1.adventureunionexp;
         if (var57 != null) {
            var2.writeInt64(16, var57);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.skilllist)) {
         List var58 = var1.skilllist;
         if (var58 != null) {
            CodedConstant.writeToList(var2, 17, FieldType.OBJECT, var58, false);
         }
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.equiplist)) {
         List var59 = var1.equiplist;
         if (var59 != null) {
            CodedConstant.writeToList(var2, 18, FieldType.OBJECT, var59, false);
         }
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.creaturelist)) {
         List var60 = var1.creaturelist;
         if (var60 != null) {
            CodedConstant.writeToList(var2, 19, FieldType.OBJECT, var60, false);
         }
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.cartifactlist)) {
         List var61 = var1.cartifactlist;
         if (var61 != null) {
            CodedConstant.writeToList(var2, 20, FieldType.OBJECT, var61, false);
         }
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.avatarlist)) {
         List var62 = var1.avatarlist;
         if (var62 != null) {
            CodedConstant.writeToList(var2, 21, FieldType.OBJECT, var62, false);
         }
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.skinlist)) {
         List var63 = var1.skinlist;
         if (var63 != null) {
            CodedConstant.writeToList(var2, 22, FieldType.OBJECT, var63, false);
         }
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var64 = var1.world;
         if (var64 != null) {
            var2.writeInt32(23, var64);
         }
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.gguid)) {
         Long var65 = var1.gguid;
         if (var65 != null) {
            var2.writeUInt64(24, var65);
         }
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.gname)) {
         String var66 = var1.gname;
         if (var66 != null) {
            var2.writeString(25, var66);
         }
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.gmastername)) {
         String var67 = var1.gmastername;
         if (var67 != null) {
            var2.writeString(26, var67);
         }
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.gmembergrade)) {
         Integer var68 = var1.gmembergrade;
         if (var68 != null) {
            var2.writeInt32(27, var68);
         }
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.blackdiamond)) {
         Long var69 = var1.blackdiamond;
         if (var69 != null) {
            var2.writeUInt64(28, var69);
         }
      }

      Object var31 = null;
      if (!CodedConstant.isNull(var1.avatarVisibleFlags)) {
         Integer var70 = var1.avatarVisibleFlags;
         if (var70 != null) {
            var2.writeUInt32(29, var70);
         }
      }

      Object var32 = null;
      if (!CodedConstant.isNull(var1.gamecenterinfo)) {
         Integer var71 = var1.gamecenterinfo;
         if (var71 != null) {
            var2.writeInt32(30, var71);
         }
      }

      Object var33 = null;
      if (!CodedConstant.isNull(var1.qqVipinfo)) {
         Integer var72 = var1.qqVipinfo;
         if (var72 != null) {
            var2.writeInt32(31, var72);
         }
      }

      Object var34 = null;
      if (!CodedConstant.isNull(var1.equipskinlist)) {
         List var73 = var1.equipskinlist;
         if (var73 != null) {
            CodedConstant.writeToList(var2, 32, FieldType.OBJECT, var73, false);
         }
      }

      Object var35 = null;
      if (!CodedConstant.isNull(var1.avatarskinlist)) {
         List var74 = var1.avatarskinlist;
         if (var74 != null) {
            CodedConstant.writeToList(var2, 33, FieldType.OBJECT, var74, false);
         }
      }

      Object var36 = null;
      if (!CodedConstant.isNull(var1.totallike)) {
         Integer var75 = var1.totallike;
         if (var75 != null) {
            var2.writeInt32(34, var75);
         }
      }

      Object var37 = null;
      if (!CodedConstant.isNull(var1.like)) {
         Integer var76 = var1.like;
         if (var76 != null) {
            var2.writeInt32(35, var76);
         }
      }

      Object var38 = null;
      if (!CodedConstant.isNull(var1.rank)) {
         Integer var77 = var1.rank;
         if (var77 != null) {
            var2.writeInt32(36, var77);
         }
      }

      Object var39 = null;
      if (!CodedConstant.isNull(var1.info)) {
         List var78 = var1.info;
         if (var78 != null) {
            CodedConstant.writeToList(var2, 37, FieldType.OBJECT, var78, false);
         }
      }

      Object var40 = null;
      if (!CodedConstant.isNull(var1.communionlevel)) {
         Integer var79 = var1.communionlevel;
         if (var79 != null) {
            var2.writeInt32(38, var79);
         }
      }

      Object var41 = null;
      if (!CodedConstant.isNull(var1.sdavatarlist)) {
         List var80 = var1.sdavatarlist;
         if (var80 != null) {
            CodedConstant.writeToList(var2, 39, FieldType.OBJECT, var80, false);
         }
      }

   }

   public void writeTo(PT_AI_CHARACTER_DETAIL_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_AI_CHARACTER_DETAIL_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_AI_CHARACTER_DETAIL_INFO var2 = new PT_AI_CHARACTER_DETAIL_INFO();
      var2.skilllist = new ArrayList();
      var2.equiplist = new ArrayList();
      var2.creaturelist = new ArrayList();
      var2.cartifactlist = new ArrayList();
      var2.avatarlist = new ArrayList();
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
               var2.charguid = var1.readUInt64();
            } else if (var5 == 18) {
               var2.name = var1.readString();
            } else if (var5 == 24) {
               var2.growtype = var1.readInt32();
            } else if (var5 == 32) {
               var2.secondgrowtype = var1.readInt32();
            } else if (var5 == 40) {
               var2.exp = var1.readInt32();
            } else if (var5 == 48) {
               var2.hp = var1.readInt32();
            } else if (var5 == 56) {
               var2.mp = var1.readInt32();
            } else if (var5 == 64) {
               var2.level = var1.readInt32();
            } else if (var5 == 72) {
               var2.job = var1.readInt32();
            } else if (var5 == 80) {
               var2.equipscore = var1.readInt32();
            } else if (var5 == 88) {
               var2.score = var1.readInt32();
            } else if (var5 == 96) {
               var2.count = var1.readInt32();
            } else if (var5 == 104) {
               var2.date = var1.readInt64();
            } else if (var5 == 112) {
               var2.spoint = var1.readInt32();
            } else if (var5 == 120) {
               var2.adventureunionlevel = var1.readInt32();
            } else if (var5 == 128) {
               var2.adventureunionexp = var1.readInt64();
            } else if (var5 == 138) {
               Codec var10 = ProtobufProxy.create(PT_SKILL.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.skilllist == null) {
                  var2.skilllist = new ArrayList();
               }

               var2.skilllist.add((PT_SKILL)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 146) {
               Codec var11 = ProtobufProxy.create(PT_EQUIP.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var20 = var1.readRawVarint32();
               int var29 = var1.pushLimit(var20);
               if (var2.equiplist == null) {
                  var2.equiplist = new ArrayList();
               }

               var2.equiplist.add((PT_EQUIP)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var29);
            } else if (var5 == 154) {
               Codec var12 = ProtobufProxy.create(PT_CREATURE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var21 = var1.readRawVarint32();
               int var30 = var1.pushLimit(var21);
               if (var2.creaturelist == null) {
                  var2.creaturelist = new ArrayList();
               }

               var2.creaturelist.add((PT_CREATURE)var12.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var30);
            } else if (var5 == 162) {
               Codec var13 = ProtobufProxy.create(PT_ARTIFACT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var22 = var1.readRawVarint32();
               int var31 = var1.pushLimit(var22);
               if (var2.cartifactlist == null) {
                  var2.cartifactlist = new ArrayList();
               }

               var2.cartifactlist.add((PT_ARTIFACT)var13.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var31);
            } else if (var5 == 170) {
               Codec var14 = ProtobufProxy.create(PT_AVATAR_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var23 = var1.readRawVarint32();
               int var32 = var1.pushLimit(var23);
               if (var2.avatarlist == null) {
                  var2.avatarlist = new ArrayList();
               }

               var2.avatarlist.add((PT_AVATAR_ITEM)var14.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var32);
            } else if (var5 == 178) {
               Codec var15 = ProtobufProxy.create(PT_SKIN_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var24 = var1.readRawVarint32();
               int var33 = var1.pushLimit(var24);
               if (var2.skinlist == null) {
                  var2.skinlist = new ArrayList();
               }

               var2.skinlist.add((PT_SKIN_ITEM)var15.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var33);
            } else if (var5 == 184) {
               var2.world = var1.readInt32();
            } else if (var5 == 192) {
               var2.gguid = var1.readUInt64();
            } else if (var5 == 202) {
               var2.gname = var1.readString();
            } else if (var5 == 210) {
               var2.gmastername = var1.readString();
            } else if (var5 == 216) {
               var2.gmembergrade = var1.readInt32();
            } else if (var5 == 224) {
               var2.blackdiamond = var1.readUInt64();
            } else if (var5 == 232) {
               var2.avatarVisibleFlags = var1.readUInt32();
            } else if (var5 == 240) {
               var2.gamecenterinfo = var1.readInt32();
            } else if (var5 == 248) {
               var2.qqVipinfo = var1.readInt32();
            } else if (var5 == 258) {
               Codec var16 = ProtobufProxy.create(PT_EQUIP.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var25 = var1.readRawVarint32();
               int var34 = var1.pushLimit(var25);
               if (var2.equipskinlist == null) {
                  var2.equipskinlist = new ArrayList();
               }

               var2.equipskinlist.add((PT_EQUIP)var16.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var34);
            } else if (var5 == 266) {
               Codec var17 = ProtobufProxy.create(PT_AVATAR_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var26 = var1.readRawVarint32();
               int var35 = var1.pushLimit(var26);
               if (var2.avatarskinlist == null) {
                  var2.avatarskinlist = new ArrayList();
               }

               var2.avatarskinlist.add((PT_AVATAR_ITEM)var17.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var35);
            } else if (var5 == 272) {
               var2.totallike = var1.readInt32();
            } else if (var5 == 280) {
               var2.like = var1.readInt32();
            } else if (var5 == 288) {
               var2.rank = var1.readInt32();
            } else if (var5 == 298) {
               Codec var18 = ProtobufProxy.create(PT_CHIVALRY.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var27 = var1.readRawVarint32();
               int var36 = var1.pushLimit(var27);
               if (var2.info == null) {
                  var2.info = new ArrayList();
               }

               var2.info.add((PT_CHIVALRY)var18.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var36);
            } else if (var5 == 304) {
               var2.communionlevel = var1.readInt32();
            } else if (var5 == 314) {
               Codec var19 = ProtobufProxy.create(PT_AVATAR_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var28 = var1.readRawVarint32();
               int var37 = var1.pushLimit(var28);
               if (var2.sdavatarlist == null) {
                  var2.sdavatarlist = new ArrayList();
               }

               var2.sdavatarlist.add((PT_AVATAR_ITEM)var19.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var37);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_AI_CHARACTER_DETAIL_INFO.class);
         return this.descriptor = var1;
      }
   }
}
