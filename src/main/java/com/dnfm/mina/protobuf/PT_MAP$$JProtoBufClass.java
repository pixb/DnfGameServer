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

public class PT_MAP$$JProtoBufClass implements Codec<PT_MAP>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_MAP var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_MAP decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_MAP var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var11 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(1, var11);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Integer var12 = var1.guid;
         var2 += CodedOutputStream.computeInt32Size(2, var12);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.clearstate)) {
         Boolean var13 = var1.clearstate;
         var2 += CodedOutputStream.computeBoolSize(3, var13);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.bossmap)) {
         Boolean var14 = var1.bossmap;
         var2 += CodedOutputStream.computeBoolSize(4, var14);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.floor)) {
         Integer var15 = var1.floor;
         var2 += CodedOutputStream.computeInt32Size(5, var15);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.lootreward)) {
         Boolean var16 = var1.lootreward;
         var2 += CodedOutputStream.computeBoolSize(6, var16);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.leave)) {
         Boolean var17 = var1.leave;
         var2 += CodedOutputStream.computeBoolSize(7, var17);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.objects)) {
         List var18 = var1.objects;
         var2 += CodedConstant.computeListSize(8, var18, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(PT_MAP var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var11 = var1.index;
         if (var11 != null) {
            var2.writeInt32(1, var11);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Integer var12 = var1.guid;
         if (var12 != null) {
            var2.writeInt32(2, var12);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.clearstate)) {
         Boolean var13 = var1.clearstate;
         if (var13 != null) {
            var2.writeBool(3, var13);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.bossmap)) {
         Boolean var14 = var1.bossmap;
         if (var14 != null) {
            var2.writeBool(4, var14);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.floor)) {
         Integer var15 = var1.floor;
         if (var15 != null) {
            var2.writeInt32(5, var15);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.lootreward)) {
         Boolean var16 = var1.lootreward;
         if (var16 != null) {
            var2.writeBool(6, var16);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.leave)) {
         Boolean var17 = var1.leave;
         if (var17 != null) {
            var2.writeBool(7, var17);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.objects)) {
         List var18 = var1.objects;
         if (var18 != null) {
            CodedConstant.writeToList(var2, 8, FieldType.OBJECT, var18, false);
         }
      }

   }

   public void writeTo(PT_MAP var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_MAP readFrom(CodedInputStream var1) throws IOException {
      PT_MAP var2 = new PT_MAP();
      var2.objects = new ArrayList();

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
               var2.guid = var1.readInt32();
            } else if (var5 == 24) {
               var2.clearstate = var1.readBool();
            } else if (var5 == 32) {
               var2.bossmap = var1.readBool();
            } else if (var5 == 40) {
               var2.floor = var1.readInt32();
            } else if (var5 == 48) {
               var2.lootreward = var1.readBool();
            } else if (var5 == 56) {
               var2.leave = var1.readBool();
            } else if (var5 == 66) {
               Codec var10 = ProtobufProxy.create(PT_OBJECT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.objects == null) {
                  var2.objects = new ArrayList();
               }

               var2.objects.add((PT_OBJECT)var10.readFrom(var1));
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_MAP.class);
         return this.descriptor = var1;
      }
   }
}
