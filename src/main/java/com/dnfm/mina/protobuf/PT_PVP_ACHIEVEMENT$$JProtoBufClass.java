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

public class PT_PVP_ACHIEVEMENT$$JProtoBufClass implements Codec<PT_PVP_ACHIEVEMENT>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_PVP_ACHIEVEMENT var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_PVP_ACHIEVEMENT decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_PVP_ACHIEVEMENT var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.seasonindex)) {
         Integer var11 = var1.seasonindex;
         var2 += CodedOutputStream.computeInt32Size(1, var11);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.season)) {
         Integer var12 = var1.season;
         var2 += CodedOutputStream.computeInt32Size(2, var12);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.win)) {
         Integer var13 = var1.win;
         var2 += CodedOutputStream.computeInt32Size(3, var13);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.lose)) {
         Integer var14 = var1.lose;
         var2 += CodedOutputStream.computeInt32Size(4, var14);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.draw)) {
         Integer var15 = var1.draw;
         var2 += CodedOutputStream.computeInt32Size(5, var15);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.maxtier)) {
         Integer var16 = var1.maxtier;
         var2 += CodedOutputStream.computeInt32Size(6, var16);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.consecutivewin)) {
         Integer var17 = var1.consecutivewin;
         var2 += CodedOutputStream.computeInt32Size(7, var17);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.pvpcareerinfos)) {
         List var18 = var1.pvpcareerinfos;
         var2 += CodedConstant.computeListSize(8, var18, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(PT_PVP_ACHIEVEMENT var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.seasonindex)) {
         Integer var11 = var1.seasonindex;
         if (var11 != null) {
            var2.writeInt32(1, var11);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.season)) {
         Integer var12 = var1.season;
         if (var12 != null) {
            var2.writeInt32(2, var12);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.win)) {
         Integer var13 = var1.win;
         if (var13 != null) {
            var2.writeInt32(3, var13);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.lose)) {
         Integer var14 = var1.lose;
         if (var14 != null) {
            var2.writeInt32(4, var14);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.draw)) {
         Integer var15 = var1.draw;
         if (var15 != null) {
            var2.writeInt32(5, var15);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.maxtier)) {
         Integer var16 = var1.maxtier;
         if (var16 != null) {
            var2.writeInt32(6, var16);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.consecutivewin)) {
         Integer var17 = var1.consecutivewin;
         if (var17 != null) {
            var2.writeInt32(7, var17);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.pvpcareerinfos)) {
         List var18 = var1.pvpcareerinfos;
         if (var18 != null) {
            CodedConstant.writeToList(var2, 8, FieldType.OBJECT, var18, false);
         }
      }

   }

   public void writeTo(PT_PVP_ACHIEVEMENT var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_PVP_ACHIEVEMENT readFrom(CodedInputStream var1) throws IOException {
      PT_PVP_ACHIEVEMENT var2 = new PT_PVP_ACHIEVEMENT();
      var2.pvpcareerinfos = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.seasonindex = var1.readInt32();
            } else if (var5 == 16) {
               var2.season = var1.readInt32();
            } else if (var5 == 24) {
               var2.win = var1.readInt32();
            } else if (var5 == 32) {
               var2.lose = var1.readInt32();
            } else if (var5 == 40) {
               var2.draw = var1.readInt32();
            } else if (var5 == 48) {
               var2.maxtier = var1.readInt32();
            } else if (var5 == 56) {
               var2.consecutivewin = var1.readInt32();
            } else if (var5 == 66) {
               Codec var10 = ProtobufProxy.create(PT_PVP_CAREER_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.pvpcareerinfos == null) {
                  var2.pvpcareerinfos = new ArrayList();
               }

               var2.pvpcareerinfos.add((PT_PVP_CAREER_INFO)var10.readFrom(var1));
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_PVP_ACHIEVEMENT.class);
         return this.descriptor = var1;
      }
   }
}
