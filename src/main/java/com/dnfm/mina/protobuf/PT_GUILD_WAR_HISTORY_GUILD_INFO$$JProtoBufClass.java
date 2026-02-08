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

public class PT_GUILD_WAR_HISTORY_GUILD_INFO$$JProtoBufClass implements Codec<PT_GUILD_WAR_HISTORY_GUILD_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_GUILD_WAR_HISTORY_GUILD_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_GUILD_WAR_HISTORY_GUILD_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_GUILD_WAR_HISTORY_GUILD_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.gguid)) {
         Long var11 = var1.gguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var11);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.gsymbol)) {
         List var12 = var1.gsymbol;
         var2 += CodedConstant.computeListSize(2, var12, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var13 = var1.name;
         var2 += CodedOutputStream.computeStringSize(3, var13);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.point)) {
         Integer var14 = var1.point;
         var2 += CodedOutputStream.computeInt32Size(4, var14);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.rank)) {
         Integer var15 = var1.rank;
         var2 += CodedOutputStream.computeInt32Size(5, var15);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.group)) {
         Integer var16 = var1.group;
         var2 += CodedOutputStream.computeInt32Size(6, var16);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.occupypoint)) {
         Integer var17 = var1.occupypoint;
         var2 += CodedOutputStream.computeInt32Size(7, var17);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var18 = var1.count;
         var2 += CodedOutputStream.computeInt32Size(8, var18);
      }

      return var2;
   }

   public void doWriteTo(PT_GUILD_WAR_HISTORY_GUILD_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.gguid)) {
         Long var11 = var1.gguid;
         if (var11 != null) {
            var2.writeUInt64(1, var11);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.gsymbol)) {
         List var12 = var1.gsymbol;
         if (var12 != null) {
            CodedConstant.writeToList(var2, 2, FieldType.OBJECT, var12, false);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var13 = var1.name;
         if (var13 != null) {
            var2.writeString(3, var13);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.point)) {
         Integer var14 = var1.point;
         if (var14 != null) {
            var2.writeInt32(4, var14);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.rank)) {
         Integer var15 = var1.rank;
         if (var15 != null) {
            var2.writeInt32(5, var15);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.group)) {
         Integer var16 = var1.group;
         if (var16 != null) {
            var2.writeInt32(6, var16);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.occupypoint)) {
         Integer var17 = var1.occupypoint;
         if (var17 != null) {
            var2.writeInt32(7, var17);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var18 = var1.count;
         if (var18 != null) {
            var2.writeInt32(8, var18);
         }
      }

   }

   public void writeTo(PT_GUILD_WAR_HISTORY_GUILD_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_GUILD_WAR_HISTORY_GUILD_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_GUILD_WAR_HISTORY_GUILD_INFO var2 = new PT_GUILD_WAR_HISTORY_GUILD_INFO();
      var2.gsymbol = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.gguid = var1.readUInt64();
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
               var2.name = var1.readString();
            } else if (var5 == 32) {
               var2.point = var1.readInt32();
            } else if (var5 == 40) {
               var2.rank = var1.readInt32();
            } else if (var5 == 48) {
               var2.group = var1.readInt32();
            } else if (var5 == 56) {
               var2.occupypoint = var1.readInt32();
            } else if (var5 == 64) {
               var2.count = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_GUILD_WAR_HISTORY_GUILD_INFO.class);
         return this.descriptor = var1;
      }
   }
}
