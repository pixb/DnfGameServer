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

public class REQ_MULTI_PLAY_REQUEST_MATCH$$JProtoBufClass implements Codec<REQ_MULTI_PLAY_REQUEST_MATCH>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_MULTI_PLAY_REQUEST_MATCH var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_MULTI_PLAY_REQUEST_MATCH decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_MULTI_PLAY_REQUEST_MATCH var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.authkey)) {
         String var12 = var1.authkey;
         var2 += CodedOutputStream.computeStringSize(1, var12);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var13 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(2, var13);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var14 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(3, var14);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var15 = var1.partyguid;
         var2 += CodedOutputStream.computeUInt64Size(4, var15);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.partyleaderguid)) {
         Long var16 = var1.partyleaderguid;
         var2 += CodedOutputStream.computeUInt64Size(5, var16);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.eamplify)) {
         Boolean var17 = var1.eamplify;
         var2 += CodedOutputStream.computeBoolSize(6, var17);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.customdata)) {
         Long var18 = var1.customdata;
         var2 += CodedOutputStream.computeUInt64Size(7, var18);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.retry)) {
         Boolean var19 = var1.retry;
         var2 += CodedOutputStream.computeBoolSize(8, var19);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.iskeyboard)) {
         Boolean var20 = var1.iskeyboard;
         var2 += CodedOutputStream.computeBoolSize(9, var20);
      }

      return var2;
   }

   public void doWriteTo(REQ_MULTI_PLAY_REQUEST_MATCH var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.authkey)) {
         String var12 = var1.authkey;
         if (var12 != null) {
            var2.writeString(1, var12);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var13 = var1.type;
         if (var13 != null) {
            var2.writeInt32(2, var13);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var14 = var1.index;
         if (var14 != null) {
            var2.writeInt32(3, var14);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var15 = var1.partyguid;
         if (var15 != null) {
            var2.writeUInt64(4, var15);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.partyleaderguid)) {
         Long var16 = var1.partyleaderguid;
         if (var16 != null) {
            var2.writeUInt64(5, var16);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.eamplify)) {
         Boolean var17 = var1.eamplify;
         if (var17 != null) {
            var2.writeBool(6, var17);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.customdata)) {
         Long var18 = var1.customdata;
         if (var18 != null) {
            var2.writeUInt64(7, var18);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.retry)) {
         Boolean var19 = var1.retry;
         if (var19 != null) {
            var2.writeBool(8, var19);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.iskeyboard)) {
         Boolean var20 = var1.iskeyboard;
         if (var20 != null) {
            var2.writeBool(9, var20);
         }
      }

   }

   public void writeTo(REQ_MULTI_PLAY_REQUEST_MATCH var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_MULTI_PLAY_REQUEST_MATCH readFrom(CodedInputStream var1) throws IOException {
      REQ_MULTI_PLAY_REQUEST_MATCH var2 = new REQ_MULTI_PLAY_REQUEST_MATCH();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 10) {
               var2.authkey = var1.readString();
            } else if (var5 == 16) {
               var2.type = var1.readInt32();
            } else if (var5 == 24) {
               var2.index = var1.readInt32();
            } else if (var5 == 32) {
               var2.partyguid = var1.readUInt64();
            } else if (var5 == 40) {
               var2.partyleaderguid = var1.readUInt64();
            } else if (var5 == 48) {
               var2.eamplify = var1.readBool();
            } else if (var5 == 56) {
               var2.customdata = var1.readUInt64();
            } else if (var5 == 64) {
               var2.retry = var1.readBool();
            } else if (var5 == 72) {
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_MULTI_PLAY_REQUEST_MATCH.class);
         return this.descriptor = var1;
      }
   }
}
