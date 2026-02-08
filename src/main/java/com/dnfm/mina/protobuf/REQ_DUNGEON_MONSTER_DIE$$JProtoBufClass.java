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

public class REQ_DUNGEON_MONSTER_DIE$$JProtoBufClass implements Codec<REQ_DUNGEON_MONSTER_DIE>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_DUNGEON_MONSTER_DIE var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_DUNGEON_MONSTER_DIE decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_DUNGEON_MONSTER_DIE var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.list)) {
         List var9 = var1.list;
         var2 += CodedConstant.computeListSize(1, var9, FieldType.INT32, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var10 = var1.dungeonguid;
         var2 += CodedOutputStream.computeUInt64Size(2, var10);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.stageguid)) {
         Integer var11 = var1.stageguid;
         var2 += CodedOutputStream.computeInt32Size(3, var11);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.playercount)) {
         Integer var12 = var1.playercount;
         var2 += CodedOutputStream.computeInt32Size(4, var12);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.quest)) {
         List var13 = var1.quest;
         var2 += CodedConstant.computeListSize(5, var13, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.lastshot)) {
         List var14 = var1.lastshot;
         var2 += CodedConstant.computeListSize(6, var14, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(REQ_DUNGEON_MONSTER_DIE var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.list)) {
         List var9 = var1.list;
         if (var9 != null) {
            CodedConstant.writeToList(var2, 1, FieldType.INT32, var9, true);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var10 = var1.dungeonguid;
         if (var10 != null) {
            var2.writeUInt64(2, var10);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.stageguid)) {
         Integer var11 = var1.stageguid;
         if (var11 != null) {
            var2.writeInt32(3, var11);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.playercount)) {
         Integer var12 = var1.playercount;
         if (var12 != null) {
            var2.writeInt32(4, var12);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.quest)) {
         List var13 = var1.quest;
         if (var13 != null) {
            CodedConstant.writeToList(var2, 5, FieldType.OBJECT, var13, false);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.lastshot)) {
         List var14 = var1.lastshot;
         if (var14 != null) {
            CodedConstant.writeToList(var2, 6, FieldType.OBJECT, var14, false);
         }
      }

   }

   public void writeTo(REQ_DUNGEON_MONSTER_DIE var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_DUNGEON_MONSTER_DIE readFrom(CodedInputStream var1) throws IOException {
      REQ_DUNGEON_MONSTER_DIE var2 = new REQ_DUNGEON_MONSTER_DIE();
      var2.list = new ArrayList();
      var2.quest = new ArrayList();
      var2.lastshot = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               if (var2.list == null) {
                  var2.list = new ArrayList();
               }

               var2.list.add(var1.readInt32());
            } else if (var5 == 10) {
               int var14 = var1.readRawVarint32();
               int var17 = var1.pushLimit(var14);
               if (var2.list == null) {
                  var2.list = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.list.add(var1.readInt32());
               }

               var1.popLimit(var17);
            } else if (var5 == 16) {
               var2.dungeonguid = var1.readUInt64();
            } else if (var5 == 24) {
               var2.stageguid = var1.readInt32();
            } else if (var5 == 32) {
               var2.playercount = var1.readInt32();
            } else if (var5 != 42) {
               if (var5 == 50) {
                  Codec var11 = ProtobufProxy.create(PT_MONSTERKILL_LAST_SHOT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
                  int var13 = var1.readRawVarint32();
                  int var16 = var1.pushLimit(var13);
                  if (var2.lastshot == null) {
                     var2.lastshot = new ArrayList();
                  }

                  var2.lastshot.add((PT_MONSTERKILL_LAST_SHOT)var11.readFrom(var1));
                  var1.checkLastTagWas(0);
                  var1.popLimit(var16);
               } else {
                  var1.skipField(var5);
               }
            } else if (var5 != 10) {
               Codec var10 = ProtobufProxy.create(PT_QUEST.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var12 = var1.readRawVarint32();
               int var15 = var1.pushLimit(var12);
               if (var2.quest == null) {
                  var2.quest = new ArrayList();
               }

               var2.quest.add((PT_QUEST)var10.readFrom(var1));
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_DUNGEON_MONSTER_DIE.class);
         return this.descriptor = var1;
      }
   }
}
