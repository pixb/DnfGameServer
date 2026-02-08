package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.baidu.bjf.remoting.protobuf.code.CodedConstant;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.InvalidProtocolBufferException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PT_GUILD_AUCTION_ITEM$$JProtoBufClass implements Codec<PT_GUILD_AUCTION_ITEM>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_GUILD_AUCTION_ITEM var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_GUILD_AUCTION_ITEM decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_GUILD_AUCTION_ITEM var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var13 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var13);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.content)) {
         Integer var14 = var1.content;
         var2 += CodedOutputStream.computeInt32Size(2, var14);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.contentvalue)) {
         Integer var15 = var1.contentvalue;
         var2 += CodedOutputStream.computeInt32Size(3, var15);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var16 = var1.count;
         var2 += CodedOutputStream.computeInt32Size(4, var16);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.date)) {
         Long var17 = var1.date;
         var2 += CodedOutputStream.computeUInt64Size(5, var17);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var18 = var1.guid;
         var2 += CodedOutputStream.computeUInt64Size(6, var18);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.itemindex)) {
         Integer var19 = var1.itemindex;
         var2 += CodedOutputStream.computeInt32Size(7, var19);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.list)) {
         List var20 = var1.list;
         var2 += CodedConstant.computeListSize(8, var20, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.price)) {
         Integer var21 = var1.price;
         var2 += CodedOutputStream.computeInt32Size(9, var21);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.quality)) {
         Integer var22 = var1.quality;
         var2 += CodedOutputStream.computeInt32Size(10, var22);
      }

      return var2;
   }

   public void doWriteTo(PT_GUILD_AUCTION_ITEM var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var13 = var1.charguid;
         if (var13 != null) {
            var2.writeUInt64(1, var13);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.content)) {
         Integer var14 = var1.content;
         if (var14 != null) {
            var2.writeInt32(2, var14);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.contentvalue)) {
         Integer var15 = var1.contentvalue;
         if (var15 != null) {
            var2.writeInt32(3, var15);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var16 = var1.count;
         if (var16 != null) {
            var2.writeInt32(4, var16);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.date)) {
         Long var17 = var1.date;
         if (var17 != null) {
            var2.writeUInt64(5, var17);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var18 = var1.guid;
         if (var18 != null) {
            var2.writeUInt64(6, var18);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.itemindex)) {
         Integer var19 = var1.itemindex;
         if (var19 != null) {
            var2.writeInt32(7, var19);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.list)) {
         List var20 = var1.list;
         if (var20 != null) {
            CodedConstant.writeToList(var2, 8, FieldType.OBJECT, var20, false);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.price)) {
         Integer var21 = var1.price;
         if (var21 != null) {
            var2.writeInt32(9, var21);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.quality)) {
         Integer var22 = var1.quality;
         if (var22 != null) {
            var2.writeInt32(10, var22);
         }
      }

   }

   public void writeTo(PT_GUILD_AUCTION_ITEM var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_GUILD_AUCTION_ITEM readFrom(CodedInputStream var1) throws IOException {
      PT_GUILD_AUCTION_ITEM var2 = new PT_GUILD_AUCTION_ITEM();
      var2.list = new ArrayList();

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
               var2.content = var1.readInt32();
            } else if (var5 == 24) {
               var2.contentvalue = var1.readInt32();
            } else if (var5 == 32) {
               var2.count = var1.readInt32();
            } else if (var5 == 40) {
               var2.date = var1.readUInt64();
            } else if (var5 == 48) {
               var2.guid = var1.readUInt64();
            } else if (var5 == 56) {
               var2.itemindex = var1.readInt32();
            } else if (var5 == 66) {
               Codec var10 = ProtobufProxy.create(PT_GUILD_AUCTION_GET_CHAR.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.list == null) {
                  var2.list = new ArrayList();
               }

               var2.list.add((PT_GUILD_AUCTION_GET_CHAR)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 72) {
               var2.price = var1.readInt32();
            } else if (var5 == 80) {
               var2.quality = var1.readInt32();
            } else {
               var1.skipField(var5);
            }
         }

         return var2;
      } catch (InvalidProtocolBufferException var8) {
         throw var8;
      } catch (IOException var9) {
         throw var9;
      }
   }

   public Descriptors.Descriptor getDescriptor() throws IOException {
      if (this.descriptor != null) {
         return this.descriptor;
      } else {
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_GUILD_AUCTION_ITEM.class);
         return this.descriptor = var1;
      }
   }
}
