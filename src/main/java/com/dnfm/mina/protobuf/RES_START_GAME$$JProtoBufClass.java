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

public class RES_START_GAME$$JProtoBufClass implements Codec<RES_START_GAME>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_START_GAME var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_START_GAME decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_START_GAME var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var54 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var54);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.growchangecount)) {
         Integer var55 = var1.growchangecount;
         var2 += CodedOutputStream.computeInt32Size(2, var55);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var56 = var1.world;
         var2 += CodedOutputStream.computeInt32Size(3, var56);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.currentworld)) {
         Integer var57 = var1.currentworld;
         var2 += CodedOutputStream.computeInt32Size(4, var57);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.town)) {
         Integer var58 = var1.town;
         var2 += CodedOutputStream.computeUInt32Size(5, var58);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.area)) {
         Integer var59 = var1.area;
         var2 += CodedOutputStream.computeUInt32Size(6, var59);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var60 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(7, var60);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.exp)) {
         Integer var61 = var1.exp;
         var2 += CodedOutputStream.computeUInt32Size(8, var61);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.fatigue)) {
         Integer var62 = var1.fatigue;
         var2 += CodedOutputStream.computeInt32Size(9, var62);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.gguid)) {
         Long var63 = var1.gguid;
         var2 += CodedOutputStream.computeUInt64Size(10, var63);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.masterguid)) {
         Long var64 = var1.masterguid;
         var2 += CodedOutputStream.computeUInt64Size(11, var64);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.ismaster)) {
         Integer var65 = var1.ismaster;
         var2 += CodedOutputStream.computeInt32Size(12, var65);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.cera)) {
         Integer var66 = var1.cera;
         var2 += CodedOutputStream.computeInt32Size(13, var66);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.goldcoin)) {
         Integer var67 = var1.goldcoin;
         var2 += CodedOutputStream.computeInt32Size(14, var67);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.helpercount)) {
         Integer var68 = var1.helpercount;
         var2 += CodedOutputStream.computeInt32Size(15, var68);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.guide)) {
         Integer var69 = var1.guide;
         var2 += CodedOutputStream.computeInt32Size(16, var69);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.groupjoined)) {
         Integer var70 = var1.groupjoined;
         var2 += CodedOutputStream.computeInt32Size(17, var70);
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.lupgrade)) {
         Integer var71 = var1.lupgrade;
         var2 += CodedOutputStream.computeInt32Size(18, var71);
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.lamplify)) {
         Integer var72 = var1.lamplify;
         var2 += CodedOutputStream.computeInt32Size(19, var72);
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.bufflist)) {
         List var73 = var1.bufflist;
         var2 += CodedConstant.computeListSize(20, var73, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var74 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(21, var74);
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.endtime)) {
         Long var75 = var1.endtime;
         var2 += CodedOutputStream.computeInt64Size(22, var75);
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.fatiguetime)) {
         Long var76 = var1.fatiguetime;
         var2 += CodedOutputStream.computeInt64Size(23, var76);
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.recoveryfatiguetime)) {
         Long var77 = var1.recoveryfatiguetime;
         var2 += CodedOutputStream.computeInt64Size(24, var77);
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.partydisturb)) {
         Integer var78 = var1.partydisturb;
         var2 += CodedOutputStream.computeInt32Size(25, var78);
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.battletutorial)) {
         Integer var79 = var1.battletutorial;
         var2 += CodedOutputStream.computeInt32Size(26, var79);
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.gname)) {
         String var80 = var1.gname;
         var2 += CodedOutputStream.computeStringSize(27, var80);
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.qindex)) {
         Integer var81 = var1.qindex;
         var2 += CodedOutputStream.computeInt32Size(28, var81);
      }

      Object var31 = null;
      if (!CodedConstant.isNull(var1.areaId)) {
         Integer var82 = var1.areaId;
         var2 += CodedOutputStream.computeInt32Size(29, var82);
      }

      Object var32 = null;
      if (!CodedConstant.isNull(var1.expratio)) {
         Integer var83 = var1.expratio;
         var2 += CodedOutputStream.computeInt32Size(30, var83);
      }

      Object var33 = null;
      if (!CodedConstant.isNull(var1.fatigueratio)) {
         Integer var84 = var1.fatigueratio;
         var2 += CodedOutputStream.computeInt32Size(31, var84);
      }

      Object var34 = null;
      if (!CodedConstant.isNull(var1.chatchnidx)) {
         Integer var85 = var1.chatchnidx;
         var2 += CodedOutputStream.computeInt32Size(32, var85);
      }

      Object var35 = null;
      if (!CodedConstant.isNull(var1.createcount)) {
         Long var86 = var1.createcount;
         var2 += CodedOutputStream.computeInt64Size(33, var86);
      }

      Object var36 = null;
      if (!CodedConstant.isNull(var1.lightsig)) {
         PT_TSSLIGHTSIG_LIST var87 = var1.lightsig;
         var2 += CodedConstant.computeSize(34, var87, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var37 = null;
      if (!CodedConstant.isNull(var1.queststate)) {
         Integer var88 = var1.queststate;
         var2 += CodedOutputStream.computeInt32Size(35, var88);
      }

      Object var38 = null;
      if (!CodedConstant.isNull(var1.returnUserInfo)) {
         PT_RETURN_USER_INFO var89 = var1.returnUserInfo;
         var2 += CodedConstant.computeSize(36, var89, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var39 = null;
      if (!CodedConstant.isNull(var1.battlePassInfo)) {
         PT_BATTLE_PASS_INFO var90 = var1.battlePassInfo;
         var2 += CodedConstant.computeSize(37, var90, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var40 = null;
      if (!CodedConstant.isNull(var1.pvpBattlePassInfo)) {
         PT_BATTLE_PASS_INFO var91 = var1.pvpBattlePassInfo;
         var2 += CodedConstant.computeSize(38, var91, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var41 = null;
      if (!CodedConstant.isNull(var1.iosreviewsystem)) {
         Boolean var92 = var1.iosreviewsystem;
         var2 += CodedOutputStream.computeBoolSize(39, var92);
      }

      Object var42 = null;
      if (!CodedConstant.isNull(var1.mt6)) {
         Integer var93 = var1.mt6;
         var2 += CodedOutputStream.computeInt32Size(40, var93);
      }

      Object var43 = null;
      if (!CodedConstant.isNull(var1.intervaltowncharacterinfoms)) {
         Integer var94 = var1.intervaltowncharacterinfoms;
         var2 += CodedOutputStream.computeInt32Size(41, var94);
      }

      Object var44 = null;
      if (!CodedConstant.isNull(var1.needinitskill)) {
         Boolean var95 = var1.needinitskill;
         var2 += CodedOutputStream.computeBoolSize(42, var95);
      }

      Object var45 = null;
      if (!CodedConstant.isNull(var1.initskillversion)) {
         PT_INIT_SKILL var96 = var1.initskillversion;
         var2 += CodedConstant.computeSize(43, var96, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var46 = null;
      if (!CodedConstant.isNull(var1.towninterval)) {
         List var97 = var1.towninterval;
         var2 += CodedConstant.computeListSize(44, var97, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var47 = null;
      if (!CodedConstant.isNull(var1.maritalstatus)) {
         Integer var98 = var1.maritalstatus;
         var2 += CodedOutputStream.computeInt32Size(45, var98);
      }

      Object var48 = null;
      if (!CodedConstant.isNull(var1.adventurebookOpen)) {
         Boolean var99 = var1.adventurebookOpen;
         var2 += CodedOutputStream.computeBoolSize(46, var99);
      }

      Object var49 = null;
      if (!CodedConstant.isNull(var1.lastkicktime)) {
         Long var100 = var1.lastkicktime;
         var2 += CodedOutputStream.computeInt64Size(47, var100);
      }

      Object var50 = null;
      if (!CodedConstant.isNull(var1.switchstatus)) {
         ENUM_MESSAGE_ASSISTANT_SWITCH_STATUS.T var101 = var1.switchstatus;
         var2 += CodedOutputStream.computeEnumSize(48, ((ENUM_MESSAGE_ASSISTANT_SWITCH_STATUS.T)var101).value());
      }

      Object var51 = null;
      if (!CodedConstant.isNull(var1.svnrevision)) {
         Integer var102 = var1.svnrevision;
         var2 += CodedOutputStream.computeInt32Size(49, var102);
      }

      Object var52 = null;
      if (!CodedConstant.isNull(var1.gitrevision)) {
         String var103 = var1.gitrevision;
         var2 += CodedOutputStream.computeStringSize(50, var103);
      }

      Object var53 = null;
      if (!CodedConstant.isNull(var1.eventSelectInfo)) {
         PT_EVENT_SELECT_INFO var104 = var1.eventSelectInfo;
         var2 += CodedConstant.computeSize(51, var104, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      return var2;
   }

   public void doWriteTo(RES_START_GAME var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var54 = var1.error;
         if (var54 != null) {
            var2.writeInt32(1, var54);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.growchangecount)) {
         Integer var55 = var1.growchangecount;
         if (var55 != null) {
            var2.writeInt32(2, var55);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var56 = var1.world;
         if (var56 != null) {
            var2.writeInt32(3, var56);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.currentworld)) {
         Integer var57 = var1.currentworld;
         if (var57 != null) {
            var2.writeInt32(4, var57);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.town)) {
         Integer var58 = var1.town;
         if (var58 != null) {
            var2.writeUInt32(5, var58);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.area)) {
         Integer var59 = var1.area;
         if (var59 != null) {
            var2.writeUInt32(6, var59);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var60 = var1.level;
         if (var60 != null) {
            var2.writeInt32(7, var60);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.exp)) {
         Integer var61 = var1.exp;
         if (var61 != null) {
            var2.writeUInt32(8, var61);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.fatigue)) {
         Integer var62 = var1.fatigue;
         if (var62 != null) {
            var2.writeInt32(9, var62);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.gguid)) {
         Long var63 = var1.gguid;
         if (var63 != null) {
            var2.writeUInt64(10, var63);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.masterguid)) {
         Long var64 = var1.masterguid;
         if (var64 != null) {
            var2.writeUInt64(11, var64);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.ismaster)) {
         Integer var65 = var1.ismaster;
         if (var65 != null) {
            var2.writeInt32(12, var65);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.cera)) {
         Integer var66 = var1.cera;
         if (var66 != null) {
            var2.writeInt32(13, var66);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.goldcoin)) {
         Integer var67 = var1.goldcoin;
         if (var67 != null) {
            var2.writeInt32(14, var67);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.helpercount)) {
         Integer var68 = var1.helpercount;
         if (var68 != null) {
            var2.writeInt32(15, var68);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.guide)) {
         Integer var69 = var1.guide;
         if (var69 != null) {
            var2.writeInt32(16, var69);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.groupjoined)) {
         Integer var70 = var1.groupjoined;
         if (var70 != null) {
            var2.writeInt32(17, var70);
         }
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.lupgrade)) {
         Integer var71 = var1.lupgrade;
         if (var71 != null) {
            var2.writeInt32(18, var71);
         }
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.lamplify)) {
         Integer var72 = var1.lamplify;
         if (var72 != null) {
            var2.writeInt32(19, var72);
         }
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.bufflist)) {
         List var73 = var1.bufflist;
         if (var73 != null) {
            CodedConstant.writeToList(var2, 20, FieldType.OBJECT, var73, false);
         }
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var74 = var1.index;
         if (var74 != null) {
            var2.writeInt32(21, var74);
         }
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.endtime)) {
         Long var75 = var1.endtime;
         if (var75 != null) {
            var2.writeInt64(22, var75);
         }
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.fatiguetime)) {
         Long var76 = var1.fatiguetime;
         if (var76 != null) {
            var2.writeInt64(23, var76);
         }
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.recoveryfatiguetime)) {
         Long var77 = var1.recoveryfatiguetime;
         if (var77 != null) {
            var2.writeInt64(24, var77);
         }
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.partydisturb)) {
         Integer var78 = var1.partydisturb;
         if (var78 != null) {
            var2.writeInt32(25, var78);
         }
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.battletutorial)) {
         Integer var79 = var1.battletutorial;
         if (var79 != null) {
            var2.writeInt32(26, var79);
         }
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.gname)) {
         String var80 = var1.gname;
         if (var80 != null) {
            var2.writeString(27, var80);
         }
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.qindex)) {
         Integer var81 = var1.qindex;
         if (var81 != null) {
            var2.writeInt32(28, var81);
         }
      }

      Object var31 = null;
      if (!CodedConstant.isNull(var1.areaId)) {
         Integer var82 = var1.areaId;
         if (var82 != null) {
            var2.writeInt32(29, var82);
         }
      }

      Object var32 = null;
      if (!CodedConstant.isNull(var1.expratio)) {
         Integer var83 = var1.expratio;
         if (var83 != null) {
            var2.writeInt32(30, var83);
         }
      }

      Object var33 = null;
      if (!CodedConstant.isNull(var1.fatigueratio)) {
         Integer var84 = var1.fatigueratio;
         if (var84 != null) {
            var2.writeInt32(31, var84);
         }
      }

      Object var34 = null;
      if (!CodedConstant.isNull(var1.chatchnidx)) {
         Integer var85 = var1.chatchnidx;
         if (var85 != null) {
            var2.writeInt32(32, var85);
         }
      }

      Object var35 = null;
      if (!CodedConstant.isNull(var1.createcount)) {
         Long var86 = var1.createcount;
         if (var86 != null) {
            var2.writeInt64(33, var86);
         }
      }

      Object var36 = null;
      if (!CodedConstant.isNull(var1.lightsig)) {
         PT_TSSLIGHTSIG_LIST var87 = var1.lightsig;
         if (var87 != null) {
            CodedConstant.writeObject(var2, 34, FieldType.OBJECT, var87, false);
         }
      }

      Object var37 = null;
      if (!CodedConstant.isNull(var1.queststate)) {
         Integer var88 = var1.queststate;
         if (var88 != null) {
            var2.writeInt32(35, var88);
         }
      }

      Object var38 = null;
      if (!CodedConstant.isNull(var1.returnUserInfo)) {
         PT_RETURN_USER_INFO var89 = var1.returnUserInfo;
         if (var89 != null) {
            CodedConstant.writeObject(var2, 36, FieldType.OBJECT, var89, false);
         }
      }

      Object var39 = null;
      if (!CodedConstant.isNull(var1.battlePassInfo)) {
         PT_BATTLE_PASS_INFO var90 = var1.battlePassInfo;
         if (var90 != null) {
            CodedConstant.writeObject(var2, 37, FieldType.OBJECT, var90, false);
         }
      }

      Object var40 = null;
      if (!CodedConstant.isNull(var1.pvpBattlePassInfo)) {
         PT_BATTLE_PASS_INFO var91 = var1.pvpBattlePassInfo;
         if (var91 != null) {
            CodedConstant.writeObject(var2, 38, FieldType.OBJECT, var91, false);
         }
      }

      Object var41 = null;
      if (!CodedConstant.isNull(var1.iosreviewsystem)) {
         Boolean var92 = var1.iosreviewsystem;
         if (var92 != null) {
            var2.writeBool(39, var92);
         }
      }

      Object var42 = null;
      if (!CodedConstant.isNull(var1.mt6)) {
         Integer var93 = var1.mt6;
         if (var93 != null) {
            var2.writeInt32(40, var93);
         }
      }

      Object var43 = null;
      if (!CodedConstant.isNull(var1.intervaltowncharacterinfoms)) {
         Integer var94 = var1.intervaltowncharacterinfoms;
         if (var94 != null) {
            var2.writeInt32(41, var94);
         }
      }

      Object var44 = null;
      if (!CodedConstant.isNull(var1.needinitskill)) {
         Boolean var95 = var1.needinitskill;
         if (var95 != null) {
            var2.writeBool(42, var95);
         }
      }

      Object var45 = null;
      if (!CodedConstant.isNull(var1.initskillversion)) {
         PT_INIT_SKILL var96 = var1.initskillversion;
         if (var96 != null) {
            CodedConstant.writeObject(var2, 43, FieldType.OBJECT, var96, false);
         }
      }

      Object var46 = null;
      if (!CodedConstant.isNull(var1.towninterval)) {
         List var97 = var1.towninterval;
         if (var97 != null) {
            CodedConstant.writeToList(var2, 44, FieldType.OBJECT, var97, false);
         }
      }

      Object var47 = null;
      if (!CodedConstant.isNull(var1.maritalstatus)) {
         Integer var98 = var1.maritalstatus;
         if (var98 != null) {
            var2.writeInt32(45, var98);
         }
      }

      Object var48 = null;
      if (!CodedConstant.isNull(var1.adventurebookOpen)) {
         Boolean var99 = var1.adventurebookOpen;
         if (var99 != null) {
            var2.writeBool(46, var99);
         }
      }

      Object var49 = null;
      if (!CodedConstant.isNull(var1.lastkicktime)) {
         Long var100 = var1.lastkicktime;
         if (var100 != null) {
            var2.writeInt64(47, var100);
         }
      }

      Object var50 = null;
      if (!CodedConstant.isNull(var1.switchstatus)) {
         ENUM_MESSAGE_ASSISTANT_SWITCH_STATUS.T var101 = var1.switchstatus;
         if (var101 != null) {
            var2.writeEnum(48, ((ENUM_MESSAGE_ASSISTANT_SWITCH_STATUS.T)var101).value());
         }
      }

      Object var51 = null;
      if (!CodedConstant.isNull(var1.svnrevision)) {
         Integer var102 = var1.svnrevision;
         if (var102 != null) {
            var2.writeInt32(49, var102);
         }
      }

      Object var52 = null;
      if (!CodedConstant.isNull(var1.gitrevision)) {
         String var103 = var1.gitrevision;
         if (var103 != null) {
            var2.writeString(50, var103);
         }
      }

      Object var53 = null;
      if (!CodedConstant.isNull(var1.eventSelectInfo)) {
         PT_EVENT_SELECT_INFO var104 = var1.eventSelectInfo;
         if (var104 != null) {
            CodedConstant.writeObject(var2, 51, FieldType.OBJECT, var104, false);
         }
      }

   }

   public void writeTo(RES_START_GAME var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_START_GAME readFrom(CodedInputStream var1) throws IOException {
      RES_START_GAME var2 = new RES_START_GAME();
      var2.bufflist = new ArrayList();
      var2.towninterval = new ArrayList();
      var2.switchstatus = (ENUM_MESSAGE_ASSISTANT_SWITCH_STATUS.T)CodedConstant.getEnumValue(ENUM_MESSAGE_ASSISTANT_SWITCH_STATUS.T.class, ENUM_MESSAGE_ASSISTANT_SWITCH_STATUS.T.values()[0].name());

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
               var2.growchangecount = var1.readInt32();
            } else if (var5 == 24) {
               var2.world = var1.readInt32();
            } else if (var5 == 32) {
               var2.currentworld = var1.readInt32();
            } else if (var5 == 40) {
               var2.town = var1.readUInt32();
            } else if (var5 == 48) {
               var2.area = var1.readUInt32();
            } else if (var5 == 56) {
               var2.level = var1.readInt32();
            } else if (var5 == 64) {
               var2.exp = var1.readUInt32();
            } else if (var5 == 72) {
               var2.fatigue = var1.readInt32();
            } else if (var5 == 80) {
               var2.gguid = var1.readUInt64();
            } else if (var5 == 88) {
               var2.masterguid = var1.readUInt64();
            } else if (var5 == 96) {
               var2.ismaster = var1.readInt32();
            } else if (var5 == 104) {
               var2.cera = var1.readInt32();
            } else if (var5 == 112) {
               var2.goldcoin = var1.readInt32();
            } else if (var5 == 120) {
               var2.helpercount = var1.readInt32();
            } else if (var5 == 128) {
               var2.guide = var1.readInt32();
            } else if (var5 == 136) {
               var2.groupjoined = var1.readInt32();
            } else if (var5 == 144) {
               var2.lupgrade = var1.readInt32();
            } else if (var5 == 152) {
               var2.lamplify = var1.readInt32();
            } else if (var5 == 162) {
               Codec var10 = ProtobufProxy.create(PT_DINING_FOOD_BUFF_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.bufflist == null) {
                  var2.bufflist = new ArrayList();
               }

               var2.bufflist.add((PT_DINING_FOOD_BUFF_INFO)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 168) {
               var2.index = var1.readInt32();
            } else if (var5 == 176) {
               var2.endtime = var1.readInt64();
            } else if (var5 == 184) {
               var2.fatiguetime = var1.readInt64();
            } else if (var5 == 192) {
               var2.recoveryfatiguetime = var1.readInt64();
            } else if (var5 == 200) {
               var2.partydisturb = var1.readInt32();
            } else if (var5 == 208) {
               var2.battletutorial = var1.readInt32();
            } else if (var5 == 218) {
               var2.gname = var1.readString();
            } else if (var5 == 224) {
               var2.qindex = var1.readInt32();
            } else if (var5 == 232) {
               var2.areaId = var1.readInt32();
            } else if (var5 == 240) {
               var2.expratio = var1.readInt32();
            } else if (var5 == 248) {
               var2.fatigueratio = var1.readInt32();
            } else if (var5 == 256) {
               var2.chatchnidx = var1.readInt32();
            } else if (var5 == 264) {
               var2.createcount = var1.readInt64();
            } else if (var5 == 274) {
               Codec var11 = ProtobufProxy.create(PT_TSSLIGHTSIG_LIST.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var18 = var1.readRawVarint32();
               int var25 = var1.pushLimit(var18);
               var2.lightsig = (PT_TSSLIGHTSIG_LIST)var11.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var25);
            } else if (var5 == 280) {
               var2.queststate = var1.readInt32();
            } else if (var5 == 290) {
               Codec var12 = ProtobufProxy.create(PT_RETURN_USER_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var19 = var1.readRawVarint32();
               int var26 = var1.pushLimit(var19);
               var2.returnUserInfo = (PT_RETURN_USER_INFO)var12.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var26);
            } else if (var5 == 298) {
               Codec var13 = ProtobufProxy.create(PT_BATTLE_PASS_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var20 = var1.readRawVarint32();
               int var27 = var1.pushLimit(var20);
               var2.battlePassInfo = (PT_BATTLE_PASS_INFO)var13.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var27);
            } else if (var5 == 306) {
               Codec var14 = ProtobufProxy.create(PT_BATTLE_PASS_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var21 = var1.readRawVarint32();
               int var28 = var1.pushLimit(var21);
               var2.pvpBattlePassInfo = (PT_BATTLE_PASS_INFO)var14.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var28);
            } else if (var5 == 312) {
               var2.iosreviewsystem = var1.readBool();
            } else if (var5 == 320) {
               var2.mt6 = var1.readInt32();
            } else if (var5 == 328) {
               var2.intervaltowncharacterinfoms = var1.readInt32();
            } else if (var5 == 336) {
               var2.needinitskill = var1.readBool();
            } else if (var5 == 346) {
               Codec var15 = ProtobufProxy.create(PT_INIT_SKILL.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var22 = var1.readRawVarint32();
               int var29 = var1.pushLimit(var22);
               var2.initskillversion = (PT_INIT_SKILL)var15.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var29);
            } else if (var5 == 354) {
               Codec var16 = ProtobufProxy.create(PT_TOWN_INTERVAL.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var23 = var1.readRawVarint32();
               int var30 = var1.pushLimit(var23);
               if (var2.towninterval == null) {
                  var2.towninterval = new ArrayList();
               }

               var2.towninterval.add((PT_TOWN_INTERVAL)var16.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var30);
            } else if (var5 == 360) {
               var2.maritalstatus = var1.readInt32();
            } else if (var5 == 368) {
               var2.adventurebookOpen = var1.readBool();
            } else if (var5 == 376) {
               var2.lastkicktime = var1.readInt64();
            } else if (var5 == 384) {
               var2.switchstatus = (ENUM_MESSAGE_ASSISTANT_SWITCH_STATUS.T)CodedConstant.getEnumValue(ENUM_MESSAGE_ASSISTANT_SWITCH_STATUS.T.class, CodedConstant.getEnumName(ENUM_MESSAGE_ASSISTANT_SWITCH_STATUS.T.values(), var1.readEnum()));
            } else if (var5 == 392) {
               var2.svnrevision = var1.readInt32();
            } else if (var5 == 402) {
               var2.gitrevision = var1.readString();
            } else if (var5 == 410) {
               Codec var17 = ProtobufProxy.create(PT_EVENT_SELECT_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var24 = var1.readRawVarint32();
               int var31 = var1.pushLimit(var24);
               var2.eventSelectInfo = (PT_EVENT_SELECT_INFO)var17.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var31);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_START_GAME.class);
         return this.descriptor = var1;
      }
   }
}
