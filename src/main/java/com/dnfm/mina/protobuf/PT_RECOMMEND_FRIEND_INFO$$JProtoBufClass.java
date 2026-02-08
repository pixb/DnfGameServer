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

public class PT_RECOMMEND_FRIEND_INFO$$JProtoBufClass implements Codec<PT_RECOMMEND_FRIEND_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_RECOMMEND_FRIEND_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_RECOMMEND_FRIEND_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_RECOMMEND_FRIEND_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.fguid)) {
         Long var14 = var1.fguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var14);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var15 = var1.name;
         var2 += CodedOutputStream.computeStringSize(2, var15);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var16 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(3, var16);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var17 = var1.job;
         var2 += CodedOutputStream.computeInt32Size(4, var17);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var18 = var1.growtype;
         var2 += CodedOutputStream.computeInt32Size(5, var18);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var19 = var1.secondgrowtype;
         var2 += CodedOutputStream.computeInt32Size(6, var19);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.creditscore)) {
         Integer var20 = var1.creditscore;
         var2 += CodedOutputStream.computeInt32Size(7, var20);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var21 = var1.world;
         var2 += CodedOutputStream.computeInt32Size(8, var21);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.gamecenterinfo)) {
         Integer var22 = var1.gamecenterinfo;
         var2 += CodedOutputStream.computeInt32Size(9, var22);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.qqVipInfo)) {
         Integer var23 = var1.qqVipInfo;
         var2 += CodedOutputStream.computeInt32Size(10, var23);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.characterframe)) {
         Integer var24 = var1.characterframe;
         var2 += CodedOutputStream.computeInt32Size(11, var24);
      }

      return var2;
   }

   public void doWriteTo(PT_RECOMMEND_FRIEND_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.fguid)) {
         Long var14 = var1.fguid;
         if (var14 != null) {
            var2.writeUInt64(1, var14);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var15 = var1.name;
         if (var15 != null) {
            var2.writeString(2, var15);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var16 = var1.level;
         if (var16 != null) {
            var2.writeInt32(3, var16);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var17 = var1.job;
         if (var17 != null) {
            var2.writeInt32(4, var17);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var18 = var1.growtype;
         if (var18 != null) {
            var2.writeInt32(5, var18);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var19 = var1.secondgrowtype;
         if (var19 != null) {
            var2.writeInt32(6, var19);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.creditscore)) {
         Integer var20 = var1.creditscore;
         if (var20 != null) {
            var2.writeInt32(7, var20);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var21 = var1.world;
         if (var21 != null) {
            var2.writeInt32(8, var21);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.gamecenterinfo)) {
         Integer var22 = var1.gamecenterinfo;
         if (var22 != null) {
            var2.writeInt32(9, var22);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.qqVipInfo)) {
         Integer var23 = var1.qqVipInfo;
         if (var23 != null) {
            var2.writeInt32(10, var23);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.characterframe)) {
         Integer var24 = var1.characterframe;
         if (var24 != null) {
            var2.writeInt32(11, var24);
         }
      }

   }

   public void writeTo(PT_RECOMMEND_FRIEND_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_RECOMMEND_FRIEND_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_RECOMMEND_FRIEND_INFO var2 = new PT_RECOMMEND_FRIEND_INFO();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.fguid = var1.readUInt64();
            } else if (var5 == 18) {
               var2.name = var1.readString();
            } else if (var5 == 24) {
               var2.level = var1.readInt32();
            } else if (var5 == 32) {
               var2.job = var1.readInt32();
            } else if (var5 == 40) {
               var2.growtype = var1.readInt32();
            } else if (var5 == 48) {
               var2.secondgrowtype = var1.readInt32();
            } else if (var5 == 56) {
               var2.creditscore = var1.readInt32();
            } else if (var5 == 64) {
               var2.world = var1.readInt32();
            } else if (var5 == 72) {
               var2.gamecenterinfo = var1.readInt32();
            } else if (var5 == 80) {
               var2.qqVipInfo = var1.readInt32();
            } else if (var5 == 88) {
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_RECOMMEND_FRIEND_INFO.class);
         return this.descriptor = var1;
      }
   }
}
