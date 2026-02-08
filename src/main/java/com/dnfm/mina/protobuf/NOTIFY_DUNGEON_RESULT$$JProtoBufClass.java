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

public class NOTIFY_DUNGEON_RESULT$$JProtoBufClass implements Codec<NOTIFY_DUNGEON_RESULT>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(NOTIFY_DUNGEON_RESULT var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public NOTIFY_DUNGEON_RESULT decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(NOTIFY_DUNGEON_RESULT var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var74 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var74);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var75 = var1.dungeonindex;
         var2 += CodedOutputStream.computeInt32Size(2, var75);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.matchtype)) {
         Integer var76 = var1.matchtype;
         var2 += CodedOutputStream.computeInt32Size(3, var76);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.exp)) {
         Integer var77 = var1.exp;
         var2 += CodedOutputStream.computeInt32Size(4, var77);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var78 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(5, var78);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var79 = var1.count;
         var2 += CodedOutputStream.computeInt32Size(6, var79);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.time)) {
         Long var80 = var1.time;
         var2 += CodedOutputStream.computeInt64Size(7, var80);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.point)) {
         Integer var81 = var1.point;
         var2 += CodedOutputStream.computeInt32Size(8, var81);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var82 = var1.grade;
         var2 += CodedOutputStream.computeInt32Size(9, var82);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.gage)) {
         Integer var83 = var1.gage;
         var2 += CodedOutputStream.computeInt32Size(10, var83);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.durability)) {
         Integer var84 = var1.durability;
         var2 += CodedOutputStream.computeInt32Size(11, var84);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.clearexp)) {
         PT_EXP_DETAILINFO var85 = var1.clearexp;
         var2 += CodedConstant.computeSize(12, var85, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.escortreward)) {
         PT_ESCORT_REWARD var86 = var1.escortreward;
         var2 += CodedConstant.computeSize(13, var86, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.starrewards)) {
         List var87 = var1.starrewards;
         var2 += CodedConstant.computeListSize(14, var87, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.totalclearreward)) {
         PT_ADVENTURE_CLEAR_REWARD var88 = var1.totalclearreward;
         var2 += CodedConstant.computeSize(15, var88, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.battleworld)) {
         Integer var89 = var1.battleworld;
         var2 += CodedOutputStream.computeInt32Size(16, var89);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.bchannel)) {
         Integer var90 = var1.bchannel;
         var2 += CodedOutputStream.computeInt32Size(17, var90);
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.bip)) {
         String var91 = var1.bip;
         var2 += CodedOutputStream.computeStringSize(18, var91);
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.bport)) {
         Integer var92 = var1.bport;
         var2 += CodedOutputStream.computeInt32Size(19, var92);
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.authkey)) {
         String var93 = var1.authkey;
         var2 += CodedOutputStream.computeStringSize(20, var93);
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var94 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(21, var94);
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.tick)) {
         Long var95 = var1.tick;
         var2 += CodedOutputStream.computeUInt64Size(22, var95);
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.mileage)) {
         Integer var96 = var1.mileage;
         var2 += CodedOutputStream.computeInt32Size(23, var96);
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.firstclearreward)) {
         PT_TOWER_REWARD_ITEMS var97 = var1.firstclearreward;
         var2 += CodedConstant.computeSize(24, var97, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.betterclearreward)) {
         PT_TOWER_REWARD_ITEMS var98 = var1.betterclearreward;
         var2 += CodedConstant.computeSize(25, var98, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.mileagereward)) {
         PT_TOWER_REWARD_ITEMS var99 = var1.mileagereward;
         var2 += CodedConstant.computeSize(26, var99, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.timecutclearreward)) {
         PT_TOWER_REWARD_ITEMS var100 = var1.timecutclearreward;
         var2 += CodedConstant.computeSize(27, var100, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.stageclearreward)) {
         PT_TOWER_REWARD_ITEMS var101 = var1.stageclearreward;
         var2 += CodedConstant.computeSize(28, var101, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var31 = null;
      if (!CodedConstant.isNull(var1.worldraidreward)) {
         PT_WORLDRADE_REWARD var102 = var1.worldraidreward;
         var2 += CodedConstant.computeSize(29, var102, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var32 = null;
      if (!CodedConstant.isNull(var1.closenessreward)) {
         List var103 = var1.closenessreward;
         var2 += CodedConstant.computeListSize(30, var103, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var33 = null;
      if (!CodedConstant.isNull(var1.arcadereward)) {
         List var104 = var1.arcadereward;
         var2 += CodedConstant.computeListSize(31, var104, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var34 = null;
      if (!CodedConstant.isNull(var1.performancerating)) {
         Integer var105 = var1.performancerating;
         var2 += CodedOutputStream.computeInt32Size(32, var105);
      }

      Object var35 = null;
      if (!CodedConstant.isNull(var1.result)) {
         Integer var106 = var1.result;
         var2 += CodedOutputStream.computeInt32Size(33, var106);
      }

      Object var36 = null;
      if (!CodedConstant.isNull(var1.pvpstarcount)) {
         Integer var107 = var1.pvpstarcount;
         var2 += CodedOutputStream.computeInt32Size(34, var107);
      }

      Object var37 = null;
      if (!CodedConstant.isNull(var1.pvpstargrade)) {
         Integer var108 = var1.pvpstargrade;
         var2 += CodedOutputStream.computeInt32Size(35, var108);
      }

      Object var38 = null;
      if (!CodedConstant.isNull(var1.orderlist)) {
         List var109 = var1.orderlist;
         var2 += CodedConstant.computeListSize(36, var109, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var39 = null;
      if (!CodedConstant.isNull(var1.guildauction)) {
         List var110 = var1.guildauction;
         var2 += CodedConstant.computeListSize(37, var110, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var40 = null;
      if (!CodedConstant.isNull(var1.deathmatchrecord)) {
         List var111 = var1.deathmatchrecord;
         var2 += CodedConstant.computeListSize(38, var111, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var41 = null;
      if (!CodedConstant.isNull(var1.deathmatchrecord1)) {
         List var112 = var1.deathmatchrecord1;
         var2 += CodedConstant.computeListSize(39, var112, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var42 = null;
      if (!CodedConstant.isNull(var1.deathmatchrecord2)) {
         List var113 = var1.deathmatchrecord2;
         var2 += CodedConstant.computeListSize(40, var113, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var43 = null;
      if (!CodedConstant.isNull(var1.deathrewards)) {
         List var114 = var1.deathrewards;
         var2 += CodedConstant.computeListSize(41, var114, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var44 = null;
      if (!CodedConstant.isNull(var1.deathrewards1)) {
         List var115 = var1.deathrewards1;
         var2 += CodedConstant.computeListSize(42, var115, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var45 = null;
      if (!CodedConstant.isNull(var1.deathrewards2)) {
         List var116 = var1.deathrewards2;
         var2 += CodedConstant.computeListSize(43, var116, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var46 = null;
      if (!CodedConstant.isNull(var1.winpoint)) {
         Integer var117 = var1.winpoint;
         var2 += CodedOutputStream.computeInt32Size(44, var117);
      }

      Object var47 = null;
      if (!CodedConstant.isNull(var1.record)) {
         List var118 = var1.record;
         var2 += CodedConstant.computeListSize(45, var118, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var48 = null;
      if (!CodedConstant.isNull(var1.currency)) {
         List var119 = var1.currency;
         var2 += CodedConstant.computeListSize(46, var119, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var49 = null;
      if (!CodedConstant.isNull(var1.adventureticket)) {
         List var120 = var1.adventureticket;
         var2 += CodedConstant.computeListSize(47, var120, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var50 = null;
      if (!CodedConstant.isNull(var1.fatigue)) {
         Integer var121 = var1.fatigue;
         var2 += CodedOutputStream.computeInt32Size(48, var121);
      }

      Object var51 = null;
      if (!CodedConstant.isNull(var1.consumefatigue)) {
         Integer var122 = var1.consumefatigue;
         var2 += CodedOutputStream.computeInt32Size(49, var122);
      }

      Object var52 = null;
      if (!CodedConstant.isNull(var1.rewards)) {
         List var123 = var1.rewards;
         var2 += CodedConstant.computeListSize(50, var123, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var53 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var124 = var1.dungeonguid;
         var2 += CodedOutputStream.computeUInt64Size(51, var124);
      }

      Object var54 = null;
      if (!CodedConstant.isNull(var1.ancientfirstclearcount)) {
         Long var125 = var1.ancientfirstclearcount;
         var2 += CodedOutputStream.computeUInt64Size(52, var125);
      }

      Object var55 = null;
      if (!CodedConstant.isNull(var1.list)) {
         List var126 = var1.list;
         var2 += CodedConstant.computeListSize(53, var126, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var56 = null;
      if (!CodedConstant.isNull(var1.gloryinfo)) {
         PT_PVP_GLORY_RESULT var127 = var1.gloryinfo;
         var2 += CodedConstant.computeSize(54, var127, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var57 = null;
      if (!CodedConstant.isNull(var1.upanddowngradetype)) {
         Integer var128 = var1.upanddowngradetype;
         var2 += CodedOutputStream.computeInt32Size(55, var128);
      }

      Object var58 = null;
      if (!CodedConstant.isNull(var1.upanddowngraderesults)) {
         List var129 = var1.upanddowngraderesults;
         var2 += CodedConstant.computeListSize(56, var129, FieldType.INT32, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var59 = null;
      if (!CodedConstant.isNull(var1.batteleaguecontribution)) {
         Integer var130 = var1.batteleaguecontribution;
         var2 += CodedOutputStream.computeInt32Size(57, var130);
      }

      Object var60 = null;
      if (!CodedConstant.isNull(var1.battleleaguepverecord1)) {
         List var131 = var1.battleleaguepverecord1;
         var2 += CodedConstant.computeListSize(58, var131, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var61 = null;
      if (!CodedConstant.isNull(var1.battleleaguepverecord2)) {
         List var132 = var1.battleleaguepverecord2;
         var2 += CodedConstant.computeListSize(59, var132, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var62 = null;
      if (!CodedConstant.isNull(var1.battleleaguepvprecord1)) {
         List var133 = var1.battleleaguepvprecord1;
         var2 += CodedConstant.computeListSize(60, var133, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var63 = null;
      if (!CodedConstant.isNull(var1.battleleaguepvprecord2)) {
         List var134 = var1.battleleaguepvprecord2;
         var2 += CodedConstant.computeListSize(61, var134, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var64 = null;
      if (!CodedConstant.isNull(var1.battleleaguepvereward)) {
         PT_BATTLELEAGUE_REWARD var135 = var1.battleleaguepvereward;
         var2 += CodedConstant.computeSize(62, var135, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var65 = null;
      if (!CodedConstant.isNull(var1.battleleaguepvprewards1)) {
         List var136 = var1.battleleaguepvprewards1;
         var2 += CodedConstant.computeListSize(63, var136, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var66 = null;
      if (!CodedConstant.isNull(var1.battleleaguepvprewards2)) {
         List var137 = var1.battleleaguepvprewards2;
         var2 += CodedConstant.computeListSize(64, var137, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var67 = null;
      if (!CodedConstant.isNull(var1.battleleaguemvp)) {
         Long var138 = var1.battleleaguemvp;
         var2 += CodedOutputStream.computeUInt64Size(65, var138);
      }

      Object var68 = null;
      if (!CodedConstant.isNull(var1.battleleaguemvps)) {
         List var139 = var1.battleleaguemvps;
         var2 += CodedConstant.computeListSize(66, var139, FieldType.UINT64, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var69 = null;
      if (!CodedConstant.isNull(var1.blpverewardprivate)) {
         PT_BATTLELEAGUE_REWARD var140 = var1.blpverewardprivate;
         var2 += CodedConstant.computeSize(67, var140, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var70 = null;
      if (!CodedConstant.isNull(var1.blpverewardpublic)) {
         PT_BATTLELEAGUE_REWARD var141 = var1.blpverewardpublic;
         var2 += CodedConstant.computeSize(68, var141, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var71 = null;
      if (!CodedConstant.isNull(var1.blpvpreward)) {
         List var142 = var1.blpvpreward;
         var2 += CodedConstant.computeListSize(69, var142, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var72 = null;
      if (!CodedConstant.isNull(var1.playtime)) {
         Long var143 = var1.playtime;
         var2 += CodedOutputStream.computeInt64Size(70, var143);
      }

      Object var73 = null;
      if (!CodedConstant.isNull(var1.cardcost)) {
         Integer var144 = var1.cardcost;
         var2 += CodedOutputStream.computeInt32Size(71, var144);
      }

      return var2;
   }

   public void doWriteTo(NOTIFY_DUNGEON_RESULT var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var74 = var1.error;
         if (var74 != null) {
            var2.writeInt32(1, var74);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var75 = var1.dungeonindex;
         if (var75 != null) {
            var2.writeInt32(2, var75);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.matchtype)) {
         Integer var76 = var1.matchtype;
         if (var76 != null) {
            var2.writeInt32(3, var76);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.exp)) {
         Integer var77 = var1.exp;
         if (var77 != null) {
            var2.writeInt32(4, var77);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var78 = var1.level;
         if (var78 != null) {
            var2.writeInt32(5, var78);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var79 = var1.count;
         if (var79 != null) {
            var2.writeInt32(6, var79);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.time)) {
         Long var80 = var1.time;
         if (var80 != null) {
            var2.writeInt64(7, var80);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.point)) {
         Integer var81 = var1.point;
         if (var81 != null) {
            var2.writeInt32(8, var81);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var82 = var1.grade;
         if (var82 != null) {
            var2.writeInt32(9, var82);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.gage)) {
         Integer var83 = var1.gage;
         if (var83 != null) {
            var2.writeInt32(10, var83);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.durability)) {
         Integer var84 = var1.durability;
         if (var84 != null) {
            var2.writeInt32(11, var84);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.clearexp)) {
         PT_EXP_DETAILINFO var85 = var1.clearexp;
         if (var85 != null) {
            CodedConstant.writeObject(var2, 12, FieldType.OBJECT, var85, false);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.escortreward)) {
         PT_ESCORT_REWARD var86 = var1.escortreward;
         if (var86 != null) {
            CodedConstant.writeObject(var2, 13, FieldType.OBJECT, var86, false);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.starrewards)) {
         List var87 = var1.starrewards;
         if (var87 != null) {
            CodedConstant.writeToList(var2, 14, FieldType.OBJECT, var87, false);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.totalclearreward)) {
         PT_ADVENTURE_CLEAR_REWARD var88 = var1.totalclearreward;
         if (var88 != null) {
            CodedConstant.writeObject(var2, 15, FieldType.OBJECT, var88, false);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.battleworld)) {
         Integer var89 = var1.battleworld;
         if (var89 != null) {
            var2.writeInt32(16, var89);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.bchannel)) {
         Integer var90 = var1.bchannel;
         if (var90 != null) {
            var2.writeInt32(17, var90);
         }
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.bip)) {
         String var91 = var1.bip;
         if (var91 != null) {
            var2.writeString(18, var91);
         }
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.bport)) {
         Integer var92 = var1.bport;
         if (var92 != null) {
            var2.writeInt32(19, var92);
         }
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.authkey)) {
         String var93 = var1.authkey;
         if (var93 != null) {
            var2.writeString(20, var93);
         }
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var94 = var1.type;
         if (var94 != null) {
            var2.writeInt32(21, var94);
         }
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.tick)) {
         Long var95 = var1.tick;
         if (var95 != null) {
            var2.writeUInt64(22, var95);
         }
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.mileage)) {
         Integer var96 = var1.mileage;
         if (var96 != null) {
            var2.writeInt32(23, var96);
         }
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.firstclearreward)) {
         PT_TOWER_REWARD_ITEMS var97 = var1.firstclearreward;
         if (var97 != null) {
            CodedConstant.writeObject(var2, 24, FieldType.OBJECT, var97, false);
         }
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.betterclearreward)) {
         PT_TOWER_REWARD_ITEMS var98 = var1.betterclearreward;
         if (var98 != null) {
            CodedConstant.writeObject(var2, 25, FieldType.OBJECT, var98, false);
         }
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.mileagereward)) {
         PT_TOWER_REWARD_ITEMS var99 = var1.mileagereward;
         if (var99 != null) {
            CodedConstant.writeObject(var2, 26, FieldType.OBJECT, var99, false);
         }
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.timecutclearreward)) {
         PT_TOWER_REWARD_ITEMS var100 = var1.timecutclearreward;
         if (var100 != null) {
            CodedConstant.writeObject(var2, 27, FieldType.OBJECT, var100, false);
         }
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.stageclearreward)) {
         PT_TOWER_REWARD_ITEMS var101 = var1.stageclearreward;
         if (var101 != null) {
            CodedConstant.writeObject(var2, 28, FieldType.OBJECT, var101, false);
         }
      }

      Object var31 = null;
      if (!CodedConstant.isNull(var1.worldraidreward)) {
         PT_WORLDRADE_REWARD var102 = var1.worldraidreward;
         if (var102 != null) {
            CodedConstant.writeObject(var2, 29, FieldType.OBJECT, var102, false);
         }
      }

      Object var32 = null;
      if (!CodedConstant.isNull(var1.closenessreward)) {
         List var103 = var1.closenessreward;
         if (var103 != null) {
            CodedConstant.writeToList(var2, 30, FieldType.OBJECT, var103, false);
         }
      }

      Object var33 = null;
      if (!CodedConstant.isNull(var1.arcadereward)) {
         List var104 = var1.arcadereward;
         if (var104 != null) {
            CodedConstant.writeToList(var2, 31, FieldType.OBJECT, var104, false);
         }
      }

      Object var34 = null;
      if (!CodedConstant.isNull(var1.performancerating)) {
         Integer var105 = var1.performancerating;
         if (var105 != null) {
            var2.writeInt32(32, var105);
         }
      }

      Object var35 = null;
      if (!CodedConstant.isNull(var1.result)) {
         Integer var106 = var1.result;
         if (var106 != null) {
            var2.writeInt32(33, var106);
         }
      }

      Object var36 = null;
      if (!CodedConstant.isNull(var1.pvpstarcount)) {
         Integer var107 = var1.pvpstarcount;
         if (var107 != null) {
            var2.writeInt32(34, var107);
         }
      }

      Object var37 = null;
      if (!CodedConstant.isNull(var1.pvpstargrade)) {
         Integer var108 = var1.pvpstargrade;
         if (var108 != null) {
            var2.writeInt32(35, var108);
         }
      }

      Object var38 = null;
      if (!CodedConstant.isNull(var1.orderlist)) {
         List var109 = var1.orderlist;
         if (var109 != null) {
            CodedConstant.writeToList(var2, 36, FieldType.OBJECT, var109, false);
         }
      }

      Object var39 = null;
      if (!CodedConstant.isNull(var1.guildauction)) {
         List var110 = var1.guildauction;
         if (var110 != null) {
            CodedConstant.writeToList(var2, 37, FieldType.OBJECT, var110, false);
         }
      }

      Object var40 = null;
      if (!CodedConstant.isNull(var1.deathmatchrecord)) {
         List var111 = var1.deathmatchrecord;
         if (var111 != null) {
            CodedConstant.writeToList(var2, 38, FieldType.OBJECT, var111, false);
         }
      }

      Object var41 = null;
      if (!CodedConstant.isNull(var1.deathmatchrecord1)) {
         List var112 = var1.deathmatchrecord1;
         if (var112 != null) {
            CodedConstant.writeToList(var2, 39, FieldType.OBJECT, var112, false);
         }
      }

      Object var42 = null;
      if (!CodedConstant.isNull(var1.deathmatchrecord2)) {
         List var113 = var1.deathmatchrecord2;
         if (var113 != null) {
            CodedConstant.writeToList(var2, 40, FieldType.OBJECT, var113, false);
         }
      }

      Object var43 = null;
      if (!CodedConstant.isNull(var1.deathrewards)) {
         List var114 = var1.deathrewards;
         if (var114 != null) {
            CodedConstant.writeToList(var2, 41, FieldType.OBJECT, var114, false);
         }
      }

      Object var44 = null;
      if (!CodedConstant.isNull(var1.deathrewards1)) {
         List var115 = var1.deathrewards1;
         if (var115 != null) {
            CodedConstant.writeToList(var2, 42, FieldType.OBJECT, var115, false);
         }
      }

      Object var45 = null;
      if (!CodedConstant.isNull(var1.deathrewards2)) {
         List var116 = var1.deathrewards2;
         if (var116 != null) {
            CodedConstant.writeToList(var2, 43, FieldType.OBJECT, var116, false);
         }
      }

      Object var46 = null;
      if (!CodedConstant.isNull(var1.winpoint)) {
         Integer var117 = var1.winpoint;
         if (var117 != null) {
            var2.writeInt32(44, var117);
         }
      }

      Object var47 = null;
      if (!CodedConstant.isNull(var1.record)) {
         List var118 = var1.record;
         if (var118 != null) {
            CodedConstant.writeToList(var2, 45, FieldType.OBJECT, var118, false);
         }
      }

      Object var48 = null;
      if (!CodedConstant.isNull(var1.currency)) {
         List var119 = var1.currency;
         if (var119 != null) {
            CodedConstant.writeToList(var2, 46, FieldType.OBJECT, var119, false);
         }
      }

      Object var49 = null;
      if (!CodedConstant.isNull(var1.adventureticket)) {
         List var120 = var1.adventureticket;
         if (var120 != null) {
            CodedConstant.writeToList(var2, 47, FieldType.OBJECT, var120, false);
         }
      }

      Object var50 = null;
      if (!CodedConstant.isNull(var1.fatigue)) {
         Integer var121 = var1.fatigue;
         if (var121 != null) {
            var2.writeInt32(48, var121);
         }
      }

      Object var51 = null;
      if (!CodedConstant.isNull(var1.consumefatigue)) {
         Integer var122 = var1.consumefatigue;
         if (var122 != null) {
            var2.writeInt32(49, var122);
         }
      }

      Object var52 = null;
      if (!CodedConstant.isNull(var1.rewards)) {
         List var123 = var1.rewards;
         if (var123 != null) {
            CodedConstant.writeToList(var2, 50, FieldType.OBJECT, var123, false);
         }
      }

      Object var53 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var124 = var1.dungeonguid;
         if (var124 != null) {
            var2.writeUInt64(51, var124);
         }
      }

      Object var54 = null;
      if (!CodedConstant.isNull(var1.ancientfirstclearcount)) {
         Long var125 = var1.ancientfirstclearcount;
         if (var125 != null) {
            var2.writeUInt64(52, var125);
         }
      }

      Object var55 = null;
      if (!CodedConstant.isNull(var1.list)) {
         List var126 = var1.list;
         if (var126 != null) {
            CodedConstant.writeToList(var2, 53, FieldType.OBJECT, var126, false);
         }
      }

      Object var56 = null;
      if (!CodedConstant.isNull(var1.gloryinfo)) {
         PT_PVP_GLORY_RESULT var127 = var1.gloryinfo;
         if (var127 != null) {
            CodedConstant.writeObject(var2, 54, FieldType.OBJECT, var127, false);
         }
      }

      Object var57 = null;
      if (!CodedConstant.isNull(var1.upanddowngradetype)) {
         Integer var128 = var1.upanddowngradetype;
         if (var128 != null) {
            var2.writeInt32(55, var128);
         }
      }

      Object var58 = null;
      if (!CodedConstant.isNull(var1.upanddowngraderesults)) {
         List var129 = var1.upanddowngraderesults;
         if (var129 != null) {
            CodedConstant.writeToList(var2, 56, FieldType.INT32, var129, true);
         }
      }

      Object var59 = null;
      if (!CodedConstant.isNull(var1.batteleaguecontribution)) {
         Integer var130 = var1.batteleaguecontribution;
         if (var130 != null) {
            var2.writeInt32(57, var130);
         }
      }

      Object var60 = null;
      if (!CodedConstant.isNull(var1.battleleaguepverecord1)) {
         List var131 = var1.battleleaguepverecord1;
         if (var131 != null) {
            CodedConstant.writeToList(var2, 58, FieldType.OBJECT, var131, false);
         }
      }

      Object var61 = null;
      if (!CodedConstant.isNull(var1.battleleaguepverecord2)) {
         List var132 = var1.battleleaguepverecord2;
         if (var132 != null) {
            CodedConstant.writeToList(var2, 59, FieldType.OBJECT, var132, false);
         }
      }

      Object var62 = null;
      if (!CodedConstant.isNull(var1.battleleaguepvprecord1)) {
         List var133 = var1.battleleaguepvprecord1;
         if (var133 != null) {
            CodedConstant.writeToList(var2, 60, FieldType.OBJECT, var133, false);
         }
      }

      Object var63 = null;
      if (!CodedConstant.isNull(var1.battleleaguepvprecord2)) {
         List var134 = var1.battleleaguepvprecord2;
         if (var134 != null) {
            CodedConstant.writeToList(var2, 61, FieldType.OBJECT, var134, false);
         }
      }

      Object var64 = null;
      if (!CodedConstant.isNull(var1.battleleaguepvereward)) {
         PT_BATTLELEAGUE_REWARD var135 = var1.battleleaguepvereward;
         if (var135 != null) {
            CodedConstant.writeObject(var2, 62, FieldType.OBJECT, var135, false);
         }
      }

      Object var65 = null;
      if (!CodedConstant.isNull(var1.battleleaguepvprewards1)) {
         List var136 = var1.battleleaguepvprewards1;
         if (var136 != null) {
            CodedConstant.writeToList(var2, 63, FieldType.OBJECT, var136, false);
         }
      }

      Object var66 = null;
      if (!CodedConstant.isNull(var1.battleleaguepvprewards2)) {
         List var137 = var1.battleleaguepvprewards2;
         if (var137 != null) {
            CodedConstant.writeToList(var2, 64, FieldType.OBJECT, var137, false);
         }
      }

      Object var67 = null;
      if (!CodedConstant.isNull(var1.battleleaguemvp)) {
         Long var138 = var1.battleleaguemvp;
         if (var138 != null) {
            var2.writeUInt64(65, var138);
         }
      }

      Object var68 = null;
      if (!CodedConstant.isNull(var1.battleleaguemvps)) {
         List var139 = var1.battleleaguemvps;
         if (var139 != null) {
            CodedConstant.writeToList(var2, 66, FieldType.UINT64, var139, true);
         }
      }

      Object var69 = null;
      if (!CodedConstant.isNull(var1.blpverewardprivate)) {
         PT_BATTLELEAGUE_REWARD var140 = var1.blpverewardprivate;
         if (var140 != null) {
            CodedConstant.writeObject(var2, 67, FieldType.OBJECT, var140, false);
         }
      }

      Object var70 = null;
      if (!CodedConstant.isNull(var1.blpverewardpublic)) {
         PT_BATTLELEAGUE_REWARD var141 = var1.blpverewardpublic;
         if (var141 != null) {
            CodedConstant.writeObject(var2, 68, FieldType.OBJECT, var141, false);
         }
      }

      Object var71 = null;
      if (!CodedConstant.isNull(var1.blpvpreward)) {
         List var142 = var1.blpvpreward;
         if (var142 != null) {
            CodedConstant.writeToList(var2, 69, FieldType.OBJECT, var142, false);
         }
      }

      Object var72 = null;
      if (!CodedConstant.isNull(var1.playtime)) {
         Long var143 = var1.playtime;
         if (var143 != null) {
            var2.writeInt64(70, var143);
         }
      }

      Object var73 = null;
      if (!CodedConstant.isNull(var1.cardcost)) {
         Integer var144 = var1.cardcost;
         if (var144 != null) {
            var2.writeInt32(71, var144);
         }
      }

   }

   public void writeTo(NOTIFY_DUNGEON_RESULT var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public NOTIFY_DUNGEON_RESULT readFrom(CodedInputStream var1) throws IOException {
      NOTIFY_DUNGEON_RESULT var2 = new NOTIFY_DUNGEON_RESULT();
      var2.starrewards = new ArrayList();
      var2.closenessreward = new ArrayList();
      var2.arcadereward = new ArrayList();
      var2.orderlist = new ArrayList();
      var2.guildauction = new ArrayList();
      var2.deathmatchrecord = new ArrayList();
      var2.deathmatchrecord1 = new ArrayList();
      var2.deathmatchrecord2 = new ArrayList();
      var2.deathrewards = new ArrayList();
      var2.deathrewards1 = new ArrayList();
      var2.deathrewards2 = new ArrayList();
      var2.record = new ArrayList();
      var2.currency = new ArrayList();
      var2.adventureticket = new ArrayList();
      var2.rewards = new ArrayList();
      var2.list = new ArrayList();
      var2.upanddowngraderesults = new ArrayList();
      var2.battleleaguepverecord1 = new ArrayList();
      var2.battleleaguepverecord2 = new ArrayList();
      var2.battleleaguepvprecord1 = new ArrayList();
      var2.battleleaguepvprecord2 = new ArrayList();
      var2.battleleaguepvprewards1 = new ArrayList();
      var2.battleleaguepvprewards2 = new ArrayList();
      var2.battleleaguemvps = new ArrayList();
      var2.blpvpreward = new ArrayList();

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
               var2.dungeonindex = var1.readInt32();
            } else if (var5 == 24) {
               var2.matchtype = var1.readInt32();
            } else if (var5 == 32) {
               var2.exp = var1.readInt32();
            } else if (var5 == 40) {
               var2.level = var1.readInt32();
            } else if (var5 == 48) {
               var2.count = var1.readInt32();
            } else if (var5 == 56) {
               var2.time = var1.readInt64();
            } else if (var5 == 64) {
               var2.point = var1.readInt32();
            } else if (var5 == 72) {
               var2.grade = var1.readInt32();
            } else if (var5 == 80) {
               var2.gage = var1.readInt32();
            } else if (var5 == 88) {
               var2.durability = var1.readInt32();
            } else if (var5 == 98) {
               Codec var45 = ProtobufProxy.create(PT_EXP_DETAILINFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var84 = var1.readRawVarint32();
               int var123 = var1.pushLimit(var84);
               var2.clearexp = (PT_EXP_DETAILINFO)var45.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var123);
            } else if (var5 == 106) {
               Codec var44 = ProtobufProxy.create(PT_ESCORT_REWARD.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var83 = var1.readRawVarint32();
               int var122 = var1.pushLimit(var83);
               var2.escortreward = (PT_ESCORT_REWARD)var44.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var122);
            } else if (var5 == 114) {
               Codec var43 = ProtobufProxy.create(PT_CLEAR_DUNGEON_STARREWARD.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var82 = var1.readRawVarint32();
               int var121 = var1.pushLimit(var82);
               if (var2.starrewards == null) {
                  var2.starrewards = new ArrayList();
               }

               var2.starrewards.add((PT_CLEAR_DUNGEON_STARREWARD)var43.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var121);
            } else if (var5 == 122) {
               Codec var42 = ProtobufProxy.create(PT_ADVENTURE_CLEAR_REWARD.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var81 = var1.readRawVarint32();
               int var120 = var1.pushLimit(var81);
               var2.totalclearreward = (PT_ADVENTURE_CLEAR_REWARD)var42.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var120);
            } else if (var5 == 128) {
               var2.battleworld = var1.readInt32();
            } else if (var5 == 136) {
               var2.bchannel = var1.readInt32();
            } else if (var5 == 146) {
               var2.bip = var1.readString();
            } else if (var5 == 152) {
               var2.bport = var1.readInt32();
            } else if (var5 == 162) {
               var2.authkey = var1.readString();
            } else if (var5 == 168) {
               var2.type = var1.readInt32();
            } else if (var5 == 176) {
               var2.tick = var1.readUInt64();
            } else if (var5 == 184) {
               var2.mileage = var1.readInt32();
            } else if (var5 == 194) {
               Codec var41 = ProtobufProxy.create(PT_TOWER_REWARD_ITEMS.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var80 = var1.readRawVarint32();
               int var119 = var1.pushLimit(var80);
               var2.firstclearreward = (PT_TOWER_REWARD_ITEMS)var41.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var119);
            } else if (var5 == 202) {
               Codec var40 = ProtobufProxy.create(PT_TOWER_REWARD_ITEMS.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var79 = var1.readRawVarint32();
               int var118 = var1.pushLimit(var79);
               var2.betterclearreward = (PT_TOWER_REWARD_ITEMS)var40.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var118);
            } else if (var5 == 210) {
               Codec var39 = ProtobufProxy.create(PT_TOWER_REWARD_ITEMS.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var78 = var1.readRawVarint32();
               int var117 = var1.pushLimit(var78);
               var2.mileagereward = (PT_TOWER_REWARD_ITEMS)var39.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var117);
            } else if (var5 == 218) {
               Codec var38 = ProtobufProxy.create(PT_TOWER_REWARD_ITEMS.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var77 = var1.readRawVarint32();
               int var116 = var1.pushLimit(var77);
               var2.timecutclearreward = (PT_TOWER_REWARD_ITEMS)var38.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var116);
            } else if (var5 == 226) {
               Codec var37 = ProtobufProxy.create(PT_TOWER_REWARD_ITEMS.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var76 = var1.readRawVarint32();
               int var115 = var1.pushLimit(var76);
               var2.stageclearreward = (PT_TOWER_REWARD_ITEMS)var37.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var115);
            } else if (var5 == 234) {
               Codec var36 = ProtobufProxy.create(PT_WORLDRADE_REWARD.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var75 = var1.readRawVarint32();
               int var114 = var1.pushLimit(var75);
               var2.worldraidreward = (PT_WORLDRADE_REWARD)var36.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var114);
            } else if (var5 == 242) {
               Codec var35 = ProtobufProxy.create(PT_CLOSENESS_RESULT_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var74 = var1.readRawVarint32();
               int var113 = var1.pushLimit(var74);
               if (var2.closenessreward == null) {
                  var2.closenessreward = new ArrayList();
               }

               var2.closenessreward.add((PT_CLOSENESS_RESULT_INFO)var35.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var113);
            } else if (var5 == 250) {
               Codec var34 = ProtobufProxy.create(PT_ARCADE_PVP_INFO_CURRENCY.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var73 = var1.readRawVarint32();
               int var112 = var1.pushLimit(var73);
               if (var2.arcadereward == null) {
                  var2.arcadereward = new ArrayList();
               }

               var2.arcadereward.add((PT_ARCADE_PVP_INFO_CURRENCY)var34.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var112);
            } else if (var5 == 256) {
               var2.performancerating = var1.readInt32();
            } else if (var5 == 264) {
               var2.result = var1.readInt32();
            } else if (var5 == 272) {
               var2.pvpstarcount = var1.readInt32();
            } else if (var5 == 280) {
               var2.pvpstargrade = var1.readInt32();
            } else if (var5 == 290) {
               Codec var33 = ProtobufProxy.create(PT_GUARDIAN_ORDER.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var72 = var1.readRawVarint32();
               int var111 = var1.pushLimit(var72);
               if (var2.orderlist == null) {
                  var2.orderlist = new ArrayList();
               }

               var2.orderlist.add((PT_GUARDIAN_ORDER)var33.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var111);
            } else if (var5 == 298) {
               Codec var32 = ProtobufProxy.create(PT_GUARDIAN_AUCTION.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var71 = var1.readRawVarint32();
               int var110 = var1.pushLimit(var71);
               if (var2.guildauction == null) {
                  var2.guildauction = new ArrayList();
               }

               var2.guildauction.add((PT_GUARDIAN_AUCTION)var32.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var110);
            } else if (var5 == 306) {
               Codec var31 = ProtobufProxy.create(PT_SD_DEATH_MATCH_RECORD.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var70 = var1.readRawVarint32();
               int var109 = var1.pushLimit(var70);
               if (var2.deathmatchrecord == null) {
                  var2.deathmatchrecord = new ArrayList();
               }

               var2.deathmatchrecord.add((PT_SD_DEATH_MATCH_RECORD)var31.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var109);
            } else if (var5 == 314) {
               Codec var30 = ProtobufProxy.create(PT_SD_DEATH_MATCH_RECORD.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var69 = var1.readRawVarint32();
               int var108 = var1.pushLimit(var69);
               if (var2.deathmatchrecord1 == null) {
                  var2.deathmatchrecord1 = new ArrayList();
               }

               var2.deathmatchrecord1.add((PT_SD_DEATH_MATCH_RECORD)var30.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var108);
            } else if (var5 == 322) {
               Codec var29 = ProtobufProxy.create(PT_SD_DEATH_MATCH_RECORD.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var68 = var1.readRawVarint32();
               int var107 = var1.pushLimit(var68);
               if (var2.deathmatchrecord2 == null) {
                  var2.deathmatchrecord2 = new ArrayList();
               }

               var2.deathmatchrecord2.add((PT_SD_DEATH_MATCH_RECORD)var29.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var107);
            } else if (var5 == 330) {
               Codec var28 = ProtobufProxy.create(PT_SD_DEATH_MATCH_REWARD.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var67 = var1.readRawVarint32();
               int var106 = var1.pushLimit(var67);
               if (var2.deathrewards == null) {
                  var2.deathrewards = new ArrayList();
               }

               var2.deathrewards.add((PT_SD_DEATH_MATCH_REWARD)var28.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var106);
            } else if (var5 == 338) {
               Codec var27 = ProtobufProxy.create(PT_SD_DEATH_MATCH_REWARD.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var66 = var1.readRawVarint32();
               int var105 = var1.pushLimit(var66);
               if (var2.deathrewards1 == null) {
                  var2.deathrewards1 = new ArrayList();
               }

               var2.deathrewards1.add((PT_SD_DEATH_MATCH_REWARD)var27.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var105);
            } else if (var5 == 346) {
               Codec var26 = ProtobufProxy.create(PT_SD_DEATH_MATCH_REWARD.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var65 = var1.readRawVarint32();
               int var104 = var1.pushLimit(var65);
               if (var2.deathrewards2 == null) {
                  var2.deathrewards2 = new ArrayList();
               }

               var2.deathrewards2.add((PT_SD_DEATH_MATCH_REWARD)var26.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var104);
            } else if (var5 == 352) {
               var2.winpoint = var1.readInt32();
            } else if (var5 == 362) {
               Codec var25 = ProtobufProxy.create(PT_SD_DEATH_MATCH_RECORD.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var64 = var1.readRawVarint32();
               int var103 = var1.pushLimit(var64);
               if (var2.record == null) {
                  var2.record = new ArrayList();
               }

               var2.record.add((PT_SD_DEATH_MATCH_RECORD)var25.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var103);
            } else if (var5 == 370) {
               Codec var24 = ProtobufProxy.create(PT_MONEY_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var63 = var1.readRawVarint32();
               int var102 = var1.pushLimit(var63);
               if (var2.currency == null) {
                  var2.currency = new ArrayList();
               }

               var2.currency.add((PT_MONEY_ITEM)var24.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var102);
            } else if (var5 == 378) {
               Codec var23 = ProtobufProxy.create(PT_ACCOUNT_TICKET.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var62 = var1.readRawVarint32();
               int var101 = var1.pushLimit(var62);
               if (var2.adventureticket == null) {
                  var2.adventureticket = new ArrayList();
               }

               var2.adventureticket.add((PT_ACCOUNT_TICKET)var23.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var101);
            } else if (var5 == 384) {
               var2.fatigue = var1.readInt32();
            } else if (var5 == 392) {
               var2.consumefatigue = var1.readInt32();
            } else if (var5 == 402) {
               Codec var22 = ProtobufProxy.create(PT_ITEMS_TOWER.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var61 = var1.readRawVarint32();
               int var100 = var1.pushLimit(var61);
               if (var2.rewards == null) {
                  var2.rewards = new ArrayList();
               }

               var2.rewards.add((PT_ITEMS_TOWER)var22.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var100);
            } else if (var5 == 408) {
               var2.dungeonguid = var1.readUInt64();
            } else if (var5 == 416) {
               var2.ancientfirstclearcount = var1.readUInt64();
            } else if (var5 == 426) {
               Codec var21 = ProtobufProxy.create(PT_ALL_CLEAR_REWARD.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var60 = var1.readRawVarint32();
               int var99 = var1.pushLimit(var60);
               if (var2.list == null) {
                  var2.list = new ArrayList();
               }

               var2.list.add((PT_ALL_CLEAR_REWARD)var21.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var99);
            } else if (var5 == 434) {
               Codec var20 = ProtobufProxy.create(PT_PVP_GLORY_RESULT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var59 = var1.readRawVarint32();
               int var98 = var1.pushLimit(var59);
               var2.gloryinfo = (PT_PVP_GLORY_RESULT)var20.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var98);
            } else if (var5 == 440) {
               var2.upanddowngradetype = var1.readInt32();
            } else if (var5 == 448) {
               if (var2.upanddowngraderesults == null) {
                  var2.upanddowngraderesults = new ArrayList();
               }

               var2.upanddowngraderesults.add(var1.readInt32());
            } else if (var5 == 450) {
               int var58 = var1.readRawVarint32();
               int var97 = var1.pushLimit(var58);
               if (var2.upanddowngraderesults == null) {
                  var2.upanddowngraderesults = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.upanddowngraderesults.add(var1.readInt32());
               }

               var1.popLimit(var97);
            } else if (var5 == 456) {
               var2.batteleaguecontribution = var1.readInt32();
            } else if (var5 == 466) {
               if (var5 != 450) {
                  Codec var19 = ProtobufProxy.create(PT_BATTLELEAGUE_PVE_RECORD.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
                  int var57 = var1.readRawVarint32();
                  int var96 = var1.pushLimit(var57);
                  if (var2.battleleaguepverecord1 == null) {
                     var2.battleleaguepverecord1 = new ArrayList();
                  }

                  var2.battleleaguepverecord1.add((PT_BATTLELEAGUE_PVE_RECORD)var19.readFrom(var1));
                  var1.checkLastTagWas(0);
                  var1.popLimit(var96);
               } else {
                  int var56 = var1.readRawVarint32();
                  int var95 = var1.pushLimit(var56);
                  if (var2.upanddowngraderesults == null) {
                     var2.upanddowngraderesults = new ArrayList();
                  }

                  while(var1.getBytesUntilLimit() > 0) {
                     var2.upanddowngraderesults.add(var1.readInt32());
                  }

                  var1.popLimit(var95);
               }
            } else if (var5 == 474) {
               Codec var18 = ProtobufProxy.create(PT_BATTLELEAGUE_PVE_RECORD.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var55 = var1.readRawVarint32();
               int var94 = var1.pushLimit(var55);
               if (var2.battleleaguepverecord2 == null) {
                  var2.battleleaguepverecord2 = new ArrayList();
               }

               var2.battleleaguepverecord2.add((PT_BATTLELEAGUE_PVE_RECORD)var18.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var94);
            } else if (var5 == 482) {
               Codec var17 = ProtobufProxy.create(PT_BATTLELEAGUE_PVP_RECORD.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var54 = var1.readRawVarint32();
               int var93 = var1.pushLimit(var54);
               if (var2.battleleaguepvprecord1 == null) {
                  var2.battleleaguepvprecord1 = new ArrayList();
               }

               var2.battleleaguepvprecord1.add((PT_BATTLELEAGUE_PVP_RECORD)var17.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var93);
            } else if (var5 == 490) {
               Codec var16 = ProtobufProxy.create(PT_BATTLELEAGUE_PVP_RECORD.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var53 = var1.readRawVarint32();
               int var92 = var1.pushLimit(var53);
               if (var2.battleleaguepvprecord2 == null) {
                  var2.battleleaguepvprecord2 = new ArrayList();
               }

               var2.battleleaguepvprecord2.add((PT_BATTLELEAGUE_PVP_RECORD)var16.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var92);
            } else if (var5 == 498) {
               Codec var15 = ProtobufProxy.create(PT_BATTLELEAGUE_REWARD.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var52 = var1.readRawVarint32();
               int var91 = var1.pushLimit(var52);
               var2.battleleaguepvereward = (PT_BATTLELEAGUE_REWARD)var15.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var91);
            } else if (var5 == 506) {
               Codec var14 = ProtobufProxy.create(PT_BATTLELEAGUE_REWARD.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var51 = var1.readRawVarint32();
               int var90 = var1.pushLimit(var51);
               if (var2.battleleaguepvprewards1 == null) {
                  var2.battleleaguepvprewards1 = new ArrayList();
               }

               var2.battleleaguepvprewards1.add((PT_BATTLELEAGUE_REWARD)var14.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var90);
            } else if (var5 == 514) {
               Codec var13 = ProtobufProxy.create(PT_BATTLELEAGUE_REWARD.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var50 = var1.readRawVarint32();
               int var89 = var1.pushLimit(var50);
               if (var2.battleleaguepvprewards2 == null) {
                  var2.battleleaguepvprewards2 = new ArrayList();
               }

               var2.battleleaguepvprewards2.add((PT_BATTLELEAGUE_REWARD)var13.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var89);
            } else if (var5 == 520) {
               var2.battleleaguemvp = var1.readUInt64();
            } else if (var5 == 528) {
               if (var2.battleleaguemvps == null) {
                  var2.battleleaguemvps = new ArrayList();
               }

               var2.battleleaguemvps.add(var1.readUInt64());
            } else if (var5 == 530) {
               int var49 = var1.readRawVarint32();
               int var88 = var1.pushLimit(var49);
               if (var2.battleleaguemvps == null) {
                  var2.battleleaguemvps = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.battleleaguemvps.add(var1.readUInt64());
               }

               var1.popLimit(var88);
            } else if (var5 != 538) {
               if (var5 == 546) {
                  Codec var11 = ProtobufProxy.create(PT_BATTLELEAGUE_REWARD.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
                  int var47 = var1.readRawVarint32();
                  int var86 = var1.pushLimit(var47);
                  var2.blpverewardpublic = (PT_BATTLELEAGUE_REWARD)var11.readFrom(var1);
                  var1.checkLastTagWas(0);
                  var1.popLimit(var86);
               } else if (var5 == 554) {
                  Codec var12 = ProtobufProxy.create(PT_BATTLELEAGUE_REWARD.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
                  int var48 = var1.readRawVarint32();
                  int var87 = var1.pushLimit(var48);
                  if (var2.blpvpreward == null) {
                     var2.blpvpreward = new ArrayList();
                  }

                  var2.blpvpreward.add((PT_BATTLELEAGUE_REWARD)var12.readFrom(var1));
                  var1.checkLastTagWas(0);
                  var1.popLimit(var87);
               } else if (var5 == 560) {
                  var2.playtime = var1.readInt64();
               } else if (var5 == 568) {
                  var2.cardcost = var1.readInt32();
               } else {
                  var1.skipField(var5);
               }
            } else if (var5 != 530) {
               Codec var10 = ProtobufProxy.create(PT_BATTLELEAGUE_REWARD.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var46 = var1.readRawVarint32();
               int var85 = var1.pushLimit(var46);
               var2.blpverewardprivate = (PT_BATTLELEAGUE_REWARD)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var85);
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.battleleaguemvps == null) {
                  var2.battleleaguemvps = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.battleleaguemvps.add(var1.readUInt64());
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(NOTIFY_DUNGEON_RESULT.class);
         return this.descriptor = var1;
      }
   }
}
