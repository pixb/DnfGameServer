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

public class PT_PVP_HISTORY_INFO$$JProtoBufClass implements Codec<PT_PVP_HISTORY_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_PVP_HISTORY_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_PVP_HISTORY_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_PVP_HISTORY_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.createtime)) {
         Long var14 = var1.createtime;
         var2 += CodedOutputStream.computeInt64Size(1, var14);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.equipscore)) {
         Integer var15 = var1.equipscore;
         var2 += CodedOutputStream.computeInt32Size(2, var15);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var16 = var1.growtype;
         var2 += CodedOutputStream.computeInt32Size(3, var16);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var17 = var1.secondgrowtype;
         var2 += CodedOutputStream.computeInt32Size(4, var17);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var18 = var1.name;
         var2 += CodedOutputStream.computeStringSize(5, var18);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var19 = var1.job;
         var2 += CodedOutputStream.computeInt32Size(6, var19);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.result)) {
         Integer var20 = var1.result;
         var2 += CodedOutputStream.computeInt32Size(7, var20);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var21 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(8, var21);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var22 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(9, var22);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.pvpcoin)) {
         Integer var23 = var1.pvpcoin;
         var2 += CodedOutputStream.computeInt32Size(10, var23);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.characters)) {
         List var24 = var1.characters;
         var2 += CodedConstant.computeListSize(11, var24, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(PT_PVP_HISTORY_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.createtime)) {
         Long var14 = var1.createtime;
         if (var14 != null) {
            var2.writeInt64(1, var14);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.equipscore)) {
         Integer var15 = var1.equipscore;
         if (var15 != null) {
            var2.writeInt32(2, var15);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var16 = var1.growtype;
         if (var16 != null) {
            var2.writeInt32(3, var16);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var17 = var1.secondgrowtype;
         if (var17 != null) {
            var2.writeInt32(4, var17);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var18 = var1.name;
         if (var18 != null) {
            var2.writeString(5, var18);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var19 = var1.job;
         if (var19 != null) {
            var2.writeInt32(6, var19);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.result)) {
         Integer var20 = var1.result;
         if (var20 != null) {
            var2.writeInt32(7, var20);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var21 = var1.type;
         if (var21 != null) {
            var2.writeInt32(8, var21);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var22 = var1.level;
         if (var22 != null) {
            var2.writeInt32(9, var22);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.pvpcoin)) {
         Integer var23 = var1.pvpcoin;
         if (var23 != null) {
            var2.writeInt32(10, var23);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.characters)) {
         List var24 = var1.characters;
         if (var24 != null) {
            CodedConstant.writeToList(var2, 11, FieldType.OBJECT, var24, false);
         }
      }

   }

   public void writeTo(PT_PVP_HISTORY_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_PVP_HISTORY_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_PVP_HISTORY_INFO var2 = new PT_PVP_HISTORY_INFO();
      var2.characters = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.createtime = var1.readInt64();
            } else if (var5 == 16) {
               var2.equipscore = var1.readInt32();
            } else if (var5 == 24) {
               var2.growtype = var1.readInt32();
            } else if (var5 == 32) {
               var2.secondgrowtype = var1.readInt32();
            } else if (var5 == 42) {
               var2.name = var1.readString();
            } else if (var5 == 48) {
               var2.job = var1.readInt32();
            } else if (var5 == 56) {
               var2.result = var1.readInt32();
            } else if (var5 == 64) {
               var2.type = var1.readInt32();
            } else if (var5 == 72) {
               var2.level = var1.readInt32();
            } else if (var5 == 80) {
               var2.pvpcoin = var1.readInt32();
            } else if (var5 == 90) {
               Codec var10 = ProtobufProxy.create(PT_PVP_HISTORY_CHARACTER_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.characters == null) {
                  var2.characters = new ArrayList();
               }

               var2.characters.add((PT_PVP_HISTORY_CHARACTER_INFO)var10.readFrom(var1));
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_PVP_HISTORY_INFO.class);
         return this.descriptor = var1;
      }
   }
}
