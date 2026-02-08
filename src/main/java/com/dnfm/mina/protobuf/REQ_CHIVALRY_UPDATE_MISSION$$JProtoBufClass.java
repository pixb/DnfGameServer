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

public class REQ_CHIVALRY_UPDATE_MISSION$$JProtoBufClass implements Codec<REQ_CHIVALRY_UPDATE_MISSION>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_CHIVALRY_UPDATE_MISSION var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_CHIVALRY_UPDATE_MISSION decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_CHIVALRY_UPDATE_MISSION var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var6 = var1.dungeonguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var6);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.clear)) {
         Boolean var7 = var1.clear;
         var2 += CodedOutputStream.computeBoolSize(2, var7);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.info)) {
         PT_CHIVALRY var8 = var1.info;
         var2 += CodedConstant.computeSize(3, var8, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      return var2;
   }

   public void doWriteTo(REQ_CHIVALRY_UPDATE_MISSION var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var6 = var1.dungeonguid;
         if (var6 != null) {
            var2.writeUInt64(1, var6);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.clear)) {
         Boolean var7 = var1.clear;
         if (var7 != null) {
            var2.writeBool(2, var7);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.info)) {
         PT_CHIVALRY var8 = var1.info;
         if (var8 != null) {
            CodedConstant.writeObject(var2, 3, FieldType.OBJECT, var8, false);
         }
      }

   }

   public void writeTo(REQ_CHIVALRY_UPDATE_MISSION var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_CHIVALRY_UPDATE_MISSION readFrom(CodedInputStream var1) throws IOException {
      REQ_CHIVALRY_UPDATE_MISSION var2 = new REQ_CHIVALRY_UPDATE_MISSION();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.dungeonguid = var1.readUInt64();
            } else if (var5 == 16) {
               var2.clear = var1.readBool();
            } else if (var5 == 26) {
               Codec var10 = ProtobufProxy.create(PT_CHIVALRY.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.info = (PT_CHIVALRY)var10.readFrom(var1);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_CHIVALRY_UPDATE_MISSION.class);
         return this.descriptor = var1;
      }
   }
}
