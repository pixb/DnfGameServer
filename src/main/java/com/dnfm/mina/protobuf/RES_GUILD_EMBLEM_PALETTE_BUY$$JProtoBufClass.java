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

public class RES_GUILD_EMBLEM_PALETTE_BUY$$JProtoBufClass implements Codec<RES_GUILD_EMBLEM_PALETTE_BUY>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_GUILD_EMBLEM_PALETTE_BUY var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_GUILD_EMBLEM_PALETTE_BUY decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_GUILD_EMBLEM_PALETTE_BUY var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var8 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var8);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.colorindex)) {
         Integer var9 = var1.colorindex;
         var2 += CodedOutputStream.computeInt32Size(2, var9);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.payindex)) {
         Integer var10 = var1.payindex;
         var2 += CodedOutputStream.computeInt32Size(3, var10);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.price)) {
         Integer var11 = var1.price;
         var2 += CodedOutputStream.computeInt32Size(4, var11);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.colorlist)) {
         List var12 = var1.colorlist;
         var2 += CodedConstant.computeListSize(5, var12, FieldType.INT32, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      return var2;
   }

   public void doWriteTo(RES_GUILD_EMBLEM_PALETTE_BUY var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var8 = var1.error;
         if (var8 != null) {
            var2.writeInt32(1, var8);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.colorindex)) {
         Integer var9 = var1.colorindex;
         if (var9 != null) {
            var2.writeInt32(2, var9);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.payindex)) {
         Integer var10 = var1.payindex;
         if (var10 != null) {
            var2.writeInt32(3, var10);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.price)) {
         Integer var11 = var1.price;
         if (var11 != null) {
            var2.writeInt32(4, var11);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.colorlist)) {
         List var12 = var1.colorlist;
         if (var12 != null) {
            CodedConstant.writeToList(var2, 5, FieldType.INT32, var12, true);
         }
      }

   }

   public void writeTo(RES_GUILD_EMBLEM_PALETTE_BUY var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_GUILD_EMBLEM_PALETTE_BUY readFrom(CodedInputStream var1) throws IOException {
      RES_GUILD_EMBLEM_PALETTE_BUY var2 = new RES_GUILD_EMBLEM_PALETTE_BUY();
      var2.colorlist = new ArrayList();

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
               var2.colorindex = var1.readInt32();
            } else if (var5 == 24) {
               var2.payindex = var1.readInt32();
            } else if (var5 == 32) {
               var2.price = var1.readInt32();
            } else if (var5 == 40) {
               if (var2.colorlist == null) {
                  var2.colorlist = new ArrayList();
               }

               var2.colorlist.add(var1.readInt32());
            } else if (var5 != 42) {
               var1.skipField(var5);
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.colorlist == null) {
                  var2.colorlist = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.colorlist.add(var1.readInt32());
               }

               var1.popLimit(var7);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_GUILD_EMBLEM_PALETTE_BUY.class);
         return this.descriptor = var1;
      }
   }
}
