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

public class PT_PROHIBIT$$JProtoBufClass implements Codec<PT_PROHIBIT>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_PROHIBIT var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_PROHIBIT decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_PROHIBIT var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.target)) {
         Long var9 = var1.target;
         var2 += CodedOutputStream.computeUInt64Size(1, var9);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.type)) {
         ENUM_IDIP_PROHIBIT_TYPE.T var10 = var1.type;
         var2 += CodedOutputStream.computeEnumSize(2, ((ENUM_IDIP_PROHIBIT_TYPE.T)var10).value());
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.subtype)) {
         List var11 = var1.subtype;
         var2 += CodedConstant.computeListSize(3, var11, FieldType.INT32, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.endtime)) {
         Long var12 = var1.endtime;
         var2 += CodedOutputStream.computeInt64Size(4, var12);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.reason)) {
         String var13 = var1.reason;
         var2 += CodedOutputStream.computeStringSize(5, var13);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.times)) {
         List var14 = var1.times;
         var2 += CodedConstant.computeListSize(6, var14, FieldType.INT64, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      return var2;
   }

   public void doWriteTo(PT_PROHIBIT var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.target)) {
         Long var9 = var1.target;
         if (var9 != null) {
            var2.writeUInt64(1, var9);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.type)) {
         ENUM_IDIP_PROHIBIT_TYPE.T var10 = var1.type;
         if (var10 != null) {
            var2.writeEnum(2, ((ENUM_IDIP_PROHIBIT_TYPE.T)var10).value());
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.subtype)) {
         List var11 = var1.subtype;
         if (var11 != null) {
            CodedConstant.writeToList(var2, 3, FieldType.INT32, var11, true);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.endtime)) {
         Long var12 = var1.endtime;
         if (var12 != null) {
            var2.writeInt64(4, var12);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.reason)) {
         String var13 = var1.reason;
         if (var13 != null) {
            var2.writeString(5, var13);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.times)) {
         List var14 = var1.times;
         if (var14 != null) {
            CodedConstant.writeToList(var2, 6, FieldType.INT64, var14, true);
         }
      }

   }

   public void writeTo(PT_PROHIBIT var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_PROHIBIT readFrom(CodedInputStream var1) throws IOException {
      PT_PROHIBIT var2 = new PT_PROHIBIT();
      var2.subtype = new ArrayList();
      var2.times = new ArrayList();
      var2.type = (ENUM_IDIP_PROHIBIT_TYPE.T)CodedConstant.getEnumValue(ENUM_IDIP_PROHIBIT_TYPE.T.class, ENUM_IDIP_PROHIBIT_TYPE.T.values()[0].name());

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.target = var1.readUInt64();
            } else if (var5 == 16) {
               var2.type = (ENUM_IDIP_PROHIBIT_TYPE.T)CodedConstant.getEnumValue(ENUM_IDIP_PROHIBIT_TYPE.T.class, CodedConstant.getEnumName(ENUM_IDIP_PROHIBIT_TYPE.T.values(), var1.readEnum()));
            } else if (var5 == 24) {
               if (var2.subtype == null) {
                  var2.subtype = new ArrayList();
               }

               var2.subtype.add(var1.readInt32());
            } else if (var5 == 26) {
               int var11 = var1.readRawVarint32();
               int var13 = var1.pushLimit(var11);
               if (var2.subtype == null) {
                  var2.subtype = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.subtype.add(var1.readInt32());
               }

               var1.popLimit(var13);
            } else if (var5 == 32) {
               var2.endtime = var1.readInt64();
            } else if (var5 == 42) {
               var2.reason = var1.readString();
            } else if (var5 == 48) {
               if (var2.times == null) {
                  var2.times = new ArrayList();
               }

               var2.times.add(var1.readInt64());
            } else if (var5 == 26) {
               int var10 = var1.readRawVarint32();
               int var12 = var1.pushLimit(var10);
               if (var2.subtype == null) {
                  var2.subtype = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.subtype.add(var1.readInt32());
               }

               var1.popLimit(var12);
            } else if (var5 != 50) {
               var1.skipField(var5);
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.times == null) {
                  var2.times = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.times.add(var1.readInt64());
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_PROHIBIT.class);
         return this.descriptor = var1;
      }
   }
}
