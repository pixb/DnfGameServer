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

public class CHAT_BASE$$JProtoBufClass implements Codec<CHAT_BASE>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(CHAT_BASE var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public CHAT_BASE decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(CHAT_BASE var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.mType)) {
         Integer var11 = var1.mType;
         var2 += CodedOutputStream.computeInt32Size(1, var11);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.mMsg)) {
         String var12 = var1.mMsg;
         var2 += CodedOutputStream.computeStringSize(2, var12);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.mVoice)) {
         String var13 = var1.mVoice;
         var2 += CodedOutputStream.computeStringSize(3, var13);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.mVoidcetime)) {
         String var14 = var1.mVoidcetime;
         var2 += CodedOutputStream.computeStringSize(4, var14);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.hyperlinktype)) {
         Integer var15 = var1.hyperlinktype;
         var2 += CodedOutputStream.computeInt32Size(5, var15);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.hyperlinksubtype)) {
         Integer var16 = var1.hyperlinksubtype;
         var2 += CodedOutputStream.computeInt32Size(6, var16);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.hyperlinkdatas)) {
         List var17 = var1.hyperlinkdatas;
         var2 += CodedConstant.computeListSize(7, var17, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.sub)) {
         List var18 = var1.sub;
         var2 += CodedConstant.computeListSize(8, var18, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(CHAT_BASE var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.mType)) {
         Integer var11 = var1.mType;
         if (var11 != null) {
            var2.writeInt32(1, var11);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.mMsg)) {
         String var12 = var1.mMsg;
         if (var12 != null) {
            var2.writeString(2, var12);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.mVoice)) {
         String var13 = var1.mVoice;
         if (var13 != null) {
            var2.writeString(3, var13);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.mVoidcetime)) {
         String var14 = var1.mVoidcetime;
         if (var14 != null) {
            var2.writeString(4, var14);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.hyperlinktype)) {
         Integer var15 = var1.hyperlinktype;
         if (var15 != null) {
            var2.writeInt32(5, var15);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.hyperlinksubtype)) {
         Integer var16 = var1.hyperlinksubtype;
         if (var16 != null) {
            var2.writeInt32(6, var16);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.hyperlinkdatas)) {
         List var17 = var1.hyperlinkdatas;
         if (var17 != null) {
            CodedConstant.writeToList(var2, 7, FieldType.OBJECT, var17, false);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.sub)) {
         List var18 = var1.sub;
         if (var18 != null) {
            CodedConstant.writeToList(var2, 8, FieldType.OBJECT, var18, false);
         }
      }

   }

   public void writeTo(CHAT_BASE var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public CHAT_BASE readFrom(CodedInputStream var1) throws IOException {
      CHAT_BASE var2 = new CHAT_BASE();
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
               var2.mType = var1.readInt32();
            } else if (var5 == 18) {
               var2.mMsg = var1.readString();
            } else if (var5 == 26) {
               var2.mVoice = var1.readString();
            } else if (var5 == 34) {
               var2.mVoidcetime = var1.readString();
            } else if (var5 == 40) {
               var2.hyperlinktype = var1.readInt32();
            } else if (var5 == 48) {
               var2.hyperlinksubtype = var1.readInt32();
            } else if (var5 == 58) {
               Codec var10 = ProtobufProxy.create(SUBSYSTEM.Types.PT_HYPERLINK_DATA.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.hyperlinkdatas == null) {
                  var2.hyperlinkdatas = new ArrayList();
               }

               var2.hyperlinkdatas.add((SUBSYSTEM.Types.PT_HYPERLINK_DATA)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 66) {
               Codec var11 = ProtobufProxy.create(SUBSYSTEM.Types.PT_HYPERLINK_SYSTEMMESSAGE_SUB.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var12 = var1.readRawVarint32();
               int var13 = var1.pushLimit(var12);
               if (var2.sub == null) {
                  var2.sub = new ArrayList();
               }

               var2.sub.add((SUBSYSTEM.Types.PT_HYPERLINK_SYSTEMMESSAGE_SUB)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var13);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(CHAT_BASE.class);
         return this.descriptor = var1;
      }
   }
}
