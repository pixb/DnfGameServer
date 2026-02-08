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

public class RES_ITEM_PRODUCTION_REGISTER$$JProtoBufClass implements Codec<RES_ITEM_PRODUCTION_REGISTER>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_ITEM_PRODUCTION_REGISTER var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_ITEM_PRODUCTION_REGISTER decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_ITEM_PRODUCTION_REGISTER var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var13 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var13);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.slottype)) {
         Integer var14 = var1.slottype;
         var2 += CodedOutputStream.computeInt32Size(2, var14);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.slotindex)) {
         Integer var15 = var1.slotindex;
         var2 += CodedOutputStream.computeInt32Size(3, var15);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.recipeindex)) {
         Integer var16 = var1.recipeindex;
         var2 += CodedOutputStream.computeInt32Size(4, var16);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.starttime)) {
         Long var17 = var1.starttime;
         var2 += CodedOutputStream.computeUInt64Size(5, var17);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.endtime)) {
         Long var18 = var1.endtime;
         var2 += CodedOutputStream.computeUInt64Size(6, var18);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.state)) {
         Integer var19 = var1.state;
         var2 += CodedOutputStream.computeInt32Size(7, var19);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.materialitems)) {
         List var20 = var1.materialitems;
         var2 += CodedConstant.computeListSize(8, var20, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.limitrecipeusecount)) {
         Integer var21 = var1.limitrecipeusecount;
         var2 += CodedOutputStream.computeInt32Size(9, var21);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.rewards)) {
         PT_CONTENTS_REWARD_INFO var22 = var1.rewards;
         var2 += CodedConstant.computeSize(10, var22, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      return var2;
   }

   public void doWriteTo(RES_ITEM_PRODUCTION_REGISTER var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var13 = var1.error;
         if (var13 != null) {
            var2.writeInt32(1, var13);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.slottype)) {
         Integer var14 = var1.slottype;
         if (var14 != null) {
            var2.writeInt32(2, var14);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.slotindex)) {
         Integer var15 = var1.slotindex;
         if (var15 != null) {
            var2.writeInt32(3, var15);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.recipeindex)) {
         Integer var16 = var1.recipeindex;
         if (var16 != null) {
            var2.writeInt32(4, var16);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.starttime)) {
         Long var17 = var1.starttime;
         if (var17 != null) {
            var2.writeUInt64(5, var17);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.endtime)) {
         Long var18 = var1.endtime;
         if (var18 != null) {
            var2.writeUInt64(6, var18);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.state)) {
         Integer var19 = var1.state;
         if (var19 != null) {
            var2.writeInt32(7, var19);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.materialitems)) {
         List var20 = var1.materialitems;
         if (var20 != null) {
            CodedConstant.writeToList(var2, 8, FieldType.OBJECT, var20, false);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.limitrecipeusecount)) {
         Integer var21 = var1.limitrecipeusecount;
         if (var21 != null) {
            var2.writeInt32(9, var21);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.rewards)) {
         PT_CONTENTS_REWARD_INFO var22 = var1.rewards;
         if (var22 != null) {
            CodedConstant.writeObject(var2, 10, FieldType.OBJECT, var22, false);
         }
      }

   }

   public void writeTo(RES_ITEM_PRODUCTION_REGISTER var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_ITEM_PRODUCTION_REGISTER readFrom(CodedInputStream var1) throws IOException {
      RES_ITEM_PRODUCTION_REGISTER var2 = new RES_ITEM_PRODUCTION_REGISTER();
      var2.materialitems = new ArrayList();

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
               var2.slottype = var1.readInt32();
            } else if (var5 == 24) {
               var2.slotindex = var1.readInt32();
            } else if (var5 == 32) {
               var2.recipeindex = var1.readInt32();
            } else if (var5 == 40) {
               var2.starttime = var1.readUInt64();
            } else if (var5 == 48) {
               var2.endtime = var1.readUInt64();
            } else if (var5 == 56) {
               var2.state = var1.readInt32();
            } else if (var5 == 66) {
               Codec var10 = ProtobufProxy.create(PT_CONSUME_ITEMS.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.materialitems == null) {
                  var2.materialitems = new ArrayList();
               }

               var2.materialitems.add((PT_CONSUME_ITEMS)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 72) {
               var2.limitrecipeusecount = var1.readInt32();
            } else if (var5 == 82) {
               Codec var11 = ProtobufProxy.create(PT_CONTENTS_REWARD_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var12 = var1.readRawVarint32();
               int var13 = var1.pushLimit(var12);
               var2.rewards = (PT_CONTENTS_REWARD_INFO)var11.readFrom(var1);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_ITEM_PRODUCTION_REGISTER.class);
         return this.descriptor = var1;
      }
   }
}
