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

public class NOTIFY_PVP_CHARACTER_SELECT$$JProtoBufClass implements Codec<NOTIFY_PVP_CHARACTER_SELECT>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(NOTIFY_PVP_CHARACTER_SELECT var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public NOTIFY_PVP_CHARACTER_SELECT decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(NOTIFY_PVP_CHARACTER_SELECT var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var7 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var7);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var8 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(2, var8);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.pvpcharindex)) {
         Integer var9 = var1.pvpcharindex;
         var2 += CodedOutputStream.computeInt32Size(3, var9);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.ready)) {
         Boolean var10 = var1.ready;
         var2 += CodedOutputStream.computeBoolSize(4, var10);
      }

      return var2;
   }

   public void doWriteTo(NOTIFY_PVP_CHARACTER_SELECT var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var7 = var1.error;
         if (var7 != null) {
            var2.writeInt32(1, var7);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var8 = var1.charguid;
         if (var8 != null) {
            var2.writeUInt64(2, var8);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.pvpcharindex)) {
         Integer var9 = var1.pvpcharindex;
         if (var9 != null) {
            var2.writeInt32(3, var9);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.ready)) {
         Boolean var10 = var1.ready;
         if (var10 != null) {
            var2.writeBool(4, var10);
         }
      }

   }

   public void writeTo(NOTIFY_PVP_CHARACTER_SELECT var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public NOTIFY_PVP_CHARACTER_SELECT readFrom(CodedInputStream var1) throws IOException {
      NOTIFY_PVP_CHARACTER_SELECT var2 = new NOTIFY_PVP_CHARACTER_SELECT();

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
               var2.charguid = var1.readUInt64();
            } else if (var5 == 24) {
               var2.pvpcharindex = var1.readInt32();
            } else if (var5 == 32) {
               var2.ready = var1.readBool();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(NOTIFY_PVP_CHARACTER_SELECT.class);
         return this.descriptor = var1;
      }
   }
}
