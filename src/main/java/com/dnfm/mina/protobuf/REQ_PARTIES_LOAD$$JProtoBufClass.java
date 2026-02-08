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

public class REQ_PARTIES_LOAD$$JProtoBufClass implements Codec<REQ_PARTIES_LOAD>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_PARTIES_LOAD var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_PARTIES_LOAD decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_PARTIES_LOAD var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.type)) {
         ENUM_PARTY_LOAD_TYPES.T var12 = var1.type;
         var2 += CodedOutputStream.computeEnumSize(1, ((ENUM_PARTY_LOAD_TYPES.T)var12).value());
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.area)) {
         Integer var13 = var1.area;
         var2 += CodedOutputStream.computeInt32Size(2, var13);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.subtype)) {
         Integer var14 = var1.subtype;
         var2 += CodedOutputStream.computeInt32Size(3, var14);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.stageindex)) {
         Integer var15 = var1.stageindex;
         var2 += CodedOutputStream.computeInt32Size(4, var15);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var16 = var1.grade;
         var2 += CodedOutputStream.computeInt32Size(5, var16);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var17 = var1.dungeonindex;
         var2 += CodedOutputStream.computeInt32Size(6, var17);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.detail)) {
         Integer var18 = var1.detail;
         var2 += CodedOutputStream.computeInt32Size(7, var18);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.containobserver)) {
         Boolean var19 = var1.containobserver;
         var2 += CodedOutputStream.computeBoolSize(8, var19);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var20 = var1.name;
         var2 += CodedOutputStream.computeStringSize(9, var20);
      }

      return var2;
   }

   public void doWriteTo(REQ_PARTIES_LOAD var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.type)) {
         ENUM_PARTY_LOAD_TYPES.T var12 = var1.type;
         if (var12 != null) {
            var2.writeEnum(1, ((ENUM_PARTY_LOAD_TYPES.T)var12).value());
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.area)) {
         Integer var13 = var1.area;
         if (var13 != null) {
            var2.writeInt32(2, var13);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.subtype)) {
         Integer var14 = var1.subtype;
         if (var14 != null) {
            var2.writeInt32(3, var14);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.stageindex)) {
         Integer var15 = var1.stageindex;
         if (var15 != null) {
            var2.writeInt32(4, var15);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var16 = var1.grade;
         if (var16 != null) {
            var2.writeInt32(5, var16);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var17 = var1.dungeonindex;
         if (var17 != null) {
            var2.writeInt32(6, var17);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.detail)) {
         Integer var18 = var1.detail;
         if (var18 != null) {
            var2.writeInt32(7, var18);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.containobserver)) {
         Boolean var19 = var1.containobserver;
         if (var19 != null) {
            var2.writeBool(8, var19);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var20 = var1.name;
         if (var20 != null) {
            var2.writeString(9, var20);
         }
      }

   }

   public void writeTo(REQ_PARTIES_LOAD var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_PARTIES_LOAD readFrom(CodedInputStream var1) throws IOException {
      REQ_PARTIES_LOAD var2 = new REQ_PARTIES_LOAD();
      var2.type = (ENUM_PARTY_LOAD_TYPES.T)CodedConstant.getEnumValue(ENUM_PARTY_LOAD_TYPES.T.class, ENUM_PARTY_LOAD_TYPES.T.values()[0].name());

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.type = (ENUM_PARTY_LOAD_TYPES.T)CodedConstant.getEnumValue(ENUM_PARTY_LOAD_TYPES.T.class, CodedConstant.getEnumName(ENUM_PARTY_LOAD_TYPES.T.values(), var1.readEnum()));
            } else if (var5 == 16) {
               var2.area = var1.readInt32();
            } else if (var5 == 24) {
               var2.subtype = var1.readInt32();
            } else if (var5 == 32) {
               var2.stageindex = var1.readInt32();
            } else if (var5 == 40) {
               var2.grade = var1.readInt32();
            } else if (var5 == 48) {
               var2.dungeonindex = var1.readInt32();
            } else if (var5 == 56) {
               var2.detail = var1.readInt32();
            } else if (var5 == 64) {
               var2.containobserver = var1.readBool();
            } else if (var5 == 74) {
               var2.name = var1.readString();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_PARTIES_LOAD.class);
         return this.descriptor = var1;
      }
   }
}
