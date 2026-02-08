package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.baidu.bjf.remoting.protobuf.code.CodedConstant;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.InvalidProtocolBufferException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class REQ_MULTI_PLAY_SYNC_DUNGEON$$JProtoBufClass implements Codec<REQ_MULTI_PLAY_SYNC_DUNGEON>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_MULTI_PLAY_SYNC_DUNGEON var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_MULTI_PLAY_SYNC_DUNGEON decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_MULTI_PLAY_SYNC_DUNGEON var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.authkey)) {
         String var9 = var1.authkey;
         var2 += CodedOutputStream.computeStringSize(1, var9);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var10 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(2, var10);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.matchingguid)) {
         Long var11 = var1.matchingguid;
         var2 += CodedOutputStream.computeUInt64Size(3, var11);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.eamplify)) {
         Boolean var12 = var1.eamplify;
         var2 += CodedOutputStream.computeBoolSize(4, var12);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.verification)) {
         PT_VERIFICATION var13 = var1.verification;
         var2 += CodedConstant.computeSize(5, var13, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.gamesafedata)) {
         String var14 = var1.gamesafedata;
         var2 += CodedOutputStream.computeStringSize(6, var14);
      }

      return var2;
   }

   public void doWriteTo(REQ_MULTI_PLAY_SYNC_DUNGEON var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.authkey)) {
         String var9 = var1.authkey;
         if (var9 != null) {
            var2.writeString(1, var9);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var10 = var1.type;
         if (var10 != null) {
            var2.writeInt32(2, var10);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.matchingguid)) {
         Long var11 = var1.matchingguid;
         if (var11 != null) {
            var2.writeUInt64(3, var11);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.eamplify)) {
         Boolean var12 = var1.eamplify;
         if (var12 != null) {
            var2.writeBool(4, var12);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.verification)) {
         PT_VERIFICATION var13 = var1.verification;
         if (var13 != null) {
            CodedConstant.writeObject(var2, 5, FieldType.OBJECT, var13, false);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.gamesafedata)) {
         String var14 = var1.gamesafedata;
         if (var14 != null) {
            var2.writeString(6, var14);
         }
      }

   }

   public void writeTo(REQ_MULTI_PLAY_SYNC_DUNGEON var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_MULTI_PLAY_SYNC_DUNGEON readFrom(CodedInputStream var1) throws IOException {
      REQ_MULTI_PLAY_SYNC_DUNGEON var2 = new REQ_MULTI_PLAY_SYNC_DUNGEON();

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
            } else if (var5 == 16) {
               var2.type = var1.readInt32();
            } else if (var5 == 24) {
               var2.matchingguid = var1.readUInt64();
            } else if (var5 == 32) {
               var2.eamplify = var1.readBool();
            } else if (var5 == 42) {
               Codec var10 = ProtobufProxy.create(PT_VERIFICATION.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.verification = (PT_VERIFICATION)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 50) {
               var2.gamesafedata = var1.readString();
            } else {
               var1.skipField(var5);
            }
         }

         return var2;
      } catch (InvalidProtocolBufferException var8) {
         throw var8;
      } catch (IOException var9) {
         throw var9;
      }
   }

   public Descriptors.Descriptor getDescriptor() throws IOException {
      if (this.descriptor != null) {
         return this.descriptor;
      } else {
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_MULTI_PLAY_SYNC_DUNGEON.class);
         return this.descriptor = var1;
      }
   }
}
