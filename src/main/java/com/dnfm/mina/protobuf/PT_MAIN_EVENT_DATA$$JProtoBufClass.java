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

public class PT_MAIN_EVENT_DATA$$JProtoBufClass implements Codec<PT_MAIN_EVENT_DATA>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_MAIN_EVENT_DATA var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_MAIN_EVENT_DATA decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_MAIN_EVENT_DATA var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var10 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(1, var10);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.status)) {
         Integer var11 = var1.status;
         var2 += CodedOutputStream.computeInt32Size(2, var11);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var12 = var1.count;
         var2 += CodedOutputStream.computeInt32Size(3, var12);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.value)) {
         Integer var13 = var1.value;
         var2 += CodedOutputStream.computeInt32Size(4, var13);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.dateStart)) {
         Long var14 = var1.dateStart;
         var2 += CodedOutputStream.computeInt64Size(5, var14);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.dateEnd)) {
         Long var15 = var1.dateEnd;
         var2 += CodedOutputStream.computeInt64Size(6, var15);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.sub)) {
         List var16 = var1.sub;
         var2 += CodedConstant.computeListSize(7, var16, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(PT_MAIN_EVENT_DATA var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var10 = var1.index;
         if (var10 != null) {
            var2.writeInt32(1, var10);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.status)) {
         Integer var11 = var1.status;
         if (var11 != null) {
            var2.writeInt32(2, var11);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var12 = var1.count;
         if (var12 != null) {
            var2.writeInt32(3, var12);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.value)) {
         Integer var13 = var1.value;
         if (var13 != null) {
            var2.writeInt32(4, var13);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.dateStart)) {
         Long var14 = var1.dateStart;
         if (var14 != null) {
            var2.writeInt64(5, var14);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.dateEnd)) {
         Long var15 = var1.dateEnd;
         if (var15 != null) {
            var2.writeInt64(6, var15);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.sub)) {
         List var16 = var1.sub;
         if (var16 != null) {
            CodedConstant.writeToList(var2, 7, FieldType.OBJECT, var16, false);
         }
      }

   }

   public void writeTo(PT_MAIN_EVENT_DATA var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_MAIN_EVENT_DATA readFrom(CodedInputStream var1) throws IOException {
      PT_MAIN_EVENT_DATA var2 = new PT_MAIN_EVENT_DATA();
      var2.sub = new ArrayList();

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
               var2.status = var1.readInt32();
            } else if (var5 == 24) {
               var2.count = var1.readInt32();
            } else if (var5 == 32) {
               var2.value = var1.readInt32();
            } else if (var5 == 40) {
               var2.dateStart = var1.readInt64();
            } else if (var5 == 48) {
               var2.dateEnd = var1.readInt64();
            } else if (var5 == 58) {
               Codec var10 = ProtobufProxy.create(PT_EVENT_DATA.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.sub == null) {
                  var2.sub = new ArrayList();
               }

               var2.sub.add((PT_EVENT_DATA)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_MAIN_EVENT_DATA.class);
         return this.descriptor = var1;
      }
   }
}
