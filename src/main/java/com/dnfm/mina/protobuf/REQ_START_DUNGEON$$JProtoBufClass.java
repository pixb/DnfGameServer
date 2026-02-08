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

public class REQ_START_DUNGEON$$JProtoBufClass implements Codec<REQ_START_DUNGEON>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_START_DUNGEON var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_START_DUNGEON decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_START_DUNGEON var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var18 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(1, var18);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.authkey)) {
         String var19 = var1.authkey;
         var2 += CodedOutputStream.computeStringSize(2, var19);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.rush)) {
         Boolean var20 = var1.rush;
         var2 += CodedOutputStream.computeBoolSize(3, var20);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var21 = var1.grade;
         var2 += CodedOutputStream.computeInt32Size(4, var21);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var22 = var1.dungeonguid;
         var2 += CodedOutputStream.computeUInt64Size(5, var22);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.time)) {
         String var23 = var1.time;
         var2 += CodedOutputStream.computeStringSize(6, var23);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.matchingguid)) {
         Long var24 = var1.matchingguid;
         var2 += CodedOutputStream.computeUInt64Size(7, var24);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.eamplify)) {
         Boolean var25 = var1.eamplify;
         var2 += CodedOutputStream.computeBoolSize(8, var25);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.verification)) {
         PT_VERIFICATION var26 = var1.verification;
         var2 += CodedConstant.computeSize(9, var26, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.position)) {
         Integer var27 = var1.position;
         var2 += CodedOutputStream.computeInt32Size(10, var27);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.enemycharguid)) {
         Long var28 = var1.enemycharguid;
         var2 += CodedOutputStream.computeUInt64Size(11, var28);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.gamesafedata)) {
         String var29 = var1.gamesafedata;
         var2 += CodedOutputStream.computeStringSize(12, var29);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.gamesafedatacrc)) {
         String var30 = var1.gamesafedatacrc;
         var2 += CodedOutputStream.computeStringSize(13, var30);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.quest)) {
         List var31 = var1.quest;
         var2 += CodedConstant.computeListSize(14, var31, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.entranceitemindex)) {
         Integer var32 = var1.entranceitemindex;
         var2 += CodedOutputStream.computeInt32Size(15, var32);
      }

      return var2;
   }

   public void doWriteTo(REQ_START_DUNGEON var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var18 = var1.index;
         if (var18 != null) {
            var2.writeInt32(1, var18);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.authkey)) {
         String var19 = var1.authkey;
         if (var19 != null) {
            var2.writeString(2, var19);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.rush)) {
         Boolean var20 = var1.rush;
         if (var20 != null) {
            var2.writeBool(3, var20);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var21 = var1.grade;
         if (var21 != null) {
            var2.writeInt32(4, var21);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var22 = var1.dungeonguid;
         if (var22 != null) {
            var2.writeUInt64(5, var22);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.time)) {
         String var23 = var1.time;
         if (var23 != null) {
            var2.writeString(6, var23);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.matchingguid)) {
         Long var24 = var1.matchingguid;
         if (var24 != null) {
            var2.writeUInt64(7, var24);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.eamplify)) {
         Boolean var25 = var1.eamplify;
         if (var25 != null) {
            var2.writeBool(8, var25);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.verification)) {
         PT_VERIFICATION var26 = var1.verification;
         if (var26 != null) {
            CodedConstant.writeObject(var2, 9, FieldType.OBJECT, var26, false);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.position)) {
         Integer var27 = var1.position;
         if (var27 != null) {
            var2.writeInt32(10, var27);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.enemycharguid)) {
         Long var28 = var1.enemycharguid;
         if (var28 != null) {
            var2.writeUInt64(11, var28);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.gamesafedata)) {
         String var29 = var1.gamesafedata;
         if (var29 != null) {
            var2.writeString(12, var29);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.gamesafedatacrc)) {
         String var30 = var1.gamesafedatacrc;
         if (var30 != null) {
            var2.writeString(13, var30);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.quest)) {
         List var31 = var1.quest;
         if (var31 != null) {
            CodedConstant.writeToList(var2, 14, FieldType.OBJECT, var31, false);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.entranceitemindex)) {
         Integer var32 = var1.entranceitemindex;
         if (var32 != null) {
            var2.writeInt32(15, var32);
         }
      }

   }

   public void writeTo(REQ_START_DUNGEON var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_START_DUNGEON readFrom(CodedInputStream var1) throws IOException {
      REQ_START_DUNGEON var2 = new REQ_START_DUNGEON();
      var2.quest = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.index = var1.readInt32();
            } else if (var5 == 18) {
               var2.authkey = var1.readString();
            } else if (var5 == 24) {
               var2.rush = var1.readBool();
            } else if (var5 == 32) {
               var2.grade = var1.readInt32();
            } else if (var5 == 40) {
               var2.dungeonguid = var1.readUInt64();
            } else if (var5 == 50) {
               var2.time = var1.readString();
            } else if (var5 == 56) {
               var2.matchingguid = var1.readUInt64();
            } else if (var5 == 64) {
               var2.eamplify = var1.readBool();
            } else if (var5 == 74) {
               Codec var10 = ProtobufProxy.create(PT_VERIFICATION.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               var2.verification = (PT_VERIFICATION)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 80) {
               var2.position = var1.readInt32();
            } else if (var5 == 88) {
               var2.enemycharguid = var1.readUInt64();
            } else if (var5 == 98) {
               var2.gamesafedata = var1.readString();
            } else if (var5 == 106) {
               var2.gamesafedatacrc = var1.readString();
            } else if (var5 == 114) {
               Codec var11 = ProtobufProxy.create(PT_QUEST.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var12 = var1.readRawVarint32();
               int var13 = var1.pushLimit(var12);
               if (var2.quest == null) {
                  var2.quest = new ArrayList();
               }

               var2.quest.add((PT_QUEST)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var13);
            } else if (var5 == 120) {
               var2.entranceitemindex = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_START_DUNGEON.class);
         return this.descriptor = var1;
      }
   }
}
