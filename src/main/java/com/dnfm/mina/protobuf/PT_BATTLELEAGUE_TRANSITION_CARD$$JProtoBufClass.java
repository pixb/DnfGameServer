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

public class PT_BATTLELEAGUE_TRANSITION_CARD$$JProtoBufClass implements Codec<PT_BATTLELEAGUE_TRANSITION_CARD>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_BATTLELEAGUE_TRANSITION_CARD var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_BATTLELEAGUE_TRANSITION_CARD decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_BATTLELEAGUE_TRANSITION_CARD var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var9 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var9);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.items)) {
         List var10 = var1.items;
         var2 += CodedConstant.computeListSize(2, var10, FieldType.INT32, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.buffs)) {
         List var11 = var1.buffs;
         var2 += CodedConstant.computeListSize(3, var11, FieldType.INT32, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.buff)) {
         List var12 = var1.buff;
         var2 += CodedConstant.computeListSize(4, var12, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.pvepoint)) {
         Integer var13 = var1.pvepoint;
         var2 += CodedOutputStream.computeInt32Size(5, var13);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.pverewards)) {
         List var14 = var1.pverewards;
         var2 += CodedConstant.computeListSize(6, var14, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(PT_BATTLELEAGUE_TRANSITION_CARD var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var9 = var1.charguid;
         if (var9 != null) {
            var2.writeUInt64(1, var9);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.items)) {
         List var10 = var1.items;
         if (var10 != null) {
            CodedConstant.writeToList(var2, 2, FieldType.INT32, var10, true);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.buffs)) {
         List var11 = var1.buffs;
         if (var11 != null) {
            CodedConstant.writeToList(var2, 3, FieldType.INT32, var11, true);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.buff)) {
         List var12 = var1.buff;
         if (var12 != null) {
            CodedConstant.writeToList(var2, 4, FieldType.OBJECT, var12, false);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.pvepoint)) {
         Integer var13 = var1.pvepoint;
         if (var13 != null) {
            var2.writeInt32(5, var13);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.pverewards)) {
         List var14 = var1.pverewards;
         if (var14 != null) {
            CodedConstant.writeToList(var2, 6, FieldType.OBJECT, var14, false);
         }
      }

   }

   public void writeTo(PT_BATTLELEAGUE_TRANSITION_CARD var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_BATTLELEAGUE_TRANSITION_CARD readFrom(CodedInputStream var1) throws IOException {
      PT_BATTLELEAGUE_TRANSITION_CARD var2 = new PT_BATTLELEAGUE_TRANSITION_CARD();
      var2.items = new ArrayList();
      var2.buffs = new ArrayList();
      var2.buff = new ArrayList();
      var2.pverewards = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.charguid = var1.readUInt64();
            } else if (var5 == 16) {
               if (var2.items == null) {
                  var2.items = new ArrayList();
               }

               var2.items.add(var1.readInt32());
            } else if (var5 == 18) {
               int var17 = var1.readRawVarint32();
               int var23 = var1.pushLimit(var17);
               if (var2.items == null) {
                  var2.items = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.items.add(var1.readInt32());
               }

               var1.popLimit(var23);
            } else if (var5 == 24) {
               if (var2.buffs == null) {
                  var2.buffs = new ArrayList();
               }

               var2.buffs.add(var1.readInt32());
            } else if (var5 == 18) {
               int var16 = var1.readRawVarint32();
               int var22 = var1.pushLimit(var16);
               if (var2.items == null) {
                  var2.items = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.items.add(var1.readInt32());
               }

               var1.popLimit(var22);
            } else if (var5 == 26) {
               int var15 = var1.readRawVarint32();
               int var21 = var1.pushLimit(var15);
               if (var2.buffs == null) {
                  var2.buffs = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.buffs.add(var1.readInt32());
               }

               var1.popLimit(var21);
            } else if (var5 != 34) {
               if (var5 == 40) {
                  var2.pvepoint = var1.readInt32();
               } else if (var5 == 50) {
                  Codec var11 = ProtobufProxy.create(PT_BATTLELEAGUE_REWARD_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
                  int var14 = var1.readRawVarint32();
                  int var20 = var1.pushLimit(var14);
                  if (var2.pverewards == null) {
                     var2.pverewards = new ArrayList();
                  }

                  var2.pverewards.add((PT_BATTLELEAGUE_REWARD_INFO)var11.readFrom(var1));
                  var1.checkLastTagWas(0);
                  var1.popLimit(var20);
               } else {
                  var1.skipField(var5);
               }
            } else if (var5 == 18) {
               int var13 = var1.readRawVarint32();
               int var19 = var1.pushLimit(var13);
               if (var2.items == null) {
                  var2.items = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.items.add(var1.readInt32());
               }

               var1.popLimit(var19);
            } else if (var5 != 26) {
               Codec var10 = ProtobufProxy.create(PT_BATTLELEAGUE_BUFF.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var12 = var1.readRawVarint32();
               int var18 = var1.pushLimit(var12);
               if (var2.buff == null) {
                  var2.buff = new ArrayList();
               }

               var2.buff.add((PT_BATTLELEAGUE_BUFF)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var18);
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.buffs == null) {
                  var2.buffs = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.buffs.add(var1.readInt32());
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_BATTLELEAGUE_TRANSITION_CARD.class);
         return this.descriptor = var1;
      }
   }
}
