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

public class PT_SYSCMD_PARTY$$JProtoBufClass implements Codec<PT_SYSCMD_PARTY>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_SYSCMD_PARTY var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_SYSCMD_PARTY decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_SYSCMD_PARTY var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.area)) {
         Integer var13 = var1.area;
         var2 += CodedOutputStream.computeInt32Size(1, var13);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.stageindex)) {
         Integer var14 = var1.stageindex;
         var2 += CodedOutputStream.computeInt32Size(2, var14);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.opentype)) {
         Integer var15 = var1.opentype;
         var2 += CodedOutputStream.computeInt32Size(3, var15);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.subtype)) {
         Integer var16 = var1.subtype;
         var2 += CodedOutputStream.computeInt32Size(4, var16);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var17 = var1.partyguid;
         var2 += CodedOutputStream.computeUInt64Size(5, var17);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.leaderguid)) {
         Long var18 = var1.leaderguid;
         var2 += CodedOutputStream.computeUInt64Size(6, var18);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.packet)) {
         Integer var19 = var1.packet;
         var2 += CodedOutputStream.computeInt32Size(7, var19);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.bundleguid)) {
         Long var20 = var1.bundleguid;
         var2 += CodedOutputStream.computeUInt64Size(8, var20);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.merge)) {
         Boolean var21 = var1.merge;
         var2 += CodedOutputStream.computeBoolSize(9, var21);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.msg)) {
         byte[] var22 = var1.msg;
         var2 += CodedOutputStream.computeByteArraySize(10, var22);
      }

      return var2;
   }

   public void doWriteTo(PT_SYSCMD_PARTY var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.area)) {
         Integer var13 = var1.area;
         if (var13 != null) {
            var2.writeInt32(1, var13);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.stageindex)) {
         Integer var14 = var1.stageindex;
         if (var14 != null) {
            var2.writeInt32(2, var14);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.opentype)) {
         Integer var15 = var1.opentype;
         if (var15 != null) {
            var2.writeInt32(3, var15);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.subtype)) {
         Integer var16 = var1.subtype;
         if (var16 != null) {
            var2.writeInt32(4, var16);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var17 = var1.partyguid;
         if (var17 != null) {
            var2.writeUInt64(5, var17);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.leaderguid)) {
         Long var18 = var1.leaderguid;
         if (var18 != null) {
            var2.writeUInt64(6, var18);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.packet)) {
         Integer var19 = var1.packet;
         if (var19 != null) {
            var2.writeInt32(7, var19);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.bundleguid)) {
         Long var20 = var1.bundleguid;
         if (var20 != null) {
            var2.writeUInt64(8, var20);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.merge)) {
         Boolean var21 = var1.merge;
         if (var21 != null) {
            var2.writeBool(9, var21);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.msg)) {
         byte[] var22 = var1.msg;
         if (var22 != null) {
            var2.writeByteArray(10, var22);
         }
      }

   }

   public void writeTo(PT_SYSCMD_PARTY var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_SYSCMD_PARTY readFrom(CodedInputStream var1) throws IOException {
      PT_SYSCMD_PARTY var2 = new PT_SYSCMD_PARTY();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.area = var1.readInt32();
            } else if (var5 == 16) {
               var2.stageindex = var1.readInt32();
            } else if (var5 == 24) {
               var2.opentype = var1.readInt32();
            } else if (var5 == 32) {
               var2.subtype = var1.readInt32();
            } else if (var5 == 40) {
               var2.partyguid = var1.readUInt64();
            } else if (var5 == 48) {
               var2.leaderguid = var1.readUInt64();
            } else if (var5 == 56) {
               var2.packet = var1.readInt32();
            } else if (var5 == 64) {
               var2.bundleguid = var1.readUInt64();
            } else if (var5 == 72) {
               var2.merge = var1.readBool();
            } else if (var5 == 82) {
               var2.msg = var1.readBytes().toByteArray();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_SYSCMD_PARTY.class);
         return this.descriptor = var1;
      }
   }
}
