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

public class RES_MAKE_ROOM$$JProtoBufClass implements Codec<RES_MAKE_ROOM>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_MAKE_ROOM var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_MAKE_ROOM decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_MAKE_ROOM var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var6 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var6);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.type)) {
         ENUM_ROOM_TYPE.T var7 = var1.type;
         var2 += CodedOutputStream.computeEnumSize(2, ((ENUM_ROOM_TYPE.T)var7).value());
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var8 = var1.guid;
         var2 += CodedOutputStream.computeUInt64Size(3, var8);
      }

      return var2;
   }

   public void doWriteTo(RES_MAKE_ROOM var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var6 = var1.error;
         if (var6 != null) {
            var2.writeInt32(1, var6);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.type)) {
         ENUM_ROOM_TYPE.T var7 = var1.type;
         if (var7 != null) {
            var2.writeEnum(2, ((ENUM_ROOM_TYPE.T)var7).value());
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var8 = var1.guid;
         if (var8 != null) {
            var2.writeUInt64(3, var8);
         }
      }

   }

   public void writeTo(RES_MAKE_ROOM var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_MAKE_ROOM readFrom(CodedInputStream var1) throws IOException {
      RES_MAKE_ROOM var2 = new RES_MAKE_ROOM();
      var2.type = (ENUM_ROOM_TYPE.T)CodedConstant.getEnumValue(ENUM_ROOM_TYPE.T.class, ENUM_ROOM_TYPE.T.values()[0].name());

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
               var2.type = (ENUM_ROOM_TYPE.T)CodedConstant.getEnumValue(ENUM_ROOM_TYPE.T.class, CodedConstant.getEnumName(ENUM_ROOM_TYPE.T.values(), var1.readEnum()));
            } else if (var5 == 24) {
               var2.guid = var1.readUInt64();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_MAKE_ROOM.class);
         return this.descriptor = var1;
      }
   }
}
