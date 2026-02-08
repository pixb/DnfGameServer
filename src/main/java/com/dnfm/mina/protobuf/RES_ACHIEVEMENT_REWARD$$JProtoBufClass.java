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

public class RES_ACHIEVEMENT_REWARD$$JProtoBufClass implements Codec<RES_ACHIEVEMENT_REWARD>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_ACHIEVEMENT_REWARD var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_ACHIEVEMENT_REWARD decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_ACHIEVEMENT_REWARD var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var30 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var30);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.equipitems)) {
         List var31 = var1.equipitems;
         var2 += CodedConstant.computeListSize(2, var31, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.titleitems)) {
         List var32 = var1.titleitems;
         var2 += CodedConstant.computeListSize(3, var32, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.flagitems)) {
         List var33 = var1.flagitems;
         var2 += CodedConstant.computeListSize(4, var33, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.materialitems)) {
         List var34 = var1.materialitems;
         var2 += CodedConstant.computeListSize(5, var34, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.consumeitems)) {
         List var35 = var1.consumeitems;
         var2 += CodedConstant.computeListSize(6, var35, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.emblemitems)) {
         List var36 = var1.emblemitems;
         var2 += CodedConstant.computeListSize(7, var36, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.carditems)) {
         List var37 = var1.carditems;
         var2 += CodedConstant.computeListSize(8, var37, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.epicpieceitems)) {
         List var38 = var1.epicpieceitems;
         var2 += CodedConstant.computeListSize(9, var38, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.cartifactitems)) {
         List var39 = var1.cartifactitems;
         var2 += CodedConstant.computeListSize(10, var39, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.creatureitems)) {
         List var40 = var1.creatureitems;
         var2 += CodedConstant.computeListSize(11, var40, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.avataritems)) {
         List var41 = var1.avataritems;
         var2 += CodedConstant.computeListSize(12, var41, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.damagefontitems)) {
         List var42 = var1.damagefontitems;
         var2 += CodedConstant.computeListSize(13, var42, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.chatframeitems)) {
         List var43 = var1.chatframeitems;
         var2 += CodedConstant.computeListSize(14, var43, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.characterframeitems)) {
         List var44 = var1.characterframeitems;
         var2 += CodedConstant.computeListSize(15, var44, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.crackitems)) {
         List var45 = var1.crackitems;
         var2 += CodedConstant.computeListSize(16, var45, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.achievement)) {
         List var46 = var1.achievement;
         var2 += CodedConstant.computeListSize(17, var46, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.rewards)) {
         List var47 = var1.rewards;
         var2 += CodedConstant.computeListSize(18, var47, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.currency)) {
         List var48 = var1.currency;
         var2 += CodedConstant.computeListSize(19, var48, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.accountcurrency)) {
         List var49 = var1.accountcurrency;
         var2 += CodedConstant.computeListSize(20, var49, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.invenitems)) {
         PT_ITEMS var50 = var1.invenitems;
         var2 += CodedConstant.computeSize(21, var50, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.crackequipitems)) {
         List var51 = var1.crackequipitems;
         var2 += CodedConstant.computeListSize(22, var51, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.sdavataritems)) {
         List var52 = var1.sdavataritems;
         var2 += CodedConstant.computeListSize(23, var52, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.bookmarkitems)) {
         List var53 = var1.bookmarkitems;
         var2 += CodedConstant.computeListSize(24, var53, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.scrollitems)) {
         List var54 = var1.scrollitems;
         var2 += CodedConstant.computeListSize(25, var54, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.adventureunionlevel)) {
         Integer var55 = var1.adventureunionlevel;
         var2 += CodedOutputStream.computeInt32Size(26, var55);
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.adventureunionexp)) {
         Long var56 = var1.adventureunionexp;
         var2 += CodedOutputStream.computeInt64Size(27, var56);
      }

      return var2;
   }

   public void doWriteTo(RES_ACHIEVEMENT_REWARD var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var30 = var1.error;
         if (var30 != null) {
            var2.writeInt32(1, var30);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.equipitems)) {
         List var31 = var1.equipitems;
         if (var31 != null) {
            CodedConstant.writeToList(var2, 2, FieldType.OBJECT, var31, false);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.titleitems)) {
         List var32 = var1.titleitems;
         if (var32 != null) {
            CodedConstant.writeToList(var2, 3, FieldType.OBJECT, var32, false);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.flagitems)) {
         List var33 = var1.flagitems;
         if (var33 != null) {
            CodedConstant.writeToList(var2, 4, FieldType.OBJECT, var33, false);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.materialitems)) {
         List var34 = var1.materialitems;
         if (var34 != null) {
            CodedConstant.writeToList(var2, 5, FieldType.OBJECT, var34, false);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.consumeitems)) {
         List var35 = var1.consumeitems;
         if (var35 != null) {
            CodedConstant.writeToList(var2, 6, FieldType.OBJECT, var35, false);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.emblemitems)) {
         List var36 = var1.emblemitems;
         if (var36 != null) {
            CodedConstant.writeToList(var2, 7, FieldType.OBJECT, var36, false);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.carditems)) {
         List var37 = var1.carditems;
         if (var37 != null) {
            CodedConstant.writeToList(var2, 8, FieldType.OBJECT, var37, false);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.epicpieceitems)) {
         List var38 = var1.epicpieceitems;
         if (var38 != null) {
            CodedConstant.writeToList(var2, 9, FieldType.OBJECT, var38, false);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.cartifactitems)) {
         List var39 = var1.cartifactitems;
         if (var39 != null) {
            CodedConstant.writeToList(var2, 10, FieldType.OBJECT, var39, false);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.creatureitems)) {
         List var40 = var1.creatureitems;
         if (var40 != null) {
            CodedConstant.writeToList(var2, 11, FieldType.OBJECT, var40, false);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.avataritems)) {
         List var41 = var1.avataritems;
         if (var41 != null) {
            CodedConstant.writeToList(var2, 12, FieldType.OBJECT, var41, false);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.damagefontitems)) {
         List var42 = var1.damagefontitems;
         if (var42 != null) {
            CodedConstant.writeToList(var2, 13, FieldType.OBJECT, var42, false);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.chatframeitems)) {
         List var43 = var1.chatframeitems;
         if (var43 != null) {
            CodedConstant.writeToList(var2, 14, FieldType.OBJECT, var43, false);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.characterframeitems)) {
         List var44 = var1.characterframeitems;
         if (var44 != null) {
            CodedConstant.writeToList(var2, 15, FieldType.OBJECT, var44, false);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.crackitems)) {
         List var45 = var1.crackitems;
         if (var45 != null) {
            CodedConstant.writeToList(var2, 16, FieldType.OBJECT, var45, false);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.achievement)) {
         List var46 = var1.achievement;
         if (var46 != null) {
            CodedConstant.writeToList(var2, 17, FieldType.OBJECT, var46, false);
         }
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.rewards)) {
         List var47 = var1.rewards;
         if (var47 != null) {
            CodedConstant.writeToList(var2, 18, FieldType.OBJECT, var47, false);
         }
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.currency)) {
         List var48 = var1.currency;
         if (var48 != null) {
            CodedConstant.writeToList(var2, 19, FieldType.OBJECT, var48, false);
         }
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.accountcurrency)) {
         List var49 = var1.accountcurrency;
         if (var49 != null) {
            CodedConstant.writeToList(var2, 20, FieldType.OBJECT, var49, false);
         }
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.invenitems)) {
         PT_ITEMS var50 = var1.invenitems;
         if (var50 != null) {
            CodedConstant.writeObject(var2, 21, FieldType.OBJECT, var50, false);
         }
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.crackequipitems)) {
         List var51 = var1.crackequipitems;
         if (var51 != null) {
            CodedConstant.writeToList(var2, 22, FieldType.OBJECT, var51, false);
         }
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.sdavataritems)) {
         List var52 = var1.sdavataritems;
         if (var52 != null) {
            CodedConstant.writeToList(var2, 23, FieldType.OBJECT, var52, false);
         }
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.bookmarkitems)) {
         List var53 = var1.bookmarkitems;
         if (var53 != null) {
            CodedConstant.writeToList(var2, 24, FieldType.OBJECT, var53, false);
         }
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.scrollitems)) {
         List var54 = var1.scrollitems;
         if (var54 != null) {
            CodedConstant.writeToList(var2, 25, FieldType.OBJECT, var54, false);
         }
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.adventureunionlevel)) {
         Integer var55 = var1.adventureunionlevel;
         if (var55 != null) {
            var2.writeInt32(26, var55);
         }
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.adventureunionexp)) {
         Long var56 = var1.adventureunionexp;
         if (var56 != null) {
            var2.writeInt64(27, var56);
         }
      }

   }

   public void writeTo(RES_ACHIEVEMENT_REWARD var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_ACHIEVEMENT_REWARD readFrom(CodedInputStream var1) throws IOException {
      RES_ACHIEVEMENT_REWARD var2 = new RES_ACHIEVEMENT_REWARD();
      var2.equipitems = new ArrayList();
      var2.titleitems = new ArrayList();
      var2.flagitems = new ArrayList();
      var2.materialitems = new ArrayList();
      var2.consumeitems = new ArrayList();
      var2.emblemitems = new ArrayList();
      var2.carditems = new ArrayList();
      var2.epicpieceitems = new ArrayList();
      var2.cartifactitems = new ArrayList();
      var2.creatureitems = new ArrayList();
      var2.avataritems = new ArrayList();
      var2.damagefontitems = new ArrayList();
      var2.chatframeitems = new ArrayList();
      var2.characterframeitems = new ArrayList();
      var2.crackitems = new ArrayList();
      var2.achievement = new ArrayList();
      var2.rewards = new ArrayList();
      var2.currency = new ArrayList();
      var2.accountcurrency = new ArrayList();
      var2.crackequipitems = new ArrayList();
      var2.sdavataritems = new ArrayList();
      var2.bookmarkitems = new ArrayList();
      var2.scrollitems = new ArrayList();

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
               Codec var10 = ProtobufProxy.create(PT_EQUIP.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.equipitems == null) {
                  var2.equipitems = new ArrayList();
               }

               var2.equipitems.add((PT_EQUIP)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 26) {
               Codec var11 = ProtobufProxy.create(PT_EQUIP.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var34 = var1.readRawVarint32();
               int var57 = var1.pushLimit(var34);
               if (var2.titleitems == null) {
                  var2.titleitems = new ArrayList();
               }

               var2.titleitems.add((PT_EQUIP)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var57);
            } else if (var5 == 34) {
               Codec var12 = ProtobufProxy.create(PT_EQUIP.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var35 = var1.readRawVarint32();
               int var58 = var1.pushLimit(var35);
               if (var2.flagitems == null) {
                  var2.flagitems = new ArrayList();
               }

               var2.flagitems.add((PT_EQUIP)var12.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var58);
            } else if (var5 == 42) {
               Codec var13 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var36 = var1.readRawVarint32();
               int var59 = var1.pushLimit(var36);
               if (var2.materialitems == null) {
                  var2.materialitems = new ArrayList();
               }

               var2.materialitems.add((PT_STACKABLE)var13.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var59);
            } else if (var5 == 50) {
               Codec var14 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var37 = var1.readRawVarint32();
               int var60 = var1.pushLimit(var37);
               if (var2.consumeitems == null) {
                  var2.consumeitems = new ArrayList();
               }

               var2.consumeitems.add((PT_STACKABLE)var14.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var60);
            } else if (var5 == 58) {
               Codec var15 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var38 = var1.readRawVarint32();
               int var61 = var1.pushLimit(var38);
               if (var2.emblemitems == null) {
                  var2.emblemitems = new ArrayList();
               }

               var2.emblemitems.add((PT_STACKABLE)var15.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var61);
            } else if (var5 == 66) {
               Codec var16 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var39 = var1.readRawVarint32();
               int var62 = var1.pushLimit(var39);
               if (var2.carditems == null) {
                  var2.carditems = new ArrayList();
               }

               var2.carditems.add((PT_STACKABLE)var16.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var62);
            } else if (var5 == 74) {
               Codec var17 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var40 = var1.readRawVarint32();
               int var63 = var1.pushLimit(var40);
               if (var2.epicpieceitems == null) {
                  var2.epicpieceitems = new ArrayList();
               }

               var2.epicpieceitems.add((PT_STACKABLE)var17.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var63);
            } else if (var5 == 82) {
               Codec var18 = ProtobufProxy.create(PT_ARTIFACT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var41 = var1.readRawVarint32();
               int var64 = var1.pushLimit(var41);
               if (var2.cartifactitems == null) {
                  var2.cartifactitems = new ArrayList();
               }

               var2.cartifactitems.add((PT_ARTIFACT)var18.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var64);
            } else if (var5 == 90) {
               Codec var19 = ProtobufProxy.create(PT_CREATURE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var42 = var1.readRawVarint32();
               int var65 = var1.pushLimit(var42);
               if (var2.creatureitems == null) {
                  var2.creatureitems = new ArrayList();
               }

               var2.creatureitems.add((PT_CREATURE)var19.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var65);
            } else if (var5 == 98) {
               Codec var20 = ProtobufProxy.create(PT_AVATAR_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var43 = var1.readRawVarint32();
               int var66 = var1.pushLimit(var43);
               if (var2.avataritems == null) {
                  var2.avataritems = new ArrayList();
               }

               var2.avataritems.add((PT_AVATAR_ITEM)var20.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var66);
            } else if (var5 == 106) {
               Codec var21 = ProtobufProxy.create(PT_SKIN_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var44 = var1.readRawVarint32();
               int var67 = var1.pushLimit(var44);
               if (var2.damagefontitems == null) {
                  var2.damagefontitems = new ArrayList();
               }

               var2.damagefontitems.add((PT_SKIN_ITEM)var21.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var67);
            } else if (var5 == 114) {
               Codec var22 = ProtobufProxy.create(PT_SKIN_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var45 = var1.readRawVarint32();
               int var68 = var1.pushLimit(var45);
               if (var2.chatframeitems == null) {
                  var2.chatframeitems = new ArrayList();
               }

               var2.chatframeitems.add((PT_SKIN_ITEM)var22.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var68);
            } else if (var5 == 122) {
               Codec var23 = ProtobufProxy.create(PT_SKIN_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var46 = var1.readRawVarint32();
               int var69 = var1.pushLimit(var46);
               if (var2.characterframeitems == null) {
                  var2.characterframeitems = new ArrayList();
               }

               var2.characterframeitems.add((PT_SKIN_ITEM)var23.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var69);
            } else if (var5 == 130) {
               Codec var24 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var47 = var1.readRawVarint32();
               int var70 = var1.pushLimit(var47);
               if (var2.crackitems == null) {
                  var2.crackitems = new ArrayList();
               }

               var2.crackitems.add((PT_STACKABLE)var24.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var70);
            } else if (var5 == 138) {
               Codec var25 = ProtobufProxy.create(AchievementInfoPacketData.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var48 = var1.readRawVarint32();
               int var71 = var1.pushLimit(var48);
               if (var2.achievement == null) {
                  var2.achievement = new ArrayList();
               }

               var2.achievement.add((AchievementInfoPacketData)var25.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var71);
            } else if (var5 == 146) {
               Codec var26 = ProtobufProxy.create(PT_ACHIEVEMENT_REWARD.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var49 = var1.readRawVarint32();
               int var72 = var1.pushLimit(var49);
               if (var2.rewards == null) {
                  var2.rewards = new ArrayList();
               }

               var2.rewards.add((PT_ACHIEVEMENT_REWARD)var26.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var72);
            } else if (var5 == 154) {
               Codec var27 = ProtobufProxy.create(PT_MONEY_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var50 = var1.readRawVarint32();
               int var73 = var1.pushLimit(var50);
               if (var2.currency == null) {
                  var2.currency = new ArrayList();
               }

               var2.currency.add((PT_MONEY_ITEM)var27.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var73);
            } else if (var5 == 162) {
               Codec var28 = ProtobufProxy.create(PT_MONEY_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var51 = var1.readRawVarint32();
               int var74 = var1.pushLimit(var51);
               if (var2.accountcurrency == null) {
                  var2.accountcurrency = new ArrayList();
               }

               var2.accountcurrency.add((PT_MONEY_ITEM)var28.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var74);
            } else if (var5 == 170) {
               Codec var29 = ProtobufProxy.create(PT_ITEMS.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var52 = var1.readRawVarint32();
               int var75 = var1.pushLimit(var52);
               var2.invenitems = (PT_ITEMS)var29.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var75);
            } else if (var5 == 178) {
               Codec var30 = ProtobufProxy.create(PT_EQUIP.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var53 = var1.readRawVarint32();
               int var76 = var1.pushLimit(var53);
               if (var2.crackequipitems == null) {
                  var2.crackequipitems = new ArrayList();
               }

               var2.crackequipitems.add((PT_EQUIP)var30.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var76);
            } else if (var5 == 186) {
               Codec var31 = ProtobufProxy.create(PT_AVATAR_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var54 = var1.readRawVarint32();
               int var77 = var1.pushLimit(var54);
               if (var2.sdavataritems == null) {
                  var2.sdavataritems = new ArrayList();
               }

               var2.sdavataritems.add((PT_AVATAR_ITEM)var31.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var77);
            } else if (var5 == 194) {
               Codec var32 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var55 = var1.readRawVarint32();
               int var78 = var1.pushLimit(var55);
               if (var2.bookmarkitems == null) {
                  var2.bookmarkitems = new ArrayList();
               }

               var2.bookmarkitems.add((PT_STACKABLE)var32.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var78);
            } else if (var5 == 202) {
               Codec var33 = ProtobufProxy.create(PT_EQUIP.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var56 = var1.readRawVarint32();
               int var79 = var1.pushLimit(var56);
               if (var2.scrollitems == null) {
                  var2.scrollitems = new ArrayList();
               }

               var2.scrollitems.add((PT_EQUIP)var33.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var79);
            } else if (var5 == 208) {
               var2.adventureunionlevel = var1.readInt32();
            } else if (var5 == 216) {
               var2.adventureunionexp = var1.readInt64();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_ACHIEVEMENT_REWARD.class);
         return this.descriptor = var1;
      }
   }
}
