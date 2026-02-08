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

public class REQ_RANK_TRAINING_FINISH$$JProtoBufClass implements Codec<REQ_RANK_TRAINING_FINISH>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_RANK_TRAINING_FINISH var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_RANK_TRAINING_FINISH decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_RANK_TRAINING_FINISH var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.damageanalysis)) {
         List var6 = var1.damageanalysis;
         var2 += CodedConstant.computeListSize(1, var6, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.totaldamage)) {
         Long var7 = var1.totaldamage;
         var2 += CodedOutputStream.computeInt64Size(2, var7);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.trainingstop)) {
         Boolean var8 = var1.trainingstop;
         var2 += CodedOutputStream.computeBoolSize(3, var8);
      }

      return var2;
   }

   public void doWriteTo(REQ_RANK_TRAINING_FINISH var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.damageanalysis)) {
         List var6 = var1.damageanalysis;
         if (var6 != null) {
            CodedConstant.writeToList(var2, 1, FieldType.OBJECT, var6, false);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.totaldamage)) {
         Long var7 = var1.totaldamage;
         if (var7 != null) {
            var2.writeInt64(2, var7);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.trainingstop)) {
         Boolean var8 = var1.trainingstop;
         if (var8 != null) {
            var2.writeBool(3, var8);
         }
      }

   }

   public void writeTo(REQ_RANK_TRAINING_FINISH var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_RANK_TRAINING_FINISH readFrom(CodedInputStream var1) throws IOException {
      REQ_RANK_TRAINING_FINISH var2 = new REQ_RANK_TRAINING_FINISH();
      var2.damageanalysis = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 10) {
               Codec var10 = ProtobufProxy.create(PT_DAMAGE_ANALYSIS.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.damageanalysis == null) {
                  var2.damageanalysis = new ArrayList();
               }

               var2.damageanalysis.add((PT_DAMAGE_ANALYSIS)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 16) {
               var2.totaldamage = var1.readInt64();
            } else if (var5 == 24) {
               var2.trainingstop = var1.readBool();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_RANK_TRAINING_FINISH.class);
         return this.descriptor = var1;
      }
   }
}
