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

public class RES_COMPOSE_BOOKMARK$$JProtoBufClass implements Codec<RES_COMPOSE_BOOKMARK>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_COMPOSE_BOOKMARK var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_COMPOSE_BOOKMARK decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_COMPOSE_BOOKMARK var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var8 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var8);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.bookmarks)) {
         List var9 = var1.bookmarks;
         var2 += CodedConstant.computeListSize(2, var9, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.rewards)) {
         PT_CONTENTS_REWARD_INFO var10 = var1.rewards;
         var2 += CodedConstant.computeSize(3, var10, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.victory)) {
         Boolean var11 = var1.victory;
         var2 += CodedOutputStream.computeBoolSize(4, var11);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.bookmarkcomposedailycount)) {
         Integer var12 = var1.bookmarkcomposedailycount;
         var2 += CodedOutputStream.computeInt32Size(5, var12);
      }

      return var2;
   }

   public void doWriteTo(RES_COMPOSE_BOOKMARK var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var8 = var1.error;
         if (var8 != null) {
            var2.writeInt32(1, var8);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.bookmarks)) {
         List var9 = var1.bookmarks;
         if (var9 != null) {
            CodedConstant.writeToList(var2, 2, FieldType.OBJECT, var9, false);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.rewards)) {
         PT_CONTENTS_REWARD_INFO var10 = var1.rewards;
         if (var10 != null) {
            CodedConstant.writeObject(var2, 3, FieldType.OBJECT, var10, false);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.victory)) {
         Boolean var11 = var1.victory;
         if (var11 != null) {
            var2.writeBool(4, var11);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.bookmarkcomposedailycount)) {
         Integer var12 = var1.bookmarkcomposedailycount;
         if (var12 != null) {
            var2.writeInt32(5, var12);
         }
      }

   }

   public void writeTo(RES_COMPOSE_BOOKMARK var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_COMPOSE_BOOKMARK readFrom(CodedInputStream var1) throws IOException {
      RES_COMPOSE_BOOKMARK var2 = new RES_COMPOSE_BOOKMARK();
      var2.bookmarks = new ArrayList();

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
               Codec var10 = ProtobufProxy.create(PT_BOOKMARK_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.bookmarks == null) {
                  var2.bookmarks = new ArrayList();
               }

               var2.bookmarks.add((PT_BOOKMARK_ITEM)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 26) {
               Codec var11 = ProtobufProxy.create(PT_CONTENTS_REWARD_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var12 = var1.readRawVarint32();
               int var13 = var1.pushLimit(var12);
               var2.rewards = (PT_CONTENTS_REWARD_INFO)var11.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var13);
            } else if (var5 == 32) {
               var2.victory = var1.readBool();
            } else if (var5 == 40) {
               var2.bookmarkcomposedailycount = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_COMPOSE_BOOKMARK.class);
         return this.descriptor = var1;
      }
   }
}
