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

public class AchievementInfoPacketData$$JProtoBufClass implements Codec<AchievementInfoPacketData>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(AchievementInfoPacketData var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public AchievementInfoPacketData decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(AchievementInfoPacketData var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var9 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(1, var9);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var10 = var1.count;
         var2 += CodedOutputStream.computeInt32Size(2, var10);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.clear)) {
         Integer var11 = var1.clear;
         var2 += CodedOutputStream.computeInt32Size(3, var11);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.rewardcount)) {
         Integer var12 = var1.rewardcount;
         var2 += CodedOutputStream.computeInt32Size(4, var12);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.cleartime)) {
         Long var13 = var1.cleartime;
         var2 += CodedOutputStream.computeInt64Size(5, var13);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.values)) {
         List var14 = var1.values;
         var2 += CodedConstant.computeListSize(6, var14, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(AchievementInfoPacketData var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var9 = var1.index;
         if (var9 != null) {
            var2.writeInt32(1, var9);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var10 = var1.count;
         if (var10 != null) {
            var2.writeInt32(2, var10);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.clear)) {
         Integer var11 = var1.clear;
         if (var11 != null) {
            var2.writeInt32(3, var11);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.rewardcount)) {
         Integer var12 = var1.rewardcount;
         if (var12 != null) {
            var2.writeInt32(4, var12);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.cleartime)) {
         Long var13 = var1.cleartime;
         if (var13 != null) {
            var2.writeInt64(5, var13);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.values)) {
         List var14 = var1.values;
         if (var14 != null) {
            CodedConstant.writeToList(var2, 6, FieldType.OBJECT, var14, false);
         }
      }

   }

   public void writeTo(AchievementInfoPacketData var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public AchievementInfoPacketData readFrom(CodedInputStream var1) throws IOException {
      AchievementInfoPacketData var2 = new AchievementInfoPacketData();
      var2.values = new ArrayList();

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
            } else if (var5 == 16) {
               var2.count = var1.readInt32();
            } else if (var5 == 24) {
               var2.clear = var1.readInt32();
            } else if (var5 == 32) {
               var2.rewardcount = var1.readInt32();
            } else if (var5 == 40) {
               var2.cleartime = var1.readInt64();
            } else if (var5 == 50) {
               Codec var10 = ProtobufProxy.create(AchievementInfo.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.values == null) {
                  var2.values = new ArrayList();
               }

               var2.values.add((AchievementInfo)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(AchievementInfoPacketData.class);
         return this.descriptor = var1;
      }
   }
}
