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

public class NOTIFY_HISTORICSITE_BUY_RELIC$$JProtoBufClass implements Codec<NOTIFY_HISTORICSITE_BUY_RELIC>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(NOTIFY_HISTORICSITE_BUY_RELIC var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public NOTIFY_HISTORICSITE_BUY_RELIC decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(NOTIFY_HISTORICSITE_BUY_RELIC var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var6 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var6);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.charname)) {
         String var7 = var1.charname;
         var2 += CodedOutputStream.computeStringSize(2, var7);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.relicindex)) {
         Integer var8 = var1.relicindex;
         var2 += CodedOutputStream.computeInt32Size(3, var8);
      }

      return var2;
   }

   public void doWriteTo(NOTIFY_HISTORICSITE_BUY_RELIC var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var6 = var1.error;
         if (var6 != null) {
            var2.writeInt32(1, var6);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.charname)) {
         String var7 = var1.charname;
         if (var7 != null) {
            var2.writeString(2, var7);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.relicindex)) {
         Integer var8 = var1.relicindex;
         if (var8 != null) {
            var2.writeInt32(3, var8);
         }
      }

   }

   public void writeTo(NOTIFY_HISTORICSITE_BUY_RELIC var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public NOTIFY_HISTORICSITE_BUY_RELIC readFrom(CodedInputStream var1) throws IOException {
      NOTIFY_HISTORICSITE_BUY_RELIC var2 = new NOTIFY_HISTORICSITE_BUY_RELIC();

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
               var2.charname = var1.readString();
            } else if (var5 == 24) {
               var2.relicindex = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(NOTIFY_HISTORICSITE_BUY_RELIC.class);
         return this.descriptor = var1;
      }
   }
}
