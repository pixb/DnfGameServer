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

public class RES_MINIGAME_BREAKING_LOAD$$JProtoBufClass implements Codec<RES_MINIGAME_BREAKING_LOAD>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_MINIGAME_BREAKING_LOAD var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_MINIGAME_BREAKING_LOAD decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_MINIGAME_BREAKING_LOAD var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var11 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var11);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.ticketcount)) {
         Integer var12 = var1.ticketcount;
         var2 += CodedOutputStream.computeInt32Size(2, var12);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.playcount)) {
         Integer var13 = var1.playcount;
         var2 += CodedOutputStream.computeInt32Size(3, var13);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.rewardstate)) {
         Integer var14 = var1.rewardstate;
         var2 += CodedOutputStream.computeInt32Size(4, var14);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.myrank)) {
         Integer var15 = var1.myrank;
         var2 += CodedOutputStream.computeInt32Size(5, var15);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.myscore)) {
         Long var16 = var1.myscore;
         var2 += CodedOutputStream.computeUInt64Size(6, var16);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.mycharguid)) {
         Long var17 = var1.mycharguid;
         var2 += CodedOutputStream.computeUInt64Size(7, var17);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.rankinginfos)) {
         List var18 = var1.rankinginfos;
         var2 += CodedConstant.computeListSize(8, var18, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(RES_MINIGAME_BREAKING_LOAD var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var11 = var1.error;
         if (var11 != null) {
            var2.writeInt32(1, var11);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.ticketcount)) {
         Integer var12 = var1.ticketcount;
         if (var12 != null) {
            var2.writeInt32(2, var12);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.playcount)) {
         Integer var13 = var1.playcount;
         if (var13 != null) {
            var2.writeInt32(3, var13);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.rewardstate)) {
         Integer var14 = var1.rewardstate;
         if (var14 != null) {
            var2.writeInt32(4, var14);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.myrank)) {
         Integer var15 = var1.myrank;
         if (var15 != null) {
            var2.writeInt32(5, var15);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.myscore)) {
         Long var16 = var1.myscore;
         if (var16 != null) {
            var2.writeUInt64(6, var16);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.mycharguid)) {
         Long var17 = var1.mycharguid;
         if (var17 != null) {
            var2.writeUInt64(7, var17);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.rankinginfos)) {
         List var18 = var1.rankinginfos;
         if (var18 != null) {
            CodedConstant.writeToList(var2, 8, FieldType.OBJECT, var18, false);
         }
      }

   }

   public void writeTo(RES_MINIGAME_BREAKING_LOAD var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_MINIGAME_BREAKING_LOAD readFrom(CodedInputStream var1) throws IOException {
      RES_MINIGAME_BREAKING_LOAD var2 = new RES_MINIGAME_BREAKING_LOAD();
      var2.rankinginfos = new ArrayList();

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
               var2.ticketcount = var1.readInt32();
            } else if (var5 == 24) {
               var2.playcount = var1.readInt32();
            } else if (var5 == 32) {
               var2.rewardstate = var1.readInt32();
            } else if (var5 == 40) {
               var2.myrank = var1.readInt32();
            } else if (var5 == 48) {
               var2.myscore = var1.readUInt64();
            } else if (var5 == 56) {
               var2.mycharguid = var1.readUInt64();
            } else if (var5 == 66) {
               Codec var10 = ProtobufProxy.create(PT_RANKING_MINIGAME_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.rankinginfos == null) {
                  var2.rankinginfos = new ArrayList();
               }

               var2.rankinginfos.add((PT_RANKING_MINIGAME_INFO)var10.readFrom(var1));
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_MINIGAME_BREAKING_LOAD.class);
         return this.descriptor = var1;
      }
   }
}
