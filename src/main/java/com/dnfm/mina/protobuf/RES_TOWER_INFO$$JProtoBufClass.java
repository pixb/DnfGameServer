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

public class RES_TOWER_INFO$$JProtoBufClass implements Codec<RES_TOWER_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_TOWER_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_TOWER_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_TOWER_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var11 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var11);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.clearfloor)) {
         Integer var12 = var1.clearfloor;
         var2 += CodedOutputStream.computeInt32Size(2, var12);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var13 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(3, var13);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.sweepcount)) {
         Integer var14 = var1.sweepcount;
         var2 += CodedOutputStream.computeInt32Size(4, var14);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.list)) {
         List var15 = var1.list;
         var2 += CodedConstant.computeListSize(5, var15, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.agp)) {
         Integer var16 = var1.agp;
         var2 += CodedOutputStream.computeInt32Size(6, var16);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.point)) {
         Integer var17 = var1.point;
         var2 += CodedOutputStream.computeInt32Size(7, var17);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.clearrate)) {
         List var18 = var1.clearrate;
         var2 += CodedConstant.computeListSize(8, var18, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(RES_TOWER_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var11 = var1.error;
         if (var11 != null) {
            var2.writeInt32(1, var11);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.clearfloor)) {
         Integer var12 = var1.clearfloor;
         if (var12 != null) {
            var2.writeInt32(2, var12);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var13 = var1.type;
         if (var13 != null) {
            var2.writeInt32(3, var13);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.sweepcount)) {
         Integer var14 = var1.sweepcount;
         if (var14 != null) {
            var2.writeInt32(4, var14);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.list)) {
         List var15 = var1.list;
         if (var15 != null) {
            CodedConstant.writeToList(var2, 5, FieldType.OBJECT, var15, false);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.agp)) {
         Integer var16 = var1.agp;
         if (var16 != null) {
            var2.writeInt32(6, var16);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.point)) {
         Integer var17 = var1.point;
         if (var17 != null) {
            var2.writeInt32(7, var17);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.clearrate)) {
         List var18 = var1.clearrate;
         if (var18 != null) {
            CodedConstant.writeToList(var2, 8, FieldType.OBJECT, var18, false);
         }
      }

   }

   public void writeTo(RES_TOWER_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_TOWER_INFO readFrom(CodedInputStream var1) throws IOException {
      RES_TOWER_INFO var2 = new RES_TOWER_INFO();
      var2.list = new ArrayList();
      var2.clearrate = new ArrayList();

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
               var2.clearfloor = var1.readInt32();
            } else if (var5 == 24) {
               var2.type = var1.readInt32();
            } else if (var5 == 32) {
               var2.sweepcount = var1.readInt32();
            } else if (var5 == 42) {
               Codec var10 = ProtobufProxy.create(PT_FLOOR_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.list == null) {
                  var2.list = new ArrayList();
               }

               var2.list.add((PT_FLOOR_INFO)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 48) {
               var2.agp = var1.readInt32();
            } else if (var5 == 56) {
               var2.point = var1.readInt32();
            } else if (var5 == 66) {
               Codec var11 = ProtobufProxy.create(PT_TOWER_OF_ILLUSION_CLEAR_RATE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var12 = var1.readRawVarint32();
               int var13 = var1.pushLimit(var12);
               if (var2.clearrate == null) {
                  var2.clearrate = new ArrayList();
               }

               var2.clearrate.add((PT_TOWER_OF_ILLUSION_CLEAR_RATE)var11.readFrom(var1));
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_TOWER_INFO.class);
         return this.descriptor = var1;
      }
   }
}
