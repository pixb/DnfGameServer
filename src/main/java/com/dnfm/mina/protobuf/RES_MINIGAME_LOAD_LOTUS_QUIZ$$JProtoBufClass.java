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

public class RES_MINIGAME_LOAD_LOTUS_QUIZ$$JProtoBufClass implements Codec<RES_MINIGAME_LOAD_LOTUS_QUIZ>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_MINIGAME_LOAD_LOTUS_QUIZ var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_MINIGAME_LOAD_LOTUS_QUIZ decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_MINIGAME_LOAD_LOTUS_QUIZ var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var9 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var9);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.day)) {
         Integer var10 = var1.day;
         var2 += CodedOutputStream.computeInt32Size(2, var10);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.state)) {
         Integer var11 = var1.state;
         var2 += CodedOutputStream.computeInt32Size(3, var11);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.attendCount)) {
         Integer var12 = var1.attendCount;
         var2 += CodedOutputStream.computeInt32Size(4, var12);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.attendReward)) {
         Integer var13 = var1.attendReward;
         var2 += CodedOutputStream.computeInt32Size(5, var13);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.attendIndexes)) {
         List var14 = var1.attendIndexes;
         var2 += CodedConstant.computeListSize(6, var14, FieldType.INT32, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      return var2;
   }

   public void doWriteTo(RES_MINIGAME_LOAD_LOTUS_QUIZ var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var9 = var1.error;
         if (var9 != null) {
            var2.writeInt32(1, var9);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.day)) {
         Integer var10 = var1.day;
         if (var10 != null) {
            var2.writeInt32(2, var10);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.state)) {
         Integer var11 = var1.state;
         if (var11 != null) {
            var2.writeInt32(3, var11);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.attendCount)) {
         Integer var12 = var1.attendCount;
         if (var12 != null) {
            var2.writeInt32(4, var12);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.attendReward)) {
         Integer var13 = var1.attendReward;
         if (var13 != null) {
            var2.writeInt32(5, var13);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.attendIndexes)) {
         List var14 = var1.attendIndexes;
         if (var14 != null) {
            CodedConstant.writeToList(var2, 6, FieldType.INT32, var14, true);
         }
      }

   }

   public void writeTo(RES_MINIGAME_LOAD_LOTUS_QUIZ var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_MINIGAME_LOAD_LOTUS_QUIZ readFrom(CodedInputStream var1) throws IOException {
      RES_MINIGAME_LOAD_LOTUS_QUIZ var2 = new RES_MINIGAME_LOAD_LOTUS_QUIZ();
      var2.attendIndexes = new ArrayList();

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
               var2.day = var1.readInt32();
            } else if (var5 == 24) {
               var2.state = var1.readInt32();
            } else if (var5 == 32) {
               var2.attendCount = var1.readInt32();
            } else if (var5 == 40) {
               var2.attendReward = var1.readInt32();
            } else if (var5 == 48) {
               if (var2.attendIndexes == null) {
                  var2.attendIndexes = new ArrayList();
               }

               var2.attendIndexes.add(var1.readInt32());
            } else if (var5 != 50) {
               var1.skipField(var5);
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.attendIndexes == null) {
                  var2.attendIndexes = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.attendIndexes.add(var1.readInt32());
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_MINIGAME_LOAD_LOTUS_QUIZ.class);
         return this.descriptor = var1;
      }
   }
}
