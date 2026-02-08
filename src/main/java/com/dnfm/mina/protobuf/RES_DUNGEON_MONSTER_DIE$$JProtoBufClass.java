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

public class RES_DUNGEON_MONSTER_DIE$$JProtoBufClass implements Codec<RES_DUNGEON_MONSTER_DIE>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_DUNGEON_MONSTER_DIE var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_DUNGEON_MONSTER_DIE decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_DUNGEON_MONSTER_DIE var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var13 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var13);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.totalexp)) {
         Integer var14 = var1.totalexp;
         var2 += CodedOutputStream.computeInt32Size(2, var14);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var15 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(3, var15);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.earnexp)) {
         Integer var16 = var1.earnexp;
         var2 += CodedOutputStream.computeInt32Size(4, var16);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.list)) {
         List var17 = var1.list;
         var2 += CodedConstant.computeListSize(5, var17, FieldType.INT32, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.sender)) {
         Long var18 = var1.sender;
         var2 += CodedOutputStream.computeUInt64Size(6, var18);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.items)) {
         PT_ITEMS var19 = var1.items;
         var2 += CodedConstant.computeSize(7, var19, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.earnbattleleaguecontribution)) {
         List var20 = var1.earnbattleleaguecontribution;
         var2 += CodedConstant.computeListSize(8, var20, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.publicbattleleaguecontribution)) {
         Integer var21 = var1.publicbattleleaguecontribution;
         var2 += CodedOutputStream.computeInt32Size(9, var21);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.epicgauge)) {
         Integer var22 = var1.epicgauge;
         var2 += CodedOutputStream.computeInt32Size(10, var22);
      }

      return var2;
   }

   public void doWriteTo(RES_DUNGEON_MONSTER_DIE var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var13 = var1.error;
         if (var13 != null) {
            var2.writeInt32(1, var13);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.totalexp)) {
         Integer var14 = var1.totalexp;
         if (var14 != null) {
            var2.writeInt32(2, var14);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var15 = var1.level;
         if (var15 != null) {
            var2.writeInt32(3, var15);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.earnexp)) {
         Integer var16 = var1.earnexp;
         if (var16 != null) {
            var2.writeInt32(4, var16);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.list)) {
         List var17 = var1.list;
         if (var17 != null) {
            CodedConstant.writeToList(var2, 5, FieldType.INT32, var17, true);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.sender)) {
         Long var18 = var1.sender;
         if (var18 != null) {
            var2.writeUInt64(6, var18);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.items)) {
         PT_ITEMS var19 = var1.items;
         if (var19 != null) {
            CodedConstant.writeObject(var2, 7, FieldType.OBJECT, var19, false);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.earnbattleleaguecontribution)) {
         List var20 = var1.earnbattleleaguecontribution;
         if (var20 != null) {
            CodedConstant.writeToList(var2, 8, FieldType.OBJECT, var20, false);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.publicbattleleaguecontribution)) {
         Integer var21 = var1.publicbattleleaguecontribution;
         if (var21 != null) {
            var2.writeInt32(9, var21);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.epicgauge)) {
         Integer var22 = var1.epicgauge;
         if (var22 != null) {
            var2.writeInt32(10, var22);
         }
      }

   }

   public void writeTo(RES_DUNGEON_MONSTER_DIE var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_DUNGEON_MONSTER_DIE readFrom(CodedInputStream var1) throws IOException {
      RES_DUNGEON_MONSTER_DIE var2 = new RES_DUNGEON_MONSTER_DIE();
      var2.list = new ArrayList();
      var2.earnbattleleaguecontribution = new ArrayList();

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
               var2.totalexp = var1.readInt32();
            } else if (var5 == 24) {
               var2.level = var1.readInt32();
            } else if (var5 == 32) {
               var2.earnexp = var1.readInt32();
            } else if (var5 == 40) {
               if (var2.list == null) {
                  var2.list = new ArrayList();
               }

               var2.list.add(var1.readInt32());
            } else if (var5 == 42) {
               int var14 = var1.readRawVarint32();
               int var17 = var1.pushLimit(var14);
               if (var2.list == null) {
                  var2.list = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.list.add(var1.readInt32());
               }

               var1.popLimit(var17);
            } else if (var5 == 48) {
               var2.sender = var1.readUInt64();
            } else if (var5 != 58) {
               if (var5 == 66) {
                  Codec var11 = ProtobufProxy.create(PT_BATTLELEAGUE_CONTRIBUTE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
                  int var13 = var1.readRawVarint32();
                  int var16 = var1.pushLimit(var13);
                  if (var2.earnbattleleaguecontribution == null) {
                     var2.earnbattleleaguecontribution = new ArrayList();
                  }

                  var2.earnbattleleaguecontribution.add((PT_BATTLELEAGUE_CONTRIBUTE)var11.readFrom(var1));
                  var1.checkLastTagWas(0);
                  var1.popLimit(var16);
               } else if (var5 == 72) {
                  var2.publicbattleleaguecontribution = var1.readInt32();
               } else if (var5 == 80) {
                  var2.epicgauge = var1.readInt32();
               } else {
                  var1.skipField(var5);
               }
            } else if (var5 != 42) {
               Codec var10 = ProtobufProxy.create(PT_ITEMS.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var12 = var1.readRawVarint32();
               int var15 = var1.pushLimit(var12);
               var2.items = (PT_ITEMS)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var15);
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.list == null) {
                  var2.list = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.list.add(var1.readInt32());
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_DUNGEON_MONSTER_DIE.class);
         return this.descriptor = var1;
      }
   }
}
