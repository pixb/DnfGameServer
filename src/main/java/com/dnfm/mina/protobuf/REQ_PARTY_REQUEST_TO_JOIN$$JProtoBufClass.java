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

public class REQ_PARTY_REQUEST_TO_JOIN$$JProtoBufClass implements Codec<REQ_PARTY_REQUEST_TO_JOIN>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_PARTY_REQUEST_TO_JOIN var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_PARTY_REQUEST_TO_JOIN decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_PARTY_REQUEST_TO_JOIN var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var8 = var1.partyguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var8);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.leaderguid)) {
         Long var9 = var1.leaderguid;
         var2 += CodedOutputStream.computeUInt64Size(2, var9);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.version)) {
         String var10 = var1.version;
         var2 += CodedOutputStream.computeStringSize(3, var10);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.observer)) {
         Boolean var11 = var1.observer;
         var2 += CodedOutputStream.computeBoolSize(4, var11);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.player)) {
         Boolean var12 = var1.player;
         var2 += CodedOutputStream.computeBoolSize(5, var12);
      }

      return var2;
   }

   public void doWriteTo(REQ_PARTY_REQUEST_TO_JOIN var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var8 = var1.partyguid;
         if (var8 != null) {
            var2.writeUInt64(1, var8);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.leaderguid)) {
         Long var9 = var1.leaderguid;
         if (var9 != null) {
            var2.writeUInt64(2, var9);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.version)) {
         String var10 = var1.version;
         if (var10 != null) {
            var2.writeString(3, var10);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.observer)) {
         Boolean var11 = var1.observer;
         if (var11 != null) {
            var2.writeBool(4, var11);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.player)) {
         Boolean var12 = var1.player;
         if (var12 != null) {
            var2.writeBool(5, var12);
         }
      }

   }

   public void writeTo(REQ_PARTY_REQUEST_TO_JOIN var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_PARTY_REQUEST_TO_JOIN readFrom(CodedInputStream var1) throws IOException {
      REQ_PARTY_REQUEST_TO_JOIN var2 = new REQ_PARTY_REQUEST_TO_JOIN();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.partyguid = var1.readUInt64();
            } else if (var5 == 16) {
               var2.leaderguid = var1.readUInt64();
            } else if (var5 == 26) {
               var2.version = var1.readString();
            } else if (var5 == 32) {
               var2.observer = var1.readBool();
            } else if (var5 == 40) {
               var2.player = var1.readBool();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_PARTY_REQUEST_TO_JOIN.class);
         return this.descriptor = var1;
      }
   }
}
