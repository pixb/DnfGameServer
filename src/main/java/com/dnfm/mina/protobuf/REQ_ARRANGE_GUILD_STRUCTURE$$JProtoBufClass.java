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

public class REQ_ARRANGE_GUILD_STRUCTURE$$JProtoBufClass implements Codec<REQ_ARRANGE_GUILD_STRUCTURE>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_ARRANGE_GUILD_STRUCTURE var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_ARRANGE_GUILD_STRUCTURE decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_ARRANGE_GUILD_STRUCTURE var1) throws IOException {
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
      if (!CodedConstant.isNull(var1.posx)) {
         Integer var10 = var1.posx;
         var2 += CodedOutputStream.computeInt32Size(3, var10);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.posy)) {
         Integer var11 = var1.posy;
         var2 += CodedOutputStream.computeInt32Size(4, var11);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.rotation)) {
         ENUM_ROTATION_TYPE.T var12 = var1.rotation;
         var2 += CodedOutputStream.computeEnumSize(5, ((ENUM_ROTATION_TYPE.T)var12).value());
      }

      return var2;
   }

   public void doWriteTo(REQ_ARRANGE_GUILD_STRUCTURE var1, CodedOutputStream var2) throws IOException {
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
      if (!CodedConstant.isNull(var1.posx)) {
         Integer var10 = var1.posx;
         if (var10 != null) {
            var2.writeInt32(3, var10);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.posy)) {
         Integer var11 = var1.posy;
         if (var11 != null) {
            var2.writeInt32(4, var11);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.rotation)) {
         ENUM_ROTATION_TYPE.T var12 = var1.rotation;
         if (var12 != null) {
            var2.writeEnum(5, ((ENUM_ROTATION_TYPE.T)var12).value());
         }
      }

   }

   public void writeTo(REQ_ARRANGE_GUILD_STRUCTURE var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_ARRANGE_GUILD_STRUCTURE readFrom(CodedInputStream var1) throws IOException {
      REQ_ARRANGE_GUILD_STRUCTURE var2 = new REQ_ARRANGE_GUILD_STRUCTURE();
      var2.rotation = (ENUM_ROTATION_TYPE.T)CodedConstant.getEnumValue(ENUM_ROTATION_TYPE.T.class, ENUM_ROTATION_TYPE.T.values()[0].name());

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
               var2.posx = var1.readInt32();
            } else if (var5 == 32) {
               var2.posy = var1.readInt32();
            } else if (var5 == 40) {
               var2.rotation = (ENUM_ROTATION_TYPE.T)CodedConstant.getEnumValue(ENUM_ROTATION_TYPE.T.class, CodedConstant.getEnumName(ENUM_ROTATION_TYPE.T.values(), var1.readEnum()));
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_ARRANGE_GUILD_STRUCTURE.class);
         return this.descriptor = var1;
      }
   }
}
