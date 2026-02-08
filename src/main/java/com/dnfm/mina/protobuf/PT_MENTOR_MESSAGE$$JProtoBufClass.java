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

public class PT_MENTOR_MESSAGE$$JProtoBufClass implements Codec<PT_MENTOR_MESSAGE>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_MENTOR_MESSAGE var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_MENTOR_MESSAGE decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_MENTOR_MESSAGE var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.masterguid)) {
         Long var31 = var1.masterguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var31);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var32 = var1.guid;
         var2 += CodedOutputStream.computeUInt64Size(2, var32);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var33 = var1.name;
         var2 += CodedOutputStream.computeStringSize(3, var33);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.msg)) {
         String var34 = var1.msg;
         var2 += CodedOutputStream.computeStringSize(4, var34);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.date)) {
         Long var35 = var1.date;
         var2 += CodedOutputStream.computeUInt64Size(5, var35);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var36 = var1.job;
         var2 += CodedOutputStream.computeInt32Size(6, var36);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var37 = var1.growtype;
         var2 += CodedOutputStream.computeInt32Size(7, var37);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var38 = var1.secondgrowtype;
         var2 += CodedOutputStream.computeInt32Size(8, var38);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var39 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(9, var39);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var40 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(10, var40);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.voice)) {
         String var41 = var1.voice;
         var2 += CodedOutputStream.computeStringSize(11, var41);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.voicetime)) {
         String var42 = var1.voicetime;
         var2 += CodedOutputStream.computeStringSize(12, var42);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.hyperlinktype)) {
         Integer var43 = var1.hyperlinktype;
         var2 += CodedOutputStream.computeInt32Size(13, var43);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.hyperlinksubtype)) {
         Integer var44 = var1.hyperlinksubtype;
         var2 += CodedOutputStream.computeInt32Size(14, var44);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.hyperlinkdatas)) {
         List var45 = var1.hyperlinkdatas;
         var2 += CodedConstant.computeListSize(15, var45, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.sub)) {
         List var46 = var1.sub;
         var2 += CodedConstant.computeListSize(16, var46, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.equip)) {
         PT_EQUIP var47 = var1.equip;
         var2 += CodedConstant.computeSize(17, var47, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.title)) {
         PT_EQUIP var48 = var1.title;
         var2 += CodedConstant.computeSize(18, var48, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.flag)) {
         PT_EQUIP var49 = var1.flag;
         var2 += CodedConstant.computeSize(19, var49, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.emblem)) {
         PT_STACKABLE var50 = var1.emblem;
         var2 += CodedConstant.computeSize(20, var50, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.material)) {
         PT_STACKABLE var51 = var1.material;
         var2 += CodedConstant.computeSize(21, var51, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.consume)) {
         PT_STACKABLE var52 = var1.consume;
         var2 += CodedConstant.computeSize(22, var52, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.avatar)) {
         PT_AVATAR_ITEM var53 = var1.avatar;
         var2 += CodedConstant.computeSize(23, var53, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.skinchatinfo)) {
         PT_SKIN_CHAT_INFO var54 = var1.skinchatinfo;
         var2 += CodedConstant.computeSize(24, var54, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.card)) {
         PT_STACKABLE var55 = var1.card;
         var2 += CodedConstant.computeSize(25, var55, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.creature)) {
         PT_CREATURE var56 = var1.creature;
         var2 += CodedConstant.computeSize(26, var56, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.cartifact)) {
         PT_ARTIFACT var57 = var1.cartifact;
         var2 += CodedConstant.computeSize(27, var57, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.list)) {
         List var58 = var1.list;
         var2 += CodedConstant.computeListSize(28, var58, FieldType.STRING, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(PT_MENTOR_MESSAGE var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.masterguid)) {
         Long var31 = var1.masterguid;
         if (var31 != null) {
            var2.writeUInt64(1, var31);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var32 = var1.guid;
         if (var32 != null) {
            var2.writeUInt64(2, var32);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var33 = var1.name;
         if (var33 != null) {
            var2.writeString(3, var33);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.msg)) {
         String var34 = var1.msg;
         if (var34 != null) {
            var2.writeString(4, var34);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.date)) {
         Long var35 = var1.date;
         if (var35 != null) {
            var2.writeUInt64(5, var35);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var36 = var1.job;
         if (var36 != null) {
            var2.writeInt32(6, var36);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var37 = var1.growtype;
         if (var37 != null) {
            var2.writeInt32(7, var37);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var38 = var1.secondgrowtype;
         if (var38 != null) {
            var2.writeInt32(8, var38);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var39 = var1.type;
         if (var39 != null) {
            var2.writeInt32(9, var39);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var40 = var1.level;
         if (var40 != null) {
            var2.writeInt32(10, var40);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.voice)) {
         String var41 = var1.voice;
         if (var41 != null) {
            var2.writeString(11, var41);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.voicetime)) {
         String var42 = var1.voicetime;
         if (var42 != null) {
            var2.writeString(12, var42);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.hyperlinktype)) {
         Integer var43 = var1.hyperlinktype;
         if (var43 != null) {
            var2.writeInt32(13, var43);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.hyperlinksubtype)) {
         Integer var44 = var1.hyperlinksubtype;
         if (var44 != null) {
            var2.writeInt32(14, var44);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.hyperlinkdatas)) {
         List var45 = var1.hyperlinkdatas;
         if (var45 != null) {
            CodedConstant.writeToList(var2, 15, FieldType.OBJECT, var45, false);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.sub)) {
         List var46 = var1.sub;
         if (var46 != null) {
            CodedConstant.writeToList(var2, 16, FieldType.OBJECT, var46, false);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.equip)) {
         PT_EQUIP var47 = var1.equip;
         if (var47 != null) {
            CodedConstant.writeObject(var2, 17, FieldType.OBJECT, var47, false);
         }
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.title)) {
         PT_EQUIP var48 = var1.title;
         if (var48 != null) {
            CodedConstant.writeObject(var2, 18, FieldType.OBJECT, var48, false);
         }
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.flag)) {
         PT_EQUIP var49 = var1.flag;
         if (var49 != null) {
            CodedConstant.writeObject(var2, 19, FieldType.OBJECT, var49, false);
         }
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.emblem)) {
         PT_STACKABLE var50 = var1.emblem;
         if (var50 != null) {
            CodedConstant.writeObject(var2, 20, FieldType.OBJECT, var50, false);
         }
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.material)) {
         PT_STACKABLE var51 = var1.material;
         if (var51 != null) {
            CodedConstant.writeObject(var2, 21, FieldType.OBJECT, var51, false);
         }
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.consume)) {
         PT_STACKABLE var52 = var1.consume;
         if (var52 != null) {
            CodedConstant.writeObject(var2, 22, FieldType.OBJECT, var52, false);
         }
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.avatar)) {
         PT_AVATAR_ITEM var53 = var1.avatar;
         if (var53 != null) {
            CodedConstant.writeObject(var2, 23, FieldType.OBJECT, var53, false);
         }
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.skinchatinfo)) {
         PT_SKIN_CHAT_INFO var54 = var1.skinchatinfo;
         if (var54 != null) {
            CodedConstant.writeObject(var2, 24, FieldType.OBJECT, var54, false);
         }
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.card)) {
         PT_STACKABLE var55 = var1.card;
         if (var55 != null) {
            CodedConstant.writeObject(var2, 25, FieldType.OBJECT, var55, false);
         }
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.creature)) {
         PT_CREATURE var56 = var1.creature;
         if (var56 != null) {
            CodedConstant.writeObject(var2, 26, FieldType.OBJECT, var56, false);
         }
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.cartifact)) {
         PT_ARTIFACT var57 = var1.cartifact;
         if (var57 != null) {
            CodedConstant.writeObject(var2, 27, FieldType.OBJECT, var57, false);
         }
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.list)) {
         List var58 = var1.list;
         if (var58 != null) {
            CodedConstant.writeToList(var2, 28, FieldType.STRING, var58, false);
         }
      }

   }

   public void writeTo(PT_MENTOR_MESSAGE var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_MENTOR_MESSAGE readFrom(CodedInputStream var1) throws IOException {
      PT_MENTOR_MESSAGE var2 = new PT_MENTOR_MESSAGE();
      var2.hyperlinkdatas = new ArrayList();
      var2.sub = new ArrayList();
      var2.list = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.masterguid = var1.readUInt64();
            } else if (var5 == 16) {
               var2.guid = var1.readUInt64();
            } else if (var5 == 26) {
               var2.name = var1.readString();
            } else if (var5 == 34) {
               var2.msg = var1.readString();
            } else if (var5 == 40) {
               var2.date = var1.readUInt64();
            } else if (var5 == 48) {
               var2.job = var1.readInt32();
            } else if (var5 == 56) {
               var2.growtype = var1.readInt32();
            } else if (var5 == 64) {
               var2.secondgrowtype = var1.readInt32();
            } else if (var5 == 72) {
               var2.type = var1.readInt32();
            } else if (var5 == 80) {
               var2.level = var1.readInt32();
            } else if (var5 == 90) {
               var2.voice = var1.readString();
            } else if (var5 == 98) {
               var2.voicetime = var1.readString();
            } else if (var5 == 104) {
               var2.hyperlinktype = var1.readInt32();
            } else if (var5 == 112) {
               var2.hyperlinksubtype = var1.readInt32();
            } else if (var5 == 122) {
               Codec var10 = ProtobufProxy.create(PT_HYPERLINK_DATA.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.hyperlinkdatas == null) {
                  var2.hyperlinkdatas = new ArrayList();
               }

               var2.hyperlinkdatas.add((PT_HYPERLINK_DATA)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 130) {
               Codec var11 = ProtobufProxy.create(PT_HYPERLINK_SYSTEMMESSAGE_SUB.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var23 = var1.readRawVarint32();
               int var35 = var1.pushLimit(var23);
               if (var2.sub == null) {
                  var2.sub = new ArrayList();
               }

               var2.sub.add((PT_HYPERLINK_SYSTEMMESSAGE_SUB)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var35);
            } else if (var5 == 138) {
               Codec var12 = ProtobufProxy.create(PT_EQUIP.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var24 = var1.readRawVarint32();
               int var36 = var1.pushLimit(var24);
               var2.equip = (PT_EQUIP)var12.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var36);
            } else if (var5 == 146) {
               Codec var13 = ProtobufProxy.create(PT_EQUIP.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var25 = var1.readRawVarint32();
               int var37 = var1.pushLimit(var25);
               var2.title = (PT_EQUIP)var13.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var37);
            } else if (var5 == 154) {
               Codec var14 = ProtobufProxy.create(PT_EQUIP.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var26 = var1.readRawVarint32();
               int var38 = var1.pushLimit(var26);
               var2.flag = (PT_EQUIP)var14.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var38);
            } else if (var5 == 162) {
               Codec var15 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var27 = var1.readRawVarint32();
               int var39 = var1.pushLimit(var27);
               var2.emblem = (PT_STACKABLE)var15.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var39);
            } else if (var5 == 170) {
               Codec var16 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var28 = var1.readRawVarint32();
               int var40 = var1.pushLimit(var28);
               var2.material = (PT_STACKABLE)var16.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var40);
            } else if (var5 == 178) {
               Codec var17 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var29 = var1.readRawVarint32();
               int var41 = var1.pushLimit(var29);
               var2.consume = (PT_STACKABLE)var17.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var41);
            } else if (var5 == 186) {
               Codec var18 = ProtobufProxy.create(PT_AVATAR_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var30 = var1.readRawVarint32();
               int var42 = var1.pushLimit(var30);
               var2.avatar = (PT_AVATAR_ITEM)var18.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var42);
            } else if (var5 == 194) {
               Codec var19 = ProtobufProxy.create(PT_SKIN_CHAT_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var31 = var1.readRawVarint32();
               int var43 = var1.pushLimit(var31);
               var2.skinchatinfo = (PT_SKIN_CHAT_INFO)var19.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var43);
            } else if (var5 == 202) {
               Codec var20 = ProtobufProxy.create(PT_STACKABLE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var32 = var1.readRawVarint32();
               int var44 = var1.pushLimit(var32);
               var2.card = (PT_STACKABLE)var20.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var44);
            } else if (var5 == 210) {
               Codec var21 = ProtobufProxy.create(PT_CREATURE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var33 = var1.readRawVarint32();
               int var45 = var1.pushLimit(var33);
               var2.creature = (PT_CREATURE)var21.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var45);
            } else if (var5 == 218) {
               Codec var22 = ProtobufProxy.create(PT_ARTIFACT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var34 = var1.readRawVarint32();
               int var46 = var1.pushLimit(var34);
               var2.cartifact = (PT_ARTIFACT)var22.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var46);
            } else if (var5 == 226) {
               if (var2.list == null) {
                  var2.list = new ArrayList();
               }

               var2.list.add(var1.readString());
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_MENTOR_MESSAGE.class);
         return this.descriptor = var1;
      }
   }
}
