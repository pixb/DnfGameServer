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

public class RES_ITEM_COMBINE$$JProtoBufClass implements Codec<RES_ITEM_COMBINE>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_ITEM_COMBINE var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_ITEM_COMBINE decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_ITEM_COMBINE var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var14 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var14);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.equip)) {
         PT_EQUIP var15 = var1.equip;
         var2 += CodedConstant.computeSize(2, var15, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.avatar)) {
         PT_AVATAR_ITEM var16 = var1.avatar;
         var2 += CodedConstant.computeSize(3, var16, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.creatures)) {
         List var17 = var1.creatures;
         var2 += CodedConstant.computeListSize(4, var17, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.artifact)) {
         PT_ARTIFACT var18 = var1.artifact;
         var2 += CodedConstant.computeSize(5, var18, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.material)) {
         PT_STACKABLE var19 = var1.material;
         var2 += CodedConstant.computeSize(6, var19, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.card)) {
         PT_STACKABLE var20 = var1.card;
         var2 += CodedConstant.computeSize(7, var20, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.emblem)) {
         PT_STACKABLE var21 = var1.emblem;
         var2 += CodedConstant.computeSize(8, var21, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.consume)) {
         PT_STACKABLE var22 = var1.consume;
         var2 += CodedConstant.computeSize(9, var22, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.sdavatar)) {
         PT_AVATAR_ITEM var23 = var1.sdavatar;
         var2 += CodedConstant.computeSize(10, var23, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.removeitems)) {
         PT_REMOVEITEMS var24 = var1.removeitems;
         var2 += CodedConstant.computeSize(11, var24, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      return var2;
   }

   public void doWriteTo(RES_ITEM_COMBINE var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var14 = var1.error;
         if (var14 != null) {
            var2.writeInt32(1, var14);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.equip)) {
         PT_EQUIP var15 = var1.equip;
         if (var15 != null) {
            CodedConstant.writeObject(var2, 2, FieldType.OBJECT, var15, false);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.avatar)) {
         PT_AVATAR_ITEM var16 = var1.avatar;
         if (var16 != null) {
            CodedConstant.writeObject(var2, 3, FieldType.OBJECT, var16, false);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.creatures)) {
         List var17 = var1.creatures;
         if (var17 != null) {
            CodedConstant.writeToList(var2, 4, FieldType.OBJECT, var17, false);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.artifact)) {
         PT_ARTIFACT var18 = var1.artifact;
         if (var18 != null) {
            CodedConstant.writeObject(var2, 5, FieldType.OBJECT, var18, false);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.material)) {
         PT_STACKABLE var19 = var1.material;
         if (var19 != null) {
            CodedConstant.writeObject(var2, 6, FieldType.OBJECT, var19, false);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.card)) {
         PT_STACKABLE var20 = var1.card;
         if (var20 != null) {
            CodedConstant.writeObject(var2, 7, FieldType.OBJECT, var20, false);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.emblem)) {
         PT_STACKABLE var21 = var1.emblem;
         if (var21 != null) {
            CodedConstant.writeObject(var2, 8, FieldType.OBJECT, var21, false);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.consume)) {
         PT_STACKABLE var22 = var1.consume;
         if (var22 != null) {
            CodedConstant.writeObject(var2, 9, FieldType.OBJECT, var22, false);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.sdavatar)) {
         PT_AVATAR_ITEM var23 = var1.sdavatar;
         if (var23 != null) {
            CodedConstant.writeObject(var2, 10, FieldType.OBJECT, var23, false);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.removeitems)) {
         PT_REMOVEITEMS var24 = var1.removeitems;
         if (var24 != null) {
            CodedConstant.writeObject(var2, 11, FieldType.OBJECT, var24, false);
         }
      }

   }

   public void writeTo(RES_ITEM_COMBINE var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_ITEM_COMBINE readFrom(CodedInputStream var1) throws IOException {
      RES_ITEM_COMBINE var2 = new RES_ITEM_COMBINE();
      var2.creatures = new ArrayList();

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
               var2.equip = (PT_EQUIP)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 26) {
               Codec var11 = ProtobufProxy.create(PT_AVATAR_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var20 = var1.readRawVarint32();
               int var29 = var1.pushLimit(var20);
               var2.avatar = (PT_AVATAR_ITEM)var11.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var29);
            } else if (var5 == 34) {
               Codec var12 = ProtobufProxy.create(PT_CREATURE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var21 = var1.readRawVarint32();
               int var30 = var1.pushLimit(var21);
               if (var2.creatures == null) {
                  var2.creatures = new ArrayList();
               }

               var2.creatures.add((PT_CREATURE)var12.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var30);
            } else if (var5 == 42) {
               Codec var13 = ProtobufProxy.create(PT_ARTIFACT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var22 = var1.readRawVarint32();
               int var31 = var1.pushLimit(var22);
               var2.artifact = (PT_ARTIFACT)var13.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var31);
            } else if (var5 == 50) {
               Codec var14 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var23 = var1.readRawVarint32();
               int var32 = var1.pushLimit(var23);
               var2.material = (PT_STACKABLE)var14.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var32);
            } else if (var5 == 58) {
               Codec var15 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var24 = var1.readRawVarint32();
               int var33 = var1.pushLimit(var24);
               var2.card = (PT_STACKABLE)var15.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var33);
            } else if (var5 == 66) {
               Codec var16 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var25 = var1.readRawVarint32();
               int var34 = var1.pushLimit(var25);
               var2.emblem = (PT_STACKABLE)var16.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var34);
            } else if (var5 == 74) {
               Codec var17 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var26 = var1.readRawVarint32();
               int var35 = var1.pushLimit(var26);
               var2.consume = (PT_STACKABLE)var17.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var35);
            } else if (var5 == 82) {
               Codec var18 = ProtobufProxy.create(PT_AVATAR_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var27 = var1.readRawVarint32();
               int var36 = var1.pushLimit(var27);
               var2.sdavatar = (PT_AVATAR_ITEM)var18.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var36);
            } else if (var5 == 90) {
               Codec var19 = ProtobufProxy.create(PT_REMOVEITEMS.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var28 = var1.readRawVarint32();
               int var37 = var1.pushLimit(var28);
               var2.removeitems = (PT_REMOVEITEMS)var19.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var37);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_ITEM_COMBINE.class);
         return this.descriptor = var1;
      }
   }
}
