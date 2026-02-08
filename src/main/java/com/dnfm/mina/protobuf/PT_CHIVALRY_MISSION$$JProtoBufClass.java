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

public class PT_CHIVALRY_MISSION$$JProtoBufClass implements Codec<PT_CHIVALRY_MISSION>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_CHIVALRY_MISSION var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_CHIVALRY_MISSION decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_CHIVALRY_MISSION var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.type)) {
         ENUM_CHIVALRY_MISSION.T var6 = var1.type;
         var2 += CodedOutputStream.computeEnumSize(1, ((ENUM_CHIVALRY_MISSION.T)var6).value());
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.value)) {
         Integer var7 = var1.value;
         var2 += CodedOutputStream.computeInt32Size(2, var7);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var8 = var1.count;
         var2 += CodedOutputStream.computeInt32Size(3, var8);
      }

      return var2;
   }

   public void doWriteTo(PT_CHIVALRY_MISSION var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.type)) {
         ENUM_CHIVALRY_MISSION.T var6 = var1.type;
         if (var6 != null) {
            var2.writeEnum(1, ((ENUM_CHIVALRY_MISSION.T)var6).value());
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.value)) {
         Integer var7 = var1.value;
         if (var7 != null) {
            var2.writeInt32(2, var7);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var8 = var1.count;
         if (var8 != null) {
            var2.writeInt32(3, var8);
         }
      }

   }

   public void writeTo(PT_CHIVALRY_MISSION var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_CHIVALRY_MISSION readFrom(CodedInputStream var1) throws IOException {
      PT_CHIVALRY_MISSION var2 = new PT_CHIVALRY_MISSION();
      var2.type = (ENUM_CHIVALRY_MISSION.T)CodedConstant.getEnumValue(ENUM_CHIVALRY_MISSION.T.class, ENUM_CHIVALRY_MISSION.T.values()[0].name());

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.type = (ENUM_CHIVALRY_MISSION.T)CodedConstant.getEnumValue(ENUM_CHIVALRY_MISSION.T.class, CodedConstant.getEnumName(ENUM_CHIVALRY_MISSION.T.values(), var1.readEnum()));
            } else if (var5 == 16) {
               var2.value = var1.readInt32();
            } else if (var5 == 24) {
               var2.count = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_CHIVALRY_MISSION.class);
         return this.descriptor = var1;
      }
   }
}
