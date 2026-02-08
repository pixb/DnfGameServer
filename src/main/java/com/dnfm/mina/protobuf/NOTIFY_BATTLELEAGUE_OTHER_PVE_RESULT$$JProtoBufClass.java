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

public class NOTIFY_BATTLELEAGUE_OTHER_PVE_RESULT$$JProtoBufClass implements Codec<NOTIFY_BATTLELEAGUE_OTHER_PVE_RESULT>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(NOTIFY_BATTLELEAGUE_OTHER_PVE_RESULT var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public NOTIFY_BATTLELEAGUE_OTHER_PVE_RESULT decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(NOTIFY_BATTLELEAGUE_OTHER_PVE_RESULT var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.battleleaguepverecord)) {
         List var5 = var1.battleleaguepverecord;
         var2 += CodedConstant.computeListSize(1, var5, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.hp)) {
         Integer var6 = var1.hp;
         var2 += CodedOutputStream.computeInt32Size(2, var6);
      }

      return var2;
   }

   public void doWriteTo(NOTIFY_BATTLELEAGUE_OTHER_PVE_RESULT var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.battleleaguepverecord)) {
         List var5 = var1.battleleaguepverecord;
         if (var5 != null) {
            CodedConstant.writeToList(var2, 1, FieldType.OBJECT, var5, false);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.hp)) {
         Integer var6 = var1.hp;
         if (var6 != null) {
            var2.writeInt32(2, var6);
         }
      }

   }

   public void writeTo(NOTIFY_BATTLELEAGUE_OTHER_PVE_RESULT var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public NOTIFY_BATTLELEAGUE_OTHER_PVE_RESULT readFrom(CodedInputStream var1) throws IOException {
      NOTIFY_BATTLELEAGUE_OTHER_PVE_RESULT var2 = new NOTIFY_BATTLELEAGUE_OTHER_PVE_RESULT();
      var2.battleleaguepverecord = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 10) {
               Codec var10 = ProtobufProxy.create(PT_BATTLELEAGUE_PVE_RECORD.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.battleleaguepverecord == null) {
                  var2.battleleaguepverecord = new ArrayList();
               }

               var2.battleleaguepverecord.add((PT_BATTLELEAGUE_PVE_RECORD)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 16) {
               var2.hp = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(NOTIFY_BATTLELEAGUE_OTHER_PVE_RESULT.class);
         return this.descriptor = var1;
      }
   }
}
