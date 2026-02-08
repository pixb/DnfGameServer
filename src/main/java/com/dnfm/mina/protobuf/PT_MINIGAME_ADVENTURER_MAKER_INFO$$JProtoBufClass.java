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

public class PT_MINIGAME_ADVENTURER_MAKER_INFO$$JProtoBufClass implements Codec<PT_MINIGAME_ADVENTURER_MAKER_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_MINIGAME_ADVENTURER_MAKER_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_MINIGAME_ADVENTURER_MAKER_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_MINIGAME_ADVENTURER_MAKER_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.babyinfo)) {
         PT_MINIGAME_ADVENTURER_MAKER_BABY_INFO var7 = var1.babyinfo;
         var2 += CodedConstant.computeSize(1, var7, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.jobinfos)) {
         List var8 = var1.jobinfos;
         var2 += CodedConstant.computeListSize(2, var8, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.ticketinfos)) {
         List var9 = var1.ticketinfos;
         var2 += CodedConstant.computeListSize(3, var9, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.history)) {
         List var10 = var1.history;
         var2 += CodedConstant.computeListSize(4, var10, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(PT_MINIGAME_ADVENTURER_MAKER_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.babyinfo)) {
         PT_MINIGAME_ADVENTURER_MAKER_BABY_INFO var7 = var1.babyinfo;
         if (var7 != null) {
            CodedConstant.writeObject(var2, 1, FieldType.OBJECT, var7, false);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.jobinfos)) {
         List var8 = var1.jobinfos;
         if (var8 != null) {
            CodedConstant.writeToList(var2, 2, FieldType.OBJECT, var8, false);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.ticketinfos)) {
         List var9 = var1.ticketinfos;
         if (var9 != null) {
            CodedConstant.writeToList(var2, 3, FieldType.OBJECT, var9, false);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.history)) {
         List var10 = var1.history;
         if (var10 != null) {
            CodedConstant.writeToList(var2, 4, FieldType.OBJECT, var10, false);
         }
      }

   }

   public void writeTo(PT_MINIGAME_ADVENTURER_MAKER_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_MINIGAME_ADVENTURER_MAKER_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_MINIGAME_ADVENTURER_MAKER_INFO var2 = new PT_MINIGAME_ADVENTURER_MAKER_INFO();
      var2.jobinfos = new ArrayList();
      var2.ticketinfos = new ArrayList();
      var2.history = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 10) {
               Codec var10 = ProtobufProxy.create(PT_MINIGAME_ADVENTURER_MAKER_BABY_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.babyinfo = (PT_MINIGAME_ADVENTURER_MAKER_BABY_INFO)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 18) {
               Codec var11 = ProtobufProxy.create(PT_MINIGAME_ADVENTURER_MAKER_JOB_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var14 = var1.readRawVarint32();
               int var17 = var1.pushLimit(var14);
               if (var2.jobinfos == null) {
                  var2.jobinfos = new ArrayList();
               }

               var2.jobinfos.add((PT_MINIGAME_ADVENTURER_MAKER_JOB_INFO)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var17);
            } else if (var5 == 26) {
               Codec var12 = ProtobufProxy.create(PT_MINIGAME_ADVENTURER_MAKER_TICKET_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var15 = var1.readRawVarint32();
               int var18 = var1.pushLimit(var15);
               if (var2.ticketinfos == null) {
                  var2.ticketinfos = new ArrayList();
               }

               var2.ticketinfos.add((PT_MINIGAME_ADVENTURER_MAKER_TICKET_INFO)var12.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var18);
            } else if (var5 == 34) {
               Codec var13 = ProtobufProxy.create(PT_MINIGAME_ADVENTURER_MAKER_CLEAR_HISTORY.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var16 = var1.readRawVarint32();
               int var19 = var1.pushLimit(var16);
               if (var2.history == null) {
                  var2.history = new ArrayList();
               }

               var2.history.add((PT_MINIGAME_ADVENTURER_MAKER_CLEAR_HISTORY)var13.readFrom(var1));
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_MINIGAME_ADVENTURER_MAKER_INFO.class);
         return this.descriptor = var1;
      }
   }
}
