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

public class PT_APC_INFO$$JProtoBufClass implements Codec<PT_APC_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_APC_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_APC_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_APC_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var15 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var15);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var16 = var1.name;
         var2 += CodedOutputStream.computeStringSize(2, var16);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var17 = var1.growtype;
         var2 += CodedOutputStream.computeInt32Size(3, var17);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var18 = var1.secondgrowtype;
         var2 += CodedOutputStream.computeInt32Size(4, var18);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var19 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(5, var19);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var20 = var1.job;
         var2 += CodedOutputStream.computeInt32Size(6, var20);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.equipscore)) {
         Integer var21 = var1.equipscore;
         var2 += CodedOutputStream.computeInt32Size(7, var21);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var22 = var1.count;
         var2 += CodedOutputStream.computeInt32Size(8, var22);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.date)) {
         Long var23 = var1.date;
         var2 += CodedOutputStream.computeInt64Size(9, var23);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.skilllist)) {
         List var24 = var1.skilllist;
         var2 += CodedConstant.computeListSize(10, var24, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.equiplist)) {
         PT_EQUIP_LIST var25 = var1.equiplist;
         var2 += CodedConstant.computeSize(11, var25, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.avatarlist)) {
         List var26 = var1.avatarlist;
         var2 += CodedConstant.computeListSize(12, var26, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(PT_APC_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var15 = var1.charguid;
         if (var15 != null) {
            var2.writeUInt64(1, var15);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var16 = var1.name;
         if (var16 != null) {
            var2.writeString(2, var16);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var17 = var1.growtype;
         if (var17 != null) {
            var2.writeInt32(3, var17);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var18 = var1.secondgrowtype;
         if (var18 != null) {
            var2.writeInt32(4, var18);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var19 = var1.level;
         if (var19 != null) {
            var2.writeInt32(5, var19);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var20 = var1.job;
         if (var20 != null) {
            var2.writeInt32(6, var20);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.equipscore)) {
         Integer var21 = var1.equipscore;
         if (var21 != null) {
            var2.writeInt32(7, var21);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var22 = var1.count;
         if (var22 != null) {
            var2.writeInt32(8, var22);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.date)) {
         Long var23 = var1.date;
         if (var23 != null) {
            var2.writeInt64(9, var23);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.skilllist)) {
         List var24 = var1.skilllist;
         if (var24 != null) {
            CodedConstant.writeToList(var2, 10, FieldType.OBJECT, var24, false);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.equiplist)) {
         PT_EQUIP_LIST var25 = var1.equiplist;
         if (var25 != null) {
            CodedConstant.writeObject(var2, 11, FieldType.OBJECT, var25, false);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.avatarlist)) {
         List var26 = var1.avatarlist;
         if (var26 != null) {
            CodedConstant.writeToList(var2, 12, FieldType.OBJECT, var26, false);
         }
      }

   }

   public void writeTo(PT_APC_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_APC_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_APC_INFO var2 = new PT_APC_INFO();
      var2.skilllist = new ArrayList();
      var2.avatarlist = new ArrayList();

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
            } else if (var5 == 18) {
               var2.name = var1.readString();
            } else if (var5 == 24) {
               var2.growtype = var1.readInt32();
            } else if (var5 == 32) {
               var2.secondgrowtype = var1.readInt32();
            } else if (var5 == 40) {
               var2.level = var1.readInt32();
            } else if (var5 == 48) {
               var2.job = var1.readInt32();
            } else if (var5 == 56) {
               var2.equipscore = var1.readInt32();
            } else if (var5 == 64) {
               var2.count = var1.readInt32();
            } else if (var5 == 72) {
               var2.date = var1.readInt64();
            } else if (var5 == 82) {
               Codec var10 = ProtobufProxy.create(PT_SKILL.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.skilllist == null) {
                  var2.skilllist = new ArrayList();
               }

               var2.skilllist.add((PT_SKILL)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 90) {
               Codec var11 = ProtobufProxy.create(PT_EQUIP_LIST.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var13 = var1.readRawVarint32();
               int var15 = var1.pushLimit(var13);
               var2.equiplist = (PT_EQUIP_LIST)var11.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var15);
            } else if (var5 == 98) {
               Codec var12 = ProtobufProxy.create(PT_AVATAR_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var14 = var1.readRawVarint32();
               int var16 = var1.pushLimit(var14);
               if (var2.avatarlist == null) {
                  var2.avatarlist = new ArrayList();
               }

               var2.avatarlist.add((PT_AVATAR_ITEM)var12.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var16);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_APC_INFO.class);
         return this.descriptor = var1;
      }
   }
}
