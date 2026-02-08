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

public class NOTIFY_DAILY_RESET$$JProtoBufClass implements Codec<NOTIFY_DAILY_RESET>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(NOTIFY_DAILY_RESET var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public NOTIFY_DAILY_RESET decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(NOTIFY_DAILY_RESET var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var19 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var19);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.ticket)) {
         List var20 = var1.ticket;
         var2 += CodedConstant.computeListSize(2, var20, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.fatigue)) {
         Integer var21 = var1.fatigue;
         var2 += CodedOutputStream.computeInt32Size(3, var21);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.fatiguetime)) {
         Integer var22 = var1.fatiguetime;
         var2 += CodedOutputStream.computeInt32Size(4, var22);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.gdrinkticket)) {
         Integer var23 = var1.gdrinkticket;
         var2 += CodedOutputStream.computeInt32Size(5, var23);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.helpercount)) {
         Integer var24 = var1.helpercount;
         var2 += CodedOutputStream.computeInt32Size(6, var24);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.jarofgreed)) {
         Integer var25 = var1.jarofgreed;
         var2 += CodedOutputStream.computeInt32Size(7, var25);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.lotteryfreecount)) {
         Integer var26 = var1.lotteryfreecount;
         var2 += CodedOutputStream.computeInt32Size(8, var26);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.lotterychargecount)) {
         Integer var27 = var1.lotterychargecount;
         var2 += CodedOutputStream.computeInt32Size(9, var27);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.supportcount)) {
         Integer var28 = var1.supportcount;
         var2 += CodedOutputStream.computeInt32Size(10, var28);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.recvfriendcount)) {
         Integer var29 = var1.recvfriendcount;
         var2 += CodedOutputStream.computeInt32Size(11, var29);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.sendfriendcount)) {
         Integer var30 = var1.sendfriendcount;
         var2 += CodedOutputStream.computeInt32Size(12, var30);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.currency)) {
         List var31 = var1.currency;
         var2 += CodedConstant.computeListSize(13, var31, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.recvplatformfriendcount)) {
         Integer var32 = var1.recvplatformfriendcount;
         var2 += CodedOutputStream.computeInt32Size(14, var32);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.sendplatformfriendcount)) {
         Integer var33 = var1.sendplatformfriendcount;
         var2 += CodedOutputStream.computeInt32Size(15, var33);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.remainCharm)) {
         Integer var34 = var1.remainCharm;
         var2 += CodedOutputStream.computeInt32Size(16, var34);
      }

      return var2;
   }

   public void doWriteTo(NOTIFY_DAILY_RESET var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var19 = var1.error;
         if (var19 != null) {
            var2.writeInt32(1, var19);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.ticket)) {
         List var20 = var1.ticket;
         if (var20 != null) {
            CodedConstant.writeToList(var2, 2, FieldType.OBJECT, var20, false);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.fatigue)) {
         Integer var21 = var1.fatigue;
         if (var21 != null) {
            var2.writeInt32(3, var21);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.fatiguetime)) {
         Integer var22 = var1.fatiguetime;
         if (var22 != null) {
            var2.writeInt32(4, var22);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.gdrinkticket)) {
         Integer var23 = var1.gdrinkticket;
         if (var23 != null) {
            var2.writeInt32(5, var23);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.helpercount)) {
         Integer var24 = var1.helpercount;
         if (var24 != null) {
            var2.writeInt32(6, var24);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.jarofgreed)) {
         Integer var25 = var1.jarofgreed;
         if (var25 != null) {
            var2.writeInt32(7, var25);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.lotteryfreecount)) {
         Integer var26 = var1.lotteryfreecount;
         if (var26 != null) {
            var2.writeInt32(8, var26);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.lotterychargecount)) {
         Integer var27 = var1.lotterychargecount;
         if (var27 != null) {
            var2.writeInt32(9, var27);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.supportcount)) {
         Integer var28 = var1.supportcount;
         if (var28 != null) {
            var2.writeInt32(10, var28);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.recvfriendcount)) {
         Integer var29 = var1.recvfriendcount;
         if (var29 != null) {
            var2.writeInt32(11, var29);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.sendfriendcount)) {
         Integer var30 = var1.sendfriendcount;
         if (var30 != null) {
            var2.writeInt32(12, var30);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.currency)) {
         List var31 = var1.currency;
         if (var31 != null) {
            CodedConstant.writeToList(var2, 13, FieldType.OBJECT, var31, false);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.recvplatformfriendcount)) {
         Integer var32 = var1.recvplatformfriendcount;
         if (var32 != null) {
            var2.writeInt32(14, var32);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.sendplatformfriendcount)) {
         Integer var33 = var1.sendplatformfriendcount;
         if (var33 != null) {
            var2.writeInt32(15, var33);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.remainCharm)) {
         Integer var34 = var1.remainCharm;
         if (var34 != null) {
            var2.writeInt32(16, var34);
         }
      }

   }

   public void writeTo(NOTIFY_DAILY_RESET var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public NOTIFY_DAILY_RESET readFrom(CodedInputStream var1) throws IOException {
      NOTIFY_DAILY_RESET var2 = new NOTIFY_DAILY_RESET();
      var2.ticket = new ArrayList();
      var2.currency = new ArrayList();

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
            } else if (var5 == 18) {
               Codec var10 = ProtobufProxy.create(PT_TICKET.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.ticket == null) {
                  var2.ticket = new ArrayList();
               }

               var2.ticket.add((PT_TICKET)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 24) {
               var2.fatigue = var1.readInt32();
            } else if (var5 == 32) {
               var2.fatiguetime = var1.readInt32();
            } else if (var5 == 40) {
               var2.gdrinkticket = var1.readInt32();
            } else if (var5 == 48) {
               var2.helpercount = var1.readInt32();
            } else if (var5 == 56) {
               var2.jarofgreed = var1.readInt32();
            } else if (var5 == 64) {
               var2.lotteryfreecount = var1.readInt32();
            } else if (var5 == 72) {
               var2.lotterychargecount = var1.readInt32();
            } else if (var5 == 80) {
               var2.supportcount = var1.readInt32();
            } else if (var5 == 88) {
               var2.recvfriendcount = var1.readInt32();
            } else if (var5 == 96) {
               var2.sendfriendcount = var1.readInt32();
            } else if (var5 == 106) {
               Codec var11 = ProtobufProxy.create(PT_MONEY_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var12 = var1.readRawVarint32();
               int var13 = var1.pushLimit(var12);
               if (var2.currency == null) {
                  var2.currency = new ArrayList();
               }

               var2.currency.add((PT_MONEY_ITEM)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var13);
            } else if (var5 == 112) {
               var2.recvplatformfriendcount = var1.readInt32();
            } else if (var5 == 120) {
               var2.sendplatformfriendcount = var1.readInt32();
            } else if (var5 == 128) {
               var2.remainCharm = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(NOTIFY_DAILY_RESET.class);
         return this.descriptor = var1;
      }
   }
}
