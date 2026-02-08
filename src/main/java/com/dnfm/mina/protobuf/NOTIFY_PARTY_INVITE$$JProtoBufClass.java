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

public class NOTIFY_PARTY_INVITE$$JProtoBufClass implements Codec<NOTIFY_PARTY_INVITE>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(NOTIFY_PARTY_INVITE var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public NOTIFY_PARTY_INVITE decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(NOTIFY_PARTY_INVITE var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var25 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var25);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var26 = var1.partyguid;
         var2 += CodedOutputStream.computeUInt64Size(2, var26);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.equipscore)) {
         Integer var27 = var1.equipscore;
         var2 += CodedOutputStream.computeInt32Size(3, var27);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.gname)) {
         String var28 = var1.gname;
         var2 += CodedOutputStream.computeStringSize(4, var28);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var29 = var1.growtype;
         var2 += CodedOutputStream.computeInt32Size(5, var29);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.guild)) {
         Long var30 = var1.guild;
         var2 += CodedOutputStream.computeUInt64Size(6, var30);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var31 = var1.job;
         var2 += CodedOutputStream.computeInt32Size(7, var31);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var32 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(8, var32);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var33 = var1.name;
         var2 += CodedOutputStream.computeStringSize(9, var33);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.leaderguid)) {
         Long var34 = var1.leaderguid;
         var2 += CodedOutputStream.computeUInt64Size(10, var34);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.secgrowtype)) {
         Integer var35 = var1.secgrowtype;
         var2 += CodedOutputStream.computeInt32Size(11, var35);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var36 = var1.dungeonindex;
         var2 += CodedOutputStream.computeInt32Size(12, var36);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.partyname)) {
         String var37 = var1.partyname;
         var2 += CodedOutputStream.computeStringSize(13, var37);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.targetguid)) {
         Long var38 = var1.targetguid;
         var2 += CodedOutputStream.computeUInt64Size(14, var38);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.ip)) {
         String var39 = var1.ip;
         var2 += CodedOutputStream.computeStringSize(15, var39);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.port)) {
         Integer var40 = var1.port;
         var2 += CodedOutputStream.computeInt32Size(16, var40);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.friendstatus)) {
         Boolean var41 = var1.friendstatus;
         var2 += CodedOutputStream.computeBoolSize(17, var41);
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var42 = var1.grade;
         var2 += CodedOutputStream.computeInt32Size(18, var42);
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.creditscore)) {
         Integer var43 = var1.creditscore;
         var2 += CodedOutputStream.computeInt32Size(19, var43);
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var44 = var1.world;
         var2 += CodedOutputStream.computeInt32Size(20, var44);
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.channel)) {
         Integer var45 = var1.channel;
         var2 += CodedOutputStream.computeInt32Size(21, var45);
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.users)) {
         List var46 = var1.users;
         var2 += CodedConstant.computeListSize(22, var46, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(NOTIFY_PARTY_INVITE var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var25 = var1.error;
         if (var25 != null) {
            var2.writeInt32(1, var25);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var26 = var1.partyguid;
         if (var26 != null) {
            var2.writeUInt64(2, var26);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.equipscore)) {
         Integer var27 = var1.equipscore;
         if (var27 != null) {
            var2.writeInt32(3, var27);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.gname)) {
         String var28 = var1.gname;
         if (var28 != null) {
            var2.writeString(4, var28);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var29 = var1.growtype;
         if (var29 != null) {
            var2.writeInt32(5, var29);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.guild)) {
         Long var30 = var1.guild;
         if (var30 != null) {
            var2.writeUInt64(6, var30);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var31 = var1.job;
         if (var31 != null) {
            var2.writeInt32(7, var31);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var32 = var1.level;
         if (var32 != null) {
            var2.writeInt32(8, var32);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var33 = var1.name;
         if (var33 != null) {
            var2.writeString(9, var33);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.leaderguid)) {
         Long var34 = var1.leaderguid;
         if (var34 != null) {
            var2.writeUInt64(10, var34);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.secgrowtype)) {
         Integer var35 = var1.secgrowtype;
         if (var35 != null) {
            var2.writeInt32(11, var35);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var36 = var1.dungeonindex;
         if (var36 != null) {
            var2.writeInt32(12, var36);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.partyname)) {
         String var37 = var1.partyname;
         if (var37 != null) {
            var2.writeString(13, var37);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.targetguid)) {
         Long var38 = var1.targetguid;
         if (var38 != null) {
            var2.writeUInt64(14, var38);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.ip)) {
         String var39 = var1.ip;
         if (var39 != null) {
            var2.writeString(15, var39);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.port)) {
         Integer var40 = var1.port;
         if (var40 != null) {
            var2.writeInt32(16, var40);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.friendstatus)) {
         Boolean var41 = var1.friendstatus;
         if (var41 != null) {
            var2.writeBool(17, var41);
         }
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var42 = var1.grade;
         if (var42 != null) {
            var2.writeInt32(18, var42);
         }
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.creditscore)) {
         Integer var43 = var1.creditscore;
         if (var43 != null) {
            var2.writeInt32(19, var43);
         }
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var44 = var1.world;
         if (var44 != null) {
            var2.writeInt32(20, var44);
         }
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.channel)) {
         Integer var45 = var1.channel;
         if (var45 != null) {
            var2.writeInt32(21, var45);
         }
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.users)) {
         List var46 = var1.users;
         if (var46 != null) {
            CodedConstant.writeToList(var2, 22, FieldType.OBJECT, var46, false);
         }
      }

   }

   public void writeTo(NOTIFY_PARTY_INVITE var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public NOTIFY_PARTY_INVITE readFrom(CodedInputStream var1) throws IOException {
      NOTIFY_PARTY_INVITE var2 = new NOTIFY_PARTY_INVITE();
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
               var2.error = var1.readInt32();
            } else if (var5 == 16) {
               var2.partyguid = var1.readUInt64();
            } else if (var5 == 24) {
               var2.equipscore = var1.readInt32();
            } else if (var5 == 34) {
               var2.gname = var1.readString();
            } else if (var5 == 40) {
               var2.growtype = var1.readInt32();
            } else if (var5 == 48) {
               var2.guild = var1.readUInt64();
            } else if (var5 == 56) {
               var2.job = var1.readInt32();
            } else if (var5 == 64) {
               var2.level = var1.readInt32();
            } else if (var5 == 74) {
               var2.name = var1.readString();
            } else if (var5 == 80) {
               var2.leaderguid = var1.readUInt64();
            } else if (var5 == 88) {
               var2.secgrowtype = var1.readInt32();
            } else if (var5 == 96) {
               var2.dungeonindex = var1.readInt32();
            } else if (var5 == 106) {
               var2.partyname = var1.readString();
            } else if (var5 == 112) {
               var2.targetguid = var1.readUInt64();
            } else if (var5 == 122) {
               var2.ip = var1.readString();
            } else if (var5 == 128) {
               var2.port = var1.readInt32();
            } else if (var5 == 136) {
               var2.friendstatus = var1.readBool();
            } else if (var5 == 144) {
               var2.grade = var1.readInt32();
            } else if (var5 == 152) {
               var2.creditscore = var1.readInt32();
            } else if (var5 == 160) {
               var2.world = var1.readInt32();
            } else if (var5 == 168) {
               var2.channel = var1.readInt32();
            } else if (var5 == 178) {
               Codec var10 = ProtobufProxy.create(PT_GROUP_MEMBER.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.users == null) {
                  var2.users = new ArrayList();
               }

               var2.users.add((PT_GROUP_MEMBER)var10.readFrom(var1));
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(NOTIFY_PARTY_INVITE.class);
         return this.descriptor = var1;
      }
   }
}
