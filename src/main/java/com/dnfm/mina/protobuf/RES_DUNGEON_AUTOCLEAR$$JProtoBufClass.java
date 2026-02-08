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

public class RES_DUNGEON_AUTOCLEAR$$JProtoBufClass implements Codec<RES_DUNGEON_AUTOCLEAR>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_DUNGEON_AUTOCLEAR var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_DUNGEON_AUTOCLEAR decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_DUNGEON_AUTOCLEAR var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var15 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var15);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var16 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(2, var16);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.fatigue)) {
         Integer var17 = var1.fatigue;
         var2 += CodedOutputStream.computeInt32Size(3, var17);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.consumefatigue)) {
         Integer var18 = var1.consumefatigue;
         var2 += CodedOutputStream.computeInt32Size(4, var18);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.clearexp)) {
         PT_EXP_DETAILINFO var19 = var1.clearexp;
         var2 += CodedConstant.computeSize(5, var19, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.ticket)) {
         PT_TICKET var20 = var1.ticket;
         var2 += CodedConstant.computeSize(6, var20, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.adventureticket)) {
         Integer var21 = var1.adventureticket;
         var2 += CodedOutputStream.computeInt32Size(7, var21);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.consumecurrency)) {
         List var22 = var1.consumecurrency;
         var2 += CodedConstant.computeListSize(8, var22, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.consumeaccountcurrency)) {
         List var23 = var1.consumeaccountcurrency;
         var2 += CodedConstant.computeListSize(9, var23, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.adventureunionlevel)) {
         Integer var24 = var1.adventureunionlevel;
         var2 += CodedOutputStream.computeInt32Size(10, var24);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.adventureunionexp)) {
         Long var25 = var1.adventureunionexp;
         var2 += CodedOutputStream.computeInt64Size(11, var25);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.rewards)) {
         PT_CONTENTS_REWARD_INFO var26 = var1.rewards;
         var2 += CodedConstant.computeSize(12, var26, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      return var2;
   }

   public void doWriteTo(RES_DUNGEON_AUTOCLEAR var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var15 = var1.error;
         if (var15 != null) {
            var2.writeInt32(1, var15);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var16 = var1.level;
         if (var16 != null) {
            var2.writeInt32(2, var16);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.fatigue)) {
         Integer var17 = var1.fatigue;
         if (var17 != null) {
            var2.writeInt32(3, var17);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.consumefatigue)) {
         Integer var18 = var1.consumefatigue;
         if (var18 != null) {
            var2.writeInt32(4, var18);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.clearexp)) {
         PT_EXP_DETAILINFO var19 = var1.clearexp;
         if (var19 != null) {
            CodedConstant.writeObject(var2, 5, FieldType.OBJECT, var19, false);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.ticket)) {
         PT_TICKET var20 = var1.ticket;
         if (var20 != null) {
            CodedConstant.writeObject(var2, 6, FieldType.OBJECT, var20, false);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.adventureticket)) {
         Integer var21 = var1.adventureticket;
         if (var21 != null) {
            var2.writeInt32(7, var21);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.consumecurrency)) {
         List var22 = var1.consumecurrency;
         if (var22 != null) {
            CodedConstant.writeToList(var2, 8, FieldType.OBJECT, var22, false);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.consumeaccountcurrency)) {
         List var23 = var1.consumeaccountcurrency;
         if (var23 != null) {
            CodedConstant.writeToList(var2, 9, FieldType.OBJECT, var23, false);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.adventureunionlevel)) {
         Integer var24 = var1.adventureunionlevel;
         if (var24 != null) {
            var2.writeInt32(10, var24);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.adventureunionexp)) {
         Long var25 = var1.adventureunionexp;
         if (var25 != null) {
            var2.writeInt64(11, var25);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.rewards)) {
         PT_CONTENTS_REWARD_INFO var26 = var1.rewards;
         if (var26 != null) {
            CodedConstant.writeObject(var2, 12, FieldType.OBJECT, var26, false);
         }
      }

   }

   public void writeTo(RES_DUNGEON_AUTOCLEAR var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_DUNGEON_AUTOCLEAR readFrom(CodedInputStream var1) throws IOException {
      RES_DUNGEON_AUTOCLEAR var2 = new RES_DUNGEON_AUTOCLEAR();
      var2.consumecurrency = new ArrayList();
      var2.consumeaccountcurrency = new ArrayList();

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
            } else if (var5 == 16) {
               var2.level = var1.readInt32();
            } else if (var5 == 24) {
               var2.fatigue = var1.readInt32();
            } else if (var5 == 32) {
               var2.consumefatigue = var1.readInt32();
            } else if (var5 == 42) {
               Codec var10 = ProtobufProxy.create(PT_EXP_DETAILINFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.clearexp = (PT_EXP_DETAILINFO)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 50) {
               Codec var11 = ProtobufProxy.create(PT_TICKET.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var15 = var1.readRawVarint32();
               int var19 = var1.pushLimit(var15);
               var2.ticket = (PT_TICKET)var11.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var19);
            } else if (var5 == 56) {
               var2.adventureticket = var1.readInt32();
            } else if (var5 == 66) {
               Codec var12 = ProtobufProxy.create(PT_MONEY_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var16 = var1.readRawVarint32();
               int var20 = var1.pushLimit(var16);
               if (var2.consumecurrency == null) {
                  var2.consumecurrency = new ArrayList();
               }

               var2.consumecurrency.add((PT_MONEY_ITEM)var12.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var20);
            } else if (var5 == 74) {
               Codec var13 = ProtobufProxy.create(PT_MONEY_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var17 = var1.readRawVarint32();
               int var21 = var1.pushLimit(var17);
               if (var2.consumeaccountcurrency == null) {
                  var2.consumeaccountcurrency = new ArrayList();
               }

               var2.consumeaccountcurrency.add((PT_MONEY_ITEM)var13.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var21);
            } else if (var5 == 80) {
               var2.adventureunionlevel = var1.readInt32();
            } else if (var5 == 88) {
               var2.adventureunionexp = var1.readInt64();
            } else if (var5 == 98) {
               Codec var14 = ProtobufProxy.create(PT_CONTENTS_REWARD_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var18 = var1.readRawVarint32();
               int var22 = var1.pushLimit(var18);
               var2.rewards = (PT_CONTENTS_REWARD_INFO)var14.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var22);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_DUNGEON_AUTOCLEAR.class);
         return this.descriptor = var1;
      }
   }
}
