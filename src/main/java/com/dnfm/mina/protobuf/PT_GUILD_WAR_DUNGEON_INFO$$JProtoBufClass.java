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

public class PT_GUILD_WAR_DUNGEON_INFO$$JProtoBufClass implements Codec<PT_GUILD_WAR_DUNGEON_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_GUILD_WAR_DUNGEON_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_GUILD_WAR_DUNGEON_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_GUILD_WAR_DUNGEON_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.attackpoints)) {
         List var9 = var1.attackpoints;
         var2 += CodedConstant.computeListSize(1, var9, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var10 = var1.dungeonindex;
         var2 += CodedOutputStream.computeInt32Size(2, var10);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var11 = var1.dungeonguid;
         var2 += CodedOutputStream.computeUInt64Size(3, var11);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.entertime)) {
         Long var12 = var1.entertime;
         var2 += CodedOutputStream.computeInt64Size(4, var12);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.occupypoint)) {
         Integer var13 = var1.occupypoint;
         var2 += CodedOutputStream.computeInt32Size(5, var13);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.group)) {
         Integer var14 = var1.group;
         var2 += CodedOutputStream.computeInt32Size(6, var14);
      }

      return var2;
   }

   public void doWriteTo(PT_GUILD_WAR_DUNGEON_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.attackpoints)) {
         List var9 = var1.attackpoints;
         if (var9 != null) {
            CodedConstant.writeToList(var2, 1, FieldType.OBJECT, var9, false);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var10 = var1.dungeonindex;
         if (var10 != null) {
            var2.writeInt32(2, var10);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var11 = var1.dungeonguid;
         if (var11 != null) {
            var2.writeUInt64(3, var11);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.entertime)) {
         Long var12 = var1.entertime;
         if (var12 != null) {
            var2.writeInt64(4, var12);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.occupypoint)) {
         Integer var13 = var1.occupypoint;
         if (var13 != null) {
            var2.writeInt32(5, var13);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.group)) {
         Integer var14 = var1.group;
         if (var14 != null) {
            var2.writeInt32(6, var14);
         }
      }

   }

   public void writeTo(PT_GUILD_WAR_DUNGEON_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_GUILD_WAR_DUNGEON_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_GUILD_WAR_DUNGEON_INFO var2 = new PT_GUILD_WAR_DUNGEON_INFO();
      var2.attackpoints = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 10) {
               Codec var10 = ProtobufProxy.create(PT_GUILD_WAR_ATTACK_POINT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.attackpoints == null) {
                  var2.attackpoints = new ArrayList();
               }

               var2.attackpoints.add((PT_GUILD_WAR_ATTACK_POINT)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 16) {
               var2.dungeonindex = var1.readInt32();
            } else if (var5 == 24) {
               var2.dungeonguid = var1.readUInt64();
            } else if (var5 == 32) {
               var2.entertime = var1.readInt64();
            } else if (var5 == 40) {
               var2.occupypoint = var1.readInt32();
            } else if (var5 == 48) {
               var2.group = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_GUILD_WAR_DUNGEON_INFO.class);
         return this.descriptor = var1;
      }
   }
}
