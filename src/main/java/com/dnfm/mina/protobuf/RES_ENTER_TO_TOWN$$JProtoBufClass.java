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

public class RES_ENTER_TO_TOWN$$JProtoBufClass implements Codec<RES_ENTER_TO_TOWN>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_ENTER_TO_TOWN var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_ENTER_TO_TOWN decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_ENTER_TO_TOWN var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var13 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var13);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.town)) {
         Integer var14 = var1.town;
         var2 += CodedOutputStream.computeUInt32Size(2, var14);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.area)) {
         Integer var15 = var1.area;
         var2 += CodedOutputStream.computeUInt32Size(3, var15);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.posx)) {
         Integer var16 = var1.posx;
         var2 += CodedOutputStream.computeInt32Size(4, var16);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.posy)) {
         Integer var17 = var1.posy;
         var2 += CodedOutputStream.computeInt32Size(5, var17);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var18 = var1.partyguid;
         var2 += CodedOutputStream.computeUInt64Size(6, var18);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.servertime)) {
         Long var19 = var1.servertime;
         var2 += CodedOutputStream.computeUInt64Size(7, var19);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.expratio)) {
         Integer var20 = var1.expratio;
         var2 += CodedOutputStream.computeInt32Size(8, var20);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.fatigueratio)) {
         Integer var21 = var1.fatigueratio;
         var2 += CodedOutputStream.computeInt32Size(9, var21);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.version)) {
         String var22 = var1.version;
         var2 += CodedOutputStream.computeStringSize(10, var22);
      }

      return var2;
   }

   public void doWriteTo(RES_ENTER_TO_TOWN var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var13 = var1.error;
         if (var13 != null) {
            var2.writeInt32(1, var13);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.town)) {
         Integer var14 = var1.town;
         if (var14 != null) {
            var2.writeUInt32(2, var14);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.area)) {
         Integer var15 = var1.area;
         if (var15 != null) {
            var2.writeUInt32(3, var15);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.posx)) {
         Integer var16 = var1.posx;
         if (var16 != null) {
            var2.writeInt32(4, var16);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.posy)) {
         Integer var17 = var1.posy;
         if (var17 != null) {
            var2.writeInt32(5, var17);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var18 = var1.partyguid;
         if (var18 != null) {
            var2.writeUInt64(6, var18);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.servertime)) {
         Long var19 = var1.servertime;
         if (var19 != null) {
            var2.writeUInt64(7, var19);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.expratio)) {
         Integer var20 = var1.expratio;
         if (var20 != null) {
            var2.writeInt32(8, var20);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.fatigueratio)) {
         Integer var21 = var1.fatigueratio;
         if (var21 != null) {
            var2.writeInt32(9, var21);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.version)) {
         String var22 = var1.version;
         if (var22 != null) {
            var2.writeString(10, var22);
         }
      }

   }

   public void writeTo(RES_ENTER_TO_TOWN var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_ENTER_TO_TOWN readFrom(CodedInputStream var1) throws IOException {
      RES_ENTER_TO_TOWN var2 = new RES_ENTER_TO_TOWN();

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
               var2.town = var1.readUInt32();
            } else if (var5 == 24) {
               var2.area = var1.readUInt32();
            } else if (var5 == 32) {
               var2.posx = var1.readInt32();
            } else if (var5 == 40) {
               var2.posy = var1.readInt32();
            } else if (var5 == 48) {
               var2.partyguid = var1.readUInt64();
            } else if (var5 == 56) {
               var2.servertime = var1.readUInt64();
            } else if (var5 == 64) {
               var2.expratio = var1.readInt32();
            } else if (var5 == 72) {
               var2.fatigueratio = var1.readInt32();
            } else if (var5 == 82) {
               var2.version = var1.readString();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_ENTER_TO_TOWN.class);
         return this.descriptor = var1;
      }
   }
}
