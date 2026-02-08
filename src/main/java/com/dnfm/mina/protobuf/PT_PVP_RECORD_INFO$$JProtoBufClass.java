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

public class PT_PVP_RECORD_INFO$$JProtoBufClass implements Codec<PT_PVP_RECORD_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_PVP_RECORD_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_PVP_RECORD_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_PVP_RECORD_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.consecutivewin)) {
         Integer var31 = var1.consecutivewin;
         var2 += CodedOutputStream.computeInt32Size(1, var31);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.draw)) {
         Integer var32 = var1.draw;
         var2 += CodedOutputStream.computeInt32Size(2, var32);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.lose)) {
         Integer var33 = var1.lose;
         var2 += CodedOutputStream.computeInt32Size(3, var33);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.maxconsecutivewin)) {
         Integer var34 = var1.maxconsecutivewin;
         var2 += CodedOutputStream.computeInt32Size(4, var34);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.maxpr)) {
         Integer var35 = var1.maxpr;
         var2 += CodedOutputStream.computeInt32Size(5, var35);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.performancerating)) {
         Integer var36 = var1.performancerating;
         var2 += CodedOutputStream.computeInt32Size(6, var36);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.ranking)) {
         Integer var37 = var1.ranking;
         var2 += CodedOutputStream.computeInt32Size(7, var37);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var38 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(8, var38);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.win)) {
         Integer var39 = var1.win;
         var2 += CodedOutputStream.computeInt32Size(9, var39);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.winpoint)) {
         Integer var40 = var1.winpoint;
         var2 += CodedOutputStream.computeInt32Size(10, var40);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.rank)) {
         Integer var41 = var1.rank;
         var2 += CodedOutputStream.computeInt32Size(11, var41);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.killcount)) {
         Integer var42 = var1.killcount;
         var2 += CodedOutputStream.computeInt32Size(12, var42);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.maxkillcount)) {
         Integer var43 = var1.maxkillcount;
         var2 += CodedOutputStream.computeInt32Size(13, var43);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.pvpstarcount)) {
         Integer var44 = var1.pvpstarcount;
         var2 += CodedOutputStream.computeInt32Size(14, var44);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.pvpstargrade)) {
         Integer var45 = var1.pvpstargrade;
         var2 += CodedOutputStream.computeInt32Size(15, var45);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.pvpstarmaxgrade)) {
         Integer var46 = var1.pvpstarmaxgrade;
         var2 += CodedOutputStream.computeInt32Size(16, var46);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.pvpstargradeold)) {
         Integer var47 = var1.pvpstargradeold;
         var2 += CodedOutputStream.computeInt32Size(17, var47);
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.placementstate)) {
         Integer var48 = var1.placementstate;
         var2 += CodedOutputStream.computeInt32Size(18, var48);
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.placementwin)) {
         Integer var49 = var1.placementwin;
         var2 += CodedOutputStream.computeInt32Size(19, var49);
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.placementlose)) {
         Integer var50 = var1.placementlose;
         var2 += CodedOutputStream.computeInt32Size(20, var50);
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.placementdraw)) {
         Integer var51 = var1.placementdraw;
         var2 += CodedOutputStream.computeInt32Size(21, var51);
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.entrancecount)) {
         Integer var52 = var1.entrancecount;
         var2 += CodedOutputStream.computeInt32Size(22, var52);
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.purchasecount)) {
         Integer var53 = var1.purchasecount;
         var2 += CodedOutputStream.computeInt32Size(23, var53);
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.glorypoint)) {
         Integer var54 = var1.glorypoint;
         var2 += CodedOutputStream.computeInt32Size(24, var54);
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.upanddowngradetype)) {
         Integer var55 = var1.upanddowngradetype;
         var2 += CodedOutputStream.computeInt32Size(25, var55);
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.upanddowngraderesults)) {
         List var56 = var1.upanddowngraderesults;
         var2 += CodedConstant.computeListSize(26, var56, FieldType.INT32, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.maxgradeinseason)) {
         Integer var57 = var1.maxgradeinseason;
         var2 += CodedOutputStream.computeInt32Size(27, var57);
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.rotationpvpday)) {
         Integer var58 = var1.rotationpvpday;
         var2 += CodedOutputStream.computeInt32Size(28, var58);
      }

      return var2;
   }

   public void doWriteTo(PT_PVP_RECORD_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.consecutivewin)) {
         Integer var31 = var1.consecutivewin;
         if (var31 != null) {
            var2.writeInt32(1, var31);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.draw)) {
         Integer var32 = var1.draw;
         if (var32 != null) {
            var2.writeInt32(2, var32);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.lose)) {
         Integer var33 = var1.lose;
         if (var33 != null) {
            var2.writeInt32(3, var33);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.maxconsecutivewin)) {
         Integer var34 = var1.maxconsecutivewin;
         if (var34 != null) {
            var2.writeInt32(4, var34);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.maxpr)) {
         Integer var35 = var1.maxpr;
         if (var35 != null) {
            var2.writeInt32(5, var35);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.performancerating)) {
         Integer var36 = var1.performancerating;
         if (var36 != null) {
            var2.writeInt32(6, var36);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.ranking)) {
         Integer var37 = var1.ranking;
         if (var37 != null) {
            var2.writeInt32(7, var37);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var38 = var1.type;
         if (var38 != null) {
            var2.writeInt32(8, var38);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.win)) {
         Integer var39 = var1.win;
         if (var39 != null) {
            var2.writeInt32(9, var39);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.winpoint)) {
         Integer var40 = var1.winpoint;
         if (var40 != null) {
            var2.writeInt32(10, var40);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.rank)) {
         Integer var41 = var1.rank;
         if (var41 != null) {
            var2.writeInt32(11, var41);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.killcount)) {
         Integer var42 = var1.killcount;
         if (var42 != null) {
            var2.writeInt32(12, var42);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.maxkillcount)) {
         Integer var43 = var1.maxkillcount;
         if (var43 != null) {
            var2.writeInt32(13, var43);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.pvpstarcount)) {
         Integer var44 = var1.pvpstarcount;
         if (var44 != null) {
            var2.writeInt32(14, var44);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.pvpstargrade)) {
         Integer var45 = var1.pvpstargrade;
         if (var45 != null) {
            var2.writeInt32(15, var45);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.pvpstarmaxgrade)) {
         Integer var46 = var1.pvpstarmaxgrade;
         if (var46 != null) {
            var2.writeInt32(16, var46);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.pvpstargradeold)) {
         Integer var47 = var1.pvpstargradeold;
         if (var47 != null) {
            var2.writeInt32(17, var47);
         }
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.placementstate)) {
         Integer var48 = var1.placementstate;
         if (var48 != null) {
            var2.writeInt32(18, var48);
         }
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.placementwin)) {
         Integer var49 = var1.placementwin;
         if (var49 != null) {
            var2.writeInt32(19, var49);
         }
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.placementlose)) {
         Integer var50 = var1.placementlose;
         if (var50 != null) {
            var2.writeInt32(20, var50);
         }
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.placementdraw)) {
         Integer var51 = var1.placementdraw;
         if (var51 != null) {
            var2.writeInt32(21, var51);
         }
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.entrancecount)) {
         Integer var52 = var1.entrancecount;
         if (var52 != null) {
            var2.writeInt32(22, var52);
         }
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.purchasecount)) {
         Integer var53 = var1.purchasecount;
         if (var53 != null) {
            var2.writeInt32(23, var53);
         }
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.glorypoint)) {
         Integer var54 = var1.glorypoint;
         if (var54 != null) {
            var2.writeInt32(24, var54);
         }
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.upanddowngradetype)) {
         Integer var55 = var1.upanddowngradetype;
         if (var55 != null) {
            var2.writeInt32(25, var55);
         }
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.upanddowngraderesults)) {
         List var56 = var1.upanddowngraderesults;
         if (var56 != null) {
            CodedConstant.writeToList(var2, 26, FieldType.INT32, var56, true);
         }
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.maxgradeinseason)) {
         Integer var57 = var1.maxgradeinseason;
         if (var57 != null) {
            var2.writeInt32(27, var57);
         }
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.rotationpvpday)) {
         Integer var58 = var1.rotationpvpday;
         if (var58 != null) {
            var2.writeInt32(28, var58);
         }
      }

   }

   public void writeTo(PT_PVP_RECORD_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_PVP_RECORD_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_PVP_RECORD_INFO var2 = new PT_PVP_RECORD_INFO();
      var2.upanddowngraderesults = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.consecutivewin = var1.readInt32();
            } else if (var5 == 16) {
               var2.draw = var1.readInt32();
            } else if (var5 == 24) {
               var2.lose = var1.readInt32();
            } else if (var5 == 32) {
               var2.maxconsecutivewin = var1.readInt32();
            } else if (var5 == 40) {
               var2.maxpr = var1.readInt32();
            } else if (var5 == 48) {
               var2.performancerating = var1.readInt32();
            } else if (var5 == 56) {
               var2.ranking = var1.readInt32();
            } else if (var5 == 64) {
               var2.type = var1.readInt32();
            } else if (var5 == 72) {
               var2.win = var1.readInt32();
            } else if (var5 == 80) {
               var2.winpoint = var1.readInt32();
            } else if (var5 == 88) {
               var2.rank = var1.readInt32();
            } else if (var5 == 96) {
               var2.killcount = var1.readInt32();
            } else if (var5 == 104) {
               var2.maxkillcount = var1.readInt32();
            } else if (var5 == 112) {
               var2.pvpstarcount = var1.readInt32();
            } else if (var5 == 120) {
               var2.pvpstargrade = var1.readInt32();
            } else if (var5 == 128) {
               var2.pvpstarmaxgrade = var1.readInt32();
            } else if (var5 == 136) {
               var2.pvpstargradeold = var1.readInt32();
            } else if (var5 == 144) {
               var2.placementstate = var1.readInt32();
            } else if (var5 == 152) {
               var2.placementwin = var1.readInt32();
            } else if (var5 == 160) {
               var2.placementlose = var1.readInt32();
            } else if (var5 == 168) {
               var2.placementdraw = var1.readInt32();
            } else if (var5 == 176) {
               var2.entrancecount = var1.readInt32();
            } else if (var5 == 184) {
               var2.purchasecount = var1.readInt32();
            } else if (var5 == 192) {
               var2.glorypoint = var1.readInt32();
            } else if (var5 == 200) {
               var2.upanddowngradetype = var1.readInt32();
            } else if (var5 == 208) {
               if (var2.upanddowngraderesults == null) {
                  var2.upanddowngraderesults = new ArrayList();
               }

               var2.upanddowngraderesults.add(var1.readInt32());
            } else if (var5 != 210) {
               if (var5 == 216) {
                  var2.maxgradeinseason = var1.readInt32();
               } else if (var5 == 224) {
                  var2.rotationpvpday = var1.readInt32();
               } else {
                  var1.skipField(var5);
               }
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.upanddowngraderesults == null) {
                  var2.upanddowngraderesults = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.upanddowngraderesults.add(var1.readInt32());
               }

               var1.popLimit(var7);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_PVP_RECORD_INFO.class);
         return this.descriptor = var1;
      }
   }
}
