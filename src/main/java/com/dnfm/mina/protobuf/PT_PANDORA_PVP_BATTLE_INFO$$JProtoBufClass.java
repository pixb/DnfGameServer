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

public class PT_PANDORA_PVP_BATTLE_INFO$$JProtoBufClass implements Codec<PT_PANDORA_PVP_BATTLE_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_PANDORA_PVP_BATTLE_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_PANDORA_PVP_BATTLE_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_PANDORA_PVP_BATTLE_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.matchtype)) {
         Integer var13 = var1.matchtype;
         var2 += CodedOutputStream.computeInt32Size(1, var13);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.currenttier)) {
         Integer var14 = var1.currenttier;
         var2 += CodedOutputStream.computeInt32Size(2, var14);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.currentseason)) {
         Long var15 = var1.currentseason;
         var2 += CodedOutputStream.computeInt64Size(3, var15);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.allmaxtier)) {
         Integer var16 = var1.allmaxtier;
         var2 += CodedOutputStream.computeInt32Size(4, var16);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.maxtierseason)) {
         Long var17 = var1.maxtierseason;
         var2 += CodedOutputStream.computeInt64Size(5, var17);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.currentwin)) {
         Integer var18 = var1.currentwin;
         var2 += CodedOutputStream.computeInt32Size(6, var18);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.currentlose)) {
         Integer var19 = var1.currentlose;
         var2 += CodedOutputStream.computeInt32Size(7, var19);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.currentmaxconsecutivewin)) {
         Integer var20 = var1.currentmaxconsecutivewin;
         var2 += CodedOutputStream.computeInt32Size(8, var20);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.allmaxconsecutivewin)) {
         Integer var21 = var1.allmaxconsecutivewin;
         var2 += CodedOutputStream.computeInt32Size(9, var21);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.pvpachievements)) {
         List var22 = var1.pvpachievements;
         var2 += CodedConstant.computeListSize(10, var22, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(PT_PANDORA_PVP_BATTLE_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.matchtype)) {
         Integer var13 = var1.matchtype;
         if (var13 != null) {
            var2.writeInt32(1, var13);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.currenttier)) {
         Integer var14 = var1.currenttier;
         if (var14 != null) {
            var2.writeInt32(2, var14);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.currentseason)) {
         Long var15 = var1.currentseason;
         if (var15 != null) {
            var2.writeInt64(3, var15);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.allmaxtier)) {
         Integer var16 = var1.allmaxtier;
         if (var16 != null) {
            var2.writeInt32(4, var16);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.maxtierseason)) {
         Long var17 = var1.maxtierseason;
         if (var17 != null) {
            var2.writeInt64(5, var17);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.currentwin)) {
         Integer var18 = var1.currentwin;
         if (var18 != null) {
            var2.writeInt32(6, var18);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.currentlose)) {
         Integer var19 = var1.currentlose;
         if (var19 != null) {
            var2.writeInt32(7, var19);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.currentmaxconsecutivewin)) {
         Integer var20 = var1.currentmaxconsecutivewin;
         if (var20 != null) {
            var2.writeInt32(8, var20);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.allmaxconsecutivewin)) {
         Integer var21 = var1.allmaxconsecutivewin;
         if (var21 != null) {
            var2.writeInt32(9, var21);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.pvpachievements)) {
         List var22 = var1.pvpachievements;
         if (var22 != null) {
            CodedConstant.writeToList(var2, 10, FieldType.OBJECT, var22, false);
         }
      }

   }

   public void writeTo(PT_PANDORA_PVP_BATTLE_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_PANDORA_PVP_BATTLE_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_PANDORA_PVP_BATTLE_INFO var2 = new PT_PANDORA_PVP_BATTLE_INFO();
      var2.pvpachievements = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.matchtype = var1.readInt32();
            } else if (var5 == 16) {
               var2.currenttier = var1.readInt32();
            } else if (var5 == 24) {
               var2.currentseason = var1.readInt64();
            } else if (var5 == 32) {
               var2.allmaxtier = var1.readInt32();
            } else if (var5 == 40) {
               var2.maxtierseason = var1.readInt64();
            } else if (var5 == 48) {
               var2.currentwin = var1.readInt32();
            } else if (var5 == 56) {
               var2.currentlose = var1.readInt32();
            } else if (var5 == 64) {
               var2.currentmaxconsecutivewin = var1.readInt32();
            } else if (var5 == 72) {
               var2.allmaxconsecutivewin = var1.readInt32();
            } else if (var5 == 82) {
               Codec var10 = ProtobufProxy.create(PT_PVP_ACHIEVEMENT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.pvpachievements == null) {
                  var2.pvpachievements = new ArrayList();
               }

               var2.pvpachievements.add((PT_PVP_ACHIEVEMENT)var10.readFrom(var1));
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_PANDORA_PVP_BATTLE_INFO.class);
         return this.descriptor = var1;
      }
   }
}
