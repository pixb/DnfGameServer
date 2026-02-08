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

public class PT_RANK_MEMBER$$JProtoBufClass implements Codec<PT_RANK_MEMBER>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_RANK_MEMBER var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_RANK_MEMBER decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_RANK_MEMBER var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var15 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var15);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var16 = var1.growtype;
         var2 += CodedOutputStream.computeInt32Size(2, var16);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var17 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(3, var17);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var18 = var1.job;
         var2 += CodedOutputStream.computeInt32Size(4, var18);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.launchinfo)) {
         Integer var19 = var1.launchinfo;
         var2 += CodedOutputStream.computeInt32Size(5, var19);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var20 = var1.name;
         var2 += CodedOutputStream.computeStringSize(6, var20);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.profilename)) {
         String var21 = var1.profilename;
         var2 += CodedOutputStream.computeStringSize(7, var21);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.profileurl)) {
         String var22 = var1.profileurl;
         var2 += CodedOutputStream.computeStringSize(8, var22);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var23 = var1.secondgrowtype;
         var2 += CodedOutputStream.computeInt32Size(9, var23);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.vip)) {
         Integer var24 = var1.vip;
         var2 += CodedOutputStream.computeInt32Size(10, var24);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var25 = var1.guid;
         var2 += CodedOutputStream.computeUInt64Size(11, var25);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.characterframe)) {
         Integer var26 = var1.characterframe;
         var2 += CodedOutputStream.computeInt32Size(12, var26);
      }

      return var2;
   }

   public void doWriteTo(PT_RANK_MEMBER var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var15 = var1.charguid;
         if (var15 != null) {
            var2.writeUInt64(1, var15);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var16 = var1.growtype;
         if (var16 != null) {
            var2.writeInt32(2, var16);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var17 = var1.level;
         if (var17 != null) {
            var2.writeInt32(3, var17);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var18 = var1.job;
         if (var18 != null) {
            var2.writeInt32(4, var18);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.launchinfo)) {
         Integer var19 = var1.launchinfo;
         if (var19 != null) {
            var2.writeInt32(5, var19);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var20 = var1.name;
         if (var20 != null) {
            var2.writeString(6, var20);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.profilename)) {
         String var21 = var1.profilename;
         if (var21 != null) {
            var2.writeString(7, var21);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.profileurl)) {
         String var22 = var1.profileurl;
         if (var22 != null) {
            var2.writeString(8, var22);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var23 = var1.secondgrowtype;
         if (var23 != null) {
            var2.writeInt32(9, var23);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.vip)) {
         Integer var24 = var1.vip;
         if (var24 != null) {
            var2.writeInt32(10, var24);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var25 = var1.guid;
         if (var25 != null) {
            var2.writeUInt64(11, var25);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.characterframe)) {
         Integer var26 = var1.characterframe;
         if (var26 != null) {
            var2.writeInt32(12, var26);
         }
      }

   }

   public void writeTo(PT_RANK_MEMBER var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_RANK_MEMBER readFrom(CodedInputStream var1) throws IOException {
      PT_RANK_MEMBER var2 = new PT_RANK_MEMBER();

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
            } else if (var5 == 16) {
               var2.growtype = var1.readInt32();
            } else if (var5 == 24) {
               var2.level = var1.readInt32();
            } else if (var5 == 32) {
               var2.job = var1.readInt32();
            } else if (var5 == 40) {
               var2.launchinfo = var1.readInt32();
            } else if (var5 == 50) {
               var2.name = var1.readString();
            } else if (var5 == 58) {
               var2.profilename = var1.readString();
            } else if (var5 == 66) {
               var2.profileurl = var1.readString();
            } else if (var5 == 72) {
               var2.secondgrowtype = var1.readInt32();
            } else if (var5 == 80) {
               var2.vip = var1.readInt32();
            } else if (var5 == 88) {
               var2.guid = var1.readUInt64();
            } else if (var5 == 96) {
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_RANK_MEMBER.class);
         return this.descriptor = var1;
      }
   }
}
