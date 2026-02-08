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

public class AchievementBonusPacketDataList$$JProtoBufClass implements Codec<AchievementBonusPacketDataList>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(AchievementBonusPacketDataList var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public AchievementBonusPacketDataList decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(AchievementBonusPacketDataList var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.subtype)) {
         Integer var7 = var1.subtype;
         var2 += CodedOutputStream.computeInt32Size(1, var7);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.rewardbonus)) {
         List var8 = var1.rewardbonus;
         var2 += CodedConstant.computeListSize(2, var8, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.subtypevalue)) {
         Integer var9 = var1.subtypevalue;
         var2 += CodedOutputStream.computeInt32Size(3, var9);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.savingtype)) {
         Integer var10 = var1.savingtype;
         var2 += CodedOutputStream.computeInt32Size(4, var10);
      }

      return var2;
   }

   public void doWriteTo(AchievementBonusPacketDataList var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.subtype)) {
         Integer var7 = var1.subtype;
         if (var7 != null) {
            var2.writeInt32(1, var7);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.rewardbonus)) {
         List var8 = var1.rewardbonus;
         if (var8 != null) {
            CodedConstant.writeToList(var2, 2, FieldType.OBJECT, var8, false);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.subtypevalue)) {
         Integer var9 = var1.subtypevalue;
         if (var9 != null) {
            var2.writeInt32(3, var9);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.savingtype)) {
         Integer var10 = var1.savingtype;
         if (var10 != null) {
            var2.writeInt32(4, var10);
         }
      }

   }

   public void writeTo(AchievementBonusPacketDataList var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public AchievementBonusPacketDataList readFrom(CodedInputStream var1) throws IOException {
      AchievementBonusPacketDataList var2 = new AchievementBonusPacketDataList();
      var2.rewardbonus = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.subtype = var1.readInt32();
            } else if (var5 == 18) {
               Codec var10 = ProtobufProxy.create(AchievementBonusPacketData.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.rewardbonus == null) {
                  var2.rewardbonus = new ArrayList();
               }

               var2.rewardbonus.add((AchievementBonusPacketData)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 24) {
               var2.subtypevalue = var1.readInt32();
            } else if (var5 == 32) {
               var2.savingtype = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(AchievementBonusPacketDataList.class);
         return this.descriptor = var1;
      }
   }
}
