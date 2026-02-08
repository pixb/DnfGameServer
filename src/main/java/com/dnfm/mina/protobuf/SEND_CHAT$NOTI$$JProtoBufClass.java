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

public class SEND_CHAT$NOTI$$JProtoBufClass implements Codec<SEND_CHAT.NOTI>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(SEND_CHAT.NOTI var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public SEND_CHAT.NOTI decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(SEND_CHAT.NOTI var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var5 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(1, var5);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.chat)) {
         String var6 = var1.chat;
         var2 += CodedOutputStream.computeStringSize(2, var6);
      }

      return var2;
   }

   public void doWriteTo(SEND_CHAT.NOTI var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var5 = var1.type;
         if (var5 != null) {
            var2.writeInt32(1, var5);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.chat)) {
         String var6 = var1.chat;
         if (var6 != null) {
            var2.writeString(2, var6);
         }
      }

   }

   public void writeTo(SEND_CHAT.NOTI var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public SEND_CHAT.NOTI readFrom(CodedInputStream var1) throws IOException {
      SEND_CHAT.NOTI var2 = new SEND_CHAT.NOTI();

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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(SEND_CHAT.NOTI.class);
         return this.descriptor = var1;
      }
   }
}
