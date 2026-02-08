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

public class PT_RES_RAID_NODE_VARIABLE$$JProtoBufClass implements Codec<PT_RES_RAID_NODE_VARIABLE>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_RES_RAID_NODE_VARIABLE var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_RES_RAID_NODE_VARIABLE decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_RES_RAID_NODE_VARIABLE var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.nodename)) {
         String var6 = var1.nodename;
         var2 += CodedOutputStream.computeStringSize(1, var6);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var7 = var1.name;
         var2 += CodedOutputStream.computeStringSize(2, var7);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.value)) {
         String var8 = var1.value;
         var2 += CodedOutputStream.computeStringSize(3, var8);
      }

      return var2;
   }

   public void doWriteTo(PT_RES_RAID_NODE_VARIABLE var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.nodename)) {
         String var6 = var1.nodename;
         if (var6 != null) {
            var2.writeString(1, var6);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var7 = var1.name;
         if (var7 != null) {
            var2.writeString(2, var7);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.value)) {
         String var8 = var1.value;
         if (var8 != null) {
            var2.writeString(3, var8);
         }
      }

   }

   public void writeTo(PT_RES_RAID_NODE_VARIABLE var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_RES_RAID_NODE_VARIABLE readFrom(CodedInputStream var1) throws IOException {
      PT_RES_RAID_NODE_VARIABLE var2 = new PT_RES_RAID_NODE_VARIABLE();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 10) {
               var2.nodename = var1.readString();
            } else if (var5 == 18) {
               var2.name = var1.readString();
            } else if (var5 == 26) {
               var2.value = var1.readString();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_RES_RAID_NODE_VARIABLE.class);
         return this.descriptor = var1;
      }
   }
}
