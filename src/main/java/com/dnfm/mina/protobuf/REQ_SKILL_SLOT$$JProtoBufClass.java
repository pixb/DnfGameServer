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

public class REQ_SKILL_SLOT$$JProtoBufClass implements Codec<REQ_SKILL_SLOT>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_SKILL_SLOT var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_SKILL_SLOT decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_SKILL_SLOT var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var9 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(1, var9);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.buff)) {
         List var10 = var1.buff;
         var2 += CodedConstant.computeListSize(2, var10, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.active)) {
         List var11 = var1.active;
         var2 += CodedConstant.computeListSize(3, var11, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.combo)) {
         List var12 = var1.combo;
         var2 += CodedConstant.computeListSize(4, var12, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.keyboardmatching)) {
         List var13 = var1.keyboardmatching;
         var2 += CodedConstant.computeListSize(5, var13, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.padmatching)) {
         List var14 = var1.padmatching;
         var2 += CodedConstant.computeListSize(6, var14, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(REQ_SKILL_SLOT var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var9 = var1.type;
         if (var9 != null) {
            var2.writeInt32(1, var9);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.buff)) {
         List var10 = var1.buff;
         if (var10 != null) {
            CodedConstant.writeToList(var2, 2, FieldType.OBJECT, var10, false);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.active)) {
         List var11 = var1.active;
         if (var11 != null) {
            CodedConstant.writeToList(var2, 3, FieldType.OBJECT, var11, false);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.combo)) {
         List var12 = var1.combo;
         if (var12 != null) {
            CodedConstant.writeToList(var2, 4, FieldType.OBJECT, var12, false);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.keyboardmatching)) {
         List var13 = var1.keyboardmatching;
         if (var13 != null) {
            CodedConstant.writeToList(var2, 5, FieldType.OBJECT, var13, false);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.padmatching)) {
         List var14 = var1.padmatching;
         if (var14 != null) {
            CodedConstant.writeToList(var2, 6, FieldType.OBJECT, var14, false);
         }
      }

   }

   public void writeTo(REQ_SKILL_SLOT var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_SKILL_SLOT readFrom(CodedInputStream var1) throws IOException {
      REQ_SKILL_SLOT var2 = new REQ_SKILL_SLOT();
      var2.buff = new ArrayList();
      var2.active = new ArrayList();
      var2.combo = new ArrayList();
      var2.keyboardmatching = new ArrayList();
      var2.padmatching = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.type = var1.readInt32();
            } else if (var5 == 18) {
               Codec var10 = ProtobufProxy.create(PT_SKILL_SLOT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.buff == null) {
                  var2.buff = new ArrayList();
               }

               var2.buff.add((PT_SKILL_SLOT)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 26) {
               Codec var11 = ProtobufProxy.create(PT_SKILL_SLOT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var15 = var1.readRawVarint32();
               int var19 = var1.pushLimit(var15);
               if (var2.active == null) {
                  var2.active = new ArrayList();
               }

               var2.active.add((PT_SKILL_SLOT)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var19);
            } else if (var5 == 34) {
               Codec var12 = ProtobufProxy.create(PT_SKILL_SLOT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var16 = var1.readRawVarint32();
               int var20 = var1.pushLimit(var16);
               if (var2.combo == null) {
                  var2.combo = new ArrayList();
               }

               var2.combo.add((PT_SKILL_SLOT)var12.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var20);
            } else if (var5 == 42) {
               Codec var13 = ProtobufProxy.create(PT_SKILL_SLOT_MATCHING.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var17 = var1.readRawVarint32();
               int var21 = var1.pushLimit(var17);
               if (var2.keyboardmatching == null) {
                  var2.keyboardmatching = new ArrayList();
               }

               var2.keyboardmatching.add((PT_SKILL_SLOT_MATCHING)var13.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var21);
            } else if (var5 == 50) {
               Codec var14 = ProtobufProxy.create(PT_SKILL_SLOT_MATCHING.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var18 = var1.readRawVarint32();
               int var22 = var1.pushLimit(var18);
               if (var2.padmatching == null) {
                  var2.padmatching = new ArrayList();
               }

               var2.padmatching.add((PT_SKILL_SLOT_MATCHING)var14.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var22);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_SKILL_SLOT.class);
         return this.descriptor = var1;
      }
   }
}
