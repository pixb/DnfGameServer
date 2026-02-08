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

public class RES_TOWN_CHAT$$JProtoBufClass implements Codec<RES_TOWN_CHAT>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_TOWN_CHAT var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_TOWN_CHAT decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_TOWN_CHAT var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var43 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var43);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var44 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(2, var44);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.subtype)) {
         Integer var45 = var1.subtype;
         var2 += CodedOutputStream.computeInt32Size(3, var45);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var46 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(4, var46);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.targetguid)) {
         Long var47 = var1.targetguid;
         var2 += CodedOutputStream.computeUInt64Size(5, var47);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var48 = var1.job;
         var2 += CodedOutputStream.computeInt32Size(6, var48);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var49 = var1.growtype;
         var2 += CodedOutputStream.computeInt32Size(7, var49);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var50 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(8, var50);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.sender)) {
         String var51 = var1.sender;
         var2 += CodedOutputStream.computeStringSize(9, var51);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.chat)) {
         String var52 = var1.chat;
         var2 += CodedOutputStream.computeStringSize(10, var52);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.voice)) {
         String var53 = var1.voice;
         var2 += CodedOutputStream.computeStringSize(11, var53);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.voicetime)) {
         String var54 = var1.voicetime;
         var2 += CodedOutputStream.computeStringSize(12, var54);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.receiver)) {
         String var55 = var1.receiver;
         var2 += CodedOutputStream.computeStringSize(13, var55);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.hyperlinktype)) {
         Integer var56 = var1.hyperlinktype;
         var2 += CodedOutputStream.computeInt32Size(14, var56);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.hyperlinksubtype)) {
         Integer var57 = var1.hyperlinksubtype;
         var2 += CodedOutputStream.computeInt32Size(15, var57);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.secgrowtype)) {
         Integer var58 = var1.secgrowtype;
         var2 += CodedOutputStream.computeInt32Size(16, var58);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.online)) {
         Integer var59 = var1.online;
         var2 += CodedOutputStream.computeInt32Size(17, var59);
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.hyperlinkdatas)) {
         List var60 = var1.hyperlinkdatas;
         var2 += CodedConstant.computeListSize(18, var60, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.sub)) {
         List var61 = var1.sub;
         var2 += CodedConstant.computeListSize(19, var61, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var62 = var1.partyguid;
         var2 += CodedOutputStream.computeUInt64Size(20, var62);
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.equip)) {
         PT_EQUIP var63 = var1.equip;
         var2 += CodedConstant.computeSize(21, var63, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.title)) {
         PT_EQUIP var64 = var1.title;
         var2 += CodedConstant.computeSize(22, var64, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.flag)) {
         PT_EQUIP var65 = var1.flag;
         var2 += CodedConstant.computeSize(23, var65, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.emblem)) {
         PT_STACKABLE var66 = var1.emblem;
         var2 += CodedConstant.computeSize(24, var66, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.material)) {
         PT_STACKABLE var67 = var1.material;
         var2 += CodedConstant.computeSize(25, var67, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.consume)) {
         PT_STACKABLE var68 = var1.consume;
         var2 += CodedConstant.computeSize(26, var68, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.avatar)) {
         PT_AVATAR_ITEM var69 = var1.avatar;
         var2 += CodedConstant.computeSize(27, var69, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.card)) {
         PT_STACKABLE var70 = var1.card;
         var2 += CodedConstant.computeSize(28, var70, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var31 = null;
      if (!CodedConstant.isNull(var1.crack)) {
         PT_STACKABLE var71 = var1.crack;
         var2 += CodedConstant.computeSize(29, var71, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var32 = null;
      if (!CodedConstant.isNull(var1.crackequip)) {
         PT_EQUIP var72 = var1.crackequip;
         var2 += CodedConstant.computeSize(30, var72, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var33 = null;
      if (!CodedConstant.isNull(var1.creature)) {
         PT_CREATURE var73 = var1.creature;
         var2 += CodedConstant.computeSize(31, var73, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var34 = null;
      if (!CodedConstant.isNull(var1.cartifact)) {
         PT_ARTIFACT var74 = var1.cartifact;
         var2 += CodedConstant.computeSize(32, var74, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var35 = null;
      if (!CodedConstant.isNull(var1.skinchatinfo)) {
         PT_SKIN_CHAT_INFO var75 = var1.skinchatinfo;
         var2 += CodedConstant.computeSize(33, var75, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var36 = null;
      if (!CodedConstant.isNull(var1.sdavatar)) {
         PT_AVATAR_ITEM var76 = var1.sdavatar;
         var2 += CodedConstant.computeSize(34, var76, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var37 = null;
      if (!CodedConstant.isNull(var1.bookmark)) {
         PT_STACKABLE var77 = var1.bookmark;
         var2 += CodedConstant.computeSize(35, var77, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var38 = null;
      if (!CodedConstant.isNull(var1.scroll)) {
         PT_EQUIP var78 = var1.scroll;
         var2 += CodedConstant.computeSize(36, var78, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var39 = null;
      if (!CodedConstant.isNull(var1.info)) {
         PT_RECRUIT_PARTY_INFO var79 = var1.info;
         var2 += CodedConstant.computeSize(37, var79, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var40 = null;
      if (!CodedConstant.isNull(var1.date)) {
         Long var80 = var1.date;
         var2 += CodedOutputStream.computeUInt64Size(38, var80);
      }

      Object var41 = null;
      if (!CodedConstant.isNull(var1.rpguid)) {
         Long var81 = var1.rpguid;
         var2 += CodedOutputStream.computeUInt64Size(39, var81);
      }

      Object var42 = null;
      if (!CodedConstant.isNull(var1.creditscore)) {
         Integer var82 = var1.creditscore;
         var2 += CodedOutputStream.computeInt32Size(40, var82);
      }

      return var2;
   }

   public void doWriteTo(RES_TOWN_CHAT var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var43 = var1.error;
         if (var43 != null) {
            var2.writeInt32(1, var43);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var44 = var1.type;
         if (var44 != null) {
            var2.writeInt32(2, var44);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.subtype)) {
         Integer var45 = var1.subtype;
         if (var45 != null) {
            var2.writeInt32(3, var45);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var46 = var1.charguid;
         if (var46 != null) {
            var2.writeUInt64(4, var46);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.targetguid)) {
         Long var47 = var1.targetguid;
         if (var47 != null) {
            var2.writeUInt64(5, var47);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var48 = var1.job;
         if (var48 != null) {
            var2.writeInt32(6, var48);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var49 = var1.growtype;
         if (var49 != null) {
            var2.writeInt32(7, var49);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var50 = var1.level;
         if (var50 != null) {
            var2.writeInt32(8, var50);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.sender)) {
         String var51 = var1.sender;
         if (var51 != null) {
            var2.writeString(9, var51);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.chat)) {
         String var52 = var1.chat;
         if (var52 != null) {
            var2.writeString(10, var52);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.voice)) {
         String var53 = var1.voice;
         if (var53 != null) {
            var2.writeString(11, var53);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.voicetime)) {
         String var54 = var1.voicetime;
         if (var54 != null) {
            var2.writeString(12, var54);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.receiver)) {
         String var55 = var1.receiver;
         if (var55 != null) {
            var2.writeString(13, var55);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.hyperlinktype)) {
         Integer var56 = var1.hyperlinktype;
         if (var56 != null) {
            var2.writeInt32(14, var56);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.hyperlinksubtype)) {
         Integer var57 = var1.hyperlinksubtype;
         if (var57 != null) {
            var2.writeInt32(15, var57);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.secgrowtype)) {
         Integer var58 = var1.secgrowtype;
         if (var58 != null) {
            var2.writeInt32(16, var58);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.online)) {
         Integer var59 = var1.online;
         if (var59 != null) {
            var2.writeInt32(17, var59);
         }
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.hyperlinkdatas)) {
         List var60 = var1.hyperlinkdatas;
         if (var60 != null) {
            CodedConstant.writeToList(var2, 18, FieldType.OBJECT, var60, false);
         }
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.sub)) {
         List var61 = var1.sub;
         if (var61 != null) {
            CodedConstant.writeToList(var2, 19, FieldType.OBJECT, var61, false);
         }
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var62 = var1.partyguid;
         if (var62 != null) {
            var2.writeUInt64(20, var62);
         }
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.equip)) {
         PT_EQUIP var63 = var1.equip;
         if (var63 != null) {
            CodedConstant.writeObject(var2, 21, FieldType.OBJECT, var63, false);
         }
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.title)) {
         PT_EQUIP var64 = var1.title;
         if (var64 != null) {
            CodedConstant.writeObject(var2, 22, FieldType.OBJECT, var64, false);
         }
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.flag)) {
         PT_EQUIP var65 = var1.flag;
         if (var65 != null) {
            CodedConstant.writeObject(var2, 23, FieldType.OBJECT, var65, false);
         }
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.emblem)) {
         PT_STACKABLE var66 = var1.emblem;
         if (var66 != null) {
            CodedConstant.writeObject(var2, 24, FieldType.OBJECT, var66, false);
         }
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.material)) {
         PT_STACKABLE var67 = var1.material;
         if (var67 != null) {
            CodedConstant.writeObject(var2, 25, FieldType.OBJECT, var67, false);
         }
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.consume)) {
         PT_STACKABLE var68 = var1.consume;
         if (var68 != null) {
            CodedConstant.writeObject(var2, 26, FieldType.OBJECT, var68, false);
         }
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.avatar)) {
         PT_AVATAR_ITEM var69 = var1.avatar;
         if (var69 != null) {
            CodedConstant.writeObject(var2, 27, FieldType.OBJECT, var69, false);
         }
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.card)) {
         PT_STACKABLE var70 = var1.card;
         if (var70 != null) {
            CodedConstant.writeObject(var2, 28, FieldType.OBJECT, var70, false);
         }
      }

      Object var31 = null;
      if (!CodedConstant.isNull(var1.crack)) {
         PT_STACKABLE var71 = var1.crack;
         if (var71 != null) {
            CodedConstant.writeObject(var2, 29, FieldType.OBJECT, var71, false);
         }
      }

      Object var32 = null;
      if (!CodedConstant.isNull(var1.crackequip)) {
         PT_EQUIP var72 = var1.crackequip;
         if (var72 != null) {
            CodedConstant.writeObject(var2, 30, FieldType.OBJECT, var72, false);
         }
      }

      Object var33 = null;
      if (!CodedConstant.isNull(var1.creature)) {
         PT_CREATURE var73 = var1.creature;
         if (var73 != null) {
            CodedConstant.writeObject(var2, 31, FieldType.OBJECT, var73, false);
         }
      }

      Object var34 = null;
      if (!CodedConstant.isNull(var1.cartifact)) {
         PT_ARTIFACT var74 = var1.cartifact;
         if (var74 != null) {
            CodedConstant.writeObject(var2, 32, FieldType.OBJECT, var74, false);
         }
      }

      Object var35 = null;
      if (!CodedConstant.isNull(var1.skinchatinfo)) {
         PT_SKIN_CHAT_INFO var75 = var1.skinchatinfo;
         if (var75 != null) {
            CodedConstant.writeObject(var2, 33, FieldType.OBJECT, var75, false);
         }
      }

      Object var36 = null;
      if (!CodedConstant.isNull(var1.sdavatar)) {
         PT_AVATAR_ITEM var76 = var1.sdavatar;
         if (var76 != null) {
            CodedConstant.writeObject(var2, 34, FieldType.OBJECT, var76, false);
         }
      }

      Object var37 = null;
      if (!CodedConstant.isNull(var1.bookmark)) {
         PT_STACKABLE var77 = var1.bookmark;
         if (var77 != null) {
            CodedConstant.writeObject(var2, 35, FieldType.OBJECT, var77, false);
         }
      }

      Object var38 = null;
      if (!CodedConstant.isNull(var1.scroll)) {
         PT_EQUIP var78 = var1.scroll;
         if (var78 != null) {
            CodedConstant.writeObject(var2, 36, FieldType.OBJECT, var78, false);
         }
      }

      Object var39 = null;
      if (!CodedConstant.isNull(var1.info)) {
         PT_RECRUIT_PARTY_INFO var79 = var1.info;
         if (var79 != null) {
            CodedConstant.writeObject(var2, 37, FieldType.OBJECT, var79, false);
         }
      }

      Object var40 = null;
      if (!CodedConstant.isNull(var1.date)) {
         Long var80 = var1.date;
         if (var80 != null) {
            var2.writeUInt64(38, var80);
         }
      }

      Object var41 = null;
      if (!CodedConstant.isNull(var1.rpguid)) {
         Long var81 = var1.rpguid;
         if (var81 != null) {
            var2.writeUInt64(39, var81);
         }
      }

      Object var42 = null;
      if (!CodedConstant.isNull(var1.creditscore)) {
         Integer var82 = var1.creditscore;
         if (var82 != null) {
            var2.writeInt32(40, var82);
         }
      }

   }

   public void writeTo(RES_TOWN_CHAT var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_TOWN_CHAT readFrom(CodedInputStream var1) throws IOException {
      RES_TOWN_CHAT var2 = new RES_TOWN_CHAT();
      var2.hyperlinkdatas = new ArrayList();
      var2.sub = new ArrayList();

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
               var2.type = var1.readInt32();
            } else if (var5 == 24) {
               var2.subtype = var1.readInt32();
            } else if (var5 == 32) {
               var2.charguid = var1.readUInt64();
            } else if (var5 == 40) {
               var2.targetguid = var1.readUInt64();
            } else if (var5 == 48) {
               var2.job = var1.readInt32();
            } else if (var5 == 56) {
               var2.growtype = var1.readInt32();
            } else if (var5 == 64) {
               var2.level = var1.readInt32();
            } else if (var5 == 74) {
               var2.sender = var1.readString();
            } else if (var5 == 82) {
               var2.chat = var1.readString();
            } else if (var5 == 90) {
               var2.voice = var1.readString();
            } else if (var5 == 98) {
               var2.voicetime = var1.readString();
            } else if (var5 == 106) {
               var2.receiver = var1.readString();
            } else if (var5 == 112) {
               var2.hyperlinktype = var1.readInt32();
            } else if (var5 == 120) {
               var2.hyperlinksubtype = var1.readInt32();
            } else if (var5 == 128) {
               var2.secgrowtype = var1.readInt32();
            } else if (var5 == 136) {
               var2.online = var1.readInt32();
            } else if (var5 == 146) {
               Codec var10 = ProtobufProxy.create(PT_HYPERLINK_DATA.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.hyperlinkdatas == null) {
                  var2.hyperlinkdatas = new ArrayList();
               }

               var2.hyperlinkdatas.add((PT_HYPERLINK_DATA)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 154) {
               Codec var11 = ProtobufProxy.create(PT_HYPERLINK_SYSTEMMESSAGE_SUB.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var29 = var1.readRawVarint32();
               int var47 = var1.pushLimit(var29);
               if (var2.sub == null) {
                  var2.sub = new ArrayList();
               }

               var2.sub.add((PT_HYPERLINK_SYSTEMMESSAGE_SUB)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var47);
            } else if (var5 == 160) {
               var2.partyguid = var1.readUInt64();
            } else if (var5 == 170) {
               Codec var12 = ProtobufProxy.create(PT_EQUIP.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var30 = var1.readRawVarint32();
               int var48 = var1.pushLimit(var30);
               var2.equip = (PT_EQUIP)var12.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var48);
            } else if (var5 == 178) {
               Codec var13 = ProtobufProxy.create(PT_EQUIP.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var31 = var1.readRawVarint32();
               int var49 = var1.pushLimit(var31);
               var2.title = (PT_EQUIP)var13.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var49);
            } else if (var5 == 186) {
               Codec var14 = ProtobufProxy.create(PT_EQUIP.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var32 = var1.readRawVarint32();
               int var50 = var1.pushLimit(var32);
               var2.flag = (PT_EQUIP)var14.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var50);
            } else if (var5 == 194) {
               Codec var15 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var33 = var1.readRawVarint32();
               int var51 = var1.pushLimit(var33);
               var2.emblem = (PT_STACKABLE)var15.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var51);
            } else if (var5 == 202) {
               Codec var16 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var34 = var1.readRawVarint32();
               int var52 = var1.pushLimit(var34);
               var2.material = (PT_STACKABLE)var16.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var52);
            } else if (var5 == 210) {
               Codec var17 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var35 = var1.readRawVarint32();
               int var53 = var1.pushLimit(var35);
               var2.consume = (PT_STACKABLE)var17.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var53);
            } else if (var5 == 218) {
               Codec var18 = ProtobufProxy.create(PT_AVATAR_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var36 = var1.readRawVarint32();
               int var54 = var1.pushLimit(var36);
               var2.avatar = (PT_AVATAR_ITEM)var18.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var54);
            } else if (var5 == 226) {
               Codec var19 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var37 = var1.readRawVarint32();
               int var55 = var1.pushLimit(var37);
               var2.card = (PT_STACKABLE)var19.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var55);
            } else if (var5 == 234) {
               Codec var20 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var38 = var1.readRawVarint32();
               int var56 = var1.pushLimit(var38);
               var2.crack = (PT_STACKABLE)var20.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var56);
            } else if (var5 == 242) {
               Codec var21 = ProtobufProxy.create(PT_EQUIP.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var39 = var1.readRawVarint32();
               int var57 = var1.pushLimit(var39);
               var2.crackequip = (PT_EQUIP)var21.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var57);
            } else if (var5 == 250) {
               Codec var22 = ProtobufProxy.create(PT_CREATURE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var40 = var1.readRawVarint32();
               int var58 = var1.pushLimit(var40);
               var2.creature = (PT_CREATURE)var22.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var58);
            } else if (var5 == 258) {
               Codec var23 = ProtobufProxy.create(PT_ARTIFACT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var41 = var1.readRawVarint32();
               int var59 = var1.pushLimit(var41);
               var2.cartifact = (PT_ARTIFACT)var23.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var59);
            } else if (var5 == 266) {
               Codec var24 = ProtobufProxy.create(PT_SKIN_CHAT_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var42 = var1.readRawVarint32();
               int var60 = var1.pushLimit(var42);
               var2.skinchatinfo = (PT_SKIN_CHAT_INFO)var24.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var60);
            } else if (var5 == 274) {
               Codec var25 = ProtobufProxy.create(PT_AVATAR_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var43 = var1.readRawVarint32();
               int var61 = var1.pushLimit(var43);
               var2.sdavatar = (PT_AVATAR_ITEM)var25.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var61);
            } else if (var5 == 282) {
               Codec var26 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var44 = var1.readRawVarint32();
               int var62 = var1.pushLimit(var44);
               var2.bookmark = (PT_STACKABLE)var26.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var62);
            } else if (var5 == 290) {
               Codec var27 = ProtobufProxy.create(PT_EQUIP.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var45 = var1.readRawVarint32();
               int var63 = var1.pushLimit(var45);
               var2.scroll = (PT_EQUIP)var27.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var63);
            } else if (var5 == 298) {
               Codec var28 = ProtobufProxy.create(PT_RECRUIT_PARTY_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var46 = var1.readRawVarint32();
               int var64 = var1.pushLimit(var46);
               var2.info = (PT_RECRUIT_PARTY_INFO)var28.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var64);
            } else if (var5 == 304) {
               var2.date = var1.readUInt64();
            } else if (var5 == 312) {
               var2.rpguid = var1.readUInt64();
            } else if (var5 == 320) {
               var2.creditscore = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_TOWN_CHAT.class);
         return this.descriptor = var1;
      }
   }
}
