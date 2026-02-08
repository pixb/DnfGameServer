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

public class PT_HISTORICSITE_EXCAVATION_RESULT$$JProtoBufClass implements Codec<PT_HISTORICSITE_EXCAVATION_RESULT>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_HISTORICSITE_EXCAVATION_RESULT var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_HISTORICSITE_EXCAVATION_RESULT decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_HISTORICSITE_EXCAVATION_RESULT var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.playerresults)) {
         List var10 = var1.playerresults;
         var2 += CodedConstant.computeListSize(1, var10, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.bonus)) {
         Integer var11 = var1.bonus;
         var2 += CodedOutputStream.computeInt32Size(2, var11);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.coin)) {
         Integer var12 = var1.coin;
         var2 += CodedOutputStream.computeInt32Size(3, var12);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.timebonus)) {
         Integer var13 = var1.timebonus;
         var2 += CodedOutputStream.computeInt32Size(4, var13);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.minebonus)) {
         Integer var14 = var1.minebonus;
         var2 += CodedOutputStream.computeInt32Size(5, var14);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.goldbonus)) {
         Integer var15 = var1.goldbonus;
         var2 += CodedOutputStream.computeInt32Size(6, var15);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.hpbonus)) {
         Integer var16 = var1.hpbonus;
         var2 += CodedOutputStream.computeInt32Size(7, var16);
      }

      return var2;
   }

   public void doWriteTo(PT_HISTORICSITE_EXCAVATION_RESULT var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.playerresults)) {
         List var10 = var1.playerresults;
         if (var10 != null) {
            CodedConstant.writeToList(var2, 1, FieldType.OBJECT, var10, false);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.bonus)) {
         Integer var11 = var1.bonus;
         if (var11 != null) {
            var2.writeInt32(2, var11);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.coin)) {
         Integer var12 = var1.coin;
         if (var12 != null) {
            var2.writeInt32(3, var12);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.timebonus)) {
         Integer var13 = var1.timebonus;
         if (var13 != null) {
            var2.writeInt32(4, var13);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.minebonus)) {
         Integer var14 = var1.minebonus;
         if (var14 != null) {
            var2.writeInt32(5, var14);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.goldbonus)) {
         Integer var15 = var1.goldbonus;
         if (var15 != null) {
            var2.writeInt32(6, var15);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.hpbonus)) {
         Integer var16 = var1.hpbonus;
         if (var16 != null) {
            var2.writeInt32(7, var16);
         }
      }

   }

   public void writeTo(PT_HISTORICSITE_EXCAVATION_RESULT var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_HISTORICSITE_EXCAVATION_RESULT readFrom(CodedInputStream var1) throws IOException {
      PT_HISTORICSITE_EXCAVATION_RESULT var2 = new PT_HISTORICSITE_EXCAVATION_RESULT();
      var2.playerresults = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 10) {
               Codec var10 = ProtobufProxy.create(PT_HISTORICSITE_EXCAVATION_PLAYER_RESULT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.playerresults == null) {
                  var2.playerresults = new ArrayList();
               }

               var2.playerresults.add((PT_HISTORICSITE_EXCAVATION_PLAYER_RESULT)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 16) {
               var2.bonus = var1.readInt32();
            } else if (var5 == 24) {
               var2.coin = var1.readInt32();
            } else if (var5 == 32) {
               var2.timebonus = var1.readInt32();
            } else if (var5 == 40) {
               var2.minebonus = var1.readInt32();
            } else if (var5 == 48) {
               var2.goldbonus = var1.readInt32();
            } else if (var5 == 56) {
               var2.hpbonus = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_HISTORICSITE_EXCAVATION_RESULT.class);
         return this.descriptor = var1;
      }
   }
}
