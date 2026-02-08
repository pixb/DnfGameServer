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

public class REQ_DUNGEON_RESULT$$JProtoBufClass implements Codec<REQ_DUNGEON_RESULT>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_DUNGEON_RESULT var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_DUNGEON_RESULT decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_DUNGEON_RESULT var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.damagehistory)) {
         byte[] var41 = var1.damagehistory;
         var2 += CodedOutputStream.computeByteArraySize(1, var41);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.authkey)) {
         String var42 = var1.authkey;
         var2 += CodedOutputStream.computeStringSize(2, var42);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var43 = var1.dungeonguid;
         var2 += CodedOutputStream.computeUInt64Size(3, var43);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.stage)) {
         Integer var44 = var1.stage;
         var2 += CodedOutputStream.computeInt32Size(4, var44);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.qindex)) {
         List var45 = var1.qindex;
         var2 += CodedConstant.computeListSize(5, var45, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.rankscore)) {
         Integer var46 = var1.rankscore;
         var2 += CodedOutputStream.computeInt32Size(6, var46);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.score)) {
         Integer var47 = var1.score;
         var2 += CodedOutputStream.computeInt32Size(7, var47);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var48 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(8, var48);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.result)) {
         Integer var49 = var1.result;
         var2 += CodedOutputStream.computeInt32Size(9, var49);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.useskill)) {
         List var50 = var1.useskill;
         var2 += CodedConstant.computeListSize(10, var50, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.cleartime)) {
         Long var51 = var1.cleartime;
         var2 += CodedOutputStream.computeUInt64Size(11, var51);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.time)) {
         String var52 = var1.time;
         var2 += CodedOutputStream.computeStringSize(12, var52);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.matchingguid)) {
         Long var53 = var1.matchingguid;
         var2 += CodedOutputStream.computeUInt64Size(13, var53);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.monster)) {
         List var54 = var1.monster;
         var2 += CodedConstant.computeListSize(14, var54, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.pobs)) {
         List var55 = var1.pobs;
         var2 += CodedConstant.computeListSize(15, var55, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.guardiandeal)) {
         Long var56 = var1.guardiandeal;
         var2 += CodedOutputStream.computeInt64Size(16, var56);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.guardianhp)) {
         Long var57 = var1.guardianhp;
         var2 += CodedOutputStream.computeInt64Size(17, var57);
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.guardiandeallist)) {
         List var58 = var1.guardiandeallist;
         var2 += CodedConstant.computeListSize(18, var58, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.gghp)) {
         Integer var59 = var1.gghp;
         var2 += CodedOutputStream.computeInt32Size(19, var59);
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.gglist)) {
         List var60 = var1.gglist;
         var2 += CodedConstant.computeListSize(20, var60, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.wagondeal)) {
         Long var61 = var1.wagondeal;
         var2 += CodedOutputStream.computeInt64Size(21, var61);
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.wagonhp)) {
         Long var62 = var1.wagonhp;
         var2 += CodedOutputStream.computeInt64Size(22, var62);
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.woundedcount)) {
         Integer var63 = var1.woundedcount;
         var2 += CodedOutputStream.computeInt32Size(23, var63);
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.damage)) {
         Long var64 = var1.damage;
         var2 += CodedOutputStream.computeInt64Size(24, var64);
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.behit)) {
         Integer var65 = var1.behit;
         var2 += CodedOutputStream.computeInt32Size(25, var65);
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.leaveusers)) {
         List var66 = var1.leaveusers;
         var2 += CodedConstant.computeListSize(26, var66, FieldType.UINT64, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.pvpbattleinfo)) {
         PT_PVP_BATTLE_INFO var67 = var1.pvpbattleinfo;
         var2 += CodedConstant.computeSize(27, var67, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.pveroundinfo)) {
         PT_PVE_ROUND_INFO var68 = var1.pveroundinfo;
         var2 += CodedConstant.computeSize(28, var68, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var31 = null;
      if (!CodedConstant.isNull(var1.gamesafedata)) {
         String var69 = var1.gamesafedata;
         var2 += CodedOutputStream.computeStringSize(29, var69);
      }

      Object var32 = null;
      if (!CodedConstant.isNull(var1.raidroundinfos)) {
         List var70 = var1.raidroundinfos;
         var2 += CodedConstant.computeListSize(30, var70, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var33 = null;
      if (!CodedConstant.isNull(var1.gloryinfo)) {
         PT_PVP_GLORY_RESULT var71 = var1.gloryinfo;
         var2 += CodedConstant.computeSize(31, var71, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var34 = null;
      if (!CodedConstant.isNull(var1.actioncountinfo)) {
         PT_ACTION_COUNT_INFO var72 = var1.actioncountinfo;
         var2 += CodedConstant.computeSize(32, var72, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var35 = null;
      if (!CodedConstant.isNull(var1.garrisonhprate)) {
         Integer var73 = var1.garrisonhprate;
         var2 += CodedOutputStream.computeInt32Size(33, var73);
      }

      Object var36 = null;
      if (!CodedConstant.isNull(var1.garrisonplaytime)) {
         Integer var74 = var1.garrisonplaytime;
         var2 += CodedOutputStream.computeInt32Size(34, var74);
      }

      Object var37 = null;
      if (!CodedConstant.isNull(var1.batteleaguecontribution)) {
         Integer var75 = var1.batteleaguecontribution;
         var2 += CodedOutputStream.computeInt32Size(35, var75);
      }

      Object var38 = null;
      if (!CodedConstant.isNull(var1.bookconditionindexes)) {
         List var76 = var1.bookconditionindexes;
         var2 += CodedConstant.computeListSize(36, var76, FieldType.INT32, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var39 = null;
      if (!CodedConstant.isNull(var1.isfailedtimecondition)) {
         Boolean var77 = var1.isfailedtimecondition;
         var2 += CodedOutputStream.computeBoolSize(37, var77);
      }

      Object var40 = null;
      if (!CodedConstant.isNull(var1.isbanrematch)) {
         Boolean var78 = var1.isbanrematch;
         var2 += CodedOutputStream.computeBoolSize(38, var78);
      }

      return var2;
   }

   public void doWriteTo(REQ_DUNGEON_RESULT var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.damagehistory)) {
         byte[] var41 = var1.damagehistory;
         if (var41 != null) {
            var2.writeByteArray(1, var41);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.authkey)) {
         String var42 = var1.authkey;
         if (var42 != null) {
            var2.writeString(2, var42);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var43 = var1.dungeonguid;
         if (var43 != null) {
            var2.writeUInt64(3, var43);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.stage)) {
         Integer var44 = var1.stage;
         if (var44 != null) {
            var2.writeInt32(4, var44);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.qindex)) {
         List var45 = var1.qindex;
         if (var45 != null) {
            CodedConstant.writeToList(var2, 5, FieldType.OBJECT, var45, false);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.rankscore)) {
         Integer var46 = var1.rankscore;
         if (var46 != null) {
            var2.writeInt32(6, var46);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.score)) {
         Integer var47 = var1.score;
         if (var47 != null) {
            var2.writeInt32(7, var47);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var48 = var1.type;
         if (var48 != null) {
            var2.writeInt32(8, var48);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.result)) {
         Integer var49 = var1.result;
         if (var49 != null) {
            var2.writeInt32(9, var49);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.useskill)) {
         List var50 = var1.useskill;
         if (var50 != null) {
            CodedConstant.writeToList(var2, 10, FieldType.OBJECT, var50, false);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.cleartime)) {
         Long var51 = var1.cleartime;
         if (var51 != null) {
            var2.writeUInt64(11, var51);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.time)) {
         String var52 = var1.time;
         if (var52 != null) {
            var2.writeString(12, var52);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.matchingguid)) {
         Long var53 = var1.matchingguid;
         if (var53 != null) {
            var2.writeUInt64(13, var53);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.monster)) {
         List var54 = var1.monster;
         if (var54 != null) {
            CodedConstant.writeToList(var2, 14, FieldType.OBJECT, var54, false);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.pobs)) {
         List var55 = var1.pobs;
         if (var55 != null) {
            CodedConstant.writeToList(var2, 15, FieldType.OBJECT, var55, false);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.guardiandeal)) {
         Long var56 = var1.guardiandeal;
         if (var56 != null) {
            var2.writeInt64(16, var56);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.guardianhp)) {
         Long var57 = var1.guardianhp;
         if (var57 != null) {
            var2.writeInt64(17, var57);
         }
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.guardiandeallist)) {
         List var58 = var1.guardiandeallist;
         if (var58 != null) {
            CodedConstant.writeToList(var2, 18, FieldType.OBJECT, var58, false);
         }
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.gghp)) {
         Integer var59 = var1.gghp;
         if (var59 != null) {
            var2.writeInt32(19, var59);
         }
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.gglist)) {
         List var60 = var1.gglist;
         if (var60 != null) {
            CodedConstant.writeToList(var2, 20, FieldType.OBJECT, var60, false);
         }
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.wagondeal)) {
         Long var61 = var1.wagondeal;
         if (var61 != null) {
            var2.writeInt64(21, var61);
         }
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.wagonhp)) {
         Long var62 = var1.wagonhp;
         if (var62 != null) {
            var2.writeInt64(22, var62);
         }
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.woundedcount)) {
         Integer var63 = var1.woundedcount;
         if (var63 != null) {
            var2.writeInt32(23, var63);
         }
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.damage)) {
         Long var64 = var1.damage;
         if (var64 != null) {
            var2.writeInt64(24, var64);
         }
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.behit)) {
         Integer var65 = var1.behit;
         if (var65 != null) {
            var2.writeInt32(25, var65);
         }
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.leaveusers)) {
         List var66 = var1.leaveusers;
         if (var66 != null) {
            CodedConstant.writeToList(var2, 26, FieldType.UINT64, var66, true);
         }
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.pvpbattleinfo)) {
         PT_PVP_BATTLE_INFO var67 = var1.pvpbattleinfo;
         if (var67 != null) {
            CodedConstant.writeObject(var2, 27, FieldType.OBJECT, var67, false);
         }
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.pveroundinfo)) {
         PT_PVE_ROUND_INFO var68 = var1.pveroundinfo;
         if (var68 != null) {
            CodedConstant.writeObject(var2, 28, FieldType.OBJECT, var68, false);
         }
      }

      Object var31 = null;
      if (!CodedConstant.isNull(var1.gamesafedata)) {
         String var69 = var1.gamesafedata;
         if (var69 != null) {
            var2.writeString(29, var69);
         }
      }

      Object var32 = null;
      if (!CodedConstant.isNull(var1.raidroundinfos)) {
         List var70 = var1.raidroundinfos;
         if (var70 != null) {
            CodedConstant.writeToList(var2, 30, FieldType.OBJECT, var70, false);
         }
      }

      Object var33 = null;
      if (!CodedConstant.isNull(var1.gloryinfo)) {
         PT_PVP_GLORY_RESULT var71 = var1.gloryinfo;
         if (var71 != null) {
            CodedConstant.writeObject(var2, 31, FieldType.OBJECT, var71, false);
         }
      }

      Object var34 = null;
      if (!CodedConstant.isNull(var1.actioncountinfo)) {
         PT_ACTION_COUNT_INFO var72 = var1.actioncountinfo;
         if (var72 != null) {
            CodedConstant.writeObject(var2, 32, FieldType.OBJECT, var72, false);
         }
      }

      Object var35 = null;
      if (!CodedConstant.isNull(var1.garrisonhprate)) {
         Integer var73 = var1.garrisonhprate;
         if (var73 != null) {
            var2.writeInt32(33, var73);
         }
      }

      Object var36 = null;
      if (!CodedConstant.isNull(var1.garrisonplaytime)) {
         Integer var74 = var1.garrisonplaytime;
         if (var74 != null) {
            var2.writeInt32(34, var74);
         }
      }

      Object var37 = null;
      if (!CodedConstant.isNull(var1.batteleaguecontribution)) {
         Integer var75 = var1.batteleaguecontribution;
         if (var75 != null) {
            var2.writeInt32(35, var75);
         }
      }

      Object var38 = null;
      if (!CodedConstant.isNull(var1.bookconditionindexes)) {
         List var76 = var1.bookconditionindexes;
         if (var76 != null) {
            CodedConstant.writeToList(var2, 36, FieldType.INT32, var76, true);
         }
      }

      Object var39 = null;
      if (!CodedConstant.isNull(var1.isfailedtimecondition)) {
         Boolean var77 = var1.isfailedtimecondition;
         if (var77 != null) {
            var2.writeBool(37, var77);
         }
      }

      Object var40 = null;
      if (!CodedConstant.isNull(var1.isbanrematch)) {
         Boolean var78 = var1.isbanrematch;
         if (var78 != null) {
            var2.writeBool(38, var78);
         }
      }

   }

   public void writeTo(REQ_DUNGEON_RESULT var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_DUNGEON_RESULT readFrom(CodedInputStream var1) throws IOException {
      REQ_DUNGEON_RESULT var2 = new REQ_DUNGEON_RESULT();
      var2.qindex = new ArrayList();
      var2.useskill = new ArrayList();
      var2.monster = new ArrayList();
      var2.pobs = new ArrayList();
      var2.guardiandeallist = new ArrayList();
      var2.gglist = new ArrayList();
      var2.leaveusers = new ArrayList();
      var2.raidroundinfos = new ArrayList();
      var2.bookconditionindexes = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 10) {
               var2.damagehistory = var1.readBytes().toByteArray();
            } else if (var5 == 18) {
               var2.authkey = var1.readString();
            } else if (var5 == 24) {
               var2.dungeonguid = var1.readUInt64();
            } else if (var5 == 32) {
               var2.stage = var1.readInt32();
            } else if (var5 == 42) {
               Codec var20 = ProtobufProxy.create(PT_DUNGEON_RESULT_QUEST_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var33 = var1.readRawVarint32();
               int var46 = var1.pushLimit(var33);
               if (var2.qindex == null) {
                  var2.qindex = new ArrayList();
               }

               var2.qindex.add((PT_DUNGEON_RESULT_QUEST_INFO)var20.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var46);
            } else if (var5 == 48) {
               var2.rankscore = var1.readInt32();
            } else if (var5 == 56) {
               var2.score = var1.readInt32();
            } else if (var5 == 64) {
               var2.type = var1.readInt32();
            } else if (var5 == 72) {
               var2.result = var1.readInt32();
            } else if (var5 == 82) {
               Codec var19 = ProtobufProxy.create(PT_SKILL_USE_COUNT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var32 = var1.readRawVarint32();
               int var45 = var1.pushLimit(var32);
               if (var2.useskill == null) {
                  var2.useskill = new ArrayList();
               }

               var2.useskill.add((PT_SKILL_USE_COUNT)var19.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var45);
            } else if (var5 == 88) {
               var2.cleartime = var1.readUInt64();
            } else if (var5 == 98) {
               var2.time = var1.readString();
            } else if (var5 == 104) {
               var2.matchingguid = var1.readUInt64();
            } else if (var5 == 114) {
               Codec var18 = ProtobufProxy.create(PT_MONSTER.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var31 = var1.readRawVarint32();
               int var44 = var1.pushLimit(var31);
               if (var2.monster == null) {
                  var2.monster = new ArrayList();
               }

               var2.monster.add((PT_MONSTER)var18.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var44);
            } else if (var5 == 122) {
               Codec var17 = ProtobufProxy.create(PT_PASSIVE_OBJECT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var30 = var1.readRawVarint32();
               int var43 = var1.pushLimit(var30);
               if (var2.pobs == null) {
                  var2.pobs = new ArrayList();
               }

               var2.pobs.add((PT_PASSIVE_OBJECT)var17.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var43);
            } else if (var5 == 128) {
               var2.guardiandeal = var1.readInt64();
            } else if (var5 == 136) {
               var2.guardianhp = var1.readInt64();
            } else if (var5 == 146) {
               Codec var16 = ProtobufProxy.create(PT_GUARDIAN_DEAL.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var29 = var1.readRawVarint32();
               int var42 = var1.pushLimit(var29);
               if (var2.guardiandeallist == null) {
                  var2.guardiandeallist = new ArrayList();
               }

               var2.guardiandeallist.add((PT_GUARDIAN_DEAL)var16.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var42);
            } else if (var5 == 152) {
               var2.gghp = var1.readInt32();
            } else if (var5 == 162) {
               Codec var15 = ProtobufProxy.create(PT_GOLD_GOBLIN_HP.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var28 = var1.readRawVarint32();
               int var41 = var1.pushLimit(var28);
               if (var2.gglist == null) {
                  var2.gglist = new ArrayList();
               }

               var2.gglist.add((PT_GOLD_GOBLIN_HP)var15.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var41);
            } else if (var5 == 168) {
               var2.wagondeal = var1.readInt64();
            } else if (var5 == 176) {
               var2.wagonhp = var1.readInt64();
            } else if (var5 == 184) {
               var2.woundedcount = var1.readInt32();
            } else if (var5 == 192) {
               var2.damage = var1.readInt64();
            } else if (var5 == 200) {
               var2.behit = var1.readInt32();
            } else if (var5 == 208) {
               if (var2.leaveusers == null) {
                  var2.leaveusers = new ArrayList();
               }

               var2.leaveusers.add(var1.readUInt64());
            } else if (var5 == 210) {
               int var27 = var1.readRawVarint32();
               int var40 = var1.pushLimit(var27);
               if (var2.leaveusers == null) {
                  var2.leaveusers = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.leaveusers.add(var1.readUInt64());
               }

               var1.popLimit(var40);
            } else if (var5 == 218) {
               if (var5 != 210) {
                  Codec var14 = ProtobufProxy.create(PT_PVP_BATTLE_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
                  int var26 = var1.readRawVarint32();
                  int var39 = var1.pushLimit(var26);
                  var2.pvpbattleinfo = (PT_PVP_BATTLE_INFO)var14.readFrom(var1);
                  var1.checkLastTagWas(0);
                  var1.popLimit(var39);
               } else {
                  int var25 = var1.readRawVarint32();
                  int var38 = var1.pushLimit(var25);
                  if (var2.leaveusers == null) {
                     var2.leaveusers = new ArrayList();
                  }

                  while(var1.getBytesUntilLimit() > 0) {
                     var2.leaveusers.add(var1.readUInt64());
                  }

                  var1.popLimit(var38);
               }
            } else if (var5 == 226) {
               Codec var13 = ProtobufProxy.create(PT_PVE_ROUND_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var24 = var1.readRawVarint32();
               int var37 = var1.pushLimit(var24);
               var2.pveroundinfo = (PT_PVE_ROUND_INFO)var13.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var37);
            } else if (var5 == 234) {
               var2.gamesafedata = var1.readString();
            } else if (var5 == 242) {
               Codec var12 = ProtobufProxy.create(PT_RAID_ROUND_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var23 = var1.readRawVarint32();
               int var36 = var1.pushLimit(var23);
               if (var2.raidroundinfos == null) {
                  var2.raidroundinfos = new ArrayList();
               }

               var2.raidroundinfos.add((PT_RAID_ROUND_INFO)var12.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var36);
            } else if (var5 == 250) {
               Codec var11 = ProtobufProxy.create(PT_PVP_GLORY_RESULT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var22 = var1.readRawVarint32();
               int var35 = var1.pushLimit(var22);
               var2.gloryinfo = (PT_PVP_GLORY_RESULT)var11.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var35);
            } else if (var5 == 258) {
               Codec var10 = ProtobufProxy.create(PT_ACTION_COUNT_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var21 = var1.readRawVarint32();
               int var34 = var1.pushLimit(var21);
               var2.actioncountinfo = (PT_ACTION_COUNT_INFO)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var34);
            } else if (var5 == 264) {
               var2.garrisonhprate = var1.readInt32();
            } else if (var5 == 272) {
               var2.garrisonplaytime = var1.readInt32();
            } else if (var5 == 280) {
               var2.batteleaguecontribution = var1.readInt32();
            } else if (var5 == 288) {
               if (var2.bookconditionindexes == null) {
                  var2.bookconditionindexes = new ArrayList();
               }

               var2.bookconditionindexes.add(var1.readInt32());
            } else if (var5 != 290) {
               if (var5 == 296) {
                  var2.isfailedtimecondition = var1.readBool();
               } else if (var5 == 304) {
                  var2.isbanrematch = var1.readBool();
               } else {
                  var1.skipField(var5);
               }
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.bookconditionindexes == null) {
                  var2.bookconditionindexes = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.bookconditionindexes.add(var1.readInt32());
               }

               var1.popLimit(var7);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_DUNGEON_RESULT.class);
         return this.descriptor = var1;
      }
   }
}
