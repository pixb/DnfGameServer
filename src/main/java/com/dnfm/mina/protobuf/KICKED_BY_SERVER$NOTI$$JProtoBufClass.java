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

public class KICKED_BY_SERVER$NOTI$$JProtoBufClass implements Codec<KICKED_BY_SERVER.NOTI>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(KICKED_BY_SERVER.NOTI var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public KICKED_BY_SERVER.NOTI decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(KICKED_BY_SERVER.NOTI var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.mGuid)) {
         String var5 = var1.mGuid;
         var2 += CodedOutputStream.computeStringSize(1, var5);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.mKicked)) {
         REASON_MSG var6 = var1.mKicked;
         var2 += CodedConstant.computeSize(2, var6, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      return var2;
   }

   public void doWriteTo(KICKED_BY_SERVER.NOTI var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.mGuid)) {
         String var5 = var1.mGuid;
         if (var5 != null) {
            var2.writeString(1, var5);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.mKicked)) {
         REASON_MSG var6 = var1.mKicked;
         if (var6 != null) {
            CodedConstant.writeObject(var2, 2, FieldType.OBJECT, var6, false);
         }
      }

   }

   public void writeTo(KICKED_BY_SERVER.NOTI var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public KICKED_BY_SERVER.NOTI readFrom(CodedInputStream var1) throws IOException {
      KICKED_BY_SERVER.NOTI var2 = new KICKED_BY_SERVER.NOTI();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 10) {
               var2.mGuid = var1.readString();
            } else if (var5 == 18) {
               Codec var10 = ProtobufProxy.create(REASON_MSG.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.mKicked = (REASON_MSG)var10.readFrom(var1);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(KICKED_BY_SERVER.NOTI.class);
         return this.descriptor = var1;
      }
   }
}
