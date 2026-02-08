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

public class PT_CHARACTER_INFO$$JProtoBufClass implements Codec<PT_CHARACTER_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_CHARACTER_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_CHARACTER_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_CHARACTER_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var37 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var37);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var38 = var1.job;
         var2 += CodedOutputStream.computeInt32Size(2, var38);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var39 = var1.growtype;
         var2 += CodedOutputStream.computeInt32Size(3, var39);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var40 = var1.secondgrowtype;
         var2 += CodedOutputStream.computeInt32Size(4, var40);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var41 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(5, var41);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var42 = var1.name;
         var2 += CodedOutputStream.computeStringSize(6, var42);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.gguid)) {
         Long var43 = var1.gguid;
         var2 += CodedOutputStream.computeUInt64Size(7, var43);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.posx)) {
         Integer var44 = var1.posx;
         var2 += CodedOutputStream.computeInt32Size(8, var44);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.posy)) {
         Integer var45 = var1.posy;
         var2 += CodedOutputStream.computeInt32Size(9, var45);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.gname)) {
         String var46 = var1.gname;
         var2 += CodedOutputStream.computeStringSize(10, var46);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.gsymbol)) {
         List var47 = var1.gsymbol;
         var2 += CodedConstant.computeListSize(11, var47, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.vip)) {
         Integer var48 = var1.vip;
         var2 += CodedOutputStream.computeInt32Size(12, var48);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.vcreature)) {
         Integer var49 = var1.vcreature;
         var2 += CodedOutputStream.computeInt32Size(13, var49);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.equiplist)) {
         List var50 = var1.equiplist;
         var2 += CodedConstant.computeListSize(14, var50, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.avatarlist)) {
         List var51 = var1.avatarlist;
         var2 += CodedConstant.computeListSize(15, var51, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.creaturelist)) {
         List var52 = var1.creaturelist;
         var2 += CodedConstant.computeListSize(16, var52, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.cartifactlist)) {
         List var53 = var1.cartifactlist;
         var2 += CodedConstant.computeListSize(17, var53, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.skinlist)) {
         List var54 = var1.skinlist;
         var2 += CodedConstant.computeListSize(18, var54, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.partydisturb)) {
         Integer var55 = var1.partydisturb;
         var2 += CodedOutputStream.computeInt32Size(19, var55);
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.spoint)) {
         Integer var56 = var1.spoint;
         var2 += CodedOutputStream.computeInt32Size(20, var56);
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.adventureunionlevel)) {
         Integer var57 = var1.adventureunionlevel;
         var2 += CodedOutputStream.computeInt32Size(21, var57);
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.adventureunionexp)) {
         Long var58 = var1.adventureunionexp;
         var2 += CodedOutputStream.computeInt64Size(22, var58);
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var59 = var1.partyguid;
         var2 += CodedOutputStream.computeUInt64Size(23, var59);
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.partyleaderguid)) {
         Long var60 = var1.partyleaderguid;
         var2 += CodedOutputStream.computeUInt64Size(24, var60);
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var61 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(25, var61);
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.pvpstargrade)) {
         Integer var62 = var1.pvpstargrade;
         var2 += CodedOutputStream.computeInt32Size(26, var62);
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.pvpstarcount)) {
         Integer var63 = var1.pvpstarcount;
         var2 += CodedOutputStream.computeInt32Size(27, var63);
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.blackdiamond)) {
         Boolean var64 = var1.blackdiamond;
         var2 += CodedOutputStream.computeBoolSize(28, var64);
      }

      Object var31 = null;
      if (!CodedConstant.isNull(var1.avatarVisibleFlags)) {
         Integer var65 = var1.avatarVisibleFlags;
         var2 += CodedOutputStream.computeUInt32Size(29, var65);
      }

      Object var32 = null;
      if (!CodedConstant.isNull(var1.equipskinlist)) {
         List var66 = var1.equipskinlist;
         var2 += CodedConstant.computeListSize(30, var66, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var33 = null;
      if (!CodedConstant.isNull(var1.avatarskinlist)) {
         List var67 = var1.avatarskinlist;
         var2 += CodedConstant.computeListSize(31, var67, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var34 = null;
      if (!CodedConstant.isNull(var1.sdavatarlist)) {
         List var68 = var1.sdavatarlist;
         var2 += CodedConstant.computeListSize(32, var68, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var35 = null;
      if (!CodedConstant.isNull(var1.fairpvpstargrade)) {
         Integer var69 = var1.fairpvpstargrade;
         var2 += CodedOutputStream.computeInt32Size(33, var69);
      }

      Object var36 = null;
      if (!CodedConstant.isNull(var1.fairpvpstarcount)) {
         Integer var70 = var1.fairpvpstarcount;
         var2 += CodedOutputStream.computeInt32Size(34, var70);
      }

      return var2;
   }

   public void doWriteTo(PT_CHARACTER_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var37 = var1.charguid;
         if (var37 != null) {
            var2.writeUInt64(1, var37);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var38 = var1.job;
         if (var38 != null) {
            var2.writeInt32(2, var38);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var39 = var1.growtype;
         if (var39 != null) {
            var2.writeInt32(3, var39);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var40 = var1.secondgrowtype;
         if (var40 != null) {
            var2.writeInt32(4, var40);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var41 = var1.level;
         if (var41 != null) {
            var2.writeInt32(5, var41);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var42 = var1.name;
         if (var42 != null) {
            var2.writeString(6, var42);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.gguid)) {
         Long var43 = var1.gguid;
         if (var43 != null) {
            var2.writeUInt64(7, var43);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.posx)) {
         Integer var44 = var1.posx;
         if (var44 != null) {
            var2.writeInt32(8, var44);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.posy)) {
         Integer var45 = var1.posy;
         if (var45 != null) {
            var2.writeInt32(9, var45);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.gname)) {
         String var46 = var1.gname;
         if (var46 != null) {
            var2.writeString(10, var46);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.gsymbol)) {
         List var47 = var1.gsymbol;
         if (var47 != null) {
            CodedConstant.writeToList(var2, 11, FieldType.OBJECT, var47, false);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.vip)) {
         Integer var48 = var1.vip;
         if (var48 != null) {
            var2.writeInt32(12, var48);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.vcreature)) {
         Integer var49 = var1.vcreature;
         if (var49 != null) {
            var2.writeInt32(13, var49);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.equiplist)) {
         List var50 = var1.equiplist;
         if (var50 != null) {
            CodedConstant.writeToList(var2, 14, FieldType.OBJECT, var50, false);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.avatarlist)) {
         List var51 = var1.avatarlist;
         if (var51 != null) {
            CodedConstant.writeToList(var2, 15, FieldType.OBJECT, var51, false);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.creaturelist)) {
         List var52 = var1.creaturelist;
         if (var52 != null) {
            CodedConstant.writeToList(var2, 16, FieldType.OBJECT, var52, false);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.cartifactlist)) {
         List var53 = var1.cartifactlist;
         if (var53 != null) {
            CodedConstant.writeToList(var2, 17, FieldType.OBJECT, var53, false);
         }
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.skinlist)) {
         List var54 = var1.skinlist;
         if (var54 != null) {
            CodedConstant.writeToList(var2, 18, FieldType.OBJECT, var54, false);
         }
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.partydisturb)) {
         Integer var55 = var1.partydisturb;
         if (var55 != null) {
            var2.writeInt32(19, var55);
         }
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.spoint)) {
         Integer var56 = var1.spoint;
         if (var56 != null) {
            var2.writeInt32(20, var56);
         }
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.adventureunionlevel)) {
         Integer var57 = var1.adventureunionlevel;
         if (var57 != null) {
            var2.writeInt32(21, var57);
         }
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.adventureunionexp)) {
         Long var58 = var1.adventureunionexp;
         if (var58 != null) {
            var2.writeInt64(22, var58);
         }
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var59 = var1.partyguid;
         if (var59 != null) {
            var2.writeUInt64(23, var59);
         }
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.partyleaderguid)) {
         Long var60 = var1.partyleaderguid;
         if (var60 != null) {
            var2.writeUInt64(24, var60);
         }
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var61 = var1.type;
         if (var61 != null) {
            var2.writeInt32(25, var61);
         }
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.pvpstargrade)) {
         Integer var62 = var1.pvpstargrade;
         if (var62 != null) {
            var2.writeInt32(26, var62);
         }
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.pvpstarcount)) {
         Integer var63 = var1.pvpstarcount;
         if (var63 != null) {
            var2.writeInt32(27, var63);
         }
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.blackdiamond)) {
         Boolean var64 = var1.blackdiamond;
         if (var64 != null) {
            var2.writeBool(28, var64);
         }
      }

      Object var31 = null;
      if (!CodedConstant.isNull(var1.avatarVisibleFlags)) {
         Integer var65 = var1.avatarVisibleFlags;
         if (var65 != null) {
            var2.writeUInt32(29, var65);
         }
      }

      Object var32 = null;
      if (!CodedConstant.isNull(var1.equipskinlist)) {
         List var66 = var1.equipskinlist;
         if (var66 != null) {
            CodedConstant.writeToList(var2, 30, FieldType.OBJECT, var66, false);
         }
      }

      Object var33 = null;
      if (!CodedConstant.isNull(var1.avatarskinlist)) {
         List var67 = var1.avatarskinlist;
         if (var67 != null) {
            CodedConstant.writeToList(var2, 31, FieldType.OBJECT, var67, false);
         }
      }

      Object var34 = null;
      if (!CodedConstant.isNull(var1.sdavatarlist)) {
         List var68 = var1.sdavatarlist;
         if (var68 != null) {
            CodedConstant.writeToList(var2, 32, FieldType.OBJECT, var68, false);
         }
      }

      Object var35 = null;
      if (!CodedConstant.isNull(var1.fairpvpstargrade)) {
         Integer var69 = var1.fairpvpstargrade;
         if (var69 != null) {
            var2.writeInt32(33, var69);
         }
      }

      Object var36 = null;
      if (!CodedConstant.isNull(var1.fairpvpstarcount)) {
         Integer var70 = var1.fairpvpstarcount;
         if (var70 != null) {
            var2.writeInt32(34, var70);
         }
      }

   }

   public void writeTo(PT_CHARACTER_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_CHARACTER_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_CHARACTER_INFO var2 = new PT_CHARACTER_INFO();
      var2.gsymbol = new ArrayList();
      var2.equiplist = new ArrayList();
      var2.avatarlist = new ArrayList();
      var2.creaturelist = new ArrayList();
      var2.cartifactlist = new ArrayList();
      var2.skinlist = new ArrayList();
      var2.equipskinlist = new ArrayList();
      var2.avatarskinlist = new ArrayList();
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
            } else if (var5 == 16) {
               var2.job = var1.readInt32();
            } else if (var5 == 24) {
               var2.growtype = var1.readInt32();
            } else if (var5 == 32) {
               var2.secondgrowtype = var1.readInt32();
            } else if (var5 == 40) {
               var2.level = var1.readInt32();
            } else if (var5 == 50) {
               var2.name = var1.readString();
            } else if (var5 == 56) {
               var2.gguid = var1.readUInt64();
            } else if (var5 == 64) {
               var2.posx = var1.readInt32();
            } else if (var5 == 72) {
               var2.posy = var1.readInt32();
            } else if (var5 == 82) {
               var2.gname = var1.readString();
            } else if (var5 == 90) {
               Codec var10 = ProtobufProxy.create(PT_GUILD_SYMBOL.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.gsymbol == null) {
                  var2.gsymbol = new ArrayList();
               }

               var2.gsymbol.add((PT_GUILD_SYMBOL)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 96) {
               var2.vip = var1.readInt32();
            } else if (var5 == 104) {
               var2.vcreature = var1.readInt32();
            } else if (var5 == 114) {
               Codec var11 = ProtobufProxy.create(PT_EQUIP.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var19 = var1.readRawVarint32();
               int var27 = var1.pushLimit(var19);
               if (var2.equiplist == null) {
                  var2.equiplist = new ArrayList();
               }

               var2.equiplist.add((PT_EQUIP)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var27);
            } else if (var5 == 122) {
               Codec var12 = ProtobufProxy.create(PT_AVATAR_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var20 = var1.readRawVarint32();
               int var28 = var1.pushLimit(var20);
               if (var2.avatarlist == null) {
                  var2.avatarlist = new ArrayList();
               }

               var2.avatarlist.add((PT_AVATAR_ITEM)var12.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var28);
            } else if (var5 == 130) {
               Codec var13 = ProtobufProxy.create(PT_CREATURE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var21 = var1.readRawVarint32();
               int var29 = var1.pushLimit(var21);
               if (var2.creaturelist == null) {
                  var2.creaturelist = new ArrayList();
               }

               var2.creaturelist.add((PT_CREATURE)var13.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var29);
            } else if (var5 == 138) {
               Codec var14 = ProtobufProxy.create(PT_ARTIFACT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var22 = var1.readRawVarint32();
               int var30 = var1.pushLimit(var22);
               if (var2.cartifactlist == null) {
                  var2.cartifactlist = new ArrayList();
               }

               var2.cartifactlist.add((PT_ARTIFACT)var14.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var30);
            } else if (var5 == 146) {
               Codec var15 = ProtobufProxy.create(PT_SKIN_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var23 = var1.readRawVarint32();
               int var31 = var1.pushLimit(var23);
               if (var2.skinlist == null) {
                  var2.skinlist = new ArrayList();
               }

               var2.skinlist.add((PT_SKIN_ITEM)var15.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var31);
            } else if (var5 == 152) {
               var2.partydisturb = var1.readInt32();
            } else if (var5 == 160) {
               var2.spoint = var1.readInt32();
            } else if (var5 == 168) {
               var2.adventureunionlevel = var1.readInt32();
            } else if (var5 == 176) {
               var2.adventureunionexp = var1.readInt64();
            } else if (var5 == 184) {
               var2.partyguid = var1.readUInt64();
            } else if (var5 == 192) {
               var2.partyleaderguid = var1.readUInt64();
            } else if (var5 == 200) {
               var2.type = var1.readInt32();
            } else if (var5 == 208) {
               var2.pvpstargrade = var1.readInt32();
            } else if (var5 == 216) {
               var2.pvpstarcount = var1.readInt32();
            } else if (var5 == 224) {
               var2.blackdiamond = var1.readBool();
            } else if (var5 == 232) {
               var2.avatarVisibleFlags = var1.readUInt32();
            } else if (var5 == 242) {
               Codec var16 = ProtobufProxy.create(PT_EQUIP.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var24 = var1.readRawVarint32();
               int var32 = var1.pushLimit(var24);
               if (var2.equipskinlist == null) {
                  var2.equipskinlist = new ArrayList();
               }

               var2.equipskinlist.add((PT_EQUIP)var16.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var32);
            } else if (var5 == 250) {
               Codec var17 = ProtobufProxy.create(PT_AVATAR_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var25 = var1.readRawVarint32();
               int var33 = var1.pushLimit(var25);
               if (var2.avatarskinlist == null) {
                  var2.avatarskinlist = new ArrayList();
               }

               var2.avatarskinlist.add((PT_AVATAR_ITEM)var17.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var33);
            } else if (var5 == 258) {
               Codec var18 = ProtobufProxy.create(PT_AVATAR_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var26 = var1.readRawVarint32();
               int var34 = var1.pushLimit(var26);
               if (var2.sdavatarlist == null) {
                  var2.sdavatarlist = new ArrayList();
               }

               var2.sdavatarlist.add((PT_AVATAR_ITEM)var18.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var34);
            } else if (var5 == 264) {
               var2.fairpvpstargrade = var1.readInt32();
            } else if (var5 == 272) {
               var2.fairpvpstarcount = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_CHARACTER_INFO.class);
         return this.descriptor = var1;
      }
   }
}
