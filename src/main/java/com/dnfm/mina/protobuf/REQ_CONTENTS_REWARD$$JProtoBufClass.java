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

public class REQ_CONTENTS_REWARD$$JProtoBufClass implements Codec<REQ_CONTENTS_REWARD>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_CONTENTS_REWARD var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_CONTENTS_REWARD decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_CONTENTS_REWARD var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.items)) {
         List var6 = var1.items;
         var2 += CodedConstant.computeListSize(1, var6, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.currency)) {
         PT_CURRENCY_REWARD_INFO var7 = var1.currency;
         var2 += CodedConstant.computeSize(2, var7, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.ticket)) {
         PT_TICKET_REWARD_INFO var8 = var1.ticket;
         var2 += CodedConstant.computeSize(3, var8, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      return var2;
   }

   public void doWriteTo(REQ_CONTENTS_REWARD var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.items)) {
         List var6 = var1.items;
         if (var6 != null) {
            CodedConstant.writeToList(var2, 1, FieldType.OBJECT, var6, false);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.currency)) {
         PT_CURRENCY_REWARD_INFO var7 = var1.currency;
         if (var7 != null) {
            CodedConstant.writeObject(var2, 2, FieldType.OBJECT, var7, false);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.ticket)) {
         PT_TICKET_REWARD_INFO var8 = var1.ticket;
         if (var8 != null) {
            CodedConstant.writeObject(var2, 3, FieldType.OBJECT, var8, false);
         }
      }

   }

   public void writeTo(REQ_CONTENTS_REWARD var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_CONTENTS_REWARD readFrom(CodedInputStream var1) throws IOException {
      REQ_CONTENTS_REWARD var2 = new REQ_CONTENTS_REWARD();
      var2.items = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 10) {
               Codec var10 = ProtobufProxy.create(PT_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.items == null) {
                  var2.items = new ArrayList();
               }

               var2.items.add((PT_ITEM)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 18) {
               Codec var11 = ProtobufProxy.create(PT_CURRENCY_REWARD_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var13 = var1.readRawVarint32();
               int var15 = var1.pushLimit(var13);
               var2.currency = (PT_CURRENCY_REWARD_INFO)var11.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var15);
            } else if (var5 == 26) {
               Codec var12 = ProtobufProxy.create(PT_TICKET_REWARD_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var14 = var1.readRawVarint32();
               int var16 = var1.pushLimit(var14);
               var2.ticket = (PT_TICKET_REWARD_INFO)var12.readFrom(var1);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_CONTENTS_REWARD.class);
         return this.descriptor = var1;
      }
   }
}
