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
import java.util.ArrayList;
import java.util.List;

public class PT_FRIEND_INFO$$JProtoBufClass implements Codec<PT_FRIEND_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_FRIEND_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_FRIEND_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_FRIEND_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var31 = var1.world;
         var2 += CodedOutputStream.computeInt32Size(1, var31);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.fguid)) {
         Long var32 = var1.fguid;
         var2 += CodedOutputStream.computeUInt64Size(2, var32);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var33 = var1.name;
         var2 += CodedOutputStream.computeStringSize(3, var33);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var34 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(4, var34);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var35 = var1.job;
         var2 += CodedOutputStream.computeInt32Size(5, var35);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var36 = var1.growtype;
         var2 += CodedOutputStream.computeInt32Size(6, var36);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var37 = var1.secondgrowtype;
         var2 += CodedOutputStream.computeInt32Size(7, var37);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.date)) {
         Long var38 = var1.date;
         var2 += CodedOutputStream.computeInt64Size(8, var38);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.online)) {
         Integer var39 = var1.online;
         var2 += CodedOutputStream.computeInt32Size(9, var39);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.lastlogout)) {
         Long var40 = var1.lastlogout;
         var2 += CodedOutputStream.computeInt64Size(10, var40);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.channel)) {
         Integer var41 = var1.channel;
         var2 += CodedOutputStream.computeInt32Size(11, var41);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.closenessscore)) {
         Integer var42 = var1.closenessscore;
         var2 += CodedOutputStream.computeInt32Size(12, var42);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.dailyContents)) {
         List var43 = var1.dailyContents;
         var2 += CodedConstant.computeListSize(13, var43, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.sendtime)) {
         Long var44 = var1.sendtime;
         var2 += CodedOutputStream.computeInt64Size(14, var44);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.recvtime)) {
         Long var45 = var1.recvtime;
         var2 += CodedOutputStream.computeInt64Size(15, var45);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.fromsendtime)) {
         Long var46 = var1.fromsendtime;
         var2 += CodedOutputStream.computeInt64Size(16, var46);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.status)) {
         Integer var47 = var1.status;
         var2 += CodedOutputStream.computeInt32Size(17, var47);
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.msg)) {
         String var48 = var1.msg;
         var2 += CodedOutputStream.computeStringSize(18, var48);
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.route)) {
         Integer var49 = var1.route;
         var2 += CodedOutputStream.computeInt32Size(19, var49);
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.openid)) {
         String var50 = var1.openid;
         var2 += CodedOutputStream.computeStringSize(20, var50);
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.launchinfo)) {
         Integer var51 = var1.launchinfo;
         var2 += CodedOutputStream.computeInt32Size(21, var51);
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.vip)) {
         Integer var52 = var1.vip;
         var2 += CodedOutputStream.computeInt32Size(22, var52);
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.creditscore)) {
         Integer var53 = var1.creditscore;
         var2 += CodedOutputStream.computeInt32Size(23, var53);
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.characterframe)) {
         Integer var54 = var1.characterframe;
         var2 += CodedOutputStream.computeInt32Size(24, var54);
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.profileurl)) {
         String var55 = var1.profileurl;
         var2 += CodedOutputStream.computeStringSize(25, var55);
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.charm)) {
         Long var56 = var1.charm;
         var2 += CodedOutputStream.computeInt64Size(26, var56);
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.remainCharm)) {
         Integer var57 = var1.remainCharm;
         var2 += CodedOutputStream.computeInt32Size(27, var57);
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.switchstatus)) {
         ENUM_MESSAGE_ASSISTANT_SWITCH_STATUS.T var58 = var1.switchstatus;
         var2 += CodedOutputStream.computeEnumSize(28, ((ENUM_MESSAGE_ASSISTANT_SWITCH_STATUS.T)var58).value());
      }

      return var2;
   }

   public void doWriteTo(PT_FRIEND_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var31 = var1.world;
         if (var31 != null) {
            var2.writeInt32(1, var31);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.fguid)) {
         Long var32 = var1.fguid;
         if (var32 != null) {
            var2.writeUInt64(2, var32);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var33 = var1.name;
         if (var33 != null) {
            var2.writeString(3, var33);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var34 = var1.level;
         if (var34 != null) {
            var2.writeInt32(4, var34);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var35 = var1.job;
         if (var35 != null) {
            var2.writeInt32(5, var35);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var36 = var1.growtype;
         if (var36 != null) {
            var2.writeInt32(6, var36);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var37 = var1.secondgrowtype;
         if (var37 != null) {
            var2.writeInt32(7, var37);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.date)) {
         Long var38 = var1.date;
         if (var38 != null) {
            var2.writeInt64(8, var38);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.online)) {
         Integer var39 = var1.online;
         if (var39 != null) {
            var2.writeInt32(9, var39);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.lastlogout)) {
         Long var40 = var1.lastlogout;
         if (var40 != null) {
            var2.writeInt64(10, var40);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.channel)) {
         Integer var41 = var1.channel;
         if (var41 != null) {
            var2.writeInt32(11, var41);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.closenessscore)) {
         Integer var42 = var1.closenessscore;
         if (var42 != null) {
            var2.writeInt32(12, var42);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.dailyContents)) {
         List var43 = var1.dailyContents;
         if (var43 != null) {
            CodedConstant.writeToList(var2, 13, FieldType.OBJECT, var43, false);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.sendtime)) {
         Long var44 = var1.sendtime;
         if (var44 != null) {
            var2.writeInt64(14, var44);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.recvtime)) {
         Long var45 = var1.recvtime;
         if (var45 != null) {
            var2.writeInt64(15, var45);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.fromsendtime)) {
         Long var46 = var1.fromsendtime;
         if (var46 != null) {
            var2.writeInt64(16, var46);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.status)) {
         Integer var47 = var1.status;
         if (var47 != null) {
            var2.writeInt32(17, var47);
         }
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.msg)) {
         String var48 = var1.msg;
         if (var48 != null) {
            var2.writeString(18, var48);
         }
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.route)) {
         Integer var49 = var1.route;
         if (var49 != null) {
            var2.writeInt32(19, var49);
         }
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.openid)) {
         String var50 = var1.openid;
         if (var50 != null) {
            var2.writeString(20, var50);
         }
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.launchinfo)) {
         Integer var51 = var1.launchinfo;
         if (var51 != null) {
            var2.writeInt32(21, var51);
         }
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.vip)) {
         Integer var52 = var1.vip;
         if (var52 != null) {
            var2.writeInt32(22, var52);
         }
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.creditscore)) {
         Integer var53 = var1.creditscore;
         if (var53 != null) {
            var2.writeInt32(23, var53);
         }
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.characterframe)) {
         Integer var54 = var1.characterframe;
         if (var54 != null) {
            var2.writeInt32(24, var54);
         }
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.profileurl)) {
         String var55 = var1.profileurl;
         if (var55 != null) {
            var2.writeString(25, var55);
         }
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.charm)) {
         Long var56 = var1.charm;
         if (var56 != null) {
            var2.writeInt64(26, var56);
         }
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.remainCharm)) {
         Integer var57 = var1.remainCharm;
         if (var57 != null) {
            var2.writeInt32(27, var57);
         }
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.switchstatus)) {
         ENUM_MESSAGE_ASSISTANT_SWITCH_STATUS.T var58 = var1.switchstatus;
         if (var58 != null) {
            var2.writeEnum(28, ((ENUM_MESSAGE_ASSISTANT_SWITCH_STATUS.T)var58).value());
         }
      }

   }

   public void writeTo(PT_FRIEND_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_FRIEND_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_FRIEND_INFO var2 = new PT_FRIEND_INFO();
      var2.dailyContents = new ArrayList();
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
               var2.world = var1.readInt32();
            } else if (var5 == 16) {
               var2.fguid = var1.readUInt64();
            } else if (var5 == 26) {
               var2.name = var1.readString();
            } else if (var5 == 32) {
               var2.level = var1.readInt32();
            } else if (var5 == 40) {
               var2.job = var1.readInt32();
            } else if (var5 == 48) {
               var2.growtype = var1.readInt32();
            } else if (var5 == 56) {
               var2.secondgrowtype = var1.readInt32();
            } else if (var5 == 64) {
               var2.date = var1.readInt64();
            } else if (var5 == 72) {
               var2.online = var1.readInt32();
            } else if (var5 == 80) {
               var2.lastlogout = var1.readInt64();
            } else if (var5 == 88) {
               var2.channel = var1.readInt32();
            } else if (var5 == 96) {
               var2.closenessscore = var1.readInt32();
            } else if (var5 == 106) {
               Codec var10 = ProtobufProxy.create(PT_FRIEND_DAILY_CLOSENESS_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.dailyContents == null) {
                  var2.dailyContents = new ArrayList();
               }

               var2.dailyContents.add((PT_FRIEND_DAILY_CLOSENESS_INFO)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 112) {
               var2.sendtime = var1.readInt64();
            } else if (var5 == 120) {
               var2.recvtime = var1.readInt64();
            } else if (var5 == 128) {
               var2.fromsendtime = var1.readInt64();
            } else if (var5 == 136) {
               var2.status = var1.readInt32();
            } else if (var5 == 146) {
               var2.msg = var1.readString();
            } else if (var5 == 152) {
               var2.route = var1.readInt32();
            } else if (var5 == 162) {
               var2.openid = var1.readString();
            } else if (var5 == 168) {
               var2.launchinfo = var1.readInt32();
            } else if (var5 == 176) {
               var2.vip = var1.readInt32();
            } else if (var5 == 184) {
               var2.creditscore = var1.readInt32();
            } else if (var5 == 192) {
               var2.characterframe = var1.readInt32();
            } else if (var5 == 202) {
               var2.profileurl = var1.readString();
            } else if (var5 == 208) {
               var2.charm = var1.readInt64();
            } else if (var5 == 216) {
               var2.remainCharm = var1.readInt32();
            } else if (var5 == 224) {
               var2.switchstatus = (ENUM_MESSAGE_ASSISTANT_SWITCH_STATUS.T)CodedConstant.getEnumValue(ENUM_MESSAGE_ASSISTANT_SWITCH_STATUS.T.class, CodedConstant.getEnumName(ENUM_MESSAGE_ASSISTANT_SWITCH_STATUS.T.values(), var1.readEnum()));
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_FRIEND_INFO.class);
         return this.descriptor = var1;
      }
   }
}
