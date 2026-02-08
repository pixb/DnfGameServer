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

public class RES_LOAD_WEDDING_PREPARATION$$JProtoBufClass implements Codec<RES_LOAD_WEDDING_PREPARATION>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_LOAD_WEDDING_PREPARATION var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_LOAD_WEDDING_PREPARATION decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_LOAD_WEDDING_PREPARATION var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var14 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var14);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.step)) {
         Integer var15 = var1.step;
         var2 += CodedOutputStream.computeInt32Size(2, var15);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.groom)) {
         PT_WEDDING_PREPARATION var16 = var1.groom;
         var2 += CodedConstant.computeSize(3, var16, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.bride)) {
         PT_WEDDING_PREPARATION var17 = var1.bride;
         var2 += CodedConstant.computeSize(4, var17, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.reservationamount)) {
         Integer var18 = var1.reservationamount;
         var2 += CodedOutputStream.computeInt32Size(5, var18);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.weddinghall)) {
         Integer var19 = var1.weddinghall;
         var2 += CodedOutputStream.computeInt32Size(6, var19);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.weddingtime)) {
         Long var20 = var1.weddingtime;
         var2 += CodedOutputStream.computeInt64Size(7, var20);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.sendinvitationcount)) {
         Integer var21 = var1.sendinvitationcount;
         var2 += CodedOutputStream.computeInt32Size(8, var21);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.reservationgold)) {
         Integer var22 = var1.reservationgold;
         var2 += CodedOutputStream.computeInt32Size(9, var22);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.reservationtera)) {
         Integer var23 = var1.reservationtera;
         var2 += CodedOutputStream.computeInt32Size(10, var23);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.marriageguid)) {
         Long var24 = var1.marriageguid;
         var2 += CodedOutputStream.computeUInt64Size(11, var24);
      }

      return var2;
   }

   public void doWriteTo(RES_LOAD_WEDDING_PREPARATION var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var14 = var1.error;
         if (var14 != null) {
            var2.writeInt32(1, var14);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.step)) {
         Integer var15 = var1.step;
         if (var15 != null) {
            var2.writeInt32(2, var15);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.groom)) {
         PT_WEDDING_PREPARATION var16 = var1.groom;
         if (var16 != null) {
            CodedConstant.writeObject(var2, 3, FieldType.OBJECT, var16, false);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.bride)) {
         PT_WEDDING_PREPARATION var17 = var1.bride;
         if (var17 != null) {
            CodedConstant.writeObject(var2, 4, FieldType.OBJECT, var17, false);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.reservationamount)) {
         Integer var18 = var1.reservationamount;
         if (var18 != null) {
            var2.writeInt32(5, var18);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.weddinghall)) {
         Integer var19 = var1.weddinghall;
         if (var19 != null) {
            var2.writeInt32(6, var19);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.weddingtime)) {
         Long var20 = var1.weddingtime;
         if (var20 != null) {
            var2.writeInt64(7, var20);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.sendinvitationcount)) {
         Integer var21 = var1.sendinvitationcount;
         if (var21 != null) {
            var2.writeInt32(8, var21);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.reservationgold)) {
         Integer var22 = var1.reservationgold;
         if (var22 != null) {
            var2.writeInt32(9, var22);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.reservationtera)) {
         Integer var23 = var1.reservationtera;
         if (var23 != null) {
            var2.writeInt32(10, var23);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.marriageguid)) {
         Long var24 = var1.marriageguid;
         if (var24 != null) {
            var2.writeUInt64(11, var24);
         }
      }

   }

   public void writeTo(RES_LOAD_WEDDING_PREPARATION var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_LOAD_WEDDING_PREPARATION readFrom(CodedInputStream var1) throws IOException {
      RES_LOAD_WEDDING_PREPARATION var2 = new RES_LOAD_WEDDING_PREPARATION();

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
               var2.step = var1.readInt32();
            } else if (var5 == 26) {
               Codec var10 = ProtobufProxy.create(PT_WEDDING_PREPARATION.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.groom = (PT_WEDDING_PREPARATION)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 34) {
               Codec var11 = ProtobufProxy.create(PT_WEDDING_PREPARATION.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var12 = var1.readRawVarint32();
               int var13 = var1.pushLimit(var12);
               var2.bride = (PT_WEDDING_PREPARATION)var11.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var13);
            } else if (var5 == 40) {
               var2.reservationamount = var1.readInt32();
            } else if (var5 == 48) {
               var2.weddinghall = var1.readInt32();
            } else if (var5 == 56) {
               var2.weddingtime = var1.readInt64();
            } else if (var5 == 64) {
               var2.sendinvitationcount = var1.readInt32();
            } else if (var5 == 72) {
               var2.reservationgold = var1.readInt32();
            } else if (var5 == 80) {
               var2.reservationtera = var1.readInt32();
            } else if (var5 == 88) {
               var2.marriageguid = var1.readUInt64();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_LOAD_WEDDING_PREPARATION.class);
         return this.descriptor = var1;
      }
   }
}
