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

public class PT_PHASE_INFO$$JProtoBufClass implements Codec<PT_PHASE_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_PHASE_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_PHASE_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_PHASE_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.starttime)) {
         Long var7 = var1.starttime;
         var2 += CodedOutputStream.computeInt64Size(1, var7);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.playtime)) {
         Long var8 = var1.playtime;
         var2 += CodedOutputStream.computeInt64Size(2, var8);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.status)) {
         ENUM_RAID_PHASE.T var9 = var1.status;
         var2 += CodedOutputStream.computeEnumSize(3, ((ENUM_RAID_PHASE.T)var9).value());
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.supernodes)) {
         List var10 = var1.supernodes;
         var2 += CodedConstant.computeListSize(4, var10, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(PT_PHASE_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.starttime)) {
         Long var7 = var1.starttime;
         if (var7 != null) {
            var2.writeInt64(1, var7);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.playtime)) {
         Long var8 = var1.playtime;
         if (var8 != null) {
            var2.writeInt64(2, var8);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.status)) {
         ENUM_RAID_PHASE.T var9 = var1.status;
         if (var9 != null) {
            var2.writeEnum(3, ((ENUM_RAID_PHASE.T)var9).value());
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.supernodes)) {
         List var10 = var1.supernodes;
         if (var10 != null) {
            CodedConstant.writeToList(var2, 4, FieldType.OBJECT, var10, false);
         }
      }

   }

   public void writeTo(PT_PHASE_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_PHASE_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_PHASE_INFO var2 = new PT_PHASE_INFO();
      var2.supernodes = new ArrayList();
      var2.status = (ENUM_RAID_PHASE.T)CodedConstant.getEnumValue(ENUM_RAID_PHASE.T.class, ENUM_RAID_PHASE.T.values()[0].name());

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.starttime = var1.readInt64();
            } else if (var5 == 16) {
               var2.playtime = var1.readInt64();
            } else if (var5 == 24) {
               var2.status = (ENUM_RAID_PHASE.T)CodedConstant.getEnumValue(ENUM_RAID_PHASE.T.class, CodedConstant.getEnumName(ENUM_RAID_PHASE.T.values(), var1.readEnum()));
            } else if (var5 == 34) {
               Codec var10 = ProtobufProxy.create(PT_RAID_NODE_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.supernodes == null) {
                  var2.supernodes = new ArrayList();
               }

               var2.supernodes.add((PT_RAID_NODE_INFO)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_PHASE_INFO.class);
         return this.descriptor = var1;
      }
   }
}
