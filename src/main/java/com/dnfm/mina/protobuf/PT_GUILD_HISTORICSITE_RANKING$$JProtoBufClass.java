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

public class PT_GUILD_HISTORICSITE_RANKING$$JProtoBufClass implements Codec<PT_GUILD_HISTORICSITE_RANKING>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_GUILD_HISTORICSITE_RANKING var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_GUILD_HISTORICSITE_RANKING decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_GUILD_HISTORICSITE_RANKING var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.wincount)) {
         Integer var12 = var1.wincount;
         var2 += CodedOutputStream.computeInt32Size(1, var12);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.winningrate)) {
         Integer var13 = var1.winningrate;
         var2 += CodedOutputStream.computeInt32Size(2, var13);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.gwinpoint)) {
         Long var14 = var1.gwinpoint;
         var2 += CodedOutputStream.computeInt64Size(3, var14);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.gsymbol)) {
         List var15 = var1.gsymbol;
         var2 += CodedConstant.computeListSize(4, var15, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var16 = var1.guid;
         var2 += CodedOutputStream.computeUInt64Size(5, var16);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var17 = var1.name;
         var2 += CodedOutputStream.computeStringSize(6, var17);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var18 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(7, var18);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.rank)) {
         Integer var19 = var1.rank;
         var2 += CodedOutputStream.computeInt32Size(8, var19);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.score)) {
         Long var20 = var1.score;
         var2 += CodedOutputStream.computeInt64Size(9, var20);
      }

      return var2;
   }

   public void doWriteTo(PT_GUILD_HISTORICSITE_RANKING var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.wincount)) {
         Integer var12 = var1.wincount;
         if (var12 != null) {
            var2.writeInt32(1, var12);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.winningrate)) {
         Integer var13 = var1.winningrate;
         if (var13 != null) {
            var2.writeInt32(2, var13);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.gwinpoint)) {
         Long var14 = var1.gwinpoint;
         if (var14 != null) {
            var2.writeInt64(3, var14);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.gsymbol)) {
         List var15 = var1.gsymbol;
         if (var15 != null) {
            CodedConstant.writeToList(var2, 4, FieldType.OBJECT, var15, false);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var16 = var1.guid;
         if (var16 != null) {
            var2.writeUInt64(5, var16);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var17 = var1.name;
         if (var17 != null) {
            var2.writeString(6, var17);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var18 = var1.level;
         if (var18 != null) {
            var2.writeInt32(7, var18);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.rank)) {
         Integer var19 = var1.rank;
         if (var19 != null) {
            var2.writeInt32(8, var19);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.score)) {
         Long var20 = var1.score;
         if (var20 != null) {
            var2.writeInt64(9, var20);
         }
      }

   }

   public void writeTo(PT_GUILD_HISTORICSITE_RANKING var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_GUILD_HISTORICSITE_RANKING readFrom(CodedInputStream var1) throws IOException {
      PT_GUILD_HISTORICSITE_RANKING var2 = new PT_GUILD_HISTORICSITE_RANKING();
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
               var2.wincount = var1.readInt32();
            } else if (var5 == 16) {
               var2.winningrate = var1.readInt32();
            } else if (var5 == 24) {
               var2.gwinpoint = var1.readInt64();
            } else if (var5 == 34) {
               Codec var10 = ProtobufProxy.create(PT_GUILD_SYMBOL.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.gsymbol == null) {
                  var2.gsymbol = new ArrayList();
               }

               var2.gsymbol.add((PT_GUILD_SYMBOL)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 40) {
               var2.guid = var1.readUInt64();
            } else if (var5 == 50) {
               var2.name = var1.readString();
            } else if (var5 == 56) {
               var2.level = var1.readInt32();
            } else if (var5 == 64) {
               var2.rank = var1.readInt32();
            } else if (var5 == 72) {
               var2.score = var1.readInt64();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_GUILD_HISTORICSITE_RANKING.class);
         return this.descriptor = var1;
      }
   }
}
