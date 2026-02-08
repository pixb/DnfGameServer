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

public class PT_UPDATE_PARTY$$JProtoBufClass implements Codec<PT_UPDATE_PARTY>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_UPDATE_PARTY var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_UPDATE_PARTY decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_UPDATE_PARTY var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var16 = var1.partyguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var16);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.area)) {
         Integer var17 = var1.area;
         var2 += CodedOutputStream.computeInt32Size(2, var17);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.subtype)) {
         Integer var18 = var1.subtype;
         var2 += CodedOutputStream.computeInt32Size(3, var18);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.stageindex)) {
         Integer var19 = var1.stageindex;
         var2 += CodedOutputStream.computeInt32Size(4, var19);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var20 = var1.grade;
         var2 += CodedOutputStream.computeInt32Size(5, var20);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.opentype)) {
         ENUM_PARTY_OPEN_TYPES.T var21 = var1.opentype;
         var2 += CodedOutputStream.computeEnumSize(6, ((ENUM_PARTY_OPEN_TYPES.T)var21).value());
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var22 = var1.dungeonindex;
         var2 += CodedOutputStream.computeInt32Size(7, var22);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.minlevel)) {
         Integer var23 = var1.minlevel;
         var2 += CodedOutputStream.computeInt32Size(8, var23);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.maxlevel)) {
         Integer var24 = var1.maxlevel;
         var2 += CodedOutputStream.computeInt32Size(9, var24);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.observer)) {
         Boolean var25 = var1.observer;
         var2 += CodedOutputStream.computeBoolSize(10, var25);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.observerchat)) {
         Boolean var26 = var1.observerchat;
         var2 += CodedOutputStream.computeBoolSize(11, var26);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.player)) {
         Boolean var27 = var1.player;
         var2 += CodedOutputStream.computeBoolSize(12, var27);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var28 = var1.name;
         var2 += CodedOutputStream.computeStringSize(13, var28);
      }

      return var2;
   }

   public void doWriteTo(PT_UPDATE_PARTY var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var16 = var1.partyguid;
         if (var16 != null) {
            var2.writeUInt64(1, var16);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.area)) {
         Integer var17 = var1.area;
         if (var17 != null) {
            var2.writeInt32(2, var17);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.subtype)) {
         Integer var18 = var1.subtype;
         if (var18 != null) {
            var2.writeInt32(3, var18);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.stageindex)) {
         Integer var19 = var1.stageindex;
         if (var19 != null) {
            var2.writeInt32(4, var19);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var20 = var1.grade;
         if (var20 != null) {
            var2.writeInt32(5, var20);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.opentype)) {
         ENUM_PARTY_OPEN_TYPES.T var21 = var1.opentype;
         if (var21 != null) {
            var2.writeEnum(6, ((ENUM_PARTY_OPEN_TYPES.T)var21).value());
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var22 = var1.dungeonindex;
         if (var22 != null) {
            var2.writeInt32(7, var22);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.minlevel)) {
         Integer var23 = var1.minlevel;
         if (var23 != null) {
            var2.writeInt32(8, var23);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.maxlevel)) {
         Integer var24 = var1.maxlevel;
         if (var24 != null) {
            var2.writeInt32(9, var24);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.observer)) {
         Boolean var25 = var1.observer;
         if (var25 != null) {
            var2.writeBool(10, var25);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.observerchat)) {
         Boolean var26 = var1.observerchat;
         if (var26 != null) {
            var2.writeBool(11, var26);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.player)) {
         Boolean var27 = var1.player;
         if (var27 != null) {
            var2.writeBool(12, var27);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var28 = var1.name;
         if (var28 != null) {
            var2.writeString(13, var28);
         }
      }

   }

   public void writeTo(PT_UPDATE_PARTY var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_UPDATE_PARTY readFrom(CodedInputStream var1) throws IOException {
      PT_UPDATE_PARTY var2 = new PT_UPDATE_PARTY();
      var2.opentype = (ENUM_PARTY_OPEN_TYPES.T)CodedConstant.getEnumValue(ENUM_PARTY_OPEN_TYPES.T.class, ENUM_PARTY_OPEN_TYPES.T.values()[0].name());

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.partyguid = var1.readUInt64();
            } else if (var5 == 16) {
               var2.area = var1.readInt32();
            } else if (var5 == 24) {
               var2.subtype = var1.readInt32();
            } else if (var5 == 32) {
               var2.stageindex = var1.readInt32();
            } else if (var5 == 40) {
               var2.grade = var1.readInt32();
            } else if (var5 == 48) {
               var2.opentype = (ENUM_PARTY_OPEN_TYPES.T)CodedConstant.getEnumValue(ENUM_PARTY_OPEN_TYPES.T.class, CodedConstant.getEnumName(ENUM_PARTY_OPEN_TYPES.T.values(), var1.readEnum()));
            } else if (var5 == 56) {
               var2.dungeonindex = var1.readInt32();
            } else if (var5 == 64) {
               var2.minlevel = var1.readInt32();
            } else if (var5 == 72) {
               var2.maxlevel = var1.readInt32();
            } else if (var5 == 80) {
               var2.observer = var1.readBool();
            } else if (var5 == 88) {
               var2.observerchat = var1.readBool();
            } else if (var5 == 96) {
               var2.player = var1.readBool();
            } else if (var5 == 106) {
               var2.name = var1.readString();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_UPDATE_PARTY.class);
         return this.descriptor = var1;
      }
   }
}
