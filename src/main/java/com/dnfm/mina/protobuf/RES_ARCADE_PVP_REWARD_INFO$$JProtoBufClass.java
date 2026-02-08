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

public class RES_ARCADE_PVP_REWARD_INFO$$JProtoBufClass implements Codec<RES_ARCADE_PVP_REWARD_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_ARCADE_PVP_REWARD_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_ARCADE_PVP_REWARD_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_ARCADE_PVP_REWARD_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var9 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var9);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.pvpstargrade)) {
         Integer var10 = var1.pvpstargrade;
         var2 += CodedOutputStream.computeInt32Size(2, var10);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.winpoint)) {
         Integer var11 = var1.winpoint;
         var2 += CodedOutputStream.computeInt32Size(3, var11);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.prepvpstargrade)) {
         Integer var12 = var1.prepvpstargrade;
         var2 += CodedOutputStream.computeInt32Size(4, var12);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.prewinpoint)) {
         Integer var13 = var1.prewinpoint;
         var2 += CodedOutputStream.computeInt32Size(5, var13);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.arcadereward)) {
         List var14 = var1.arcadereward;
         var2 += CodedConstant.computeListSize(6, var14, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(RES_ARCADE_PVP_REWARD_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var9 = var1.error;
         if (var9 != null) {
            var2.writeInt32(1, var9);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.pvpstargrade)) {
         Integer var10 = var1.pvpstargrade;
         if (var10 != null) {
            var2.writeInt32(2, var10);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.winpoint)) {
         Integer var11 = var1.winpoint;
         if (var11 != null) {
            var2.writeInt32(3, var11);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.prepvpstargrade)) {
         Integer var12 = var1.prepvpstargrade;
         if (var12 != null) {
            var2.writeInt32(4, var12);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.prewinpoint)) {
         Integer var13 = var1.prewinpoint;
         if (var13 != null) {
            var2.writeInt32(5, var13);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.arcadereward)) {
         List var14 = var1.arcadereward;
         if (var14 != null) {
            CodedConstant.writeToList(var2, 6, FieldType.OBJECT, var14, false);
         }
      }

   }

   public void writeTo(RES_ARCADE_PVP_REWARD_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_ARCADE_PVP_REWARD_INFO readFrom(CodedInputStream var1) throws IOException {
      RES_ARCADE_PVP_REWARD_INFO var2 = new RES_ARCADE_PVP_REWARD_INFO();
      var2.arcadereward = new ArrayList();

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
               var2.pvpstargrade = var1.readInt32();
            } else if (var5 == 24) {
               var2.winpoint = var1.readInt32();
            } else if (var5 == 32) {
               var2.prepvpstargrade = var1.readInt32();
            } else if (var5 == 40) {
               var2.prewinpoint = var1.readInt32();
            } else if (var5 == 50) {
               Codec var10 = ProtobufProxy.create(PT_ARCADE_PVP_INFO_CURRENCY.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.arcadereward == null) {
                  var2.arcadereward = new ArrayList();
               }

               var2.arcadereward.add((PT_ARCADE_PVP_INFO_CURRENCY)var10.readFrom(var1));
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_ARCADE_PVP_REWARD_INFO.class);
         return this.descriptor = var1;
      }
   }
}
