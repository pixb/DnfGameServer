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

public class RES_ARCADE_PVP_BUY_TICKET$$JProtoBufClass implements Codec<RES_ARCADE_PVP_BUY_TICKET>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_ARCADE_PVP_BUY_TICKET var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_ARCADE_PVP_BUY_TICKET decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_ARCADE_PVP_BUY_TICKET var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var7 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var7);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.total)) {
         Integer var8 = var1.total;
         var2 += CodedOutputStream.computeInt32Size(2, var8);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.purchasecount)) {
         Integer var9 = var1.purchasecount;
         var2 += CodedOutputStream.computeInt32Size(3, var9);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.accountcurrency)) {
         List var10 = var1.accountcurrency;
         var2 += CodedConstant.computeListSize(4, var10, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(RES_ARCADE_PVP_BUY_TICKET var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var7 = var1.error;
         if (var7 != null) {
            var2.writeInt32(1, var7);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.total)) {
         Integer var8 = var1.total;
         if (var8 != null) {
            var2.writeInt32(2, var8);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.purchasecount)) {
         Integer var9 = var1.purchasecount;
         if (var9 != null) {
            var2.writeInt32(3, var9);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.accountcurrency)) {
         List var10 = var1.accountcurrency;
         if (var10 != null) {
            CodedConstant.writeToList(var2, 4, FieldType.OBJECT, var10, false);
         }
      }

   }

   public void writeTo(RES_ARCADE_PVP_BUY_TICKET var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_ARCADE_PVP_BUY_TICKET readFrom(CodedInputStream var1) throws IOException {
      RES_ARCADE_PVP_BUY_TICKET var2 = new RES_ARCADE_PVP_BUY_TICKET();
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
            } else if (var5 == 16) {
               var2.total = var1.readInt32();
            } else if (var5 == 24) {
               var2.purchasecount = var1.readInt32();
            } else if (var5 == 34) {
               Codec var10 = ProtobufProxy.create(PT_ARCADE_PVP_INFO_CURRENCY.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.accountcurrency == null) {
                  var2.accountcurrency = new ArrayList();
               }

               var2.accountcurrency.add((PT_ARCADE_PVP_INFO_CURRENCY)var10.readFrom(var1));
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_ARCADE_PVP_BUY_TICKET.class);
         return this.descriptor = var1;
      }
   }
}
