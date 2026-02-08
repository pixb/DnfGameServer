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

public class RES_LOGIN$$JProtoBufClass implements Codec<RES_LOGIN>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_LOGIN var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_LOGIN decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_LOGIN var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var15 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var15);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.authkey)) {
         String var16 = var1.authkey;
         var2 += CodedOutputStream.computeStringSize(2, var16);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.accountkey)) {
         String var17 = var1.accountkey;
         var2 += CodedOutputStream.computeStringSize(3, var17);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.encrypt)) {
         Boolean var18 = var1.encrypt;
         var2 += CodedOutputStream.computeBoolSize(4, var18);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.servertime)) {
         Long var19 = var1.servertime;
         var2 += CodedOutputStream.computeUInt64Size(5, var19);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.localtime)) {
         String var20 = var1.localtime;
         var2 += CodedOutputStream.computeStringSize(6, var20);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.authority)) {
         Integer var21 = var1.authority;
         var2 += CodedOutputStream.computeUInt32Size(7, var21);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.key)) {
         String var22 = var1.key;
         var2 += CodedOutputStream.computeStringSize(8, var22);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.channel)) {
         List var23 = var1.channel;
         var2 += CodedConstant.computeListSize(9, var23, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.intrudeinfo)) {
         PT_INTRUDE_INFO var24 = var1.intrudeinfo;
         var2 += CodedConstant.computeSize(10, var24, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.worldid)) {
         Integer var25 = var1.worldid;
         var2 += CodedOutputStream.computeInt32Size(11, var25);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.seeds)) {
         List var26 = var1.seeds;
         var2 += CodedConstant.computeListSize(12, var26, FieldType.INT32, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      return var2;
   }

   public void doWriteTo(RES_LOGIN var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var15 = var1.error;
         if (var15 != null) {
            var2.writeInt32(1, var15);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.authkey)) {
         String var16 = var1.authkey;
         if (var16 != null) {
            var2.writeString(2, var16);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.accountkey)) {
         String var17 = var1.accountkey;
         if (var17 != null) {
            var2.writeString(3, var17);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.encrypt)) {
         Boolean var18 = var1.encrypt;
         if (var18 != null) {
            var2.writeBool(4, var18);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.servertime)) {
         Long var19 = var1.servertime;
         if (var19 != null) {
            var2.writeUInt64(5, var19);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.localtime)) {
         String var20 = var1.localtime;
         if (var20 != null) {
            var2.writeString(6, var20);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.authority)) {
         Integer var21 = var1.authority;
         if (var21 != null) {
            var2.writeUInt32(7, var21);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.key)) {
         String var22 = var1.key;
         if (var22 != null) {
            var2.writeString(8, var22);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.channel)) {
         List var23 = var1.channel;
         if (var23 != null) {
            CodedConstant.writeToList(var2, 9, FieldType.OBJECT, var23, false);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.intrudeinfo)) {
         PT_INTRUDE_INFO var24 = var1.intrudeinfo;
         if (var24 != null) {
            CodedConstant.writeObject(var2, 10, FieldType.OBJECT, var24, false);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.worldid)) {
         Integer var25 = var1.worldid;
         if (var25 != null) {
            var2.writeInt32(11, var25);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.seeds)) {
         List var26 = var1.seeds;
         if (var26 != null) {
            CodedConstant.writeToList(var2, 12, FieldType.INT32, var26, true);
         }
      }

   }

   public void writeTo(RES_LOGIN var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_LOGIN readFrom(CodedInputStream var1) throws IOException {
      RES_LOGIN var2 = new RES_LOGIN();
      var2.channel = new ArrayList();
      var2.seeds = new ArrayList();

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
            } else if (var5 == 18) {
               var2.authkey = var1.readString();
            } else if (var5 == 26) {
               var2.accountkey = var1.readString();
            } else if (var5 == 32) {
               var2.encrypt = var1.readBool();
            } else if (var5 == 40) {
               var2.servertime = var1.readUInt64();
            } else if (var5 == 50) {
               var2.localtime = var1.readString();
            } else if (var5 == 56) {
               var2.authority = var1.readUInt32();
            } else if (var5 == 66) {
               var2.key = var1.readString();
            } else if (var5 == 74) {
               Codec var11 = ProtobufProxy.create(PT_CHANNEL_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var13 = var1.readRawVarint32();
               int var15 = var1.pushLimit(var13);
               if (var2.channel == null) {
                  var2.channel = new ArrayList();
               }

               var2.channel.add((PT_CHANNEL_INFO)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var15);
            } else if (var5 == 82) {
               Codec var10 = ProtobufProxy.create(PT_INTRUDE_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var12 = var1.readRawVarint32();
               int var14 = var1.pushLimit(var12);
               var2.intrudeinfo = (PT_INTRUDE_INFO)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var14);
            } else if (var5 == 88) {
               var2.worldid = var1.readInt32();
            } else if (var5 == 96) {
               if (var2.seeds == null) {
                  var2.seeds = new ArrayList();
               }

               var2.seeds.add(var1.readInt32());
            } else if (var5 != 98) {
               var1.skipField(var5);
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.seeds == null) {
                  var2.seeds = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.seeds.add(var1.readInt32());
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_LOGIN.class);
         return this.descriptor = var1;
      }
   }
}
