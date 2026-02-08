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

public class PT_MENTOR_INFO$$JProtoBufClass implements Codec<PT_MENTOR_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_MENTOR_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_MENTOR_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_MENTOR_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var21 = var1.guid;
         var2 += CodedOutputStream.computeUInt64Size(1, var21);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var22 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(2, var22);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var23 = var1.name;
         var2 += CodedOutputStream.computeStringSize(3, var23);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.introduce)) {
         String var24 = var1.introduce;
         var2 += CodedOutputStream.computeStringSize(4, var24);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var25 = var1.job;
         var2 += CodedOutputStream.computeInt32Size(5, var25);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var26 = var1.growtype;
         var2 += CodedOutputStream.computeInt32Size(6, var26);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var27 = var1.secondgrowtype;
         var2 += CodedOutputStream.computeInt32Size(7, var27);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.online)) {
         Integer var28 = var1.online;
         var2 += CodedOutputStream.computeInt32Size(8, var28);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.area)) {
         Integer var29 = var1.area;
         var2 += CodedOutputStream.computeInt32Size(9, var29);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.gender)) {
         Integer var30 = var1.gender;
         var2 += CodedOutputStream.computeInt32Size(10, var30);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.playingtime)) {
         Integer var31 = var1.playingtime;
         var2 += CodedOutputStream.computeInt32Size(11, var31);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.followercount)) {
         Integer var32 = var1.followercount;
         var2 += CodedOutputStream.computeInt32Size(12, var32);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.demanded)) {
         Integer var33 = var1.demanded;
         var2 += CodedOutputStream.computeInt32Size(13, var33);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.title)) {
         Integer var34 = var1.title;
         var2 += CodedOutputStream.computeInt32Size(14, var34);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.rank)) {
         Integer var35 = var1.rank;
         var2 += CodedOutputStream.computeInt32Size(15, var35);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.score)) {
         Integer var36 = var1.score;
         var2 += CodedOutputStream.computeInt32Size(16, var36);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.addmentorslot)) {
         Integer var37 = var1.addmentorslot;
         var2 += CodedOutputStream.computeInt32Size(17, var37);
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.mentordungeonticket)) {
         Integer var38 = var1.mentordungeonticket;
         var2 += CodedOutputStream.computeInt32Size(18, var38);
      }

      return var2;
   }

   public void doWriteTo(PT_MENTOR_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var21 = var1.guid;
         if (var21 != null) {
            var2.writeUInt64(1, var21);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var22 = var1.level;
         if (var22 != null) {
            var2.writeInt32(2, var22);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var23 = var1.name;
         if (var23 != null) {
            var2.writeString(3, var23);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.introduce)) {
         String var24 = var1.introduce;
         if (var24 != null) {
            var2.writeString(4, var24);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var25 = var1.job;
         if (var25 != null) {
            var2.writeInt32(5, var25);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var26 = var1.growtype;
         if (var26 != null) {
            var2.writeInt32(6, var26);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var27 = var1.secondgrowtype;
         if (var27 != null) {
            var2.writeInt32(7, var27);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.online)) {
         Integer var28 = var1.online;
         if (var28 != null) {
            var2.writeInt32(8, var28);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.area)) {
         Integer var29 = var1.area;
         if (var29 != null) {
            var2.writeInt32(9, var29);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.gender)) {
         Integer var30 = var1.gender;
         if (var30 != null) {
            var2.writeInt32(10, var30);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.playingtime)) {
         Integer var31 = var1.playingtime;
         if (var31 != null) {
            var2.writeInt32(11, var31);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.followercount)) {
         Integer var32 = var1.followercount;
         if (var32 != null) {
            var2.writeInt32(12, var32);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.demanded)) {
         Integer var33 = var1.demanded;
         if (var33 != null) {
            var2.writeInt32(13, var33);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.title)) {
         Integer var34 = var1.title;
         if (var34 != null) {
            var2.writeInt32(14, var34);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.rank)) {
         Integer var35 = var1.rank;
         if (var35 != null) {
            var2.writeInt32(15, var35);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.score)) {
         Integer var36 = var1.score;
         if (var36 != null) {
            var2.writeInt32(16, var36);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.addmentorslot)) {
         Integer var37 = var1.addmentorslot;
         if (var37 != null) {
            var2.writeInt32(17, var37);
         }
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.mentordungeonticket)) {
         Integer var38 = var1.mentordungeonticket;
         if (var38 != null) {
            var2.writeInt32(18, var38);
         }
      }

   }

   public void writeTo(PT_MENTOR_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_MENTOR_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_MENTOR_INFO var2 = new PT_MENTOR_INFO();

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
               var2.level = var1.readInt32();
            } else if (var5 == 26) {
               var2.name = var1.readString();
            } else if (var5 == 34) {
               var2.introduce = var1.readString();
            } else if (var5 == 40) {
               var2.job = var1.readInt32();
            } else if (var5 == 48) {
               var2.growtype = var1.readInt32();
            } else if (var5 == 56) {
               var2.secondgrowtype = var1.readInt32();
            } else if (var5 == 64) {
               var2.online = var1.readInt32();
            } else if (var5 == 72) {
               var2.area = var1.readInt32();
            } else if (var5 == 80) {
               var2.gender = var1.readInt32();
            } else if (var5 == 88) {
               var2.playingtime = var1.readInt32();
            } else if (var5 == 96) {
               var2.followercount = var1.readInt32();
            } else if (var5 == 104) {
               var2.demanded = var1.readInt32();
            } else if (var5 == 112) {
               var2.title = var1.readInt32();
            } else if (var5 == 120) {
               var2.rank = var1.readInt32();
            } else if (var5 == 128) {
               var2.score = var1.readInt32();
            } else if (var5 == 136) {
               var2.addmentorslot = var1.readInt32();
            } else if (var5 == 144) {
               var2.mentordungeonticket = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_MENTOR_INFO.class);
         return this.descriptor = var1;
      }
   }
}
