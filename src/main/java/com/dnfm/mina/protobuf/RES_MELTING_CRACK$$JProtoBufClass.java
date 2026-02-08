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

public class RES_MELTING_CRACK$$JProtoBufClass implements Codec<RES_MELTING_CRACK>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_MELTING_CRACK var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_MELTING_CRACK decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_MELTING_CRACK var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var7 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var7);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.crack)) {
         List var8 = var1.crack;
         var2 += CodedConstant.computeListSize(2, var8, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.crackequips)) {
         List var9 = var1.crackequips;
         var2 += CodedConstant.computeListSize(3, var9, FieldType.UINT64, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.rewards)) {
         PT_CONTENTS_REWARD_INFO var10 = var1.rewards;
         var2 += CodedConstant.computeSize(4, var10, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      return var2;
   }

   public void doWriteTo(RES_MELTING_CRACK var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var7 = var1.error;
         if (var7 != null) {
            var2.writeInt32(1, var7);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.crack)) {
         List var8 = var1.crack;
         if (var8 != null) {
            CodedConstant.writeToList(var2, 2, FieldType.OBJECT, var8, false);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.crackequips)) {
         List var9 = var1.crackequips;
         if (var9 != null) {
            CodedConstant.writeToList(var2, 3, FieldType.UINT64, var9, true);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.rewards)) {
         PT_CONTENTS_REWARD_INFO var10 = var1.rewards;
         if (var10 != null) {
            CodedConstant.writeObject(var2, 4, FieldType.OBJECT, var10, false);
         }
      }

   }

   public void writeTo(RES_MELTING_CRACK var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_MELTING_CRACK readFrom(CodedInputStream var1) throws IOException {
      RES_MELTING_CRACK var2 = new RES_MELTING_CRACK();
      var2.crack = new ArrayList();
      var2.crackequips = new ArrayList();

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
               Codec var11 = ProtobufProxy.create(PT_METING_CRACK_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var14 = var1.readRawVarint32();
               int var17 = var1.pushLimit(var14);
               if (var2.crack == null) {
                  var2.crack = new ArrayList();
               }

               var2.crack.add((PT_METING_CRACK_INFO)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var17);
            } else if (var5 == 24) {
               if (var2.crackequips == null) {
                  var2.crackequips = new ArrayList();
               }

               var2.crackequips.add(var1.readUInt64());
            } else if (var5 == 26) {
               int var13 = var1.readRawVarint32();
               int var16 = var1.pushLimit(var13);
               if (var2.crackequips == null) {
                  var2.crackequips = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.crackequips.add(var1.readUInt64());
               }

               var1.popLimit(var16);
            } else if (var5 != 34) {
               var1.skipField(var5);
            } else if (var5 != 26) {
               Codec var10 = ProtobufProxy.create(PT_CONTENTS_REWARD_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var12 = var1.readRawVarint32();
               int var15 = var1.pushLimit(var12);
               var2.rewards = (PT_CONTENTS_REWARD_INFO)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var15);
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.crackequips == null) {
                  var2.crackequips = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.crackequips.add(var1.readUInt64());
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_MELTING_CRACK.class);
         return this.descriptor = var1;
      }
   }
}
