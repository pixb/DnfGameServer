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

public class REQ_ENTER_CHANNEL$$JProtoBufClass implements Codec<REQ_ENTER_CHANNEL>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_ENTER_CHANNEL var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_ENTER_CHANNEL decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_ENTER_CHANNEL var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.openid)) {
         String var21 = var1.openid;
         var2 += CodedOutputStream.computeStringSize(1, var21);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var22 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(2, var22);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.authkey)) {
         String var23 = var1.authkey;
         var2 += CodedOutputStream.computeStringSize(3, var23);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.version)) {
         String var24 = var1.version;
         var2 += CodedOutputStream.computeStringSize(4, var24);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.accesstoken)) {
         String var25 = var1.accesstoken;
         var2 += CodedOutputStream.computeStringSize(5, var25);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.launchinfo)) {
         Integer var26 = var1.launchinfo;
         var2 += CodedOutputStream.computeInt32Size(6, var26);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var27 = var1.dungeonguid;
         var2 += CodedOutputStream.computeUInt64Size(7, var27);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.deviceinfo)) {
         PT_CLIENTINFO var28 = var1.deviceinfo;
         var2 += CodedConstant.computeSize(8, var28, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.registeredchannelid)) {
         String var29 = var1.registeredchannelid;
         var2 += CodedOutputStream.computeStringSize(9, var29);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.installchannelid)) {
         String var30 = var1.installchannelid;
         var2 += CodedOutputStream.computeStringSize(10, var30);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.isexternpackage)) {
         Boolean var31 = var1.isexternpackage;
         var2 += CodedOutputStream.computeBoolSize(11, var31);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.validusercheckcode)) {
         String var32 = var1.validusercheckcode;
         var2 += CodedOutputStream.computeStringSize(12, var32);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.toyPlatID)) {
         Integer var33 = var1.toyPlatID;
         var2 += CodedOutputStream.computeInt32Size(13, var33);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.countrycode)) {
         String var34 = var1.countrycode;
         var2 += CodedOutputStream.computeStringSize(14, var34);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.language)) {
         String var35 = var1.language;
         var2 += CodedOutputStream.computeStringSize(15, var35);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.adid)) {
         String var36 = var1.adid;
         var2 += CodedOutputStream.computeStringSize(16, var36);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.idfv)) {
         String var37 = var1.idfv;
         var2 += CodedOutputStream.computeStringSize(17, var37);
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.isadult)) {
         Boolean var38 = var1.isadult;
         var2 += CodedOutputStream.computeBoolSize(18, var38);
      }

      return var2;
   }

   public void doWriteTo(REQ_ENTER_CHANNEL var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.openid)) {
         String var21 = var1.openid;
         if (var21 != null) {
            var2.writeString(1, var21);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var22 = var1.charguid;
         if (var22 != null) {
            var2.writeUInt64(2, var22);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.authkey)) {
         String var23 = var1.authkey;
         if (var23 != null) {
            var2.writeString(3, var23);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.version)) {
         String var24 = var1.version;
         if (var24 != null) {
            var2.writeString(4, var24);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.accesstoken)) {
         String var25 = var1.accesstoken;
         if (var25 != null) {
            var2.writeString(5, var25);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.launchinfo)) {
         Integer var26 = var1.launchinfo;
         if (var26 != null) {
            var2.writeInt32(6, var26);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var27 = var1.dungeonguid;
         if (var27 != null) {
            var2.writeUInt64(7, var27);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.deviceinfo)) {
         PT_CLIENTINFO var28 = var1.deviceinfo;
         if (var28 != null) {
            CodedConstant.writeObject(var2, 8, FieldType.OBJECT, var28, false);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.registeredchannelid)) {
         String var29 = var1.registeredchannelid;
         if (var29 != null) {
            var2.writeString(9, var29);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.installchannelid)) {
         String var30 = var1.installchannelid;
         if (var30 != null) {
            var2.writeString(10, var30);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.isexternpackage)) {
         Boolean var31 = var1.isexternpackage;
         if (var31 != null) {
            var2.writeBool(11, var31);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.validusercheckcode)) {
         String var32 = var1.validusercheckcode;
         if (var32 != null) {
            var2.writeString(12, var32);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.toyPlatID)) {
         Integer var33 = var1.toyPlatID;
         if (var33 != null) {
            var2.writeInt32(13, var33);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.countrycode)) {
         String var34 = var1.countrycode;
         if (var34 != null) {
            var2.writeString(14, var34);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.language)) {
         String var35 = var1.language;
         if (var35 != null) {
            var2.writeString(15, var35);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.adid)) {
         String var36 = var1.adid;
         if (var36 != null) {
            var2.writeString(16, var36);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.idfv)) {
         String var37 = var1.idfv;
         if (var37 != null) {
            var2.writeString(17, var37);
         }
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.isadult)) {
         Boolean var38 = var1.isadult;
         if (var38 != null) {
            var2.writeBool(18, var38);
         }
      }

   }

   public void writeTo(REQ_ENTER_CHANNEL var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_ENTER_CHANNEL readFrom(CodedInputStream var1) throws IOException {
      REQ_ENTER_CHANNEL var2 = new REQ_ENTER_CHANNEL();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 10) {
               var2.openid = var1.readString();
            } else if (var5 == 16) {
               var2.charguid = var1.readUInt64();
            } else if (var5 == 26) {
               var2.authkey = var1.readString();
            } else if (var5 == 34) {
               var2.version = var1.readString();
            } else if (var5 == 42) {
               var2.accesstoken = var1.readString();
            } else if (var5 == 48) {
               var2.launchinfo = var1.readInt32();
            } else if (var5 == 56) {
               var2.dungeonguid = var1.readUInt64();
            } else if (var5 == 66) {
               Codec var10 = ProtobufProxy.create(PT_CLIENTINFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.deviceinfo = (PT_CLIENTINFO)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 74) {
               var2.registeredchannelid = var1.readString();
            } else if (var5 == 82) {
               var2.installchannelid = var1.readString();
            } else if (var5 == 88) {
               var2.isexternpackage = var1.readBool();
            } else if (var5 == 98) {
               var2.validusercheckcode = var1.readString();
            } else if (var5 == 104) {
               var2.toyPlatID = var1.readInt32();
            } else if (var5 == 114) {
               var2.countrycode = var1.readString();
            } else if (var5 == 122) {
               var2.language = var1.readString();
            } else if (var5 == 130) {
               var2.adid = var1.readString();
            } else if (var5 == 138) {
               var2.idfv = var1.readString();
            } else if (var5 == 144) {
               var2.isadult = var1.readBool();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_ENTER_CHANNEL.class);
         return this.descriptor = var1;
      }
   }
}
