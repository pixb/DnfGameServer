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

public class RES_CHAR_STORAGE_LIST$$JProtoBufClass implements Codec<RES_CHAR_STORAGE_LIST>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_CHAR_STORAGE_LIST var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_CHAR_STORAGE_LIST decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_CHAR_STORAGE_LIST var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var19 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var19);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.equipitems)) {
         List var20 = var1.equipitems;
         var2 += CodedConstant.computeListSize(2, var20, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.titleitems)) {
         List var21 = var1.titleitems;
         var2 += CodedConstant.computeListSize(3, var21, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.flagitems)) {
         List var22 = var1.flagitems;
         var2 += CodedConstant.computeListSize(4, var22, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.materialitems)) {
         List var23 = var1.materialitems;
         var2 += CodedConstant.computeListSize(5, var23, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.consumeitems)) {
         List var24 = var1.consumeitems;
         var2 += CodedConstant.computeListSize(6, var24, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.emblemitems)) {
         List var25 = var1.emblemitems;
         var2 += CodedConstant.computeListSize(7, var25, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.carditems)) {
         List var26 = var1.carditems;
         var2 += CodedConstant.computeListSize(8, var26, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.epicpieceitems)) {
         List var27 = var1.epicpieceitems;
         var2 += CodedConstant.computeListSize(9, var27, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.cartifactitems)) {
         List var28 = var1.cartifactitems;
         var2 += CodedConstant.computeListSize(10, var28, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.creatureitems)) {
         List var29 = var1.creatureitems;
         var2 += CodedConstant.computeListSize(11, var29, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.avataritems)) {
         List var30 = var1.avataritems;
         var2 += CodedConstant.computeListSize(12, var30, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.line)) {
         Integer var31 = var1.line;
         var2 += CodedOutputStream.computeInt32Size(13, var31);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.page)) {
         Integer var32 = var1.page;
         var2 += CodedOutputStream.computeInt32Size(14, var32);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.storagegold)) {
         Integer var33 = var1.storagegold;
         var2 += CodedOutputStream.computeInt32Size(15, var33);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.storagetera)) {
         Integer var34 = var1.storagetera;
         var2 += CodedOutputStream.computeInt32Size(16, var34);
      }

      return var2;
   }

   public void doWriteTo(RES_CHAR_STORAGE_LIST var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var19 = var1.error;
         if (var19 != null) {
            var2.writeInt32(1, var19);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.equipitems)) {
         List var20 = var1.equipitems;
         if (var20 != null) {
            CodedConstant.writeToList(var2, 2, FieldType.OBJECT, var20, false);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.titleitems)) {
         List var21 = var1.titleitems;
         if (var21 != null) {
            CodedConstant.writeToList(var2, 3, FieldType.OBJECT, var21, false);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.flagitems)) {
         List var22 = var1.flagitems;
         if (var22 != null) {
            CodedConstant.writeToList(var2, 4, FieldType.OBJECT, var22, false);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.materialitems)) {
         List var23 = var1.materialitems;
         if (var23 != null) {
            CodedConstant.writeToList(var2, 5, FieldType.OBJECT, var23, false);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.consumeitems)) {
         List var24 = var1.consumeitems;
         if (var24 != null) {
            CodedConstant.writeToList(var2, 6, FieldType.OBJECT, var24, false);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.emblemitems)) {
         List var25 = var1.emblemitems;
         if (var25 != null) {
            CodedConstant.writeToList(var2, 7, FieldType.OBJECT, var25, false);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.carditems)) {
         List var26 = var1.carditems;
         if (var26 != null) {
            CodedConstant.writeToList(var2, 8, FieldType.OBJECT, var26, false);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.epicpieceitems)) {
         List var27 = var1.epicpieceitems;
         if (var27 != null) {
            CodedConstant.writeToList(var2, 9, FieldType.OBJECT, var27, false);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.cartifactitems)) {
         List var28 = var1.cartifactitems;
         if (var28 != null) {
            CodedConstant.writeToList(var2, 10, FieldType.OBJECT, var28, false);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.creatureitems)) {
         List var29 = var1.creatureitems;
         if (var29 != null) {
            CodedConstant.writeToList(var2, 11, FieldType.OBJECT, var29, false);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.avataritems)) {
         List var30 = var1.avataritems;
         if (var30 != null) {
            CodedConstant.writeToList(var2, 12, FieldType.OBJECT, var30, false);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.line)) {
         Integer var31 = var1.line;
         if (var31 != null) {
            var2.writeInt32(13, var31);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.page)) {
         Integer var32 = var1.page;
         if (var32 != null) {
            var2.writeInt32(14, var32);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.storagegold)) {
         Integer var33 = var1.storagegold;
         if (var33 != null) {
            var2.writeInt32(15, var33);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.storagetera)) {
         Integer var34 = var1.storagetera;
         if (var34 != null) {
            var2.writeInt32(16, var34);
         }
      }

   }

   public void writeTo(RES_CHAR_STORAGE_LIST var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_CHAR_STORAGE_LIST readFrom(CodedInputStream var1) throws IOException {
      RES_CHAR_STORAGE_LIST var2 = new RES_CHAR_STORAGE_LIST();
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
               int var21 = var1.readRawVarint32();
               int var31 = var1.pushLimit(var21);
               if (var2.titleitems == null) {
                  var2.titleitems = new ArrayList();
               }

               var2.titleitems.add((PT_EQUIP)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var31);
            } else if (var5 == 34) {
               Codec var12 = ProtobufProxy.create(PT_EQUIP.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var22 = var1.readRawVarint32();
               int var32 = var1.pushLimit(var22);
               if (var2.flagitems == null) {
                  var2.flagitems = new ArrayList();
               }

               var2.flagitems.add((PT_EQUIP)var12.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var32);
            } else if (var5 == 42) {
               Codec var13 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var23 = var1.readRawVarint32();
               int var33 = var1.pushLimit(var23);
               if (var2.materialitems == null) {
                  var2.materialitems = new ArrayList();
               }

               var2.materialitems.add((PT_STACKABLE)var13.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var33);
            } else if (var5 == 50) {
               Codec var14 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var24 = var1.readRawVarint32();
               int var34 = var1.pushLimit(var24);
               if (var2.consumeitems == null) {
                  var2.consumeitems = new ArrayList();
               }

               var2.consumeitems.add((PT_STACKABLE)var14.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var34);
            } else if (var5 == 58) {
               Codec var15 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var25 = var1.readRawVarint32();
               int var35 = var1.pushLimit(var25);
               if (var2.emblemitems == null) {
                  var2.emblemitems = new ArrayList();
               }

               var2.emblemitems.add((PT_STACKABLE)var15.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var35);
            } else if (var5 == 66) {
               Codec var16 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var26 = var1.readRawVarint32();
               int var36 = var1.pushLimit(var26);
               if (var2.carditems == null) {
                  var2.carditems = new ArrayList();
               }

               var2.carditems.add((PT_STACKABLE)var16.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var36);
            } else if (var5 == 74) {
               Codec var17 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var27 = var1.readRawVarint32();
               int var37 = var1.pushLimit(var27);
               if (var2.epicpieceitems == null) {
                  var2.epicpieceitems = new ArrayList();
               }

               var2.epicpieceitems.add((PT_STACKABLE)var17.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var37);
            } else if (var5 == 82) {
               Codec var18 = ProtobufProxy.create(PT_ARTIFACT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var28 = var1.readRawVarint32();
               int var38 = var1.pushLimit(var28);
               if (var2.cartifactitems == null) {
                  var2.cartifactitems = new ArrayList();
               }

               var2.cartifactitems.add((PT_ARTIFACT)var18.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var38);
            } else if (var5 == 90) {
               Codec var19 = ProtobufProxy.create(PT_CREATURE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var29 = var1.readRawVarint32();
               int var39 = var1.pushLimit(var29);
               if (var2.creatureitems == null) {
                  var2.creatureitems = new ArrayList();
               }

               var2.creatureitems.add((PT_CREATURE)var19.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var39);
            } else if (var5 == 98) {
               Codec var20 = ProtobufProxy.create(PT_AVATAR_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var30 = var1.readRawVarint32();
               int var40 = var1.pushLimit(var30);
               if (var2.avataritems == null) {
                  var2.avataritems = new ArrayList();
               }

               var2.avataritems.add((PT_AVATAR_ITEM)var20.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var40);
            } else if (var5 == 104) {
               var2.line = var1.readInt32();
            } else if (var5 == 112) {
               var2.page = var1.readInt32();
            } else if (var5 == 120) {
               var2.storagegold = var1.readInt32();
            } else if (var5 == 128) {
               var2.storagetera = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_CHAR_STORAGE_LIST.class);
         return this.descriptor = var1;
      }
   }
}
