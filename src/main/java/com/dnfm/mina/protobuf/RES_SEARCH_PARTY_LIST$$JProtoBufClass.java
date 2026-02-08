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

public class RES_SEARCH_PARTY_LIST$$JProtoBufClass implements Codec<RES_SEARCH_PARTY_LIST>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_SEARCH_PARTY_LIST var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_SEARCH_PARTY_LIST decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_SEARCH_PARTY_LIST var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var15 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var15);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var16 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(2, var16);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.area)) {
         Integer var17 = var1.area;
         var2 += CodedOutputStream.computeInt32Size(3, var17);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.subtype)) {
         Integer var18 = var1.subtype;
         var2 += CodedOutputStream.computeInt32Size(4, var18);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.detail)) {
         Integer var19 = var1.detail;
         var2 += CodedOutputStream.computeInt32Size(5, var19);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.list)) {
         List var20 = var1.list;
         var2 += CodedConstant.computeListSize(6, var20, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.dungeonindexcount)) {
         List var21 = var1.dungeonindexcount;
         var2 += CodedConstant.computeListSize(7, var21, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.stagecount)) {
         List var22 = var1.stagecount;
         var2 += CodedConstant.computeListSize(8, var22, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.containobserver)) {
         Boolean var23 = var1.containobserver;
         var2 += CodedOutputStream.computeBoolSize(9, var23);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var24 = var1.dungeonindex;
         var2 += CodedOutputStream.computeInt32Size(10, var24);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.stageindex)) {
         Integer var25 = var1.stageindex;
         var2 += CodedOutputStream.computeInt32Size(11, var25);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.enablepartyping)) {
         Boolean var26 = var1.enablepartyping;
         var2 += CodedOutputStream.computeBoolSize(12, var26);
      }

      return var2;
   }

   public void doWriteTo(RES_SEARCH_PARTY_LIST var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var15 = var1.error;
         if (var15 != null) {
            var2.writeInt32(1, var15);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var16 = var1.type;
         if (var16 != null) {
            var2.writeInt32(2, var16);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.area)) {
         Integer var17 = var1.area;
         if (var17 != null) {
            var2.writeInt32(3, var17);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.subtype)) {
         Integer var18 = var1.subtype;
         if (var18 != null) {
            var2.writeInt32(4, var18);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.detail)) {
         Integer var19 = var1.detail;
         if (var19 != null) {
            var2.writeInt32(5, var19);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.list)) {
         List var20 = var1.list;
         if (var20 != null) {
            CodedConstant.writeToList(var2, 6, FieldType.OBJECT, var20, false);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.dungeonindexcount)) {
         List var21 = var1.dungeonindexcount;
         if (var21 != null) {
            CodedConstant.writeToList(var2, 7, FieldType.OBJECT, var21, false);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.stagecount)) {
         List var22 = var1.stagecount;
         if (var22 != null) {
            CodedConstant.writeToList(var2, 8, FieldType.OBJECT, var22, false);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.containobserver)) {
         Boolean var23 = var1.containobserver;
         if (var23 != null) {
            var2.writeBool(9, var23);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var24 = var1.dungeonindex;
         if (var24 != null) {
            var2.writeInt32(10, var24);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.stageindex)) {
         Integer var25 = var1.stageindex;
         if (var25 != null) {
            var2.writeInt32(11, var25);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.enablepartyping)) {
         Boolean var26 = var1.enablepartyping;
         if (var26 != null) {
            var2.writeBool(12, var26);
         }
      }

   }

   public void writeTo(RES_SEARCH_PARTY_LIST var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_SEARCH_PARTY_LIST readFrom(CodedInputStream var1) throws IOException {
      RES_SEARCH_PARTY_LIST var2 = new RES_SEARCH_PARTY_LIST();
      var2.list = new ArrayList();
      var2.dungeonindexcount = new ArrayList();
      var2.stagecount = new ArrayList();

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
               var2.area = var1.readInt32();
            } else if (var5 == 32) {
               var2.subtype = var1.readInt32();
            } else if (var5 == 40) {
               var2.detail = var1.readInt32();
            } else if (var5 == 50) {
               Codec var10 = ProtobufProxy.create(PT_RES_PARTY_LIST.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.list == null) {
                  var2.list = new ArrayList();
               }

               var2.list.add((PT_RES_PARTY_LIST)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 58) {
               Codec var11 = ProtobufProxy.create(PT_DUNGEON_PARTY_COUNT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var13 = var1.readRawVarint32();
               int var15 = var1.pushLimit(var13);
               if (var2.dungeonindexcount == null) {
                  var2.dungeonindexcount = new ArrayList();
               }

               var2.dungeonindexcount.add((PT_DUNGEON_PARTY_COUNT)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var15);
            } else if (var5 == 66) {
               Codec var12 = ProtobufProxy.create(PT_STAGE_PARTY_COUNT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var14 = var1.readRawVarint32();
               int var16 = var1.pushLimit(var14);
               if (var2.stagecount == null) {
                  var2.stagecount = new ArrayList();
               }

               var2.stagecount.add((PT_STAGE_PARTY_COUNT)var12.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var16);
            } else if (var5 == 72) {
               var2.containobserver = var1.readBool();
            } else if (var5 == 80) {
               var2.dungeonindex = var1.readInt32();
            } else if (var5 == 88) {
               var2.stageindex = var1.readInt32();
            } else if (var5 == 96) {
               var2.enablepartyping = var1.readBool();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_SEARCH_PARTY_LIST.class);
         return this.descriptor = var1;
      }
   }
}
