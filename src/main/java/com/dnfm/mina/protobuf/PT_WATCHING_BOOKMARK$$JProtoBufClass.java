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

public class PT_WATCHING_BOOKMARK$$JProtoBufClass implements Codec<PT_WATCHING_BOOKMARK>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_WATCHING_BOOKMARK var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_WATCHING_BOOKMARK decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_WATCHING_BOOKMARK var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var14 = var1.guid;
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
      if (!CodedConstant.isNull(var1.characterframe)) {
         Integer var20 = var1.characterframe;
         var2 += CodedOutputStream.computeInt32Size(7, var20);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.profileurl)) {
         String var21 = var1.profileurl;
         var2 += CodedOutputStream.computeStringSize(8, var21);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.profilename)) {
         String var22 = var1.profilename;
         var2 += CodedOutputStream.computeStringSize(9, var22);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.playtime)) {
         Long var23 = var1.playtime;
         var2 += CodedOutputStream.computeInt64Size(10, var23);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.lastpvpmatch)) {
         Integer var24 = var1.lastpvpmatch;
         var2 += CodedOutputStream.computeInt32Size(11, var24);
      }

      return var2;
   }

   public void doWriteTo(PT_WATCHING_BOOKMARK var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var14 = var1.guid;
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
      if (!CodedConstant.isNull(var1.characterframe)) {
         Integer var20 = var1.characterframe;
         if (var20 != null) {
            var2.writeInt32(7, var20);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.profileurl)) {
         String var21 = var1.profileurl;
         if (var21 != null) {
            var2.writeString(8, var21);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.profilename)) {
         String var22 = var1.profilename;
         if (var22 != null) {
            var2.writeString(9, var22);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.playtime)) {
         Long var23 = var1.playtime;
         if (var23 != null) {
            var2.writeInt64(10, var23);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.lastpvpmatch)) {
         Integer var24 = var1.lastpvpmatch;
         if (var24 != null) {
            var2.writeInt32(11, var24);
         }
      }

   }

   public void writeTo(PT_WATCHING_BOOKMARK var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_WATCHING_BOOKMARK readFrom(CodedInputStream var1) throws IOException {
      PT_WATCHING_BOOKMARK var2 = new PT_WATCHING_BOOKMARK();

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
               var2.characterframe = var1.readInt32();
            } else if (var5 == 66) {
               var2.profileurl = var1.readString();
            } else if (var5 == 74) {
               var2.profilename = var1.readString();
            } else if (var5 == 80) {
               var2.playtime = var1.readInt64();
            } else if (var5 == 88) {
               var2.lastpvpmatch = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_WATCHING_BOOKMARK.class);
         return this.descriptor = var1;
      }
   }
}
