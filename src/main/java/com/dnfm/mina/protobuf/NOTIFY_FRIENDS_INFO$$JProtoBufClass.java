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

public class NOTIFY_FRIENDS_INFO$$JProtoBufClass implements Codec<NOTIFY_FRIENDS_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(NOTIFY_FRIENDS_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public NOTIFY_FRIENDS_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(NOTIFY_FRIENDS_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var6 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var6);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.type)) {
         ENUM_NOTIFY_FRIEND_INFO_TYPE.T var7 = var1.type;
         var2 += CodedOutputStream.computeEnumSize(2, ((ENUM_NOTIFY_FRIEND_INFO_TYPE.T)var7).value());
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.friendinfo)) {
         PT_FRIEND_INFO var8 = var1.friendinfo;
         var2 += CodedConstant.computeSize(3, var8, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      return var2;
   }

   public void doWriteTo(NOTIFY_FRIENDS_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var6 = var1.error;
         if (var6 != null) {
            var2.writeInt32(1, var6);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.type)) {
         ENUM_NOTIFY_FRIEND_INFO_TYPE.T var7 = var1.type;
         if (var7 != null) {
            var2.writeEnum(2, ((ENUM_NOTIFY_FRIEND_INFO_TYPE.T)var7).value());
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.friendinfo)) {
         PT_FRIEND_INFO var8 = var1.friendinfo;
         if (var8 != null) {
            CodedConstant.writeObject(var2, 3, FieldType.OBJECT, var8, false);
         }
      }

   }

   public void writeTo(NOTIFY_FRIENDS_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public NOTIFY_FRIENDS_INFO readFrom(CodedInputStream var1) throws IOException {
      NOTIFY_FRIENDS_INFO var2 = new NOTIFY_FRIENDS_INFO();
      var2.type = (ENUM_NOTIFY_FRIEND_INFO_TYPE.T)CodedConstant.getEnumValue(ENUM_NOTIFY_FRIEND_INFO_TYPE.T.class, ENUM_NOTIFY_FRIEND_INFO_TYPE.T.values()[0].name());

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
               var2.type = (ENUM_NOTIFY_FRIEND_INFO_TYPE.T)CodedConstant.getEnumValue(ENUM_NOTIFY_FRIEND_INFO_TYPE.T.class, CodedConstant.getEnumName(ENUM_NOTIFY_FRIEND_INFO_TYPE.T.values(), var1.readEnum()));
            } else if (var5 == 26) {
               Codec var10 = ProtobufProxy.create(PT_FRIEND_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.friendinfo = (PT_FRIEND_INFO)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(NOTIFY_FRIENDS_INFO.class);
         return this.descriptor = var1;
      }
   }
}
