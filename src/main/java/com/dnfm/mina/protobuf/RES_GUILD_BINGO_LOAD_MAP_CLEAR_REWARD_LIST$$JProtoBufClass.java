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

public class RES_GUILD_BINGO_LOAD_MAP_CLEAR_REWARD_LIST$$JProtoBufClass implements Codec<RES_GUILD_BINGO_LOAD_MAP_CLEAR_REWARD_LIST>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_GUILD_BINGO_LOAD_MAP_CLEAR_REWARD_LIST var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_GUILD_BINGO_LOAD_MAP_CLEAR_REWARD_LIST decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_GUILD_BINGO_LOAD_MAP_CLEAR_REWARD_LIST var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var6 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var6);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.rewardlist)) {
         List var7 = var1.rewardlist;
         var2 += CodedConstant.computeListSize(2, var7, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.pastrewardlist)) {
         List var8 = var1.pastrewardlist;
         var2 += CodedConstant.computeListSize(3, var8, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(RES_GUILD_BINGO_LOAD_MAP_CLEAR_REWARD_LIST var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var6 = var1.error;
         if (var6 != null) {
            var2.writeInt32(1, var6);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.rewardlist)) {
         List var7 = var1.rewardlist;
         if (var7 != null) {
            CodedConstant.writeToList(var2, 2, FieldType.OBJECT, var7, false);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.pastrewardlist)) {
         List var8 = var1.pastrewardlist;
         if (var8 != null) {
            CodedConstant.writeToList(var2, 3, FieldType.OBJECT, var8, false);
         }
      }

   }

   public void writeTo(RES_GUILD_BINGO_LOAD_MAP_CLEAR_REWARD_LIST var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_GUILD_BINGO_LOAD_MAP_CLEAR_REWARD_LIST readFrom(CodedInputStream var1) throws IOException {
      RES_GUILD_BINGO_LOAD_MAP_CLEAR_REWARD_LIST var2 = new RES_GUILD_BINGO_LOAD_MAP_CLEAR_REWARD_LIST();
      var2.rewardlist = new ArrayList();
      var2.pastrewardlist = new ArrayList();

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
            } else if (var5 == 18) {
               Codec var10 = ProtobufProxy.create(PT_GUILD_BINGO_MAP_CLEAR_REWARD_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.rewardlist == null) {
                  var2.rewardlist = new ArrayList();
               }

               var2.rewardlist.add((PT_GUILD_BINGO_MAP_CLEAR_REWARD_INFO)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 26) {
               Codec var11 = ProtobufProxy.create(PT_GUILD_BINGO_MAP_CLEAR_PAST_REWARD_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var12 = var1.readRawVarint32();
               int var13 = var1.pushLimit(var12);
               if (var2.pastrewardlist == null) {
                  var2.pastrewardlist = new ArrayList();
               }

               var2.pastrewardlist.add((PT_GUILD_BINGO_MAP_CLEAR_PAST_REWARD_INFO)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var13);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_GUILD_BINGO_LOAD_MAP_CLEAR_REWARD_LIST.class);
         return this.descriptor = var1;
      }
   }
}
