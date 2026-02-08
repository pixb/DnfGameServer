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

public class PT_ATTACK_SQUAD_MEMBER_INFO$$JProtoBufClass implements Codec<PT_ATTACK_SQUAD_MEMBER_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_ATTACK_SQUAD_MEMBER_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_ATTACK_SQUAD_MEMBER_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_ATTACK_SQUAD_MEMBER_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var9 = var1.partyguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var9);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.partyindex)) {
         Integer var10 = var1.partyindex;
         var2 += CodedOutputStream.computeInt32Size(2, var10);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.partyslot)) {
         Integer var11 = var1.partyslot;
         var2 += CodedOutputStream.computeInt32Size(3, var11);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.user)) {
         PT_GROUP_MEMBER var12 = var1.user;
         var2 += CodedConstant.computeSize(4, var12, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.disconnecttime)) {
         Long var13 = var1.disconnecttime;
         var2 += CodedOutputStream.computeInt64Size(5, var13);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.rewardconditioncount)) {
         Integer var14 = var1.rewardconditioncount;
         var2 += CodedOutputStream.computeInt32Size(6, var14);
      }

      return var2;
   }

   public void doWriteTo(PT_ATTACK_SQUAD_MEMBER_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var9 = var1.partyguid;
         if (var9 != null) {
            var2.writeUInt64(1, var9);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.partyindex)) {
         Integer var10 = var1.partyindex;
         if (var10 != null) {
            var2.writeInt32(2, var10);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.partyslot)) {
         Integer var11 = var1.partyslot;
         if (var11 != null) {
            var2.writeInt32(3, var11);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.user)) {
         PT_GROUP_MEMBER var12 = var1.user;
         if (var12 != null) {
            CodedConstant.writeObject(var2, 4, FieldType.OBJECT, var12, false);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.disconnecttime)) {
         Long var13 = var1.disconnecttime;
         if (var13 != null) {
            var2.writeInt64(5, var13);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.rewardconditioncount)) {
         Integer var14 = var1.rewardconditioncount;
         if (var14 != null) {
            var2.writeInt32(6, var14);
         }
      }

   }

   public void writeTo(PT_ATTACK_SQUAD_MEMBER_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_ATTACK_SQUAD_MEMBER_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_ATTACK_SQUAD_MEMBER_INFO var2 = new PT_ATTACK_SQUAD_MEMBER_INFO();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.partyguid = var1.readUInt64();
            } else if (var5 == 16) {
               var2.partyindex = var1.readInt32();
            } else if (var5 == 24) {
               var2.partyslot = var1.readInt32();
            } else if (var5 == 34) {
               Codec var10 = ProtobufProxy.create(PT_GROUP_MEMBER.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.user = (PT_GROUP_MEMBER)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 40) {
               var2.disconnecttime = var1.readInt64();
            } else if (var5 == 48) {
               var2.rewardconditioncount = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_ATTACK_SQUAD_MEMBER_INFO.class);
         return this.descriptor = var1;
      }
   }
}
