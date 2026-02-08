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

public class NOTIFY_BOARD_GAME_MESSAGE$$JProtoBufClass implements Codec<NOTIFY_BOARD_GAME_MESSAGE>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(NOTIFY_BOARD_GAME_MESSAGE var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public NOTIFY_BOARD_GAME_MESSAGE decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(NOTIFY_BOARD_GAME_MESSAGE var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var18 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var18);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var19 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(2, var19);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.matchingguid)) {
         Long var20 = var1.matchingguid;
         var2 += CodedOutputStream.computeUInt64Size(3, var20);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.randomseed)) {
         Integer var21 = var1.randomseed;
         var2 += CodedOutputStream.computeInt32Size(4, var21);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var22 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(5, var22);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.result)) {
         Integer var23 = var1.result;
         var2 += CodedOutputStream.computeInt32Size(6, var23);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var24 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(7, var24);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var25 = var1.dungeonindex;
         var2 += CodedOutputStream.computeInt32Size(8, var25);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.intlist)) {
         List var26 = var1.intlist;
         var2 += CodedConstant.computeListSize(9, var26, FieldType.INT32, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.strlist)) {
         List var27 = var1.strlist;
         var2 += CodedConstant.computeListSize(10, var27, FieldType.STRING, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.list)) {
         List var28 = var1.list;
         var2 += CodedConstant.computeListSize(11, var28, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.dice)) {
         Integer var29 = var1.dice;
         var2 += CodedOutputStream.computeInt32Size(12, var29);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.ticket)) {
         Integer var30 = var1.ticket;
         var2 += CodedOutputStream.computeInt32Size(13, var30);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.gmspecial)) {
         Boolean var31 = var1.gmspecial;
         var2 += CodedOutputStream.computeBoolSize(14, var31);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.losers)) {
         List var32 = var1.losers;
         var2 += CodedConstant.computeListSize(15, var32, FieldType.UINT64, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      return var2;
   }

   public void doWriteTo(NOTIFY_BOARD_GAME_MESSAGE var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var18 = var1.error;
         if (var18 != null) {
            var2.writeInt32(1, var18);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var19 = var1.type;
         if (var19 != null) {
            var2.writeInt32(2, var19);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.matchingguid)) {
         Long var20 = var1.matchingguid;
         if (var20 != null) {
            var2.writeUInt64(3, var20);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.randomseed)) {
         Integer var21 = var1.randomseed;
         if (var21 != null) {
            var2.writeInt32(4, var21);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var22 = var1.charguid;
         if (var22 != null) {
            var2.writeUInt64(5, var22);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.result)) {
         Integer var23 = var1.result;
         if (var23 != null) {
            var2.writeInt32(6, var23);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var24 = var1.index;
         if (var24 != null) {
            var2.writeInt32(7, var24);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var25 = var1.dungeonindex;
         if (var25 != null) {
            var2.writeInt32(8, var25);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.intlist)) {
         List var26 = var1.intlist;
         if (var26 != null) {
            CodedConstant.writeToList(var2, 9, FieldType.INT32, var26, true);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.strlist)) {
         List var27 = var1.strlist;
         if (var27 != null) {
            CodedConstant.writeToList(var2, 10, FieldType.STRING, var27, false);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.list)) {
         List var28 = var1.list;
         if (var28 != null) {
            CodedConstant.writeToList(var2, 11, FieldType.OBJECT, var28, false);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.dice)) {
         Integer var29 = var1.dice;
         if (var29 != null) {
            var2.writeInt32(12, var29);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.ticket)) {
         Integer var30 = var1.ticket;
         if (var30 != null) {
            var2.writeInt32(13, var30);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.gmspecial)) {
         Boolean var31 = var1.gmspecial;
         if (var31 != null) {
            var2.writeBool(14, var31);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.losers)) {
         List var32 = var1.losers;
         if (var32 != null) {
            CodedConstant.writeToList(var2, 15, FieldType.UINT64, var32, true);
         }
      }

   }

   public void writeTo(NOTIFY_BOARD_GAME_MESSAGE var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public NOTIFY_BOARD_GAME_MESSAGE readFrom(CodedInputStream var1) throws IOException {
      NOTIFY_BOARD_GAME_MESSAGE var2 = new NOTIFY_BOARD_GAME_MESSAGE();
      var2.intlist = new ArrayList();
      var2.strlist = new ArrayList();
      var2.list = new ArrayList();
      var2.losers = new ArrayList();

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
               var2.type = var1.readInt32();
            } else if (var5 == 24) {
               var2.matchingguid = var1.readUInt64();
            } else if (var5 == 32) {
               var2.randomseed = var1.readInt32();
            } else if (var5 == 40) {
               var2.charguid = var1.readUInt64();
            } else if (var5 == 48) {
               var2.result = var1.readInt32();
            } else if (var5 == 56) {
               var2.index = var1.readInt32();
            } else if (var5 == 64) {
               var2.dungeonindex = var1.readInt32();
            } else if (var5 == 72) {
               if (var2.intlist == null) {
                  var2.intlist = new ArrayList();
               }

               var2.intlist.add(var1.readInt32());
            } else if (var5 == 74) {
               int var13 = var1.readRawVarint32();
               int var16 = var1.pushLimit(var13);
               if (var2.intlist == null) {
                  var2.intlist = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.intlist.add(var1.readInt32());
               }

               var1.popLimit(var16);
            } else if (var5 == 82) {
               if (var2.strlist == null) {
                  var2.strlist = new ArrayList();
               }

               var2.strlist.add(var1.readString());
            } else if (var5 == 90) {
               if (var5 != 74) {
                  Codec var10 = ProtobufProxy.create(PT_REWARD_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
                  int var12 = var1.readRawVarint32();
                  int var15 = var1.pushLimit(var12);
                  if (var2.list == null) {
                     var2.list = new ArrayList();
                  }

                  var2.list.add((PT_REWARD_INFO)var10.readFrom(var1));
                  var1.checkLastTagWas(0);
                  var1.popLimit(var15);
               } else {
                  int var11 = var1.readRawVarint32();
                  int var14 = var1.pushLimit(var11);
                  if (var2.intlist == null) {
                     var2.intlist = new ArrayList();
                  }

                  while(var1.getBytesUntilLimit() > 0) {
                     var2.intlist.add(var1.readInt32());
                  }

                  var1.popLimit(var14);
               }
            } else if (var5 == 96) {
               var2.dice = var1.readInt32();
            } else if (var5 == 104) {
               var2.ticket = var1.readInt32();
            } else if (var5 == 112) {
               var2.gmspecial = var1.readBool();
            } else if (var5 == 120) {
               if (var2.losers == null) {
                  var2.losers = new ArrayList();
               }

               var2.losers.add(var1.readUInt64());
            } else if (var5 != 122) {
               var1.skipField(var5);
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.losers == null) {
                  var2.losers = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.losers.add(var1.readUInt64());
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(NOTIFY_BOARD_GAME_MESSAGE.class);
         return this.descriptor = var1;
      }
   }
}
