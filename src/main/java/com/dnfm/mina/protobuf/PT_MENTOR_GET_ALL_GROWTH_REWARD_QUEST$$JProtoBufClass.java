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

public class PT_MENTOR_GET_ALL_GROWTH_REWARD_QUEST$$JProtoBufClass implements Codec<PT_MENTOR_GET_ALL_GROWTH_REWARD_QUEST>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_MENTOR_GET_ALL_GROWTH_REWARD_QUEST var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_MENTOR_GET_ALL_GROWTH_REWARD_QUEST decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_MENTOR_GET_ALL_GROWTH_REWARD_QUEST var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.addmentorcoin)) {
         Integer var7 = var1.addmentorcoin;
         var2 += CodedOutputStream.computeInt32Size(1, var7);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.totalmentorcoin)) {
         Integer var8 = var1.totalmentorcoin;
         var2 += CodedOutputStream.computeInt32Size(2, var8);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.resettime)) {
         Long var9 = var1.resettime;
         var2 += CodedOutputStream.computeInt64Size(3, var9);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.quest)) {
         List var10 = var1.quest;
         var2 += CodedConstant.computeListSize(4, var10, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(PT_MENTOR_GET_ALL_GROWTH_REWARD_QUEST var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.addmentorcoin)) {
         Integer var7 = var1.addmentorcoin;
         if (var7 != null) {
            var2.writeInt32(1, var7);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.totalmentorcoin)) {
         Integer var8 = var1.totalmentorcoin;
         if (var8 != null) {
            var2.writeInt32(2, var8);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.resettime)) {
         Long var9 = var1.resettime;
         if (var9 != null) {
            var2.writeInt64(3, var9);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.quest)) {
         List var10 = var1.quest;
         if (var10 != null) {
            CodedConstant.writeToList(var2, 4, FieldType.OBJECT, var10, false);
         }
      }

   }

   public void writeTo(PT_MENTOR_GET_ALL_GROWTH_REWARD_QUEST var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_MENTOR_GET_ALL_GROWTH_REWARD_QUEST readFrom(CodedInputStream var1) throws IOException {
      PT_MENTOR_GET_ALL_GROWTH_REWARD_QUEST var2 = new PT_MENTOR_GET_ALL_GROWTH_REWARD_QUEST();
      var2.quest = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.addmentorcoin = var1.readInt32();
            } else if (var5 == 16) {
               var2.totalmentorcoin = var1.readInt32();
            } else if (var5 == 24) {
               var2.resettime = var1.readInt64();
            } else if (var5 == 34) {
               Codec var10 = ProtobufProxy.create(PT_MENTOR_QUEST_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.quest == null) {
                  var2.quest = new ArrayList();
               }

               var2.quest.add((PT_MENTOR_QUEST_INFO)var10.readFrom(var1));
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_MENTOR_GET_ALL_GROWTH_REWARD_QUEST.class);
         return this.descriptor = var1;
      }
   }
}
