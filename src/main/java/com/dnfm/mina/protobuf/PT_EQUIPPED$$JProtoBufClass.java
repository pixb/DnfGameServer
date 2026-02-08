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

public class PT_EQUIPPED$$JProtoBufClass implements Codec<PT_EQUIPPED>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_EQUIPPED var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_EQUIPPED decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_EQUIPPED var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var29 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(1, var29);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var30 = var1.guid;
         var2 += CodedOutputStream.computeUInt64Size(2, var30);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.upgrade)) {
         Integer var31 = var1.upgrade;
         var2 += CodedOutputStream.computeInt32Size(3, var31);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.reforge)) {
         Integer var32 = var1.reforge;
         var2 += CodedOutputStream.computeInt32Size(4, var32);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.reforgeexp)) {
         Integer var33 = var1.reforgeexp;
         var2 += CodedOutputStream.computeInt32Size(5, var33);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.amplify)) {
         Integer var34 = var1.amplify;
         var2 += CodedOutputStream.computeInt32Size(6, var34);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.aoption)) {
         Integer var35 = var1.aoption;
         var2 += CodedOutputStream.computeInt32Size(7, var35);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.quality)) {
         Integer var36 = var1.quality;
         var2 += CodedOutputStream.computeInt32Size(8, var36);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.endurance)) {
         Integer var37 = var1.endurance;
         var2 += CodedOutputStream.computeInt32Size(9, var37);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.enchant)) {
         Integer var38 = var1.enchant;
         var2 += CodedOutputStream.computeInt32Size(10, var38);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.slot)) {
         Integer var39 = var1.slot;
         var2 += CodedOutputStream.computeInt32Size(11, var39);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.emblem)) {
         List var40 = var1.emblem;
         var2 += CodedConstant.computeListSize(12, var40, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.card)) {
         List var41 = var1.card;
         var2 += CodedConstant.computeListSize(13, var41, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.scount)) {
         Integer var42 = var1.scount;
         var2 += CodedOutputStream.computeInt32Size(14, var42);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.tcount)) {
         Integer var43 = var1.tcount;
         var2 += CodedOutputStream.computeInt32Size(15, var43);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.expiretime)) {
         Long var44 = var1.expiretime;
         var2 += CodedOutputStream.computeInt64Size(16, var44);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.rappearance)) {
         Boolean var45 = var1.rappearance;
         var2 += CodedOutputStream.computeBoolSize(17, var45);
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.roption)) {
         List var46 = var1.roption;
         var2 += CodedConstant.computeListSize(18, var46, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.rnoption)) {
         List var47 = var1.rnoption;
         var2 += CodedConstant.computeListSize(19, var47, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.skin)) {
         Integer var48 = var1.skin;
         var2 += CodedOutputStream.computeInt32Size(20, var48);
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.locked)) {
         Boolean var49 = var1.locked;
         var2 += CodedOutputStream.computeBoolSize(21, var49);
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.enchantindex)) {
         Integer var50 = var1.enchantindex;
         var2 += CodedOutputStream.computeInt32Size(22, var50);
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.crack)) {
         List var51 = var1.crack;
         var2 += CodedConstant.computeListSize(23, var51, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.sealing)) {
         Integer var52 = var1.sealing;
         var2 += CodedOutputStream.computeInt32Size(24, var52);
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.upgradepoint)) {
         Integer var53 = var1.upgradepoint;
         var2 += CodedOutputStream.computeInt32Size(25, var53);
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.season)) {
         Integer var54 = var1.season;
         var2 += CodedOutputStream.computeInt32Size(26, var54);
      }

      return var2;
   }

   public void doWriteTo(PT_EQUIPPED var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var29 = var1.index;
         if (var29 != null) {
            var2.writeInt32(1, var29);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var30 = var1.guid;
         if (var30 != null) {
            var2.writeUInt64(2, var30);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.upgrade)) {
         Integer var31 = var1.upgrade;
         if (var31 != null) {
            var2.writeInt32(3, var31);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.reforge)) {
         Integer var32 = var1.reforge;
         if (var32 != null) {
            var2.writeInt32(4, var32);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.reforgeexp)) {
         Integer var33 = var1.reforgeexp;
         if (var33 != null) {
            var2.writeInt32(5, var33);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.amplify)) {
         Integer var34 = var1.amplify;
         if (var34 != null) {
            var2.writeInt32(6, var34);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.aoption)) {
         Integer var35 = var1.aoption;
         if (var35 != null) {
            var2.writeInt32(7, var35);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.quality)) {
         Integer var36 = var1.quality;
         if (var36 != null) {
            var2.writeInt32(8, var36);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.endurance)) {
         Integer var37 = var1.endurance;
         if (var37 != null) {
            var2.writeInt32(9, var37);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.enchant)) {
         Integer var38 = var1.enchant;
         if (var38 != null) {
            var2.writeInt32(10, var38);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.slot)) {
         Integer var39 = var1.slot;
         if (var39 != null) {
            var2.writeInt32(11, var39);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.emblem)) {
         List var40 = var1.emblem;
         if (var40 != null) {
            CodedConstant.writeToList(var2, 12, FieldType.OBJECT, var40, false);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.card)) {
         List var41 = var1.card;
         if (var41 != null) {
            CodedConstant.writeToList(var2, 13, FieldType.OBJECT, var41, false);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.scount)) {
         Integer var42 = var1.scount;
         if (var42 != null) {
            var2.writeInt32(14, var42);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.tcount)) {
         Integer var43 = var1.tcount;
         if (var43 != null) {
            var2.writeInt32(15, var43);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.expiretime)) {
         Long var44 = var1.expiretime;
         if (var44 != null) {
            var2.writeInt64(16, var44);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.rappearance)) {
         Boolean var45 = var1.rappearance;
         if (var45 != null) {
            var2.writeBool(17, var45);
         }
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.roption)) {
         List var46 = var1.roption;
         if (var46 != null) {
            CodedConstant.writeToList(var2, 18, FieldType.OBJECT, var46, false);
         }
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.rnoption)) {
         List var47 = var1.rnoption;
         if (var47 != null) {
            CodedConstant.writeToList(var2, 19, FieldType.OBJECT, var47, false);
         }
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.skin)) {
         Integer var48 = var1.skin;
         if (var48 != null) {
            var2.writeInt32(20, var48);
         }
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.locked)) {
         Boolean var49 = var1.locked;
         if (var49 != null) {
            var2.writeBool(21, var49);
         }
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.enchantindex)) {
         Integer var50 = var1.enchantindex;
         if (var50 != null) {
            var2.writeInt32(22, var50);
         }
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.crack)) {
         List var51 = var1.crack;
         if (var51 != null) {
            CodedConstant.writeToList(var2, 23, FieldType.OBJECT, var51, false);
         }
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.sealing)) {
         Integer var52 = var1.sealing;
         if (var52 != null) {
            var2.writeInt32(24, var52);
         }
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.upgradepoint)) {
         Integer var53 = var1.upgradepoint;
         if (var53 != null) {
            var2.writeInt32(25, var53);
         }
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.season)) {
         Integer var54 = var1.season;
         if (var54 != null) {
            var2.writeInt32(26, var54);
         }
      }

   }

   public void writeTo(PT_EQUIPPED var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_EQUIPPED readFrom(CodedInputStream var1) throws IOException {
      PT_EQUIPPED var2 = new PT_EQUIPPED();
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
               var2.guid = var1.readUInt64();
            } else if (var5 == 24) {
               var2.upgrade = var1.readInt32();
            } else if (var5 == 32) {
               var2.reforge = var1.readInt32();
            } else if (var5 == 40) {
               var2.reforgeexp = var1.readInt32();
            } else if (var5 == 48) {
               var2.amplify = var1.readInt32();
            } else if (var5 == 56) {
               var2.aoption = var1.readInt32();
            } else if (var5 == 64) {
               var2.quality = var1.readInt32();
            } else if (var5 == 72) {
               var2.endurance = var1.readInt32();
            } else if (var5 == 80) {
               var2.enchant = var1.readInt32();
            } else if (var5 == 88) {
               var2.slot = var1.readInt32();
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
               var2.locked = var1.readBool();
            } else if (var5 == 176) {
               var2.enchantindex = var1.readInt32();
            } else if (var5 == 186) {
               Codec var14 = ProtobufProxy.create(PT_CRACK.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var18 = var1.readRawVarint32();
               int var22 = var1.pushLimit(var18);
               if (var2.crack == null) {
                  var2.crack = new ArrayList();
               }

               var2.crack.add((PT_CRACK)var14.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var22);
            } else if (var5 == 192) {
               var2.sealing = var1.readInt32();
            } else if (var5 == 200) {
               var2.upgradepoint = var1.readInt32();
            } else if (var5 == 208) {
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_EQUIPPED.class);
         return this.descriptor = var1;
      }
   }
}
