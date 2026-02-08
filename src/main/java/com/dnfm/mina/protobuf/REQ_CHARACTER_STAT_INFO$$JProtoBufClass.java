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

public class REQ_CHARACTER_STAT_INFO$$JProtoBufClass implements Codec<REQ_CHARACTER_STAT_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_CHARACTER_STAT_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_CHARACTER_STAT_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_CHARACTER_STAT_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.lightattribute)) {
         Integer var33 = var1.lightattribute;
         var2 += CodedOutputStream.computeInt32Size(1, var33);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.iceattribute)) {
         Integer var34 = var1.iceattribute;
         var2 += CodedOutputStream.computeInt32Size(2, var34);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.fireattribute)) {
         Integer var35 = var1.fireattribute;
         var2 += CodedOutputStream.computeInt32Size(3, var35);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.darkattribute)) {
         Integer var36 = var1.darkattribute;
         var2 += CodedOutputStream.computeInt32Size(4, var36);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.lightattributeResistance)) {
         Integer var37 = var1.lightattributeResistance;
         var2 += CodedOutputStream.computeInt32Size(5, var37);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.iceattributeResistance)) {
         Integer var38 = var1.iceattributeResistance;
         var2 += CodedOutputStream.computeInt32Size(6, var38);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.fireattributeResistance)) {
         Integer var39 = var1.fireattributeResistance;
         var2 += CodedOutputStream.computeInt32Size(7, var39);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.darkattributeResistance)) {
         Integer var40 = var1.darkattributeResistance;
         var2 += CodedOutputStream.computeInt32Size(8, var40);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.intelligencevalue)) {
         Integer var41 = var1.intelligencevalue;
         var2 += CodedOutputStream.computeInt32Size(9, var41);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.powervalue)) {
         Integer var42 = var1.powervalue;
         var2 += CodedOutputStream.computeInt32Size(10, var42);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.strengthvalue)) {
         Integer var43 = var1.strengthvalue;
         var2 += CodedOutputStream.computeInt32Size(11, var43);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.spiritvalue)) {
         Integer var44 = var1.spiritvalue;
         var2 += CodedOutputStream.computeInt32Size(12, var44);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.magicattack)) {
         Integer var45 = var1.magicattack;
         var2 += CodedOutputStream.computeInt32Size(13, var45);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.magiccri)) {
         Integer var46 = var1.magiccri;
         var2 += CodedOutputStream.computeInt32Size(14, var46);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.magiccrirate)) {
         Integer var47 = var1.magiccrirate;
         var2 += CodedOutputStream.computeInt32Size(15, var47);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.physicalattack)) {
         Integer var48 = var1.physicalattack;
         var2 += CodedOutputStream.computeInt32Size(16, var48);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.physicalcrit)) {
         Integer var49 = var1.physicalcrit;
         var2 += CodedOutputStream.computeInt32Size(17, var49);
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.physicalcritrate)) {
         Integer var50 = var1.physicalcritrate;
         var2 += CodedOutputStream.computeInt32Size(18, var50);
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.attackspeed)) {
         Integer var51 = var1.attackspeed;
         var2 += CodedOutputStream.computeInt32Size(19, var51);
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.castingspeed)) {
         Integer var52 = var1.castingspeed;
         var2 += CodedOutputStream.computeInt32Size(20, var52);
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.movingspeed)) {
         Integer var53 = var1.movingspeed;
         var2 += CodedOutputStream.computeInt32Size(21, var53);
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.defyingdefenseattack)) {
         Integer var54 = var1.defyingdefenseattack;
         var2 += CodedOutputStream.computeInt32Size(22, var54);
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.accuracyrate)) {
         Integer var55 = var1.accuracyrate;
         var2 += CodedOutputStream.computeInt32Size(23, var55);
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.accuracyvariablerate)) {
         Integer var56 = var1.accuracyvariablerate;
         var2 += CodedOutputStream.computeInt32Size(24, var56);
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.stuckvariablerate)) {
         Integer var57 = var1.stuckvariablerate;
         var2 += CodedOutputStream.computeInt32Size(25, var57);
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.stuck)) {
         Integer var58 = var1.stuck;
         var2 += CodedOutputStream.computeInt32Size(26, var58);
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.hpregenrate)) {
         Integer var59 = var1.hpregenrate;
         var2 += CodedOutputStream.computeInt32Size(27, var59);
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.mpregenrate)) {
         Integer var60 = var1.mpregenrate;
         var2 += CodedOutputStream.computeInt32Size(28, var60);
      }

      Object var31 = null;
      if (!CodedConstant.isNull(var1.ridgidity)) {
         Integer var61 = var1.ridgidity;
         var2 += CodedOutputStream.computeInt32Size(29, var61);
      }

      Object var32 = null;
      if (!CodedConstant.isNull(var1.hitrecovery)) {
         Integer var62 = var1.hitrecovery;
         var2 += CodedOutputStream.computeInt32Size(30, var62);
      }

      return var2;
   }

   public void doWriteTo(REQ_CHARACTER_STAT_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.lightattribute)) {
         Integer var33 = var1.lightattribute;
         if (var33 != null) {
            var2.writeInt32(1, var33);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.iceattribute)) {
         Integer var34 = var1.iceattribute;
         if (var34 != null) {
            var2.writeInt32(2, var34);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.fireattribute)) {
         Integer var35 = var1.fireattribute;
         if (var35 != null) {
            var2.writeInt32(3, var35);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.darkattribute)) {
         Integer var36 = var1.darkattribute;
         if (var36 != null) {
            var2.writeInt32(4, var36);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.lightattributeResistance)) {
         Integer var37 = var1.lightattributeResistance;
         if (var37 != null) {
            var2.writeInt32(5, var37);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.iceattributeResistance)) {
         Integer var38 = var1.iceattributeResistance;
         if (var38 != null) {
            var2.writeInt32(6, var38);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.fireattributeResistance)) {
         Integer var39 = var1.fireattributeResistance;
         if (var39 != null) {
            var2.writeInt32(7, var39);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.darkattributeResistance)) {
         Integer var40 = var1.darkattributeResistance;
         if (var40 != null) {
            var2.writeInt32(8, var40);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.intelligencevalue)) {
         Integer var41 = var1.intelligencevalue;
         if (var41 != null) {
            var2.writeInt32(9, var41);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.powervalue)) {
         Integer var42 = var1.powervalue;
         if (var42 != null) {
            var2.writeInt32(10, var42);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.strengthvalue)) {
         Integer var43 = var1.strengthvalue;
         if (var43 != null) {
            var2.writeInt32(11, var43);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.spiritvalue)) {
         Integer var44 = var1.spiritvalue;
         if (var44 != null) {
            var2.writeInt32(12, var44);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.magicattack)) {
         Integer var45 = var1.magicattack;
         if (var45 != null) {
            var2.writeInt32(13, var45);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.magiccri)) {
         Integer var46 = var1.magiccri;
         if (var46 != null) {
            var2.writeInt32(14, var46);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.magiccrirate)) {
         Integer var47 = var1.magiccrirate;
         if (var47 != null) {
            var2.writeInt32(15, var47);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.physicalattack)) {
         Integer var48 = var1.physicalattack;
         if (var48 != null) {
            var2.writeInt32(16, var48);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.physicalcrit)) {
         Integer var49 = var1.physicalcrit;
         if (var49 != null) {
            var2.writeInt32(17, var49);
         }
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.physicalcritrate)) {
         Integer var50 = var1.physicalcritrate;
         if (var50 != null) {
            var2.writeInt32(18, var50);
         }
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.attackspeed)) {
         Integer var51 = var1.attackspeed;
         if (var51 != null) {
            var2.writeInt32(19, var51);
         }
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.castingspeed)) {
         Integer var52 = var1.castingspeed;
         if (var52 != null) {
            var2.writeInt32(20, var52);
         }
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.movingspeed)) {
         Integer var53 = var1.movingspeed;
         if (var53 != null) {
            var2.writeInt32(21, var53);
         }
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.defyingdefenseattack)) {
         Integer var54 = var1.defyingdefenseattack;
         if (var54 != null) {
            var2.writeInt32(22, var54);
         }
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.accuracyrate)) {
         Integer var55 = var1.accuracyrate;
         if (var55 != null) {
            var2.writeInt32(23, var55);
         }
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.accuracyvariablerate)) {
         Integer var56 = var1.accuracyvariablerate;
         if (var56 != null) {
            var2.writeInt32(24, var56);
         }
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.stuckvariablerate)) {
         Integer var57 = var1.stuckvariablerate;
         if (var57 != null) {
            var2.writeInt32(25, var57);
         }
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.stuck)) {
         Integer var58 = var1.stuck;
         if (var58 != null) {
            var2.writeInt32(26, var58);
         }
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.hpregenrate)) {
         Integer var59 = var1.hpregenrate;
         if (var59 != null) {
            var2.writeInt32(27, var59);
         }
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.mpregenrate)) {
         Integer var60 = var1.mpregenrate;
         if (var60 != null) {
            var2.writeInt32(28, var60);
         }
      }

      Object var31 = null;
      if (!CodedConstant.isNull(var1.ridgidity)) {
         Integer var61 = var1.ridgidity;
         if (var61 != null) {
            var2.writeInt32(29, var61);
         }
      }

      Object var32 = null;
      if (!CodedConstant.isNull(var1.hitrecovery)) {
         Integer var62 = var1.hitrecovery;
         if (var62 != null) {
            var2.writeInt32(30, var62);
         }
      }

   }

   public void writeTo(REQ_CHARACTER_STAT_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_CHARACTER_STAT_INFO readFrom(CodedInputStream var1) throws IOException {
      REQ_CHARACTER_STAT_INFO var2 = new REQ_CHARACTER_STAT_INFO();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.lightattribute = var1.readInt32();
            } else if (var5 == 16) {
               var2.iceattribute = var1.readInt32();
            } else if (var5 == 24) {
               var2.fireattribute = var1.readInt32();
            } else if (var5 == 32) {
               var2.darkattribute = var1.readInt32();
            } else if (var5 == 40) {
               var2.lightattributeResistance = var1.readInt32();
            } else if (var5 == 48) {
               var2.iceattributeResistance = var1.readInt32();
            } else if (var5 == 56) {
               var2.fireattributeResistance = var1.readInt32();
            } else if (var5 == 64) {
               var2.darkattributeResistance = var1.readInt32();
            } else if (var5 == 72) {
               var2.intelligencevalue = var1.readInt32();
            } else if (var5 == 80) {
               var2.powervalue = var1.readInt32();
            } else if (var5 == 88) {
               var2.strengthvalue = var1.readInt32();
            } else if (var5 == 96) {
               var2.spiritvalue = var1.readInt32();
            } else if (var5 == 104) {
               var2.magicattack = var1.readInt32();
            } else if (var5 == 112) {
               var2.magiccri = var1.readInt32();
            } else if (var5 == 120) {
               var2.magiccrirate = var1.readInt32();
            } else if (var5 == 128) {
               var2.physicalattack = var1.readInt32();
            } else if (var5 == 136) {
               var2.physicalcrit = var1.readInt32();
            } else if (var5 == 144) {
               var2.physicalcritrate = var1.readInt32();
            } else if (var5 == 152) {
               var2.attackspeed = var1.readInt32();
            } else if (var5 == 160) {
               var2.castingspeed = var1.readInt32();
            } else if (var5 == 168) {
               var2.movingspeed = var1.readInt32();
            } else if (var5 == 176) {
               var2.defyingdefenseattack = var1.readInt32();
            } else if (var5 == 184) {
               var2.accuracyrate = var1.readInt32();
            } else if (var5 == 192) {
               var2.accuracyvariablerate = var1.readInt32();
            } else if (var5 == 200) {
               var2.stuckvariablerate = var1.readInt32();
            } else if (var5 == 208) {
               var2.stuck = var1.readInt32();
            } else if (var5 == 216) {
               var2.hpregenrate = var1.readInt32();
            } else if (var5 == 224) {
               var2.mpregenrate = var1.readInt32();
            } else if (var5 == 232) {
               var2.ridgidity = var1.readInt32();
            } else if (var5 == 240) {
               var2.hitrecovery = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_CHARACTER_STAT_INFO.class);
         return this.descriptor = var1;
      }
   }
}
