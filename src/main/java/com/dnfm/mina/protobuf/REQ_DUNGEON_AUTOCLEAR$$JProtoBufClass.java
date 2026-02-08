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

public class REQ_DUNGEON_AUTOCLEAR$$JProtoBufClass implements Codec<REQ_DUNGEON_AUTOCLEAR>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_DUNGEON_AUTOCLEAR var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_DUNGEON_AUTOCLEAR decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_DUNGEON_AUTOCLEAR var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var8 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(1, var8);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.ratio)) {
         Float var9 = var1.ratio;
         var2 += CodedOutputStream.computeFloatSize(2, var9);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.step)) {
         Integer var10 = var1.step;
         var2 += CodedOutputStream.computeInt32Size(3, var10);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.gamesafedata)) {
         String var11 = var1.gamesafedata;
         var2 += CodedOutputStream.computeStringSize(4, var11);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.gamesafedatacrc)) {
         String var12 = var1.gamesafedatacrc;
         var2 += CodedOutputStream.computeStringSize(5, var12);
      }

      return var2;
   }

   public void doWriteTo(REQ_DUNGEON_AUTOCLEAR var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var8 = var1.index;
         if (var8 != null) {
            var2.writeInt32(1, var8);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.ratio)) {
         Float var9 = var1.ratio;
         if (var9 != null) {
            var2.writeFloat(2, var9);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.step)) {
         Integer var10 = var1.step;
         if (var10 != null) {
            var2.writeInt32(3, var10);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.gamesafedata)) {
         String var11 = var1.gamesafedata;
         if (var11 != null) {
            var2.writeString(4, var11);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.gamesafedatacrc)) {
         String var12 = var1.gamesafedatacrc;
         if (var12 != null) {
            var2.writeString(5, var12);
         }
      }

   }

   public void writeTo(REQ_DUNGEON_AUTOCLEAR var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_DUNGEON_AUTOCLEAR readFrom(CodedInputStream var1) throws IOException {
      REQ_DUNGEON_AUTOCLEAR var2 = new REQ_DUNGEON_AUTOCLEAR();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.index = var1.readInt32();
            } else if (var5 == 21) {
               var2.ratio = var1.readFloat();
            } else if (var5 == 24) {
               var2.step = var1.readInt32();
            } else if (var5 == 34) {
               var2.gamesafedata = var1.readString();
            } else if (var5 == 42) {
               var2.gamesafedatacrc = var1.readString();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_DUNGEON_AUTOCLEAR.class);
         return this.descriptor = var1;
      }
   }
}
