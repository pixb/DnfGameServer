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

public class PT_DUNGEON_START_INFO$$JProtoBufClass implements Codec<PT_DUNGEON_START_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_DUNGEON_START_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_DUNGEON_START_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_DUNGEON_START_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.matchingguid)) {
         Long var15 = var1.matchingguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var15);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var16 = var1.dungeonguid;
         var2 += CodedOutputStream.computeUInt64Size(2, var16);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.battleworld)) {
         Integer var17 = var1.battleworld;
         var2 += CodedOutputStream.computeInt32Size(3, var17);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.bchannel)) {
         Integer var18 = var1.bchannel;
         var2 += CodedOutputStream.computeInt32Size(4, var18);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.bip)) {
         String var19 = var1.bip;
         var2 += CodedOutputStream.computeStringSize(5, var19);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.bport)) {
         Integer var20 = var1.bport;
         var2 += CodedOutputStream.computeInt32Size(6, var20);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.matchtype)) {
         Integer var21 = var1.matchtype;
         var2 += CodedOutputStream.computeInt32Size(7, var21);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var22 = var1.world;
         var2 += CodedOutputStream.computeInt32Size(8, var22);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.channel)) {
         Integer var23 = var1.channel;
         var2 += CodedOutputStream.computeInt32Size(9, var23);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.targetguid)) {
         Long var24 = var1.targetguid;
         var2 += CodedOutputStream.computeUInt64Size(10, var24);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.users)) {
         List var25 = var1.users;
         var2 += CodedConstant.computeListSize(11, var25, FieldType.UINT64, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.detail)) {
         List var26 = var1.detail;
         var2 += CodedConstant.computeListSize(12, var26, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(PT_DUNGEON_START_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.matchingguid)) {
         Long var15 = var1.matchingguid;
         if (var15 != null) {
            var2.writeUInt64(1, var15);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var16 = var1.dungeonguid;
         if (var16 != null) {
            var2.writeUInt64(2, var16);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.battleworld)) {
         Integer var17 = var1.battleworld;
         if (var17 != null) {
            var2.writeInt32(3, var17);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.bchannel)) {
         Integer var18 = var1.bchannel;
         if (var18 != null) {
            var2.writeInt32(4, var18);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.bip)) {
         String var19 = var1.bip;
         if (var19 != null) {
            var2.writeString(5, var19);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.bport)) {
         Integer var20 = var1.bport;
         if (var20 != null) {
            var2.writeInt32(6, var20);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.matchtype)) {
         Integer var21 = var1.matchtype;
         if (var21 != null) {
            var2.writeInt32(7, var21);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var22 = var1.world;
         if (var22 != null) {
            var2.writeInt32(8, var22);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.channel)) {
         Integer var23 = var1.channel;
         if (var23 != null) {
            var2.writeInt32(9, var23);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.targetguid)) {
         Long var24 = var1.targetguid;
         if (var24 != null) {
            var2.writeUInt64(10, var24);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.users)) {
         List var25 = var1.users;
         if (var25 != null) {
            CodedConstant.writeToList(var2, 11, FieldType.UINT64, var25, true);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.detail)) {
         List var26 = var1.detail;
         if (var26 != null) {
            CodedConstant.writeToList(var2, 12, FieldType.OBJECT, var26, false);
         }
      }

   }

   public void writeTo(PT_DUNGEON_START_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_DUNGEON_START_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_DUNGEON_START_INFO var2 = new PT_DUNGEON_START_INFO();
      var2.users = new ArrayList();
      var2.detail = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.matchingguid = var1.readUInt64();
            } else if (var5 == 16) {
               var2.dungeonguid = var1.readUInt64();
            } else if (var5 == 24) {
               var2.battleworld = var1.readInt32();
            } else if (var5 == 32) {
               var2.bchannel = var1.readInt32();
            } else if (var5 == 42) {
               var2.bip = var1.readString();
            } else if (var5 == 48) {
               var2.bport = var1.readInt32();
            } else if (var5 == 56) {
               var2.matchtype = var1.readInt32();
            } else if (var5 == 64) {
               var2.world = var1.readInt32();
            } else if (var5 == 72) {
               var2.channel = var1.readInt32();
            } else if (var5 == 80) {
               var2.targetguid = var1.readUInt64();
            } else if (var5 == 88) {
               if (var2.users == null) {
                  var2.users = new ArrayList();
               }

               var2.users.add(var1.readUInt64());
            } else if (var5 == 90) {
               int var12 = var1.readRawVarint32();
               int var14 = var1.pushLimit(var12);
               if (var2.users == null) {
                  var2.users = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.users.add(var1.readUInt64());
               }

               var1.popLimit(var14);
            } else if (var5 != 98) {
               var1.skipField(var5);
            } else if (var5 != 90) {
               Codec var10 = ProtobufProxy.create(PT_USER_MINIMUM_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var11 = var1.readRawVarint32();
               int var13 = var1.pushLimit(var11);
               if (var2.detail == null) {
                  var2.detail = new ArrayList();
               }

               var2.detail.add((PT_USER_MINIMUM_INFO)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var13);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_DUNGEON_START_INFO.class);
         return this.descriptor = var1;
      }
   }
}
