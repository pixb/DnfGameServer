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

public class PT_BOARD_GAME_INFO$$JProtoBufClass implements Codec<PT_BOARD_GAME_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_BOARD_GAME_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_BOARD_GAME_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_BOARD_GAME_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.tiles)) {
         List var8 = var1.tiles;
         var2 += CodedConstant.computeListSize(1, var8, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.users)) {
         List var9 = var1.users;
         var2 += CodedConstant.computeListSize(2, var9, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.sizex)) {
         Integer var10 = var1.sizex;
         var2 += CodedOutputStream.computeInt32Size(3, var10);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.sizey)) {
         Integer var11 = var1.sizey;
         var2 += CodedOutputStream.computeInt32Size(4, var11);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.playtime)) {
         Integer var12 = var1.playtime;
         var2 += CodedOutputStream.computeInt32Size(5, var12);
      }

      return var2;
   }

   public void doWriteTo(PT_BOARD_GAME_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.tiles)) {
         List var8 = var1.tiles;
         if (var8 != null) {
            CodedConstant.writeToList(var2, 1, FieldType.OBJECT, var8, false);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.users)) {
         List var9 = var1.users;
         if (var9 != null) {
            CodedConstant.writeToList(var2, 2, FieldType.OBJECT, var9, false);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.sizex)) {
         Integer var10 = var1.sizex;
         if (var10 != null) {
            var2.writeInt32(3, var10);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.sizey)) {
         Integer var11 = var1.sizey;
         if (var11 != null) {
            var2.writeInt32(4, var11);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.playtime)) {
         Integer var12 = var1.playtime;
         if (var12 != null) {
            var2.writeInt32(5, var12);
         }
      }

   }

   public void writeTo(PT_BOARD_GAME_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_BOARD_GAME_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_BOARD_GAME_INFO var2 = new PT_BOARD_GAME_INFO();
      var2.tiles = new ArrayList();
      var2.users = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 10) {
               Codec var10 = ProtobufProxy.create(PT_BOARD_TILE_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.tiles == null) {
                  var2.tiles = new ArrayList();
               }

               var2.tiles.add((PT_BOARD_TILE_INFO)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 18) {
               Codec var11 = ProtobufProxy.create(PT_BOARD_USER_CONTENTS_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var12 = var1.readRawVarint32();
               int var13 = var1.pushLimit(var12);
               if (var2.users == null) {
                  var2.users = new ArrayList();
               }

               var2.users.add((PT_BOARD_USER_CONTENTS_INFO)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var13);
            } else if (var5 == 24) {
               var2.sizex = var1.readInt32();
            } else if (var5 == 32) {
               var2.sizey = var1.readInt32();
            } else if (var5 == 40) {
               var2.playtime = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_BOARD_GAME_INFO.class);
         return this.descriptor = var1;
      }
   }
}
