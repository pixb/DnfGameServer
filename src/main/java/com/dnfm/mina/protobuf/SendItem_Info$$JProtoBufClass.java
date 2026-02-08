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

public class SendItem_Info$$JProtoBufClass implements Codec<SendItem_Info>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(SendItem_Info var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public SendItem_Info decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(SendItem_Info var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var6 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(1, var6);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var7 = var1.count;
         var2 += CodedOutputStream.computeInt32Size(2, var7);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.bind)) {
         Boolean var8 = var1.bind;
         var2 += CodedOutputStream.computeBoolSize(3, var8);
      }

      return var2;
   }

   public void doWriteTo(SendItem_Info var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var6 = var1.index;
         if (var6 != null) {
            var2.writeInt32(1, var6);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var7 = var1.count;
         if (var7 != null) {
            var2.writeInt32(2, var7);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.bind)) {
         Boolean var8 = var1.bind;
         if (var8 != null) {
            var2.writeBool(3, var8);
         }
      }

   }

   public void writeTo(SendItem_Info var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public SendItem_Info readFrom(CodedInputStream var1) throws IOException {
      SendItem_Info var2 = new SendItem_Info();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.index = var1.readInt32();
            } else if (var5 == 16) {
               var2.count = var1.readInt32();
            } else if (var5 == 24) {
               var2.bind = var1.readBool();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(SendItem_Info.class);
         return this.descriptor = var1;
      }
   }
}
