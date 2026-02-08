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

public class PT_WAREHOUSE_STRUCTURE$$JProtoBufClass implements Codec<PT_WAREHOUSE_STRUCTURE>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_WAREHOUSE_STRUCTURE var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_WAREHOUSE_STRUCTURE decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_WAREHOUSE_STRUCTURE var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.type)) {
         ENUM_GUILD_STRUCTURE_TYPE.T var8 = var1.type;
         var2 += CodedOutputStream.computeEnumSize(1, ((ENUM_GUILD_STRUCTURE_TYPE.T)var8).value());
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var9 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(2, var9);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var10 = var1.count;
         var2 += CodedOutputStream.computeInt32Size(3, var10);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var11 = var1.guid;
         var2 += CodedOutputStream.computeUInt64Size(4, var11);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.status)) {
         ENUM_GUILD_STRUCTURE_STATUS.T var12 = var1.status;
         var2 += CodedOutputStream.computeEnumSize(5, ((ENUM_GUILD_STRUCTURE_STATUS.T)var12).value());
      }

      return var2;
   }

   public void doWriteTo(PT_WAREHOUSE_STRUCTURE var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.type)) {
         ENUM_GUILD_STRUCTURE_TYPE.T var8 = var1.type;
         if (var8 != null) {
            var2.writeEnum(1, ((ENUM_GUILD_STRUCTURE_TYPE.T)var8).value());
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var9 = var1.index;
         if (var9 != null) {
            var2.writeInt32(2, var9);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var10 = var1.count;
         if (var10 != null) {
            var2.writeInt32(3, var10);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var11 = var1.guid;
         if (var11 != null) {
            var2.writeUInt64(4, var11);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.status)) {
         ENUM_GUILD_STRUCTURE_STATUS.T var12 = var1.status;
         if (var12 != null) {
            var2.writeEnum(5, ((ENUM_GUILD_STRUCTURE_STATUS.T)var12).value());
         }
      }

   }

   public void writeTo(PT_WAREHOUSE_STRUCTURE var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_WAREHOUSE_STRUCTURE readFrom(CodedInputStream var1) throws IOException {
      PT_WAREHOUSE_STRUCTURE var2 = new PT_WAREHOUSE_STRUCTURE();
      var2.type = (ENUM_GUILD_STRUCTURE_TYPE.T)CodedConstant.getEnumValue(ENUM_GUILD_STRUCTURE_TYPE.T.class, ENUM_GUILD_STRUCTURE_TYPE.T.values()[0].name());
      var2.status = (ENUM_GUILD_STRUCTURE_STATUS.T)CodedConstant.getEnumValue(ENUM_GUILD_STRUCTURE_STATUS.T.class, ENUM_GUILD_STRUCTURE_STATUS.T.values()[0].name());

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.type = (ENUM_GUILD_STRUCTURE_TYPE.T)CodedConstant.getEnumValue(ENUM_GUILD_STRUCTURE_TYPE.T.class, CodedConstant.getEnumName(ENUM_GUILD_STRUCTURE_TYPE.T.values(), var1.readEnum()));
            } else if (var5 == 16) {
               var2.index = var1.readInt32();
            } else if (var5 == 24) {
               var2.count = var1.readInt32();
            } else if (var5 == 32) {
               var2.guid = var1.readUInt64();
            } else if (var5 == 40) {
               var2.status = (ENUM_GUILD_STRUCTURE_STATUS.T)CodedConstant.getEnumValue(ENUM_GUILD_STRUCTURE_STATUS.T.class, CodedConstant.getEnumName(ENUM_GUILD_STRUCTURE_STATUS.T.values(), var1.readEnum()));
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_WAREHOUSE_STRUCTURE.class);
         return this.descriptor = var1;
      }
   }
}
