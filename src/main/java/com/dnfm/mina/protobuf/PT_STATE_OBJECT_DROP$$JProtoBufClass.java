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

public class PT_STATE_OBJECT_DROP$$JProtoBufClass implements Codec<PT_STATE_OBJECT_DROP>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_STATE_OBJECT_DROP var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_STATE_OBJECT_DROP decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_STATE_OBJECT_DROP var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.golds)) {
         List var6 = var1.golds;
         var2 += CodedConstant.computeListSize(1, var6, FieldType.INT32, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.items)) {
         List var7 = var1.items;
         var2 += CodedConstant.computeListSize(2, var7, FieldType.INT32, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var8 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(3, var8);
      }

      return var2;
   }

   public void doWriteTo(PT_STATE_OBJECT_DROP var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.golds)) {
         List var6 = var1.golds;
         if (var6 != null) {
            CodedConstant.writeToList(var2, 1, FieldType.INT32, var6, true);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.items)) {
         List var7 = var1.items;
         if (var7 != null) {
            CodedConstant.writeToList(var2, 2, FieldType.INT32, var7, true);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var8 = var1.charguid;
         if (var8 != null) {
            var2.writeUInt64(3, var8);
         }
      }

   }

   public void writeTo(PT_STATE_OBJECT_DROP var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_STATE_OBJECT_DROP readFrom(CodedInputStream var1) throws IOException {
      PT_STATE_OBJECT_DROP var2 = new PT_STATE_OBJECT_DROP();
      var2.golds = new ArrayList();
      var2.items = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               if (var2.golds == null) {
                  var2.golds = new ArrayList();
               }

               var2.golds.add(var1.readInt32());
            } else if (var5 == 10) {
               int var11 = var1.readRawVarint32();
               int var13 = var1.pushLimit(var11);
               if (var2.golds == null) {
                  var2.golds = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.golds.add(var1.readInt32());
               }

               var1.popLimit(var13);
            } else if (var5 == 16) {
               if (var2.items == null) {
                  var2.items = new ArrayList();
               }

               var2.items.add(var1.readInt32());
            } else if (var5 == 10) {
               int var10 = var1.readRawVarint32();
               int var12 = var1.pushLimit(var10);
               if (var2.golds == null) {
                  var2.golds = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.golds.add(var1.readInt32());
               }

               var1.popLimit(var12);
            } else if (var5 != 18) {
               if (var5 == 24) {
                  var2.charguid = var1.readUInt64();
               } else {
                  var1.skipField(var5);
               }
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.items == null) {
                  var2.items = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.items.add(var1.readInt32());
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_STATE_OBJECT_DROP.class);
         return this.descriptor = var1;
      }
   }
}
