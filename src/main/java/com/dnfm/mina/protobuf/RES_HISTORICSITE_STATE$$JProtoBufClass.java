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

public class RES_HISTORICSITE_STATE$$JProtoBufClass implements Codec<RES_HISTORICSITE_STATE>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_HISTORICSITE_STATE var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_HISTORICSITE_STATE decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_HISTORICSITE_STATE var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var17 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var17);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.state)) {
         Integer var18 = var1.state;
         var2 += CodedOutputStream.computeInt32Size(2, var18);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.season)) {
         Integer var19 = var1.season;
         var2 += CodedOutputStream.computeInt32Size(3, var19);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.week)) {
         Integer var20 = var1.week;
         var2 += CodedOutputStream.computeInt32Size(4, var20);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.ip)) {
         String var21 = var1.ip;
         var2 += CodedOutputStream.computeStringSize(5, var21);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.port)) {
         Integer var22 = var1.port;
         var2 += CodedOutputStream.computeInt32Size(6, var22);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var23 = var1.world;
         var2 += CodedOutputStream.computeInt32Size(7, var23);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.channel)) {
         Integer var24 = var1.channel;
         var2 += CodedOutputStream.computeInt32Size(8, var24);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.teamtype)) {
         Integer var25 = var1.teamtype;
         var2 += CodedOutputStream.computeInt32Size(9, var25);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.targetguid)) {
         Long var26 = var1.targetguid;
         var2 += CodedOutputStream.computeUInt64Size(10, var26);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.targetlevel)) {
         Integer var27 = var1.targetlevel;
         var2 += CodedOutputStream.computeInt32Size(11, var27);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.targetname)) {
         String var28 = var1.targetname;
         var2 += CodedOutputStream.computeStringSize(12, var28);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.gsymbol)) {
         List var29 = var1.gsymbol;
         var2 += CodedConstant.computeListSize(13, var29, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.registeredenter)) {
         ENUM_HISTORICSITE_ENTER.T var30 = var1.registeredenter;
         var2 += CodedOutputStream.computeEnumSize(14, ((ENUM_HISTORICSITE_ENTER.T)var30).value());
      }

      return var2;
   }

   public void doWriteTo(RES_HISTORICSITE_STATE var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var17 = var1.error;
         if (var17 != null) {
            var2.writeInt32(1, var17);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.state)) {
         Integer var18 = var1.state;
         if (var18 != null) {
            var2.writeInt32(2, var18);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.season)) {
         Integer var19 = var1.season;
         if (var19 != null) {
            var2.writeInt32(3, var19);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.week)) {
         Integer var20 = var1.week;
         if (var20 != null) {
            var2.writeInt32(4, var20);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.ip)) {
         String var21 = var1.ip;
         if (var21 != null) {
            var2.writeString(5, var21);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.port)) {
         Integer var22 = var1.port;
         if (var22 != null) {
            var2.writeInt32(6, var22);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var23 = var1.world;
         if (var23 != null) {
            var2.writeInt32(7, var23);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.channel)) {
         Integer var24 = var1.channel;
         if (var24 != null) {
            var2.writeInt32(8, var24);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.teamtype)) {
         Integer var25 = var1.teamtype;
         if (var25 != null) {
            var2.writeInt32(9, var25);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.targetguid)) {
         Long var26 = var1.targetguid;
         if (var26 != null) {
            var2.writeUInt64(10, var26);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.targetlevel)) {
         Integer var27 = var1.targetlevel;
         if (var27 != null) {
            var2.writeInt32(11, var27);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.targetname)) {
         String var28 = var1.targetname;
         if (var28 != null) {
            var2.writeString(12, var28);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.gsymbol)) {
         List var29 = var1.gsymbol;
         if (var29 != null) {
            CodedConstant.writeToList(var2, 13, FieldType.OBJECT, var29, false);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.registeredenter)) {
         ENUM_HISTORICSITE_ENTER.T var30 = var1.registeredenter;
         if (var30 != null) {
            var2.writeEnum(14, ((ENUM_HISTORICSITE_ENTER.T)var30).value());
         }
      }

   }

   public void writeTo(RES_HISTORICSITE_STATE var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_HISTORICSITE_STATE readFrom(CodedInputStream var1) throws IOException {
      RES_HISTORICSITE_STATE var2 = new RES_HISTORICSITE_STATE();
      var2.gsymbol = new ArrayList();
      var2.registeredenter = (ENUM_HISTORICSITE_ENTER.T)CodedConstant.getEnumValue(ENUM_HISTORICSITE_ENTER.T.class, ENUM_HISTORICSITE_ENTER.T.values()[0].name());

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
               var2.state = var1.readInt32();
            } else if (var5 == 24) {
               var2.season = var1.readInt32();
            } else if (var5 == 32) {
               var2.week = var1.readInt32();
            } else if (var5 == 42) {
               var2.ip = var1.readString();
            } else if (var5 == 48) {
               var2.port = var1.readInt32();
            } else if (var5 == 56) {
               var2.world = var1.readInt32();
            } else if (var5 == 64) {
               var2.channel = var1.readInt32();
            } else if (var5 == 72) {
               var2.teamtype = var1.readInt32();
            } else if (var5 == 80) {
               var2.targetguid = var1.readUInt64();
            } else if (var5 == 88) {
               var2.targetlevel = var1.readInt32();
            } else if (var5 == 98) {
               var2.targetname = var1.readString();
            } else if (var5 == 106) {
               Codec var10 = ProtobufProxy.create(PT_GUILD_SYMBOL.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.gsymbol == null) {
                  var2.gsymbol = new ArrayList();
               }

               var2.gsymbol.add((PT_GUILD_SYMBOL)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 112) {
               var2.registeredenter = (ENUM_HISTORICSITE_ENTER.T)CodedConstant.getEnumValue(ENUM_HISTORICSITE_ENTER.T.class, CodedConstant.getEnumName(ENUM_HISTORICSITE_ENTER.T.values(), var1.readEnum()));
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_HISTORICSITE_STATE.class);
         return this.descriptor = var1;
      }
   }
}
