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

public class RES_EQUIPPED_LIST$$JProtoBufClass implements Codec<RES_EQUIPPED_LIST>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_EQUIPPED_LIST var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_EQUIPPED_LIST decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_EQUIPPED_LIST var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var12 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var12);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.equiplist)) {
         List var13 = var1.equiplist;
         var2 += CodedConstant.computeListSize(2, var13, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.avatarlist)) {
         List var14 = var1.avatarlist;
         var2 += CodedConstant.computeListSize(3, var14, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.creaturelist)) {
         List var15 = var1.creaturelist;
         var2 += CodedConstant.computeListSize(4, var15, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.cartifactlist)) {
         List var16 = var1.cartifactlist;
         var2 += CodedConstant.computeListSize(5, var16, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.equipskinlist)) {
         List var17 = var1.equipskinlist;
         var2 += CodedConstant.computeListSize(6, var17, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.avatarskinlist)) {
         List var18 = var1.avatarskinlist;
         var2 += CodedConstant.computeListSize(7, var18, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.skinlist)) {
         List var19 = var1.skinlist;
         var2 += CodedConstant.computeListSize(8, var19, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.sdavatarlist)) {
         List var20 = var1.sdavatarlist;
         var2 += CodedConstant.computeListSize(9, var20, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(RES_EQUIPPED_LIST var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var12 = var1.error;
         if (var12 != null) {
            var2.writeInt32(1, var12);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.equiplist)) {
         List var13 = var1.equiplist;
         if (var13 != null) {
            CodedConstant.writeToList(var2, 2, FieldType.OBJECT, var13, false);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.avatarlist)) {
         List var14 = var1.avatarlist;
         if (var14 != null) {
            CodedConstant.writeToList(var2, 3, FieldType.OBJECT, var14, false);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.creaturelist)) {
         List var15 = var1.creaturelist;
         if (var15 != null) {
            CodedConstant.writeToList(var2, 4, FieldType.OBJECT, var15, false);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.cartifactlist)) {
         List var16 = var1.cartifactlist;
         if (var16 != null) {
            CodedConstant.writeToList(var2, 5, FieldType.OBJECT, var16, false);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.equipskinlist)) {
         List var17 = var1.equipskinlist;
         if (var17 != null) {
            CodedConstant.writeToList(var2, 6, FieldType.OBJECT, var17, false);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.avatarskinlist)) {
         List var18 = var1.avatarskinlist;
         if (var18 != null) {
            CodedConstant.writeToList(var2, 7, FieldType.OBJECT, var18, false);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.skinlist)) {
         List var19 = var1.skinlist;
         if (var19 != null) {
            CodedConstant.writeToList(var2, 8, FieldType.OBJECT, var19, false);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.sdavatarlist)) {
         List var20 = var1.sdavatarlist;
         if (var20 != null) {
            CodedConstant.writeToList(var2, 9, FieldType.OBJECT, var20, false);
         }
      }

   }

   public void writeTo(RES_EQUIPPED_LIST var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_EQUIPPED_LIST readFrom(CodedInputStream var1) throws IOException {
      RES_EQUIPPED_LIST var2 = new RES_EQUIPPED_LIST();
      var2.equiplist = new ArrayList();
      var2.avatarlist = new ArrayList();
      var2.creaturelist = new ArrayList();
      var2.cartifactlist = new ArrayList();
      var2.equipskinlist = new ArrayList();
      var2.avatarskinlist = new ArrayList();
      var2.skinlist = new ArrayList();
      var2.sdavatarlist = new ArrayList();

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
            } else if (var5 == 18) {
               Codec var10 = ProtobufProxy.create(PT_EQUIPPED.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.equiplist == null) {
                  var2.equiplist = new ArrayList();
               }

               var2.equiplist.add((PT_EQUIPPED)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 26) {
               Codec var11 = ProtobufProxy.create(PT_AVATAR_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var18 = var1.readRawVarint32();
               int var25 = var1.pushLimit(var18);
               if (var2.avatarlist == null) {
                  var2.avatarlist = new ArrayList();
               }

               var2.avatarlist.add((PT_AVATAR_ITEM)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var25);
            } else if (var5 == 34) {
               Codec var12 = ProtobufProxy.create(PT_CREATURE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var19 = var1.readRawVarint32();
               int var26 = var1.pushLimit(var19);
               if (var2.creaturelist == null) {
                  var2.creaturelist = new ArrayList();
               }

               var2.creaturelist.add((PT_CREATURE)var12.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var26);
            } else if (var5 == 42) {
               Codec var13 = ProtobufProxy.create(PT_ARTIFACT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var20 = var1.readRawVarint32();
               int var27 = var1.pushLimit(var20);
               if (var2.cartifactlist == null) {
                  var2.cartifactlist = new ArrayList();
               }

               var2.cartifactlist.add((PT_ARTIFACT)var13.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var27);
            } else if (var5 == 50) {
               Codec var14 = ProtobufProxy.create(PT_EQUIPPED.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var21 = var1.readRawVarint32();
               int var28 = var1.pushLimit(var21);
               if (var2.equipskinlist == null) {
                  var2.equipskinlist = new ArrayList();
               }

               var2.equipskinlist.add((PT_EQUIPPED)var14.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var28);
            } else if (var5 == 58) {
               Codec var15 = ProtobufProxy.create(PT_AVATAR_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var22 = var1.readRawVarint32();
               int var29 = var1.pushLimit(var22);
               if (var2.avatarskinlist == null) {
                  var2.avatarskinlist = new ArrayList();
               }

               var2.avatarskinlist.add((PT_AVATAR_ITEM)var15.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var29);
            } else if (var5 == 66) {
               Codec var16 = ProtobufProxy.create(PT_SKIN_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var23 = var1.readRawVarint32();
               int var30 = var1.pushLimit(var23);
               if (var2.skinlist == null) {
                  var2.skinlist = new ArrayList();
               }

               var2.skinlist.add((PT_SKIN_ITEM)var16.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var30);
            } else if (var5 == 74) {
               Codec var17 = ProtobufProxy.create(PT_AVATAR_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var24 = var1.readRawVarint32();
               int var31 = var1.pushLimit(var24);
               if (var2.sdavatarlist == null) {
                  var2.sdavatarlist = new ArrayList();
               }

               var2.sdavatarlist.add((PT_AVATAR_ITEM)var17.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var31);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_EQUIPPED_LIST.class);
         return this.descriptor = var1;
      }
   }
}
