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

public class RES_INTERACTION_MENU$$JProtoBufClass implements Codec<RES_INTERACTION_MENU>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_INTERACTION_MENU var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_INTERACTION_MENU decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_INTERACTION_MENU var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var30 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var30);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var31 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(2, var31);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.online)) {
         Integer var32 = var1.online;
         var2 += CodedOutputStream.computeInt32Size(3, var32);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.status)) {
         Integer var33 = var1.status;
         var2 += CodedOutputStream.computeInt32Size(4, var33);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.lastlogout)) {
         Long var34 = var1.lastlogout;
         var2 += CodedOutputStream.computeInt64Size(5, var34);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.gguid)) {
         Long var35 = var1.gguid;
         var2 += CodedOutputStream.computeUInt64Size(6, var35);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.openmenutype)) {
         Integer var36 = var1.openmenutype;
         var2 += CodedOutputStream.computeInt32Size(7, var36);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var37 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(8, var37);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var38 = var1.job;
         var2 += CodedOutputStream.computeInt32Size(9, var38);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var39 = var1.growtype;
         var2 += CodedOutputStream.computeInt32Size(10, var39);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.secgrowtype)) {
         Integer var40 = var1.secgrowtype;
         var2 += CodedOutputStream.computeInt32Size(11, var40);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var41 = var1.name;
         var2 += CodedOutputStream.computeStringSize(12, var41);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.gname)) {
         String var42 = var1.gname;
         var2 += CodedOutputStream.computeStringSize(13, var42);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var43 = var1.grade;
         var2 += CodedOutputStream.computeInt32Size(14, var43);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.qindex)) {
         Integer var44 = var1.qindex;
         var2 += CodedOutputStream.computeInt32Size(15, var44);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var45 = var1.partyguid;
         var2 += CodedOutputStream.computeUInt64Size(16, var45);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.partyleader)) {
         Long var46 = var1.partyleader;
         var2 += CodedOutputStream.computeUInt64Size(17, var46);
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.publictype)) {
         Integer var47 = var1.publictype;
         var2 += CodedOutputStream.computeInt32Size(18, var47);
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.creditscore)) {
         Integer var48 = var1.creditscore;
         var2 += CodedOutputStream.computeInt32Size(19, var48);
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var49 = var1.world;
         var2 += CodedOutputStream.computeInt32Size(20, var49);
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.openid)) {
         String var50 = var1.openid;
         var2 += CodedOutputStream.computeStringSize(21, var50);
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.platform)) {
         Integer var51 = var1.platform;
         var2 += CodedOutputStream.computeInt32Size(22, var51);
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.platformserverid)) {
         Integer var52 = var1.platformserverid;
         var2 += CodedOutputStream.computeUInt32Size(23, var52);
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.adventureunionname)) {
         String var53 = var1.adventureunionname;
         var2 += CodedOutputStream.computeStringSize(24, var53);
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.gamecenterinfo)) {
         Integer var54 = var1.gamecenterinfo;
         var2 += CodedOutputStream.computeInt32Size(25, var54);
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.qqVipinfo)) {
         Integer var55 = var1.qqVipinfo;
         var2 += CodedOutputStream.computeInt32Size(26, var55);
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.characterframe)) {
         Integer var56 = var1.characterframe;
         var2 += CodedOutputStream.computeInt32Size(27, var56);
      }

      return var2;
   }

   public void doWriteTo(RES_INTERACTION_MENU var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var30 = var1.error;
         if (var30 != null) {
            var2.writeInt32(1, var30);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var31 = var1.charguid;
         if (var31 != null) {
            var2.writeUInt64(2, var31);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.online)) {
         Integer var32 = var1.online;
         if (var32 != null) {
            var2.writeInt32(3, var32);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.status)) {
         Integer var33 = var1.status;
         if (var33 != null) {
            var2.writeInt32(4, var33);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.lastlogout)) {
         Long var34 = var1.lastlogout;
         if (var34 != null) {
            var2.writeInt64(5, var34);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.gguid)) {
         Long var35 = var1.gguid;
         if (var35 != null) {
            var2.writeUInt64(6, var35);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.openmenutype)) {
         Integer var36 = var1.openmenutype;
         if (var36 != null) {
            var2.writeInt32(7, var36);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var37 = var1.level;
         if (var37 != null) {
            var2.writeInt32(8, var37);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var38 = var1.job;
         if (var38 != null) {
            var2.writeInt32(9, var38);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var39 = var1.growtype;
         if (var39 != null) {
            var2.writeInt32(10, var39);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.secgrowtype)) {
         Integer var40 = var1.secgrowtype;
         if (var40 != null) {
            var2.writeInt32(11, var40);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var41 = var1.name;
         if (var41 != null) {
            var2.writeString(12, var41);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.gname)) {
         String var42 = var1.gname;
         if (var42 != null) {
            var2.writeString(13, var42);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var43 = var1.grade;
         if (var43 != null) {
            var2.writeInt32(14, var43);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.qindex)) {
         Integer var44 = var1.qindex;
         if (var44 != null) {
            var2.writeInt32(15, var44);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var45 = var1.partyguid;
         if (var45 != null) {
            var2.writeUInt64(16, var45);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.partyleader)) {
         Long var46 = var1.partyleader;
         if (var46 != null) {
            var2.writeUInt64(17, var46);
         }
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.publictype)) {
         Integer var47 = var1.publictype;
         if (var47 != null) {
            var2.writeInt32(18, var47);
         }
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.creditscore)) {
         Integer var48 = var1.creditscore;
         if (var48 != null) {
            var2.writeInt32(19, var48);
         }
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var49 = var1.world;
         if (var49 != null) {
            var2.writeInt32(20, var49);
         }
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.openid)) {
         String var50 = var1.openid;
         if (var50 != null) {
            var2.writeString(21, var50);
         }
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.platform)) {
         Integer var51 = var1.platform;
         if (var51 != null) {
            var2.writeInt32(22, var51);
         }
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.platformserverid)) {
         Integer var52 = var1.platformserverid;
         if (var52 != null) {
            var2.writeUInt32(23, var52);
         }
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.adventureunionname)) {
         String var53 = var1.adventureunionname;
         if (var53 != null) {
            var2.writeString(24, var53);
         }
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.gamecenterinfo)) {
         Integer var54 = var1.gamecenterinfo;
         if (var54 != null) {
            var2.writeInt32(25, var54);
         }
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.qqVipinfo)) {
         Integer var55 = var1.qqVipinfo;
         if (var55 != null) {
            var2.writeInt32(26, var55);
         }
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.characterframe)) {
         Integer var56 = var1.characterframe;
         if (var56 != null) {
            var2.writeInt32(27, var56);
         }
      }

   }

   public void writeTo(RES_INTERACTION_MENU var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_INTERACTION_MENU readFrom(CodedInputStream var1) throws IOException {
      RES_INTERACTION_MENU var2 = new RES_INTERACTION_MENU();

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
               var2.charguid = var1.readUInt64();
            } else if (var5 == 24) {
               var2.online = var1.readInt32();
            } else if (var5 == 32) {
               var2.status = var1.readInt32();
            } else if (var5 == 40) {
               var2.lastlogout = var1.readInt64();
            } else if (var5 == 48) {
               var2.gguid = var1.readUInt64();
            } else if (var5 == 56) {
               var2.openmenutype = var1.readInt32();
            } else if (var5 == 64) {
               var2.level = var1.readInt32();
            } else if (var5 == 72) {
               var2.job = var1.readInt32();
            } else if (var5 == 80) {
               var2.growtype = var1.readInt32();
            } else if (var5 == 88) {
               var2.secgrowtype = var1.readInt32();
            } else if (var5 == 98) {
               var2.name = var1.readString();
            } else if (var5 == 106) {
               var2.gname = var1.readString();
            } else if (var5 == 112) {
               var2.grade = var1.readInt32();
            } else if (var5 == 120) {
               var2.qindex = var1.readInt32();
            } else if (var5 == 128) {
               var2.partyguid = var1.readUInt64();
            } else if (var5 == 136) {
               var2.partyleader = var1.readUInt64();
            } else if (var5 == 144) {
               var2.publictype = var1.readInt32();
            } else if (var5 == 152) {
               var2.creditscore = var1.readInt32();
            } else if (var5 == 160) {
               var2.world = var1.readInt32();
            } else if (var5 == 170) {
               var2.openid = var1.readString();
            } else if (var5 == 176) {
               var2.platform = var1.readInt32();
            } else if (var5 == 184) {
               var2.platformserverid = var1.readUInt32();
            } else if (var5 == 194) {
               var2.adventureunionname = var1.readString();
            } else if (var5 == 200) {
               var2.gamecenterinfo = var1.readInt32();
            } else if (var5 == 208) {
               var2.qqVipinfo = var1.readInt32();
            } else if (var5 == 216) {
               var2.characterframe = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_INTERACTION_MENU.class);
         return this.descriptor = var1;
      }
   }
}
