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

public class PT_GUILD_MEMBER$$JProtoBufClass implements Codec<PT_GUILD_MEMBER>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_GUILD_MEMBER var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_GUILD_MEMBER decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_GUILD_MEMBER var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.accountkey)) {
         Long var24 = var1.accountkey;
         var2 += CodedOutputStream.computeUInt64Size(1, var24);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var25 = var1.guid;
         var2 += CodedOutputStream.computeUInt64Size(2, var25);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.adventureunionname)) {
         String var26 = var1.adventureunionname;
         var2 += CodedOutputStream.computeStringSize(3, var26);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var27 = var1.name;
         var2 += CodedOutputStream.computeStringSize(4, var27);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var28 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(5, var28);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.gmembergrade)) {
         Integer var29 = var1.gmembergrade;
         var2 += CodedOutputStream.computeInt32Size(6, var29);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var30 = var1.job;
         var2 += CodedOutputStream.computeInt32Size(7, var30);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var31 = var1.growtype;
         var2 += CodedOutputStream.computeInt32Size(8, var31);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var32 = var1.secondgrowtype;
         var2 += CodedOutputStream.computeInt32Size(9, var32);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.online)) {
         Integer var33 = var1.online;
         var2 += CodedOutputStream.computeInt32Size(10, var33);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.lastlogout)) {
         Long var34 = var1.lastlogout;
         var2 += CodedOutputStream.computeUInt64Size(11, var34);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.agp)) {
         Integer var35 = var1.agp;
         var2 += CodedOutputStream.computeInt32Size(12, var35);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.equipscore)) {
         Integer var36 = var1.equipscore;
         var2 += CodedOutputStream.computeInt32Size(13, var36);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.mpoint)) {
         Integer var37 = var1.mpoint;
         var2 += CodedOutputStream.computeInt32Size(14, var37);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.attendance)) {
         Integer var38 = var1.attendance;
         var2 += CodedOutputStream.computeInt32Size(15, var38);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.ladate)) {
         Long var39 = var1.ladate;
         var2 += CodedOutputStream.computeInt64Size(16, var39);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.status)) {
         Integer var40 = var1.status;
         var2 += CodedOutputStream.computeInt32Size(17, var40);
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.regdate)) {
         Long var41 = var1.regdate;
         var2 += CodedOutputStream.computeInt64Size(18, var41);
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.creditscore)) {
         Integer var42 = var1.creditscore;
         var2 += CodedOutputStream.computeInt32Size(19, var42);
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.characterframe)) {
         Integer var43 = var1.characterframe;
         var2 += CodedOutputStream.computeInt32Size(20, var43);
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.dungeonparticiate)) {
         List var44 = var1.dungeonparticiate;
         var2 += CodedConstant.computeListSize(21, var44, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(PT_GUILD_MEMBER var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.accountkey)) {
         Long var24 = var1.accountkey;
         if (var24 != null) {
            var2.writeUInt64(1, var24);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var25 = var1.guid;
         if (var25 != null) {
            var2.writeUInt64(2, var25);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.adventureunionname)) {
         String var26 = var1.adventureunionname;
         if (var26 != null) {
            var2.writeString(3, var26);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var27 = var1.name;
         if (var27 != null) {
            var2.writeString(4, var27);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var28 = var1.level;
         if (var28 != null) {
            var2.writeInt32(5, var28);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.gmembergrade)) {
         Integer var29 = var1.gmembergrade;
         if (var29 != null) {
            var2.writeInt32(6, var29);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var30 = var1.job;
         if (var30 != null) {
            var2.writeInt32(7, var30);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var31 = var1.growtype;
         if (var31 != null) {
            var2.writeInt32(8, var31);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var32 = var1.secondgrowtype;
         if (var32 != null) {
            var2.writeInt32(9, var32);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.online)) {
         Integer var33 = var1.online;
         if (var33 != null) {
            var2.writeInt32(10, var33);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.lastlogout)) {
         Long var34 = var1.lastlogout;
         if (var34 != null) {
            var2.writeUInt64(11, var34);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.agp)) {
         Integer var35 = var1.agp;
         if (var35 != null) {
            var2.writeInt32(12, var35);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.equipscore)) {
         Integer var36 = var1.equipscore;
         if (var36 != null) {
            var2.writeInt32(13, var36);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.mpoint)) {
         Integer var37 = var1.mpoint;
         if (var37 != null) {
            var2.writeInt32(14, var37);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.attendance)) {
         Integer var38 = var1.attendance;
         if (var38 != null) {
            var2.writeInt32(15, var38);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.ladate)) {
         Long var39 = var1.ladate;
         if (var39 != null) {
            var2.writeInt64(16, var39);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.status)) {
         Integer var40 = var1.status;
         if (var40 != null) {
            var2.writeInt32(17, var40);
         }
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.regdate)) {
         Long var41 = var1.regdate;
         if (var41 != null) {
            var2.writeInt64(18, var41);
         }
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.creditscore)) {
         Integer var42 = var1.creditscore;
         if (var42 != null) {
            var2.writeInt32(19, var42);
         }
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.characterframe)) {
         Integer var43 = var1.characterframe;
         if (var43 != null) {
            var2.writeInt32(20, var43);
         }
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.dungeonparticiate)) {
         List var44 = var1.dungeonparticiate;
         if (var44 != null) {
            CodedConstant.writeToList(var2, 21, FieldType.OBJECT, var44, false);
         }
      }

   }

   public void writeTo(PT_GUILD_MEMBER var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_GUILD_MEMBER readFrom(CodedInputStream var1) throws IOException {
      PT_GUILD_MEMBER var2 = new PT_GUILD_MEMBER();
      var2.dungeonparticiate = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.accountkey = var1.readUInt64();
            } else if (var5 == 16) {
               var2.guid = var1.readUInt64();
            } else if (var5 == 26) {
               var2.adventureunionname = var1.readString();
            } else if (var5 == 34) {
               var2.name = var1.readString();
            } else if (var5 == 40) {
               var2.level = var1.readInt32();
            } else if (var5 == 48) {
               var2.gmembergrade = var1.readInt32();
            } else if (var5 == 56) {
               var2.job = var1.readInt32();
            } else if (var5 == 64) {
               var2.growtype = var1.readInt32();
            } else if (var5 == 72) {
               var2.secondgrowtype = var1.readInt32();
            } else if (var5 == 80) {
               var2.online = var1.readInt32();
            } else if (var5 == 88) {
               var2.lastlogout = var1.readUInt64();
            } else if (var5 == 96) {
               var2.agp = var1.readInt32();
            } else if (var5 == 104) {
               var2.equipscore = var1.readInt32();
            } else if (var5 == 112) {
               var2.mpoint = var1.readInt32();
            } else if (var5 == 120) {
               var2.attendance = var1.readInt32();
            } else if (var5 == 128) {
               var2.ladate = var1.readInt64();
            } else if (var5 == 136) {
               var2.status = var1.readInt32();
            } else if (var5 == 144) {
               var2.regdate = var1.readInt64();
            } else if (var5 == 152) {
               var2.creditscore = var1.readInt32();
            } else if (var5 == 160) {
               var2.characterframe = var1.readInt32();
            } else if (var5 == 170) {
               Codec var10 = ProtobufProxy.create(PT_DUNGEON_PARTICIATE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.dungeonparticiate == null) {
                  var2.dungeonparticiate = new ArrayList();
               }

               var2.dungeonparticiate.add((PT_DUNGEON_PARTICIATE)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_GUILD_MEMBER.class);
         return this.descriptor = var1;
      }
   }
}
