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

public class NOTIFY_MULTI_PLAY_DUNGEON_SELECT_REWARD_CARD$$JProtoBufClass implements Codec<NOTIFY_MULTI_PLAY_DUNGEON_SELECT_REWARD_CARD>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(NOTIFY_MULTI_PLAY_DUNGEON_SELECT_REWARD_CARD var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public NOTIFY_MULTI_PLAY_DUNGEON_SELECT_REWARD_CARD decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(NOTIFY_MULTI_PLAY_DUNGEON_SELECT_REWARD_CARD var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var15 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var15);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var16 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(2, var16);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var17 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(3, var17);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.gold)) {
         Integer var18 = var1.gold;
         var2 += CodedOutputStream.computeInt32Size(4, var18);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.tera)) {
         Integer var19 = var1.tera;
         var2 += CodedOutputStream.computeInt32Size(5, var19);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.earngold)) {
         Integer var20 = var1.earngold;
         var2 += CodedOutputStream.computeInt32Size(6, var20);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.premiumindex)) {
         Integer var21 = var1.premiumindex;
         var2 += CodedOutputStream.computeInt32Size(7, var21);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var22 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(8, var22);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.qqvipinfo)) {
         Integer var23 = var1.qqvipinfo;
         var2 += CodedOutputStream.computeInt32Size(9, var23);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.gamecenter)) {
         Integer var24 = var1.gamecenter;
         var2 += CodedOutputStream.computeInt32Size(10, var24);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.items)) {
         PT_ITEMS var25 = var1.items;
         var2 += CodedConstant.computeSize(11, var25, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.blackdiamond)) {
         Boolean var26 = var1.blackdiamond;
         var2 += CodedOutputStream.computeBoolSize(12, var26);
      }

      return var2;
   }

   public void doWriteTo(NOTIFY_MULTI_PLAY_DUNGEON_SELECT_REWARD_CARD var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var15 = var1.error;
         if (var15 != null) {
            var2.writeInt32(1, var15);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var16 = var1.type;
         if (var16 != null) {
            var2.writeInt32(2, var16);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var17 = var1.index;
         if (var17 != null) {
            var2.writeInt32(3, var17);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.gold)) {
         Integer var18 = var1.gold;
         if (var18 != null) {
            var2.writeInt32(4, var18);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.tera)) {
         Integer var19 = var1.tera;
         if (var19 != null) {
            var2.writeInt32(5, var19);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.earngold)) {
         Integer var20 = var1.earngold;
         if (var20 != null) {
            var2.writeInt32(6, var20);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.premiumindex)) {
         Integer var21 = var1.premiumindex;
         if (var21 != null) {
            var2.writeInt32(7, var21);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var22 = var1.charguid;
         if (var22 != null) {
            var2.writeUInt64(8, var22);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.qqvipinfo)) {
         Integer var23 = var1.qqvipinfo;
         if (var23 != null) {
            var2.writeInt32(9, var23);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.gamecenter)) {
         Integer var24 = var1.gamecenter;
         if (var24 != null) {
            var2.writeInt32(10, var24);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.items)) {
         PT_ITEMS var25 = var1.items;
         if (var25 != null) {
            CodedConstant.writeObject(var2, 11, FieldType.OBJECT, var25, false);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.blackdiamond)) {
         Boolean var26 = var1.blackdiamond;
         if (var26 != null) {
            var2.writeBool(12, var26);
         }
      }

   }

   public void writeTo(NOTIFY_MULTI_PLAY_DUNGEON_SELECT_REWARD_CARD var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public NOTIFY_MULTI_PLAY_DUNGEON_SELECT_REWARD_CARD readFrom(CodedInputStream var1) throws IOException {
      NOTIFY_MULTI_PLAY_DUNGEON_SELECT_REWARD_CARD var2 = new NOTIFY_MULTI_PLAY_DUNGEON_SELECT_REWARD_CARD();

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
               var2.type = var1.readInt32();
            } else if (var5 == 24) {
               var2.index = var1.readInt32();
            } else if (var5 == 32) {
               var2.gold = var1.readInt32();
            } else if (var5 == 40) {
               var2.tera = var1.readInt32();
            } else if (var5 == 48) {
               var2.earngold = var1.readInt32();
            } else if (var5 == 56) {
               var2.premiumindex = var1.readInt32();
            } else if (var5 == 64) {
               var2.charguid = var1.readUInt64();
            } else if (var5 == 72) {
               var2.qqvipinfo = var1.readInt32();
            } else if (var5 == 80) {
               var2.gamecenter = var1.readInt32();
            } else if (var5 == 90) {
               Codec var10 = ProtobufProxy.create(PT_ITEMS.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.items = (PT_ITEMS)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 96) {
               var2.blackdiamond = var1.readBool();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(NOTIFY_MULTI_PLAY_DUNGEON_SELECT_REWARD_CARD.class);
         return this.descriptor = var1;
      }
   }
}
