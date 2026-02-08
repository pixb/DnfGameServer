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

public class REQ_ITEM_USE$$JProtoBufClass implements Codec<REQ_ITEM_USE>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_ITEM_USE var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_ITEM_USE decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_ITEM_USE var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var11 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(1, var11);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var12 = var1.count;
         var2 += CodedOutputStream.computeInt32Size(2, var12);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.bind)) {
         Boolean var13 = var1.bind;
         var2 += CodedOutputStream.computeBoolSize(3, var13);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var14 = var1.guid;
         var2 += CodedOutputStream.computeUInt64Size(4, var14);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var15 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(5, var15);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.input)) {
         String var16 = var1.input;
         var2 += CodedOutputStream.computeStringSize(6, var16);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.selectitemindex)) {
         Integer var17 = var1.selectitemindex;
         var2 += CodedOutputStream.computeInt32Size(7, var17);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.quest)) {
         List var18 = var1.quest;
         var2 += CodedConstant.computeListSize(8, var18, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(REQ_ITEM_USE var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var11 = var1.index;
         if (var11 != null) {
            var2.writeInt32(1, var11);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var12 = var1.count;
         if (var12 != null) {
            var2.writeInt32(2, var12);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.bind)) {
         Boolean var13 = var1.bind;
         if (var13 != null) {
            var2.writeBool(3, var13);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var14 = var1.guid;
         if (var14 != null) {
            var2.writeUInt64(4, var14);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var15 = var1.type;
         if (var15 != null) {
            var2.writeInt32(5, var15);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.input)) {
         String var16 = var1.input;
         if (var16 != null) {
            var2.writeString(6, var16);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.selectitemindex)) {
         Integer var17 = var1.selectitemindex;
         if (var17 != null) {
            var2.writeInt32(7, var17);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.quest)) {
         List var18 = var1.quest;
         if (var18 != null) {
            CodedConstant.writeToList(var2, 8, FieldType.OBJECT, var18, false);
         }
      }

   }

   public void writeTo(REQ_ITEM_USE var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_ITEM_USE readFrom(CodedInputStream var1) throws IOException {
      REQ_ITEM_USE var2 = new REQ_ITEM_USE();
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
               var2.index = var1.readInt32();
            } else if (var5 == 16) {
               var2.count = var1.readInt32();
            } else if (var5 == 24) {
               var2.bind = var1.readBool();
            } else if (var5 == 32) {
               var2.guid = var1.readUInt64();
            } else if (var5 == 40) {
               var2.type = var1.readInt32();
            } else if (var5 == 50) {
               var2.input = var1.readString();
            } else if (var5 == 56) {
               var2.selectitemindex = var1.readInt32();
            } else if (var5 == 66) {
               Codec var10 = ProtobufProxy.create(PT_QUEST.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.quest == null) {
                  var2.quest = new ArrayList();
               }

               var2.quest.add((PT_QUEST)var10.readFrom(var1));
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_ITEM_USE.class);
         return this.descriptor = var1;
      }
   }
}
