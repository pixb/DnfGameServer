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

public class PT_USER_LOADING_STATUS$$JProtoBufClass implements Codec<PT_USER_LOADING_STATUS>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_USER_LOADING_STATUS var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_USER_LOADING_STATUS decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_USER_LOADING_STATUS var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var9 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var9);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.ready)) {
         Boolean var10 = var1.ready;
         var2 += CodedOutputStream.computeBoolSize(2, var10);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.waiting)) {
         Boolean var11 = var1.waiting;
         var2 += CodedOutputStream.computeBoolSize(3, var11);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.disconnect)) {
         Boolean var12 = var1.disconnect;
         var2 += CodedOutputStream.computeBoolSize(4, var12);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.leave)) {
         Boolean var13 = var1.leave;
         var2 += CodedOutputStream.computeBoolSize(5, var13);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.kickoutvotes)) {
         List var14 = var1.kickoutvotes;
         var2 += CodedConstant.computeListSize(6, var14, FieldType.UINT64, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      return var2;
   }

   public void doWriteTo(PT_USER_LOADING_STATUS var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var9 = var1.charguid;
         if (var9 != null) {
            var2.writeUInt64(1, var9);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.ready)) {
         Boolean var10 = var1.ready;
         if (var10 != null) {
            var2.writeBool(2, var10);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.waiting)) {
         Boolean var11 = var1.waiting;
         if (var11 != null) {
            var2.writeBool(3, var11);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.disconnect)) {
         Boolean var12 = var1.disconnect;
         if (var12 != null) {
            var2.writeBool(4, var12);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.leave)) {
         Boolean var13 = var1.leave;
         if (var13 != null) {
            var2.writeBool(5, var13);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.kickoutvotes)) {
         List var14 = var1.kickoutvotes;
         if (var14 != null) {
            CodedConstant.writeToList(var2, 6, FieldType.UINT64, var14, true);
         }
      }

   }

   public void writeTo(PT_USER_LOADING_STATUS var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_USER_LOADING_STATUS readFrom(CodedInputStream var1) throws IOException {
      PT_USER_LOADING_STATUS var2 = new PT_USER_LOADING_STATUS();
      var2.kickoutvotes = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.charguid = var1.readUInt64();
            } else if (var5 == 16) {
               var2.ready = var1.readBool();
            } else if (var5 == 24) {
               var2.waiting = var1.readBool();
            } else if (var5 == 32) {
               var2.disconnect = var1.readBool();
            } else if (var5 == 40) {
               var2.leave = var1.readBool();
            } else if (var5 == 48) {
               if (var2.kickoutvotes == null) {
                  var2.kickoutvotes = new ArrayList();
               }

               var2.kickoutvotes.add(var1.readUInt64());
            } else if (var5 != 50) {
               var1.skipField(var5);
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.kickoutvotes == null) {
                  var2.kickoutvotes = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.kickoutvotes.add(var1.readUInt64());
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_USER_LOADING_STATUS.class);
         return this.descriptor = var1;
      }
   }
}
