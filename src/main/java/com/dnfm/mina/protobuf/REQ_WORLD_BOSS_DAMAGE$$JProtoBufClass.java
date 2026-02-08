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

public class REQ_WORLD_BOSS_DAMAGE$$JProtoBufClass implements Codec<REQ_WORLD_BOSS_DAMAGE>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_WORLD_BOSS_DAMAGE var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_WORLD_BOSS_DAMAGE decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_WORLD_BOSS_DAMAGE var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.damagehistory)) {
         byte[] var6 = var1.damagehistory;
         var2 += CodedOutputStream.computeByteArraySize(1, var6);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.damage)) {
         Long var7 = var1.damage;
         var2 += CodedOutputStream.computeInt64Size(2, var7);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var8 = var1.dungeonguid;
         var2 += CodedOutputStream.computeUInt64Size(3, var8);
      }

      return var2;
   }

   public void doWriteTo(REQ_WORLD_BOSS_DAMAGE var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.damagehistory)) {
         byte[] var6 = var1.damagehistory;
         if (var6 != null) {
            var2.writeByteArray(1, var6);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.damage)) {
         Long var7 = var1.damage;
         if (var7 != null) {
            var2.writeInt64(2, var7);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var8 = var1.dungeonguid;
         if (var8 != null) {
            var2.writeUInt64(3, var8);
         }
      }

   }

   public void writeTo(REQ_WORLD_BOSS_DAMAGE var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_WORLD_BOSS_DAMAGE readFrom(CodedInputStream var1) throws IOException {
      REQ_WORLD_BOSS_DAMAGE var2 = new REQ_WORLD_BOSS_DAMAGE();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 10) {
               var2.damagehistory = var1.readBytes().toByteArray();
            } else if (var5 == 16) {
               var2.damage = var1.readInt64();
            } else if (var5 == 24) {
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_WORLD_BOSS_DAMAGE.class);
         return this.descriptor = var1;
      }
   }
}
