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

public class RES_CREATURE_COMPOSE$$JProtoBufClass implements Codec<RES_CREATURE_COMPOSE>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_CREATURE_COMPOSE var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_CREATURE_COMPOSE decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_CREATURE_COMPOSE var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var8 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var8);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.creature)) {
         Long var9 = var1.creature;
         var2 += CodedOutputStream.computeUInt64Size(2, var9);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.guids)) {
         List var10 = var1.guids;
         var2 += CodedConstant.computeListSize(3, var10, FieldType.UINT64, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.exp)) {
         Integer var11 = var1.exp;
         var2 += CodedOutputStream.computeInt32Size(4, var11);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.cartifactitems)) {
         List var12 = var1.cartifactitems;
         var2 += CodedConstant.computeListSize(5, var12, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(RES_CREATURE_COMPOSE var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var8 = var1.error;
         if (var8 != null) {
            var2.writeInt32(1, var8);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.creature)) {
         Long var9 = var1.creature;
         if (var9 != null) {
            var2.writeUInt64(2, var9);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.guids)) {
         List var10 = var1.guids;
         if (var10 != null) {
            CodedConstant.writeToList(var2, 3, FieldType.UINT64, var10, true);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.exp)) {
         Integer var11 = var1.exp;
         if (var11 != null) {
            var2.writeInt32(4, var11);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.cartifactitems)) {
         List var12 = var1.cartifactitems;
         if (var12 != null) {
            CodedConstant.writeToList(var2, 5, FieldType.OBJECT, var12, false);
         }
      }

   }

   public void writeTo(RES_CREATURE_COMPOSE var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_CREATURE_COMPOSE readFrom(CodedInputStream var1) throws IOException {
      RES_CREATURE_COMPOSE var2 = new RES_CREATURE_COMPOSE();
      var2.guids = new ArrayList();
      var2.cartifactitems = new ArrayList();

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
               var2.creature = var1.readUInt64();
            } else if (var5 == 24) {
               if (var2.guids == null) {
                  var2.guids = new ArrayList();
               }

               var2.guids.add(var1.readUInt64());
            } else if (var5 == 26) {
               int var12 = var1.readRawVarint32();
               int var14 = var1.pushLimit(var12);
               if (var2.guids == null) {
                  var2.guids = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.guids.add(var1.readUInt64());
               }

               var1.popLimit(var14);
            } else if (var5 == 32) {
               var2.exp = var1.readInt32();
            } else if (var5 != 42) {
               var1.skipField(var5);
            } else if (var5 != 26) {
               Codec var10 = ProtobufProxy.create(PT_ARTIFACT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var11 = var1.readRawVarint32();
               int var13 = var1.pushLimit(var11);
               if (var2.cartifactitems == null) {
                  var2.cartifactitems = new ArrayList();
               }

               var2.cartifactitems.add((PT_ARTIFACT)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var13);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_CREATURE_COMPOSE.class);
         return this.descriptor = var1;
      }
   }
}
