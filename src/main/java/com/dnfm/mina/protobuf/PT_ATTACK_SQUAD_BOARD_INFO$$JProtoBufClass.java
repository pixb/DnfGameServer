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

public class PT_ATTACK_SQUAD_BOARD_INFO$$JProtoBufClass implements Codec<PT_ATTACK_SQUAD_BOARD_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_ATTACK_SQUAD_BOARD_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_ATTACK_SQUAD_BOARD_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_ATTACK_SQUAD_BOARD_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.rpguid)) {
         Long var14 = var1.rpguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var14);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.rpname)) {
         String var15 = var1.rpname;
         var2 += CodedOutputStream.computeStringSize(2, var15);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.membercount)) {
         Integer var16 = var1.membercount;
         var2 += CodedOutputStream.computeInt32Size(3, var16);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.antievilscore)) {
         Integer var17 = var1.antievilscore;
         var2 += CodedOutputStream.computeInt32Size(4, var17);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.publictype)) {
         Integer var18 = var1.publictype;
         var2 += CodedOutputStream.computeInt32Size(5, var18);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.status)) {
         Integer var19 = var1.status;
         var2 += CodedOutputStream.computeInt32Size(6, var19);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.raidindex)) {
         Integer var20 = var1.raidindex;
         var2 += CodedOutputStream.computeInt32Size(7, var20);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.chivalry)) {
         Boolean var21 = var1.chivalry;
         var2 += CodedOutputStream.computeBoolSize(8, var21);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.leaderguid)) {
         Long var22 = var1.leaderguid;
         var2 += CodedOutputStream.computeUInt64Size(9, var22);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.started)) {
         Boolean var23 = var1.started;
         var2 += CodedOutputStream.computeBoolSize(10, var23);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.memberinfos)) {
         List var24 = var1.memberinfos;
         var2 += CodedConstant.computeListSize(11, var24, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(PT_ATTACK_SQUAD_BOARD_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.rpguid)) {
         Long var14 = var1.rpguid;
         if (var14 != null) {
            var2.writeUInt64(1, var14);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.rpname)) {
         String var15 = var1.rpname;
         if (var15 != null) {
            var2.writeString(2, var15);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.membercount)) {
         Integer var16 = var1.membercount;
         if (var16 != null) {
            var2.writeInt32(3, var16);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.antievilscore)) {
         Integer var17 = var1.antievilscore;
         if (var17 != null) {
            var2.writeInt32(4, var17);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.publictype)) {
         Integer var18 = var1.publictype;
         if (var18 != null) {
            var2.writeInt32(5, var18);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.status)) {
         Integer var19 = var1.status;
         if (var19 != null) {
            var2.writeInt32(6, var19);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.raidindex)) {
         Integer var20 = var1.raidindex;
         if (var20 != null) {
            var2.writeInt32(7, var20);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.chivalry)) {
         Boolean var21 = var1.chivalry;
         if (var21 != null) {
            var2.writeBool(8, var21);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.leaderguid)) {
         Long var22 = var1.leaderguid;
         if (var22 != null) {
            var2.writeUInt64(9, var22);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.started)) {
         Boolean var23 = var1.started;
         if (var23 != null) {
            var2.writeBool(10, var23);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.memberinfos)) {
         List var24 = var1.memberinfos;
         if (var24 != null) {
            CodedConstant.writeToList(var2, 11, FieldType.OBJECT, var24, false);
         }
      }

   }

   public void writeTo(PT_ATTACK_SQUAD_BOARD_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_ATTACK_SQUAD_BOARD_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_ATTACK_SQUAD_BOARD_INFO var2 = new PT_ATTACK_SQUAD_BOARD_INFO();
      var2.memberinfos = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.rpguid = var1.readUInt64();
            } else if (var5 == 18) {
               var2.rpname = var1.readString();
            } else if (var5 == 24) {
               var2.membercount = var1.readInt32();
            } else if (var5 == 32) {
               var2.antievilscore = var1.readInt32();
            } else if (var5 == 40) {
               var2.publictype = var1.readInt32();
            } else if (var5 == 48) {
               var2.status = var1.readInt32();
            } else if (var5 == 56) {
               var2.raidindex = var1.readInt32();
            } else if (var5 == 64) {
               var2.chivalry = var1.readBool();
            } else if (var5 == 72) {
               var2.leaderguid = var1.readUInt64();
            } else if (var5 == 80) {
               var2.started = var1.readBool();
            } else if (var5 == 90) {
               Codec var10 = ProtobufProxy.create(PT_ATTACK_SQUAD_BOARD_USER_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.memberinfos == null) {
                  var2.memberinfos = new ArrayList();
               }

               var2.memberinfos.add((PT_ATTACK_SQUAD_BOARD_USER_INFO)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_ATTACK_SQUAD_BOARD_INFO.class);
         return this.descriptor = var1;
      }
   }
}
