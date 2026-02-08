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

public class RES_AUCTION_REGISTER_ITEM$$JProtoBufClass implements Codec<RES_AUCTION_REGISTER_ITEM>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_AUCTION_REGISTER_ITEM var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_AUCTION_REGISTER_ITEM decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_AUCTION_REGISTER_ITEM var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var12 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var12);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.time)) {
         Long var13 = var1.time;
         var2 += CodedOutputStream.computeInt64Size(2, var13);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var14 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(3, var14);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.tab)) {
         Integer var15 = var1.tab;
         var2 += CodedOutputStream.computeInt32Size(4, var15);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var16 = var1.guid;
         var2 += CodedOutputStream.computeUInt64Size(5, var16);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var17 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(6, var17);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.qty)) {
         Integer var18 = var1.qty;
         var2 += CodedOutputStream.computeInt32Size(7, var18);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.equip)) {
         List var19 = var1.equip;
         var2 += CodedConstant.computeListSize(8, var19, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.stackable)) {
         List var20 = var1.stackable;
         var2 += CodedConstant.computeListSize(9, var20, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(RES_AUCTION_REGISTER_ITEM var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var12 = var1.error;
         if (var12 != null) {
            var2.writeInt32(1, var12);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.time)) {
         Long var13 = var1.time;
         if (var13 != null) {
            var2.writeInt64(2, var13);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var14 = var1.type;
         if (var14 != null) {
            var2.writeInt32(3, var14);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.tab)) {
         Integer var15 = var1.tab;
         if (var15 != null) {
            var2.writeInt32(4, var15);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var16 = var1.guid;
         if (var16 != null) {
            var2.writeUInt64(5, var16);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var17 = var1.index;
         if (var17 != null) {
            var2.writeInt32(6, var17);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.qty)) {
         Integer var18 = var1.qty;
         if (var18 != null) {
            var2.writeInt32(7, var18);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.equip)) {
         List var19 = var1.equip;
         if (var19 != null) {
            CodedConstant.writeToList(var2, 8, FieldType.OBJECT, var19, false);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.stackable)) {
         List var20 = var1.stackable;
         if (var20 != null) {
            CodedConstant.writeToList(var2, 9, FieldType.OBJECT, var20, false);
         }
      }

   }

   public void writeTo(RES_AUCTION_REGISTER_ITEM var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_AUCTION_REGISTER_ITEM readFrom(CodedInputStream var1) throws IOException {
      RES_AUCTION_REGISTER_ITEM var2 = new RES_AUCTION_REGISTER_ITEM();
      var2.equip = new ArrayList();
      var2.stackable = new ArrayList();

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
               var2.time = var1.readInt64();
            } else if (var5 == 24) {
               var2.type = var1.readInt32();
            } else if (var5 == 32) {
               var2.tab = var1.readInt32();
            } else if (var5 == 40) {
               var2.guid = var1.readUInt64();
            } else if (var5 == 48) {
               var2.index = var1.readInt32();
            } else if (var5 == 56) {
               var2.qty = var1.readInt32();
            } else if (var5 == 66) {
               Codec var10 = ProtobufProxy.create(PT_AUCTION_EQUIP.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.equip == null) {
                  var2.equip = new ArrayList();
               }

               var2.equip.add((PT_AUCTION_EQUIP)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 74) {
               Codec var11 = ProtobufProxy.create(PT_AUCTION_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var12 = var1.readRawVarint32();
               int var13 = var1.pushLimit(var12);
               if (var2.stackable == null) {
                  var2.stackable = new ArrayList();
               }

               var2.stackable.add((PT_AUCTION_STACKABLE)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var13);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_AUCTION_REGISTER_ITEM.class);
         return this.descriptor = var1;
      }
   }
}
