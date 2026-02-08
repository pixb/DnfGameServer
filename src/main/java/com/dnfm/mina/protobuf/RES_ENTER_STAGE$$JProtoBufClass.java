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

public class RES_ENTER_STAGE$$JProtoBufClass implements Codec<RES_ENTER_STAGE>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_ENTER_STAGE var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_ENTER_STAGE decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_ENTER_STAGE var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var11 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var11);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.fatigue)) {
         Integer var12 = var1.fatigue;
         var2 += CodedOutputStream.computeInt32Size(2, var12);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.consumefatigue)) {
         Integer var13 = var1.consumefatigue;
         var2 += CodedOutputStream.computeInt32Size(3, var13);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.recoveryfatiguetime)) {
         Long var14 = var1.recoveryfatiguetime;
         var2 += CodedOutputStream.computeInt64Size(4, var14);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.servertime)) {
         Long var15 = var1.servertime;
         var2 += CodedOutputStream.computeUInt64Size(5, var15);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.monstermove)) {
         List var16 = var1.monstermove;
         var2 += CodedConstant.computeListSize(6, var16, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.sender)) {
         Long var17 = var1.sender;
         var2 += CodedOutputStream.computeUInt64Size(7, var17);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.isopencrack)) {
         Boolean var18 = var1.isopencrack;
         var2 += CodedOutputStream.computeBoolSize(8, var18);
      }

      return var2;
   }

   public void doWriteTo(RES_ENTER_STAGE var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var11 = var1.error;
         if (var11 != null) {
            var2.writeInt32(1, var11);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.fatigue)) {
         Integer var12 = var1.fatigue;
         if (var12 != null) {
            var2.writeInt32(2, var12);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.consumefatigue)) {
         Integer var13 = var1.consumefatigue;
         if (var13 != null) {
            var2.writeInt32(3, var13);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.recoveryfatiguetime)) {
         Long var14 = var1.recoveryfatiguetime;
         if (var14 != null) {
            var2.writeInt64(4, var14);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.servertime)) {
         Long var15 = var1.servertime;
         if (var15 != null) {
            var2.writeUInt64(5, var15);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.monstermove)) {
         List var16 = var1.monstermove;
         if (var16 != null) {
            CodedConstant.writeToList(var2, 6, FieldType.OBJECT, var16, false);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.sender)) {
         Long var17 = var1.sender;
         if (var17 != null) {
            var2.writeUInt64(7, var17);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.isopencrack)) {
         Boolean var18 = var1.isopencrack;
         if (var18 != null) {
            var2.writeBool(8, var18);
         }
      }

   }

   public void writeTo(RES_ENTER_STAGE var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_ENTER_STAGE readFrom(CodedInputStream var1) throws IOException {
      RES_ENTER_STAGE var2 = new RES_ENTER_STAGE();
      var2.monstermove = new ArrayList();

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
               var2.fatigue = var1.readInt32();
            } else if (var5 == 24) {
               var2.consumefatigue = var1.readInt32();
            } else if (var5 == 32) {
               var2.recoveryfatiguetime = var1.readInt64();
            } else if (var5 == 40) {
               var2.servertime = var1.readUInt64();
            } else if (var5 == 50) {
               Codec var10 = ProtobufProxy.create(PT_CHAMPION_CHANGE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.monstermove == null) {
                  var2.monstermove = new ArrayList();
               }

               var2.monstermove.add((PT_CHAMPION_CHANGE)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 56) {
               var2.sender = var1.readUInt64();
            } else if (var5 == 64) {
               var2.isopencrack = var1.readBool();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_ENTER_STAGE.class);
         return this.descriptor = var1;
      }
   }
}
