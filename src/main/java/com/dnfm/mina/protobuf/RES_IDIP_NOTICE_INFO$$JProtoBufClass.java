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

public class RES_IDIP_NOTICE_INFO$$JProtoBufClass implements Codec<RES_IDIP_NOTICE_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_IDIP_NOTICE_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_IDIP_NOTICE_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_IDIP_NOTICE_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var12 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var12);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var13 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(2, var13);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.starttime)) {
         Long var14 = var1.starttime;
         var2 += CodedOutputStream.computeInt64Size(3, var14);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.endtime)) {
         Long var15 = var1.endtime;
         var2 += CodedOutputStream.computeInt64Size(4, var15);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.frequency)) {
         Integer var16 = var1.frequency;
         var2 += CodedOutputStream.computeInt32Size(5, var16);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.content)) {
         String var17 = var1.content;
         var2 += CodedOutputStream.computeStringSize(6, var17);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var18 = var1.guid;
         var2 += CodedOutputStream.computeUInt64Size(7, var18);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.action)) {
         Integer var19 = var1.action;
         var2 += CodedOutputStream.computeInt32Size(8, var19);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.title)) {
         String var20 = var1.title;
         var2 += CodedOutputStream.computeStringSize(9, var20);
      }

      return var2;
   }

   public void doWriteTo(RES_IDIP_NOTICE_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var12 = var1.error;
         if (var12 != null) {
            var2.writeInt32(1, var12);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var13 = var1.type;
         if (var13 != null) {
            var2.writeInt32(2, var13);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.starttime)) {
         Long var14 = var1.starttime;
         if (var14 != null) {
            var2.writeInt64(3, var14);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.endtime)) {
         Long var15 = var1.endtime;
         if (var15 != null) {
            var2.writeInt64(4, var15);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.frequency)) {
         Integer var16 = var1.frequency;
         if (var16 != null) {
            var2.writeInt32(5, var16);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.content)) {
         String var17 = var1.content;
         if (var17 != null) {
            var2.writeString(6, var17);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var18 = var1.guid;
         if (var18 != null) {
            var2.writeUInt64(7, var18);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.action)) {
         Integer var19 = var1.action;
         if (var19 != null) {
            var2.writeInt32(8, var19);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.title)) {
         String var20 = var1.title;
         if (var20 != null) {
            var2.writeString(9, var20);
         }
      }

   }

   public void writeTo(RES_IDIP_NOTICE_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_IDIP_NOTICE_INFO readFrom(CodedInputStream var1) throws IOException {
      RES_IDIP_NOTICE_INFO var2 = new RES_IDIP_NOTICE_INFO();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.error = var1.readInt32();
            } else if (var5 == 16) {
               var2.type = var1.readInt32();
            } else if (var5 == 24) {
               var2.starttime = var1.readInt64();
            } else if (var5 == 32) {
               var2.endtime = var1.readInt64();
            } else if (var5 == 40) {
               var2.frequency = var1.readInt32();
            } else if (var5 == 50) {
               var2.content = var1.readString();
            } else if (var5 == 56) {
               var2.guid = var1.readUInt64();
            } else if (var5 == 64) {
               var2.action = var1.readInt32();
            } else if (var5 == 74) {
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_IDIP_NOTICE_INFO.class);
         return this.descriptor = var1;
      }
   }
}
