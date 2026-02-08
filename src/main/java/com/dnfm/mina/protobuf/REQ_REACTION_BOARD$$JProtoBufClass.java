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

public class REQ_REACTION_BOARD$$JProtoBufClass implements Codec<REQ_REACTION_BOARD>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_REACTION_BOARD var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_REACTION_BOARD decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_REACTION_BOARD var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.boardtype)) {
         Integer var9 = var1.boardtype;
         var2 += CodedOutputStream.computeInt32Size(1, var9);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var10 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(2, var10);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.reaction)) {
         Integer var11 = var1.reaction;
         var2 += CodedOutputStream.computeInt32Size(3, var11);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var12 = var1.guid;
         var2 += CodedOutputStream.computeUInt64Size(4, var12);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.receiverguid)) {
         Long var13 = var1.receiverguid;
         var2 += CodedOutputStream.computeUInt64Size(5, var13);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var14 = var1.job;
         var2 += CodedOutputStream.computeInt32Size(6, var14);
      }

      return var2;
   }

   public void doWriteTo(REQ_REACTION_BOARD var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.boardtype)) {
         Integer var9 = var1.boardtype;
         if (var9 != null) {
            var2.writeInt32(1, var9);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var10 = var1.index;
         if (var10 != null) {
            var2.writeInt32(2, var10);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.reaction)) {
         Integer var11 = var1.reaction;
         if (var11 != null) {
            var2.writeInt32(3, var11);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var12 = var1.guid;
         if (var12 != null) {
            var2.writeUInt64(4, var12);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.receiverguid)) {
         Long var13 = var1.receiverguid;
         if (var13 != null) {
            var2.writeUInt64(5, var13);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var14 = var1.job;
         if (var14 != null) {
            var2.writeInt32(6, var14);
         }
      }

   }

   public void writeTo(REQ_REACTION_BOARD var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_REACTION_BOARD readFrom(CodedInputStream var1) throws IOException {
      REQ_REACTION_BOARD var2 = new REQ_REACTION_BOARD();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.boardtype = var1.readInt32();
            } else if (var5 == 16) {
               var2.index = var1.readInt32();
            } else if (var5 == 24) {
               var2.reaction = var1.readInt32();
            } else if (var5 == 32) {
               var2.guid = var1.readUInt64();
            } else if (var5 == 40) {
               var2.receiverguid = var1.readUInt64();
            } else if (var5 == 48) {
               var2.job = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_REACTION_BOARD.class);
         return this.descriptor = var1;
      }
   }
}
