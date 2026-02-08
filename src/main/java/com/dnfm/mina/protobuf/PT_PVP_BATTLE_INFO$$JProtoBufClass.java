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

public class PT_PVP_BATTLE_INFO$$JProtoBufClass implements Codec<PT_PVP_BATTLE_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_PVP_BATTLE_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_PVP_BATTLE_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_PVP_BATTLE_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.maxcombodamage)) {
         Long var33 = var1.maxcombodamage;
         var2 += CodedOutputStream.computeInt64Size(1, var33);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.startcombosuccessrate)) {
         Float var34 = var1.startcombosuccessrate;
         var2 += CodedOutputStream.computeFloatSize(2, var34);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.counterdamage)) {
         Long var35 = var1.counterdamage;
         var2 += CodedOutputStream.computeInt64Size(3, var35);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.matchtime)) {
         Long var36 = var1.matchtime;
         var2 += CodedOutputStream.computeInt64Size(4, var36);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.hprate)) {
         Float var37 = var1.hprate;
         var2 += CodedOutputStream.computeFloatSize(5, var37);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.totaldamage)) {
         Long var38 = var1.totaldamage;
         var2 += CodedOutputStream.computeInt64Size(6, var38);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.skilldodgerate)) {
         Float var39 = var1.skilldodgerate;
         var2 += CodedOutputStream.computeFloatSize(7, var39);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.totalcombocount)) {
         Integer var40 = var1.totalcombocount;
         var2 += CodedOutputStream.computeInt32Size(8, var40);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.aerialdamage)) {
         Long var41 = var1.aerialdamage;
         var2 += CodedOutputStream.computeInt64Size(9, var41);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.standdamage)) {
         Long var42 = var1.standdamage;
         var2 += CodedOutputStream.computeInt64Size(10, var42);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.startcombosuccesscount)) {
         Integer var43 = var1.startcombosuccesscount;
         var2 += CodedOutputStream.computeInt32Size(11, var43);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.startcombototalcount)) {
         Integer var44 = var1.startcombototalcount;
         var2 += CodedOutputStream.computeInt32Size(12, var44);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.attackedskillcount)) {
         Integer var45 = var1.attackedskillcount;
         var2 += CodedOutputStream.computeInt32Size(13, var45);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.allcharacterskillcount)) {
         Integer var46 = var1.allcharacterskillcount;
         var2 += CodedOutputStream.computeInt32Size(14, var46);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.killcount)) {
         Integer var47 = var1.killcount;
         var2 += CodedOutputStream.computeInt32Size(15, var47);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.killedcount)) {
         Integer var48 = var1.killedcount;
         var2 += CodedOutputStream.computeInt32Size(16, var48);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.mortar)) {
         Integer var49 = var1.mortar;
         var2 += CodedOutputStream.computeInt32Size(17, var49);
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.deltaKillcount)) {
         Integer var50 = var1.deltaKillcount;
         var2 += CodedOutputStream.computeInt32Size(18, var50);
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.deltaMaxcombodamage)) {
         Long var51 = var1.deltaMaxcombodamage;
         var2 += CodedOutputStream.computeInt64Size(19, var51);
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.deltaStartcombosuccessrate)) {
         Float var52 = var1.deltaStartcombosuccessrate;
         var2 += CodedOutputStream.computeFloatSize(20, var52);
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.deltaStartcombosuccesscount)) {
         Integer var53 = var1.deltaStartcombosuccesscount;
         var2 += CodedOutputStream.computeInt32Size(21, var53);
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.deltaStartcombototalcount)) {
         Integer var54 = var1.deltaStartcombototalcount;
         var2 += CodedOutputStream.computeInt32Size(22, var54);
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.deltaCounterdamage)) {
         Long var55 = var1.deltaCounterdamage;
         var2 += CodedOutputStream.computeInt64Size(23, var55);
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.deltaTotaldamage)) {
         Long var56 = var1.deltaTotaldamage;
         var2 += CodedOutputStream.computeInt64Size(24, var56);
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.deltaSkilldodgerate)) {
         Float var57 = var1.deltaSkilldodgerate;
         var2 += CodedOutputStream.computeFloatSize(25, var57);
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.deltaAttackedskillcount)) {
         Integer var58 = var1.deltaAttackedskillcount;
         var2 += CodedOutputStream.computeInt32Size(26, var58);
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.deltaAllcharacterskillcount)) {
         Integer var59 = var1.deltaAllcharacterskillcount;
         var2 += CodedOutputStream.computeInt32Size(27, var59);
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.deltaTotalcombocount)) {
         Integer var60 = var1.deltaTotalcombocount;
         var2 += CodedOutputStream.computeInt32Size(28, var60);
      }

      Object var31 = null;
      if (!CodedConstant.isNull(var1.deltaAerialdamage)) {
         Long var61 = var1.deltaAerialdamage;
         var2 += CodedOutputStream.computeInt64Size(29, var61);
      }

      Object var32 = null;
      if (!CodedConstant.isNull(var1.deltaStanddamage)) {
         Long var62 = var1.deltaStanddamage;
         var2 += CodedOutputStream.computeInt64Size(30, var62);
      }

      return var2;
   }

   public void doWriteTo(PT_PVP_BATTLE_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.maxcombodamage)) {
         Long var33 = var1.maxcombodamage;
         if (var33 != null) {
            var2.writeInt64(1, var33);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.startcombosuccessrate)) {
         Float var34 = var1.startcombosuccessrate;
         if (var34 != null) {
            var2.writeFloat(2, var34);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.counterdamage)) {
         Long var35 = var1.counterdamage;
         if (var35 != null) {
            var2.writeInt64(3, var35);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.matchtime)) {
         Long var36 = var1.matchtime;
         if (var36 != null) {
            var2.writeInt64(4, var36);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.hprate)) {
         Float var37 = var1.hprate;
         if (var37 != null) {
            var2.writeFloat(5, var37);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.totaldamage)) {
         Long var38 = var1.totaldamage;
         if (var38 != null) {
            var2.writeInt64(6, var38);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.skilldodgerate)) {
         Float var39 = var1.skilldodgerate;
         if (var39 != null) {
            var2.writeFloat(7, var39);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.totalcombocount)) {
         Integer var40 = var1.totalcombocount;
         if (var40 != null) {
            var2.writeInt32(8, var40);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.aerialdamage)) {
         Long var41 = var1.aerialdamage;
         if (var41 != null) {
            var2.writeInt64(9, var41);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.standdamage)) {
         Long var42 = var1.standdamage;
         if (var42 != null) {
            var2.writeInt64(10, var42);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.startcombosuccesscount)) {
         Integer var43 = var1.startcombosuccesscount;
         if (var43 != null) {
            var2.writeInt32(11, var43);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.startcombototalcount)) {
         Integer var44 = var1.startcombototalcount;
         if (var44 != null) {
            var2.writeInt32(12, var44);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.attackedskillcount)) {
         Integer var45 = var1.attackedskillcount;
         if (var45 != null) {
            var2.writeInt32(13, var45);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.allcharacterskillcount)) {
         Integer var46 = var1.allcharacterskillcount;
         if (var46 != null) {
            var2.writeInt32(14, var46);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.killcount)) {
         Integer var47 = var1.killcount;
         if (var47 != null) {
            var2.writeInt32(15, var47);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.killedcount)) {
         Integer var48 = var1.killedcount;
         if (var48 != null) {
            var2.writeInt32(16, var48);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.mortar)) {
         Integer var49 = var1.mortar;
         if (var49 != null) {
            var2.writeInt32(17, var49);
         }
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.deltaKillcount)) {
         Integer var50 = var1.deltaKillcount;
         if (var50 != null) {
            var2.writeInt32(18, var50);
         }
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.deltaMaxcombodamage)) {
         Long var51 = var1.deltaMaxcombodamage;
         if (var51 != null) {
            var2.writeInt64(19, var51);
         }
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.deltaStartcombosuccessrate)) {
         Float var52 = var1.deltaStartcombosuccessrate;
         if (var52 != null) {
            var2.writeFloat(20, var52);
         }
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.deltaStartcombosuccesscount)) {
         Integer var53 = var1.deltaStartcombosuccesscount;
         if (var53 != null) {
            var2.writeInt32(21, var53);
         }
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.deltaStartcombototalcount)) {
         Integer var54 = var1.deltaStartcombototalcount;
         if (var54 != null) {
            var2.writeInt32(22, var54);
         }
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.deltaCounterdamage)) {
         Long var55 = var1.deltaCounterdamage;
         if (var55 != null) {
            var2.writeInt64(23, var55);
         }
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.deltaTotaldamage)) {
         Long var56 = var1.deltaTotaldamage;
         if (var56 != null) {
            var2.writeInt64(24, var56);
         }
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.deltaSkilldodgerate)) {
         Float var57 = var1.deltaSkilldodgerate;
         if (var57 != null) {
            var2.writeFloat(25, var57);
         }
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.deltaAttackedskillcount)) {
         Integer var58 = var1.deltaAttackedskillcount;
         if (var58 != null) {
            var2.writeInt32(26, var58);
         }
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.deltaAllcharacterskillcount)) {
         Integer var59 = var1.deltaAllcharacterskillcount;
         if (var59 != null) {
            var2.writeInt32(27, var59);
         }
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.deltaTotalcombocount)) {
         Integer var60 = var1.deltaTotalcombocount;
         if (var60 != null) {
            var2.writeInt32(28, var60);
         }
      }

      Object var31 = null;
      if (!CodedConstant.isNull(var1.deltaAerialdamage)) {
         Long var61 = var1.deltaAerialdamage;
         if (var61 != null) {
            var2.writeInt64(29, var61);
         }
      }

      Object var32 = null;
      if (!CodedConstant.isNull(var1.deltaStanddamage)) {
         Long var62 = var1.deltaStanddamage;
         if (var62 != null) {
            var2.writeInt64(30, var62);
         }
      }

   }

   public void writeTo(PT_PVP_BATTLE_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_PVP_BATTLE_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_PVP_BATTLE_INFO var2 = new PT_PVP_BATTLE_INFO();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.maxcombodamage = var1.readInt64();
            } else if (var5 == 21) {
               var2.startcombosuccessrate = var1.readFloat();
            } else if (var5 == 24) {
               var2.counterdamage = var1.readInt64();
            } else if (var5 == 32) {
               var2.matchtime = var1.readInt64();
            } else if (var5 == 45) {
               var2.hprate = var1.readFloat();
            } else if (var5 == 48) {
               var2.totaldamage = var1.readInt64();
            } else if (var5 == 61) {
               var2.skilldodgerate = var1.readFloat();
            } else if (var5 == 64) {
               var2.totalcombocount = var1.readInt32();
            } else if (var5 == 72) {
               var2.aerialdamage = var1.readInt64();
            } else if (var5 == 80) {
               var2.standdamage = var1.readInt64();
            } else if (var5 == 88) {
               var2.startcombosuccesscount = var1.readInt32();
            } else if (var5 == 96) {
               var2.startcombototalcount = var1.readInt32();
            } else if (var5 == 104) {
               var2.attackedskillcount = var1.readInt32();
            } else if (var5 == 112) {
               var2.allcharacterskillcount = var1.readInt32();
            } else if (var5 == 120) {
               var2.killcount = var1.readInt32();
            } else if (var5 == 128) {
               var2.killedcount = var1.readInt32();
            } else if (var5 == 136) {
               var2.mortar = var1.readInt32();
            } else if (var5 == 144) {
               var2.deltaKillcount = var1.readInt32();
            } else if (var5 == 152) {
               var2.deltaMaxcombodamage = var1.readInt64();
            } else if (var5 == 165) {
               var2.deltaStartcombosuccessrate = var1.readFloat();
            } else if (var5 == 168) {
               var2.deltaStartcombosuccesscount = var1.readInt32();
            } else if (var5 == 176) {
               var2.deltaStartcombototalcount = var1.readInt32();
            } else if (var5 == 184) {
               var2.deltaCounterdamage = var1.readInt64();
            } else if (var5 == 192) {
               var2.deltaTotaldamage = var1.readInt64();
            } else if (var5 == 205) {
               var2.deltaSkilldodgerate = var1.readFloat();
            } else if (var5 == 208) {
               var2.deltaAttackedskillcount = var1.readInt32();
            } else if (var5 == 216) {
               var2.deltaAllcharacterskillcount = var1.readInt32();
            } else if (var5 == 224) {
               var2.deltaTotalcombocount = var1.readInt32();
            } else if (var5 == 232) {
               var2.deltaAerialdamage = var1.readInt64();
            } else if (var5 == 240) {
               var2.deltaStanddamage = var1.readInt64();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_PVP_BATTLE_INFO.class);
         return this.descriptor = var1;
      }
   }
}
