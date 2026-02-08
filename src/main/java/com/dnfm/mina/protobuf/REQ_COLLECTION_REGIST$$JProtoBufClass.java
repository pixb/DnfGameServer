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

public class REQ_COLLECTION_REGIST$$JProtoBufClass implements Codec<REQ_COLLECTION_REGIST>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_COLLECTION_REGIST var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_COLLECTION_REGIST decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_COLLECTION_REGIST var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.collectionindex)) {
         Integer var6 = var1.collectionindex;
         var2 += CodedOutputStream.computeInt32Size(1, var6);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.itemguid)) {
         Long var7 = var1.itemguid;
         var2 += CodedOutputStream.computeUInt64Size(2, var7);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.rewardindex)) {
         Integer var8 = var1.rewardindex;
         var2 += CodedOutputStream.computeInt32Size(3, var8);
      }

      return var2;
   }

   public void doWriteTo(REQ_COLLECTION_REGIST var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.collectionindex)) {
         Integer var6 = var1.collectionindex;
         if (var6 != null) {
            var2.writeInt32(1, var6);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.itemguid)) {
         Long var7 = var1.itemguid;
         if (var7 != null) {
            var2.writeUInt64(2, var7);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.rewardindex)) {
         Integer var8 = var1.rewardindex;
         if (var8 != null) {
            var2.writeInt32(3, var8);
         }
      }

   }

   public void writeTo(REQ_COLLECTION_REGIST var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_COLLECTION_REGIST readFrom(CodedInputStream var1) throws IOException {
      REQ_COLLECTION_REGIST var2 = new REQ_COLLECTION_REGIST();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.collectionindex = var1.readInt32();
            } else if (var5 == 16) {
               var2.itemguid = var1.readUInt64();
            } else if (var5 == 24) {
               var2.rewardindex = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_COLLECTION_REGIST.class);
         return this.descriptor = var1;
      }
   }
}
