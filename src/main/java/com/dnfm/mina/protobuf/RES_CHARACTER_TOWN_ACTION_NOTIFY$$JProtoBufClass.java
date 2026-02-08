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

public class RES_CHARACTER_TOWN_ACTION_NOTIFY$$JProtoBufClass implements Codec<RES_CHARACTER_TOWN_ACTION_NOTIFY>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_CHARACTER_TOWN_ACTION_NOTIFY var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_CHARACTER_TOWN_ACTION_NOTIFY decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_CHARACTER_TOWN_ACTION_NOTIFY var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.targetcharguid)) {
         Long var6 = var1.targetcharguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var6);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.actiontype)) {
         Integer var7 = var1.actiontype;
         var2 += CodedOutputStream.computeInt32Size(2, var7);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.actionparams)) {
         List var8 = var1.actionparams;
         var2 += CodedConstant.computeListSize(3, var8, FieldType.UINT64, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      return var2;
   }

   public void doWriteTo(RES_CHARACTER_TOWN_ACTION_NOTIFY var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.targetcharguid)) {
         Long var6 = var1.targetcharguid;
         if (var6 != null) {
            var2.writeUInt64(1, var6);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.actiontype)) {
         Integer var7 = var1.actiontype;
         if (var7 != null) {
            var2.writeInt32(2, var7);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.actionparams)) {
         List var8 = var1.actionparams;
         if (var8 != null) {
            CodedConstant.writeToList(var2, 3, FieldType.UINT64, var8, true);
         }
      }

   }

   public void writeTo(RES_CHARACTER_TOWN_ACTION_NOTIFY var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_CHARACTER_TOWN_ACTION_NOTIFY readFrom(CodedInputStream var1) throws IOException {
      RES_CHARACTER_TOWN_ACTION_NOTIFY var2 = new RES_CHARACTER_TOWN_ACTION_NOTIFY();
      var2.actionparams = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.targetcharguid = var1.readUInt64();
            } else if (var5 == 16) {
               var2.actiontype = var1.readInt32();
            } else if (var5 == 24) {
               if (var2.actionparams == null) {
                  var2.actionparams = new ArrayList();
               }

               var2.actionparams.add(var1.readUInt64());
            } else if (var5 != 26) {
               var1.skipField(var5);
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.actionparams == null) {
                  var2.actionparams = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.actionparams.add(var1.readUInt64());
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_CHARACTER_TOWN_ACTION_NOTIFY.class);
         return this.descriptor = var1;
      }
   }
}
