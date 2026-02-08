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

public class PT_AUCTION_BASE$$JProtoBufClass implements Codec<PT_AUCTION_BASE>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_AUCTION_BASE var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_AUCTION_BASE decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_AUCTION_BASE var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var34 = var1.guid;
         var2 += CodedOutputStream.computeUInt64Size(1, var34);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.upgrade)) {
         Integer var35 = var1.upgrade;
         var2 += CodedOutputStream.computeInt32Size(2, var35);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.quality)) {
         Integer var36 = var1.quality;
         var2 += CodedOutputStream.computeInt32Size(3, var36);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.endurance)) {
         Integer var37 = var1.endurance;
         var2 += CodedOutputStream.computeInt32Size(4, var37);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.enchant)) {
         Integer var38 = var1.enchant;
         var2 += CodedOutputStream.computeInt32Size(5, var38);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.reforge)) {
         Integer var39 = var1.reforge;
         var2 += CodedOutputStream.computeInt32Size(6, var39);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.amplify)) {
         Integer var40 = var1.amplify;
         var2 += CodedOutputStream.computeInt32Size(7, var40);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.aoption)) {
         Integer var41 = var1.aoption;
         var2 += CodedOutputStream.computeInt32Size(8, var41);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.emblem)) {
         List var42 = var1.emblem;
         var2 += CodedConstant.computeListSize(9, var42, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.card)) {
         List var43 = var1.card;
         var2 += CodedConstant.computeListSize(10, var43, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.scount)) {
         Integer var44 = var1.scount;
         var2 += CodedOutputStream.computeInt32Size(11, var44);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.tcount)) {
         Integer var45 = var1.tcount;
         var2 += CodedOutputStream.computeInt32Size(12, var45);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.expiretime)) {
         Long var46 = var1.expiretime;
         var2 += CodedOutputStream.computeInt64Size(13, var46);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.rappearance)) {
         Boolean var47 = var1.rappearance;
         var2 += CodedOutputStream.computeBoolSize(14, var47);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.roption)) {
         List var48 = var1.roption;
         var2 += CodedConstant.computeListSize(15, var48, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.skin)) {
         Integer var49 = var1.skin;
         var2 += CodedOutputStream.computeInt32Size(16, var49);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.skinguid)) {
         Long var50 = var1.skinguid;
         var2 += CodedOutputStream.computeUInt64Size(17, var50);
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.locked)) {
         Boolean var51 = var1.locked;
         var2 += CodedOutputStream.computeBoolSize(18, var51);
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.seal)) {
         Boolean var52 = var1.seal;
         var2 += CodedOutputStream.computeBoolSize(19, var52);
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var53 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(20, var53);
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.auid)) {
         Long var54 = var1.auid;
         var2 += CodedOutputStream.computeUInt64Size(21, var54);
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.bidder)) {
         Long var55 = var1.bidder;
         var2 += CodedOutputStream.computeUInt64Size(22, var55);
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.price)) {
         Integer var56 = var1.price;
         var2 += CodedOutputStream.computeInt32Size(23, var56);
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.enddate)) {
         Long var57 = var1.enddate;
         var2 += CodedOutputStream.computeUInt64Size(24, var57);
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.registcount)) {
         Integer var58 = var1.registcount;
         var2 += CodedOutputStream.computeInt32Size(25, var58);
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.tera)) {
         Integer var59 = var1.tera;
         var2 += CodedOutputStream.computeInt32Size(26, var59);
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.buyprice)) {
         Integer var60 = var1.buyprice;
         var2 += CodedOutputStream.computeInt32Size(27, var60);
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.flag)) {
         Boolean var61 = var1.flag;
         var2 += CodedOutputStream.computeBoolSize(28, var61);
      }

      Object var31 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var62 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(29, var62);
      }

      Object var32 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var63 = var1.count;
         var2 += CodedOutputStream.computeInt32Size(30, var63);
      }

      Object var33 = null;
      if (!CodedConstant.isNull(var1.season)) {
         Integer var64 = var1.season;
         var2 += CodedOutputStream.computeInt32Size(31, var64);
      }

      return var2;
   }

   public void doWriteTo(PT_AUCTION_BASE var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var34 = var1.guid;
         if (var34 != null) {
            var2.writeUInt64(1, var34);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.upgrade)) {
         Integer var35 = var1.upgrade;
         if (var35 != null) {
            var2.writeInt32(2, var35);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.quality)) {
         Integer var36 = var1.quality;
         if (var36 != null) {
            var2.writeInt32(3, var36);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.endurance)) {
         Integer var37 = var1.endurance;
         if (var37 != null) {
            var2.writeInt32(4, var37);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.enchant)) {
         Integer var38 = var1.enchant;
         if (var38 != null) {
            var2.writeInt32(5, var38);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.reforge)) {
         Integer var39 = var1.reforge;
         if (var39 != null) {
            var2.writeInt32(6, var39);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.amplify)) {
         Integer var40 = var1.amplify;
         if (var40 != null) {
            var2.writeInt32(7, var40);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.aoption)) {
         Integer var41 = var1.aoption;
         if (var41 != null) {
            var2.writeInt32(8, var41);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.emblem)) {
         List var42 = var1.emblem;
         if (var42 != null) {
            CodedConstant.writeToList(var2, 9, FieldType.OBJECT, var42, false);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.card)) {
         List var43 = var1.card;
         if (var43 != null) {
            CodedConstant.writeToList(var2, 10, FieldType.OBJECT, var43, false);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.scount)) {
         Integer var44 = var1.scount;
         if (var44 != null) {
            var2.writeInt32(11, var44);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.tcount)) {
         Integer var45 = var1.tcount;
         if (var45 != null) {
            var2.writeInt32(12, var45);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.expiretime)) {
         Long var46 = var1.expiretime;
         if (var46 != null) {
            var2.writeInt64(13, var46);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.rappearance)) {
         Boolean var47 = var1.rappearance;
         if (var47 != null) {
            var2.writeBool(14, var47);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.roption)) {
         List var48 = var1.roption;
         if (var48 != null) {
            CodedConstant.writeToList(var2, 15, FieldType.OBJECT, var48, false);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.skin)) {
         Integer var49 = var1.skin;
         if (var49 != null) {
            var2.writeInt32(16, var49);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.skinguid)) {
         Long var50 = var1.skinguid;
         if (var50 != null) {
            var2.writeUInt64(17, var50);
         }
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.locked)) {
         Boolean var51 = var1.locked;
         if (var51 != null) {
            var2.writeBool(18, var51);
         }
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.seal)) {
         Boolean var52 = var1.seal;
         if (var52 != null) {
            var2.writeBool(19, var52);
         }
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var53 = var1.type;
         if (var53 != null) {
            var2.writeInt32(20, var53);
         }
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.auid)) {
         Long var54 = var1.auid;
         if (var54 != null) {
            var2.writeUInt64(21, var54);
         }
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.bidder)) {
         Long var55 = var1.bidder;
         if (var55 != null) {
            var2.writeUInt64(22, var55);
         }
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.price)) {
         Integer var56 = var1.price;
         if (var56 != null) {
            var2.writeInt32(23, var56);
         }
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.enddate)) {
         Long var57 = var1.enddate;
         if (var57 != null) {
            var2.writeUInt64(24, var57);
         }
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.registcount)) {
         Integer var58 = var1.registcount;
         if (var58 != null) {
            var2.writeInt32(25, var58);
         }
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.tera)) {
         Integer var59 = var1.tera;
         if (var59 != null) {
            var2.writeInt32(26, var59);
         }
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.buyprice)) {
         Integer var60 = var1.buyprice;
         if (var60 != null) {
            var2.writeInt32(27, var60);
         }
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.flag)) {
         Boolean var61 = var1.flag;
         if (var61 != null) {
            var2.writeBool(28, var61);
         }
      }

      Object var31 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var62 = var1.index;
         if (var62 != null) {
            var2.writeInt32(29, var62);
         }
      }

      Object var32 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var63 = var1.count;
         if (var63 != null) {
            var2.writeInt32(30, var63);
         }
      }

      Object var33 = null;
      if (!CodedConstant.isNull(var1.season)) {
         Integer var64 = var1.season;
         if (var64 != null) {
            var2.writeInt32(31, var64);
         }
      }

   }

   public void writeTo(PT_AUCTION_BASE var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_AUCTION_BASE readFrom(CodedInputStream var1) throws IOException {
      PT_AUCTION_BASE var2 = new PT_AUCTION_BASE();
      var2.emblem = new ArrayList();
      var2.card = new ArrayList();
      var2.roption = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.guid = var1.readUInt64();
            } else if (var5 == 16) {
               var2.upgrade = var1.readInt32();
            } else if (var5 == 24) {
               var2.quality = var1.readInt32();
            } else if (var5 == 32) {
               var2.endurance = var1.readInt32();
            } else if (var5 == 40) {
               var2.enchant = var1.readInt32();
            } else if (var5 == 48) {
               var2.reforge = var1.readInt32();
            } else if (var5 == 56) {
               var2.amplify = var1.readInt32();
            } else if (var5 == 64) {
               var2.aoption = var1.readInt32();
            } else if (var5 == 74) {
               Codec var10 = ProtobufProxy.create(PT_EMBLEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.emblem == null) {
                  var2.emblem = new ArrayList();
               }

               var2.emblem.add((PT_EMBLEM)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 82) {
               Codec var11 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var13 = var1.readRawVarint32();
               int var15 = var1.pushLimit(var13);
               if (var2.card == null) {
                  var2.card = new ArrayList();
               }

               var2.card.add((PT_STACKABLE)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var15);
            } else if (var5 == 88) {
               var2.scount = var1.readInt32();
            } else if (var5 == 96) {
               var2.tcount = var1.readInt32();
            } else if (var5 == 104) {
               var2.expiretime = var1.readInt64();
            } else if (var5 == 112) {
               var2.rappearance = var1.readBool();
            } else if (var5 == 122) {
               Codec var12 = ProtobufProxy.create(PT_RANDOMOPTION_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var14 = var1.readRawVarint32();
               int var16 = var1.pushLimit(var14);
               if (var2.roption == null) {
                  var2.roption = new ArrayList();
               }

               var2.roption.add((PT_RANDOMOPTION_ITEM)var12.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var16);
            } else if (var5 == 128) {
               var2.skin = var1.readInt32();
            } else if (var5 == 136) {
               var2.skinguid = var1.readUInt64();
            } else if (var5 == 144) {
               var2.locked = var1.readBool();
            } else if (var5 == 152) {
               var2.seal = var1.readBool();
            } else if (var5 == 160) {
               var2.type = var1.readInt32();
            } else if (var5 == 168) {
               var2.auid = var1.readUInt64();
            } else if (var5 == 176) {
               var2.bidder = var1.readUInt64();
            } else if (var5 == 184) {
               var2.price = var1.readInt32();
            } else if (var5 == 192) {
               var2.enddate = var1.readUInt64();
            } else if (var5 == 200) {
               var2.registcount = var1.readInt32();
            } else if (var5 == 208) {
               var2.tera = var1.readInt32();
            } else if (var5 == 216) {
               var2.buyprice = var1.readInt32();
            } else if (var5 == 224) {
               var2.flag = var1.readBool();
            } else if (var5 == 232) {
               var2.index = var1.readInt32();
            } else if (var5 == 240) {
               var2.count = var1.readInt32();
            } else if (var5 == 248) {
               var2.season = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_AUCTION_BASE.class);
         return this.descriptor = var1;
      }
   }
}
