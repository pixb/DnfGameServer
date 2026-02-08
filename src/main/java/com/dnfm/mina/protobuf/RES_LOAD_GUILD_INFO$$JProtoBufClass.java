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

public class RES_LOAD_GUILD_INFO$$JProtoBufClass implements Codec<RES_LOAD_GUILD_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_LOAD_GUILD_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_LOAD_GUILD_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_LOAD_GUILD_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var33 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var33);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.gname)) {
         String var34 = var1.gname;
         var2 += CodedOutputStream.computeStringSize(2, var34);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.gsymbol)) {
         List var35 = var1.gsymbol;
         var2 += CodedConstant.computeListSize(3, var35, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.gmaster)) {
         Long var36 = var1.gmaster;
         var2 += CodedOutputStream.computeUInt64Size(4, var36);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.gmastername)) {
         String var37 = var1.gmastername;
         var2 += CodedOutputStream.computeStringSize(5, var37);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.gmasteropenid)) {
         String var38 = var1.gmasteropenid;
         var2 += CodedOutputStream.computeStringSize(6, var38);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.glevel)) {
         Integer var39 = var1.glevel;
         var2 += CodedOutputStream.computeInt32Size(7, var39);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.gexp)) {
         Integer var40 = var1.gexp;
         var2 += CodedOutputStream.computeInt32Size(8, var40);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.gboard)) {
         String var41 = var1.gboard;
         var2 += CodedOutputStream.computeStringSize(9, var41);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.gbulletin)) {
         String var42 = var1.gbulletin;
         var2 += CodedOutputStream.computeStringSize(10, var42);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.gpoint)) {
         Integer var43 = var1.gpoint;
         var2 += CodedOutputStream.computeInt32Size(11, var43);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.groupopenid)) {
         String var44 = var1.groupopenid;
         var2 += CodedOutputStream.computeStringSize(12, var44);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.bufflist)) {
         List var45 = var1.bufflist;
         var2 += CodedConstant.computeListSize(13, var45, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.kcount)) {
         Integer var46 = var1.kcount;
         var2 += CodedOutputStream.computeInt32Size(14, var46);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.gwranking)) {
         Integer var47 = var1.gwranking;
         var2 += CodedOutputStream.computeInt32Size(15, var47);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.gcranking)) {
         Integer var48 = var1.gcranking;
         var2 += CodedOutputStream.computeInt32Size(16, var48);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.grranking)) {
         Integer var49 = var1.grranking;
         var2 += CodedOutputStream.computeInt32Size(17, var49);
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.giranking)) {
         Integer var50 = var1.giranking;
         var2 += CodedOutputStream.computeInt32Size(18, var50);
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.gcrankingscore)) {
         Integer var51 = var1.gcrankingscore;
         var2 += CodedOutputStream.computeInt32Size(19, var51);
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.girankingscore)) {
         Integer var52 = var1.girankingscore;
         var2 += CodedOutputStream.computeInt32Size(20, var52);
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var53 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(21, var53);
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.freejoin)) {
         Boolean var54 = var1.freejoin;
         var2 += CodedOutputStream.computeBoolSize(22, var54);
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.area)) {
         Integer var55 = var1.area;
         var2 += CodedOutputStream.computeInt32Size(23, var55);
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.minlevel)) {
         Integer var56 = var1.minlevel;
         var2 += CodedOutputStream.computeInt32Size(24, var56);
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.gattendance)) {
         Integer var57 = var1.gattendance;
         var2 += CodedOutputStream.computeInt32Size(25, var57);
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.wadate)) {
         Long var58 = var1.wadate;
         var2 += CodedOutputStream.computeInt64Size(26, var58);
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.gguid)) {
         Long var59 = var1.gguid;
         var2 += CodedOutputStream.computeInt64Size(27, var59);
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.garewards)) {
         List var60 = var1.garewards;
         var2 += CodedConstant.computeListSize(28, var60, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var31 = null;
      if (!CodedConstant.isNull(var1.accbattleleaguevalue)) {
         Integer var61 = var1.accbattleleaguevalue;
         var2 += CodedOutputStream.computeInt32Size(29, var61);
      }

      Object var32 = null;
      if (!CodedConstant.isNull(var1.yesterdayGattendance)) {
         Integer var62 = var1.yesterdayGattendance;
         var2 += CodedOutputStream.computeInt32Size(30, var62);
      }

      return var2;
   }

   public void doWriteTo(RES_LOAD_GUILD_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var33 = var1.error;
         if (var33 != null) {
            var2.writeInt32(1, var33);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.gname)) {
         String var34 = var1.gname;
         if (var34 != null) {
            var2.writeString(2, var34);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.gsymbol)) {
         List var35 = var1.gsymbol;
         if (var35 != null) {
            CodedConstant.writeToList(var2, 3, FieldType.OBJECT, var35, false);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.gmaster)) {
         Long var36 = var1.gmaster;
         if (var36 != null) {
            var2.writeUInt64(4, var36);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.gmastername)) {
         String var37 = var1.gmastername;
         if (var37 != null) {
            var2.writeString(5, var37);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.gmasteropenid)) {
         String var38 = var1.gmasteropenid;
         if (var38 != null) {
            var2.writeString(6, var38);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.glevel)) {
         Integer var39 = var1.glevel;
         if (var39 != null) {
            var2.writeInt32(7, var39);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.gexp)) {
         Integer var40 = var1.gexp;
         if (var40 != null) {
            var2.writeInt32(8, var40);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.gboard)) {
         String var41 = var1.gboard;
         if (var41 != null) {
            var2.writeString(9, var41);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.gbulletin)) {
         String var42 = var1.gbulletin;
         if (var42 != null) {
            var2.writeString(10, var42);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.gpoint)) {
         Integer var43 = var1.gpoint;
         if (var43 != null) {
            var2.writeInt32(11, var43);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.groupopenid)) {
         String var44 = var1.groupopenid;
         if (var44 != null) {
            var2.writeString(12, var44);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.bufflist)) {
         List var45 = var1.bufflist;
         if (var45 != null) {
            CodedConstant.writeToList(var2, 13, FieldType.OBJECT, var45, false);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.kcount)) {
         Integer var46 = var1.kcount;
         if (var46 != null) {
            var2.writeInt32(14, var46);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.gwranking)) {
         Integer var47 = var1.gwranking;
         if (var47 != null) {
            var2.writeInt32(15, var47);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.gcranking)) {
         Integer var48 = var1.gcranking;
         if (var48 != null) {
            var2.writeInt32(16, var48);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.grranking)) {
         Integer var49 = var1.grranking;
         if (var49 != null) {
            var2.writeInt32(17, var49);
         }
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.giranking)) {
         Integer var50 = var1.giranking;
         if (var50 != null) {
            var2.writeInt32(18, var50);
         }
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.gcrankingscore)) {
         Integer var51 = var1.gcrankingscore;
         if (var51 != null) {
            var2.writeInt32(19, var51);
         }
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.girankingscore)) {
         Integer var52 = var1.girankingscore;
         if (var52 != null) {
            var2.writeInt32(20, var52);
         }
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var53 = var1.type;
         if (var53 != null) {
            var2.writeInt32(21, var53);
         }
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.freejoin)) {
         Boolean var54 = var1.freejoin;
         if (var54 != null) {
            var2.writeBool(22, var54);
         }
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.area)) {
         Integer var55 = var1.area;
         if (var55 != null) {
            var2.writeInt32(23, var55);
         }
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.minlevel)) {
         Integer var56 = var1.minlevel;
         if (var56 != null) {
            var2.writeInt32(24, var56);
         }
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.gattendance)) {
         Integer var57 = var1.gattendance;
         if (var57 != null) {
            var2.writeInt32(25, var57);
         }
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.wadate)) {
         Long var58 = var1.wadate;
         if (var58 != null) {
            var2.writeInt64(26, var58);
         }
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.gguid)) {
         Long var59 = var1.gguid;
         if (var59 != null) {
            var2.writeInt64(27, var59);
         }
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.garewards)) {
         List var60 = var1.garewards;
         if (var60 != null) {
            CodedConstant.writeToList(var2, 28, FieldType.OBJECT, var60, false);
         }
      }

      Object var31 = null;
      if (!CodedConstant.isNull(var1.accbattleleaguevalue)) {
         Integer var61 = var1.accbattleleaguevalue;
         if (var61 != null) {
            var2.writeInt32(29, var61);
         }
      }

      Object var32 = null;
      if (!CodedConstant.isNull(var1.yesterdayGattendance)) {
         Integer var62 = var1.yesterdayGattendance;
         if (var62 != null) {
            var2.writeInt32(30, var62);
         }
      }

   }

   public void writeTo(RES_LOAD_GUILD_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_LOAD_GUILD_INFO readFrom(CodedInputStream var1) throws IOException {
      RES_LOAD_GUILD_INFO var2 = new RES_LOAD_GUILD_INFO();
      var2.gsymbol = new ArrayList();
      var2.bufflist = new ArrayList();
      var2.garewards = new ArrayList();

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
            } else if (var5 == 18) {
               var2.gname = var1.readString();
            } else if (var5 == 26) {
               Codec var10 = ProtobufProxy.create(PT_GUILD_SYMBOL.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.gsymbol == null) {
                  var2.gsymbol = new ArrayList();
               }

               var2.gsymbol.add((PT_GUILD_SYMBOL)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 32) {
               var2.gmaster = var1.readUInt64();
            } else if (var5 == 42) {
               var2.gmastername = var1.readString();
            } else if (var5 == 50) {
               var2.gmasteropenid = var1.readString();
            } else if (var5 == 56) {
               var2.glevel = var1.readInt32();
            } else if (var5 == 64) {
               var2.gexp = var1.readInt32();
            } else if (var5 == 74) {
               var2.gboard = var1.readString();
            } else if (var5 == 82) {
               var2.gbulletin = var1.readString();
            } else if (var5 == 88) {
               var2.gpoint = var1.readInt32();
            } else if (var5 == 98) {
               var2.groupopenid = var1.readString();
            } else if (var5 == 106) {
               Codec var11 = ProtobufProxy.create(PT_USABLE_FACILITY_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var13 = var1.readRawVarint32();
               int var15 = var1.pushLimit(var13);
               if (var2.bufflist == null) {
                  var2.bufflist = new ArrayList();
               }

               var2.bufflist.add((PT_USABLE_FACILITY_INFO)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var15);
            } else if (var5 == 112) {
               var2.kcount = var1.readInt32();
            } else if (var5 == 120) {
               var2.gwranking = var1.readInt32();
            } else if (var5 == 128) {
               var2.gcranking = var1.readInt32();
            } else if (var5 == 136) {
               var2.grranking = var1.readInt32();
            } else if (var5 == 144) {
               var2.giranking = var1.readInt32();
            } else if (var5 == 152) {
               var2.gcrankingscore = var1.readInt32();
            } else if (var5 == 160) {
               var2.girankingscore = var1.readInt32();
            } else if (var5 == 168) {
               var2.type = var1.readInt32();
            } else if (var5 == 176) {
               var2.freejoin = var1.readBool();
            } else if (var5 == 184) {
               var2.area = var1.readInt32();
            } else if (var5 == 192) {
               var2.minlevel = var1.readInt32();
            } else if (var5 == 200) {
               var2.gattendance = var1.readInt32();
            } else if (var5 == 208) {
               var2.wadate = var1.readInt64();
            } else if (var5 == 216) {
               var2.gguid = var1.readInt64();
            } else if (var5 == 226) {
               Codec var12 = ProtobufProxy.create(PT_GUILD_ATTEND_REWARD.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var14 = var1.readRawVarint32();
               int var16 = var1.pushLimit(var14);
               if (var2.garewards == null) {
                  var2.garewards = new ArrayList();
               }

               var2.garewards.add((PT_GUILD_ATTEND_REWARD)var12.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var16);
            } else if (var5 == 232) {
               var2.accbattleleaguevalue = var1.readInt32();
            } else if (var5 == 240) {
               var2.yesterdayGattendance = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_LOAD_GUILD_INFO.class);
         return this.descriptor = var1;
      }
   }
}
