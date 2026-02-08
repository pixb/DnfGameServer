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

public class PT_WEDDING_INVITATION$$JProtoBufClass implements Codec<PT_WEDDING_INVITATION>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_WEDDING_INVITATION var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_WEDDING_INVITATION decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_WEDDING_INVITATION var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.marriageguid)) {
         Long var10 = var1.marriageguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var10);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.inviterguid)) {
         Long var11 = var1.inviterguid;
         var2 += CodedOutputStream.computeUInt64Size(2, var11);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.invitername)) {
         String var12 = var1.invitername;
         var2 += CodedOutputStream.computeStringSize(3, var12);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.spouseguid)) {
         Long var13 = var1.spouseguid;
         var2 += CodedOutputStream.computeUInt64Size(4, var13);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.spousename)) {
         String var14 = var1.spousename;
         var2 += CodedOutputStream.computeStringSize(5, var14);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.weddinghallindex)) {
         Integer var15 = var1.weddinghallindex;
         var2 += CodedOutputStream.computeInt32Size(6, var15);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.weddingdate)) {
         Integer var16 = var1.weddingdate;
         var2 += CodedOutputStream.computeInt32Size(7, var16);
      }

      return var2;
   }

   public void doWriteTo(PT_WEDDING_INVITATION var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.marriageguid)) {
         Long var10 = var1.marriageguid;
         if (var10 != null) {
            var2.writeUInt64(1, var10);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.inviterguid)) {
         Long var11 = var1.inviterguid;
         if (var11 != null) {
            var2.writeUInt64(2, var11);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.invitername)) {
         String var12 = var1.invitername;
         if (var12 != null) {
            var2.writeString(3, var12);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.spouseguid)) {
         Long var13 = var1.spouseguid;
         if (var13 != null) {
            var2.writeUInt64(4, var13);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.spousename)) {
         String var14 = var1.spousename;
         if (var14 != null) {
            var2.writeString(5, var14);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.weddinghallindex)) {
         Integer var15 = var1.weddinghallindex;
         if (var15 != null) {
            var2.writeInt32(6, var15);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.weddingdate)) {
         Integer var16 = var1.weddingdate;
         if (var16 != null) {
            var2.writeInt32(7, var16);
         }
      }

   }

   public void writeTo(PT_WEDDING_INVITATION var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_WEDDING_INVITATION readFrom(CodedInputStream var1) throws IOException {
      PT_WEDDING_INVITATION var2 = new PT_WEDDING_INVITATION();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.marriageguid = var1.readUInt64();
            } else if (var5 == 16) {
               var2.inviterguid = var1.readUInt64();
            } else if (var5 == 26) {
               var2.invitername = var1.readString();
            } else if (var5 == 32) {
               var2.spouseguid = var1.readUInt64();
            } else if (var5 == 42) {
               var2.spousename = var1.readString();
            } else if (var5 == 48) {
               var2.weddinghallindex = var1.readInt32();
            } else if (var5 == 56) {
               var2.weddingdate = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_WEDDING_INVITATION.class);
         return this.descriptor = var1;
      }
   }
}
