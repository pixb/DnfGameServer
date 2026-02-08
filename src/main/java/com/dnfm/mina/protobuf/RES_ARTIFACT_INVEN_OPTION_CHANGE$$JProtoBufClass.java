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

public class RES_ARTIFACT_INVEN_OPTION_CHANGE$$JProtoBufClass implements Codec<RES_ARTIFACT_INVEN_OPTION_CHANGE>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_ARTIFACT_INVEN_OPTION_CHANGE var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_ARTIFACT_INVEN_OPTION_CHANGE decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_ARTIFACT_INVEN_OPTION_CHANGE var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var7 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var7);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.boption)) {
         List var8 = var1.boption;
         var2 += CodedConstant.computeListSize(2, var8, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.removeitems)) {
         PT_REMOVEITEMS var9 = var1.removeitems;
         var2 += CodedConstant.computeSize(3, var9, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.currency)) {
         List var10 = var1.currency;
         var2 += CodedConstant.computeListSize(4, var10, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(RES_ARTIFACT_INVEN_OPTION_CHANGE var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var7 = var1.error;
         if (var7 != null) {
            var2.writeInt32(1, var7);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.boption)) {
         List var8 = var1.boption;
         if (var8 != null) {
            CodedConstant.writeToList(var2, 2, FieldType.OBJECT, var8, false);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.removeitems)) {
         PT_REMOVEITEMS var9 = var1.removeitems;
         if (var9 != null) {
            CodedConstant.writeObject(var2, 3, FieldType.OBJECT, var9, false);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.currency)) {
         List var10 = var1.currency;
         if (var10 != null) {
            CodedConstant.writeToList(var2, 4, FieldType.OBJECT, var10, false);
         }
      }

   }

   public void writeTo(RES_ARTIFACT_INVEN_OPTION_CHANGE var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_ARTIFACT_INVEN_OPTION_CHANGE readFrom(CodedInputStream var1) throws IOException {
      RES_ARTIFACT_INVEN_OPTION_CHANGE var2 = new RES_ARTIFACT_INVEN_OPTION_CHANGE();
      var2.boption = new ArrayList();
      var2.currency = new ArrayList();

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
            } else if (var5 == 18) {
               Codec var10 = ProtobufProxy.create(PT_ARTIFACT_BASE_OPTION.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.boption == null) {
                  var2.boption = new ArrayList();
               }

               var2.boption.add((PT_ARTIFACT_BASE_OPTION)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 26) {
               Codec var11 = ProtobufProxy.create(PT_REMOVEITEMS.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var13 = var1.readRawVarint32();
               int var15 = var1.pushLimit(var13);
               var2.removeitems = (PT_REMOVEITEMS)var11.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var15);
            } else if (var5 == 34) {
               Codec var12 = ProtobufProxy.create(PT_MONEY_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var14 = var1.readRawVarint32();
               int var16 = var1.pushLimit(var14);
               if (var2.currency == null) {
                  var2.currency = new ArrayList();
               }

               var2.currency.add((PT_MONEY_ITEM)var12.readFrom(var1));
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_ARTIFACT_INVEN_OPTION_CHANGE.class);
         return this.descriptor = var1;
      }
   }
}
