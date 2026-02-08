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

public class REQ_RECOMMAND_GROUP$$JProtoBufClass implements Codec<REQ_RECOMMAND_GROUP>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_RECOMMAND_GROUP var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_RECOMMAND_GROUP decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_RECOMMAND_GROUP var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.types)) {
         List var10 = var1.types;
         var2 += CodedConstant.computeListSize(1, var10, FieldType.INT32, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.list)) {
         List var11 = var1.list;
         var2 += CodedConstant.computeListSize(2, var11, FieldType.UINT64, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var12 = var1.dungeonindex;
         var2 += CodedOutputStream.computeInt32Size(3, var12);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.dungeongrade)) {
         Integer var13 = var1.dungeongrade;
         var2 += CodedOutputStream.computeInt32Size(4, var13);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.friendseq)) {
         Integer var14 = var1.friendseq;
         var2 += CodedOutputStream.computeInt32Size(5, var14);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.invitetype)) {
         ENUM_PARTY_INVITE_TYPE.T var15 = var1.invitetype;
         var2 += CodedOutputStream.computeEnumSize(6, ((ENUM_PARTY_INVITE_TYPE.T)var15).value());
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var16 = var1.name;
         var2 += CodedOutputStream.computeStringSize(7, var16);
      }

      return var2;
   }

   public void doWriteTo(REQ_RECOMMAND_GROUP var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.types)) {
         List var10 = var1.types;
         if (var10 != null) {
            CodedConstant.writeToList(var2, 1, FieldType.INT32, var10, true);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.list)) {
         List var11 = var1.list;
         if (var11 != null) {
            CodedConstant.writeToList(var2, 2, FieldType.UINT64, var11, true);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var12 = var1.dungeonindex;
         if (var12 != null) {
            var2.writeInt32(3, var12);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.dungeongrade)) {
         Integer var13 = var1.dungeongrade;
         if (var13 != null) {
            var2.writeInt32(4, var13);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.friendseq)) {
         Integer var14 = var1.friendseq;
         if (var14 != null) {
            var2.writeInt32(5, var14);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.invitetype)) {
         ENUM_PARTY_INVITE_TYPE.T var15 = var1.invitetype;
         if (var15 != null) {
            var2.writeEnum(6, ((ENUM_PARTY_INVITE_TYPE.T)var15).value());
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var16 = var1.name;
         if (var16 != null) {
            var2.writeString(7, var16);
         }
      }

   }

   public void writeTo(REQ_RECOMMAND_GROUP var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_RECOMMAND_GROUP readFrom(CodedInputStream var1) throws IOException {
      REQ_RECOMMAND_GROUP var2 = new REQ_RECOMMAND_GROUP();
      var2.types = new ArrayList();
      var2.list = new ArrayList();
      var2.invitetype = (ENUM_PARTY_INVITE_TYPE.T)CodedConstant.getEnumValue(ENUM_PARTY_INVITE_TYPE.T.class, ENUM_PARTY_INVITE_TYPE.T.values()[0].name());

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               if (var2.types == null) {
                  var2.types = new ArrayList();
               }

               var2.types.add(var1.readInt32());
            } else if (var5 == 10) {
               int var11 = var1.readRawVarint32();
               int var13 = var1.pushLimit(var11);
               if (var2.types == null) {
                  var2.types = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.types.add(var1.readInt32());
               }

               var1.popLimit(var13);
            } else if (var5 == 16) {
               if (var2.list == null) {
                  var2.list = new ArrayList();
               }

               var2.list.add(var1.readUInt64());
            } else if (var5 == 10) {
               int var10 = var1.readRawVarint32();
               int var12 = var1.pushLimit(var10);
               if (var2.types == null) {
                  var2.types = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.types.add(var1.readInt32());
               }

               var1.popLimit(var12);
            } else if (var5 != 18) {
               if (var5 == 24) {
                  var2.dungeonindex = var1.readInt32();
               } else if (var5 == 32) {
                  var2.dungeongrade = var1.readInt32();
               } else if (var5 == 40) {
                  var2.friendseq = var1.readInt32();
               } else if (var5 == 48) {
                  var2.invitetype = (ENUM_PARTY_INVITE_TYPE.T)CodedConstant.getEnumValue(ENUM_PARTY_INVITE_TYPE.T.class, CodedConstant.getEnumName(ENUM_PARTY_INVITE_TYPE.T.values(), var1.readEnum()));
               } else if (var5 == 58) {
                  var2.name = var1.readString();
               } else {
                  var1.skipField(var5);
               }
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.list == null) {
                  var2.list = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.list.add(var1.readUInt64());
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_RECOMMAND_GROUP.class);
         return this.descriptor = var1;
      }
   }
}
