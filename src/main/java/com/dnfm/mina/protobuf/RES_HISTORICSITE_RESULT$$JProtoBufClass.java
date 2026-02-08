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

public class RES_HISTORICSITE_RESULT$$JProtoBufClass implements Codec<RES_HISTORICSITE_RESULT>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_HISTORICSITE_RESULT var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_HISTORICSITE_RESULT decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_HISTORICSITE_RESULT var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var14 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var14);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.win)) {
         Integer var15 = var1.win;
         var2 += CodedOutputStream.computeInt32Size(2, var15);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.totalnum)) {
         Integer var16 = var1.totalnum;
         var2 += CodedOutputStream.computeInt32Size(3, var16);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.redpoint)) {
         PT_HISTORICSITE_POINT var17 = var1.redpoint;
         var2 += CodedConstant.computeSize(4, var17, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.bluepoint)) {
         PT_HISTORICSITE_POINT var18 = var1.bluepoint;
         var2 += CodedConstant.computeSize(5, var18, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.list)) {
         List var19 = var1.list;
         var2 += CodedConstant.computeListSize(6, var19, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.targetguid)) {
         Long var20 = var1.targetguid;
         var2 += CodedOutputStream.computeUInt64Size(7, var20);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.targetlevel)) {
         Integer var21 = var1.targetlevel;
         var2 += CodedOutputStream.computeInt32Size(8, var21);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.targetname)) {
         String var22 = var1.targetname;
         var2 += CodedOutputStream.computeStringSize(9, var22);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.gsymbol)) {
         List var23 = var1.gsymbol;
         var2 += CodedConstant.computeListSize(10, var23, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.earlybird)) {
         List var24 = var1.earlybird;
         var2 += CodedConstant.computeListSize(11, var24, FieldType.UINT64, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      return var2;
   }

   public void doWriteTo(RES_HISTORICSITE_RESULT var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var14 = var1.error;
         if (var14 != null) {
            var2.writeInt32(1, var14);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.win)) {
         Integer var15 = var1.win;
         if (var15 != null) {
            var2.writeInt32(2, var15);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.totalnum)) {
         Integer var16 = var1.totalnum;
         if (var16 != null) {
            var2.writeInt32(3, var16);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.redpoint)) {
         PT_HISTORICSITE_POINT var17 = var1.redpoint;
         if (var17 != null) {
            CodedConstant.writeObject(var2, 4, FieldType.OBJECT, var17, false);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.bluepoint)) {
         PT_HISTORICSITE_POINT var18 = var1.bluepoint;
         if (var18 != null) {
            CodedConstant.writeObject(var2, 5, FieldType.OBJECT, var18, false);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.list)) {
         List var19 = var1.list;
         if (var19 != null) {
            CodedConstant.writeToList(var2, 6, FieldType.OBJECT, var19, false);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.targetguid)) {
         Long var20 = var1.targetguid;
         if (var20 != null) {
            var2.writeUInt64(7, var20);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.targetlevel)) {
         Integer var21 = var1.targetlevel;
         if (var21 != null) {
            var2.writeInt32(8, var21);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.targetname)) {
         String var22 = var1.targetname;
         if (var22 != null) {
            var2.writeString(9, var22);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.gsymbol)) {
         List var23 = var1.gsymbol;
         if (var23 != null) {
            CodedConstant.writeToList(var2, 10, FieldType.OBJECT, var23, false);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.earlybird)) {
         List var24 = var1.earlybird;
         if (var24 != null) {
            CodedConstant.writeToList(var2, 11, FieldType.UINT64, var24, true);
         }
      }

   }

   public void writeTo(RES_HISTORICSITE_RESULT var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_HISTORICSITE_RESULT readFrom(CodedInputStream var1) throws IOException {
      RES_HISTORICSITE_RESULT var2 = new RES_HISTORICSITE_RESULT();
      var2.list = new ArrayList();
      var2.gsymbol = new ArrayList();
      var2.earlybird = new ArrayList();

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
               var2.win = var1.readInt32();
            } else if (var5 == 24) {
               var2.totalnum = var1.readInt32();
            } else if (var5 == 34) {
               Codec var13 = ProtobufProxy.create(PT_HISTORICSITE_POINT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var17 = var1.readRawVarint32();
               int var21 = var1.pushLimit(var17);
               var2.redpoint = (PT_HISTORICSITE_POINT)var13.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var21);
            } else if (var5 == 42) {
               Codec var12 = ProtobufProxy.create(PT_HISTORICSITE_POINT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var16 = var1.readRawVarint32();
               int var20 = var1.pushLimit(var16);
               var2.bluepoint = (PT_HISTORICSITE_POINT)var12.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var20);
            } else if (var5 == 50) {
               Codec var11 = ProtobufProxy.create(PT_MEMBER_RESULT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var15 = var1.readRawVarint32();
               int var19 = var1.pushLimit(var15);
               if (var2.list == null) {
                  var2.list = new ArrayList();
               }

               var2.list.add((PT_MEMBER_RESULT)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var19);
            } else if (var5 == 56) {
               var2.targetguid = var1.readUInt64();
            } else if (var5 == 64) {
               var2.targetlevel = var1.readInt32();
            } else if (var5 == 74) {
               var2.targetname = var1.readString();
            } else if (var5 == 82) {
               Codec var10 = ProtobufProxy.create(PT_GUILD_SYMBOL.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var14 = var1.readRawVarint32();
               int var18 = var1.pushLimit(var14);
               if (var2.gsymbol == null) {
                  var2.gsymbol = new ArrayList();
               }

               var2.gsymbol.add((PT_GUILD_SYMBOL)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var18);
            } else if (var5 == 88) {
               if (var2.earlybird == null) {
                  var2.earlybird = new ArrayList();
               }

               var2.earlybird.add(var1.readUInt64());
            } else if (var5 != 90) {
               var1.skipField(var5);
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.earlybird == null) {
                  var2.earlybird = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.earlybird.add(var1.readUInt64());
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_HISTORICSITE_RESULT.class);
         return this.descriptor = var1;
      }
   }
}
