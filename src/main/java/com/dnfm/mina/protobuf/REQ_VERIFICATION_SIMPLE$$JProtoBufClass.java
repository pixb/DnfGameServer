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

public class REQ_VERIFICATION_SIMPLE$$JProtoBufClass implements Codec<REQ_VERIFICATION_SIMPLE>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_VERIFICATION_SIMPLE var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_VERIFICATION_SIMPLE decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_VERIFICATION_SIMPLE var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var15 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var15);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var16 = var1.dungeonindex;
         var2 += CodedOutputStream.computeInt32Size(2, var16);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.mapindex)) {
         Integer var17 = var1.mapindex;
         var2 += CodedOutputStream.computeInt32Size(3, var17);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.gameworld)) {
         Integer var18 = var1.gameworld;
         var2 += CodedOutputStream.computeInt32Size(4, var18);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.gchannel)) {
         Integer var19 = var1.gchannel;
         var2 += CodedOutputStream.computeInt32Size(5, var19);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.triggerpacketid)) {
         Integer var20 = var1.triggerpacketid;
         var2 += CodedOutputStream.computeInt32Size(6, var20);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.error)) {
         List var21 = var1.error;
         var2 += CodedConstant.computeListSize(7, var21, FieldType.INT32, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.verifyguid)) {
         Long var22 = var1.verifyguid;
         var2 += CodedOutputStream.computeUInt64Size(8, var22);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.clientIP)) {
         String var23 = var1.clientIP;
         var2 += CodedOutputStream.computeStringSize(9, var23);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.gmclient)) {
         Boolean var24 = var1.gmclient;
         var2 += CodedOutputStream.computeBoolSize(10, var24);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.clientversion)) {
         String var25 = var1.clientversion;
         var2 += CodedOutputStream.computeStringSize(11, var25);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.verifytype)) {
         Integer var26 = var1.verifytype;
         var2 += CodedOutputStream.computeInt32Size(12, var26);
      }

      return var2;
   }

   public void doWriteTo(REQ_VERIFICATION_SIMPLE var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var15 = var1.charguid;
         if (var15 != null) {
            var2.writeUInt64(1, var15);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var16 = var1.dungeonindex;
         if (var16 != null) {
            var2.writeInt32(2, var16);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.mapindex)) {
         Integer var17 = var1.mapindex;
         if (var17 != null) {
            var2.writeInt32(3, var17);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.gameworld)) {
         Integer var18 = var1.gameworld;
         if (var18 != null) {
            var2.writeInt32(4, var18);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.gchannel)) {
         Integer var19 = var1.gchannel;
         if (var19 != null) {
            var2.writeInt32(5, var19);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.triggerpacketid)) {
         Integer var20 = var1.triggerpacketid;
         if (var20 != null) {
            var2.writeInt32(6, var20);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.error)) {
         List var21 = var1.error;
         if (var21 != null) {
            CodedConstant.writeToList(var2, 7, FieldType.INT32, var21, true);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.verifyguid)) {
         Long var22 = var1.verifyguid;
         if (var22 != null) {
            var2.writeUInt64(8, var22);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.clientIP)) {
         String var23 = var1.clientIP;
         if (var23 != null) {
            var2.writeString(9, var23);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.gmclient)) {
         Boolean var24 = var1.gmclient;
         if (var24 != null) {
            var2.writeBool(10, var24);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.clientversion)) {
         String var25 = var1.clientversion;
         if (var25 != null) {
            var2.writeString(11, var25);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.verifytype)) {
         Integer var26 = var1.verifytype;
         if (var26 != null) {
            var2.writeInt32(12, var26);
         }
      }

   }

   public void writeTo(REQ_VERIFICATION_SIMPLE var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_VERIFICATION_SIMPLE readFrom(CodedInputStream var1) throws IOException {
      REQ_VERIFICATION_SIMPLE var2 = new REQ_VERIFICATION_SIMPLE();
      var2.error = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.charguid = var1.readUInt64();
            } else if (var5 == 16) {
               var2.dungeonindex = var1.readInt32();
            } else if (var5 == 24) {
               var2.mapindex = var1.readInt32();
            } else if (var5 == 32) {
               var2.gameworld = var1.readInt32();
            } else if (var5 == 40) {
               var2.gchannel = var1.readInt32();
            } else if (var5 == 48) {
               var2.triggerpacketid = var1.readInt32();
            } else if (var5 == 56) {
               if (var2.error == null) {
                  var2.error = new ArrayList();
               }

               var2.error.add(var1.readInt32());
            } else if (var5 != 58) {
               if (var5 == 64) {
                  var2.verifyguid = var1.readUInt64();
               } else if (var5 == 74) {
                  var2.clientIP = var1.readString();
               } else if (var5 == 80) {
                  var2.gmclient = var1.readBool();
               } else if (var5 == 90) {
                  var2.clientversion = var1.readString();
               } else if (var5 == 96) {
                  var2.verifytype = var1.readInt32();
               } else {
                  var1.skipField(var5);
               }
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.error == null) {
                  var2.error = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.error.add(var1.readInt32());
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_VERIFICATION_SIMPLE.class);
         return this.descriptor = var1;
      }
   }
}
