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

public class USER_INFO$$JProtoBufClass implements Codec<USER_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(USER_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public USER_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(USER_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var15 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var15);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var16 = var1.job;
         var2 += CodedOutputStream.computeInt32Size(2, var16);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var17 = var1.growtype;
         var2 += CodedOutputStream.computeInt32Size(3, var17);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.secgrowtype)) {
         Integer var18 = var1.secgrowtype;
         var2 += CodedOutputStream.computeInt32Size(4, var18);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.teamType)) {
         Integer var19 = var1.teamType;
         var2 += CodedOutputStream.computeInt32Size(5, var19);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var20 = var1.world;
         var2 += CodedOutputStream.computeInt32Size(6, var20);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var21 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(7, var21);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var22 = var1.name;
         var2 += CodedOutputStream.computeStringSize(8, var22);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.profileURL)) {
         String var23 = var1.profileURL;
         var2 += CodedOutputStream.computeStringSize(9, var23);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.profileName)) {
         String var24 = var1.profileName;
         var2 += CodedOutputStream.computeStringSize(10, var24);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.characterFrame)) {
         Integer var25 = var1.characterFrame;
         var2 += CodedOutputStream.computeInt32Size(11, var25);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.rank)) {
         Integer var26 = var1.rank;
         var2 += CodedOutputStream.computeInt32Size(12, var26);
      }

      return var2;
   }

   public void doWriteTo(USER_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var15 = var1.charguid;
         if (var15 != null) {
            var2.writeUInt64(1, var15);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var16 = var1.job;
         if (var16 != null) {
            var2.writeInt32(2, var16);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var17 = var1.growtype;
         if (var17 != null) {
            var2.writeInt32(3, var17);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.secgrowtype)) {
         Integer var18 = var1.secgrowtype;
         if (var18 != null) {
            var2.writeInt32(4, var18);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.teamType)) {
         Integer var19 = var1.teamType;
         if (var19 != null) {
            var2.writeInt32(5, var19);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var20 = var1.world;
         if (var20 != null) {
            var2.writeInt32(6, var20);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var21 = var1.level;
         if (var21 != null) {
            var2.writeInt32(7, var21);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var22 = var1.name;
         if (var22 != null) {
            var2.writeString(8, var22);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.profileURL)) {
         String var23 = var1.profileURL;
         if (var23 != null) {
            var2.writeString(9, var23);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.profileName)) {
         String var24 = var1.profileName;
         if (var24 != null) {
            var2.writeString(10, var24);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.characterFrame)) {
         Integer var25 = var1.characterFrame;
         if (var25 != null) {
            var2.writeInt32(11, var25);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.rank)) {
         Integer var26 = var1.rank;
         if (var26 != null) {
            var2.writeInt32(12, var26);
         }
      }

   }

   public void writeTo(USER_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public USER_INFO readFrom(CodedInputStream var1) throws IOException {
      USER_INFO var2 = new USER_INFO();

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
               var2.job = var1.readInt32();
            } else if (var5 == 24) {
               var2.growtype = var1.readInt32();
            } else if (var5 == 32) {
               var2.secgrowtype = var1.readInt32();
            } else if (var5 == 40) {
               var2.teamType = var1.readInt32();
            } else if (var5 == 48) {
               var2.world = var1.readInt32();
            } else if (var5 == 56) {
               var2.level = var1.readInt32();
            } else if (var5 == 66) {
               var2.name = var1.readString();
            } else if (var5 == 74) {
               var2.profileURL = var1.readString();
            } else if (var5 == 82) {
               var2.profileName = var1.readString();
            } else if (var5 == 88) {
               var2.characterFrame = var1.readInt32();
            } else if (var5 == 96) {
               var2.rank = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(USER_INFO.class);
         return this.descriptor = var1;
      }
   }
}
