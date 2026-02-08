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

public class PT_CREATURE$$JProtoBufClass implements Codec<PT_CREATURE>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_CREATURE var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_CREATURE decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_CREATURE var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var15 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(1, var15);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var16 = var1.count;
         var2 += CodedOutputStream.computeInt32Size(2, var16);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var17 = var1.guid;
         var2 += CodedOutputStream.computeUInt64Size(3, var17);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.slot)) {
         Integer var18 = var1.slot;
         var2 += CodedOutputStream.computeInt32Size(4, var18);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.exp)) {
         Integer var19 = var1.exp;
         var2 += CodedOutputStream.computeInt32Size(5, var19);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var20 = var1.grade;
         var2 += CodedOutputStream.computeInt32Size(6, var20);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.scount)) {
         Integer var21 = var1.scount;
         var2 += CodedOutputStream.computeInt32Size(7, var21);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.tcount)) {
         Integer var22 = var1.tcount;
         var2 += CodedOutputStream.computeInt32Size(8, var22);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.skill)) {
         List var23 = var1.skill;
         var2 += CodedConstant.computeListSize(9, var23, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.errand)) {
         Integer var24 = var1.errand;
         var2 += CodedOutputStream.computeInt32Size(10, var24);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.sindex)) {
         Integer var25 = var1.sindex;
         var2 += CodedOutputStream.computeInt32Size(11, var25);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.locked)) {
         Boolean var26 = var1.locked;
         var2 += CodedOutputStream.computeBoolSize(12, var26);
      }

      return var2;
   }

   public void doWriteTo(PT_CREATURE var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var15 = var1.index;
         if (var15 != null) {
            var2.writeInt32(1, var15);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var16 = var1.count;
         if (var16 != null) {
            var2.writeInt32(2, var16);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var17 = var1.guid;
         if (var17 != null) {
            var2.writeUInt64(3, var17);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.slot)) {
         Integer var18 = var1.slot;
         if (var18 != null) {
            var2.writeInt32(4, var18);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.exp)) {
         Integer var19 = var1.exp;
         if (var19 != null) {
            var2.writeInt32(5, var19);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var20 = var1.grade;
         if (var20 != null) {
            var2.writeInt32(6, var20);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.scount)) {
         Integer var21 = var1.scount;
         if (var21 != null) {
            var2.writeInt32(7, var21);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.tcount)) {
         Integer var22 = var1.tcount;
         if (var22 != null) {
            var2.writeInt32(8, var22);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.skill)) {
         List var23 = var1.skill;
         if (var23 != null) {
            CodedConstant.writeToList(var2, 9, FieldType.OBJECT, var23, false);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.errand)) {
         Integer var24 = var1.errand;
         if (var24 != null) {
            var2.writeInt32(10, var24);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.sindex)) {
         Integer var25 = var1.sindex;
         if (var25 != null) {
            var2.writeInt32(11, var25);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.locked)) {
         Boolean var26 = var1.locked;
         if (var26 != null) {
            var2.writeBool(12, var26);
         }
      }

   }

   public void writeTo(PT_CREATURE var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_CREATURE readFrom(CodedInputStream var1) throws IOException {
      PT_CREATURE var2 = new PT_CREATURE();
      var2.skill = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.index = var1.readInt32();
            } else if (var5 == 16) {
               var2.count = var1.readInt32();
            } else if (var5 == 24) {
               var2.guid = var1.readUInt64();
            } else if (var5 == 32) {
               var2.slot = var1.readInt32();
            } else if (var5 == 40) {
               var2.exp = var1.readInt32();
            } else if (var5 == 48) {
               var2.grade = var1.readInt32();
            } else if (var5 == 56) {
               var2.scount = var1.readInt32();
            } else if (var5 == 64) {
               var2.tcount = var1.readInt32();
            } else if (var5 == 74) {
               Codec var10 = ProtobufProxy.create(PT_CREATURE_SKILL.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.skill == null) {
                  var2.skill = new ArrayList();
               }

               var2.skill.add((PT_CREATURE_SKILL)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 80) {
               var2.errand = var1.readInt32();
            } else if (var5 == 88) {
               var2.sindex = var1.readInt32();
            } else if (var5 == 96) {
               var2.locked = var1.readBool();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_CREATURE.class);
         return this.descriptor = var1;
      }
   }
}
