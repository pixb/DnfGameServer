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

public class PT_WEDDING_THEME$$JProtoBufClass implements Codec<PT_WEDDING_THEME>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_WEDDING_THEME var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_WEDDING_THEME decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_WEDDING_THEME var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.weddinghall)) {
         Integer var8 = var1.weddinghall;
         var2 += CodedOutputStream.computeInt32Size(1, var8);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.officiant)) {
         Integer var9 = var1.officiant;
         var2 += CodedOutputStream.computeInt32Size(2, var9);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.guestlist)) {
         List var10 = var1.guestlist;
         var2 += CodedConstant.computeListSize(3, var10, FieldType.INT32, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.dress)) {
         Integer var11 = var1.dress;
         var2 += CodedOutputStream.computeInt32Size(4, var11);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.ceremonylist)) {
         List var12 = var1.ceremonylist;
         var2 += CodedConstant.computeListSize(5, var12, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(PT_WEDDING_THEME var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.weddinghall)) {
         Integer var8 = var1.weddinghall;
         if (var8 != null) {
            var2.writeInt32(1, var8);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.officiant)) {
         Integer var9 = var1.officiant;
         if (var9 != null) {
            var2.writeInt32(2, var9);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.guestlist)) {
         List var10 = var1.guestlist;
         if (var10 != null) {
            CodedConstant.writeToList(var2, 3, FieldType.INT32, var10, true);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.dress)) {
         Integer var11 = var1.dress;
         if (var11 != null) {
            var2.writeInt32(4, var11);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.ceremonylist)) {
         List var12 = var1.ceremonylist;
         if (var12 != null) {
            CodedConstant.writeToList(var2, 5, FieldType.OBJECT, var12, false);
         }
      }

   }

   public void writeTo(PT_WEDDING_THEME var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_WEDDING_THEME readFrom(CodedInputStream var1) throws IOException {
      PT_WEDDING_THEME var2 = new PT_WEDDING_THEME();
      var2.guestlist = new ArrayList();
      var2.ceremonylist = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.weddinghall = var1.readInt32();
            } else if (var5 == 16) {
               var2.officiant = var1.readInt32();
            } else if (var5 == 24) {
               if (var2.guestlist == null) {
                  var2.guestlist = new ArrayList();
               }

               var2.guestlist.add(var1.readInt32());
            } else if (var5 == 26) {
               int var12 = var1.readRawVarint32();
               int var14 = var1.pushLimit(var12);
               if (var2.guestlist == null) {
                  var2.guestlist = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.guestlist.add(var1.readInt32());
               }

               var1.popLimit(var14);
            } else if (var5 == 32) {
               var2.dress = var1.readInt32();
            } else if (var5 != 42) {
               var1.skipField(var5);
            } else if (var5 != 26) {
               Codec var10 = ProtobufProxy.create(PT_WEDDING_THEME_CEREMONY.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var11 = var1.readRawVarint32();
               int var13 = var1.pushLimit(var11);
               if (var2.ceremonylist == null) {
                  var2.ceremonylist = new ArrayList();
               }

               var2.ceremonylist.add((PT_WEDDING_THEME_CEREMONY)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var13);
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.guestlist == null) {
                  var2.guestlist = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.guestlist.add(var1.readInt32());
               }

               var1.popLimit(var7);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_WEDDING_THEME.class);
         return this.descriptor = var1;
      }
   }
}
