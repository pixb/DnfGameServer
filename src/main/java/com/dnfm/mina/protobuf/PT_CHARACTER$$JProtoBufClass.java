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

public class PT_CHARACTER$$JProtoBufClass implements Codec<PT_CHARACTER>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_CHARACTER var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_CHARACTER decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_CHARACTER var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var19 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var19);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.lastlogout)) {
         Long var20 = var1.lastlogout;
         var2 += CodedOutputStream.computeUInt64Size(2, var20);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var21 = var1.growtype;
         var2 += CodedOutputStream.computeInt32Size(3, var21);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.secgrowtype)) {
         Integer var22 = var1.secgrowtype;
         var2 += CodedOutputStream.computeInt32Size(4, var22);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var23 = var1.job;
         var2 += CodedOutputStream.computeInt32Size(5, var23);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var24 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(6, var24);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var25 = var1.name;
         var2 += CodedOutputStream.computeStringSize(7, var25);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.fatigue)) {
         Integer var26 = var1.fatigue;
         var2 += CodedOutputStream.computeInt32Size(8, var26);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.equipscore)) {
         Integer var27 = var1.equipscore;
         var2 += CodedOutputStream.computeInt32Size(9, var27);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.characterframe)) {
         Integer var28 = var1.characterframe;
         var2 += CodedOutputStream.computeInt32Size(10, var28);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.equips)) {
         PT_CHARACTER_EQUIP_ONLY_INDEX var29 = var1.equips;
         var2 += CodedConstant.computeSize(11, var29, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.avatarVisibleFlags)) {
         Integer var30 = var1.avatarVisibleFlags;
         var2 += CodedOutputStream.computeUInt32Size(12, var30);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.deletionstatus)) {
         Integer var31 = var1.deletionstatus;
         var2 += CodedOutputStream.computeInt32Size(13, var31);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.deletiontime)) {
         Long var32 = var1.deletiontime;
         var2 += CodedOutputStream.computeInt64Size(14, var32);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.createtime)) {
         Long var33 = var1.createtime;
         var2 += CodedOutputStream.computeInt64Size(15, var33);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.changename)) {
         Boolean var34 = var1.changename;
         var2 += CodedOutputStream.computeBoolSize(16, var34);
      }

      return var2;
   }

   public void doWriteTo(PT_CHARACTER var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var19 = var1.charguid;
         if (var19 != null) {
            var2.writeUInt64(1, var19);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.lastlogout)) {
         Long var20 = var1.lastlogout;
         if (var20 != null) {
            var2.writeUInt64(2, var20);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var21 = var1.growtype;
         if (var21 != null) {
            var2.writeInt32(3, var21);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.secgrowtype)) {
         Integer var22 = var1.secgrowtype;
         if (var22 != null) {
            var2.writeInt32(4, var22);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var23 = var1.job;
         if (var23 != null) {
            var2.writeInt32(5, var23);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var24 = var1.level;
         if (var24 != null) {
            var2.writeInt32(6, var24);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var25 = var1.name;
         if (var25 != null) {
            var2.writeString(7, var25);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.fatigue)) {
         Integer var26 = var1.fatigue;
         if (var26 != null) {
            var2.writeInt32(8, var26);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.equipscore)) {
         Integer var27 = var1.equipscore;
         if (var27 != null) {
            var2.writeInt32(9, var27);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.characterframe)) {
         Integer var28 = var1.characterframe;
         if (var28 != null) {
            var2.writeInt32(10, var28);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.equips)) {
         PT_CHARACTER_EQUIP_ONLY_INDEX var29 = var1.equips;
         if (var29 != null) {
            CodedConstant.writeObject(var2, 11, FieldType.OBJECT, var29, false);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.avatarVisibleFlags)) {
         Integer var30 = var1.avatarVisibleFlags;
         if (var30 != null) {
            var2.writeUInt32(12, var30);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.deletionstatus)) {
         Integer var31 = var1.deletionstatus;
         if (var31 != null) {
            var2.writeInt32(13, var31);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.deletiontime)) {
         Long var32 = var1.deletiontime;
         if (var32 != null) {
            var2.writeInt64(14, var32);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.createtime)) {
         Long var33 = var1.createtime;
         if (var33 != null) {
            var2.writeInt64(15, var33);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.changename)) {
         Boolean var34 = var1.changename;
         if (var34 != null) {
            var2.writeBool(16, var34);
         }
      }

   }

   public void writeTo(PT_CHARACTER var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_CHARACTER readFrom(CodedInputStream var1) throws IOException {
      PT_CHARACTER var2 = new PT_CHARACTER();

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
               var2.lastlogout = var1.readUInt64();
            } else if (var5 == 24) {
               var2.growtype = var1.readInt32();
            } else if (var5 == 32) {
               var2.secgrowtype = var1.readInt32();
            } else if (var5 == 40) {
               var2.job = var1.readInt32();
            } else if (var5 == 48) {
               var2.level = var1.readInt32();
            } else if (var5 == 58) {
               var2.name = var1.readString();
            } else if (var5 == 64) {
               var2.fatigue = var1.readInt32();
            } else if (var5 == 72) {
               var2.equipscore = var1.readInt32();
            } else if (var5 == 80) {
               var2.characterframe = var1.readInt32();
            } else if (var5 == 90) {
               Codec var10 = ProtobufProxy.create(PT_CHARACTER_EQUIP_ONLY_INDEX.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.equips = (PT_CHARACTER_EQUIP_ONLY_INDEX)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 96) {
               var2.avatarVisibleFlags = var1.readUInt32();
            } else if (var5 == 104) {
               var2.deletionstatus = var1.readInt32();
            } else if (var5 == 112) {
               var2.deletiontime = var1.readInt64();
            } else if (var5 == 120) {
               var2.createtime = var1.readInt64();
            } else if (var5 == 128) {
               var2.changename = var1.readBool();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_CHARACTER.class);
         return this.descriptor = var1;
      }
   }
}
