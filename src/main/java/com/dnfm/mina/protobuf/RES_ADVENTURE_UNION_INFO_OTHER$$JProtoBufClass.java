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

public class RES_ADVENTURE_UNION_INFO_OTHER$$JProtoBufClass implements Codec<RES_ADVENTURE_UNION_INFO_OTHER>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_ADVENTURE_UNION_INFO_OTHER var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_ADVENTURE_UNION_INFO_OTHER decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_ADVENTURE_UNION_INFO_OTHER var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var14 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var14);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var15 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(2, var15);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.adventureunionexp)) {
         Long var16 = var1.adventureunionexp;
         var2 += CodedOutputStream.computeInt64Size(3, var16);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.day)) {
         Integer var17 = var1.day;
         var2 += CodedOutputStream.computeInt32Size(4, var17);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var18 = var1.name;
         var2 += CodedOutputStream.computeStringSize(5, var18);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.typicalcharacter)) {
         PT_CHARACTER var19 = var1.typicalcharacter;
         var2 += CodedConstant.computeSize(6, var19, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.charactercount)) {
         Integer var20 = var1.charactercount;
         var2 += CodedOutputStream.computeInt32Size(7, var20);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var21 = var1.world;
         var2 += CodedOutputStream.computeInt32Size(8, var21);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.characterlist)) {
         List var22 = var1.characterlist;
         var2 += CodedConstant.computeListSize(9, var22, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.shareboardbackground)) {
         Integer var23 = var1.shareboardbackground;
         var2 += CodedOutputStream.computeInt32Size(10, var23);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.achievementinfolist)) {
         List var24 = var1.achievementinfolist;
         var2 += CodedConstant.computeListSize(11, var24, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(RES_ADVENTURE_UNION_INFO_OTHER var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var14 = var1.error;
         if (var14 != null) {
            var2.writeInt32(1, var14);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var15 = var1.level;
         if (var15 != null) {
            var2.writeInt32(2, var15);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.adventureunionexp)) {
         Long var16 = var1.adventureunionexp;
         if (var16 != null) {
            var2.writeInt64(3, var16);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.day)) {
         Integer var17 = var1.day;
         if (var17 != null) {
            var2.writeInt32(4, var17);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var18 = var1.name;
         if (var18 != null) {
            var2.writeString(5, var18);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.typicalcharacter)) {
         PT_CHARACTER var19 = var1.typicalcharacter;
         if (var19 != null) {
            CodedConstant.writeObject(var2, 6, FieldType.OBJECT, var19, false);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.charactercount)) {
         Integer var20 = var1.charactercount;
         if (var20 != null) {
            var2.writeInt32(7, var20);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var21 = var1.world;
         if (var21 != null) {
            var2.writeInt32(8, var21);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.characterlist)) {
         List var22 = var1.characterlist;
         if (var22 != null) {
            CodedConstant.writeToList(var2, 9, FieldType.OBJECT, var22, false);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.shareboardbackground)) {
         Integer var23 = var1.shareboardbackground;
         if (var23 != null) {
            var2.writeInt32(10, var23);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.achievementinfolist)) {
         List var24 = var1.achievementinfolist;
         if (var24 != null) {
            CodedConstant.writeToList(var2, 11, FieldType.OBJECT, var24, false);
         }
      }

   }

   public void writeTo(RES_ADVENTURE_UNION_INFO_OTHER var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_ADVENTURE_UNION_INFO_OTHER readFrom(CodedInputStream var1) throws IOException {
      RES_ADVENTURE_UNION_INFO_OTHER var2 = new RES_ADVENTURE_UNION_INFO_OTHER();
      var2.characterlist = new ArrayList();
      var2.achievementinfolist = new ArrayList();

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
               var2.level = var1.readInt32();
            } else if (var5 == 24) {
               var2.adventureunionexp = var1.readInt64();
            } else if (var5 == 32) {
               var2.day = var1.readInt32();
            } else if (var5 == 42) {
               var2.name = var1.readString();
            } else if (var5 == 50) {
               Codec var10 = ProtobufProxy.create(PT_CHARACTER.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.typicalcharacter = (PT_CHARACTER)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 56) {
               var2.charactercount = var1.readInt32();
            } else if (var5 == 64) {
               var2.world = var1.readInt32();
            } else if (var5 == 74) {
               Codec var11 = ProtobufProxy.create(PT_ADVENTURE_UNION_SHAREBOARD_SLOT_DETAIL_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var13 = var1.readRawVarint32();
               int var15 = var1.pushLimit(var13);
               if (var2.characterlist == null) {
                  var2.characterlist = new ArrayList();
               }

               var2.characterlist.add((PT_ADVENTURE_UNION_SHAREBOARD_SLOT_DETAIL_INFO)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var15);
            } else if (var5 == 80) {
               var2.shareboardbackground = var1.readInt32();
            } else if (var5 == 90) {
               Codec var12 = ProtobufProxy.create(AchievementInfoPacketData.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var14 = var1.readRawVarint32();
               int var16 = var1.pushLimit(var14);
               if (var2.achievementinfolist == null) {
                  var2.achievementinfolist = new ArrayList();
               }

               var2.achievementinfolist.add((AchievementInfoPacketData)var12.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var16);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_ADVENTURE_UNION_INFO_OTHER.class);
         return this.descriptor = var1;
      }
   }
}
