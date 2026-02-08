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

public class REQ_TOWN_CHAT$$JProtoBufClass implements Codec<REQ_TOWN_CHAT>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_TOWN_CHAT var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_TOWN_CHAT decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_TOWN_CHAT var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var14 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(1, var14);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.chat)) {
         String var15 = var1.chat;
         var2 += CodedOutputStream.computeStringSize(2, var15);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.voice)) {
         String var16 = var1.voice;
         var2 += CodedOutputStream.computeStringSize(3, var16);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.voicetime)) {
         String var17 = var1.voicetime;
         var2 += CodedOutputStream.computeStringSize(4, var17);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var18 = var1.name;
         var2 += CodedOutputStream.computeStringSize(5, var18);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var19 = var1.guid;
         var2 += CodedOutputStream.computeUInt64Size(6, var19);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.hyperlinktype)) {
         Integer var20 = var1.hyperlinktype;
         var2 += CodedOutputStream.computeInt32Size(7, var20);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.hyperlinksubtype)) {
         Integer var21 = var1.hyperlinksubtype;
         var2 += CodedOutputStream.computeInt32Size(8, var21);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.hyperlinkdatas)) {
         List var22 = var1.hyperlinkdatas;
         var2 += CodedConstant.computeListSize(9, var22, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.sub)) {
         List var23 = var1.sub;
         var2 += CodedConstant.computeListSize(10, var23, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.skinchatinfo)) {
         PT_SKIN_CHAT_INFO var24 = var1.skinchatinfo;
         var2 += CodedConstant.computeSize(11, var24, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      return var2;
   }

   public void doWriteTo(REQ_TOWN_CHAT var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var14 = var1.type;
         if (var14 != null) {
            var2.writeInt32(1, var14);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.chat)) {
         String var15 = var1.chat;
         if (var15 != null) {
            var2.writeString(2, var15);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.voice)) {
         String var16 = var1.voice;
         if (var16 != null) {
            var2.writeString(3, var16);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.voicetime)) {
         String var17 = var1.voicetime;
         if (var17 != null) {
            var2.writeString(4, var17);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var18 = var1.name;
         if (var18 != null) {
            var2.writeString(5, var18);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var19 = var1.guid;
         if (var19 != null) {
            var2.writeUInt64(6, var19);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.hyperlinktype)) {
         Integer var20 = var1.hyperlinktype;
         if (var20 != null) {
            var2.writeInt32(7, var20);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.hyperlinksubtype)) {
         Integer var21 = var1.hyperlinksubtype;
         if (var21 != null) {
            var2.writeInt32(8, var21);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.hyperlinkdatas)) {
         List var22 = var1.hyperlinkdatas;
         if (var22 != null) {
            CodedConstant.writeToList(var2, 9, FieldType.OBJECT, var22, false);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.sub)) {
         List var23 = var1.sub;
         if (var23 != null) {
            CodedConstant.writeToList(var2, 10, FieldType.OBJECT, var23, false);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.skinchatinfo)) {
         PT_SKIN_CHAT_INFO var24 = var1.skinchatinfo;
         if (var24 != null) {
            CodedConstant.writeObject(var2, 11, FieldType.OBJECT, var24, false);
         }
      }

   }

   public void writeTo(REQ_TOWN_CHAT var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_TOWN_CHAT readFrom(CodedInputStream var1) throws IOException {
      REQ_TOWN_CHAT var2 = new REQ_TOWN_CHAT();
      var2.hyperlinkdatas = new ArrayList();
      var2.sub = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.type = var1.readInt32();
            } else if (var5 == 18) {
               var2.chat = var1.readString();
            } else if (var5 == 26) {
               var2.voice = var1.readString();
            } else if (var5 == 34) {
               var2.voicetime = var1.readString();
            } else if (var5 == 42) {
               var2.name = var1.readString();
            } else if (var5 == 48) {
               var2.guid = var1.readUInt64();
            } else if (var5 == 56) {
               var2.hyperlinktype = var1.readInt32();
            } else if (var5 == 64) {
               var2.hyperlinksubtype = var1.readInt32();
            } else if (var5 == 74) {
               Codec var10 = ProtobufProxy.create(PT_HYPERLINK_DATA.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.hyperlinkdatas == null) {
                  var2.hyperlinkdatas = new ArrayList();
               }

               var2.hyperlinkdatas.add((PT_HYPERLINK_DATA)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 82) {
               Codec var11 = ProtobufProxy.create(PT_HYPERLINK_SYSTEMMESSAGE_SUB.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var13 = var1.readRawVarint32();
               int var15 = var1.pushLimit(var13);
               if (var2.sub == null) {
                  var2.sub = new ArrayList();
               }

               var2.sub.add((PT_HYPERLINK_SYSTEMMESSAGE_SUB)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var15);
            } else if (var5 == 90) {
               Codec var12 = ProtobufProxy.create(PT_SKIN_CHAT_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var14 = var1.readRawVarint32();
               int var16 = var1.pushLimit(var14);
               var2.skinchatinfo = (PT_SKIN_CHAT_INFO)var12.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var16);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_TOWN_CHAT.class);
         return this.descriptor = var1;
      }
   }
}
