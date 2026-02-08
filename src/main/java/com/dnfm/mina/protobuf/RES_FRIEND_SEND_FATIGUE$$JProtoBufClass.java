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

public class RES_FRIEND_SEND_FATIGUE$$JProtoBufClass implements Codec<RES_FRIEND_SEND_FATIGUE>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_FRIEND_SEND_FATIGUE var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_FRIEND_SEND_FATIGUE decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_FRIEND_SEND_FATIGUE var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var10 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var10);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.fguid)) {
         Long var11 = var1.fguid;
         var2 += CodedOutputStream.computeUInt64Size(2, var11);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.sendtime)) {
         Long var12 = var1.sendtime;
         var2 += CodedOutputStream.computeInt64Size(3, var12);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.closenessscore)) {
         Integer var13 = var1.closenessscore;
         var2 += CodedOutputStream.computeInt32Size(4, var13);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.ftype)) {
         Integer var14 = var1.ftype;
         var2 += CodedOutputStream.computeInt32Size(5, var14);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.charm)) {
         Integer var15 = var1.charm;
         var2 += CodedOutputStream.computeInt32Size(6, var15);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.remainCharm)) {
         Integer var16 = var1.remainCharm;
         var2 += CodedOutputStream.computeInt32Size(7, var16);
      }

      return var2;
   }

   public void doWriteTo(RES_FRIEND_SEND_FATIGUE var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var10 = var1.error;
         if (var10 != null) {
            var2.writeInt32(1, var10);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.fguid)) {
         Long var11 = var1.fguid;
         if (var11 != null) {
            var2.writeUInt64(2, var11);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.sendtime)) {
         Long var12 = var1.sendtime;
         if (var12 != null) {
            var2.writeInt64(3, var12);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.closenessscore)) {
         Integer var13 = var1.closenessscore;
         if (var13 != null) {
            var2.writeInt32(4, var13);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.ftype)) {
         Integer var14 = var1.ftype;
         if (var14 != null) {
            var2.writeInt32(5, var14);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.charm)) {
         Integer var15 = var1.charm;
         if (var15 != null) {
            var2.writeInt32(6, var15);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.remainCharm)) {
         Integer var16 = var1.remainCharm;
         if (var16 != null) {
            var2.writeInt32(7, var16);
         }
      }

   }

   public void writeTo(RES_FRIEND_SEND_FATIGUE var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_FRIEND_SEND_FATIGUE readFrom(CodedInputStream var1) throws IOException {
      RES_FRIEND_SEND_FATIGUE var2 = new RES_FRIEND_SEND_FATIGUE();

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
               var2.fguid = var1.readUInt64();
            } else if (var5 == 24) {
               var2.sendtime = var1.readInt64();
            } else if (var5 == 32) {
               var2.closenessscore = var1.readInt32();
            } else if (var5 == 40) {
               var2.ftype = var1.readInt32();
            } else if (var5 == 48) {
               var2.charm = var1.readInt32();
            } else if (var5 == 56) {
               var2.remainCharm = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_FRIEND_SEND_FATIGUE.class);
         return this.descriptor = var1;
      }
   }
}
