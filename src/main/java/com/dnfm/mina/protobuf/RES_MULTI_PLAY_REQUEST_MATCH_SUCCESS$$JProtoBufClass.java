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

public class RES_MULTI_PLAY_REQUEST_MATCH_SUCCESS$$JProtoBufClass implements Codec<RES_MULTI_PLAY_REQUEST_MATCH_SUCCESS>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_MULTI_PLAY_REQUEST_MATCH_SUCCESS var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_MULTI_PLAY_REQUEST_MATCH_SUCCESS decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_MULTI_PLAY_REQUEST_MATCH_SUCCESS var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var19 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var19);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var20 = var1.partyguid;
         var2 += CodedOutputStream.computeUInt64Size(2, var20);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var21 = var1.dungeonguid;
         var2 += CodedOutputStream.computeUInt64Size(3, var21);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.battleworld)) {
         Integer var22 = var1.battleworld;
         var2 += CodedOutputStream.computeInt32Size(4, var22);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.bchannel)) {
         Integer var23 = var1.bchannel;
         var2 += CodedOutputStream.computeInt32Size(5, var23);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.bip)) {
         String var24 = var1.bip;
         var2 += CodedOutputStream.computeStringSize(6, var24);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.bport)) {
         Integer var25 = var1.bport;
         var2 += CodedOutputStream.computeInt32Size(7, var25);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.matchtype)) {
         Integer var26 = var1.matchtype;
         var2 += CodedOutputStream.computeInt32Size(8, var26);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var27 = var1.world;
         var2 += CodedOutputStream.computeInt32Size(9, var27);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.channel)) {
         Integer var28 = var1.channel;
         var2 += CodedOutputStream.computeInt32Size(10, var28);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var29 = var1.dungeonindex;
         var2 += CodedOutputStream.computeInt32Size(11, var29);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.users)) {
         List var30 = var1.users;
         var2 += CodedConstant.computeListSize(12, var30, FieldType.UINT64, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.detail)) {
         List var31 = var1.detail;
         var2 += CodedConstant.computeListSize(13, var31, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.comeback)) {
         PT_COMEBACK_MATCHING_USERS var32 = var1.comeback;
         var2 += CodedConstant.computeSize(14, var32, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.tiles)) {
         List var33 = var1.tiles;
         var2 += CodedConstant.computeListSize(15, var33, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.pvpdiceinfo)) {
         List var34 = var1.pvpdiceinfo;
         var2 += CodedConstant.computeListSize(16, var34, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(RES_MULTI_PLAY_REQUEST_MATCH_SUCCESS var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var19 = var1.error;
         if (var19 != null) {
            var2.writeInt32(1, var19);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var20 = var1.partyguid;
         if (var20 != null) {
            var2.writeUInt64(2, var20);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var21 = var1.dungeonguid;
         if (var21 != null) {
            var2.writeUInt64(3, var21);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.battleworld)) {
         Integer var22 = var1.battleworld;
         if (var22 != null) {
            var2.writeInt32(4, var22);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.bchannel)) {
         Integer var23 = var1.bchannel;
         if (var23 != null) {
            var2.writeInt32(5, var23);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.bip)) {
         String var24 = var1.bip;
         if (var24 != null) {
            var2.writeString(6, var24);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.bport)) {
         Integer var25 = var1.bport;
         if (var25 != null) {
            var2.writeInt32(7, var25);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.matchtype)) {
         Integer var26 = var1.matchtype;
         if (var26 != null) {
            var2.writeInt32(8, var26);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var27 = var1.world;
         if (var27 != null) {
            var2.writeInt32(9, var27);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.channel)) {
         Integer var28 = var1.channel;
         if (var28 != null) {
            var2.writeInt32(10, var28);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var29 = var1.dungeonindex;
         if (var29 != null) {
            var2.writeInt32(11, var29);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.users)) {
         List var30 = var1.users;
         if (var30 != null) {
            CodedConstant.writeToList(var2, 12, FieldType.UINT64, var30, true);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.detail)) {
         List var31 = var1.detail;
         if (var31 != null) {
            CodedConstant.writeToList(var2, 13, FieldType.OBJECT, var31, false);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.comeback)) {
         PT_COMEBACK_MATCHING_USERS var32 = var1.comeback;
         if (var32 != null) {
            CodedConstant.writeObject(var2, 14, FieldType.OBJECT, var32, false);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.tiles)) {
         List var33 = var1.tiles;
         if (var33 != null) {
            CodedConstant.writeToList(var2, 15, FieldType.OBJECT, var33, false);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.pvpdiceinfo)) {
         List var34 = var1.pvpdiceinfo;
         if (var34 != null) {
            CodedConstant.writeToList(var2, 16, FieldType.OBJECT, var34, false);
         }
      }

   }

   public void writeTo(RES_MULTI_PLAY_REQUEST_MATCH_SUCCESS var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_MULTI_PLAY_REQUEST_MATCH_SUCCESS readFrom(CodedInputStream var1) throws IOException {
      RES_MULTI_PLAY_REQUEST_MATCH_SUCCESS var2 = new RES_MULTI_PLAY_REQUEST_MATCH_SUCCESS();
      var2.users = new ArrayList();
      var2.detail = new ArrayList();
      var2.tiles = new ArrayList();
      var2.pvpdiceinfo = new ArrayList();

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
               var2.partyguid = var1.readUInt64();
            } else if (var5 == 24) {
               var2.dungeonguid = var1.readUInt64();
            } else if (var5 == 32) {
               var2.battleworld = var1.readInt32();
            } else if (var5 == 40) {
               var2.bchannel = var1.readInt32();
            } else if (var5 == 50) {
               var2.bip = var1.readString();
            } else if (var5 == 56) {
               var2.bport = var1.readInt32();
            } else if (var5 == 64) {
               var2.matchtype = var1.readInt32();
            } else if (var5 == 72) {
               var2.world = var1.readInt32();
            } else if (var5 == 80) {
               var2.channel = var1.readInt32();
            } else if (var5 == 88) {
               var2.dungeonindex = var1.readInt32();
            } else if (var5 == 96) {
               if (var2.users == null) {
                  var2.users = new ArrayList();
               }

               var2.users.add(var1.readUInt64());
            } else if (var5 == 98) {
               int var18 = var1.readRawVarint32();
               int var23 = var1.pushLimit(var18);
               if (var2.users == null) {
                  var2.users = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.users.add(var1.readUInt64());
               }

               var1.popLimit(var23);
            } else if (var5 != 106) {
               if (var5 == 114) {
                  Codec var11 = ProtobufProxy.create(PT_COMEBACK_MATCHING_USERS.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
                  int var15 = var1.readRawVarint32();
                  int var20 = var1.pushLimit(var15);
                  var2.comeback = (PT_COMEBACK_MATCHING_USERS)var11.readFrom(var1);
                  var1.checkLastTagWas(0);
                  var1.popLimit(var20);
               } else if (var5 == 122) {
                  Codec var12 = ProtobufProxy.create(PT_TILE_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
                  int var16 = var1.readRawVarint32();
                  int var21 = var1.pushLimit(var16);
                  if (var2.tiles == null) {
                     var2.tiles = new ArrayList();
                  }

                  var2.tiles.add((PT_TILE_INFO)var12.readFrom(var1));
                  var1.checkLastTagWas(0);
                  var1.popLimit(var21);
               } else if (var5 == 130) {
                  Codec var13 = ProtobufProxy.create(PT_PVP_DICE_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
                  int var17 = var1.readRawVarint32();
                  int var22 = var1.pushLimit(var17);
                  if (var2.pvpdiceinfo == null) {
                     var2.pvpdiceinfo = new ArrayList();
                  }

                  var2.pvpdiceinfo.add((PT_PVP_DICE_INFO)var13.readFrom(var1));
                  var1.checkLastTagWas(0);
                  var1.popLimit(var22);
               } else {
                  var1.skipField(var5);
               }
            } else if (var5 != 98) {
               Codec var10 = ProtobufProxy.create(PT_USER_MINIMUM_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var14 = var1.readRawVarint32();
               int var19 = var1.pushLimit(var14);
               if (var2.detail == null) {
                  var2.detail = new ArrayList();
               }

               var2.detail.add((PT_USER_MINIMUM_INFO)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var19);
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.users == null) {
                  var2.users = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.users.add(var1.readUInt64());
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_MULTI_PLAY_REQUEST_MATCH_SUCCESS.class);
         return this.descriptor = var1;
      }
   }
}
