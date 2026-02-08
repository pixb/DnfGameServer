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

public class RES_COMMUNITY_GIFT_RECEIPT$$JProtoBufClass implements Codec<RES_COMMUNITY_GIFT_RECEIPT>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_COMMUNITY_GIFT_RECEIPT var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_COMMUNITY_GIFT_RECEIPT decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_COMMUNITY_GIFT_RECEIPT var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var21 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var21);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.equipitems)) {
         List var22 = var1.equipitems;
         var2 += CodedConstant.computeListSize(2, var22, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.titleitems)) {
         List var23 = var1.titleitems;
         var2 += CodedConstant.computeListSize(3, var23, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.flagitems)) {
         List var24 = var1.flagitems;
         var2 += CodedConstant.computeListSize(4, var24, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.materialitems)) {
         List var25 = var1.materialitems;
         var2 += CodedConstant.computeListSize(5, var25, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.consumeitems)) {
         List var26 = var1.consumeitems;
         var2 += CodedConstant.computeListSize(6, var26, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.emblemitems)) {
         List var27 = var1.emblemitems;
         var2 += CodedConstant.computeListSize(7, var27, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.carditems)) {
         List var28 = var1.carditems;
         var2 += CodedConstant.computeListSize(8, var28, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.epicpieceitems)) {
         List var29 = var1.epicpieceitems;
         var2 += CodedConstant.computeListSize(9, var29, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.cartifactitems)) {
         List var30 = var1.cartifactitems;
         var2 += CodedConstant.computeListSize(10, var30, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.creatureitems)) {
         List var31 = var1.creatureitems;
         var2 += CodedConstant.computeListSize(11, var31, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.avataritems)) {
         List var32 = var1.avataritems;
         var2 += CodedConstant.computeListSize(12, var32, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.crackitems)) {
         List var33 = var1.crackitems;
         var2 += CodedConstant.computeListSize(13, var33, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.crackequipitems)) {
         List var34 = var1.crackequipitems;
         var2 += CodedConstant.computeListSize(14, var34, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.sdavataritems)) {
         List var35 = var1.sdavataritems;
         var2 += CodedConstant.computeListSize(15, var35, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.bookmarkitems)) {
         List var36 = var1.bookmarkitems;
         var2 += CodedConstant.computeListSize(16, var36, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.scrollitems)) {
         List var37 = var1.scrollitems;
         var2 += CodedConstant.computeListSize(17, var37, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.historyvalue)) {
         List var38 = var1.historyvalue;
         var2 += CodedConstant.computeListSize(18, var38, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(RES_COMMUNITY_GIFT_RECEIPT var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var21 = var1.error;
         if (var21 != null) {
            var2.writeInt32(1, var21);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.equipitems)) {
         List var22 = var1.equipitems;
         if (var22 != null) {
            CodedConstant.writeToList(var2, 2, FieldType.OBJECT, var22, false);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.titleitems)) {
         List var23 = var1.titleitems;
         if (var23 != null) {
            CodedConstant.writeToList(var2, 3, FieldType.OBJECT, var23, false);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.flagitems)) {
         List var24 = var1.flagitems;
         if (var24 != null) {
            CodedConstant.writeToList(var2, 4, FieldType.OBJECT, var24, false);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.materialitems)) {
         List var25 = var1.materialitems;
         if (var25 != null) {
            CodedConstant.writeToList(var2, 5, FieldType.OBJECT, var25, false);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.consumeitems)) {
         List var26 = var1.consumeitems;
         if (var26 != null) {
            CodedConstant.writeToList(var2, 6, FieldType.OBJECT, var26, false);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.emblemitems)) {
         List var27 = var1.emblemitems;
         if (var27 != null) {
            CodedConstant.writeToList(var2, 7, FieldType.OBJECT, var27, false);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.carditems)) {
         List var28 = var1.carditems;
         if (var28 != null) {
            CodedConstant.writeToList(var2, 8, FieldType.OBJECT, var28, false);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.epicpieceitems)) {
         List var29 = var1.epicpieceitems;
         if (var29 != null) {
            CodedConstant.writeToList(var2, 9, FieldType.OBJECT, var29, false);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.cartifactitems)) {
         List var30 = var1.cartifactitems;
         if (var30 != null) {
            CodedConstant.writeToList(var2, 10, FieldType.OBJECT, var30, false);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.creatureitems)) {
         List var31 = var1.creatureitems;
         if (var31 != null) {
            CodedConstant.writeToList(var2, 11, FieldType.OBJECT, var31, false);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.avataritems)) {
         List var32 = var1.avataritems;
         if (var32 != null) {
            CodedConstant.writeToList(var2, 12, FieldType.OBJECT, var32, false);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.crackitems)) {
         List var33 = var1.crackitems;
         if (var33 != null) {
            CodedConstant.writeToList(var2, 13, FieldType.OBJECT, var33, false);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.crackequipitems)) {
         List var34 = var1.crackequipitems;
         if (var34 != null) {
            CodedConstant.writeToList(var2, 14, FieldType.OBJECT, var34, false);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.sdavataritems)) {
         List var35 = var1.sdavataritems;
         if (var35 != null) {
            CodedConstant.writeToList(var2, 15, FieldType.OBJECT, var35, false);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.bookmarkitems)) {
         List var36 = var1.bookmarkitems;
         if (var36 != null) {
            CodedConstant.writeToList(var2, 16, FieldType.OBJECT, var36, false);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.scrollitems)) {
         List var37 = var1.scrollitems;
         if (var37 != null) {
            CodedConstant.writeToList(var2, 17, FieldType.OBJECT, var37, false);
         }
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.historyvalue)) {
         List var38 = var1.historyvalue;
         if (var38 != null) {
            CodedConstant.writeToList(var2, 18, FieldType.OBJECT, var38, false);
         }
      }

   }

   public void writeTo(RES_COMMUNITY_GIFT_RECEIPT var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_COMMUNITY_GIFT_RECEIPT readFrom(CodedInputStream var1) throws IOException {
      RES_COMMUNITY_GIFT_RECEIPT var2 = new RES_COMMUNITY_GIFT_RECEIPT();
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
      var2.bookmarkitems = new ArrayList();
      var2.scrollitems = new ArrayList();
      var2.historyvalue = new ArrayList();

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
               int var27 = var1.readRawVarint32();
               int var43 = var1.pushLimit(var27);
               if (var2.titleitems == null) {
                  var2.titleitems = new ArrayList();
               }

               var2.titleitems.add((PT_EQUIP)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var43);
            } else if (var5 == 34) {
               Codec var12 = ProtobufProxy.create(PT_EQUIP.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var28 = var1.readRawVarint32();
               int var44 = var1.pushLimit(var28);
               if (var2.flagitems == null) {
                  var2.flagitems = new ArrayList();
               }

               var2.flagitems.add((PT_EQUIP)var12.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var44);
            } else if (var5 == 42) {
               Codec var13 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var29 = var1.readRawVarint32();
               int var45 = var1.pushLimit(var29);
               if (var2.materialitems == null) {
                  var2.materialitems = new ArrayList();
               }

               var2.materialitems.add((PT_STACKABLE)var13.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var45);
            } else if (var5 == 50) {
               Codec var14 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var30 = var1.readRawVarint32();
               int var46 = var1.pushLimit(var30);
               if (var2.consumeitems == null) {
                  var2.consumeitems = new ArrayList();
               }

               var2.consumeitems.add((PT_STACKABLE)var14.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var46);
            } else if (var5 == 58) {
               Codec var15 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var31 = var1.readRawVarint32();
               int var47 = var1.pushLimit(var31);
               if (var2.emblemitems == null) {
                  var2.emblemitems = new ArrayList();
               }

               var2.emblemitems.add((PT_STACKABLE)var15.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var47);
            } else if (var5 == 66) {
               Codec var16 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var32 = var1.readRawVarint32();
               int var48 = var1.pushLimit(var32);
               if (var2.carditems == null) {
                  var2.carditems = new ArrayList();
               }

               var2.carditems.add((PT_STACKABLE)var16.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var48);
            } else if (var5 == 74) {
               Codec var17 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var33 = var1.readRawVarint32();
               int var49 = var1.pushLimit(var33);
               if (var2.epicpieceitems == null) {
                  var2.epicpieceitems = new ArrayList();
               }

               var2.epicpieceitems.add((PT_STACKABLE)var17.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var49);
            } else if (var5 == 82) {
               Codec var18 = ProtobufProxy.create(PT_ARTIFACT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var34 = var1.readRawVarint32();
               int var50 = var1.pushLimit(var34);
               if (var2.cartifactitems == null) {
                  var2.cartifactitems = new ArrayList();
               }

               var2.cartifactitems.add((PT_ARTIFACT)var18.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var50);
            } else if (var5 == 90) {
               Codec var19 = ProtobufProxy.create(PT_CREATURE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var35 = var1.readRawVarint32();
               int var51 = var1.pushLimit(var35);
               if (var2.creatureitems == null) {
                  var2.creatureitems = new ArrayList();
               }

               var2.creatureitems.add((PT_CREATURE)var19.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var51);
            } else if (var5 == 98) {
               Codec var20 = ProtobufProxy.create(PT_AVATAR_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var36 = var1.readRawVarint32();
               int var52 = var1.pushLimit(var36);
               if (var2.avataritems == null) {
                  var2.avataritems = new ArrayList();
               }

               var2.avataritems.add((PT_AVATAR_ITEM)var20.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var52);
            } else if (var5 == 106) {
               Codec var21 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var37 = var1.readRawVarint32();
               int var53 = var1.pushLimit(var37);
               if (var2.crackitems == null) {
                  var2.crackitems = new ArrayList();
               }

               var2.crackitems.add((PT_STACKABLE)var21.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var53);
            } else if (var5 == 114) {
               Codec var22 = ProtobufProxy.create(PT_EQUIP.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var38 = var1.readRawVarint32();
               int var54 = var1.pushLimit(var38);
               if (var2.crackequipitems == null) {
                  var2.crackequipitems = new ArrayList();
               }

               var2.crackequipitems.add((PT_EQUIP)var22.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var54);
            } else if (var5 == 122) {
               Codec var23 = ProtobufProxy.create(PT_AVATAR_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var39 = var1.readRawVarint32();
               int var55 = var1.pushLimit(var39);
               if (var2.sdavataritems == null) {
                  var2.sdavataritems = new ArrayList();
               }

               var2.sdavataritems.add((PT_AVATAR_ITEM)var23.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var55);
            } else if (var5 == 130) {
               Codec var24 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var40 = var1.readRawVarint32();
               int var56 = var1.pushLimit(var40);
               if (var2.bookmarkitems == null) {
                  var2.bookmarkitems = new ArrayList();
               }

               var2.bookmarkitems.add((PT_STACKABLE)var24.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var56);
            } else if (var5 == 138) {
               Codec var25 = ProtobufProxy.create(PT_EQUIP.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var41 = var1.readRawVarint32();
               int var57 = var1.pushLimit(var41);
               if (var2.scrollitems == null) {
                  var2.scrollitems = new ArrayList();
               }

               var2.scrollitems.add((PT_EQUIP)var25.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var57);
            } else if (var5 == 146) {
               Codec var26 = ProtobufProxy.create(PT_COMMUNITY_GIFT_UNTIE_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var42 = var1.readRawVarint32();
               int var58 = var1.pushLimit(var42);
               if (var2.historyvalue == null) {
                  var2.historyvalue = new ArrayList();
               }

               var2.historyvalue.add((PT_COMMUNITY_GIFT_UNTIE_INFO)var26.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var58);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_COMMUNITY_GIFT_RECEIPT.class);
         return this.descriptor = var1;
      }
   }
}
