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

public class REQ_ITEM_REPAIR$$JProtoBufClass implements Codec<REQ_ITEM_REPAIR>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_ITEM_REPAIR var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_ITEM_REPAIR decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_ITEM_REPAIR var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.equiplist)) {
         List var6 = var1.equiplist;
         var2 += CodedConstant.computeListSize(1, var6, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.equip)) {
         List var7 = var1.equip;
         var2 += CodedConstant.computeListSize(2, var7, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.quest)) {
         List var8 = var1.quest;
         var2 += CodedConstant.computeListSize(3, var8, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(REQ_ITEM_REPAIR var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.equiplist)) {
         List var6 = var1.equiplist;
         if (var6 != null) {
            CodedConstant.writeToList(var2, 1, FieldType.OBJECT, var6, false);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.equip)) {
         List var7 = var1.equip;
         if (var7 != null) {
            CodedConstant.writeToList(var2, 2, FieldType.OBJECT, var7, false);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.quest)) {
         List var8 = var1.quest;
         if (var8 != null) {
            CodedConstant.writeToList(var2, 3, FieldType.OBJECT, var8, false);
         }
      }

   }

   public void writeTo(REQ_ITEM_REPAIR var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_ITEM_REPAIR readFrom(CodedInputStream var1) throws IOException {
      REQ_ITEM_REPAIR var2 = new REQ_ITEM_REPAIR();
      var2.equiplist = new ArrayList();
      var2.equip = new ArrayList();
      var2.quest = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 10) {
               Codec var10 = ProtobufProxy.create(PT_ITEM_GUID.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.equiplist == null) {
                  var2.equiplist = new ArrayList();
               }

               var2.equiplist.add((PT_ITEM_GUID)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 18) {
               Codec var11 = ProtobufProxy.create(PT_ITEM_GUID.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var13 = var1.readRawVarint32();
               int var15 = var1.pushLimit(var13);
               if (var2.equip == null) {
                  var2.equip = new ArrayList();
               }

               var2.equip.add((PT_ITEM_GUID)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var15);
            } else if (var5 == 26) {
               Codec var12 = ProtobufProxy.create(PT_QUEST.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var14 = var1.readRawVarint32();
               int var16 = var1.pushLimit(var14);
               if (var2.quest == null) {
                  var2.quest = new ArrayList();
               }

               var2.quest.add((PT_QUEST)var12.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var16);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_ITEM_REPAIR.class);
         return this.descriptor = var1;
      }
   }
}
