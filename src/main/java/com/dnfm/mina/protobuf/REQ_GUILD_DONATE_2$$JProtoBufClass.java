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

public class REQ_GUILD_DONATE_2$$JProtoBufClass implements Codec<REQ_GUILD_DONATE_2>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_GUILD_DONATE_2 var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_GUILD_DONATE_2 decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_GUILD_DONATE_2 var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var8 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(1, var8);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.itemindex)) {
         Integer var9 = var1.itemindex;
         var2 += CodedOutputStream.computeInt32Size(2, var9);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var10 = var1.count;
         var2 += CodedOutputStream.computeInt32Size(3, var10);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.helpguid)) {
         Long var11 = var1.helpguid;
         var2 += CodedOutputStream.computeUInt64Size(4, var11);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.recipes)) {
         List var12 = var1.recipes;
         var2 += CodedConstant.computeListSize(5, var12, FieldType.INT32, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      return var2;
   }

   public void doWriteTo(REQ_GUILD_DONATE_2 var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var8 = var1.index;
         if (var8 != null) {
            var2.writeInt32(1, var8);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.itemindex)) {
         Integer var9 = var1.itemindex;
         if (var9 != null) {
            var2.writeInt32(2, var9);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var10 = var1.count;
         if (var10 != null) {
            var2.writeInt32(3, var10);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.helpguid)) {
         Long var11 = var1.helpguid;
         if (var11 != null) {
            var2.writeUInt64(4, var11);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.recipes)) {
         List var12 = var1.recipes;
         if (var12 != null) {
            CodedConstant.writeToList(var2, 5, FieldType.INT32, var12, true);
         }
      }

   }

   public void writeTo(REQ_GUILD_DONATE_2 var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_GUILD_DONATE_2 readFrom(CodedInputStream var1) throws IOException {
      REQ_GUILD_DONATE_2 var2 = new REQ_GUILD_DONATE_2();
      var2.recipes = new ArrayList();

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
               var2.itemindex = var1.readInt32();
            } else if (var5 == 24) {
               var2.count = var1.readInt32();
            } else if (var5 == 32) {
               var2.helpguid = var1.readUInt64();
            } else if (var5 == 40) {
               if (var2.recipes == null) {
                  var2.recipes = new ArrayList();
               }

               var2.recipes.add(var1.readInt32());
            } else if (var5 != 42) {
               var1.skipField(var5);
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.recipes == null) {
                  var2.recipes = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.recipes.add(var1.readInt32());
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_GUILD_DONATE_2.class);
         return this.descriptor = var1;
      }
   }
}
