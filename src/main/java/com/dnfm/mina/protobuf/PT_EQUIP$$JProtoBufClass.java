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

public class PT_EQUIP$$JProtoBufClass implements Codec<PT_EQUIP>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_EQUIP var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_EQUIP decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_EQUIP var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var33 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(1, var33);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var34 = var1.count;
         var2 += CodedOutputStream.computeInt32Size(2, var34);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var35 = var1.guid;
         var2 += CodedOutputStream.computeUInt64Size(3, var35);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.upgrade)) {
         Integer var36 = var1.upgrade;
         var2 += CodedOutputStream.computeInt32Size(4, var36);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.quality)) {
         Integer var37 = var1.quality;
         var2 += CodedOutputStream.computeInt32Size(5, var37);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.endurance)) {
         Integer var38 = var1.endurance;
         var2 += CodedOutputStream.computeInt32Size(6, var38);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.enchant)) {
         Integer var39 = var1.enchant;
         var2 += CodedOutputStream.computeInt32Size(7, var39);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.reforge)) {
         Integer var40 = var1.reforge;
         var2 += CodedOutputStream.computeInt32Size(8, var40);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.reforgeexp)) {
         Integer var41 = var1.reforgeexp;
         var2 += CodedOutputStream.computeInt32Size(9, var41);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.amplify)) {
         Integer var42 = var1.amplify;
         var2 += CodedOutputStream.computeInt32Size(10, var42);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.aoption)) {
         Integer var43 = var1.aoption;
         var2 += CodedOutputStream.computeInt32Size(11, var43);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.emblem)) {
         List var44 = var1.emblem;
         var2 += CodedConstant.computeListSize(12, var44, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.card)) {
         List var45 = var1.card;
         var2 += CodedConstant.computeListSize(13, var45, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.scount)) {
         Integer var46 = var1.scount;
         var2 += CodedOutputStream.computeInt32Size(14, var46);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.tcount)) {
         Integer var47 = var1.tcount;
         var2 += CodedOutputStream.computeInt32Size(15, var47);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.expiretime)) {
         Long var48 = var1.expiretime;
         var2 += CodedOutputStream.computeInt64Size(16, var48);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.rappearance)) {
         Boolean var49 = var1.rappearance;
         var2 += CodedOutputStream.computeBoolSize(17, var49);
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.roption)) {
         List var50 = var1.roption;
         var2 += CodedConstant.computeListSize(18, var50, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.rnoption)) {
         List var51 = var1.rnoption;
         var2 += CodedConstant.computeListSize(19, var51, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.skin)) {
         Integer var52 = var1.skin;
         var2 += CodedOutputStream.computeInt32Size(20, var52);
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.skinguid)) {
         Long var53 = var1.skinguid;
         var2 += CodedOutputStream.computeUInt64Size(21, var53);
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.locked)) {
         Boolean var54 = var1.locked;
         var2 += CodedOutputStream.computeBoolSize(22, var54);
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.seal)) {
         Boolean var55 = var1.seal;
         var2 += CodedOutputStream.computeBoolSize(23, var55);
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.enchantindex)) {
         Integer var56 = var1.enchantindex;
         var2 += CodedOutputStream.computeInt32Size(24, var56);
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.crack)) {
         List var57 = var1.crack;
         var2 += CodedConstant.computeListSize(25, var57, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.sindex)) {
         Integer var58 = var1.sindex;
         var2 += CodedOutputStream.computeInt32Size(26, var58);
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.sealing)) {
         Integer var59 = var1.sealing;
         var2 += CodedOutputStream.computeInt32Size(27, var59);
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.upgradepoint)) {
         Integer var60 = var1.upgradepoint;
         var2 += CodedOutputStream.computeInt32Size(28, var60);
      }

      Object var31 = null;
      if (!CodedConstant.isNull(var1.season)) {
         Integer var61 = var1.season;
         var2 += CodedOutputStream.computeInt32Size(29, var61);
      }

      Object var32 = null;
      if (!CodedConstant.isNull(var1.slot)) {
         Integer var62 = var1.slot;
         var2 += CodedOutputStream.computeInt32Size(30, var62);
      }

      return var2;
   }

   public void doWriteTo(PT_EQUIP var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var33 = var1.index;
         if (var33 != null) {
            var2.writeInt32(1, var33);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var34 = var1.count;
         if (var34 != null) {
            var2.writeInt32(2, var34);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var35 = var1.guid;
         if (var35 != null) {
            var2.writeUInt64(3, var35);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.upgrade)) {
         Integer var36 = var1.upgrade;
         if (var36 != null) {
            var2.writeInt32(4, var36);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.quality)) {
         Integer var37 = var1.quality;
         if (var37 != null) {
            var2.writeInt32(5, var37);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.endurance)) {
         Integer var38 = var1.endurance;
         if (var38 != null) {
            var2.writeInt32(6, var38);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.enchant)) {
         Integer var39 = var1.enchant;
         if (var39 != null) {
            var2.writeInt32(7, var39);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.reforge)) {
         Integer var40 = var1.reforge;
         if (var40 != null) {
            var2.writeInt32(8, var40);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.reforgeexp)) {
         Integer var41 = var1.reforgeexp;
         if (var41 != null) {
            var2.writeInt32(9, var41);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.amplify)) {
         Integer var42 = var1.amplify;
         if (var42 != null) {
            var2.writeInt32(10, var42);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.aoption)) {
         Integer var43 = var1.aoption;
         if (var43 != null) {
            var2.writeInt32(11, var43);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.emblem)) {
         List var44 = var1.emblem;
         if (var44 != null) {
            CodedConstant.writeToList(var2, 12, FieldType.OBJECT, var44, false);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.card)) {
         List var45 = var1.card;
         if (var45 != null) {
            CodedConstant.writeToList(var2, 13, FieldType.OBJECT, var45, false);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.scount)) {
         Integer var46 = var1.scount;
         if (var46 != null) {
            var2.writeInt32(14, var46);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.tcount)) {
         Integer var47 = var1.tcount;
         if (var47 != null) {
            var2.writeInt32(15, var47);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.expiretime)) {
         Long var48 = var1.expiretime;
         if (var48 != null) {
            var2.writeInt64(16, var48);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.rappearance)) {
         Boolean var49 = var1.rappearance;
         if (var49 != null) {
            var2.writeBool(17, var49);
         }
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.roption)) {
         List var50 = var1.roption;
         if (var50 != null) {
            CodedConstant.writeToList(var2, 18, FieldType.OBJECT, var50, false);
         }
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.rnoption)) {
         List var51 = var1.rnoption;
         if (var51 != null) {
            CodedConstant.writeToList(var2, 19, FieldType.OBJECT, var51, false);
         }
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.skin)) {
         Integer var52 = var1.skin;
         if (var52 != null) {
            var2.writeInt32(20, var52);
         }
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.skinguid)) {
         Long var53 = var1.skinguid;
         if (var53 != null) {
            var2.writeUInt64(21, var53);
         }
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.locked)) {
         Boolean var54 = var1.locked;
         if (var54 != null) {
            var2.writeBool(22, var54);
         }
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.seal)) {
         Boolean var55 = var1.seal;
         if (var55 != null) {
            var2.writeBool(23, var55);
         }
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.enchantindex)) {
         Integer var56 = var1.enchantindex;
         if (var56 != null) {
            var2.writeInt32(24, var56);
         }
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.crack)) {
         List var57 = var1.crack;
         if (var57 != null) {
            CodedConstant.writeToList(var2, 25, FieldType.OBJECT, var57, false);
         }
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.sindex)) {
         Integer var58 = var1.sindex;
         if (var58 != null) {
            var2.writeInt32(26, var58);
         }
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.sealing)) {
         Integer var59 = var1.sealing;
         if (var59 != null) {
            var2.writeInt32(27, var59);
         }
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.upgradepoint)) {
         Integer var60 = var1.upgradepoint;
         if (var60 != null) {
            var2.writeInt32(28, var60);
         }
      }

      Object var31 = null;
      if (!CodedConstant.isNull(var1.season)) {
         Integer var61 = var1.season;
         if (var61 != null) {
            var2.writeInt32(29, var61);
         }
      }

      Object var32 = null;
      if (!CodedConstant.isNull(var1.slot)) {
         Integer var62 = var1.slot;
         if (var62 != null) {
            var2.writeInt32(30, var62);
         }
      }

   }

   public void writeTo(PT_EQUIP var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_EQUIP readFrom(CodedInputStream var1) throws IOException {
      PT_EQUIP var2 = new PT_EQUIP();
      var2.emblem = new ArrayList();
      var2.card = new ArrayList();
      var2.roption = new ArrayList();
      var2.rnoption = new ArrayList();
      var2.crack = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.index = var1.readInt32();
            } else if (var5 == 16) {
               var2.count = var1.readInt32();
            } else if (var5 == 24) {
               var2.guid = var1.readUInt64();
            } else if (var5 == 32) {
               var2.upgrade = var1.readInt32();
            } else if (var5 == 40) {
               var2.quality = var1.readInt32();
            } else if (var5 == 48) {
               var2.endurance = var1.readInt32();
            } else if (var5 == 56) {
               var2.enchant = var1.readInt32();
            } else if (var5 == 64) {
               var2.reforge = var1.readInt32();
            } else if (var5 == 72) {
               var2.reforgeexp = var1.readInt32();
            } else if (var5 == 80) {
               var2.amplify = var1.readInt32();
            } else if (var5 == 88) {
               var2.aoption = var1.readInt32();
            } else if (var5 == 98) {
               Codec var10 = ProtobufProxy.create(PT_EMBLEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.emblem == null) {
                  var2.emblem = new ArrayList();
               }

               var2.emblem.add((PT_EMBLEM)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 106) {
               Codec var11 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var15 = var1.readRawVarint32();
               int var19 = var1.pushLimit(var15);
               if (var2.card == null) {
                  var2.card = new ArrayList();
               }

               var2.card.add((PT_STACKABLE)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var19);
            } else if (var5 == 112) {
               var2.scount = var1.readInt32();
            } else if (var5 == 120) {
               var2.tcount = var1.readInt32();
            } else if (var5 == 128) {
               var2.expiretime = var1.readInt64();
            } else if (var5 == 136) {
               var2.rappearance = var1.readBool();
            } else if (var5 == 146) {
               Codec var12 = ProtobufProxy.create(PT_RANDOMOPTION_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var16 = var1.readRawVarint32();
               int var20 = var1.pushLimit(var16);
               if (var2.roption == null) {
                  var2.roption = new ArrayList();
               }

               var2.roption.add((PT_RANDOMOPTION_ITEM)var12.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var20);
            } else if (var5 == 154) {
               Codec var13 = ProtobufProxy.create(PT_RANDOMOPTION_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var17 = var1.readRawVarint32();
               int var21 = var1.pushLimit(var17);
               if (var2.rnoption == null) {
                  var2.rnoption = new ArrayList();
               }

               var2.rnoption.add((PT_RANDOMOPTION_ITEM)var13.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var21);
            } else if (var5 == 160) {
               var2.skin = var1.readInt32();
            } else if (var5 == 168) {
               var2.skinguid = var1.readUInt64();
            } else if (var5 == 176) {
               var2.locked = var1.readBool();
            } else if (var5 == 184) {
               var2.seal = var1.readBool();
            } else if (var5 == 192) {
               var2.enchantindex = var1.readInt32();
            } else if (var5 == 202) {
               Codec var14 = ProtobufProxy.create(PT_CRACK.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var18 = var1.readRawVarint32();
               int var22 = var1.pushLimit(var18);
               if (var2.crack == null) {
                  var2.crack = new ArrayList();
               }

               var2.crack.add((PT_CRACK)var14.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var22);
            } else if (var5 == 208) {
               var2.sindex = var1.readInt32();
            } else if (var5 == 216) {
               var2.sealing = var1.readInt32();
            } else if (var5 == 224) {
               var2.upgradepoint = var1.readInt32();
            } else if (var5 == 232) {
               var2.season = var1.readInt32();
            } else if (var5 == 240) {
               var2.slot = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_EQUIP.class);
         return this.descriptor = var1;
      }
   }
}
