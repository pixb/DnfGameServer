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

public class PT_GUILD_FISHING_DISASTER_INFO$$JProtoBufClass implements Codec<PT_GUILD_FISHING_DISASTER_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_GUILD_FISHING_DISASTER_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_GUILD_FISHING_DISASTER_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_GUILD_FISHING_DISASTER_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.disaster)) {
         Boolean var7 = var1.disaster;
         var2 += CodedOutputStream.computeBoolSize(1, var7);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.disasterindex)) {
         Integer var8 = var1.disasterindex;
         var2 += CodedOutputStream.computeInt32Size(2, var8);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var9 = var1.count;
         var2 += CodedOutputStream.computeInt32Size(3, var9);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.areaindex)) {
         Integer var10 = var1.areaindex;
         var2 += CodedOutputStream.computeInt32Size(4, var10);
      }

      return var2;
   }

   public void doWriteTo(PT_GUILD_FISHING_DISASTER_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.disaster)) {
         Boolean var7 = var1.disaster;
         if (var7 != null) {
            var2.writeBool(1, var7);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.disasterindex)) {
         Integer var8 = var1.disasterindex;
         if (var8 != null) {
            var2.writeInt32(2, var8);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var9 = var1.count;
         if (var9 != null) {
            var2.writeInt32(3, var9);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.areaindex)) {
         Integer var10 = var1.areaindex;
         if (var10 != null) {
            var2.writeInt32(4, var10);
         }
      }

   }

   public void writeTo(PT_GUILD_FISHING_DISASTER_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_GUILD_FISHING_DISASTER_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_GUILD_FISHING_DISASTER_INFO var2 = new PT_GUILD_FISHING_DISASTER_INFO();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.disaster = var1.readBool();
            } else if (var5 == 16) {
               var2.disasterindex = var1.readInt32();
            } else if (var5 == 24) {
               var2.count = var1.readInt32();
            } else if (var5 == 32) {
               var2.areaindex = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_GUILD_FISHING_DISASTER_INFO.class);
         return this.descriptor = var1;
      }
   }
}
