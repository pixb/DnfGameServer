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

public class REQ_PARTY_CREATE$$JProtoBufClass implements Codec<REQ_PARTY_CREATE>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_PARTY_CREATE var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_PARTY_CREATE decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_PARTY_CREATE var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.area)) {
         Integer var15 = var1.area;
         var2 += CodedOutputStream.computeInt32Size(1, var15);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.subtype)) {
         Integer var16 = var1.subtype;
         var2 += CodedOutputStream.computeInt32Size(2, var16);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.stageindex)) {
         Integer var17 = var1.stageindex;
         var2 += CodedOutputStream.computeInt32Size(3, var17);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var18 = var1.grade;
         var2 += CodedOutputStream.computeInt32Size(4, var18);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.opentype)) {
         ENUM_PARTY_OPEN_TYPES.T var19 = var1.opentype;
         var2 += CodedOutputStream.computeEnumSize(5, ((ENUM_PARTY_OPEN_TYPES.T)var19).value());
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var20 = var1.dungeonindex;
         var2 += CodedOutputStream.computeInt32Size(6, var20);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.minlevel)) {
         Integer var21 = var1.minlevel;
         var2 += CodedOutputStream.computeInt32Size(7, var21);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.maxlevel)) {
         Integer var22 = var1.maxlevel;
         var2 += CodedOutputStream.computeInt32Size(8, var22);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.observer)) {
         Boolean var23 = var1.observer;
         var2 += CodedOutputStream.computeBoolSize(9, var23);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.observerchat)) {
         Boolean var24 = var1.observerchat;
         var2 += CodedOutputStream.computeBoolSize(10, var24);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.player)) {
         Boolean var25 = var1.player;
         var2 += CodedOutputStream.computeBoolSize(11, var25);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var26 = var1.name;
         var2 += CodedOutputStream.computeStringSize(12, var26);
      }

      return var2;
   }

   public void doWriteTo(REQ_PARTY_CREATE var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.area)) {
         Integer var15 = var1.area;
         if (var15 != null) {
            var2.writeInt32(1, var15);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.subtype)) {
         Integer var16 = var1.subtype;
         if (var16 != null) {
            var2.writeInt32(2, var16);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.stageindex)) {
         Integer var17 = var1.stageindex;
         if (var17 != null) {
            var2.writeInt32(3, var17);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var18 = var1.grade;
         if (var18 != null) {
            var2.writeInt32(4, var18);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.opentype)) {
         ENUM_PARTY_OPEN_TYPES.T var19 = var1.opentype;
         if (var19 != null) {
            var2.writeEnum(5, ((ENUM_PARTY_OPEN_TYPES.T)var19).value());
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var20 = var1.dungeonindex;
         if (var20 != null) {
            var2.writeInt32(6, var20);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.minlevel)) {
         Integer var21 = var1.minlevel;
         if (var21 != null) {
            var2.writeInt32(7, var21);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.maxlevel)) {
         Integer var22 = var1.maxlevel;
         if (var22 != null) {
            var2.writeInt32(8, var22);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.observer)) {
         Boolean var23 = var1.observer;
         if (var23 != null) {
            var2.writeBool(9, var23);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.observerchat)) {
         Boolean var24 = var1.observerchat;
         if (var24 != null) {
            var2.writeBool(10, var24);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.player)) {
         Boolean var25 = var1.player;
         if (var25 != null) {
            var2.writeBool(11, var25);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var26 = var1.name;
         if (var26 != null) {
            var2.writeString(12, var26);
         }
      }

   }

   public void writeTo(REQ_PARTY_CREATE var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_PARTY_CREATE readFrom(CodedInputStream var1) throws IOException {
      REQ_PARTY_CREATE var2 = new REQ_PARTY_CREATE();
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
               var2.area = var1.readInt32();
            } else if (var5 == 16) {
               var2.subtype = var1.readInt32();
            } else if (var5 == 24) {
               var2.stageindex = var1.readInt32();
            } else if (var5 == 32) {
               var2.grade = var1.readInt32();
            } else if (var5 == 40) {
               var2.opentype = (ENUM_PARTY_OPEN_TYPES.T)CodedConstant.getEnumValue(ENUM_PARTY_OPEN_TYPES.T.class, CodedConstant.getEnumName(ENUM_PARTY_OPEN_TYPES.T.values(), var1.readEnum()));
            } else if (var5 == 48) {
               var2.dungeonindex = var1.readInt32();
            } else if (var5 == 56) {
               var2.minlevel = var1.readInt32();
            } else if (var5 == 64) {
               var2.maxlevel = var1.readInt32();
            } else if (var5 == 72) {
               var2.observer = var1.readBool();
            } else if (var5 == 80) {
               var2.observerchat = var1.readBool();
            } else if (var5 == 88) {
               var2.player = var1.readBool();
            } else if (var5 == 98) {
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_PARTY_CREATE.class);
         return this.descriptor = var1;
      }
   }
}
