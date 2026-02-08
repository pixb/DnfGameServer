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

public class REQ_DREAM_MAZE_HP_SYNC$$JProtoBufClass implements Codec<REQ_DREAM_MAZE_HP_SYNC>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_DREAM_MAZE_HP_SYNC var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_DREAM_MAZE_HP_SYNC decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_DREAM_MAZE_HP_SYNC var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.damagehistory)) {
         byte[] var8 = var1.damagehistory;
         var2 += CodedOutputStream.computeByteArraySize(1, var8);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.dungeonIndex)) {
         Integer var9 = var1.dungeonIndex;
         var2 += CodedOutputStream.computeInt32Size(2, var9);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.bossMaxHp)) {
         Long var10 = var1.bossMaxHp;
         var2 += CodedOutputStream.computeUInt64Size(3, var10);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.damage)) {
         Long var11 = var1.damage;
         var2 += CodedOutputStream.computeUInt64Size(4, var11);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.damageseq)) {
         Integer var12 = var1.damageseq;
         var2 += CodedOutputStream.computeInt32Size(5, var12);
      }

      return var2;
   }

   public void doWriteTo(REQ_DREAM_MAZE_HP_SYNC var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.damagehistory)) {
         byte[] var8 = var1.damagehistory;
         if (var8 != null) {
            var2.writeByteArray(1, var8);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.dungeonIndex)) {
         Integer var9 = var1.dungeonIndex;
         if (var9 != null) {
            var2.writeInt32(2, var9);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.bossMaxHp)) {
         Long var10 = var1.bossMaxHp;
         if (var10 != null) {
            var2.writeUInt64(3, var10);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.damage)) {
         Long var11 = var1.damage;
         if (var11 != null) {
            var2.writeUInt64(4, var11);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.damageseq)) {
         Integer var12 = var1.damageseq;
         if (var12 != null) {
            var2.writeInt32(5, var12);
         }
      }

   }

   public void writeTo(REQ_DREAM_MAZE_HP_SYNC var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_DREAM_MAZE_HP_SYNC readFrom(CodedInputStream var1) throws IOException {
      REQ_DREAM_MAZE_HP_SYNC var2 = new REQ_DREAM_MAZE_HP_SYNC();

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
               var2.dungeonIndex = var1.readInt32();
            } else if (var5 == 24) {
               var2.bossMaxHp = var1.readUInt64();
            } else if (var5 == 32) {
               var2.damage = var1.readUInt64();
            } else if (var5 == 40) {
               var2.damageseq = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_DREAM_MAZE_HP_SYNC.class);
         return this.descriptor = var1;
      }
   }
}
