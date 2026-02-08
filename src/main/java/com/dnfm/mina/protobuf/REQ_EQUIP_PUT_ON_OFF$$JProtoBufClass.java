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

public class REQ_EQUIP_PUT_ON_OFF$$JProtoBufClass implements Codec<REQ_EQUIP_PUT_ON_OFF>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_EQUIP_PUT_ON_OFF var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_EQUIP_PUT_ON_OFF decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_EQUIP_PUT_ON_OFF var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.equip)) {
         List var5 = var1.equip;
         var2 += CodedConstant.computeListSize(1, var5, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.autotransferemblem)) {
         Boolean var6 = var1.autotransferemblem;
         var2 += CodedOutputStream.computeBoolSize(2, var6);
      }

      return var2;
   }

   public void doWriteTo(REQ_EQUIP_PUT_ON_OFF var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.equip)) {
         List var5 = var1.equip;
         if (var5 != null) {
            CodedConstant.writeToList(var2, 1, FieldType.OBJECT, var5, false);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.autotransferemblem)) {
         Boolean var6 = var1.autotransferemblem;
         if (var6 != null) {
            var2.writeBool(2, var6);
         }
      }

   }

   public void writeTo(REQ_EQUIP_PUT_ON_OFF var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_EQUIP_PUT_ON_OFF readFrom(CodedInputStream var1) throws IOException {
      REQ_EQUIP_PUT_ON_OFF var2 = new REQ_EQUIP_PUT_ON_OFF();
      var2.equip = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 10) {
               Codec var10 = ProtobufProxy.create(PT_EQUIP_PUT_ON_OFF.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.equip == null) {
                  var2.equip = new ArrayList();
               }

               var2.equip.add((PT_EQUIP_PUT_ON_OFF)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 16) {
               var2.autotransferemblem = var1.readBool();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_EQUIP_PUT_ON_OFF.class);
         return this.descriptor = var1;
      }
   }
}
