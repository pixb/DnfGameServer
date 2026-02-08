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

public class REQ_RAID_SYNC_NODE_VARIABLE$$JProtoBufClass implements Codec<REQ_RAID_SYNC_NODE_VARIABLE>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_RAID_SYNC_NODE_VARIABLE var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_RAID_SYNC_NODE_VARIABLE decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_RAID_SYNC_NODE_VARIABLE var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.symbols)) {
         List var4 = var1.symbols;
         var2 += CodedConstant.computeListSize(1, var4, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(REQ_RAID_SYNC_NODE_VARIABLE var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.symbols)) {
         List var4 = var1.symbols;
         if (var4 != null) {
            CodedConstant.writeToList(var2, 1, FieldType.OBJECT, var4, false);
         }
      }

   }

   public void writeTo(REQ_RAID_SYNC_NODE_VARIABLE var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_RAID_SYNC_NODE_VARIABLE readFrom(CodedInputStream var1) throws IOException {
      REQ_RAID_SYNC_NODE_VARIABLE var2 = new REQ_RAID_SYNC_NODE_VARIABLE();
      var2.symbols = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 10) {
               Codec var10 = ProtobufProxy.create(PT_REQ_RAID_NODE_VARIABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.symbols == null) {
                  var2.symbols = new ArrayList();
               }

               var2.symbols.add((PT_REQ_RAID_NODE_VARIABLE)var10.readFrom(var1));
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_RAID_SYNC_NODE_VARIABLE.class);
         return this.descriptor = var1;
      }
   }
}
