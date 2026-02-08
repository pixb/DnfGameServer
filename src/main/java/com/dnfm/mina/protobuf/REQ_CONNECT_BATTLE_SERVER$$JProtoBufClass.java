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

public class REQ_CONNECT_BATTLE_SERVER$$JProtoBufClass implements Codec<REQ_CONNECT_BATTLE_SERVER>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_CONNECT_BATTLE_SERVER var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_CONNECT_BATTLE_SERVER decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_CONNECT_BATTLE_SERVER var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.authkey)) {
         String var10 = var1.authkey;
         var2 += CodedOutputStream.computeStringSize(1, var10);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.openid)) {
         String var11 = var1.openid;
         var2 += CodedOutputStream.computeStringSize(2, var11);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var12 = var1.world;
         var2 += CodedOutputStream.computeInt32Size(3, var12);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.channel)) {
         Integer var13 = var1.channel;
         var2 += CodedOutputStream.computeInt32Size(4, var13);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var14 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(5, var14);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var15 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(6, var15);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var16 = var1.dungeonguid;
         var2 += CodedOutputStream.computeUInt64Size(7, var16);
      }

      return var2;
   }

   public void doWriteTo(REQ_CONNECT_BATTLE_SERVER var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.authkey)) {
         String var10 = var1.authkey;
         if (var10 != null) {
            var2.writeString(1, var10);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.openid)) {
         String var11 = var1.openid;
         if (var11 != null) {
            var2.writeString(2, var11);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var12 = var1.world;
         if (var12 != null) {
            var2.writeInt32(3, var12);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.channel)) {
         Integer var13 = var1.channel;
         if (var13 != null) {
            var2.writeInt32(4, var13);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var14 = var1.charguid;
         if (var14 != null) {
            var2.writeUInt64(5, var14);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var15 = var1.type;
         if (var15 != null) {
            var2.writeInt32(6, var15);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var16 = var1.dungeonguid;
         if (var16 != null) {
            var2.writeUInt64(7, var16);
         }
      }

   }

   public void writeTo(REQ_CONNECT_BATTLE_SERVER var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_CONNECT_BATTLE_SERVER readFrom(CodedInputStream var1) throws IOException {
      REQ_CONNECT_BATTLE_SERVER var2 = new REQ_CONNECT_BATTLE_SERVER();

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
            } else if (var5 == 18) {
               var2.openid = var1.readString();
            } else if (var5 == 24) {
               var2.world = var1.readInt32();
            } else if (var5 == 32) {
               var2.channel = var1.readInt32();
            } else if (var5 == 40) {
               var2.charguid = var1.readUInt64();
            } else if (var5 == 48) {
               var2.type = var1.readInt32();
            } else if (var5 == 56) {
               var2.dungeonguid = var1.readUInt64();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_CONNECT_BATTLE_SERVER.class);
         return this.descriptor = var1;
      }
   }
}
