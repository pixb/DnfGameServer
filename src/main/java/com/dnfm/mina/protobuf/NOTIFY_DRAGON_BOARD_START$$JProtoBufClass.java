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

public class NOTIFY_DRAGON_BOARD_START$$JProtoBufClass implements Codec<NOTIFY_DRAGON_BOARD_START>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(NOTIFY_DRAGON_BOARD_START var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public NOTIFY_DRAGON_BOARD_START decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(NOTIFY_DRAGON_BOARD_START var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var6 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var6);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.starttime)) {
         Long var7 = var1.starttime;
         var2 += CodedOutputStream.computeInt64Size(2, var7);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.cards)) {
         List var8 = var1.cards;
         var2 += CodedConstant.computeListSize(3, var8, FieldType.UINT64, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      return var2;
   }

   public void doWriteTo(NOTIFY_DRAGON_BOARD_START var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var6 = var1.error;
         if (var6 != null) {
            var2.writeInt32(1, var6);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.starttime)) {
         Long var7 = var1.starttime;
         if (var7 != null) {
            var2.writeInt64(2, var7);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.cards)) {
         List var8 = var1.cards;
         if (var8 != null) {
            CodedConstant.writeToList(var2, 3, FieldType.UINT64, var8, true);
         }
      }

   }

   public void writeTo(NOTIFY_DRAGON_BOARD_START var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public NOTIFY_DRAGON_BOARD_START readFrom(CodedInputStream var1) throws IOException {
      NOTIFY_DRAGON_BOARD_START var2 = new NOTIFY_DRAGON_BOARD_START();
      var2.cards = new ArrayList();

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
               var2.starttime = var1.readInt64();
            } else if (var5 == 24) {
               if (var2.cards == null) {
                  var2.cards = new ArrayList();
               }

               var2.cards.add(var1.readUInt64());
            } else if (var5 != 26) {
               var1.skipField(var5);
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.cards == null) {
                  var2.cards = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.cards.add(var1.readUInt64());
               }

               var1.popLimit(var7);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(NOTIFY_DRAGON_BOARD_START.class);
         return this.descriptor = var1;
      }
   }
}
