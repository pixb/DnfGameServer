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

public class RES_BLACK_DIAMON_INFO$$JProtoBufClass implements Codec<RES_BLACK_DIAMON_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_BLACK_DIAMON_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_BLACK_DIAMON_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_BLACK_DIAMON_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var12 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var12);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.reward)) {
         Integer var13 = var1.reward;
         var2 += CodedOutputStream.computeInt32Size(2, var13);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.buff)) {
         Integer var14 = var1.buff;
         var2 += CodedOutputStream.computeInt32Size(3, var14);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.payment)) {
         Integer var15 = var1.payment;
         var2 += CodedOutputStream.computeInt32Size(4, var15);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.state)) {
         Integer var16 = var1.state;
         var2 += CodedOutputStream.computeInt32Size(5, var16);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.starttime)) {
         Long var17 = var1.starttime;
         var2 += CodedOutputStream.computeUInt64Size(6, var17);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.endtime)) {
         Long var18 = var1.endtime;
         var2 += CodedOutputStream.computeUInt64Size(7, var18);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.recvtime)) {
         Long var19 = var1.recvtime;
         var2 += CodedOutputStream.computeUInt64Size(8, var19);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.bucketitems)) {
         List var20 = var1.bucketitems;
         var2 += CodedConstant.computeListSize(9, var20, FieldType.INT32, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      return var2;
   }

   public void doWriteTo(RES_BLACK_DIAMON_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var12 = var1.error;
         if (var12 != null) {
            var2.writeInt32(1, var12);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.reward)) {
         Integer var13 = var1.reward;
         if (var13 != null) {
            var2.writeInt32(2, var13);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.buff)) {
         Integer var14 = var1.buff;
         if (var14 != null) {
            var2.writeInt32(3, var14);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.payment)) {
         Integer var15 = var1.payment;
         if (var15 != null) {
            var2.writeInt32(4, var15);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.state)) {
         Integer var16 = var1.state;
         if (var16 != null) {
            var2.writeInt32(5, var16);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.starttime)) {
         Long var17 = var1.starttime;
         if (var17 != null) {
            var2.writeUInt64(6, var17);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.endtime)) {
         Long var18 = var1.endtime;
         if (var18 != null) {
            var2.writeUInt64(7, var18);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.recvtime)) {
         Long var19 = var1.recvtime;
         if (var19 != null) {
            var2.writeUInt64(8, var19);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.bucketitems)) {
         List var20 = var1.bucketitems;
         if (var20 != null) {
            CodedConstant.writeToList(var2, 9, FieldType.INT32, var20, true);
         }
      }

   }

   public void writeTo(RES_BLACK_DIAMON_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_BLACK_DIAMON_INFO readFrom(CodedInputStream var1) throws IOException {
      RES_BLACK_DIAMON_INFO var2 = new RES_BLACK_DIAMON_INFO();
      var2.bucketitems = new ArrayList();

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
               var2.reward = var1.readInt32();
            } else if (var5 == 24) {
               var2.buff = var1.readInt32();
            } else if (var5 == 32) {
               var2.payment = var1.readInt32();
            } else if (var5 == 40) {
               var2.state = var1.readInt32();
            } else if (var5 == 48) {
               var2.starttime = var1.readUInt64();
            } else if (var5 == 56) {
               var2.endtime = var1.readUInt64();
            } else if (var5 == 64) {
               var2.recvtime = var1.readUInt64();
            } else if (var5 == 72) {
               if (var2.bucketitems == null) {
                  var2.bucketitems = new ArrayList();
               }

               var2.bucketitems.add(var1.readInt32());
            } else if (var5 != 74) {
               var1.skipField(var5);
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.bucketitems == null) {
                  var2.bucketitems = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.bucketitems.add(var1.readInt32());
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_BLACK_DIAMON_INFO.class);
         return this.descriptor = var1;
      }
   }
}
