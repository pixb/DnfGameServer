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

public class PT_ADVENTURE_UNION_EXPEDITION$$JProtoBufClass implements Codec<PT_ADVENTURE_UNION_EXPEDITION>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_ADVENTURE_UNION_EXPEDITION var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_ADVENTURE_UNION_EXPEDITION decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_ADVENTURE_UNION_EXPEDITION var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.area)) {
         Integer var9 = var1.area;
         var2 += CodedOutputStream.computeInt32Size(1, var9);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.time)) {
         Integer var10 = var1.time;
         var2 += CodedOutputStream.computeInt32Size(2, var10);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.consumefatigue)) {
         Integer var11 = var1.consumefatigue;
         var2 += CodedOutputStream.computeInt32Size(3, var11);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.regdate)) {
         Long var12 = var1.regdate;
         var2 += CodedOutputStream.computeInt64Size(4, var12);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.characters)) {
         List var13 = var1.characters;
         var2 += CodedConstant.computeListSize(5, var13, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.rewards)) {
         List var14 = var1.rewards;
         var2 += CodedConstant.computeListSize(6, var14, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(PT_ADVENTURE_UNION_EXPEDITION var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.area)) {
         Integer var9 = var1.area;
         if (var9 != null) {
            var2.writeInt32(1, var9);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.time)) {
         Integer var10 = var1.time;
         if (var10 != null) {
            var2.writeInt32(2, var10);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.consumefatigue)) {
         Integer var11 = var1.consumefatigue;
         if (var11 != null) {
            var2.writeInt32(3, var11);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.regdate)) {
         Long var12 = var1.regdate;
         if (var12 != null) {
            var2.writeInt64(4, var12);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.characters)) {
         List var13 = var1.characters;
         if (var13 != null) {
            CodedConstant.writeToList(var2, 5, FieldType.OBJECT, var13, false);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.rewards)) {
         List var14 = var1.rewards;
         if (var14 != null) {
            CodedConstant.writeToList(var2, 6, FieldType.OBJECT, var14, false);
         }
      }

   }

   public void writeTo(PT_ADVENTURE_UNION_EXPEDITION var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_ADVENTURE_UNION_EXPEDITION readFrom(CodedInputStream var1) throws IOException {
      PT_ADVENTURE_UNION_EXPEDITION var2 = new PT_ADVENTURE_UNION_EXPEDITION();
      var2.characters = new ArrayList();
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
               var2.area = var1.readInt32();
            } else if (var5 == 16) {
               var2.time = var1.readInt32();
            } else if (var5 == 24) {
               var2.consumefatigue = var1.readInt32();
            } else if (var5 == 32) {
               var2.regdate = var1.readInt64();
            } else if (var5 == 42) {
               Codec var10 = ProtobufProxy.create(PT_CHARGUID_FATIGUE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.characters == null) {
                  var2.characters = new ArrayList();
               }

               var2.characters.add((PT_CHARGUID_FATIGUE)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 50) {
               Codec var11 = ProtobufProxy.create(PT_ADVENTURE_UNION_EXPEDITION_REWARDS.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var12 = var1.readRawVarint32();
               int var13 = var1.pushLimit(var12);
               if (var2.rewards == null) {
                  var2.rewards = new ArrayList();
               }

               var2.rewards.add((PT_ADVENTURE_UNION_EXPEDITION_REWARDS)var11.readFrom(var1));
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_ADVENTURE_UNION_EXPEDITION.class);
         return this.descriptor = var1;
      }
   }
}
