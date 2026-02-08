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

public class PT_APC_CHARACTER$$JProtoBufClass implements Codec<PT_APC_CHARACTER>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_APC_CHARACTER var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_APC_CHARACTER decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_APC_CHARACTER var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var18 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var18);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.hp)) {
         Integer var19 = var1.hp;
         var2 += CodedOutputStream.computeInt32Size(2, var19);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.mp)) {
         Integer var20 = var1.mp;
         var2 += CodedOutputStream.computeInt32Size(3, var20);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var21 = var1.job;
         var2 += CodedOutputStream.computeInt32Size(4, var21);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var22 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(5, var22);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.exp)) {
         Integer var23 = var1.exp;
         var2 += CodedOutputStream.computeInt32Size(6, var23);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var24 = var1.growtype;
         var2 += CodedOutputStream.computeInt32Size(7, var24);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var25 = var1.secondgrowtype;
         var2 += CodedOutputStream.computeInt32Size(8, var25);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var26 = var1.name;
         var2 += CodedOutputStream.computeStringSize(9, var26);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.equiplist)) {
         PT_EQUIP_LIST var27 = var1.equiplist;
         var2 += CodedConstant.computeSize(10, var27, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.skilllist)) {
         PT_SKILLS var28 = var1.skilllist;
         var2 += CodedConstant.computeSize(11, var28, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.ownerguid)) {
         Long var29 = var1.ownerguid;
         var2 += CodedOutputStream.computeUInt64Size(12, var29);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.teamtype)) {
         Integer var30 = var1.teamtype;
         var2 += CodedOutputStream.computeInt32Size(13, var30);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.objectguid)) {
         Integer var31 = var1.objectguid;
         var2 += CodedOutputStream.computeInt32Size(14, var31);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.equipscore)) {
         Integer var32 = var1.equipscore;
         var2 += CodedOutputStream.computeInt32Size(15, var32);
      }

      return var2;
   }

   public void doWriteTo(PT_APC_CHARACTER var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var18 = var1.charguid;
         if (var18 != null) {
            var2.writeUInt64(1, var18);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.hp)) {
         Integer var19 = var1.hp;
         if (var19 != null) {
            var2.writeInt32(2, var19);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.mp)) {
         Integer var20 = var1.mp;
         if (var20 != null) {
            var2.writeInt32(3, var20);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var21 = var1.job;
         if (var21 != null) {
            var2.writeInt32(4, var21);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var22 = var1.level;
         if (var22 != null) {
            var2.writeInt32(5, var22);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.exp)) {
         Integer var23 = var1.exp;
         if (var23 != null) {
            var2.writeInt32(6, var23);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var24 = var1.growtype;
         if (var24 != null) {
            var2.writeInt32(7, var24);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var25 = var1.secondgrowtype;
         if (var25 != null) {
            var2.writeInt32(8, var25);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var26 = var1.name;
         if (var26 != null) {
            var2.writeString(9, var26);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.equiplist)) {
         PT_EQUIP_LIST var27 = var1.equiplist;
         if (var27 != null) {
            CodedConstant.writeObject(var2, 10, FieldType.OBJECT, var27, false);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.skilllist)) {
         PT_SKILLS var28 = var1.skilllist;
         if (var28 != null) {
            CodedConstant.writeObject(var2, 11, FieldType.OBJECT, var28, false);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.ownerguid)) {
         Long var29 = var1.ownerguid;
         if (var29 != null) {
            var2.writeUInt64(12, var29);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.teamtype)) {
         Integer var30 = var1.teamtype;
         if (var30 != null) {
            var2.writeInt32(13, var30);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.objectguid)) {
         Integer var31 = var1.objectguid;
         if (var31 != null) {
            var2.writeInt32(14, var31);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.equipscore)) {
         Integer var32 = var1.equipscore;
         if (var32 != null) {
            var2.writeInt32(15, var32);
         }
      }

   }

   public void writeTo(PT_APC_CHARACTER var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_APC_CHARACTER readFrom(CodedInputStream var1) throws IOException {
      PT_APC_CHARACTER var2 = new PT_APC_CHARACTER();

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
               var2.hp = var1.readInt32();
            } else if (var5 == 24) {
               var2.mp = var1.readInt32();
            } else if (var5 == 32) {
               var2.job = var1.readInt32();
            } else if (var5 == 40) {
               var2.level = var1.readInt32();
            } else if (var5 == 48) {
               var2.exp = var1.readInt32();
            } else if (var5 == 56) {
               var2.growtype = var1.readInt32();
            } else if (var5 == 64) {
               var2.secondgrowtype = var1.readInt32();
            } else if (var5 == 74) {
               var2.name = var1.readString();
            } else if (var5 == 82) {
               Codec var10 = ProtobufProxy.create(PT_EQUIP_LIST.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.equiplist = (PT_EQUIP_LIST)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 90) {
               Codec var11 = ProtobufProxy.create(PT_SKILLS.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var12 = var1.readRawVarint32();
               int var13 = var1.pushLimit(var12);
               var2.skilllist = (PT_SKILLS)var11.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var13);
            } else if (var5 == 96) {
               var2.ownerguid = var1.readUInt64();
            } else if (var5 == 104) {
               var2.teamtype = var1.readInt32();
            } else if (var5 == 112) {
               var2.objectguid = var1.readInt32();
            } else if (var5 == 120) {
               var2.equipscore = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_APC_CHARACTER.class);
         return this.descriptor = var1;
      }
   }
}
