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

public class REQ_ARCADE_PVP_BUY_TICKET$$JProtoBufClass implements Codec<REQ_ARCADE_PVP_BUY_TICKET>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_ARCADE_PVP_BUY_TICKET var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_ARCADE_PVP_BUY_TICKET decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_ARCADE_PVP_BUY_TICKET var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var4 = var1.count;
         var2 += CodedOutputStream.computeInt32Size(1, var4);
      }

      return var2;
   }

   public void doWriteTo(REQ_ARCADE_PVP_BUY_TICKET var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var4 = var1.count;
         if (var4 != null) {
            var2.writeInt32(1, var4);
         }
      }

   }

   public void writeTo(REQ_ARCADE_PVP_BUY_TICKET var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_ARCADE_PVP_BUY_TICKET readFrom(CodedInputStream var1) throws IOException {
      REQ_ARCADE_PVP_BUY_TICKET var2 = new REQ_ARCADE_PVP_BUY_TICKET();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.count = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_ARCADE_PVP_BUY_TICKET.class);
         return this.descriptor = var1;
      }
   }
}
