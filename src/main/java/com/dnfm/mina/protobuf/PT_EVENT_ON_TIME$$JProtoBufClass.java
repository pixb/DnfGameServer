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

public class PT_EVENT_ON_TIME$$JProtoBufClass implements Codec<PT_EVENT_ON_TIME>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_EVENT_ON_TIME var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_EVENT_ON_TIME decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_EVENT_ON_TIME var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var13 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(1, var13);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.active)) {
         Integer var14 = var1.active;
         var2 += CodedOutputStream.computeInt32Size(2, var14);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.days)) {
         List var15 = var1.days;
         var2 += CodedConstant.computeListSize(3, var15, FieldType.INT32, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.startHour)) {
         Integer var16 = var1.startHour;
         var2 += CodedOutputStream.computeInt32Size(4, var16);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.startMinute)) {
         Integer var17 = var1.startMinute;
         var2 += CodedOutputStream.computeInt32Size(5, var17);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.endHour)) {
         Integer var18 = var1.endHour;
         var2 += CodedOutputStream.computeInt32Size(6, var18);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.endMinute)) {
         Integer var19 = var1.endMinute;
         var2 += CodedOutputStream.computeInt32Size(7, var19);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.rewardItemId)) {
         Integer var20 = var1.rewardItemId;
         var2 += CodedOutputStream.computeInt32Size(8, var20);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.rewardItemCount)) {
         Integer var21 = var1.rewardItemCount;
         var2 += CodedOutputStream.computeInt32Size(9, var21);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.rewardBuffId)) {
         Integer var22 = var1.rewardBuffId;
         var2 += CodedOutputStream.computeInt32Size(10, var22);
      }

      return var2;
   }

   public void doWriteTo(PT_EVENT_ON_TIME var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var13 = var1.index;
         if (var13 != null) {
            var2.writeInt32(1, var13);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.active)) {
         Integer var14 = var1.active;
         if (var14 != null) {
            var2.writeInt32(2, var14);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.days)) {
         List var15 = var1.days;
         if (var15 != null) {
            CodedConstant.writeToList(var2, 3, FieldType.INT32, var15, true);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.startHour)) {
         Integer var16 = var1.startHour;
         if (var16 != null) {
            var2.writeInt32(4, var16);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.startMinute)) {
         Integer var17 = var1.startMinute;
         if (var17 != null) {
            var2.writeInt32(5, var17);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.endHour)) {
         Integer var18 = var1.endHour;
         if (var18 != null) {
            var2.writeInt32(6, var18);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.endMinute)) {
         Integer var19 = var1.endMinute;
         if (var19 != null) {
            var2.writeInt32(7, var19);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.rewardItemId)) {
         Integer var20 = var1.rewardItemId;
         if (var20 != null) {
            var2.writeInt32(8, var20);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.rewardItemCount)) {
         Integer var21 = var1.rewardItemCount;
         if (var21 != null) {
            var2.writeInt32(9, var21);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.rewardBuffId)) {
         Integer var22 = var1.rewardBuffId;
         if (var22 != null) {
            var2.writeInt32(10, var22);
         }
      }

   }

   public void writeTo(PT_EVENT_ON_TIME var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_EVENT_ON_TIME readFrom(CodedInputStream var1) throws IOException {
      PT_EVENT_ON_TIME var2 = new PT_EVENT_ON_TIME();
      var2.days = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.index = var1.readInt32();
            } else if (var5 == 16) {
               var2.active = var1.readInt32();
            } else if (var5 == 24) {
               if (var2.days == null) {
                  var2.days = new ArrayList();
               }

               var2.days.add(var1.readInt32());
            } else if (var5 != 26) {
               if (var5 == 32) {
                  var2.startHour = var1.readInt32();
               } else if (var5 == 40) {
                  var2.startMinute = var1.readInt32();
               } else if (var5 == 48) {
                  var2.endHour = var1.readInt32();
               } else if (var5 == 56) {
                  var2.endMinute = var1.readInt32();
               } else if (var5 == 64) {
                  var2.rewardItemId = var1.readInt32();
               } else if (var5 == 72) {
                  var2.rewardItemCount = var1.readInt32();
               } else if (var5 == 80) {
                  var2.rewardBuffId = var1.readInt32();
               } else {
                  var1.skipField(var5);
               }
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.days == null) {
                  var2.days = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.days.add(var1.readInt32());
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_EVENT_ON_TIME.class);
         return this.descriptor = var1;
      }
   }
}
