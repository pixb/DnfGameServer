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

public class RES_MINIGAME_SPECIAL_CLASS_MAZE_CLEAR$$JProtoBufClass implements Codec<RES_MINIGAME_SPECIAL_CLASS_MAZE_CLEAR>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_MINIGAME_SPECIAL_CLASS_MAZE_CLEAR var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_MINIGAME_SPECIAL_CLASS_MAZE_CLEAR decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_MINIGAME_SPECIAL_CLASS_MAZE_CLEAR var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var8 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var8);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.info)) {
         PT_MINIGAME_SPECIAL_CLASS_STUDENT_INFO var9 = var1.info;
         var2 += CodedConstant.computeSize(2, var9, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.jobinfos)) {
         List var10 = var1.jobinfos;
         var2 += CodedConstant.computeListSize(3, var10, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.ticketinfos)) {
         List var11 = var1.ticketinfos;
         var2 += CodedConstant.computeListSize(4, var11, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.rewardInfos)) {
         PT_CONTENTS_REWARD_INFO var12 = var1.rewardInfos;
         var2 += CodedConstant.computeSize(5, var12, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      return var2;
   }

   public void doWriteTo(RES_MINIGAME_SPECIAL_CLASS_MAZE_CLEAR var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var8 = var1.error;
         if (var8 != null) {
            var2.writeInt32(1, var8);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.info)) {
         PT_MINIGAME_SPECIAL_CLASS_STUDENT_INFO var9 = var1.info;
         if (var9 != null) {
            CodedConstant.writeObject(var2, 2, FieldType.OBJECT, var9, false);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.jobinfos)) {
         List var10 = var1.jobinfos;
         if (var10 != null) {
            CodedConstant.writeToList(var2, 3, FieldType.OBJECT, var10, false);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.ticketinfos)) {
         List var11 = var1.ticketinfos;
         if (var11 != null) {
            CodedConstant.writeToList(var2, 4, FieldType.OBJECT, var11, false);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.rewardInfos)) {
         PT_CONTENTS_REWARD_INFO var12 = var1.rewardInfos;
         if (var12 != null) {
            CodedConstant.writeObject(var2, 5, FieldType.OBJECT, var12, false);
         }
      }

   }

   public void writeTo(RES_MINIGAME_SPECIAL_CLASS_MAZE_CLEAR var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_MINIGAME_SPECIAL_CLASS_MAZE_CLEAR readFrom(CodedInputStream var1) throws IOException {
      RES_MINIGAME_SPECIAL_CLASS_MAZE_CLEAR var2 = new RES_MINIGAME_SPECIAL_CLASS_MAZE_CLEAR();
      var2.jobinfos = new ArrayList();
      var2.ticketinfos = new ArrayList();

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
               Codec var10 = ProtobufProxy.create(PT_MINIGAME_SPECIAL_CLASS_STUDENT_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.info = (PT_MINIGAME_SPECIAL_CLASS_STUDENT_INFO)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 26) {
               Codec var11 = ProtobufProxy.create(PT_MINIGAME_SPECIAL_CLASS_JOB_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var14 = var1.readRawVarint32();
               int var17 = var1.pushLimit(var14);
               if (var2.jobinfos == null) {
                  var2.jobinfos = new ArrayList();
               }

               var2.jobinfos.add((PT_MINIGAME_SPECIAL_CLASS_JOB_INFO)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var17);
            } else if (var5 == 34) {
               Codec var12 = ProtobufProxy.create(PT_MINIGAME_SPECIAL_CLASS_TICKET_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var15 = var1.readRawVarint32();
               int var18 = var1.pushLimit(var15);
               if (var2.ticketinfos == null) {
                  var2.ticketinfos = new ArrayList();
               }

               var2.ticketinfos.add((PT_MINIGAME_SPECIAL_CLASS_TICKET_INFO)var12.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var18);
            } else if (var5 == 42) {
               Codec var13 = ProtobufProxy.create(PT_CONTENTS_REWARD_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var16 = var1.readRawVarint32();
               int var19 = var1.pushLimit(var16);
               var2.rewardInfos = (PT_CONTENTS_REWARD_INFO)var13.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var19);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_MINIGAME_SPECIAL_CLASS_MAZE_CLEAR.class);
         return this.descriptor = var1;
      }
   }
}
