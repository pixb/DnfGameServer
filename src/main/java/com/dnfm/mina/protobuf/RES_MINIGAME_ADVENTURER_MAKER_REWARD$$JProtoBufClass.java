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

public class RES_MINIGAME_ADVENTURER_MAKER_REWARD$$JProtoBufClass implements Codec<RES_MINIGAME_ADVENTURER_MAKER_REWARD>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_MINIGAME_ADVENTURER_MAKER_REWARD var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_MINIGAME_ADVENTURER_MAKER_REWARD decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_MINIGAME_ADVENTURER_MAKER_REWARD var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var7 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var7);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.info)) {
         PT_MINIGAME_ADVENTURER_MAKER_BABY_INFO var8 = var1.info;
         var2 += CodedConstant.computeSize(2, var8, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.history)) {
         List var9 = var1.history;
         var2 += CodedConstant.computeListSize(3, var9, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.rewardInfos)) {
         PT_CONTENTS_REWARD_INFO var10 = var1.rewardInfos;
         var2 += CodedConstant.computeSize(4, var10, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      return var2;
   }

   public void doWriteTo(RES_MINIGAME_ADVENTURER_MAKER_REWARD var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var7 = var1.error;
         if (var7 != null) {
            var2.writeInt32(1, var7);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.info)) {
         PT_MINIGAME_ADVENTURER_MAKER_BABY_INFO var8 = var1.info;
         if (var8 != null) {
            CodedConstant.writeObject(var2, 2, FieldType.OBJECT, var8, false);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.history)) {
         List var9 = var1.history;
         if (var9 != null) {
            CodedConstant.writeToList(var2, 3, FieldType.OBJECT, var9, false);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.rewardInfos)) {
         PT_CONTENTS_REWARD_INFO var10 = var1.rewardInfos;
         if (var10 != null) {
            CodedConstant.writeObject(var2, 4, FieldType.OBJECT, var10, false);
         }
      }

   }

   public void writeTo(RES_MINIGAME_ADVENTURER_MAKER_REWARD var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_MINIGAME_ADVENTURER_MAKER_REWARD readFrom(CodedInputStream var1) throws IOException {
      RES_MINIGAME_ADVENTURER_MAKER_REWARD var2 = new RES_MINIGAME_ADVENTURER_MAKER_REWARD();
      var2.history = new ArrayList();

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
               Codec var10 = ProtobufProxy.create(PT_MINIGAME_ADVENTURER_MAKER_BABY_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.info = (PT_MINIGAME_ADVENTURER_MAKER_BABY_INFO)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 26) {
               Codec var11 = ProtobufProxy.create(PT_MINIGAME_ADVENTURER_MAKER_CLEAR_HISTORY.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var13 = var1.readRawVarint32();
               int var15 = var1.pushLimit(var13);
               if (var2.history == null) {
                  var2.history = new ArrayList();
               }

               var2.history.add((PT_MINIGAME_ADVENTURER_MAKER_CLEAR_HISTORY)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var15);
            } else if (var5 == 34) {
               Codec var12 = ProtobufProxy.create(PT_CONTENTS_REWARD_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var14 = var1.readRawVarint32();
               int var16 = var1.pushLimit(var14);
               var2.rewardInfos = (PT_CONTENTS_REWARD_INFO)var12.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var16);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_MINIGAME_ADVENTURER_MAKER_REWARD.class);
         return this.descriptor = var1;
      }
   }
}
