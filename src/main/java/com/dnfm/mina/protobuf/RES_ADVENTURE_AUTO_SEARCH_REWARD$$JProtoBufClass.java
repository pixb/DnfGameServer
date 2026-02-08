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

public class RES_ADVENTURE_AUTO_SEARCH_REWARD$$JProtoBufClass implements Codec<RES_ADVENTURE_AUTO_SEARCH_REWARD>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_ADVENTURE_AUTO_SEARCH_REWARD var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_ADVENTURE_AUTO_SEARCH_REWARD decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_ADVENTURE_AUTO_SEARCH_REWARD var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var8 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var8);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.remaincount)) {
         Integer var9 = var1.remaincount;
         var2 += CodedOutputStream.computeInt32Size(2, var9);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.info)) {
         PT_CONTENTS_REWARD_INFO var10 = var1.info;
         var2 += CodedConstant.computeSize(3, var10, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.adventureunionlevel)) {
         Integer var11 = var1.adventureunionlevel;
         var2 += CodedOutputStream.computeInt32Size(4, var11);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.adventureunionexp)) {
         Long var12 = var1.adventureunionexp;
         var2 += CodedOutputStream.computeInt64Size(5, var12);
      }

      return var2;
   }

   public void doWriteTo(RES_ADVENTURE_AUTO_SEARCH_REWARD var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var8 = var1.error;
         if (var8 != null) {
            var2.writeInt32(1, var8);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.remaincount)) {
         Integer var9 = var1.remaincount;
         if (var9 != null) {
            var2.writeInt32(2, var9);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.info)) {
         PT_CONTENTS_REWARD_INFO var10 = var1.info;
         if (var10 != null) {
            CodedConstant.writeObject(var2, 3, FieldType.OBJECT, var10, false);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.adventureunionlevel)) {
         Integer var11 = var1.adventureunionlevel;
         if (var11 != null) {
            var2.writeInt32(4, var11);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.adventureunionexp)) {
         Long var12 = var1.adventureunionexp;
         if (var12 != null) {
            var2.writeInt64(5, var12);
         }
      }

   }

   public void writeTo(RES_ADVENTURE_AUTO_SEARCH_REWARD var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_ADVENTURE_AUTO_SEARCH_REWARD readFrom(CodedInputStream var1) throws IOException {
      RES_ADVENTURE_AUTO_SEARCH_REWARD var2 = new RES_ADVENTURE_AUTO_SEARCH_REWARD();

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
               var2.remaincount = var1.readInt32();
            } else if (var5 == 26) {
               Codec var10 = ProtobufProxy.create(PT_CONTENTS_REWARD_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.info = (PT_CONTENTS_REWARD_INFO)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 32) {
               var2.adventureunionlevel = var1.readInt32();
            } else if (var5 == 40) {
               var2.adventureunionexp = var1.readInt64();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_ADVENTURE_AUTO_SEARCH_REWARD.class);
         return this.descriptor = var1;
      }
   }
}
