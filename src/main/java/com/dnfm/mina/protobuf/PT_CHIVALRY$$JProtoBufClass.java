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

public class PT_CHIVALRY$$JProtoBufClass implements Codec<PT_CHIVALRY>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_CHIVALRY var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_CHIVALRY decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_CHIVALRY var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.dungeontype)) {
         Integer var9 = var1.dungeontype;
         var2 += CodedOutputStream.computeInt32Size(1, var9);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var10 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(2, var10);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var11 = var1.grade;
         var2 += CodedOutputStream.computeInt32Size(3, var11);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.exp)) {
         Integer var12 = var1.exp;
         var2 += CodedOutputStream.computeInt32Size(4, var12);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.reward)) {
         Boolean var13 = var1.reward;
         var2 += CodedOutputStream.computeBoolSize(5, var13);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.missions)) {
         List var14 = var1.missions;
         var2 += CodedConstant.computeListSize(6, var14, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(PT_CHIVALRY var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.dungeontype)) {
         Integer var9 = var1.dungeontype;
         if (var9 != null) {
            var2.writeInt32(1, var9);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var10 = var1.index;
         if (var10 != null) {
            var2.writeInt32(2, var10);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var11 = var1.grade;
         if (var11 != null) {
            var2.writeInt32(3, var11);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.exp)) {
         Integer var12 = var1.exp;
         if (var12 != null) {
            var2.writeInt32(4, var12);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.reward)) {
         Boolean var13 = var1.reward;
         if (var13 != null) {
            var2.writeBool(5, var13);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.missions)) {
         List var14 = var1.missions;
         if (var14 != null) {
            CodedConstant.writeToList(var2, 6, FieldType.OBJECT, var14, false);
         }
      }

   }

   public void writeTo(PT_CHIVALRY var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_CHIVALRY readFrom(CodedInputStream var1) throws IOException {
      PT_CHIVALRY var2 = new PT_CHIVALRY();
      var2.missions = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.dungeontype = var1.readInt32();
            } else if (var5 == 16) {
               var2.index = var1.readInt32();
            } else if (var5 == 24) {
               var2.grade = var1.readInt32();
            } else if (var5 == 32) {
               var2.exp = var1.readInt32();
            } else if (var5 == 40) {
               var2.reward = var1.readBool();
            } else if (var5 == 50) {
               Codec var10 = ProtobufProxy.create(PT_CHIVALRY_MISSION.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.missions == null) {
                  var2.missions = new ArrayList();
               }

               var2.missions.add((PT_CHIVALRY_MISSION)var10.readFrom(var1));
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_CHIVALRY.class);
         return this.descriptor = var1;
      }
   }
}
