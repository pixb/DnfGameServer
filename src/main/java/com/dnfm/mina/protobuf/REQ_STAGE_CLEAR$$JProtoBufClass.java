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

public class REQ_STAGE_CLEAR$$JProtoBufClass implements Codec<REQ_STAGE_CLEAR>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_STAGE_CLEAR var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_STAGE_CLEAR decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_STAGE_CLEAR var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.damagehistory)) {
         byte[] var33 = var1.damagehistory;
         var2 += CodedOutputStream.computeByteArraySize(1, var33);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.authkey)) {
         String var34 = var1.authkey;
         var2 += CodedOutputStream.computeStringSize(2, var34);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var35 = var1.dungeonguid;
         var2 += CodedOutputStream.computeUInt64Size(3, var35);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.stage)) {
         Integer var36 = var1.stage;
         var2 += CodedOutputStream.computeInt32Size(4, var36);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.qindex)) {
         List var37 = var1.qindex;
         var2 += CodedConstant.computeListSize(5, var37, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.rankscore)) {
         Integer var38 = var1.rankscore;
         var2 += CodedOutputStream.computeInt32Size(6, var38);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.score)) {
         Integer var39 = var1.score;
         var2 += CodedOutputStream.computeInt32Size(7, var39);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var40 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(8, var40);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.result)) {
         Integer var41 = var1.result;
         var2 += CodedOutputStream.computeInt32Size(9, var41);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.useskill)) {
         List var42 = var1.useskill;
         var2 += CodedConstant.computeListSize(10, var42, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.cleartime)) {
         Long var43 = var1.cleartime;
         var2 += CodedOutputStream.computeUInt64Size(11, var43);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.time)) {
         String var44 = var1.time;
         var2 += CodedOutputStream.computeStringSize(12, var44);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.matchingguid)) {
         Long var45 = var1.matchingguid;
         var2 += CodedOutputStream.computeUInt64Size(13, var45);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.monster)) {
         List var46 = var1.monster;
         var2 += CodedConstant.computeListSize(14, var46, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.pobs)) {
         List var47 = var1.pobs;
         var2 += CodedConstant.computeListSize(15, var47, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.guardiandeal)) {
         Long var48 = var1.guardiandeal;
         var2 += CodedOutputStream.computeInt64Size(16, var48);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.guardianhp)) {
         Long var49 = var1.guardianhp;
         var2 += CodedOutputStream.computeInt64Size(17, var49);
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.guardiandeallist)) {
         List var50 = var1.guardiandeallist;
         var2 += CodedConstant.computeListSize(18, var50, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.gghp)) {
         Integer var51 = var1.gghp;
         var2 += CodedOutputStream.computeInt32Size(19, var51);
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.gglist)) {
         List var52 = var1.gglist;
         var2 += CodedConstant.computeListSize(20, var52, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.wagondeal)) {
         Long var53 = var1.wagondeal;
         var2 += CodedOutputStream.computeInt64Size(21, var53);
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.wagonhp)) {
         Long var54 = var1.wagonhp;
         var2 += CodedOutputStream.computeInt64Size(22, var54);
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.woundedcount)) {
         Integer var55 = var1.woundedcount;
         var2 += CodedOutputStream.computeInt32Size(23, var55);
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.damage)) {
         Long var56 = var1.damage;
         var2 += CodedOutputStream.computeInt64Size(24, var56);
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.behit)) {
         Integer var57 = var1.behit;
         var2 += CodedOutputStream.computeInt32Size(25, var57);
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.leaveusers)) {
         List var58 = var1.leaveusers;
         var2 += CodedConstant.computeListSize(26, var58, FieldType.UINT64, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.gamesafedata)) {
         String var59 = var1.gamesafedata;
         var2 += CodedOutputStream.computeStringSize(27, var59);
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.gamesafedatacrc)) {
         String var60 = var1.gamesafedatacrc;
         var2 += CodedOutputStream.computeStringSize(28, var60);
      }

      Object var31 = null;
      if (!CodedConstant.isNull(var1.gloryinfo)) {
         PT_PVP_GLORY_RESULT var61 = var1.gloryinfo;
         var2 += CodedConstant.computeSize(29, var61, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var32 = null;
      if (!CodedConstant.isNull(var1.bookconditionindexes)) {
         List var62 = var1.bookconditionindexes;
         var2 += CodedConstant.computeListSize(30, var62, FieldType.INT32, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      return var2;
   }

   public void doWriteTo(REQ_STAGE_CLEAR var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.damagehistory)) {
         byte[] var33 = var1.damagehistory;
         if (var33 != null) {
            var2.writeByteArray(1, var33);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.authkey)) {
         String var34 = var1.authkey;
         if (var34 != null) {
            var2.writeString(2, var34);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var35 = var1.dungeonguid;
         if (var35 != null) {
            var2.writeUInt64(3, var35);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.stage)) {
         Integer var36 = var1.stage;
         if (var36 != null) {
            var2.writeInt32(4, var36);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.qindex)) {
         List var37 = var1.qindex;
         if (var37 != null) {
            CodedConstant.writeToList(var2, 5, FieldType.OBJECT, var37, false);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.rankscore)) {
         Integer var38 = var1.rankscore;
         if (var38 != null) {
            var2.writeInt32(6, var38);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.score)) {
         Integer var39 = var1.score;
         if (var39 != null) {
            var2.writeInt32(7, var39);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var40 = var1.type;
         if (var40 != null) {
            var2.writeInt32(8, var40);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.result)) {
         Integer var41 = var1.result;
         if (var41 != null) {
            var2.writeInt32(9, var41);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.useskill)) {
         List var42 = var1.useskill;
         if (var42 != null) {
            CodedConstant.writeToList(var2, 10, FieldType.OBJECT, var42, false);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.cleartime)) {
         Long var43 = var1.cleartime;
         if (var43 != null) {
            var2.writeUInt64(11, var43);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.time)) {
         String var44 = var1.time;
         if (var44 != null) {
            var2.writeString(12, var44);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.matchingguid)) {
         Long var45 = var1.matchingguid;
         if (var45 != null) {
            var2.writeUInt64(13, var45);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.monster)) {
         List var46 = var1.monster;
         if (var46 != null) {
            CodedConstant.writeToList(var2, 14, FieldType.OBJECT, var46, false);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.pobs)) {
         List var47 = var1.pobs;
         if (var47 != null) {
            CodedConstant.writeToList(var2, 15, FieldType.OBJECT, var47, false);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.guardiandeal)) {
         Long var48 = var1.guardiandeal;
         if (var48 != null) {
            var2.writeInt64(16, var48);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.guardianhp)) {
         Long var49 = var1.guardianhp;
         if (var49 != null) {
            var2.writeInt64(17, var49);
         }
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.guardiandeallist)) {
         List var50 = var1.guardiandeallist;
         if (var50 != null) {
            CodedConstant.writeToList(var2, 18, FieldType.OBJECT, var50, false);
         }
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.gghp)) {
         Integer var51 = var1.gghp;
         if (var51 != null) {
            var2.writeInt32(19, var51);
         }
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.gglist)) {
         List var52 = var1.gglist;
         if (var52 != null) {
            CodedConstant.writeToList(var2, 20, FieldType.OBJECT, var52, false);
         }
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.wagondeal)) {
         Long var53 = var1.wagondeal;
         if (var53 != null) {
            var2.writeInt64(21, var53);
         }
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.wagonhp)) {
         Long var54 = var1.wagonhp;
         if (var54 != null) {
            var2.writeInt64(22, var54);
         }
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.woundedcount)) {
         Integer var55 = var1.woundedcount;
         if (var55 != null) {
            var2.writeInt32(23, var55);
         }
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.damage)) {
         Long var56 = var1.damage;
         if (var56 != null) {
            var2.writeInt64(24, var56);
         }
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.behit)) {
         Integer var57 = var1.behit;
         if (var57 != null) {
            var2.writeInt32(25, var57);
         }
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.leaveusers)) {
         List var58 = var1.leaveusers;
         if (var58 != null) {
            CodedConstant.writeToList(var2, 26, FieldType.UINT64, var58, true);
         }
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.gamesafedata)) {
         String var59 = var1.gamesafedata;
         if (var59 != null) {
            var2.writeString(27, var59);
         }
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.gamesafedatacrc)) {
         String var60 = var1.gamesafedatacrc;
         if (var60 != null) {
            var2.writeString(28, var60);
         }
      }

      Object var31 = null;
      if (!CodedConstant.isNull(var1.gloryinfo)) {
         PT_PVP_GLORY_RESULT var61 = var1.gloryinfo;
         if (var61 != null) {
            CodedConstant.writeObject(var2, 29, FieldType.OBJECT, var61, false);
         }
      }

      Object var32 = null;
      if (!CodedConstant.isNull(var1.bookconditionindexes)) {
         List var62 = var1.bookconditionindexes;
         if (var62 != null) {
            CodedConstant.writeToList(var2, 30, FieldType.INT32, var62, true);
         }
      }

   }

   public void writeTo(REQ_STAGE_CLEAR var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_STAGE_CLEAR readFrom(CodedInputStream var1) throws IOException {
      REQ_STAGE_CLEAR var2 = new REQ_STAGE_CLEAR();
      var2.qindex = new ArrayList();
      var2.useskill = new ArrayList();
      var2.monster = new ArrayList();
      var2.pobs = new ArrayList();
      var2.guardiandeallist = new ArrayList();
      var2.gglist = new ArrayList();
      var2.leaveusers = new ArrayList();
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
               Codec var16 = ProtobufProxy.create(PT_DUNGEON_RESULT_QUEST_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var25 = var1.readRawVarint32();
               int var34 = var1.pushLimit(var25);
               if (var2.qindex == null) {
                  var2.qindex = new ArrayList();
               }

               var2.qindex.add((PT_DUNGEON_RESULT_QUEST_INFO)var16.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var34);
            } else if (var5 == 48) {
               var2.rankscore = var1.readInt32();
            } else if (var5 == 56) {
               var2.score = var1.readInt32();
            } else if (var5 == 64) {
               var2.type = var1.readInt32();
            } else if (var5 == 72) {
               var2.result = var1.readInt32();
            } else if (var5 == 82) {
               Codec var15 = ProtobufProxy.create(PT_SKILL_USE_COUNT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var24 = var1.readRawVarint32();
               int var33 = var1.pushLimit(var24);
               if (var2.useskill == null) {
                  var2.useskill = new ArrayList();
               }

               var2.useskill.add((PT_SKILL_USE_COUNT)var15.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var33);
            } else if (var5 == 88) {
               var2.cleartime = var1.readUInt64();
            } else if (var5 == 98) {
               var2.time = var1.readString();
            } else if (var5 == 104) {
               var2.matchingguid = var1.readUInt64();
            } else if (var5 == 114) {
               Codec var14 = ProtobufProxy.create(PT_MONSTER.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var23 = var1.readRawVarint32();
               int var32 = var1.pushLimit(var23);
               if (var2.monster == null) {
                  var2.monster = new ArrayList();
               }

               var2.monster.add((PT_MONSTER)var14.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var32);
            } else if (var5 == 122) {
               Codec var13 = ProtobufProxy.create(PT_PASSIVE_OBJECT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var22 = var1.readRawVarint32();
               int var31 = var1.pushLimit(var22);
               if (var2.pobs == null) {
                  var2.pobs = new ArrayList();
               }

               var2.pobs.add((PT_PASSIVE_OBJECT)var13.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var31);
            } else if (var5 == 128) {
               var2.guardiandeal = var1.readInt64();
            } else if (var5 == 136) {
               var2.guardianhp = var1.readInt64();
            } else if (var5 == 146) {
               Codec var12 = ProtobufProxy.create(PT_GUARDIAN_DEAL.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var21 = var1.readRawVarint32();
               int var30 = var1.pushLimit(var21);
               if (var2.guardiandeallist == null) {
                  var2.guardiandeallist = new ArrayList();
               }

               var2.guardiandeallist.add((PT_GUARDIAN_DEAL)var12.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var30);
            } else if (var5 == 152) {
               var2.gghp = var1.readInt32();
            } else if (var5 == 162) {
               Codec var11 = ProtobufProxy.create(PT_GOLD_GOBLIN_HP.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var20 = var1.readRawVarint32();
               int var29 = var1.pushLimit(var20);
               if (var2.gglist == null) {
                  var2.gglist = new ArrayList();
               }

               var2.gglist.add((PT_GOLD_GOBLIN_HP)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var29);
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
               int var19 = var1.readRawVarint32();
               int var28 = var1.pushLimit(var19);
               if (var2.leaveusers == null) {
                  var2.leaveusers = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.leaveusers.add(var1.readUInt64());
               }

               var1.popLimit(var28);
            } else if (var5 == 218) {
               var2.gamesafedata = var1.readString();
            } else if (var5 == 226) {
               var2.gamesafedatacrc = var1.readString();
            } else if (var5 == 234) {
               if (var5 != 210) {
                  Codec var10 = ProtobufProxy.create(PT_PVP_GLORY_RESULT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
                  int var18 = var1.readRawVarint32();
                  int var27 = var1.pushLimit(var18);
                  var2.gloryinfo = (PT_PVP_GLORY_RESULT)var10.readFrom(var1);
                  var1.checkLastTagWas(0);
                  var1.popLimit(var27);
               } else {
                  int var17 = var1.readRawVarint32();
                  int var26 = var1.pushLimit(var17);
                  if (var2.leaveusers == null) {
                     var2.leaveusers = new ArrayList();
                  }

                  while(var1.getBytesUntilLimit() > 0) {
                     var2.leaveusers.add(var1.readUInt64());
                  }

                  var1.popLimit(var26);
               }
            } else if (var5 == 240) {
               if (var2.bookconditionindexes == null) {
                  var2.bookconditionindexes = new ArrayList();
               }

               var2.bookconditionindexes.add(var1.readInt32());
            } else if (var5 != 242) {
               var1.skipField(var5);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_STAGE_CLEAR.class);
         return this.descriptor = var1;
      }
   }
}
