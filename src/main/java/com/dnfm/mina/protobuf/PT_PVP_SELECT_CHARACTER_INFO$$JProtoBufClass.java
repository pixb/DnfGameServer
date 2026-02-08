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

public class PT_PVP_SELECT_CHARACTER_INFO$$JProtoBufClass implements Codec<PT_PVP_SELECT_CHARACTER_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_PVP_SELECT_CHARACTER_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_PVP_SELECT_CHARACTER_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_PVP_SELECT_CHARACTER_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.pvpcharindex)) {
         Integer var8 = var1.pvpcharindex;
         var2 += CodedOutputStream.computeInt32Size(1, var8);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.openstatus)) {
         Integer var9 = var1.openstatus;
         var2 += CodedOutputStream.computeInt32Size(2, var9);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.playcount)) {
         Integer var10 = var1.playcount;
         var2 += CodedOutputStream.computeInt32Size(3, var10);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.exclusivemissioncount)) {
         Integer var11 = var1.exclusivemissioncount;
         var2 += CodedOutputStream.computeInt32Size(4, var11);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.exclusivemissionreward)) {
         Boolean var12 = var1.exclusivemissionreward;
         var2 += CodedOutputStream.computeBoolSize(5, var12);
      }

      return var2;
   }

   public void doWriteTo(PT_PVP_SELECT_CHARACTER_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.pvpcharindex)) {
         Integer var8 = var1.pvpcharindex;
         if (var8 != null) {
            var2.writeInt32(1, var8);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.openstatus)) {
         Integer var9 = var1.openstatus;
         if (var9 != null) {
            var2.writeInt32(2, var9);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.playcount)) {
         Integer var10 = var1.playcount;
         if (var10 != null) {
            var2.writeInt32(3, var10);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.exclusivemissioncount)) {
         Integer var11 = var1.exclusivemissioncount;
         if (var11 != null) {
            var2.writeInt32(4, var11);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.exclusivemissionreward)) {
         Boolean var12 = var1.exclusivemissionreward;
         if (var12 != null) {
            var2.writeBool(5, var12);
         }
      }

   }

   public void writeTo(PT_PVP_SELECT_CHARACTER_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_PVP_SELECT_CHARACTER_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_PVP_SELECT_CHARACTER_INFO var2 = new PT_PVP_SELECT_CHARACTER_INFO();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.pvpcharindex = var1.readInt32();
            } else if (var5 == 16) {
               var2.openstatus = var1.readInt32();
            } else if (var5 == 24) {
               var2.playcount = var1.readInt32();
            } else if (var5 == 32) {
               var2.exclusivemissioncount = var1.readInt32();
            } else if (var5 == 40) {
               var2.exclusivemissionreward = var1.readBool();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_PVP_SELECT_CHARACTER_INFO.class);
         return this.descriptor = var1;
      }
   }
}
