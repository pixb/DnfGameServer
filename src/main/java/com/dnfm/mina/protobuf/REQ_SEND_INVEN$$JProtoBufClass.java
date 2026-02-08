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

public class REQ_SEND_INVEN$$JProtoBufClass implements Codec<REQ_SEND_INVEN>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_SEND_INVEN var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_SEND_INVEN decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_SEND_INVEN var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.storage)) {
         Integer var18 = var1.storage;
         var2 += CodedOutputStream.computeInt32Size(1, var18);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.equipitems)) {
         List var19 = var1.equipitems;
         var2 += CodedConstant.computeListSize(2, var19, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.materialitems)) {
         List var20 = var1.materialitems;
         var2 += CodedConstant.computeListSize(3, var20, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.consumeitems)) {
         List var21 = var1.consumeitems;
         var2 += CodedConstant.computeListSize(4, var21, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.avataritems)) {
         List var22 = var1.avataritems;
         var2 += CodedConstant.computeListSize(5, var22, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.emblemitems)) {
         List var23 = var1.emblemitems;
         var2 += CodedConstant.computeListSize(6, var23, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.carditems)) {
         List var24 = var1.carditems;
         var2 += CodedConstant.computeListSize(7, var24, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.titleitems)) {
         List var25 = var1.titleitems;
         var2 += CodedConstant.computeListSize(8, var25, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.creatureitems)) {
         List var26 = var1.creatureitems;
         var2 += CodedConstant.computeListSize(9, var26, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.artifactitems)) {
         List var27 = var1.artifactitems;
         var2 += CodedConstant.computeListSize(10, var27, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.crackitems)) {
         List var28 = var1.crackitems;
         var2 += CodedConstant.computeListSize(11, var28, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.crackequipitems)) {
         List var29 = var1.crackequipitems;
         var2 += CodedConstant.computeListSize(12, var29, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.sdavataritems)) {
         List var30 = var1.sdavataritems;
         var2 += CodedConstant.computeListSize(13, var30, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.bookmarkitems)) {
         List var31 = var1.bookmarkitems;
         var2 += CodedConstant.computeListSize(14, var31, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.scrollitems)) {
         List var32 = var1.scrollitems;
         var2 += CodedConstant.computeListSize(15, var32, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(REQ_SEND_INVEN var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.storage)) {
         Integer var18 = var1.storage;
         if (var18 != null) {
            var2.writeInt32(1, var18);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.equipitems)) {
         List var19 = var1.equipitems;
         if (var19 != null) {
            CodedConstant.writeToList(var2, 2, FieldType.OBJECT, var19, false);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.materialitems)) {
         List var20 = var1.materialitems;
         if (var20 != null) {
            CodedConstant.writeToList(var2, 3, FieldType.OBJECT, var20, false);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.consumeitems)) {
         List var21 = var1.consumeitems;
         if (var21 != null) {
            CodedConstant.writeToList(var2, 4, FieldType.OBJECT, var21, false);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.avataritems)) {
         List var22 = var1.avataritems;
         if (var22 != null) {
            CodedConstant.writeToList(var2, 5, FieldType.OBJECT, var22, false);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.emblemitems)) {
         List var23 = var1.emblemitems;
         if (var23 != null) {
            CodedConstant.writeToList(var2, 6, FieldType.OBJECT, var23, false);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.carditems)) {
         List var24 = var1.carditems;
         if (var24 != null) {
            CodedConstant.writeToList(var2, 7, FieldType.OBJECT, var24, false);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.titleitems)) {
         List var25 = var1.titleitems;
         if (var25 != null) {
            CodedConstant.writeToList(var2, 8, FieldType.OBJECT, var25, false);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.creatureitems)) {
         List var26 = var1.creatureitems;
         if (var26 != null) {
            CodedConstant.writeToList(var2, 9, FieldType.OBJECT, var26, false);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.artifactitems)) {
         List var27 = var1.artifactitems;
         if (var27 != null) {
            CodedConstant.writeToList(var2, 10, FieldType.OBJECT, var27, false);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.crackitems)) {
         List var28 = var1.crackitems;
         if (var28 != null) {
            CodedConstant.writeToList(var2, 11, FieldType.OBJECT, var28, false);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.crackequipitems)) {
         List var29 = var1.crackequipitems;
         if (var29 != null) {
            CodedConstant.writeToList(var2, 12, FieldType.OBJECT, var29, false);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.sdavataritems)) {
         List var30 = var1.sdavataritems;
         if (var30 != null) {
            CodedConstant.writeToList(var2, 13, FieldType.OBJECT, var30, false);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.bookmarkitems)) {
         List var31 = var1.bookmarkitems;
         if (var31 != null) {
            CodedConstant.writeToList(var2, 14, FieldType.OBJECT, var31, false);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.scrollitems)) {
         List var32 = var1.scrollitems;
         if (var32 != null) {
            CodedConstant.writeToList(var2, 15, FieldType.OBJECT, var32, false);
         }
      }

   }

   public void writeTo(REQ_SEND_INVEN var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_SEND_INVEN readFrom(CodedInputStream var1) throws IOException {
      REQ_SEND_INVEN var2 = new REQ_SEND_INVEN();
      var2.equipitems = new ArrayList();
      var2.materialitems = new ArrayList();
      var2.consumeitems = new ArrayList();
      var2.avataritems = new ArrayList();
      var2.emblemitems = new ArrayList();
      var2.carditems = new ArrayList();
      var2.titleitems = new ArrayList();
      var2.creatureitems = new ArrayList();
      var2.artifactitems = new ArrayList();
      var2.crackitems = new ArrayList();
      var2.crackequipitems = new ArrayList();
      var2.sdavataritems = new ArrayList();
      var2.bookmarkitems = new ArrayList();
      var2.scrollitems = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.storage = var1.readInt32();
            } else if (var5 == 18) {
               Codec var10 = ProtobufProxy.create(SendItem_GuidInfo.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.equipitems == null) {
                  var2.equipitems = new ArrayList();
               }

               var2.equipitems.add((SendItem_GuidInfo)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 26) {
               Codec var11 = ProtobufProxy.create(SendItem_Info.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var24 = var1.readRawVarint32();
               int var37 = var1.pushLimit(var24);
               if (var2.materialitems == null) {
                  var2.materialitems = new ArrayList();
               }

               var2.materialitems.add((SendItem_Info)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var37);
            } else if (var5 == 34) {
               Codec var12 = ProtobufProxy.create(SendItem_Info.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var25 = var1.readRawVarint32();
               int var38 = var1.pushLimit(var25);
               if (var2.consumeitems == null) {
                  var2.consumeitems = new ArrayList();
               }

               var2.consumeitems.add((SendItem_Info)var12.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var38);
            } else if (var5 == 42) {
               Codec var13 = ProtobufProxy.create(SendItem_GuidInfo.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var26 = var1.readRawVarint32();
               int var39 = var1.pushLimit(var26);
               if (var2.avataritems == null) {
                  var2.avataritems = new ArrayList();
               }

               var2.avataritems.add((SendItem_GuidInfo)var13.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var39);
            } else if (var5 == 50) {
               Codec var14 = ProtobufProxy.create(SendItem_Info.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var27 = var1.readRawVarint32();
               int var40 = var1.pushLimit(var27);
               if (var2.emblemitems == null) {
                  var2.emblemitems = new ArrayList();
               }

               var2.emblemitems.add((SendItem_Info)var14.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var40);
            } else if (var5 == 58) {
               Codec var15 = ProtobufProxy.create(SendItem_Info.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var28 = var1.readRawVarint32();
               int var41 = var1.pushLimit(var28);
               if (var2.carditems == null) {
                  var2.carditems = new ArrayList();
               }

               var2.carditems.add((SendItem_Info)var15.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var41);
            } else if (var5 == 66) {
               Codec var16 = ProtobufProxy.create(SendItem_GuidInfo.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var29 = var1.readRawVarint32();
               int var42 = var1.pushLimit(var29);
               if (var2.titleitems == null) {
                  var2.titleitems = new ArrayList();
               }

               var2.titleitems.add((SendItem_GuidInfo)var16.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var42);
            } else if (var5 == 74) {
               Codec var17 = ProtobufProxy.create(SendItem_GuidInfo.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var30 = var1.readRawVarint32();
               int var43 = var1.pushLimit(var30);
               if (var2.creatureitems == null) {
                  var2.creatureitems = new ArrayList();
               }

               var2.creatureitems.add((SendItem_GuidInfo)var17.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var43);
            } else if (var5 == 82) {
               Codec var18 = ProtobufProxy.create(SendItem_GuidInfo.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var31 = var1.readRawVarint32();
               int var44 = var1.pushLimit(var31);
               if (var2.artifactitems == null) {
                  var2.artifactitems = new ArrayList();
               }

               var2.artifactitems.add((SendItem_GuidInfo)var18.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var44);
            } else if (var5 == 90) {
               Codec var19 = ProtobufProxy.create(SendItem_Info.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var32 = var1.readRawVarint32();
               int var45 = var1.pushLimit(var32);
               if (var2.crackitems == null) {
                  var2.crackitems = new ArrayList();
               }

               var2.crackitems.add((SendItem_Info)var19.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var45);
            } else if (var5 == 98) {
               Codec var20 = ProtobufProxy.create(SendItem_GuidInfo.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var33 = var1.readRawVarint32();
               int var46 = var1.pushLimit(var33);
               if (var2.crackequipitems == null) {
                  var2.crackequipitems = new ArrayList();
               }

               var2.crackequipitems.add((SendItem_GuidInfo)var20.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var46);
            } else if (var5 == 106) {
               Codec var21 = ProtobufProxy.create(SendItem_GuidInfo.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var34 = var1.readRawVarint32();
               int var47 = var1.pushLimit(var34);
               if (var2.sdavataritems == null) {
                  var2.sdavataritems = new ArrayList();
               }

               var2.sdavataritems.add((SendItem_GuidInfo)var21.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var47);
            } else if (var5 == 114) {
               Codec var22 = ProtobufProxy.create(SendItem_Info.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var35 = var1.readRawVarint32();
               int var48 = var1.pushLimit(var35);
               if (var2.bookmarkitems == null) {
                  var2.bookmarkitems = new ArrayList();
               }

               var2.bookmarkitems.add((SendItem_Info)var22.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var48);
            } else if (var5 == 122) {
               Codec var23 = ProtobufProxy.create(SendItem_GuidInfo.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var36 = var1.readRawVarint32();
               int var49 = var1.pushLimit(var36);
               if (var2.scrollitems == null) {
                  var2.scrollitems = new ArrayList();
               }

               var2.scrollitems.add((SendItem_GuidInfo)var23.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var49);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_SEND_INVEN.class);
         return this.descriptor = var1;
      }
   }
}
