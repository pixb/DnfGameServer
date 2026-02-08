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

public class PT_TUTORIAL_DATA$$JProtoBufClass implements Codec<PT_TUTORIAL_DATA>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_TUTORIAL_DATA var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_TUTORIAL_DATA decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_TUTORIAL_DATA var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var8 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(1, var8);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.state)) {
         Integer var9 = var1.state;
         var2 += CodedOutputStream.computeInt32Size(2, var9);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var10 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(3, var10);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.enforce)) {
         Boolean var11 = var1.enforce;
         var2 += CodedOutputStream.computeBoolSize(4, var11);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.tutorialreward)) {
         PT_TUTORIAL_REWARD var12 = var1.tutorialreward;
         var2 += CodedConstant.computeSize(5, var12, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      return var2;
   }

   public void doWriteTo(PT_TUTORIAL_DATA var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var8 = var1.index;
         if (var8 != null) {
            var2.writeInt32(1, var8);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.state)) {
         Integer var9 = var1.state;
         if (var9 != null) {
            var2.writeInt32(2, var9);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var10 = var1.type;
         if (var10 != null) {
            var2.writeInt32(3, var10);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.enforce)) {
         Boolean var11 = var1.enforce;
         if (var11 != null) {
            var2.writeBool(4, var11);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.tutorialreward)) {
         PT_TUTORIAL_REWARD var12 = var1.tutorialreward;
         if (var12 != null) {
            CodedConstant.writeObject(var2, 5, FieldType.OBJECT, var12, false);
         }
      }

   }

   public void writeTo(PT_TUTORIAL_DATA var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_TUTORIAL_DATA readFrom(CodedInputStream var1) throws IOException {
      PT_TUTORIAL_DATA var2 = new PT_TUTORIAL_DATA();

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
               var2.state = var1.readInt32();
            } else if (var5 == 24) {
               var2.type = var1.readInt32();
            } else if (var5 == 32) {
               var2.enforce = var1.readBool();
            } else if (var5 == 42) {
               Codec var10 = ProtobufProxy.create(PT_TUTORIAL_REWARD.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.tutorialreward = (PT_TUTORIAL_REWARD)var10.readFrom(var1);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_TUTORIAL_DATA.class);
         return this.descriptor = var1;
      }
   }
}
