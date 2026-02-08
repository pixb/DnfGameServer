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

public class RES_GUILD_PUBLIC_BUFF_UPGRADE$$JProtoBufClass implements Codec<RES_GUILD_PUBLIC_BUFF_UPGRADE>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_GUILD_PUBLIC_BUFF_UPGRADE var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_GUILD_PUBLIC_BUFF_UPGRADE decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_GUILD_PUBLIC_BUFF_UPGRADE var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var10 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var10);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var11 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(2, var11);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.endtime)) {
         Integer var12 = var1.endtime;
         var2 += CodedOutputStream.computeInt32Size(3, var12);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.time)) {
         Integer var13 = var1.time;
         var2 += CodedOutputStream.computeInt32Size(4, var13);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.values)) {
         List var14 = var1.values;
         var2 += CodedConstant.computeListSize(5, var14, FieldType.INT32, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.gpoint)) {
         Integer var15 = var1.gpoint;
         var2 += CodedOutputStream.computeInt32Size(6, var15);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.list)) {
         List var16 = var1.list;
         var2 += CodedConstant.computeListSize(7, var16, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(RES_GUILD_PUBLIC_BUFF_UPGRADE var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var10 = var1.error;
         if (var10 != null) {
            var2.writeInt32(1, var10);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var11 = var1.index;
         if (var11 != null) {
            var2.writeInt32(2, var11);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.endtime)) {
         Integer var12 = var1.endtime;
         if (var12 != null) {
            var2.writeInt32(3, var12);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.time)) {
         Integer var13 = var1.time;
         if (var13 != null) {
            var2.writeInt32(4, var13);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.values)) {
         List var14 = var1.values;
         if (var14 != null) {
            CodedConstant.writeToList(var2, 5, FieldType.INT32, var14, true);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.gpoint)) {
         Integer var15 = var1.gpoint;
         if (var15 != null) {
            var2.writeInt32(6, var15);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.list)) {
         List var16 = var1.list;
         if (var16 != null) {
            CodedConstant.writeToList(var2, 7, FieldType.OBJECT, var16, false);
         }
      }

   }

   public void writeTo(RES_GUILD_PUBLIC_BUFF_UPGRADE var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_GUILD_PUBLIC_BUFF_UPGRADE readFrom(CodedInputStream var1) throws IOException {
      RES_GUILD_PUBLIC_BUFF_UPGRADE var2 = new RES_GUILD_PUBLIC_BUFF_UPGRADE();
      var2.values = new ArrayList();
      var2.list = new ArrayList();

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
               var2.index = var1.readInt32();
            } else if (var5 == 24) {
               var2.endtime = var1.readInt32();
            } else if (var5 == 32) {
               var2.time = var1.readInt32();
            } else if (var5 == 40) {
               if (var2.values == null) {
                  var2.values = new ArrayList();
               }

               var2.values.add(var1.readInt32());
            } else if (var5 == 42) {
               int var12 = var1.readRawVarint32();
               int var14 = var1.pushLimit(var12);
               if (var2.values == null) {
                  var2.values = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.values.add(var1.readInt32());
               }

               var1.popLimit(var14);
            } else if (var5 == 48) {
               var2.gpoint = var1.readInt32();
            } else if (var5 != 58) {
               var1.skipField(var5);
            } else if (var5 != 42) {
               Codec var10 = ProtobufProxy.create(PT_GUILD_BUFF_UPGRADE_COST.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var11 = var1.readRawVarint32();
               int var13 = var1.pushLimit(var11);
               if (var2.list == null) {
                  var2.list = new ArrayList();
               }

               var2.list.add((PT_GUILD_BUFF_UPGRADE_COST)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var13);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_GUILD_PUBLIC_BUFF_UPGRADE.class);
         return this.descriptor = var1;
      }
   }
}
