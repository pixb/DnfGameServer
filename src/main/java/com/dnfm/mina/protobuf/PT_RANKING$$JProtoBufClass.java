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

public class PT_RANKING$$JProtoBufClass implements Codec<PT_RANKING>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_RANKING var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_RANKING decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_RANKING var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var26 = var1.guid;
         var2 += CodedOutputStream.computeUInt64Size(1, var26);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var27 = var1.growtype;
         var2 += CodedOutputStream.computeInt32Size(2, var27);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var28 = var1.secondgrowtype;
         var2 += CodedOutputStream.computeInt32Size(3, var28);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var29 = var1.job;
         var2 += CodedOutputStream.computeInt32Size(4, var29);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var30 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(5, var30);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var31 = var1.name;
         var2 += CodedOutputStream.computeStringSize(6, var31);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.rank)) {
         Long var32 = var1.rank;
         var2 += CodedOutputStream.computeInt64Size(7, var32);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.score)) {
         Long var33 = var1.score;
         var2 += CodedOutputStream.computeUInt64Size(8, var33);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.gsymbol)) {
         List var34 = var1.gsymbol;
         var2 += CodedConstant.computeListSize(9, var34, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.gmastername)) {
         String var35 = var1.gmastername;
         var2 += CodedOutputStream.computeStringSize(10, var35);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.launchinfo)) {
         Integer var36 = var1.launchinfo;
         var2 += CodedOutputStream.computeInt32Size(11, var36);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.vip)) {
         Integer var37 = var1.vip;
         var2 += CodedOutputStream.computeInt32Size(12, var37);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.profileurl)) {
         String var38 = var1.profileurl;
         var2 += CodedOutputStream.computeStringSize(13, var38);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.profilename)) {
         String var39 = var1.profilename;
         var2 += CodedOutputStream.computeStringSize(14, var39);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.wincount)) {
         Integer var40 = var1.wincount;
         var2 += CodedOutputStream.computeInt32Size(15, var40);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.gwinpoint)) {
         Long var41 = var1.gwinpoint;
         var2 += CodedOutputStream.computeUInt64Size(16, var41);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.winningrate)) {
         Integer var42 = var1.winningrate;
         var2 += CodedOutputStream.computeInt32Size(17, var42);
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.spinfos)) {
         List var43 = var1.spinfos;
         var2 += CodedConstant.computeListSize(18, var43, FieldType.INT32, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.creditscore)) {
         Integer var44 = var1.creditscore;
         var2 += CodedOutputStream.computeInt32Size(19, var44);
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.characterframe)) {
         Integer var45 = var1.characterframe;
         var2 += CodedOutputStream.computeInt32Size(20, var45);
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.platform)) {
         Integer var46 = var1.platform;
         var2 += CodedOutputStream.computeInt32Size(21, var46);
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.platformserverid)) {
         Integer var47 = var1.platformserverid;
         var2 += CodedOutputStream.computeUInt32Size(22, var47);
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.gname)) {
         String var48 = var1.gname;
         var2 += CodedOutputStream.computeStringSize(23, var48);
      }

      return var2;
   }

   public void doWriteTo(PT_RANKING var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var26 = var1.guid;
         if (var26 != null) {
            var2.writeUInt64(1, var26);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var27 = var1.growtype;
         if (var27 != null) {
            var2.writeInt32(2, var27);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var28 = var1.secondgrowtype;
         if (var28 != null) {
            var2.writeInt32(3, var28);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var29 = var1.job;
         if (var29 != null) {
            var2.writeInt32(4, var29);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var30 = var1.level;
         if (var30 != null) {
            var2.writeInt32(5, var30);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var31 = var1.name;
         if (var31 != null) {
            var2.writeString(6, var31);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.rank)) {
         Long var32 = var1.rank;
         if (var32 != null) {
            var2.writeInt64(7, var32);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.score)) {
         Long var33 = var1.score;
         if (var33 != null) {
            var2.writeUInt64(8, var33);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.gsymbol)) {
         List var34 = var1.gsymbol;
         if (var34 != null) {
            CodedConstant.writeToList(var2, 9, FieldType.OBJECT, var34, false);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.gmastername)) {
         String var35 = var1.gmastername;
         if (var35 != null) {
            var2.writeString(10, var35);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.launchinfo)) {
         Integer var36 = var1.launchinfo;
         if (var36 != null) {
            var2.writeInt32(11, var36);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.vip)) {
         Integer var37 = var1.vip;
         if (var37 != null) {
            var2.writeInt32(12, var37);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.profileurl)) {
         String var38 = var1.profileurl;
         if (var38 != null) {
            var2.writeString(13, var38);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.profilename)) {
         String var39 = var1.profilename;
         if (var39 != null) {
            var2.writeString(14, var39);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.wincount)) {
         Integer var40 = var1.wincount;
         if (var40 != null) {
            var2.writeInt32(15, var40);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.gwinpoint)) {
         Long var41 = var1.gwinpoint;
         if (var41 != null) {
            var2.writeUInt64(16, var41);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.winningrate)) {
         Integer var42 = var1.winningrate;
         if (var42 != null) {
            var2.writeInt32(17, var42);
         }
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.spinfos)) {
         List var43 = var1.spinfos;
         if (var43 != null) {
            CodedConstant.writeToList(var2, 18, FieldType.INT32, var43, true);
         }
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.creditscore)) {
         Integer var44 = var1.creditscore;
         if (var44 != null) {
            var2.writeInt32(19, var44);
         }
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.characterframe)) {
         Integer var45 = var1.characterframe;
         if (var45 != null) {
            var2.writeInt32(20, var45);
         }
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.platform)) {
         Integer var46 = var1.platform;
         if (var46 != null) {
            var2.writeInt32(21, var46);
         }
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.platformserverid)) {
         Integer var47 = var1.platformserverid;
         if (var47 != null) {
            var2.writeUInt32(22, var47);
         }
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.gname)) {
         String var48 = var1.gname;
         if (var48 != null) {
            var2.writeString(23, var48);
         }
      }

   }

   public void writeTo(PT_RANKING var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_RANKING readFrom(CodedInputStream var1) throws IOException {
      PT_RANKING var2 = new PT_RANKING();
      var2.gsymbol = new ArrayList();
      var2.spinfos = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.guid = var1.readUInt64();
            } else if (var5 == 16) {
               var2.growtype = var1.readInt32();
            } else if (var5 == 24) {
               var2.secondgrowtype = var1.readInt32();
            } else if (var5 == 32) {
               var2.job = var1.readInt32();
            } else if (var5 == 40) {
               var2.level = var1.readInt32();
            } else if (var5 == 50) {
               var2.name = var1.readString();
            } else if (var5 == 56) {
               var2.rank = var1.readInt64();
            } else if (var5 == 64) {
               var2.score = var1.readUInt64();
            } else if (var5 == 74) {
               Codec var10 = ProtobufProxy.create(PT_GUILD_SYMBOL.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var11 = var1.readRawVarint32();
               int var12 = var1.pushLimit(var11);
               if (var2.gsymbol == null) {
                  var2.gsymbol = new ArrayList();
               }

               var2.gsymbol.add((PT_GUILD_SYMBOL)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var12);
            } else if (var5 == 82) {
               var2.gmastername = var1.readString();
            } else if (var5 == 88) {
               var2.launchinfo = var1.readInt32();
            } else if (var5 == 96) {
               var2.vip = var1.readInt32();
            } else if (var5 == 106) {
               var2.profileurl = var1.readString();
            } else if (var5 == 114) {
               var2.profilename = var1.readString();
            } else if (var5 == 120) {
               var2.wincount = var1.readInt32();
            } else if (var5 == 128) {
               var2.gwinpoint = var1.readUInt64();
            } else if (var5 == 136) {
               var2.winningrate = var1.readInt32();
            } else if (var5 == 144) {
               if (var2.spinfos == null) {
                  var2.spinfos = new ArrayList();
               }

               var2.spinfos.add(var1.readInt32());
            } else if (var5 != 146) {
               if (var5 == 152) {
                  var2.creditscore = var1.readInt32();
               } else if (var5 == 160) {
                  var2.characterframe = var1.readInt32();
               } else if (var5 == 168) {
                  var2.platform = var1.readInt32();
               } else if (var5 == 176) {
                  var2.platformserverid = var1.readUInt32();
               } else if (var5 == 186) {
                  var2.gname = var1.readString();
               } else {
                  var1.skipField(var5);
               }
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.spinfos == null) {
                  var2.spinfos = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.spinfos.add(var1.readInt32());
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_RANKING.class);
         return this.descriptor = var1;
      }
   }
}
