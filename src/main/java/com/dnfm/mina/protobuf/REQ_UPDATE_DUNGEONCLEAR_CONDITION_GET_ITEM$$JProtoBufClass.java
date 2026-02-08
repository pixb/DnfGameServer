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

public class REQ_UPDATE_DUNGEONCLEAR_CONDITION_GET_ITEM$$JProtoBufClass implements Codec<REQ_UPDATE_DUNGEONCLEAR_CONDITION_GET_ITEM>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_UPDATE_DUNGEONCLEAR_CONDITION_GET_ITEM var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_UPDATE_DUNGEONCLEAR_CONDITION_GET_ITEM decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_UPDATE_DUNGEONCLEAR_CONDITION_GET_ITEM var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.items)) {
         List var7 = var1.items;
         var2 += CodedConstant.computeListSize(1, var7, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var8 = var1.dungeonguid;
         var2 += CodedOutputStream.computeUInt64Size(2, var8);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.frame)) {
         Integer var9 = var1.frame;
         var2 += CodedOutputStream.computeInt32Size(3, var9);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.targetguid)) {
         Long var10 = var1.targetguid;
         var2 += CodedOutputStream.computeUInt64Size(4, var10);
      }

      return var2;
   }

   public void doWriteTo(REQ_UPDATE_DUNGEONCLEAR_CONDITION_GET_ITEM var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.items)) {
         List var7 = var1.items;
         if (var7 != null) {
            CodedConstant.writeToList(var2, 1, FieldType.OBJECT, var7, false);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var8 = var1.dungeonguid;
         if (var8 != null) {
            var2.writeUInt64(2, var8);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.frame)) {
         Integer var9 = var1.frame;
         if (var9 != null) {
            var2.writeInt32(3, var9);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.targetguid)) {
         Long var10 = var1.targetguid;
         if (var10 != null) {
            var2.writeUInt64(4, var10);
         }
      }

   }

   public void writeTo(REQ_UPDATE_DUNGEONCLEAR_CONDITION_GET_ITEM var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_UPDATE_DUNGEONCLEAR_CONDITION_GET_ITEM readFrom(CodedInputStream var1) throws IOException {
      REQ_UPDATE_DUNGEONCLEAR_CONDITION_GET_ITEM var2 = new REQ_UPDATE_DUNGEONCLEAR_CONDITION_GET_ITEM();
      var2.items = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 10) {
               Codec var10 = ProtobufProxy.create(PT_LOOTS.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.items == null) {
                  var2.items = new ArrayList();
               }

               var2.items.add((PT_LOOTS)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 16) {
               var2.dungeonguid = var1.readUInt64();
            } else if (var5 == 24) {
               var2.frame = var1.readInt32();
            } else if (var5 == 32) {
               var2.targetguid = var1.readUInt64();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_UPDATE_DUNGEONCLEAR_CONDITION_GET_ITEM.class);
         return this.descriptor = var1;
      }
   }
}
