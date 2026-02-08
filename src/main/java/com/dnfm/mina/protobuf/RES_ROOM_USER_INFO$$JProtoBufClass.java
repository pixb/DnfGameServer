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

public class RES_ROOM_USER_INFO$$JProtoBufClass implements Codec<RES_ROOM_USER_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_ROOM_USER_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_ROOM_USER_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_ROOM_USER_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var15 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var15);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.channel)) {
         Integer var16 = var1.channel;
         var2 += CodedOutputStream.computeInt32Size(2, var16);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var17 = var1.count;
         var2 += CodedOutputStream.computeInt32Size(3, var17);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.gchatguid)) {
         Long var18 = var1.gchatguid;
         var2 += CodedOutputStream.computeUInt64Size(4, var18);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var19 = var1.guid;
         var2 += CodedOutputStream.computeUInt64Size(5, var19);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.ip)) {
         String var20 = var1.ip;
         var2 += CodedOutputStream.computeStringSize(6, var20);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.port)) {
         Integer var21 = var1.port;
         var2 += CodedOutputStream.computeInt32Size(7, var21);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.type)) {
         ENUM_ROOM_TYPE.T var22 = var1.type;
         var2 += CodedOutputStream.computeEnumSize(8, ((ENUM_ROOM_TYPE.T)var22).value());
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.date)) {
         Long var23 = var1.date;
         var2 += CodedOutputStream.computeInt64Size(9, var23);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.leader)) {
         Long var24 = var1.leader;
         var2 += CodedOutputStream.computeUInt64Size(10, var24);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.users)) {
         List var25 = var1.users;
         var2 += CodedConstant.computeListSize(11, var25, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var26 = var1.world;
         var2 += CodedOutputStream.computeInt32Size(12, var26);
      }

      return var2;
   }

   public void doWriteTo(RES_ROOM_USER_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var15 = var1.error;
         if (var15 != null) {
            var2.writeInt32(1, var15);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.channel)) {
         Integer var16 = var1.channel;
         if (var16 != null) {
            var2.writeInt32(2, var16);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var17 = var1.count;
         if (var17 != null) {
            var2.writeInt32(3, var17);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.gchatguid)) {
         Long var18 = var1.gchatguid;
         if (var18 != null) {
            var2.writeUInt64(4, var18);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var19 = var1.guid;
         if (var19 != null) {
            var2.writeUInt64(5, var19);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.ip)) {
         String var20 = var1.ip;
         if (var20 != null) {
            var2.writeString(6, var20);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.port)) {
         Integer var21 = var1.port;
         if (var21 != null) {
            var2.writeInt32(7, var21);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.type)) {
         ENUM_ROOM_TYPE.T var22 = var1.type;
         if (var22 != null) {
            var2.writeEnum(8, ((ENUM_ROOM_TYPE.T)var22).value());
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.date)) {
         Long var23 = var1.date;
         if (var23 != null) {
            var2.writeInt64(9, var23);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.leader)) {
         Long var24 = var1.leader;
         if (var24 != null) {
            var2.writeUInt64(10, var24);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.users)) {
         List var25 = var1.users;
         if (var25 != null) {
            CodedConstant.writeToList(var2, 11, FieldType.OBJECT, var25, false);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var26 = var1.world;
         if (var26 != null) {
            var2.writeInt32(12, var26);
         }
      }

   }

   public void writeTo(RES_ROOM_USER_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_ROOM_USER_INFO readFrom(CodedInputStream var1) throws IOException {
      RES_ROOM_USER_INFO var2 = new RES_ROOM_USER_INFO();
      var2.users = new ArrayList();
      var2.type = (ENUM_ROOM_TYPE.T)CodedConstant.getEnumValue(ENUM_ROOM_TYPE.T.class, ENUM_ROOM_TYPE.T.values()[0].name());

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
               var2.channel = var1.readInt32();
            } else if (var5 == 24) {
               var2.count = var1.readInt32();
            } else if (var5 == 32) {
               var2.gchatguid = var1.readUInt64();
            } else if (var5 == 40) {
               var2.guid = var1.readUInt64();
            } else if (var5 == 50) {
               var2.ip = var1.readString();
            } else if (var5 == 56) {
               var2.port = var1.readInt32();
            } else if (var5 == 64) {
               var2.type = (ENUM_ROOM_TYPE.T)CodedConstant.getEnumValue(ENUM_ROOM_TYPE.T.class, CodedConstant.getEnumName(ENUM_ROOM_TYPE.T.values(), var1.readEnum()));
            } else if (var5 == 72) {
               var2.date = var1.readInt64();
            } else if (var5 == 80) {
               var2.leader = var1.readUInt64();
            } else if (var5 == 90) {
               Codec var10 = ProtobufProxy.create(PT_ROOM_USER_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.users == null) {
                  var2.users = new ArrayList();
               }

               var2.users.add((PT_ROOM_USER_INFO)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 96) {
               var2.world = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_ROOM_USER_INFO.class);
         return this.descriptor = var1;
      }
   }
}
