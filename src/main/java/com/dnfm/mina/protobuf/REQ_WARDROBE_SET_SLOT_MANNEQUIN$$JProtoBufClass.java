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

public class REQ_WARDROBE_SET_SLOT_MANNEQUIN$$JProtoBufClass implements Codec<REQ_WARDROBE_SET_SLOT_MANNEQUIN>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_WARDROBE_SET_SLOT_MANNEQUIN var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_WARDROBE_SET_SLOT_MANNEQUIN decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_WARDROBE_SET_SLOT_MANNEQUIN var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.beforeslots)) {
         List var5 = var1.beforeslots;
         var2 += CodedConstant.computeListSize(1, var5, FieldType.INT32, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.afterslots)) {
         List var6 = var1.afterslots;
         var2 += CodedConstant.computeListSize(2, var6, FieldType.INT32, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      return var2;
   }

   public void doWriteTo(REQ_WARDROBE_SET_SLOT_MANNEQUIN var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.beforeslots)) {
         List var5 = var1.beforeslots;
         if (var5 != null) {
            CodedConstant.writeToList(var2, 1, FieldType.INT32, var5, true);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.afterslots)) {
         List var6 = var1.afterslots;
         if (var6 != null) {
            CodedConstant.writeToList(var2, 2, FieldType.INT32, var6, true);
         }
      }

   }

   public void writeTo(REQ_WARDROBE_SET_SLOT_MANNEQUIN var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_WARDROBE_SET_SLOT_MANNEQUIN readFrom(CodedInputStream var1) throws IOException {
      REQ_WARDROBE_SET_SLOT_MANNEQUIN var2 = new REQ_WARDROBE_SET_SLOT_MANNEQUIN();
      var2.beforeslots = new ArrayList();
      var2.afterslots = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               if (var2.beforeslots == null) {
                  var2.beforeslots = new ArrayList();
               }

               var2.beforeslots.add(var1.readInt32());
            } else if (var5 == 10) {
               int var11 = var1.readRawVarint32();
               int var13 = var1.pushLimit(var11);
               if (var2.beforeslots == null) {
                  var2.beforeslots = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.beforeslots.add(var1.readInt32());
               }

               var1.popLimit(var13);
            } else if (var5 == 16) {
               if (var2.afterslots == null) {
                  var2.afterslots = new ArrayList();
               }

               var2.afterslots.add(var1.readInt32());
            } else if (var5 == 10) {
               int var10 = var1.readRawVarint32();
               int var12 = var1.pushLimit(var10);
               if (var2.beforeslots == null) {
                  var2.beforeslots = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.beforeslots.add(var1.readInt32());
               }

               var1.popLimit(var12);
            } else if (var5 != 18) {
               var1.skipField(var5);
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.afterslots == null) {
                  var2.afterslots = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.afterslots.add(var1.readInt32());
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_WARDROBE_SET_SLOT_MANNEQUIN.class);
         return this.descriptor = var1;
      }
   }
}
