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

public class NOTIFY_BATTLELEAGUE_TEAM_RANDOM_CARD$$JProtoBufClass implements Codec<NOTIFY_BATTLELEAGUE_TEAM_RANDOM_CARD>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(NOTIFY_BATTLELEAGUE_TEAM_RANDOM_CARD var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public NOTIFY_BATTLELEAGUE_TEAM_RANDOM_CARD decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(NOTIFY_BATTLELEAGUE_TEAM_RANDOM_CARD var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.teamtype)) {
         ENUM_TEAM.T var8 = var1.teamtype;
         var2 += CodedOutputStream.computeEnumSize(1, ((Enum)var8).ordinal());
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.itemindex)) {
         Integer var9 = var1.itemindex;
         var2 += CodedOutputStream.computeInt32Size(2, var9);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.buffindex)) {
         Integer var10 = var1.buffindex;
         var2 += CodedOutputStream.computeInt32Size(3, var10);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.monsterguid)) {
         Long var11 = var1.monsterguid;
         var2 += CodedOutputStream.computeUInt64Size(4, var11);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.buff)) {
         PT_BATTLELEAGUE_BUFF var12 = var1.buff;
         var2 += CodedConstant.computeSize(5, var12, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      return var2;
   }

   public void doWriteTo(NOTIFY_BATTLELEAGUE_TEAM_RANDOM_CARD var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.teamtype)) {
         ENUM_TEAM.T var8 = var1.teamtype;
         if (var8 != null) {
            var2.writeEnum(1, ((Enum)var8).ordinal());
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.itemindex)) {
         Integer var9 = var1.itemindex;
         if (var9 != null) {
            var2.writeInt32(2, var9);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.buffindex)) {
         Integer var10 = var1.buffindex;
         if (var10 != null) {
            var2.writeInt32(3, var10);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.monsterguid)) {
         Long var11 = var1.monsterguid;
         if (var11 != null) {
            var2.writeUInt64(4, var11);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.buff)) {
         PT_BATTLELEAGUE_BUFF var12 = var1.buff;
         if (var12 != null) {
            CodedConstant.writeObject(var2, 5, FieldType.OBJECT, var12, false);
         }
      }

   }

   public void writeTo(NOTIFY_BATTLELEAGUE_TEAM_RANDOM_CARD var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public NOTIFY_BATTLELEAGUE_TEAM_RANDOM_CARD readFrom(CodedInputStream var1) throws IOException {
      NOTIFY_BATTLELEAGUE_TEAM_RANDOM_CARD var2 = new NOTIFY_BATTLELEAGUE_TEAM_RANDOM_CARD();
      var2.teamtype = (ENUM_TEAM.T)CodedConstant.getEnumValue(ENUM_TEAM.T.class, ENUM_TEAM.T.values()[0].name());

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.teamtype = (ENUM_TEAM.T)CodedConstant.getEnumValue(ENUM_TEAM.T.class, CodedConstant.getEnumName(ENUM_TEAM.T.values(), var1.readEnum()));
            } else if (var5 == 16) {
               var2.itemindex = var1.readInt32();
            } else if (var5 == 24) {
               var2.buffindex = var1.readInt32();
            } else if (var5 == 32) {
               var2.monsterguid = var1.readUInt64();
            } else if (var5 == 42) {
               Codec var10 = ProtobufProxy.create(PT_BATTLELEAGUE_BUFF.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.buff = (PT_BATTLELEAGUE_BUFF)var10.readFrom(var1);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(NOTIFY_BATTLELEAGUE_TEAM_RANDOM_CARD.class);
         return this.descriptor = var1;
      }
   }
}
