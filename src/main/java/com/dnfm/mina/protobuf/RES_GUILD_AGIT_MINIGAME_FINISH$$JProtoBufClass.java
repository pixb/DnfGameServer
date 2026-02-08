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

public class RES_GUILD_AGIT_MINIGAME_FINISH$$JProtoBufClass implements Codec<RES_GUILD_AGIT_MINIGAME_FINISH>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_GUILD_AGIT_MINIGAME_FINISH var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_GUILD_AGIT_MINIGAME_FINISH decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_GUILD_AGIT_MINIGAME_FINISH var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var12 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var12);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.myrank)) {
         Integer var13 = var1.myrank;
         var2 += CodedOutputStream.computeInt32Size(2, var13);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.myscore)) {
         Long var14 = var1.myscore;
         var2 += CodedOutputStream.computeUInt64Size(3, var14);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.rewardinfo)) {
         PT_GUILD_INVENTORY_LIST var15 = var1.rewardinfo;
         var2 += CodedConstant.computeSize(4, var15, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.rankinginfos)) {
         List var16 = var1.rankinginfos;
         var2 += CodedConstant.computeListSize(5, var16, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.type)) {
         ENUM_GUILD_AGIT_MINIGAME_TYPE.T var17 = var1.type;
         var2 += CodedOutputStream.computeEnumSize(6, ((ENUM_GUILD_AGIT_MINIGAME_TYPE.T)var17).value());
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.myguildrank)) {
         Integer var18 = var1.myguildrank;
         var2 += CodedOutputStream.computeInt32Size(7, var18);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.finishtype)) {
         ENUM_GUILD_AGIT_FINISH_TYPE.T var19 = var1.finishtype;
         var2 += CodedOutputStream.computeEnumSize(8, ((ENUM_GUILD_AGIT_FINISH_TYPE.T)var19).value());
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.memberrewardinfo)) {
         PT_GUILD_INVENTORY_LIST var20 = var1.memberrewardinfo;
         var2 += CodedConstant.computeSize(9, var20, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      return var2;
   }

   public void doWriteTo(RES_GUILD_AGIT_MINIGAME_FINISH var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var12 = var1.error;
         if (var12 != null) {
            var2.writeInt32(1, var12);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.myrank)) {
         Integer var13 = var1.myrank;
         if (var13 != null) {
            var2.writeInt32(2, var13);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.myscore)) {
         Long var14 = var1.myscore;
         if (var14 != null) {
            var2.writeUInt64(3, var14);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.rewardinfo)) {
         PT_GUILD_INVENTORY_LIST var15 = var1.rewardinfo;
         if (var15 != null) {
            CodedConstant.writeObject(var2, 4, FieldType.OBJECT, var15, false);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.rankinginfos)) {
         List var16 = var1.rankinginfos;
         if (var16 != null) {
            CodedConstant.writeToList(var2, 5, FieldType.OBJECT, var16, false);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.type)) {
         ENUM_GUILD_AGIT_MINIGAME_TYPE.T var17 = var1.type;
         if (var17 != null) {
            var2.writeEnum(6, ((ENUM_GUILD_AGIT_MINIGAME_TYPE.T)var17).value());
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.myguildrank)) {
         Integer var18 = var1.myguildrank;
         if (var18 != null) {
            var2.writeInt32(7, var18);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.finishtype)) {
         ENUM_GUILD_AGIT_FINISH_TYPE.T var19 = var1.finishtype;
         if (var19 != null) {
            var2.writeEnum(8, ((ENUM_GUILD_AGIT_FINISH_TYPE.T)var19).value());
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.memberrewardinfo)) {
         PT_GUILD_INVENTORY_LIST var20 = var1.memberrewardinfo;
         if (var20 != null) {
            CodedConstant.writeObject(var2, 9, FieldType.OBJECT, var20, false);
         }
      }

   }

   public void writeTo(RES_GUILD_AGIT_MINIGAME_FINISH var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_GUILD_AGIT_MINIGAME_FINISH readFrom(CodedInputStream var1) throws IOException {
      RES_GUILD_AGIT_MINIGAME_FINISH var2 = new RES_GUILD_AGIT_MINIGAME_FINISH();
      var2.rankinginfos = new ArrayList();
      var2.type = (ENUM_GUILD_AGIT_MINIGAME_TYPE.T)CodedConstant.getEnumValue(ENUM_GUILD_AGIT_MINIGAME_TYPE.T.class, ENUM_GUILD_AGIT_MINIGAME_TYPE.T.values()[0].name());
      var2.finishtype = (ENUM_GUILD_AGIT_FINISH_TYPE.T)CodedConstant.getEnumValue(ENUM_GUILD_AGIT_FINISH_TYPE.T.class, ENUM_GUILD_AGIT_FINISH_TYPE.T.values()[0].name());

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
               var2.myrank = var1.readInt32();
            } else if (var5 == 24) {
               var2.myscore = var1.readUInt64();
            } else if (var5 == 34) {
               Codec var10 = ProtobufProxy.create(PT_GUILD_INVENTORY_LIST.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.rewardinfo = (PT_GUILD_INVENTORY_LIST)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 42) {
               Codec var11 = ProtobufProxy.create(PT_RANKING_GROUP.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var13 = var1.readRawVarint32();
               int var15 = var1.pushLimit(var13);
               if (var2.rankinginfos == null) {
                  var2.rankinginfos = new ArrayList();
               }

               var2.rankinginfos.add((PT_RANKING_GROUP)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var15);
            } else if (var5 == 48) {
               var2.type = (ENUM_GUILD_AGIT_MINIGAME_TYPE.T)CodedConstant.getEnumValue(ENUM_GUILD_AGIT_MINIGAME_TYPE.T.class, CodedConstant.getEnumName(ENUM_GUILD_AGIT_MINIGAME_TYPE.T.values(), var1.readEnum()));
            } else if (var5 == 56) {
               var2.myguildrank = var1.readInt32();
            } else if (var5 == 64) {
               var2.finishtype = (ENUM_GUILD_AGIT_FINISH_TYPE.T)CodedConstant.getEnumValue(ENUM_GUILD_AGIT_FINISH_TYPE.T.class, CodedConstant.getEnumName(ENUM_GUILD_AGIT_FINISH_TYPE.T.values(), var1.readEnum()));
            } else if (var5 == 74) {
               Codec var12 = ProtobufProxy.create(PT_GUILD_INVENTORY_LIST.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var14 = var1.readRawVarint32();
               int var16 = var1.pushLimit(var14);
               var2.memberrewardinfo = (PT_GUILD_INVENTORY_LIST)var12.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var16);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_GUILD_AGIT_MINIGAME_FINISH.class);
         return this.descriptor = var1;
      }
   }
}
