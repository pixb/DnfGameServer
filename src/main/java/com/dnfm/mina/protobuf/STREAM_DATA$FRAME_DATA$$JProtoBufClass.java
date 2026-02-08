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

public class STREAM_DATA$FRAME_DATA$$JProtoBufClass implements Codec<STREAM_DATA.FRAME_DATA>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(STREAM_DATA.FRAME_DATA var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public STREAM_DATA.FRAME_DATA decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(STREAM_DATA.FRAME_DATA var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.frameSize)) {
         Integer var7 = var1.frameSize;
         var2 += CodedOutputStream.computeInt32Size(1, var7);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.splitCount)) {
         Integer var8 = var1.splitCount;
         var2 += CodedOutputStream.computeInt32Size(2, var8);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.splitSeq)) {
         Integer var9 = var1.splitSeq;
         var2 += CodedOutputStream.computeInt32Size(3, var9);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.frameData)) {
         byte[] var10 = var1.frameData;
         var2 += CodedOutputStream.computeByteArraySize(4, var10);
      }

      return var2;
   }

   public void doWriteTo(STREAM_DATA.FRAME_DATA var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.frameSize)) {
         Integer var7 = var1.frameSize;
         if (var7 != null) {
            var2.writeInt32(1, var7);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.splitCount)) {
         Integer var8 = var1.splitCount;
         if (var8 != null) {
            var2.writeInt32(2, var8);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.splitSeq)) {
         Integer var9 = var1.splitSeq;
         if (var9 != null) {
            var2.writeInt32(3, var9);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.frameData)) {
         byte[] var10 = var1.frameData;
         if (var10 != null) {
            var2.writeByteArray(4, var10);
         }
      }

   }

   public void writeTo(STREAM_DATA.FRAME_DATA var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public STREAM_DATA.FRAME_DATA readFrom(CodedInputStream var1) throws IOException {
      STREAM_DATA.FRAME_DATA var2 = new STREAM_DATA.FRAME_DATA();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.frameSize = var1.readInt32();
            } else if (var5 == 16) {
               var2.splitCount = var1.readInt32();
            } else if (var5 == 24) {
               var2.splitSeq = var1.readInt32();
            } else if (var5 == 34) {
               var2.frameData = var1.readBytes().toByteArray();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(STREAM_DATA.FRAME_DATA.class);
         return this.descriptor = var1;
      }
   }
}
