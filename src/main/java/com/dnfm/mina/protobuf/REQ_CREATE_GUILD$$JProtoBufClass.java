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

public class REQ_CREATE_GUILD$$JProtoBufClass implements Codec<REQ_CREATE_GUILD>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_CREATE_GUILD var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_CREATE_GUILD decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_CREATE_GUILD var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.gname)) {
         String var9 = var1.gname;
         var2 += CodedOutputStream.computeStringSize(1, var9);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.gsymbol)) {
         List var10 = var1.gsymbol;
         var2 += CodedConstant.computeListSize(2, var10, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.gboard)) {
         String var11 = var1.gboard;
         var2 += CodedOutputStream.computeStringSize(3, var11);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.freejoin)) {
         Boolean var12 = var1.freejoin;
         var2 += CodedOutputStream.computeBoolSize(4, var12);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.area)) {
         Integer var13 = var1.area;
         var2 += CodedOutputStream.computeInt32Size(5, var13);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.minlevel)) {
         Integer var14 = var1.minlevel;
         var2 += CodedOutputStream.computeInt32Size(6, var14);
      }

      return var2;
   }

   public void doWriteTo(REQ_CREATE_GUILD var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.gname)) {
         String var9 = var1.gname;
         if (var9 != null) {
            var2.writeString(1, var9);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.gsymbol)) {
         List var10 = var1.gsymbol;
         if (var10 != null) {
            CodedConstant.writeToList(var2, 2, FieldType.OBJECT, var10, false);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.gboard)) {
         String var11 = var1.gboard;
         if (var11 != null) {
            var2.writeString(3, var11);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.freejoin)) {
         Boolean var12 = var1.freejoin;
         if (var12 != null) {
            var2.writeBool(4, var12);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.area)) {
         Integer var13 = var1.area;
         if (var13 != null) {
            var2.writeInt32(5, var13);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.minlevel)) {
         Integer var14 = var1.minlevel;
         if (var14 != null) {
            var2.writeInt32(6, var14);
         }
      }

   }

   public void writeTo(REQ_CREATE_GUILD var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_CREATE_GUILD readFrom(CodedInputStream var1) throws IOException {
      REQ_CREATE_GUILD var2 = new REQ_CREATE_GUILD();
      var2.gsymbol = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 10) {
               var2.gname = var1.readString();
            } else if (var5 == 18) {
               Codec var10 = ProtobufProxy.create(PT_GUILD_SYMBOL.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.gsymbol == null) {
                  var2.gsymbol = new ArrayList();
               }

               var2.gsymbol.add((PT_GUILD_SYMBOL)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 26) {
               var2.gboard = var1.readString();
            } else if (var5 == 32) {
               var2.freejoin = var1.readBool();
            } else if (var5 == 40) {
               var2.area = var1.readInt32();
            } else if (var5 == 48) {
               var2.minlevel = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_CREATE_GUILD.class);
         return this.descriptor = var1;
      }
   }
}
