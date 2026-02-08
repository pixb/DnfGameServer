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

public class RES_SYSTEM_BUFF_APPENDAGE$$JProtoBufClass implements Codec<RES_SYSTEM_BUFF_APPENDAGE>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_SYSTEM_BUFF_APPENDAGE var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_SYSTEM_BUFF_APPENDAGE decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_SYSTEM_BUFF_APPENDAGE var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var10 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var10);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.time)) {
         Long var11 = var1.time;
         var2 += CodedOutputStream.computeInt64Size(2, var11);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.endtime)) {
         Long var12 = var1.endtime;
         var2 += CodedOutputStream.computeInt64Size(3, var12);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var13 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(4, var13);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var14 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(5, var14);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.values)) {
         List var15 = var1.values;
         var2 += CodedConstant.computeListSize(6, var15, FieldType.INT32, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.targetguid)) {
         Long var16 = var1.targetguid;
         var2 += CodedOutputStream.computeUInt64Size(7, var16);
      }

      return var2;
   }

   public void doWriteTo(RES_SYSTEM_BUFF_APPENDAGE var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var10 = var1.error;
         if (var10 != null) {
            var2.writeInt32(1, var10);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.time)) {
         Long var11 = var1.time;
         if (var11 != null) {
            var2.writeInt64(2, var11);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.endtime)) {
         Long var12 = var1.endtime;
         if (var12 != null) {
            var2.writeInt64(3, var12);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var13 = var1.type;
         if (var13 != null) {
            var2.writeInt32(4, var13);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var14 = var1.index;
         if (var14 != null) {
            var2.writeInt32(5, var14);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.values)) {
         List var15 = var1.values;
         if (var15 != null) {
            CodedConstant.writeToList(var2, 6, FieldType.INT32, var15, true);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.targetguid)) {
         Long var16 = var1.targetguid;
         if (var16 != null) {
            var2.writeUInt64(7, var16);
         }
      }

   }

   public void writeTo(RES_SYSTEM_BUFF_APPENDAGE var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_SYSTEM_BUFF_APPENDAGE readFrom(CodedInputStream var1) throws IOException {
      RES_SYSTEM_BUFF_APPENDAGE var2 = new RES_SYSTEM_BUFF_APPENDAGE();
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
               var2.error = var1.readInt32();
            } else if (var5 == 16) {
               var2.time = var1.readInt64();
            } else if (var5 == 24) {
               var2.endtime = var1.readInt64();
            } else if (var5 == 32) {
               var2.type = var1.readInt32();
            } else if (var5 == 40) {
               var2.index = var1.readInt32();
            } else if (var5 == 48) {
               if (var2.values == null) {
                  var2.values = new ArrayList();
               }

               var2.values.add(var1.readInt32());
            } else if (var5 != 50) {
               if (var5 == 56) {
                  var2.targetguid = var1.readUInt64();
               } else {
                  var1.skipField(var5);
               }
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_SYSTEM_BUFF_APPENDAGE.class);
         return this.descriptor = var1;
      }
   }
}
