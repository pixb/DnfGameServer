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

public class PT_POST_ALL_LIST$$JProtoBufClass implements Codec<PT_POST_ALL_LIST>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_POST_ALL_LIST var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_POST_ALL_LIST decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_POST_ALL_LIST var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.equipitems)) {
         List var33 = var1.equipitems;
         var2 += CodedConstant.computeListSize(1, var33, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.titleitems)) {
         List var34 = var1.titleitems;
         var2 += CodedConstant.computeListSize(2, var34, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.flagitems)) {
         List var35 = var1.flagitems;
         var2 += CodedConstant.computeListSize(3, var35, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.materialitems)) {
         List var36 = var1.materialitems;
         var2 += CodedConstant.computeListSize(4, var36, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.consumeitems)) {
         List var37 = var1.consumeitems;
         var2 += CodedConstant.computeListSize(5, var37, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.emblemitems)) {
         List var38 = var1.emblemitems;
         var2 += CodedConstant.computeListSize(6, var38, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.carditems)) {
         List var39 = var1.carditems;
         var2 += CodedConstant.computeListSize(7, var39, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.epicpieceitems)) {
         List var40 = var1.epicpieceitems;
         var2 += CodedConstant.computeListSize(8, var40, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.cartifactitems)) {
         List var41 = var1.cartifactitems;
         var2 += CodedConstant.computeListSize(9, var41, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.creatureitems)) {
         List var42 = var1.creatureitems;
         var2 += CodedConstant.computeListSize(10, var42, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.avataritems)) {
         List var43 = var1.avataritems;
         var2 += CodedConstant.computeListSize(11, var43, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.crackitems)) {
         List var44 = var1.crackitems;
         var2 += CodedConstant.computeListSize(12, var44, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.crackequipitems)) {
         List var45 = var1.crackequipitems;
         var2 += CodedConstant.computeListSize(13, var45, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.sdavataritems)) {
         List var46 = var1.sdavataritems;
         var2 += CodedConstant.computeListSize(14, var46, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var47 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(15, var47);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var48 = var1.count;
         var2 += CodedOutputStream.computeInt32Size(16, var48);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var49 = var1.guid;
         var2 += CodedOutputStream.computeUInt64Size(17, var49);
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.title)) {
         String var50 = var1.title;
         var2 += CodedOutputStream.computeStringSize(18, var50);
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.msg)) {
         String var51 = var1.msg;
         var2 += CodedOutputStream.computeStringSize(19, var51);
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.expiretime)) {
         Long var52 = var1.expiretime;
         var2 += CodedOutputStream.computeInt64Size(20, var52);
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.package0)) {
         List var53 = var1.package0;
         var2 += CodedConstant.computeListSize(21, var53, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.link)) {
         List var54 = var1.link;
         var2 += CodedConstant.computeListSize(22, var54, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.read)) {
         Boolean var55 = var1.read;
         var2 += CodedOutputStream.computeBoolSize(23, var55);
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.receive)) {
         Boolean var56 = var1.receive;
         var2 += CodedOutputStream.computeBoolSize(24, var56);
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.remainedpackages)) {
         List var57 = var1.remainedpackages;
         var2 += CodedConstant.computeListSize(25, var57, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.remaineditems)) {
         List var58 = var1.remaineditems;
         var2 += CodedConstant.computeListSize(26, var58, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.timeboxitems)) {
         List var59 = var1.timeboxitems;
         var2 += CodedConstant.computeListSize(27, var59, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.bookmarkitems)) {
         List var60 = var1.bookmarkitems;
         var2 += CodedConstant.computeListSize(28, var60, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var31 = null;
      if (!CodedConstant.isNull(var1.scrollitems)) {
         List var61 = var1.scrollitems;
         var2 += CodedConstant.computeListSize(29, var61, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var32 = null;
      if (!CodedConstant.isNull(var1.importance)) {
         Integer var62 = var1.importance;
         var2 += CodedOutputStream.computeInt32Size(30, var62);
      }

      return var2;
   }

   public void doWriteTo(PT_POST_ALL_LIST var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.equipitems)) {
         List var33 = var1.equipitems;
         if (var33 != null) {
            CodedConstant.writeToList(var2, 1, FieldType.OBJECT, var33, false);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.titleitems)) {
         List var34 = var1.titleitems;
         if (var34 != null) {
            CodedConstant.writeToList(var2, 2, FieldType.OBJECT, var34, false);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.flagitems)) {
         List var35 = var1.flagitems;
         if (var35 != null) {
            CodedConstant.writeToList(var2, 3, FieldType.OBJECT, var35, false);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.materialitems)) {
         List var36 = var1.materialitems;
         if (var36 != null) {
            CodedConstant.writeToList(var2, 4, FieldType.OBJECT, var36, false);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.consumeitems)) {
         List var37 = var1.consumeitems;
         if (var37 != null) {
            CodedConstant.writeToList(var2, 5, FieldType.OBJECT, var37, false);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.emblemitems)) {
         List var38 = var1.emblemitems;
         if (var38 != null) {
            CodedConstant.writeToList(var2, 6, FieldType.OBJECT, var38, false);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.carditems)) {
         List var39 = var1.carditems;
         if (var39 != null) {
            CodedConstant.writeToList(var2, 7, FieldType.OBJECT, var39, false);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.epicpieceitems)) {
         List var40 = var1.epicpieceitems;
         if (var40 != null) {
            CodedConstant.writeToList(var2, 8, FieldType.OBJECT, var40, false);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.cartifactitems)) {
         List var41 = var1.cartifactitems;
         if (var41 != null) {
            CodedConstant.writeToList(var2, 9, FieldType.OBJECT, var41, false);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.creatureitems)) {
         List var42 = var1.creatureitems;
         if (var42 != null) {
            CodedConstant.writeToList(var2, 10, FieldType.OBJECT, var42, false);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.avataritems)) {
         List var43 = var1.avataritems;
         if (var43 != null) {
            CodedConstant.writeToList(var2, 11, FieldType.OBJECT, var43, false);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.crackitems)) {
         List var44 = var1.crackitems;
         if (var44 != null) {
            CodedConstant.writeToList(var2, 12, FieldType.OBJECT, var44, false);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.crackequipitems)) {
         List var45 = var1.crackequipitems;
         if (var45 != null) {
            CodedConstant.writeToList(var2, 13, FieldType.OBJECT, var45, false);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.sdavataritems)) {
         List var46 = var1.sdavataritems;
         if (var46 != null) {
            CodedConstant.writeToList(var2, 14, FieldType.OBJECT, var46, false);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var47 = var1.index;
         if (var47 != null) {
            var2.writeInt32(15, var47);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var48 = var1.count;
         if (var48 != null) {
            var2.writeInt32(16, var48);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var49 = var1.guid;
         if (var49 != null) {
            var2.writeUInt64(17, var49);
         }
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.title)) {
         String var50 = var1.title;
         if (var50 != null) {
            var2.writeString(18, var50);
         }
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.msg)) {
         String var51 = var1.msg;
         if (var51 != null) {
            var2.writeString(19, var51);
         }
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.expiretime)) {
         Long var52 = var1.expiretime;
         if (var52 != null) {
            var2.writeInt64(20, var52);
         }
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.package0)) {
         List var53 = var1.package0;
         if (var53 != null) {
            CodedConstant.writeToList(var2, 21, FieldType.OBJECT, var53, false);
         }
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.link)) {
         List var54 = var1.link;
         if (var54 != null) {
            CodedConstant.writeToList(var2, 22, FieldType.OBJECT, var54, false);
         }
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.read)) {
         Boolean var55 = var1.read;
         if (var55 != null) {
            var2.writeBool(23, var55);
         }
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.receive)) {
         Boolean var56 = var1.receive;
         if (var56 != null) {
            var2.writeBool(24, var56);
         }
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.remainedpackages)) {
         List var57 = var1.remainedpackages;
         if (var57 != null) {
            CodedConstant.writeToList(var2, 25, FieldType.OBJECT, var57, false);
         }
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.remaineditems)) {
         List var58 = var1.remaineditems;
         if (var58 != null) {
            CodedConstant.writeToList(var2, 26, FieldType.OBJECT, var58, false);
         }
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.timeboxitems)) {
         List var59 = var1.timeboxitems;
         if (var59 != null) {
            CodedConstant.writeToList(var2, 27, FieldType.OBJECT, var59, false);
         }
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.bookmarkitems)) {
         List var60 = var1.bookmarkitems;
         if (var60 != null) {
            CodedConstant.writeToList(var2, 28, FieldType.OBJECT, var60, false);
         }
      }

      Object var31 = null;
      if (!CodedConstant.isNull(var1.scrollitems)) {
         List var61 = var1.scrollitems;
         if (var61 != null) {
            CodedConstant.writeToList(var2, 29, FieldType.OBJECT, var61, false);
         }
      }

      Object var32 = null;
      if (!CodedConstant.isNull(var1.importance)) {
         Integer var62 = var1.importance;
         if (var62 != null) {
            var2.writeInt32(30, var62);
         }
      }

   }

   public void writeTo(PT_POST_ALL_LIST var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_POST_ALL_LIST readFrom(CodedInputStream var1) throws IOException {
      PT_POST_ALL_LIST var2 = new PT_POST_ALL_LIST();
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
      var2.crackitems = new ArrayList();
      var2.crackequipitems = new ArrayList();
      var2.sdavataritems = new ArrayList();
      var2.package0 = new ArrayList();
      var2.link = new ArrayList();
      var2.remainedpackages = new ArrayList();
      var2.remaineditems = new ArrayList();
      var2.timeboxitems = new ArrayList();
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

            if (var5 == 10) {
               Codec var10 = ProtobufProxy.create(PT_EQUIP.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.equipitems == null) {
                  var2.equipitems = new ArrayList();
               }

               var2.equipitems.add((PT_EQUIP)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 18) {
               Codec var11 = ProtobufProxy.create(PT_EQUIP.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var31 = var1.readRawVarint32();
               int var51 = var1.pushLimit(var31);
               if (var2.titleitems == null) {
                  var2.titleitems = new ArrayList();
               }

               var2.titleitems.add((PT_EQUIP)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var51);
            } else if (var5 == 26) {
               Codec var12 = ProtobufProxy.create(PT_EQUIP.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var32 = var1.readRawVarint32();
               int var52 = var1.pushLimit(var32);
               if (var2.flagitems == null) {
                  var2.flagitems = new ArrayList();
               }

               var2.flagitems.add((PT_EQUIP)var12.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var52);
            } else if (var5 == 34) {
               Codec var13 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var33 = var1.readRawVarint32();
               int var53 = var1.pushLimit(var33);
               if (var2.materialitems == null) {
                  var2.materialitems = new ArrayList();
               }

               var2.materialitems.add((PT_STACKABLE)var13.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var53);
            } else if (var5 == 42) {
               Codec var14 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var34 = var1.readRawVarint32();
               int var54 = var1.pushLimit(var34);
               if (var2.consumeitems == null) {
                  var2.consumeitems = new ArrayList();
               }

               var2.consumeitems.add((PT_STACKABLE)var14.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var54);
            } else if (var5 == 50) {
               Codec var15 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var35 = var1.readRawVarint32();
               int var55 = var1.pushLimit(var35);
               if (var2.emblemitems == null) {
                  var2.emblemitems = new ArrayList();
               }

               var2.emblemitems.add((PT_STACKABLE)var15.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var55);
            } else if (var5 == 58) {
               Codec var16 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var36 = var1.readRawVarint32();
               int var56 = var1.pushLimit(var36);
               if (var2.carditems == null) {
                  var2.carditems = new ArrayList();
               }

               var2.carditems.add((PT_STACKABLE)var16.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var56);
            } else if (var5 == 66) {
               Codec var17 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var37 = var1.readRawVarint32();
               int var57 = var1.pushLimit(var37);
               if (var2.epicpieceitems == null) {
                  var2.epicpieceitems = new ArrayList();
               }

               var2.epicpieceitems.add((PT_STACKABLE)var17.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var57);
            } else if (var5 == 74) {
               Codec var18 = ProtobufProxy.create(PT_ARTIFACT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var38 = var1.readRawVarint32();
               int var58 = var1.pushLimit(var38);
               if (var2.cartifactitems == null) {
                  var2.cartifactitems = new ArrayList();
               }

               var2.cartifactitems.add((PT_ARTIFACT)var18.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var58);
            } else if (var5 == 82) {
               Codec var19 = ProtobufProxy.create(PT_CREATURE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var39 = var1.readRawVarint32();
               int var59 = var1.pushLimit(var39);
               if (var2.creatureitems == null) {
                  var2.creatureitems = new ArrayList();
               }

               var2.creatureitems.add((PT_CREATURE)var19.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var59);
            } else if (var5 == 90) {
               Codec var20 = ProtobufProxy.create(PT_AVATAR_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var40 = var1.readRawVarint32();
               int var60 = var1.pushLimit(var40);
               if (var2.avataritems == null) {
                  var2.avataritems = new ArrayList();
               }

               var2.avataritems.add((PT_AVATAR_ITEM)var20.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var60);
            } else if (var5 == 98) {
               Codec var21 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var41 = var1.readRawVarint32();
               int var61 = var1.pushLimit(var41);
               if (var2.crackitems == null) {
                  var2.crackitems = new ArrayList();
               }

               var2.crackitems.add((PT_STACKABLE)var21.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var61);
            } else if (var5 == 106) {
               Codec var22 = ProtobufProxy.create(PT_EQUIP.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var42 = var1.readRawVarint32();
               int var62 = var1.pushLimit(var42);
               if (var2.crackequipitems == null) {
                  var2.crackequipitems = new ArrayList();
               }

               var2.crackequipitems.add((PT_EQUIP)var22.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var62);
            } else if (var5 == 114) {
               Codec var23 = ProtobufProxy.create(PT_AVATAR_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var43 = var1.readRawVarint32();
               int var63 = var1.pushLimit(var43);
               if (var2.sdavataritems == null) {
                  var2.sdavataritems = new ArrayList();
               }

               var2.sdavataritems.add((PT_AVATAR_ITEM)var23.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var63);
            } else if (var5 == 120) {
               var2.index = var1.readInt32();
            } else if (var5 == 128) {
               var2.count = var1.readInt32();
            } else if (var5 == 136) {
               var2.guid = var1.readUInt64();
            } else if (var5 == 146) {
               var2.title = var1.readString();
            } else if (var5 == 154) {
               var2.msg = var1.readString();
            } else if (var5 == 160) {
               var2.expiretime = var1.readInt64();
            } else if (var5 == 170) {
               Codec var24 = ProtobufProxy.create(PT_POST_PACKAGE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var44 = var1.readRawVarint32();
               int var64 = var1.pushLimit(var44);
               if (var2.package0 == null) {
                  var2.package0 = new ArrayList();
               }

               var2.package0.add((PT_POST_PACKAGE)var24.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var64);
            } else if (var5 == 178) {
               Codec var25 = ProtobufProxy.create(PT_LinkText.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var45 = var1.readRawVarint32();
               int var65 = var1.pushLimit(var45);
               if (var2.link == null) {
                  var2.link = new ArrayList();
               }

               var2.link.add((PT_LinkText)var25.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var65);
            } else if (var5 == 184) {
               var2.read = var1.readBool();
            } else if (var5 == 192) {
               var2.receive = var1.readBool();
            } else if (var5 == 202) {
               Codec var26 = ProtobufProxy.create(PT_POST_PACKAGE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var46 = var1.readRawVarint32();
               int var66 = var1.pushLimit(var46);
               if (var2.remainedpackages == null) {
                  var2.remainedpackages = new ArrayList();
               }

               var2.remainedpackages.add((PT_POST_PACKAGE)var26.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var66);
            } else if (var5 == 210) {
               Codec var27 = ProtobufProxy.create(PT_SELECTED_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var47 = var1.readRawVarint32();
               int var67 = var1.pushLimit(var47);
               if (var2.remaineditems == null) {
                  var2.remaineditems = new ArrayList();
               }

               var2.remaineditems.add((PT_SELECTED_ITEM)var27.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var67);
            } else if (var5 == 218) {
               Codec var28 = ProtobufProxy.create(PT_ITEM_TIMEBOX.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var48 = var1.readRawVarint32();
               int var68 = var1.pushLimit(var48);
               if (var2.timeboxitems == null) {
                  var2.timeboxitems = new ArrayList();
               }

               var2.timeboxitems.add((PT_ITEM_TIMEBOX)var28.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var68);
            } else if (var5 == 226) {
               Codec var29 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var49 = var1.readRawVarint32();
               int var69 = var1.pushLimit(var49);
               if (var2.bookmarkitems == null) {
                  var2.bookmarkitems = new ArrayList();
               }

               var2.bookmarkitems.add((PT_STACKABLE)var29.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var69);
            } else if (var5 == 234) {
               Codec var30 = ProtobufProxy.create(PT_EQUIP.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var50 = var1.readRawVarint32();
               int var70 = var1.pushLimit(var50);
               if (var2.scrollitems == null) {
                  var2.scrollitems = new ArrayList();
               }

               var2.scrollitems.add((PT_EQUIP)var30.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var70);
            } else if (var5 == 240) {
               var2.importance = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_POST_ALL_LIST.class);
         return this.descriptor = var1;
      }
   }
}
