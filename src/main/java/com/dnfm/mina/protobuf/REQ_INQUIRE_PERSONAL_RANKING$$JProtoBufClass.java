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

public class REQ_INQUIRE_PERSONAL_RANKING$$JProtoBufClass implements Codec<REQ_INQUIRE_PERSONAL_RANKING>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_INQUIRE_PERSONAL_RANKING var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_INQUIRE_PERSONAL_RANKING decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_INQUIRE_PERSONAL_RANKING var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.season)) {
         Integer var9 = var1.season;
         var2 += CodedOutputStream.computeInt32Size(1, var9);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var10 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(2, var10);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.rankMin)) {
         Integer var11 = var1.rankMin;
         var2 += CodedOutputStream.computeInt32Size(3, var11);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.rankMax)) {
         Integer var12 = var1.rankMax;
         var2 += CodedOutputStream.computeInt32Size(4, var12);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var13 = var1.job;
         var2 += CodedOutputStream.computeInt32Size(5, var13);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var14 = var1.growtype;
         var2 += CodedOutputStream.computeInt32Size(6, var14);
      }

      return var2;
   }

   public void doWriteTo(REQ_INQUIRE_PERSONAL_RANKING var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.season)) {
         Integer var9 = var1.season;
         if (var9 != null) {
            var2.writeInt32(1, var9);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var10 = var1.type;
         if (var10 != null) {
            var2.writeInt32(2, var10);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.rankMin)) {
         Integer var11 = var1.rankMin;
         if (var11 != null) {
            var2.writeInt32(3, var11);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.rankMax)) {
         Integer var12 = var1.rankMax;
         if (var12 != null) {
            var2.writeInt32(4, var12);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var13 = var1.job;
         if (var13 != null) {
            var2.writeInt32(5, var13);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var14 = var1.growtype;
         if (var14 != null) {
            var2.writeInt32(6, var14);
         }
      }

   }

   public void writeTo(REQ_INQUIRE_PERSONAL_RANKING var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_INQUIRE_PERSONAL_RANKING readFrom(CodedInputStream var1) throws IOException {
      REQ_INQUIRE_PERSONAL_RANKING var2 = new REQ_INQUIRE_PERSONAL_RANKING();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.season = var1.readInt32();
            } else if (var5 == 16) {
               var2.type = var1.readInt32();
            } else if (var5 == 24) {
               var2.rankMin = var1.readInt32();
            } else if (var5 == 32) {
               var2.rankMax = var1.readInt32();
            } else if (var5 == 40) {
               var2.job = var1.readInt32();
            } else if (var5 == 48) {
               var2.growtype = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_INQUIRE_PERSONAL_RANKING.class);
         return this.descriptor = var1;
      }
   }
}
