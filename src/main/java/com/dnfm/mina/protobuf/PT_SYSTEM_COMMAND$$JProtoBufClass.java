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

public class PT_SYSTEM_COMMAND$$JProtoBufClass implements Codec<PT_SYSTEM_COMMAND>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_SYSTEM_COMMAND var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_SYSTEM_COMMAND decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_SYSTEM_COMMAND var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.commands)) {
         PT_SYSCMD_COMMAND var87 = var1.commands;
         var2 += CodedConstant.computeSize(1, var87, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.dungeon)) {
         Integer var88 = var1.dungeon;
         var2 += CodedOutputStream.computeInt32Size(2, var88);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.value)) {
         byte[] var89 = var1.value;
         var2 += CodedOutputStream.computeByteArraySize(3, var89);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.reason)) {
         String var90 = var1.reason;
         var2 += CodedOutputStream.computeStringSize(4, var90);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var91 = var1.type;
         var2 += CodedOutputStream.computeUInt32Size(5, var91);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.time)) {
         Long var92 = var1.time;
         var2 += CodedOutputStream.computeUInt64Size(6, var92);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.idip)) {
         Integer var93 = var1.idip;
         var2 += CodedOutputStream.computeInt32Size(7, var93);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.platID)) {
         Integer var94 = var1.platID;
         var2 += CodedOutputStream.computeInt32Size(8, var94);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var95 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(9, var95);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.subtype)) {
         Integer var96 = var1.subtype;
         var2 += CodedOutputStream.computeInt32Size(10, var96);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.addtime)) {
         Long var97 = var1.addtime;
         var2 += CodedOutputStream.computeUInt64Size(11, var97);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.zeroBenefit)) {
         Integer var98 = var1.zeroBenefit;
         var2 += CodedOutputStream.computeInt32Size(12, var98);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.unPlay)) {
         Integer var99 = var1.unPlay;
         var2 += CodedOutputStream.computeInt32Size(13, var99);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.unBanRank)) {
         Integer var100 = var1.unBanRank;
         var2 += CodedOutputStream.computeInt32Size(14, var100);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.unGag)) {
         Integer var101 = var1.unGag;
         var2 += CodedOutputStream.computeInt32Size(15, var101);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.unTitle)) {
         Integer var102 = var1.unTitle;
         var2 += CodedOutputStream.computeInt32Size(16, var102);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.cera)) {
         Integer var103 = var1.cera;
         var2 += CodedOutputStream.computeInt32Size(17, var103);
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.title)) {
         String var104 = var1.title;
         var2 += CodedOutputStream.computeStringSize(18, var104);
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.sender)) {
         String var105 = var1.sender;
         var2 += CodedOutputStream.computeStringSize(19, var105);
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.content)) {
         String var106 = var1.content;
         var2 += CodedOutputStream.computeStringSize(20, var106);
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.itemflag)) {
         Integer var107 = var1.itemflag;
         var2 += CodedOutputStream.computeInt32Size(21, var107);
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.subreason)) {
         Integer var108 = var1.subreason;
         var2 += CodedOutputStream.computeInt32Size(22, var108);
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.systemreq)) {
         Boolean var109 = var1.systemreq;
         var2 += CodedOutputStream.computeBoolSize(23, var109);
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.exp)) {
         Integer var110 = var1.exp;
         var2 += CodedOutputStream.computeInt32Size(24, var110);
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var111 = var1.growtype;
         var2 += CodedOutputStream.computeInt32Size(25, var111);
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.secgrowtype)) {
         Integer var112 = var1.secgrowtype;
         var2 += CodedOutputStream.computeInt32Size(26, var112);
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.reset)) {
         Boolean var113 = var1.reset;
         var2 += CodedOutputStream.computeBoolSize(27, var113);
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.qindex)) {
         Integer var114 = var1.qindex;
         var2 += CodedOutputStream.computeInt32Size(28, var114);
      }

      Object var31 = null;
      if (!CodedConstant.isNull(var1.qtype)) {
         Integer var115 = var1.qtype;
         var2 += CodedOutputStream.computeInt32Size(29, var115);
      }

      Object var32 = null;
      if (!CodedConstant.isNull(var1.flag)) {
         Boolean var116 = var1.flag;
         var2 += CodedOutputStream.computeBoolSize(30, var116);
      }

      Object var33 = null;
      if (!CodedConstant.isNull(var1.fguid)) {
         Long var117 = var1.fguid;
         var2 += CodedOutputStream.computeUInt64Size(31, var117);
      }

      Object var34 = null;
      if (!CodedConstant.isNull(var1.fromsendtime)) {
         Long var118 = var1.fromsendtime;
         var2 += CodedOutputStream.computeInt64Size(32, var118);
      }

      Object var35 = null;
      if (!CodedConstant.isNull(var1.closenessguid)) {
         Long var119 = var1.closenessguid;
         var2 += CodedOutputStream.computeUInt64Size(33, var119);
      }

      Object var36 = null;
      if (!CodedConstant.isNull(var1.items)) {
         List var120 = var1.items;
         var2 += CodedConstant.computeListSize(34, var120, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var37 = null;
      if (!CodedConstant.isNull(var1.cmd)) {
         Integer var121 = var1.cmd;
         var2 += CodedOutputStream.computeInt32Size(35, var121);
      }

      Object var38 = null;
      if (!CodedConstant.isNull(var1.source)) {
         Integer var122 = var1.source;
         var2 += CodedOutputStream.computeInt32Size(36, var122);
      }

      Object var39 = null;
      if (!CodedConstant.isNull(var1.serial)) {
         String var123 = var1.serial;
         var2 += CodedOutputStream.computeStringSize(37, var123);
      }

      Object var40 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var124 = var1.dungeonindex;
         var2 += CodedOutputStream.computeInt32Size(38, var124);
      }

      Object var41 = null;
      if (!CodedConstant.isNull(var1.withdrawaltime)) {
         Long var125 = var1.withdrawaltime;
         var2 += CodedOutputStream.computeInt64Size(39, var125);
      }

      Object var42 = null;
      if (!CodedConstant.isNull(var1.kickout)) {
         Boolean var126 = var1.kickout;
         var2 += CodedOutputStream.computeBoolSize(40, var126);
      }

      Object var43 = null;
      if (!CodedConstant.isNull(var1.expireday)) {
         Integer var127 = var1.expireday;
         var2 += CodedOutputStream.computeInt32Size(41, var127);
      }

      Object var44 = null;
      if (!CodedConstant.isNull(var1.matchtype)) {
         Integer var128 = var1.matchtype;
         var2 += CodedOutputStream.computeInt32Size(42, var128);
      }

      Object var45 = null;
      if (!CodedConstant.isNull(var1.result)) {
         Integer var129 = var1.result;
         var2 += CodedOutputStream.computeInt32Size(43, var129);
      }

      Object var46 = null;
      if (!CodedConstant.isNull(var1.status)) {
         Integer var130 = var1.status;
         var2 += CodedOutputStream.computeInt32Size(44, var130);
      }

      Object var47 = null;
      if (!CodedConstant.isNull(var1.ison)) {
         Boolean var131 = var1.ison;
         var2 += CodedOutputStream.computeBoolSize(45, var131);
      }

      Object var48 = null;
      if (!CodedConstant.isNull(var1.shopid)) {
         Integer var132 = var1.shopid;
         var2 += CodedOutputStream.computeInt32Size(46, var132);
      }

      Object var49 = null;
      if (!CodedConstant.isNull(var1.lists)) {
         List var133 = var1.lists;
         var2 += CodedConstant.computeListSize(47, var133, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var50 = null;
      if (!CodedConstant.isNull(var1.goodsid)) {
         Integer var134 = var1.goodsid;
         var2 += CodedOutputStream.computeInt32Size(48, var134);
      }

      Object var51 = null;
      if (!CodedConstant.isNull(var1.amount)) {
         Integer var135 = var1.amount;
         var2 += CodedOutputStream.computeInt32Size(49, var135);
      }

      Object var52 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var136 = var1.count;
         var2 += CodedOutputStream.computeInt32Size(50, var136);
      }

      Object var53 = null;
      if (!CodedConstant.isNull(var1.buycount)) {
         Integer var137 = var1.buycount;
         var2 += CodedOutputStream.computeInt32Size(51, var137);
      }

      Object var54 = null;
      if (!CodedConstant.isNull(var1.packageindex)) {
         Integer var138 = var1.packageindex;
         var2 += CodedOutputStream.computeInt32Size(52, var138);
      }

      Object var55 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var139 = var1.guid;
         var2 += CodedOutputStream.computeUInt64Size(53, var139);
      }

      Object var56 = null;
      if (!CodedConstant.isNull(var1.msg)) {
         String var140 = var1.msg;
         var2 += CodedOutputStream.computeStringSize(54, var140);
      }

      Object var57 = null;
      if (!CodedConstant.isNull(var1.itemindex)) {
         Integer var141 = var1.itemindex;
         var2 += CodedOutputStream.computeInt32Size(55, var141);
      }

      Object var58 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var142 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(56, var142);
      }

      Object var59 = null;
      if (!CodedConstant.isNull(var1.receiverguid)) {
         Long var143 = var1.receiverguid;
         var2 += CodedOutputStream.computeUInt64Size(57, var143);
      }

      Object var60 = null;
      if (!CodedConstant.isNull(var1.boardtype)) {
         Integer var144 = var1.boardtype;
         var2 += CodedOutputStream.computeInt32Size(58, var144);
      }

      Object var61 = null;
      if (!CodedConstant.isNull(var1.reactiontype)) {
         Integer var145 = var1.reactiontype;
         var2 += CodedOutputStream.computeInt32Size(59, var145);
      }

      Object var62 = null;
      if (!CodedConstant.isNull(var1.noteguid)) {
         Long var146 = var1.noteguid;
         var2 += CodedOutputStream.computeUInt64Size(60, var146);
      }

      Object var63 = null;
      if (!CodedConstant.isNull(var1.fopenid)) {
         String var147 = var1.fopenid;
         var2 += CodedOutputStream.computeStringSize(61, var147);
      }

      Object var64 = null;
      if (!CodedConstant.isNull(var1.openid)) {
         String var148 = var1.openid;
         var2 += CodedOutputStream.computeStringSize(62, var148);
      }

      Object var65 = null;
      if (!CodedConstant.isNull(var1.accountkey)) {
         Long var149 = var1.accountkey;
         var2 += CodedOutputStream.computeUInt64Size(63, var149);
      }

      Object var66 = null;
      if (!CodedConstant.isNull(var1.itemguid)) {
         Long var150 = var1.itemguid;
         var2 += CodedOutputStream.computeUInt64Size(64, var150);
      }

      Object var67 = null;
      if (!CodedConstant.isNull(var1.inventype)) {
         Integer var151 = var1.inventype;
         var2 += CodedOutputStream.computeInt32Size(65, var151);
      }

      Object var68 = null;
      if (!CodedConstant.isNull(var1.upgrade)) {
         Integer var152 = var1.upgrade;
         var2 += CodedOutputStream.computeInt32Size(66, var152);
      }

      Object var69 = null;
      if (!CodedConstant.isNull(var1.reforge)) {
         Integer var153 = var1.reforge;
         var2 += CodedOutputStream.computeInt32Size(67, var153);
      }

      Object var70 = null;
      if (!CodedConstant.isNull(var1.endurance)) {
         Integer var154 = var1.endurance;
         var2 += CodedOutputStream.computeInt32Size(68, var154);
      }

      Object var71 = null;
      if (!CodedConstant.isNull(var1.changename)) {
         String var155 = var1.changename;
         var2 += CodedOutputStream.computeStringSize(69, var155);
      }

      Object var72 = null;
      if (!CodedConstant.isNull(var1.gguid)) {
         Long var156 = var1.gguid;
         var2 += CodedOutputStream.computeUInt64Size(70, var156);
      }

      Object var73 = null;
      if (!CodedConstant.isNull(var1.endtime)) {
         Long var157 = var1.endtime;
         var2 += CodedOutputStream.computeInt64Size(71, var157);
      }

      Object var74 = null;
      if (!CodedConstant.isNull(var1.packages)) {
         List var158 = var1.packages;
         var2 += CodedConstant.computeListSize(72, var158, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var75 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var159 = var1.world;
         var2 += CodedOutputStream.computeInt32Size(73, var159);
      }

      Object var76 = null;
      if (!CodedConstant.isNull(var1.channel)) {
         Integer var160 = var1.channel;
         var2 += CodedOutputStream.computeInt32Size(74, var160);
      }

      Object var77 = null;
      if (!CodedConstant.isNull(var1.takeitem)) {
         PT_SYSCMD_COMMAND_TAKE_ITEM var161 = var1.takeitem;
         var2 += CodedConstant.computeSize(75, var161, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var78 = null;
      if (!CodedConstant.isNull(var1.createtime)) {
         Long var162 = var1.createtime;
         var2 += CodedOutputStream.computeInt64Size(76, var162);
      }

      Object var79 = null;
      if (!CodedConstant.isNull(var1.party)) {
         PT_SYSCMD_PARTY var163 = var1.party;
         var2 += CodedConstant.computeSize(77, var163, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var80 = null;
      if (!CodedConstant.isNull(var1.importance)) {
         Integer var164 = var1.importance;
         var2 += CodedOutputStream.computeInt32Size(78, var164);
      }

      Object var81 = null;
      if (!CodedConstant.isNull(var1.needcare)) {
         Boolean var165 = var1.needcare;
         var2 += CodedOutputStream.computeBoolSize(79, var165);
      }

      Object var82 = null;
      if (!CodedConstant.isNull(var1.faileddelete)) {
         Boolean var166 = var1.faileddelete;
         var2 += CodedOutputStream.computeBoolSize(80, var166);
      }

      Object var83 = null;
      if (!CodedConstant.isNull(var1.orderId)) {
         String var167 = var1.orderId;
         var2 += CodedOutputStream.computeStringSize(81, var167);
      }

      Object var84 = null;
      if (!CodedConstant.isNull(var1.reasonOpt)) {
         String var168 = var1.reasonOpt;
         var2 += CodedOutputStream.computeStringSize(82, var168);
      }

      Object var85 = null;
      if (!CodedConstant.isNull(var1.masterbuff)) {
         List var169 = var1.masterbuff;
         var2 += CodedConstant.computeListSize(83, var169, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var86 = null;
      if (!CodedConstant.isNull(var1.mailType)) {
         String var170 = var1.mailType;
         var2 += CodedOutputStream.computeStringSize(84, var170);
      }

      return var2;
   }

   public void doWriteTo(PT_SYSTEM_COMMAND var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.commands)) {
         PT_SYSCMD_COMMAND var87 = var1.commands;
         if (var87 != null) {
            CodedConstant.writeObject(var2, 1, FieldType.OBJECT, var87, false);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.dungeon)) {
         Integer var88 = var1.dungeon;
         if (var88 != null) {
            var2.writeInt32(2, var88);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.value)) {
         byte[] var89 = var1.value;
         if (var89 != null) {
            var2.writeByteArray(3, var89);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.reason)) {
         String var90 = var1.reason;
         if (var90 != null) {
            var2.writeString(4, var90);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var91 = var1.type;
         if (var91 != null) {
            var2.writeUInt32(5, var91);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.time)) {
         Long var92 = var1.time;
         if (var92 != null) {
            var2.writeUInt64(6, var92);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.idip)) {
         Integer var93 = var1.idip;
         if (var93 != null) {
            var2.writeInt32(7, var93);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.platID)) {
         Integer var94 = var1.platID;
         if (var94 != null) {
            var2.writeInt32(8, var94);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var95 = var1.charguid;
         if (var95 != null) {
            var2.writeUInt64(9, var95);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.subtype)) {
         Integer var96 = var1.subtype;
         if (var96 != null) {
            var2.writeInt32(10, var96);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.addtime)) {
         Long var97 = var1.addtime;
         if (var97 != null) {
            var2.writeUInt64(11, var97);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.zeroBenefit)) {
         Integer var98 = var1.zeroBenefit;
         if (var98 != null) {
            var2.writeInt32(12, var98);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.unPlay)) {
         Integer var99 = var1.unPlay;
         if (var99 != null) {
            var2.writeInt32(13, var99);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.unBanRank)) {
         Integer var100 = var1.unBanRank;
         if (var100 != null) {
            var2.writeInt32(14, var100);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.unGag)) {
         Integer var101 = var1.unGag;
         if (var101 != null) {
            var2.writeInt32(15, var101);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.unTitle)) {
         Integer var102 = var1.unTitle;
         if (var102 != null) {
            var2.writeInt32(16, var102);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.cera)) {
         Integer var103 = var1.cera;
         if (var103 != null) {
            var2.writeInt32(17, var103);
         }
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.title)) {
         String var104 = var1.title;
         if (var104 != null) {
            var2.writeString(18, var104);
         }
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.sender)) {
         String var105 = var1.sender;
         if (var105 != null) {
            var2.writeString(19, var105);
         }
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.content)) {
         String var106 = var1.content;
         if (var106 != null) {
            var2.writeString(20, var106);
         }
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.itemflag)) {
         Integer var107 = var1.itemflag;
         if (var107 != null) {
            var2.writeInt32(21, var107);
         }
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.subreason)) {
         Integer var108 = var1.subreason;
         if (var108 != null) {
            var2.writeInt32(22, var108);
         }
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.systemreq)) {
         Boolean var109 = var1.systemreq;
         if (var109 != null) {
            var2.writeBool(23, var109);
         }
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.exp)) {
         Integer var110 = var1.exp;
         if (var110 != null) {
            var2.writeInt32(24, var110);
         }
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var111 = var1.growtype;
         if (var111 != null) {
            var2.writeInt32(25, var111);
         }
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.secgrowtype)) {
         Integer var112 = var1.secgrowtype;
         if (var112 != null) {
            var2.writeInt32(26, var112);
         }
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.reset)) {
         Boolean var113 = var1.reset;
         if (var113 != null) {
            var2.writeBool(27, var113);
         }
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.qindex)) {
         Integer var114 = var1.qindex;
         if (var114 != null) {
            var2.writeInt32(28, var114);
         }
      }

      Object var31 = null;
      if (!CodedConstant.isNull(var1.qtype)) {
         Integer var115 = var1.qtype;
         if (var115 != null) {
            var2.writeInt32(29, var115);
         }
      }

      Object var32 = null;
      if (!CodedConstant.isNull(var1.flag)) {
         Boolean var116 = var1.flag;
         if (var116 != null) {
            var2.writeBool(30, var116);
         }
      }

      Object var33 = null;
      if (!CodedConstant.isNull(var1.fguid)) {
         Long var117 = var1.fguid;
         if (var117 != null) {
            var2.writeUInt64(31, var117);
         }
      }

      Object var34 = null;
      if (!CodedConstant.isNull(var1.fromsendtime)) {
         Long var118 = var1.fromsendtime;
         if (var118 != null) {
            var2.writeInt64(32, var118);
         }
      }

      Object var35 = null;
      if (!CodedConstant.isNull(var1.closenessguid)) {
         Long var119 = var1.closenessguid;
         if (var119 != null) {
            var2.writeUInt64(33, var119);
         }
      }

      Object var36 = null;
      if (!CodedConstant.isNull(var1.items)) {
         List var120 = var1.items;
         if (var120 != null) {
            CodedConstant.writeToList(var2, 34, FieldType.OBJECT, var120, false);
         }
      }

      Object var37 = null;
      if (!CodedConstant.isNull(var1.cmd)) {
         Integer var121 = var1.cmd;
         if (var121 != null) {
            var2.writeInt32(35, var121);
         }
      }

      Object var38 = null;
      if (!CodedConstant.isNull(var1.source)) {
         Integer var122 = var1.source;
         if (var122 != null) {
            var2.writeInt32(36, var122);
         }
      }

      Object var39 = null;
      if (!CodedConstant.isNull(var1.serial)) {
         String var123 = var1.serial;
         if (var123 != null) {
            var2.writeString(37, var123);
         }
      }

      Object var40 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var124 = var1.dungeonindex;
         if (var124 != null) {
            var2.writeInt32(38, var124);
         }
      }

      Object var41 = null;
      if (!CodedConstant.isNull(var1.withdrawaltime)) {
         Long var125 = var1.withdrawaltime;
         if (var125 != null) {
            var2.writeInt64(39, var125);
         }
      }

      Object var42 = null;
      if (!CodedConstant.isNull(var1.kickout)) {
         Boolean var126 = var1.kickout;
         if (var126 != null) {
            var2.writeBool(40, var126);
         }
      }

      Object var43 = null;
      if (!CodedConstant.isNull(var1.expireday)) {
         Integer var127 = var1.expireday;
         if (var127 != null) {
            var2.writeInt32(41, var127);
         }
      }

      Object var44 = null;
      if (!CodedConstant.isNull(var1.matchtype)) {
         Integer var128 = var1.matchtype;
         if (var128 != null) {
            var2.writeInt32(42, var128);
         }
      }

      Object var45 = null;
      if (!CodedConstant.isNull(var1.result)) {
         Integer var129 = var1.result;
         if (var129 != null) {
            var2.writeInt32(43, var129);
         }
      }

      Object var46 = null;
      if (!CodedConstant.isNull(var1.status)) {
         Integer var130 = var1.status;
         if (var130 != null) {
            var2.writeInt32(44, var130);
         }
      }

      Object var47 = null;
      if (!CodedConstant.isNull(var1.ison)) {
         Boolean var131 = var1.ison;
         if (var131 != null) {
            var2.writeBool(45, var131);
         }
      }

      Object var48 = null;
      if (!CodedConstant.isNull(var1.shopid)) {
         Integer var132 = var1.shopid;
         if (var132 != null) {
            var2.writeInt32(46, var132);
         }
      }

      Object var49 = null;
      if (!CodedConstant.isNull(var1.lists)) {
         List var133 = var1.lists;
         if (var133 != null) {
            CodedConstant.writeToList(var2, 47, FieldType.OBJECT, var133, false);
         }
      }

      Object var50 = null;
      if (!CodedConstant.isNull(var1.goodsid)) {
         Integer var134 = var1.goodsid;
         if (var134 != null) {
            var2.writeInt32(48, var134);
         }
      }

      Object var51 = null;
      if (!CodedConstant.isNull(var1.amount)) {
         Integer var135 = var1.amount;
         if (var135 != null) {
            var2.writeInt32(49, var135);
         }
      }

      Object var52 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var136 = var1.count;
         if (var136 != null) {
            var2.writeInt32(50, var136);
         }
      }

      Object var53 = null;
      if (!CodedConstant.isNull(var1.buycount)) {
         Integer var137 = var1.buycount;
         if (var137 != null) {
            var2.writeInt32(51, var137);
         }
      }

      Object var54 = null;
      if (!CodedConstant.isNull(var1.packageindex)) {
         Integer var138 = var1.packageindex;
         if (var138 != null) {
            var2.writeInt32(52, var138);
         }
      }

      Object var55 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var139 = var1.guid;
         if (var139 != null) {
            var2.writeUInt64(53, var139);
         }
      }

      Object var56 = null;
      if (!CodedConstant.isNull(var1.msg)) {
         String var140 = var1.msg;
         if (var140 != null) {
            var2.writeString(54, var140);
         }
      }

      Object var57 = null;
      if (!CodedConstant.isNull(var1.itemindex)) {
         Integer var141 = var1.itemindex;
         if (var141 != null) {
            var2.writeInt32(55, var141);
         }
      }

      Object var58 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var142 = var1.index;
         if (var142 != null) {
            var2.writeInt32(56, var142);
         }
      }

      Object var59 = null;
      if (!CodedConstant.isNull(var1.receiverguid)) {
         Long var143 = var1.receiverguid;
         if (var143 != null) {
            var2.writeUInt64(57, var143);
         }
      }

      Object var60 = null;
      if (!CodedConstant.isNull(var1.boardtype)) {
         Integer var144 = var1.boardtype;
         if (var144 != null) {
            var2.writeInt32(58, var144);
         }
      }

      Object var61 = null;
      if (!CodedConstant.isNull(var1.reactiontype)) {
         Integer var145 = var1.reactiontype;
         if (var145 != null) {
            var2.writeInt32(59, var145);
         }
      }

      Object var62 = null;
      if (!CodedConstant.isNull(var1.noteguid)) {
         Long var146 = var1.noteguid;
         if (var146 != null) {
            var2.writeUInt64(60, var146);
         }
      }

      Object var63 = null;
      if (!CodedConstant.isNull(var1.fopenid)) {
         String var147 = var1.fopenid;
         if (var147 != null) {
            var2.writeString(61, var147);
         }
      }

      Object var64 = null;
      if (!CodedConstant.isNull(var1.openid)) {
         String var148 = var1.openid;
         if (var148 != null) {
            var2.writeString(62, var148);
         }
      }

      Object var65 = null;
      if (!CodedConstant.isNull(var1.accountkey)) {
         Long var149 = var1.accountkey;
         if (var149 != null) {
            var2.writeUInt64(63, var149);
         }
      }

      Object var66 = null;
      if (!CodedConstant.isNull(var1.itemguid)) {
         Long var150 = var1.itemguid;
         if (var150 != null) {
            var2.writeUInt64(64, var150);
         }
      }

      Object var67 = null;
      if (!CodedConstant.isNull(var1.inventype)) {
         Integer var151 = var1.inventype;
         if (var151 != null) {
            var2.writeInt32(65, var151);
         }
      }

      Object var68 = null;
      if (!CodedConstant.isNull(var1.upgrade)) {
         Integer var152 = var1.upgrade;
         if (var152 != null) {
            var2.writeInt32(66, var152);
         }
      }

      Object var69 = null;
      if (!CodedConstant.isNull(var1.reforge)) {
         Integer var153 = var1.reforge;
         if (var153 != null) {
            var2.writeInt32(67, var153);
         }
      }

      Object var70 = null;
      if (!CodedConstant.isNull(var1.endurance)) {
         Integer var154 = var1.endurance;
         if (var154 != null) {
            var2.writeInt32(68, var154);
         }
      }

      Object var71 = null;
      if (!CodedConstant.isNull(var1.changename)) {
         String var155 = var1.changename;
         if (var155 != null) {
            var2.writeString(69, var155);
         }
      }

      Object var72 = null;
      if (!CodedConstant.isNull(var1.gguid)) {
         Long var156 = var1.gguid;
         if (var156 != null) {
            var2.writeUInt64(70, var156);
         }
      }

      Object var73 = null;
      if (!CodedConstant.isNull(var1.endtime)) {
         Long var157 = var1.endtime;
         if (var157 != null) {
            var2.writeInt64(71, var157);
         }
      }

      Object var74 = null;
      if (!CodedConstant.isNull(var1.packages)) {
         List var158 = var1.packages;
         if (var158 != null) {
            CodedConstant.writeToList(var2, 72, FieldType.OBJECT, var158, false);
         }
      }

      Object var75 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var159 = var1.world;
         if (var159 != null) {
            var2.writeInt32(73, var159);
         }
      }

      Object var76 = null;
      if (!CodedConstant.isNull(var1.channel)) {
         Integer var160 = var1.channel;
         if (var160 != null) {
            var2.writeInt32(74, var160);
         }
      }

      Object var77 = null;
      if (!CodedConstant.isNull(var1.takeitem)) {
         PT_SYSCMD_COMMAND_TAKE_ITEM var161 = var1.takeitem;
         if (var161 != null) {
            CodedConstant.writeObject(var2, 75, FieldType.OBJECT, var161, false);
         }
      }

      Object var78 = null;
      if (!CodedConstant.isNull(var1.createtime)) {
         Long var162 = var1.createtime;
         if (var162 != null) {
            var2.writeInt64(76, var162);
         }
      }

      Object var79 = null;
      if (!CodedConstant.isNull(var1.party)) {
         PT_SYSCMD_PARTY var163 = var1.party;
         if (var163 != null) {
            CodedConstant.writeObject(var2, 77, FieldType.OBJECT, var163, false);
         }
      }

      Object var80 = null;
      if (!CodedConstant.isNull(var1.importance)) {
         Integer var164 = var1.importance;
         if (var164 != null) {
            var2.writeInt32(78, var164);
         }
      }

      Object var81 = null;
      if (!CodedConstant.isNull(var1.needcare)) {
         Boolean var165 = var1.needcare;
         if (var165 != null) {
            var2.writeBool(79, var165);
         }
      }

      Object var82 = null;
      if (!CodedConstant.isNull(var1.faileddelete)) {
         Boolean var166 = var1.faileddelete;
         if (var166 != null) {
            var2.writeBool(80, var166);
         }
      }

      Object var83 = null;
      if (!CodedConstant.isNull(var1.orderId)) {
         String var167 = var1.orderId;
         if (var167 != null) {
            var2.writeString(81, var167);
         }
      }

      Object var84 = null;
      if (!CodedConstant.isNull(var1.reasonOpt)) {
         String var168 = var1.reasonOpt;
         if (var168 != null) {
            var2.writeString(82, var168);
         }
      }

      Object var85 = null;
      if (!CodedConstant.isNull(var1.masterbuff)) {
         List var169 = var1.masterbuff;
         if (var169 != null) {
            CodedConstant.writeToList(var2, 83, FieldType.OBJECT, var169, false);
         }
      }

      Object var86 = null;
      if (!CodedConstant.isNull(var1.mailType)) {
         String var170 = var1.mailType;
         if (var170 != null) {
            var2.writeString(84, var170);
         }
      }

   }

   public void writeTo(PT_SYSTEM_COMMAND var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_SYSTEM_COMMAND readFrom(CodedInputStream var1) throws IOException {
      PT_SYSTEM_COMMAND var2 = new PT_SYSTEM_COMMAND();
      var2.items = new ArrayList();
      var2.lists = new ArrayList();
      var2.packages = new ArrayList();
      var2.masterbuff = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 10) {
               Codec var10 = ProtobufProxy.create(PT_SYSCMD_COMMAND.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.commands = (PT_SYSCMD_COMMAND)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 16) {
               var2.dungeon = var1.readInt32();
            } else if (var5 == 26) {
               var2.value = var1.readBytes().toByteArray();
            } else if (var5 == 34) {
               var2.reason = var1.readString();
            } else if (var5 == 40) {
               var2.type = var1.readUInt32();
            } else if (var5 == 48) {
               var2.time = var1.readUInt64();
            } else if (var5 == 56) {
               var2.idip = var1.readInt32();
            } else if (var5 == 64) {
               var2.platID = var1.readInt32();
            } else if (var5 == 72) {
               var2.charguid = var1.readUInt64();
            } else if (var5 == 80) {
               var2.subtype = var1.readInt32();
            } else if (var5 == 88) {
               var2.addtime = var1.readUInt64();
            } else if (var5 == 96) {
               var2.zeroBenefit = var1.readInt32();
            } else if (var5 == 104) {
               var2.unPlay = var1.readInt32();
            } else if (var5 == 112) {
               var2.unBanRank = var1.readInt32();
            } else if (var5 == 120) {
               var2.unGag = var1.readInt32();
            } else if (var5 == 128) {
               var2.unTitle = var1.readInt32();
            } else if (var5 == 136) {
               var2.cera = var1.readInt32();
            } else if (var5 == 146) {
               var2.title = var1.readString();
            } else if (var5 == 154) {
               var2.sender = var1.readString();
            } else if (var5 == 162) {
               var2.content = var1.readString();
            } else if (var5 == 168) {
               var2.itemflag = var1.readInt32();
            } else if (var5 == 176) {
               var2.subreason = var1.readInt32();
            } else if (var5 == 184) {
               var2.systemreq = var1.readBool();
            } else if (var5 == 192) {
               var2.exp = var1.readInt32();
            } else if (var5 == 200) {
               var2.growtype = var1.readInt32();
            } else if (var5 == 208) {
               var2.secgrowtype = var1.readInt32();
            } else if (var5 == 216) {
               var2.reset = var1.readBool();
            } else if (var5 == 224) {
               var2.qindex = var1.readInt32();
            } else if (var5 == 232) {
               var2.qtype = var1.readInt32();
            } else if (var5 == 240) {
               var2.flag = var1.readBool();
            } else if (var5 == 248) {
               var2.fguid = var1.readUInt64();
            } else if (var5 == 256) {
               var2.fromsendtime = var1.readInt64();
            } else if (var5 == 264) {
               var2.closenessguid = var1.readUInt64();
            } else if (var5 == 274) {
               Codec var11 = ProtobufProxy.create(PT_SYSCMD_ITEMJSON.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var17 = var1.readRawVarint32();
               int var23 = var1.pushLimit(var17);
               if (var2.items == null) {
                  var2.items = new ArrayList();
               }

               var2.items.add((PT_SYSCMD_ITEMJSON)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var23);
            } else if (var5 == 280) {
               var2.cmd = var1.readInt32();
            } else if (var5 == 288) {
               var2.source = var1.readInt32();
            } else if (var5 == 298) {
               var2.serial = var1.readString();
            } else if (var5 == 304) {
               var2.dungeonindex = var1.readInt32();
            } else if (var5 == 312) {
               var2.withdrawaltime = var1.readInt64();
            } else if (var5 == 320) {
               var2.kickout = var1.readBool();
            } else if (var5 == 328) {
               var2.expireday = var1.readInt32();
            } else if (var5 == 336) {
               var2.matchtype = var1.readInt32();
            } else if (var5 == 344) {
               var2.result = var1.readInt32();
            } else if (var5 == 352) {
               var2.status = var1.readInt32();
            } else if (var5 == 360) {
               var2.ison = var1.readBool();
            } else if (var5 == 368) {
               var2.shopid = var1.readInt32();
            } else if (var5 == 378) {
               Codec var12 = ProtobufProxy.create(PT_SYSCMD_CERASHOP.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var18 = var1.readRawVarint32();
               int var24 = var1.pushLimit(var18);
               if (var2.lists == null) {
                  var2.lists = new ArrayList();
               }

               var2.lists.add((PT_SYSCMD_CERASHOP)var12.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var24);
            } else if (var5 == 384) {
               var2.goodsid = var1.readInt32();
            } else if (var5 == 392) {
               var2.amount = var1.readInt32();
            } else if (var5 == 400) {
               var2.count = var1.readInt32();
            } else if (var5 == 408) {
               var2.buycount = var1.readInt32();
            } else if (var5 == 416) {
               var2.packageindex = var1.readInt32();
            } else if (var5 == 424) {
               var2.guid = var1.readUInt64();
            } else if (var5 == 434) {
               var2.msg = var1.readString();
            } else if (var5 == 440) {
               var2.itemindex = var1.readInt32();
            } else if (var5 == 448) {
               var2.index = var1.readInt32();
            } else if (var5 == 456) {
               var2.receiverguid = var1.readUInt64();
            } else if (var5 == 464) {
               var2.boardtype = var1.readInt32();
            } else if (var5 == 472) {
               var2.reactiontype = var1.readInt32();
            } else if (var5 == 480) {
               var2.noteguid = var1.readUInt64();
            } else if (var5 == 490) {
               var2.fopenid = var1.readString();
            } else if (var5 == 498) {
               var2.openid = var1.readString();
            } else if (var5 == 504) {
               var2.accountkey = var1.readUInt64();
            } else if (var5 == 512) {
               var2.itemguid = var1.readUInt64();
            } else if (var5 == 520) {
               var2.inventype = var1.readInt32();
            } else if (var5 == 528) {
               var2.upgrade = var1.readInt32();
            } else if (var5 == 536) {
               var2.reforge = var1.readInt32();
            } else if (var5 == 544) {
               var2.endurance = var1.readInt32();
            } else if (var5 == 554) {
               var2.changename = var1.readString();
            } else if (var5 == 560) {
               var2.gguid = var1.readUInt64();
            } else if (var5 == 568) {
               var2.endtime = var1.readInt64();
            } else if (var5 == 578) {
               Codec var13 = ProtobufProxy.create(PT_SYSCMD_PACKAGES.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var19 = var1.readRawVarint32();
               int var25 = var1.pushLimit(var19);
               if (var2.packages == null) {
                  var2.packages = new ArrayList();
               }

               var2.packages.add((PT_SYSCMD_PACKAGES)var13.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var25);
            } else if (var5 == 584) {
               var2.world = var1.readInt32();
            } else if (var5 == 592) {
               var2.channel = var1.readInt32();
            } else if (var5 == 602) {
               Codec var14 = ProtobufProxy.create(PT_SYSCMD_COMMAND_TAKE_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var20 = var1.readRawVarint32();
               int var26 = var1.pushLimit(var20);
               var2.takeitem = (PT_SYSCMD_COMMAND_TAKE_ITEM)var14.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var26);
            } else if (var5 == 608) {
               var2.createtime = var1.readInt64();
            } else if (var5 == 618) {
               Codec var15 = ProtobufProxy.create(PT_SYSCMD_PARTY.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var21 = var1.readRawVarint32();
               int var27 = var1.pushLimit(var21);
               var2.party = (PT_SYSCMD_PARTY)var15.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var27);
            } else if (var5 == 624) {
               var2.importance = var1.readInt32();
            } else if (var5 == 632) {
               var2.needcare = var1.readBool();
            } else if (var5 == 640) {
               var2.faileddelete = var1.readBool();
            } else if (var5 == 650) {
               var2.orderId = var1.readString();
            } else if (var5 == 658) {
               var2.reasonOpt = var1.readString();
            } else if (var5 == 666) {
               Codec var16 = ProtobufProxy.create(PT_SYSCMD_MASTERBUFF.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var22 = var1.readRawVarint32();
               int var28 = var1.pushLimit(var22);
               if (var2.masterbuff == null) {
                  var2.masterbuff = new ArrayList();
               }

               var2.masterbuff.add((PT_SYSCMD_MASTERBUFF)var16.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var28);
            } else if (var5 == 674) {
               var2.mailType = var1.readString();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_SYSTEM_COMMAND.class);
         return this.descriptor = var1;
      }
   }
}
