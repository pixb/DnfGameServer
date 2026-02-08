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

public class RES_VERIFICATION_START$$JProtoBufClass implements Codec<RES_VERIFICATION_START>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_VERIFICATION_START var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_VERIFICATION_START decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_VERIFICATION_START var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var8 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var8);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var9 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(2, var9);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var10 = var1.dungeonguid;
         var2 += CodedOutputStream.computeUInt64Size(3, var10);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.replay)) {
         String var11 = var1.replay;
         var2 += CodedOutputStream.computeStringSize(4, var11);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.dungeon)) {
         String var12 = var1.dungeon;
         var2 += CodedOutputStream.computeStringSize(5, var12);
      }

      return var2;
   }

   public void doWriteTo(RES_VERIFICATION_START var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var8 = var1.error;
         if (var8 != null) {
            var2.writeInt32(1, var8);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var9 = var1.charguid;
         if (var9 != null) {
            var2.writeUInt64(2, var9);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var10 = var1.dungeonguid;
         if (var10 != null) {
            var2.writeUInt64(3, var10);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.replay)) {
         String var11 = var1.replay;
         if (var11 != null) {
            var2.writeString(4, var11);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.dungeon)) {
         String var12 = var1.dungeon;
         if (var12 != null) {
            var2.writeString(5, var12);
         }
      }

   }

   public void writeTo(RES_VERIFICATION_START var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_VERIFICATION_START readFrom(CodedInputStream var1) throws IOException {
      RES_VERIFICATION_START var2 = new RES_VERIFICATION_START();

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
               var2.dungeonguid = var1.readUInt64();
            } else if (var5 == 34) {
               var2.replay = var1.readString();
            } else if (var5 == 42) {
               var2.dungeon = var1.readString();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_VERIFICATION_START.class);
         return this.descriptor = var1;
      }
   }
}
