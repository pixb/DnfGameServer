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

public class RES_HISTORICSITE_RESULT_RECORD$$JProtoBufClass implements Codec<RES_HISTORICSITE_RESULT_RECORD>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_HISTORICSITE_RESULT_RECORD var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_HISTORICSITE_RESULT_RECORD decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_HISTORICSITE_RESULT_RECORD var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var13 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var13);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.time)) {
         String var14 = var1.time;
         var2 += CodedOutputStream.computeStringSize(2, var14);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.gguid)) {
         Long var15 = var1.gguid;
         var2 += CodedOutputStream.computeUInt64Size(3, var15);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.result)) {
         Integer var16 = var1.result;
         var2 += CodedOutputStream.computeInt32Size(4, var16);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.win)) {
         Integer var17 = var1.win;
         var2 += CodedOutputStream.computeInt32Size(5, var17);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var18 = var1.count;
         var2 += CodedOutputStream.computeInt32Size(6, var18);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.totalnum)) {
         Integer var19 = var1.totalnum;
         var2 += CodedOutputStream.computeInt32Size(7, var19);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.totalreward)) {
         Long var20 = var1.totalreward;
         var2 += CodedOutputStream.computeUInt64Size(8, var20);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.point)) {
         PT_HISTORICSITE_POINT var21 = var1.point;
         var2 += CodedConstant.computeSize(9, var21, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.list)) {
         List var22 = var1.list;
         var2 += CodedConstant.computeListSize(10, var22, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(RES_HISTORICSITE_RESULT_RECORD var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var13 = var1.error;
         if (var13 != null) {
            var2.writeInt32(1, var13);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.time)) {
         String var14 = var1.time;
         if (var14 != null) {
            var2.writeString(2, var14);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.gguid)) {
         Long var15 = var1.gguid;
         if (var15 != null) {
            var2.writeUInt64(3, var15);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.result)) {
         Integer var16 = var1.result;
         if (var16 != null) {
            var2.writeInt32(4, var16);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.win)) {
         Integer var17 = var1.win;
         if (var17 != null) {
            var2.writeInt32(5, var17);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var18 = var1.count;
         if (var18 != null) {
            var2.writeInt32(6, var18);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.totalnum)) {
         Integer var19 = var1.totalnum;
         if (var19 != null) {
            var2.writeInt32(7, var19);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.totalreward)) {
         Long var20 = var1.totalreward;
         if (var20 != null) {
            var2.writeUInt64(8, var20);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.point)) {
         PT_HISTORICSITE_POINT var21 = var1.point;
         if (var21 != null) {
            CodedConstant.writeObject(var2, 9, FieldType.OBJECT, var21, false);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.list)) {
         List var22 = var1.list;
         if (var22 != null) {
            CodedConstant.writeToList(var2, 10, FieldType.OBJECT, var22, false);
         }
      }

   }

   public void writeTo(RES_HISTORICSITE_RESULT_RECORD var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_HISTORICSITE_RESULT_RECORD readFrom(CodedInputStream var1) throws IOException {
      RES_HISTORICSITE_RESULT_RECORD var2 = new RES_HISTORICSITE_RESULT_RECORD();
      var2.list = new ArrayList();

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
            } else if (var5 == 18) {
               var2.time = var1.readString();
            } else if (var5 == 24) {
               var2.gguid = var1.readUInt64();
            } else if (var5 == 32) {
               var2.result = var1.readInt32();
            } else if (var5 == 40) {
               var2.win = var1.readInt32();
            } else if (var5 == 48) {
               var2.count = var1.readInt32();
            } else if (var5 == 56) {
               var2.totalnum = var1.readInt32();
            } else if (var5 == 64) {
               var2.totalreward = var1.readUInt64();
            } else if (var5 == 74) {
               Codec var10 = ProtobufProxy.create(PT_HISTORICSITE_POINT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.point = (PT_HISTORICSITE_POINT)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 82) {
               Codec var11 = ProtobufProxy.create(PT_MEMBER_RESULT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var12 = var1.readRawVarint32();
               int var13 = var1.pushLimit(var12);
               if (var2.list == null) {
                  var2.list = new ArrayList();
               }

               var2.list.add((PT_MEMBER_RESULT)var11.readFrom(var1));
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_HISTORICSITE_RESULT_RECORD.class);
         return this.descriptor = var1;
      }
   }
}
