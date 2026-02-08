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

public class PT_GUILD_STRUCTURE_INFO$$JProtoBufClass implements Codec<PT_GUILD_STRUCTURE_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_GUILD_STRUCTURE_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_GUILD_STRUCTURE_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_GUILD_STRUCTURE_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.type)) {
         ENUM_GUILD_STRUCTURE_TYPE.T var14 = var1.type;
         var2 += CodedOutputStream.computeEnumSize(1, ((ENUM_GUILD_STRUCTURE_TYPE.T)var14).value());
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var15 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(2, var15);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var16 = var1.count;
         var2 += CodedOutputStream.computeInt32Size(3, var16);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var17 = var1.guid;
         var2 += CodedOutputStream.computeUInt64Size(4, var17);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.skinindex)) {
         Integer var18 = var1.skinindex;
         var2 += CodedOutputStream.computeInt32Size(5, var18);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.status)) {
         ENUM_GUILD_STRUCTURE_STATUS.T var19 = var1.status;
         var2 += CodedOutputStream.computeEnumSize(6, ((ENUM_GUILD_STRUCTURE_STATUS.T)var19).value());
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.posx)) {
         Integer var20 = var1.posx;
         var2 += CodedOutputStream.computeInt32Size(7, var20);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.posy)) {
         Integer var21 = var1.posy;
         var2 += CodedOutputStream.computeInt32Size(8, var21);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.rotationtype)) {
         ENUM_ROTATION_TYPE.T var22 = var1.rotationtype;
         var2 += CodedOutputStream.computeEnumSize(9, ((ENUM_ROTATION_TYPE.T)var22).value());
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.time)) {
         Long var23 = var1.time;
         var2 += CodedOutputStream.computeInt64Size(10, var23);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.baseindex)) {
         Integer var24 = var1.baseindex;
         var2 += CodedOutputStream.computeInt32Size(11, var24);
      }

      return var2;
   }

   public void doWriteTo(PT_GUILD_STRUCTURE_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.type)) {
         ENUM_GUILD_STRUCTURE_TYPE.T var14 = var1.type;
         if (var14 != null) {
            var2.writeEnum(1, ((ENUM_GUILD_STRUCTURE_TYPE.T)var14).value());
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var15 = var1.index;
         if (var15 != null) {
            var2.writeInt32(2, var15);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var16 = var1.count;
         if (var16 != null) {
            var2.writeInt32(3, var16);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var17 = var1.guid;
         if (var17 != null) {
            var2.writeUInt64(4, var17);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.skinindex)) {
         Integer var18 = var1.skinindex;
         if (var18 != null) {
            var2.writeInt32(5, var18);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.status)) {
         ENUM_GUILD_STRUCTURE_STATUS.T var19 = var1.status;
         if (var19 != null) {
            var2.writeEnum(6, ((ENUM_GUILD_STRUCTURE_STATUS.T)var19).value());
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.posx)) {
         Integer var20 = var1.posx;
         if (var20 != null) {
            var2.writeInt32(7, var20);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.posy)) {
         Integer var21 = var1.posy;
         if (var21 != null) {
            var2.writeInt32(8, var21);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.rotationtype)) {
         ENUM_ROTATION_TYPE.T var22 = var1.rotationtype;
         if (var22 != null) {
            var2.writeEnum(9, ((ENUM_ROTATION_TYPE.T)var22).value());
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.time)) {
         Long var23 = var1.time;
         if (var23 != null) {
            var2.writeInt64(10, var23);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.baseindex)) {
         Integer var24 = var1.baseindex;
         if (var24 != null) {
            var2.writeInt32(11, var24);
         }
      }

   }

   public void writeTo(PT_GUILD_STRUCTURE_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_GUILD_STRUCTURE_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_GUILD_STRUCTURE_INFO var2 = new PT_GUILD_STRUCTURE_INFO();
      var2.type = (ENUM_GUILD_STRUCTURE_TYPE.T)CodedConstant.getEnumValue(ENUM_GUILD_STRUCTURE_TYPE.T.class, ENUM_GUILD_STRUCTURE_TYPE.T.values()[0].name());
      var2.status = (ENUM_GUILD_STRUCTURE_STATUS.T)CodedConstant.getEnumValue(ENUM_GUILD_STRUCTURE_STATUS.T.class, ENUM_GUILD_STRUCTURE_STATUS.T.values()[0].name());
      var2.rotationtype = (ENUM_ROTATION_TYPE.T)CodedConstant.getEnumValue(ENUM_ROTATION_TYPE.T.class, ENUM_ROTATION_TYPE.T.values()[0].name());

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
               var2.skinindex = var1.readInt32();
            } else if (var5 == 48) {
               var2.status = (ENUM_GUILD_STRUCTURE_STATUS.T)CodedConstant.getEnumValue(ENUM_GUILD_STRUCTURE_STATUS.T.class, CodedConstant.getEnumName(ENUM_GUILD_STRUCTURE_STATUS.T.values(), var1.readEnum()));
            } else if (var5 == 56) {
               var2.posx = var1.readInt32();
            } else if (var5 == 64) {
               var2.posy = var1.readInt32();
            } else if (var5 == 72) {
               var2.rotationtype = (ENUM_ROTATION_TYPE.T)CodedConstant.getEnumValue(ENUM_ROTATION_TYPE.T.class, CodedConstant.getEnumName(ENUM_ROTATION_TYPE.T.values(), var1.readEnum()));
            } else if (var5 == 80) {
               var2.time = var1.readInt64();
            } else if (var5 == 88) {
               var2.baseindex = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_GUILD_STRUCTURE_INFO.class);
         return this.descriptor = var1;
      }
   }
}
