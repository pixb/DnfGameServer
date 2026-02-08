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

public class RES_HALF_OPEN_PARTY_JOIN$$JProtoBufClass implements Codec<RES_HALF_OPEN_PARTY_JOIN>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_HALF_OPEN_PARTY_JOIN var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_HALF_OPEN_PARTY_JOIN decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_HALF_OPEN_PARTY_JOIN var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var16 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var16);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var17 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(2, var17);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var18 = var1.job;
         var2 += CodedOutputStream.computeInt32Size(3, var18);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var19 = var1.growtype;
         var2 += CodedOutputStream.computeInt32Size(4, var19);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var20 = var1.secondgrowtype;
         var2 += CodedOutputStream.computeInt32Size(5, var20);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.equipscore)) {
         Integer var21 = var1.equipscore;
         var2 += CodedOutputStream.computeInt32Size(6, var21);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var22 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(7, var22);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var23 = var1.name;
         var2 += CodedOutputStream.computeStringSize(8, var23);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.gname)) {
         String var24 = var1.gname;
         var2 += CodedOutputStream.computeStringSize(9, var24);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.creditscore)) {
         Integer var25 = var1.creditscore;
         var2 += CodedOutputStream.computeInt32Size(10, var25);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.chivalrygrade)) {
         Integer var26 = var1.chivalrygrade;
         var2 += CodedOutputStream.computeInt32Size(11, var26);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var27 = var1.dungeonindex;
         var2 += CodedOutputStream.computeInt32Size(12, var27);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.characterframe)) {
         Integer var28 = var1.characterframe;
         var2 += CodedOutputStream.computeInt32Size(13, var28);
      }

      return var2;
   }

   public void doWriteTo(RES_HALF_OPEN_PARTY_JOIN var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var16 = var1.error;
         if (var16 != null) {
            var2.writeInt32(1, var16);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var17 = var1.charguid;
         if (var17 != null) {
            var2.writeUInt64(2, var17);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var18 = var1.job;
         if (var18 != null) {
            var2.writeInt32(3, var18);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var19 = var1.growtype;
         if (var19 != null) {
            var2.writeInt32(4, var19);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var20 = var1.secondgrowtype;
         if (var20 != null) {
            var2.writeInt32(5, var20);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.equipscore)) {
         Integer var21 = var1.equipscore;
         if (var21 != null) {
            var2.writeInt32(6, var21);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var22 = var1.level;
         if (var22 != null) {
            var2.writeInt32(7, var22);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var23 = var1.name;
         if (var23 != null) {
            var2.writeString(8, var23);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.gname)) {
         String var24 = var1.gname;
         if (var24 != null) {
            var2.writeString(9, var24);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.creditscore)) {
         Integer var25 = var1.creditscore;
         if (var25 != null) {
            var2.writeInt32(10, var25);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.chivalrygrade)) {
         Integer var26 = var1.chivalrygrade;
         if (var26 != null) {
            var2.writeInt32(11, var26);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var27 = var1.dungeonindex;
         if (var27 != null) {
            var2.writeInt32(12, var27);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.characterframe)) {
         Integer var28 = var1.characterframe;
         if (var28 != null) {
            var2.writeInt32(13, var28);
         }
      }

   }

   public void writeTo(RES_HALF_OPEN_PARTY_JOIN var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_HALF_OPEN_PARTY_JOIN readFrom(CodedInputStream var1) throws IOException {
      RES_HALF_OPEN_PARTY_JOIN var2 = new RES_HALF_OPEN_PARTY_JOIN();

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
               var2.job = var1.readInt32();
            } else if (var5 == 32) {
               var2.growtype = var1.readInt32();
            } else if (var5 == 40) {
               var2.secondgrowtype = var1.readInt32();
            } else if (var5 == 48) {
               var2.equipscore = var1.readInt32();
            } else if (var5 == 56) {
               var2.level = var1.readInt32();
            } else if (var5 == 66) {
               var2.name = var1.readString();
            } else if (var5 == 74) {
               var2.gname = var1.readString();
            } else if (var5 == 80) {
               var2.creditscore = var1.readInt32();
            } else if (var5 == 88) {
               var2.chivalrygrade = var1.readInt32();
            } else if (var5 == 96) {
               var2.dungeonindex = var1.readInt32();
            } else if (var5 == 104) {
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_HALF_OPEN_PARTY_JOIN.class);
         return this.descriptor = var1;
      }
   }
}
