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

public class PT_MEMBER_RESULT$$JProtoBufClass implements Codec<PT_MEMBER_RESULT>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_MEMBER_RESULT var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_MEMBER_RESULT decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_MEMBER_RESULT var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var19 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var19);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var20 = var1.name;
         var2 += CodedOutputStream.computeStringSize(2, var20);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var21 = var1.job;
         var2 += CodedOutputStream.computeInt32Size(3, var21);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var22 = var1.growtype;
         var2 += CodedOutputStream.computeInt32Size(4, var22);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.secgrowtype)) {
         Integer var23 = var1.secgrowtype;
         var2 += CodedOutputStream.computeInt32Size(5, var23);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var24 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(6, var24);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.dungeon)) {
         Integer var25 = var1.dungeon;
         var2 += CodedOutputStream.computeInt32Size(7, var25);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.pvp)) {
         Integer var26 = var1.pvp;
         var2 += CodedOutputStream.computeInt32Size(8, var26);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.sdpvp)) {
         Integer var27 = var1.sdpvp;
         var2 += CodedOutputStream.computeInt32Size(9, var27);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.dungeonpoint)) {
         Integer var28 = var1.dungeonpoint;
         var2 += CodedOutputStream.computeInt32Size(10, var28);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.pvppoint)) {
         Integer var29 = var1.pvppoint;
         var2 += CodedOutputStream.computeInt32Size(11, var29);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.sdpvppoint)) {
         Integer var30 = var1.sdpvppoint;
         var2 += CodedOutputStream.computeInt32Size(12, var30);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.characterframe)) {
         Integer var31 = var1.characterframe;
         var2 += CodedOutputStream.computeInt32Size(13, var31);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.staff)) {
         Boolean var32 = var1.staff;
         var2 += CodedOutputStream.computeBoolSize(14, var32);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.excavation)) {
         Integer var33 = var1.excavation;
         var2 += CodedOutputStream.computeInt32Size(15, var33);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.excavationpoint)) {
         Integer var34 = var1.excavationpoint;
         var2 += CodedOutputStream.computeInt32Size(16, var34);
      }

      return var2;
   }

   public void doWriteTo(PT_MEMBER_RESULT var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var19 = var1.charguid;
         if (var19 != null) {
            var2.writeUInt64(1, var19);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var20 = var1.name;
         if (var20 != null) {
            var2.writeString(2, var20);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var21 = var1.job;
         if (var21 != null) {
            var2.writeInt32(3, var21);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var22 = var1.growtype;
         if (var22 != null) {
            var2.writeInt32(4, var22);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.secgrowtype)) {
         Integer var23 = var1.secgrowtype;
         if (var23 != null) {
            var2.writeInt32(5, var23);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var24 = var1.level;
         if (var24 != null) {
            var2.writeInt32(6, var24);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.dungeon)) {
         Integer var25 = var1.dungeon;
         if (var25 != null) {
            var2.writeInt32(7, var25);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.pvp)) {
         Integer var26 = var1.pvp;
         if (var26 != null) {
            var2.writeInt32(8, var26);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.sdpvp)) {
         Integer var27 = var1.sdpvp;
         if (var27 != null) {
            var2.writeInt32(9, var27);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.dungeonpoint)) {
         Integer var28 = var1.dungeonpoint;
         if (var28 != null) {
            var2.writeInt32(10, var28);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.pvppoint)) {
         Integer var29 = var1.pvppoint;
         if (var29 != null) {
            var2.writeInt32(11, var29);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.sdpvppoint)) {
         Integer var30 = var1.sdpvppoint;
         if (var30 != null) {
            var2.writeInt32(12, var30);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.characterframe)) {
         Integer var31 = var1.characterframe;
         if (var31 != null) {
            var2.writeInt32(13, var31);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.staff)) {
         Boolean var32 = var1.staff;
         if (var32 != null) {
            var2.writeBool(14, var32);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.excavation)) {
         Integer var33 = var1.excavation;
         if (var33 != null) {
            var2.writeInt32(15, var33);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.excavationpoint)) {
         Integer var34 = var1.excavationpoint;
         if (var34 != null) {
            var2.writeInt32(16, var34);
         }
      }

   }

   public void writeTo(PT_MEMBER_RESULT var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_MEMBER_RESULT readFrom(CodedInputStream var1) throws IOException {
      PT_MEMBER_RESULT var2 = new PT_MEMBER_RESULT();

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
               var2.job = var1.readInt32();
            } else if (var5 == 32) {
               var2.growtype = var1.readInt32();
            } else if (var5 == 40) {
               var2.secgrowtype = var1.readInt32();
            } else if (var5 == 48) {
               var2.level = var1.readInt32();
            } else if (var5 == 56) {
               var2.dungeon = var1.readInt32();
            } else if (var5 == 64) {
               var2.pvp = var1.readInt32();
            } else if (var5 == 72) {
               var2.sdpvp = var1.readInt32();
            } else if (var5 == 80) {
               var2.dungeonpoint = var1.readInt32();
            } else if (var5 == 88) {
               var2.pvppoint = var1.readInt32();
            } else if (var5 == 96) {
               var2.sdpvppoint = var1.readInt32();
            } else if (var5 == 104) {
               var2.characterframe = var1.readInt32();
            } else if (var5 == 112) {
               var2.staff = var1.readBool();
            } else if (var5 == 120) {
               var2.excavation = var1.readInt32();
            } else if (var5 == 128) {
               var2.excavationpoint = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_MEMBER_RESULT.class);
         return this.descriptor = var1;
      }
   }
}
