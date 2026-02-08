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

public class PT_RAID_USER_REWARDS$$JProtoBufClass implements Codec<PT_RAID_USER_REWARDS>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_RAID_USER_REWARDS var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_RAID_USER_REWARDS decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_RAID_USER_REWARDS var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var16 = var1.guid;
         var2 += CodedOutputStream.computeUInt64Size(1, var16);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var17 = var1.name;
         var2 += CodedOutputStream.computeStringSize(2, var17);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var18 = var1.job;
         var2 += CodedOutputStream.computeInt32Size(3, var18);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var19 = var1.growtype;
         var2 += CodedOutputStream.computeInt32Size(4, var19);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.secgrowtype)) {
         Integer var20 = var1.secgrowtype;
         var2 += CodedOutputStream.computeInt32Size(5, var20);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.goldcard)) {
         Boolean var21 = var1.goldcard;
         var2 += CodedOutputStream.computeBoolSize(6, var21);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.rewarded)) {
         Boolean var22 = var1.rewarded;
         var2 += CodedOutputStream.computeBoolSize(7, var22);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.rewards)) {
         List var23 = var1.rewards;
         var2 += CodedConstant.computeListSize(8, var23, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.incompleted)) {
         Boolean var24 = var1.incompleted;
         var2 += CodedOutputStream.computeBoolSize(9, var24);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.limitedreward)) {
         Boolean var25 = var1.limitedreward;
         var2 += CodedOutputStream.computeBoolSize(10, var25);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.cardtype)) {
         Integer var26 = var1.cardtype;
         var2 += CodedOutputStream.computeInt32Size(11, var26);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.eventcardtype)) {
         Integer var27 = var1.eventcardtype;
         var2 += CodedOutputStream.computeInt32Size(12, var27);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.ismastercontract)) {
         Boolean var28 = var1.ismastercontract;
         var2 += CodedOutputStream.computeBoolSize(13, var28);
      }

      return var2;
   }

   public void doWriteTo(PT_RAID_USER_REWARDS var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var16 = var1.guid;
         if (var16 != null) {
            var2.writeUInt64(1, var16);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var17 = var1.name;
         if (var17 != null) {
            var2.writeString(2, var17);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var18 = var1.job;
         if (var18 != null) {
            var2.writeInt32(3, var18);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var19 = var1.growtype;
         if (var19 != null) {
            var2.writeInt32(4, var19);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.secgrowtype)) {
         Integer var20 = var1.secgrowtype;
         if (var20 != null) {
            var2.writeInt32(5, var20);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.goldcard)) {
         Boolean var21 = var1.goldcard;
         if (var21 != null) {
            var2.writeBool(6, var21);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.rewarded)) {
         Boolean var22 = var1.rewarded;
         if (var22 != null) {
            var2.writeBool(7, var22);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.rewards)) {
         List var23 = var1.rewards;
         if (var23 != null) {
            CodedConstant.writeToList(var2, 8, FieldType.OBJECT, var23, false);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.incompleted)) {
         Boolean var24 = var1.incompleted;
         if (var24 != null) {
            var2.writeBool(9, var24);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.limitedreward)) {
         Boolean var25 = var1.limitedreward;
         if (var25 != null) {
            var2.writeBool(10, var25);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.cardtype)) {
         Integer var26 = var1.cardtype;
         if (var26 != null) {
            var2.writeInt32(11, var26);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.eventcardtype)) {
         Integer var27 = var1.eventcardtype;
         if (var27 != null) {
            var2.writeInt32(12, var27);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.ismastercontract)) {
         Boolean var28 = var1.ismastercontract;
         if (var28 != null) {
            var2.writeBool(13, var28);
         }
      }

   }

   public void writeTo(PT_RAID_USER_REWARDS var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_RAID_USER_REWARDS readFrom(CodedInputStream var1) throws IOException {
      PT_RAID_USER_REWARDS var2 = new PT_RAID_USER_REWARDS();
      var2.rewards = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.guid = var1.readUInt64();
            } else if (var5 == 18) {
               var2.name = var1.readString();
            } else if (var5 == 24) {
               var2.job = var1.readInt32();
            } else if (var5 == 32) {
               var2.growtype = var1.readInt32();
            } else if (var5 == 40) {
               var2.secgrowtype = var1.readInt32();
            } else if (var5 == 48) {
               var2.goldcard = var1.readBool();
            } else if (var5 == 56) {
               var2.rewarded = var1.readBool();
            } else if (var5 == 66) {
               Codec var10 = ProtobufProxy.create(PT_RAID_REWARD_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.rewards == null) {
                  var2.rewards = new ArrayList();
               }

               var2.rewards.add((PT_RAID_REWARD_INFO)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 72) {
               var2.incompleted = var1.readBool();
            } else if (var5 == 80) {
               var2.limitedreward = var1.readBool();
            } else if (var5 == 88) {
               var2.cardtype = var1.readInt32();
            } else if (var5 == 96) {
               var2.eventcardtype = var1.readInt32();
            } else if (var5 == 104) {
               var2.ismastercontract = var1.readBool();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_RAID_USER_REWARDS.class);
         return this.descriptor = var1;
      }
   }
}
