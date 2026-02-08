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

public class RES_START_DUNGEON$$JProtoBufClass implements Codec<RES_START_DUNGEON>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_START_DUNGEON var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_START_DUNGEON decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_START_DUNGEON var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var32 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var32);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.createtime)) {
         Integer var33 = var1.createtime;
         var2 += CodedOutputStream.computeInt32Size(2, var33);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var34 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(3, var34);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var35 = var1.dungeonguid;
         var2 += CodedOutputStream.computeUInt64Size(4, var35);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.score)) {
         Integer var36 = var1.score;
         var2 += CodedOutputStream.computeInt32Size(5, var36);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var37 = var1.grade;
         var2 += CodedOutputStream.computeInt32Size(6, var37);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var38 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(7, var38);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.earngold)) {
         Integer var39 = var1.earngold;
         var2 += CodedOutputStream.computeInt32Size(8, var39);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.earnexp)) {
         Integer var40 = var1.earnexp;
         var2 += CodedOutputStream.computeInt32Size(9, var40);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.enchant)) {
         Boolean var41 = var1.enchant;
         var2 += CodedOutputStream.computeBoolSize(10, var41);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.entercomplete)) {
         Integer var42 = var1.entercomplete;
         var2 += CodedOutputStream.computeInt32Size(11, var42);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.smash)) {
         Integer var43 = var1.smash;
         var2 += CodedOutputStream.computeInt32Size(12, var43);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.counter)) {
         Integer var44 = var1.counter;
         var2 += CodedOutputStream.computeInt32Size(13, var44);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.multihit)) {
         Integer var45 = var1.multihit;
         var2 += CodedOutputStream.computeInt32Size(14, var45);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.receivedamage)) {
         Integer var46 = var1.receivedamage;
         var2 += CodedOutputStream.computeInt32Size(15, var46);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.givedamage)) {
         Integer var47 = var1.givedamage;
         var2 += CodedOutputStream.computeInt32Size(16, var47);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.startstageguid)) {
         Integer var48 = var1.startstageguid;
         var2 += CodedOutputStream.computeInt32Size(17, var48);
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.gcontribute)) {
         Integer var49 = var1.gcontribute;
         var2 += CodedOutputStream.computeInt32Size(18, var49);
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.maxobjectdrop)) {
         List var50 = var1.maxobjectdrop;
         var2 += CodedConstant.computeListSize(19, var50, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.consumeitems)) {
         List var51 = var1.consumeitems;
         var2 += CodedConstant.computeListSize(20, var51, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.cubeitems)) {
         List var52 = var1.cubeitems;
         var2 += CodedConstant.computeListSize(21, var52, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.dindex)) {
         Integer var53 = var1.dindex;
         var2 += CodedOutputStream.computeInt32Size(22, var53);
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.character)) {
         PT_USER_INFO var54 = var1.character;
         var2 += CodedConstant.computeSize(23, var54, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.champion)) {
         PT_CHAMPION_INFO var55 = var1.champion;
         var2 += CodedConstant.computeSize(24, var55, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.enemycharacter)) {
         PT_USER_INFO var56 = var1.enemycharacter;
         var2 += CodedConstant.computeSize(25, var56, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.mapguids)) {
         List var57 = var1.mapguids;
         var2 += CodedConstant.computeListSize(26, var57, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.lastdungeonrankingscore)) {
         Long var58 = var1.lastdungeonrankingscore;
         var2 += CodedOutputStream.computeUInt64Size(27, var58);
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.requestedindex)) {
         Integer var59 = var1.requestedindex;
         var2 += CodedOutputStream.computeInt32Size(28, var59);
      }

      Object var31 = null;
      if (!CodedConstant.isNull(var1.mazeinfoindex)) {
         Integer var60 = var1.mazeinfoindex;
         var2 += CodedOutputStream.computeInt32Size(29, var60);
      }

      return var2;
   }

   public void doWriteTo(RES_START_DUNGEON var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var32 = var1.error;
         if (var32 != null) {
            var2.writeInt32(1, var32);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.createtime)) {
         Integer var33 = var1.createtime;
         if (var33 != null) {
            var2.writeInt32(2, var33);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var34 = var1.index;
         if (var34 != null) {
            var2.writeInt32(3, var34);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var35 = var1.dungeonguid;
         if (var35 != null) {
            var2.writeUInt64(4, var35);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.score)) {
         Integer var36 = var1.score;
         if (var36 != null) {
            var2.writeInt32(5, var36);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var37 = var1.grade;
         if (var37 != null) {
            var2.writeInt32(6, var37);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var38 = var1.level;
         if (var38 != null) {
            var2.writeInt32(7, var38);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.earngold)) {
         Integer var39 = var1.earngold;
         if (var39 != null) {
            var2.writeInt32(8, var39);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.earnexp)) {
         Integer var40 = var1.earnexp;
         if (var40 != null) {
            var2.writeInt32(9, var40);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.enchant)) {
         Boolean var41 = var1.enchant;
         if (var41 != null) {
            var2.writeBool(10, var41);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.entercomplete)) {
         Integer var42 = var1.entercomplete;
         if (var42 != null) {
            var2.writeInt32(11, var42);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.smash)) {
         Integer var43 = var1.smash;
         if (var43 != null) {
            var2.writeInt32(12, var43);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.counter)) {
         Integer var44 = var1.counter;
         if (var44 != null) {
            var2.writeInt32(13, var44);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.multihit)) {
         Integer var45 = var1.multihit;
         if (var45 != null) {
            var2.writeInt32(14, var45);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.receivedamage)) {
         Integer var46 = var1.receivedamage;
         if (var46 != null) {
            var2.writeInt32(15, var46);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.givedamage)) {
         Integer var47 = var1.givedamage;
         if (var47 != null) {
            var2.writeInt32(16, var47);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.startstageguid)) {
         Integer var48 = var1.startstageguid;
         if (var48 != null) {
            var2.writeInt32(17, var48);
         }
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.gcontribute)) {
         Integer var49 = var1.gcontribute;
         if (var49 != null) {
            var2.writeInt32(18, var49);
         }
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.maxobjectdrop)) {
         List var50 = var1.maxobjectdrop;
         if (var50 != null) {
            CodedConstant.writeToList(var2, 19, FieldType.OBJECT, var50, false);
         }
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.consumeitems)) {
         List var51 = var1.consumeitems;
         if (var51 != null) {
            CodedConstant.writeToList(var2, 20, FieldType.OBJECT, var51, false);
         }
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.cubeitems)) {
         List var52 = var1.cubeitems;
         if (var52 != null) {
            CodedConstant.writeToList(var2, 21, FieldType.OBJECT, var52, false);
         }
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.dindex)) {
         Integer var53 = var1.dindex;
         if (var53 != null) {
            var2.writeInt32(22, var53);
         }
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.character)) {
         PT_USER_INFO var54 = var1.character;
         if (var54 != null) {
            CodedConstant.writeObject(var2, 23, FieldType.OBJECT, var54, false);
         }
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.champion)) {
         PT_CHAMPION_INFO var55 = var1.champion;
         if (var55 != null) {
            CodedConstant.writeObject(var2, 24, FieldType.OBJECT, var55, false);
         }
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.enemycharacter)) {
         PT_USER_INFO var56 = var1.enemycharacter;
         if (var56 != null) {
            CodedConstant.writeObject(var2, 25, FieldType.OBJECT, var56, false);
         }
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.mapguids)) {
         List var57 = var1.mapguids;
         if (var57 != null) {
            CodedConstant.writeToList(var2, 26, FieldType.OBJECT, var57, false);
         }
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.lastdungeonrankingscore)) {
         Long var58 = var1.lastdungeonrankingscore;
         if (var58 != null) {
            var2.writeUInt64(27, var58);
         }
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.requestedindex)) {
         Integer var59 = var1.requestedindex;
         if (var59 != null) {
            var2.writeInt32(28, var59);
         }
      }

      Object var31 = null;
      if (!CodedConstant.isNull(var1.mazeinfoindex)) {
         Integer var60 = var1.mazeinfoindex;
         if (var60 != null) {
            var2.writeInt32(29, var60);
         }
      }

   }

   public void writeTo(RES_START_DUNGEON var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_START_DUNGEON readFrom(CodedInputStream var1) throws IOException {
      RES_START_DUNGEON var2 = new RES_START_DUNGEON();
      var2.maxobjectdrop = new ArrayList();
      var2.consumeitems = new ArrayList();
      var2.cubeitems = new ArrayList();
      var2.mapguids = new ArrayList();

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
               var2.createtime = var1.readInt32();
            } else if (var5 == 24) {
               var2.index = var1.readInt32();
            } else if (var5 == 32) {
               var2.dungeonguid = var1.readUInt64();
            } else if (var5 == 40) {
               var2.score = var1.readInt32();
            } else if (var5 == 48) {
               var2.grade = var1.readInt32();
            } else if (var5 == 56) {
               var2.level = var1.readInt32();
            } else if (var5 == 64) {
               var2.earngold = var1.readInt32();
            } else if (var5 == 72) {
               var2.earnexp = var1.readInt32();
            } else if (var5 == 80) {
               var2.enchant = var1.readBool();
            } else if (var5 == 88) {
               var2.entercomplete = var1.readInt32();
            } else if (var5 == 96) {
               var2.smash = var1.readInt32();
            } else if (var5 == 104) {
               var2.counter = var1.readInt32();
            } else if (var5 == 112) {
               var2.multihit = var1.readInt32();
            } else if (var5 == 120) {
               var2.receivedamage = var1.readInt32();
            } else if (var5 == 128) {
               var2.givedamage = var1.readInt32();
            } else if (var5 == 136) {
               var2.startstageguid = var1.readInt32();
            } else if (var5 == 144) {
               var2.gcontribute = var1.readInt32();
            } else if (var5 == 154) {
               Codec var10 = ProtobufProxy.create(PT_MAP_OBJECT_DROP.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.maxobjectdrop == null) {
                  var2.maxobjectdrop = new ArrayList();
               }

               var2.maxobjectdrop.add((PT_MAP_OBJECT_DROP)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 162) {
               Codec var11 = ProtobufProxy.create(PT_CONSUME_ITEMS.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var17 = var1.readRawVarint32();
               int var23 = var1.pushLimit(var17);
               if (var2.consumeitems == null) {
                  var2.consumeitems = new ArrayList();
               }

               var2.consumeitems.add((PT_CONSUME_ITEMS)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var23);
            } else if (var5 == 170) {
               Codec var12 = ProtobufProxy.create(PT_CUBE_ITEMS.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var18 = var1.readRawVarint32();
               int var24 = var1.pushLimit(var18);
               if (var2.cubeitems == null) {
                  var2.cubeitems = new ArrayList();
               }

               var2.cubeitems.add((PT_CUBE_ITEMS)var12.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var24);
            } else if (var5 == 176) {
               var2.dindex = var1.readInt32();
            } else if (var5 == 186) {
               Codec var13 = ProtobufProxy.create(PT_USER_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var19 = var1.readRawVarint32();
               int var25 = var1.pushLimit(var19);
               var2.character = (PT_USER_INFO)var13.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var25);
            } else if (var5 == 194) {
               Codec var14 = ProtobufProxy.create(PT_CHAMPION_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var20 = var1.readRawVarint32();
               int var26 = var1.pushLimit(var20);
               var2.champion = (PT_CHAMPION_INFO)var14.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var26);
            } else if (var5 == 202) {
               Codec var15 = ProtobufProxy.create(PT_USER_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var21 = var1.readRawVarint32();
               int var27 = var1.pushLimit(var21);
               var2.enemycharacter = (PT_USER_INFO)var15.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var27);
            } else if (var5 == 210) {
               Codec var16 = ProtobufProxy.create(PT_MAP_GUIDS.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var22 = var1.readRawVarint32();
               int var28 = var1.pushLimit(var22);
               if (var2.mapguids == null) {
                  var2.mapguids = new ArrayList();
               }

               var2.mapguids.add((PT_MAP_GUIDS)var16.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var28);
            } else if (var5 == 216) {
               var2.lastdungeonrankingscore = var1.readUInt64();
            } else if (var5 == 224) {
               var2.requestedindex = var1.readInt32();
            } else if (var5 == 232) {
               var2.mazeinfoindex = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_START_DUNGEON.class);
         return this.descriptor = var1;
      }
   }
}
