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

public class NOTIFY_CONTROL_GROUP$$JProtoBufClass implements Codec<NOTIFY_CONTROL_GROUP>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(NOTIFY_CONTROL_GROUP var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public NOTIFY_CONTROL_GROUP decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(NOTIFY_CONTROL_GROUP var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var46 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var46);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var47 = var1.world;
         var2 += CodedOutputStream.computeInt32Size(2, var47);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.channel)) {
         Integer var48 = var1.channel;
         var2 += CodedOutputStream.computeInt32Size(3, var48);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var49 = var1.partyguid;
         var2 += CodedOutputStream.computeUInt64Size(4, var49);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.partyleaderguid)) {
         Long var50 = var1.partyleaderguid;
         var2 += CodedOutputStream.computeUInt64Size(5, var50);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.ip)) {
         String var51 = var1.ip;
         var2 += CodedOutputStream.computeStringSize(6, var51);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.port)) {
         Integer var52 = var1.port;
         var2 += CodedOutputStream.computeInt32Size(7, var52);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var53 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(8, var53);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var54 = var1.name;
         var2 += CodedOutputStream.computeStringSize(9, var54);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.gname)) {
         String var55 = var1.gname;
         var2 += CodedOutputStream.computeStringSize(10, var55);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.equipscore)) {
         Integer var56 = var1.equipscore;
         var2 += CodedOutputStream.computeInt32Size(11, var56);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var57 = var1.growtype;
         var2 += CodedOutputStream.computeInt32Size(12, var57);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var58 = var1.secondgrowtype;
         var2 += CodedOutputStream.computeInt32Size(13, var58);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var59 = var1.job;
         var2 += CodedOutputStream.computeInt32Size(14, var59);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var60 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(15, var60);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.minlevel)) {
         Integer var61 = var1.minlevel;
         var2 += CodedOutputStream.computeInt32Size(16, var61);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.maxlevel)) {
         Integer var62 = var1.maxlevel;
         var2 += CodedOutputStream.computeInt32Size(17, var62);
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.town)) {
         Integer var63 = var1.town;
         var2 += CodedOutputStream.computeInt32Size(18, var63);
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.area)) {
         Integer var64 = var1.area;
         var2 += CodedOutputStream.computeInt32Size(19, var64);
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.friend)) {
         Boolean var65 = var1.friend;
         var2 += CodedOutputStream.computeBoolSize(20, var65);
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.guild)) {
         Boolean var66 = var1.guild;
         var2 += CodedOutputStream.computeBoolSize(21, var66);
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.creditscore)) {
         Integer var67 = var1.creditscore;
         var2 += CodedOutputStream.computeInt32Size(22, var67);
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.list)) {
         List var68 = var1.list;
         var2 += CodedConstant.computeListSize(23, var68, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.state)) {
         Integer var69 = var1.state;
         var2 += CodedOutputStream.computeInt32Size(24, var69);
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.targetguid)) {
         Long var70 = var1.targetguid;
         var2 += CodedOutputStream.computeUInt64Size(25, var70);
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var71 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(26, var71);
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var72 = var1.dungeonindex;
         var2 += CodedOutputStream.computeInt32Size(27, var72);
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.matchtype)) {
         Integer var73 = var1.matchtype;
         var2 += CodedOutputStream.computeInt32Size(28, var73);
      }

      Object var31 = null;
      if (!CodedConstant.isNull(var1.subtype)) {
         Integer var74 = var1.subtype;
         var2 += CodedOutputStream.computeInt32Size(29, var74);
      }

      Object var32 = null;
      if (!CodedConstant.isNull(var1.max)) {
         Integer var75 = var1.max;
         var2 += CodedOutputStream.computeInt32Size(30, var75);
      }

      Object var33 = null;
      if (!CodedConstant.isNull(var1.contentlockstate)) {
         Integer var76 = var1.contentlockstate;
         var2 += CodedOutputStream.computeInt32Size(31, var76);
      }

      Object var34 = null;
      if (!CodedConstant.isNull(var1.password)) {
         Integer var77 = var1.password;
         var2 += CodedOutputStream.computeInt32Size(32, var77);
      }

      Object var35 = null;
      if (!CodedConstant.isNull(var1.stageindex)) {
         Integer var78 = var1.stageindex;
         var2 += CodedOutputStream.computeInt32Size(33, var78);
      }

      Object var36 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var79 = var1.grade;
         var2 += CodedOutputStream.computeInt32Size(34, var79);
      }

      Object var37 = null;
      if (!CodedConstant.isNull(var1.partyname)) {
         String var80 = var1.partyname;
         var2 += CodedOutputStream.computeStringSize(35, var80);
      }

      Object var38 = null;
      if (!CodedConstant.isNull(var1.publictype)) {
         Integer var81 = var1.publictype;
         var2 += CodedOutputStream.computeInt32Size(36, var81);
      }

      Object var39 = null;
      if (!CodedConstant.isNull(var1.users)) {
         List var82 = var1.users;
         var2 += CodedConstant.computeListSize(37, var82, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var40 = null;
      if (!CodedConstant.isNull(var1.customdata)) {
         PT_CUSTOM_DATA var83 = var1.customdata;
         var2 += CodedConstant.computeSize(38, var83, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var41 = null;
      if (!CodedConstant.isNull(var1.members)) {
         List var84 = var1.members;
         var2 += CodedConstant.computeListSize(39, var84, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var42 = null;
      if (!CodedConstant.isNull(var1.targetname)) {
         String var85 = var1.targetname;
         var2 += CodedOutputStream.computeStringSize(40, var85);
      }

      Object var43 = null;
      if (!CodedConstant.isNull(var1.isobserver)) {
         Boolean var86 = var1.isobserver;
         var2 += CodedOutputStream.computeBoolSize(41, var86);
      }

      Object var44 = null;
      if (!CodedConstant.isNull(var1.isobserverchat)) {
         Boolean var87 = var1.isobserverchat;
         var2 += CodedOutputStream.computeBoolSize(42, var87);
      }

      Object var45 = null;
      if (!CodedConstant.isNull(var1.battleoptionlist)) {
         List var88 = var1.battleoptionlist;
         var2 += CodedConstant.computeListSize(43, var88, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(NOTIFY_CONTROL_GROUP var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var46 = var1.error;
         if (var46 != null) {
            var2.writeInt32(1, var46);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var47 = var1.world;
         if (var47 != null) {
            var2.writeInt32(2, var47);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.channel)) {
         Integer var48 = var1.channel;
         if (var48 != null) {
            var2.writeInt32(3, var48);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var49 = var1.partyguid;
         if (var49 != null) {
            var2.writeUInt64(4, var49);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.partyleaderguid)) {
         Long var50 = var1.partyleaderguid;
         if (var50 != null) {
            var2.writeUInt64(5, var50);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.ip)) {
         String var51 = var1.ip;
         if (var51 != null) {
            var2.writeString(6, var51);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.port)) {
         Integer var52 = var1.port;
         if (var52 != null) {
            var2.writeInt32(7, var52);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var53 = var1.type;
         if (var53 != null) {
            var2.writeInt32(8, var53);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var54 = var1.name;
         if (var54 != null) {
            var2.writeString(9, var54);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.gname)) {
         String var55 = var1.gname;
         if (var55 != null) {
            var2.writeString(10, var55);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.equipscore)) {
         Integer var56 = var1.equipscore;
         if (var56 != null) {
            var2.writeInt32(11, var56);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var57 = var1.growtype;
         if (var57 != null) {
            var2.writeInt32(12, var57);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var58 = var1.secondgrowtype;
         if (var58 != null) {
            var2.writeInt32(13, var58);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var59 = var1.job;
         if (var59 != null) {
            var2.writeInt32(14, var59);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var60 = var1.level;
         if (var60 != null) {
            var2.writeInt32(15, var60);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.minlevel)) {
         Integer var61 = var1.minlevel;
         if (var61 != null) {
            var2.writeInt32(16, var61);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.maxlevel)) {
         Integer var62 = var1.maxlevel;
         if (var62 != null) {
            var2.writeInt32(17, var62);
         }
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.town)) {
         Integer var63 = var1.town;
         if (var63 != null) {
            var2.writeInt32(18, var63);
         }
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.area)) {
         Integer var64 = var1.area;
         if (var64 != null) {
            var2.writeInt32(19, var64);
         }
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.friend)) {
         Boolean var65 = var1.friend;
         if (var65 != null) {
            var2.writeBool(20, var65);
         }
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.guild)) {
         Boolean var66 = var1.guild;
         if (var66 != null) {
            var2.writeBool(21, var66);
         }
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.creditscore)) {
         Integer var67 = var1.creditscore;
         if (var67 != null) {
            var2.writeInt32(22, var67);
         }
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.list)) {
         List var68 = var1.list;
         if (var68 != null) {
            CodedConstant.writeToList(var2, 23, FieldType.OBJECT, var68, false);
         }
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.state)) {
         Integer var69 = var1.state;
         if (var69 != null) {
            var2.writeInt32(24, var69);
         }
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.targetguid)) {
         Long var70 = var1.targetguid;
         if (var70 != null) {
            var2.writeUInt64(25, var70);
         }
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var71 = var1.charguid;
         if (var71 != null) {
            var2.writeUInt64(26, var71);
         }
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var72 = var1.dungeonindex;
         if (var72 != null) {
            var2.writeInt32(27, var72);
         }
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.matchtype)) {
         Integer var73 = var1.matchtype;
         if (var73 != null) {
            var2.writeInt32(28, var73);
         }
      }

      Object var31 = null;
      if (!CodedConstant.isNull(var1.subtype)) {
         Integer var74 = var1.subtype;
         if (var74 != null) {
            var2.writeInt32(29, var74);
         }
      }

      Object var32 = null;
      if (!CodedConstant.isNull(var1.max)) {
         Integer var75 = var1.max;
         if (var75 != null) {
            var2.writeInt32(30, var75);
         }
      }

      Object var33 = null;
      if (!CodedConstant.isNull(var1.contentlockstate)) {
         Integer var76 = var1.contentlockstate;
         if (var76 != null) {
            var2.writeInt32(31, var76);
         }
      }

      Object var34 = null;
      if (!CodedConstant.isNull(var1.password)) {
         Integer var77 = var1.password;
         if (var77 != null) {
            var2.writeInt32(32, var77);
         }
      }

      Object var35 = null;
      if (!CodedConstant.isNull(var1.stageindex)) {
         Integer var78 = var1.stageindex;
         if (var78 != null) {
            var2.writeInt32(33, var78);
         }
      }

      Object var36 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var79 = var1.grade;
         if (var79 != null) {
            var2.writeInt32(34, var79);
         }
      }

      Object var37 = null;
      if (!CodedConstant.isNull(var1.partyname)) {
         String var80 = var1.partyname;
         if (var80 != null) {
            var2.writeString(35, var80);
         }
      }

      Object var38 = null;
      if (!CodedConstant.isNull(var1.publictype)) {
         Integer var81 = var1.publictype;
         if (var81 != null) {
            var2.writeInt32(36, var81);
         }
      }

      Object var39 = null;
      if (!CodedConstant.isNull(var1.users)) {
         List var82 = var1.users;
         if (var82 != null) {
            CodedConstant.writeToList(var2, 37, FieldType.OBJECT, var82, false);
         }
      }

      Object var40 = null;
      if (!CodedConstant.isNull(var1.customdata)) {
         PT_CUSTOM_DATA var83 = var1.customdata;
         if (var83 != null) {
            CodedConstant.writeObject(var2, 38, FieldType.OBJECT, var83, false);
         }
      }

      Object var41 = null;
      if (!CodedConstant.isNull(var1.members)) {
         List var84 = var1.members;
         if (var84 != null) {
            CodedConstant.writeToList(var2, 39, FieldType.OBJECT, var84, false);
         }
      }

      Object var42 = null;
      if (!CodedConstant.isNull(var1.targetname)) {
         String var85 = var1.targetname;
         if (var85 != null) {
            var2.writeString(40, var85);
         }
      }

      Object var43 = null;
      if (!CodedConstant.isNull(var1.isobserver)) {
         Boolean var86 = var1.isobserver;
         if (var86 != null) {
            var2.writeBool(41, var86);
         }
      }

      Object var44 = null;
      if (!CodedConstant.isNull(var1.isobserverchat)) {
         Boolean var87 = var1.isobserverchat;
         if (var87 != null) {
            var2.writeBool(42, var87);
         }
      }

      Object var45 = null;
      if (!CodedConstant.isNull(var1.battleoptionlist)) {
         List var88 = var1.battleoptionlist;
         if (var88 != null) {
            CodedConstant.writeToList(var2, 43, FieldType.OBJECT, var88, false);
         }
      }

   }

   public void writeTo(NOTIFY_CONTROL_GROUP var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public NOTIFY_CONTROL_GROUP readFrom(CodedInputStream var1) throws IOException {
      NOTIFY_CONTROL_GROUP var2 = new NOTIFY_CONTROL_GROUP();
      var2.list = new ArrayList();
      var2.users = new ArrayList();
      var2.members = new ArrayList();
      var2.battleoptionlist = new ArrayList();

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
               var2.world = var1.readInt32();
            } else if (var5 == 24) {
               var2.channel = var1.readInt32();
            } else if (var5 == 32) {
               var2.partyguid = var1.readUInt64();
            } else if (var5 == 40) {
               var2.partyleaderguid = var1.readUInt64();
            } else if (var5 == 50) {
               var2.ip = var1.readString();
            } else if (var5 == 56) {
               var2.port = var1.readInt32();
            } else if (var5 == 64) {
               var2.type = var1.readInt32();
            } else if (var5 == 74) {
               var2.name = var1.readString();
            } else if (var5 == 82) {
               var2.gname = var1.readString();
            } else if (var5 == 88) {
               var2.equipscore = var1.readInt32();
            } else if (var5 == 96) {
               var2.growtype = var1.readInt32();
            } else if (var5 == 104) {
               var2.secondgrowtype = var1.readInt32();
            } else if (var5 == 112) {
               var2.job = var1.readInt32();
            } else if (var5 == 120) {
               var2.level = var1.readInt32();
            } else if (var5 == 128) {
               var2.minlevel = var1.readInt32();
            } else if (var5 == 136) {
               var2.maxlevel = var1.readInt32();
            } else if (var5 == 144) {
               var2.town = var1.readInt32();
            } else if (var5 == 152) {
               var2.area = var1.readInt32();
            } else if (var5 == 160) {
               var2.friend = var1.readBool();
            } else if (var5 == 168) {
               var2.guild = var1.readBool();
            } else if (var5 == 176) {
               var2.creditscore = var1.readInt32();
            } else if (var5 == 186) {
               Codec var10 = ProtobufProxy.create(PT_GROUP_MEMBER.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.list == null) {
                  var2.list = new ArrayList();
               }

               var2.list.add((PT_GROUP_MEMBER)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 192) {
               var2.state = var1.readInt32();
            } else if (var5 == 200) {
               var2.targetguid = var1.readUInt64();
            } else if (var5 == 208) {
               var2.charguid = var1.readUInt64();
            } else if (var5 == 216) {
               var2.dungeonindex = var1.readInt32();
            } else if (var5 == 224) {
               var2.matchtype = var1.readInt32();
            } else if (var5 == 232) {
               var2.subtype = var1.readInt32();
            } else if (var5 == 240) {
               var2.max = var1.readInt32();
            } else if (var5 == 248) {
               var2.contentlockstate = var1.readInt32();
            } else if (var5 == 256) {
               var2.password = var1.readInt32();
            } else if (var5 == 264) {
               var2.stageindex = var1.readInt32();
            } else if (var5 == 272) {
               var2.grade = var1.readInt32();
            } else if (var5 == 282) {
               var2.partyname = var1.readString();
            } else if (var5 == 288) {
               var2.publictype = var1.readInt32();
            } else if (var5 == 298) {
               Codec var11 = ProtobufProxy.create(PT_GROUP_MEMBER.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var15 = var1.readRawVarint32();
               int var19 = var1.pushLimit(var15);
               if (var2.users == null) {
                  var2.users = new ArrayList();
               }

               var2.users.add((PT_GROUP_MEMBER)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var19);
            } else if (var5 == 306) {
               Codec var12 = ProtobufProxy.create(PT_CUSTOM_DATA.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var16 = var1.readRawVarint32();
               int var20 = var1.pushLimit(var16);
               var2.customdata = (PT_CUSTOM_DATA)var12.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var20);
            } else if (var5 == 314) {
               Codec var13 = ProtobufProxy.create(PT_MEMBER_AREA_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var17 = var1.readRawVarint32();
               int var21 = var1.pushLimit(var17);
               if (var2.members == null) {
                  var2.members = new ArrayList();
               }

               var2.members.add((PT_MEMBER_AREA_INFO)var13.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var21);
            } else if (var5 == 322) {
               var2.targetname = var1.readString();
            } else if (var5 == 328) {
               var2.isobserver = var1.readBool();
            } else if (var5 == 336) {
               var2.isobserverchat = var1.readBool();
            } else if (var5 == 346) {
               Codec var14 = ProtobufProxy.create(PT_BATTLE_OPTION.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var18 = var1.readRawVarint32();
               int var22 = var1.pushLimit(var18);
               if (var2.battleoptionlist == null) {
                  var2.battleoptionlist = new ArrayList();
               }

               var2.battleoptionlist.add((PT_BATTLE_OPTION)var14.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var22);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(NOTIFY_CONTROL_GROUP.class);
         return this.descriptor = var1;
      }
   }
}
