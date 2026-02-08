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

public class PT_PVP_GLORY_RESULT$$JProtoBufClass implements Codec<PT_PVP_GLORY_RESULT>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_PVP_GLORY_RESULT var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_PVP_GLORY_RESULT decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_PVP_GLORY_RESULT var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.completedtypes)) {
         List var10 = var1.completedtypes;
         var2 += CodedConstant.computeListSize(1, var10, FieldType.INT32, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.isidlegame)) {
         Boolean var11 = var1.isidlegame;
         var2 += CodedOutputStream.computeBoolSize(2, var11);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.isstarprotect)) {
         Boolean var12 = var1.isstarprotect;
         var2 += CodedOutputStream.computeBoolSize(3, var12);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.isstaradditional)) {
         Boolean var13 = var1.isstaradditional;
         var2 += CodedOutputStream.computeBoolSize(4, var13);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.glorypoint)) {
         Integer var14 = var1.glorypoint;
         var2 += CodedOutputStream.computeInt32Size(5, var14);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.gainglorypoint)) {
         Integer var15 = var1.gainglorypoint;
         var2 += CodedOutputStream.computeInt32Size(6, var15);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.stagegainglorypoint)) {
         Integer var16 = var1.stagegainglorypoint;
         var2 += CodedOutputStream.computeInt32Size(7, var16);
      }

      return var2;
   }

   public void doWriteTo(PT_PVP_GLORY_RESULT var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.completedtypes)) {
         List var10 = var1.completedtypes;
         if (var10 != null) {
            CodedConstant.writeToList(var2, 1, FieldType.INT32, var10, true);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.isidlegame)) {
         Boolean var11 = var1.isidlegame;
         if (var11 != null) {
            var2.writeBool(2, var11);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.isstarprotect)) {
         Boolean var12 = var1.isstarprotect;
         if (var12 != null) {
            var2.writeBool(3, var12);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.isstaradditional)) {
         Boolean var13 = var1.isstaradditional;
         if (var13 != null) {
            var2.writeBool(4, var13);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.glorypoint)) {
         Integer var14 = var1.glorypoint;
         if (var14 != null) {
            var2.writeInt32(5, var14);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.gainglorypoint)) {
         Integer var15 = var1.gainglorypoint;
         if (var15 != null) {
            var2.writeInt32(6, var15);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.stagegainglorypoint)) {
         Integer var16 = var1.stagegainglorypoint;
         if (var16 != null) {
            var2.writeInt32(7, var16);
         }
      }

   }

   public void writeTo(PT_PVP_GLORY_RESULT var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_PVP_GLORY_RESULT readFrom(CodedInputStream var1) throws IOException {
      PT_PVP_GLORY_RESULT var2 = new PT_PVP_GLORY_RESULT();
      var2.completedtypes = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               if (var2.completedtypes == null) {
                  var2.completedtypes = new ArrayList();
               }

               var2.completedtypes.add(var1.readInt32());
            } else if (var5 != 10) {
               if (var5 == 16) {
                  var2.isidlegame = var1.readBool();
               } else if (var5 == 24) {
                  var2.isstarprotect = var1.readBool();
               } else if (var5 == 32) {
                  var2.isstaradditional = var1.readBool();
               } else if (var5 == 40) {
                  var2.glorypoint = var1.readInt32();
               } else if (var5 == 48) {
                  var2.gainglorypoint = var1.readInt32();
               } else if (var5 == 56) {
                  var2.stagegainglorypoint = var1.readInt32();
               } else {
                  var1.skipField(var5);
               }
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.completedtypes == null) {
                  var2.completedtypes = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.completedtypes.add(var1.readInt32());
               }

               var1.popLimit(var7);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_PVP_GLORY_RESULT.class);
         return this.descriptor = var1;
      }
   }
}
