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

public class RES_DREAM_MAZE_BASICINFO$$JProtoBufClass implements Codec<RES_DREAM_MAZE_BASICINFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_DREAM_MAZE_BASICINFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_DREAM_MAZE_BASICINFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_DREAM_MAZE_BASICINFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var10 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var10);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.state)) {
         Integer var11 = var1.state;
         var2 += CodedOutputStream.computeInt32Size(2, var11);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.opendungeonindex)) {
         Integer var12 = var1.opendungeonindex;
         var2 += CodedOutputStream.computeInt32Size(3, var12);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.dungeons)) {
         List var13 = var1.dungeons;
         var2 += CodedConstant.computeListSize(4, var13, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.rewardcount)) {
         Integer var14 = var1.rewardcount;
         var2 += CodedOutputStream.computeInt32Size(5, var14);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.clearrewardcount)) {
         Integer var15 = var1.clearrewardcount;
         var2 += CodedOutputStream.computeInt32Size(6, var15);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.receptibleclearreward)) {
         Boolean var16 = var1.receptibleclearreward;
         var2 += CodedOutputStream.computeBoolSize(7, var16);
      }

      return var2;
   }

   public void doWriteTo(RES_DREAM_MAZE_BASICINFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var10 = var1.error;
         if (var10 != null) {
            var2.writeInt32(1, var10);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.state)) {
         Integer var11 = var1.state;
         if (var11 != null) {
            var2.writeInt32(2, var11);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.opendungeonindex)) {
         Integer var12 = var1.opendungeonindex;
         if (var12 != null) {
            var2.writeInt32(3, var12);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.dungeons)) {
         List var13 = var1.dungeons;
         if (var13 != null) {
            CodedConstant.writeToList(var2, 4, FieldType.OBJECT, var13, false);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.rewardcount)) {
         Integer var14 = var1.rewardcount;
         if (var14 != null) {
            var2.writeInt32(5, var14);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.clearrewardcount)) {
         Integer var15 = var1.clearrewardcount;
         if (var15 != null) {
            var2.writeInt32(6, var15);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.receptibleclearreward)) {
         Boolean var16 = var1.receptibleclearreward;
         if (var16 != null) {
            var2.writeBool(7, var16);
         }
      }

   }

   public void writeTo(RES_DREAM_MAZE_BASICINFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_DREAM_MAZE_BASICINFO readFrom(CodedInputStream var1) throws IOException {
      RES_DREAM_MAZE_BASICINFO var2 = new RES_DREAM_MAZE_BASICINFO();
      var2.dungeons = new ArrayList();

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
               var2.state = var1.readInt32();
            } else if (var5 == 24) {
               var2.opendungeonindex = var1.readInt32();
            } else if (var5 == 34) {
               Codec var10 = ProtobufProxy.create(PT_DREAM_MAZE_DUNGEON.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.dungeons == null) {
                  var2.dungeons = new ArrayList();
               }

               var2.dungeons.add((PT_DREAM_MAZE_DUNGEON)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 40) {
               var2.rewardcount = var1.readInt32();
            } else if (var5 == 48) {
               var2.clearrewardcount = var1.readInt32();
            } else if (var5 == 56) {
               var2.receptibleclearreward = var1.readBool();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_DREAM_MAZE_BASICINFO.class);
         return this.descriptor = var1;
      }
   }
}
