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

public class RES_MULTI_PLAY_START_DUNGEON$$JProtoBufClass implements Codec<RES_MULTI_PLAY_START_DUNGEON>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_MULTI_PLAY_START_DUNGEON var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_MULTI_PLAY_START_DUNGEON decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_MULTI_PLAY_START_DUNGEON var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var21 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var21);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var22 = var1.dungeonguid;
         var2 += CodedOutputStream.computeUInt64Size(2, var22);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var23 = var1.partyguid;
         var2 += CodedOutputStream.computeUInt64Size(3, var23);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.partyleaderguid)) {
         Long var24 = var1.partyleaderguid;
         var2 += CodedOutputStream.computeUInt64Size(4, var24);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.fatigue)) {
         Integer var25 = var1.fatigue;
         var2 += CodedOutputStream.computeInt32Size(5, var25);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var26 = var1.grade;
         var2 += CodedOutputStream.computeInt32Size(6, var26);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var27 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(7, var27);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var28 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(8, var28);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.matchtype)) {
         Integer var29 = var1.matchtype;
         var2 += CodedOutputStream.computeInt32Size(9, var29);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.apcmode)) {
         Boolean var30 = var1.apcmode;
         var2 += CodedOutputStream.computeBoolSize(10, var30);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.characinfos)) {
         List var31 = var1.characinfos;
         var2 += CodedConstant.computeListSize(11, var31, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.champion)) {
         PT_CHAMPION_INFO var32 = var1.champion;
         var2 += CodedConstant.computeSize(12, var32, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.mapguids)) {
         List var33 = var1.mapguids;
         var2 += CodedConstant.computeListSize(13, var33, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.partyleaderjob)) {
         Integer var34 = var1.partyleaderjob;
         var2 += CodedOutputStream.computeInt32Size(14, var34);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.customdata)) {
         PT_CUSTOM_PVP_ROOM_SETTING var35 = var1.customdata;
         var2 += CodedConstant.computeSize(15, var35, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.requestedindex)) {
         Integer var36 = var1.requestedindex;
         var2 += CodedOutputStream.computeInt32Size(16, var36);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.mazeinfoindex)) {
         Integer var37 = var1.mazeinfoindex;
         var2 += CodedOutputStream.computeInt32Size(17, var37);
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.battleoptionlist)) {
         List var38 = var1.battleoptionlist;
         var2 += CodedConstant.computeListSize(18, var38, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(RES_MULTI_PLAY_START_DUNGEON var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var21 = var1.error;
         if (var21 != null) {
            var2.writeInt32(1, var21);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var22 = var1.dungeonguid;
         if (var22 != null) {
            var2.writeUInt64(2, var22);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var23 = var1.partyguid;
         if (var23 != null) {
            var2.writeUInt64(3, var23);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.partyleaderguid)) {
         Long var24 = var1.partyleaderguid;
         if (var24 != null) {
            var2.writeUInt64(4, var24);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.fatigue)) {
         Integer var25 = var1.fatigue;
         if (var25 != null) {
            var2.writeInt32(5, var25);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var26 = var1.grade;
         if (var26 != null) {
            var2.writeInt32(6, var26);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var27 = var1.index;
         if (var27 != null) {
            var2.writeInt32(7, var27);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var28 = var1.level;
         if (var28 != null) {
            var2.writeInt32(8, var28);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.matchtype)) {
         Integer var29 = var1.matchtype;
         if (var29 != null) {
            var2.writeInt32(9, var29);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.apcmode)) {
         Boolean var30 = var1.apcmode;
         if (var30 != null) {
            var2.writeBool(10, var30);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.characinfos)) {
         List var31 = var1.characinfos;
         if (var31 != null) {
            CodedConstant.writeToList(var2, 11, FieldType.OBJECT, var31, false);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.champion)) {
         PT_CHAMPION_INFO var32 = var1.champion;
         if (var32 != null) {
            CodedConstant.writeObject(var2, 12, FieldType.OBJECT, var32, false);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.mapguids)) {
         List var33 = var1.mapguids;
         if (var33 != null) {
            CodedConstant.writeToList(var2, 13, FieldType.OBJECT, var33, false);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.partyleaderjob)) {
         Integer var34 = var1.partyleaderjob;
         if (var34 != null) {
            var2.writeInt32(14, var34);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.customdata)) {
         PT_CUSTOM_PVP_ROOM_SETTING var35 = var1.customdata;
         if (var35 != null) {
            CodedConstant.writeObject(var2, 15, FieldType.OBJECT, var35, false);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.requestedindex)) {
         Integer var36 = var1.requestedindex;
         if (var36 != null) {
            var2.writeInt32(16, var36);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.mazeinfoindex)) {
         Integer var37 = var1.mazeinfoindex;
         if (var37 != null) {
            var2.writeInt32(17, var37);
         }
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.battleoptionlist)) {
         List var38 = var1.battleoptionlist;
         if (var38 != null) {
            CodedConstant.writeToList(var2, 18, FieldType.OBJECT, var38, false);
         }
      }

   }

   public void writeTo(RES_MULTI_PLAY_START_DUNGEON var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_MULTI_PLAY_START_DUNGEON readFrom(CodedInputStream var1) throws IOException {
      RES_MULTI_PLAY_START_DUNGEON var2 = new RES_MULTI_PLAY_START_DUNGEON();
      var2.characinfos = new ArrayList();
      var2.mapguids = new ArrayList();
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
               var2.dungeonguid = var1.readUInt64();
            } else if (var5 == 24) {
               var2.partyguid = var1.readUInt64();
            } else if (var5 == 32) {
               var2.partyleaderguid = var1.readUInt64();
            } else if (var5 == 40) {
               var2.fatigue = var1.readInt32();
            } else if (var5 == 48) {
               var2.grade = var1.readInt32();
            } else if (var5 == 56) {
               var2.index = var1.readInt32();
            } else if (var5 == 64) {
               var2.level = var1.readInt32();
            } else if (var5 == 72) {
               var2.matchtype = var1.readInt32();
            } else if (var5 == 80) {
               var2.apcmode = var1.readBool();
            } else if (var5 == 90) {
               Codec var10 = ProtobufProxy.create(PT_USER_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.characinfos == null) {
                  var2.characinfos = new ArrayList();
               }

               var2.characinfos.add((PT_USER_INFO)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 98) {
               Codec var11 = ProtobufProxy.create(PT_CHAMPION_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var15 = var1.readRawVarint32();
               int var19 = var1.pushLimit(var15);
               var2.champion = (PT_CHAMPION_INFO)var11.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var19);
            } else if (var5 == 106) {
               Codec var12 = ProtobufProxy.create(PT_MAP_GUIDS.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var16 = var1.readRawVarint32();
               int var20 = var1.pushLimit(var16);
               if (var2.mapguids == null) {
                  var2.mapguids = new ArrayList();
               }

               var2.mapguids.add((PT_MAP_GUIDS)var12.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var20);
            } else if (var5 == 112) {
               var2.partyleaderjob = var1.readInt32();
            } else if (var5 == 122) {
               Codec var13 = ProtobufProxy.create(PT_CUSTOM_PVP_ROOM_SETTING.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var17 = var1.readRawVarint32();
               int var21 = var1.pushLimit(var17);
               var2.customdata = (PT_CUSTOM_PVP_ROOM_SETTING)var13.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var21);
            } else if (var5 == 128) {
               var2.requestedindex = var1.readInt32();
            } else if (var5 == 136) {
               var2.mazeinfoindex = var1.readInt32();
            } else if (var5 == 146) {
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_MULTI_PLAY_START_DUNGEON.class);
         return this.descriptor = var1;
      }
   }
}
