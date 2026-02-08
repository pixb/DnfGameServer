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

public class RES_HISTORICSITE_RELIC_LIST$$JProtoBufClass implements Codec<RES_HISTORICSITE_RELIC_LIST>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_HISTORICSITE_RELIC_LIST var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_HISTORICSITE_RELIC_LIST decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_HISTORICSITE_RELIC_LIST var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var7 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var7);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.relicindexes)) {
         List var8 = var1.relicindexes;
         var2 += CodedConstant.computeListSize(2, var8, FieldType.INT32, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.coin)) {
         Integer var9 = var1.coin;
         var2 += CodedOutputStream.computeInt32Size(3, var9);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.relicspirit)) {
         Integer var10 = var1.relicspirit;
         var2 += CodedOutputStream.computeInt32Size(4, var10);
      }

      return var2;
   }

   public void doWriteTo(RES_HISTORICSITE_RELIC_LIST var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var7 = var1.error;
         if (var7 != null) {
            var2.writeInt32(1, var7);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.relicindexes)) {
         List var8 = var1.relicindexes;
         if (var8 != null) {
            CodedConstant.writeToList(var2, 2, FieldType.INT32, var8, true);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.coin)) {
         Integer var9 = var1.coin;
         if (var9 != null) {
            var2.writeInt32(3, var9);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.relicspirit)) {
         Integer var10 = var1.relicspirit;
         if (var10 != null) {
            var2.writeInt32(4, var10);
         }
      }

   }

   public void writeTo(RES_HISTORICSITE_RELIC_LIST var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_HISTORICSITE_RELIC_LIST readFrom(CodedInputStream var1) throws IOException {
      RES_HISTORICSITE_RELIC_LIST var2 = new RES_HISTORICSITE_RELIC_LIST();
      var2.relicindexes = new ArrayList();

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
               if (var2.relicindexes == null) {
                  var2.relicindexes = new ArrayList();
               }

               var2.relicindexes.add(var1.readInt32());
            } else if (var5 != 18) {
               if (var5 == 24) {
                  var2.coin = var1.readInt32();
               } else if (var5 == 32) {
                  var2.relicspirit = var1.readInt32();
               } else {
                  var1.skipField(var5);
               }
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.relicindexes == null) {
                  var2.relicindexes = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.relicindexes.add(var1.readInt32());
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_HISTORICSITE_RELIC_LIST.class);
         return this.descriptor = var1;
      }
   }
}
