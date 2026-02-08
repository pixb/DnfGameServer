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

public class PT_DRAGON_BOARD_CHANCE_CARD$$JProtoBufClass implements Codec<PT_DRAGON_BOARD_CHANCE_CARD>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_DRAGON_BOARD_CHANCE_CARD var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_DRAGON_BOARD_CHANCE_CARD decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_DRAGON_BOARD_CHANCE_CARD var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var11 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(1, var11);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.changedice)) {
         PT_DRAGON_BOARD_CHANGE_DICE var12 = var1.changedice;
         var2 += CodedConstant.computeSize(2, var12, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.changeslots)) {
         List var13 = var1.changeslots;
         var2 += CodedConstant.computeListSize(3, var13, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.hold)) {
         PT_DRAGON_BOARD_HOLD var14 = var1.hold;
         var2 += CodedConstant.computeSize(4, var14, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.moveslot)) {
         PT_DRAGON_BOARD_CHANGE_SLOT var15 = var1.moveslot;
         var2 += CodedConstant.computeSize(5, var15, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.debuff)) {
         PT_DRAGON_BOARD_APPENDAGE var16 = var1.debuff;
         var2 += CodedConstant.computeSize(6, var16, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.dotdamage)) {
         PT_DRAGON_BOARD_APPENDAGE var17 = var1.dotdamage;
         var2 += CodedConstant.computeSize(7, var17, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.boss)) {
         PT_DRAGON_BOARD_APPENDAGE var18 = var1.boss;
         var2 += CodedConstant.computeSize(8, var18, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      return var2;
   }

   public void doWriteTo(PT_DRAGON_BOARD_CHANCE_CARD var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var11 = var1.index;
         if (var11 != null) {
            var2.writeInt32(1, var11);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.changedice)) {
         PT_DRAGON_BOARD_CHANGE_DICE var12 = var1.changedice;
         if (var12 != null) {
            CodedConstant.writeObject(var2, 2, FieldType.OBJECT, var12, false);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.changeslots)) {
         List var13 = var1.changeslots;
         if (var13 != null) {
            CodedConstant.writeToList(var2, 3, FieldType.OBJECT, var13, false);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.hold)) {
         PT_DRAGON_BOARD_HOLD var14 = var1.hold;
         if (var14 != null) {
            CodedConstant.writeObject(var2, 4, FieldType.OBJECT, var14, false);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.moveslot)) {
         PT_DRAGON_BOARD_CHANGE_SLOT var15 = var1.moveslot;
         if (var15 != null) {
            CodedConstant.writeObject(var2, 5, FieldType.OBJECT, var15, false);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.debuff)) {
         PT_DRAGON_BOARD_APPENDAGE var16 = var1.debuff;
         if (var16 != null) {
            CodedConstant.writeObject(var2, 6, FieldType.OBJECT, var16, false);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.dotdamage)) {
         PT_DRAGON_BOARD_APPENDAGE var17 = var1.dotdamage;
         if (var17 != null) {
            CodedConstant.writeObject(var2, 7, FieldType.OBJECT, var17, false);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.boss)) {
         PT_DRAGON_BOARD_APPENDAGE var18 = var1.boss;
         if (var18 != null) {
            CodedConstant.writeObject(var2, 8, FieldType.OBJECT, var18, false);
         }
      }

   }

   public void writeTo(PT_DRAGON_BOARD_CHANCE_CARD var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_DRAGON_BOARD_CHANCE_CARD readFrom(CodedInputStream var1) throws IOException {
      PT_DRAGON_BOARD_CHANCE_CARD var2 = new PT_DRAGON_BOARD_CHANCE_CARD();
      var2.changeslots = new ArrayList();

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
            } else if (var5 == 18) {
               Codec var10 = ProtobufProxy.create(PT_DRAGON_BOARD_CHANGE_DICE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.changedice = (PT_DRAGON_BOARD_CHANGE_DICE)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 26) {
               Codec var11 = ProtobufProxy.create(PT_DRAGON_BOARD_CHANGE_SLOT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var17 = var1.readRawVarint32();
               int var23 = var1.pushLimit(var17);
               if (var2.changeslots == null) {
                  var2.changeslots = new ArrayList();
               }

               var2.changeslots.add((PT_DRAGON_BOARD_CHANGE_SLOT)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var23);
            } else if (var5 == 34) {
               Codec var12 = ProtobufProxy.create(PT_DRAGON_BOARD_HOLD.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var18 = var1.readRawVarint32();
               int var24 = var1.pushLimit(var18);
               var2.hold = (PT_DRAGON_BOARD_HOLD)var12.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var24);
            } else if (var5 == 42) {
               Codec var13 = ProtobufProxy.create(PT_DRAGON_BOARD_CHANGE_SLOT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var19 = var1.readRawVarint32();
               int var25 = var1.pushLimit(var19);
               var2.moveslot = (PT_DRAGON_BOARD_CHANGE_SLOT)var13.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var25);
            } else if (var5 == 50) {
               Codec var14 = ProtobufProxy.create(PT_DRAGON_BOARD_APPENDAGE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var20 = var1.readRawVarint32();
               int var26 = var1.pushLimit(var20);
               var2.debuff = (PT_DRAGON_BOARD_APPENDAGE)var14.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var26);
            } else if (var5 == 58) {
               Codec var15 = ProtobufProxy.create(PT_DRAGON_BOARD_APPENDAGE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var21 = var1.readRawVarint32();
               int var27 = var1.pushLimit(var21);
               var2.dotdamage = (PT_DRAGON_BOARD_APPENDAGE)var15.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var27);
            } else if (var5 == 66) {
               Codec var16 = ProtobufProxy.create(PT_DRAGON_BOARD_APPENDAGE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var22 = var1.readRawVarint32();
               int var28 = var1.pushLimit(var22);
               var2.boss = (PT_DRAGON_BOARD_APPENDAGE)var16.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var28);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_DRAGON_BOARD_CHANCE_CARD.class);
         return this.descriptor = var1;
      }
   }
}
