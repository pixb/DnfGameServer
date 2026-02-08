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

public class PT_CUSTOM_DATA$$JProtoBufClass implements Codec<PT_CUSTOM_DATA>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_CUSTOM_DATA var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_CUSTOM_DATA decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_CUSTOM_DATA var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var7 = var1.type;
         var2 += CodedOutputStream.computeUInt32Size(1, var7);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.senderguid)) {
         Long var8 = var1.senderguid;
         var2 += CodedOutputStream.computeUInt64Size(2, var8);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.iValue)) {
         Integer var9 = var1.iValue;
         var2 += CodedOutputStream.computeInt32Size(3, var9);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.fValue)) {
         Float var10 = var1.fValue;
         var2 += CodedOutputStream.computeFloatSize(4, var10);
      }

      return var2;
   }

   public void doWriteTo(PT_CUSTOM_DATA var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var7 = var1.type;
         if (var7 != null) {
            var2.writeUInt32(1, var7);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.senderguid)) {
         Long var8 = var1.senderguid;
         if (var8 != null) {
            var2.writeUInt64(2, var8);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.iValue)) {
         Integer var9 = var1.iValue;
         if (var9 != null) {
            var2.writeInt32(3, var9);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.fValue)) {
         Float var10 = var1.fValue;
         if (var10 != null) {
            var2.writeFloat(4, var10);
         }
      }

   }

   public void writeTo(PT_CUSTOM_DATA var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_CUSTOM_DATA readFrom(CodedInputStream var1) throws IOException {
      PT_CUSTOM_DATA var2 = new PT_CUSTOM_DATA();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.type = var1.readUInt32();
            } else if (var5 == 16) {
               var2.senderguid = var1.readUInt64();
            } else if (var5 == 24) {
               var2.iValue = var1.readInt32();
            } else if (var5 == 37) {
               var2.fValue = var1.readFloat();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_CUSTOM_DATA.class);
         return this.descriptor = var1;
      }
   }
}
