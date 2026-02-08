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

public class REQ_ADVENTUREBOOK_SPECIAL_REWARD$$JProtoBufClass implements Codec<REQ_ADVENTUREBOOK_SPECIAL_REWARD>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_ADVENTUREBOOK_SPECIAL_REWARD var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_ADVENTUREBOOK_SPECIAL_REWARD decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_ADVENTUREBOOK_SPECIAL_REWARD var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.authkey)) {
         String var8 = var1.authkey;
         var2 += CodedOutputStream.computeStringSize(1, var8);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.accountkey)) {
         Long var9 = var1.accountkey;
         var2 += CodedOutputStream.computeUInt64Size(2, var9);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var10 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(3, var10);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.bookindex)) {
         Integer var11 = var1.bookindex;
         var2 += CodedOutputStream.computeInt32Size(4, var11);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.booktype)) {
         Integer var12 = var1.booktype;
         var2 += CodedOutputStream.computeInt32Size(5, var12);
      }

      return var2;
   }

   public void doWriteTo(REQ_ADVENTUREBOOK_SPECIAL_REWARD var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.authkey)) {
         String var8 = var1.authkey;
         if (var8 != null) {
            var2.writeString(1, var8);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.accountkey)) {
         Long var9 = var1.accountkey;
         if (var9 != null) {
            var2.writeUInt64(2, var9);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var10 = var1.charguid;
         if (var10 != null) {
            var2.writeUInt64(3, var10);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.bookindex)) {
         Integer var11 = var1.bookindex;
         if (var11 != null) {
            var2.writeInt32(4, var11);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.booktype)) {
         Integer var12 = var1.booktype;
         if (var12 != null) {
            var2.writeInt32(5, var12);
         }
      }

   }

   public void writeTo(REQ_ADVENTUREBOOK_SPECIAL_REWARD var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_ADVENTUREBOOK_SPECIAL_REWARD readFrom(CodedInputStream var1) throws IOException {
      REQ_ADVENTUREBOOK_SPECIAL_REWARD var2 = new REQ_ADVENTUREBOOK_SPECIAL_REWARD();

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
               var2.accountkey = var1.readUInt64();
            } else if (var5 == 24) {
               var2.charguid = var1.readUInt64();
            } else if (var5 == 32) {
               var2.bookindex = var1.readInt32();
            } else if (var5 == 40) {
               var2.booktype = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_ADVENTUREBOOK_SPECIAL_REWARD.class);
         return this.descriptor = var1;
      }
   }
}
