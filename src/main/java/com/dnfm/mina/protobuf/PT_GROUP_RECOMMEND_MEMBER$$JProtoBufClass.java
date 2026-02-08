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

public class PT_GROUP_RECOMMEND_MEMBER$$JProtoBufClass implements Codec<PT_GROUP_RECOMMEND_MEMBER>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_GROUP_RECOMMEND_MEMBER var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_GROUP_RECOMMEND_MEMBER decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_GROUP_RECOMMEND_MEMBER var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.groupguid)) {
         Long var28 = var1.groupguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var28);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var29 = var1.world;
         var2 += CodedOutputStream.computeInt32Size(2, var29);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.isPvPMember)) {
         Boolean var30 = var1.isPvPMember;
         var2 += CodedOutputStream.computeBoolSize(3, var30);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.isMentorMember)) {
         Boolean var31 = var1.isMentorMember;
         var2 += CodedOutputStream.computeBoolSize(4, var31);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.status)) {
         Integer var32 = var1.status;
         var2 += CodedOutputStream.computeInt32Size(5, var32);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var33 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(6, var33);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.equipscore)) {
         Integer var34 = var1.equipscore;
         var2 += CodedOutputStream.computeInt32Size(7, var34);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var35 = var1.job;
         var2 += CodedOutputStream.computeInt32Size(8, var35);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var36 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(9, var36);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var37 = var1.name;
         var2 += CodedOutputStream.computeStringSize(10, var37);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var38 = var1.growtype;
         var2 += CodedOutputStream.computeInt32Size(11, var38);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var39 = var1.secondgrowtype;
         var2 += CodedOutputStream.computeInt32Size(12, var39);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.gname)) {
         String var40 = var1.gname;
         var2 += CodedOutputStream.computeStringSize(13, var40);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.ip)) {
         String var41 = var1.ip;
         var2 += CodedOutputStream.computeStringSize(14, var41);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.port)) {
         Integer var42 = var1.port;
         var2 += CodedOutputStream.computeUInt32Size(15, var42);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var43 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(16, var43);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.isMyFriend)) {
         Boolean var44 = var1.isMyFriend;
         var2 += CodedOutputStream.computeBoolSize(17, var44);
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.isMyGuild)) {
         Boolean var45 = var1.isMyGuild;
         var2 += CodedOutputStream.computeBoolSize(18, var45);
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.creditscore)) {
         Integer var46 = var1.creditscore;
         var2 += CodedOutputStream.computeInt32Size(19, var46);
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.chivalrygrade)) {
         Integer var47 = var1.chivalrygrade;
         var2 += CodedOutputStream.computeInt32Size(20, var47);
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.characterframe)) {
         Integer var48 = var1.characterframe;
         var2 += CodedOutputStream.computeInt32Size(21, var48);
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.pvpstargrade)) {
         Integer var49 = var1.pvpstargrade;
         var2 += CodedOutputStream.computeInt32Size(22, var49);
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.switchstatus)) {
         ENUM_MESSAGE_ASSISTANT_SWITCH_STATUS.T var50 = var1.switchstatus;
         var2 += CodedOutputStream.computeEnumSize(23, ((ENUM_MESSAGE_ASSISTANT_SWITCH_STATUS.T)var50).value());
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.isInvite)) {
         Boolean var51 = var1.isInvite;
         var2 += CodedOutputStream.computeBoolSize(24, var51);
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.inviteblockflag)) {
         Integer var52 = var1.inviteblockflag;
         var2 += CodedOutputStream.computeInt32Size(25, var52);
      }

      return var2;
   }

   public void doWriteTo(PT_GROUP_RECOMMEND_MEMBER var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.groupguid)) {
         Long var28 = var1.groupguid;
         if (var28 != null) {
            var2.writeUInt64(1, var28);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var29 = var1.world;
         if (var29 != null) {
            var2.writeInt32(2, var29);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.isPvPMember)) {
         Boolean var30 = var1.isPvPMember;
         if (var30 != null) {
            var2.writeBool(3, var30);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.isMentorMember)) {
         Boolean var31 = var1.isMentorMember;
         if (var31 != null) {
            var2.writeBool(4, var31);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.status)) {
         Integer var32 = var1.status;
         if (var32 != null) {
            var2.writeInt32(5, var32);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var33 = var1.charguid;
         if (var33 != null) {
            var2.writeUInt64(6, var33);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.equipscore)) {
         Integer var34 = var1.equipscore;
         if (var34 != null) {
            var2.writeInt32(7, var34);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var35 = var1.job;
         if (var35 != null) {
            var2.writeInt32(8, var35);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var36 = var1.level;
         if (var36 != null) {
            var2.writeInt32(9, var36);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var37 = var1.name;
         if (var37 != null) {
            var2.writeString(10, var37);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var38 = var1.growtype;
         if (var38 != null) {
            var2.writeInt32(11, var38);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var39 = var1.secondgrowtype;
         if (var39 != null) {
            var2.writeInt32(12, var39);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.gname)) {
         String var40 = var1.gname;
         if (var40 != null) {
            var2.writeString(13, var40);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.ip)) {
         String var41 = var1.ip;
         if (var41 != null) {
            var2.writeString(14, var41);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.port)) {
         Integer var42 = var1.port;
         if (var42 != null) {
            var2.writeUInt32(15, var42);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var43 = var1.type;
         if (var43 != null) {
            var2.writeInt32(16, var43);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.isMyFriend)) {
         Boolean var44 = var1.isMyFriend;
         if (var44 != null) {
            var2.writeBool(17, var44);
         }
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.isMyGuild)) {
         Boolean var45 = var1.isMyGuild;
         if (var45 != null) {
            var2.writeBool(18, var45);
         }
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.creditscore)) {
         Integer var46 = var1.creditscore;
         if (var46 != null) {
            var2.writeInt32(19, var46);
         }
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.chivalrygrade)) {
         Integer var47 = var1.chivalrygrade;
         if (var47 != null) {
            var2.writeInt32(20, var47);
         }
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.characterframe)) {
         Integer var48 = var1.characterframe;
         if (var48 != null) {
            var2.writeInt32(21, var48);
         }
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.pvpstargrade)) {
         Integer var49 = var1.pvpstargrade;
         if (var49 != null) {
            var2.writeInt32(22, var49);
         }
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.switchstatus)) {
         ENUM_MESSAGE_ASSISTANT_SWITCH_STATUS.T var50 = var1.switchstatus;
         if (var50 != null) {
            var2.writeEnum(23, ((ENUM_MESSAGE_ASSISTANT_SWITCH_STATUS.T)var50).value());
         }
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.isInvite)) {
         Boolean var51 = var1.isInvite;
         if (var51 != null) {
            var2.writeBool(24, var51);
         }
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.inviteblockflag)) {
         Integer var52 = var1.inviteblockflag;
         if (var52 != null) {
            var2.writeInt32(25, var52);
         }
      }

   }

   public void writeTo(PT_GROUP_RECOMMEND_MEMBER var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_GROUP_RECOMMEND_MEMBER readFrom(CodedInputStream var1) throws IOException {
      PT_GROUP_RECOMMEND_MEMBER var2 = new PT_GROUP_RECOMMEND_MEMBER();
      var2.switchstatus = (ENUM_MESSAGE_ASSISTANT_SWITCH_STATUS.T)CodedConstant.getEnumValue(ENUM_MESSAGE_ASSISTANT_SWITCH_STATUS.T.class, ENUM_MESSAGE_ASSISTANT_SWITCH_STATUS.T.values()[0].name());

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.groupguid = var1.readUInt64();
            } else if (var5 == 16) {
               var2.world = var1.readInt32();
            } else if (var5 == 24) {
               var2.isPvPMember = var1.readBool();
            } else if (var5 == 32) {
               var2.isMentorMember = var1.readBool();
            } else if (var5 == 40) {
               var2.status = var1.readInt32();
            } else if (var5 == 48) {
               var2.charguid = var1.readUInt64();
            } else if (var5 == 56) {
               var2.equipscore = var1.readInt32();
            } else if (var5 == 64) {
               var2.job = var1.readInt32();
            } else if (var5 == 72) {
               var2.level = var1.readInt32();
            } else if (var5 == 82) {
               var2.name = var1.readString();
            } else if (var5 == 88) {
               var2.growtype = var1.readInt32();
            } else if (var5 == 96) {
               var2.secondgrowtype = var1.readInt32();
            } else if (var5 == 106) {
               var2.gname = var1.readString();
            } else if (var5 == 114) {
               var2.ip = var1.readString();
            } else if (var5 == 120) {
               var2.port = var1.readUInt32();
            } else if (var5 == 128) {
               var2.type = var1.readInt32();
            } else if (var5 == 136) {
               var2.isMyFriend = var1.readBool();
            } else if (var5 == 144) {
               var2.isMyGuild = var1.readBool();
            } else if (var5 == 152) {
               var2.creditscore = var1.readInt32();
            } else if (var5 == 160) {
               var2.chivalrygrade = var1.readInt32();
            } else if (var5 == 168) {
               var2.characterframe = var1.readInt32();
            } else if (var5 == 176) {
               var2.pvpstargrade = var1.readInt32();
            } else if (var5 == 184) {
               var2.switchstatus = (ENUM_MESSAGE_ASSISTANT_SWITCH_STATUS.T)CodedConstant.getEnumValue(ENUM_MESSAGE_ASSISTANT_SWITCH_STATUS.T.class, CodedConstant.getEnumName(ENUM_MESSAGE_ASSISTANT_SWITCH_STATUS.T.values(), var1.readEnum()));
            } else if (var5 == 192) {
               var2.isInvite = var1.readBool();
            } else if (var5 == 200) {
               var2.inviteblockflag = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_GROUP_RECOMMEND_MEMBER.class);
         return this.descriptor = var1;
      }
   }
}
