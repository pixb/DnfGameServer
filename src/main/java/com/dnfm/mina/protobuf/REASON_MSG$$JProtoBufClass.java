package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.code.CodedConstant;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.InvalidProtocolBufferException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class REASON_MSG$$JProtoBufClass implements Codec<REASON_MSG>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REASON_MSG var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REASON_MSG decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REASON_MSG var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.mReason)) {
         Long var5 = var1.mReason;
         var2 += CodedOutputStream.computeInt64Size(1, var5);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.mResaonMsg)) {
         String var6 = var1.mResaonMsg;
         var2 += CodedOutputStream.computeStringSize(2, var6);
      }

      return var2;
   }

   public void doWriteTo(REASON_MSG var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.mReason)) {
         Long var5 = var1.mReason;
         if (var5 != null) {
            var2.writeInt64(1, var5);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.mResaonMsg)) {
         String var6 = var1.mResaonMsg;
         if (var6 != null) {
            var2.writeString(2, var6);
         }
      }

   }

   public void writeTo(REASON_MSG var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REASON_MSG readFrom(CodedInputStream var1) throws IOException {
      REASON_MSG var2 = new REASON_MSG();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.mReason = var1.readInt64();
            } else if (var5 == 18) {
               var2.mResaonMsg = var1.readString();
            } else {
               var1.skipField(var5);
            }
         }

         return var2;
      } catch (InvalidProtocolBufferException var6) {
         throw var6;
      } catch (IOException var7) {
         throw var7;
      }
   }

   public Descriptors.Descriptor getDescriptor() throws IOException {
      if (this.descriptor != null) {
         return this.descriptor;
      } else {
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REASON_MSG.class);
         return this.descriptor = var1;
      }
   }
}
