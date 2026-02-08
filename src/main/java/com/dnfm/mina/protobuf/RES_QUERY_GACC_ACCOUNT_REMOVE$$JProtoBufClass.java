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

public class RES_QUERY_GACC_ACCOUNT_REMOVE$$JProtoBufClass implements Codec<RES_QUERY_GACC_ACCOUNT_REMOVE>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_QUERY_GACC_ACCOUNT_REMOVE var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_QUERY_GACC_ACCOUNT_REMOVE decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_QUERY_GACC_ACCOUNT_REMOVE var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.result)) {
         Boolean var5 = var1.result;
         var2 += CodedOutputStream.computeBoolSize(1, var5);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.characters)) {
         List var6 = var1.characters;
         var2 += CodedConstant.computeListSize(2, var6, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(RES_QUERY_GACC_ACCOUNT_REMOVE var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.result)) {
         Boolean var5 = var1.result;
         if (var5 != null) {
            var2.writeBool(1, var5);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.characters)) {
         List var6 = var1.characters;
         if (var6 != null) {
            CodedConstant.writeToList(var2, 2, FieldType.OBJECT, var6, false);
         }
      }

   }

   public void writeTo(RES_QUERY_GACC_ACCOUNT_REMOVE var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_QUERY_GACC_ACCOUNT_REMOVE readFrom(CodedInputStream var1) throws IOException {
      RES_QUERY_GACC_ACCOUNT_REMOVE var2 = new RES_QUERY_GACC_ACCOUNT_REMOVE();
      var2.characters = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.result = var1.readBool();
            } else if (var5 == 18) {
               Codec var10 = ProtobufProxy.create(PT_GACC_REMOVE_RESULT_CHARACTER.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.characters == null) {
                  var2.characters = new ArrayList();
               }

               var2.characters.add((PT_GACC_REMOVE_RESULT_CHARACTER)var10.readFrom(var1));
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_QUERY_GACC_ACCOUNT_REMOVE.class);
         return this.descriptor = var1;
      }
   }
}
