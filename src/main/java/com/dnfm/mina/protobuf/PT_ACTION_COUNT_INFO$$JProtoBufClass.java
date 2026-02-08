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

public class PT_ACTION_COUNT_INFO$$JProtoBufClass implements Codec<PT_ACTION_COUNT_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_ACTION_COUNT_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_ACTION_COUNT_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_ACTION_COUNT_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.combocount)) {
         Integer var11 = var1.combocount;
         var2 += CodedOutputStream.computeInt32Size(1, var11);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.aerialcount)) {
         Integer var12 = var1.aerialcount;
         var2 += CodedOutputStream.computeInt32Size(2, var12);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.overkillcount1)) {
         Integer var13 = var1.overkillcount1;
         var2 += CodedOutputStream.computeInt32Size(3, var13);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.overkillcount2)) {
         Integer var14 = var1.overkillcount2;
         var2 += CodedOutputStream.computeInt32Size(4, var14);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.countercount)) {
         Integer var15 = var1.countercount;
         var2 += CodedOutputStream.computeInt32Size(5, var15);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.backattackcount)) {
         Integer var16 = var1.backattackcount;
         var2 += CodedOutputStream.computeInt32Size(6, var16);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.crowdcontrolcount)) {
         Integer var17 = var1.crowdcontrolcount;
         var2 += CodedOutputStream.computeInt32Size(7, var17);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.justevadecount)) {
         Integer var18 = var1.justevadecount;
         var2 += CodedOutputStream.computeInt32Size(8, var18);
      }

      return var2;
   }

   public void doWriteTo(PT_ACTION_COUNT_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.combocount)) {
         Integer var11 = var1.combocount;
         if (var11 != null) {
            var2.writeInt32(1, var11);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.aerialcount)) {
         Integer var12 = var1.aerialcount;
         if (var12 != null) {
            var2.writeInt32(2, var12);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.overkillcount1)) {
         Integer var13 = var1.overkillcount1;
         if (var13 != null) {
            var2.writeInt32(3, var13);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.overkillcount2)) {
         Integer var14 = var1.overkillcount2;
         if (var14 != null) {
            var2.writeInt32(4, var14);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.countercount)) {
         Integer var15 = var1.countercount;
         if (var15 != null) {
            var2.writeInt32(5, var15);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.backattackcount)) {
         Integer var16 = var1.backattackcount;
         if (var16 != null) {
            var2.writeInt32(6, var16);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.crowdcontrolcount)) {
         Integer var17 = var1.crowdcontrolcount;
         if (var17 != null) {
            var2.writeInt32(7, var17);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.justevadecount)) {
         Integer var18 = var1.justevadecount;
         if (var18 != null) {
            var2.writeInt32(8, var18);
         }
      }

   }

   public void writeTo(PT_ACTION_COUNT_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_ACTION_COUNT_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_ACTION_COUNT_INFO var2 = new PT_ACTION_COUNT_INFO();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.combocount = var1.readInt32();
            } else if (var5 == 16) {
               var2.aerialcount = var1.readInt32();
            } else if (var5 == 24) {
               var2.overkillcount1 = var1.readInt32();
            } else if (var5 == 32) {
               var2.overkillcount2 = var1.readInt32();
            } else if (var5 == 40) {
               var2.countercount = var1.readInt32();
            } else if (var5 == 48) {
               var2.backattackcount = var1.readInt32();
            } else if (var5 == 56) {
               var2.crowdcontrolcount = var1.readInt32();
            } else if (var5 == 64) {
               var2.justevadecount = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_ACTION_COUNT_INFO.class);
         return this.descriptor = var1;
      }
   }
}
