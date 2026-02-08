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

public class PT_SUBDUE_INFO$$JProtoBufClass implements Codec<PT_SUBDUE_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_SUBDUE_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_SUBDUE_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_SUBDUE_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var10 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(1, var10);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.charguids)) {
         List var11 = var1.charguids;
         var2 += CodedConstant.computeListSize(2, var11, FieldType.UINT64, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.starttime)) {
         Long var12 = var1.starttime;
         var2 += CodedOutputStream.computeInt64Size(3, var12);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.endtime)) {
         Long var13 = var1.endtime;
         var2 += CodedOutputStream.computeInt64Size(4, var13);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.state)) {
         Integer var14 = var1.state;
         var2 += CodedOutputStream.computeInt32Size(5, var14);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var15 = var1.count;
         var2 += CodedOutputStream.computeInt32Size(6, var15);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.totalantievil)) {
         Integer var16 = var1.totalantievil;
         var2 += CodedOutputStream.computeInt32Size(7, var16);
      }

      return var2;
   }

   public void doWriteTo(PT_SUBDUE_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var10 = var1.index;
         if (var10 != null) {
            var2.writeInt32(1, var10);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.charguids)) {
         List var11 = var1.charguids;
         if (var11 != null) {
            CodedConstant.writeToList(var2, 2, FieldType.UINT64, var11, true);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.starttime)) {
         Long var12 = var1.starttime;
         if (var12 != null) {
            var2.writeInt64(3, var12);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.endtime)) {
         Long var13 = var1.endtime;
         if (var13 != null) {
            var2.writeInt64(4, var13);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.state)) {
         Integer var14 = var1.state;
         if (var14 != null) {
            var2.writeInt32(5, var14);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var15 = var1.count;
         if (var15 != null) {
            var2.writeInt32(6, var15);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.totalantievil)) {
         Integer var16 = var1.totalantievil;
         if (var16 != null) {
            var2.writeInt32(7, var16);
         }
      }

   }

   public void writeTo(PT_SUBDUE_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_SUBDUE_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_SUBDUE_INFO var2 = new PT_SUBDUE_INFO();
      var2.charguids = new ArrayList();

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
               if (var2.charguids == null) {
                  var2.charguids = new ArrayList();
               }

               var2.charguids.add(var1.readUInt64());
            } else if (var5 != 18) {
               if (var5 == 24) {
                  var2.starttime = var1.readInt64();
               } else if (var5 == 32) {
                  var2.endtime = var1.readInt64();
               } else if (var5 == 40) {
                  var2.state = var1.readInt32();
               } else if (var5 == 48) {
                  var2.count = var1.readInt32();
               } else if (var5 == 56) {
                  var2.totalantievil = var1.readInt32();
               } else {
                  var1.skipField(var5);
               }
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.charguids == null) {
                  var2.charguids = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.charguids.add(var1.readUInt64());
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_SUBDUE_INFO.class);
         return this.descriptor = var1;
      }
   }
}
