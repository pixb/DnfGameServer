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

public class RES_REWARD_QUEST$$JProtoBufClass implements Codec<RES_REWARD_QUEST>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_REWARD_QUEST var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_REWARD_QUEST decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_REWARD_QUEST var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var10 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var10);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.exp)) {
         Integer var11 = var1.exp;
         var2 += CodedOutputStream.computeInt32Size(2, var11);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var12 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(3, var12);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.quest)) {
         List var13 = var1.quest;
         var2 += CodedConstant.computeListSize(4, var13, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.adventureunionlevel)) {
         Integer var14 = var1.adventureunionlevel;
         var2 += CodedOutputStream.computeInt32Size(5, var14);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.adventureunionexp)) {
         Long var15 = var1.adventureunionexp;
         var2 += CodedOutputStream.computeInt64Size(6, var15);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.rewards)) {
         PT_CONTENTS_REWARD_INFO var16 = var1.rewards;
         var2 += CodedConstant.computeSize(7, var16, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      return var2;
   }

   public void doWriteTo(RES_REWARD_QUEST var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var10 = var1.error;
         if (var10 != null) {
            var2.writeInt32(1, var10);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.exp)) {
         Integer var11 = var1.exp;
         if (var11 != null) {
            var2.writeInt32(2, var11);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var12 = var1.level;
         if (var12 != null) {
            var2.writeInt32(3, var12);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.quest)) {
         List var13 = var1.quest;
         if (var13 != null) {
            CodedConstant.writeToList(var2, 4, FieldType.OBJECT, var13, false);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.adventureunionlevel)) {
         Integer var14 = var1.adventureunionlevel;
         if (var14 != null) {
            var2.writeInt32(5, var14);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.adventureunionexp)) {
         Long var15 = var1.adventureunionexp;
         if (var15 != null) {
            var2.writeInt64(6, var15);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.rewards)) {
         PT_CONTENTS_REWARD_INFO var16 = var1.rewards;
         if (var16 != null) {
            CodedConstant.writeObject(var2, 7, FieldType.OBJECT, var16, false);
         }
      }

   }

   public void writeTo(RES_REWARD_QUEST var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_REWARD_QUEST readFrom(CodedInputStream var1) throws IOException {
      RES_REWARD_QUEST var2 = new RES_REWARD_QUEST();
      var2.quest = new ArrayList();

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
               var2.exp = var1.readInt32();
            } else if (var5 == 24) {
               var2.level = var1.readInt32();
            } else if (var5 == 34) {
               Codec var10 = ProtobufProxy.create(PT_QUEST_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.quest == null) {
                  var2.quest = new ArrayList();
               }

               var2.quest.add((PT_QUEST_INFO)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 40) {
               var2.adventureunionlevel = var1.readInt32();
            } else if (var5 == 48) {
               var2.adventureunionexp = var1.readInt64();
            } else if (var5 == 58) {
               Codec var11 = ProtobufProxy.create(PT_CONTENTS_REWARD_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var12 = var1.readRawVarint32();
               int var13 = var1.pushLimit(var12);
               var2.rewards = (PT_CONTENTS_REWARD_INFO)var11.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var13);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_REWARD_QUEST.class);
         return this.descriptor = var1;
      }
   }
}
