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

public class PT_REWARD_ITEM_INFO$$JProtoBufClass implements Codec<PT_REWARD_ITEM_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_REWARD_ITEM_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_REWARD_ITEM_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_REWARD_ITEM_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.equips)) {
         List var17 = var1.equips;
         var2 += CodedConstant.computeListSize(1, var17, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.titles)) {
         List var18 = var1.titles;
         var2 += CodedConstant.computeListSize(2, var18, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.flags)) {
         List var19 = var1.flags;
         var2 += CodedConstant.computeListSize(3, var19, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.materials)) {
         List var20 = var1.materials;
         var2 += CodedConstant.computeListSize(4, var20, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.consumes)) {
         List var21 = var1.consumes;
         var2 += CodedConstant.computeListSize(5, var21, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.emblems)) {
         List var22 = var1.emblems;
         var2 += CodedConstant.computeListSize(6, var22, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.cards)) {
         List var23 = var1.cards;
         var2 += CodedConstant.computeListSize(7, var23, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.epicpieces)) {
         List var24 = var1.epicpieces;
         var2 += CodedConstant.computeListSize(8, var24, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.cartifacts)) {
         List var25 = var1.cartifacts;
         var2 += CodedConstant.computeListSize(9, var25, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.creatures)) {
         List var26 = var1.creatures;
         var2 += CodedConstant.computeListSize(10, var26, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.avatars)) {
         List var27 = var1.avatars;
         var2 += CodedConstant.computeListSize(11, var27, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.damagefonts)) {
         List var28 = var1.damagefonts;
         var2 += CodedConstant.computeListSize(12, var28, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.chatframes)) {
         List var29 = var1.chatframes;
         var2 += CodedConstant.computeListSize(13, var29, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.characterframes)) {
         List var30 = var1.characterframes;
         var2 += CodedConstant.computeListSize(14, var30, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(PT_REWARD_ITEM_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.equips)) {
         List var17 = var1.equips;
         if (var17 != null) {
            CodedConstant.writeToList(var2, 1, FieldType.OBJECT, var17, false);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.titles)) {
         List var18 = var1.titles;
         if (var18 != null) {
            CodedConstant.writeToList(var2, 2, FieldType.OBJECT, var18, false);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.flags)) {
         List var19 = var1.flags;
         if (var19 != null) {
            CodedConstant.writeToList(var2, 3, FieldType.OBJECT, var19, false);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.materials)) {
         List var20 = var1.materials;
         if (var20 != null) {
            CodedConstant.writeToList(var2, 4, FieldType.OBJECT, var20, false);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.consumes)) {
         List var21 = var1.consumes;
         if (var21 != null) {
            CodedConstant.writeToList(var2, 5, FieldType.OBJECT, var21, false);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.emblems)) {
         List var22 = var1.emblems;
         if (var22 != null) {
            CodedConstant.writeToList(var2, 6, FieldType.OBJECT, var22, false);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.cards)) {
         List var23 = var1.cards;
         if (var23 != null) {
            CodedConstant.writeToList(var2, 7, FieldType.OBJECT, var23, false);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.epicpieces)) {
         List var24 = var1.epicpieces;
         if (var24 != null) {
            CodedConstant.writeToList(var2, 8, FieldType.OBJECT, var24, false);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.cartifacts)) {
         List var25 = var1.cartifacts;
         if (var25 != null) {
            CodedConstant.writeToList(var2, 9, FieldType.OBJECT, var25, false);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.creatures)) {
         List var26 = var1.creatures;
         if (var26 != null) {
            CodedConstant.writeToList(var2, 10, FieldType.OBJECT, var26, false);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.avatars)) {
         List var27 = var1.avatars;
         if (var27 != null) {
            CodedConstant.writeToList(var2, 11, FieldType.OBJECT, var27, false);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.damagefonts)) {
         List var28 = var1.damagefonts;
         if (var28 != null) {
            CodedConstant.writeToList(var2, 12, FieldType.OBJECT, var28, false);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.chatframes)) {
         List var29 = var1.chatframes;
         if (var29 != null) {
            CodedConstant.writeToList(var2, 13, FieldType.OBJECT, var29, false);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.characterframes)) {
         List var30 = var1.characterframes;
         if (var30 != null) {
            CodedConstant.writeToList(var2, 14, FieldType.OBJECT, var30, false);
         }
      }

   }

   public void writeTo(PT_REWARD_ITEM_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_REWARD_ITEM_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_REWARD_ITEM_INFO var2 = new PT_REWARD_ITEM_INFO();
      var2.equips = new ArrayList();
      var2.titles = new ArrayList();
      var2.flags = new ArrayList();
      var2.materials = new ArrayList();
      var2.consumes = new ArrayList();
      var2.emblems = new ArrayList();
      var2.cards = new ArrayList();
      var2.epicpieces = new ArrayList();
      var2.cartifacts = new ArrayList();
      var2.creatures = new ArrayList();
      var2.avatars = new ArrayList();
      var2.damagefonts = new ArrayList();
      var2.chatframes = new ArrayList();
      var2.characterframes = new ArrayList();

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
               if (var2.equips == null) {
                  var2.equips = new ArrayList();
               }

               var2.equips.add((PT_EQUIP)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 18) {
               Codec var11 = ProtobufProxy.create(PT_EQUIP.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var24 = var1.readRawVarint32();
               int var37 = var1.pushLimit(var24);
               if (var2.titles == null) {
                  var2.titles = new ArrayList();
               }

               var2.titles.add((PT_EQUIP)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var37);
            } else if (var5 == 26) {
               Codec var12 = ProtobufProxy.create(PT_EQUIP.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var25 = var1.readRawVarint32();
               int var38 = var1.pushLimit(var25);
               if (var2.flags == null) {
                  var2.flags = new ArrayList();
               }

               var2.flags.add((PT_EQUIP)var12.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var38);
            } else if (var5 == 34) {
               Codec var13 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var26 = var1.readRawVarint32();
               int var39 = var1.pushLimit(var26);
               if (var2.materials == null) {
                  var2.materials = new ArrayList();
               }

               var2.materials.add((PT_STACKABLE)var13.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var39);
            } else if (var5 == 42) {
               Codec var14 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var27 = var1.readRawVarint32();
               int var40 = var1.pushLimit(var27);
               if (var2.consumes == null) {
                  var2.consumes = new ArrayList();
               }

               var2.consumes.add((PT_STACKABLE)var14.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var40);
            } else if (var5 == 50) {
               Codec var15 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var28 = var1.readRawVarint32();
               int var41 = var1.pushLimit(var28);
               if (var2.emblems == null) {
                  var2.emblems = new ArrayList();
               }

               var2.emblems.add((PT_STACKABLE)var15.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var41);
            } else if (var5 == 58) {
               Codec var16 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var29 = var1.readRawVarint32();
               int var42 = var1.pushLimit(var29);
               if (var2.cards == null) {
                  var2.cards = new ArrayList();
               }

               var2.cards.add((PT_STACKABLE)var16.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var42);
            } else if (var5 == 66) {
               Codec var17 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var30 = var1.readRawVarint32();
               int var43 = var1.pushLimit(var30);
               if (var2.epicpieces == null) {
                  var2.epicpieces = new ArrayList();
               }

               var2.epicpieces.add((PT_STACKABLE)var17.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var43);
            } else if (var5 == 74) {
               Codec var18 = ProtobufProxy.create(PT_ARTIFACT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var31 = var1.readRawVarint32();
               int var44 = var1.pushLimit(var31);
               if (var2.cartifacts == null) {
                  var2.cartifacts = new ArrayList();
               }

               var2.cartifacts.add((PT_ARTIFACT)var18.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var44);
            } else if (var5 == 82) {
               Codec var19 = ProtobufProxy.create(PT_CREATURE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var32 = var1.readRawVarint32();
               int var45 = var1.pushLimit(var32);
               if (var2.creatures == null) {
                  var2.creatures = new ArrayList();
               }

               var2.creatures.add((PT_CREATURE)var19.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var45);
            } else if (var5 == 90) {
               Codec var20 = ProtobufProxy.create(PT_AVATAR_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var33 = var1.readRawVarint32();
               int var46 = var1.pushLimit(var33);
               if (var2.avatars == null) {
                  var2.avatars = new ArrayList();
               }

               var2.avatars.add((PT_AVATAR_ITEM)var20.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var46);
            } else if (var5 == 98) {
               Codec var21 = ProtobufProxy.create(PT_SKIN_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var34 = var1.readRawVarint32();
               int var47 = var1.pushLimit(var34);
               if (var2.damagefonts == null) {
                  var2.damagefonts = new ArrayList();
               }

               var2.damagefonts.add((PT_SKIN_ITEM)var21.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var47);
            } else if (var5 == 106) {
               Codec var22 = ProtobufProxy.create(PT_SKIN_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var35 = var1.readRawVarint32();
               int var48 = var1.pushLimit(var35);
               if (var2.chatframes == null) {
                  var2.chatframes = new ArrayList();
               }

               var2.chatframes.add((PT_SKIN_ITEM)var22.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var48);
            } else if (var5 == 114) {
               Codec var23 = ProtobufProxy.create(PT_SKIN_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var36 = var1.readRawVarint32();
               int var49 = var1.pushLimit(var36);
               if (var2.characterframes == null) {
                  var2.characterframes = new ArrayList();
               }

               var2.characterframes.add((PT_SKIN_ITEM)var23.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var49);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_REWARD_ITEM_INFO.class);
         return this.descriptor = var1;
      }
   }
}
