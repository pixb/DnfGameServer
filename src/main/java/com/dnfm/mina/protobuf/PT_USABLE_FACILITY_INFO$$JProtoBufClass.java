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

public class PT_USABLE_FACILITY_INFO$$JProtoBufClass implements Codec<PT_USABLE_FACILITY_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_USABLE_FACILITY_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_USABLE_FACILITY_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_USABLE_FACILITY_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.buff)) {
         Integer var5 = var1.buff;
         var2 += CodedOutputStream.computeInt32Size(1, var5);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.endtime)) {
         Long var6 = var1.endtime;
         var2 += CodedOutputStream.computeInt64Size(2, var6);
      }

      return var2;
   }

   public void doWriteTo(PT_USABLE_FACILITY_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.buff)) {
         Integer var5 = var1.buff;
         if (var5 != null) {
            var2.writeInt32(1, var5);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.endtime)) {
         Long var6 = var1.endtime;
         if (var6 != null) {
            var2.writeInt64(2, var6);
         }
      }

   }

   public void writeTo(PT_USABLE_FACILITY_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_USABLE_FACILITY_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_USABLE_FACILITY_INFO var2 = new PT_USABLE_FACILITY_INFO();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.buff = var1.readInt32();
            } else if (var5 == 16) {
               var2.endtime = var1.readInt64();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_USABLE_FACILITY_INFO.class);
         return this.descriptor = var1;
      }
   }
}
