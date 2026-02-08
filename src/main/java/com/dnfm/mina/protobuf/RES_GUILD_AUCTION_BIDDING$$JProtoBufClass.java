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

public class RES_GUILD_AUCTION_BIDDING$$JProtoBufClass implements Codec<RES_GUILD_AUCTION_BIDDING>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_GUILD_AUCTION_BIDDING var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_GUILD_AUCTION_BIDDING decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_GUILD_AUCTION_BIDDING var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var12 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var12);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var13 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(2, var13);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var14 = var1.guid;
         var2 += CodedOutputStream.computeUInt64Size(3, var14);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.date)) {
         Long var15 = var1.date;
         var2 += CodedOutputStream.computeUInt64Size(4, var15);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.itemindex)) {
         Integer var16 = var1.itemindex;
         var2 += CodedOutputStream.computeInt32Size(5, var16);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var17 = var1.count;
         var2 += CodedOutputStream.computeInt32Size(6, var17);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.price)) {
         Integer var18 = var1.price;
         var2 += CodedOutputStream.computeInt32Size(7, var18);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.quality)) {
         Integer var19 = var1.quality;
         var2 += CodedOutputStream.computeInt32Size(8, var19);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var20 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(9, var20);
      }

      return var2;
   }

   public void doWriteTo(RES_GUILD_AUCTION_BIDDING var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var12 = var1.error;
         if (var12 != null) {
            var2.writeInt32(1, var12);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var13 = var1.type;
         if (var13 != null) {
            var2.writeInt32(2, var13);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var14 = var1.guid;
         if (var14 != null) {
            var2.writeUInt64(3, var14);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.date)) {
         Long var15 = var1.date;
         if (var15 != null) {
            var2.writeUInt64(4, var15);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.itemindex)) {
         Integer var16 = var1.itemindex;
         if (var16 != null) {
            var2.writeInt32(5, var16);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var17 = var1.count;
         if (var17 != null) {
            var2.writeInt32(6, var17);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.price)) {
         Integer var18 = var1.price;
         if (var18 != null) {
            var2.writeInt32(7, var18);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.quality)) {
         Integer var19 = var1.quality;
         if (var19 != null) {
            var2.writeInt32(8, var19);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var20 = var1.charguid;
         if (var20 != null) {
            var2.writeUInt64(9, var20);
         }
      }

   }

   public void writeTo(RES_GUILD_AUCTION_BIDDING var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_GUILD_AUCTION_BIDDING readFrom(CodedInputStream var1) throws IOException {
      RES_GUILD_AUCTION_BIDDING var2 = new RES_GUILD_AUCTION_BIDDING();

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
               var2.type = var1.readInt32();
            } else if (var5 == 24) {
               var2.guid = var1.readUInt64();
            } else if (var5 == 32) {
               var2.date = var1.readUInt64();
            } else if (var5 == 40) {
               var2.itemindex = var1.readInt32();
            } else if (var5 == 48) {
               var2.count = var1.readInt32();
            } else if (var5 == 56) {
               var2.price = var1.readInt32();
            } else if (var5 == 64) {
               var2.quality = var1.readInt32();
            } else if (var5 == 72) {
               var2.charguid = var1.readUInt64();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_GUILD_AUCTION_BIDDING.class);
         return this.descriptor = var1;
      }
   }
}
