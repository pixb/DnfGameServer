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

public class REQ_BOARD_GAME_MESSAGE$$JProtoBufClass implements Codec<REQ_BOARD_GAME_MESSAGE>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_BOARD_GAME_MESSAGE var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_BOARD_GAME_MESSAGE decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_BOARD_GAME_MESSAGE var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var15 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(1, var15);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.matchingguid)) {
         Long var16 = var1.matchingguid;
         var2 += CodedOutputStream.computeUInt64Size(2, var16);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.itemindex)) {
         Integer var17 = var1.itemindex;
         var2 += CodedOutputStream.computeInt32Size(3, var17);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var18 = var1.count;
         var2 += CodedOutputStream.computeInt32Size(4, var18);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var19 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(5, var19);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var20 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(6, var20);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var21 = var1.dungeonindex;
         var2 += CodedOutputStream.computeInt32Size(7, var21);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.intlist)) {
         List var22 = var1.intlist;
         var2 += CodedConstant.computeListSize(8, var22, FieldType.INT32, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.strlist)) {
         List var23 = var1.strlist;
         var2 += CodedConstant.computeListSize(9, var23, FieldType.STRING, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.bufflist)) {
         List var24 = var1.bufflist;
         var2 += CodedConstant.computeListSize(10, var24, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.dice)) {
         Integer var25 = var1.dice;
         var2 += CodedOutputStream.computeInt32Size(11, var25);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.gmspecial)) {
         Boolean var26 = var1.gmspecial;
         var2 += CodedOutputStream.computeBoolSize(12, var26);
      }

      return var2;
   }

   public void doWriteTo(REQ_BOARD_GAME_MESSAGE var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var15 = var1.type;
         if (var15 != null) {
            var2.writeInt32(1, var15);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.matchingguid)) {
         Long var16 = var1.matchingguid;
         if (var16 != null) {
            var2.writeUInt64(2, var16);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.itemindex)) {
         Integer var17 = var1.itemindex;
         if (var17 != null) {
            var2.writeInt32(3, var17);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var18 = var1.count;
         if (var18 != null) {
            var2.writeInt32(4, var18);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var19 = var1.charguid;
         if (var19 != null) {
            var2.writeUInt64(5, var19);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var20 = var1.index;
         if (var20 != null) {
            var2.writeInt32(6, var20);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var21 = var1.dungeonindex;
         if (var21 != null) {
            var2.writeInt32(7, var21);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.intlist)) {
         List var22 = var1.intlist;
         if (var22 != null) {
            CodedConstant.writeToList(var2, 8, FieldType.INT32, var22, true);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.strlist)) {
         List var23 = var1.strlist;
         if (var23 != null) {
            CodedConstant.writeToList(var2, 9, FieldType.STRING, var23, false);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.bufflist)) {
         List var24 = var1.bufflist;
         if (var24 != null) {
            CodedConstant.writeToList(var2, 10, FieldType.OBJECT, var24, false);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.dice)) {
         Integer var25 = var1.dice;
         if (var25 != null) {
            var2.writeInt32(11, var25);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.gmspecial)) {
         Boolean var26 = var1.gmspecial;
         if (var26 != null) {
            var2.writeBool(12, var26);
         }
      }

   }

   public void writeTo(REQ_BOARD_GAME_MESSAGE var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_BOARD_GAME_MESSAGE readFrom(CodedInputStream var1) throws IOException {
      REQ_BOARD_GAME_MESSAGE var2 = new REQ_BOARD_GAME_MESSAGE();
      var2.intlist = new ArrayList();
      var2.strlist = new ArrayList();
      var2.bufflist = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.type = var1.readInt32();
            } else if (var5 == 16) {
               var2.matchingguid = var1.readUInt64();
            } else if (var5 == 24) {
               var2.itemindex = var1.readInt32();
            } else if (var5 == 32) {
               var2.count = var1.readInt32();
            } else if (var5 == 40) {
               var2.charguid = var1.readUInt64();
            } else if (var5 == 48) {
               var2.index = var1.readInt32();
            } else if (var5 == 56) {
               var2.dungeonindex = var1.readInt32();
            } else if (var5 == 64) {
               if (var2.intlist == null) {
                  var2.intlist = new ArrayList();
               }

               var2.intlist.add(var1.readInt32());
            } else if (var5 == 66) {
               int var12 = var1.readRawVarint32();
               int var14 = var1.pushLimit(var12);
               if (var2.intlist == null) {
                  var2.intlist = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.intlist.add(var1.readInt32());
               }

               var1.popLimit(var14);
            } else if (var5 == 74) {
               if (var2.strlist == null) {
                  var2.strlist = new ArrayList();
               }

               var2.strlist.add(var1.readString());
            } else if (var5 != 82) {
               if (var5 == 88) {
                  var2.dice = var1.readInt32();
               } else if (var5 == 96) {
                  var2.gmspecial = var1.readBool();
               } else {
                  var1.skipField(var5);
               }
            } else if (var5 != 66) {
               Codec var10 = ProtobufProxy.create(PT_BUFF.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var11 = var1.readRawVarint32();
               int var13 = var1.pushLimit(var11);
               if (var2.bufflist == null) {
                  var2.bufflist = new ArrayList();
               }

               var2.bufflist.add((PT_BUFF)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var13);
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.intlist == null) {
                  var2.intlist = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.intlist.add(var1.readInt32());
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_BOARD_GAME_MESSAGE.class);
         return this.descriptor = var1;
      }
   }
}
