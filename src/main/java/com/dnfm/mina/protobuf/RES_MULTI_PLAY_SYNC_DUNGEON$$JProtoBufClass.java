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

public class RES_MULTI_PLAY_SYNC_DUNGEON$$JProtoBufClass implements Codec<RES_MULTI_PLAY_SYNC_DUNGEON>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_MULTI_PLAY_SYNC_DUNGEON var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_MULTI_PLAY_SYNC_DUNGEON decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_MULTI_PLAY_SYNC_DUNGEON var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var19 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var19);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.hp)) {
         Integer var20 = var1.hp;
         var2 += CodedOutputStream.computeInt32Size(2, var20);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.matchingguid)) {
         Long var21 = var1.matchingguid;
         var2 += CodedOutputStream.computeUInt64Size(3, var21);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var22 = var1.dungeonguid;
         var2 += CodedOutputStream.computeUInt64Size(4, var22);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var23 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(5, var23);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.objectgroupid)) {
         Integer var24 = var1.objectgroupid;
         var2 += CodedOutputStream.computeInt32Size(6, var24);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.cobjectid)) {
         Integer var25 = var1.cobjectid;
         var2 += CodedOutputStream.computeInt32Size(7, var25);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.roomid)) {
         Long var26 = var1.roomid;
         var2 += CodedOutputStream.computeUInt64Size(8, var26);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.playerid)) {
         Integer var27 = var1.playerid;
         var2 += CodedOutputStream.computeInt32Size(9, var27);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.accesstoken)) {
         String var28 = var1.accesstoken;
         var2 += CodedOutputStream.computeStringSize(10, var28);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var29 = var1.grade;
         var2 += CodedOutputStream.computeInt32Size(11, var29);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.partycoin)) {
         Integer var30 = var1.partycoin;
         var2 += CodedOutputStream.computeInt32Size(12, var30);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.position)) {
         Integer var31 = var1.position;
         var2 += CodedOutputStream.computeInt32Size(13, var31);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.cardindex)) {
         Integer var32 = var1.cardindex;
         var2 += CodedOutputStream.computeInt32Size(14, var32);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.apc)) {
         Boolean var33 = var1.apc;
         var2 += CodedOutputStream.computeBoolSize(15, var33);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.createtime)) {
         Long var34 = var1.createtime;
         var2 += CodedOutputStream.computeUInt64Size(16, var34);
      }

      return var2;
   }

   public void doWriteTo(RES_MULTI_PLAY_SYNC_DUNGEON var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var19 = var1.error;
         if (var19 != null) {
            var2.writeInt32(1, var19);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.hp)) {
         Integer var20 = var1.hp;
         if (var20 != null) {
            var2.writeInt32(2, var20);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.matchingguid)) {
         Long var21 = var1.matchingguid;
         if (var21 != null) {
            var2.writeUInt64(3, var21);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var22 = var1.dungeonguid;
         if (var22 != null) {
            var2.writeUInt64(4, var22);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var23 = var1.index;
         if (var23 != null) {
            var2.writeInt32(5, var23);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.objectgroupid)) {
         Integer var24 = var1.objectgroupid;
         if (var24 != null) {
            var2.writeInt32(6, var24);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.cobjectid)) {
         Integer var25 = var1.cobjectid;
         if (var25 != null) {
            var2.writeInt32(7, var25);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.roomid)) {
         Long var26 = var1.roomid;
         if (var26 != null) {
            var2.writeUInt64(8, var26);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.playerid)) {
         Integer var27 = var1.playerid;
         if (var27 != null) {
            var2.writeInt32(9, var27);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.accesstoken)) {
         String var28 = var1.accesstoken;
         if (var28 != null) {
            var2.writeString(10, var28);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var29 = var1.grade;
         if (var29 != null) {
            var2.writeInt32(11, var29);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.partycoin)) {
         Integer var30 = var1.partycoin;
         if (var30 != null) {
            var2.writeInt32(12, var30);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.position)) {
         Integer var31 = var1.position;
         if (var31 != null) {
            var2.writeInt32(13, var31);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.cardindex)) {
         Integer var32 = var1.cardindex;
         if (var32 != null) {
            var2.writeInt32(14, var32);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.apc)) {
         Boolean var33 = var1.apc;
         if (var33 != null) {
            var2.writeBool(15, var33);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.createtime)) {
         Long var34 = var1.createtime;
         if (var34 != null) {
            var2.writeUInt64(16, var34);
         }
      }

   }

   public void writeTo(RES_MULTI_PLAY_SYNC_DUNGEON var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_MULTI_PLAY_SYNC_DUNGEON readFrom(CodedInputStream var1) throws IOException {
      RES_MULTI_PLAY_SYNC_DUNGEON var2 = new RES_MULTI_PLAY_SYNC_DUNGEON();

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
               var2.hp = var1.readInt32();
            } else if (var5 == 24) {
               var2.matchingguid = var1.readUInt64();
            } else if (var5 == 32) {
               var2.dungeonguid = var1.readUInt64();
            } else if (var5 == 40) {
               var2.index = var1.readInt32();
            } else if (var5 == 48) {
               var2.objectgroupid = var1.readInt32();
            } else if (var5 == 56) {
               var2.cobjectid = var1.readInt32();
            } else if (var5 == 64) {
               var2.roomid = var1.readUInt64();
            } else if (var5 == 72) {
               var2.playerid = var1.readInt32();
            } else if (var5 == 82) {
               var2.accesstoken = var1.readString();
            } else if (var5 == 88) {
               var2.grade = var1.readInt32();
            } else if (var5 == 96) {
               var2.partycoin = var1.readInt32();
            } else if (var5 == 104) {
               var2.position = var1.readInt32();
            } else if (var5 == 112) {
               var2.cardindex = var1.readInt32();
            } else if (var5 == 120) {
               var2.apc = var1.readBool();
            } else if (var5 == 128) {
               var2.createtime = var1.readUInt64();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_MULTI_PLAY_SYNC_DUNGEON.class);
         return this.descriptor = var1;
      }
   }
}
