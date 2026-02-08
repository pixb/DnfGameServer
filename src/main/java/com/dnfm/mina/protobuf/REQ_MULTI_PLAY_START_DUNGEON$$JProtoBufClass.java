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

public class REQ_MULTI_PLAY_START_DUNGEON$$JProtoBufClass implements Codec<REQ_MULTI_PLAY_START_DUNGEON>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_MULTI_PLAY_START_DUNGEON var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_MULTI_PLAY_START_DUNGEON decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_MULTI_PLAY_START_DUNGEON var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var7 = var1.dungeonguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var7);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.intrude)) {
         Boolean var8 = var1.intrude;
         var2 += CodedOutputStream.computeBoolSize(2, var8);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.gamesafedata)) {
         String var9 = var1.gamesafedata;
         var2 += CodedOutputStream.computeStringSize(3, var9);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.gamesafedatacrc)) {
         String var10 = var1.gamesafedatacrc;
         var2 += CodedOutputStream.computeStringSize(4, var10);
      }

      return var2;
   }

   public void doWriteTo(REQ_MULTI_PLAY_START_DUNGEON var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var7 = var1.dungeonguid;
         if (var7 != null) {
            var2.writeUInt64(1, var7);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.intrude)) {
         Boolean var8 = var1.intrude;
         if (var8 != null) {
            var2.writeBool(2, var8);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.gamesafedata)) {
         String var9 = var1.gamesafedata;
         if (var9 != null) {
            var2.writeString(3, var9);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.gamesafedatacrc)) {
         String var10 = var1.gamesafedatacrc;
         if (var10 != null) {
            var2.writeString(4, var10);
         }
      }

   }

   public void writeTo(REQ_MULTI_PLAY_START_DUNGEON var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_MULTI_PLAY_START_DUNGEON readFrom(CodedInputStream var1) throws IOException {
      REQ_MULTI_PLAY_START_DUNGEON var2 = new REQ_MULTI_PLAY_START_DUNGEON();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.dungeonguid = var1.readUInt64();
            } else if (var5 == 16) {
               var2.intrude = var1.readBool();
            } else if (var5 == 26) {
               var2.gamesafedata = var1.readString();
            } else if (var5 == 34) {
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_MULTI_PLAY_START_DUNGEON.class);
         return this.descriptor = var1;
      }
   }
}
