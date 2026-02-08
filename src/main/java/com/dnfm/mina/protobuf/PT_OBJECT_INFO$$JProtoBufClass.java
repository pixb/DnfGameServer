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

public class PT_OBJECT_INFO$$JProtoBufClass implements Codec<PT_OBJECT_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_OBJECT_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_OBJECT_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_OBJECT_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var17 = var1.guid;
         var2 += CodedOutputStream.computeUInt64Size(1, var17);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.ownerguid)) {
         Long var18 = var1.ownerguid;
         var2 += CodedOutputStream.computeUInt64Size(2, var18);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var19 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(3, var19);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.teamtype)) {
         Integer var20 = var1.teamtype;
         var2 += CodedOutputStream.computeInt32Size(4, var20);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.die)) {
         Boolean var21 = var1.die;
         var2 += CodedOutputStream.computeBoolSize(5, var21);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var22 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(6, var22);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var23 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(7, var23);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.hp)) {
         Long var24 = var1.hp;
         var2 += CodedOutputStream.computeUInt64Size(8, var24);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.golds)) {
         List var25 = var1.golds;
         var2 += CodedConstant.computeListSize(9, var25, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.items)) {
         List var26 = var1.items;
         var2 += CodedConstant.computeListSize(10, var26, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.exps)) {
         List var27 = var1.exps;
         var2 += CodedConstant.computeListSize(11, var27, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.appendages)) {
         List var28 = var1.appendages;
         var2 += CodedConstant.computeListSize(12, var28, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.waveindex)) {
         Integer var29 = var1.waveindex;
         var2 += CodedOutputStream.computeInt32Size(13, var29);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.spawnindex)) {
         Integer var30 = var1.spawnindex;
         var2 += CodedOutputStream.computeInt32Size(14, var30);
      }

      return var2;
   }

   public void doWriteTo(PT_OBJECT_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var17 = var1.guid;
         if (var17 != null) {
            var2.writeUInt64(1, var17);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.ownerguid)) {
         Long var18 = var1.ownerguid;
         if (var18 != null) {
            var2.writeUInt64(2, var18);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var19 = var1.level;
         if (var19 != null) {
            var2.writeInt32(3, var19);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.teamtype)) {
         Integer var20 = var1.teamtype;
         if (var20 != null) {
            var2.writeInt32(4, var20);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.die)) {
         Boolean var21 = var1.die;
         if (var21 != null) {
            var2.writeBool(5, var21);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var22 = var1.type;
         if (var22 != null) {
            var2.writeInt32(6, var22);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var23 = var1.index;
         if (var23 != null) {
            var2.writeInt32(7, var23);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.hp)) {
         Long var24 = var1.hp;
         if (var24 != null) {
            var2.writeUInt64(8, var24);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.golds)) {
         List var25 = var1.golds;
         if (var25 != null) {
            CodedConstant.writeToList(var2, 9, FieldType.OBJECT, var25, false);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.items)) {
         List var26 = var1.items;
         if (var26 != null) {
            CodedConstant.writeToList(var2, 10, FieldType.OBJECT, var26, false);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.exps)) {
         List var27 = var1.exps;
         if (var27 != null) {
            CodedConstant.writeToList(var2, 11, FieldType.OBJECT, var27, false);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.appendages)) {
         List var28 = var1.appendages;
         if (var28 != null) {
            CodedConstant.writeToList(var2, 12, FieldType.OBJECT, var28, false);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.waveindex)) {
         Integer var29 = var1.waveindex;
         if (var29 != null) {
            var2.writeInt32(13, var29);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.spawnindex)) {
         Integer var30 = var1.spawnindex;
         if (var30 != null) {
            var2.writeInt32(14, var30);
         }
      }

   }

   public void writeTo(PT_OBJECT_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_OBJECT_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_OBJECT_INFO var2 = new PT_OBJECT_INFO();
      var2.golds = new ArrayList();
      var2.items = new ArrayList();
      var2.exps = new ArrayList();
      var2.appendages = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.guid = var1.readUInt64();
            } else if (var5 == 16) {
               var2.ownerguid = var1.readUInt64();
            } else if (var5 == 24) {
               var2.level = var1.readInt32();
            } else if (var5 == 32) {
               var2.teamtype = var1.readInt32();
            } else if (var5 == 40) {
               var2.die = var1.readBool();
            } else if (var5 == 48) {
               var2.type = var1.readInt32();
            } else if (var5 == 56) {
               var2.index = var1.readInt32();
            } else if (var5 == 64) {
               var2.hp = var1.readUInt64();
            } else if (var5 == 74) {
               Codec var10 = ProtobufProxy.create(PT_DROP_OBJECT_GOLD.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.golds == null) {
                  var2.golds = new ArrayList();
               }

               var2.golds.add((PT_DROP_OBJECT_GOLD)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 82) {
               Codec var11 = ProtobufProxy.create(PT_DROP_OBJECT_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var14 = var1.readRawVarint32();
               int var17 = var1.pushLimit(var14);
               if (var2.items == null) {
                  var2.items = new ArrayList();
               }

               var2.items.add((PT_DROP_OBJECT_ITEM)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var17);
            } else if (var5 == 90) {
               Codec var12 = ProtobufProxy.create(PT_MONSTER_EXP.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var15 = var1.readRawVarint32();
               int var18 = var1.pushLimit(var15);
               if (var2.exps == null) {
                  var2.exps = new ArrayList();
               }

               var2.exps.add((PT_MONSTER_EXP)var12.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var18);
            } else if (var5 == 98) {
               Codec var13 = ProtobufProxy.create(PT_APPENDAGE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var16 = var1.readRawVarint32();
               int var19 = var1.pushLimit(var16);
               if (var2.appendages == null) {
                  var2.appendages = new ArrayList();
               }

               var2.appendages.add((PT_APPENDAGE)var13.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var19);
            } else if (var5 == 104) {
               var2.waveindex = var1.readInt32();
            } else if (var5 == 112) {
               var2.spawnindex = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_OBJECT_INFO.class);
         return this.descriptor = var1;
      }
   }
}
