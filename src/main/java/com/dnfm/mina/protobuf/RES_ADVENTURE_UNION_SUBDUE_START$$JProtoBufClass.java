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

public class RES_ADVENTURE_UNION_SUBDUE_START$$JProtoBufClass implements Codec<RES_ADVENTURE_UNION_SUBDUE_START>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_ADVENTURE_UNION_SUBDUE_START var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_ADVENTURE_UNION_SUBDUE_START decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_ADVENTURE_UNION_SUBDUE_START var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var11 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var11);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var12 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(2, var12);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var13 = var1.count;
         var2 += CodedOutputStream.computeInt32Size(3, var13);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.guids)) {
         List var14 = var1.guids;
         var2 += CodedConstant.computeListSize(4, var14, FieldType.UINT64, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.consumefatigue)) {
         Integer var15 = var1.consumefatigue;
         var2 += CodedOutputStream.computeInt32Size(5, var15);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.consumeticket)) {
         Integer var16 = var1.consumeticket;
         var2 += CodedOutputStream.computeInt32Size(6, var16);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.consumeitemindex)) {
         Integer var17 = var1.consumeitemindex;
         var2 += CodedOutputStream.computeInt32Size(7, var17);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.consumeitemcount)) {
         Integer var18 = var1.consumeitemcount;
         var2 += CodedOutputStream.computeInt32Size(8, var18);
      }

      return var2;
   }

   public void doWriteTo(RES_ADVENTURE_UNION_SUBDUE_START var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var11 = var1.error;
         if (var11 != null) {
            var2.writeInt32(1, var11);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var12 = var1.index;
         if (var12 != null) {
            var2.writeInt32(2, var12);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var13 = var1.count;
         if (var13 != null) {
            var2.writeInt32(3, var13);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.guids)) {
         List var14 = var1.guids;
         if (var14 != null) {
            CodedConstant.writeToList(var2, 4, FieldType.UINT64, var14, true);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.consumefatigue)) {
         Integer var15 = var1.consumefatigue;
         if (var15 != null) {
            var2.writeInt32(5, var15);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.consumeticket)) {
         Integer var16 = var1.consumeticket;
         if (var16 != null) {
            var2.writeInt32(6, var16);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.consumeitemindex)) {
         Integer var17 = var1.consumeitemindex;
         if (var17 != null) {
            var2.writeInt32(7, var17);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.consumeitemcount)) {
         Integer var18 = var1.consumeitemcount;
         if (var18 != null) {
            var2.writeInt32(8, var18);
         }
      }

   }

   public void writeTo(RES_ADVENTURE_UNION_SUBDUE_START var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_ADVENTURE_UNION_SUBDUE_START readFrom(CodedInputStream var1) throws IOException {
      RES_ADVENTURE_UNION_SUBDUE_START var2 = new RES_ADVENTURE_UNION_SUBDUE_START();
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
               var2.error = var1.readInt32();
            } else if (var5 == 16) {
               var2.index = var1.readInt32();
            } else if (var5 == 24) {
               var2.count = var1.readInt32();
            } else if (var5 == 32) {
               if (var2.guids == null) {
                  var2.guids = new ArrayList();
               }

               var2.guids.add(var1.readUInt64());
            } else if (var5 != 34) {
               if (var5 == 40) {
                  var2.consumefatigue = var1.readInt32();
               } else if (var5 == 48) {
                  var2.consumeticket = var1.readInt32();
               } else if (var5 == 56) {
                  var2.consumeitemindex = var1.readInt32();
               } else if (var5 == 64) {
                  var2.consumeitemcount = var1.readInt32();
               } else {
                  var1.skipField(var5);
               }
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_ADVENTURE_UNION_SUBDUE_START.class);
         return this.descriptor = var1;
      }
   }
}
