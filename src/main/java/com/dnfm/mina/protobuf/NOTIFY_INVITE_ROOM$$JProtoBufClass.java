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

public class NOTIFY_INVITE_ROOM$$JProtoBufClass implements Codec<NOTIFY_INVITE_ROOM>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(NOTIFY_INVITE_ROOM var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public NOTIFY_INVITE_ROOM decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(NOTIFY_INVITE_ROOM var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var21 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var21);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var22 = var1.guid;
         var2 += CodedOutputStream.computeUInt64Size(2, var22);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.gchatguid)) {
         Long var23 = var1.gchatguid;
         var2 += CodedOutputStream.computeUInt64Size(3, var23);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.type)) {
         ENUM_ROOM_TYPE.T var24 = var1.type;
         var2 += CodedOutputStream.computeEnumSize(4, ((ENUM_ROOM_TYPE.T)var24).value());
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var25 = var1.count;
         var2 += CodedOutputStream.computeInt32Size(5, var25);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var26 = var1.world;
         var2 += CodedOutputStream.computeInt32Size(6, var26);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.channel)) {
         Integer var27 = var1.channel;
         var2 += CodedOutputStream.computeInt32Size(7, var27);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.ip)) {
         String var28 = var1.ip;
         var2 += CodedOutputStream.computeStringSize(8, var28);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.port)) {
         Integer var29 = var1.port;
         var2 += CodedOutputStream.computeInt32Size(9, var29);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.targetname)) {
         String var30 = var1.targetname;
         var2 += CodedOutputStream.computeStringSize(10, var30);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var31 = var1.name;
         var2 += CodedOutputStream.computeStringSize(11, var31);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var32 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(12, var32);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var33 = var1.job;
         var2 += CodedOutputStream.computeInt32Size(13, var33);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var34 = var1.growtype;
         var2 += CodedOutputStream.computeInt32Size(14, var34);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.secgrowtype)) {
         Integer var35 = var1.secgrowtype;
         var2 += CodedOutputStream.computeInt32Size(15, var35);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.equipscore)) {
         Integer var36 = var1.equipscore;
         var2 += CodedOutputStream.computeInt32Size(16, var36);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.clientversion)) {
         String var37 = var1.clientversion;
         var2 += CodedOutputStream.computeStringSize(17, var37);
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.teamtype)) {
         ENUM_TEAM.T var38 = var1.teamtype;
         var2 += CodedOutputStream.computeEnumSize(18, ((Enum)var38).ordinal());
      }

      return var2;
   }

   public void doWriteTo(NOTIFY_INVITE_ROOM var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var21 = var1.error;
         if (var21 != null) {
            var2.writeInt32(1, var21);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var22 = var1.guid;
         if (var22 != null) {
            var2.writeUInt64(2, var22);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.gchatguid)) {
         Long var23 = var1.gchatguid;
         if (var23 != null) {
            var2.writeUInt64(3, var23);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.type)) {
         ENUM_ROOM_TYPE.T var24 = var1.type;
         if (var24 != null) {
            var2.writeEnum(4, ((ENUM_ROOM_TYPE.T)var24).value());
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var25 = var1.count;
         if (var25 != null) {
            var2.writeInt32(5, var25);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var26 = var1.world;
         if (var26 != null) {
            var2.writeInt32(6, var26);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.channel)) {
         Integer var27 = var1.channel;
         if (var27 != null) {
            var2.writeInt32(7, var27);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.ip)) {
         String var28 = var1.ip;
         if (var28 != null) {
            var2.writeString(8, var28);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.port)) {
         Integer var29 = var1.port;
         if (var29 != null) {
            var2.writeInt32(9, var29);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.targetname)) {
         String var30 = var1.targetname;
         if (var30 != null) {
            var2.writeString(10, var30);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var31 = var1.name;
         if (var31 != null) {
            var2.writeString(11, var31);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var32 = var1.level;
         if (var32 != null) {
            var2.writeInt32(12, var32);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var33 = var1.job;
         if (var33 != null) {
            var2.writeInt32(13, var33);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var34 = var1.growtype;
         if (var34 != null) {
            var2.writeInt32(14, var34);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.secgrowtype)) {
         Integer var35 = var1.secgrowtype;
         if (var35 != null) {
            var2.writeInt32(15, var35);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.equipscore)) {
         Integer var36 = var1.equipscore;
         if (var36 != null) {
            var2.writeInt32(16, var36);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.clientversion)) {
         String var37 = var1.clientversion;
         if (var37 != null) {
            var2.writeString(17, var37);
         }
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.teamtype)) {
         ENUM_TEAM.T var38 = var1.teamtype;
         if (var38 != null) {
            var2.writeEnum(18, ((Enum)var38).ordinal());
         }
      }

   }

   public void writeTo(NOTIFY_INVITE_ROOM var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public NOTIFY_INVITE_ROOM readFrom(CodedInputStream var1) throws IOException {
      NOTIFY_INVITE_ROOM var2 = new NOTIFY_INVITE_ROOM();
      var2.type = (ENUM_ROOM_TYPE.T)CodedConstant.getEnumValue(ENUM_ROOM_TYPE.T.class, ENUM_ROOM_TYPE.T.values()[0].name());
      var2.teamtype = (ENUM_TEAM.T)CodedConstant.getEnumValue(ENUM_TEAM.T.class, ENUM_TEAM.T.values()[0].name());

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
               var2.guid = var1.readUInt64();
            } else if (var5 == 24) {
               var2.gchatguid = var1.readUInt64();
            } else if (var5 == 32) {
               var2.type = (ENUM_ROOM_TYPE.T)CodedConstant.getEnumValue(ENUM_ROOM_TYPE.T.class, CodedConstant.getEnumName(ENUM_ROOM_TYPE.T.values(), var1.readEnum()));
            } else if (var5 == 40) {
               var2.count = var1.readInt32();
            } else if (var5 == 48) {
               var2.world = var1.readInt32();
            } else if (var5 == 56) {
               var2.channel = var1.readInt32();
            } else if (var5 == 66) {
               var2.ip = var1.readString();
            } else if (var5 == 72) {
               var2.port = var1.readInt32();
            } else if (var5 == 82) {
               var2.targetname = var1.readString();
            } else if (var5 == 90) {
               var2.name = var1.readString();
            } else if (var5 == 96) {
               var2.level = var1.readInt32();
            } else if (var5 == 104) {
               var2.job = var1.readInt32();
            } else if (var5 == 112) {
               var2.growtype = var1.readInt32();
            } else if (var5 == 120) {
               var2.secgrowtype = var1.readInt32();
            } else if (var5 == 128) {
               var2.equipscore = var1.readInt32();
            } else if (var5 == 138) {
               var2.clientversion = var1.readString();
            } else if (var5 == 144) {
               var2.teamtype = (ENUM_TEAM.T)CodedConstant.getEnumValue(ENUM_TEAM.T.class, CodedConstant.getEnumName(ENUM_TEAM.T.values(), var1.readEnum()));
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(NOTIFY_INVITE_ROOM.class);
         return this.descriptor = var1;
      }
   }
}
