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

public class RES_ADVENTURE_UNION_EXPEDITION_START$$JProtoBufClass implements Codec<RES_ADVENTURE_UNION_EXPEDITION_START>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_ADVENTURE_UNION_EXPEDITION_START var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_ADVENTURE_UNION_EXPEDITION_START decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_ADVENTURE_UNION_EXPEDITION_START var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var6 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var6);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.expedition)) {
         PT_ADVENTURE_UNION_EXPEDITION var7 = var1.expedition;
         var2 += CodedConstant.computeSize(2, var7, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.accountcurrency)) {
         List var8 = var1.accountcurrency;
         var2 += CodedConstant.computeListSize(3, var8, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(RES_ADVENTURE_UNION_EXPEDITION_START var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var6 = var1.error;
         if (var6 != null) {
            var2.writeInt32(1, var6);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.expedition)) {
         PT_ADVENTURE_UNION_EXPEDITION var7 = var1.expedition;
         if (var7 != null) {
            CodedConstant.writeObject(var2, 2, FieldType.OBJECT, var7, false);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.accountcurrency)) {
         List var8 = var1.accountcurrency;
         if (var8 != null) {
            CodedConstant.writeToList(var2, 3, FieldType.OBJECT, var8, false);
         }
      }

   }

   public void writeTo(RES_ADVENTURE_UNION_EXPEDITION_START var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_ADVENTURE_UNION_EXPEDITION_START readFrom(CodedInputStream var1) throws IOException {
      RES_ADVENTURE_UNION_EXPEDITION_START var2 = new RES_ADVENTURE_UNION_EXPEDITION_START();
      var2.accountcurrency = new ArrayList();

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
               Codec var10 = ProtobufProxy.create(PT_ADVENTURE_UNION_EXPEDITION.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.expedition = (PT_ADVENTURE_UNION_EXPEDITION)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 26) {
               Codec var11 = ProtobufProxy.create(PT_MONEY_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var12 = var1.readRawVarint32();
               int var13 = var1.pushLimit(var12);
               if (var2.accountcurrency == null) {
                  var2.accountcurrency = new ArrayList();
               }

               var2.accountcurrency.add((PT_MONEY_ITEM)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var13);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_ADVENTURE_UNION_EXPEDITION_START.class);
         return this.descriptor = var1;
      }
   }
}
