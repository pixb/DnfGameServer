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

public class REQ_MESSAGE_ASSISTANT_SET_SWITCH$$JProtoBufClass implements Codec<REQ_MESSAGE_ASSISTANT_SET_SWITCH>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_MESSAGE_ASSISTANT_SET_SWITCH var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_MESSAGE_ASSISTANT_SET_SWITCH decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_MESSAGE_ASSISTANT_SET_SWITCH var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.switchstatus)) {
         ENUM_MESSAGE_ASSISTANT_SWITCH_STATUS.T var4 = var1.switchstatus;
         var2 += CodedOutputStream.computeEnumSize(1, ((ENUM_MESSAGE_ASSISTANT_SWITCH_STATUS.T)var4).value());
      }

      return var2;
   }

   public void doWriteTo(REQ_MESSAGE_ASSISTANT_SET_SWITCH var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.switchstatus)) {
         ENUM_MESSAGE_ASSISTANT_SWITCH_STATUS.T var4 = var1.switchstatus;
         if (var4 != null) {
            var2.writeEnum(1, ((ENUM_MESSAGE_ASSISTANT_SWITCH_STATUS.T)var4).value());
         }
      }

   }

   public void writeTo(REQ_MESSAGE_ASSISTANT_SET_SWITCH var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_MESSAGE_ASSISTANT_SET_SWITCH readFrom(CodedInputStream var1) throws IOException {
      REQ_MESSAGE_ASSISTANT_SET_SWITCH var2 = new REQ_MESSAGE_ASSISTANT_SET_SWITCH();
      var2.switchstatus = (ENUM_MESSAGE_ASSISTANT_SWITCH_STATUS.T)CodedConstant.getEnumValue(ENUM_MESSAGE_ASSISTANT_SWITCH_STATUS.T.class, ENUM_MESSAGE_ASSISTANT_SWITCH_STATUS.T.values()[0].name());

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.switchstatus = (ENUM_MESSAGE_ASSISTANT_SWITCH_STATUS.T)CodedConstant.getEnumValue(ENUM_MESSAGE_ASSISTANT_SWITCH_STATUS.T.class, CodedConstant.getEnumName(ENUM_MESSAGE_ASSISTANT_SWITCH_STATUS.T.values(), var1.readEnum()));
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_MESSAGE_ASSISTANT_SET_SWITCH.class);
         return this.descriptor = var1;
      }
   }
}
