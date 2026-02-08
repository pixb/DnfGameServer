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

public class REQ_MINIGAME_BREAKING_CLEAR$$JProtoBufClass implements Codec<REQ_MINIGAME_BREAKING_CLEAR>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_MINIGAME_BREAKING_CLEAR var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_MINIGAME_BREAKING_CLEAR decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_MINIGAME_BREAKING_CLEAR var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.score)) {
         Integer var10 = var1.score;
         var2 += CodedOutputStream.computeInt32Size(1, var10);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.maxcombo)) {
         Integer var11 = var1.maxcombo;
         var2 += CodedOutputStream.computeInt32Size(2, var11);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.life)) {
         Integer var12 = var1.life;
         var2 += CodedOutputStream.computeInt32Size(3, var12);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.atargetcount)) {
         Integer var13 = var1.atargetcount;
         var2 += CodedOutputStream.computeInt32Size(4, var13);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.btargetcount)) {
         Integer var14 = var1.btargetcount;
         var2 += CodedOutputStream.computeInt32Size(5, var14);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.ctargetcount)) {
         Integer var15 = var1.ctargetcount;
         var2 += CodedOutputStream.computeInt32Size(6, var15);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.fevercount)) {
         Integer var16 = var1.fevercount;
         var2 += CodedOutputStream.computeInt32Size(7, var16);
      }

      return var2;
   }

   public void doWriteTo(REQ_MINIGAME_BREAKING_CLEAR var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.score)) {
         Integer var10 = var1.score;
         if (var10 != null) {
            var2.writeInt32(1, var10);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.maxcombo)) {
         Integer var11 = var1.maxcombo;
         if (var11 != null) {
            var2.writeInt32(2, var11);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.life)) {
         Integer var12 = var1.life;
         if (var12 != null) {
            var2.writeInt32(3, var12);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.atargetcount)) {
         Integer var13 = var1.atargetcount;
         if (var13 != null) {
            var2.writeInt32(4, var13);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.btargetcount)) {
         Integer var14 = var1.btargetcount;
         if (var14 != null) {
            var2.writeInt32(5, var14);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.ctargetcount)) {
         Integer var15 = var1.ctargetcount;
         if (var15 != null) {
            var2.writeInt32(6, var15);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.fevercount)) {
         Integer var16 = var1.fevercount;
         if (var16 != null) {
            var2.writeInt32(7, var16);
         }
      }

   }

   public void writeTo(REQ_MINIGAME_BREAKING_CLEAR var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_MINIGAME_BREAKING_CLEAR readFrom(CodedInputStream var1) throws IOException {
      REQ_MINIGAME_BREAKING_CLEAR var2 = new REQ_MINIGAME_BREAKING_CLEAR();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.score = var1.readInt32();
            } else if (var5 == 16) {
               var2.maxcombo = var1.readInt32();
            } else if (var5 == 24) {
               var2.life = var1.readInt32();
            } else if (var5 == 32) {
               var2.atargetcount = var1.readInt32();
            } else if (var5 == 40) {
               var2.btargetcount = var1.readInt32();
            } else if (var5 == 48) {
               var2.ctargetcount = var1.readInt32();
            } else if (var5 == 56) {
               var2.fevercount = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_MINIGAME_BREAKING_CLEAR.class);
         return this.descriptor = var1;
      }
   }
}
