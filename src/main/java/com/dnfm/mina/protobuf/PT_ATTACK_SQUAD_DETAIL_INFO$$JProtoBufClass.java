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

public class PT_ATTACK_SQUAD_DETAIL_INFO$$JProtoBufClass implements Codec<PT_ATTACK_SQUAD_DETAIL_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_ATTACK_SQUAD_DETAIL_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_ATTACK_SQUAD_DETAIL_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_ATTACK_SQUAD_DETAIL_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.rpguid)) {
         Long var19 = var1.rpguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var19);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.rpname)) {
         String var20 = var1.rpname;
         var2 += CodedOutputStream.computeStringSize(2, var20);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.membercount)) {
         Integer var21 = var1.membercount;
         var2 += CodedOutputStream.computeInt32Size(3, var21);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.antievilscore)) {
         Integer var22 = var1.antievilscore;
         var2 += CodedOutputStream.computeInt32Size(4, var22);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.publictype)) {
         Integer var23 = var1.publictype;
         var2 += CodedOutputStream.computeInt32Size(5, var23);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.status)) {
         Integer var24 = var1.status;
         var2 += CodedOutputStream.computeInt32Size(6, var24);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.phasestatus)) {
         Integer var25 = var1.phasestatus;
         var2 += CodedOutputStream.computeInt32Size(7, var25);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.changetime)) {
         Long var26 = var1.changetime;
         var2 += CodedOutputStream.computeInt64Size(8, var26);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.leadername)) {
         String var27 = var1.leadername;
         var2 += CodedOutputStream.computeStringSize(9, var27);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.leaderguid)) {
         Long var28 = var1.leaderguid;
         var2 += CodedOutputStream.computeUInt64Size(10, var28);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var29 = var1.world;
         var2 += CodedOutputStream.computeInt32Size(11, var29);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.channel)) {
         Integer var30 = var1.channel;
         var2 += CodedOutputStream.computeInt32Size(12, var30);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.ip)) {
         String var31 = var1.ip;
         var2 += CodedOutputStream.computeStringSize(13, var31);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.port)) {
         Integer var32 = var1.port;
         var2 += CodedOutputStream.computeUInt32Size(14, var32);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.users)) {
         List var33 = var1.users;
         var2 += CodedConstant.computeListSize(15, var33, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.started)) {
         Boolean var34 = var1.started;
         var2 += CodedOutputStream.computeBoolSize(16, var34);
      }

      return var2;
   }

   public void doWriteTo(PT_ATTACK_SQUAD_DETAIL_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.rpguid)) {
         Long var19 = var1.rpguid;
         if (var19 != null) {
            var2.writeUInt64(1, var19);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.rpname)) {
         String var20 = var1.rpname;
         if (var20 != null) {
            var2.writeString(2, var20);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.membercount)) {
         Integer var21 = var1.membercount;
         if (var21 != null) {
            var2.writeInt32(3, var21);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.antievilscore)) {
         Integer var22 = var1.antievilscore;
         if (var22 != null) {
            var2.writeInt32(4, var22);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.publictype)) {
         Integer var23 = var1.publictype;
         if (var23 != null) {
            var2.writeInt32(5, var23);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.status)) {
         Integer var24 = var1.status;
         if (var24 != null) {
            var2.writeInt32(6, var24);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.phasestatus)) {
         Integer var25 = var1.phasestatus;
         if (var25 != null) {
            var2.writeInt32(7, var25);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.changetime)) {
         Long var26 = var1.changetime;
         if (var26 != null) {
            var2.writeInt64(8, var26);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.leadername)) {
         String var27 = var1.leadername;
         if (var27 != null) {
            var2.writeString(9, var27);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.leaderguid)) {
         Long var28 = var1.leaderguid;
         if (var28 != null) {
            var2.writeUInt64(10, var28);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var29 = var1.world;
         if (var29 != null) {
            var2.writeInt32(11, var29);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.channel)) {
         Integer var30 = var1.channel;
         if (var30 != null) {
            var2.writeInt32(12, var30);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.ip)) {
         String var31 = var1.ip;
         if (var31 != null) {
            var2.writeString(13, var31);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.port)) {
         Integer var32 = var1.port;
         if (var32 != null) {
            var2.writeUInt32(14, var32);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.users)) {
         List var33 = var1.users;
         if (var33 != null) {
            CodedConstant.writeToList(var2, 15, FieldType.OBJECT, var33, false);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.started)) {
         Boolean var34 = var1.started;
         if (var34 != null) {
            var2.writeBool(16, var34);
         }
      }

   }

   public void writeTo(PT_ATTACK_SQUAD_DETAIL_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_ATTACK_SQUAD_DETAIL_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_ATTACK_SQUAD_DETAIL_INFO var2 = new PT_ATTACK_SQUAD_DETAIL_INFO();
      var2.users = new ArrayList();

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
               var2.phasestatus = var1.readInt32();
            } else if (var5 == 64) {
               var2.changetime = var1.readInt64();
            } else if (var5 == 74) {
               var2.leadername = var1.readString();
            } else if (var5 == 80) {
               var2.leaderguid = var1.readUInt64();
            } else if (var5 == 88) {
               var2.world = var1.readInt32();
            } else if (var5 == 96) {
               var2.channel = var1.readInt32();
            } else if (var5 == 106) {
               var2.ip = var1.readString();
            } else if (var5 == 112) {
               var2.port = var1.readUInt32();
            } else if (var5 == 122) {
               Codec var10 = ProtobufProxy.create(PT_ATTACK_SQUAD_MEMBER_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.users == null) {
                  var2.users = new ArrayList();
               }

               var2.users.add((PT_ATTACK_SQUAD_MEMBER_INFO)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 128) {
               var2.started = var1.readBool();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_ATTACK_SQUAD_DETAIL_INFO.class);
         return this.descriptor = var1;
      }
   }
}
