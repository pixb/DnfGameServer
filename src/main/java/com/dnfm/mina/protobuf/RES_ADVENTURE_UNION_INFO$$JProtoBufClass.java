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

public class RES_ADVENTURE_UNION_INFO$$JProtoBufClass implements Codec<RES_ADVENTURE_UNION_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_ADVENTURE_UNION_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_ADVENTURE_UNION_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_ADVENTURE_UNION_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var27 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var27);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.exp)) {
         Integer var28 = var1.exp;
         var2 += CodedOutputStream.computeInt32Size(2, var28);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var29 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(3, var29);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.day)) {
         Integer var30 = var1.day;
         var2 += CodedOutputStream.computeInt32Size(4, var30);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.typicalcharacterguid)) {
         Long var31 = var1.typicalcharacterguid;
         var2 += CodedOutputStream.computeUInt64Size(5, var31);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var32 = var1.name;
         var2 += CodedOutputStream.computeStringSize(6, var32);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.updatetime)) {
         Long var33 = var1.updatetime;
         var2 += CodedOutputStream.computeUInt64Size(7, var33);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.lastchangenametime)) {
         Long var34 = var1.lastchangenametime;
         var2 += CodedOutputStream.computeUInt64Size(8, var34);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.expedition)) {
         PT_ADVENTURE_UNION_EXPEDITION var35 = var1.expedition;
         var2 += CodedConstant.computeSize(9, var35, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.expeditions)) {
         List var36 = var1.expeditions;
         var2 += CodedConstant.computeListSize(10, var36, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.collections)) {
         List var37 = var1.collections;
         var2 += CodedConstant.computeListSize(11, var37, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.collectionslots)) {
         List var38 = var1.collectionslots;
         var2 += CodedConstant.computeListSize(12, var38, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.shareboardbackground)) {
         Integer var39 = var1.shareboardbackground;
         var2 += CodedOutputStream.computeInt32Size(13, var39);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.shareboardframe)) {
         Integer var40 = var1.shareboardframe;
         var2 += CodedOutputStream.computeInt32Size(14, var40);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.shareboardslotlist)) {
         List var41 = var1.shareboardslotlist;
         var2 += CodedConstant.computeListSize(15, var41, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.shareboardbackgroundlist)) {
         List var42 = var1.shareboardbackgroundlist;
         var2 += CodedConstant.computeListSize(16, var42, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.shareboardframelist)) {
         List var43 = var1.shareboardframelist;
         var2 += CodedConstant.computeListSize(17, var43, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.shareboardshowantievilscore)) {
         Boolean var44 = var1.shareboardshowantievilscore;
         var2 += CodedOutputStream.computeBoolSize(18, var44);
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.autosearchcount)) {
         Integer var45 = var1.autosearchcount;
         var2 += CodedOutputStream.computeInt32Size(19, var45);
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.receivedcollectionrewards)) {
         List var46 = var1.receivedcollectionrewards;
         var2 += CodedConstant.computeListSize(20, var46, FieldType.INT32, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.levelrewards)) {
         List var47 = var1.levelrewards;
         var2 += CodedConstant.computeListSize(21, var47, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.shareboardtotalantievilscore)) {
         Integer var48 = var1.shareboardtotalantievilscore;
         var2 += CodedOutputStream.computeInt32Size(22, var48);
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.shareboardantievilscorerefresh)) {
         Boolean var49 = var1.shareboardantievilscorerefresh;
         var2 += CodedOutputStream.computeBoolSize(23, var49);
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.isadventureCondition)) {
         Boolean var50 = var1.isadventureCondition;
         var2 += CodedOutputStream.computeBoolSize(24, var50);
      }

      return var2;
   }

   public void doWriteTo(RES_ADVENTURE_UNION_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var27 = var1.error;
         if (var27 != null) {
            var2.writeInt32(1, var27);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.exp)) {
         Integer var28 = var1.exp;
         if (var28 != null) {
            var2.writeInt32(2, var28);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var29 = var1.level;
         if (var29 != null) {
            var2.writeInt32(3, var29);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.day)) {
         Integer var30 = var1.day;
         if (var30 != null) {
            var2.writeInt32(4, var30);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.typicalcharacterguid)) {
         Long var31 = var1.typicalcharacterguid;
         if (var31 != null) {
            var2.writeUInt64(5, var31);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var32 = var1.name;
         if (var32 != null) {
            var2.writeString(6, var32);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.updatetime)) {
         Long var33 = var1.updatetime;
         if (var33 != null) {
            var2.writeUInt64(7, var33);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.lastchangenametime)) {
         Long var34 = var1.lastchangenametime;
         if (var34 != null) {
            var2.writeUInt64(8, var34);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.expedition)) {
         PT_ADVENTURE_UNION_EXPEDITION var35 = var1.expedition;
         if (var35 != null) {
            CodedConstant.writeObject(var2, 9, FieldType.OBJECT, var35, false);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.expeditions)) {
         List var36 = var1.expeditions;
         if (var36 != null) {
            CodedConstant.writeToList(var2, 10, FieldType.OBJECT, var36, false);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.collections)) {
         List var37 = var1.collections;
         if (var37 != null) {
            CodedConstant.writeToList(var2, 11, FieldType.OBJECT, var37, false);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.collectionslots)) {
         List var38 = var1.collectionslots;
         if (var38 != null) {
            CodedConstant.writeToList(var2, 12, FieldType.OBJECT, var38, false);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.shareboardbackground)) {
         Integer var39 = var1.shareboardbackground;
         if (var39 != null) {
            var2.writeInt32(13, var39);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.shareboardframe)) {
         Integer var40 = var1.shareboardframe;
         if (var40 != null) {
            var2.writeInt32(14, var40);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.shareboardslotlist)) {
         List var41 = var1.shareboardslotlist;
         if (var41 != null) {
            CodedConstant.writeToList(var2, 15, FieldType.OBJECT, var41, false);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.shareboardbackgroundlist)) {
         List var42 = var1.shareboardbackgroundlist;
         if (var42 != null) {
            CodedConstant.writeToList(var2, 16, FieldType.OBJECT, var42, false);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.shareboardframelist)) {
         List var43 = var1.shareboardframelist;
         if (var43 != null) {
            CodedConstant.writeToList(var2, 17, FieldType.OBJECT, var43, false);
         }
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.shareboardshowantievilscore)) {
         Boolean var44 = var1.shareboardshowantievilscore;
         if (var44 != null) {
            var2.writeBool(18, var44);
         }
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.autosearchcount)) {
         Integer var45 = var1.autosearchcount;
         if (var45 != null) {
            var2.writeInt32(19, var45);
         }
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.receivedcollectionrewards)) {
         List var46 = var1.receivedcollectionrewards;
         if (var46 != null) {
            CodedConstant.writeToList(var2, 20, FieldType.INT32, var46, true);
         }
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.levelrewards)) {
         List var47 = var1.levelrewards;
         if (var47 != null) {
            CodedConstant.writeToList(var2, 21, FieldType.OBJECT, var47, false);
         }
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.shareboardtotalantievilscore)) {
         Integer var48 = var1.shareboardtotalantievilscore;
         if (var48 != null) {
            var2.writeInt32(22, var48);
         }
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.shareboardantievilscorerefresh)) {
         Boolean var49 = var1.shareboardantievilscorerefresh;
         if (var49 != null) {
            var2.writeBool(23, var49);
         }
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.isadventureCondition)) {
         Boolean var50 = var1.isadventureCondition;
         if (var50 != null) {
            var2.writeBool(24, var50);
         }
      }

   }

   public void writeTo(RES_ADVENTURE_UNION_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_ADVENTURE_UNION_INFO readFrom(CodedInputStream var1) throws IOException {
      RES_ADVENTURE_UNION_INFO var2 = new RES_ADVENTURE_UNION_INFO();
      var2.expeditions = new ArrayList();
      var2.collections = new ArrayList();
      var2.collectionslots = new ArrayList();
      var2.shareboardslotlist = new ArrayList();
      var2.shareboardbackgroundlist = new ArrayList();
      var2.shareboardframelist = new ArrayList();
      var2.receivedcollectionrewards = new ArrayList();
      var2.levelrewards = new ArrayList();

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
               var2.exp = var1.readInt32();
            } else if (var5 == 24) {
               var2.level = var1.readInt32();
            } else if (var5 == 32) {
               var2.day = var1.readInt32();
            } else if (var5 == 40) {
               var2.typicalcharacterguid = var1.readUInt64();
            } else if (var5 == 50) {
               var2.name = var1.readString();
            } else if (var5 == 56) {
               var2.updatetime = var1.readUInt64();
            } else if (var5 == 64) {
               var2.lastchangenametime = var1.readUInt64();
            } else if (var5 == 74) {
               Codec var17 = ProtobufProxy.create(PT_ADVENTURE_UNION_EXPEDITION.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var26 = var1.readRawVarint32();
               int var35 = var1.pushLimit(var26);
               var2.expedition = (PT_ADVENTURE_UNION_EXPEDITION)var17.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var35);
            } else if (var5 == 82) {
               Codec var16 = ProtobufProxy.create(PT_ADVENTURE_UNION_EXPEDITION.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var25 = var1.readRawVarint32();
               int var34 = var1.pushLimit(var25);
               if (var2.expeditions == null) {
                  var2.expeditions = new ArrayList();
               }

               var2.expeditions.add((PT_ADVENTURE_UNION_EXPEDITION)var16.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var34);
            } else if (var5 == 90) {
               Codec var15 = ProtobufProxy.create(PT_ADVENTURE_UNION_COLLECTION.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var24 = var1.readRawVarint32();
               int var33 = var1.pushLimit(var24);
               if (var2.collections == null) {
                  var2.collections = new ArrayList();
               }

               var2.collections.add((PT_ADVENTURE_UNION_COLLECTION)var15.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var33);
            } else if (var5 == 98) {
               Codec var14 = ProtobufProxy.create(PT_ADVENTURE_UNION_COLLECTION_SLOT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var23 = var1.readRawVarint32();
               int var32 = var1.pushLimit(var23);
               if (var2.collectionslots == null) {
                  var2.collectionslots = new ArrayList();
               }

               var2.collectionslots.add((PT_ADVENTURE_UNION_COLLECTION_SLOT)var14.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var32);
            } else if (var5 == 104) {
               var2.shareboardbackground = var1.readInt32();
            } else if (var5 == 112) {
               var2.shareboardframe = var1.readInt32();
            } else if (var5 == 122) {
               Codec var13 = ProtobufProxy.create(PT_ADVENTURE_UNION_SHAREBOARD_SLOT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var22 = var1.readRawVarint32();
               int var31 = var1.pushLimit(var22);
               if (var2.shareboardslotlist == null) {
                  var2.shareboardslotlist = new ArrayList();
               }

               var2.shareboardslotlist.add((PT_ADVENTURE_UNION_SHAREBOARD_SLOT)var13.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var31);
            } else if (var5 == 130) {
               Codec var12 = ProtobufProxy.create(PT_ADVENTURE_UNION_SHAREBOARD_BACKGROUND.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var21 = var1.readRawVarint32();
               int var30 = var1.pushLimit(var21);
               if (var2.shareboardbackgroundlist == null) {
                  var2.shareboardbackgroundlist = new ArrayList();
               }

               var2.shareboardbackgroundlist.add((PT_ADVENTURE_UNION_SHAREBOARD_BACKGROUND)var12.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var30);
            } else if (var5 == 138) {
               Codec var11 = ProtobufProxy.create(PT_ADVENTURE_UNION_SHAREBOARD_FRAME.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var20 = var1.readRawVarint32();
               int var29 = var1.pushLimit(var20);
               if (var2.shareboardframelist == null) {
                  var2.shareboardframelist = new ArrayList();
               }

               var2.shareboardframelist.add((PT_ADVENTURE_UNION_SHAREBOARD_FRAME)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var29);
            } else if (var5 == 144) {
               var2.shareboardshowantievilscore = var1.readBool();
            } else if (var5 == 152) {
               var2.autosearchcount = var1.readInt32();
            } else if (var5 == 160) {
               if (var2.receivedcollectionrewards == null) {
                  var2.receivedcollectionrewards = new ArrayList();
               }

               var2.receivedcollectionrewards.add(var1.readInt32());
            } else if (var5 == 162) {
               int var19 = var1.readRawVarint32();
               int var28 = var1.pushLimit(var19);
               if (var2.receivedcollectionrewards == null) {
                  var2.receivedcollectionrewards = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.receivedcollectionrewards.add(var1.readInt32());
               }

               var1.popLimit(var28);
            } else if (var5 != 170) {
               if (var5 == 176) {
                  var2.shareboardtotalantievilscore = var1.readInt32();
               } else if (var5 == 184) {
                  var2.shareboardantievilscorerefresh = var1.readBool();
               } else if (var5 == 192) {
                  var2.isadventureCondition = var1.readBool();
               } else {
                  var1.skipField(var5);
               }
            } else if (var5 != 162) {
               Codec var10 = ProtobufProxy.create(PT_ADVENTURE_UNION_LEVEL_REWARD.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var18 = var1.readRawVarint32();
               int var27 = var1.pushLimit(var18);
               if (var2.levelrewards == null) {
                  var2.levelrewards = new ArrayList();
               }

               var2.levelrewards.add((PT_ADVENTURE_UNION_LEVEL_REWARD)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var27);
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.receivedcollectionrewards == null) {
                  var2.receivedcollectionrewards = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.receivedcollectionrewards.add(var1.readInt32());
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_ADVENTURE_UNION_INFO.class);
         return this.descriptor = var1;
      }
   }
}
