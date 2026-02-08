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

public class PT_VERIFICATION_ADD_DAMAGE_DATA$$JProtoBufClass implements Codec<PT_VERIFICATION_ADD_DAMAGE_DATA>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_VERIFICATION_ADD_DAMAGE_DATA var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_VERIFICATION_ADD_DAMAGE_DATA decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_VERIFICATION_ADD_DAMAGE_DATA var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.flag)) {
         Integer var8 = var1.flag;
         var2 += CodedOutputStream.computeUInt32Size(1, var8);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.damage)) {
         Integer var9 = var1.damage;
         var2 += CodedOutputStream.computeInt32Size(2, var9);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.itemindex)) {
         Integer var10 = var1.itemindex;
         var2 += CodedOutputStream.computeInt32Size(3, var10);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.ifindex)) {
         Integer var11 = var1.ifindex;
         var2 += CodedOutputStream.computeInt32Size(4, var11);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.thenindex)) {
         Integer var12 = var1.thenindex;
         var2 += CodedOutputStream.computeInt32Size(5, var12);
      }

      return var2;
   }

   public void doWriteTo(PT_VERIFICATION_ADD_DAMAGE_DATA var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.flag)) {
         Integer var8 = var1.flag;
         if (var8 != null) {
            var2.writeUInt32(1, var8);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.damage)) {
         Integer var9 = var1.damage;
         if (var9 != null) {
            var2.writeInt32(2, var9);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.itemindex)) {
         Integer var10 = var1.itemindex;
         if (var10 != null) {
            var2.writeInt32(3, var10);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.ifindex)) {
         Integer var11 = var1.ifindex;
         if (var11 != null) {
            var2.writeInt32(4, var11);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.thenindex)) {
         Integer var12 = var1.thenindex;
         if (var12 != null) {
            var2.writeInt32(5, var12);
         }
      }

   }

   public void writeTo(PT_VERIFICATION_ADD_DAMAGE_DATA var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_VERIFICATION_ADD_DAMAGE_DATA readFrom(CodedInputStream var1) throws IOException {
      PT_VERIFICATION_ADD_DAMAGE_DATA var2 = new PT_VERIFICATION_ADD_DAMAGE_DATA();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.flag = var1.readUInt32();
            } else if (var5 == 16) {
               var2.damage = var1.readInt32();
            } else if (var5 == 24) {
               var2.itemindex = var1.readInt32();
            } else if (var5 == 32) {
               var2.ifindex = var1.readInt32();
            } else if (var5 == 40) {
               var2.thenindex = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_VERIFICATION_ADD_DAMAGE_DATA.class);
         return this.descriptor = var1;
      }
   }
}
