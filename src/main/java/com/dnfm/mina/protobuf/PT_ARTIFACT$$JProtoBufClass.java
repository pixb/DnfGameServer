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

public class PT_ARTIFACT$$JProtoBufClass implements Codec<PT_ARTIFACT>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_ARTIFACT var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_ARTIFACT decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_ARTIFACT var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var12 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(1, var12);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var13 = var1.count;
         var2 += CodedOutputStream.computeInt32Size(2, var13);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var14 = var1.guid;
         var2 += CodedOutputStream.computeUInt64Size(3, var14);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.slot)) {
         Integer var15 = var1.slot;
         var2 += CodedOutputStream.computeInt32Size(4, var15);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.scount)) {
         Integer var16 = var1.scount;
         var2 += CodedOutputStream.computeInt32Size(5, var16);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.tcount)) {
         Integer var17 = var1.tcount;
         var2 += CodedOutputStream.computeInt32Size(6, var17);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.boption)) {
         List var18 = var1.boption;
         var2 += CodedConstant.computeListSize(7, var18, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.upgrade)) {
         Integer var19 = var1.upgrade;
         var2 += CodedOutputStream.computeInt32Size(8, var19);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.sindex)) {
         Integer var20 = var1.sindex;
         var2 += CodedOutputStream.computeInt32Size(9, var20);
      }

      return var2;
   }

   public void doWriteTo(PT_ARTIFACT var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var12 = var1.index;
         if (var12 != null) {
            var2.writeInt32(1, var12);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var13 = var1.count;
         if (var13 != null) {
            var2.writeInt32(2, var13);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var14 = var1.guid;
         if (var14 != null) {
            var2.writeUInt64(3, var14);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.slot)) {
         Integer var15 = var1.slot;
         if (var15 != null) {
            var2.writeInt32(4, var15);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.scount)) {
         Integer var16 = var1.scount;
         if (var16 != null) {
            var2.writeInt32(5, var16);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.tcount)) {
         Integer var17 = var1.tcount;
         if (var17 != null) {
            var2.writeInt32(6, var17);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.boption)) {
         List var18 = var1.boption;
         if (var18 != null) {
            CodedConstant.writeToList(var2, 7, FieldType.OBJECT, var18, false);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.upgrade)) {
         Integer var19 = var1.upgrade;
         if (var19 != null) {
            var2.writeInt32(8, var19);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.sindex)) {
         Integer var20 = var1.sindex;
         if (var20 != null) {
            var2.writeInt32(9, var20);
         }
      }

   }

   public void writeTo(PT_ARTIFACT var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_ARTIFACT readFrom(CodedInputStream var1) throws IOException {
      PT_ARTIFACT var2 = new PT_ARTIFACT();
      var2.boption = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.index = var1.readInt32();
            } else if (var5 == 16) {
               var2.count = var1.readInt32();
            } else if (var5 == 24) {
               var2.guid = var1.readUInt64();
            } else if (var5 == 32) {
               var2.slot = var1.readInt32();
            } else if (var5 == 40) {
               var2.scount = var1.readInt32();
            } else if (var5 == 48) {
               var2.tcount = var1.readInt32();
            } else if (var5 == 58) {
               Codec var10 = ProtobufProxy.create(PT_ARTIFACT_BASE_OPTION.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.boption == null) {
                  var2.boption = new ArrayList();
               }

               var2.boption.add((PT_ARTIFACT_BASE_OPTION)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 64) {
               var2.upgrade = var1.readInt32();
            } else if (var5 == 72) {
               var2.sindex = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_ARTIFACT.class);
         return this.descriptor = var1;
      }
   }
}
