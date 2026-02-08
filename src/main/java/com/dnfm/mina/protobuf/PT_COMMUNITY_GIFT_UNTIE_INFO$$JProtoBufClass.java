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

public class PT_COMMUNITY_GIFT_UNTIE_INFO$$JProtoBufClass implements Codec<PT_COMMUNITY_GIFT_UNTIE_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_COMMUNITY_GIFT_UNTIE_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_COMMUNITY_GIFT_UNTIE_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_COMMUNITY_GIFT_UNTIE_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         String var17 = var1.index;
         var2 += CodedOutputStream.computeStringSize(1, var17);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var18 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(2, var18);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var19 = var1.name;
         var2 += CodedOutputStream.computeStringSize(3, var19);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var20 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(4, var20);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var21 = var1.job;
         var2 += CodedOutputStream.computeInt32Size(5, var21);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var22 = var1.growtype;
         var2 += CodedOutputStream.computeInt32Size(6, var22);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var23 = var1.secondgrowtype;
         var2 += CodedOutputStream.computeInt32Size(7, var23);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.itemindex)) {
         Integer var24 = var1.itemindex;
         var2 += CodedOutputStream.computeInt32Size(8, var24);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var25 = var1.count;
         var2 += CodedOutputStream.computeInt32Size(9, var25);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.charm)) {
         Long var26 = var1.charm;
         var2 += CodedOutputStream.computeInt64Size(10, var26);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.closenessscore)) {
         Long var27 = var1.closenessscore;
         var2 += CodedOutputStream.computeInt64Size(11, var27);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.date)) {
         Long var28 = var1.date;
         var2 += CodedOutputStream.computeUInt64Size(12, var28);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.check)) {
         Integer var29 = var1.check;
         var2 += CodedOutputStream.computeInt32Size(13, var29);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.msg)) {
         String var30 = var1.msg;
         var2 += CodedOutputStream.computeStringSize(14, var30);
      }

      return var2;
   }

   public void doWriteTo(PT_COMMUNITY_GIFT_UNTIE_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         String var17 = var1.index;
         if (var17 != null) {
            var2.writeString(1, var17);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var18 = var1.charguid;
         if (var18 != null) {
            var2.writeUInt64(2, var18);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var19 = var1.name;
         if (var19 != null) {
            var2.writeString(3, var19);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var20 = var1.level;
         if (var20 != null) {
            var2.writeInt32(4, var20);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var21 = var1.job;
         if (var21 != null) {
            var2.writeInt32(5, var21);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var22 = var1.growtype;
         if (var22 != null) {
            var2.writeInt32(6, var22);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var23 = var1.secondgrowtype;
         if (var23 != null) {
            var2.writeInt32(7, var23);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.itemindex)) {
         Integer var24 = var1.itemindex;
         if (var24 != null) {
            var2.writeInt32(8, var24);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var25 = var1.count;
         if (var25 != null) {
            var2.writeInt32(9, var25);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.charm)) {
         Long var26 = var1.charm;
         if (var26 != null) {
            var2.writeInt64(10, var26);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.closenessscore)) {
         Long var27 = var1.closenessscore;
         if (var27 != null) {
            var2.writeInt64(11, var27);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.date)) {
         Long var28 = var1.date;
         if (var28 != null) {
            var2.writeUInt64(12, var28);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.check)) {
         Integer var29 = var1.check;
         if (var29 != null) {
            var2.writeInt32(13, var29);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.msg)) {
         String var30 = var1.msg;
         if (var30 != null) {
            var2.writeString(14, var30);
         }
      }

   }

   public void writeTo(PT_COMMUNITY_GIFT_UNTIE_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_COMMUNITY_GIFT_UNTIE_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_COMMUNITY_GIFT_UNTIE_INFO var2 = new PT_COMMUNITY_GIFT_UNTIE_INFO();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 10) {
               var2.index = var1.readString();
            } else if (var5 == 16) {
               var2.charguid = var1.readUInt64();
            } else if (var5 == 26) {
               var2.name = var1.readString();
            } else if (var5 == 32) {
               var2.level = var1.readInt32();
            } else if (var5 == 40) {
               var2.job = var1.readInt32();
            } else if (var5 == 48) {
               var2.growtype = var1.readInt32();
            } else if (var5 == 56) {
               var2.secondgrowtype = var1.readInt32();
            } else if (var5 == 64) {
               var2.itemindex = var1.readInt32();
            } else if (var5 == 72) {
               var2.count = var1.readInt32();
            } else if (var5 == 80) {
               var2.charm = var1.readInt64();
            } else if (var5 == 88) {
               var2.closenessscore = var1.readInt64();
            } else if (var5 == 96) {
               var2.date = var1.readUInt64();
            } else if (var5 == 104) {
               var2.check = var1.readInt32();
            } else if (var5 == 114) {
               var2.msg = var1.readString();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_COMMUNITY_GIFT_UNTIE_INFO.class);
         return this.descriptor = var1;
      }
   }
}
