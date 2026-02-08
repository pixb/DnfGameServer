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

public class PT_CREATURE_ERRAND$$JProtoBufClass implements Codec<PT_CREATURE_ERRAND>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_CREATURE_ERRAND var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_CREATURE_ERRAND decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_CREATURE_ERRAND var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var8 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(1, var8);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.todayinfo)) {
         PT_ERRAND_TODAY var9 = var1.todayinfo;
         var2 += CodedConstant.computeSize(2, var9, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.clearlist)) {
         List var10 = var1.clearlist;
         var2 += CodedConstant.computeListSize(3, var10, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.slotlist)) {
         List var11 = var1.slotlist;
         var2 += CodedConstant.computeListSize(4, var11, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.lastresettime)) {
         Long var12 = var1.lastresettime;
         var2 += CodedOutputStream.computeUInt64Size(5, var12);
      }

      return var2;
   }

   public void doWriteTo(PT_CREATURE_ERRAND var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var8 = var1.level;
         if (var8 != null) {
            var2.writeInt32(1, var8);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.todayinfo)) {
         PT_ERRAND_TODAY var9 = var1.todayinfo;
         if (var9 != null) {
            CodedConstant.writeObject(var2, 2, FieldType.OBJECT, var9, false);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.clearlist)) {
         List var10 = var1.clearlist;
         if (var10 != null) {
            CodedConstant.writeToList(var2, 3, FieldType.OBJECT, var10, false);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.slotlist)) {
         List var11 = var1.slotlist;
         if (var11 != null) {
            CodedConstant.writeToList(var2, 4, FieldType.OBJECT, var11, false);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.lastresettime)) {
         Long var12 = var1.lastresettime;
         if (var12 != null) {
            var2.writeUInt64(5, var12);
         }
      }

   }

   public void writeTo(PT_CREATURE_ERRAND var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_CREATURE_ERRAND readFrom(CodedInputStream var1) throws IOException {
      PT_CREATURE_ERRAND var2 = new PT_CREATURE_ERRAND();
      var2.clearlist = new ArrayList();
      var2.slotlist = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.level = var1.readInt32();
            } else if (var5 == 18) {
               Codec var10 = ProtobufProxy.create(PT_ERRAND_TODAY.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.todayinfo = (PT_ERRAND_TODAY)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 26) {
               Codec var11 = ProtobufProxy.create(PT_ERRAND_CLEAR.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var13 = var1.readRawVarint32();
               int var15 = var1.pushLimit(var13);
               if (var2.clearlist == null) {
                  var2.clearlist = new ArrayList();
               }

               var2.clearlist.add((PT_ERRAND_CLEAR)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var15);
            } else if (var5 == 34) {
               Codec var12 = ProtobufProxy.create(PT_ERRAND_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var14 = var1.readRawVarint32();
               int var16 = var1.pushLimit(var14);
               if (var2.slotlist == null) {
                  var2.slotlist = new ArrayList();
               }

               var2.slotlist.add((PT_ERRAND_INFO)var12.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var16);
            } else if (var5 == 40) {
               var2.lastresettime = var1.readUInt64();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_CREATURE_ERRAND.class);
         return this.descriptor = var1;
      }
   }
}
