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

public class PT_BATTLEFIELD_STATEINFO$$JProtoBufClass implements Codec<PT_BATTLEFIELD_STATEINFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_BATTLEFIELD_STATEINFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_BATTLEFIELD_STATEINFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_BATTLEFIELD_STATEINFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.battlefield)) {
         Integer var11 = var1.battlefield;
         var2 += CodedOutputStream.computeInt32Size(1, var11);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.scramble)) {
         Integer var12 = var1.scramble;
         var2 += CodedOutputStream.computeInt32Size(2, var12);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.slotindex)) {
         Integer var13 = var1.slotindex;
         var2 += CodedOutputStream.computeInt32Size(3, var13);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.state)) {
         Integer var14 = var1.state;
         var2 += CodedOutputStream.computeInt32Size(4, var14);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.endtime)) {
         Long var15 = var1.endtime;
         var2 += CodedOutputStream.computeInt64Size(5, var15);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.red)) {
         PT_BATTLEFIELD_TEAMINFO var16 = var1.red;
         var2 += CodedConstant.computeSize(6, var16, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.blue)) {
         PT_BATTLEFIELD_TEAMINFO var17 = var1.blue;
         var2 += CodedConstant.computeSize(7, var17, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.garrison)) {
         Boolean var18 = var1.garrison;
         var2 += CodedOutputStream.computeBoolSize(8, var18);
      }

      return var2;
   }

   public void doWriteTo(PT_BATTLEFIELD_STATEINFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.battlefield)) {
         Integer var11 = var1.battlefield;
         if (var11 != null) {
            var2.writeInt32(1, var11);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.scramble)) {
         Integer var12 = var1.scramble;
         if (var12 != null) {
            var2.writeInt32(2, var12);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.slotindex)) {
         Integer var13 = var1.slotindex;
         if (var13 != null) {
            var2.writeInt32(3, var13);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.state)) {
         Integer var14 = var1.state;
         if (var14 != null) {
            var2.writeInt32(4, var14);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.endtime)) {
         Long var15 = var1.endtime;
         if (var15 != null) {
            var2.writeInt64(5, var15);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.red)) {
         PT_BATTLEFIELD_TEAMINFO var16 = var1.red;
         if (var16 != null) {
            CodedConstant.writeObject(var2, 6, FieldType.OBJECT, var16, false);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.blue)) {
         PT_BATTLEFIELD_TEAMINFO var17 = var1.blue;
         if (var17 != null) {
            CodedConstant.writeObject(var2, 7, FieldType.OBJECT, var17, false);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.garrison)) {
         Boolean var18 = var1.garrison;
         if (var18 != null) {
            var2.writeBool(8, var18);
         }
      }

   }

   public void writeTo(PT_BATTLEFIELD_STATEINFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_BATTLEFIELD_STATEINFO readFrom(CodedInputStream var1) throws IOException {
      PT_BATTLEFIELD_STATEINFO var2 = new PT_BATTLEFIELD_STATEINFO();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.battlefield = var1.readInt32();
            } else if (var5 == 16) {
               var2.scramble = var1.readInt32();
            } else if (var5 == 24) {
               var2.slotindex = var1.readInt32();
            } else if (var5 == 32) {
               var2.state = var1.readInt32();
            } else if (var5 == 40) {
               var2.endtime = var1.readInt64();
            } else if (var5 == 50) {
               Codec var10 = ProtobufProxy.create(PT_BATTLEFIELD_TEAMINFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.red = (PT_BATTLEFIELD_TEAMINFO)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 58) {
               Codec var11 = ProtobufProxy.create(PT_BATTLEFIELD_TEAMINFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var12 = var1.readRawVarint32();
               int var13 = var1.pushLimit(var12);
               var2.blue = (PT_BATTLEFIELD_TEAMINFO)var11.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var13);
            } else if (var5 == 64) {
               var2.garrison = var1.readBool();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_BATTLEFIELD_STATEINFO.class);
         return this.descriptor = var1;
      }
   }
}
