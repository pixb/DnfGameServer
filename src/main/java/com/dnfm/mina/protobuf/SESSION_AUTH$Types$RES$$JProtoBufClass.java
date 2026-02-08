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

public class SESSION_AUTH$Types$RES$$JProtoBufClass implements Codec<SESSION_AUTH.Types.RES>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(SESSION_AUTH.Types.RES var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public SESSION_AUTH.Types.RES decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(SESSION_AUTH.Types.RES var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var8 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var8);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.serverType)) {
         Integer var9 = var1.serverType;
         var2 += CodedOutputStream.computeInt32Size(2, var9);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.serverKey)) {
         Long var10 = var1.serverKey;
         var2 += CodedOutputStream.computeInt64Size(3, var10);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.authIndex)) {
         Long var11 = var1.authIndex;
         var2 += CodedOutputStream.computeInt64Size(4, var11);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.standby)) {
         Boolean var12 = var1.standby;
         var2 += CodedOutputStream.computeBoolSize(5, var12);
      }

      return var2;
   }

   public void doWriteTo(SESSION_AUTH.Types.RES var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var8 = var1.error;
         if (var8 != null) {
            var2.writeInt32(1, var8);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.serverType)) {
         Integer var9 = var1.serverType;
         if (var9 != null) {
            var2.writeInt32(2, var9);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.serverKey)) {
         Long var10 = var1.serverKey;
         if (var10 != null) {
            var2.writeInt64(3, var10);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.authIndex)) {
         Long var11 = var1.authIndex;
         if (var11 != null) {
            var2.writeInt64(4, var11);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.standby)) {
         Boolean var12 = var1.standby;
         if (var12 != null) {
            var2.writeBool(5, var12);
         }
      }

   }

   public void writeTo(SESSION_AUTH.Types.RES var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public SESSION_AUTH.Types.RES readFrom(CodedInputStream var1) throws IOException {
      SESSION_AUTH.Types.RES var2 = new SESSION_AUTH.Types.RES();

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
               var2.serverType = var1.readInt32();
            } else if (var5 == 24) {
               var2.serverKey = var1.readInt64();
            } else if (var5 == 32) {
               var2.authIndex = var1.readInt64();
            } else if (var5 == 40) {
               var2.standby = var1.readBool();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(SESSION_AUTH.Types.RES.class);
         return this.descriptor = var1;
      }
   }
}
