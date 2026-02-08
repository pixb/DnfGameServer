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

public class IDIP_NOTICE_INFO$$JProtoBufClass implements Codec<IDIP_NOTICE_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(IDIP_NOTICE_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public IDIP_NOTICE_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(IDIP_NOTICE_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var10 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(1, var10);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.starttime)) {
         Long var11 = var1.starttime;
         var2 += CodedOutputStream.computeInt64Size(2, var11);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.endtime)) {
         Long var12 = var1.endtime;
         var2 += CodedOutputStream.computeInt64Size(3, var12);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.frequency)) {
         Integer var13 = var1.frequency;
         var2 += CodedOutputStream.computeInt32Size(4, var13);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.content)) {
         String var14 = var1.content;
         var2 += CodedOutputStream.computeStringSize(5, var14);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var15 = var1.guid;
         var2 += CodedOutputStream.computeUInt64Size(6, var15);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.title)) {
         String var16 = var1.title;
         var2 += CodedOutputStream.computeStringSize(7, var16);
      }

      return var2;
   }

   public void doWriteTo(IDIP_NOTICE_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var10 = var1.type;
         if (var10 != null) {
            var2.writeInt32(1, var10);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.starttime)) {
         Long var11 = var1.starttime;
         if (var11 != null) {
            var2.writeInt64(2, var11);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.endtime)) {
         Long var12 = var1.endtime;
         if (var12 != null) {
            var2.writeInt64(3, var12);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.frequency)) {
         Integer var13 = var1.frequency;
         if (var13 != null) {
            var2.writeInt32(4, var13);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.content)) {
         String var14 = var1.content;
         if (var14 != null) {
            var2.writeString(5, var14);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var15 = var1.guid;
         if (var15 != null) {
            var2.writeUInt64(6, var15);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.title)) {
         String var16 = var1.title;
         if (var16 != null) {
            var2.writeString(7, var16);
         }
      }

   }

   public void writeTo(IDIP_NOTICE_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public IDIP_NOTICE_INFO readFrom(CodedInputStream var1) throws IOException {
      IDIP_NOTICE_INFO var2 = new IDIP_NOTICE_INFO();

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
            } else if (var5 == 16) {
               var2.starttime = var1.readInt64();
            } else if (var5 == 24) {
               var2.endtime = var1.readInt64();
            } else if (var5 == 32) {
               var2.frequency = var1.readInt32();
            } else if (var5 == 42) {
               var2.content = var1.readString();
            } else if (var5 == 48) {
               var2.guid = var1.readUInt64();
            } else if (var5 == 58) {
               var2.title = var1.readString();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(IDIP_NOTICE_INFO.class);
         return this.descriptor = var1;
      }
   }
}
