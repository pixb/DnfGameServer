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

public class RES_PARTY_REQUEST_TO_JOIN$$JProtoBufClass implements Codec<RES_PARTY_REQUEST_TO_JOIN>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_PARTY_REQUEST_TO_JOIN var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_PARTY_REQUEST_TO_JOIN decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_PARTY_REQUEST_TO_JOIN var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var17 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var17);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var18 = var1.partyguid;
         var2 += CodedOutputStream.computeUInt64Size(2, var18);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.leaderguid)) {
         Long var19 = var1.leaderguid;
         var2 += CodedOutputStream.computeUInt64Size(3, var19);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.minlevel)) {
         Integer var20 = var1.minlevel;
         var2 += CodedOutputStream.computeInt32Size(4, var20);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.maxlevel)) {
         Integer var21 = var1.maxlevel;
         var2 += CodedOutputStream.computeInt32Size(5, var21);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.area)) {
         Integer var22 = var1.area;
         var2 += CodedOutputStream.computeInt32Size(6, var22);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var23 = var1.dungeonindex;
         var2 += CodedOutputStream.computeInt32Size(7, var23);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.subtype)) {
         Integer var24 = var1.subtype;
         var2 += CodedOutputStream.computeInt32Size(8, var24);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.stageindex)) {
         Integer var25 = var1.stageindex;
         var2 += CodedOutputStream.computeInt32Size(9, var25);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var26 = var1.grade;
         var2 += CodedOutputStream.computeInt32Size(10, var26);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.opentype)) {
         ENUM_PARTY_OPEN_TYPES.T var27 = var1.opentype;
         var2 += CodedOutputStream.computeEnumSize(11, ((ENUM_PARTY_OPEN_TYPES.T)var27).value());
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.observer)) {
         Boolean var28 = var1.observer;
         var2 += CodedOutputStream.computeBoolSize(12, var28);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.observerchat)) {
         Boolean var29 = var1.observerchat;
         var2 += CodedOutputStream.computeBoolSize(13, var29);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var30 = var1.name;
         var2 += CodedOutputStream.computeStringSize(14, var30);
      }

      return var2;
   }

   public void doWriteTo(RES_PARTY_REQUEST_TO_JOIN var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var17 = var1.error;
         if (var17 != null) {
            var2.writeInt32(1, var17);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var18 = var1.partyguid;
         if (var18 != null) {
            var2.writeUInt64(2, var18);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.leaderguid)) {
         Long var19 = var1.leaderguid;
         if (var19 != null) {
            var2.writeUInt64(3, var19);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.minlevel)) {
         Integer var20 = var1.minlevel;
         if (var20 != null) {
            var2.writeInt32(4, var20);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.maxlevel)) {
         Integer var21 = var1.maxlevel;
         if (var21 != null) {
            var2.writeInt32(5, var21);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.area)) {
         Integer var22 = var1.area;
         if (var22 != null) {
            var2.writeInt32(6, var22);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var23 = var1.dungeonindex;
         if (var23 != null) {
            var2.writeInt32(7, var23);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.subtype)) {
         Integer var24 = var1.subtype;
         if (var24 != null) {
            var2.writeInt32(8, var24);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.stageindex)) {
         Integer var25 = var1.stageindex;
         if (var25 != null) {
            var2.writeInt32(9, var25);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var26 = var1.grade;
         if (var26 != null) {
            var2.writeInt32(10, var26);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.opentype)) {
         ENUM_PARTY_OPEN_TYPES.T var27 = var1.opentype;
         if (var27 != null) {
            var2.writeEnum(11, ((ENUM_PARTY_OPEN_TYPES.T)var27).value());
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.observer)) {
         Boolean var28 = var1.observer;
         if (var28 != null) {
            var2.writeBool(12, var28);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.observerchat)) {
         Boolean var29 = var1.observerchat;
         if (var29 != null) {
            var2.writeBool(13, var29);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var30 = var1.name;
         if (var30 != null) {
            var2.writeString(14, var30);
         }
      }

   }

   public void writeTo(RES_PARTY_REQUEST_TO_JOIN var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_PARTY_REQUEST_TO_JOIN readFrom(CodedInputStream var1) throws IOException {
      RES_PARTY_REQUEST_TO_JOIN var2 = new RES_PARTY_REQUEST_TO_JOIN();
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
               var2.error = var1.readInt32();
            } else if (var5 == 16) {
               var2.partyguid = var1.readUInt64();
            } else if (var5 == 24) {
               var2.leaderguid = var1.readUInt64();
            } else if (var5 == 32) {
               var2.minlevel = var1.readInt32();
            } else if (var5 == 40) {
               var2.maxlevel = var1.readInt32();
            } else if (var5 == 48) {
               var2.area = var1.readInt32();
            } else if (var5 == 56) {
               var2.dungeonindex = var1.readInt32();
            } else if (var5 == 64) {
               var2.subtype = var1.readInt32();
            } else if (var5 == 72) {
               var2.stageindex = var1.readInt32();
            } else if (var5 == 80) {
               var2.grade = var1.readInt32();
            } else if (var5 == 88) {
               var2.opentype = (ENUM_PARTY_OPEN_TYPES.T)CodedConstant.getEnumValue(ENUM_PARTY_OPEN_TYPES.T.class, CodedConstant.getEnumName(ENUM_PARTY_OPEN_TYPES.T.values(), var1.readEnum()));
            } else if (var5 == 96) {
               var2.observer = var1.readBool();
            } else if (var5 == 104) {
               var2.observerchat = var1.readBool();
            } else if (var5 == 114) {
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_PARTY_REQUEST_TO_JOIN.class);
         return this.descriptor = var1;
      }
   }
}
