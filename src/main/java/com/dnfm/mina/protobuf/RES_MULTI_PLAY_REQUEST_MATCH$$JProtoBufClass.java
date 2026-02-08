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

public class RES_MULTI_PLAY_REQUEST_MATCH$$JProtoBufClass implements Codec<RES_MULTI_PLAY_REQUEST_MATCH>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_MULTI_PLAY_REQUEST_MATCH var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_MULTI_PLAY_REQUEST_MATCH decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_MULTI_PLAY_REQUEST_MATCH var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var10 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var10);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var11 = var1.dungeonindex;
         var2 += CodedOutputStream.computeInt32Size(2, var11);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.enemycharguid)) {
         Long var12 = var1.enemycharguid;
         var2 += CodedOutputStream.computeUInt64Size(3, var12);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.aimatchingindex)) {
         Integer var13 = var1.aimatchingindex;
         var2 += CodedOutputStream.computeInt32Size(4, var13);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.retry)) {
         Boolean var14 = var1.retry;
         var2 += CodedOutputStream.computeBoolSize(5, var14);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.estimatedtime)) {
         Long var15 = var1.estimatedtime;
         var2 += CodedOutputStream.computeUInt64Size(6, var15);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.iskeyboard)) {
         Boolean var16 = var1.iskeyboard;
         var2 += CodedOutputStream.computeBoolSize(7, var16);
      }

      return var2;
   }

   public void doWriteTo(RES_MULTI_PLAY_REQUEST_MATCH var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var10 = var1.error;
         if (var10 != null) {
            var2.writeInt32(1, var10);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var11 = var1.dungeonindex;
         if (var11 != null) {
            var2.writeInt32(2, var11);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.enemycharguid)) {
         Long var12 = var1.enemycharguid;
         if (var12 != null) {
            var2.writeUInt64(3, var12);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.aimatchingindex)) {
         Integer var13 = var1.aimatchingindex;
         if (var13 != null) {
            var2.writeInt32(4, var13);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.retry)) {
         Boolean var14 = var1.retry;
         if (var14 != null) {
            var2.writeBool(5, var14);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.estimatedtime)) {
         Long var15 = var1.estimatedtime;
         if (var15 != null) {
            var2.writeUInt64(6, var15);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.iskeyboard)) {
         Boolean var16 = var1.iskeyboard;
         if (var16 != null) {
            var2.writeBool(7, var16);
         }
      }

   }

   public void writeTo(RES_MULTI_PLAY_REQUEST_MATCH var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_MULTI_PLAY_REQUEST_MATCH readFrom(CodedInputStream var1) throws IOException {
      RES_MULTI_PLAY_REQUEST_MATCH var2 = new RES_MULTI_PLAY_REQUEST_MATCH();

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
               var2.dungeonindex = var1.readInt32();
            } else if (var5 == 24) {
               var2.enemycharguid = var1.readUInt64();
            } else if (var5 == 32) {
               var2.aimatchingindex = var1.readInt32();
            } else if (var5 == 40) {
               var2.retry = var1.readBool();
            } else if (var5 == 48) {
               var2.estimatedtime = var1.readUInt64();
            } else if (var5 == 56) {
               var2.iskeyboard = var1.readBool();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_MULTI_PLAY_REQUEST_MATCH.class);
         return this.descriptor = var1;
      }
   }
}
