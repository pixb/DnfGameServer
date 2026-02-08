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

public class PT_CLIENTINFO$$JProtoBufClass implements Codec<PT_CLIENTINFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_CLIENTINFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_CLIENTINFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_CLIENTINFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.platID)) {
         Integer var22 = var1.platID;
         var2 += CodedOutputStream.computeUInt32Size(1, var22);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.deviceSoft)) {
         String var23 = var1.deviceSoft;
         var2 += CodedOutputStream.computeStringSize(2, var23);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.deviceHard)) {
         String var24 = var1.deviceHard;
         var2 += CodedOutputStream.computeStringSize(3, var24);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.teleOper)) {
         String var25 = var1.teleOper;
         var2 += CodedOutputStream.computeStringSize(4, var25);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.network)) {
         String var26 = var1.network;
         var2 += CodedOutputStream.computeStringSize(5, var26);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.scrWidth)) {
         Integer var27 = var1.scrWidth;
         var2 += CodedOutputStream.computeInt32Size(6, var27);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.scrHeight)) {
         Integer var28 = var1.scrHeight;
         var2 += CodedOutputStream.computeInt32Size(7, var28);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.density)) {
         Float var29 = var1.density;
         var2 += CodedOutputStream.computeFloatSize(8, var29);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.cpu)) {
         String var30 = var1.cpu;
         var2 += CodedOutputStream.computeStringSize(9, var30);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.memory)) {
         Integer var31 = var1.memory;
         var2 += CodedOutputStream.computeInt32Size(10, var31);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.glRender)) {
         String var32 = var1.glRender;
         var2 += CodedOutputStream.computeStringSize(11, var32);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.glVersion)) {
         String var33 = var1.glVersion;
         var2 += CodedOutputStream.computeStringSize(12, var33);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.deviceID)) {
         String var34 = var1.deviceID;
         var2 += CodedOutputStream.computeStringSize(13, var34);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.clientIP)) {
         String var35 = var1.clientIP;
         var2 += CodedOutputStream.computeStringSize(14, var35);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var36 = var1.type;
         var2 += CodedOutputStream.computeUInt32Size(15, var36);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.oAID)) {
         String var37 = var1.oAID;
         var2 += CodedOutputStream.computeStringSize(16, var37);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.buildType)) {
         ENUM_CLIENT_BUILD_TYPE.T var38 = var1.buildType;
         var2 += CodedOutputStream.computeEnumSize(17, ((ENUM_CLIENT_BUILD_TYPE.T)var38).value());
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.deviceLanguage)) {
         String var39 = var1.deviceLanguage;
         var2 += CodedOutputStream.computeStringSize(18, var39);
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.deviceUTC)) {
         Integer var40 = var1.deviceUTC;
         var2 += CodedOutputStream.computeInt32Size(19, var40);
      }

      return var2;
   }

   public void doWriteTo(PT_CLIENTINFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.platID)) {
         Integer var22 = var1.platID;
         if (var22 != null) {
            var2.writeUInt32(1, var22);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.deviceSoft)) {
         String var23 = var1.deviceSoft;
         if (var23 != null) {
            var2.writeString(2, var23);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.deviceHard)) {
         String var24 = var1.deviceHard;
         if (var24 != null) {
            var2.writeString(3, var24);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.teleOper)) {
         String var25 = var1.teleOper;
         if (var25 != null) {
            var2.writeString(4, var25);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.network)) {
         String var26 = var1.network;
         if (var26 != null) {
            var2.writeString(5, var26);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.scrWidth)) {
         Integer var27 = var1.scrWidth;
         if (var27 != null) {
            var2.writeInt32(6, var27);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.scrHeight)) {
         Integer var28 = var1.scrHeight;
         if (var28 != null) {
            var2.writeInt32(7, var28);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.density)) {
         Float var29 = var1.density;
         if (var29 != null) {
            var2.writeFloat(8, var29);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.cpu)) {
         String var30 = var1.cpu;
         if (var30 != null) {
            var2.writeString(9, var30);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.memory)) {
         Integer var31 = var1.memory;
         if (var31 != null) {
            var2.writeInt32(10, var31);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.glRender)) {
         String var32 = var1.glRender;
         if (var32 != null) {
            var2.writeString(11, var32);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.glVersion)) {
         String var33 = var1.glVersion;
         if (var33 != null) {
            var2.writeString(12, var33);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.deviceID)) {
         String var34 = var1.deviceID;
         if (var34 != null) {
            var2.writeString(13, var34);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.clientIP)) {
         String var35 = var1.clientIP;
         if (var35 != null) {
            var2.writeString(14, var35);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var36 = var1.type;
         if (var36 != null) {
            var2.writeUInt32(15, var36);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.oAID)) {
         String var37 = var1.oAID;
         if (var37 != null) {
            var2.writeString(16, var37);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.buildType)) {
         ENUM_CLIENT_BUILD_TYPE.T var38 = var1.buildType;
         if (var38 != null) {
            var2.writeEnum(17, ((ENUM_CLIENT_BUILD_TYPE.T)var38).value());
         }
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.deviceLanguage)) {
         String var39 = var1.deviceLanguage;
         if (var39 != null) {
            var2.writeString(18, var39);
         }
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.deviceUTC)) {
         Integer var40 = var1.deviceUTC;
         if (var40 != null) {
            var2.writeInt32(19, var40);
         }
      }

   }

   public void writeTo(PT_CLIENTINFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_CLIENTINFO readFrom(CodedInputStream var1) throws IOException {
      PT_CLIENTINFO var2 = new PT_CLIENTINFO();
      var2.buildType = (ENUM_CLIENT_BUILD_TYPE.T)CodedConstant.getEnumValue(ENUM_CLIENT_BUILD_TYPE.T.class, ENUM_CLIENT_BUILD_TYPE.T.values()[0].name());

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.platID = var1.readUInt32();
            } else if (var5 == 18) {
               var2.deviceSoft = var1.readString();
            } else if (var5 == 26) {
               var2.deviceHard = var1.readString();
            } else if (var5 == 34) {
               var2.teleOper = var1.readString();
            } else if (var5 == 42) {
               var2.network = var1.readString();
            } else if (var5 == 48) {
               var2.scrWidth = var1.readInt32();
            } else if (var5 == 56) {
               var2.scrHeight = var1.readInt32();
            } else if (var5 == 69) {
               var2.density = var1.readFloat();
            } else if (var5 == 74) {
               var2.cpu = var1.readString();
            } else if (var5 == 80) {
               var2.memory = var1.readInt32();
            } else if (var5 == 90) {
               var2.glRender = var1.readString();
            } else if (var5 == 98) {
               var2.glVersion = var1.readString();
            } else if (var5 == 106) {
               var2.deviceID = var1.readString();
            } else if (var5 == 114) {
               var2.clientIP = var1.readString();
            } else if (var5 == 120) {
               var2.type = var1.readUInt32();
            } else if (var5 == 130) {
               var2.oAID = var1.readString();
            } else if (var5 == 136) {
               var2.buildType = (ENUM_CLIENT_BUILD_TYPE.T)CodedConstant.getEnumValue(ENUM_CLIENT_BUILD_TYPE.T.class, CodedConstant.getEnumName(ENUM_CLIENT_BUILD_TYPE.T.values(), var1.readEnum()));
            } else if (var5 == 146) {
               var2.deviceLanguage = var1.readString();
            } else if (var5 == 152) {
               var2.deviceUTC = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_CLIENTINFO.class);
         return this.descriptor = var1;
      }
   }
}
