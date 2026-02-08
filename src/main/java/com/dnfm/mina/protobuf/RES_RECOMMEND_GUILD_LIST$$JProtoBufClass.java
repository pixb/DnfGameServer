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
import java.util.ArrayList;
import java.util.List;

public class RES_RECOMMEND_GUILD_LIST$$JProtoBufClass implements Codec<RES_RECOMMEND_GUILD_LIST>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_RECOMMEND_GUILD_LIST var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_RECOMMEND_GUILD_LIST decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_RECOMMEND_GUILD_LIST var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var8 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var8);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.lastkicktime)) {
         Long var9 = var1.lastkicktime;
         var2 += CodedOutputStream.computeInt64Size(2, var9);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.freejoin)) {
         Boolean var10 = var1.freejoin;
         var2 += CodedOutputStream.computeBoolSize(3, var10);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.freejoinMinlevel)) {
         Integer var11 = var1.freejoinMinlevel;
         var2 += CodedOutputStream.computeInt32Size(4, var11);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.list)) {
         List var12 = var1.list;
         var2 += CodedConstant.computeListSize(5, var12, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(RES_RECOMMEND_GUILD_LIST var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var8 = var1.error;
         if (var8 != null) {
            var2.writeInt32(1, var8);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.lastkicktime)) {
         Long var9 = var1.lastkicktime;
         if (var9 != null) {
            var2.writeInt64(2, var9);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.freejoin)) {
         Boolean var10 = var1.freejoin;
         if (var10 != null) {
            var2.writeBool(3, var10);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.freejoinMinlevel)) {
         Integer var11 = var1.freejoinMinlevel;
         if (var11 != null) {
            var2.writeInt32(4, var11);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.list)) {
         List var12 = var1.list;
         if (var12 != null) {
            CodedConstant.writeToList(var2, 5, FieldType.OBJECT, var12, false);
         }
      }

   }

   public void writeTo(RES_RECOMMEND_GUILD_LIST var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_RECOMMEND_GUILD_LIST readFrom(CodedInputStream var1) throws IOException {
      RES_RECOMMEND_GUILD_LIST var2 = new RES_RECOMMEND_GUILD_LIST();
      var2.list = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.error = var1.readInt32();
            } else if (var5 == 16) {
               var2.lastkicktime = var1.readInt64();
            } else if (var5 == 24) {
               var2.freejoin = var1.readBool();
            } else if (var5 == 32) {
               var2.freejoinMinlevel = var1.readInt32();
            } else if (var5 == 42) {
               Codec var10 = ProtobufProxy.create(PT_RECOMMEND_GUILD.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.list == null) {
                  var2.list = new ArrayList();
               }

               var2.list.add((PT_RECOMMEND_GUILD)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_RECOMMEND_GUILD_LIST.class);
         return this.descriptor = var1;
      }
   }
}
