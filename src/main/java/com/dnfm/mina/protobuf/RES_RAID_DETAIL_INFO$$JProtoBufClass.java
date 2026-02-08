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

public class RES_RAID_DETAIL_INFO$$JProtoBufClass implements Codec<RES_RAID_DETAIL_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_RAID_DETAIL_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_RAID_DETAIL_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_RAID_DETAIL_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var9 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var9);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.coin)) {
         PT_RAID_COIN_INFO var10 = var1.coin;
         var2 += CodedConstant.computeSize(2, var10, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.buffs)) {
         List var11 = var1.buffs;
         var2 += CodedConstant.computeListSize(3, var11, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.party)) {
         List var12 = var1.party;
         var2 += CodedConstant.computeListSize(4, var12, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.advertisement)) {
         PT_ATTACK_SQUAD_ADVERTISEMENT var13 = var1.advertisement;
         var2 += CodedConstant.computeSize(5, var13, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.timer)) {
         PT_ATTACK_SQUAD_TIMER var14 = var1.timer;
         var2 += CodedConstant.computeSize(6, var14, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      return var2;
   }

   public void doWriteTo(RES_RAID_DETAIL_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var9 = var1.error;
         if (var9 != null) {
            var2.writeInt32(1, var9);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.coin)) {
         PT_RAID_COIN_INFO var10 = var1.coin;
         if (var10 != null) {
            CodedConstant.writeObject(var2, 2, FieldType.OBJECT, var10, false);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.buffs)) {
         List var11 = var1.buffs;
         if (var11 != null) {
            CodedConstant.writeToList(var2, 3, FieldType.OBJECT, var11, false);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.party)) {
         List var12 = var1.party;
         if (var12 != null) {
            CodedConstant.writeToList(var2, 4, FieldType.OBJECT, var12, false);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.advertisement)) {
         PT_ATTACK_SQUAD_ADVERTISEMENT var13 = var1.advertisement;
         if (var13 != null) {
            CodedConstant.writeObject(var2, 5, FieldType.OBJECT, var13, false);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.timer)) {
         PT_ATTACK_SQUAD_TIMER var14 = var1.timer;
         if (var14 != null) {
            CodedConstant.writeObject(var2, 6, FieldType.OBJECT, var14, false);
         }
      }

   }

   public void writeTo(RES_RAID_DETAIL_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_RAID_DETAIL_INFO readFrom(CodedInputStream var1) throws IOException {
      RES_RAID_DETAIL_INFO var2 = new RES_RAID_DETAIL_INFO();
      var2.buffs = new ArrayList();
      var2.party = new ArrayList();

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
               Codec var10 = ProtobufProxy.create(PT_RAID_COIN_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.coin = (PT_RAID_COIN_INFO)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 26) {
               Codec var11 = ProtobufProxy.create(PT_RAID_BUFF_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var15 = var1.readRawVarint32();
               int var19 = var1.pushLimit(var15);
               if (var2.buffs == null) {
                  var2.buffs = new ArrayList();
               }

               var2.buffs.add((PT_RAID_BUFF_INFO)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var19);
            } else if (var5 == 34) {
               Codec var12 = ProtobufProxy.create(PT_RAID_PARTY_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var16 = var1.readRawVarint32();
               int var20 = var1.pushLimit(var16);
               if (var2.party == null) {
                  var2.party = new ArrayList();
               }

               var2.party.add((PT_RAID_PARTY_INFO)var12.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var20);
            } else if (var5 == 42) {
               Codec var13 = ProtobufProxy.create(PT_ATTACK_SQUAD_ADVERTISEMENT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var17 = var1.readRawVarint32();
               int var21 = var1.pushLimit(var17);
               var2.advertisement = (PT_ATTACK_SQUAD_ADVERTISEMENT)var13.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var21);
            } else if (var5 == 50) {
               Codec var14 = ProtobufProxy.create(PT_ATTACK_SQUAD_TIMER.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var18 = var1.readRawVarint32();
               int var22 = var1.pushLimit(var18);
               var2.timer = (PT_ATTACK_SQUAD_TIMER)var14.readFrom(var1);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_RAID_DETAIL_INFO.class);
         return this.descriptor = var1;
      }
   }
}
