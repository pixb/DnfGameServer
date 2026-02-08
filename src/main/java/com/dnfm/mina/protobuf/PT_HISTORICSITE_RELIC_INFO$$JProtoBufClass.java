package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.code.CodedConstant;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.InvalidProtocolBufferException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class PT_HISTORICSITE_RELIC_INFO$$JProtoBufClass implements Codec<PT_HISTORICSITE_RELIC_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_HISTORICSITE_RELIC_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_HISTORICSITE_RELIC_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_HISTORICSITE_RELIC_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.teamtype)) {
         ENUM_TEAM.T var9 = var1.teamtype;
         var2 += CodedOutputStream.computeEnumSize(1, ((Enum)var9).ordinal());
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.relicindex)) {
         Integer var10 = var1.relicindex;
         var2 += CodedOutputStream.computeInt32Size(2, var10);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.reliccount)) {
         Integer var11 = var1.reliccount;
         var2 += CodedOutputStream.computeInt32Size(3, var11);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.buyername)) {
         String var12 = var1.buyername;
         var2 += CodedOutputStream.computeStringSize(4, var12);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.buyerjob)) {
         Integer var13 = var1.buyerjob;
         var2 += CodedOutputStream.computeInt32Size(5, var13);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.buyergrowtype)) {
         Integer var14 = var1.buyergrowtype;
         var2 += CodedOutputStream.computeInt32Size(6, var14);
      }

      return var2;
   }

   public void doWriteTo(PT_HISTORICSITE_RELIC_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.teamtype)) {
         ENUM_TEAM.T var9 = var1.teamtype;
         if (var9 != null) {
            var2.writeEnum(1, ((Enum)var9).ordinal());
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.relicindex)) {
         Integer var10 = var1.relicindex;
         if (var10 != null) {
            var2.writeInt32(2, var10);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.reliccount)) {
         Integer var11 = var1.reliccount;
         if (var11 != null) {
            var2.writeInt32(3, var11);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.buyername)) {
         String var12 = var1.buyername;
         if (var12 != null) {
            var2.writeString(4, var12);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.buyerjob)) {
         Integer var13 = var1.buyerjob;
         if (var13 != null) {
            var2.writeInt32(5, var13);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.buyergrowtype)) {
         Integer var14 = var1.buyergrowtype;
         if (var14 != null) {
            var2.writeInt32(6, var14);
         }
      }

   }

   public void writeTo(PT_HISTORICSITE_RELIC_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_HISTORICSITE_RELIC_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_HISTORICSITE_RELIC_INFO var2 = new PT_HISTORICSITE_RELIC_INFO();
      var2.teamtype = (ENUM_TEAM.T)CodedConstant.getEnumValue(ENUM_TEAM.T.class, ENUM_TEAM.T.values()[0].name());

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.teamtype = (ENUM_TEAM.T)CodedConstant.getEnumValue(ENUM_TEAM.T.class, CodedConstant.getEnumName(ENUM_TEAM.T.values(), var1.readEnum()));
            } else if (var5 == 16) {
               var2.relicindex = var1.readInt32();
            } else if (var5 == 24) {
               var2.reliccount = var1.readInt32();
            } else if (var5 == 34) {
               var2.buyername = var1.readString();
            } else if (var5 == 40) {
               var2.buyerjob = var1.readInt32();
            } else if (var5 == 48) {
               var2.buyergrowtype = var1.readInt32();
            } else {
               var1.skipField(var5);
            }
         }

         return var2;
      } catch (InvalidProtocolBufferException var6) {
         throw var6;
      } catch (IOException var7) {
         throw var7;
      }
   }

   public Descriptors.Descriptor getDescriptor() throws IOException {
      if (this.descriptor != null) {
         return this.descriptor;
      } else {
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_HISTORICSITE_RELIC_INFO.class);
         return this.descriptor = var1;
      }
   }
}
