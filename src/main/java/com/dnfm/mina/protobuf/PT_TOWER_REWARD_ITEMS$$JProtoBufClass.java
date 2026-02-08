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

public class PT_TOWER_REWARD_ITEMS$$JProtoBufClass implements Codec<PT_TOWER_REWARD_ITEMS>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_TOWER_REWARD_ITEMS var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_TOWER_REWARD_ITEMS decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_TOWER_REWARD_ITEMS var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.equipitems)) {
         List var16 = var1.equipitems;
         var2 += CodedConstant.computeListSize(1, var16, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.titleitems)) {
         List var17 = var1.titleitems;
         var2 += CodedConstant.computeListSize(2, var17, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.flagitems)) {
         List var18 = var1.flagitems;
         var2 += CodedConstant.computeListSize(3, var18, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.materialitems)) {
         List var19 = var1.materialitems;
         var2 += CodedConstant.computeListSize(4, var19, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.consumeitems)) {
         List var20 = var1.consumeitems;
         var2 += CodedConstant.computeListSize(5, var20, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.emblemitems)) {
         List var21 = var1.emblemitems;
         var2 += CodedConstant.computeListSize(6, var21, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.carditems)) {
         List var22 = var1.carditems;
         var2 += CodedConstant.computeListSize(7, var22, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.epicpieceitems)) {
         List var23 = var1.epicpieceitems;
         var2 += CodedConstant.computeListSize(8, var23, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.cartifactitems)) {
         List var24 = var1.cartifactitems;
         var2 += CodedConstant.computeListSize(9, var24, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.creatureitems)) {
         List var25 = var1.creatureitems;
         var2 += CodedConstant.computeListSize(10, var25, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.avataritems)) {
         List var26 = var1.avataritems;
         var2 += CodedConstant.computeListSize(11, var26, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var27 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(12, var27);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var28 = var1.count;
         var2 += CodedOutputStream.computeInt32Size(13, var28);
      }

      return var2;
   }

   public void doWriteTo(PT_TOWER_REWARD_ITEMS var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.equipitems)) {
         List var16 = var1.equipitems;
         if (var16 != null) {
            CodedConstant.writeToList(var2, 1, FieldType.OBJECT, var16, false);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.titleitems)) {
         List var17 = var1.titleitems;
         if (var17 != null) {
            CodedConstant.writeToList(var2, 2, FieldType.OBJECT, var17, false);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.flagitems)) {
         List var18 = var1.flagitems;
         if (var18 != null) {
            CodedConstant.writeToList(var2, 3, FieldType.OBJECT, var18, false);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.materialitems)) {
         List var19 = var1.materialitems;
         if (var19 != null) {
            CodedConstant.writeToList(var2, 4, FieldType.OBJECT, var19, false);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.consumeitems)) {
         List var20 = var1.consumeitems;
         if (var20 != null) {
            CodedConstant.writeToList(var2, 5, FieldType.OBJECT, var20, false);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.emblemitems)) {
         List var21 = var1.emblemitems;
         if (var21 != null) {
            CodedConstant.writeToList(var2, 6, FieldType.OBJECT, var21, false);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.carditems)) {
         List var22 = var1.carditems;
         if (var22 != null) {
            CodedConstant.writeToList(var2, 7, FieldType.OBJECT, var22, false);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.epicpieceitems)) {
         List var23 = var1.epicpieceitems;
         if (var23 != null) {
            CodedConstant.writeToList(var2, 8, FieldType.OBJECT, var23, false);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.cartifactitems)) {
         List var24 = var1.cartifactitems;
         if (var24 != null) {
            CodedConstant.writeToList(var2, 9, FieldType.OBJECT, var24, false);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.creatureitems)) {
         List var25 = var1.creatureitems;
         if (var25 != null) {
            CodedConstant.writeToList(var2, 10, FieldType.OBJECT, var25, false);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.avataritems)) {
         List var26 = var1.avataritems;
         if (var26 != null) {
            CodedConstant.writeToList(var2, 11, FieldType.OBJECT, var26, false);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var27 = var1.index;
         if (var27 != null) {
            var2.writeInt32(12, var27);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var28 = var1.count;
         if (var28 != null) {
            var2.writeInt32(13, var28);
         }
      }

   }

   public void writeTo(PT_TOWER_REWARD_ITEMS var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_TOWER_REWARD_ITEMS readFrom(CodedInputStream var1) throws IOException {
      PT_TOWER_REWARD_ITEMS var2 = new PT_TOWER_REWARD_ITEMS();
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
               int var21 = var1.readRawVarint32();
               int var31 = var1.pushLimit(var21);
               if (var2.titleitems == null) {
                  var2.titleitems = new ArrayList();
               }

               var2.titleitems.add((PT_EQUIP)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var31);
            } else if (var5 == 26) {
               Codec var12 = ProtobufProxy.create(PT_EQUIP.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var22 = var1.readRawVarint32();
               int var32 = var1.pushLimit(var22);
               if (var2.flagitems == null) {
                  var2.flagitems = new ArrayList();
               }

               var2.flagitems.add((PT_EQUIP)var12.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var32);
            } else if (var5 == 34) {
               Codec var13 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var23 = var1.readRawVarint32();
               int var33 = var1.pushLimit(var23);
               if (var2.materialitems == null) {
                  var2.materialitems = new ArrayList();
               }

               var2.materialitems.add((PT_STACKABLE)var13.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var33);
            } else if (var5 == 42) {
               Codec var14 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var24 = var1.readRawVarint32();
               int var34 = var1.pushLimit(var24);
               if (var2.consumeitems == null) {
                  var2.consumeitems = new ArrayList();
               }

               var2.consumeitems.add((PT_STACKABLE)var14.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var34);
            } else if (var5 == 50) {
               Codec var15 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var25 = var1.readRawVarint32();
               int var35 = var1.pushLimit(var25);
               if (var2.emblemitems == null) {
                  var2.emblemitems = new ArrayList();
               }

               var2.emblemitems.add((PT_STACKABLE)var15.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var35);
            } else if (var5 == 58) {
               Codec var16 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var26 = var1.readRawVarint32();
               int var36 = var1.pushLimit(var26);
               if (var2.carditems == null) {
                  var2.carditems = new ArrayList();
               }

               var2.carditems.add((PT_STACKABLE)var16.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var36);
            } else if (var5 == 66) {
               Codec var17 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var27 = var1.readRawVarint32();
               int var37 = var1.pushLimit(var27);
               if (var2.epicpieceitems == null) {
                  var2.epicpieceitems = new ArrayList();
               }

               var2.epicpieceitems.add((PT_STACKABLE)var17.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var37);
            } else if (var5 == 74) {
               Codec var18 = ProtobufProxy.create(PT_ARTIFACT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var28 = var1.readRawVarint32();
               int var38 = var1.pushLimit(var28);
               if (var2.cartifactitems == null) {
                  var2.cartifactitems = new ArrayList();
               }

               var2.cartifactitems.add((PT_ARTIFACT)var18.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var38);
            } else if (var5 == 82) {
               Codec var19 = ProtobufProxy.create(PT_CREATURE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var29 = var1.readRawVarint32();
               int var39 = var1.pushLimit(var29);
               if (var2.creatureitems == null) {
                  var2.creatureitems = new ArrayList();
               }

               var2.creatureitems.add((PT_CREATURE)var19.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var39);
            } else if (var5 == 90) {
               Codec var20 = ProtobufProxy.create(PT_AVATAR_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var30 = var1.readRawVarint32();
               int var40 = var1.pushLimit(var30);
               if (var2.avataritems == null) {
                  var2.avataritems = new ArrayList();
               }

               var2.avataritems.add((PT_AVATAR_ITEM)var20.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var40);
            } else if (var5 == 96) {
               var2.index = var1.readInt32();
            } else if (var5 == 104) {
               var2.count = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_TOWER_REWARD_ITEMS.class);
         return this.descriptor = var1;
      }
   }
}
