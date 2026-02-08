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

public class PT_GUILD_BINGO_MAP_CLEAR_PAST_REWARD_INFO$$JProtoBufClass implements Codec<PT_GUILD_BINGO_MAP_CLEAR_PAST_REWARD_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_GUILD_BINGO_MAP_CLEAR_PAST_REWARD_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_GUILD_BINGO_MAP_CLEAR_PAST_REWARD_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_GUILD_BINGO_MAP_CLEAR_PAST_REWARD_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.maplevel)) {
         Integer var6 = var1.maplevel;
         var2 += CodedOutputStream.computeInt32Size(1, var6);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.rewarditems)) {
         List var7 = var1.rewarditems;
         var2 += CodedConstant.computeListSize(2, var7, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.received)) {
         Boolean var8 = var1.received;
         var2 += CodedOutputStream.computeBoolSize(3, var8);
      }

      return var2;
   }

   public void doWriteTo(PT_GUILD_BINGO_MAP_CLEAR_PAST_REWARD_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.maplevel)) {
         Integer var6 = var1.maplevel;
         if (var6 != null) {
            var2.writeInt32(1, var6);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.rewarditems)) {
         List var7 = var1.rewarditems;
         if (var7 != null) {
            CodedConstant.writeToList(var2, 2, FieldType.OBJECT, var7, false);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.received)) {
         Boolean var8 = var1.received;
         if (var8 != null) {
            var2.writeBool(3, var8);
         }
      }

   }

   public void writeTo(PT_GUILD_BINGO_MAP_CLEAR_PAST_REWARD_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_GUILD_BINGO_MAP_CLEAR_PAST_REWARD_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_GUILD_BINGO_MAP_CLEAR_PAST_REWARD_INFO var2 = new PT_GUILD_BINGO_MAP_CLEAR_PAST_REWARD_INFO();
      var2.rewarditems = new ArrayList();

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
            } else if (var5 == 18) {
               Codec var10 = ProtobufProxy.create(PT_GUILD_BINGO_REWARD_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.rewarditems == null) {
                  var2.rewarditems = new ArrayList();
               }

               var2.rewarditems.add((PT_GUILD_BINGO_REWARD_ITEM)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 24) {
               var2.received = var1.readBool();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_GUILD_BINGO_MAP_CLEAR_PAST_REWARD_INFO.class);
         return this.descriptor = var1;
      }
   }
}
