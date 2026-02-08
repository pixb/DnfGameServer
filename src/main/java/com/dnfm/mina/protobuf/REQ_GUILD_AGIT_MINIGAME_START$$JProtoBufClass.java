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

public class REQ_GUILD_AGIT_MINIGAME_START$$JProtoBufClass implements Codec<REQ_GUILD_AGIT_MINIGAME_START>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_GUILD_AGIT_MINIGAME_START var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_GUILD_AGIT_MINIGAME_START decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_GUILD_AGIT_MINIGAME_START var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.type)) {
         ENUM_GUILD_AGIT_MINIGAME_TYPE.T var6 = var1.type;
         var2 += CodedOutputStream.computeEnumSize(1, ((ENUM_GUILD_AGIT_MINIGAME_TYPE.T)var6).value());
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.guilditem)) {
         PT_ITEM var7 = var1.guilditem;
         var2 += CodedConstant.computeSize(2, var7, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.areaindex)) {
         Integer var8 = var1.areaindex;
         var2 += CodedOutputStream.computeInt32Size(3, var8);
      }

      return var2;
   }

   public void doWriteTo(REQ_GUILD_AGIT_MINIGAME_START var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.type)) {
         ENUM_GUILD_AGIT_MINIGAME_TYPE.T var6 = var1.type;
         if (var6 != null) {
            var2.writeEnum(1, ((ENUM_GUILD_AGIT_MINIGAME_TYPE.T)var6).value());
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.guilditem)) {
         PT_ITEM var7 = var1.guilditem;
         if (var7 != null) {
            CodedConstant.writeObject(var2, 2, FieldType.OBJECT, var7, false);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.areaindex)) {
         Integer var8 = var1.areaindex;
         if (var8 != null) {
            var2.writeInt32(3, var8);
         }
      }

   }

   public void writeTo(REQ_GUILD_AGIT_MINIGAME_START var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_GUILD_AGIT_MINIGAME_START readFrom(CodedInputStream var1) throws IOException {
      REQ_GUILD_AGIT_MINIGAME_START var2 = new REQ_GUILD_AGIT_MINIGAME_START();
      var2.type = (ENUM_GUILD_AGIT_MINIGAME_TYPE.T)CodedConstant.getEnumValue(ENUM_GUILD_AGIT_MINIGAME_TYPE.T.class, ENUM_GUILD_AGIT_MINIGAME_TYPE.T.values()[0].name());

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.type = (ENUM_GUILD_AGIT_MINIGAME_TYPE.T)CodedConstant.getEnumValue(ENUM_GUILD_AGIT_MINIGAME_TYPE.T.class, CodedConstant.getEnumName(ENUM_GUILD_AGIT_MINIGAME_TYPE.T.values(), var1.readEnum()));
            } else if (var5 == 18) {
               Codec var10 = ProtobufProxy.create(PT_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.guilditem = (PT_ITEM)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 24) {
               var2.areaindex = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_GUILD_AGIT_MINIGAME_START.class);
         return this.descriptor = var1;
      }
   }
}
