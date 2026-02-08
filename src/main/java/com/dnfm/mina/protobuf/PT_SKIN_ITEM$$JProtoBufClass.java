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

public class PT_SKIN_ITEM$$JProtoBufClass implements Codec<PT_SKIN_ITEM>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_SKIN_ITEM var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_SKIN_ITEM decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_SKIN_ITEM var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var8 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(1, var8);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var9 = var1.guid;
         var2 += CodedOutputStream.computeUInt64Size(2, var9);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.expiretime)) {
         Long var10 = var1.expiretime;
         var2 += CodedOutputStream.computeInt64Size(3, var10);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.acquisitionTime)) {
         Long var11 = var1.acquisitionTime;
         var2 += CodedOutputStream.computeInt64Size(4, var11);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.favorite)) {
         Integer var12 = var1.favorite;
         var2 += CodedOutputStream.computeInt32Size(5, var12);
      }

      return var2;
   }

   public void doWriteTo(PT_SKIN_ITEM var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var8 = var1.index;
         if (var8 != null) {
            var2.writeInt32(1, var8);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var9 = var1.guid;
         if (var9 != null) {
            var2.writeUInt64(2, var9);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.expiretime)) {
         Long var10 = var1.expiretime;
         if (var10 != null) {
            var2.writeInt64(3, var10);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.acquisitionTime)) {
         Long var11 = var1.acquisitionTime;
         if (var11 != null) {
            var2.writeInt64(4, var11);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.favorite)) {
         Integer var12 = var1.favorite;
         if (var12 != null) {
            var2.writeInt32(5, var12);
         }
      }

   }

   public void writeTo(PT_SKIN_ITEM var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_SKIN_ITEM readFrom(CodedInputStream var1) throws IOException {
      PT_SKIN_ITEM var2 = new PT_SKIN_ITEM();

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
               var2.guid = var1.readUInt64();
            } else if (var5 == 24) {
               var2.expiretime = var1.readInt64();
            } else if (var5 == 32) {
               var2.acquisitionTime = var1.readInt64();
            } else if (var5 == 40) {
               var2.favorite = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_SKIN_ITEM.class);
         return this.descriptor = var1;
      }
   }
}
