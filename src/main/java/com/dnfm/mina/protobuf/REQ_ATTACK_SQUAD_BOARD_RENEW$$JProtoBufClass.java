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

public class REQ_ATTACK_SQUAD_BOARD_RENEW$$JProtoBufClass implements Codec<REQ_ATTACK_SQUAD_BOARD_RENEW>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_ATTACK_SQUAD_BOARD_RENEW var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_ATTACK_SQUAD_BOARD_RENEW decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_ATTACK_SQUAD_BOARD_RENEW var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var6 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(1, var6);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.rpguids)) {
         List var7 = var1.rpguids;
         var2 += CodedConstant.computeListSize(2, var7, FieldType.UINT64, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var8 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(3, var8);
      }

      return var2;
   }

   public void doWriteTo(REQ_ATTACK_SQUAD_BOARD_RENEW var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var6 = var1.index;
         if (var6 != null) {
            var2.writeInt32(1, var6);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.rpguids)) {
         List var7 = var1.rpguids;
         if (var7 != null) {
            CodedConstant.writeToList(var2, 2, FieldType.UINT64, var7, true);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var8 = var1.type;
         if (var8 != null) {
            var2.writeInt32(3, var8);
         }
      }

   }

   public void writeTo(REQ_ATTACK_SQUAD_BOARD_RENEW var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_ATTACK_SQUAD_BOARD_RENEW readFrom(CodedInputStream var1) throws IOException {
      REQ_ATTACK_SQUAD_BOARD_RENEW var2 = new REQ_ATTACK_SQUAD_BOARD_RENEW();
      var2.rpguids = new ArrayList();

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
               if (var2.rpguids == null) {
                  var2.rpguids = new ArrayList();
               }

               var2.rpguids.add(var1.readUInt64());
            } else if (var5 != 18) {
               if (var5 == 24) {
                  var2.type = var1.readInt32();
               } else {
                  var1.skipField(var5);
               }
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.rpguids == null) {
                  var2.rpguids = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.rpguids.add(var1.readUInt64());
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_ATTACK_SQUAD_BOARD_RENEW.class);
         return this.descriptor = var1;
      }
   }
}
