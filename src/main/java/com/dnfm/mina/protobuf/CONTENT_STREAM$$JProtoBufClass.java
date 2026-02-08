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

public class CONTENT_STREAM$$JProtoBufClass implements Codec<CONTENT_STREAM>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(CONTENT_STREAM var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public CONTENT_STREAM decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(CONTENT_STREAM var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Long var10 = var1.index;
         var2 += CodedOutputStream.computeUInt64Size(1, var10);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.users)) {
         List var11 = var1.users;
         var2 += CodedConstant.computeListSize(2, var11, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.roomState)) {
         Integer var12 = var1.roomState;
         var2 += CodedOutputStream.computeInt32Size(3, var12);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.version)) {
         String var13 = var1.version;
         var2 += CodedOutputStream.computeStringSize(4, var13);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.playFrame)) {
         Integer var14 = var1.playFrame;
         var2 += CodedOutputStream.computeUInt32Size(5, var14);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.winnerTeam)) {
         Integer var15 = var1.winnerTeam;
         var2 += CodedOutputStream.computeInt32Size(6, var15);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.disconnectUsers)) {
         List var16 = var1.disconnectUsers;
         var2 += CodedConstant.computeListSize(7, var16, FieldType.UINT64, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      return var2;
   }

   public void doWriteTo(CONTENT_STREAM var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Long var10 = var1.index;
         if (var10 != null) {
            var2.writeUInt64(1, var10);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.users)) {
         List var11 = var1.users;
         if (var11 != null) {
            CodedConstant.writeToList(var2, 2, FieldType.OBJECT, var11, false);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.roomState)) {
         Integer var12 = var1.roomState;
         if (var12 != null) {
            var2.writeInt32(3, var12);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.version)) {
         String var13 = var1.version;
         if (var13 != null) {
            var2.writeString(4, var13);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.playFrame)) {
         Integer var14 = var1.playFrame;
         if (var14 != null) {
            var2.writeUInt32(5, var14);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.winnerTeam)) {
         Integer var15 = var1.winnerTeam;
         if (var15 != null) {
            var2.writeInt32(6, var15);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.disconnectUsers)) {
         List var16 = var1.disconnectUsers;
         if (var16 != null) {
            CodedConstant.writeToList(var2, 7, FieldType.UINT64, var16, true);
         }
      }

   }

   public void writeTo(CONTENT_STREAM var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public CONTENT_STREAM readFrom(CodedInputStream var1) throws IOException {
      CONTENT_STREAM var2 = new CONTENT_STREAM();
      var2.users = new ArrayList();
      var2.disconnectUsers = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.index = var1.readUInt64();
            } else if (var5 == 18) {
               Codec var10 = ProtobufProxy.create(USER_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var11 = var1.readRawVarint32();
               int var12 = var1.pushLimit(var11);
               if (var2.users == null) {
                  var2.users = new ArrayList();
               }

               var2.users.add((USER_INFO)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var12);
            } else if (var5 == 24) {
               var2.roomState = var1.readInt32();
            } else if (var5 == 34) {
               var2.version = var1.readString();
            } else if (var5 == 40) {
               var2.playFrame = var1.readUInt32();
            } else if (var5 == 48) {
               var2.winnerTeam = var1.readInt32();
            } else if (var5 == 56) {
               if (var2.disconnectUsers == null) {
                  var2.disconnectUsers = new ArrayList();
               }

               var2.disconnectUsers.add(var1.readUInt64());
            } else if (var5 != 58) {
               var1.skipField(var5);
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.disconnectUsers == null) {
                  var2.disconnectUsers = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.disconnectUsers.add(var1.readUInt64());
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(CONTENT_STREAM.class);
         return this.descriptor = var1;
      }
   }
}
