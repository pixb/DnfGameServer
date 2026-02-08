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

public class RES_ALARM_GUILD_CHAT$$JProtoBufClass implements Codec<RES_ALARM_GUILD_CHAT>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_ALARM_GUILD_CHAT var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_ALARM_GUILD_CHAT decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_ALARM_GUILD_CHAT var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var38 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var38);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var39 = var1.name;
         var2 += CodedOutputStream.computeStringSize(2, var39);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.msg)) {
         String var40 = var1.msg;
         var2 += CodedOutputStream.computeStringSize(3, var40);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.voice)) {
         String var41 = var1.voice;
         var2 += CodedOutputStream.computeStringSize(4, var41);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.voicetime)) {
         String var42 = var1.voicetime;
         var2 += CodedOutputStream.computeStringSize(5, var42);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.date)) {
         Long var43 = var1.date;
         var2 += CodedOutputStream.computeUInt64Size(6, var43);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var44 = var1.job;
         var2 += CodedOutputStream.computeInt32Size(7, var44);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var45 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(8, var45);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var46 = var1.growtype;
         var2 += CodedOutputStream.computeInt32Size(9, var46);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.secgrowtype)) {
         Integer var47 = var1.secgrowtype;
         var2 += CodedOutputStream.computeInt32Size(10, var47);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var48 = var1.grade;
         var2 += CodedOutputStream.computeInt32Size(11, var48);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var49 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(12, var49);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var50 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(13, var50);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.creditscore)) {
         Integer var51 = var1.creditscore;
         var2 += CodedOutputStream.computeInt32Size(14, var51);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.hyperlinktype)) {
         Integer var52 = var1.hyperlinktype;
         var2 += CodedOutputStream.computeInt32Size(15, var52);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.hyperlinksubtype)) {
         Integer var53 = var1.hyperlinksubtype;
         var2 += CodedOutputStream.computeInt32Size(16, var53);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.hyperlinkdatas)) {
         List var54 = var1.hyperlinkdatas;
         var2 += CodedConstant.computeListSize(17, var54, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.sub)) {
         List var55 = var1.sub;
         var2 += CodedConstant.computeListSize(18, var55, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.equip)) {
         PT_EQUIP var56 = var1.equip;
         var2 += CodedConstant.computeSize(19, var56, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.title)) {
         PT_EQUIP var57 = var1.title;
         var2 += CodedConstant.computeSize(20, var57, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.flag)) {
         PT_EQUIP var58 = var1.flag;
         var2 += CodedConstant.computeSize(21, var58, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.emblem)) {
         PT_STACKABLE var59 = var1.emblem;
         var2 += CodedConstant.computeSize(22, var59, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.material)) {
         PT_STACKABLE var60 = var1.material;
         var2 += CodedConstant.computeSize(23, var60, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.consume)) {
         PT_STACKABLE var61 = var1.consume;
         var2 += CodedConstant.computeSize(24, var61, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.avatar)) {
         PT_AVATAR_ITEM var62 = var1.avatar;
         var2 += CodedConstant.computeSize(25, var62, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.card)) {
         PT_STACKABLE var63 = var1.card;
         var2 += CodedConstant.computeSize(26, var63, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.creature)) {
         PT_CREATURE var64 = var1.creature;
         var2 += CodedConstant.computeSize(27, var64, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.cartifact)) {
         PT_ARTIFACT var65 = var1.cartifact;
         var2 += CodedConstant.computeSize(28, var65, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var31 = null;
      if (!CodedConstant.isNull(var1.skinchatinfo)) {
         PT_SKIN_CHAT_INFO var66 = var1.skinchatinfo;
         var2 += CodedConstant.computeSize(29, var66, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var32 = null;
      if (!CodedConstant.isNull(var1.sdavatar)) {
         PT_AVATAR_ITEM var67 = var1.sdavatar;
         var2 += CodedConstant.computeSize(30, var67, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var33 = null;
      if (!CodedConstant.isNull(var1.info)) {
         PT_RECRUIT_PARTY_INFO var68 = var1.info;
         var2 += CodedConstant.computeSize(31, var68, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var34 = null;
      if (!CodedConstant.isNull(var1.list)) {
         List var69 = var1.list;
         var2 += CodedConstant.computeListSize(32, var69, FieldType.STRING, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var35 = null;
      if (!CodedConstant.isNull(var1.targetguid)) {
         Long var70 = var1.targetguid;
         var2 += CodedOutputStream.computeInt64Size(33, var70);
      }

      Object var36 = null;
      if (!CodedConstant.isNull(var1.crack)) {
         PT_STACKABLE var71 = var1.crack;
         var2 += CodedConstant.computeSize(34, var71, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var37 = null;
      if (!CodedConstant.isNull(var1.crackequip)) {
         PT_EQUIP var72 = var1.crackequip;
         var2 += CodedConstant.computeSize(35, var72, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      return var2;
   }

   public void doWriteTo(RES_ALARM_GUILD_CHAT var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var38 = var1.error;
         if (var38 != null) {
            var2.writeInt32(1, var38);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var39 = var1.name;
         if (var39 != null) {
            var2.writeString(2, var39);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.msg)) {
         String var40 = var1.msg;
         if (var40 != null) {
            var2.writeString(3, var40);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.voice)) {
         String var41 = var1.voice;
         if (var41 != null) {
            var2.writeString(4, var41);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.voicetime)) {
         String var42 = var1.voicetime;
         if (var42 != null) {
            var2.writeString(5, var42);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.date)) {
         Long var43 = var1.date;
         if (var43 != null) {
            var2.writeUInt64(6, var43);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var44 = var1.job;
         if (var44 != null) {
            var2.writeInt32(7, var44);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var45 = var1.level;
         if (var45 != null) {
            var2.writeInt32(8, var45);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var46 = var1.growtype;
         if (var46 != null) {
            var2.writeInt32(9, var46);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.secgrowtype)) {
         Integer var47 = var1.secgrowtype;
         if (var47 != null) {
            var2.writeInt32(10, var47);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var48 = var1.grade;
         if (var48 != null) {
            var2.writeInt32(11, var48);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var49 = var1.type;
         if (var49 != null) {
            var2.writeInt32(12, var49);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var50 = var1.charguid;
         if (var50 != null) {
            var2.writeUInt64(13, var50);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.creditscore)) {
         Integer var51 = var1.creditscore;
         if (var51 != null) {
            var2.writeInt32(14, var51);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.hyperlinktype)) {
         Integer var52 = var1.hyperlinktype;
         if (var52 != null) {
            var2.writeInt32(15, var52);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.hyperlinksubtype)) {
         Integer var53 = var1.hyperlinksubtype;
         if (var53 != null) {
            var2.writeInt32(16, var53);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.hyperlinkdatas)) {
         List var54 = var1.hyperlinkdatas;
         if (var54 != null) {
            CodedConstant.writeToList(var2, 17, FieldType.OBJECT, var54, false);
         }
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.sub)) {
         List var55 = var1.sub;
         if (var55 != null) {
            CodedConstant.writeToList(var2, 18, FieldType.OBJECT, var55, false);
         }
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.equip)) {
         PT_EQUIP var56 = var1.equip;
         if (var56 != null) {
            CodedConstant.writeObject(var2, 19, FieldType.OBJECT, var56, false);
         }
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.title)) {
         PT_EQUIP var57 = var1.title;
         if (var57 != null) {
            CodedConstant.writeObject(var2, 20, FieldType.OBJECT, var57, false);
         }
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.flag)) {
         PT_EQUIP var58 = var1.flag;
         if (var58 != null) {
            CodedConstant.writeObject(var2, 21, FieldType.OBJECT, var58, false);
         }
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.emblem)) {
         PT_STACKABLE var59 = var1.emblem;
         if (var59 != null) {
            CodedConstant.writeObject(var2, 22, FieldType.OBJECT, var59, false);
         }
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.material)) {
         PT_STACKABLE var60 = var1.material;
         if (var60 != null) {
            CodedConstant.writeObject(var2, 23, FieldType.OBJECT, var60, false);
         }
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.consume)) {
         PT_STACKABLE var61 = var1.consume;
         if (var61 != null) {
            CodedConstant.writeObject(var2, 24, FieldType.OBJECT, var61, false);
         }
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.avatar)) {
         PT_AVATAR_ITEM var62 = var1.avatar;
         if (var62 != null) {
            CodedConstant.writeObject(var2, 25, FieldType.OBJECT, var62, false);
         }
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.card)) {
         PT_STACKABLE var63 = var1.card;
         if (var63 != null) {
            CodedConstant.writeObject(var2, 26, FieldType.OBJECT, var63, false);
         }
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.creature)) {
         PT_CREATURE var64 = var1.creature;
         if (var64 != null) {
            CodedConstant.writeObject(var2, 27, FieldType.OBJECT, var64, false);
         }
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.cartifact)) {
         PT_ARTIFACT var65 = var1.cartifact;
         if (var65 != null) {
            CodedConstant.writeObject(var2, 28, FieldType.OBJECT, var65, false);
         }
      }

      Object var31 = null;
      if (!CodedConstant.isNull(var1.skinchatinfo)) {
         PT_SKIN_CHAT_INFO var66 = var1.skinchatinfo;
         if (var66 != null) {
            CodedConstant.writeObject(var2, 29, FieldType.OBJECT, var66, false);
         }
      }

      Object var32 = null;
      if (!CodedConstant.isNull(var1.sdavatar)) {
         PT_AVATAR_ITEM var67 = var1.sdavatar;
         if (var67 != null) {
            CodedConstant.writeObject(var2, 30, FieldType.OBJECT, var67, false);
         }
      }

      Object var33 = null;
      if (!CodedConstant.isNull(var1.info)) {
         PT_RECRUIT_PARTY_INFO var68 = var1.info;
         if (var68 != null) {
            CodedConstant.writeObject(var2, 31, FieldType.OBJECT, var68, false);
         }
      }

      Object var34 = null;
      if (!CodedConstant.isNull(var1.list)) {
         List var69 = var1.list;
         if (var69 != null) {
            CodedConstant.writeToList(var2, 32, FieldType.STRING, var69, false);
         }
      }

      Object var35 = null;
      if (!CodedConstant.isNull(var1.targetguid)) {
         Long var70 = var1.targetguid;
         if (var70 != null) {
            var2.writeInt64(33, var70);
         }
      }

      Object var36 = null;
      if (!CodedConstant.isNull(var1.crack)) {
         PT_STACKABLE var71 = var1.crack;
         if (var71 != null) {
            CodedConstant.writeObject(var2, 34, FieldType.OBJECT, var71, false);
         }
      }

      Object var37 = null;
      if (!CodedConstant.isNull(var1.crackequip)) {
         PT_EQUIP var72 = var1.crackequip;
         if (var72 != null) {
            CodedConstant.writeObject(var2, 35, FieldType.OBJECT, var72, false);
         }
      }

   }

   public void writeTo(RES_ALARM_GUILD_CHAT var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_ALARM_GUILD_CHAT readFrom(CodedInputStream var1) throws IOException {
      RES_ALARM_GUILD_CHAT var2 = new RES_ALARM_GUILD_CHAT();
      var2.hyperlinkdatas = new ArrayList();
      var2.sub = new ArrayList();
      var2.list = new ArrayList();

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
            } else if (var5 == 18) {
               var2.name = var1.readString();
            } else if (var5 == 26) {
               var2.msg = var1.readString();
            } else if (var5 == 34) {
               var2.voice = var1.readString();
            } else if (var5 == 42) {
               var2.voicetime = var1.readString();
            } else if (var5 == 48) {
               var2.date = var1.readUInt64();
            } else if (var5 == 56) {
               var2.job = var1.readInt32();
            } else if (var5 == 64) {
               var2.level = var1.readInt32();
            } else if (var5 == 72) {
               var2.growtype = var1.readInt32();
            } else if (var5 == 80) {
               var2.secgrowtype = var1.readInt32();
            } else if (var5 == 88) {
               var2.grade = var1.readInt32();
            } else if (var5 == 96) {
               var2.type = var1.readInt32();
            } else if (var5 == 104) {
               var2.charguid = var1.readUInt64();
            } else if (var5 == 112) {
               var2.creditscore = var1.readInt32();
            } else if (var5 == 120) {
               var2.hyperlinktype = var1.readInt32();
            } else if (var5 == 128) {
               var2.hyperlinksubtype = var1.readInt32();
            } else if (var5 == 138) {
               Codec var10 = ProtobufProxy.create(PT_HYPERLINK_DATA.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.hyperlinkdatas == null) {
                  var2.hyperlinkdatas = new ArrayList();
               }

               var2.hyperlinkdatas.add((PT_HYPERLINK_DATA)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 146) {
               Codec var11 = ProtobufProxy.create(PT_HYPERLINK_SYSTEMMESSAGE_SUB.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var27 = var1.readRawVarint32();
               int var43 = var1.pushLimit(var27);
               if (var2.sub == null) {
                  var2.sub = new ArrayList();
               }

               var2.sub.add((PT_HYPERLINK_SYSTEMMESSAGE_SUB)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var43);
            } else if (var5 == 154) {
               Codec var12 = ProtobufProxy.create(PT_EQUIP.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var28 = var1.readRawVarint32();
               int var44 = var1.pushLimit(var28);
               var2.equip = (PT_EQUIP)var12.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var44);
            } else if (var5 == 162) {
               Codec var13 = ProtobufProxy.create(PT_EQUIP.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var29 = var1.readRawVarint32();
               int var45 = var1.pushLimit(var29);
               var2.title = (PT_EQUIP)var13.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var45);
            } else if (var5 == 170) {
               Codec var14 = ProtobufProxy.create(PT_EQUIP.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var30 = var1.readRawVarint32();
               int var46 = var1.pushLimit(var30);
               var2.flag = (PT_EQUIP)var14.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var46);
            } else if (var5 == 178) {
               Codec var15 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var31 = var1.readRawVarint32();
               int var47 = var1.pushLimit(var31);
               var2.emblem = (PT_STACKABLE)var15.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var47);
            } else if (var5 == 186) {
               Codec var16 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var32 = var1.readRawVarint32();
               int var48 = var1.pushLimit(var32);
               var2.material = (PT_STACKABLE)var16.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var48);
            } else if (var5 == 194) {
               Codec var17 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var33 = var1.readRawVarint32();
               int var49 = var1.pushLimit(var33);
               var2.consume = (PT_STACKABLE)var17.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var49);
            } else if (var5 == 202) {
               Codec var18 = ProtobufProxy.create(PT_AVATAR_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var34 = var1.readRawVarint32();
               int var50 = var1.pushLimit(var34);
               var2.avatar = (PT_AVATAR_ITEM)var18.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var50);
            } else if (var5 == 210) {
               Codec var19 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var35 = var1.readRawVarint32();
               int var51 = var1.pushLimit(var35);
               var2.card = (PT_STACKABLE)var19.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var51);
            } else if (var5 == 218) {
               Codec var20 = ProtobufProxy.create(PT_CREATURE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var36 = var1.readRawVarint32();
               int var52 = var1.pushLimit(var36);
               var2.creature = (PT_CREATURE)var20.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var52);
            } else if (var5 == 226) {
               Codec var21 = ProtobufProxy.create(PT_ARTIFACT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var37 = var1.readRawVarint32();
               int var53 = var1.pushLimit(var37);
               var2.cartifact = (PT_ARTIFACT)var21.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var53);
            } else if (var5 == 234) {
               Codec var22 = ProtobufProxy.create(PT_SKIN_CHAT_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var38 = var1.readRawVarint32();
               int var54 = var1.pushLimit(var38);
               var2.skinchatinfo = (PT_SKIN_CHAT_INFO)var22.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var54);
            } else if (var5 == 242) {
               Codec var23 = ProtobufProxy.create(PT_AVATAR_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var39 = var1.readRawVarint32();
               int var55 = var1.pushLimit(var39);
               var2.sdavatar = (PT_AVATAR_ITEM)var23.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var55);
            } else if (var5 == 250) {
               Codec var24 = ProtobufProxy.create(PT_RECRUIT_PARTY_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var40 = var1.readRawVarint32();
               int var56 = var1.pushLimit(var40);
               var2.info = (PT_RECRUIT_PARTY_INFO)var24.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var56);
            } else if (var5 == 258) {
               if (var2.list == null) {
                  var2.list = new ArrayList();
               }

               var2.list.add(var1.readString());
            } else if (var5 == 264) {
               var2.targetguid = var1.readInt64();
            } else if (var5 == 274) {
               Codec var25 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var41 = var1.readRawVarint32();
               int var57 = var1.pushLimit(var41);
               var2.crack = (PT_STACKABLE)var25.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var57);
            } else if (var5 == 282) {
               Codec var26 = ProtobufProxy.create(PT_EQUIP.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var42 = var1.readRawVarint32();
               int var58 = var1.pushLimit(var42);
               var2.crackequip = (PT_EQUIP)var26.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var58);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_ALARM_GUILD_CHAT.class);
         return this.descriptor = var1;
      }
   }
}
