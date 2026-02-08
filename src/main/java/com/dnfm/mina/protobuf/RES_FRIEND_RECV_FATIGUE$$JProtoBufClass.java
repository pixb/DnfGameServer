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

public class RES_FRIEND_RECV_FATIGUE$$JProtoBufClass implements Codec<RES_FRIEND_RECV_FATIGUE>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_FRIEND_RECV_FATIGUE var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_FRIEND_RECV_FATIGUE decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_FRIEND_RECV_FATIGUE var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var13 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var13);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.fguid)) {
         Long var14 = var1.fguid;
         var2 += CodedOutputStream.computeUInt64Size(2, var14);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.recvtime)) {
         Long var15 = var1.recvtime;
         var2 += CodedOutputStream.computeInt64Size(3, var15);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.ftype)) {
         Integer var16 = var1.ftype;
         var2 += CodedOutputStream.computeInt32Size(4, var16);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var17 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(5, var17);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var18 = var1.count;
         var2 += CodedOutputStream.computeInt32Size(6, var18);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.total)) {
         Integer var19 = var1.total;
         var2 += CodedOutputStream.computeInt32Size(7, var19);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.openid)) {
         String var20 = var1.openid;
         var2 += CodedOutputStream.computeStringSize(8, var20);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.charm)) {
         Long var21 = var1.charm;
         var2 += CodedOutputStream.computeInt64Size(9, var21);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.remainCharm)) {
         Integer var22 = var1.remainCharm;
         var2 += CodedOutputStream.computeInt32Size(10, var22);
      }

      return var2;
   }

   public void doWriteTo(RES_FRIEND_RECV_FATIGUE var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var13 = var1.error;
         if (var13 != null) {
            var2.writeInt32(1, var13);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.fguid)) {
         Long var14 = var1.fguid;
         if (var14 != null) {
            var2.writeUInt64(2, var14);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.recvtime)) {
         Long var15 = var1.recvtime;
         if (var15 != null) {
            var2.writeInt64(3, var15);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.ftype)) {
         Integer var16 = var1.ftype;
         if (var16 != null) {
            var2.writeInt32(4, var16);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var17 = var1.index;
         if (var17 != null) {
            var2.writeInt32(5, var17);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var18 = var1.count;
         if (var18 != null) {
            var2.writeInt32(6, var18);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.total)) {
         Integer var19 = var1.total;
         if (var19 != null) {
            var2.writeInt32(7, var19);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.openid)) {
         String var20 = var1.openid;
         if (var20 != null) {
            var2.writeString(8, var20);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.charm)) {
         Long var21 = var1.charm;
         if (var21 != null) {
            var2.writeInt64(9, var21);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.remainCharm)) {
         Integer var22 = var1.remainCharm;
         if (var22 != null) {
            var2.writeInt32(10, var22);
         }
      }

   }

   public void writeTo(RES_FRIEND_RECV_FATIGUE var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_FRIEND_RECV_FATIGUE readFrom(CodedInputStream var1) throws IOException {
      RES_FRIEND_RECV_FATIGUE var2 = new RES_FRIEND_RECV_FATIGUE();

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
               var2.recvtime = var1.readInt64();
            } else if (var5 == 32) {
               var2.ftype = var1.readInt32();
            } else if (var5 == 40) {
               var2.index = var1.readInt32();
            } else if (var5 == 48) {
               var2.count = var1.readInt32();
            } else if (var5 == 56) {
               var2.total = var1.readInt32();
            } else if (var5 == 66) {
               var2.openid = var1.readString();
            } else if (var5 == 72) {
               var2.charm = var1.readInt64();
            } else if (var5 == 80) {
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_FRIEND_RECV_FATIGUE.class);
         return this.descriptor = var1;
      }
   }
}
