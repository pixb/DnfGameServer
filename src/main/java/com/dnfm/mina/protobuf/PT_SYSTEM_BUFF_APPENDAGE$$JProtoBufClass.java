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

public class PT_SYSTEM_BUFF_APPENDAGE$$JProtoBufClass implements Codec<PT_SYSTEM_BUFF_APPENDAGE>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_SYSTEM_BUFF_APPENDAGE var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_SYSTEM_BUFF_APPENDAGE decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_SYSTEM_BUFF_APPENDAGE var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var6 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(1, var6);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.endtime)) {
         Long var7 = var1.endtime;
         var2 += CodedOutputStream.computeInt64Size(2, var7);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.values)) {
         List var8 = var1.values;
         var2 += CodedConstant.computeListSize(3, var8, FieldType.INT32, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      return var2;
   }

   public void doWriteTo(PT_SYSTEM_BUFF_APPENDAGE var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var6 = var1.index;
         if (var6 != null) {
            var2.writeInt32(1, var6);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.endtime)) {
         Long var7 = var1.endtime;
         if (var7 != null) {
            var2.writeInt64(2, var7);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.values)) {
         List var8 = var1.values;
         if (var8 != null) {
            CodedConstant.writeToList(var2, 3, FieldType.INT32, var8, true);
         }
      }

   }

   public void writeTo(PT_SYSTEM_BUFF_APPENDAGE var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_SYSTEM_BUFF_APPENDAGE readFrom(CodedInputStream var1) throws IOException {
      PT_SYSTEM_BUFF_APPENDAGE var2 = new PT_SYSTEM_BUFF_APPENDAGE();
      var2.values = new ArrayList();

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
               var2.endtime = var1.readInt64();
            } else if (var5 == 24) {
               if (var2.values == null) {
                  var2.values = new ArrayList();
               }

               var2.values.add(var1.readInt32());
            } else if (var5 != 26) {
               var1.skipField(var5);
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.values == null) {
                  var2.values = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.values.add(var1.readInt32());
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_SYSTEM_BUFF_APPENDAGE.class);
         return this.descriptor = var1;
      }
   }
}
