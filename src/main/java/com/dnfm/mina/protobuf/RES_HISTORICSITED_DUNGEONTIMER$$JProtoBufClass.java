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

public class RES_HISTORICSITED_DUNGEONTIMER$$JProtoBufClass implements Codec<RES_HISTORICSITED_DUNGEONTIMER>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_HISTORICSITED_DUNGEONTIMER var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_HISTORICSITED_DUNGEONTIMER decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_HISTORICSITED_DUNGEONTIMER var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var8 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var8);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.battlefield)) {
         Integer var9 = var1.battlefield;
         var2 += CodedOutputStream.computeInt32Size(2, var9);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.scramble)) {
         Integer var10 = var1.scramble;
         var2 += CodedOutputStream.computeInt32Size(3, var10);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.slotindex)) {
         Integer var11 = var1.slotindex;
         var2 += CodedOutputStream.computeInt32Size(4, var11);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.endtime)) {
         Long var12 = var1.endtime;
         var2 += CodedOutputStream.computeInt64Size(5, var12);
      }

      return var2;
   }

   public void doWriteTo(RES_HISTORICSITED_DUNGEONTIMER var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var8 = var1.error;
         if (var8 != null) {
            var2.writeInt32(1, var8);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.battlefield)) {
         Integer var9 = var1.battlefield;
         if (var9 != null) {
            var2.writeInt32(2, var9);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.scramble)) {
         Integer var10 = var1.scramble;
         if (var10 != null) {
            var2.writeInt32(3, var10);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.slotindex)) {
         Integer var11 = var1.slotindex;
         if (var11 != null) {
            var2.writeInt32(4, var11);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.endtime)) {
         Long var12 = var1.endtime;
         if (var12 != null) {
            var2.writeInt64(5, var12);
         }
      }

   }

   public void writeTo(RES_HISTORICSITED_DUNGEONTIMER var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_HISTORICSITED_DUNGEONTIMER readFrom(CodedInputStream var1) throws IOException {
      RES_HISTORICSITED_DUNGEONTIMER var2 = new RES_HISTORICSITED_DUNGEONTIMER();

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
            } else if (var5 == 16) {
               var2.battlefield = var1.readInt32();
            } else if (var5 == 24) {
               var2.scramble = var1.readInt32();
            } else if (var5 == 32) {
               var2.slotindex = var1.readInt32();
            } else if (var5 == 40) {
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_HISTORICSITED_DUNGEONTIMER.class);
         return this.descriptor = var1;
      }
   }
}
