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

public class PT_HISTORICSITE_STRATEGY_SLOT$$JProtoBufClass implements Codec<PT_HISTORICSITE_STRATEGY_SLOT>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_HISTORICSITE_STRATEGY_SLOT var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_HISTORICSITE_STRATEGY_SLOT decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_HISTORICSITE_STRATEGY_SLOT var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.battlefield)) {
         Integer var7 = var1.battlefield;
         var2 += CodedOutputStream.computeInt32Size(1, var7);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.scramble)) {
         Integer var8 = var1.scramble;
         var2 += CodedOutputStream.computeInt32Size(2, var8);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.slotindex)) {
         Integer var9 = var1.slotindex;
         var2 += CodedOutputStream.computeInt32Size(3, var9);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.guids)) {
         List var10 = var1.guids;
         var2 += CodedConstant.computeListSize(4, var10, FieldType.UINT64, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      return var2;
   }

   public void doWriteTo(PT_HISTORICSITE_STRATEGY_SLOT var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.battlefield)) {
         Integer var7 = var1.battlefield;
         if (var7 != null) {
            var2.writeInt32(1, var7);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.scramble)) {
         Integer var8 = var1.scramble;
         if (var8 != null) {
            var2.writeInt32(2, var8);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.slotindex)) {
         Integer var9 = var1.slotindex;
         if (var9 != null) {
            var2.writeInt32(3, var9);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.guids)) {
         List var10 = var1.guids;
         if (var10 != null) {
            CodedConstant.writeToList(var2, 4, FieldType.UINT64, var10, true);
         }
      }

   }

   public void writeTo(PT_HISTORICSITE_STRATEGY_SLOT var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_HISTORICSITE_STRATEGY_SLOT readFrom(CodedInputStream var1) throws IOException {
      PT_HISTORICSITE_STRATEGY_SLOT var2 = new PT_HISTORICSITE_STRATEGY_SLOT();
      var2.guids = new ArrayList();

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
               if (var2.guids == null) {
                  var2.guids = new ArrayList();
               }

               var2.guids.add(var1.readUInt64());
            } else if (var5 != 34) {
               var1.skipField(var5);
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.guids == null) {
                  var2.guids = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.guids.add(var1.readUInt64());
               }

               var1.popLimit(var7);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_HISTORICSITE_STRATEGY_SLOT.class);
         return this.descriptor = var1;
      }
   }
}
