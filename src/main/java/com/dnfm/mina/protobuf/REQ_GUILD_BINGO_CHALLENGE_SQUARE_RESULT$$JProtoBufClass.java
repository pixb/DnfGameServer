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

public class REQ_GUILD_BINGO_CHALLENGE_SQUARE_RESULT$$JProtoBufClass implements Codec<REQ_GUILD_BINGO_CHALLENGE_SQUARE_RESULT>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_GUILD_BINGO_CHALLENGE_SQUARE_RESULT var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_GUILD_BINGO_CHALLENGE_SQUARE_RESULT decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_GUILD_BINGO_CHALLENGE_SQUARE_RESULT var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.maplevel)) {
         Integer var7 = var1.maplevel;
         var2 += CodedOutputStream.computeInt32Size(1, var7);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.squareindex)) {
         Integer var8 = var1.squareindex;
         var2 += CodedOutputStream.computeInt32Size(2, var8);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.score)) {
         Integer var9 = var1.score;
         var2 += CodedOutputStream.computeInt32Size(3, var9);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.result)) {
         Boolean var10 = var1.result;
         var2 += CodedOutputStream.computeBoolSize(4, var10);
      }

      return var2;
   }

   public void doWriteTo(REQ_GUILD_BINGO_CHALLENGE_SQUARE_RESULT var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.maplevel)) {
         Integer var7 = var1.maplevel;
         if (var7 != null) {
            var2.writeInt32(1, var7);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.squareindex)) {
         Integer var8 = var1.squareindex;
         if (var8 != null) {
            var2.writeInt32(2, var8);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.score)) {
         Integer var9 = var1.score;
         if (var9 != null) {
            var2.writeInt32(3, var9);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.result)) {
         Boolean var10 = var1.result;
         if (var10 != null) {
            var2.writeBool(4, var10);
         }
      }

   }

   public void writeTo(REQ_GUILD_BINGO_CHALLENGE_SQUARE_RESULT var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_GUILD_BINGO_CHALLENGE_SQUARE_RESULT readFrom(CodedInputStream var1) throws IOException {
      REQ_GUILD_BINGO_CHALLENGE_SQUARE_RESULT var2 = new REQ_GUILD_BINGO_CHALLENGE_SQUARE_RESULT();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.maplevel = var1.readInt32();
            } else if (var5 == 16) {
               var2.squareindex = var1.readInt32();
            } else if (var5 == 24) {
               var2.score = var1.readInt32();
            } else if (var5 == 32) {
               var2.result = var1.readBool();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_GUILD_BINGO_CHALLENGE_SQUARE_RESULT.class);
         return this.descriptor = var1;
      }
   }
}
