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

public class REQ_START_GAME$$JProtoBufClass implements Codec<REQ_START_GAME>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_START_GAME var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_START_GAME decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_START_GAME var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var14 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var14);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var15 = var1.dungeonguid;
         var2 += CodedOutputStream.computeUInt64Size(2, var15);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.authkey)) {
         String var16 = var1.authkey;
         var2 += CodedOutputStream.computeStringSize(3, var16);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.accesstoken)) {
         String var17 = var1.accesstoken;
         var2 += CodedOutputStream.computeStringSize(4, var17);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.paytoken)) {
         String var18 = var1.paytoken;
         var2 += CodedOutputStream.computeStringSize(5, var18);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.town)) {
         Integer var19 = var1.town;
         var2 += CodedOutputStream.computeUInt32Size(6, var19);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.area)) {
         Integer var20 = var1.area;
         var2 += CodedOutputStream.computeUInt32Size(7, var20);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.posx)) {
         Integer var21 = var1.posx;
         var2 += CodedOutputStream.computeInt32Size(8, var21);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.posy)) {
         Integer var22 = var1.posy;
         var2 += CodedOutputStream.computeInt32Size(9, var22);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.request)) {
         List var23 = var1.request;
         var2 += CodedConstant.computeListSize(10, var23, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var24 = var1.partyguid;
         var2 += CodedOutputStream.computeUInt64Size(11, var24);
      }

      return var2;
   }

   public void doWriteTo(REQ_START_GAME var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var14 = var1.charguid;
         if (var14 != null) {
            var2.writeUInt64(1, var14);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var15 = var1.dungeonguid;
         if (var15 != null) {
            var2.writeUInt64(2, var15);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.authkey)) {
         String var16 = var1.authkey;
         if (var16 != null) {
            var2.writeString(3, var16);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.accesstoken)) {
         String var17 = var1.accesstoken;
         if (var17 != null) {
            var2.writeString(4, var17);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.paytoken)) {
         String var18 = var1.paytoken;
         if (var18 != null) {
            var2.writeString(5, var18);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.town)) {
         Integer var19 = var1.town;
         if (var19 != null) {
            var2.writeUInt32(6, var19);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.area)) {
         Integer var20 = var1.area;
         if (var20 != null) {
            var2.writeUInt32(7, var20);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.posx)) {
         Integer var21 = var1.posx;
         if (var21 != null) {
            var2.writeInt32(8, var21);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.posy)) {
         Integer var22 = var1.posy;
         if (var22 != null) {
            var2.writeInt32(9, var22);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.request)) {
         List var23 = var1.request;
         if (var23 != null) {
            CodedConstant.writeToList(var2, 10, FieldType.OBJECT, var23, false);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var24 = var1.partyguid;
         if (var24 != null) {
            var2.writeUInt64(11, var24);
         }
      }

   }

   public void writeTo(REQ_START_GAME var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_START_GAME readFrom(CodedInputStream var1) throws IOException {
      REQ_START_GAME var2 = new REQ_START_GAME();
      var2.request = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.charguid = var1.readUInt64();
            } else if (var5 == 16) {
               var2.dungeonguid = var1.readUInt64();
            } else if (var5 == 26) {
               var2.authkey = var1.readString();
            } else if (var5 == 34) {
               var2.accesstoken = var1.readString();
            } else if (var5 == 42) {
               var2.paytoken = var1.readString();
            } else if (var5 == 48) {
               var2.town = var1.readUInt32();
            } else if (var5 == 56) {
               var2.area = var1.readUInt32();
            } else if (var5 == 64) {
               var2.posx = var1.readInt32();
            } else if (var5 == 72) {
               var2.posy = var1.readInt32();
            } else if (var5 == 82) {
               Codec var10 = ProtobufProxy.create(PT_PROTOCOL_TRANSACTION.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.request == null) {
                  var2.request = new ArrayList();
               }

               var2.request.add((PT_PROTOCOL_TRANSACTION)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 88) {
               var2.partyguid = var1.readUInt64();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_START_GAME.class);
         return this.descriptor = var1;
      }
   }
}
