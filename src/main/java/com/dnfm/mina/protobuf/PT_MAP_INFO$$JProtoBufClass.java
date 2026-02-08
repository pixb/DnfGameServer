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

public class PT_MAP_INFO$$JProtoBufClass implements Codec<PT_MAP_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_MAP_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_MAP_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_MAP_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var18 = var1.guid;
         var2 += CodedOutputStream.computeUInt64Size(1, var18);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var19 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(2, var19);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.bossmap)) {
         Boolean var20 = var1.bossmap;
         var2 += CodedOutputStream.computeBoolSize(3, var20);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.cleartime)) {
         Long var21 = var1.cleartime;
         var2 += CodedOutputStream.computeUInt64Size(4, var21);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.starttick)) {
         Long var22 = var1.starttick;
         var2 += CodedOutputStream.computeUInt64Size(5, var22);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.endtick)) {
         Long var23 = var1.endtick;
         var2 += CodedOutputStream.computeUInt64Size(6, var23);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.guardiandeal)) {
         Long var24 = var1.guardiandeal;
         var2 += CodedOutputStream.computeUInt64Size(7, var24);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.floor)) {
         Integer var25 = var1.floor;
         var2 += CodedOutputStream.computeInt32Size(8, var25);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.objects)) {
         List var26 = var1.objects;
         var2 += CodedConstant.computeListSize(9, var26, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.startmap)) {
         Boolean var27 = var1.startmap;
         var2 += CodedOutputStream.computeBoolSize(10, var27);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.posx)) {
         Integer var28 = var1.posx;
         var2 += CodedOutputStream.computeInt32Size(11, var28);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.posy)) {
         Integer var29 = var1.posy;
         var2 += CodedOutputStream.computeInt32Size(12, var29);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.expinfos)) {
         List var30 = var1.expinfos;
         var2 += CodedConstant.computeListSize(13, var30, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.waveobjects)) {
         List var31 = var1.waveobjects;
         var2 += CodedConstant.computeListSize(14, var31, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.crackgateindex)) {
         Integer var32 = var1.crackgateindex;
         var2 += CodedOutputStream.computeInt32Size(15, var32);
      }

      return var2;
   }

   public void doWriteTo(PT_MAP_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var18 = var1.guid;
         if (var18 != null) {
            var2.writeUInt64(1, var18);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var19 = var1.index;
         if (var19 != null) {
            var2.writeInt32(2, var19);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.bossmap)) {
         Boolean var20 = var1.bossmap;
         if (var20 != null) {
            var2.writeBool(3, var20);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.cleartime)) {
         Long var21 = var1.cleartime;
         if (var21 != null) {
            var2.writeUInt64(4, var21);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.starttick)) {
         Long var22 = var1.starttick;
         if (var22 != null) {
            var2.writeUInt64(5, var22);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.endtick)) {
         Long var23 = var1.endtick;
         if (var23 != null) {
            var2.writeUInt64(6, var23);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.guardiandeal)) {
         Long var24 = var1.guardiandeal;
         if (var24 != null) {
            var2.writeUInt64(7, var24);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.floor)) {
         Integer var25 = var1.floor;
         if (var25 != null) {
            var2.writeInt32(8, var25);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.objects)) {
         List var26 = var1.objects;
         if (var26 != null) {
            CodedConstant.writeToList(var2, 9, FieldType.OBJECT, var26, false);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.startmap)) {
         Boolean var27 = var1.startmap;
         if (var27 != null) {
            var2.writeBool(10, var27);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.posx)) {
         Integer var28 = var1.posx;
         if (var28 != null) {
            var2.writeInt32(11, var28);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.posy)) {
         Integer var29 = var1.posy;
         if (var29 != null) {
            var2.writeInt32(12, var29);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.expinfos)) {
         List var30 = var1.expinfos;
         if (var30 != null) {
            CodedConstant.writeToList(var2, 13, FieldType.OBJECT, var30, false);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.waveobjects)) {
         List var31 = var1.waveobjects;
         if (var31 != null) {
            CodedConstant.writeToList(var2, 14, FieldType.OBJECT, var31, false);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.crackgateindex)) {
         Integer var32 = var1.crackgateindex;
         if (var32 != null) {
            var2.writeInt32(15, var32);
         }
      }

   }

   public void writeTo(PT_MAP_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_MAP_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_MAP_INFO var2 = new PT_MAP_INFO();
      var2.objects = new ArrayList();
      var2.expinfos = new ArrayList();
      var2.waveobjects = new ArrayList();

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
               var2.index = var1.readInt32();
            } else if (var5 == 24) {
               var2.bossmap = var1.readBool();
            } else if (var5 == 32) {
               var2.cleartime = var1.readUInt64();
            } else if (var5 == 40) {
               var2.starttick = var1.readUInt64();
            } else if (var5 == 48) {
               var2.endtick = var1.readUInt64();
            } else if (var5 == 56) {
               var2.guardiandeal = var1.readUInt64();
            } else if (var5 == 64) {
               var2.floor = var1.readInt32();
            } else if (var5 == 74) {
               Codec var10 = ProtobufProxy.create(PT_OBJECT_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.objects == null) {
                  var2.objects = new ArrayList();
               }

               var2.objects.add((PT_OBJECT_INFO)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 80) {
               var2.startmap = var1.readBool();
            } else if (var5 == 88) {
               var2.posx = var1.readInt32();
            } else if (var5 == 96) {
               var2.posy = var1.readInt32();
            } else if (var5 == 106) {
               Codec var11 = ProtobufProxy.create(PT_EXP_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var13 = var1.readRawVarint32();
               int var15 = var1.pushLimit(var13);
               if (var2.expinfos == null) {
                  var2.expinfos = new ArrayList();
               }

               var2.expinfos.add((PT_EXP_INFO)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var15);
            } else if (var5 == 114) {
               Codec var12 = ProtobufProxy.create(PT_OBJECT_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var14 = var1.readRawVarint32();
               int var16 = var1.pushLimit(var14);
               if (var2.waveobjects == null) {
                  var2.waveobjects = new ArrayList();
               }

               var2.waveobjects.add((PT_OBJECT_INFO)var12.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var16);
            } else if (var5 == 120) {
               var2.crackgateindex = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_MAP_INFO.class);
         return this.descriptor = var1;
      }
   }
}
