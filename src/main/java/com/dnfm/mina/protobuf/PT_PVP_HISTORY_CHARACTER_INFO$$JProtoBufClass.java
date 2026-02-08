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

public class PT_PVP_HISTORY_CHARACTER_INFO$$JProtoBufClass implements Codec<PT_PVP_HISTORY_CHARACTER_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_PVP_HISTORY_CHARACTER_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_PVP_HISTORY_CHARACTER_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_PVP_HISTORY_CHARACTER_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var14 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var14);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.equipscore)) {
         Integer var15 = var1.equipscore;
         var2 += CodedOutputStream.computeInt32Size(2, var15);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var16 = var1.growtype;
         var2 += CodedOutputStream.computeInt32Size(3, var16);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.secgrowtype)) {
         Integer var17 = var1.secgrowtype;
         var2 += CodedOutputStream.computeInt32Size(4, var17);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var18 = var1.name;
         var2 += CodedOutputStream.computeStringSize(5, var18);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var19 = var1.job;
         var2 += CodedOutputStream.computeInt32Size(6, var19);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var20 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(7, var20);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.teamtype)) {
         Integer var21 = var1.teamtype;
         var2 += CodedOutputStream.computeInt32Size(8, var21);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.characterframe)) {
         Integer var22 = var1.characterframe;
         var2 += CodedOutputStream.computeInt32Size(9, var22);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.accountkey)) {
         Long var23 = var1.accountkey;
         var2 += CodedOutputStream.computeUInt64Size(10, var23);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.selectcharindex)) {
         Integer var24 = var1.selectcharindex;
         var2 += CodedOutputStream.computeInt32Size(11, var24);
      }

      return var2;
   }

   public void doWriteTo(PT_PVP_HISTORY_CHARACTER_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var14 = var1.charguid;
         if (var14 != null) {
            var2.writeUInt64(1, var14);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.equipscore)) {
         Integer var15 = var1.equipscore;
         if (var15 != null) {
            var2.writeInt32(2, var15);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var16 = var1.growtype;
         if (var16 != null) {
            var2.writeInt32(3, var16);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.secgrowtype)) {
         Integer var17 = var1.secgrowtype;
         if (var17 != null) {
            var2.writeInt32(4, var17);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var18 = var1.name;
         if (var18 != null) {
            var2.writeString(5, var18);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var19 = var1.job;
         if (var19 != null) {
            var2.writeInt32(6, var19);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var20 = var1.level;
         if (var20 != null) {
            var2.writeInt32(7, var20);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.teamtype)) {
         Integer var21 = var1.teamtype;
         if (var21 != null) {
            var2.writeInt32(8, var21);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.characterframe)) {
         Integer var22 = var1.characterframe;
         if (var22 != null) {
            var2.writeInt32(9, var22);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.accountkey)) {
         Long var23 = var1.accountkey;
         if (var23 != null) {
            var2.writeUInt64(10, var23);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.selectcharindex)) {
         Integer var24 = var1.selectcharindex;
         if (var24 != null) {
            var2.writeInt32(11, var24);
         }
      }

   }

   public void writeTo(PT_PVP_HISTORY_CHARACTER_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_PVP_HISTORY_CHARACTER_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_PVP_HISTORY_CHARACTER_INFO var2 = new PT_PVP_HISTORY_CHARACTER_INFO();

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
               var2.equipscore = var1.readInt32();
            } else if (var5 == 24) {
               var2.growtype = var1.readInt32();
            } else if (var5 == 32) {
               var2.secgrowtype = var1.readInt32();
            } else if (var5 == 42) {
               var2.name = var1.readString();
            } else if (var5 == 48) {
               var2.job = var1.readInt32();
            } else if (var5 == 56) {
               var2.level = var1.readInt32();
            } else if (var5 == 64) {
               var2.teamtype = var1.readInt32();
            } else if (var5 == 72) {
               var2.characterframe = var1.readInt32();
            } else if (var5 == 80) {
               var2.accountkey = var1.readUInt64();
            } else if (var5 == 88) {
               var2.selectcharindex = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_PVP_HISTORY_CHARACTER_INFO.class);
         return this.descriptor = var1;
      }
   }
}
