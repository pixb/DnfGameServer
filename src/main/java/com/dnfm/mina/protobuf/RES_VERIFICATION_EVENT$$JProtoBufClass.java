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

public class RES_VERIFICATION_EVENT$$JProtoBufClass implements Codec<RES_VERIFICATION_EVENT>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_VERIFICATION_EVENT var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_VERIFICATION_EVENT decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_VERIFICATION_EVENT var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var13 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var13);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var14 = var1.dungeonindex;
         var2 += CodedOutputStream.computeInt32Size(2, var14);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var15 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(3, var15);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.mpaindex)) {
         Integer var16 = var1.mpaindex;
         var2 += CodedOutputStream.computeInt32Size(4, var16);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.clientversion)) {
         String var17 = var1.clientversion;
         var2 += CodedOutputStream.computeStringSize(5, var17);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.verifytype)) {
         Integer var18 = var1.verifytype;
         var2 += CodedOutputStream.computeInt32Size(6, var18);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.clientIP)) {
         String var19 = var1.clientIP;
         var2 += CodedOutputStream.computeStringSize(7, var19);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.errvalue)) {
         List var20 = var1.errvalue;
         var2 += CodedConstant.computeListSize(8, var20, FieldType.INT32, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.errstring)) {
         String var21 = var1.errstring;
         var2 += CodedOutputStream.computeStringSize(9, var21);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.triggerpacketid)) {
         Integer var22 = var1.triggerpacketid;
         var2 += CodedOutputStream.computeInt32Size(10, var22);
      }

      return var2;
   }

   public void doWriteTo(RES_VERIFICATION_EVENT var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var13 = var1.error;
         if (var13 != null) {
            var2.writeInt32(1, var13);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var14 = var1.dungeonindex;
         if (var14 != null) {
            var2.writeInt32(2, var14);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var15 = var1.charguid;
         if (var15 != null) {
            var2.writeUInt64(3, var15);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.mpaindex)) {
         Integer var16 = var1.mpaindex;
         if (var16 != null) {
            var2.writeInt32(4, var16);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.clientversion)) {
         String var17 = var1.clientversion;
         if (var17 != null) {
            var2.writeString(5, var17);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.verifytype)) {
         Integer var18 = var1.verifytype;
         if (var18 != null) {
            var2.writeInt32(6, var18);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.clientIP)) {
         String var19 = var1.clientIP;
         if (var19 != null) {
            var2.writeString(7, var19);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.errvalue)) {
         List var20 = var1.errvalue;
         if (var20 != null) {
            CodedConstant.writeToList(var2, 8, FieldType.INT32, var20, true);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.errstring)) {
         String var21 = var1.errstring;
         if (var21 != null) {
            var2.writeString(9, var21);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.triggerpacketid)) {
         Integer var22 = var1.triggerpacketid;
         if (var22 != null) {
            var2.writeInt32(10, var22);
         }
      }

   }

   public void writeTo(RES_VERIFICATION_EVENT var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_VERIFICATION_EVENT readFrom(CodedInputStream var1) throws IOException {
      RES_VERIFICATION_EVENT var2 = new RES_VERIFICATION_EVENT();
      var2.errvalue = new ArrayList();

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
               var2.dungeonindex = var1.readInt32();
            } else if (var5 == 24) {
               var2.charguid = var1.readUInt64();
            } else if (var5 == 32) {
               var2.mpaindex = var1.readInt32();
            } else if (var5 == 42) {
               var2.clientversion = var1.readString();
            } else if (var5 == 48) {
               var2.verifytype = var1.readInt32();
            } else if (var5 == 58) {
               var2.clientIP = var1.readString();
            } else if (var5 == 64) {
               if (var2.errvalue == null) {
                  var2.errvalue = new ArrayList();
               }

               var2.errvalue.add(var1.readInt32());
            } else if (var5 != 66) {
               if (var5 == 74) {
                  var2.errstring = var1.readString();
               } else if (var5 == 80) {
                  var2.triggerpacketid = var1.readInt32();
               } else {
                  var1.skipField(var5);
               }
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.errvalue == null) {
                  var2.errvalue = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.errvalue.add(var1.readInt32());
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_VERIFICATION_EVENT.class);
         return this.descriptor = var1;
      }
   }
}
