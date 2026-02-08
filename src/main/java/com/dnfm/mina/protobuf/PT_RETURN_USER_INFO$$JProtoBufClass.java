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

public class PT_RETURN_USER_INFO$$JProtoBufClass implements Codec<PT_RETURN_USER_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_RETURN_USER_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_RETURN_USER_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_RETURN_USER_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.selectedCharguid)) {
         Long var9 = var1.selectedCharguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var9);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.selectableDateStart)) {
         Long var10 = var1.selectableDateStart;
         var2 += CodedOutputStream.computeInt64Size(2, var10);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.selectableDateEnd)) {
         Long var11 = var1.selectableDateEnd;
         var2 += CodedOutputStream.computeInt64Size(3, var11);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.missionDateStart)) {
         Long var12 = var1.missionDateStart;
         var2 += CodedOutputStream.computeInt64Size(4, var12);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.missionDateEnd)) {
         Long var13 = var1.missionDateEnd;
         var2 += CodedOutputStream.computeInt64Size(5, var13);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.seasonpassFlag)) {
         Integer var14 = var1.seasonpassFlag;
         var2 += CodedOutputStream.computeInt32Size(6, var14);
      }

      return var2;
   }

   public void doWriteTo(PT_RETURN_USER_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.selectedCharguid)) {
         Long var9 = var1.selectedCharguid;
         if (var9 != null) {
            var2.writeUInt64(1, var9);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.selectableDateStart)) {
         Long var10 = var1.selectableDateStart;
         if (var10 != null) {
            var2.writeInt64(2, var10);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.selectableDateEnd)) {
         Long var11 = var1.selectableDateEnd;
         if (var11 != null) {
            var2.writeInt64(3, var11);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.missionDateStart)) {
         Long var12 = var1.missionDateStart;
         if (var12 != null) {
            var2.writeInt64(4, var12);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.missionDateEnd)) {
         Long var13 = var1.missionDateEnd;
         if (var13 != null) {
            var2.writeInt64(5, var13);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.seasonpassFlag)) {
         Integer var14 = var1.seasonpassFlag;
         if (var14 != null) {
            var2.writeInt32(6, var14);
         }
      }

   }

   public void writeTo(PT_RETURN_USER_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_RETURN_USER_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_RETURN_USER_INFO var2 = new PT_RETURN_USER_INFO();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.selectedCharguid = var1.readUInt64();
            } else if (var5 == 16) {
               var2.selectableDateStart = var1.readInt64();
            } else if (var5 == 24) {
               var2.selectableDateEnd = var1.readInt64();
            } else if (var5 == 32) {
               var2.missionDateStart = var1.readInt64();
            } else if (var5 == 40) {
               var2.missionDateEnd = var1.readInt64();
            } else if (var5 == 48) {
               var2.seasonpassFlag = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_RETURN_USER_INFO.class);
         return this.descriptor = var1;
      }
   }
}
