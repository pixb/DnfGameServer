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

public class PT_STACKABLE$$JProtoBufClass implements Codec<PT_STACKABLE>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_STACKABLE var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_STACKABLE decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_STACKABLE var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var12 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(1, var12);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var13 = var1.count;
         var2 += CodedOutputStream.computeInt32Size(2, var13);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.bind)) {
         Boolean var14 = var1.bind;
         var2 += CodedOutputStream.computeBoolSize(3, var14);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.scount)) {
         Integer var15 = var1.scount;
         var2 += CodedOutputStream.computeInt32Size(4, var15);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.tcount)) {
         Integer var16 = var1.tcount;
         var2 += CodedOutputStream.computeInt32Size(5, var16);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.expiretime)) {
         Long var17 = var1.expiretime;
         var2 += CodedOutputStream.computeInt64Size(6, var17);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.acquisitiontime)) {
         Long var18 = var1.acquisitiontime;
         var2 += CodedOutputStream.computeInt64Size(7, var18);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.sindex)) {
         Integer var19 = var1.sindex;
         var2 += CodedOutputStream.computeInt32Size(8, var19);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.slotindex)) {
         Integer var20 = var1.slotindex;
         var2 += CodedOutputStream.computeInt32Size(9, var20);
      }

      return var2;
   }

   public void doWriteTo(PT_STACKABLE var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var12 = var1.index;
         if (var12 != null) {
            var2.writeInt32(1, var12);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var13 = var1.count;
         if (var13 != null) {
            var2.writeInt32(2, var13);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.bind)) {
         Boolean var14 = var1.bind;
         if (var14 != null) {
            var2.writeBool(3, var14);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.scount)) {
         Integer var15 = var1.scount;
         if (var15 != null) {
            var2.writeInt32(4, var15);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.tcount)) {
         Integer var16 = var1.tcount;
         if (var16 != null) {
            var2.writeInt32(5, var16);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.expiretime)) {
         Long var17 = var1.expiretime;
         if (var17 != null) {
            var2.writeInt64(6, var17);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.acquisitiontime)) {
         Long var18 = var1.acquisitiontime;
         if (var18 != null) {
            var2.writeInt64(7, var18);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.sindex)) {
         Integer var19 = var1.sindex;
         if (var19 != null) {
            var2.writeInt32(8, var19);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.slotindex)) {
         Integer var20 = var1.slotindex;
         if (var20 != null) {
            var2.writeInt32(9, var20);
         }
      }

   }

   public void writeTo(PT_STACKABLE var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_STACKABLE readFrom(CodedInputStream var1) throws IOException {
      PT_STACKABLE var2 = new PT_STACKABLE();

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
            } else if (var5 == 32) {
               var2.scount = var1.readInt32();
            } else if (var5 == 40) {
               var2.tcount = var1.readInt32();
            } else if (var5 == 48) {
               var2.expiretime = var1.readInt64();
            } else if (var5 == 56) {
               var2.acquisitiontime = var1.readInt64();
            } else if (var5 == 64) {
               var2.sindex = var1.readInt32();
            } else if (var5 == 72) {
               var2.slotindex = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_STACKABLE.class);
         return this.descriptor = var1;
      }
   }
}
