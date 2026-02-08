package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.baidu.bjf.remoting.protobuf.code.CodedConstant;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.InvalidProtocolBufferException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class PT_MESSENGER_FRIEND_INFO$$JProtoBufClass implements Codec<PT_MESSENGER_FRIEND_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_MESSENGER_FRIEND_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_MESSENGER_FRIEND_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_MESSENGER_FRIEND_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var27 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var27);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var28 = var1.name;
         var2 += CodedOutputStream.computeStringSize(2, var28);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var29 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(3, var29);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var30 = var1.job;
         var2 += CodedOutputStream.computeInt32Size(4, var30);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var31 = var1.growtype;
         var2 += CodedOutputStream.computeInt32Size(5, var31);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.secgrowtype)) {
         Integer var32 = var1.secgrowtype;
         var2 += CodedOutputStream.computeInt32Size(6, var32);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.openid)) {
         String var33 = var1.openid;
         var2 += CodedOutputStream.computeStringSize(7, var33);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var34 = var1.world;
         var2 += CodedOutputStream.computeInt32Size(8, var34);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.creditscore)) {
         Integer var35 = var1.creditscore;
         var2 += CodedOutputStream.computeInt32Size(9, var35);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.lastlogin)) {
         Long var36 = var1.lastlogin;
         var2 += CodedOutputStream.computeInt64Size(10, var36);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.lastlogout)) {
         Long var37 = var1.lastlogout;
         var2 += CodedOutputStream.computeInt64Size(11, var37);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.platform)) {
         Integer var38 = var1.platform;
         var2 += CodedOutputStream.computeInt32Size(12, var38);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.status)) {
         Integer var39 = var1.status;
         var2 += CodedOutputStream.computeInt32Size(13, var39);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.online)) {
         Integer var40 = var1.online;
         var2 += CodedOutputStream.computeInt32Size(14, var40);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.sendtime)) {
         Long var41 = var1.sendtime;
         var2 += CodedOutputStream.computeInt64Size(15, var41);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.recvtime)) {
         Long var42 = var1.recvtime;
         var2 += CodedOutputStream.computeInt64Size(16, var42);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.fromsendtime)) {
         Long var43 = var1.fromsendtime;
         var2 += CodedOutputStream.computeInt64Size(17, var43);
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.platformserverid)) {
         Integer var44 = var1.platformserverid;
         var2 += CodedOutputStream.computeUInt32Size(18, var44);
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.gamecenterinfo)) {
         Integer var45 = var1.gamecenterinfo;
         var2 += CodedOutputStream.computeInt32Size(19, var45);
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.qqVipInfo)) {
         Integer var46 = var1.qqVipInfo;
         var2 += CodedOutputStream.computeInt32Size(20, var46);
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.chivalrygrade)) {
         Integer var47 = var1.chivalrygrade;
         var2 += CodedOutputStream.computeInt32Size(21, var47);
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.skinchatinfo)) {
         PT_SKIN_CHAT_INFO var48 = var1.skinchatinfo;
         var2 += CodedConstant.computeSize(22, var48, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.isInvite)) {
         Boolean var49 = var1.isInvite;
         var2 += CodedOutputStream.computeBoolSize(23, var49);
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.inviteblockflag)) {
         Integer var50 = var1.inviteblockflag;
         var2 += CodedOutputStream.computeInt32Size(24, var50);
      }

      return var2;
   }

   public void doWriteTo(PT_MESSENGER_FRIEND_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var27 = var1.charguid;
         if (var27 != null) {
            var2.writeUInt64(1, var27);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var28 = var1.name;
         if (var28 != null) {
            var2.writeString(2, var28);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var29 = var1.level;
         if (var29 != null) {
            var2.writeInt32(3, var29);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var30 = var1.job;
         if (var30 != null) {
            var2.writeInt32(4, var30);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var31 = var1.growtype;
         if (var31 != null) {
            var2.writeInt32(5, var31);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.secgrowtype)) {
         Integer var32 = var1.secgrowtype;
         if (var32 != null) {
            var2.writeInt32(6, var32);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.openid)) {
         String var33 = var1.openid;
         if (var33 != null) {
            var2.writeString(7, var33);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var34 = var1.world;
         if (var34 != null) {
            var2.writeInt32(8, var34);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.creditscore)) {
         Integer var35 = var1.creditscore;
         if (var35 != null) {
            var2.writeInt32(9, var35);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.lastlogin)) {
         Long var36 = var1.lastlogin;
         if (var36 != null) {
            var2.writeInt64(10, var36);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.lastlogout)) {
         Long var37 = var1.lastlogout;
         if (var37 != null) {
            var2.writeInt64(11, var37);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.platform)) {
         Integer var38 = var1.platform;
         if (var38 != null) {
            var2.writeInt32(12, var38);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.status)) {
         Integer var39 = var1.status;
         if (var39 != null) {
            var2.writeInt32(13, var39);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.online)) {
         Integer var40 = var1.online;
         if (var40 != null) {
            var2.writeInt32(14, var40);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.sendtime)) {
         Long var41 = var1.sendtime;
         if (var41 != null) {
            var2.writeInt64(15, var41);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.recvtime)) {
         Long var42 = var1.recvtime;
         if (var42 != null) {
            var2.writeInt64(16, var42);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.fromsendtime)) {
         Long var43 = var1.fromsendtime;
         if (var43 != null) {
            var2.writeInt64(17, var43);
         }
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.platformserverid)) {
         Integer var44 = var1.platformserverid;
         if (var44 != null) {
            var2.writeUInt32(18, var44);
         }
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.gamecenterinfo)) {
         Integer var45 = var1.gamecenterinfo;
         if (var45 != null) {
            var2.writeInt32(19, var45);
         }
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.qqVipInfo)) {
         Integer var46 = var1.qqVipInfo;
         if (var46 != null) {
            var2.writeInt32(20, var46);
         }
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.chivalrygrade)) {
         Integer var47 = var1.chivalrygrade;
         if (var47 != null) {
            var2.writeInt32(21, var47);
         }
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.skinchatinfo)) {
         PT_SKIN_CHAT_INFO var48 = var1.skinchatinfo;
         if (var48 != null) {
            CodedConstant.writeObject(var2, 22, FieldType.OBJECT, var48, false);
         }
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.isInvite)) {
         Boolean var49 = var1.isInvite;
         if (var49 != null) {
            var2.writeBool(23, var49);
         }
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.inviteblockflag)) {
         Integer var50 = var1.inviteblockflag;
         if (var50 != null) {
            var2.writeInt32(24, var50);
         }
      }

   }

   public void writeTo(PT_MESSENGER_FRIEND_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_MESSENGER_FRIEND_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_MESSENGER_FRIEND_INFO var2 = new PT_MESSENGER_FRIEND_INFO();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.charguid = var1.readUInt64();
            } else if (var5 == 18) {
               var2.name = var1.readString();
            } else if (var5 == 24) {
               var2.level = var1.readInt32();
            } else if (var5 == 32) {
               var2.job = var1.readInt32();
            } else if (var5 == 40) {
               var2.growtype = var1.readInt32();
            } else if (var5 == 48) {
               var2.secgrowtype = var1.readInt32();
            } else if (var5 == 58) {
               var2.openid = var1.readString();
            } else if (var5 == 64) {
               var2.world = var1.readInt32();
            } else if (var5 == 72) {
               var2.creditscore = var1.readInt32();
            } else if (var5 == 80) {
               var2.lastlogin = var1.readInt64();
            } else if (var5 == 88) {
               var2.lastlogout = var1.readInt64();
            } else if (var5 == 96) {
               var2.platform = var1.readInt32();
            } else if (var5 == 104) {
               var2.status = var1.readInt32();
            } else if (var5 == 112) {
               var2.online = var1.readInt32();
            } else if (var5 == 120) {
               var2.sendtime = var1.readInt64();
            } else if (var5 == 128) {
               var2.recvtime = var1.readInt64();
            } else if (var5 == 136) {
               var2.fromsendtime = var1.readInt64();
            } else if (var5 == 144) {
               var2.platformserverid = var1.readUInt32();
            } else if (var5 == 152) {
               var2.gamecenterinfo = var1.readInt32();
            } else if (var5 == 160) {
               var2.qqVipInfo = var1.readInt32();
            } else if (var5 == 168) {
               var2.chivalrygrade = var1.readInt32();
            } else if (var5 == 178) {
               Codec var10 = ProtobufProxy.create(PT_SKIN_CHAT_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.skinchatinfo = (PT_SKIN_CHAT_INFO)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 184) {
               var2.isInvite = var1.readBool();
            } else if (var5 == 192) {
               var2.inviteblockflag = var1.readInt32();
            } else {
               var1.skipField(var5);
            }
         }

         return var2;
      } catch (InvalidProtocolBufferException var8) {
         throw var8;
      } catch (IOException var9) {
         throw var9;
      }
   }

   public Descriptors.Descriptor getDescriptor() throws IOException {
      if (this.descriptor != null) {
         return this.descriptor;
      } else {
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_MESSENGER_FRIEND_INFO.class);
         return this.descriptor = var1;
      }
   }
}
