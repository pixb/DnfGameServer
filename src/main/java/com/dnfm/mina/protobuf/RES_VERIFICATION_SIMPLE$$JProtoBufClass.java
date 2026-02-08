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

public class RES_VERIFICATION_SIMPLE$$JProtoBufClass implements Codec<RES_VERIFICATION_SIMPLE>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_VERIFICATION_SIMPLE var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_VERIFICATION_SIMPLE decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_VERIFICATION_SIMPLE var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var17 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var17);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var18 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(2, var18);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var19 = var1.dungeonindex;
         var2 += CodedOutputStream.computeInt32Size(3, var19);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.mapindex)) {
         Integer var20 = var1.mapindex;
         var2 += CodedOutputStream.computeInt32Size(4, var20);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.gameworld)) {
         Integer var21 = var1.gameworld;
         var2 += CodedOutputStream.computeInt32Size(5, var21);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.gchannel)) {
         Integer var22 = var1.gchannel;
         var2 += CodedOutputStream.computeInt32Size(6, var22);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.packetid)) {
         Integer var23 = var1.packetid;
         var2 += CodedOutputStream.computeInt32Size(7, var23);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.verifyguid)) {
         Long var24 = var1.verifyguid;
         var2 += CodedOutputStream.computeUInt64Size(8, var24);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.clientIP)) {
         String var25 = var1.clientIP;
         var2 += CodedOutputStream.computeStringSize(9, var25);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.packetmsg)) {
         byte[] var26 = var1.packetmsg;
         var2 += CodedOutputStream.computeByteArraySize(10, var26);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.characinfos)) {
         List var27 = var1.characinfos;
         var2 += CodedConstant.computeListSize(11, var27, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.map)) {
         PT_MAP_INFO var28 = var1.map;
         var2 += CodedConstant.computeSize(12, var28, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.featureswitch)) {
         Long var29 = var1.featureswitch;
         var2 += CodedOutputStream.computeUInt64Size(13, var29);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.dungeontypeswitch)) {
         byte[] var30 = var1.dungeontypeswitch;
         var2 += CodedOutputStream.computeByteArraySize(14, var30);
      }

      return var2;
   }

   public void doWriteTo(RES_VERIFICATION_SIMPLE var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var17 = var1.error;
         if (var17 != null) {
            var2.writeInt32(1, var17);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var18 = var1.charguid;
         if (var18 != null) {
            var2.writeUInt64(2, var18);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var19 = var1.dungeonindex;
         if (var19 != null) {
            var2.writeInt32(3, var19);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.mapindex)) {
         Integer var20 = var1.mapindex;
         if (var20 != null) {
            var2.writeInt32(4, var20);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.gameworld)) {
         Integer var21 = var1.gameworld;
         if (var21 != null) {
            var2.writeInt32(5, var21);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.gchannel)) {
         Integer var22 = var1.gchannel;
         if (var22 != null) {
            var2.writeInt32(6, var22);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.packetid)) {
         Integer var23 = var1.packetid;
         if (var23 != null) {
            var2.writeInt32(7, var23);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.verifyguid)) {
         Long var24 = var1.verifyguid;
         if (var24 != null) {
            var2.writeUInt64(8, var24);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.clientIP)) {
         String var25 = var1.clientIP;
         if (var25 != null) {
            var2.writeString(9, var25);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.packetmsg)) {
         byte[] var26 = var1.packetmsg;
         if (var26 != null) {
            var2.writeByteArray(10, var26);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.characinfos)) {
         List var27 = var1.characinfos;
         if (var27 != null) {
            CodedConstant.writeToList(var2, 11, FieldType.OBJECT, var27, false);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.map)) {
         PT_MAP_INFO var28 = var1.map;
         if (var28 != null) {
            CodedConstant.writeObject(var2, 12, FieldType.OBJECT, var28, false);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.featureswitch)) {
         Long var29 = var1.featureswitch;
         if (var29 != null) {
            var2.writeUInt64(13, var29);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.dungeontypeswitch)) {
         byte[] var30 = var1.dungeontypeswitch;
         if (var30 != null) {
            var2.writeByteArray(14, var30);
         }
      }

   }

   public void writeTo(RES_VERIFICATION_SIMPLE var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_VERIFICATION_SIMPLE readFrom(CodedInputStream var1) throws IOException {
      RES_VERIFICATION_SIMPLE var2 = new RES_VERIFICATION_SIMPLE();
      var2.characinfos = new ArrayList();

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
               var2.charguid = var1.readUInt64();
            } else if (var5 == 24) {
               var2.dungeonindex = var1.readInt32();
            } else if (var5 == 32) {
               var2.mapindex = var1.readInt32();
            } else if (var5 == 40) {
               var2.gameworld = var1.readInt32();
            } else if (var5 == 48) {
               var2.gchannel = var1.readInt32();
            } else if (var5 == 56) {
               var2.packetid = var1.readInt32();
            } else if (var5 == 64) {
               var2.verifyguid = var1.readUInt64();
            } else if (var5 == 74) {
               var2.clientIP = var1.readString();
            } else if (var5 == 82) {
               var2.packetmsg = var1.readBytes().toByteArray();
            } else if (var5 == 90) {
               Codec var10 = ProtobufProxy.create(PT_USER_INFO_VERIFICATION.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.characinfos == null) {
                  var2.characinfos = new ArrayList();
               }

               var2.characinfos.add((PT_USER_INFO_VERIFICATION)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 98) {
               Codec var11 = ProtobufProxy.create(PT_MAP_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var12 = var1.readRawVarint32();
               int var13 = var1.pushLimit(var12);
               var2.map = (PT_MAP_INFO)var11.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var13);
            } else if (var5 == 104) {
               var2.featureswitch = var1.readUInt64();
            } else if (var5 == 114) {
               var2.dungeontypeswitch = var1.readBytes().toByteArray();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_VERIFICATION_SIMPLE.class);
         return this.descriptor = var1;
      }
   }
}
